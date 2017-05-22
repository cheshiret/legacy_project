/*
 * Created on Aug 21, 2009
 *
 */
package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author vzhao
 * - In Campground Directory page, click on some campgroud agencies link will get you into
 * this page which display the camp list by state, such as Federal Agencies,Private Campgrounds 
 */
public class UwpCampgroundsByStatePage extends UwpPage {
	private static UwpCampgroundsByStatePage _instance = null;

	public static UwpCampgroundsByStatePage getInstance() {
		if (null == _instance)
			_instance = new UwpCampgroundsByStatePage();

		return _instance;
	}

	protected UwpCampgroundsByStatePage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "stateindex") ||
							browser.checkHtmlObjectExists(".className", "stateindex") || browser.checkHtmlObjectExists(".id", "shoppingitems");
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
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",".id", "pagetitle");
		String pageTitle = objs[0].getProperty(".text").trim();

		Browser.unregister(objs);
		return pageTitle;
	}

	public void waitForPageTitleDisplay(String pageTitle){
		browser.searchObjectWaitExists(".id", "pagetitle", ".text", pageTitle);
	}
	
	/**
	 * Click on the state link and retrieve the state name
	 */
	public String clickOnStateLink() {
		RegularExpression hrefReg = new RegularExpression(
				".*/camping/.*/r/.*contractCode=.*", false);

		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",".href", hrefReg);
		String stateName = objs[0].getProperty(".text");
		((ILink)objs[0]).click();
		
		Browser.unregister(objs);
		return stateName;
	}
	
	public boolean checkFacilityPhotoExist(String description){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TR");
		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".title", description);
		Property[] p3 = Property.toPropertyArray(".class", "Html.IMG", ".alt", description);
		return browser.checkHtmlObjectExists(Property.atList(p1, p2)) && browser.checkHtmlObjectExists(Property.atList(p1, p3));
	}
}
