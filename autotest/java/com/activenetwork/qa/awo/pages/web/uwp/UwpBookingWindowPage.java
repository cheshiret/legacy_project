/*
 * Created on Sep 8, 2009
 *
 */
package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
/**
 * @author vzhao
 *
 * You can access this page by clicking 'Later Dates' in site details page
 * OR by clicking 'Booking Window' in campground detail page
 * 
 */
public class UwpBookingWindowPage extends UwpPage {
	private static UwpBookingWindowPage _instance = null;

	public static UwpBookingWindowPage getInstance() {
		if (null == _instance)
			_instance = new UwpBookingWindowPage();

		return _instance;
	}

	protected UwpBookingWindowPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "btnbookacampsite");
	}

	/**
	 * Click on camp description link
	 */
	public void gotoCampDescription() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Description");
	}

	/**
	 * Click on link 'Season Dates'
	 */
	public void gotoSeasonDates() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Season Dates");
	}

	/**
	 * Click on link 'Local Attractions'
	 */
	public void gotoLocalAttraction() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Local Attractions");
	}

	/**
	 * Click on link 'Fees and Cancellation'
	 */
	public void gotoFeeAndCancellation() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Fees and Cancellation");
	}

	/**
	 * Click on link 'General Rules'
	 */
	public void gotoGeneralRules() {
		browser.clickGuiObject(".class", "Html.A", ".text", "General Rules");
	}

	/**
	 * Click on link 'Book Now'.
	 */
	public void clickOnBookNow() {
		browser.clickGuiObject(".id", "btnbookacampsite");
	}

	/**
	 * This method goes to retrieve the specified site's booking window
	 * @param siteType - the specified site, if only one booking window, set it to null
	 * @return 
	 * 	---bookWindow[0] stands for Minimum Booking Window;
	 * 	---bookWindow[1] stands for Maximum Booking Window.
	 */
	public String[] getBookingWindow(String siteType) {
		String[] bookWindow = new String[2];

		if (siteType==null||siteType.length()<=0) {
			siteType = "Booking Window";// some campground only have one booking window
		}

		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
				".id", "contentcol");
		String context = (String) objs[0].getProperty(".text");
		Browser.unregister(objs);

		String regStr = " [a-zA-Z]{3} [a-zA-Z]{3} [0-9]{2} [0-9]{4} - [a-zA-Z]{3} [a-zA-Z]{3} [0-9]{2} [0-9]{4}";
		Pattern p = Pattern.compile(siteType + regStr);
		Matcher m = p.matcher(context);

		if (m.find()) {
			String[] temp = m.group().split(" - ");
			bookWindow[0] = temp[0].replaceAll(siteType, "").trim();
			bookWindow[1] = temp[1];
		} else
			throw new ItemNotFoundException("Unkown site type.");

		return bookWindow;
	}
}
