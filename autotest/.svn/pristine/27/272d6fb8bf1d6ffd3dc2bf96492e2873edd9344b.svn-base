/*
 * Created on May 19, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.ticket;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author raonqa
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsTicketOrderSearchPage extends OrmsPage {

	private static OrmsTicketOrderSearchPage _instance = null;

	// private Subitem transaction = Browser.atDescendant(".class",
	// "Html.FRAME",
	// ".id", "transaction");

	public static OrmsTicketOrderSearchPage getInstance() {
		if (null == _instance)
			_instance = new OrmsTicketOrderSearchPage();

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("Ticket Order Search/List$", false));
	}

	/**
	 * Select Ticket according ticket number
	 * 
	 * @param ticketNumber
	 */
	public void selectTicket(String ticketNumber) {
//		HtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".href",new RegularExpression(".*ViewTourOrder.*" + ticketNumber + ".*", false));
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", ticketNumber);
		p[2] = new Property(".href", new RegularExpression(".*TourOrderAlert.do.*", false));
//		HtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",ticketNumber);
		IHtmlObject[] objs = browser.getHtmlObject(p);
		if (objs.length < 1) {
			throw new ItemNotFoundException("Ticket order#"+ticketNumber+" link is not found");
		}
		if (objs.length > 1) {
			throw new RuntimeException("ambigous object recogintion");
		}
		
		objs[0].click();
		Browser.unregister(objs);
	}

	/**
	 * Select ticket by order number
	 * 
	 * @param ticketNumber
	 */
	public void searchTicketByOrderNumber(String OrderNumber) {
		this.setOrderNumber(OrderNumber);
		this.clickSearch();
	}

	/**
	 * Set Order Ormber
	 * 
	 * @param ordID
	 */
	public void setOrderNumber(String ordID) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.setTextField(".id", "reservationNum", ordID, true, 0,
						frames[0]);
		Browser.unregister(frames);
	}

	/**
	 * Input Order Date
	 * 
	 * @param date
	 */
	public void setOrderDate(String date) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.setTextField(".id", "tourOrderDate_ForDisplay", date, true, 0,
				frames[0]);
	}

	/**
	 * Input First Name
	 * 
	 * @param fName
	 */
	public void setFirstName(String fName) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.setTextField(".id", "firstName", fName, true, 0, frames[0]);
		Browser.unregister(frames);
	}

	/**
	 * Input Last Name
	 * 
	 * @param lName
	 */
	public void setLastName(String lName) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.setTextField(".id", "lastName", lName, true, 0, frames[0]);
		Browser.unregister(frames);
	}

	/**
	 * Set Phone number
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.setTextField(".id", "phone", phone, true, 0, frames[0]);
		Browser.unregister(frames);
	}

	/**
	 * Set operator first name
	 * 
	 * @param fName
	 */
	public void setOperatorFistName(String fName) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.setTextField(".id", "operatorfirstname", fName, true, 0,
				frames[0]);
		Browser.unregister(frames);
	}

	/**
	 * Set operator last name
	 * 
	 * @param lName
	 */
	public void setOperatorLastName(String lName) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.setTextField(".id", "operatorlastname", lName, true, 0,
				frames[0]);
		Browser.unregister(frames);
	}

	/**
	 * Set organization
	 * 
	 * @param org
	 */
	public void setOrganization(String org) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.setTextField(".id", "organization", org, true, 0, frames[0]);
		Browser.unregister(frames);
	}

	/**
	 * Set receipt number
	 * 
	 * @param receipt
	 */
	public void setReceiptNumber(String receipt) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.setTextField(".id", "receiptnum", receipt, true, 0, frames[0]);
		Browser.unregister(frames);
	}

	/**
	 * Set qty of ticket
	 * 
	 * @param qty
	 */
	public void setTicketQty(String qty) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.setTextField(".id", "ticketQty", qty, true, 0, frames[0]);
		Browser.unregister(frames);
	}

	/**
	 * set tour name
	 * 
	 * @param name
	 */
	public void setTourName(String name) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.setTextField(".id", "tourName", name, true, 0, frames[0]);
		Browser.unregister(frames);
	}

	/**
	 * Input tour End time
	 * 
	 * @param time
	 */
	public void setTourEndTime(String time) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.setTextField(".id", "tourEndTime", time, true, 0, frames[0]);
		Browser.unregister(frames);
	}

	/** Input tour start time */
	public void setTourStartTime(String time) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.setTextField(".id", "tourStartTime", time, true, 0, frames[0]);
		Browser.unregister(frames);
	}

	/**
	 * Input tour from date
	 * 
	 * @param date
	 */
	public void setTourFromDate(String date) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.setTextField(".id", "tourFromDate_ForDisplay", date, true, 0,
				frames[0]);
		Browser.unregister(frames);
	}

	/**
	 * Input tour to date
	 * 
	 * @param date
	 */
	public void setTourToDate(String date) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.setTextField(".id", "tourToDate_ForDisplay", date, true, 0,
				frames[0]);
		Browser.unregister(frames);
	}

	/**
	 * Select Facility
	 * 
	 * @param item
	 */
	public void selectFacilty(String item) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.selectDropdownList(".id", "facility", item, true, frames[0]);
		Browser.unregister(frames);
	}

	/**
	 * Select order status
	 * 
	 * @param status
	 */
	public void selectOrderStatus(String status) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.selectDropdownList(".id", "orderstatus", status, true,
				frames[0]);
		Browser.unregister(frames);
	}

	/**
	 * Select sort order
	 * 
	 * @param sort
	 */
	public void selectSortOrder(String sort) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.selectDropdownList(".id", "ticketSearchOrder", sort, true,
				frames[0]);
		Browser.unregister(frames);
	}

	/**
	 * Select Ticket category
	 * 
	 * @param cat
	 */
	public void selectTicketCategory(String cat) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.selectDropdownList(".id", "ticketcategory", cat, true,
				frames[0]);
		Browser.unregister(frames);
	}

	/**
	 * Select tour end time option AM/PM
	 * 
	 * @param option
	 */
	public void selectTourEndTimeAMPM(String option) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.selectDropdownList(".id", "tourEndTimeAMPM", option, true,
				frames[0]);
		Browser.unregister(frames);
	}

	/**
	 * Select Tour start time option AM/PM
	 * 
	 * @param option
	 */
	public void selectTourStartTimeAMPM(String option) {
		IHtmlObject[] frames = getTransactionFrame();
		browser.selectDropdownList(".id", "tourStartTimeAMPM", option, true,
				frames[0]);
		Browser.unregister(frames);
	}

	/**
	 * Select tour status
	 * 
	 * @param status
	 */
	public void selectTourStatus(String status) {
		IHtmlObject[] frames = getTransactionFrame();
		browser
				.selectDropdownList(".id", "tourStatus", status, true,
						frames[0]);
		Browser.unregister(frames);
	}

	/** Click Search */
	public void clickSearch() {
		IHtmlObject[] frames = getTransactionFrame();
		browser.clickGuiObject(".class", "Html.A", ".text", "Search", true, 0,
				frames[0]);
		Browser.unregister(frames);
	}

	/** Select UnPrintedOnly Checkbox */
	public void selectUnprintedOnlyCheckBox() {
		IHtmlObject[] frames = getTransactionFrame();
		browser.selectCheckBox(".id", "UnprintedOnly", 0, frames[0]);
		Browser.unregister(frames);
	}

	/** Select Include Area Code Checkbox */
	public void selectIncludeAreaCodeCheckBox() {
		IHtmlObject[] frames = getTransactionFrame();
		browser.selectCheckBox(".id", "includeareacode", 0, frames[0]);
		Browser.unregister(frames);
	}

	/**
	 * Get status message
	 * 
	 * @return
	 */
	public String getStatusMessage() {
		IHtmlObject[] frames = getTransactionFrame();
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".id", "statusMessages", frames[0]);
		String toReturn = objs[0].getProperty(".text").toString();
		Browser.unregister(frames);
		Browser.unregister(objs);
		return toReturn;
	}

	/**
	 * Get printNum message
	 * 
	 * @return
	 */
	public String getPrintNumMessage() {
		String toReturn = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", new RegularExpression("^Order # Bal Order Stat.*",
						false));
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		if (tableGrid.rowCount() > 0) {
			toReturn = tableGrid.getCellValue(1, 11);
			Browser.unregister(objs);
		} else
			throw new ErrorOnDataException(
					"The row count of Table Object is <0");
		return toReturn;
	}

	/**
	 * Check result is empty or not
	 * 
	 * @return
	 */
	public boolean resultNotEmpty() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				new RegularExpression("^[0-9]-[0-9]+$", false));
	}

	/**
	 * Click Order Number link
	 * 
	 * @param orderID
	 */
	public void clickOrderNumberLink(String orderID) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				orderID);
		
		if (objs.length > 1) {
			objs[1].click();
		}else if(objs.length == 1){
			objs[0].click();
		}else{
			throw new ItemNotFoundException("the Link in search order page does not exist");
		}

		// browser.clickGuiObject(".class", "Html.A", ".href", orderID,2);
	}

	/**
	 * check gotonext button exist or not, if exist, click it
	 * 
	 * @return
	 */
	public boolean gotoNext() {
		boolean hasMore = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Next");
		if (objs.length > 0) {
			// MiscFunctions.clickObject(objs[0]);
			objs[0].click();
			hasMore = true;
		}

		return hasMore;
	}

	/**
	 * Check gotofirst button exit or not, if exit, click it
	 * 
	 * @return
	 */
	public boolean gotoFirst() {
		boolean hasMore = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"First");
		if (objs.length > 0) {
			// MiscFunctions.clickObject(objs[0]);
			objs[0].click();
			hasMore = true;
		}

		return hasMore;
	}

	/**
	 * Check Gotolast button exist or not, if exist, click it
	 * 
	 * @return
	 */
	public boolean gotoLast() {
		boolean hasMore = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Last");
		if (objs.length > 0) {
			// MiscFunctions.clickObject(objs[0]);
			objs[0].click();
			hasMore = true;
		}

		return hasMore;
	}

	/**
	 * Check gotoprevious button exit or not, if exist, click it
	 * 
	 * @return
	 */
	public boolean gotoPrevious() {
		boolean hasMore = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Previous");
		if (objs.length > 0) {
			// MiscFunctions.clickObject(objs[0]);
			objs[0].click();
			hasMore = true;
		}

		return hasMore;
	}

	/**
	 * Select how many row per page
	 * 
	 * @param rows
	 */
	public void selectRowsPerPage(int rows) {
		browser.selectDropdownList(".id", "pagingBarRowsPerPage", rows
				+ " rows per page");
	}

	/** Click Print tickets */
	public void clickPrintTickets() {
		browser
				.clickGuiObject(".class", "Html.TABLE", ".text",
						"Print Tickets");
	}

	/** Click Print button */
	public void clickPrint() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Print");
	}

	/** Get the print object */
	public IHtmlObject[] getPrintObject() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".text", "Print");
		return objs;
	}

	/** Check Print table exit */
	public boolean checkPrintTableExit() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				"Print");
	}

	/** Check Print button */
	public boolean checkPrintAvailable() {
		boolean available = false;
		if (checkPrintTableExit()) {
			available = browser.checkHtmlObjectExists(".class", "Html.A",
					".text", "Print");
		} else
			throw new ErrorOnDataException("Print table doesn't exit.");
		return available;
	}

	/**
	 * Select order checkbox by orderID
	 * 
	 * @param ordID
	 */
	public void selectOrderCheckBox(String ordID) {
		browser.selectCheckBox(".id", "reservationNumber", ".value", ordID);
	}

	/**
	 * Check ordercheckbox enable or disable
	 * 
	 * @param ordID
	 * @return
	 */
	public boolean orderCheckBoxDisabled(String ordID) {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.INPUT.checkbox");
		p[1] = new Property(".id", "reservationNumber");
		p[2] = new Property(".text", ordID);
		IHtmlObject[] objs = browser.getHtmlObject(p);
		// boolean toReturn = ((Boolean)
		// objs[0].getProperty(".disabled")).booleanValue();
		boolean toReturn = (Boolean.valueOf(objs[0].getProperty(".disabled")))
				.booleanValue();

		Browser.unregister(objs);

		return toReturn;
	}

	/**
	 * Search ticket order by order ID
	 * 
	 * @param ordID
	 */
	public void searchTicketOrder(String ordID) {
		this.setOrderNumber(ordID);
		this.clickSearch();
		this.waitLoading();
	}
	public void searchTicketOrder(String orderNum,String status){
		this.setOrderNumber(orderNum);
		this.selectOrderStatus(status);
		this.clickSearch();
		this.waitLoading();
	}
	
	private IHtmlObject getSearchResultTable(){
		IHtmlObject[] resultTable = browser.getTableTestObject(".class",
				"Html.TABLE", ".className", "searchResult");
		if(resultTable.length >0){
			return resultTable[0];
		}else{
			throw new ErrorOnPageException("Can not found result table!");
		}
	}
	
	private String getItemInfoForOrder(String ordNum, String itemType){
		IHtmlTable table = (IHtmlTable)this.getSearchResultTable();
		int rowNum = table.findRow(1, ordNum);
		int colNum = table.findColumn(0, itemType);
		String content = table.getCellValue(rowNum, colNum);
		return content;
	}
	
	public String getOrderTourDate(String ordNum){
		return this.getItemInfoForOrder(ordNum, "Tour Date");
	}
}
