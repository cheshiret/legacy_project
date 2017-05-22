package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.vendor;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ApplicationStatusCheck;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorDetailAddAndContractsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorSearchListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:
 * This case block by DEFECT-32462,DEFECT-32395 
 * @Preconditions:
 * @SPEC: Edit Vendor.UCS
 * @Task#:Auto-758

 * @author VZhang
 * @Date Dec 23, 2011
 */
public class EditVendor extends LicenseManagerTestCase{
	private VendorInfo vendor = new VendorInfo();
	private LicMgrVendorSearchListPage vendorSearchListPg = LicMgrVendorSearchListPage.getInstance();

	@Override
	public void execute() {
		//Login license manager and goto vendor search page
		lm.loginLicenseManager(login);
		lm.gotoVendorSearchPg();
		
		//add a vendor as precondition
		lm.addVendor(vendor);
		
		lm.gotoVendorDetailPgFromVendorSearchPg(vendor.number);
		//initial update vendor basic info
		this.initialUpdateVendorBasicInfo();
		//update vendor info
		lm.updateVendorBasicInfo(vendor);
		lm.searchVendorInfoByVendorName(vendor.name);
		//verify vendor info from vendor list page
		vendorSearchListPg.verifyVendorListInfo(vendor);
		
		lm.gotoVendorDetailPgFromVendorSearchPg(vendor.number);
		//verify vendor basic info from vendor detail page
		this.verifyVendorBasicInfo(vendor);
		
		lm.gotoVendorAddressAndContactSubPagFromVendorDetailPg();
		//initial vendor address and contact info
		this.initialUpdateVendorAddressAndContactInfo();
		//update vendor address and contact info
		lm.updateVendorAddressAndContactInfo(vendor,true);	
		
		lm.searchVendorInfoByVendorName(vendor.name);
		//verify vendor info from vendor list page
		vendorSearchListPg.verifyVendorListInfo(vendor);
		//go to detail page
		lm.gotoVendorDetailPgFromVendorSearchPg(vendor.number);
		//verify vendor address and contact info from detail page
		this.verifyVendorAddressAndContactInfo(vendor);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		vendor.appReceivedDate = DateFunctions.getToday();
		vendor.appName = "Basic Test Case";
		vendor.appPhone = "(905) 286-7777";
		vendor.appEmail = "12345@activenetwork.com";
		vendor.requestStoreNum = "5";
		vendor.requestStoreEquipmentNum = "2";
		
		vendor.number = "EditVendor" + DateFunctions.getCurrentTime();
		vendor.name = vendor.number;
		vendor.ownerName = "Basic Test Case";
		vendor.vendorType = "Government";
		
		vendor.taxID = "000000001";
		vendor.taxIDType = "Federal Tax ID";

		vendor.phyAddress = "address";
		vendor.phySuppAddress = "address";
		vendor.phyCityTown = "Simi Valley";
		vendor.phyStateProvince = "California";
		vendor.phyCounty = "Ventura";
		vendor.phyZipPostal = "93063";
		vendor.phyCountry = "United States";
		vendor.isValidatePhysicalAdd = true;
		vendor.phyAddStatus = "Pending";
		
		vendor.isMailingAddSameAsPhysicalAdd = false;
		vendor.mailingAddress = "address1";
		vendor.mailingSuppAddress = "address1";
		vendor.mailingCityTown = "New Albany";
		vendor.mailingStateProvince = "Mississippi";
		vendor.mailingCounty = "Union";
		vendor.mailingZipPostal = "38652";
		vendor.mailingCountry = "United States";
		vendor.isValidateMailingAdd = true;
		vendor.mailingAddStatus = "Pending";
		
		vendor.contacts = new ArrayList<Contacts>();
		Contacts contact = new Contacts();
		contact.contactType = "Business Mgr";
		contact.isPrimary = false;
		contact.firstName = "Add";
		contact.midName = "Vendor";
		contact.lastName = "Test";
		contact.suffix = "SR";
		contact.businessPhone = "9052867777";
		contact.homePhone = "9052867777";
		contact.mobilePhone = "9052867777";
		contact.fax = "9052867777";
		contact.email = "12345@activenetwork.com";
		contact.website = "www.activenetwork.com";
		
		Contacts contact1 = new Contacts();
		contact1.contactType = "Finance Mgr";
		contact1.isPrimary = true;
		contact1.firstName = "Add1";
		contact1.midName = "Vendor1";
		contact1.lastName = "Test1";
		contact1.suffix = "JR";
		contact1.businessPhone = "9052868888";
		contact1.homePhone = "9052868888";
		contact1.mobilePhone = "9052868888";
		contact1.fax = "9052868888";
		contact1.email = "12345@activenetwork.com";
		contact1.website = "www.activenetwork.com";
		vendor.contacts.add(contact);
		vendor.contacts.add(contact1);
		
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
	}
	
