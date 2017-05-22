package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;

public class UwpSocialSecurityNumberRequiredPage extends  UwpPage {
	
	private static UwpSocialSecurityNumberRequiredPage _instance = null;

	public static UwpSocialSecurityNumberRequiredPage getInstance() {
		if (null == _instance)
			_instance = new UwpSocialSecurityNumberRequiredPage();

		return _instance;
	}

	protected UwpSocialSecurityNumberRequiredPage() {
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.INPUT.text",".id", "SocialSecurityNumber");
	}
	
	public void setSocialSecurityNumber(String ssn) {
		browser.setTextField(".id","SocialSecurityNumber",ssn);
	}
	
	public void clickContinue() {
		browser.clickGuiObject(".id","Continuebutton");
	}
}
