/*
 * $Id: UwpSignInPage.java 6822 2008-12-02 16:48:26Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;

/**
 * @author jchen
 */
public class UwpSignInPage extends UwpPage {
	private static UwpSignInPage _instance = null;

	public static UwpSignInPage getInstance() {
		if (null == _instance)
			_instance = new UwpSignInPage();

		return _instance;
	}

	protected UwpSignInPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "signinbtn");
	}
	
	/**
	 * Fill in user name.
	 * @param user - user account
	 */
	public void setUserName(String user) {
		browser.setTextField(".id", "userid", user, true);
	}
	/**
	 * Fill in password.
	 * @param pwd - password
	 */
	public void setPassword(String pwd) {
		browser.setPasswordField(".id", "pw", pwd, true);
	}
	/**
	 * Click on Sign In button.
	 */
	public void clickSignIn() {
		browser.clickGuiObject(".id", "signinbtn", true);
	}
	
	/**
	 * Click on Sign In button.
	 */
	public void clickAgencySignIn() {
		browser.clickGuiObject(".class", "Html.A",".text","Agency Sign In");
	}
	
	/**
	 * Fill in sign in info.
	 * @param user - user name
	 * @param pwd - password
	 */
	public void signIn(String user, String pwd) {
		this.setUserName(user);
		this.setPassword(pwd);
		this.clickSignIn();
	}
	
	/** Verify user can not log in when using a wrong account. */
	public boolean verifySignInFailed(){
	  	boolean toReturn=false;

	  	Property[] p1 = Property.concatPropertyArray(div(), ".id", "signin_wrapper");
		Property[] p2 = Property.concatPropertyArray(div(), ".className", "msg topofpage error");
		Property[] p3 = Property.concatPropertyArray(form(), ".id", "signinform");
		Property[] p4 = Property.concatPropertyArray(div(), ".className", "msg error");
		
	  	IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2)); //browser.getHtmlObject(".id","signinform");
	  	if(objs.length<1){
	  		objs = browser.getHtmlObject(Property.atList(p3, p4));
	  		if(objs.length<1) throw new ObjectNotFoundException("Fan't find mes error objects in sign in page.");
	  	}
		
	  	String content=objs[0].getProperty(".text");
	  	if(!content.matches(".*Unable to sign in.*") && !content.matches(".*Forgot your Password.*")){
	  	  	throw new ErrorOnPageException("Unknown error.");
	  	}else
	  	  	toReturn=true;
	  	
	  	Browser.unregister(objs);
	  	return toReturn;
	}
	
	public void clickSignUpNowLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Sign Up Now!");
	}
}
