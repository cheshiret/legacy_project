package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.lookupaccount;

import java.util.ArrayList;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFWebIdentifierValidationTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify the Identifier type which only has country required on Lookup Account page. 
 *  NON-US DL Number / Green Card / Visa: at least 5 number and letters combined , the entry consists of only numbers and letters. 
 *  When it only contains numbers, it shall be less than or equal to the defined Maximum Value (max_value in c_identifier_type table)
 *  NON-US DL Number: 999999999999
 *  Green Card: 999999999
 *  Visa: 999999999999
 * @Preconditions: Make sure the identifiers - NON-US DL Number / Green Card / Visa setup in c_identifier_type as:
 * state_req_ind=0, country_req_ind=1
 * @SPEC:
 * Identifier - NON-US DL Number (COUNTRY_ REQ_IND=1) [TC:044520]
 * Identifier - Green Card (COUNTRY_ REQ_IND=1) [TC:044533]
 * Identifier - Visa (COUNTRY_ REQ_IND=1) [TC:044521]
 * @Task#: Auto-1769
 * 
 * @author Lesley Wang
 * @Date  Jul 17, 2013
 */
public class IdenValidation_OnlyCountryList extends
		HFWebIdentifierValidationTestCase {

	private String validDOB, validNum, existDOB, identName_NonUSDL, identName_GreenCard;
	private CustomerIdentifier iden;
	
	@Override
	public void execute() {
		// Add US Driver License identifier to the customer
		hf.deleteCustIden(schema, iden.id, cus.fName, cus.lName);
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.addIdentifier(iden);
		hf.signOut();
		
		//1. verify VISA identifier info in Lookup Account page 
		cus.identifier.identifierType = iden.identifierType;
		cus.identifier.identifierNum = StringUtil.EMPTY;
		hf.gotoLookupAccountPage();
		lookupAccountPg.lookupAccount(cus);
		lookupAccountPg.verifyIdenTypeUI(cus.identifier.identifierType, true, true, true, false);
		lookupAccountPg.verifyCountry(cus.identifier.identifierType, cus.identifier.countries.get(0));
		lookupAccountPg.verifyCountries(cus.identifier.identifierType, cus.identifier.countries);
		
		//2. Verify the input field for VISA identifier will be hidden after select another identifier
		lookupAccountPg.lookupAccount(cusNew);
		lookupAccountPg.verifyIdenTypeUI(cus.identifier.identifierType, false, false, false, false);
		lookupAccountPg.verifyIdenTypeUI(cusNew.identifier.identifierType, true, true, true, false);
		
		//3. Verify error message when input empty number and click Continue button
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, topPgErrorMes, StringUtil.EMPTY, msgs);
		
		//4. Verify error message when input invalid number and click Continue button:
		cus.identifier.country = iden.country;
		cus.identifier.identifierNum = "abc1"; // length less than 5
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, topPgErrorMes, StringUtil.EMPTY, errMsgWhenIdenIsInvalid);

		cus.identifier.identifierNum = "1123abc%9"; // contains special characters
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, topPgErrorMes, StringUtil.EMPTY, errMsgWhenIdenIsInvalid);
		
		cus.identifier.identifierNum = "1000000000000"; // exceed max value
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, topPgErrorMes, StringUtil.EMPTY, errMsgWhenIdenIsInvalid);
		
		//5. Verify error message when input invalid number for Non-US DL Number and click Continue button:
		cus.identifier.identifierType = identName_NonUSDL;
		cus.identifier.identifierNum = "1000000000000"; // exceed max value
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, topPgErrorMes, StringUtil.EMPTY, errMsgWhenIdenIsInvalid);
		
		//6. Verify error message when input invalid number for Green Card and click Continue button:
		cus.identifier.identifierType = identName_GreenCard;
		cus.identifier.identifierNum = "1000000000"; // exceed max value
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, topPgErrorMes, StringUtil.EMPTY, errMsgWhenIdenIsInvalid);
		
		//7. Verify error message when input valid number but level blank in Date of Birth
		cus.identifier.identifierType = iden.identifierType;
		cus.identifier.identifierNum = validNum;
		cus.dateOfBirth = StringUtil.EMPTY;
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, topPgErrorMes, errMsgWhenDOBIsBlank, StringUtil.EMPTY);
		
		//8. Verify message when input a valid but not existing number and a valid DOB
		cus.dateOfBirth = existDOB;
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, errMsgWhenNoAccountFound, StringUtil.EMPTY, StringUtil.EMPTY);
		lookupAccountPg.verifySignUpPageLinkExist(true);
		
		//9. Verify message when input a valid existing number but not existing DOB
		cus.identifier.identifierNum = iden.identifierNum;
		cus.dateOfBirth = validDOB;
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, errMsgWhenNoProfileFound, StringUtil.EMPTY, StringUtil.EMPTY);
		
		//10. Lookup by existing number and DOB
		cus.dateOfBirth = existDOB;
		hf.lookupAccountFromAccountLookupPage(cus);
		yourAccountFoundPg.verifyIdenRecord(cus.identifier.identifierType, cus.identifier.identifierNum+","+cus.identifier.country);
	}

	@Override
	public void wrapParameters(Object[] param) {
		this.initialCommonHFMOTestInfo();
		// Existing Identifier info
		iden = new CustomerIdentifier();
		iden.id = IDEN_VISA_ID;
		iden.identifierType = hf.getIdenTypeName(schema, iden.id, false, true);
		iden.identifierNum = "VISAVALID001";
		iden.country = "Canada";
		
		// Customer info
		cus.email = "moidentvalidation@test.com"; // d_web_hf_signupaccount, id=820
		cus.password = "asdfasdf";
		cus.fName = "MOIdent_FN";
		cus.lName = "MOIdent_LM";
		cus.dateOfBirth = "01/01/" + DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.id = OrmsConstants.IDEN_CONSERVATION_ID; // for login in to add identifier
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, false);
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		cus.identifier.countries.add("Please select");
		cus.identifier.countries.add("Canada");
		cus.identifier.countries.addAll(hf.getIdenCountriesViaIdenTypeID(conmmonSchema, schema, IDEN_VISA_ID));
		
		cusNew.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_PASSPORT_NUM_ID, false, true);
		
		// Other test data
		validDOB = "02/02/" + DateFunctions.getYearAfterCurrentYear(-16);
		validNum = "000000001"; // must be unique
		existDOB = cus.dateOfBirth;
		identName_NonUSDL = hf.getIdenTypeName(schema, IDEN_NONUSDL_NUM_ID, false, true);
		identName_GreenCard = hf.getIdenTypeName(schema, IDEN_GREENCARD_NUM_ID, false, true);
		errMsgWhenIdenIsBlank = iden.identifierType + errMsgWhenIdenIsBlank;
		msgs = new ArrayList<String>();
		msgs.add(errMsgWhenIdenIsBlank);
		msgs.add(errMsgWhenCountryIsBlank);
	}
}
