package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.vendorandstore.vendor.add;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorAddVendorPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorSearchListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case is used to verify error message when add vendor address info(physical address and mailing address)
 * @Preconditions:
 * @SPEC:Add Vendor.UCS
 * @Task#:AUTO-518
 * [Phoebe] Update case to add error message check of zip when select country as "Canada" or "Australia",
 * need to script in Canada 1 and Australia 14 into table, please referred to "http://wiki.reserveamerica.com/display/dev/Vendor+and+Store+Setup"
 * insert into d_entity_country(id, entity_type_id, country_id) values (get_sequence('d_entity_country'), 1, 1);
 * insert into d_entity_country(id, entity_type_id, country_id) values (get_sequence('d_entity_country'), 1, 14);
 * commit;
 * @author XPMUser
 * @Date Feb 8, 2012
 */
public class VerifyMessage_Address extends LicenseManagerTestCase{
	private VendorInfo vendor = new VendorInfo();
	private LicMgrVendorAddVendorPage addVendorPg = LicMgrVendorAddVendorPage.getInstance();
	private LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage.getInstance();
	private String actualMessage = "";
	private String expMessage1,expMessage2,expMessage3,expMessage4,expMessage5,expMessage6,expMessage7,
	               expMessage8, expMessage9,expMessage10,expMessage11,expMessage12,expMessage13,expMessage14,expMessage15
	               ,expMessage16,expMessage17;
	private boolean result = true;

