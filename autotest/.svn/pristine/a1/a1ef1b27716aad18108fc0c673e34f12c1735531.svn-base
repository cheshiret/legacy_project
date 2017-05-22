package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.updateaccount;

import com.activenetwork.qa.awo.datacollection.legacy.CustomerIdentifier;
import com.activenetwork.qa.awo.pages.web.hf.HFContactUsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateIdentificationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify error messages when input invalid identifier info on Update Identifier page
 * @Preconditions:
 * Make sure the SQL file is run: SetupIdentifierType.sql to setup the types RCMP # and CAF #:
 * RCMP: Max_value=999999999; Min_len=3; Max_len=12; Max_age=80
 * CAF: Numeric_only_ind=1; Max_value=999999999; Max_age=80
 * @SPEC: 
 * SK Identifier Validation on Add Identifier / Update Identifier page [TC:063347]
 * @Task#: Auto-1717
 * 
 * @author Lesley Wang
 * @Date  Jun 13, 2013
 */
public class UpdateIdenValidation_SpecialTypes extends HFSKWebTestCase {

	private HFUpdateIdentificationPage updIdenPg = HFUpdateIdentificationPage.getInstance();
	private HFUpdateAccountPage updAcctPg = HFUpdateAccountPage.getInstance();
	private String dob, numWithSpace, numExcMaxValue, topErrorMes, identNumReqMes, 
	errMsg_LessMin, errMsg_InvalidChar, errMsg_ExcMax, errMsg_ExistNum, 
	errMsg_CFLFormat, errMsg_InvalidAge, errMsg_ExcMaxLeg;
	private CustomerIdentifier extRCMP, extCAF, extCF, validRCMP, validCAF, validCF;
	
