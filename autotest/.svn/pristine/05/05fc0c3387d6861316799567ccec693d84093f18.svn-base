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
 * @Description:This case is used to verify error message when edit vendor address info(Physical address, mailing address and alter address)
 * @Preconditions:
 * @SPEC: Edit Vendor.UCS
 * @Task#:AUTO-576

 * @author vzhang
 * @Date Feb 8, 2012
 */
public class VerifyMessage_Address extends LicenseManagerTestCase{
	private LicMgrVendorDetailAddAndContractsPage addressAndContactPg = LicMgrVendorDetailAddAndContractsPage.getInstance();
	private VendorInfo vendor = new VendorInfo();
	private String actualMessage = "";
	private String expMessage4,expMessage5,expMessage6,expMessage7,expMessage8,expMessage9,expMessage10,expMessage11,
	               expMessage12,expMessage13,expMessage14,expMessage15,expMessage16,expMessage17,expMessage18,expMessage19,
	               expMessage20,expMessage21,expMessage22,expMessage23,expMessage24;
	private boolean result = true;

	@Override
	public void execute() {
		//Login license manager and goto vendor search page
		lm.loginLicenseManager(login);
		lm.gotoVendorSearchPg();
		
		//add a vendor as precondition
		lm.addVendor(vendor);
		
		lm.gotoVendorDetailPgFromVendorSearchPg(vendor.number);	
		
		//physical address is not specific
		vendor.phyAddress = "";
		actualMessage = this.updateAddressInfo(vendor,0);
		this.verifyErrorMessage(actualMessage, expMessage4);
		
		//physical city town is not specific
		vendor.phyAddress = "keji 2 road";
		vendor.phyCityTown = "";
		actualMessage = this.updateAddressInfo(vendor,0);
		this.verifyErrorMessage(actualMessage, expMessage5);
		
		//physical zip is not specific
		vendor.phyCityTown = "xian";
		vendor.phyZipPostal = "";
		actualMessage = this.updateAddressInfo(vendor,0);
		this.verifyErrorMessage(actualMessage, expMessage6);
		
		//physical zip is not correct
		vendor.phyZipPostal = "8479";
		actualMessage = this.updateAddressInfo(vendor,0);
		this.verifyErrorMessage(actualMessage, expMessage7);
		
		//physical county is not specific
		vendor.phyZipPostal = "45679";
		vendor.phyCounty = "";
		actualMessage = this.updateAddressInfo(vendor,0);
		this.verifyErrorMessage(actualMessage, expMessage8);
			
		//physical state is not specific
		vendor.phyStateProvince = "";
		actualMessage = this.updateAddressInfo(vendor,0);
		this.verifyErrorMessage(actualMessage, expMessage9);
		
		//physical country is not specific
		vendor.phyCountry = "";
		actualMessage = this.updateAddressInfo(vendor,0);
		this.verifyErrorMessage(actualMessage, expMessage10);
		
		lm.gotoVendorDetailsPgFromTopMenu(vendor.number);
		//mailing address is not specific
		vendor.mailingAddress = "";
		actualMessage = this.updateAddressInfo(vendor,1);
		this.verifyErrorMessage(actualMessage, expMessage11);
		
		//mailing city town is not specific
		vendor.mailingAddress = "keji 2 road";
		vendor.mailingCityTown = "";
		actualMessage = this.updateAddressInfo(vendor,1);
		this.verifyErrorMessage(actualMessage, expMessage12);
		
		//mailing zip is not specific
		vendor.mailingCityTown = "xian";
		vendor.mailingZipPostal = "";
		actualMessage = this.updateAddressInfo(vendor,1);
		this.verifyErrorMessage(actualMessage, expMessage13);
		
		//mailing zip is not correct
		vendor.mailingZipPostal = "1234";
		actualMessage = this.updateAddressInfo(vendor,1);
		this.verifyErrorMessage(actualMessage, expMessage14);
		
		//mailing county is not specific
		vendor.mailingZipPostal = "12345";
		vendor.mailingCounty = "";
		actualMessage = this.updateAddressInfo(vendor,1);
		this.verifyErrorMessage(actualMessage, expMessage15);
		
		//mailing state is not specific
		vendor.mailingStateProvince = "";
		actualMessage = this.updateAddressInfo(vendor,1);
		this.verifyErrorMessage(actualMessage, expMessage16);		
		
		//mailing country is not specific
		vendor.mailingCountry = "";
		actualMessage = this.updateAddressInfo(vendor,1);
		this.verifyErrorMessage(actualMessage, expMessage17);
		
		lm.gotoVendorDetailsPgFromTopMenu(vendor.number);
		
		//alter country is not specific
		vendor.alterAddress = "keji 2 road";
		actualMessage = this.updateAddressInfo(vendor,2);
		this.verifyErrorMessage(actualMessage, expMessage20);
		
		//alter zip is not specific
		vendor.alterCountry = "United States";
		actualMessage = this.updateAddressInfo(vendor,2);
		this.verifyErrorMessage(actualMessage, expMessage21);
		
		//alter zip is not correct
		vendor.alterZipPostal = "1234";
		actualMessage = this.updateAddressInfo(vendor,2);
		this.verifyErrorMessage(actualMessage, expMessage22);
		
		//alter city town is not specific
		vendor.alterZipPostal = "12345";
		actualMessage = this.updateAddressInfo(vendor,2);
		this.verifyErrorMessage(actualMessage, expMessage18);
		
		//alter address is not specific
		vendor.alterAddress = "";
		vendor.alterCityTown =  "xian";
		actualMessage = this.updateAddressInfo(vendor,2);
		this.verifyErrorMessage(actualMessage, expMessage19);
		
		//alter state is not specific
		vendor.alterAddress = "keji 2 road";
		actualMessage = this.updateAddressInfo(vendor,2);
		this.verifyErrorMessage(actualMessage, expMessage23);
		
		//alter county is not specific
		vendor.alterStateProvince = "Mississippi";
		actualMessage = this.updateAddressInfo(vendor,2);
		this.verifyErrorMessage(actualMessage, expMessage24);
				
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
		vendor.contacts.add(contact);
		
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
		
		expMessage4 = "Address is required for Address Type \"Physical Address\". Please specify the Address.";
		expMessage5 = "City/Town is required for Address Type \"Physical Address\". Please specify the City/Town.";
		expMessage6 = "ZIP/Postal Code is required for Address Type \"Physical Address\". Please specify the ZIP/Postal Code.";
		expMessage7 = "ZIP/Postal Code must contain either 5 or 9 numbers, and must only contain numbers, and optionally, a single dash or a single space." +
				" Please change your entries for Address Type \"Physical Address\".";  //This is for US, Updated for DEFECT-49431
		expMessage8 = "County is required for Address Type \"Physical Address\". Please specify the County.";
		expMessage9 = "State is required for Address Type \"Physical Address\". Please specify the State.";
		expMessage10 = "Country is required for Address Type Physical Address. Please specify the Country.";
		expMessage11 = "Address is required for Address Type \"Mailing Address\". Please specify the Address." ;
		expMessage12 = "City/Town is required for Address Type \"Mailing Address\". Please specify the City/Town.";
		expMessage13 = "ZIP/Postal Code is required for Address Type \"Mailing Address\". Please specify the ZIP/Postal Code.";
		expMessage14 = "ZIP/Postal Code must contain either 5 or 9 numbers, and must only contain numbers, and optionally, a single dash or a single space." +
				" Please change your entries for Address Type \"Mailing Address\".";  //This is for US, Updated for DEFECT-49431
		expMessage15 = "County is required for Address Type \"Mailing Address\". Please specify the County.";
		expMessage16 = "State is required for Address Type \"Mailing Address\". Please specify the State.";
		expMessage17 = "Country is required for Address Type Mailing Address. Please specify the Country.";
		expMessage18 = "City/Town is required for Address Type \"Alternate Address\". Please specify the City/Town.";
		expMessage19 = "Address is required for Address Type \"Alternate Address\". Please specify the Address.";
		expMessage20 = "Country is required for Address Type Alternate Address. Please specify the Country.";
		expMessage21 = "ZIP/Postal Code is required for Address Type \"Alternate Address\". Please specify the ZIP/Postal Code.";
		expMessage22 = "ZIP/Postal Code must contain either 5 or 9 numbers, and must only contain numbers, and optionally, a single dash or a single space." +
				" Please change your entries for Address Type \"Alternate Address\".";  //This is for US, Updated for DEFECT-49431
		expMessage23 = "State is required for Address Type \"Alternate Address\". Please specify the State.";
		expMessage24 = "County is required for Address Type \"Alternate Address\". Please specify the County.";
	}
	
	private void verifyErrorMessage(String actuMessage, String expMessage){
		logger.info("Verify Error Message.");
		
		if(!expMessage.equals(actuMessage)){
			result &= false;
			logger.error("Expect error message should be:" + expMessage 
					+ ", but actually is:" + actuMessage);
		}else {
			logger.info("Error Message is correct.");
		}
	}
	
	private String updateAddressInfo(VendorInfo updateVendor, int value){
		String toReturn = "";
		logger.info("Update vendor info.");
		
		switch (value){
		case 0:
			logger.info("Update vendor physical address info.");
			addressAndContactPg.setupVendorPhyAddressInfo(updateVendor);
			break;
		case 1:
			logger.info("Update vendor mailing address info.");
			addressAndContactPg.setupVendorMailingAddressInfo(updateVendor);
			break;
		case 2:
			logger.info("Update alter address info.");
			addressAndContactPg.setupVendorAlterAddressInfo(updateVendor);
			break;
		default:
			throw new ErrorOnPageException("Please check you wanted update vendor info in which area.");
		}
		
		addressAndContactPg.clickSave();
		ajax.waitLoading();
		
		toReturn = addressAndContactPg.getErrorMessage();
		return toReturn;
	}

}