	@Override
	public void execute() {
		//Login license manager and goto vendor search page
		lm.loginLicenseManager(login);
		lm.gotoVendorSearchPg();
		
		this.gotoAddVendorPg();
		//Physical address is not specific
		actualMessage = this.AddVendorAddressAndContactInfo(vendor, 0);
		this.verifyErrorMessage(actualMessage, expMessage1);
		
		//physical country is not specific
		vendor.phyAddress = "address";
		vendor.phyZipPostal = "9306";
		actualMessage = this.AddVendorAddressAndContactInfo(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage3);
		
		//physical zip is not specific
		vendor.phyCountry = "United States";
		vendor.phyZipPostal = "";
		actualMessage = this.AddVendorAddressAndContactInfo(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage4);
		
		//physical zip is not correct
		vendor.phyZipPostal = "9306";//block by DEFECT-33123 
		actualMessage = this.AddVendorAddressAndContactInfo(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage5);
		
		vendor.phyCountry = "Australia";  //Added by Phoebe for auto-script review, country must be "Australia"
		//physical zip is not correct
		vendor.phyZipPostal = "&";
		actualMessage = this.AddVendorAddressAndContactInfo(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage16);
		
		vendor.phyCountry = "Canada";  //Added by Phoebe for auto-script review, country must be "Canada"
		//physical zip is not correct
		vendor.phyZipPostal = "&";
		actualMessage = this.AddVendorAddressAndContactInfo(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage17);
				
				
		//physical city town is not specific
		vendor.phyCountry = "United States";
		vendor.phyZipPostal = "93063";
		actualMessage = this.AddVendorAddressAndContactInfo(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage2);
		
		//physical state is not specific
		vendor.phyCityTown = "address";
		actualMessage = this.AddVendorAddressAndContactInfo(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage6);
		
		//physical county is not specific
		vendor.phyStateProvince = "California";
		actualMessage = this.AddVendorAddressAndContactInfo(vendor, 1);
		this.verifyErrorMessage(actualMessage, expMessage7);
		
		vendor.phyCounty = "Ventura";		
		this.AddVendorAddressAndContactInfo(vendor, 1);
		
		//mailing address is not specific
		vendor.isMailingAddSameAsPhysicalAdd = false;
		actualMessage = this.AddVendorAddressAndContactInfo(vendor, 2);
		this.verifyErrorMessage(actualMessage, expMessage8);
		
		vendor.mailingCityTown = "mailingTown";
		actualMessage = this.AddVendorAddressAndContactInfo(vendor, 2);
		this.verifyErrorMessage(actualMessage, expMessage9);
		
		//mailing country is not specific
		vendor.mailingAddress = "mailingAdd";
		vendor.mailingZipPostal = "1234";
		vendor.mailingCityTown = "";
		actualMessage = this.AddVendorAddressAndContactInfo(vendor, 2);
		this.verifyErrorMessage(actualMessage, expMessage11);
		
		//mailing zip is not specific
		vendor.mailingCountry = "United States";
		vendor.mailingZipPostal = "";
		actualMessage = this.AddVendorAddressAndContactInfo(vendor, 2);
		this.verifyErrorMessage(actualMessage, expMessage12);
		
		//mailing zip is not correct
		vendor.mailingZipPostal = "1234";
		actualMessage = this.AddVendorAddressAndContactInfo(vendor, 2);
		this.verifyErrorMessage(actualMessage, expMessage13);
		
		//mailing city town is not specific
		vendor.mailingZipPostal = "12345";
		actualMessage = this.AddVendorAddressAndContactInfo(vendor, 2);
		this.verifyErrorMessage(actualMessage, expMessage10);
		
		//mailing state is not specific
		vendor.mailingCityTown = "mailingTown";
		actualMessage = this.AddVendorAddressAndContactInfo(vendor, 2);
		this.verifyErrorMessage(actualMessage, expMessage14);
		
		//mailing county is not specific
		vendor.mailingStateProvince = "Mississippi";
		actualMessage = this.AddVendorAddressAndContactInfo(vendor, 2);
		this.verifyErrorMessage(actualMessage, expMessage15);
		
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
		
		expMessage1 = "Address is required for Address Type \"Physical Address\". Please specify the Address.";
		expMessage2 = "City/Town is required for Address Type \"Physical Address\". Please specify the City/Town.";
		expMessage3 = "Country is required for Address Type Physical Address. Please specify the Country.";
		expMessage4 = "ZIP/Postal Code is required for Address Type \"Physical Address\". Please specify the ZIP/Postal Code.";
//		expMessage5 = "ZIP/Postal Code must contain at least 5 numbers and letters combined, and must only contain numbers, letters, a single dash, or a single space. Please change your entries for Address Type \"Physical Address\".";
		expMessage5 = "ZIP/Postal Code must contain either 5 or 9 numbers, and must only contain numbers, and optionally, a single dash or a single space." +
				" Please change your entries for Address Type \"Physical Address\".";  //This is for US, Updated for DEFECT-49431
		expMessage6 = "State is required for Address Type \"Physical Address\". Please specify the State.";
		expMessage7 = "County is required for Address Type \"Physical Address\". Please specify the County.";

		expMessage8 = "Address Type \"Mailing Address\" is required. Please specify the Address.";
		expMessage9 = "Address is required for Address Type \"Mailing Address\". Please specify the Address." ;
		expMessage10 = "City/Town is required for Address Type \"Mailing Address\". Please specify the City/Town.";
		expMessage11 = "Country is required for Address Type Mailing Address. Please specify the Country.";
		expMessage12 = "ZIP/Postal Code is required for Address Type \"Mailing Address\". Please specify the ZIP/Postal Code.";
//		expMessage13 = "ZIP/Postal Code must contain at least 5 numbers and letters combined, and must only contain numbers, letters, a single dash, or a single space. Please change your entries for Address Type \"Mailing Address\".";
		expMessage13 = "ZIP/Postal Code must contain either 5 or 9 numbers, and must only contain numbers, and optionally, a single dash or a single space." +
				" Please change your entries for Address Type \"Mailing Address\".";  //This is for US, Updated for DEFECT-49431
		expMessage14 = "State is required for Address Type \"Mailing Address\". Please specify the State.";
		expMessage15 = "County is required for Address Type \"Mailing Address\". Please specify the County.";
		
		//Added just for error of zip when country is "Australia"
		expMessage16 = "ZIP/Postal Code must contain at least 1 number or letter, and must only contain numbers, letters, a single dash, or a single space. Please change your entries for Address Type \"Physical Address\".";
		//Added just for error of zip when country is "Canada"
		expMessage17 = "Postal Code must contain exactly 6 numbers and letters combined, and contain only the following characters: number, letter, single dash, single space in one of the following formats: A#A #A# or A#A#A#, " +
				"or A#A-#A# where A is any alphabetic character and # is a numeric digit from 0 to 9.Please change your entries for Address Type Physical Address.";
	}
	
	private void gotoAddVendorPg(){
		logger.info("Go to Add Vendor Page.");
		vendorSearchPg.clickAddVendor();
		ajax.waitLoading();
		addVendorPg.waitLoading();
	}
	
	private String AddVendorAddressAndContactInfo(VendorInfo addVendor, int value){
		String toReturn = "";
		logger.info("Add vendor info.");
		
		switch (value){
		case 0:
			logger.info("Add vendor basic info.");
			addVendorPg.setupVendorAppInfo(addVendor);
			addVendorPg.setupVendorBasicInfo(addVendor);
			break;
		case 1:
			logger.info("Add vendor physical address info.");
			addVendorPg.setupVendorPhyAddressInfo(addVendor);
			break;			
		case 2:
			logger.info("Add vendor mailing address info.");
			addVendorPg.setupVendorMailingAddressInfo(addVendor);
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
