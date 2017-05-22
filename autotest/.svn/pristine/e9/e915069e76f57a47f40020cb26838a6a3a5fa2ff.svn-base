/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFCreateAccountPage;
import com.activenetwork.qa.awo.pages.web.hf.HFUpdateAccountPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: (P) Check error messages when input: 
 * 1. email address with wrong format, 2. email with spacing, 3. existing email
 * @Preconditions:
 * @SPEC:
 * Email Address format check [TC:048815]
 * Email Address trim space check [TC:048816] 
 * Email Address unique check [TC:048817] 
 * Customer Profile - Email Address and PassWord [TC:044123], only email address part
 * @Task#: Auto-1483
 * 
 * @author Lesley Wang, Sara Wang
 * @Date  Feb 26, 2013, July 16, 2013
 */
public class SignUpWithInvalidEmail extends HFSKWebTestCase {
	private HFCreateAccountPage createAccPg = HFCreateAccountPage.getInstance();
	private HFUpdateAccountPage updateAccountPg = HFUpdateAccountPage.getInstance();
	private String msg, msg2;
	public void execute() {
		
		if(hf.isSignInWorkFlows(url)){
			hf.gotoCreateAccountPage();
		}else{
			hf.invokeURL(url);
			hf.lookupAccount(cus);
			hf.gotoCreateAccountPgFromAccountLookupPg();
		}

		logger.info("1. Sign up with email in wrong format...");
		this.createAccountWithInvalidEmail("abc");
		createAccPg.verifyErrorMsgExist(msg, true);

		this.createAccountWithInvalidEmail("1abc@");
		createAccPg.verifyErrorMsgExist(msg, true);

		this.createAccountWithInvalidEmail("@test.com");
		createAccPg.verifyErrorMsgExist(msg, true);

		this.createAccountWithInvalidEmail(".test@test.com.");
		createAccPg.verifyErrorMsgExist(msg, true);

		this.createAccountWithInvalidEmail("test!#$%@test.com");
		createAccPg.verifyErrorMsgExist(msg, true);

		logger.info("2. Sign up with email containing leading and trailing space...");
		this.createAccountWithInvalidEmail(cus.email);
		cus.email = cus.email.trim();
		createAccPg.verifyErrorMsgExist(msg, false);
		createAccPg.verifyEmailAddress(cus.email.trim());

		if(hf.isSignInWorkFlows(url)){
			logger.info("3. Sign up with an existing email...");
			if (!hf.checkLoginNameExists(schema, cus.email)) {
				logger.info("The email:" + cus.email + " doesn't exist. Go to sign up a new account with the email...");
				hf.signUpNewAccount(cus);
				hf.signOut();
				hf.deleteCustIden(schema, cus.identifier.identifierTypeID, cus.email);
			}
			hf.signUpNewAccount(cus);
			createAccPg.verifyErrorMsgExist(msg2, true);
		}

		logger.info("4. The email address with pre-populated value of logged in user.");
		if(hf.isSignInWorkFlows(url)){
			hf.signIn(cusNew.email, cusNew.password);
		}else{
			hf.lookupAccount(cusNew);
			hf.gotoAccountOverviewPgFromYourAccountFoundPg();
		}
		hf.gotoUpdateProfilePg();
		updateAccountPg.verifyEmail(cusNew.email);
	}

	@Override
	public void wrapParameters(Object[] param) {
		msg = "The email address you provided is not valid.";
		msg2 = "The email address you have entered is already in our system.\\s*If you've ever made a purchase by phone we may already have a Web Account waiting for you. To retrieve an existing Web Account, use Send My Password.";

		cus.email = "    signupwithwrongemail@hfwebtest.com    ";
		cus.password = "asdfasdf";
		cus.retypePassword = cus.password;
		cus.setDefaultValuesForHFWebSignUp();
		cus.identifier.identifierTypeID = OrmsConstants.IDEN_SKDL_ID;
		cus.identifier.identifierType = OrmsConstants.IDENT_TYPE_NAME_SKDL;
		cus.identifier.identifierNum = new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		
		cusNew.email = "edudeclare04@test.com";
		cusNew.password = TestProperty.getProperty("web.login.pw");
		cusNew.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cusNew.identifier.identifierType = IDENT_TYPE_RCMP;
		cusNew.identifier.identifierNum = "1R9Y4x4152";
		cusNew.identifier.state = "Ontario";

	}

	private void createAccountWithInvalidEmail(String email) {
		createAccPg.setEmailAddress(email);
		createAccPg.clickCreateAccount();
		createAccPg.waitLoading();
	}
}
