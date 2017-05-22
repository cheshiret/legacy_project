package com.activenetwork.qa.awo.testcases.sanity.orms;


import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class LM_PrivilegeWithoutInventory_1 extends LicenseManagerTestCase{

	private Suspension suspension = new Suspension();
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private String locationName;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//check customer setup
		if(!lm.checkCustomerExists(cust.fName, cust.lName, schema)) {
			cust.setDefaultCanadaAddress();
			lm.createNewCustomer(cust);
			lm.gotoLicenseManageHomePage();
		}
		
		//cleanup customer privileges
		lm.invalidatePrivilegesPerCustomer(cust, lm.getCustomerPrivileges(cust.fName, cust.lName, privilege.licenseYear,schema));
		
		//make a new privilege order and verify status
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		//verify correct privilege and quantity is added to cart
		lm.verifyAddedCorrectPrivilegeAndQty(privilege.purchasingName, privilege.licenseYear, "1",1);
//		lm.addMorePrivilegeFromCartToCart(privilege);
//		lm.verifyAddedCorrectPrivilegeAndQty(privilege.purchasingName, privilege.licenseYear, "1",2);
		String ordNum1 = lm.processOrderCart(pay,false).split(" ")[0];
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
		
		String status=lm.getVendorAutoReturnVoidedDoc(locationName, schema)?"Returned":"Void";
		
		lm.verifyPrivilegeItemStatusFromOrderPg(allPrivNum, status);
		lm.verifyPrivilegeStatusFromDB(allPrivNum, PRIV_STATUS_VOIDED, schema);
//		privilege.operateReason = "";
//		lm.undoVoidPrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
//		lm.processOrderCart(pay);
//
//		lm.gotoOrderPageFromOrdersQuickSearch(ordNum1);
//		lm.verifyPrivilegeItemStatusFromOrderPg(allPrivNum, ACTIVE_STATUS);
//		lm.verifyPrivilegeStatusFromDB(allPrivNum, PRIV_STATUS_ACTIVE, schema);
//		lm.gotoHomePage();
//
//		privilege.operateReason = "14 - Other";
//		lm.gotoOrderPageFromOrdersQuickSearch(ordNum1);
//		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
//		lm.processOrderCart(pay,false);
				
		lm.logOutLicenseManager();
	}	
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		//updated role to match new roles AP and CW -- 11/29/11
		//login.location = "LM - Facility/WAL-MART";
		login.contract = "MS Contract";
		login.location = "HF HQ Role/LAKE LINCOLN STATE PARK(Store Loc)";
		locationName="LAKE LINCOLN STATE PARK(Store Loc)";
		schema = DataBaseFunctions.getSchemaName("MS",env);
		privilege.purchasingName = "136-NR 3 DAY FRESHWATER";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.validFromDate = DateFunctions.getToday();
				
		privilege.qty = "1";
		privilege.operateReason = "01 - Operator Error";
		privilege.cust=cust;
		
		cust.identifier.identifierType = "Green Card";//"Green Card";
		cust.identifier.identifierNum = "987654321";//"123456789";
		cust.identifier.country = "Canada";
		cust.customerClass = "Individual";
		cust.dateOfBirth="19870803"; //changing it from 19800122 to 19870803. QA2 3.03 DOB stored as 19870803
		cust.lName = "Test-Sanity3";
		cust.fName = "QA-Sanity3";
		cust.residencyStatus="Non Resident";
			
		suspension.beginDate = DateFunctions.getToday();
		suspension.datePosted = DateFunctions.getToday();
		
	}

}
