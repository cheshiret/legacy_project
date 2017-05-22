/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.maintenanceapps;

import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.page.HtmlMainPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: it is for the web maintenance application sign in page. 
 * Web maintenance applications include Facility Photo Manager (Photo Tool), Marketing Spot Manager and Admin.do
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Dec 11, 2012
 */
public class WebMaintenanceAppSignInPage extends HtmlMainPage {
	public final String topMsg_emptyValue = "We need you to correct or provide more information. Please see each marked field.";
	public final String topMsg_invalidValue ="Invalid User Name and/or Password. Please try again.";
	public final String topMsg_invalidRole = "There is a problem with your account. Please contact your ORMS administrator."; 
	public final String userNameLabel = "User Name";
	public final String pwLabel = "Password";
	public final String fieldMsg_username = "User Name is required"; 
	public final String fieldMsg_pw = "Password is required";
	public final String topMsg_pwExpired = "Your password expired. Please change your ORMS password and try again.";
	public final String headerText1 = "ORMS authentication is required to view the page you requested.";
	public final String headerText2 = "Please sign in with your ORMS user name and password:";
	
	private static WebMaintenanceAppSignInPage _instance = null;

	public static WebMaintenanceAppSignInPage getInstance() {
		if (null == _instance)
			_instance = new WebMaintenanceAppSignInPage();

		return _instance;
	}
	
	protected WebMaintenanceAppSignInPage() {
	}
	
	/* (non-Javadoc)
	 * @see testAPI.interfaces.IPage#exists()
	 */
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "submit", ".text", "Log In");
	}
	
	public void setUserName(String userName) {
		browser.setTextField(".id", "usernameid", userName);
	}
	
	public String getUserName() {
		return browser.getTextFieldValue(".id", "usernameid");
	}
	
	public void setPassword(String pw) {
		browser.setPasswordField(".id", "passwordid", pw);
	}
	
	public void clearPassword() {
		IHtmlObject[] objs = browser.getPasswordField(".id", "passwordid");
		((IText)objs[0]).clear();
		Browser.unregister(objs);
	}
	
	public void clickLogIn() {
		browser.clickGuiObject(".id", "submit");
	}
	
	/**
	 * Input the user name and password, and then click Log In button
	 * @param userName
	 * @param pw
	 */
	public void signIn(String userName, String pw) {
		setUserName(userName);
		clearPassword();
		setPassword(pw);
		clickLogIn();
	}

	/**
	 * Get the error message on the top of the page
	 * @return
	 */
	public String getTopErrorMessage() {
		return browser.getObjectText(".class", "Html.DIV", ".classname", "msg error");
	}
	
	/**
	 * Get the error message displayed above user name or password text field
	 * @param fieldLable
	 * @return
	 */
	public String getFieldErrorMessage(String fieldLable) {
		return browser.getObjectText(Property.toPropertyArray(".class", "Html.DIV", ".classname", "msg error", 
				".text", new RegularExpression("^" + fieldLable + ".*", false)));
	}
	
	/**
	 * Check if there is a error message displayed above user name or password text field
	 * @return
	 */
	public boolean isFieldErrorMessageExist() {
		return browser.checkHtmlObjectExists(Property.atList(
				Property.toPropertyArray(".id", "loginForm"), 
				Property.toPropertyArray(".class", "Html.DIV", ".classname", "msg error")));
	}
	
	public String getPageAllText() {
		return browser.getObjectText(".class", "Html.DIV", ".classname", "loginContainer");
	}
	
	/**
	 * Verify the sign in page UI
	 */
	public void verifySignInPgUI(String userName, String... texts) {
		boolean result = true;
		// Verify user name text field value
		result &= MiscFunctions.compareString("user name", userName, this.getUserName());
		
		// Verify header text
		String allText = this.getPageAllText();
		for (String s : texts) {
			if (!allText.contains(s)) {
				result &= false;
				logger.error("the header text on sign in page is wrong! " + allText + " should contain " + s);
			}
		}
		
		if (!result) {
			throw new ErrorOnPageException("Sign in Page UI is wrong! Check logger error!");
		}
		logger.info("Verify sign in page UI correctly!");
	}
	
	/**
	 * Compare top error message when select invalid role/location
	 */
	public boolean compareTopErrMessage(String expTopMsg) {
		String actTopMsg = this.getTopErrorMessage();	
		return MiscFunctions.compareString("Top Error Messsage", expTopMsg, actTopMsg);
	}
	
	/**
	 * Compare field error message when not select any role/location
	 */
	public boolean compareFieldErrMessage(String errFieldLabel, String expFieldMsg) {
		String actFieldMsg = this.getFieldErrorMessage(errFieldLabel);
		return MiscFunctions.compareString("Field Error Message", expFieldMsg, actFieldMsg);
	}
	
	/**
	 * Compare field error message exist
	 * @param expected
	 * @return
	 */
	public boolean compareFieldErrMsgExist(boolean expected) {
		String msg = expected ? " " : " not ";
		if (this.isFieldErrorMessageExist() != expected) {
			logger.error("Field Error Message should" + msg + "exist on the page!");
			return false;
		} else {
			logger.info("Field Error Message does" + msg + "exist on the page!");
			return true;
		}
	}
	
	/**
	 * Verify top error message
	 */
	public void verifyTopErrMsg(String expMsg, String logMsg) {
		if (!this.compareTopErrMessage(expMsg)) {
			throw new ErrorOnPageException(logMsg + " is wrong! Check log error above!"); 
		}
		logger.info(logMsg + " is correct!");
	}
}
