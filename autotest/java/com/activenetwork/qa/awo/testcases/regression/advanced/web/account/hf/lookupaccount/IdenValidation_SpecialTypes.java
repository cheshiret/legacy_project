package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.lookupaccount;

import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountLookupPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify error messages when input invalid identifier info on Account Lookup page
 * @Preconditions:
 * Make sure the SQL file is run: SetupIdentifierType.sql to setup the types RCMP # and CAF #:
 * RCMP: Max_value=999999999; Min_len=3; Max_len=12; Max_age=80
 * CAF: Numeric_only_ind=1; Max_value=999999999; Max_age=80
 * @SPEC: 
 * SK Identifier Validation on Account Lookup page [TC:062994]
 * @Task#: Auto-1717
 * 
 * @author Lesley Wang
 * @Date  Jun 13, 2013
 */
public class IdenValidation_SpecialTypes extends HFSKWebTestCase {

	private HFAccountLookupPage lookupAcctPg = HFAccountLookupPage.getInstance();
	private String numWithSpace, numExcMaxValue, topErrorMes, identNumReqMes, identErrMsg;
	private CustomerIdentifier extRCMP, extCAF, extCF;
	
	@Override
	public void execute() {
		// Go to Lookup Account Page
		hf.invokeURL(url);
		hf.gotoLookupAccountPage();
		
		// Verify RCMP identifier type: number with spaces; empty number; less than min length; contains invalid characters; exceed max length; exceed max value
		this.inputNumWithSpaceAndVerifyTrim(extRCMP.identTypeFullNm, numWithSpace);
		this.inputInvalidIdentAndVerifyErrorMsg(extRCMP.identTypeFullNm, StringUtil.EMPTY, extRCMP.state, topErrorMes, extRCMP.identifierType + identNumReqMes);
		this.inputInvalidIdentAndVerifyErrorMsg(extRCMP.identTypeFullNm, "12", extRCMP.state, topErrorMes, identErrMsg);
		this.inputInvalidIdentAndVerifyErrorMsg(extRCMP.identTypeFullNm, "12%%@3", extRCMP.state, topErrorMes, identErrMsg);
		this.inputInvalidIdentAndVerifyErrorMsg(extRCMP.identTypeFullNm, "abcde12345678", extRCMP.state, topErrorMes, identErrMsg);
		this.inputInvalidIdentAndVerifyErrorMsg(extRCMP.identTypeFullNm, numExcMaxValue, extRCMP.state, topErrorMes, identErrMsg);
		
		// Verify CAF identifier type: empty number; less than min length; contains invalid characters; exceed max value
		this.inputNumWithSpaceAndVerifyTrim(extCAF.identTypeFullNm, numWithSpace);
		this.inputInvalidIdentAndVerifyErrorMsg(extCAF.identTypeFullNm, StringUtil.EMPTY, extCAF.state, topErrorMes, extCAF.identifierType + identNumReqMes);
		this.inputInvalidIdentAndVerifyErrorMsg(extCAF.identTypeFullNm, "1234", extCAF.state, topErrorMes, identErrMsg);
		this.inputInvalidIdentAndVerifyErrorMsg(extCAF.identTypeFullNm, "12^&*3", extCAF.state, topErrorMes, identErrMsg);
		this.inputInvalidIdentAndVerifyErrorMsg(extCAF.identTypeFullNm, "abc123", extCAF.state, topErrorMes, identErrMsg);
		this.inputInvalidIdentAndVerifyErrorMsg(extCAF.identTypeFullNm, numExcMaxValue, extCAF.state, topErrorMes, identErrMsg);
		
		// Verify CF identifier type
		this.inputInvalidIdentAndVerifyErrorMsg(extCF.identTypeFullNm, StringUtil.EMPTY, extCF.state, topErrorMes, extCF.identifierType + identNumReqMes);
		this.inputInvalidIdentAndVerifyErrorMsg(extCF.identTypeFullNm, "123", extCF.state, topErrorMes, identErrMsg);
		this.inputInvalidIdentAndVerifyErrorMsg(extCF.identTypeFullNm, "12^&*3", extCF.state, topErrorMes, identErrMsg);
		this.inputInvalidIdentAndVerifyErrorMsg(extCF.identTypeFullNm, "12345678.12ab", extCF.state, topErrorMes, identErrMsg);
		this.inputInvalidIdentAndVerifyErrorMsg(extCF.identTypeFullNm, numExcMaxValue, extCF.state, topErrorMes, identErrMsg);
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.dateOfBirth = "1986-01-01";
		
		// Identifiers info
		extRCMP = this.generateIdentInfo(IDEN_RCMP_ID, IDENT_TYPE_RCMP, IDENT_TYPE_NAME_RCMP, "Saskatchewan");
		extCAF = this.generateIdentInfo(IDEN_CAF_ID, IDENT_TYPE_CAF, IDENT_TYPE_NAME_CAF, "Nunavut");
		extCF = this.generateIdentInfo(IDEN_FL_ID, IDENT_TYPE_FL, IDENT_TYPE_NAME_FL, StringUtil.EMPTY);

		// Invalid Numbers and error messages
		numWithSpace = " 010--50 ";
		numExcMaxValue = "100000000000";
		topErrorMes = "We need you to correct or provide more information. Please see each marked section.";
		identErrMsg = "The Identification number is not valid for the selected identification type.";
		identNumReqMes = " is required.";
	}
	
	private void inputInvalidIdentAndVerifyErrorMsg(String identType, String num, String state, String topErrMsg, String errMsg) {
		cus.identifier.identifierType = identType;
		cus.identifier.identifierNum = num;
		cus.identifier.state = state;
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAcctPg.verifyTopPgErrorMes(topErrMsg);
		lookupAcctPg.verifyIdenRelatredErrorMes(identType, errMsg);
	}

	private void inputNumWithSpaceAndVerifyTrim(String type, String num) {
		cus.identifier.identifierType = type;
		cus.identifier.identifierNum = num;
		cus.identifier.state = StringUtil.EMPTY;
		hf.lookupAccountFromAccountLookupPage(cus);
		lookupAcctPg.verifyIdenNum(type, num.trim());
	}
	
	private CustomerIdentifier generateIdentInfo(String id, String type, String fullNm, String state) {
		CustomerIdentifier ident = new CustomerIdentifier();
		ident.id = id;
		ident.identifierType = type;
		ident.identTypeFullNm = fullNm;
		ident.state = state;
		return ident;
	}
}
