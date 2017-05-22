/*
 * $Id: CallMgrTopMenuPage.java 7028 2009-01-20 16:50:34Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.callManager;

import com.activenetwork.qa.testapi.PageNotFoundException;

/**
 * 
 * @author CGuo,jdu
 */
public class CallMgrTopMenuPage extends CallMgrCommonTopMenuPage {

	/**
	 * Script Name   : CallMgrTopMenuPage
	 * Generated     : Dec 6, 2004 5:12:27 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/12/06
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private CallMgrTopMenuPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected CallMgrTopMenuPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public CallMgrTopMenuPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new CallMgrTopMenuPage();
		}

		return _instance;
	}

	/** Determine if the CallManager top-menu exists */
	public boolean exists() { // use Search: button as pageMark
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text","Search:");
	}
	
	public void searchTicket(){
	    selectSearchDropDown("Ticket Orders");
	}
	
	/** Select "Invoice" from the top-menu drop-down list */
	public void selectReceipt() {
		selectSearchDropDown("Receipts");
	}
}
