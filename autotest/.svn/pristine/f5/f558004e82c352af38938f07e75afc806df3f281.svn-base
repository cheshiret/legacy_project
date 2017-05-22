package com.activenetwork.qa.awo.pages.web.uwp;

/**
 * 
 * @Description: This page displays after click Expired Passed tab In UwpDiscountPassesPage
 * URL:memberDiscountPasses.do?mode=expired
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Apr 24, 2014
 */
public class UwpExpiredPassesPage extends UwpDiscountPassesPage{
	static class SingletonHolder {
		protected static UwpExpiredPassesPage _instance = new UwpExpiredPassesPage();
	}

	protected UwpExpiredPassesPage() {
	}

	public static UwpExpiredPassesPage getInstance() {
		return SingletonHolder._instance;
	}
	
	/** Page Object Property Definition Begin */
	/** Page Object Property Definition End */
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(expiredPassesTabSelected());
	}
}
