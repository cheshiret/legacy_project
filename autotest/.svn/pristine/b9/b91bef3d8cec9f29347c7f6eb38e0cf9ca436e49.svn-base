package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.property;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust.PropertyAttr;
import com.activenetwork.qa.awo.pages.orms.licenseManager.cust.property.LicMgrPropertyDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description: (P) Verify error messages during edit customer property
 * @Preconditions: SetupTypeOfOwnership.sql
 * @LinkSetUp: d_assign_feature:id=5022 --MO Admin - Auto, %Property%
 *             d_hf_add_cust_profile:id=2620 --QA-Advanced06, TEST-Advaced06
 * @SPEC: Edit Customer Profile Property [TC:070100]
 * @Task#:AUTO-2043
 * 
 * @author SWang
 * @Date Jan 14, 2014
 */
public class EditPropertyWithErrorMsg extends LicenseManagerTestCase {
	private Data<PropertyAttr> cpa;
	private String msg1, msg2, msg3, msg4, msg5, msg6, msg7, msg8, testData1, testData2, testData3, address;

	public void execute() {
		// Go to customer details page to add Property
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromTopMenu(cust);
		cpa.put(PropertyAttr.propertyID, lm.addCustProperty(cpa));

		try{
			//Go to property details page
			lm.gotoCustPropertyDetailsPg(cpa.stringValue(PropertyAttr.propertyID));

			//Check error message
			verifyErrorMsg();

			//Logout
			lm.logOutLicenseManager();

		}finally{
			lm.deleteCustPropertyFromDB(schema, cpa.stringValue(PropertyAttr.propertyID));
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
		cust.fName = "QA-Advanced06";
		cust.lName = "TEST-Advaced06";
		cust.dateOfBirth = "Jan 13 1989";
		cust.licenseType = "Conservation #";
		cust.licenseNum = lm.getCustomerNum(cust, schema);

		// Customer Property parameters
		cpa = new Data<PropertyAttr>();
		cpa.put(PropertyAttr.propertyCounty, "Barry");
		cpa.put(PropertyAttr.propertyAcres, "123");
		cpa.put(PropertyAttr.typeOfOwnership, "Private TFO1");
		cpa.put(PropertyAttr.yearOwned, String.valueOf(DateFunctions.getYearAfterCurrentYear(-1)));
		address = "Address for EditPropertyWithErrorMsg";

		testData1 = "@12345";
		testData2 = "0";
		testData3 = "00011";
		msg1 = "County is required for the Property Details. Please specify the County.";
		msg2 = "Acres is required for the Property Details. Please specify the Acres.";
		msg3 = "Acres is not a valid decimal number format.";
		msg4 = "Acres contains a number less than 1. Please re-enter.";
		msg5 = "Address is required for Address Type \"Physical Address\". Please specify the Address.";
		msg6 = "ZIP/Postal Code is required for Address Type \"Physical Address\". Please specify the ZIP/Postal Code.";
		msg7 = "ZIP/Postal Code must contain either 5 or 9 numbers, and must only contain numbers, and optionally, a single dash or a single space. Please change your entries.";
	    msg8 = "ZIP/Postal Code 00011 could not be found for Address Type \"Physical Address\". Please change your entries.";
	}

	private boolean checkErrorMsg(String attriName,String expectedErrorMes, boolean clickValidate) {
		LicMgrPropertyDetailsPage propertyDetailsPg = LicMgrPropertyDetailsPage.getInstance();
		if(clickValidate){
			propertyDetailsPg.clickValidate();
		}else propertyDetailsPg.clickOK();

		ajax.waitLoading();
		String errorMesFromUI = propertyDetailsPg.getErrorMsg();
		return MiscFunctions.compareResult("ErrorMsg about " + attriName, expectedErrorMes, errorMesFromUI);
	}
	private boolean checkErrorMsg(String attriName,String expectedErrorMes) {
		return checkErrorMsg(attriName, expectedErrorMes, false);
	}

	private void verifyErrorMsg() {
		LicMgrPropertyDetailsPage propertyDetailsPg = LicMgrPropertyDetailsPage.getInstance();

		// Property county
		propertyDetailsPg.selectPropertyCounty(0);
		boolean result = checkErrorMsg("Property County has not been specified", msg1);

		//Property Acres
		propertyDetailsPg.selectPropertyCounty(cpa.stringValue(PropertyAttr.propertyCounty));
		propertyDetailsPg.setPropertyAcres(StringUtil.EMPTY);
		result &= checkErrorMsg("Property Acres has not been specified", msg2);
		propertyDetailsPg.setPropertyAcres(testData1);
		result &= checkErrorMsg("Property Acres contains characters other than whole numbers", msg3);
		propertyDetailsPg.setPropertyAcres(testData2);
		result &= checkErrorMsg("Property Acres contains number less than 1", msg4);

		//Address 
		propertyDetailsPg.setPropertyAcres(cpa.stringValue(PropertyAttr.propertyAcres));
		propertyDetailsPg.setAddress(StringUtil.EMPTY);
		result &= checkErrorMsg("Address has not been specified with Validate", msg5, true);

		//Address zip
		propertyDetailsPg.setAddress(address);
		result &= checkErrorMsg("Address zip has not been specified", msg6, true);
		propertyDetailsPg.setAddressZip(testData1);
		result &= checkErrorMsg("Address zip contains invalid character", msg7, true);
		propertyDetailsPg.setAddressZip(testData3);
		result &= checkErrorMsg("Address zip could not be found", msg8, true);
		
		if (!result) {
			throw new ErrorOnPageException("Not all error message are passed in Customer Property Details page. Please check the details from previous logs.");
		}
		logger.info("All error message are passed in Customer Property Details page.");
	}
}
