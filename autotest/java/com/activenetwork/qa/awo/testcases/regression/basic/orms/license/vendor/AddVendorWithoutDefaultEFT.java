package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.vendor;

import java.util.ArrayList;
import java.util.HashMap;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ApplicationStatusCheck;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrVendorFinConfigInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorApplicationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorDetailAddAndContractsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorFinConfigSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorSearchListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case is used to verify add vendor without default EFT info
 * This case block by DEFECT-32462,DEFECT-32395 
 * @Preconditions:
 * If system did not display application status check info, please insert the following script into DB:
 * 1, make sure system display application status check info  
 * insert into d_loc_attr_value (id, loc_id, attr_id, value)
 * values (get_sequence('d_loc_attr_value'), 1, 4000, 'Y');
 * 
 * 2, add application status check info
 * insert into d_vendor_app_check(id, loc_id, check_name, precedence_key, active, deleted)
 * values (1,1,'Credit Check Run',1,1,0);
 * 
 * insert into d_vendor_app_check(id, loc_id, check_name, precedence_key, active, deleted)
 * values (2,1,'Law Enforcement Background',3,1,0);
 * 
 * insert into d_vendor_app_check(id, loc_id, check_name, precedence_key, active, deleted)
 * values (3,1,'Owner Suspensions Check',2,1,0);
 * 
 * insert into d_vendor_app_check(id, loc_id, check_name, precedence_key, active, deleted)
 * values (4,1,'Law enforcement A check',3,1,0);
 * 
 * @SPEC:Add Vendor.UCS
 * @Task#:Auto-758

 * @author VZhang
 * @Date Dec 22, 2011
 */
public class AddVendorWithoutDefaultEFT extends LicenseManagerTestCase{
	private VendorInfo vendor = new VendorInfo();
	private LicMgrVendorSearchListPage vendorSearchListPg = LicMgrVendorSearchListPage.getInstance();
	private LicMgrVendorFinConfigSubPage vendorFinConfigPg = LicMgrVendorFinConfigSubPage.getInstance();

