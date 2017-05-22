package com.activenetwork.qa.awo.pages.orms.common.ticket;

import com.activenetwork.qa.awo.pages.OrmsPage;

/**
 * @Desc: this page class is created for vm/om/cm
 * @ScriptName OrmsTourDetaisPage.java
 * @Date:Oct 9, 2010
 * @author asun
 */
public class OrmsTourDetaisPage extends OrmsPage {

	private static OrmsTourDetaisPage instance = null;

	private OrmsTourDetaisPage() {

	}

	public static OrmsTourDetaisPage getInstance() {
		if (instance == null) {
			instance = new OrmsTourDetaisPage();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Tour Details");
	}
	
	public void clickCheckAvailabilityButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Check Availability");
	}

	public void clickBackButton() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Back");
	}

}