	@Override
	public void execute() {
		// Add valid RCMP, CAF, CF for one customer
		hf.signIn(url, cusNew.email, cusNew.password);
		this.addValidIdentifiers(extRCMP, extCAF, extCF);
		hf.signOut();
		
		// Login in with another account to add valid identifiers
		hf.signIn(url, cus.email, cus.password);
		hf.updateCustDOB(cus.dateOfBirth);
		this.addValidIdentifiers(validRCMP, validCAF, validCF);
		
		// Verify RCMP identifier type: number with spaces; empty number; less than min length; contains invalid characters; exceed max length; exceed max value; existing number; 
		this.generateErrMsgByIdentType(IDENT_TYPE_RCMP);
		this.inputNumWithSpaceAndVerifyTrim(extRCMP.id, numWithSpace);
		this.inputInvalidIdentAndVerifyErrorMsg(extRCMP.id, StringUtil.EMPTY, extRCMP.state, topErrorMes, identNumReqMes);
		this.inputInvalidIdentAndVerifyErrorMsg(extRCMP.id, "12", extRCMP.state, topErrorMes, errMsg_LessMin);
		this.inputInvalidIdentAndVerifyErrorMsg(extRCMP.id, "12%%@3", extRCMP.state, topErrorMes, errMsg_InvalidChar);
//		this.inputInvalidIdentAndVerifyErrorMsg(extRCMP.id, "abcde12345678", extRCMP.state, topErrorMes, errMsg_ExcMaxLeg); //Sara[6/18/2014] Can't enter more characters than the max length
		this.inputInvalidIdentAndVerifyErrorMsg(extRCMP.id, numExcMaxValue, extRCMP.state, topErrorMes, errMsg_ExcMax);
		this.inputInvalidIdentAndVerifyErrorMsg(extRCMP.id, extRCMP.identifierNum, extRCMP.state, topErrorMes, errMsg_ExistNum);
		
		// Verify CAF identifier type: empty number; less than min length; contains invalid characters; exceed max value; existing number; 
		this.generateErrMsgByIdentType(IDENT_TYPE_CAF);
		this.inputNumWithSpaceAndVerifyTrim(extCAF.id, numWithSpace);
		this.inputInvalidIdentAndVerifyErrorMsg(extCAF.id, StringUtil.EMPTY, extCAF.state, topErrorMes, identNumReqMes);
		this.inputInvalidIdentAndVerifyErrorMsg(extCAF.id, "1234", extCAF.state, topErrorMes, errMsg_LessMin);
		this.inputInvalidIdentAndVerifyErrorMsg(extCAF.id, "12^&*3", extCAF.state, topErrorMes, errMsg_InvalidChar);
		this.inputInvalidIdentAndVerifyErrorMsg(extCAF.id, "abc123", extCAF.state, topErrorMes, errMsg_InvalidChar);
		this.inputInvalidIdentAndVerifyErrorMsg(extCAF.id, numExcMaxValue, extCAF.state, topErrorMes, errMsg_ExcMax);
		this.inputInvalidIdentAndVerifyErrorMsg(extCAF.id, extCAF.identifierNum, extCAF.state, topErrorMes, errMsg_ExistNum);
		
		// Verify CF identifier type
		hf.gotoUpdateIdenPg(extCF.id);
		this.generateErrMsgByIdentType(IDENT_TYPE_FL);
		this.inputInvalidIdentAndVerifyErrorMsg(extCF.id, StringUtil.EMPTY, extCF.state, topErrorMes, identNumReqMes);
		this.inputInvalidIdentAndVerifyErrorMsg(extCF.id, "123", extCF.state, topErrorMes, errMsg_CFLFormat);
		this.inputInvalidIdentAndVerifyErrorMsg(extCF.id, "12^&*3", extCF.state, topErrorMes, errMsg_CFLFormat);
		this.inputInvalidIdentAndVerifyErrorMsg(extCF.id, "12345678.12ab", extCF.state, topErrorMes, errMsg_CFLFormat);
		this.inputInvalidIdentAndVerifyErrorMsg(extCF.id, numExcMaxValue, extCF.state, topErrorMes, errMsg_CFLFormat);
		this.inputInvalidIdentAndVerifyErrorMsg(extCF.id, extCF.identifierNum, extCF.state, topErrorMes, errMsg_ExistNum);
		// contact the call center link check
		this.verifyContactCallCenterLink();
		
		// Update account to age > 80 and verify update identifier
		hf.updateCustDOB(dob);
		hf.gotoUpdateIdenPg(extRCMP.id);
		this.inputInvalidIdentAndVerifyErrorMsg(extRCMP.id, "abcdefg", extRCMP.state, topErrorMes, IDENT_TYPE_RCMP + errMsg_InvalidAge);
		hf.gotoUpdateIdenPg(extCAF.id);
		this.inputInvalidIdentAndVerifyErrorMsg(extCAF.id, "88888", extCAF.state, topErrorMes, IDENT_TYPE_CAF + errMsg_InvalidAge);
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "updateidenvalidation@test.com"; // d_web_hf_signupaccount, id=660
		cus.password = "asdfasdf";
		cus.dateOfBirth = "1986-01-01";
		
		cusNew.email = "updateidenvalidation02@test.com";// d_web_hf_signupaccount, id=670
		cusNew.password = cus.password;
		
		// Identifiers Info
		extRCMP = this.generateIdentInfo(IDEN_RCMP_ID, IDENT_TYPE_NAME_RCMP, "RCMPUpd0", "Saskatchewan");
		validRCMP = this.generateIdentInfo(IDEN_RCMP_ID, IDENT_TYPE_NAME_RCMP, "RCMPUpd1", "Saskatchewan");
		extCAF = this.generateIdentInfo(IDEN_CAF_ID, IDENT_TYPE_NAME_CAF, "9999999", "Nunavut");
		validCAF = this.generateIdentInfo(IDEN_CAF_ID, IDENT_TYPE_NAME_CAF, "99999999", "Nunavut");
		extCF = this.generateIdentInfo(IDEN_FL_ID, IDENT_TYPE_NAME_FL, "12345678.0001", StringUtil.EMPTY);
		validCF = this.generateIdentInfo(IDEN_FL_ID, IDENT_TYPE_NAME_FL, "12345678.0002", StringUtil.EMPTY);

		dob = DateFunctions.getDateAfterGivenMonth(DateFunctions.getToday("yyyy-MM-dd"), -81 * 12);
		// Invalid Numbers and error messages
		numWithSpace = " 01050-- ";
		numExcMaxValue = "100000000000";
		topErrorMes = "We need you to correct or provide more information. Please see each marked section.";
		errMsg_ExcMaxLeg = extRCMP.identifierType +" must contain no more than 12 characters, excluding spaces and dashes.";
		errMsg_CFLFormat = IDENT_TYPE_FL + " must be in the format NNNNNNNN.NNNN.";
		errMsg_InvalidAge = " can only be used for Customers up to 80 years of age.";
		
	}

	private void inputInvalidIdentAndVerifyErrorMsg(String typeID, String num, String state, String... errMsg) {
		cus.identifier.id = typeID;
		cus.identifier.identifierNum = num;
		cus.identifier.state = state;
		hf.updateIdenFromUpdateIdentificationPg(cus.identifier);
		for (String err : errMsg) {
			updIdenPg.verifyErrorMsgExist(err, true);
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
	
	private void inputNumWithSpaceAndVerifyTrim(String typeID, String num) {
		hf.updateIdentifier(typeID, num, StringUtil.EMPTY, StringUtil.EMPTY);
		updIdenPg.verifyIdenNum(num.trim());
	}
	
	private void verifyContactCallCenterLink() {
		HFContactUsPage contactPg = HFContactUsPage.getInstance();
		updIdenPg.clickContactCallCenterLink();
		contactPg.waitLoading();
	}
	
	private CustomerIdentifier generateIdentInfo(String id, String type, String num, String state) {
		CustomerIdentifier ident = new CustomerIdentifier();
		ident.id = id;
		ident.identifierType = type;
		ident.identifierNum = num;
		ident.state = state;
		return ident;
	}

	private void addValidIdentifiers(CustomerIdentifier... idents) {
		hf.gotoUpdateProfilePg();
		for (CustomerIdentifier ident : idents) {
			if (!updAcctPg.checkIdenExisting(ident.identifierType)) 
				hf.addIdentifier(ident);
		}		
	}
}
