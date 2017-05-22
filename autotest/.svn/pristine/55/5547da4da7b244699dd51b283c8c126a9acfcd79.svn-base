/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.testapi.ErrorOnPageException;


/**
 * @Description: HF Web Member Sign in page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Feb 25, 2013
 */
public class HFSignInPage extends HFHeaderBar {
	private static HFSignInPage _instance = null;

	public static HFSignInPage getInstance() {
		if (null == _instance)
			_instance = new HFSignInPage();

		return _instance;
	}
	
	protected HFSignInPage() {
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "signinbtn");
	}
	
	/** Set email address */
	public void setEmail(String email) {
		browser.setTextField(".id", "userid", email);
	}

	/** Set password */
	public void setPassword(String pw) {
		browser.setTextField(".id", "pw", pw);
	}

	/** Click Sign in button */
	public void clickSignInButton() {
		browser.clickGuiObject(".id", "signinbtn");
	}

	/** Get page title */
	public String getPageTitle() {
		return browser.getObjectText(".id", "pagetitle");
	}

	/** Verify page title */
	public void verifyPageTitle(String exp) {
		String actual = this.getPageTitle();
		this.verifyResults("Sign In page title", exp, actual);
	}

	/** Get the text displayed on the top of the sign in form */
	public String getSignInFormTitle() {
		return browser.getObjectText(".class", "HTML.DIV", ".classname", "contenthdr");
	}
	
	/** Verify the text displayed on the top of the sign in form*/
	public void verifySignInFormTitle(String exp) {
		String actual = this.getSignInFormTitle();
		this.verifyResults("Sign In form title",	 exp, actual);
	}

	/** Get the label for User Name */
	public String getUserNameLabel() {
		return browser.getObjectText(".class", "Html.Label", ".for", "userid");
	}

	/** Verify the label for User Name */
	public void verifyUserNameLabel(String exp) {
		String actual = this.getUserNameLabel();
		this.verifyResults("Sign In User Name lable",	exp, actual);
	}

	/** Get the label for Password */
	public String getPasswordLabel() {
		return browser.getObjectText(".class", "Html.Label", ".for", "pw");
	}

	/** Verify the label for Password */
	public void verifyPasswordLabel(String exp) {
		String actual = this.getPasswordLabel();
		this.verifyResults("Sign In Password lable",	exp, actual);
	}
	
	private void verifyResults(String msg, String exp, String actual) {
		if (!actual.equals(exp)) {
			throw new ErrorOnPageException(msg + " is wrong!", exp, actual);
		}
		logger.info(msg + " is correct as " + exp);
	}

	/** Check if the link Sign Up Now exists */
	public boolean isSignUpNowLinkExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Sign Up Now!");
	}
	
	/** Verify the link Sign Up Now exists */
	public void verifySignUpNowLinkExist() {
		if (!this.isSignUpNowLinkExist()) {
			throw new ErrorOnPageException("The Sign Up Now link should exist!");
		}
		logger.info("The Sign Up Now link exists!");
	}
	
	/** Check if the link Forgot Your Password exists */
	public boolean isForgotPasswordLinkExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Forgot your Password?");
	}
	
	public void clickForgetPasswordLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Forgot your Password?");
	}
	
	/** Verify the link Forgot Your Password exists */
	public void verifyForgotPasswordLinkExist() {
		if (!this.isForgotPasswordLinkExist()) {
			throw new ErrorOnPageException("The Forgot password link should exist!");
		}
		logger.info("The Forgot password link exists!");
	}
	
	/** Get the error message on top of page */
	public String getTopErrorMsg() {
		return browser.getObjectText(".class", "Html.DIV", ".classname", "msg topofpage error");
	}

	/** Verify the error message on top of page */
	public void verifyTopErrorMsg(String expMsg) {
		String actual = this.getTopErrorMsg();
		this.verifyResults("Top message", expMsg, actual);
	}

	/** Get error message */
	public String getErrorMsg() {
		return browser.getObjectText(".class", "Html.DIV", ".classname", "msg error");
	}

	/** Verify error message */
	public void verifyErrorMsg(String expMsg) {
		String actual = this.getErrorMsg();
		this.verifyResults("Error message", expMsg, actual);
	}
}
