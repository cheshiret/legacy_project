/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf;

import com.activenetwork.qa.awo.pages.web.hf.HFCreateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;

/**
 * @Description: Check error message when sign up with date of birth in wrong format.
 * @Preconditions:
 * The configuration of the DOB format for SK is in property files. See TC 058433
 * The format for SK is yyyy-MM-dd as default
 * @SPEC: DOB check [TC:048827] 
 * @Task#: Auto-1483
 * 
 * @author Lesley Wang
 * @Date  Feb 27, 2013
 * Deactivate the case in DB due to it is covered by testCases.regression.advanced.web.account.hf.createanaccount.DateOfBirthTextInput
 */
public class SignUpWithInvalidDOB extends HFSKWebTestCase {
	private HFCreateAccountPage createAccPg = HFCreateAccountPage.getInstance();
	private String msg;
	public void execute() {
		hf.invokeURL(url);
		hf.gotoCreateAccountPage();
		
		logger.info("1. Sign up with invalid date of birth...");
		this.createAccountWithInvalidDOB("1986-02-30"); 
		createAccPg.verifyErrorMsgExist(msg, true);
		
		this.createAccountWithInvalidDOB("1900-00-15");
		createAccPg.verifyErrorMsgExist(msg, true);
		
		logger.info("2. Sign up with date in wrong format...");
		this.createAccountWithInvalidDOB("02/28/1986");
		createAccPg.verifyErrorMsgExist(msg, true);
		
		this.createAccountWithInvalidDOB("86/1/1");
		createAccPg.verifyErrorMsgExist(msg, true);
	}

	@Override
	public void wrapParameters(Object[] param) {
		msg = "Date of Birth \\(YYYY-MM-DD\\) entered is invalid.\\s*Please enter a date in the format YYYY-MM-DD.";
	}

	private void createAccountWithInvalidDOB(String date) {
		createAccPg.setDateOfBirth(date);
		createAccPg.clickCreateAccount();
		createAccPg.waitLoading();
	}
}
