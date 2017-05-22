package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.duplicate;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 *
 * @Description:
 * @Preconditions:
 * 							Steps: 1. make a duplicated privilege order with printing document;
 * 									  2. go to order detail page, verify the status is 'Active' and 'Reprint' button is Enabled;
 * 									  3. invalidate the privilege instance(Not order), go to order detail page, verify the status is 'Invalid' and 'Reprint' button is Disabled;
 * 									  4. reactivate the privilege instance(Not order), go to order detail page, verify the status is 'Active' and 'Reprint' button is Enabled;
 * 									  5. void this order, go to order detail page, verify the status is 'Voided' and 'Reprint' button is Disabled;
 * 									  5. undo void this order and verify the status is 'Active' and 'Reprint' button is Enabled;
 * 									  6. go to customer suspension sub page and add a suspension to make this order 'Revoked' and 'Reprint' button is Disabled;
 * 									  7. verify the reprint count is less than or equal to the [Maximum Reprint Count in its associated Document Template PLUS 1]
 * @SPEC: <<Get Privilege List for Reprint.doc>>
 * @Task#: AUTO-881
 *
 * @author qchen
 * @Date  Mar 21, 2012
 */
public class GetListForReprint extends LicenseManagerTestCase {
	private LicMgrPrivilegeOrderDetailsPage orderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private Suspension suspension = new Suspension();
	
	@Override
	public void execute() {
		lm.checkPrivilegesExist(schema, privilege.code);

		lm.loginLicenseManager(login);
		//1. duplicate a privilege order with printing document
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay);
		lm.replacePrivilegeToCartFromCustQuickSearch(cust, orderNum, privilege);
		orderNum = lm.processOrderCart(pay);

		//2. go to order detail to verify the 'Reprint' button is Enabled
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String privilegeNum = orderDetailsPage.getAllPrivilegesNum();
		orderDetailsPage.verifyIfReprintButtonEnabled(OrmsConstants.ACTIVE_STATUS, true);

		//3. invalidate this order to 'Invalid', then verify the 'Reprint' button is Disabled
		lm.gotoPrivilegeDetailFromOrderPg(privilegeNum);
		lm.invalidatePrivilegeItem(privilege.operateReason, privilege.operateNote);
		lm.gotoPrivilegeOrderDetailPageFromTopMenu(orderNum);
		orderDetailsPage.verifyIfReprintButtonEnabled(OrmsConstants.INVALID_STATUS, false);

		//4. reactivate this order back to 'Active', verify the 'Reprint' button is Enabled
		lm.gotoPrivilegeDetailFromOrderPg(privilegeNum);
		lm.reactivatePrivilegeItem(privilege.operateReason, privilege.operateNote);
		lm.gotoPrivilegeOrderDetailPageFromTopMenu(orderNum);
		orderDetailsPage.verifyIfReprintButtonEnabled(OrmsConstants.ACTIVE_STATUS, true);

		//5. void this order to 'Voided', verify the 'Reprint' button is Disabled
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay, false);
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		orderDetailsPage.verifyIfReprintButtonEnabled(OrmsConstants.VOIDED_STATUS, false);

		//6. undo void order back to 'Active', verify the 'Reprint' button is Enabled
		lm.undoReversePrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay, false);
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		orderDetailsPage.verifyIfReprintButtonEnabled(OrmsConstants.ACTIVE_STATUS, true);

		//7. add a suspension record for the customer to make order status as 'Revoked', then verify the 'Reprint' button is Disabled
		lm.gotoCustomerDetailFromTopMenu(cust);
		lm.gotoCustomerSubTabPage("Suspensions");
		lm.addCustomerSuspension(suspension);
		lm.gotoPrivilegeOrderDetailPageFromTopMenu(orderNum);
		orderDetailsPage.verifyIfReprintButtonEnabled(OrmsConstants.REVOKED_STATUS, false);
		
		//8. clean up: remove the suspension
		lm.gotoCustomerDetailFromTopMenu(cust);
		lm.gotoCustomerSubTabPage("Suspensions");
		lm.removeCustomerSuspension(suspension);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		privilege.code = "GLR";
		privilege.name = "GetListForReprint";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.qty = "1";
		privilege.operateReason = "";
		privilege.operateNote = "Automation regression test";

		cust.customerClass = "Individual";
		cust.fName = "QA-Dont2";
		cust.lName = "TEST-Touchme2";
		cust.dateOfBirth = "20120416";
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "444444";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";

		suspension.status = OrmsConstants.ACTIVE_STATUS;
		suspension.type = "Bad Check Suspension";
		suspension.beginDate = DateFunctions.getDateAfterToday(-1);
		suspension.endDate = DateFunctions.getDateAfterToday(1);
		suspension.datePosted = suspension.beginDate;
		suspension.comment = "For " + this.caseName + " to add customer suspension to make privilege as revoked - " + DateFunctions.getCurrentTime();
	}
}
