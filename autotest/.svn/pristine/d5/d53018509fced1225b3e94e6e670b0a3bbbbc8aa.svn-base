package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.saleflow;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrVoidUndoVoidPrivilegePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 *
 * @Description:
 * @Preconditions: Privilege has print document.
 * @SPEC:
 * @Task#:
 *
 * @author QA-qchen
 * @Date  Mar 31, 2011
 */
public class PrivilegeWithInventory extends LicenseManagerTestCase {
	private PrivilegeInfo privilege1 = new PrivilegeInfo();
	private Suspension suspension = new Suspension();
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		/**
		 * Make a new privilege order
		 */
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		lm.verifyAddedCorrectPrivilegeAndQty(privilege.purchasingName, privilege.licenseYear, privilege.qty, 1);
		int availableCountBefore = lm.getAvailablePrivInventoryCount(schema, privilege.invType, privilege.purchasingName, privilege.licenseYear, login.location.split("/")[1].trim());
		// set privilege number to next number
		lm.addMorePrivilegeFromCartToCart(privilege1);
		lm.verifyAddedCorrectPrivilegeAndQty(privilege1.purchasingName, privilege1.licenseYear, privilege1.qty, 2);
		String privilegeOrdNum = lm.processOrderCart(pay, true);// must print document, it needs to void this order an the end and verify status
        if(privilegeOrdNum.contains(" ")){
        	privilegeOrdNum = privilegeOrdNum.split(" ")[0];
        }
		lm.verifyOrderStatus(privilegeOrdNum, ORD_STATUS_ACTIVE, schema);
		int availableCountAfter = lm.getAvailablePrivInventoryCount(schema, privilege.invType, privilege.purchasingName, privilege.licenseYear, login.location.split("/")[1].trim());
		if(availableCountBefore - availableCountAfter != 2) {
			throw new ErrorOnDataException("The total available inventory count should be decreased by 2.");
		}

		/**
		 * Invalidate/Reactivate privilege order
		 */
		lm.gotoOrderPageFromOrdersQuickSearch(privilegeOrdNum);
		String privilegeItemNums = privOrderDetailsPage.getAllPrivilegesNum();
		
		String privilegeItemNum1 = privilegeItemNums.split(" ")[0];
		String privilegeItemNum2 = privilegeItemNums.split(" ")[1];
		lm.invalidatePrivilegeOrder(privilege.operateReason, privilege.operateNote);
		lm.verifyAllPrivilegesStatus(privilegeOrdNum, PRIV_STATUS_INVALID, schema);
		lm.verifyPrivilegeInventoryStatus(schema, PRIV_INV_STATUS_SOLD_CODE, privilege, privilege1);//23. Verify all privilege inventory are not released
		lm.reactivatePrivilegeOrder(privilege.operateReason, privilege.operateNote);
		lm.verifyAllPrivilegesStatus(privilegeOrdNum, PRIV_STATUS_ACTIVE, schema);
		lm.gotoHomePage();

		/**
		 * Suspension/Revoke/Invalidate/Reactivate
		 */
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.manageSuspensions("Add", suspension);
		lm.verifyAllPrivilegesStatus(privilegeOrdNum, PRIV_STATUS_REVOKED, schema);
		lm.verifyPrivilegeInventoryStatus(schema, PRIV_INV_STATUS_SOLD_CODE, privilege, privilege1);//45. Verify all privilege inventory are not released
		lm.manageSuspensions("Deactivate", suspension);
		lm.verifyAllPrivilegesStatus(privilegeOrdNum, PRIV_STATUS_REVOKED, schema);
		lm.manageSuspensions("Remove", suspension);
		lm.verifyAllPrivilegesStatus(privilegeOrdNum, PRIV_STATUS_REVOKED, schema);

