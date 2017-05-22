package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.lookupaccount;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFWebIdentifierValidationTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify the Identifier type Social Security Number on Lookup Account page. 
 *  If the specified Identifier Type is a â€œSocial Security Numberâ€�, the System shall enforce that exactly 9 numbers are specified, and that the entry consists of only numbers.  
 *  The System shall persist any leading zeros, if specified. 
 *  The first three numbers cannot all be zeros (i.e. 000)
 *  The fourth and fifth numbers cannot all be zeros (i.e. 00).
 *  The sixth to ninth numbers cannot all be zeros (i.e. 0000).
 * @Preconditions: Make sure the identifier - Social Security Number (type_id=4)setup in c_identifier_type as:
 * state_req_ind=0, country_req_ind=0
 * @SPEC: Identifier - Social Security Number [TC:044134]
 * @Task#: Auto-1769
 * 
 * @author Lesley Wang
 * @Date  Jul 16, 2013
 */
public class IdenValidation_SocialSecuNum extends HFWebIdentifierValidationTestCase {
	private String validDOB, validNum, existDOB, existNum;
	@Override
	public void execute() {
		hf.invokeURL(url);
		hf.gotoLookupAccountPage();
		
		//1. verify Social Security Number info in Lookup Account page 
		lookupAccountPg.lookupAccount(cus);
		lookupAccountPg.verifyIdenTypeUI(cus.identifier.identifierType, true, true, false, false);
		
		//2. Verify the input field for Social Security Number will be hidden after select another identifier
		lookupAccountPg.lookupAccount(cusNew);
		lookupAccountPg.verifyIdenTypeUI(cus.identifier.identifierType, false, false, false, false);
		lookupAccountPg.verifyIdenTypeUI(cusNew.identifier.identifierType, true, true, true, false);

		//3. Verify error message when input empty number and click Continue button
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, topPgErrorMes, StringUtil.EMPTY, errMsgWhenIdenIsBlank);
		
		//4. Verify error message when input invalid number and click Continue button:
		cus.identifier.identifierNum = "abc123456";
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, topPgErrorMes, StringUtil.EMPTY, errMsgWhenIdenIsInvalid);

		cus.identifier.identifierNum = "000123456";
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, topPgErrorMes, StringUtil.EMPTY, errMsgWhenIdenIsInvalid);
		
		cus.identifier.identifierNum = "123004567";
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, topPgErrorMes, StringUtil.EMPTY, errMsgWhenIdenIsInvalid);
		
		cus.identifier.identifierNum = "123450000";
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, topPgErrorMes, StringUtil.EMPTY, errMsgWhenIdenIsInvalid);
		
		cus.identifier.identifierNum = "1234567890";
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
		cus.identifier.identifierNum = existNum;
		cus.dateOfBirth = validDOB;
		this.verifyErrorMsgWhenLookUpAcctWithInvalidInfo(cus, errMsgWhenNoProfileFound, StringUtil.EMPTY, StringUtil.EMPTY);
		
		//8. Lookup by existing number and DOB
		cus.dateOfBirth = existDOB;
		cus.identifier.identifierNum = existNum;
		hf.lookupAccountFromAccountLookupPage(cus);
		yourAccountFoundPg.verifyIdenRecord(cus.identifier.identifierType, cus.identifier.identifierNum);
	}

	@Override
	public void wrapParameters(Object[] param) {
		this.initialCommonHFMOTestInfo();
		cusNew.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_PASSPORT_NUM_ID, false, true);
		
		cus.email = "moidentvalidation@test.com"; // d_web_hf_signupaccount, id=820
		cus.dateOfBirth = "01/01/" + DateFunctions.getYearAfterCurrentYear(-16);
		cus.identifier.id = IDEN_SOCIALSECURITY_NUM_ID;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, cus.identifier.id, false, false);
		cus.identifier.identifierNum = StringUtil.EMPTY;
		cus.identifier.country = StringUtil.EMPTY;
		cus.identifier.state = StringUtil.EMPTY;
		
		validDOB = "02/02/" + DateFunctions.getYearAfterCurrentYear(-16);
		validNum = "900909001"; // must be unique
		existDOB = cus.dateOfBirth;
		existNum = "900909000"; // d_web_hf_signupaccount, id=820
		
		errMsgWhenIdenIsBlank = cus.identifier.identifierType + errMsgWhenIdenIsBlank;
	}
}
