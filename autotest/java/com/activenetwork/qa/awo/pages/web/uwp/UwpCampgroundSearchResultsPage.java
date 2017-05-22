/*
 * Created on Aug 21, 2009
 */
package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author vzhao
 * In Campground Directory page, click on some campgroud agencies link will get you into
 * this page which display the camp search results list, such as State Agencies.
 */
public class UwpCampgroundSearchResultsPage extends UwpPage {
	private static UwpCampgroundSearchResultsPage _instance = null;

	public static UwpCampgroundSearchResultsPage getInstance() {
		if (null == _instance)
			_instance = new UwpCampgroundSearchResultsPage();

		return _instance;
	}

	protected UwpCampgroundSearchResultsPage() {
	}
	
	public boolean exists() {
		RegularExpression reg = new RegularExpression("(book now|book elsewhere)",false);
		return browser.checkHtmlObjectExists(".className", reg);
	}

	/**
	 * Go back to Campground Directory page
	 */
	public void gotoCampgroundDirectoryPg() {
		browser.clickGuiObject(".class", "Html.A", ".text","Campground Directory");
	}

	/**
	 * Retrieve the page title after clicking link in Campground Directory page
	 */
	public String getPageTitle() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "pagetitle");
		String pageTitle = objs[0].getProperty(".text").trim();

		Browser.unregister(objs);
		return pageTitle;
	}

	/**
	 * Click on first Enter Date button
	 */
	public void clickOnFirstEnterDate() {
		RegularExpression reg = new RegularExpression("[b|B]ook [n|N]ow", false);
		
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".className", reg);
		((ILink)objs[0]).click();

		Browser.unregister(objs);
	}
	
	/**
	 * Click on first See Details button
	 */
	public void clickOnFirstSeeDetails() {
		RegularExpression reg = new RegularExpression("See Details", false);
		
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".className", reg);
		((ILink)objs[0]).click();

		Browser.unregister(objs);
	}

	/**
	 * Retrieve the park numbers displayede in first result page
	 */
	public int getParkNmuInFristResultPg() {
		RegularExpression reg = new RegularExpression("(B|b)ook ((N|n)ow|(E|e)lsewhere)", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",".className", reg);
		int objNum = objs.length;

		Browser.unregister(objs);
		return objNum;
	}
}
