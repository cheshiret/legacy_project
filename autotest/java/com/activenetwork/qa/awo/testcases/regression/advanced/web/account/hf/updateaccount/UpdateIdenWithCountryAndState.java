package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.updateaccount;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateIdentificationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOWebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify below check points for adding "OTHER" identifier type which has country_req_ind=1 and state_req_ind=1
 * Initial no state DDL, verify country drop down list default and all options
 * After select Canada country, verify states
 * After select other country without state, no state DDL
 * error messages when leave the input field blank and country DDL as default
 * error messages when leave the input field blank and select country DDL 
 * error message when can't match at least 5 number and letters combined , the entry consists of only the following characters: numbers, letters, embedded space, dash.
 * error message when can't match only contains numbers, it shall be less than or equal to the define Maximum Value (max_value in c_identifier_type table)
 * verify successfully message when enter a valid number (not exists in the system yet)
 * Verify identifier info after successfully update one

 * @Preconditions:
 * d_web_hf_signupaccount
 * id=550, hfmo_00007@webhftest.com, UpdateIdenCS1_FN, UpdateIdenCS1_LN, OTHER|1R9Y4x4139|Canada|Alberta 
 * id=560, hfmo_00008@webhftest.com, UpdateIdenCS2_FN, UpdateIdenCS2_LN, OTHER|1R9Y4x4140|Canada|Alberta
 * 
 * @SPEC: Update Identifier - Other (STATE_ REQ_IND=1, COUNTRY_REQ_IND=1) [TC:046102] 
 * @Task#: AUTO-1638
 * 
 * @author SWang
 * @Date  May 30, 2013
 */
public class UpdateIdenWithCountryAndState extends HFMOWebTestCase {
	private HFUpdateIdentificationPage updateIdenPg = HFUpdateIdentificationPage.getInstance();
	private HFUpdateAccountPage updateAccountPg = HFUpdateAccountPage.getInstance();
	private String invalidValue1, invalidValue2, invalidValue3, invalidValue4, validValue, topErrorMes, errorMes1, errorMes2, errorMes3, errorMes4, errorMes5, errorMes6, errorMes7, successfulMes, mask, maskedNum;
	private int hideNum, showNum;
	private CustomerIdentifier iden;

	public void execute() {
		//Go to 1# account update identifier page
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		hf.gotoUpdateIdenPg(iden.id);

		//Initial no state DDL, verify country drop down list default and all options
		updateIdenPg.verifyStateExists(false);
		updateIdenPg.verifyCountry(iden.countries.get(0));
		updateIdenPg.verifyCountries(iden.countries);

		//After select Canada country, verify states
		updateIdenPg.synStateSelectingCountry(iden.countries.get(1), false);
		updateIdenPg.verifyStates(iden.states);

		//After select other country without state, no state DDL
		updateIdenPg.synStateSelectingCountry(iden.countries.get(2), true);

		//error messages when leave the input field blank and country DDL as default 
		iden.identifierNum = StringUtil.EMPTY;
		iden.country = iden.countries.get(0);
		iden.state = StringUtil.EMPTY;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(topErrorMes, true);
		updateIdenPg.verifyErrorMsgExist(errorMes1, true);
		updateIdenPg.verifyErrorMsgExist(errorMes2, true);

		//error messages when leave the input field blank and select country DDL 
		iden.country = iden.countries.get(1);
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes1, true);
		updateIdenPg.verifyErrorMsgExist(errorMes3, true);

