package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.consumables;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBankAccountInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBondInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableSaleAddItemPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: this case is used to verify purchase donation product list info when system have two store assigned to a same vendor,
 *  one is close , and another is active
 *  steps:
 *  1. close all store which location is LAKE LOWNDES
 *  2. add one store which select existing location LAKE LOWNDES, and named 'CloseDona***', 
 *  3. assign donation product "888 - DonConfLettter" to this store 'CloseDona***'
 *  4. make purchase and submit this donation product
 *  5. close this store 'CloseDona***'
 *  6. make purchase this donation product, to add item product list page
 *  7. verify this product should not existing 
 *  8. add new store which still select existing location LAKE LOWNDES, and named 'SameWithClose***',
 *  9. assign donation product "888 - DonConfLettter" to this store 'SameWithClose***' 
 *  10. make purchase this donation product, to add item product list page
 *  11. verify this product should existing and product info size should be 1
 * @Preconditions:
 * 1. prepare one location role: HF HQ Role/LAKE LOWNDES
 * 2. donation product:"888 - DonConfLettter", could be purchased
 * @SPEC:TC:030067
 * @Task#:Auto-1325
 * 
 * @author vzhang
 * @Date  Oct 17, 2012
 */
public class DonationPurListMutiStoreSameLocation extends LicenseManagerTestCase{
	private String vendorNum,vendorName,switchAdmLocation,switchSaleLocation,facilityID,facilityName;
	private StoreInfo storeForClose = new StoreInfo();
	private StoreInfo storeSameWithClosed = new StoreInfo();
	private VendorBankAccountInfo bankAccount = new VendorBankAccountInfo();
	private VendorBondInfo bondInfo = new VendorBondInfo();
	

	@Override
	public void execute() {
		//close all store which location is LAKE LOWNDES
		this.clearupStore(facilityID);
		//add store and assign product
		lm.loginLicenseManager(login);
		this.addStoreForSale(storeForClose);
		
		//purchase this product
		lm.gotoHomePage();		
		lm.switchLocationInHomePage(switchSaleLocation);
		lm.makeConsumableOrderToCartFromQuickSearch(consumable);
		lm.processOrderCart(pay);
		
		//close this store
		this.clearupStore(facilityID);
		//go to add item page, verify product info, this product should not existing
		lm.gotoAddItemPgFromConsumableQuickSearch();
		this.verifyProductInfo(consumable.name,0);
		
		//add new store
		lm.switchLocationInHomePage(switchAdmLocation);
		this.addStoreForSale(storeSameWithClosed);
		
		//go to add item page, verify product info, this product info size should be 1
		lm.gotoHomePage();		
		lm.switchLocationInHomePage(switchSaleLocation);
		lm.gotoAddItemPgFromConsumableQuickSearch();
		this.verifyProductInfo(consumable.name,1);
		
		//clear up
		this.clearupStore(facilityID);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initial login info
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		facilityID = "151815";//LAKE LOWNDES
		facilityName = lm.getFacilityName(facilityID, schema);
		
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		switchAdmLocation= login.location.replace("/", "-");
		switchSaleLocation = "HF HQ Role - Auto-" + facilityName+"-Gate House 1 AM";
		
		vendorNum = "Auto555";
		vendorName = "Auto Vendor";
		
		storeForClose.isNewLocation = false;
		storeForClose.locationClass = "State Parks Agent";
		storeForClose.agency = "STATE PARKS";
		storeForClose.region = "DISTRICT 1";
		storeForClose.location = "LAKE LOWNDES";
		storeForClose.storeName = "CloseDona"+ DateFunctions.getCurrentTime();
		storeForClose.physicalAddress.address = "3319 Lake Lowndes Road";
		storeForClose.physicalAddress.city = "Columbus";
		storeForClose.physicalAddress.state = "Mississippi";
		storeForClose.physicalAddress.county = "Adams";
		storeForClose.physicalAddress.zip = "39702";
		storeForClose.physicalAddress.country = "United States";
		storeForClose.physicalAddress.isValidate = false;
		storeForClose.isMailSamePhyAddress = true;
		
		Contacts contact = new Contacts();
		contact.contactType = "Finance Mgr";
		contact.isPrimary = false;
		contact.firstName = "DonationPurList";
		contact.lastName = "StoreSameWithExisting";
		contact.suffix = "SR";
		contact.businessPhone = "4088127894";
		storeForClose.contactArray.add(contact);
		
		storeSameWithClosed.isNewLocation = false;
		storeSameWithClosed.locationClass = storeForClose.locationClass;
		storeSameWithClosed.agency = storeForClose.agency;
		storeSameWithClosed.region = storeForClose.region;
		storeSameWithClosed.location = storeForClose.location;
		storeSameWithClosed.storeName = "SameWithClose"+ DateFunctions.getCurrentTime();
		storeSameWithClosed.physicalAddress.address = storeForClose.physicalAddress.address;
		storeSameWithClosed.physicalAddress.city = storeForClose.physicalAddress.city;
		storeSameWithClosed.physicalAddress.state = storeForClose.physicalAddress.state;
		storeSameWithClosed.physicalAddress.county = storeForClose.physicalAddress.county;
		storeSameWithClosed.physicalAddress.zip = storeForClose.physicalAddress.zip;
		storeSameWithClosed.physicalAddress.country = storeForClose.physicalAddress.country;
		storeSameWithClosed.physicalAddress.isValidate = storeForClose.physicalAddress.isValidate;
		storeSameWithClosed.isMailSamePhyAddress = true;

		storeSameWithClosed.contactArray.add(contact);
		
		bankAccount.accountPrenoteStatus = "Pending";
		bankAccount.accountStatus = "Active";
		bankAccount.accountType = "Checking";
		bankAccount.routingNum = "026009593";
		bankAccount.accountNum = "753159";
		bankAccount.effectiveDate = DateFunctions.getDateAfterToday(1);
		bankAccount.accountRegx = bankAccount.accountType + " Routing # " + bankAccount.routingNum + " Acct # " + bankAccount.accountNum.substring(0, 4);
		
		bondInfo.bondNum = "010";
		bondInfo.issuer = "Federated Mutual";
		
		consumable.code = "888";
		consumable.name= "888 - DonConfLettter";
		consumable.quantity = "1";
		
		cust.lName = "TEST-RAFee2";
		cust.fName = "QA-RAFee2";
		cust.dateOfBirth = "Jun 01 1980";
	}
	
