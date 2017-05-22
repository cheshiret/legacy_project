/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.transfer;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrIdentifyCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrResidencyStatusSelectionWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeItemDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrAlertDialogWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case was designed to verify alert dialog when no applied transfer pricing for transfer privilege from individual customer in MS State to individual customer in FL State 
 * @Preconditions: 
 * 				(1) Privilege product: PP8 - PriParticular8
 * 				(2) Privilege pricing: Original, Transfer
 * 					For transfer pricing, it should applied to specified State/Province: AB;
 * 				(3) Individual customer: QA-IndivFLCust2, Test-IndivFLCust2, Passport 19900803 Canada; FL State;
 * 				(4) Individual customer: QA-IndivMSCust, Test-IndivMSCust, Passport 19900801 Canada; MS State
 * 
 * @SPEC:TC:42674
 * @Task#:AUTO-1241
 * @author Jane Wang
 * @Date  Sep 24, 2012
 */
public class IndivFLTransToIndivMSWithABStatePrice extends
		LicenseManagerTestCase {

	private Customer toCust = new Customer();
	private String alertMsg = "The Privilege Instance cannot be transferred because of missing pricing information.";
	
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
		verifyNoTransferPricingLaertDialog();
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/HUGH WHITE";
		login.station = "Gate House 1 AM";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		// Product has transfer pricing for AB State
		privilege.code = "PP8";
		privilege.name = "PriParticular8";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;// IMPORTANT: precondition, don't change!
		cust.fName = "QA-IndivFLCust2";
		cust.lName = "Test-IndivFLCust2";
		cust.dateOfBirth = "1990-8-3";
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "19900803";// IMPORTANT: the same with support script.
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		toCust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;// IMPORTANT: precondition, don't change!
		toCust.fName = "QA-IndivMSCust";
		toCust.lName = "Test-IndivMSCust";
		toCust.dateOfBirth = "1990-8-1";
		toCust.identifier.identifierType = "Passport";
		toCust.identifier.identifierNum = "19900801";// IMPORTANT: the same with support script.
		toCust.identifier.country = "Canada";
		toCust.residencyStatus = "Non Resident";
	}
	
	private void verifyNoTransferPricingLaertDialog(){
		LicMgrPrivilegeItemDetailPage itemPg = LicMgrPrivilegeItemDetailPage.getInstance();
		LicMgrIdentifyCustomerPage identifyCustPg = LicMgrIdentifyCustomerPage.getInstance();
		LicMgrConfirmCustomerPage confirmCustPg = LicMgrConfirmCustomerPage.getInstance();
		LicMgrResidencyStatusSelectionWidget residencyWidget = LicMgrResidencyStatusSelectionWidget.getInstance();
		LicMgrAlertDialogWidget alert = LicMgrAlertDialogWidget.getInstance();
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		
		logger.info("Transfer privilege To Customer.");

		itemPg.clickTransfer();
		ajax.waitLoading();
		identifyCustPg.waitLoading();
		identifyCustPg.identifyCustomer(toCust);
		identifyCustPg.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(confirmCustPg, alert);
		if (page == confirmCustPg) {
			confirmCustPg.clickOK();
			ajax.waitLoading();
			residencyWidget.waitLoading();
			residencyWidget.selectNonResident();
			residencyWidget.clickOK();
			ajax.waitLoading();
			alert.waitLoading();
		}
		logger.info("------Verify alert dialog existed for no transfer pricing.");
		
		String actualMsg = alert.getMessage();
		if(StringUtil.isEmpty(actualMsg) || !actualMsg.equals(alertMsg))
			throw new ErrorOnPageException("Confirm dialog content was not correct.",alertMsg, actualMsg);
		logger.info("-----Verify alert msg successfully.");
		
		alert.clickOK();
		ajax.waitLoading();
		homePg.waitLoading();
	}

}