		//error message when can't match at least 5 number and letters combined , the entry consists of only the following characters: numbers, letters, embedded space, dash.
		iden.identifierNum = invalidValue1;
		iden.country = iden.countries.get(1);
		iden.state = iden.states.get(1);
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes4, true);

		iden.identifierNum = invalidValue2;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes4, true);

		iden.identifierNum = invalidValue3;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes5, true);

		//error message when can't match only contains numbers, it shall be less than or equal to the define Maximum Value (max_value in c_identifier_type table)
		iden.identifierNum = invalidValue4;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes6, true);

		//error message when enter an existing identifier
		iden.identifierNum = cusNew.identifier.identifierNum;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateIdenPg.verifyErrorMsgExist(errorMes7, true);

		//verify successfully message when enter a valid number (not exists in the system yet)
		iden.identifierType = hf.getIdenTypeName(schema, iden.id, true, true);
		iden.identifierNum = validValue;
		hf.updateIdenFromUpdateIdentificationPg(iden);
		updateAccountPg.verifyMsgExist(successfulMes, true);

		//Verify identifier info after successfully update one
		updateAccountPg.verifyIdentifierInfo(iden, maskedNum);

		hf.signOut();
	}

	public void wrapParameters(Object[] param) {

		//First customer parameters
		cus.email = "hfmo_00007@webhftest.com";
		cus.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cus.fName = "UpdateIdenCS1_FN";
		cus.lName = "UpdateIdenCS1_LN";
		cus.identifier.id = OrmsConstants.IDEN_CONSERVATION_ID;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, false);
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);

		iden = new CustomerIdentifier();
		iden.countries.add("Please select");
		iden.countries.add("Canada");
		iden.countries.addAll(hf.getIdenCountriesViaIdenTypeID(conmmonSchema, schema, OrmsConstants.IDEN_OTHER_NUM_ID));

		iden.states.add("Please select");
		iden.states.addAll(hf.getIdenContractRelatedStates(conmmonSchema, schema, OrmsConstants.IDEN_OTHER_NUM_ID, StringUtil.EMPTY));

		iden.id = OrmsConstants.IDEN_OTHER_NUM_ID;
		iden.identifierType = hf.getIdenTypeName(schema, iden.id, true, true);
		iden.identifierNum = "1R9Y4x4139";
		iden.country = iden.countries.get(1);
		iden.state = iden.states.get(1);
		iden.stateCode = "AB";

		//Second customer parameters
		cusNew.email = "hfmo_00008@webhftest.com";
		cusNew.dateOfBirth = "01/01/"+DateFunctions.getYearAfterCurrentYear(-16);
		cusNew.fName = "UpdateIdenCS2_FN";
		cusNew.lName = "UpdateIdenCS2_LN";
		cusNew.identifier.id = OrmsConstants.IDEN_OTHER_NUM_ID;
		cusNew.identifier.identifierType = hf.getIdenTypeName(schema, cusNew.identifier.id, true, true);
		cusNew.identifier.identifierNum = "1R9Y4x4140";
		cusNew.identifier.country = iden.countries.get(1);
		cusNew.identifier.state = iden.states.get(1);

		invalidValue1 = "0123";
		invalidValue2 = "0a-bc";
		invalidValue3 = "1123abc%9";
		invalidValue4 = "9999999991";
		validValue = "A"+new DecimalFormat("0000000").format(new Random().nextInt(9999999))+"BC";

		hideNum = Integer.valueOf(TestProperty.getProperty("identification.mask.hide"));
		showNum = Integer.valueOf(TestProperty.getProperty("identification.mask.show"));
		mask = TestProperty.getProperty("identification.mask.character");
		maskedNum = StringUtil.encryptString(validValue, mask, hideNum, showNum);

		topErrorMes = "We need you to correct or provide more information\\. Please see each marked section\\.";
		errorMes1 = iden.identifierType+" is required\\.";
		errorMes2 = "Country is required\\.";
		errorMes3 = "State/Province is required\\.";
		errorMes4 = "OTHER must contains at least 5 characters, excluding spaces and dashes\\.";
		errorMes5 = "OTHER must only contain numbers, embedded spaces or a dash\\.";
		errorMes6 = "OTHER is invalid.";
		errorMes7 = iden.identifierType+" with the same number is used by another record\\. Please contact the call center for more information\\.";
		successfulMes = iden.identifierType+" information is updated successfully";
	}
}
