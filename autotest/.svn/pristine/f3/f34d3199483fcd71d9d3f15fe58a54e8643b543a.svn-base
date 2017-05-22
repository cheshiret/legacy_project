package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.edu;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationInfomationRequiredPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: (DEFECT-44632) verify country and state DDL list in "Education information required" page
 * @Preconditions:
 * d_web_hf_signupaccount, id=690, hfmo_00015@webhftest.com, Edu1_FN, Edu1_LN, Green Card|1R9Y4x4145|Canada| |
 * 
 * @SPEC: Education Country and States/Province drop down list [TC:047154]
 * @Task#: AUTO-1761
 * @author SWang
 * @Date  Jun 16, 2013
 */
public class EduCountryAndStateDDL extends HFMOWebTestCase {
	private String canada, canadaDefaultState, unitedStates, unitedStatesDefaultState;
	private List<String> countries, canadaRelatedStates, unitedStateRelatedStates;
	private HFEducationInfomationRequiredPage eduInfoRequiredPg = HFEducationInfomationRequiredPage.getInstance();

	public void execute() {
		//Lookup account, make privilege to education information required page
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		hf.makePrivilegeOrder(cus, privilege, eduInfoRequiredPg);

		//Verify
		verifyEduCOuntryAndStateDDL();

		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "hfmo_00015@webhftest.com";
		cus.fName = "Edu1_FN";
		cus.lName = "Edu1_LN";
		cus.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_CONSERVATION_ID, false, false);
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);

		//Privilege parameters
		privilege.name = "HFMO License002";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.qty = "2";

		canada = "Canada";
		unitedStates = "United States";
		canadaDefaultState = "Alberta";
		unitedStatesDefaultState = "Missouri";

		countries = hf.getCountries(conmmonSchema);
		canadaRelatedStates = hf.getContractRelatedStates(conmmonSchema, canada);
		unitedStateRelatedStates = hf.getContractRelatedStates(conmmonSchema, unitedStates);
	}

	/**
	 * 1. All options in country drop down list
	 * 2. Initial, united states country selected, verify state default value and all states option
	 * 3. Select Canada to verify state default value and all states option
	 * 4. Select other country without state to verify no state DDL
	 */
	private void verifyEduCOuntryAndStateDDL(){
		boolean passed = MiscFunctions.compareResult("Options in country DDL", countries.toString(), eduInfoRequiredPg.getCountries().toString());

		passed &= MiscFunctions.compareResult("United states default state", unitedStatesDefaultState, eduInfoRequiredPg.getState());
		passed &= MiscFunctions.compareResult("United states related states", unitedStateRelatedStates.toString(), eduInfoRequiredPg.getStates().toString());

		eduInfoRequiredPg.syncStateSelectingCountry(canada, false);
		passed &= MiscFunctions.compareResult("Canada default state", canadaDefaultState, eduInfoRequiredPg.getState());
		passed &= MiscFunctions.compareResult("Canada related states", canadaRelatedStates.toString(), eduInfoRequiredPg.getStates().toString());

		eduInfoRequiredPg.syncStateSelectingCountry(countries.get(3), true); //State DDL will disappear after select state other than Canada and United states

		if(!passed){
			throw new ErrorOnPageException("Failed to check all country and state DDL related points in Education Infomation Required page.. Please find detauls from previous logs.");
		}
		logger.info("Successfully check all country and state DDL related points in Education Infomation Required page.");
	}
}
