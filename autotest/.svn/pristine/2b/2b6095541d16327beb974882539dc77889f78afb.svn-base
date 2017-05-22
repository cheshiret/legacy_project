package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.lookupaccount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountLookupPage;
import com.activenetwork.qa.awo.pages.web.hf.HFAddIdentificationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: (P) Identifier type has country and state check in Lookup account page
 * @Preconditions:
 * Customer has identifier setup in support script: OTHER|1R9Y4x4168|Canada|Alberta
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=130
 * @SPEC:Identifier - OTHER, with both 'Country' and 'State/Province' [TC:044501] 
 * @Task#:Auto-1915
 * 
 * @author SWang
 * @Date  Oct 10, 2013
 */
public class IdenValidation_BothCountryAndState extends HFSKWebTestCase {
	private HFAddIdentificationPage addIdenPg = HFAddIdentificationPage.getInstance();
	private String identifierType, conmmonSchema, topErrorMes, idenNumErrorMes, idenCountryErrorMes, idenStateErrorMes,
	invaliedNum1, invaliedNum2, invaliedNum3, invaliedNum4, errorMesWithInvalidIdenNum, noFoundErrorMes, dateOfBEmpty;
	private List<String> idenState1, idenState2;
	private HFAccountLookupPage lookupAccountPg = HFAccountLookupPage.getInstance();

	public void execute() {
		//Go to account lookup page
		hf.invokeURL(url);
		hf.gotoLookupAccountPage();

		//Select identifier type "Others", has number, country (values without unknown, sort alphabetically), no state drop down list
		cus.identifier.identifierNum = StringUtil.EMPTY;
		cus.identifier.country = StringUtil.EMPTY;
		cus.identifier.state = StringUtil.EMPTY;
		lookupAccountPg.lookupAccount(cus);
		lookupAccountPg.verifyIdenTypeUI(cus.identifier.identifierType, true, true, true);
		lookupAccountPg.verifyCountries(cus.identifier.identifierType, cus.identifier.countries);

		//Verify state DDL values after select country "Canada"
		lookupAccountPg.syncProvinceSelectingCountry(cus.identifier.countries.get(1), cus.identifier.identifierType, false);
		lookupAccountPg.verifyCountry(cus.identifier.identifierType, cus.identifier.countries.get(1));
		lookupAccountPg.verifyProvinces(cus.identifier.identifierType, idenState1);

		//Verify no state DDL values after select country without states
		lookupAccountPg.syncProvinceSelectingCountry(cus.identifier.countries.get(3), cus.identifier.identifierType, true);
		lookupAccountPg.verifyProvinceDisplaying(cus.identifier.identifierType, false);

		//Verify state DDL values after select country "United States"
		lookupAccountPg.syncProvinceSelectingCountry(cus.identifier.countries.get(2), cus.identifier.identifierType, false);
		lookupAccountPg.verifyProvinces(cus.identifier.identifierType, idenState2);

		//Verify UI changed between identifier Passport # and Other #
		lookupAccountPg.selectIdentifierType(identifierType);
		lookupAccountPg.verifyIdenTypeUI(identifierType, true, true, true);
		lookupAccountPg.verifyIdenTypeUI(cus.identifier.identifierType, false, false, false);
		lookupAccountPg.selectIdentifierType(cus.identifier.identifierType);
		lookupAccountPg.verifyIdenTypeUI(identifierType, false, false, false);
		lookupAccountPg.verifyIdenTypeUI(cus.identifier.identifierType, true, true, true, true);

		//Verify error message when identifier number is blank and country is initial
		cus.identifier.identifierNum = StringUtil.EMPTY;
		cus.identifier.country = cus.identifier.countries.get(0);
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(topErrorMes);
		lookupAccountPg.verifyIdenRelatredErrorMeses(cus.identifier.identifierType, Arrays.asList(new String[]{idenNumErrorMes, idenCountryErrorMes}));

		//Verify error message when state is initial
		cus.identifier.identifierNum = "2R9Y4x0002";
		cus.identifier.country = cus.identifier.countries.get(1);
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyTopPgErrorMes(topErrorMes);
		lookupAccountPg.verifyIdenRelatredErrorMeses(cus.identifier.identifierType, Arrays.asList(new String[]{idenStateErrorMes}));

		//Verify error message when identifier number is invalid with state
		cus.identifier.country = cus.identifier.countries.get(1);
		cus.identifier.state = "Alberta";
		cus.identifier.identifierNum = invaliedNum1;
		hf.lookupAccountFromAccountLookupPage(cus);
		addIdenPg.verifyErrorMsgExist(errorMesWithInvalidIdenNum, true);

		cus.identifier.identifierNum = invaliedNum2;
		hf.lookupAccountFromAccountLookupPage(cus);
		addIdenPg.verifyErrorMsgExist(errorMesWithInvalidIdenNum, true);

		cus.identifier.identifierNum = invaliedNum3;
		hf.lookupAccountFromAccountLookupPage(cus);
		addIdenPg.verifyErrorMsgExist(errorMesWithInvalidIdenNum, true);

		//Verify error message when identifier number beyond maximum without state and date of birth is blank
		cus.identifier.country = cus.identifier.countries.get(3);
		cus.identifier.identifierNum = invaliedNum4;
		cus.dateOfBirth = StringUtil.EMPTY;
		cus.identifier.state = StringUtil.EMPTY;
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyIdenRelatredErrorMeses(cus.identifier.identifierType, Arrays.asList(new String[]{errorMesWithInvalidIdenNum}));
		lookupAccountPg.verifyProvinceDisplaying(cus.identifier.identifierType, false);
		lookupAccountPg.verifyErrorMsgExist(dateOfBEmpty, true);

		//Verify error message for existing identifier, but DOB is not matching
		cus.identifier.country = cus.identifier.countries.get(1);
		cus.identifier.identifierNum = "1R9Y4x4168";
		cus.identifier.state = "Alberta";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-02";
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAccountPg.verifyErrorMsgExist(noFoundErrorMes, true);

		//Successfully lookup account
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		hf.lookupAccountToAcctOverviewPg(cus);

		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("SK", env);
		conmmonSchema = DataBaseFunctions.getSchemaName("COMMON", env);

		cus.email = "hfsk_00033@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_OTHER_NUM_ID, true, true);
		cus.identifier.identifierNum = "2R9Y4x0002";
		cus.identifier.stateCode = "AB";
		identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_PASSPORT_NUM_ID, false, true);

