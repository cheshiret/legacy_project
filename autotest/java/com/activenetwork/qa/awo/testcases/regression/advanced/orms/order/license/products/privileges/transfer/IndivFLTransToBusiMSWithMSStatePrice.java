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
 * @Description:This test case was designed to verify transfer privilege price from individual customer in FL State to business customer in MS State,
 * and only one transfer pricing setup, which was applied to MS State, system should use this price; 
 * @Preconditions: 
 * 				(1) Privilege product: PP2 - PriParticular2
 * 				(2) Privilege pricing: Original, Transfer
 * 					For transfer pricing, only one transfer pricing setup, which was applied to MS State;
 * 				(3) Individual customer: QA-IndivFLCust1, Test-IndivFLCust1, Passport 19900801 Canada; FL State;
 * 				(4) Business customer: QA-BusiMSCust1, Test-BusiMSCust1, Passport 19910802 Canada; MS State
 * 
 * @SPEC:TC:42674
 * @Task#:AUTO-1241
 * @author Jane Wang
 * @Date  Sep 24, 2012
 */
public class IndivFLTransToBusiMSWithMSStatePrice extends LicenseManagerTestCase {
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
//		String ordNum2 = lm.processOrderCart(pay);
		
		// clean up
//		lm.gotoPrivilegeOrderDetailPage(ordNum2);
//		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
//		lm.processOrderCart(pay);
//		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/HUGH WHITE";
		login.station = "Gate House 1 AM";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		privilege.code = "PP2";
		privilege.name = "PriParticular2";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;// IMPORTANT: precondition, don't change!
		cust.fName = "QA-IndivFLCust1";
		cust.lName = "Test-IndivFLCust1";
		cust.dateOfBirth = "1990-8-2";
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "19900802";// IMPORTANT: the same with support script.
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		toCust.customerClass = OrmsConstants.BUSINESS_CUSTOMER_CLASS;// IMPORTANT: precondition, don't change!
		toCust.fName = "QA-BusiMSCust1";
		toCust.lName = "Test-BusiMSCust1";
		toCust.dateOfBirth = "1991-8-2";
		toCust.identifier.identifierType = "Passport";
		toCust.identifier.identifierNum = "19910802";// IMPORTANT: the same with support script.
		toCust.identifier.country = "Canada";
		toCust.residencyStatus = "Non Resident";
	}
	
	private void verifyTransferPricing(){
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		
		Double actualValue = ordCartPg.getOrderTotalSum();
		// transfer of pricing is applied to MS State
		Double expectedValue = Double.valueOf(lm.getPriCustPrice(schema, privilege.code, TRANSFER_PURCHASE_TYPE_ID, OrmsConstants.STATUS_INACTIVE));
		if(!NumberUtil.valueEquals(expectedValue, actualValue))
			throw new ErrorOnPageException("Transfer pricing applied un-correctly.", expectedValue, actualValue);
		logger.info("------Verify Transfer pricing applied correctly.");
	}
}
