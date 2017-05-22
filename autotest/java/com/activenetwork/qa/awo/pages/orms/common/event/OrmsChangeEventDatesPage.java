/*
 * $Id: OrmsChangeEventDatesPage.java 7017 2009-01-16 23:15:02Z i2k-net\raonqa $ 
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
public class OrmsChangeEventDatesPage extends OrmsPage {

	/**
	 * Script Name   : OrmsChangeEventDatesPage
	 * Generated     : Jan 25, 2008 11:40:38 AM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2008/01/25
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsChangeEventDatesPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsChangeEventDatesPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsChangeEventDatesPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsChangeEventDatesPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("Change Event Dates$", false));
	}

	/**Input StartDate*/
	public void setStartDate(String date) {
		browser.setTextField(".id", "DateRange.startDate_ForDisplay", date);
	}

	/**Input EndDate*/
	public void setEndDate(String date) {
		browser.setTextField(".id", "DateRange.endDate_ForDisplay", date);
	}

	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**Click Cancel button*/
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**
	 * Change Event Dates
	 * @param start
	 * @param end
	 */
	public void changeEventDates(String start, String end) {
		this.setStartDate(start);
		this.setEndDate(end);
		this.clickOK();
	}
}
