package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.web.common.camping.UwpCampingPage;
/**
 * 
 * @author Swang
 *
 */
public class UwpUnifiedCabinDetailsPage extends UwpCampingPage {

	private static UwpUnifiedCabinDetailsPage _instance = null;

	public static UwpUnifiedCabinDetailsPage getInstance() {
		if (null == _instance)
			_instance = new UwpUnifiedCabinDetailsPage();

		return _instance;
	}

	public UwpUnifiedCabinDetailsPage() {}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "campDetail", ".text", "Cabin Details");
	}
}
