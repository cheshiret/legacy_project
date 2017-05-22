package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.property;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.OwnerAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.PropertyAttr;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrAddPropertyPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrOwnerDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrPropertyListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description: (P) Verify error messages during add property and add owner
 * @Preconditions: SetupTypeOfOwnership.sql
 * @LinkSetUp:
 * d_assign_feature:id=5022 --MO Admin - Auto, %Property%
 * d_hf_add_cust_profile:id=2610 --QA-Advanced05, TEST-Advaced05 
 * @SPEC: 
 * Assign Property to Customer Profile [TC:070792] 
 * Add Customer Profile Property [TC:067766]
 * Add Customer Profile Property-OK/Apply/Cancel button [TC:070099]
 * Add Customer Profile Property [TC:067766]
 * @Task#:AUTO-2043, AUTO-2042
 * 
 * @author SWang
 * @Date  Jan 14, 2014
 */
public class AddPropertyWithErrorMsg extends LicenseManagerTestCase {
	private Data<PropertyAttr> cpa, cpa2, cpa3;
	private String msg1, msg2, msg3, msg4, msg5, msg6, msg7, msg8, msg9, msg10, msg11, msg12, msg13, invilateData1, invilateData3, invilateData2;
	private LicMgrPropertyListPage propertyListPg = LicMgrPropertyListPage.getInstance();
	private List<String> counties = new ArrayList<String>();
	private Data<OwnerAttr> owner;

