package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.saleflow;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeItemDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @ScriptName LM_PrivilegeCrossReference.java
 * @Date:Mar 22, 2011
 * @Preconditions: There exist at least 4 privileges (#1, #2, #3 and #4) without inventory setup in the system: 
 *               1.Privilege#1 has the following rule setup for privilege#3: 
 *               Cannot purchase IF parameter privilege on file 
 *               2.Privilege#2 has the following rule setup for privilege#3 and #4: 
 *               Cannot purchase IF ALL parameter privileges on file 
 *               3.Privilege#3 and #4 have no any rules' setup.
 * 'd_hf_add_pri_business_rule' ID:80,90
 * @author Sophia
 * 
 */
public class PrivilegeCrossReference extends LicenseManagerTestCase {
	private String[] privilege;
	private PrivilegeInfo privilegeInfo_1 = new PrivilegeInfo();
	private PrivilegeInfo privilegeInfo_2 = new PrivilegeInfo();
	private PrivilegeInfo privilegeInfo_3 = new PrivilegeInfo();
	private PrivilegeInfo privilegeInfo_4 = new PrivilegeInfo();
	private String reactiveError;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// Add new customer
		lm.createNewCustomer(cust);
		
		// Purchase Privilege from customer profile page
		lm.gotoAddItemPgFromCustomerTopMenu(cust);
		
		// Verify Privilege#1, #2, #3 and #4 displayed in the list
		lm.verifyPrivilegeDisplayOnThePurchaseList(true, privilege);
		
		// Verify Privilege#1, #2, #3 and #4 displayed in the list
		lm.addPrivilegeItem(privilegeInfo_1, privilegeInfo_2);
		lm.verifyPrivilegeDisplayOnThePurchaseList(true, privilege);
		
		// Verify Privilege#1 is not displayed in the list
		// Privilege#2, #3 and #4 are displayed in the list
		lm.addPrivilegeItem(privilegeInfo_3);
		lm.verifyPrivilegeDisplayOnThePurchaseList(false, privilege[0]);
		lm.verifyPrivilegeDisplayOnThePurchaseList(true, privilege[1], privilege[2], privilege[3]);
		
		// Verify Privilege#1 and #2 are not displayed in the list
		// Privilege#3 and #4 are displayed in the list
		lm.addPrivilegeItem(privilegeInfo_4);
		lm.verifyPrivilegeDisplayOnThePurchaseList(false, privilege[0], privilege[1]);
		lm.verifyPrivilegeDisplayOnThePurchaseList(true, privilege[2], privilege[3]);
		lm.goToCart();
		lm.processOrderToOrderSummary(pay);

		// Verify processing order cart failed due to cross reference rule failed
		this.verifyProcessOrderErrorMessage("Error 1001: Cannot purchase "
				+ privilege[0].split("-")[0] + " - "
				+ privilege[0].split("-")[1] + " due to "
				+ privilege[2].split("-")[0] + " - "
				+ privilege[2].split("-")[1] + " on file");
		lm.removeItemInTheOrderCart(privilege[0], privilege[1], privilege[3]);
		String order_1 = lm.processOrderAfterUpdateAmount(pay).split(" ")[0];

		// Purchase Privilege from home page
		lm.gotoAddItemPgFromPrivilegeQuickSearch(cust);
		
		// Verify Privilege#1 is not displayed in the list, 
		// Privilege#2, #3 and #4 are displayed in the list
		lm.verifyPrivilegeDisplayOnThePurchaseList(false, privilege[0]);
		lm.verifyPrivilegeDisplayOnThePurchaseList(true, privilege[1], privilege[2], privilege[3]);
		
		// Verify Privilege#1 and #2 are not displayed in the list, 
		// Privilege#3 and #4 are displayed in the list
		lm.addPrivilegeItem(privilegeInfo_4);
		lm.verifyPrivilegeDisplayOnThePurchaseList(false, privilege[0], privilege[1]);
		lm.verifyPrivilegeDisplayOnThePurchaseList(true, privilege[2], privilege[3]);
		lm.goToCart();
		String order_2 = lm.processOrderCart(pay).split(" ")[0];
		
		// Verify Privilege#1 and #2 are not displayed in the list, 
		// Privilege#3 and #4 are displayed in the list.
		lm.gotoAddItemPgFromCustomersQuickSearch(cust);
		lm.verifyPrivilegeDisplayOnThePurchaseList(false, privilege[0], privilege[1]);
		lm.verifyPrivilegeDisplayOnThePurchaseList(true, privilege[2], privilege[3]);
		lm.gotoHomePage();

		// rules checking(invalidate/reactivate privilege)
		lm.gotoPrivSearchPgFromCustomerPg();
		
		// invalidate privilege#3 in order#1
		privilegeInfo_3.privilegeId = lm.searchAndGotoPrivilegeItemDetail(
				order_1, privilegeInfo_3.purchasingName,
				privilegeInfo_3.licenseYear);
		lm.invalidatePrivilegeItem("01 - Operator Error", "qa auto test");
		lm.gotoPrivSearchPgFromPrivItemDetailPg();
		
		// invalidate privilege#4 in order#2
		privilegeInfo_4.privilegeId = lm.searchAndGotoPrivilegeItemDetail(
				order_2, privilegeInfo_4.purchasingName,
				privilegeInfo_4.licenseYear);
		lm.invalidatePrivilegeItem("01 - Operator Error", "qa auto test");
		lm.gotoAddItemPgFromCustomerTopMenu(cust);
		
		// Verify Privilege#1, #2, #3 and #4 displayed in the list
		lm.verifyPrivilegeDisplayOnThePurchaseList(true, privilege);
		lm.addPrivilegeItem(privilegeInfo_1, privilegeInfo_2);
		lm.goToCart();
		String order_3 = lm.processOrderCart(pay).split(" ")[0];
		
		// reactivate privilege#3 in order#1
		lm.gotoPrivSearchPgFromCustomerPg();
		lm.searchAndGotoPrivilegeItemDetail(privilegeInfo_3.privilegeId);
		lm.reactivatePrivilegeItem("01 - Operator Error", "qa auto test");
		
		// Verify privilege#3 is reactivated successfully
		lm.verifyPrivilegeStatusFromDB(privilegeInfo_3.privilegeId, PRIV_STATUS_ACTIVE, schema);
		
		// reactivate privilege#4 in order#2
		lm.gotoPrivSearchPgFromPrivItemDetailPg();
		lm.searchAndGotoPrivilegeItemDetail(privilegeInfo_4.privilegeId);
		lm.reactivatePrivilegeItem("01 - Operator Error", "qa auto test");
		
		// Verify privilege#4 is reactivated successfully
		lm.verifyPrivilegeStatusFromDB(privilegeInfo_4.privilegeId, PRIV_STATUS_ACTIVE, schema);
		
		// invalidate and reactive privilege#1 in order#3
		lm.gotoPrivSearchPgFromPrivItemDetailPg();
		privilegeInfo_1.privilegeId = lm.searchAndGotoPrivilegeItemDetail(
				order_3, privilegeInfo_1.purchasingName,
				privilegeInfo_1.licenseYear);
		lm.invalidatePrivilegeItem("01 - Operator Error", "qa auto test");
		lm.reactivatePrivilegeItem("01 - Operator Error", "qa auto test");
		
		// Verify it is failed to reactivate privilege#1 due to cross reference rule failed
		this.verifyReactivePrivilegeErrorMessage(reactiveError);
		lm.verifyPrivilegeStatusFromDB(privilegeInfo_1.privilegeId, PRIV_STATUS_INVALID, schema);
		
		// invalidate and reactive privilege#2 in order#3
		lm.gotoPrivSearchPgFromPrivItemDetailPg();
		privilegeInfo_2.privilegeId = lm.searchAndGotoPrivilegeItemDetail(
				order_3, privilegeInfo_2.purchasingName,
				privilegeInfo_2.licenseYear);
		lm.invalidatePrivilegeItem("01 - Operator Error", "qa auto test");
		lm.reactivatePrivilegeItem("01 - Operator Error", "qa auto test");
		
		// Verify it is failed to reactivate privilege#2 due to cross reference rule failed
		this.verifyReactivePrivilegeErrorMessage(reactiveError);
		lm.verifyPrivilegeStatusFromDB(privilegeInfo_2.privilegeId,
				PRIV_STATUS_INVALID, schema);
		lm.gotoHomePage();

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/WAL-MART";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		String fiscalYear = lm.getFiscalYear(schema);
		
		cust.setDefaultValuesForLMCust();
		
		privilegeInfo_1.purchasingName = "119-LM_VerifyCrossReference01";
		privilegeInfo_1.qty = "1";
		privilegeInfo_1.licenseYear = fiscalYear;
		
		privilegeInfo_2.purchasingName = "696-LM_VerifyCrossReference02";
		privilegeInfo_2.qty = "1";
		privilegeInfo_2.licenseYear = fiscalYear;
		
		privilegeInfo_3.purchasingName = "929-LM_VerifyCrossReference03";
		privilegeInfo_3.qty = "1";
		privilegeInfo_3.licenseYear = fiscalYear;
		
		privilegeInfo_4.purchasingName = "529-LM_VerifyCrossReference04";
		privilegeInfo_4.qty = "1";
		privilegeInfo_4.licenseYear = fiscalYear;

		privilege = new String[4];
		privilege[0] = privilegeInfo_1.purchasingName;
		privilege[1] = privilegeInfo_2.purchasingName;
		privilege[2] = privilegeInfo_3.purchasingName;
		privilege[3] = privilegeInfo_4.purchasingName;

		reactiveError = "The reactivation of the Privilege is in violation of the Business Rules. The Privilege cannot be reactivated.";
	}

	private void verifyProcessOrderErrorMessage(String error) {
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();
		
		logger.info("Verify processing order cart under rule control...");
		String message = ordCartPg.getWarningMessage();
		this.verifyErrorMessage(error, message);
	}

	private void verifyReactivePrivilegeErrorMessage(String error) {
		LicMgrPrivilegeItemDetailPage privilegeItemDetailPg = LicMgrPrivilegeItemDetailPage .getInstance();
		
		logger.info("Verify reactive privilege under rule control...");
		String message = privilegeItemDetailPg.getErrorMsg();
		this.verifyErrorMessage(error, message);
	}
	
	private void verifyErrorMessage(String expect, String actual){
		if (MiscFunctions.compareResult("---Error message", expect, actual)) {
			logger.info("Can not reactive privilege due to cross reference rule.");
		} else {
			throw new ErrorOnPageException("The rule for privilege failed to take effect.");
		}
	}
}