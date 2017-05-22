/*
 * $Id: FinMgrAddFeePage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $
 */

package com.activenetwork.qa.awo.pages.orms.financeManager;

import com.activenetwork.qa.testapi.PageNotFoundException;

/**
 * Description   : XDE Tester Script
 * @author CGuo
 */
public class FinMgrAddFeePage extends FinanceManagerPage {

	/**
	 * Script Name   : <b>FinMgrAddFeePage</b>
	 * Generated     : <b>Oct 6, 2004 6:09:00 PM</b>
	 * Description   : XDE Tester Script
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/10/06
	 * @author CGuo
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrAddFeePage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrAddFeePage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrAddFeePage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new FinMgrAddFeePage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.A",".text","Go");
	}

	/** Click on Go link. */
	public void clickGo() {
		browser.clickGuiObject(".class","Html.A",".text", "Search");
	}

	/**
	 * Select location category by given location category.
	 * @param location
	 */
	public void selectLocationCategory(String location) {
		browser.selectDropdownList(".id", "showCategory", location);
	}

	/**
	 * Select status.
	 * @param status
	 */
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", "status", status);
	}

	/**
	 * Fill in location by given location name.
	 * @param location
	 */
	public void setLocationName(String location) {
		browser.setTextField(".id", "locationName", location);
	}

	/**
	 * Search and select location by given location category and location name.
	 * @param locationCategory 
	 * @param location - location name
	 */
	public void selectLocation(String locationCategory, String location) {

		if (!location.equals("")) {
			setLocationName(location);
		}

		if (!locationCategory.equals("")) {
			selectLocationCategory(locationCategory);
		}

		clickGo();

		waitLoading();
		clickSelect(0);

	}

	/**
	 * Select location by given index. 
	 * @param index - location index
	 */
	public void clickSelect(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Select", index);
	}
}
