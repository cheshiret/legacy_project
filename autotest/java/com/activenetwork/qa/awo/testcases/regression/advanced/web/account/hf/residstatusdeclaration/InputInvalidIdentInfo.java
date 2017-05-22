/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf.residstatusdeclaration;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.pages.web.hf.HFResidencyStatusDeclarationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify the messages when input invalid identifier info on Residency Status Declaration page
 * @Preconditions: Make sure the two identifier types are set by SQL:
 * RMCP:Max_value=999999999; Min_len=3; Max_len=12; Max_age=80
 * CAF: Numeric_only_ind=1; Max_value=999999999; Max_age=80
 * @SPEC: 
 * (Web) Residency Status Declaration page - Identifier Validation (Update Identifier) [TC:063021]
 * (Web) Residency Status Declaration page - Identifier Validation (Add Identifier) [TC:062242]
 * @Task#: Auto-1623
 * 
 * @author Lesley Wang
 * @Date  Apr 18, 2013
 */
public class InputInvalidIdentInfo extends HFSKWebTestCase {

	private HFResidencyStatusDeclarationPage resStatusPg = HFResidencyStatusDeclarationPage.getInstance();
	private String identNum, dob, topErrMsg, errMsg_LessLen, errMsg_LessLen2, errMsg_WithLetters, 
		errMsg_invalidAge, errMsg_exceedLen, errMsg_exceedLen2, errMsg_Exist;
	
	@Override
	public void execute() {
		hf.deleteCustIden(schema, cusNew.identifier.identifierTypeID, cusNew.email);
	
		// Add a CAF identifier firstly for another customer
		hf.signIn(url, cusNew.email, cusNew.password);		
		hf.gotoResidStatusDeclaPg();	
		hf.selectResidStatusToPrdCategoryPg(RESID_STATUS_SK, cusNew.identifier);
		hf.signOut();
		
		hf.signIn(url, cus.email, cus.password);		
		
		// Input invalid identifier number in Residency Status Declaration page and verify error messages
		hf.gotoResidStatusDeclaPg();	
		cus.identifier.identifierNum = "12";
		hf.selectResidStatusToPrdCategoryPg(RESID_STATUS_SK, cus.identifier);
		boolean result = MiscFunctions.matchString("Top error message", resStatusPg.getTopErrorMsg(), topErrMsg);
		result &= MiscFunctions.matchString("Identifier Number error message when less than default min length", 
				resStatusPg.getIdentNumErrorMsg(RESID_STATUS_SK, IDENT_TYPE_CAF), 
				IDENT_TYPE_CAF + errMsg_LessLen);
		
		cus.identifier.identifierNum = "AB1234";
		hf.selectResidStatusToPrdCategoryPg(RESID_STATUS_SK, cus.identifier);
		result &= MiscFunctions.matchString("Identifier Number error message when containing letters", 
				resStatusPg.getIdentNumErrorMsg(RESID_STATUS_SK, IDENT_TYPE_CAF), 
				IDENT_TYPE_CAF + errMsg_WithLetters);
		
		cus.identifier.identifierNum = "4567981234";
		hf.selectResidStatusToPrdCategoryPg(RESID_STATUS_SK, cus.identifier);
		result &= MiscFunctions.matchString("Identifier Number error message when more than default max length", 
				resStatusPg.getIdentNumErrorMsg(RESID_STATUS_SK, IDENT_TYPE_CAF), 
				IDENT_TYPE_CAF + errMsg_exceedLen);
		
		// Update customer age to 80
		hf.updateCustDOB(dob);	
		hf.gotoResidStatusDeclaPg();	
		cus.identifier.identifierNum = "45679";
		hf.selectResidStatusToPrdCategoryPg(RESID_STATUS_SK, cus.identifier);
		result &= MiscFunctions.matchString("Identifier Number error message when older than 80", 
				resStatusPg.getIdentNumErrorMsg(RESID_STATUS_SK, IDENT_TYPE_CAF), 
				IDENT_TYPE_CAF + errMsg_invalidAge);
		hf.updateCustDOB(cus.dateOfBirth);
		
		hf.gotoResidStatusDeclaPg();	
		cus.identifier.identifierNum = identNum;
		hf.selectResidStatusToPrdCategoryPg(RESID_STATUS_SK, cus.identifier);
		result &= MiscFunctions.matchString("Identifier Number error message when same as another customer", 
				resStatusPg.getIdentNumErrorMsg(RESID_STATUS_SK, IDENT_TYPE_CAF), 
				IDENT_TYPE_CAF + errMsg_Exist);
		
		cus.identifier.identTypeShortNm = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "12";
		hf.selectResidStatusToPrdCategoryPg(RESID_STATUS_SK, cus.identifier);
		result &= MiscFunctions.matchString("Identifier Number error message when less than min length set in DB", 
				resStatusPg.getIdentNumErrorMsg(RESID_STATUS_SK, IDENT_TYPE_RCMP), 
				IDENT_TYPE_RCMP + errMsg_LessLen2);
		
		cus.identifier.identifierNum = "1111111111111";
		hf.selectResidStatusToPrdCategoryPg(RESID_STATUS_SK, cus.identifier);
		result &= MiscFunctions.matchString("Identifier Number error message when less than min length set in DB", 
				resStatusPg.getIdentNumErrorMsg(RESID_STATUS_SK, IDENT_TYPE_RCMP), 
				IDENT_TYPE_RCMP + errMsg_exceedLen2);
		
		if (!result) {
			throw new ErrorOnPageException("Identifier number Error Messages are wrong! Check error log.");
		}
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SK";
		cus.email = "inputinvaliddentinfo01@test.com"; // with SK address
		cus.password = "asdfasdf";
		cus.residencyStatus = RESID_STATUS_SK;
		cus.identifier.identifierType = IDENT_TYPE_CAF;
		cus.identifier.state = "Quebec";
		cus.identifier.isDeclarRequired = true;
		cus.dateOfBirth = "1986-01-05";
		
		cusNew.email = "inputinvaliddentinfo02@test.com"; // with SK address
		cusNew.password = "asdfasdf";
		cusNew.identifier.identifierType = IDENT_TYPE_CAF;
		cusNew.identifier.identifierTypeID = IDEN_CAF_ID;
		identNum = new DecimalFormat("00000").format(new Random().nextInt(9999));
		cusNew.identifier.identifierNum = identNum;
		cusNew.identifier.state = "Quebec";
		cusNew.identifier.isDeclarRequired = true;
		
		dob = DateFunctions.getDateAfterGivenMonth(DateFunctions.getToday("yyyy-MM-dd"), -81 * 12);
		topErrMsg = ".*correct or provide more information.*see each marked section.*";
		errMsg_LessLen = " must contains at least 5 characters, excluding spaces and dashes.";
		errMsg_LessLen2 = " must contains at least 3 characters, excluding spaces and dashes.";
		errMsg_WithLetters = " must only contain numbers, embedded spaces or a dash.";
		errMsg_invalidAge = " can only be used for Customers up to 80 years of age.";
		errMsg_exceedLen = " is invalid.";
		errMsg_exceedLen2 = " must contain no more than 12 characters, excluding spaces and dashes.";
		errMsg_Exist = " with the same number is used by another account.*contact the call center.*";
	}
}
