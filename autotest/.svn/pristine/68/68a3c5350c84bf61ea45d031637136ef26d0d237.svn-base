package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.vendor;

import java.util.ArrayList;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ApplicationStatusCheck;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBankAccountInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBondInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorSearchListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case is used to verify search vendor
 * Blocked by DEFECT-32651,DEFECT-32462 
 * @Preconditions:
 * @SPEC:Search Vendor.UCS
 * @Task#:Auto-758

 * @author VZhang
 * @Date Jan 9, 2012
 */
public class SearchVendor extends LicenseManagerTestCase{
	private VendorInfo vendor = new VendorInfo();
	private VendorInfo searchVendor = new VendorInfo();
	private StoreInfo store = new StoreInfo();
	private StoreInfo searchStore = new StoreInfo();
	private VendorBankAccountInfo bankAccount = new VendorBankAccountInfo();
	private VendorBondInfo bondInfo = new VendorBondInfo();
	private String searchBankAccountNum, searchBondNum = "";

	@Override
	public void execute() {
		//Login license manager and goto vendor search page
		lm.loginLicenseManager(login);
		lm.gotoVendorSearchPg();
		
		//add vendor with default eft info
		lm.addVendor(vendor);
		//go to vendor detail page
		lm.gotoVendorDetailPgFromVendorSearchPg(vendor.number);
		lm.gotoVendorAgentsPg();
		//add agent
		store.storeID = lm.addVendorAgents(store);
				
		//add vendor bank account
		lm.gotoVendorBankAccountPage();
		//add vendor bank account
		lm.addVendorBankAccount(bankAccount, true);
		//assign vendor bank account to store
		lm.assignVendorBankAccountToStore(store.storeName, bankAccount.accountRegx, bankAccount.effectiveDate, true);
		
		//go to bound sub tab
		lm.gotoBondSubTabFromVendorDetailPg();
		//add vendor bond
		lm.addVendorBond(bondInfo, true);
		//assign vendor bond to agent
		lm.changeAgentBondAssignment(store.storeID, bondInfo.bondNum, bondInfo.issuer);
		
		//go to vendor search page
		lm.gotoVendorSearchPg();
		
		searchVendor.number = vendor.number;
		//search by vendor number
		lm.searchVendorOrAgent(searchVendor, searchStore, searchBankAccountNum, searchBondNum);
		this.verifySearchResultByVendorNum(searchVendor.number);
		lm.gotoVendorSearchPg();
		
		searchVendor.number = "";
		searchStore.storeID = store.storeID;
		//search by agent ID
		lm.searchVendorOrAgent(searchVendor, searchStore, searchBankAccountNum, searchBondNum);
		this.verifySearchResultByStoreID(searchStore.storeID);
		lm.gotoVendorSearchPg();
		
		searchStore.storeID = "";
		searchVendor.name = vendor.name;
		//search by vendor name
		lm.searchVendorOrAgent(searchVendor, searchStore, searchBankAccountNum, searchBondNum);
		this.verifySearchResultList(vendor, store);
		
		searchVendor.name = "";
		searchVendor.status = vendor.status;
		//search by vendor status
		lm.searchVendorOrAgent(searchVendor, searchStore, null, null);
		this.verifySearchResultList(vendor, store);
		
		searchVendor.status = "";
		searchVendor.vendorSearchBy = "Tax ID";
		searchVendor.vendorSearchByValue = vendor.taxID;
		//search by vendor search type
		lm.searchVendorOrAgent(searchVendor, searchStore, searchBankAccountNum, searchBondNum);
		this.verifySearchResultList(vendor, store);
		
		//search by store name
		searchVendor.vendorSearchBy = "";
		searchVendor.vendorSearchByValue = "";
		searchStore.storeName = store.storeName;
		lm.searchVendorOrAgent(searchVendor, searchStore, searchBankAccountNum, searchBondNum);
		this.verifySearchResultList(vendor, store);

		//search by store status
		searchStore.storeName = "";
		searchStore.status = store.status;
		lm.searchVendorOrAgent(searchVendor, searchStore, searchBankAccountNum, searchBondNum);
		this.verifySearchResultList(vendor, store);
		
		searchStore.status = "";
		searchStore.physicalAddress.address = store.physicalAddress.address;
		//search by agent physical address
		lm.searchVendorOrAgent(searchVendor, searchStore, searchBankAccountNum, searchBondNum);
		this.verifySearchResultList(vendor, store);
		
		searchStore.physicalAddress.address = "";
		searchStore.physicalAddress.city = store.physicalAddress.city;
		//search by agent physical city
		lm.searchVendorOrAgent(searchVendor, searchStore, searchBankAccountNum, searchBondNum);
		this.verifySearchResultList(vendor, store);
		
		searchStore.physicalAddress.city = "";
		searchStore.physicalAddress.country = store.physicalAddress.country;
		searchStore.physicalAddress.state = store.physicalAddress.state;
		searchStore.physicalAddress.county = store.physicalAddress.county;
		searchStore.physicalAddress.zip = store.physicalAddress.zip;
		//search by agent physical country
		lm.searchVendorOrAgent(searchVendor, searchStore, searchBankAccountNum, searchBondNum);
		this.verifySearchResultList(vendor, store);
		
		searchStore.physicalAddress.country = "";
		searchStore.physicalAddress.state = "";
		searchStore.physicalAddress.county = "";
		searchStore.physicalAddress.zip = "";
		searchBankAccountNum = bankAccount.accountNum;
		//search by agent bank account
		lm.searchVendorOrAgent(searchVendor, searchStore, searchBankAccountNum, searchBondNum);
		this.verifySearchResultList(vendor, store);
		
		searchBankAccountNum = "";
		searchBondNum = bondInfo.bondNum;
		//search by agent bound number
		lm.searchVendorOrAgent(searchVendor, searchStore, searchBankAccountNum, searchBondNum);
		this.verifySearchResultList(vendor, store);
		
		this.initializeFullSearchCriteria();
		//search by the full search criteria
		lm.searchVendorOrAgent(searchVendor, searchStore, searchBankAccountNum, searchBondNum);
		this.verifySearchResultList(vendor, store);
		
		lm.logOutLicenseManager();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		vendor.appReceivedDate = DateFunctions.getToday();
		vendor.appName = "Basic Test Case";
		vendor.appPhone = "9052867777";
		vendor.requestStoreNum = "5";
		vendor.requestStoreEquipmentNum = "2";
		
		vendor.number = "Search" + DateFunctions.getCurrentTime();
		vendor.name = vendor.number;
		vendor.status = "Active";
		vendor.ownerName = "Basic Test Case";
		vendor.vendorType = "Government";
		
		vendor.taxID = "000000001";
		vendor.taxIDType = "Federal Tax ID";
		
		vendor.phyAddress = "address";
		vendor.phyCityTown = "Simi Valley";
		vendor.phyStateProvince = "California";
		vendor.phyCounty = "Ventura";
		vendor.phyZipPostal = "93063";
		vendor.phyCountry = "United States";
		
		vendor.isMailingAddSameAsPhysicalAdd = true;
		
		vendor.contacts = new ArrayList<Contacts>();
		Contacts vendorContact = new Contacts();
		vendorContact.contactType = "Business Mgr";
		vendorContact.isPrimary = false;
		vendorContact.firstName = "Add";
		vendorContact.midName = "Vendor";
		vendorContact.lastName = "Test";
		vendorContact.suffix = "SR";
		vendorContact.businessPhone = "9052867777";
		vendor.contacts.add(vendorContact);
		
		vendor.specifyDefault = "Weekly EFT";
		vendor.isFillValues = true;

		vendor.applicationStatusCheck = new ArrayList<ApplicationStatusCheck>();
		ApplicationStatusCheck appStatusCheck = new ApplicationStatusCheck();
		appStatusCheck.byPassChecked = true;
		appStatusCheck.statusCheck = "Credit Check Run";
		appStatusCheck.dateCompleted = "";
		appStatusCheck.completedBy = "";
		appStatusCheck.comments = "";
		
		ApplicationStatusCheck appStatusCheck1 = new ApplicationStatusCheck();
		appStatusCheck1.byPassChecked = true;
		appStatusCheck1.statusCheck = "Owner Suspensions Check";
		appStatusCheck1.dateCompleted = "";
		appStatusCheck1.completedBy = "";
		appStatusCheck1.comments = "";
		
		ApplicationStatusCheck appStatusCheck2 = new ApplicationStatusCheck();
		appStatusCheck2.byPassChecked = true;
		appStatusCheck2.statusCheck = "Law enforcement A check";
		appStatusCheck2.dateCompleted = "";
		appStatusCheck2.completedBy = "";
		appStatusCheck2.comments = "";
		
		ApplicationStatusCheck appStatusCheck3 = new ApplicationStatusCheck();
		appStatusCheck3.byPassChecked = true;
		appStatusCheck3.statusCheck = "Law Enforcement Background";
		appStatusCheck3.dateCompleted = "";
		appStatusCheck3.completedBy = "";
		appStatusCheck3.comments = "";
		vendor.applicationStatusCheck.add(appStatusCheck);
		vendor.applicationStatusCheck.add(appStatusCheck1);
		vendor.applicationStatusCheck.add(appStatusCheck2);
		vendor.applicationStatusCheck.add(appStatusCheck3);		
		
		store.status = "Active";
		store.isNewLocation = true;
		store.locationClass = "Commercial Agent";
		store.agency = "STATE PARKS";
		store.region = "DISTRICT 1";
		store.storeName = "Search"+ DateFunctions.getCurrentTime();
		store.physicalAddress.address = "Shanxi";
		store.physicalAddress.supplementalAddr = "ChengDu";
		store.physicalAddress.city = "Schenectady";
		store.physicalAddress.state = "New York";
		store.physicalAddress.county = "Schenectady";
		store.physicalAddress.zip = "12345-0001";
		store.physicalAddress.country = "United States";
		
		store.isMailSamePhyAddress = true;
		
		Contacts storeContact = new Contacts();
		storeContact.contactType = "Finance Mgr";
		storeContact.isPrimary = true;
		storeContact.firstName = "Auto";
		storeContact.lastName = "Test";
		storeContact.suffix = "SR";
		storeContact.businessPhone = "4088127894";
		store.contactArray.add(storeContact);
		
		bankAccount.accountType = "Checking";
		bankAccount.routingNum = "026009593";
		bankAccount.accountPrenoteStatus = "Pending";
		bankAccount.accountStatus = "Active";
		bankAccount.accountNum = "c" + DateFunctions.getCurrentTime();	
		bankAccount.effectiveDate = DateFunctions.getDateAfterToday(1);
		bankAccount.accountRegx = bankAccount.accountType + " Routing # " + bankAccount.routingNum + " Acct # " + bankAccount.accountNum.substring(0, 4);
		
		bondInfo.issuer = "Farm Bureau";
		bondInfo.type = "Bond";
		bondInfo.bondNum = String.valueOf(DateFunctions.getCurrentTime());
		bondInfo.bondAmount = "19.87";
		bondInfo.effectiveFrom = DateFunctions.getToday();
		bondInfo.effectiveTo = DateFunctions.getDateAfterToday(1);
		bondInfo.status = OrmsConstants.ACTIVE_STATUS;
	}
	
