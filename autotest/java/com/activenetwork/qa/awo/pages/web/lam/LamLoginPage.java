package com.activenetwork.qa.awo.pages.web.lam;


public class LamLoginPage extends LamTopMenuBar {
  	private static LamLoginPage _instance=null;
  	
  	public static LamLoginPage getInstance() {
  	  	if(null==_instance) {
  	  	  	_instance=new LamLoginPage();
  	  	}
  	  	
  	  	return _instance;
  	}
  	
  	protected LamLoginPage() {
  	  
  	}
  	
  	public boolean exists() {
  	  	return browser.checkHtmlObjectExists(".id","signinform");
  	}
  	
  	/**
  	 * Set username
  	 * @param name - username
  	 */
  	public void setUserName(String name) {
  	  	browser.setTextField(".id","userid",name);
  	}
  	
  	/**
  	 * Set password
  	 * @param password - password
  	 */
  	public void setPassword(String password) {
  	  	browser.setPasswordField(".id", "pw", password);
  	}
  	
  	/**
  	 * Click to signin
  	 */
  	public void clickSignIn() {
  	  	browser.clickGuiObject(".id","signinbtn");
  	}
}
