package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.updateaccount;

import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.web.hf.HFAddIdentificationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFContactUsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify error messages when input invalid identifier info on Add Identifier page
 * @Preconditions:
 * Make sure the SQL file is run: SetupIdentifierType.sql to setup the types RCMP # and CAF #:
 * RCMP: Max_value=999999999; Min_len=3; Max_len=12; Max_age=80
 * CAF: Numeric_only_ind=1; Max_value=999999999; Max_age=80
 * @SPEC: 
 * SK Identifier Validation on Add Identifier / Update Identifier page [TC:063347]
 * @Task#: Auto-1717
 * 
 * @author Lesley Wang
 * @Date  Jun 9, 2013
 */
public class AddIdenValidation_SpecialTypes extends
		HFSKWebTestCase {
	private HFAddIdentificationPage addIdenPg = HFAddIdentificationPage.getInstance();
	private String numWithSpace, numExcMaxValue, topErrorMes, identNumReqMes, 
	errMsg_LessMin, errMsg_InvalidChar, errMsg_ExcMax, errMsg_ExistNum, 
	errMsg_CFLFormat, errMsg_InvalidAge, errMsg_ExcMaxLeg;
	private CustomerIdentifier extRCMP, extCAF, extCF;
	private String emailWith80;
	
	@Override
	public void execute() {
		hf.deleteCustAllIdentExceptCustNum(schema, cus.email);

		// Add valid RCMP, CAF, CF for one customer
		hf.signIn(url, cusNew.email, cusNew.password);
		this.addValidIdentifiers(extRCMP, extCAF, extCF);
		hf.signOut();
		
		// Login in with another account to test
		hf.signIn(url, cus.email, cus.password);
		hf.gotoAddIdentificationPg();
		
		// Verify RCMP identifier type: number with spaces; empty number; less than min length; contains invalid characters; exceed max length; exceed max value; existing number; 
		this.generateErrMsgByIdentType(IDENT_TYPE_RCMP);
		this.inputNumWithSpaceAndVerifyTrim(extRCMP.identifierType, numWithSpace);
		this.inputInvalidIdentAndVerifyErrorMsg(extRCMP.identifierType, StringUtil.EMPTY, extRCMP.state, topErrorMes, identNumReqMes);
		this.inputInvalidIdentAndVerifyErrorMsg(extRCMP.identifierType, "12", extRCMP.state, topErrorMes, errMsg_LessMin);
		this.inputInvalidIdentAndVerifyErrorMsg(extRCMP.identifierType, "12%%@3", extRCMP.state, topErrorMes, errMsg_InvalidChar);
//		this.inputInvalidIdentAndVerifyErrorMsg(extRCMP.identifierType, "abcde12345678", extRCMP.state, topErrorMes, errMsg_ExcMaxLeg);//Sara[6/18/2014] can't enter more characters than the maxLength=12
		this.inputInvalidIdentAndVerifyErrorMsg(extRCMP.identifierType, numExcMaxValue, extRCMP.state, topErrorMes, errMsg_ExcMax);
		this.inputInvalidIdentAndVerifyErrorMsg(extRCMP.identifierType, extRCMP.identifierNum, extRCMP.state, topErrorMes, errMsg_ExistNum);
		
		// Verify CAF identifier type: empty number; less than min length; contains invalid characters; exceed max value; existing number; 
		this.generateErrMsgByIdentType(IDENT_TYPE_CAF);
		this.inputNumWithSpaceAndVerifyTrim(extCAF.identifierType, numWithSpace);
		this.inputInvalidIdentAndVerifyErrorMsg(extCAF.identifierType, StringUtil.EMPTY, extCAF.state, topErrorMes, identNumReqMes);
		this.inputInvalidIdentAndVerifyErrorMsg(extCAF.identifierType, "1234", extCAF.state, topErrorMes, errMsg_LessMin);
		this.inputInvalidIdentAndVerifyErrorMsg(extCAF.identifierType, "12^&*3", extCAF.state, topErrorMes, errMsg_InvalidChar);
		this.inputInvalidIdentAndVerifyErrorMsg(extCAF.identifierType, "abc123", extCAF.state, topErrorMes, errMsg_InvalidChar);
		this.inputInvalidIdentAndVerifyErrorMsg(extCAF.identifierType, numExcMaxValue, extCAF.state, topErrorMes, errMsg_ExcMax);
		this.inputInvalidIdentAndVerifyErrorMsg(extCAF.identifierType, extCAF.identifierNum, extCAF.state, topErrorMes, errMsg_ExistNum);
		
		// Verify CF identifier type
		this.generateErrMsgByIdentType(IDENT_TYPE_FL);
		this.inputInvalidIdentAndVerifyErrorMsg(extCF.identifierType, StringUtil.EMPTY, extCF.state, topErrorMes, identNumReqMes);
		this.inputInvalidIdentAndVerifyErrorMsg(extCF.identifierType, "123", extCF.state, topErrorMes, errMsg_CFLFormat);
		this.inputInvalidIdentAndVerifyErrorMsg(extCF.identifierType, "12^&*3", extCF.state, topErrorMes, errMsg_CFLFormat);
		this.inputInvalidIdentAndVerifyErrorMsg(extCF.identifierType, "12345678.12ab", extCF.state, topErrorMes, errMsg_CFLFormat);
		this.inputInvalidIdentAndVerifyErrorMsg(extCF.identifierType, numExcMaxValue, extCF.state, topErrorMes, errMsg_CFLFormat);
		this.inputInvalidIdentAndVerifyErrorMsg(extCF.identifierType, extCF.identifierNum, extCF.state, topErrorMes, errMsg_ExistNum);
		// contact the call center link check
		this.verifyContactCallCenterLink();
		hf.signOut();
		
		// Login in with account with age > 80 and verify add identifier
		hf.signIn(url, emailWith80, cus.password);
		hf.gotoAddIdentificationPg();
		this.inputInvalidIdentAndVerifyErrorMsg(extRCMP.identifierType, "abcdefg", extRCMP.state, topErrorMes, IDENT_TYPE_RCMP + errMsg_InvalidAge);
		this.inputInvalidIdentAndVerifyErrorMsg(extCAF.identifierType, "88888", extCAF.state, topErrorMes, IDENT_TYPE_CAF + errMsg_InvalidAge);
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "updatecustprofile@test.com"; // d_web_hf_signupaccount, id=110
		cus.password = "asdfasdf";
		
		cusNew.email = "inputinvaliddentinfo03@test.com"; // d_web_hf_signupaccount, id=830
		cusNew.password = cus.password;
		
		emailWith80 = "ageolderthan80@test.com"; // d_web_hf_signupaccount, id=650 , age > 80
		
		extRCMP = this.generateIdentInfo(IDEN_RCMP_ID, IDENT_TYPE_NAME_RCMP, "SK0000000001", "Saskatchewan");// the number added by support script, can't be deleted	
		extCAF = this.generateIdentInfo(IDEN_CAF_ID, IDENT_TYPE_NAME_CAF, "900001", "Alberta");	
		extCF = this.generateIdentInfo(IDEN_FL_ID, IDENT_TYPE_NAME_FL, "12345678.0000", StringUtil.EMPTY);
		
		numWithSpace = " 01050-- ";
		numExcMaxValue = "100000000000";
		topErrorMes = "We need you to correct or provide more information. Please see each marked section.";
		errMsg_ExcMaxLeg = IDENT_TYPE_RCMP +" must contain no more than 12 characters, excluding spaces and dashes.";
		errMsg_CFLFormat = IDENT_TYPE_FL + " must be in the format NNNNNNNN.NNNN.";
		errMsg_InvalidAge = " can only be used for Customers up to 80 years of age.";
	}

	private CustomerIdentifier generateIdentInfo(String id, String type, String num, String state) {
		CustomerIdentifier ident = new CustomerIdentifier();
		ident.id = id;
		ident.identifierType = type;
		ident.identifierNum = num;
		ident.state = state;
		return ident;
	}

	private void inputInvalidIdentAndVerifyErrorMsg(String identType, String num, String state, String... errMsg) {
		cus.identifier.identifierType = identType;
		cus.identifier.identifierNum = num;
		cus.identifier.state = state;
		hf.addIdentifierFromAddIdentificationPg(cus.identifier);
		for (String err : errMsg) {
			addIdenPg.verifyErrorMsgExist(err, true);
		}
	}
	
	private void generateErrMsgByIdentType(String type) {
		identNumReqMes = type+" is required.";
		if (type.equals(IDENT_TYPE_RCMP))
			errMsg_LessMin = type+" must contains at least 3 characters, excluding spaces and dashes.";
		else 
			errMsg_LessMin = type+" must contains at least 5 characters, excluding spaces and dashes.";
		errMsg_InvalidChar = type+" must only contain numbers, embedded spaces or a dash.";
		errMsg_ExcMax = type+" is invalid.";
		errMsg_ExistNum = type + " with the same number is used by another account. Please contact the call center for more information.";
		
	}
	
	private void inputNumWithSpaceAndVerifyTrim(String type, String num) {
		cus.identifier.identifierType = type;
		cus.identifier.identifierNum = num;
		cus.identifier.state = StringUtil.EMPTY;
		hf.addIdentifierFromAddIdentificationPg(cus.identifier);
		addIdenPg.verifyIdenNum(type, num.trim());
	}
	
	private void verifyContactCallCenterLink() {
		HFContactUsPage contactPg = HFContactUsPage.getInstance();
		addIdenPg.clickContactCallCenterLink();
		contactPg.waitLoading();
	}
	
	private void addValidIdentifiers(CustomerIdentifier... idents) {
		HFUpdateAccountPage updAcctPg = HFUpdateAccountPage.getInstance();
		hf.gotoUpdateProfilePg();
		for (CustomerIdentifier ident : idents) {
			if (!updAcctPg.checkIdenExisting(ident.identifierType)) 
				hf.addIdentifier(ident);
		}		
	}
}
