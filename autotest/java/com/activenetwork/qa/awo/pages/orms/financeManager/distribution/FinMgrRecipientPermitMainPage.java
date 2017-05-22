/*
 * Created on Jan 17, 2010
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
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrRecipientPermitMainPage extends FinanceManagerPage {

  /**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrRecipientPermitMainPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrRecipientPermitMainPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrRecipientPermitMainPage getInstance(){
		if (null == _instance) {
			_instance = new FinMgrRecipientPermitMainPage();
		}

		return _instance;
	}

	/**
	 * check specific page mark display or not
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("^Recipient Permit ID Active.*", false));
	}
	
	/**
	 * Select search type from drop down list
	 * @param searchType
	 */
	public void selectSearchType(String searchType) {
	  	browser.selectDropdownList(".id", "perm_search_by", searchType);
	}
	
	/**
	 * Input search value
	 * @param value
	 */
	public void setSearchValue(String value) {
	  	browser.setTextField(".id", "perm_search_by_value", value);
	}
	
	/**
	 * Select date type from drop down list
	 * @param dateType
	 */
	public void selectDateType(String dateType) {
	  	browser.selectDropdownList(".id", "perm_search_by_date", dateType);
	}
	
	/**
	 * Input from date
	 * @param fromDate
	 */
	public void setFromDate(String fromDate) {
	  	browser.setTextField(".id", "perm_search_from_ForDisplay", fromDate);
	}
	
	/**
	 * Input end date
	 * @param toDate
	 */
	public void setToDate(String toDate) {
	  	browser.setTextField(".id", "perm_search_to_ForDisplay", toDate);
	}
	
	/**
	 * Select show status from drop down list
	 * @param status
	 */
	public void selectShowStatus(String status) {
	  	browser.selectDropdownList(".id", "perm_show_active", status);
	}
	
	public void deselectShowStatus() {
	  	browser.selectDropdownList(".id", "perm_show_active", 0);
	}
	
	/**
	 * Select recipient type from drop down list
	 * @param recipientType
	 */
	public void selectRecipientType(String recipientType) {
	  	browser.selectDropdownList(".id", "perm_recipient_type", recipientType);
	}
	
	public void deselectRecipientType() {
	  	browser.selectDropdownList(".id", "perm_recipient_type", 0);
	}
	
	/**
	 * Select disbursement frequency type
	 * @param frequency
	 */
	public void selectFrequencyType(String frequency) {
	  	browser.selectDropdownList(".id", "disburse_frequency_type", frequency);
	}
	
	public void deselectFrequencyType() {
	  	browser.selectDropdownList(".id", "disburse_frequency_type", 0);
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
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$", false));
	}

	/** Click on Add New link. */
	public void clickAddNew() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New");
	}

	/**
	 * Search Recipient permit by permit ID
	 * @param permitId
	 */
	public void searchByPermitId(String permitId) {
	  	selectSearchType("Recipient Permit ID");
	  	setSearchValue(permitId);
	  	clickGo();
	  	waitLoading();
	}
	
	public List<ScheduleData> getAllRecipientPermitRecord(){
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<ScheduleData> records = new ArrayList<ScheduleData>();
		int rows;
		int columns;
		ScheduleData info;
		
		RegularExpression rex = new RegularExpression("^Recipient Permit ID Active.*", false);
		objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", rex);
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find recipient permit table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			info = new ScheduleData();
			info.recipientPermitID = table.getCellValue(i, table.findColumn(0, "Recipient Permit ID"));
			info.activeStatus = table.getCellValue(i, table.findColumn(0, "Active"));
			info.revenueLocation = table.getCellValue(i, table.findColumn(0, "Revenue Location"));
			info.recipientType = table.getCellValue(i, table.findColumn(0, "Recipient Type"));
			info.recipient = table.getCellValue(i, table.findColumn(0, "Recipient"));
			info.effectiveDate = table.getCellValue(i, table.findColumn(0, "Effective Date"));
			info.startDate = table.getCellValue(i, table.findColumn(0, "Permit Start Date"));
			info.endDate = table.getCellValue(i, table.findColumn(0, "Permit End Date"));

			records.add(info);			
		}
		
		
		Browser.unregister(objs);
		return records;
	}
	
	/**
	 * This method used to clear all search criteria
	 *
	 */
	public void clearAllSearchCriteria() {
	  	setSearchValue("");
	  	setFromDate("");
	  	setToDate("");
	  	deselectShowStatus();
	  	deselectRecipientType();
	  	deselectFrequencyType();
	}
	
	/**
	 * Select specific permit check box
	 * @param permitID
	 */
	public void selectPermitCheckBox(String permitID) {
	  	browser.selectCheckBox(".value", permitID);
	}
	public void selectPermitCheckBoxAfterSearch(String permitID) {
		Property[] trPro = new Property[2];
		trPro[0] = new Property(".class","Html.TR");
		trPro[1] = new Property(".text",new RegularExpression(permitID + ".*",false));
		
		IHtmlObject[] objs = browser.getHtmlObject(trPro);
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found recipient permit row info. permitID = " +permitID);
		}
		
		Property[] checkPro = new Property[1];
		checkPro[0] = new Property(".class","Html.INPUT.checkbox");
	  	browser.selectCheckBox(checkPro, 0,objs[0]);
	  	Browser.unregister(objs);
	}
	
	
	/**
	 * Click specific permit
	 * @param permitId
	 */
	public void clickPermit(String permitId) {
	  	browser.clickGuiObject(".class", "Html.A", ".text", permitId);
	}
	
	/**
	 * This method used to set up different search criteria
	 * @param permit
	 */
	public void setUpSearchCriteria(ScheduleData permit) {
		  clearAllSearchCriteria();
		  
		  String log = "Search Recipient Permit by ";
		  if(permit.searchBy != null && !permit.searchBy.equals("")) {
		    selectSearchType(permit.searchBy);
		    if(permit.searchBy.equalsIgnoreCase("Recipient Permit ID")) {
		      setSearchValue(permit.scheduleId);
		    }else if(permit.searchBy.equalsIgnoreCase("Disbursement Frequency")) {
		      setSearchValue(permit.frequency);
		    }else if(permit.searchBy.equalsIgnoreCase("Recipient")) {
		      setSearchValue(permit.recipient);
		    }else if(permit.searchBy.equalsIgnoreCase("Revenue Location")) {
		      setSearchValue(permit.location);
		    }
		    log = log+permit.searchBy+" ";
		  }
		  if(permit.dateType != null && !permit.dateType.equals("")) {
		    selectDateType(permit.dateType);
		    setFromDate(permit.fromDate);
		    setToDate(permit.toDate);
		    log = log+permit.dateType+" ";
		  }
		  if(permit.activeStatus != null && !permit.activeStatus.equals("")) {
		    selectShowStatus(permit.activeStatus);
		    log = log+"status ";
		  }
		  if(permit.recipientType != null && !permit.recipientType.equals("")) {
		    selectRecipientType(permit.recipientType);
		    log = log+"Recipient Type ";
		  }
		  if(permit.frequencyType != null && !permit.frequencyType.equals("")) {
		    selectFrequencyType(permit.frequencyType);
		    log = log+"Frequency Type ";
		  }
		  this.clickGo();
		  this.waitLoading();
		  logger.info(log);
	}
	
	/**
	 * This method used to verify Permit list contain correct information
	 * @param permit
	 */
	public void verifyPermitInfo(ScheduleData permit) {
	  if(permit.searchBy != null && !permit.searchBy.equals("")) {
	    if(permit.searchBy.equalsIgnoreCase("Recipient Permit ID")) {
	      	verifyPermitInfo("Recipient Permit ID", permit.scheduleId);
	  	  }else if(permit.searchBy.equalsIgnoreCase("Disbursement Frequency")) {
	  	    verifyPermitInfo("Disbursement Frequency", permit.frequency);
	  	  }else if(permit.searchBy.equalsIgnoreCase("Recipient")) {
	  	    verifyPermitInfo("Recipient", permit.recipient);
	  	  }else if(permit.searchBy.equalsIgnoreCase("Revenue Location")) {
	  	    verifyPermitInfo("Revenue Location", permit.revenueLocation);
	  	  }
	  }
	  if(permit.dateType != null && !permit.dateType.equals("")) {
	    if(permit.dateType.equalsIgnoreCase("Effective Date")) {
	      	verifyDateInGivenRange("Effective Date", permit.fromDate,permit.toDate);
	    }else if(permit.dateType.equalsIgnoreCase("Permit Start Date")) {
	      	verifyDateInGivenRange("Permit Start Date", permit.fromDate, permit.toDate);
	    }else if(permit.dateType.equalsIgnoreCase("Permit End Date")) {
	      	verifyDateInGivenRange("Permit End Date", permit.fromDate, permit.toDate);
	    }
	  }
	  if(permit.activeStatus != null && !permit.activeStatus.equals("")) {
	    
		String isActive = "No";
		if(permit.activeStatus.equalsIgnoreCase("Inactive")) {
		  isActive = "No";
		}else if(permit.activeStatus.equalsIgnoreCase("Active")) {
		  isActive = "Yes";
		}
		verifyPermitInfo("Active",isActive);
	  }
	  if(permit.recipientType != null && !permit.recipientType.equals("")) {
	    verifyPermitInfo("Recipient Type", permit.recipientType);
	  }
	  if(permit.frequencyType != null && !permit.frequencyType.equals("")) {
	    verifyPermitFrequencyType(permit.frequencyType);
	  }
	}
	
	/**
	 * This method is used to verfiy given column date value is in the given date range
	 * @param colName
	 * @param fromDate
	 * @param toDate
	 */
	public void verifyDateInGivenRange(String colName,String fromDate,String toDate) {
	    int colNum = getColNum(colName);
		RegularExpression rex = new RegularExpression("^Recipient Permit ID Active.*", false);
		IHtmlObject[] objs = null;
		do {
		  	objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", rex);
		  	IHtmlTable tableGrid = (IHtmlTable) objs[0];
			for (int i = 1; i < tableGrid.rowCount(); i++) {
				if (null != tableGrid.getCellValue(i, colNum)) {
					String date = DateFunctions.formatDate(tableGrid.getCellValue(i, colNum),"M/d/yyyy");
					int value = DateFunctions.compareDates(fromDate,date) + DateFunctions.compareDates(toDate,date);
					if(value==2||value==-2) {
					  	Browser.unregister(objs);
					  	logger.error("Date "+date+" is not in the given date range!");
					  	throw new ItemNotFoundException("Date " + date + " is not in the given date range!");
					}
				}
			}
		} while (gotoNext());

		Browser.unregister(objs);
	}
	/**
	 * This method used to change Permit status
	 * @param schdId
	 * @param status
	 */
	public void changePermitStatus(String schdId, String status) {
	  	logger.info("Start to Change Permit Status to " + status);
	  	
	  	searchByPermitId(schdId);
	  	selectPermitCheckBoxAfterSearch(schdId);//updated by pzhu
	  	if(status.equalsIgnoreCase("Active")) {
	  	  clickActive();
	  	}else if(status.equalsIgnoreCase("Inactive")) {
	  	  clickDeactive();
	  	}else{
	  	  throw new ItemNotFoundException("Unkown Status " + status);
	  	}
	  	waitLoading();
	}
	
	/**
	 * Verify specific Recipient Permit status is given status
	 * @param scheduleId
	 * @param status
	 */
	public void verifyPermitStatus(String permitId, String status) {
	   	logger.info("Start to verify Permit "+permitId+" is " + status);
	   	
		searchByPermitId(permitId);
	   	String isActive = "No";
	   	if(status.equalsIgnoreCase("Inactive")) {
	      isActive = "No";
	    }else if(status.equalsIgnoreCase("Active")) {
	      isActive = "Yes";
	    }else{
	  	  throw new ItemNotFoundException("Unkown Status " + status);
	    }
	   	verifyPermitInfo("Active", isActive);
	 }
	
	/**
	 * Verify Specific recipient permit In Search List
	 * @param id recipient permit id
	 */
	public void verifyPermitInSearchList(String id) {
		RegularExpression rex = new RegularExpression("^Recipient Permit ID Active.*", false);
		IHtmlObject[] objs = null;
		boolean found = false;
		do {
			objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", rex);
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
			for (int i = 1; i < tableGrid.rowCount(); i++) {
				if (tableGrid.getCellValue(i, 1).toString().equals(id)) {
				  found = true;
				  break;
				}
			}
			if (found) {
			  	logger.info("Recipient Permit " + id + " is in the search result!");
				break;
			}
			if (!hasNext() && !found) {
				Browser.unregister(objs);
				throw new ItemNotFoundException("Search Recipient Permit Fail!");
			}
		} while (gotoNext());

		Browser.unregister(objs);
	}
	  /**
	   * Verify specific column value is the same with given value
	   * @param colName column Name
	   * @param value
	   */
	  public void verifyPermitInfo( String colName, String value ) {
	    int colNum = getColNum( colName );
	    RegularExpression rex = new RegularExpression("^Recipient Permit ID Active.*", false );
	    IHtmlObject[] objs = null;
	    do {
	      objs = browser.getHtmlObject( ".class", "Html.TABLE", ".text", rex );
	      IHtmlTable tableGrid = (IHtmlTable) objs[0];
	      if(tableGrid.rowCount() > 1){
	        for ( int i = 1; i < tableGrid.rowCount(); i++ ) {
		        if ( null != tableGrid.getCellValue( i, colNum ) ) {
		          if ( !tableGrid.getCellValue( i, colNum ).toString().trim().equals( value ) ) {
		            Browser.unregister( objs );
		            logger.error( "Permit Info " + tableGrid.getCellValue( i, colNum ) + " is not Correct! " );
		            throw new ItemNotFoundException( tableGrid.getCellValue( i, colNum ) + " is different with given value " + value );
		          }
		        }
		      }
	      }else{
	        Browser.unregister( objs );
	        throw new ItemNotFoundException("No Permit Found.");
	      }
	     
	    }
	    while ( gotoNext() );

	    Browser.unregister( objs );
	  }
	  
	  /**
	   * This method used to verify perimt list correct after searched by frequency type
	   * @param colName
	   * @param value
	   */
	  public void verifyPermitFrequencyType(String value) {
	    int colNum = getColNum( "Disbursement Frequency" );
	    RegularExpression rex = new RegularExpression("^Recipient Permit ID Active.*", false );
	    IHtmlObject[] objs = null;
	    do {
		      objs = browser.getHtmlObject( ".class", "Html.TABLE", ".text", rex );
		      IHtmlTable tableGrid = (IHtmlTable) objs[0];
		      if(tableGrid.rowCount()>1){
		        for ( int i = 1; i < tableGrid.rowCount(); i++ ) {
			        if ( null != tableGrid.getCellValue( i, colNum ) ) {
			          if ( tableGrid.getCellValue( i, colNum ).toString().indexOf( value )==-1) {
			            Browser.unregister( objs );
			            throw new ItemNotFoundException( "Permit Info " + tableGrid.getCellValue( i, colNum ) + " is not Correct! " );
			          }
			        }
			      }
		      }else{
		        Browser.unregister( objs );
		        throw new ItemNotFoundException("No Permit Found.");
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
	  /**
	   * This method is used to goto first page
	   *
	   */
	  public void gotoFirstPg() {
		  IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",".text", "First");
		  if (objs.length > 0) {
			  ((ILink)objs[0]).click();
		  }
		  Browser.unregister(objs);

		  this.waitLoading();
	  }
	  
	 /**
	   * Get Column Number by Column Name
	   * @param colName
	   * @return Column Number
	   */
	  public int getColNum( String colName ) {
	    RegularExpression rex = new RegularExpression("^Recipient Permit ID Active.*", false );
	    IHtmlObject[] objs = browser.getHtmlObject( ".class", "Html.TABLE", ".text", rex );
	    if ( null != objs && objs.length > 0 ) {
	      IHtmlTable tableGrid = (IHtmlTable)objs[0];
	      int colCounts = tableGrid.columnCount();
	      for ( int i = 0; i < colCounts; i++ ) {
	        if ( tableGrid.getCellValue( 0, i ).equalsIgnoreCase( colName ) ) {
	          Browser.unregister( objs );
	          return i;
	        }
	      }
	    }
	    Browser.unregister( objs );
	    return -1;
	  }

	  public void searchByRecipientName(String recipientName, String activeStatus) {
		  logger.info("Search by recipient name "+recipientName);
		  this.selectSearchType("Recipient");
		  this.setSearchValue(recipientName);
		  this.selectShowStatus(activeStatus);
		  this.clickGo();
		  this.waitLoading();
	  }
	  
	  public String searchByRecipientPermitInfo(ScheduleData permitInfo) {
			String permitID = null;
			
			this.searchByRecipientName(permitInfo.recipient, permitInfo.activeStatus);
			
			RegularExpression rex = new RegularExpression("^Recipient Permit ID Active.*", false );
		    IHtmlObject[] objs = browser.getHtmlObject( ".class", "Html.TABLE", ".text", rex );
		    if(objs.length<1)
		    	throw new ItemNotFoundException("Could not find Recipient Permit list on page.");
		    IHtmlTable table = (IHtmlTable)objs[0];
		    
		    if(table.rowCount()-1<1)//remove header row
		    	return null;
		    
		    //Deal with the single page situation
		    for(int i=1;i<table.rowCount();i++) {
		    	String recipientType = table.getCellValue(i, 4);
		    	String recipient = table.getCellValue(i, 5);
		    	String effDate = table.getCellValue(i, 6);
		    	String startDate = table.getCellValue(i, 7);
		    	String endDate = table.getCellValue(i, 8);
		    	
		    	if(recipientType.equals(permitInfo.recipientType)
		    	&& recipient.equals(permitInfo.recipient)
		    	&& DateFunctions.compareDates(effDate, permitInfo.effectiveDate)==0
		    	&& DateFunctions.compareDates(startDate, permitInfo.startDate)==0
		    	&& ((StringUtil.notEmpty(permitInfo.endDate) && DateFunctions.compareDates(endDate, permitInfo.endDate)==0)
		    	|| (StringUtil.isEmpty(permitInfo.endDate)))) {
		    		permitID = table.getCellValue(i, 1);
		    		break;
		    	}
		    }
		    Browser.unregister(objs);
			return permitID;
		}
	  
	  public void searchByStatus(String status) {
		  logger.info("Search by "+status+" status.");
		  selectShowStatus(status);
		  clickGo();
		  ajax.waitLoading();
		  this.waitLoading();
	  }
	  
	  public void selectAllRecipientIDs() {
		  browser.selectCheckBox("Html.Name", "all_slct");
	  }
}
