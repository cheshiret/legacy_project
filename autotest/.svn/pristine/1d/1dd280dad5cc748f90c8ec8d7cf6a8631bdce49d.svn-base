package com.activenetwork.qa.awo.pages.orms.venueManager;

import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author CGuo
 */
public class VnuMgrHomePage extends VnuMgrCommonTopMenuPage {

	/**
	 * Script Name : VnuMgrHomePage Generated : Dec 19, 2006 4:40:24 PM Original
	 * Host : WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2006/12/19
	 */
	private static VnuMgrHomePage _instance = null;

	private VnuMgrHomePage() {
	}

	public static VnuMgrHomePage getInstance() {
		if (null == _instance) {
			_instance = new VnuMgrHomePage();
		}

		return _instance;
	}

	public boolean exists() { // use #Tickets dropdown list as pageMark
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id",
				"VenueHome.numberOfTickets");
	}

	/** Go to ticket order page from top menu search dropdown. */
	public void searchTicketOrder() throws PageNotFoundException {
		String value = browser.getDropdownListValue(".id", "field_search_dropdown", 0);
		if(value.equalsIgnoreCase("Ticket Orders")){
			browser.clickGuiObject(".class","Html.A",".text", "Search:");
		}else{
			this.selectSearchDropDown("Ticket Orders");
		}
	}

	/**
	 * Fill in order number.
	 * 
	 * @param orderNum
	 *            - order number
	 */
	public void setOrderNumber(String orderNum) {
		browser.setTextField(".id", "reservationNum", orderNum);
	}

	/**
	 * Fill in customer last name.
	 * 
	 * @param lName
	 *            - last name
	 */
	public void setLastName(String lName) {
		browser.setTextField(".id", "lastName", lName);
	}

	/**
	 * Fill in customer first name.
	 * 
	 * @param fName
	 *            - first name
	 */
	public void setFirstName(String fName) {
		browser.setTextField(".id", "firstName", fName);
	}

	/**
	 * Fill in customer phone number.
	 * 
	 * @param phone
	 *            - phone number
	 */
	public void setPhoneNumber(String phone) {
		browser.setTextField(".id", "phone", phone);
	}

	/**
	 * Fill in customer organization.
	 * 
	 * @param org
	 *            - organization
	 */
	public void setOrganization(String org) {
		browser.setTextField(".id", "organization", org);
	}

	/** Select include area code check box. */
	public void selectIncludeAreaCodeCheckbox() {
		browser.selectCheckBox(".id", "includeareacode");
	}

	/** Deselect include area code check box. */
	public void deselectIncludeAreaCodeCheckbox() {
		browser.unSelectCheckBox(".id", "includeareacode");
	}

	/** Select current date check box. */
	public void selectCurrentDateCheckbox() {
		browser.selectCheckBox(".id", "useCurrentDate");
	}

	/** Deselect current date check box. */
	public void deselectCurrentDateCheckbox() {
		browser.unSelectCheckBox(".id", "useCurrentDate");
	}

	/** Click on link to do quick order searching. */
	public void clickQuickOrderSearch() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".href", new RegularExpression(
				"\"SearchResultSites\\.do\",.+\"scrollsearch\"", false));
		p[2] = new Property(".text", "Search");
		browser.clickGuiObject(p);
	}

	/** Click on Today's Will Call link. */
	public void clickTodayWillCall() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", "todaysWillCall");
		p[2] = new Property(".text", "Today's Will Call");
		browser.clickGuiObject(p);
	}

	/** Click on Advanced Search link to do advance searching. */
	public void clickAdvancedSearch() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(
				".href",
				new RegularExpression(
						"\"TicketSearchReservations\\.do\",.+\"ticketsearchreservations\"",
						false));
		p[2] = new Property(".text", "Advanced Search");
		browser.clickGuiObject(p);
	}

	/**
	 * Check the Html Link Displayed
	 * 
	 * @param htmlLink
	 *            -- the html link will be checked
	 * @return
	 */
	public boolean checkHtmlLinkExist(String htmlLink) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				htmlLink);
	}

	// Click the Tomorrow link
	public void clickTomorrow() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Tomorrow");
	}

	// Click the Today link
	public void clickToday() {
		browser.clickGuiObject(".id", "sellTicketsToday");
	}
	
	public void clickFuture(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Future...");
	}
	
	public String getTourAvailableTime(String tourName){
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("^Tour(| )Next Available Today.*",false));
				
		if(objs.length<1){
			throw new ObjectNotFoundException("Tour display info table object did not found.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(0, tourName);
		
		String value = table.getCellValue(row, 1);
		
		return value;
	}
	
	public void clickAvailableTimeLink(String tourName,String availableTime){
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("^Tour" + "(| )" + "Next Available Today.*",false));

		if(objs.length<1){
			throw new ObjectNotFoundException("Tour display info table object did not found.");
		}
		
		Property[] p = new Property[2];
		p[0] = new Property(".class","Html.TR");
		p[1] = new Property(".text", new RegularExpression("^" + tourName + ".*" + availableTime + ".*",false));
		
		IHtmlObject[] trObj = browser.getHtmlObject(p, objs[0]);
		if(trObj.length<1){
			throw new ObjectNotFoundException("Did not found tour name = " +tourName + ", available time = " + availableTime + " row object.");
		}
		
		browser.clickGuiObject(".class", "Html.A", ".text", availableTime,true, 0, trObj[0]);
		Browser.unregister(trObj);
		Browser.unregister(objs);
	}

	public void clickTourNameUrl(String tourname) {
		browser.clickGuiObject(".class", "Html.A", ".text", tourname);
	}

	/**
	 * Click 'purchase pos' link in 'Quick POS Sale' span
	 */
	public void clickPurchasePOS(){
		Property[] p = new Property[2];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Purchase POS");
		browser.clickGuiObject(p);
	}
}
