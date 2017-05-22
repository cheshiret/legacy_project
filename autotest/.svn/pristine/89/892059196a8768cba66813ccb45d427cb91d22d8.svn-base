package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.vendorandstore.vendor.add;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorAddVendorPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorSearchListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case is used to verify error message when add vendor basic info
 * @Preconditions:Vendor AutoTest has been existed.
 * @SPEC:Add Vendor.UCS
 * @Task#:AUTO-518

 * @author VZhang
 * @Date Feb 8, 2012
 */
public class VerifyMessage_Basic extends LicenseManagerTestCase{
	private VendorInfo vendor = new VendorInfo();
	private LicMgrVendorAddVendorPage addVendorPg = LicMgrVendorAddVendorPage.getInstance();
	private LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage.getInstance();
	private String actualMessage = "";
	private String expMessage1,expMessage2,expMessage3,expMessage4,expMessage5,expMessage6,expMessage7,
	expMessage8,expMessage9,expMessage10,expMessage11,expMessage12,expMessage13;
	private boolean result = true;

	@Override
	public void execute() {
		//Login license manager and goto vendor search page
		lm.loginLicenseManager(login);
		lm.gotoVendorSearchPg();
		
		this.gotoAddVendorPg();
		//vendor number is not specific
		vendor.number = "";
		this.addVendorBasicAndAppInfo(vendor, 0);// physical
		this.addVendorBasicAndAppInfo(vendor, 2);// app
		this.addVendorBasicAndAppInfo(vendor, 3);// contact
		actualMessage = this.addVendorBasicAndAppInfo(vendor, 1);// basic
		this.verifyErrorMessage(actualMessage, expMessage1);
		
		//vendor name is not specific
		vendor.number = "A" + DateFunctions.getCurrentTime();
		vendor.name = "";
		actualMessage = this.addVendorBasicAndAppInfo(vendor, 1);// basic
		this.verifyErrorMessage(actualMessage, expMessage2);
		
		//vendor number is not correct
		vendor.name = vendor.number;
		vendor.number = "Auto Test";
		actualMessage = this.addVendorBasicAndAppInfo(vendor, 1);// basic
		this.verifyErrorMessage(actualMessage, expMessage3);
		
		//vendor owner name is not specific
		vendor.number = "AutoTest";
		vendor.ownerName = "";
		actualMessage = this.addVendorBasicAndAppInfo(vendor, 1);// basic
		this.verifyErrorMessage(actualMessage, expMessage4);
		
		//vendor application received date is not specific
		vendor.ownerName = "Auto";
		vendor.appReceivedDate = "";
		this.addVendorBasicAndAppInfo(vendor, 1);// basic
		actualMessage = this.addVendorBasicAndAppInfo(vendor, 2);// app
		this.verifyErrorMessage(actualMessage, expMessage5);
		
		//vendor application name is not specific
		vendor.appReceivedDate = DateFunctions.getToday();
		vendor.appName = "";
		actualMessage = this.addVendorBasicAndAppInfo(vendor, 2);// app
		this.verifyErrorMessage(actualMessage, expMessage6);
		
		//vendor application phone is not specific
		vendor.appName = "Auto";
		vendor.appPhone = "";
		actualMessage = this.addVendorBasicAndAppInfo(vendor, 2);// app
		this.verifyErrorMessage(actualMessage, expMessage7);
		
		//vendor application phone is not correct
		vendor.appPhone = "123456q";
		actualMessage = this.addVendorBasicAndAppInfo(vendor, 2);// app
		this.verifyErrorMessage(actualMessage, expMessage8);
		
		//vendor requested store number is not specific
		vendor.appPhone = "1234567";
		vendor.requestStoreNum = "";
		actualMessage = this.addVendorBasicAndAppInfo(vendor, 2);// app
		this.verifyErrorMessage(actualMessage, expMessage9);
		
		//vendor requested store number is not correct
		vendor.requestStoreNum = "-1";
		actualMessage = this.addVendorBasicAndAppInfo(vendor, 2);// app
		this.verifyErrorMessage(actualMessage, expMessage10);
		
		//vendor equipment per agent requested is not specific
		vendor.requestStoreNum = "5";
		vendor.requestStoreEquipmentNum = "";
		actualMessage = this.addVendorBasicAndAppInfo(vendor, 2);// app
		this.verifyErrorMessage(actualMessage, expMessage11);
		
		//vendor equipment per agent requested is not correct
		vendor.requestStoreEquipmentNum = "-1";
		actualMessage = this.addVendorBasicAndAppInfo(vendor, 2);// app
		this.verifyErrorMessage(actualMessage, expMessage12);
		
		//vendor number is exists
		vendor.requestStoreEquipmentNum = "5";
		addVendorPg.setupVendorEFTInfo(vendor);
		actualMessage = this.addVendorBasicAndAppInfo(vendor, 2);// app
		this.verifyErrorMessage(actualMessage, expMessage13);
		
		if(!result){
			throw new ErrorOnPageException("Error Message Not Correct,please check Error Log.");
		}
		lm.logOutLicenseManager();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		vendor.specifyDefault = "Weekly EFT";
		vendor.isFillValues = true;
		
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

		vendor.number = "A" + DateFunctions.getCurrentTime();
		vendor.name = vendor.number;
		vendor.ownerName = "Auto";
		vendor.appReceivedDate = DateFunctions.getToday();
		vendor.appName = "Auto";
		vendor.appPhone = "1234567";
		vendor.requestStoreNum = "5";
		vendor.requestStoreEquipmentNum = "2";
		
		expMessage1 = "Vendor # is required. Please specify the Vendor #.";
		expMessage2 = "Vendor Name is required. Please specify the Vendor Name.";
		expMessage3 = "Vendor # must contain only numbers or letters. Please re-enter.";
		expMessage4 = "Owner Name is required. Please specify the Owner Name.";
		expMessage5 = "Application Received Date is required. Please specify the Application Received Date.";
		expMessage6 = "Applicant Name is required. Please specify the Applicant Name.";
		expMessage7 = "Applicant Phone is required. Please specify the Applicant Phone.";
		expMessage8 = "Applicant Phone must contain only numbers, spaces, brackets, dashes or a single 'x' (to denote extension number). Please re-enter.";
		expMessage9 = "Number of Agents Requested is required. Please specify the Number of Agents Requested.";
		expMessage10 = "Number of Agents Requested must be a number, zero or greater. Please re-enter.";
		expMessage11 = "Equipment Per Agent Requested is required. Please specify the Equipment Per Agent Requested.";
		expMessage12 = "Equipment Per Agent Requested must be a number, zero or greater. Please re-enter.";
		expMessage13 = "Vendor # already exists. Please verify the Vendor #.";		
	}
	
	private void gotoAddVendorPg(){
		logger.info("Go to Add Vendor Page.");
		vendorSearchPg.clickAddVendor();
		ajax.waitLoading();
		addVendorPg.waitLoading();
	}

	private String addVendorBasicAndAppInfo(VendorInfo addVendor, int value){
		String toReturn = "";
		logger.info("Add vendor info.");
		
		switch (value){
		case 0:
			logger.info("Add vendor physical info.");
			addVendorPg.setupVendorPhyAddressInfo(vendor);
			break;
		case 1:
			logger.info("Add vendor basic info");
			addVendorPg.setupVendorBasicInfo(addVendor);
			break;
		case 2:
			logger.info("Add vendor application info.");
			addVendorPg.setupVendorAppInfo(addVendor);
			break;
		case 3:
			logger.info("Add vendor contact info.");
			addVendorPg.setupVendorContactInfo(addVendor.contacts, addVendor.removedContacts);
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
			logger.error("Expect error message should be " + expMessage 
					+ ", but actually is " + actuMessage);
		}else {
			logger.info("Error Message("+expMessage+") is correct.");
		}
	}
}
