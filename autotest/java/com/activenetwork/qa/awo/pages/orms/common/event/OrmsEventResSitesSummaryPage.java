/*
 * $Id: OrmsEventResSitesSummaryPage.java 7017 2009-01-16 23:15:02Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.event;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author jdu
 */
public class OrmsEventResSitesSummaryPage extends OrmsPage {

	/**
	 * Script Name   : OrmsEventResSitesSummaryPage
	 * Generated     : Jan 28, 2008 3:29:38 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2008/01/28
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsEventResSitesSummaryPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsEventResSitesSummaryPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsEventResSitesSummaryPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsEventResSitesSummaryPage();
		}

		return _instance;
	}
	
	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("Reserved Sites Summary$", false));
	}

	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**Click cancel*/
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

}
