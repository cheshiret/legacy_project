package com.activenetwork.qa.awo.pages.orms.callManager;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class CallMgrCommonTopMenuPage extends CallManagerPage {
	private static CallMgrCommonTopMenuPage _instance;
	
	protected CallMgrCommonTopMenuPage() {
		
	}
	
	public static CallMgrCommonTopMenuPage getInstance() {
		if(null == _instance) {
			_instance=new CallMgrCommonTopMenuPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT",".id", "field_search_dropdown");
	}
	
	/** Select "Reservations" from the top-menu drop-down list */
	public void searchReservations() {
		selectSearchDropDown("Reservations");
	}

	/** Select "Customers" from the top-menu drop-down list */
	public void searchCustomers() {
		selectSearchDropDown("Customers");
	}

	/** Select "Invoice" from the top-menu drop-down list */
	public void selectInvoice() {
		selectSearchDropDown("Invoices");
	}
	
	/** Select "gift card" from the top-menu drop-down list */
	public void selectGiftCard() {
		selectSearchDropDown("Gift Cards");
	}

	/**Click Home clink*/
	public void clickHome() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression("callmgr\\.mainmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Home");
		browser.clickGuiObject(p);
	}
	
	/**Click Cart clink*/
	public void clickCart() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression("callmgr\\.mainmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Cart");
		browser.clickGuiObject(p);
	}

	/** 
	 * Click on the "Sites" link
	 * (ORMS 2.82 replacement for drop-list option)
	 */
	public void clickSites() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression(
				"callmgr\\.mainmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Sites");
		browser.clickGuiObject(p);
	}
	
	public void clickSlips() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression(
				"callmgr\\.mainmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Slips");
		browser.clickGuiObject(p);
	}
	
	/**
	 * Check the 'Sites' object exists in Call Manager top menu page
	 * @return
	 */
	public boolean checkSitesExists() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression(
				"callmgr\\.mainmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Sites");
		
		return browser.checkHtmlObjectExists(p);
	}
	
	/** 
	 * Click on the "Parks" link
	 * (ORMS 2.82 replacement for drop-list option)
	 */
	public void clickParks() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression(
				"callmgr\\.mainmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Parks");
		browser.clickGuiObject(p);
	}

	/** Click on the top-menu "POS" link (if it exists...) */
	public void clickPOS() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression(
				"callmgr\\.mainmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "POS");
		browser.clickGuiObject(p);
	}
	
	/** Click on the top-menu "gift card" link (if it exists...) */
	public void clickGiftCard() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression(
				"callmgr\\.mainmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Gift Card");
		browser.clickGuiObject(p);
	}

	/**Click ticket link*/
	public void clickTickets() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression(
				"callmgr\\.mainmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Tickets");
		browser.clickGuiObject(p);
	}

	/**Click Permit*/
	public void clickPermits() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression("callmgr\\.mainmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Permits");
		browser.clickGuiObject(p);
	}

	/** Click on the top-menu "Cancel Call" link */
	public void clickCancelCall() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression("callmgr\\.mainmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Cancel Call");
		browser.clickGuiObject(p);
	}
	
	public void clickWildLife() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression("callmgr\\.mainmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Wildlife");
		browser.clickGuiObject(p);
	}
	
	public boolean cancelCallExists() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression("callmgr\\.mainmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Cancel Call");
		return browser.checkHtmlObjectExists(p);
	}

	/**Click Search*/
	public void clickSearch() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".href", new RegularExpression("doFieldSearch\\(.*\\)", false));
		p[2] = new Property(".text", "Search:");
		browser.clickGuiObject(p,true);
	}

	/** Select the name-specified item from the top-menu drop-list */
	public void selectSearchDropDown(String item) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SELECT",".id", "field_search_dropdown");
		if (objs.length < 1)
			throw new ItemNotFoundException("It failed to find the top search menu.");
		
		ISelect dd=(ISelect) objs[0];
		if (dd.getSelectedText().equalsIgnoreCase(item)) {
			logger.debug("Current selected item is the same as target: "+item);
			clickSearch();
		} else {
			dd.select(item);
		}

		Browser.unregister(objs);
	}

	/** Click on the "Event" link (if it exists...) */
	public void clickEvent() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression("callmgr\\.mainmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Event");
		browser.clickGuiObject(p);
	}
	
	public void searchEquipmentRental() {
		selectSearchDropDown("Equipment Rental Search");
	}

	public void searchEquipmentRentalOrder() {
		selectSearchDropDown("Equipment Rental Orders");
	}

}
