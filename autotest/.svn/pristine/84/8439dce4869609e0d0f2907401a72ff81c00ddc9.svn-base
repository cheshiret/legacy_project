package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.instructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrInstructorListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description: In Instructor search page, check initial UI, DDL options and error message
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC: 
 * Instructor Search Criteria UI [TC:110771]
 * Initial Search [TC:110251]
 * @Task#:Auto-2047
 * 
 * @author SWang
 * @Date  Mar 16, 2014
 */
public class VerifyInitialSearchUIAndErrorMsg extends LicenseManagerTestCase {
	private LicMgrInstructorListPage instructorListPg = LicMgrInstructorListPage.getInstance();
	private String testData1, testData2, testData3, testData4, errorMsg1, errorMsg2, errorMsg3, errorMsg4, canada, unitedStates, stateUS, stateC;
	private Customer cust = new Customer();
	private List<String> countries, canadaRelatedStates, unitedStateRelatedStates, counties, instructorTypes, instructorStatus;

	public void execute() {
		lm.loginLicenseManager(login);

		//Go to instructor list page
		lm.gotoActivityPgFromHomePg();
		lm.gotoInstructorListPgFromActivityPg();

		//Check instructor search initial UI, DDL options and error messages
		instructorListPg.verifyInstructorInitialSearchUI();
		verifyDropDownListOptions();
		verifyInstructorSearchErrorMsg();

		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("MS", env);

		//Login in License Manager
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";

		errorMsg1 = "At least one of Instructor #, Last Name, Phone, Physical Address \\(i.e\\. Street Address\\), City/Town, State/Province, Zip/Postal or Country is not specified\\. Please re-enter\\.";
		errorMsg2 = "Phone Number must contain at least 5 numbers\\. Please re-enter\\.";
		errorMsg3 = "ZIP/Postal must contain at least 5 numbers and letters combined, and must only contain numbers, letters, a single dash, or a single space\\. Please re-enter\\.";	
		errorMsg4 = "No results found matching the search criteria\\. Please re-enter\\.";
		testData1 = "1234";
		testData2 = "12ab";
		testData3 = "@12abc";
		testData4 = "12abc";

		canada = "Canada";
		unitedStates = "United States";
		stateUS = "Alabama";
		stateC = "Alberta";

		countries = new ArrayList<String>();
		countries.add(StringUtil.EMPTY);
		countries.addAll(lm.getCountries(schema));

		canadaRelatedStates = new ArrayList<String>();
		canadaRelatedStates.add(StringUtil.EMPTY);
		canadaRelatedStates.addAll(lm.getContractRelatedStates(schema, canada));

		unitedStateRelatedStates = new ArrayList<String>();
		unitedStateRelatedStates.add(StringUtil.EMPTY);
		unitedStateRelatedStates.addAll(lm.getContractRelatedStates(schema, unitedStates));

		counties = new ArrayList<String>();
		counties.add(StringUtil.EMPTY);
		counties.addAll(lm.getCountyBasedOnState(schema, stateUS));

		instructorTypes = new ArrayList<String>();
		instructorTypes.add(StringUtil.EMPTY);
		instructorTypes.addAll(lm.getInstructorTypes(schema));
		instructorStatus = Arrays.asList(StringUtil.EMPTY, OrmsConstants.ACTIVE_STATUS, OrmsConstants.INACTIVE_STATUS);
	}

	private void verifyInstructorSearchErrorMsg(){
		instructorListPg.searchInstructor(cust);
		boolean result = MiscFunctions.compareResult("Initial search UI", true, instructorListPg.isMsgExisted(errorMsg1));

		cust.hPhone = testData1;
		instructorListPg.searchInstructor(cust);
		result &= MiscFunctions.compareResult("Phone number contails less than 5 numbers", true, instructorListPg.isMsgExisted(errorMsg2));

		cust.hPhone = StringUtil.EMPTY;
		cust.physicalAddr.zip = testData2;
		instructorListPg.searchInstructor(cust);
		result &= MiscFunctions.compareResult("ZIP contains less than 5 numbers and letters combined", true, instructorListPg.isMsgExisted(errorMsg3));

		cust.physicalAddr.zip = testData3;
		instructorListPg.searchInstructor(cust);
		result &= MiscFunctions.compareResult("ZIP must contains characters other than numbers, letters, single dash, or single space", true, instructorListPg.isMsgExisted(errorMsg3));

		cust.physicalAddr.zip = testData4;
		instructorListPg.searchInstructor(cust);
		result &= MiscFunctions.compareResult("No result is found", true, instructorListPg.isMsgExisted(errorMsg4));

		if(!result){
			throw new ErrorOnPageException("Not all error messages in Instructor search page are correct. Please check the details from previous logs.");
		}else logger.info("Successfully verify all error message in Instructor search page.");
	}

	private void verifyDropDownListOptions(){
		boolean result = MiscFunctions.compareResult("Instructor Status DDL", instructorStatus.toString(), instructorListPg.getInstructorStatuses().toString());
		result &= MiscFunctions.compareResult("Instructor Type DDL ", instructorTypes.toString(), instructorListPg.getInstructorTypes().toString());
		result &= MiscFunctions.compareResult("Country DDL", countries.toString(), instructorListPg.getCountries().toString()); //DEFECT-61779

		logger.info("-----Countey is Unitied States");
		instructorListPg.selectCountry(unitedStates);
		ajax.waitLoading();
		result &= MiscFunctions.compareResult("State", unitedStateRelatedStates.toString(),  instructorListPg.getStates().toString());
		result &= MiscFunctions.compareResult("County", Arrays.asList(StringUtil.EMPTY).toString(),  instructorListPg.getCounties().toString());

		instructorListPg.selectState(stateUS);
		ajax.waitLoading();
		result &= MiscFunctions.compareResult("County", counties.toString(),  instructorListPg.getCounties().toString());

		logger.info("-----Countey is Canada");
		instructorListPg.selectCountry(canada);
		ajax.waitLoading();
		result &= MiscFunctions.compareResult("State", canadaRelatedStates.toString(),  instructorListPg.getStates().toString());

		instructorListPg.selectState(stateC);
		ajax.waitLoading();
		result &= MiscFunctions.compareResult("County", Arrays.asList(StringUtil.EMPTY).toString(),  instructorListPg.getCounties().toString());

		if(!result){
			throw new ErrorOnPageException("Failed to verify all drop down list in Instructor list page. Please check the details from previous logs.");
		}else logger.info("Successfully verify all drop down list in instructor list page.");
	}
}

