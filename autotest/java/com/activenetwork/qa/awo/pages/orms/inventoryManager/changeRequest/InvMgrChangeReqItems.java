/*
 * $Id: InvMgrChangeReqItems.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.changeRequest;


import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author cguo
 */
public class InvMgrChangeReqItems extends InventoryManagerPage {

	/**
	 * Script Name   : InvMgrChangeReqItems
	 * Generated     : Dec 19, 2005 3:50:39 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2005/12/19
	 */
	private static InvMgrChangeReqItems _instance = null;

	public static InvMgrChangeReqItems getInstance() {
		if (null == _instance) {
			_instance = new InvMgrChangeReqItems();
		}

		return _instance;
	}

	protected InvMgrChangeReqItems() {

	}

	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text","Submit");
	}

	/**Click Submit button*/
	public void clickSubmit() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Submit");
	}

	/**Click Delete Button*/
	public void clickDelete() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Delete");
	}

	/**
	 * Select search type
	 * @param type
	 */
	public void selectSearchType(String type) {
		browser.selectDropdownList(".id", "itemsearch-type-id", type);
	}
	
	/**
	 * Select Request type
	 * @param type
	 */
	public void selectRequestType(String type) {
		browser.selectDropdownList(".id", "search-request-id", type);
	}

	/**
	 * Set search value 
	 * @param value
	 */
	public void setSearchTypeValue(String value) {
		browser.setTextField(".id", "itemsearch-type-value", value);
	}

	/**
	 * Set search status
	 * @param status
	 */
	public void selectSearchStatus(String status) {
		browser.selectDropdownList(".id", "itemsearch-status-id", status);
	}

	/**
	 * Select Request
	 * @param request
	 */
	public void selectRequest(String request) {
		browser.selectDropdownList(".id", "search-request-id", request);
	}
	
	/**
	 * Input Facility Name
	 * @param name
	 */
	public void setFacilityName(String name) {
		if(this.checkFacilityNameExist()){
			browser.setTextField(".id", "search-facility-name", name);
		}
	}

	public boolean checkFacilityNameExist(){
		return browser.checkHtmlObjectExists(".id", "itemsearch-facility-name");
	}

	/**
	 * Select Date Type
	 * @param type
	 */
	public void selectDateType(String type) {
		browser.selectDropdownList(".id", "itemsearch-date-id", type);
	}

	/**
	 * Input From Date
	 * @param date
	 */
	public void setFromDate(String date) {
		browser.setTextField(".id", "itemsearch-date-from_ForDisplay", date);
	}

	/**
	 * Input To Date
	 * @param date
	 */
	public void setToDate(String date) {
		browser.setTextField(".id", "itemsearch-date-to_ForDisplay", date);
	}

	/**Click Search Button*/
	public void clickSearch() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}

	/**
	 * Search items 
	 * @param searchType
	 * @param itemId
	 */
	public void searchItem(String searchType, String itemId) {
		this.selectSearchType(searchType);
		this.setSearchTypeValue(itemId);
		this.clickSearch();
		this.waitLoading();
	}

	/**
	 * Search item by item ID
	 * @param itemId
	 */
	public void searchItem(String itemId) {
		searchItem("Change Request Item ID", itemId);
	}
	
	/**
	 * Search item by status
	 * @param status
	 */
	public void searchItemViaStatus(String status) {
		selectSearchStatus(status);
	}
	
	/**
	 * Search item by status
	 * @param status
	 */
	public void searchItemViaRequestType(String requestType) {
		selectRequest(requestType);
	}
	
	/**
	 * Select change request by requestID
	 * @param requestId
	 */
	public void selectChangeRequest(String requestId) {
		RegularExpression rex = new RegularExpression("\"ViewChangeRequestDetail\".+\"" + requestId + "\"", false);
		browser.clickGuiObject(".text",requestId,".href",rex);
	}

	/**
	 * Select change request item by item ID
	 * @param itemId
	 */
	public void selecthangeRequestItem(String itemId) {
		RegularExpression rex = new RegularExpression("\"getChangeRequestItemDetail\".+\"" + itemId + "\"", false);
		browser.clickGuiObject(".text",itemId,".href",rex);
	}

	/**
	 * Select reference by reference ID
	 * @param refId
	 */
	public void selectReferenceID(String refId) {
		RegularExpression rex = new RegularExpression("\"selectPark\".+\""+ refId + ":FromCRItemList\"", false);
		browser.clickGuiObject(".text",refId,".href",rex);
	}
	
	/**Click first check box*/
	public void clickFirstCheckBox(){
		browser.clickGuiObject(".class", "Html.INPUT.checkbox", ".id", "row_0_checkbox");
	}
	
	/**Click all the check box*/
	public void clickAllCheckBox(){
		browser.clickGuiObject(".class", "Html.INPUT.checkbox", ".name", "all_slct");
	}
	
	/**Get Change requests ID*/
	public String getChangeRequestsID(){
		return getChangeRequestItemsTableCellValue(1,1);
	}
	
	/**Get Change requests ID*/
	public String getChangeRequestsItemsStatus(){
		return getChangeRequestItemsTableCellValue(1,3);
	}
	
	/**Get the first Change requests Items ID*/
	public String getChangeRequestsItemsID(){
		return getChangeRequestItemsTableCellValue(1,2);
	}
	
	/**Get Change Requests Items Table*/
	public IHtmlObject[] getChangeRequestsItemsTable(){
		return browser.getTableTestObject(".text",
				new RegularExpression("^Change Request ID Item ID Status Request Type.*",false));
	}
	
	/**Get All Change requests Items IDs*/
	public List<String> getChangeRequestsItemsIDs(){
		List<String> changeReqItemsIDs = new ArrayList<String>();
		
		IHtmlObject[] objs = this.getChangeRequestsItemsTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		for(int i=1; i<table.rowCount(); i++){
			changeReqItemsIDs.add(table.getCellValue(i, 2));
		}
		
		Browser.unregister(objs);
		
		return changeReqItemsIDs;
	}
	
	/**
	 * Get the specific cell value from change request items table
	 * @param row
	 * @param column
	 * @return
	 */
	public String getChangeRequestItemsTableCellValue(int row, int column){
		IHtmlObject[] objs = this.getChangeRequestsItemsTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		String toReturn = table.getCellValue(row, column).toString();
		Browser.unregister(objs);
		return toReturn;
	}
	
	/** Get change request items number */
	public int getchangeRequestItemsNum() {
		int num = 0;
		IHtmlObject[] objs = this.getChangeRequestsItemsTable();
		if(objs.length>0){
			IHtmlTable table = (IHtmlTable)objs[0];
			num = table.rowCount()-1;
		}else throw new ObjectNotFoundException("Object can't find.");

		Browser.unregister(objs);
		return num;
	}
	
	/**Click Change Requests tab*/
	public void clickChangeRequestsTab(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Change Requests");
	}
	
	/**
	 * Click Change request items
	 * @param reqId
	 */
	public void clickChangeRequestItmes(String reqId) {
		RegularExpression rex = new RegularExpression("\"getChangeRequestItemDetail\".+\"" + reqId + "\"", false);
		browser.clickGuiObject(".class","Html.A",".href",rex);
	}
	
	/** Click Facility tab  */
	public void clickFacility() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Facilities");
	}
	
	/**
	 * Clear up search criteria for change request items
	 */
	public void clearUpSearchCriteria(){
		this.selectSearchType("*All");
		this.setSearchTypeValue("");
		this.selectSearchStatus("*All");
		this.selectRequestType("*All");
		this.setFacilityName("");
		this.selectDateType("*All");
		this.setFromDate("");
		this.setToDate("");
	}
	
	/** Check "Next" button is available*/
	public boolean checkNext() {
		boolean flag = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",".text", "Next" );
		if(objs.length>0){
			flag=true;
		}
		Browser.unregister(objs);
		return flag;
	}
	
}
