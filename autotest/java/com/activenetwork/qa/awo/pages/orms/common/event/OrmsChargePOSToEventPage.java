/*
 * $Id: OrmsChargePOSToEventPage.java 7017 2009-01-16 23:15:02Z i2k-net\raonqa $ 
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
public class OrmsChargePOSToEventPage extends OrmsPage {

	/**
	 * Script Name   : OrmsChargePOSToEventPage
	 * Generated     : Feb 1, 2008 8:15:36 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2008/02/01
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsChargePOSToEventPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsChargePOSToEventPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsChargePOSToEventPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsChargePOSToEventPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("Charge To Event Search/List$", false));
	}

	/**wait seach event exist*/
	public void schEventWaitExists() throws PageNotFoundException {
		browser.searchObjectWaitExists(".class", "Html.A", ".text", "Select");
	}

	/**Click Select Button*/
	public void clickSelect() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Select");
	}

	/**Input Event ID*/
	public void setEventID(String id) {
		browser.setTextField(".id", "EventSearchCriteria.eventId:ZERO_TO_NULL",
				id);
	}

	/**Click Go Button*/
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$",false));
	}

	/**Chareg Event*/
	public void chargeToEvent(String id) throws PageNotFoundException {
		this.setEventID(id);
		this.clickGo();
		this.schEventWaitExists();
		this.clickSelect();
	}

}
