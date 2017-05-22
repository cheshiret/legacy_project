package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;

public class AgencySignInPage extends UwpPage {
	private static AgencySignInPage _instance = null;

	public static AgencySignInPage getInstance() {
		if (null == _instance)
			_instance = new AgencySignInPage();

		return _instance;
	}

	protected AgencySignInPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".className", "formSubmitButton");
	}
	
	/**
	 * Fill in user name.
	 * @param user - user account
	 */
	public void setUserName(String user) {
		browser.setTextField(".id", "userName", user, true);
	}
	
	/**
	 * Fill in password.
	 * @param pwd - password
	 */
	public void setPassword(String pwd) {
		browser.setPasswordField(".id", "password", pwd, true);
	}
	
	/**
	 * select environment type.
	 * @param type
	 */
	public void selectEnvType(String type) {
		browser.selectDropdownList(".id", "envType", type);
	}
	
	/**
	 * Click on OK button to Sign In.
	 */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A",".text","OK");
	}
	
	/**Click on Member Sign In button*/
	public void clickMemberSignIn() {
		browser.clickGuiObject(".class", "Html.A",".text","Member Sign In");
	}
	
	/**
	 * Fill in sign in info.
	 * @param user - user name
	 * @param pwd - password
	 * @param envType - environment type
	 */
	public void agencySignIn(String user, String pwd, String envType) {
		this.setUserName(user);
		this.setPassword(pwd);
		if(null != envType && envType.length()>0) {
			this.selectEnvType(envType);
		}
		this.clickOK();
	}
	
	/** Verify user can not log in when using a wrong account. */
	public boolean verifySignInFailed(){
	  	boolean toReturn=false;
	  	IHtmlObject[] objs = browser.getHtmlObject(".id","statusMessages");
	  	
	  	String content=objs[0].getProperty(".text");
	  	System.out.println(content);
	  	
	  	if(!content.equals("Incorrect user name and/or password.")){
	  	  	throw new ErrorOnPageException("unknown error.");
	  	}else
	  	  	toReturn=true;
	  	
	  	Browser.unregister(objs);
	  	return toReturn;
	}
}
