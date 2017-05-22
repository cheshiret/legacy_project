package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.lookupaccount;

import java.util.ArrayList;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFWebIdentifierValidationTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify the Identifier type US Driver License on Lookup Account page. 
 *  US Drivers License: at least 5 number and letters combined , the entry consists of only numbers and letters. 
 *  When it only contains numbers, it shall be less than or equal to the defined Maximum Value (max_value in c_identifier_type table)
 * @Preconditions: Make sure the identifier - US Driver License (type_id=6)setup in c_identifier_type as:
 * state_req_ind=1, country_req_ind=0, max_value=999999999999
 * @SPEC: Identifier - US Drivers License (STATE_ REQ_IND=1) [TC:044136]
 * @Task#: Auto-1769
 * 
 * @author Lesley Wang
 * @Date  Jul 17, 2013
 */
public class IdenValidation_USDriverLic extends
		HFWebIdentifierValidationTestCase {
	private String validDOB, validNum, existDOB;
	private CustomerIdentifier iden;
	
	@Override
	public void execute() {
		// Add US Driver License identifier to the customer
		hf.deleteCustIden(schema, iden.id, cus.fName, cus.lName);
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.addIdentifier(iden);
		hf.signOut();
		
		//1. verify US Driver License identifier info in Lookup Account page 
		cus.identifier.identifierType = iden.identifierType;
		cus.identifier.identifierNum = StringUtil.EMPTY;
		hf.gotoLookupAccountPage();
		lookupAccountPg.lookupAccount(cus);
		lookupAccountPg.verifyIdenTypeUI(cus.identifier.identifierType, true, true, false, true);
		lookupAccountPg.verifyProvince(cus.identifier.identifierType, cus.identifier.states.get(0));
		lookupAccountPg.verifyProvinces(cus.identifier.identifierType, cus.identifier.states);
		
		//2. Verify the input field for US Driver License identifier will be hidden after select another identifier
		lookupAccountPg.lookupAccount(cusNew);
		lookupAccountPg.verifyIdenTypeUI(cus.identifier.identifierType, false, false, false, false);
		lookupAccountPg.verifyIdenTypeUI(cusNew.identifier.identifierType, true, true, true, false);
		
		//3. Verify error message when input empty number and click Continue button
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, topPgErrorMes, StringUtil.EMPTY, msgs);
		
		//4. Verify error message when input invalid number and click Continue button:
		cus.identifier.state = iden.state;
		cus.identifier.identifierNum = "abc1"; // length less than 5
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, topPgErrorMes, StringUtil.EMPTY, errMsgWhenIdenIsInvalid);

		cus.identifier.identifierNum = "ab124#*1"; // contains special characters
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, topPgErrorMes, StringUtil.EMPTY, errMsgWhenIdenIsInvalid);
		
		cus.identifier.identifierNum = "1000000000000"; // exceed max value
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, topPgErrorMes, StringUtil.EMPTY, errMsgWhenIdenIsInvalid);
		
		//5. Verify error message when input valid number but level blank in Date of Birth
		cus.identifier.identifierNum = validNum;
		cus.dateOfBirth = StringUtil.EMPTY;
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, topPgErrorMes, errMsgWhenDOBIsBlank, StringUtil.EMPTY);
		
		//6. Verify message when input a valid but not existing number and a valid DOB
		cus.dateOfBirth = existDOB;
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, errMsgWhenNoAccountFound, StringUtil.EMPTY, StringUtil.EMPTY);
		lookupAccountPg.verifySignUpPageLinkExist(true);
		
		//7. Verify message when input a valid existing number but not existing DOB
		cus.identifier.identifierNum = iden.identifierNum;
		cus.dateOfBirth = validDOB;
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, errMsgWhenNoProfileFound, StringUtil.EMPTY, StringUtil.EMPTY);
		
		//8. Lookup by existing number and DOB
		cus.dateOfBirth = existDOB;
		hf.lookupAccountFromAccountLookupPage(cus);
		yourAccountFoundPg.verifyIdenRecord(cus.identifier.identifierType, cus.identifier.identifierNum+","+cus.identifier.stateCode);
	}

	@Override
	public void wrapParameters(Object[] param) {
		this.initialCommonHFMOTestInfo();
		cusNew.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_PASSPORT_NUM_ID, false, true);
		
		cus.email = "moidentvalidation@test.com"; // d_web_hf_signupaccount, id=820
		cus.password = "asdfasdf";
		cus.fName = "MOIdent_FN";
		cus.lName = "MOIdent_LM";
		cus.dateOfBirth = "01/01/" + DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.id = OrmsConstants.IDEN_CONSERVATION_ID; // for login in to add identifier
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, false);
		cus.identifier.identifierNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		cus.identifier.states.add("Please select");
		cus.identifier.states.addAll(hf.getIdenContractRelatedStates(conmmonSchema, schema, OrmsConstants.IDEN_USDRIVERSLICENSE_NUM_ID, StringUtil.EMPTY));
		cus.identifier.states.remove("Unknown");
		cus.identifier.stateCode = "AL";
		
		// Existing Identifier info
		iden = new CustomerIdentifier();
		iden.id = IDEN_USDRIVERSLICENSE_NUM_ID;
		iden.identifierType = hf.getIdenTypeName(schema, iden.id, true, false);
		iden.identifierNum = "USDLVALID001";
		iden.state = "Alabama";
		
		validDOB = "02/02/" + DateFunctions.getYearAfterCurrentYear(-16);
		validNum = "000000001"; // must be unique
		existDOB = cus.dateOfBirth;
	
		errMsgWhenIdenIsBlank = iden.identifierType + errMsgWhenIdenIsBlank;
		msgs = new ArrayList<String>();
		msgs.add(errMsgWhenIdenIsBlank);
		msgs.add(errMsgWhenStateIsBlank);
	}

}
