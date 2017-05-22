/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.transfer;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.NumberUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case was designed to verify transfer privilege price from business customer in MS State to business customer in FL State,
 * and there are two transfer pricing setup, one applied to All State and the other one applied to FL State, system should use price applied to FL State; 
 * @Preconditions: 
 * 				(1) Privilege product: PP3 - PriParticular3
 * 				(2) Privilege pricing: Original, Transfer
 * 					For transfer pricing, there are two transfer pricing setup, one applied to All State and the other one applied to FL State;
 * 				(3) Business customer: QA-BusiFLCust2, Test-BusiFLCust2, Passport 19910803 Canada; FL State;
 * 				(4) Business customer: QA-BusiMSCust2, Test-BusiMSCust2, Passport 19910804 Canada; MS State
 * 
 * @SPEC:TC:42674
 * @Task#:AUTO-1241
 * @author Jane Wang
 * @Date  Sep 24, 2012
 */
public class BusiMSTransToBusiFLWithAllAndFLStatePrice extends LicenseManagerTestCase {
	private Customer toCust = new Customer();
	
	public void execute() {
		lm.loginLicenseManager(login);
		
		// make a privilege order
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String ordNum1 = lm.processOrderCart(pay);
		// get privilege number
		String privilegeNum = lm.getPrivilegeNumByOrdNum(schema, ordNum1);

		// transfer privilege order
		lm.gotoOrderPageFromOrdersTopMenu(ordNum1);
		lm.gotoPrivilegeDetailFromOrderPg(privilegeNum);
		lm.transferPrivToOrderCart(toCust, privilege);
		// verify transfer pricing
		verifyTransferPricing();
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/HUGH WHITE";
		login.station = "Gate House 1 AM";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		privilege.code = "PP3";
		privilege.name = "PriParticular3";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		
		cust.customerClass = OrmsConstants.BUSINESS_CUSTOMER_CLASS;// IMPORTANT: precondition, don't change!
		cust.fName = "QA-BusiMSCust2";
		cust.lName = "Test-BusiMSCust2";
		cust.dateOfBirth = "1991-8-4";
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "19910804";// IMPORTANT: the same with support script.
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		toCust.customerClass = OrmsConstants.BUSINESS_CUSTOMER_CLASS;// IMPORTANT: precondition, don't change!
		toCust.fName = "QA-BusiFLCust2";
		toCust.lName = "Test-BusiFLCust2";
		toCust.dateOfBirth = "1991-8-3";
		toCust.identifier.identifierType = "Passport";
		toCust.identifier.identifierNum = "19910803";// IMPORTANT: the same with support script.
		toCust.identifier.country = "Canada";
		toCust.residencyStatus = "Non Resident";
	}
	
	private void verifyTransferPricing(){
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		
		Double actualValue = ordCartPg.getOrderTotalSum();
		// Product has Transfer fee which created for All State and FL State
		Double expectedValue = Double.valueOf(lm.getPriCustPrice(schema, privilege.code, TRANSFER_PURCHASE_TYPE_ID, OrmsConstants.STATUS_INACTIVE));
		if(!NumberUtil.valueEquals(expectedValue, actualValue))
			throw new ErrorOnPageException("Transfer pricing applied un-correctly.", expectedValue, actualValue);
		logger.info("------Verify Transfer pricing applied correctly.");
	}
}
