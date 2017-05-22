/*
 * Created on Jan 12, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.distribution;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrRecipientScheduleMainPage extends FinanceManagerPage{

  /**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrRecipientScheduleMainPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrRecipientScheduleMainPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrRecipientScheduleMainPage getInstance(){
		if (null == _instance) {
			_instance = new FinMgrRecipientScheduleMainPage();
		}

		return _instance;
	}

	/**
	 * check specific page mark display or not
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("Distribution Recipient Schedule ID Active.*", false));
	}
	
	/** Click on Active link. */
	public void clickActive() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate");
	}

	/** Click on Deactivate link. */
	public void clickDeactive() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}

	/** Click on Go link. */
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}

	/** Click on Add New link. */
	public void clickAddNew() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New");
	}
	
	/**
	 * Select search by value from drop down list
	 * @param searchBy
	 */
	public void selectSearchBy(String searchBy) {
	  	browser.selectDropdownList(".id", "search_by", searchBy);
	}
	
	/**
	 * Input search value
	 * @param value
	 */
	public void setSearchValue(String value) {
	  	browser.setTextField(".id", "search_by_value", value);
	}
	
	/**
	 * Select date type from drop down list
	 * @param dateType
	 */
	public void selectDateType(String dateType) {
	  	browser.selectDropdownList(".id", "search_by_date", dateType);
	}
	
	/**Deselect Date Type*/
	public void deselectDateType() {
	  	browser.selectDropdownList(".id", "search_by_date", 0);
	}
	
	/**
	 * Input Start From Date
	 * @param fromDate
	 */
	public void setFromDate(String fromDate) {
	  	browser.setTextField(".id", "search_from_ForDisplay", fromDate);
	}
	
	/**
	 * Input Start to date
	 * @param toDate
	 */
	public void setToDate(String toDate) {
	  	browser.setTextField(".id", "search_to_ForDisplay", toDate);
	}
	
	/**
	 * Select show status from drop down list
	 * @param status
	 */
	public void selectShowStatus(String status) {
	  	browser.selectDropdownList(".id", "show_active", status);
	}
	
	/**
	 * Deselect show status
	 *
	 */
	public void deselectStatus() {
	  	browser.selectDropdownList(".id", "show_active", 0);
	}
	
	/**
	 * Select coverage from drop down list
	 * @param coverage
	 */
	public void selectCoverage(String coverage) {
	  	browser.selectDropdownList(".id", "coverage_location", coverage);
	}
	
	/**
	 * Deselect coverage
	 *
	 */
	public void deselectCoverage() {
	  	browser.selectDropdownList(".id", "coverage_location", 0);
	}
	/**
	 * Select Recipient Type from drop down list
	 * @param recipientType
	 */
	public void selectRecipientType(String recipientType) {
	  	browser.selectDropdownList(".id", "show_recipient_type", recipientType);
	}
	
	/**
	 * Deselect Recipient type
	 *
	 */
	public void deselectRecipientType() {
	  	browser.selectDropdownList(".id", "show_recipient_type", 0);
	}
	/**
	 * Select product category from drop down list
	 * @param prdCategory
	 */
	public void selectPrdCategory(String prdCategory) {
	  	browser.selectDropdownList(".id", "show_prd_cat_type", prdCategory);
	}
	
	/**
	 * Deselect product category
	 *
	 */
	public void deselectPrdCategory() {
	  	browser.selectDropdownList(".id", "show_prd_cat_type", 0);
	}
	
	/**
	 * Select Applicable Product Category from drop down list
	 * @param category
	 */
	public void selectAppPrdCategory(String category) {	
	  	browser.selectDropdownList(".id", "show_applicable_prd_cat_type", category);
	}
	
	/**
	 * Deselect Applicable product category
	 *
	 */
	public void deselectAppPrdCategory() {
	  	browser.selectDropdownList(".id", "show_applicable_prd_cat_type", 0);
	}
	/**
	 * Select item type from drop down list
	 * @param item
	 */
	public void selectItemType(String item) {
	  	browser.selectDropdownList(".id", "show_distribution_item_type", item);
	}
	
	/**
	 * Deselect Distribution Item Type
	 *
	 */
	public void deselectItemType() {
	  	browser.selectDropdownList(".id", "show_distribution_item_type", 0);
	}
	/**
	 * Select unit from drop down list
	 * @param unit
	 */
	public void selectUnit(String unit) {
	  	browser.selectDropdownList(".id", "show_distribution_unit", unit);
	}
	
	/**
	 * Deselect Distribution unit
	 *
	 */
	public void deselectUnit() {
	  	browser.selectDropdownList(".id", "show_distribution_unit", 0);
	}
	
	/**
	 * Select ticket category from drop down list
	 * @param ticketCat
	 */
	public void selectTicketCategory(String ticketCat) {
	  	browser.selectDropdownList(".id", "show_sales_category", ticketCat);
	}
	
	/**
	 * Deselect ticket category
	 *
	 */
	public void deselectTicketCategory() {
	  	browser.selectDropdownList(".id", "show_sales_category", 0);
	}
	
	/**
	 * Select Transaction type from drop down list
	 * @param transType
	 */
	public void selectTransType(String transType) {
	  	browser.selectDropdownList(".id", "show_trans_type", transType);
	}
	
	/**
	 * Deselect Transaction type
	 *
	 */
	public void deselectTransType() {
	  	browser.selectDropdownList(".id", "show_trans_type", 0);
	}
	/**
	 * Select Transaction Occurrence from drop down list
	 * @param transOcc
	 */
	public void selectTransOcc(String transOcc) {
	  	browser.selectDropdownList(".id", "show_trans_occ_type", transOcc);
	}
	
	/**
	 * Deselect Transaction Occurrence
	 *
	 */
	public void deselectTransOcc() {
	  	browser.selectDropdownList(".id", "show_trans_occ_type", 0);
	}
	
	/**
	 * This method used to clear all search criteria
	 *
	 */
	public void clearAllCriteria() {
	  	setSearchValue("");
	  	deselectDateType();
	  	setFromDate("");
	  	setToDate("");
	  	deselectStatus();
	  	deselectCoverage();
	  	deselectRecipientType();
	  	deselectPrdCategory();
	  	deselectAppPrdCategory();
	  	deselectItemType();
	  	deselectUnit();
	  	deselectTicketCategory();
	  	deselectTransType();
	  	deselectTransOcc();
	}
	
	/**
	 * This method used to set up all kinds of search criteria
	 * @param schedule
	 */
	public void setUpSearchCriteria(ScheduleData schedule) {
	  	clearAllCriteria();
	  	String log = "Search Recipient Schedule by ";
	  	if(schedule.searchBy != null && !schedule.searchBy.equals("")) {
	  	  selectSearchBy(schedule.searchBy);
	  	  if(schedule.searchBy.equalsIgnoreCase("Distribution Recipient Schedule ID")) {
	  	    setSearchValue(schedule.scheduleId);
	  	  }else if(schedule.searchBy.equalsIgnoreCase("Product")) {
	  	    setSearchValue(schedule.product);
	  	  }else if(schedule.searchBy.equalsIgnoreCase("Product Group")) {
	  	    setSearchValue(schedule.productGroup);
	  	  }else if(schedule.searchBy.equalsIgnoreCase("Recipient")) {
	  	    setSearchValue(schedule.recipient);
	  	  }else if(schedule.searchBy.equalsIgnoreCase("Recipient Permit ID")) {
	  	    setSearchValue(schedule.recipientPermit);
	  	  }else if(schedule.searchBy.equalsIgnoreCase("Revenue Location")) {
	  	    setSearchValue(schedule.location);
	  	  }
	  	  log = log+schedule.searchBy+" ";
	  	}
	  	if(schedule.dateType != null && !schedule.dateType.equals("")) {
	  	  selectDateType(schedule.dateType);
	  	  setFromDate(schedule.startDate);
	  	  setToDate(schedule.endDate);
	  	}
	  	if(schedule.activeStatus != null && !schedule.activeStatus.equals("")) {
	  	  selectShowStatus(schedule.activeStatus);
	  	  log = log+"status";
	  	}
	  	if(schedule.coverage != null && !schedule.coverage.equals("")) {
	  	  selectCoverage(schedule.coverage);
	  	log = log+"coverage";
	  	}
	  	if(schedule.recipientType != null && !schedule.recipientType.equals("")) {
	  	  selectRecipientType(schedule.recipientType);
	  	  log = log+"recipientType";
	  	}
	  	if(schedule.productCategory != null && !schedule.productCategory.equals("")) {
	  	  selectPrdCategory(schedule.productCategory);
	  	  log = log+"productCategory";
	  	}
	  	if(schedule.appPrdCategory != null && !schedule.appPrdCategory.equals("")) {
	  	  selectAppPrdCategory(schedule.appPrdCategory);
	  	  log = log+"appPrdCategory";
	  	}
	  	if(schedule.itemType != null && !schedule.itemType.equals("")) {
	  	  selectItemType(schedule.itemType);
	  	  log = log+"distribution item";
	  	}
	  	if(schedule.unit != null && !schedule.unit.equals("")) {
	  	  selectUnit(schedule.unit);
	  	  log = log+"unit";
	  	}
	  	if(schedule.ticketCategory != null && !schedule.ticketCategory.equals("")) {
	  	  selectTicketCategory(schedule.ticketCategory);
	  	  log = log+"ticket Category";
	  	}
	  	if(schedule.tranType != null && !schedule.tranType.equals("")) {
	  	  selectTransType(schedule.tranType);
	  	  log = log+"transaction type";
	  	}
	  	if(schedule.tranOccur != null && !schedule.tranOccur.equals("")) {
	  	  selectTransOcc(schedule.tranOccur);
	  	  log = log+"transaction Occurrence";
	  	}
	  	this.clickGo();
	  	waitLoading();
	  	logger.info(log);
	}
	
	/**
	 * This method used to verify Recipient Schedule correct after search
	 * @param schedule
	 */
	public void verifyRecipientSchedue(ScheduleData schedule) {
	  if(schedule.searchBy != null && !schedule.searchBy.equals("")) {
	    if(schedule.searchBy.equalsIgnoreCase("Distribution Recipient Schedule ID")) {
	  	    verifyScheduleInfo("Distribution Recipient Schedule ID",schedule.scheduleId);
	  	  }else if(schedule.searchBy.equalsIgnoreCase("Product Group")) {
	  	    verifyScheduleInfo("Product(Group or Applicable Category)",schedule.productGroup);
	  	  }else if(schedule.searchBy.equalsIgnoreCase("Recipient")) {
	  	    verifyScheduleInfo("Recipient",schedule.recipient);
	  	  }else if(schedule.searchBy.equalsIgnoreCase("Revenue Location")) {
	  	    verifyScheduleInfo("Revenue Location", schedule.location);
	  	  }
	  }
	  if(schedule.activeStatus != null && !schedule.activeStatus.equals("")) {
	    
		String isActive = "No";
		if(schedule.activeStatus.equalsIgnoreCase("Inactive")) {
		  isActive = "No";
		}else if(schedule.activeStatus.equalsIgnoreCase("Active")) {
		  isActive = "Yes";
		}
	  	verifyScheduleInfo("Active",isActive);
	  }
	  if(schedule.coverage != null && !schedule.coverage.equals("")) {
	    verifyScheduleInfo("Distribution Coverage",schedule.coverage);
	  }
	  if(schedule.recipientType != null && !schedule.recipientType.equals("")) {
	    verifyScheduleInfo("Recipient Type",schedule.recipientType);
	  }
	  if(schedule.productCategory != null && !schedule.productCategory.equals("")) {
	    verifyScheduleInfo("Product Category",schedule.productCategory);
	  }
	  if(schedule.appPrdCategory != null && !schedule.appPrdCategory.equals("")) {
	    verifyScheduleInfo("Product(Group or Applicable Category)",schedule.appPrdCategory);
	  }
	  if(schedule.itemType != null && !schedule.itemType.equals("")) {
	    verifyScheduleInfo("Distribution Item Type",schedule.itemType);
	  }
	  if(schedule.unit != null && !schedule.unit.equals("")) {
	    verifyScheduleInfo("Unit",schedule.unit);
	  }
	  if(schedule.ticketCategory != null && !schedule.ticketCategory.equals("")) {
	    verifyScheduleInfo("Ticket Category",schedule.ticketCategory);
	  }
	  if(schedule.tranType != null && !schedule.tranType.equals("")) {
	    verifyScheduleInfo("Transaction Type",schedule.tranType);
	  }
	  if(schedule.tranOccur != null && !schedule.tranOccur.equals("")) {
	    verifyScheduleInfo("Transaction Occurrence",schedule.tranOccur);
	  }
	}
	
	/**
	 * Verify Specific recipient schedule In Search List
	 * @param id recipient schedule id
	 */
	public void verifyRecipientInSearchList(String id) {
		RegularExpression rex = new RegularExpression(
				"Distribution Recipient Schedule ID Active.*", false);
		IHtmlObject[] objs = null;
		boolean found = false;
		do {
			objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", rex);
			IHtmlTable tableGrid = (IHtmlTable)objs[0];
			for (int i = 1; i < tableGrid.rowCount(); i++) {
				if (tableGrid.getCellValue(i, 1).equals(id)) {
				  found = true;
				  break;
				}
			}
			if (found) {
			  	logger.info("Recipient Schedule " + id + " is in the search result!");
				break;
			}
			if (!hasNext() && !found) {
				Browser.unregister(objs);
				throw new ItemNotFoundException("Search Recipient Schedule Fail!");
			}
		} while (gotoNext());

		Browser.unregister(objs);
	}
	
	/**
	 * Search Recipient Schedule By Schedule Id
	 * @param scheId
	 */
	public void searchByScheduleId(String scheId) {
	  	selectSearchBy("Distribution Recipient Schedule ID");
	  	setSearchValue(scheId);
	  	clickGo();
	  	waitLoading();
	}
	
	/**
	 * Select specific schedule check box
	 * @param scheId
	 */
	public void selectScheduleCheckBox(String scheId) {
	  	browser.selectCheckBox(".id","row_0_checkbox",".value", scheId);
	}
	
	/**
	 * Click specific schedule
	 * @param scheId
	 */
	public void clickSchedule(String scheId) {
	  	browser.clickGuiObject(".class", "Html.A", ".text", scheId);
	}
	
	/**
	 * This method used to change schedule status
	 * @param schdId
	 * @param status
	 */
	public void changeScheduleStatus(String schdId,String status) {
	  	logger.info("Start to Change Schedule Status to " + status);
	  	
	  	searchByScheduleId(schdId);
	  	selectScheduleCheckBox(schdId);
	  	if(status.equalsIgnoreCase("Active")){
	  	  clickActive();
	  	}else if(status.equalsIgnoreCase("Inactive")){
	  	  clickDeactive();
	  	}else{
	  	  throw new ItemNotFoundException("Unkown Status "+status);
	  	}
	  	waitLoading();
	}
	
	/**
	 * Verify specific schedule status is given status
	 * @param scheduleId
	 * @param status
	 */
	public void verifyStatus(String scheduleId,String status) {
	   	logger.info("Start to verify schedule " + scheduleId + " is " + status);
	   	
		searchByScheduleId(scheduleId);
	   	String isActive = "No";
	   	if(status.equalsIgnoreCase("Inactive")){
	      isActive = "No";
	    }else if(status.equalsIgnoreCase("Active")){
	      isActive = "Yes";
	    }else{
	  	  throw new ItemNotFoundException("Unkown Status "+status);
	    }
	   	verifyScheduleInfo("Active", isActive);
	 }
	
	  /**
	   * Verify specific column value is the same with given value
	   * @param colName column Name
	   * @param value
	   */
	  public void verifyScheduleInfo( String colName, String value ) {
	    int colNum = getColNum( colName );
	    RegularExpression rex = new RegularExpression("Distribution Recipient Schedule ID Active.*", false );
	    IHtmlObject[] objs = null;
	    do {
	      objs = browser.getHtmlObject( ".class", "Html.TABLE", ".text", rex );
	      IHtmlTable tableGrid = (IHtmlTable)objs[0];
	      if(tableGrid.rowCount()>1){
	        for ( int i = 1; i < tableGrid.rowCount(); i++ ) {
		        if ( null != tableGrid.getCellValue( i, colNum ) ) {
		          if ( !tableGrid.getCellValue( i, colNum ).trim().equals( value ) ) {
		            Browser.unregister( objs );
		            logger.error( "Schedule Info " + tableGrid.getCellValue( i, colNum ) + " is not Correct! " );
		            throw new ItemNotFoundException( tableGrid.getCellValue( i, colNum ) + " is different with given value " + value );
		          }
		        }
		      }
	      }else{
	        Browser.unregister( objs );
	        throw new ItemNotFoundException("No Schedule Found.");
	      }
	    }
	    while ( gotoNext() );

	    Browser.unregister( objs );
	  }
	  
	  /**
	   * Check whether there have next page,if have,click Next Button
	   * @return
	   */
	  public boolean gotoNext() {
	    IHtmlObject[] objs = browser.getHtmlObject( ".class", "Html.A", ".text", "Next");
	    boolean toReturn = false;
	    if ( objs.length > 0 ) {
	      toReturn = true;
	      ((ILink)objs[0]).click();
	    }
	    Browser.unregister( objs );

	    this.waitLoading();

	    return toReturn;
	  }
	  
	  public boolean hasNext() {
	    IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Next");
		boolean toReturn = false;
		if (objs.length > 0) {
			toReturn = true;
		}
		Browser.unregister(objs);

		return toReturn;
	  }
	  
	  public String getMessage(){
		  String message = "";
		  IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".id", "statusMessages");
		  if(objs.length>0){
			  message = objs[0].text();
		  }
		  
		  Browser.unregister(objs);
		  return message;	  
	  }
	  
	 /**
	   * Get Column Number by Column Name
	   * @param colName
	   * @return Column Number
	   */
	  public int getColNum( String colName ) {
	    RegularExpression rex = new RegularExpression("Distribution Recipient Schedule ID Active.*", false );
	    IHtmlObject[] objs = browser.getHtmlObject( ".class", "Html.TABLE", ".text", rex );
	    if ( null != objs && objs.length > 0 ) {
	      IHtmlTable tableGrid = (IHtmlTable)objs[0];
	      int colCounts = tableGrid.columnCount();
	      for ( int i = 0; i < colCounts; i++ ) {
	        if ( tableGrid.getCellValue( 0, i ).toString().equalsIgnoreCase( colName ) ) {
	          Browser.unregister( objs );
	          return i;
	        }
	      }
	    }
	    Browser.unregister( objs );
	    return -1;
	  }
	  
	  public List<ScheduleData> getAllRecordOnPage(){
			IHtmlObject objs[] = null;
			IHtmlTable table = null;
			List<ScheduleData> records = new ArrayList<ScheduleData>();
			int rows;
			int columns;
			ScheduleData info;
			
			RegularExpression rex = new RegularExpression("Distribution Recipient Schedule ID Active.*", false );
			objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", rex);
			
			if(objs.length < 1) {
						throw new ItemNotFoundException("Can't find recipient schedule table object.");
					}
			
			table = (IHtmlTable)objs[0];
			rows = table.rowCount();
			columns = table.columnCount();
			logger.info("Find record on page, "+rows+" rows, "+columns+" columns.");
			
			for(int i = 1; i < rows; i ++) {
				info = new ScheduleData();
				info.scheduleId = table.getCellValue(i, table.findColumn(0, "Distribution Recipient Schedule ID"));
				info.activeStatus = table.getCellValue(i, table.findColumn(0, "Active"));
				info.coverage = table.getCellValue(i, table.findColumn(0, "Distribution Coverage"));
				info.recipientType = table.getCellValue(i, table.findColumn(0, "Recipient Type"));
				info.recipient = table.getCellValue(i, table.findColumn(0, "Recipient"));
				info.recipientPermit = table.getCellValue(i, table.findColumn(0, "Recipient Permit"));
				info.effectiveDate = table.getCellValue(i, table.findColumn(0, "Effective Date"));
				info.productCategory = table.getCellValue(i, table.findColumn(0, "Product Category"));
				info.rate = table.getCellValue(i, table.findColumn(0, "Rate"));
				info.unit = table.getCellValue(i, table.findColumn(0, "Unit"));
				info.appRate = table.getCellValue(i, table.findColumn(0, "Apply Rate"));

				records.add(info);			
			}
			
			
			Browser.unregister(objs);
			return records;
		}
	  
	  public void searchByProduct(String product, String activeStatus) {
		  this.selectSearchBy("Product");
		  this.setSearchValue(product);
		  this.selectShowStatus(activeStatus);
		  this.clickGo();
		  this.waitLoading();
	  }
}
