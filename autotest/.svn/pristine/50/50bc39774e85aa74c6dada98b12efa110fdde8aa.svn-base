/*
 * $Id: OrmsReservationHistoryPage.java 7017 2009-01-16 23:15:02Z i2k-net\raonqa $
 */

package com.activenetwork.qa.awo.pages.orms.common.camping;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

/**
 * Description   : XDE Tester Script
 * @author CGuo
 */
public class OrmsReservationHistoryPage extends OrmsPage {

	/**
	 * Script Name   : <b>FldMgrReservHistoryPage</b>
	 * Generated     : <b>Sep 30, 2004 1:25:05 PM</b>
	 * Description   : XDE Tester Script
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/09/30
	 * @author CGuo
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsReservationHistoryPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsReservationHistoryPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsReservationHistoryPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsReservationHistoryPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Return To Reservation Details");
	}

	/**Select how many rows that display in one page*/
	public void selectRowsPerPage(int rows) {
		browser.selectDropdownList(".id", "pagingBarRowsPerPage", rows+ " rows per page");
	}

	/**Click "Return to reservation details" button*/
	public void clickReturnReservDetail() {
		browser.clickGuiObject(".class", "Html.A", ".text","Return To Reservation Details");
	}

	/**
	 * Get history table object
	 * @return
	 */
	public IHtmlObject[] getHistoryTable() {
		return browser.getTableTestObject(".id", "ReservationHistory");
	}

	/**
	 * Get one record
	 * @param row
	 * @param col
	 * @return
	 */
	public String getRecord(int row, int col) {
		IHtmlObject[] table = this.getHistoryTable();
		String text = ((IHtmlTable)table[0]).getCellValue(row, col).toString();
		Browser.unregister(table);

		return text;
	}

}