	@Override
	public void execute() {
		//Login license manager and goto vendor search page
		lm.loginLicenseManager(login);
		lm.gotoVendorSearchPg();
		//add vendor with default eft info
		lm.addVendor(vendor);
		lm.searchVendorInfoByVendorName(vendor.name);
		//verify vendor info on vendor list page
		vendorSearchListPg.verifyVendorListInfo(vendor);
		
		//go to vendor detail page
		lm.gotoVendorDetailPgFromVendorSearchPg(vendor.number);
		//compare vendor detail info
		this.verifyVendorBasicInfo(vendor);
		
		//go to vendor application page
		lm.gotoVendorApplicationPgFromVendorDetailPg();
		//compare vendor application info
		this.verifyVendorApplicationInfo(vendor);
		
		//go to vendor detail page
		lm.gotoVendorDetialPgFromVendorApplicationPg();
		//go to vendor address and contact page
		lm.gotoVendorAddressAndContactSubPagFromVendorDetailPg();
		//initial address and contact info
		this.initialExpectAddressAndContactInfo();
		//verify vendor address and contact info
		this.verifyVendorAddressAndContactInfo(vendor);
		
		//go to vendor finance configuration page
		lm.gotoVendorFinConfigSubPage();
		//initial expect notification email info
		//verify vendor finance(EFT) info
		vendorFinConfigPg.verifyFinancialInfo(vendor.finConfigInfo);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		vendor.appReceivedDate = DateFunctions.getToday();
		
		vendor.appName = "Basic Test Case";
		vendor.appPhone = "9052867777";
		vendor.appEmail = "12345@activenetwork.com";
		vendor.requestStoreNum = "5";
		vendor.requestStoreEquipmentNum = "2";
		vendor.appCreationDate = DateFunctions.getToday();
		vendor.appCreationUser = login.userName;
		
		vendor.number = "AddVendor" + DateFunctions.getCurrentTime();
		vendor.status = "Active";
		vendor.name = vendor.number;
		vendor.ownerName = "Basic Test Case";
		vendor.vendorType = "Government";
		vendor.vendorCreationDate = DateFunctions.getToday();
		vendor.vendorCreationUser = login.userName;
		
		
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
		vendor.phyAddStatus = "Pending|Zip Only";
		
		vendor.isMailingAddSameAsPhysicalAdd = true;

		vendor.contacts = new ArrayList<Contacts>();
		Contacts contact = new Contacts();
		contact.contactType = "Business Mgr";
		contact.isPrimary = true;
		contact.firstName = "Add";
		contact.midName = "Vendor";
		contact.lastName = "Test";
		contact.suffix = "SR";
		contact.businessPhone = "9052867777";
		contact.homePhone ="9052867777";
		contact.mobilePhone = "9052867777";
		contact.fax = "9052867777";
		contact.email = "12345@activenetwork.com";
		contact.website = "www.activenetwork.com";
		vendor.contacts.add(contact);
		
		vendor.finConfigInfo = new LicMgrVendorFinConfigInfo();
		vendor.finConfigInfo.eftType = "EFT";
		vendor.finConfigInfo.invoiceSchedule = "Weekly";
		vendor.finConfigInfo.failedEFTEnforcement = "Revoke After 2nd EFT Failure";
		vendor.finConfigInfo.invoiceFrequency = "Weekly";
		vendor.finConfigInfo.invoiceSchedule = "Weekly";
		vendor.finConfigInfo.voidReturnChargeDays = "1";
		vendor.finConfigInfo.autoReturnVoidedDoc= "Yes";
		vendor.finConfigInfo.rptNotification = new HashMap<String, Boolean>();
		vendor.finConfigInfo.rptNotification.put("EFT Invoice", true);
		vendor.finConfigInfo.rptNotification.put("Daily Sales Activity", true);
		vendor.finConfigInfo.reportEmails = new ArrayList<String>();
		vendor.finConfigInfo.reportEmails.add("AO.QAOrmstest@activenetwork.com");
		vendor.finConfigInfo.reportEmails.add("noreply@reserveamerica.com");
		
		vendor.removeRepNotifiEmails = new ArrayList<String>();
		vendor.removeRepNotifiEmails.add(vendor.finConfigInfo.reportEmails.get(1));
		
		vendor.applicationStatusCheck  = new ArrayList<ApplicationStatusCheck>();
		ApplicationStatusCheck appStatusCheck = new ApplicationStatusCheck();
		appStatusCheck.byPassChecked = true;
		appStatusCheck.statusCheck = "Credit Check Run";
		appStatusCheck.dateCompleted = "";
		appStatusCheck.completedBy = "";
		appStatusCheck.comments = "";
		
		ApplicationStatusCheck appStatusCheck1 = new ApplicationStatusCheck();
		appStatusCheck1.byPassChecked = false;
		appStatusCheck1.statusCheck = "Owner Suspensions Check";
		appStatusCheck1.dateCompleted = DateFunctions.getToday();
		appStatusCheck1.completedBy = "Auto Test";
		appStatusCheck1.comments =  "Basic Test Case of Add Vendor";
		
		ApplicationStatusCheck appStatusCheck2 = new ApplicationStatusCheck();
		appStatusCheck2.byPassChecked = true;
		appStatusCheck2.statusCheck = "Law enforcement A check";
		appStatusCheck2.dateCompleted = "";
		appStatusCheck2.completedBy = "";
		appStatusCheck2.comments = "";
		
		ApplicationStatusCheck appStatusCheck3 = new ApplicationStatusCheck();
		appStatusCheck3.byPassChecked = false;
		appStatusCheck3.statusCheck = "Law Enforcement Background";
		appStatusCheck3.dateCompleted = DateFunctions.getToday();
		appStatusCheck3.completedBy = "Auto Test";
		appStatusCheck3.comments = "Basic Test Case of Add Vendor";
		vendor.applicationStatusCheck.add(appStatusCheck);
		vendor.applicationStatusCheck.add(appStatusCheck1);
		vendor.applicationStatusCheck.add(appStatusCheck2);
		vendor.applicationStatusCheck.add(appStatusCheck3);
	}
	
	private void initialExpectAddressAndContactInfo(){
		vendor.mailingAddress = "address";
		vendor.mailingSuppAddress = "address";
		vendor.mailingCityTown = "Simi Valley";
		vendor.mailingStateProvince = "California";
		vendor.mailingCounty = "Ventura";
		vendor.mailingZipPostal = "93063";
		vendor.mailingCountry = "United States";
		vendor.mailingAddStatus = "Pending|Zip Only";
		
		vendor.alterAddress = "";
		vendor.alterSuppAddress = "";
		vendor.alterCityTown = "";
		vendor.alterStateProvince = "";
		vendor.alterCounty = "";
		vendor.alterZipPostal = "";
		vendor.alterCountry = "";
		vendor.alterAddStatus = "";
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
	
	private void verifyVendorApplicationInfo(VendorInfo expectVendor){
		LicMgrVendorApplicationPage vendorApplicationPg = LicMgrVendorApplicationPage.getInstance();
		boolean result = true;
		
		logger.info("Verify vendor application info.");
		result = vendorApplicationPg.compareVendorApplicationInfo(expectVendor);
		if(!result){
			throw new ErrorOnPageException("Vendor address and contact info are not correct. please check error log.");
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
