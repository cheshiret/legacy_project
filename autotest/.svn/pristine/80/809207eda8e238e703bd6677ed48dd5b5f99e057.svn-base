package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.saleflow;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrVoidUndoVoidPrivilegePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class PrivilegeWithoutInventory_1 extends LicenseManagerTestCase{

	private Suspension suspension = new Suspension();
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private LicMgrVoidUndoVoidPrivilegePage voidUndoVoidPg = LicMgrVoidUndoVoidPrivilegePage.getInstance();
	private Customer newCust = new Customer();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//make a new privilege order and verify status
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		//verify correct privilege and quantity is added to cart
		lm.verifyAddedCorrectPrivilegeAndQty(privilege.purchasingName, privilege.licenseYear, "1",1);
//		lm.addMorePrivilegeFromCartToCart(privilege);
//		lm.verifyAddedCorrectPrivilegeAndQty(privilege.purchasingName, privilege.licenseYear, "1",2);
		String ordNum1 = lm.processOrderCart(pay,true).split(" ")[0];
		//verify new generated privilege order status correct
		lm.verifyOrderStatus(ordNum1, ORD_STATUS_ACTIVE, schema);

		//invalidate and reactivate privilege
		//goto privilege order detail page
		lm.gotoOrderPageFromOrdersQuickSearch(ordNum1);
		String allPrivNum = privOrderDetailsPage.getAllPrivilegesNum();

		//invalidate privilege order and verify related privileges status changed to invalid from DB
		lm.invalidatePrivilegeOrder(privilege.operateReason, privilege.operateNote);
		lm.verifyAllPrivilegesStatus(ordNum1, PRIV_STATUS_INVALID, schema);
		//reactivate privilege order and verify related privileges status changed to active
		lm.reactivatePrivilegeOrder(privilege.operateReason, privilege.operateNote);
		lm.verifyAllPrivilegesStatus(ordNum1, PRIV_STATUS_ACTIVE, schema);
		
		//invalidate the first privilege
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
		lm.invalidatePrivilegeItem(privilege.operateReason, privilege.operateReason);
		//verify the privilege invalidated correct from UI and DB
		lm.verifyPrivilegeStatusFromUI(INVALID_STATUS);
		lm.verifyPrivilegeStatusFromDB(allPrivNum, PRIV_STATUS_INVALID, schema);
		
		//reactivate the privilege 
		lm.reactivatePrivilegeItem(privilege.operateReason, privilege.operateNote);
		//verify the privilege reactivated correct from UI and DB
		lm.verifyPrivilegeStatusFromUI(ACTIVE_STATUS);
		lm.verifyPrivilegeStatusFromDB(allPrivNum, PRIV_STATUS_ACTIVE, schema);
		lm.gotoHomePage();
		
		//revoke,invalidate and reactivate
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.manageSuspensions("add",suspension);
		lm.verifyAllPrivilegesStatus(ordNum1, PRIV_STATUS_REVOKED, schema);
		lm.manageSuspensions("Deactivate", suspension);
		lm.verifyAllPrivilegesStatus(ordNum1, PRIV_STATUS_REVOKED, schema);
		lm.manageSuspensions("Remove",suspension);
		lm.verifyAllPrivilegesStatus(ordNum1, PRIV_STATUS_REVOKED, schema);
		
		lm.invalidatePrivilegeFromCustomerPrivilegePage(allPrivNum,privilege.licenseYear, privilege.operateReason, privilege.operateNote);
		//verify the privilege invalidated correct from UI and DB
		lm.verifyPrivilegeStatusFromUI(INVALID_STATUS);
		lm.verifyPrivilegeStatusFromDB(allPrivNum, PRIV_STATUS_INVALID, schema);
		
		//reactivate the privilege 
		lm.reactivatePrivilegeItem(privilege.operateReason, privilege.operateNote);
		//verify the privilege reactivated correct from UI and DB
		lm.verifyPrivilegeStatusFromUI(ACTIVE_STATUS);
		lm.verifyPrivilegeStatusFromDB(allPrivNum, PRIV_STATUS_ACTIVE, schema);
		
//		lm.gotoPrivSearchPgFromPrivItemDetailPg();
//		lm.searchAndGotoPrivilegeItemDetail(allPrivNum.split(" ")[1]);
//		lm.reactivatePrivilegeItem(privilege.operateReason, privilege.operateNote);
//		lm.verifyPrivilegeStatusFromUI(ACTIVE_STATUS);
//		lm.verifyPrivilegeStatusFromDB(allPrivNum.split(" ")[1], PRIV_STATUS_ACTIVE, schema);
		lm.gotoHomePage();
		
		//void/undo void original order
		privilege.operateReason = "14 - Other";
		lm.gotoOrderPageFromOrdersQuickSearch(ordNum1);
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay,false);
		
		lm.gotoOrderPageFromOrdersQuickSearch(ordNum1);
		lm.verifyPrivilegeItemStatusFromOrderPg(allPrivNum, "Void");
		lm.verifyPrivilegeStatusFromDB(allPrivNum, PRIV_STATUS_VOIDED, schema);
		privilege.operateReason = "";
		lm.undoVoidPrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);

		lm.gotoOrderPageFromOrdersQuickSearch(ordNum1);
		lm.verifyPrivilegeItemStatusFromOrderPg(allPrivNum, ACTIVE_STATUS);
		lm.verifyPrivilegeStatusFromDB(allPrivNum, PRIV_STATUS_ACTIVE, schema);
		lm.gotoHomePage();

		//original order transfer
		lm.gotoOrderPageFromOrdersTopMenu(ordNum1);
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum);
		lm.transferPrivToOrderCart(newCust,privilege);
		String ordNum2 = lm.processOrderCart(pay).split(" ")[0];
		lm.verifyPrivilegeStatusFromDB(allPrivNum, PRIV_STATUS_TRANSFERRED, schema);
				
		//void/reverse transferred order
		lm.gotoOrderPageFromOrdersQuickSearch(ordNum2);
		privOrderDetailsPage.voidOrReverseOrder();
		voidUndoVoidPg.waitLoading();
		if(voidUndoVoidPg.getDisplayedPrivilegeRowNum()>0){
			throw new ErrorOnPageException("There should be No privilege displayed.");
		}
		
		lm.logOutLicenseManager();
	}	
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		schema = DataBaseFunctions.getSchemaName("MS",env);
		TimeZone tz = DataBaseFunctions.getContractTimeZone(schema);
		
		privilege.purchasingName = "876-Ball";
		privilege.licenseYear = lm.getFiscalYear(schema);
		
		privilege.qty = "1";
		privilege.operateReason = "01 - Operator Error";
		
		cust.identifier.identifierType = "Green Card";//"Green Card";
		cust.identifier.identifierNum = "GC321456";//"123456789";
		cust.identifier.country = "Canada";
		cust.customerClass = "Individual";
		cust.lName = "Test-PriInventory1";
		cust.fName = "QA-PriInventory1";
		cust.residencyStatus="Non Resident";
		
		newCust.identifier.identifierType = "Tax ID";//"Tax ID";
		newCust.identifier.identifierNum = "888888";//"888888";
		newCust.identifier.country="Canada";
		newCust.customerClass = "Individual";
		newCust.dateOfBirth = "19880608";
		newCust.residencyStatus="Non Resident";
		
		suspension.beginDate = DateFunctions.getToday(tz);
		suspension.datePosted = DateFunctions.getToday(tz);
	}
}
