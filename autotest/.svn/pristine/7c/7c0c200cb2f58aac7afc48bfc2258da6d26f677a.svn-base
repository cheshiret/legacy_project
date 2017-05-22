/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf;

import java.text.DecimalFormat;
import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.hf.HFSignInPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;

/**
 * @Description: Check error messages when sign in with invalid email and password
 * @Preconditions:
 * @SPEC: 
 * Sign in - wrong account [TC:048832] 
 * @Task#: Auto-1482
 * 
 * @author Lesley Wang
 * @Date  Feb 27, 2013
 */
public class SignInWithInvalidEmailAndPw extends HFSKWebTestCase {
	private HFSignInPage signInPg = HFSignInPage.getInstance();
	private String topMsg, errMsg;
	
	@Override
	public void execute() {
		hf.invokeURL(url);
		
		if (!hf.checkLoginNameExists(schema, cus.email)) {
			logger.info("The email:" + cus.email + " doesn't exist. Go to sign up a new account with the email...");
			hf.signUpNewAccount(url, cus);
			hf.signOut();	
			hf.deleteCustIden(schema, cus.identifier.identifierTypeID, cus.email);
		}
		
		logger.info("1. Sign in with a wrong account...");
		hf.signIn("test", cus.password);
		signInPg.verifyTopErrorMsg(topMsg);
		signInPg.verifyErrorMsg(errMsg);	

		logger.info("2. Sign in with a wrong password...");
		hf.signIn(cus.email, "test");
		signInPg.verifyTopErrorMsg(topMsg); 
		signInPg.verifyErrorMsg(errMsg);	
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "signinwithinvalid@hfwebtest.com";
		cus.password = "asdfasdf";
		cus.retypePassword = cus.password;
		cus.setDefaultValuesForHFWebSignUp();
		cus.identifier.identifierTypeID = OrmsConstants.IDEN_SKDL_ID;
		cus.identifier.identifierType = OrmsConstants.IDENT_TYPE_NAME_SKDL;
		cus.identifier.identifierNum = new DecimalFormat("0000000").format(new Random().nextInt(9999999));
		cus.identifier.state = "Saskatchewan";
		
		topMsg = "Unable to sign in.";
		errMsg = "Please verify your information or click the link \"Forgot your Password?\" to generate a new password.";
	}

}
