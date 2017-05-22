package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.vendorandstore.vendor.edit;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ApplicationStatusCheck;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorDetailAddAndContractsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case is used to verify error message when edit vendor contact and basic info
 * @Preconditions:
 * @SPEC: Edit Vendor.UCS
 * @Task#:AUTO-576

 * @author vzhang
 * @Date Feb 8, 2012
 */
public class VerifyMessage_ContactAndBasic extends LicenseManagerTestCase{
	private VendorInfo vendor = new VendorInfo();
	private String actualMessage = "";
	private String expMessage1,expMessage2,expMessage3,expMessage4,expMessage5,expMessage6,expMessage7,
	               expMessage8,expMessage9,expMessage10,expMessage11,expMessage12,expMessage13;
	private boolean result = true;

	@Override
	public void execute() {
		//Login license manager and goto vendor search page
		lm.loginLicenseManager(login);
		lm.gotoVendorSearchPg();
		
		//add a vendor as precondition
		lm.addVendor(vendor);
		lm.gotoVendorDetailPgFromVendorSearchPg(vendor.number);
		
		//first name is not specific
		vendor.contacts.get(0).firstName = "";
		actualMessage = this.updateVendorContactInfo(vendor);
		this.verifyErrorMessage(actualMessage, expMessage1);
		
		//last name is not specific
		vendor.contacts.get(0).firstName = "Edit1";
		vendor.contacts.get(0).lastName = "";
		actualMessage = this.updateVendorContactInfo(vendor);
		this.verifyErrorMessage(actualMessage, expMessage2);
		
		//phone number is not specific
		vendor.contacts.get(0).lastName = "Edit1";
		vendor.contacts.get(0).businessPhone = "";
		vendor.contacts.get(0).homePhone = "";
		vendor.contacts.get(0).mobilePhone = "";
		actualMessage = this.updateVendorContactInfo(vendor);
		this.verifyErrorMessage(actualMessage, expMessage3);
		
		//phone number is not correct
		vendor.contacts.get(0).businessPhone = "905286333q";
		actualMessage = this.updateVendorContactInfo(vendor);
		this.verifyErrorMessage(actualMessage, expMessage4);
		
		//first name is not specific
		vendor.contacts.get(0).businessPhone = "9052863333";
		vendor.contacts.get(1).firstName = "";
		actualMessage = this.updateVendorContactInfo(vendor);
		this.verifyErrorMessage(actualMessage, expMessage5);
		
		//last name is not specific
		vendor.contacts.get(1).firstName = "Edit2";
		vendor.contacts.get(1).lastName = "";
		actualMessage = this.updateVendorContactInfo(vendor);
		this.verifyErrorMessage(actualMessage, expMessage6);
		
		//phone number is not specific
		vendor.contacts.get(1).lastName = "Edit2";
		vendor.contacts.get(1).businessPhone = "";
		vendor.contacts.get(1).mobilePhone = "";
		vendor.contacts.get(1).homePhone = "";		
		actualMessage = this.updateVendorContactInfo(vendor);
		this.verifyErrorMessage(actualMessage, expMessage7);
		
		//tax number is not correct
		vendor.contacts.get(1).businessPhone = "905283333";
		vendor.contacts.get(1).fax = "90528655q";
		actualMessage = this.updateVendorContactInfo(vendor);
		this.verifyErrorMessage(actualMessage, expMessage8);
		
		//email is not correct
		vendor.contacts.get(1).fax = "905286555";
		vendor.contacts.get(1).email = "12345activenetwork.com";
		actualMessage = this.updateVendorContactInfo(vendor);
		this.verifyErrorMessage(actualMessage, expMessage9);
		
		//duplicate contact type info
		vendor.contacts.get(1).email = "12345@activenetwork.com";
		this.initialVendorContactInfo();
		actualMessage = this.updateVendorContactInfo(vendor);
		this.verifyErrorMessage(actualMessage, expMessage10);
		
		//vendor name is not specific
		lm.gotoVendorDetailsPgFromTopMenu(vendor.number);
		vendor.name = "";
		actualMessage = lm.updateVendorBasicInfo(vendor);
		this.verifyErrorMessage(actualMessage, expMessage11);		
		
		//owner name is not specific
		vendor.name = "Advanced";
		vendor.ownerName = "";
		actualMessage = lm.updateVendorBasicInfo(vendor);
		this.verifyErrorMessage(actualMessage, expMessage12);
		
		//vendor type is not specific
		vendor.ownerName = "activeNetwork";
		vendor.vendorType = "";
		actualMessage = lm.updateVendorBasicInfo(vendor);
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
		
		vendor.isMailingAddSameAsPhysicalAdd = false;
		vendor.mailingAddress = "address1";
		vendor.mailingSuppAddress = "address1";
		vendor.mailingCityTown = "Toronto";
		vendor.mailingStateProvince = "Mississippi";
		vendor.mailingCounty = "Union";
		vendor.mailingZipPostal = "38652";
		vendor.mailingCountry = "United States";
		
		vendor.contacts = new ArrayList<Contacts>();
		Contacts contact = new Contacts();
		contact.contactType = "Business Mgr";
		contact.isPrimary = true;
		contact.firstName = "Add";
		contact.midName = "Vendor";
		contact.lastName = "Test";
		contact.suffix = "SR";
		contact.businessPhone = "9052867777";
		
		Contacts contact1 = new Contacts();
		contact1.contactType = "Finance Mgr";
		contact1.isPrimary = false;
		contact1.firstName = "Add1";
		contact1.midName = "Vendor1";
		contact1.lastName = "Test1";
		contact1.suffix = "JR";
		contact1.businessPhone = "9052868888";
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
		
		expMessage1 = "First Name is required for Contact Type \"" + vendor.contacts.get(0).contactType + "\". Please specify the First Name.";
		expMessage2 = "Last Name is required for Contact Type \"" + vendor.contacts.get(0).contactType + "\". Please specify the Last Name.";
		expMessage3 = "At least one Phone is required for Contact Type \"" + vendor.contacts.get(0).contactType + "\". Please specify the Business, Home, or Mobile Phone.";
		expMessage4 = "Contact Phone (Business, Home, or Mobile) must contain only numbers, spaces, brackets, dashes or a single 'x' (to denote extension number). Please change your entries for Contact Type \"" + vendor.contacts.get(0).contactType + "\".";
		expMessage5 = "First Name is required for Contact Type \"" + vendor.contacts.get(1).contactType + "\". Please specify the First Name.";
		expMessage6 = "Last Name is required for Contact Type \"" + vendor.contacts.get(1).contactType + "\". Please specify the Last Name.";
		expMessage7 = "At least one Phone is required for Contact Type \"" + vendor.contacts.get(1).contactType + "\". Please specify the Business, Home, or Mobile Phone.";
		expMessage8 = "Contact Fax must contain only numbers, spaces, brackets, dashes or a single 'x' (to denote extension number). Please change your entries for Contact Type \"" + vendor.contacts.get(1).contactType + "\".";
		expMessage9 = "Contact Email must be a valid email. Please change your entries for Contact Type \"" + vendor.contacts.get(1).contactType + "\"." ;
		expMessage10 = "Duplicate Contact Types have been specified. Please change your entries.";
		expMessage11 = "Vendor Name is required. Please specify the Vendor Name.";
		expMessage12 = "Owner Name is required. Please specify the Owner Name.";
		expMessage13 = "Vendor Type is required. Please specify the Vendor Type.";
	}
	
	private void initialVendorContactInfo(){
		Contacts contact2 = new Contacts();
		
		contact2.contactType = "Finance Mgr";
		contact2.isPrimary = false;
		contact2.firstName = "Edit3";
		contact2.midName = "Edit3";
		contact2.lastName = "Edit3";
		contact2.suffix = "JR";
		contact2.businessPhone = "9052868888";
		vendor.contacts.add(contact2);
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
	
	private String updateVendorContactInfo(VendorInfo updateVendor){
		LicMgrVendorDetailAddAndContractsPage addressAndContactPg = LicMgrVendorDetailAddAndContractsPage.getInstance();
		String toReturn = "";
		logger.info("Update Vendor Contact Info.");
		
		addressAndContactPg.setupVendorContactInfo(updateVendor.contacts, updateVendor.removedContacts);
		addressAndContactPg.clickSave();
		ajax.waitLoading();
		
		toReturn = addressAndContactPg.getErrorMessage();
		return toReturn;	
	}

}
