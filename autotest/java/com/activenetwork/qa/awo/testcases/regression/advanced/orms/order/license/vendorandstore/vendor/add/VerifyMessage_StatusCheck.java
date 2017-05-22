package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.vendorandstore.vendor.add;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ApplicationStatusCheck;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorAddVendorPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorSearchListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case is used to verify error message when add vendor status check info
 * @Preconditions:
 * @SPEC:Add Vendor.UCS
 * @Task#:AUTO-518

 * @author VZhang
 * @Date Feb 8, 2012
 */
public class VerifyMessage_StatusCheck  extends LicenseManagerTestCase{
	private VendorInfo vendor = new VendorInfo();
	private LicMgrVendorAddVendorPage addVendorPg = LicMgrVendorAddVendorPage.getInstance();
	private LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage.getInstance();
	private String actualMessage = "";
	private String expMessage1,expMessage2,expMessage3,expMessage4;
	private boolean result = true;

	@Override
	public void execute() {
		//Login license manager and goto vendor search page
		lm.loginLicenseManager(login);
		lm.gotoVendorSearchPg();
		
		this.gotoAddVendorPg();
		this.AddVendorBasicAndStatusCheckInfo(vendor, 0);
		
		ApplicationStatusCheck appStatusCheck = new ApplicationStatusCheck();
		appStatusCheck.byPassChecked = false;
		appStatusCheck.statusCheck = "Credit Check Run";
		appStatusCheck.dateCompleted = DateFunctions.getDateAfterToday(1, DataBaseFunctions.getContractTimeZone(DataBaseFunctions.getSchemaName("MS", env)));
		vendor.applicationStatusCheck.add(appStatusCheck);
		expMessage1 = "Date Completed must be today or in the past. Please re-enter the date for \"" + appStatusCheck.statusCheck+ "\".";
		actualMessage = this.AddVendorBasicAndStatusCheckInfo(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage1);
		
		appStatusCheck.dateCompleted = "";
		appStatusCheck.completedBy = "Auto";
		expMessage2 = "Both Date Completed and Completed By are required for \"" + appStatusCheck.statusCheck+ "\". Please re-enter.";
		actualMessage = this.AddVendorBasicAndStatusCheckInfo(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage2);
		
		appStatusCheck.byPassChecked = true;
		appStatusCheck.statusCheck = "Credit Check Run";
		appStatusCheck.dateCompleted = "";
		appStatusCheck.completedBy = "";
		appStatusCheck.comments = "";
		ApplicationStatusCheck appStatusCheck1 = new ApplicationStatusCheck();
		appStatusCheck1.byPassChecked = false;
		appStatusCheck1.statusCheck = "Owner Suspensions Check";
		appStatusCheck1.dateCompleted = DateFunctions.getDateAfterToday(0, DataBaseFunctions.getContractTimeZone(DataBaseFunctions.getSchemaName("MS", env)));
		vendor.applicationStatusCheck.add(appStatusCheck1);
		expMessage3 = "Both Date Completed and Completed By are required for \"" + appStatusCheck1.statusCheck+ "\". Please re-enter.";
		actualMessage = this.AddVendorBasicAndStatusCheckInfo(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage3);
		
		appStatusCheck1.byPassChecked = true;
		appStatusCheck1.completedBy = "Auto";
		expMessage4 = "Bypass Check, Date Completed, and Completed By have been specified for \"" + appStatusCheck1.statusCheck+ "\". Please deselect the Bypass Check indicator, or remove your Date and Completed By entries.";
		actualMessage = this.AddVendorBasicAndStatusCheckInfo(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage4);
		
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
		
		vendor.specifyDefault = "Weekly EFT";
		vendor.isFillValues = true;		
		
		vendor.applicationStatusCheck = new ArrayList<ApplicationStatusCheck>();		
	}
	
	private void gotoAddVendorPg(){
		logger.info("Go to Add Vendor Page.");
		vendorSearchPg.clickAddVendor();
		ajax.waitLoading();
		addVendorPg.waitLoading();
	}
	
	private String AddVendorBasicAndStatusCheckInfo(VendorInfo addVendor, int value){
		String toReturn = "";
		logger.info("Add vendor info.");
		
		switch (value){
		case 0:
			logger.info("Add vendor basic, physical address info and mailing address info as same as physical address info.");
			addVendorPg.setupVendorAppInfo(addVendor);
			addVendorPg.setupVendorBasicInfo(addVendor);
			addVendorPg.setupVendorPhyAddressInfo(addVendor);	
			addVendorPg.setupVendorContactInfo(addVendor.contacts, addVendor.removedContacts);
			addVendorPg.setupVendorEFTInfo(addVendor);
			break;
		case 1:
			logger.info("Add vendor application status check info.");
			addVendorPg.setupVendorAppStatusCheckInfo(addVendor.applicationStatusCheck);
			addVendorPg.clickOK();
			ajax.waitLoading();
			addVendorPg.waitLoading();
			toReturn = addVendorPg.getErrorMsg();
			break;
		default:
			throw new ErrorOnPageException("Please check you want added vendor info in which area.");
		}

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
