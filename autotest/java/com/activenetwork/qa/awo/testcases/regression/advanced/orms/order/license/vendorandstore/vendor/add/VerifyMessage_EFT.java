package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.vendorandstore.vendor.add;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorAddVendorPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorSearchListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class VerifyMessage_EFT extends LicenseManagerTestCase{
	private VendorInfo vendor = new VendorInfo();
	private LicMgrVendorAddVendorPage addVendorPg = LicMgrVendorAddVendorPage.getInstance();
	private LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage.getInstance();
	private String actualMessage = "";
	private String expMessage1,expMessage2,expMessage3,expMessage4,expMessage5,expMessage6;
	private boolean result = true;

	@Override
	public void execute() {
		//Login license manager and goto vendor search page
		lm.loginLicenseManager(login);
		lm.gotoVendorSearchPg();

		this.gotoAddVendorPg();
		//EFT Type is not specific
		vendor.finConfigInfo.eftType = "";
		actualMessage = this.AddVendorBasicAndEFT(vendor, 0);
		this.verifyErrorMessage(actualMessage, expMessage1);

		//EFT schedule is not specific
		vendor.finConfigInfo.eftType = "EFT";
		vendor.finConfigInfo.invoiceSchedule = "";
		actualMessage = this.AddVendorBasicAndEFT(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage2);
		
		//EFT void return charge day is not specific
		vendor.finConfigInfo.invoiceSchedule = "Weekly";
		vendor.finConfigInfo.voidReturnChargeDays = "";
		actualMessage = this.AddVendorBasicAndEFT(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage3);

		//EFT void return charge day is not greater than 0
		vendor.finConfigInfo.voidReturnChargeDays = "0";
		actualMessage = this.AddVendorBasicAndEFT(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage4);

		//EFT failed Enforcement is not specific
		addVendorPg.unSelectFailedEFTEnforcement();
		vendor.finConfigInfo.voidReturnChargeDays = "5";
		vendor.finConfigInfo.failedEFTEnforcement = "";
		actualMessage = this.AddVendorBasicAndEFT(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage5);

		//EFT report notification email is not correct
		vendor.finConfigInfo.failedEFTEnforcement = "Don't Enforce";
		vendor.finConfigInfo.reportEmails = new ArrayList<String>();
		vendor.finConfigInfo.reportEmails.add("reserveamrica.com");
		actualMessage = this.AddVendorBasicAndEFT(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage6);
		
		if(!result){
			throw new ErrorOnPageException("Error Message Not Correct,please check Error Log.");
		}
		
		lm.logOutLicenseManager();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";	
				
		vendor.appReceivedDate = DateFunctions.getToday();
		vendor.appName = "Advanced Test Case";
		vendor.appPhone = "9052867777";
		vendor.appEmail = "12345@activenetwork.com";
		vendor.requestStoreNum = "5";
		vendor.requestStoreEquipmentNum = "2";
		
		vendor.number = "EditVendor" + DateFunctions.getCurrentTime();
		vendor.name = vendor.number;
		vendor.ownerName = "Advanced Test Case";
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
		
		vendor.contacts = new ArrayList<Contacts>();
		Contacts contact = new Contacts();
		contact.contactType = "Business Mgr";
		contact.isPrimary = true;
		contact.firstName = "Add";
		contact.midName = "Vendor";
		contact.lastName = "Test";
		contact.suffix = "SR";
		contact.businessPhone = "9052867777";
		vendor.contacts.add(contact);

		vendor.finConfigInfo.eftType = "EFT";
		vendor.finConfigInfo.invoiceFrequency = "Weekly";
		vendor.finConfigInfo.invoiceSchedule = "Weekly";
		vendor.finConfigInfo.voidReturnChargeDays = "0";
		vendor.finConfigInfo.voidReturnChargeDays = "5";
		vendor.finConfigInfo.failedEFTEnforcement = "Don't Enforce";
		
		//Expect error message should be:The Void Document Return Days is required. Please specify your entry.
		//, but actually is:The Invoice Schedule is required. Please select the Invoice Schedule from the list provided

		expMessage1 = "The EFT Type is required. Please select the EFT Type from the list provided.";
		expMessage2 = "The Invoice Schedule is required. Please select the Invoice Schedule from the list provided";
		expMessage3 = "The Void Document Return Days is required. Please specify your entry.";
		expMessage4 = "The Void Document Return Days entered is not valid. Please enter an integer value greater than 0.";
		expMessage5 = "The Failed EFT Deactivation is required. Please select the Failed EFT Deactivation from the list provided.";
		expMessage6 = "An Email Address for the report notification is invalid. Please specify a valid Email Address.";
	}
	
		private void gotoAddVendorPg(){
		logger.info("Go to Add Vendor Page.");
		vendorSearchPg.clickAddVendor();
		ajax.waitLoading();
		addVendorPg.waitLoading();
	}
	
	private String AddVendorBasicAndEFT(VendorInfo addVendor, int value){
		String toReturn = "";
		logger.info("Add vendor info.");
		
		switch (value){
		case 0:
			logger.info("Add vendor basic, physical address info and mailing address info as same as physical address info.");
			addVendorPg.setupVendorAppInfo(addVendor);
			addVendorPg.setupVendorBasicInfo(addVendor);
			addVendorPg.setupVendorPhyAddressInfo(addVendor);	
			addVendorPg.setupVendorContactInfo(addVendor.contacts, addVendor.removedContacts);
			break;
		case 1:
			logger.info("Add vendor EFT info.");
			addVendorPg.setupVendorEFTInfo(addVendor);
			break;
		default:
			throw new ErrorOnPageException("Please check you want added vendor info in which area.");
		}
		
		addVendorPg.clickOK();
		ajax.waitLoading();
		addVendorPg.waitLoading();
		toReturn = addVendorPg.getErrorMsg();

		return toReturn;
	}
	
	private void verifyErrorMessage(String actuMessage, String expMessage){
		logger.info("Verify Error Message.");
		
		if(!actuMessage.equals(expMessage)){
			result &= false;
			logger.error("Expect error message should be:" + expMessage 
					+ ", but actually is:" + actuMessage);
		}else {
			logger.info("Error Message("+expMessage+") is correct.");
		}
	}
}
