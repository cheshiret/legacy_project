package com.activenetwork.qa.awo.testcases.sanity.orms;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ConsumableInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuestionInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
import com.activenetwork.qa.awo.datacollection.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;


import com.activenetwork.qa.awo.util.CSVLoader;




/**
 * 
 * @Description: verify basic search function after login
 * @Preconditions: Migration finished

 * @Task#: MIGA-Auto 02
 * 
 * @author tchen
 * @Date  Sep 24, 2014
 */
public class LM_MIG_BasicSearchSanity extends LicenseManagerTestCase {
	
//	private ConsumableInfo donationConsumable = new ConsumableInfo();
//	private String voidReason, voidNote;
//	private Customer cust = new Customer();
	private VendorInfo vendor = new VendorInfo();	
	private OrderInfo order = new OrderInfo();
	private PrivilegeInfo priv = new PrivilegeInfo();
	private StoreInfo store = new StoreInfo();
	
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//System.out.println(browser.url());
		//lm.searchCustomerFromHomePageCustomerSection(cust);
		//System.out.println(browser.url());

		//lm.gotoHomePage();
		//System.out.println(browser.url());
		//lm.searchVendorFromHomePageVendorSection(vendor);
		//lm.gotoHomePage();
		lm.searchStoreFromHomePageStoreSection(store);
		
		//lm.getTranlationFromDB("store", schema);
		//System.out.println(browser.url());

		
		//lm.purchaseInventoryPrivilegeFromHomePage()
		//lm.gotoApplicationOrderDetailsPageFromHomePage
		
//		//1. make a donation consumable with transaction name - Make Donation
//		lm.makeConsumableOrderToCartFromQuickSearch(donationConsumable);
//		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_MAKE_DONATION);
//		lm.verifyAddedCorrectConsumableAndQty(donationConsumable.name, donationConsumable.licenseYear, "1", 1);
//		String ordNums = lm.processOrderCart(pay);
//		String[] tokens=ordNums.split(" ");
//		String ordNum=tokens[0];
//		lm.verifyOrderStatus(ordNum, OrmsConstants.ORD_STATUS_ACTIVE, schema);
//		lm.verifyAllConsumablesStatus(ordNum, OrmsConstants.ORD_ITEM_STATUS_ACTIVE, schema);
//		
//		//2. void this donation consumable order
//		lm.gotoConsumableOrderDetailsPage(ordNum);
//		lm.voidConsumableOrderToCart(voidReason, voidNote);
//		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_VOID_DONATION);
//		lm.processOrderCart(pay);
//		lm.verifyOrderStatus(ordNum, OrmsConstants.ORD_STATUS_VOIDED, schema);
//		lm.verifyAllConsumablesStatus(ordNum, OrmsConstants.ORD_ITEM_STATUS_VOIDED, schema);
//
//		//3. make 3 consumables with transaction name - Purchase Consumable
//		lm.makeConsumableOrderToCartFromCustomerTopMenu(cust, consumable);
//		lm.verifyAddedCorrectConsumableAndQty(consumable.name, consumable.licenseYear, "1", 1);
//		lm.addMoreConsumableFromCartToCart(consumable);
//		lm.verifyAddedCorrectConsumableAndQty(consumable.name, consumable.licenseYear, "2", 1);
//		lm.purchaseConsumableFromCartToCart(consumable);
//		lm.verifyAddedCorrectConsumableAndQty(consumable.name, consumable.licenseYear, "3", 1);
//		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_PURCHASE_CONSUMABLE);
//		ordNum = lm.processOrderCart(pay).split(" ")[0];
//		lm.verifyOrderStatus(ordNum, OrmsConstants.ORD_STATUS_ACTIVE, schema);
//		lm.verifyAllConsumablesStatus(ordNum, OrmsConstants.ORD_ITEM_STATUS_ACTIVE, schema);
//		
//		//4. void this consumable order
//		lm.gotoConsumableOrderDetailsPage(ordNum);
//		lm.voidConsumableOrderToCart(voidReason, voidNote);
//		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_VOID_CONSUMABLE);
//		lm.processOrderCart(pay);
//		lm.verifyOrderStatus(ordNum, OrmsConstants.ORD_STATUS_VOIDED, schema);
//		lm.verifyAllConsumablesStatus(ordNum, OrmsConstants.ORD_ITEM_STATUS_VOIDED, schema);
//		
//		//5. purchase a privilege and make a consumable during the process - click 'OK' on donation prompt widget
//		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(true, cust, privilege);
//		lm.makeConsumableOrderToCartFromAddItemPage(consumable);
//		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_PURCHASE_PRIVILEGE);
//		lm.verifyAddedCorrectPrivilegeAndQty(privilege.purchasingName, privilege.licenseYear, privilege.qty, 1);
//		lm.verifyAddedCorrectConsumableAndQty(consumable.name, consumable.licenseYear, "1", 2);
//		String allOrdNums = lm.processOrderCart(pay);
//		ordNum = allOrdNums.split(" ")[0];
//		lm.verifyOrderStatus(ordNum, OrmsConstants.ORD_STATUS_ACTIVE, schema);
//		lm.verifyAllConsumablesStatus(ordNum, OrmsConstants.ORD_ITEM_STATUS_ACTIVE, schema);
//		
//		//6. void this consumable and privilege order
//		lm.gotoConsumableOrderDetailsPage(ordNum);
//		lm.voidConsumableOrderToCart(voidReason, voidNote);
//		lm.verifyTransactionNameInOrdCart(OrmsConstants.TRANNAME_VOID_CONSUMABLE);
//		lm.processOrderCart(pay);
//		lm.verifyOrderStatus(ordNum, OrmsConstants.ORD_STATUS_VOIDED, schema);
//		lm.verifyAllConsumablesStatus(ordNum, OrmsConstants.ORD_ITEM_STATUS_VOIDED, schema);
//		
//		lm.gotoPrivilegeOrderDetailPage(allOrdNums.split(" ")[1]);
//		lm.voidPrivilegeOrderToCart(privilege.operateReason, voidNote);
//		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MO Contract";
		login.location = "imp-admin-role/BLUE ACRES INC(Store Loc)";
		//login.location = "ID Admin/IDFG";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + login.contract.replace(" Contract", "");//"MO";
		
