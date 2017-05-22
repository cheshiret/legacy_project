package com.activenetwork.qa.awo.pages.orms.venueManager;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public abstract class VnuMgrCommonTopMenuPage extends VenueManagerPage {
	/** Click link to go to Home page from left menu bar. */
	public void clickHome() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression(
				"venuemanager\\.leftmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Home");
		browser.clickGuiObject(p);
	}

	/** Click Sales link from left menu bar. */
	public void clickSales() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression(
				"venuemanager\\.leftmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Sales");
		browser.clickGuiObject(p);
	}

	/** Click Tour Inventory link from left menu bar. */
	public void clickTourInventory() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression(
				"venuemanager\\.leftmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Tour Inventory");
		browser.clickGuiObject(p);
	}

	/** Click Print Tickets link from left menu bar. */
	public void clickPrintTickets() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression(
				"venuemanager\\.leftmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Print Tickets");
		browser.clickGuiObject(p);
	}

	/** Click on the top-menu "Cancel Cart" link */
	public void clickCancelCart() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression(
				"venuemanager\\.cartmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Cancel Cart");
		browser.clickGuiObject(p);
	}
	
	public void clickCart() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression(
				"venuemanager\\.cartmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Cart");
		browser.clickGuiObject(p);
	}
	
	public boolean cartExists() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression(
				"venuemanager\\.cartmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Cart");
		return browser.checkHtmlObjectExists(p);
	}

	/** Click search link from left menu bar. */
	public void clickSearch() {
		Property[] p = new Property[2];
		p[0] = new Property(".class", "Html.A");
//		p[1] = new Property(".href", new RegularExpression(
//				"javascript\\: doFieldSearch\\('field_search_dropdown'\\)", false));
		p[1] = new Property(".text", "Search:");
		browser.clickGuiObject(p);
	}

	/** Click Print Tickets link from left menu bar. */
	public void clickAdmin() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression(
				"venuemanager\\.rightmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Admin");
		browser.clickGuiObject(p);
	}

	/** Click Reports link from left menu bar. */
	public void clickReports() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression(
				"venuemanager\\.rightmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Reports");
		browser.clickGuiObject(p);
	}

	/** Click Financials link from left menu bar. */
	public void clickFinancials() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression(
				"venuemanager\\.rightmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Financials");
		browser.clickGuiObject(p);
	}

	/** Click Sign Out link from left menu bar. */
	public void clickSignOut() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", "logout");
		p[2] = new Property(".text", "Sign Out");
		browser.clickGuiObject(p);
	}

	/** Click POS link from right menu bar. */
	public void clickPOS() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression(
				"venuemanager\\.rightmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "POS");
		browser.clickGuiObject(p);
	}

	
	
	/**
	 * Select the search dropdown list to go to sepcial search page.
	 * 
	 * @param item
	 *            - search property
	 */
	public void selectSearchDropDown(String item) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SELECT",
				".id", "field_search_dropdown");
		ISelect select = (ISelect) objs[0];
		if (select.getSelectedText().equalsIgnoreCase(item))
			this.clickSearch();
		else
			((ISelect) objs[0]).select(item);
		Browser.unregister(objs);
	}

	/** Go to receipts page by clicking on link Receipts. */
	public void goToReceiptPage() {
		this.selectSearchDropDown("Receipts");
	}

}
