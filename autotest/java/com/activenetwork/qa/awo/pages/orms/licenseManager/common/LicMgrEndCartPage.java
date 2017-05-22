package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA-qchen
 * @Date  Feb 21, 2012
 */
public class LicMgrEndCartPage extends LicenseManagerPage {
	
	private static LicMgrEndCartPage _instance = null;

	public static LicMgrEndCartPage getInstance() {
		if (null == _instance)
			_instance = new LicMgrEndCartPage();

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", "OK to Cancel Cart");
	}

	public void clickOKToCancelCart() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "OK to Cancel Cart");
	}

	public void clickDontCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Don't Cancel");
	}
}
