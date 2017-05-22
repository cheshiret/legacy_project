package com.activenetwork.qa.awo.pages.web.bw;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 */
public class BwHeaderBar extends UwpPage {

	private static BwHeaderBar _instance = null;

	public static BwHeaderBar getInstance() {
		if (null == _instance)
			_instance = new BwHeaderBar();

		return _instance;
	}

	public boolean exists() {
//		boolean flag1 = browser.checkHtmlObjectExists(".class", "Html.A", ".id", "Permits");
//		boolean flag2 =  browser.checkHtmlObjectExists(".class", "Html.A", ".id", "Home");
//		return flag1 | flag2;
		return browser.checkHtmlObjectExists(".class", "Html.A", ".id", new RegularExpression("Permits|Search", false));//BW&REC.GOV
	}

	/**
	 * Sign out application.
	 */
	public void clickSignOut() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Sign Out");
	}

	/**
	 * Click on wildness permit link.
	 */
	public void clickWildnessPermits() {
		browser.clickGuiObject(".class", "Html.A", ".id", "Permits");
	}

	/**
	 * Go to my reservation account.
	 */
	public void clickMyReservationAccount() {
		browser.clickGuiObject(".class", "Html.A", ".text", "My Reservations & Account");
	}

	/**
	 * Go to shopping cart by clickig on Item In Cart: .*
	 */
	public void clickItemsInCart() {
		RegularExpression pattern = new RegularExpression(
				"Items In Cart: [0-9]+", false);
		browser.clickGuiObject(".class", "Html.A", ".text", pattern);
	}
	
	/**
	 * Click on wildness permit link.
	 */
	public void clickPermits() {
		browser.clickGuiObject(".class", "Html.A", ".id", new RegularExpression("Permits|Home", false));
	}
}
