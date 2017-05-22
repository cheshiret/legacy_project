package com.activenetwork.qa.awo.pages.orms.operationManager;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public abstract class OpMgrCommonTopMenuPage extends OperationsManagerPage {
	
	RegularExpression menuIDPattern=new RegularExpression("(opmgr|callmgr)\\.(menuleft|mainmenu)\\.id\\.\\d+", false);

	/**Click Home in the top menu*/
	public void clickHome() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Home");
		p[2] = new Property(".id", new RegularExpression("opmgr\\.menuleft\\.id\\.\\d+", false));
		browser.clickGuiObject(p);
	}

	/**Click Search in the top menu*/
	public void clickSearch() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Search:");
		p[2] = new Property(".href", new RegularExpression("doFieldSearch\\(.*\\)$", false));
		browser.clickGuiObject(p);
	}

	/**Select item from the search drop down*/
	public void selectSearchDropDown(String option) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SELECT",".id", "field_search_dropdown");
		ISelect select = (ISelect) objs[0];
		if (select.getSelectedText().equalsIgnoreCase(option))
			this.clickSearch();
		else
			((ISelect) objs[0]).select(option);

		Browser.unregister(objs);
	}
	
	public void selectReceipt(){
	  selectSearchDropDown("Receipts");
	}

	/**Click Parks in the top menu*/
	public void clickParks() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Parks");
		p[2] = new Property(".id", menuIDPattern);
		browser.clickGuiObject(p);
	}

	/**Click Sites in the top menu*/
	public void clickSites() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Sites");
		p[2] = new Property(".id", menuIDPattern);
		browser.clickGuiObject(p);
	}

	/**Click Slips in the top menu*/
	public void clickSlips() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Slips");
		p[2] = new Property(".id", menuIDPattern);
		browser.clickGuiObject(p);
	}
	
	/**Click Bulletins in the top menu*/
	public void clickBulletins() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Bulletins");
		p[2] = new Property(".id", new RegularExpression("opmgr\\.menuright\\.id\\.\\d+", false));
		browser.clickGuiObject(p);
	}

	/**Click Admin in the top menu*/
	public void clickAdmin() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Admin");
		p[2] = new Property(".id", new RegularExpression("opmgr\\.menuright\\.id\\.\\d+", false));
		browser.clickGuiObject(p);
	}

	/**Click Financials in the top menu*/
	public void clickFinancials() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Financials");
		p[2] = new Property(".id", new RegularExpression("opmgr\\.menuright\\.id\\.\\d+", false));
		browser.clickGuiObject(p);
	}

	/**Click Sign out in the top menu*/
	public void clickSignOut() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Sign-Out");
		p[2] = new Property(".id", new RegularExpression("opmgr\\.menuright\\.id\\.\\d+", false));
		browser.clickGuiObject(p);
	}

	/**Click search customers*/
	public void searchCustomers() {
		selectSearchDropDown("Customers");
	}
	
	/**
	 * 
	 * TODO    Select search ticket order
	 * @Return void
	 * @Throws
	 */
	public void searchTicketOrder(){
		selectSearchDropDown("Ticket Orders");
	}
	
	/** Click on the "Event" link (if it exists...) */
	public void clickEvent() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", menuIDPattern);
		p[2] = new Property(".text", "Event");
		browser.clickGuiObject(p);
	}
	
	public void clickTickets(){
	    Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", menuIDPattern);
		p[2] = new Property(".text", "Tickets");
		browser.clickGuiObject(p);
	}
	
	public void clickPermit(){
	    Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", menuIDPattern);
		p[2] = new Property(".text", "Permits");
		browser.clickGuiObject(p);
	}
	
	public void clickCart(){
	    Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", menuIDPattern);
		p[2] = new Property(".text", "Cart");
		browser.clickGuiObject(p);
	}
	
	public void clickGiftCard(){
	    Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", menuIDPattern);
		p[2] = new Property(".text", "Gift Cards");
		browser.clickGuiObject(p);
	}
	
	public void clickCancelCall() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", menuIDPattern);
		p[2] = new Property(".text", "Cancel Call");
		browser.clickGuiObject(p);
	}
	
	public boolean cartExists() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", menuIDPattern);
		p[2] = new Property(".text", "Cart");
		return browser.checkHtmlObjectExists(p);
	}

	public void searchRefundRequest(){
		selectSearchDropDown("Refund Requests");
	}

	public void searchReceipt(){
		selectSearchDropDown("Receipts");
	}
}