	private void clearupStore(String locID){
		logger.info("Close all store which loction id = " + locID);
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		db.resetSchema(schema);
		String update = "update d_store set status = 4, reason_id = null where loc_id = " + locID;

		db.connect();
		db.executeUpdate(update);
	}
	
	private void addStoreForSale(StoreInfo store){
		lm.gotoVendorDetailsPgFromTopMenu(vendorNum);
		lm.gotoVendorAgentsPg();
		store.storeID = lm.addVendorAgents(store);
		
		lm.gotoBondSubTabFromVendorDetailPg();		
		lm.changeAgentBondAssignment(store.storeID, bondInfo.bondNum,bondInfo.issuer);
		
		lm.gotoVendorBankAccountPage();
		lm.assignVendorBankAccountToStore(store.storeName, bankAccount.accountRegx, bankAccount.effectiveDate, true);
		
		//go to store product assignment page
		lm.gotoStoreProductAssignmentPage(store.storeName, vendorName);
		
		//assign consumable to store
		lm.assignConsumableToStore(consumable.code);
	}
	
	private void verifyProductInfo(String prodName, int expProdInfoNum){
		LicMgrConsumableSaleAddItemPage addItemPg = LicMgrConsumableSaleAddItemPage.getInstance();
		
		logger.info("Verify product info, producnt name = " + prodName);
		IHtmlObject[] objs = addItemPg.getProdTableRows(prodName);
		boolean result = MiscFunctions.compareResult("Product info size", expProdInfoNum, objs.length);
		if(!result){
			throw new ErrorOnPageException("Product info size is not correct at Add Product Item Page, product name = " + prodName );
		}else logger.info("Product info size is correct at Add Product Item Page, product name = " + prodName );
	}
	
}