	private void initialUpdateVendorBasicInfo(){
		vendor.name = "Vendor" + DateFunctions.getCurrentTime();
		vendor.status = "Inactive - Other";
		vendor.ownerName = "Basic Test Case Edit";
		vendor.vendorType = "Corporation";
		
		vendor.taxID = "000000002";
		vendor.taxIDType = "SSN";
		
		vendor.vendorCreationDate = DateFunctions.getToday();
		vendor.vendorCreationUser = login.userName;
	}
	
	private void initialUpdateVendorAddressAndContactInfo(){
		vendor.phyAddress = "edit address";
		vendor.phySuppAddress = "edit address";
		vendor.phyCityTown = "New Albany";
		vendor.phyStateProvince = "Mississippi";
		vendor.phyCounty = "Union";
		vendor.phyZipPostal = "38652";
		vendor.phyCountry = "United States";
		vendor.isValidatePhysicalAdd = true;
		vendor.phyAddStatus = "Pending|Zip Only";
		
		vendor.isMailingAddSameAsPhysicalAdd = true;
		vendor.mailingAddress = "edit address";
		vendor.mailingSuppAddress = "edit address";
		vendor.mailingCityTown = "New Albany";
		vendor.mailingStateProvince = "Mississippi";
		vendor.mailingCounty = "Union";
		vendor.mailingZipPostal = "38652";
		vendor.mailingCountry = "United States";
		vendor.isValidateMailingAdd = false;
		vendor.mailingAddStatus = "Pending|Zip Only";

		vendor.alterAddress = "address";
		vendor.alterSuppAddress = "address";
		vendor.alterCityTown = "Simi Valley";
		vendor.alterStateProvince = "California";
		vendor.alterCounty = "Ventura";
		vendor.alterZipPostal = "93063";
		vendor.alterCountry = "United States";
		vendor.isValidateAlterAdd = true;
		vendor.alterAddStatus = "Pending|Zip Only";
		
		vendor.removedContacts = new ArrayList<Contacts>();
		vendor.contacts.get(1).isPrimary = false;
		vendor.removedContacts.add(vendor.contacts.get(1));
		
		vendor.contacts.clear();
		Contacts updatedContact = new Contacts();
		updatedContact.contactType = "Business Mgr";
		updatedContact.isPrimary = true;
		updatedContact.firstName = "Edit";
		updatedContact.midName = "Vendor";
		updatedContact.lastName = "Test";
		updatedContact.suffix = "JR";
		updatedContact.businessPhone = "9052863333";
		updatedContact.homePhone ="9052863333";
		updatedContact.mobilePhone = "9052863333";
		updatedContact.fax = "9052863333";
		updatedContact.email = "12345@activenetwork.com";
		updatedContact.website = "www.activenetwork.com";
		vendor.contacts.add(updatedContact);
	}
	
	private void verifyVendorBasicInfo(VendorInfo expectVendor){
		LicMgrVendorDetailsPage vendorDetailPg = LicMgrVendorDetailsPage.getInstance();
		boolean result = true;
		
		logger.info("Verify vendor basic info.");
		result = vendorDetailPg.compareVendorBasicInfo(expectVendor);
		if(!result){
			throw new ErrorOnPageException("Vendor basic info is not correct, please check error log.");
		}
	}
	
	private void verifyVendorAddressAndContactInfo(VendorInfo expectVendor){
		LicMgrVendorDetailAddAndContractsPage vendorAddressAndContactPg = LicMgrVendorDetailAddAndContractsPage.getInstance();
		boolean result = true;
		
		logger.info("Verify vendor address and contact info.");
		result = vendorAddressAndContactPg.compareVendorAddressAndContactInfo(expectVendor);
		if(!result){
			throw new ErrorOnPageException("Vendor Address and contact info are not correct. Please check error log.");
		}
	}
}
