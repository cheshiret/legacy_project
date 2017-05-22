package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.awo.pages.UwpPage;

public class MarketingWebsiteSignInPage extends UwpPage {
	private static MarketingWebsiteSignInPage _instance = null;

	public static MarketingWebsiteSignInPage getInstance() {
		if (null == _instance)
			_instance = new MarketingWebsiteSignInPage();

		return _instance;
	}

	protected MarketingWebsiteSignInPage() {
	}

	public boolean exists() {
//		return browser.checkHtmlObjectExists(".id", "login");
		return browser.checkHtmlObjectExists(".id", "main-in");
	}
	
	/**
	 * Fill in user name.
	 * @param user - user account
	 */
	public void setUserName(String user) {
		browser.setTextField(".id", "login", user, true);
	}
	
	/**
	 * Fill in password.
	 * @param pwd - password
	 */
	public void setPassword(String pwd) {
		browser.setPasswordField(".id", "passWord", pwd, true);
	}
}
