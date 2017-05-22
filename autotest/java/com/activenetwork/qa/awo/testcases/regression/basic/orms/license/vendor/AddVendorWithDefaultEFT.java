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
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify add vendor with default EFT info
 * @Preconditions:
 * If system did not display EFT specific default info, please insert the flowing script into DB
 * Notice: the id of eft_schdl will be different with QA1,2,3,4 so if you see the default EFT setup but the specific EFT Schedule ID is still NULL, please check the id of eft_schdl
 * insert into f_vendor_fin_conf_default (id, label, eft_type, eft_schdl,eft_failure_enforce, void_rtn_charge_days, rtn_voided_doc, rpt_notifications, rpt_notification_emails, deleted_ind)
 * values(101, 'Weekly EFT', 2, 133804260, 3, 1, '1', '1;2', 'AO.QAOrmstest@activenetwork.com;noreply@reserveamerica.com', 0);
 * 
 * insert into f_vendor_fin_conf_default (id, label, eft_type, eft_schdl,eft_failure_enforce, void_rtn_charge_days, rtn_voided_doc, rpt_notifications, rpt_notification_emails, deleted_ind)
 * values(102, 'Daily EFT', 2, 133804258, 3, 1, '2', '1;2', 'AO.QAOrmstest@activenetwork.com;noreply@reserveamerica.com', 0);
 * 
 * insert into f_vendor_fin_conf_default (id, label, eft_type, eft_schdl,eft_failure_enforce, void_rtn_charge_days, rtn_voided_doc, rpt_notifications, rpt_notification_emails, deleted_ind)
 * values(1103, 'No EFT', 1, null, null, null, 0, null, null, 0);
 * 
 * @SPEC:Add Vendor.UCS
 * @Task#:Auto-758

 * @author VZhang
 * @Date Dec 22, 2011
 */
public class AddVendorWithDefaultEFT extends LicenseManagerTestCase{
	private VendorInfo vendor = new VendorInfo();
	private LicMgrVendorSearchListPage vendorSearchListPg = LicMgrVendorSearchListPage.getInstance();
	private LicMgrVendorFinConfigSubPage vendorFinConfigPg = LicMgrVendorFinConfigSubPage.getInstance();
	
	@Override
	public void execute() {
		lm.updateVendorFinancialConfigDefaultEFTSchdl(schema, "Weekly");
		
		//Login license manager and goto vendor search page
		lm.loginLicenseManager(login);
		lm.gotoVendorSearchPg();
		//add vendor with default EFT info
		lm.addVendor(vendor);
		lm.searchVendorInfoByVendorName(vendor.name);
		//verify vendor info on vendor list page
		vendorSearchListPg.verifyVendorListInfo(vendor);
		
		//initial expect vendor info
		this.defaultFinacialConfigInfo();
		
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
		//verify vendor address and contact info
		this.verifyVendorAddressAndContactInfo(vendor);
		
		//go to vendor finance configuration page
		lm.gotoVendorFinConfigSubPage();
		//verify vendor finance(EFT) info
		vendorFinConfigPg.verifyFinancialInfo(vendor.finConfigInfo);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		vendor.appReceivedDate = DateFunctions.getToday();
		vendor.appName = "Basic Test Case";
		vendor.appPhone = "9052867777";
		vendor.appEmail = "12345@activenetwork.com";
		vendor.requestStoreNum = "5";
		vendor.requestStoreEquipmentNum = "2";
		vendor.appCreationDate = DateFunctions.getToday();
		vendor.appCreationUser = login.userName;
		
		vendor.number = "AddVendor" + DateFunctions.getCurrentTime();
		vendor.name = vendor.number;
		vendor.status = "Active";
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
		
		vendor.isMailingAddSameAsPhysicalAdd = false;
		vendor.mailingAddress = "address1";
		vendor.mailingSuppAddress = "address1";
		vendor.mailingCityTown = "New Albany";
		vendor.mailingStateProvince = "Mississippi";
		vendor.mailingCounty = "Union";
		vendor.mailingZipPostal = "38652";
		vendor.mailingCountry = "United States";
		vendor.isValidateMailingAdd = true;
		vendor.mailingAddStatus = "Pending|Zip Only";
		
		vendor.contacts = new ArrayList<Contacts>();
		Contacts contact = new Contacts();
		contact.contactType = "Business Mgr";
		contact.isPrimary = false;
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
		
		Contacts contact1 = new Contacts();
		contact1.contactType = "Finance Mgr";
		contact1.isPrimary = false;
		contact1.firstName = "Add1";
		contact1.midName = "Vendor1";
		contact1.lastName = "Test1";
		contact1.suffix = "JR";
		contact1.businessPhone = "9052868888";
		contact1.homePhone ="9052868888";
		contact1.mobilePhone = "9052868888";
		contact1.fax = "9052868888";
		contact1.email = "12345@activenetwork.com";
		contact1.website = "www.activenetwork.com";
		
		Contacts contact2 = new Contacts();
		contact2.contactType = "Finance Mgr";
		contact2.isPrimary = true;
		contact2.firstName = "Add3";
		contact2.midName = "Vendor3";
		contact2.lastName = "Test3";
		contact2.suffix = "JR";
		contact2.businessPhone = "9052865555";
		contact2.homePhone ="9052865555";
		contact2.mobilePhone = "9052865555";
		contact2.fax = "9052865555";
		contact2.email = "12345@activenetwork.com";
		contact2.website = "www.activenetwork.com";
		vendor.contacts.add(contact);
		vendor.contacts.add(contact1);
		vendor.contacts.add(contact2);
		
		vendor.removedContacts = new ArrayList<Contacts>();
		vendor.removedContacts.add(contact1);
		
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
	
	private void defaultFinacialConfigInfo(){
		//default EFT financial config info
		vendor.finConfigInfo = new LicMgrVendorFinConfigInfo();
		vendor.finConfigInfo.eftType = "EFT";
		vendor.finConfigInfo.invoiceSchedule = "Weekly";
		vendor.finConfigInfo.failedEFTEnforcement = "Revoke After 2nd EFT Failure";
		vendor.finConfigInfo.voidReturnChargeDays = "1";
		vendor.finConfigInfo.autoReturnVoidedDoc= "Yes";
		vendor.finConfigInfo.rptNotification = new HashMap<String, Boolean>();
		vendor.finConfigInfo.rptNotification.put("EFT Invoice", true);
		vendor.finConfigInfo.rptNotification.put("Daily Sales Activity", true);
		vendor.finConfigInfo.reportEmails = new ArrayList<String>();
		vendor.finConfigInfo.reportEmails.add("AO.QAOrmstest@activenetwork.com");
		vendor.finConfigInfo.reportEmails.add("noreply@reserveamerica.com");
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
