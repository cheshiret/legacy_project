/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf;

import com.activenetwork.qa.awo.pages.web.hf.HFCreateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;

/**
 * @Description: Check error messages when:
 * 1. input invalid password, length less than or greater than the required.
 * 2. input the password containing illegal character
 * 3. input the password containing login name, or name
 * 4. retype password is different with password
 * @Preconditions:
 * @SPEC:
 * Password check [TC:048818] 
 * Password illegal character [TC:048819] 
 * insecure password [TC:048824] 
 * retype password null [TC:048825] 
 * retype password different [TC:048826]
 * @Task#: Auto-1483
 * 
 * @author Lesley Wang
 * @Date  Feb 26, 2013
 */
public class SignUpWithInvalidPassword extends HFSKWebTestCase {
	private HFCreateAccountPage createAccPg = HFCreateAccountPage.getInstance();
	private String msg, msg2, msg3, msg4;
	public void execute() {
		hf.invokeURL(url);
		hf.gotoCreateAccountPage();
		
		logger.info("1. Sign up with invalid password...");
		this.createAccountWithInvalidPw("abcde"); // password length less than 6
		createAccPg.verifyErrorMsgExist(msg, true);
		
		this.createAccountWithInvalidPw("abc1abc2ab@3ab#4qwe5YU^6KO:7%L)89");// password length greater than 32
		createAccPg.verifyErrorMsgExist(msg, true);
		
		logger.info("2. Sign up with password containing illegal character...");
		this.createAccountWithInvalidPw("æ´»è·ƒç½‘ç»œAWO");
		createAccPg.verifyErrorMsgExist(msg2, true);
		
		logger.info("3. Sign up with password containing email, first name, last name...");
		createAccPg.setEmailAddress(cus.email);
		this.createAccountWithInvalidPw(cus.email);
		createAccPg.verifyErrorMsgExist(msg3, true);
		
		createAccPg.setFirstName(cus.fName);	
		createAccPg.setLastName(cus.lName);	
		this.createAccountWithInvalidPw(cus.lName + cus.fName);
		createAccPg.verifyErrorMsgExist(msg3, true);
		
		logger.info("4. Sign up with a different retype password...");
		createAccPg.setRetypePassword(cus.retypePassword);
		this.createAccountWithInvalidPw(cus.password);
		createAccPg.verifyErrorMsgExist(msg4, true);
	}

	@Override
	public void wrapParameters(Object[] param) {
		msg = "Password is invalid. Please enter a Password between 6 and 32 characters.\\s*";
		msg2 = "Invalid password. Your password must contain between 6 and 32 standard keyboard characters in length, containing no spaces. Please change your password to match these requirements.\\s*";
		msg3 = "Invalid password. Your password must not contain your name or email address. Please change your password to match these requirements.\\s*";
		msg4 = "Your passwords do not match. Please re-enter your password to match the original entry.\\s*";
		
		cus.email = "wrongpw@hfwebtest.com";
		cus.fName = "hf_fname";
		cus.lName = "hf_lname";
		cus.password = "asdfasdf";
		cus.retypePassword = "asdf";
	}

	private void createAccountWithInvalidPw(String pw) {
		createAccPg.clearPassword();
		createAccPg.setPassword(pw);
		createAccPg.clickCreateAccount();
		createAccPg.waitLoading();
	}
}
