package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;

/**
 * Description   : Functional Test Script
 * @author raonqa
 */
public class UwpCreateCampingClubProfilePage extends UwpPage {
	private static UwpCreateCampingClubProfilePage _instance = null;

	protected UwpCreateCampingClubProfilePage() {
	}

	public static UwpCreateCampingClubProfilePage getInstance() {
		if (null == _instance) {
			_instance = new UwpCreateCampingClubProfilePage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id","signinbtn");
	}
	/**
	 * Click on 'Skip' link
	 */
	public void clickSkip() {
		browser.clickGuiObject(".text", "Skip", ".class", "Html.A", true);
	}
}
