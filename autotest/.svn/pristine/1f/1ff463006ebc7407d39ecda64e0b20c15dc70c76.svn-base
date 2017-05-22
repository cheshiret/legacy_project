package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * Description   : Functional Test Script
 * @author raonqa
 */
public class UwpAdminPage extends UwpPage {
	/**
	 * Script Name   : <b>UwpAdminPage</b>
	 * Generated     : <b>Feb 18, 2009 11:00:24 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/02/18
	 * @author raonqa
	 */
	private static UwpAdminPage _instance = null;

	public UwpAdminPage() {
	}

	public static UwpAdminPage getInstance() {
		if (null == _instance)
			_instance = new UwpAdminPage();

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.BODY", ".id",
				"UWP Admin Page");
	}

	/**
	 *Click on System link
	 */
	public void clickSystem() {
		browser.clickGuiObject(".class", "Html.A", ".text", "System", true);
	}

	/**
	 * wait for page to load
	 */
	public void waitForRefresh() {
		browser.searchObjectWaitExists(".class","Html.A",".text",new RegularExpression("System Properties:\\d+",false));
	}

	/**
	 *  Retrieve ORMS build number
	 * @return - build number
	 */
	public String getOrmsBuildNumber() {
		String toReturn = null;
		String pattern1 = "\\d\\.\\d{2}\\.\\d{2}\\.\\d{1,4}";
		String pattern2 = "ORMS-" + pattern1;

		String txt = browser.getObjectText(".class", "Html.DIV", ".id","pagearea");
		if (txt != null) {
			String[] s = RegularExpression.getMatches(txt, pattern2);
			if (s.length > 0) {
				s = RegularExpression.getMatches(s[0], pattern1);
				if (s.length > 0)
					toReturn = s[0];
			}
		}
		if (toReturn == null)
			throw new ItemNotFoundException("Failed to retrieve Orms build number.");

		return toReturn;
	}

	/**
	 *  Retrieve web build number.
	 * @return - build number
	 */
	public String getWebBuildNumber() {
		String toReturn = null;
		String pattern1 = "\\d\\.\\d{2}\\.\\d{2}\\.\\d{1,4}";
		String pattern2 = "Build version: " + pattern1;

		String txt = browser.getObjectText(".id","pagearea");
		if (txt != null) {
			String[] s = RegularExpression.getMatches(txt, pattern2);
			if (s.length > 0) {
				s = RegularExpression.getMatches(s[0], pattern1);
				if (s.length > 0)
					toReturn = s[0];
			}
		}

		if (toReturn == null || toReturn.trim().length()<1)
			throw new ItemNotFoundException("Failed to retrieve web build number.");

		return toReturn;
	}

	/**
	 *  Retrieve OCCAM server build number.
	 * @return - build number
	 */
	public String getOccamBuildNumber() {
		String toReturn = null;
		String pattern1 = "\\d\\.\\d{2}\\.\\d{2}\\.\\d{1,4}";
		String pattern2 = "OCCAM Server Status: .* Available Build version: "
				+ pattern1;

		String txt = browser.getObjectText(".class", "Html.DIV", ".id",
				"pagearea");
		if (txt != null) {
			String[] s = RegularExpression.getMatches(txt, pattern2);
			if (s.length > 0) {
				s = RegularExpression.getMatches(s[0], pattern1);
				if (s.length > 0)
					toReturn = s[0];
			}
		}
		if (toReturn == null)
			throw new ItemNotFoundException(
					"Failed to retrieve Occam build number.");

		return toReturn;
	}
}
