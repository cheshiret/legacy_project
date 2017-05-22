/*
 * $Id: InvMgrChangeRequest.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.changeRequest;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author cguo
 */
public class InvMgrChangeRequest extends InventoryManagerPage {

	/**
	 * Script Name   : InvMgrChangeRequest
	 * Generated     : Dec 19, 2005 5:31:36 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2005/12/19
	 */

	private static InvMgrChangeRequest _instance = null;

	public static InvMgrChangeRequest getInstance() {
		if (null == _instance) {
			_instance = new InvMgrChangeRequest();
		}

		return _instance;
	}

	protected InvMgrChangeRequest() {
	}

	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("^Change Request ID # of Items Status Facility Name Support Case ID.*",false));
	}

	/**Create Support Case*/
	public void clickCreateSupportCase() {
		browser.clickGuiObject(".class", "Html.A", ".text","Create Support Case");
	}

	/**
	 * Select search Type
	 * @param type
	 */
	public void selectSearchType(String type) {
		browser.selectDropdownList(".id", "search-type-id", type);
	}

	/**
	 * Input search type value
	 * @param value
	 */
	public void setSearchTypeValue(String value) {
		browser.setTextField(".id", "search-type-value", value);
	}

	/**
	 * Select search status
	 * @param status
	 */
	public void selectSearchStatus(String status) {
		browser.selectDropdownList(".id", "search-status-id", status);
	}

	/**
	 * Input Facility Name
	 * @param name
	 */
	public void setFacilityName(String name) {
		String disable = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "search-facility-name");
		
		if(objs.length>0){
			disable = objs[0].getProperty(".disabled").toString();
			if(disable.equals("true")){
				browser.setTextField(".id", "search-facility-name", name);
			}
		}else{
			throw new ErrorOnDataException("Object doesn't exist.");
		}
		
		Browser.unregister(objs);
	}

	/**
	 * Select Date Type
	 * @param type
	 */
	public void selectDateType(String type) {
		browser.selectDropdownList(".id", "search-date-id", type);
	}

	/**
	 * Input FromDate Text field
	 * @param date
	 */
	public void setFromDate(String date) {
		browser.setTextField(".id", "search-date-from_ForDisplay", date);
	}

	/**
	 * Input ToDate Text Field
	 * @param date
	 */
	public void setToDate(String date) {
		browser.setTextField(".id", "search-date-to_ForDisplay", date);
	}

	/**Click Search button*/
	public void clickSearch() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}

	/**
	 * Set change request search criteria
	 * @param changeRequest---The data collection of change request
	 */
	public void setChangeRequestSearchCriteria(ChangeRequestsInfo changeRequest) {
		if (null!=changeRequest.searchType && changeRequest.searchType.length()>0) {
			this.selectSearchType(changeRequest.searchType);
			if(null!=changeRequest.searchTypeIdOrValue && changeRequest.searchTypeIdOrValue.length()>0) {
				this.setSearchTypeValue(changeRequest.searchTypeIdOrValue);
			}
		}
		if(null!=changeRequest.cRStatus && changeRequest.cRStatus.length()>0) {
			this.selectSearchStatus(changeRequest.cRStatus);
		}
		if(null!=changeRequest.facilityName && changeRequest.facilityName.length()>0) {
			this.setFacilityName(changeRequest.facilityName);
		}
		if(null!=changeRequest.searchDate && changeRequest.searchDate.length()>0) {
			this.selectDateType(changeRequest.searchDate);
			if(null!=changeRequest.searchFromDate && changeRequest.searchFromDate.length()>0) {
				this.setFromDate(changeRequest.searchFromDate);
			}
			if(null!=changeRequest.searchToDate && changeRequest.searchToDate.length()>0) {
				this.setToDate(changeRequest.searchToDate);
			}
		}

	}
	/**
	 * Search change request
	 * @param changeRequest
	 */
	public void searchChangeRequest(ChangeRequestsInfo changeRequest) {
		this.clearUpSearchCriteria();
		this.setChangeRequestSearchCriteria(changeRequest);
		this.clickSearch();
		this.waitLoading();
	}
	
	/**
	 * Search change request via change request id
	 * @param changeRequestID
	 */
	public void searchChangeRequest(String changeRequestID) {
		this.selectSearchType("Change Request ID");
		this.setSearchTypeValue(changeRequestID);
		
		this.clickSearch();
		this.waitLoading();
	}

	/**
	 * Click change request ID
	 * @param requestId
	 */
	public void clickChangeRequestId(String requestId) {
		RegularExpression rex = new RegularExpression("\"ViewChangeRequestDetail\".+\"" + requestId + "\"", false);
		browser.clickGuiObject(".text",requestId,".href",rex);
	}

	/**
	 * Click Change request items
	 * @param reqId
	 */
	public void clickChangeRequestItmes(String reqId) {
		RegularExpression rex = new RegularExpression("\"GetChangeRequestItemList\".+\"" + reqId + "\"", false);
		browser.clickGuiObject(".class","Html.A",".href",rex);
	}
	
	/**
	 * Get default value of drop down list
	 * @param property
	 * @return
	 */
	public String getDefaultValueOfDropDownList(String property) {
		return browser.getDropdownListValue(".id", property, 0);
	}
	/**
	 * Get all elements of specific drop down list
	 * @param id
	 * @return
	 */
	public String getAllDropDownListElements(String id) {
		List<String> groups = browser.getDropdownElements(".id", id);
		StringBuffer criterias = new StringBuffer();
		for (int i = 0; i < groups.size(); i++) {
			criterias.append(groups.get(i) + "#");
		}
		return criterias.toString();
	}
	
	/**Get "Search Type" value*/
	public String getSearchTypeValue(){
		return browser.getTextFieldValue(".id", "search-type-value");
	}
	
   /**
    * Get "Facility Name" value
    * @param isFacilityType
    * @return
    */
	public String getFacilityNameValue(boolean isFacilityType){
		if(isFacilityType){
			return browser.getTextFieldValue(".id", "search-facility-name_display");
		}else{
			return browser.getTextFieldValue(".id", "search-facility-name");
		}
	}
	
	/**Get "From" date value*/
	public String getFromDateValue(){
		return browser.getTextFieldValue(".id", "search-date-from_ForDisplay");
	}
	
	/**Get "To" date value*/
	public String getToDateValue(){
		return browser.getTextFieldValue(".id", "search-date-to_ForDisplay");
	}
	
	/**
	 * Check whether "Next" Button exist, if exist, click it
	 * @return
	 */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",".text", "Next");

		boolean toReturn = false;
		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}
		Browser.unregister(objs);

		this.waitLoading();

		return toReturn;
	}
	
	/**
	 * Get the change request IDs in the first page
	 * @return
	 */
	public List<String> getChangeRequestIDs(){
		List<String> changeRequestIDs = new ArrayList<String>();
		
		do{
			IHtmlObject[] objs = browser.getTableTestObject(".text",
					new RegularExpression("^Change Request ID # of Items Status Facility Name Support Case ID.*",false));
			IHtmlTable table = (IHtmlTable)objs[0];
			for(int i=0; i<table.rowCount(); i++){
				changeRequestIDs.add(table.getColumnValues(1).get(i));
			}
			Browser.unregister(objs);
		}while(gotoNext());
		
		return changeRequestIDs;
	}
	
	/** Retrive first page change request info in the change requests page */
	public List<List<String>> retriveFirstPageChangeRequestsInfo() {
		List<List<String>> changeRequestsInfo =new ArrayList<List<String>>();
		List<String> changeRequestsInfoRow = null;
		
		RegularExpression reg = new RegularExpression("^Change Request ID # of Items Status Facility Name Support Case ID.*",false);
		IHtmlObject[] changeRequesttable = browser.getTableTestObject(".class", "Html.TABLE", ".text", reg);
		IHtmlTable changeRequsetsTableGrid = (IHtmlTable) changeRequesttable[0];
		
		if(changeRequsetsTableGrid.getRowValues(0)!=null){
			changeRequestsInfoRow = changeRequsetsTableGrid.getRowValues(0);
			changeRequestsInfo.add(changeRequestsInfoRow);
			
			for (int row = 1; row < changeRequsetsTableGrid.rowCount(); row++) {
				changeRequestsInfoRow = changeRequsetsTableGrid.getRowValues(row);
				changeRequestsInfo.add(changeRequestsInfoRow);
			}
			Browser.unregister(changeRequesttable);
		} else throw new ErrorOnDataException("Data is null");	
		
		return changeRequestsInfo;
	}
	
	/**get table's all columns*/
	public int getTableColCount() {
		RegularExpression reg = new RegularExpression("^Change Request ID # of Items Status Facility Name Support Case ID.*",false);
		IHtmlObject[] changeRequestTable = browser.getTableTestObject(".text", reg);
		IHtmlTable bulletinTableGrid = (IHtmlTable) changeRequestTable[0];
		int colcount = bulletinTableGrid.columnCount();

		Browser.unregister(changeRequestTable);
		return colcount;
	}
	
	/**Get specific column's number*/
	public int getColNum(String colname) {
		List<List<String>> changeRequestInfo = new ArrayList<List<String>>();
		List<String> changeRequestInfoRow =new ArrayList<String>();
		int colcount = getTableColCount();
		
		changeRequestInfo = this.retriveFirstPageChangeRequestsInfo();
		changeRequestInfoRow = changeRequestInfo.get(0);
		for (int col = 0; col < colcount; col++) {
			if (changeRequestInfoRow.get(col).toString().trim().equalsIgnoreCase(
					colname)) {
				return col;
			}
		}

		return -1;
	}
	
	/**
	 * Clear up search criteria for change requests
	 */
	public void clearUpSearchCriteria(){
		this.selectSearchType("*All");
		this.setSearchTypeValue("");
		this.selectSearchStatus("*All");
		this.setFacilityName("");
		this.selectDateType("*All");
		this.setFromDate("");
		this.setToDate("");
	}
	
	/**
	 * Check object exist 
	 * @param propertyValue
	 * @return
	 */
	public boolean checkObjectExist(String propertyValue){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", propertyValue);
	}
	
	/**Click Last button*/
	public void clickLast(){
		 browser.clickGuiObject(".class", "Html.A", ".text", "Last");
	}
	
	public void clickChangeReqItemsTab(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Change Request Items");
	}

}
