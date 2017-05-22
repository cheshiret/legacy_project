package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.saleflow;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;

//Need 2013 license year
//Need transfer price
public class PrivilegeWithoutInventory_2 extends LicenseManagerTestCase{
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private Customer newCust = new Customer();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//make a new privilege order and verify status
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		
		//verify correct privilege and quantity is added to cart
		lm.verifyAddedCorrectPrivilegeAndQty(privilege.purchasingName, privilege.licenseYear, "1",1);
		
		// add more privilege to cart
		lm.addMorePrivilegeFromCartToCart(privilege);
		lm.verifyAddedCorrectPrivilegeAndQty(privilege.purchasingName, privilege.licenseYear, "1",2);
		String ordNum1 = lm.processOrderCart(pay).split(" ")[0];
		
		//verify new generated privilege order status correct
		lm.verifyOrderStatus(ordNum1, ORD_STATUS_ACTIVE, schema);
		
		//Duplicate original order
		lm.replacePrivilegeToCartFromCustQuickSearch(cust, ordNum1, privilege);
		lm.verifyTransactionNameInOrdCart("Duplicate Privilege");
		
		lm.addMorePrivilegeFromCartToCart(privilege);
		lm.verifyAddedCorrectPrivilegeAndQty(privilege.purchasingName, privilege.licenseYear, "1", 3);
		
		String ordNum2 = lm.processOrderCart(pay).split(" ")[0];
		lm.verifyAllPrivilegesStatus(ordNum2, PRIV_STATUS_ACTIVE, schema);
		
		lm.gotoOrderPageFromOrdersQuickSearch(ordNum2);
		String allPrivNum2 = privOrderDetailsPage.getAllPrivilegesNum();
		
		//duplicated order transfer
		lm.gotoPrivilegeDetailFromOrderPg(allPrivNum2.split(" ")[1]);
		lm.transferPrivToOrderCart(newCust,privilege);
		lm.processOrderCart(pay);
		lm.gotoOrderPageFromOrdersQuickSearch(ordNum2);
		
		//Reverse/Undo reverse on duplicated order
		privilege.operateReason = "14 - Other";
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
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
		login.location = "HF HQ Role - Auto/WAL-MART";
		
		schema = DataBaseFunctions.getSchemaName("MS",env);
		
		privilege.purchasingName = "876-Ball";
		privilege.licenseYear = lm.getFiscalYear(schema);

		privilege.qty = "1";
		privilege.operateReason = "01 - Operator Error";
		
		cust.identifier.identifierType = "Tax ID";//"Green Card";
		cust.identifier.identifierNum = "123456789";//"123456789";
		cust.customerClass = "Individual";
		cust.lName = "TEST_PriInv1";
		cust.fName = "QA_PriInv1";
		cust.residencyStatus="Non Resident";
		
		newCust.identifier.identifierType = "Tax ID";//"Tax ID";
		newCust.identifier.identifierNum = "888888";//"888888";
		newCust.identifier.country="Canada";
		newCust.customerClass = "Individual";
		newCust.dateOfBirth = "19880608";
		newCust.residencyStatus="Non Resident";
	}
}