	private void initializeFullSearchCriteria(){
		logger.info("initial full search criteria.");
		
		searchVendor.name = vendor.name;
		searchVendor.status = vendor.status;
		searchVendor.vendorSearchBy = "Tax ID";
		searchVendor.vendorSearchByValue = vendor.taxID;
		
		searchStore.storeName = store.storeName;
		searchStore.status = store.status;
		searchStore.storeSearchBy = "Old Agent ID";
		searchStore.storeSearchByValue = store.oldStoreID;
		searchStore.physicalAddress.address = store.physicalAddress.address;
		searchStore.physicalAddress.supplementalAddr = store.physicalAddress.supplementalAddr;
		searchStore.physicalAddress.city = store.physicalAddress.city;
		searchStore.physicalAddress.country = store.physicalAddress.country;
		searchStore.physicalAddress.state = store.physicalAddress.state;
		searchStore.physicalAddress.county = store.physicalAddress.county;
		searchStore.physicalAddress.zip = store.physicalAddress.zip;
		
		searchBankAccountNum = bankAccount.accountNum;
		searchBondNum = bondInfo.bondNum;
	}
	
	private void verifySearchResultByVendorNum(String expectVendorNum){
		LicMgrVendorDetailsPage vendorDetailPg = LicMgrVendorDetailsPage.getInstance();
		
		logger.info("Verify search result by vendor number.");
		if(!vendorDetailPg.exists()){
			throw new ErrorOnPageException("When search by vendor number, after click search button, " +
					"should switch to vendor detail page.");
		}	
		String actualValue = vendorDetailPg.getVendorNum();
		if(!actualValue.equals(expectVendorNum)){
			throw new ErrorOnPageException("Expect vendor number should be " + expectVendorNum
					+ ", but acutally is " + actualValue);
		}
	}
	
	private void verifySearchResultByStoreID(String expectStoreID){
		LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage.getInstance();
		
		logger.info("Verify search result by store ID.");
		if(!storeDetailPg.exists()){
			throw new ErrorOnPageException("When search by store ID, after click search button, " +
					"should switch to store detail page.");
		}
		String actualVaue = storeDetailPg.getStoreID();
		if(!actualVaue.equals(expectStoreID)){
			throw new ErrorOnPageException("Expect store ID should be " + expectStoreID 
					+", but actually is " + actualVaue);
		}
	}
	
	private void verifySearchResultList(VendorInfo expVendorInfo, StoreInfo expStoreInfo){
		LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage.getInstance();
		
		logger.info("Verify search result list.");
		boolean existing = vendorSearchPg.checkVendorInfoIsExistInListPg(expVendorInfo.number);
		if(existing){
			vendorSearchPg.verifyVendorListInfo(expVendorInfo);
			vendorSearchPg.verifyAgentListInfo(expStoreInfo);
			vendorSearchPg.verifyAgentIsBelongToVendor(expVendorInfo.number, expStoreInfo.storeName, expStoreInfo.storeID);		
		}else {
			throw new ErrorOnPageException("Vendor info not exist in list page. Please check.");
		}
		
	}
}
