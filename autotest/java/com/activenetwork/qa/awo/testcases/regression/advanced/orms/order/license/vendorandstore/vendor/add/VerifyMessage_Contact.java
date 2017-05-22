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
 * @Description:This case is used to verify error message when add vendor contact info
 * @Preconditions:
 * @SPEC:Add Vendor.UCS
 * @Task#:AUTO-518

 * @author VZhang
 * @Date Feb 8, 2012
 */
public class VerifyMessage_Contact extends LicenseManagerTestCase{
	private VendorInfo vendor = new VendorInfo();
	private LicMgrVendorAddVendorPage addVendorPg = LicMgrVendorAddVendorPage.getInstance();
	private LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage.getInstance();
	private String actualMessage = "";
	private String expMessage1,expMessage2,expMessage3,expMessage4,expMessage5,expMessage6,expMessage7;
	private boolean result = true;

	@Override
	public void execute() {
		//Login license manager and goto vendor search page
		lm.loginLicenseManager(login);
		lm.gotoVendorSearchPg();
		
		this.gotoAddVendorPg();
		//contact type is not specific
		expMessage1 = "At least one Contact Type is required. Please specify a Contact Type.";
		actualMessage = this.AddVendorBasicAndContactInfo(vendor, 0);
		this.verifyErrorMessage(actualMessage, expMessage1);
		
		//contact first name is not specific
		Contacts contact = new Contacts();
		vendor.contacts.add(contact);
		contact.contactType = "Business Mgr";
		expMessage2 = "First Name is required for Contact Type \"" + contact.contactType + "\". Please specify the First Name.";
		actualMessage = this.AddVendorBasicAndContactInfo(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage2);
		
		//contact last name is not specific
		contact.firstName = "Add";
		expMessage3 = "Last Name is required for Contact Type \"" + contact.contactType + "\". Please specify the Last Name.";
		actualMessage = this.AddVendorBasicAndContactInfo(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage3);
		
		//contact phone number is not specific
		contact.lastName = "Add";
		expMessage4 = "At least one Phone is required for Contact Type \"" + contact.contactType + "\". Please specify the Business, Home, or Mobile Phone.";
		actualMessage = this.AddVendorBasicAndContactInfo(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage4);
		
		//contact phone number is not correct
		contact.businessPhone = "123456q";
		expMessage5 = "Contact Phone (Business, Home, or Mobile) must contain only numbers, spaces, brackets, dashes or a single 'x' (to denote extension number). Please change your entries for Contact Type \"" + contact.contactType + "\".";
		actualMessage = this.AddVendorBasicAndContactInfo(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage5);
		
		//contact fax is not correct
		contact.businessPhone = "1234567";
		contact.fax =  "123456q";
		expMessage6 = "Contact Fax must contain only numbers, spaces, brackets, dashes or a single 'x' (to denote extension number). Please change your entries for Contact Type \"" + contact.contactType + "\".";
		actualMessage = this.AddVendorBasicAndContactInfo(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage6);
		
		//contact email is not correct
		contact.fax =  "1234567";
		contact.email = "12345activenetwork.com";
		expMessage7 = "Contact Email must be a valid email. Please change your entries for Contact Type \"" + contact.contactType + "\".";
		actualMessage = this.AddVendorBasicAndContactInfo(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage7);
		
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
		
		vendor.isMailingAddSameAsPhysicalAdd = true;
		
		vendor.contacts = new ArrayList<Contacts>();
	}
	
	private void gotoAddVendorPg(){
		logger.info("Go to Add Vendor Page.");
		vendorSearchPg.clickAddVendor();
		ajax.waitLoading();
		addVendorPg.waitLoading();
	}
	
	private String AddVendorBasicAndContactInfo(VendorInfo addVendor, int value){
		String toReturn = "";
		logger.info("Add vendor info.");
		
		switch (value){
		case 0:
			logger.info("Add vendor basic, physical address info and mailing address info as same as physical address info.");
			addVendorPg.setupVendorAppInfo(addVendor);
			addVendorPg.setupVendorBasicInfo(addVendor);
			addVendorPg.setupVendorPhyAddressInfo(addVendor);
			break;
		case 1:
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
			logger.info("Error Message is correct.");
		}
	}

}