		//Static Value Search
		
		//customer
		cust.customerClass = "Individual";
		cust.identifier.identifierType = "Conservation #";//"US Drivers License";
		cust.identifier.identifierNum = "100000041";//"2165270";
		//cust.dateOfBirth = "1984/01/01";
		cust.lName = "Chen";
		cust.fName = "Tony";
		
		//vendor
		vendor.translation = lm.getTranlationFromDB("vendor", schema);
		vendor.name = "HUNTING";//"FOUTZ'S HUNTING & FISHING";
		vendor.number = "017015";
				
		
		//Store
		store.translation = lm.getTranlationFromDB("store", schema);
		//store.storeName = "HUNTING";//"FOUTZ'S HUNTING & FISHING";
		store.storeID = "17015";
		
		
		//order
		order.searchType = "";
		order.searchValue = "XX";
		order.orderNum = "";
		
		//Privilege
		priv.primaryPrivilegeNum = "";
		
		
		
		
		//search info
		//search obj
		//search type
		//search value
		
		//Data Driven Test with CSV
//		donationConsumable.name = "115 - WILDLIFE FOUNDATION DONATION";
//		donationConsumable.price = "10";
//		donationConsumable.licenseYear = lm.getFiscalYear(schema);
//		QuestionInfo q1 = new QuestionInfo();
//		q1.questionType = "radio";
//		q1.questDisplayText = "What color do you prefer?";
//		q1.questAnswer = "Grey";
//		donationConsumable.questions = new QuestionInfo[] {q1};
//		
//		consumable.name = "451 - Legacy Inspection";
//		consumable.code = "451";
//		QuestionInfo q2 = new QuestionInfo();
//		q2.questionType = "dropdownlist";
//		q2.questDisplayText = "What type of flashlight do you preffer?";
//		q2.questAnswer = "head";
//		consumable.questions = new QuestionInfo[] {q2};
//		consumable.price = "14";
//		consumable.licenseYear = lm.getFiscalYear(schema);
//		
//		//customer info
//		cust.identifier.identifierType = "Green Card";
//		cust.identifier.identifierNum = "987654321";
//		cust.identifier.country = "Canada";
//		cust.customerClass = "Individual";
//		cust.lName = "Test-Sanity3";
//		cust.fName = "QA-Sanity3";
//		cust.residencyStatus = "Non Resident";
//		cust.dateOfBirth = "Aug 03 1987";
//		
//		//privilege info
//		privilege.purchasingName = "167-NR WATERFOWL STAMP";
//		privilege.licenseYear = lm.getFiscalYear(schema);
//		privilege.validFromDate = DateFunctions.getToday();
//		privilege.qty = "1";
//		privilege.operateReason = "14 - Other";
//		privilege.cust = cust;
//		
//		voidReason = "12 - Payment Not Received After Printing";
//		voidNote = "Automation Sanity Test";
	}
}