		lm.invalidatePrivilegeFromCustomerPrivilegePage(privilegeItemNum1,privilege.licenseYear, privilege.operateReason, privilege.operateNote);
		lm.verifyPrivilegeStatusFromDB(privilegeItemNum1, PRIV_STATUS_INVALID, schema);
		lm.verifyPrivilegeStatusFromUI(INVALID_STATUS);
		lm.reactivatePrivilegeItem(privilege.operateReason, privilege.operateNote);
		lm.verifyPrivilegeStatusFromDB(privilegeItemNum1, PRIV_STATUS_ACTIVE, schema);
		lm.verifyPrivilegeStatusFromUI(ACTIVE_STATUS);

		lm.gotoPrivSearchPgFromPrivItemDetailPg();
		lm.searchAndGotoPrivilegeItemDetail(privilegeItemNum2);
		lm.reactivatePrivilegeItem(privilege.operateReason, privilege.operateNote);
		lm.verifyPrivilegeStatusFromDB(privilegeItemNum2, PRIV_STATUS_ACTIVE, schema);
		lm.verifyPrivilegeStatusFromUI(ACTIVE_STATUS);

		/**
		 * Duplicate - not allowed
		 */
		lm.gotoCustomerDetailFromTopMenu(cust);
		lm.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
		lm.verifyPrivilegeOrderCannotBeReplaced(privilegeOrdNum);
		lm.gotoHomePage();

		/**
		 * Transfer - not allowed
		 */
		lm.gotoOrderPageFromOrdersQuickSearch(privilegeOrdNum);
		lm.gotoPrivilegeDetailFromOrderPg(privilegeItemNum1);
		lm.verifyPrivilegeItemCannotBeTransferred("The Privilege Instance cannot be transferred because it has an associated inventory.");
		lm.gotoHomePage();

		/**
		 * Void/Undo-void (undo-void is not allowed)
		 */
		lm.gotoOrderPageFromOrdersQuickSearch(privilegeOrdNum);
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		if(LicMgrVoidUndoVoidPrivilegePage.getInstance().exists()){
			lm.gotoHomePage();
		} else {
			lm.processOrderCart(pay);
		}
		lm.gotoOrderPageFromOrdersQuickSearch(privilegeOrdNum);
		lm.gotoPrivilegeDetailFromOrderPg(privilegeItemNum2);
		lm.verifyPrivilegeStatusFromUI(VOIDED_STATUS);
		lm.verifyPrivilegeStatusFromDB(privilegeItemNum2, PRIV_STATUS_VOIDED, schema);
		lm.gotoHomePage();

		lm.gotoOrderPageFromOrdersQuickSearch(privilegeOrdNum);
		lm.gotoUndoVoidPageFromOrderPg();
		lm.verifyPrivilegeCannotBeUndoVoided();
		lm.gotoHomePage();

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("MS", env);
		String fiscalYear = lm.getFiscalYear(schema);
		TimeZone tz = DataBaseFunctions.getContractTimeZone(schema);
		
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";

		cust.customerClass = "Individual";
		cust.dateOfBirth = "19880608";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "321456";
		cust.identifier.country="Canada";
		cust.residencyStatus = "Non Resident";
		cust.lName = "Test-Sanity4";
		cust.fName = "QA-Sanity4";

		privilege.invType = "Sanity_Inv";
		privilege.purchasingName = "614-LM_PrivilegeWithInventory";
		privilege.licenseYear = fiscalYear;
		privilege.qty = "1";
		privilege.operateReason = "";
		privilege.operateNote = "QA Automation";

		privilege1.invType = "Sanity_Inv";
		privilege1.purchasingName = "614-LM_PrivilegeWithInventory";
		privilege1.licenseYear = fiscalYear;
		privilege1.qty = "1";
		privilege1.operateReason = "";
		privilege1.operateNote = "QA Automation";

		suspension.type = "Bad Check Suspension";
		suspension.beginDate = DateFunctions.getToday(tz);
		suspension.datePosted = DateFunctions.getToday(tz);
	}
}
