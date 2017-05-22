/*
 * Created on Oct 28, 2009
 */
package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @author vzhao
 * You can access this page by clicking 'Season Dates' in site details page
 * OR campground detail page
 */
public class UwpSeasonDatePage extends UwpPage {
	private static UwpSeasonDatePage _instance = null;

	public static UwpSeasonDatePage getInstance() {
		if (null == _instance)
			_instance = new UwpSeasonDatePage();

		return _instance;
	}

	protected UwpSeasonDatePage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "btnbookacampsite");
	}

	/**
	 * Click on campground description link.
	 */
	public void gotoCampDescription() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Description");
	}
	
	/**
	 * Click on Booking Window link.
	 */
	public void gotoBookingWindow() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Booking Window");
	}

	/**
	 * Click on Local Attractions link.
	 */
	public void gotoLocalAttraction() {
		browser.clickGuiObject(".class","Html.A", ".text","Local Attractions");
	}

	/**
	 * Click on Fees and Cancellation link.
	 */
	public void gotoFeeAndCancellation() {
		browser.clickGuiObject(".class", "Html.A", ".text","Fees and Cancellation");
	}

	/**
	 * Click on General Rules link.
	 */
	public void gotoGeneralRules() {
		browser.clickGuiObject(".class", "Html.A", ".text", "General Rules");
	}
	
	/**
	 * Click on Book Now button to book site.
	 */
	public void clickOnBookNow() {
		browser.clickGuiObject(".id", "btnbookacampsite");
	}

	/**
	 * This method goes to retrieve the park season for given date
	 * @param date - the special date you want to know which season belongs to
	 * @return season - the date's season
	 */
	public String getSeasonForToday(String date) {
		String season = "";
		String startDate = "";
		String endDate = "";

		if (!DateFunctions.getDateStringPattern(date).equalsIgnoreCase("M/d/yyyy")) {
			date = DateFunctions.formatDate(date);
		}

		IHtmlObject[] seasonTable = browser.getHtmlObject(".id","seasonsView");
		IHtmlTable tableGrid = (IHtmlTable)seasonTable[0];

		for (int i = 1; i < tableGrid.rowCount(); i++) {
			startDate = DateFunctions.formatDate(tableGrid.getCellValue(i, 1)
					.toString());

			if (DateFunctions.compareDates(startDate, date) == -1
					|| DateFunctions.compareDates(startDate, date) == 0) {
				endDate = DateFunctions.formatDate(tableGrid.getCellValue(i, 2)
						.toString());
				if (DateFunctions.compareDates(endDate, date) == 1
						|| DateFunctions.compareDates(endDate, date) == 0) {
					season = tableGrid.getCellValue(i, 0).toString();
				} else {
					if (i == tableGrid.rowCount() - 1)
						throw new ItemNotFoundException(
								"All seasons start before " + date);
				}
			} else {
				if (i == tableGrid.rowCount() - 1)
					throw new ItemNotFoundException("All seasons start after "
							+ date);
			}
		}

		Browser.unregister(seasonTable);
		return season;
	}

	/**
	 * retrieve today's booking season
	 * @return - Booking season
	 */
	public String getSpecialDateSeason() {
		String today = DateFunctions.getToday().toString();
		return getSeasonForToday(today);
	}
}