		cus.identifier.countries.add("Please select");
		cus.identifier.countries.add("Canada");
		cus.identifier.countries.add("United States");
		cus.identifier.countries.addAll(hf.getIdenCountriesViaIdenTypeID(conmmonSchema, schema, OrmsConstants.IDEN_OTHER_NUM_ID));

		idenState1 = new ArrayList<String>();
		idenState1.add("Please select");
		idenState1.addAll(hf.getIdenContractRelatedStates(conmmonSchema, schema, OrmsConstants.IDEN_OTHER_NUM_ID, cus.identifier.countries.get(1)));

		idenState2 = new ArrayList<String>();
		idenState2.add("Please select");
		idenState2.addAll(hf.getIdenContractRelatedStates(conmmonSchema, schema, OrmsConstants.IDEN_OTHER_NUM_ID, cus.identifier.countries.get(2)));

		invaliedNum1 = "123";
		invaliedNum2 = "a-bc";
		invaliedNum3 = "1123abc%9";
		invaliedNum4 = hf.getIdenTypeMaxValue(schema, OrmsConstants.IDEN_OTHER_NUM_ID)+"1"; //"9999999999991"

		topErrorMes = "We need you to correct or provide more information. Please see each marked section.";
		idenNumErrorMes = cus.identifier.identifierType+" is required.";
		idenCountryErrorMes = "Country is required.";
		idenStateErrorMes = "Province is required.";
		errorMesWithInvalidIdenNum = "The Identification number is not valid for the selected identification type.";
		//Sara[20140303] DEFECT-61188
//		noFoundErrorMes = "No profile is found\\. Please check and correct the information or contact our call center if everything is accurate\\.";
		noFoundErrorMes = "No account has been found with the supplied information.*sign up page.*";
		dateOfBEmpty = "Date of Birth is required";
	}
}
