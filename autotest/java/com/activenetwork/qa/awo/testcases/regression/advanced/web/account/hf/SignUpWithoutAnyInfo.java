/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf;

import com.activenetwork.qa.awo.pages.web.hf.HFCreateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description: Check error messages on Create new account page when sign up without any input
 * @Preconditions:
 * @SPEC: information mandatory check [TC:048814] 
 * @Task#: Auto-1483
 * 
 * @author Lesley Wang
 * @Date  Feb 26, 2013
 */
public class SignUpWithoutAnyInfo extends HFSKWebTestCase {
	private String topMsg;
	private HFCreateAccountPage createAccPg = HFCreateAccountPage.getInstance();
	
	@Override
	public void execute() {
		hf.signUpNewAccount(url, cus);
		createAccPg.verifyErrorMsgExist(topMsg, true);
		this.verifyFieldRequiredErrMsg();
	}

	@Override
	public void wrapParameters(Object[] param) {
		topMsg = "We need you to correct or provide more information. Please see each marked section.";
		cus.identifier.identifierType = hf.getIdenTypeName(schema, IDEN_SKDL_ID, false, false);//false, false
		cus.identifier.identifierNum = "011111111"; // customer info only used to go to sign up page in Identification mode 
		
		cus.dateOfBirth = "1980-12-31"; 
	}

	private void verifyFieldRequiredErrMsg() {
		boolean result = true;
		if (hf.isSignInWorkFlows(url)) {
			result &= MiscFunctions.compareResult("The display of Error message for " + createAccPg.FIELDNAME_EMAIL, true, createAccPg.isInfoRequiredErrorMsgExist(createAccPg.FIELDNAME_EMAIL));
			result &= MiscFunctions.compareResult("The display of Error message for " + createAccPg.FIELDNAME_PW, true, createAccPg.isInfoRequiredErrorMsgExist(createAccPg.FIELDNAME_PW));
			result &= MiscFunctions.compareResult("The display of Error message for " + createAccPg.FIELDNAME_REPW, true, createAccPg.isInfoRequiredErrorMsgExist(createAccPg.FIELDNAME_REPW));
		} 
		result &= MiscFunctions.compareResult("The display of Error message for " + createAccPg.FIELDNAME_FNAME, true, createAccPg.isInfoRequiredErrorMsgExist(createAccPg.FIELDNAME_FNAME));
		result &= MiscFunctions.compareResult("The display of Error message for " + createAccPg.FIELDNAME_LNAME, true, createAccPg.isInfoRequiredErrorMsgExist(createAccPg.FIELDNAME_LNAME));
//		result &= MiscFunctions.compareResult("The display of Error message for " + createAccPg.FIELDNAME_DOB, true, createAccPg.isInfoRequiredErrorMsgExist(createAccPg.FIELDNAME_DOB + " \\(YYYY-MM-DD\\)"));
		result &= MiscFunctions.compareResult("The display of Error message for " + createAccPg.FIELDNAME_GENDER, true, createAccPg.isInfoRequiredErrorMsgExist(createAccPg.FIELDNAME_GENDER));
		result &= MiscFunctions.compareResult("The display of Error message for " + createAccPg.FIELDNAME_EYECOLOR, true, createAccPg.isInfoRequiredErrorMsgExist(createAccPg.FIELDNAME_EYECOLOR));
		result &= MiscFunctions.compareResult("The display of Error message for " + createAccPg.FIELDNAME_HAIRCOLOR, true, createAccPg.isInfoRequiredErrorMsgExist(createAccPg.FIELDNAME_HAIRCOLOR));
		result &= MiscFunctions.compareResult("The display of Error message for " + createAccPg.FIELDNAME_HEIGHT, true, createAccPg.isInfoRequiredErrorMsgExist(createAccPg.FIELDNAME_HEIGHT));
		result &= MiscFunctions.compareResult("The display of Error message for " + createAccPg.FIELDNAME_ZIP, true, createAccPg.isInfoRequiredErrorMsgExist(createAccPg.FIELDNAME_ZIP));
		result &= MiscFunctions.compareResult("The display of Error message for " + createAccPg.FIELDNAME_STREET, true, createAccPg.isInfoRequiredErrorMsgExist(createAccPg.FIELDNAME_STREET));
		result &= MiscFunctions.compareResult("The display of Error message for " + createAccPg.FIELDNAME_CITY, true, createAccPg.isInfoRequiredErrorMsgExist(createAccPg.FIELDNAME_CITY));
		result &= MiscFunctions.compareResult("The display of Error message for " + createAccPg.FIELDNAME_HPHONE, true, createAccPg.isInfoRequiredErrorMsgExist("At least one phone number"));
		
		if (!result) {
			throw new ErrorOnPageException("Field Required Error messages are wrong! Check logger error!");
		}
		logger.info("Field Required Error Messages are correct!");
	}
}
