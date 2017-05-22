/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.hf;

import com.activenetwork.qa.awo.pages.web.hf.HFSignInPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;

/**
 * @Description: Check Sign in Page UI
 * @Preconditions:
 * @SPEC: check sign in page [TC:048829]
 * @Task#: Auto-1482
 * 
 * @author Lesley Wang
 * @Date  Feb 27, 2013
 */
public class SignInPageUICheck extends HFSKWebTestCase {
	private HFSignInPage signInPg = HFSignInPage.getInstance();
	private String pageTitle, formTitle, label_userName, label_pw;
	
	@Override
	public void execute() {
		hf.invokeURL(url);
		hf.gotoSignInPage();
		signInPg.verifyPageTitle(pageTitle);
		signInPg.verifySignUpNowLinkExist();
		signInPg.verifySignInFormTitle(formTitle);
		signInPg.verifyUserNameLabel(label_userName);
		signInPg.verifyPasswordLabel(label_pw);
		signInPg.verifyForgotPasswordLinkExist();
	}

	@Override
	public void wrapParameters(Object[] param) {
		pageTitle = "Member Sign In";
		formTitle = "Registered Member";
		label_userName = "User Name (email)";
		label_pw = "Password";
	}
}
