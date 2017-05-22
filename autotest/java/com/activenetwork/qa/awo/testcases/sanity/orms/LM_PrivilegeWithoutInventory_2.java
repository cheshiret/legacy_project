package com.activenetwork.qa.awo.testcases.sanity.orms;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class LM_PrivilegeWithoutInventory_2 extends LicenseManagerTestCase{

	private Suspension suspension = new Suspension();
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
//	private LicMgrVoidUndoVoidPrivilegePage voidUndoVoidPg = LicMgrVoidUndoVoidPrivilegePage.getInstance();
//	private Customer newCust = new Customer();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//check customer setup
//		if(!lm.checkCustomerExists(cust.fName, cust.lName, schema)) {
		cust.setDefaultCanadaAddress();
		lm.createNewCustomer(cust);
		lm.gotoLicenseManageHomePage();
//		}
		
		//make a new privilege order and verify status
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		//verify correct privilege and quantity is added to cart
		lm.verifyAddedCorrectPrivilegeAndQty(privilege.purchasingName, privilege.licenseYear, "1",1);
		lm.addMorePrivilegeFromCartToCart(privilege);
		lm.verifyAddedCorrectPrivilegeAndQty(privilege.purchasingName, privilege.licenseYear, "1",2);
		String ordNum1 = lm.processOrderCart(pay).split(" ")[0];
		//verify new generated privilege order status correct
		lm.verifyOrderStatus(ordNum1, ORD_STATUS_ACTIVE, schema);

		privilege.operateReason = "01 - Operator Error";
		//Duplicate original order
		lm.replacePrivilegeToCartFromCustQuickSearch(cust, ordNum1, privilege);
		lm.verifyTransactionNameInOrdCart("Duplicate Privilege");
		lm.addMorePrivilegeFromCartToCart(privilege);
		lm.verifyAddedCorrectPrivilegeAndQty(privilege.purchasingName, privilege.licenseYear, "1", 3);
		String ordNum2 = lm.processOrderCart(pay).split(" ")[0];
		lm.verifyAllPrivilegesStatus(ordNum2, PRIV_STATUS_ACTIVE, schema);
		
		lm.gotoOrderPageFromOrdersQuickSearch(ordNum2);
		String allPrivNum2 = privOrderDetailsPage.getAllPrivilegesNum();
				
		//Reverse/Undo reverse on duplicated order
		privilege.operateReason = "14 - Other";
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		
		TestProperty.putProperty("control.printdoc", "false"); //skip print document from here due to defect
		lm.processOrderCart(pay);

		privilege.operateReason = "17 - Other";
		lm.gotoOrderPageFromOrdersQuickSearch(ordNum2);
		lm.verifyPrivilegeItemStatusFromOrderPg(allPrivNum2.split(" ")[2], REVERSED_STATUS);
		lm.verifyPrivilegeStatusFromDB(allPrivNum2.split(" ")[2], PRIV_STATUS_REVERSED, schema);
		lm.undoReversePrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);

		lm.gotoOrderPageFromOrdersQuickSearch(ordNum2);
		lm.verifyPrivilegeItemStatusFromOrderPg(allPrivNum2.split(" ")[2], ACTIVE_STATUS);
		lm.verifyPrivilegeStatusFromDB(allPrivNum2.split(" ")[2], PRIV_STATUS_ACTIVE, schema);
		
		lm.logOutLicenseManager();
	}	
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/LAKE LINCOLN STATE PARK(Store Loc)";
			
		schema = DataBaseFunctions.getSchemaName("MS",env);
			
		privilege.purchasingName = "167-NR WATERFOWL STAMP";
		privilege.licenseYear = lm.getFiscalYear(schema);;

		privilege.qty = "1";
		privilege.operateReason = "01 - Operator Error";
		
//		cust.identifier.identifierType = "Tax ID";//"Green Card";
//		cust.identifier.identifierNum = "245687456";//"123456789";
//		cust.customerClass = "Individual";
//		cust.lName = "test";
//		cust.fName = "sanity";
//		cust.residencyStatus="Non Resident";
		
		int seq=DataBaseFunctions.getEmailSequence();
		cust.identifier.identifierType = "Tax ID";//"Tax ID";
		cust.identifier.identifierNum = "LMPWI2"+seq;//"888888";
		cust.identifier.country="Canada";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "19880608";
		cust.lName = "Test_LMPWI2"+seq;
		cust.fName = "Sanity_LMPWI2"+seq;
		cust.residencyStatus="Non Resident";
		
		suspension.beginDate = DateFunctions.getToday();
		suspension.datePosted = DateFunctions.getToday();
		
	}

}