	public void execute() {
		// Go to Property list page
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromTopMenu(cust);
		lm.gotoCustPropertyPgFromCustDetailsPg();

		//Add two properties
		cpa2.put(PropertyAttr.propertyID, lm.addCustProperty(cpa2));
		cpa3.put(PropertyAttr.propertyID, lm.addCustProperty(cpa3));

		try{
			//Cancel add property
			lm.addCustProperty(cpa, true);
			propertyListPg.verifyPropertyExisted(cpa, false);

			//During add new property
			//1# error message 
			lm.gotoAddPropertyPgFromPropertyListPg();
			verifyErrorMsgInAddPropertyPage();
			//2# default UI
			verifyAddPropertyUI();
			lm.gotoPropertyListPgFromAddPropertyPg();

			//Verify error message during add new owners
			lm.gotoCustPropertyDetailsPg(cpa2.stringValue(PropertyAttr.propertyID));
			lm.gotoOwnerDetailsPgFromOwnerListPg();
			verifyErrorMsgInAddOwnerPage();
			lm.gotoOwnerListFromOwnerDetailsPg();

			// Logout
			lm.logOutLicenseManager();

		}finally{
			lm.deleteCustPropertyFromDB(schema, cpa2.stringValue(PropertyAttr.propertyID));
			lm.deleteCustPropertyFromDB(schema, cpa3.stringValue(PropertyAttr.propertyID));
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login in License Manager
		schema = DataBaseFunctions.getSchemaName("MO", env);
		login.contract = "MO Contract";
		login.location = "MO Admin - Auto/MO Department of Conservation";

		// customer parameters
		cust.customerClass = "Individual";
		cust.fName = "QA-Advanced05";
		cust.lName = "TEST-Advaced05";
		cust.dateOfBirth = "Jan 13 1989";
		cust.licenseType = "Conservation #";
		cust.licenseNum = lm.getCustomerNum(cust, schema);
		cust.status = "Active";

		// Customer Property parameters
		cpa = new Data<PropertyAttr>();
		cpa.put(PropertyAttr.ownershipStatus, "Active");
		cpa.put(PropertyAttr.propertyCounty, "Barry");
		cpa.put(PropertyAttr.propertyAcres, "123");
		cpa.put(PropertyAttr.address, "2480 meadowvale address 1");
		cpa.put(PropertyAttr.addressZip, "25149");
		cpa.put(PropertyAttr.addressCountry, "United States");
		cpa.put(PropertyAttr.supplementalAddress,"2480 meadowvale supplemental address 1");
		cpa.put(PropertyAttr.addressCity, "Mississauga");
		cpa.put(PropertyAttr.addressState, "Missouri");
		cpa.put(PropertyAttr.addressCounty, "Barry");
		cpa.put(PropertyAttr.typeOfOwnership, "Private TFO1");
		cpa.put(PropertyAttr.yearOwned, String.valueOf(DateFunctions.getYearAfterCurrentYear(-1)));

		counties.add(StringUtil.EMPTY);
		counties.addAll(lm.getCountyBasedOnState(schema, cpa.stringValue(PropertyAttr.addressState)));

		cpa2 = new Data<PropertyAttr>();
		cpa2.put(PropertyAttr.propertyCounty, "Bates");
		cpa2.put(PropertyAttr.propertyAcres, "234");
		cpa2.put(PropertyAttr.typeOfOwnership, "Private TFO1");
		cpa2.put(PropertyAttr.yearOwned, cpa.stringValue(PropertyAttr.yearOwned));

		owner = new Data<OwnerAttr>();
		owner.put(OwnerAttr.typeOfOwnership, cpa2.stringValue(PropertyAttr.typeOfOwnership));
		owner.put(OwnerAttr.yearOwned, cpa2.stringValue(PropertyAttr.yearOwned));
		
		cpa3 = new Data<PropertyAttr>();
		cpa3.put(PropertyAttr.propertyCounty, "Boone");
		cpa3.put(PropertyAttr.propertyAcres, "345");
		cpa3.put(PropertyAttr.typeOfOwnership, "Private TFO1");
		cpa3.put(PropertyAttr.yearOwned, StringUtil.EMPTY);

		invilateData1 = "@12345";
		invilateData2 = "0";
		invilateData3 = String.valueOf(DateFunctions.getYearAfterCurrentYear(5));
		msg1 = "County is required for the Property Details. Please specify the County.";
		msg2 = "Acres is required for the Property Details. Please specify the Acres.";
		msg3 = "Acres is not a valid decimal number format.";
		msg4 = "Acres contains a number less than 1. Please re-enter.";
		msg5 = "Address is required for Address Type \"Physical Address\". Please specify the Address.";
		msg6 = "ZIP/Postal Code is required for Address Type \"Physical Address\". Please specify the ZIP/Postal Code.";
		msg7 = "ZIP/Postal Code must contain either 5 or 9 numbers, and must only contain numbers, and optionally, a single dash or a single space. Please change your entries.";
		msg8 = "City/Town is required for Address Type \"Physical Address\". Please specify the City/Town.";
		msg9 = "County is required. Please specify the County.";
		msg10 = "Type of Ownership is required. Please specify the Type of Ownership.";
		msg11 = "Year Owned is not a valid number format.";
		msg12 = "Year Owned is not a valid four-digit current or past year. Please re-enter Year Owned.";
		msg13 = "There is already an existing Property for the specified Customer Profile, with the same Type of Ownership, and the same Year Owned.";
	}

	private void verifyAddPropertyUI(){
		LicMgrAddPropertyPage addPropertyPg = LicMgrAddPropertyPage.getInstance();
		boolean result = MiscFunctions.compareResult("Property ID", "NEW", addPropertyPg.getPropertyID());
		result &= MiscFunctions.compareResult("Property County", counties.toString(), addPropertyPg.getPropertyCounties().toString());
		result &= MiscFunctions.compareResult("Address Country", cpa.stringValue(PropertyAttr.addressCountry), addPropertyPg.getAddressCountry());
		result &= MiscFunctions.compareResult("Address Status", StringUtil.EMPTY, addPropertyPg.getAddressValidateStatus());
		result &= MiscFunctions.compareResult("Address State", cpa.stringValue(PropertyAttr.addressState), addPropertyPg.getAddressState());
		result &= MiscFunctions.compareResult("Address County", counties.toString(), addPropertyPg.getAddressCounties().toString());
		result &= MiscFunctions.compareResult("Conservation #", cust.licenseNum, addPropertyPg.getConservationNum());
		result &= MiscFunctions.compareResult("Customer Status", cust.status, addPropertyPg.getCustStatus());
		result &= MiscFunctions.compareResult("Customer Class", cust.customerClass, addPropertyPg.getCustomerClass());
		result &= MiscFunctions.compareResult("Customer First Name", cust.fName, addPropertyPg.getFirstName());
		result &= MiscFunctions.compareResult("Customer Middle Name", cust.mName, addPropertyPg.getMiddleName());
		result &= MiscFunctions.compareResult("Customer Last Name", cust.lName, addPropertyPg.getLastName());
		result &= MiscFunctions.compareResult("Customer Suffix", cust.suffix, addPropertyPg.getSuffix());
		result &= MiscFunctions.compareResult("Customer DOB", DateFunctions.formatDate(cust.dateOfBirth, "E MMM dd yyyy"), addPropertyPg.getDateOfBirth());
		if(!result){
			throw new ErrorOnPageException("Failed to verify add property page UI.");
		}
		logger.info("Successfully verify add property page UI.");
	}

	private boolean checkErrorMesInAddPropertyPg(String attriName, String errorMsg, boolean clickValidate){
		LicMgrAddPropertyPage addPropertyPg = LicMgrAddPropertyPage.getInstance();
		String errorMesFromUI = StringUtil.EMPTY;
		if(clickValidate){
			addPropertyPg.clickValidate();
		}else addPropertyPg.clickOK();
		ajax.waitLoading();
		errorMesFromUI = addPropertyPg.getErrorMsg();
		return MiscFunctions.compareResult("ErrorMsg in add property page about " + attriName, errorMsg, errorMesFromUI);
	}

	private boolean checkErrorMesInOwnerDetailsPg(String attriName, String errorMsg){
		LicMgrOwnerDetailsWidget ownerDetailsPg = LicMgrOwnerDetailsWidget.getInstance();
		ownerDetailsPg.clickOK();
		ajax.waitLoading();
		String errorMesFromUI = ownerDetailsPg.getErrorMsg();
		return MiscFunctions.compareResult("ErrorMsg in owner details page about " + attriName, errorMsg, errorMesFromUI);
	}

	private void verifyErrorMsgInAddPropertyPage() {
		LicMgrAddPropertyPage addPropertyPg = LicMgrAddPropertyPage.getInstance();

		//Property County 
		boolean result = checkErrorMesInAddPropertyPg("Property county has not been specified", msg1, false);

		//Property Acres
		addPropertyPg.selectPropertyCounty(cpa.stringValue(PropertyAttr.propertyCounty));
		result &= checkErrorMesInAddPropertyPg("Property acres has not been specified", msg2, false);
		addPropertyPg.setPropertyAcres(invilateData1);
		result &= checkErrorMesInAddPropertyPg("Property Acres contains characters other than whole numbers", msg3, false);
		addPropertyPg.setPropertyAcres(invilateData2);
		result &= checkErrorMesInAddPropertyPg("Property Acres contains number less than 1", msg4, false);

		//Address 
		addPropertyPg.setPropertyAcres(cpa.stringValue(PropertyAttr.propertyAcres));
		addPropertyPg.setAddress(StringUtil.EMPTY);
		result &= checkErrorMesInAddPropertyPg("Address has not been specified with Validate", msg5, true);

		//Address zip
		addPropertyPg.setAddress(cpa.stringValue(PropertyAttr.address));
		result &= checkErrorMesInAddPropertyPg("Address zip has not been specified", msg6, true);
		addPropertyPg.setAddressZip(invilateData1);
		result &= checkErrorMesInAddPropertyPg("Address zip contains invalid character", msg7, true);

		//City
		addPropertyPg.setAddressZip(cpa.stringValue(PropertyAttr.addressZip));
		result &= checkErrorMesInAddPropertyPg("Address city has not been specified", msg8, false);

		//County
		addPropertyPg.setAddressCity(cpa.stringValue(PropertyAttr.addressCity));
		result &= checkErrorMesInAddPropertyPg("Address county has not been specified", msg9, false);

		//Type of ownership
		addPropertyPg.selectAddressCounty(cpa.stringValue(PropertyAttr.addressCounty));
		ajax.waitLoading();
		result &= checkErrorMesInAddPropertyPg("Type of ownership has not been specified", msg10, false);

		//Year Owned
		addPropertyPg.selectTypeOfOwnership(cpa.stringValue(PropertyAttr.typeOfOwnership));
		addPropertyPg.setYearOwned(invilateData1);
		result &= checkErrorMesInAddPropertyPg("Year Owned has been specified, and is not a valid four digit", msg11, false);
		addPropertyPg.setYearOwned(invilateData3);
		result &= checkErrorMesInAddPropertyPg("Year Owned has been specified, and is four digit future year", msg12, false);

		//Have existing property
		addPropertyPg.selectTypeOfOwnership(cpa2.stringValue(PropertyAttr.typeOfOwnership));
		addPropertyPg.setYearOwned(cpa2.stringValue(PropertyAttr.yearOwned));
		result &= checkErrorMesInAddPropertyPg("Have existing Customer Property with the same Type of Ownership and the same Year Owned", msg13, false);

		addPropertyPg.selectTypeOfOwnership(cpa3.stringValue(PropertyAttr.typeOfOwnership));
		addPropertyPg.setYearOwned(cpa3.stringValue(PropertyAttr.yearOwned));
		result &= checkErrorMesInAddPropertyPg("Have existing Customer Property with the same Type of Ownership and empty Year Owned", msg13, false);

		if (!result) {
			throw new ErrorOnPageException("Not all error messages are passed in Add Property page. Please check the details from previous logs.");
		}
		logger.info("All error messages are passed in Add Property page.");
	}

	private void verifyErrorMsgInAddOwnerPage(){
		LicMgrOwnerDetailsWidget ownerDetailsPg = LicMgrOwnerDetailsWidget.getInstance();

		ownerDetailsPg.findOwner(cust);
		ownerDetailsPg.selectOwner(cust);
		ownerDetailsPg.setOwnerDetails(owner);
		boolean result = checkErrorMesInOwnerDetailsPg("Have existing owner with same Type of Ownership and the same Year Owned", msg13);
		if (!result) {
			throw new ErrorOnPageException("Not all error messages are passed in Owner details page. Please check the details from previous logs.");
		}
		logger.info("All error messages are passed in Owner details page.");
	}
}
