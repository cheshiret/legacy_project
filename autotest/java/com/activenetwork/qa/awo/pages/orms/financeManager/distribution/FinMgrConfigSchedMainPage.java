/*
 * Created on Jan 19, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.distribution;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrConfigSchedMainPage extends FinanceManagerPage {

  /**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrConfigSchedMainPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrConfigSchedMainPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrConfigSchedMainPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrConfigSchedMainPage();
		}

		return _instance;
	}

	/**
	 * check specific page mark display or not
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("^Distribution Configuration Schedule ID Active.*", false));
	}
	
	public void selectSearBy(String searchBy) {
	  	browser.selectDropdownList(".id", "search_by", searchBy);
	}
	
	public void setSearchByValue(String searchValue) {
	  	browser.setTextField(".id", "search_by_value", searchValue);
	}
	
	public void selectDateType(String dateType) {
	  	browser.selectDropdownList(".id", "search_by_date", dateType);
	}
	
	public void setFromDate(String fromDate) {
	  	browser.setTextField(".id", "search_from_ForDisplay", fromDate);
	}
	
	public void setToDate(String toDate) {
	  	browser.setTextField(".id", "search_to_ForDisplay", toDate);
	}
	
	public void selectShowStatus(String status) {
	  	browser.selectDropdownList(".id", "show_active", status);
	}
	
	public void deselectShowStatus() {
	  	browser.selectDropdownList(".id", "show_active", 0);
	}
	
	public void selectPaymentOrRefund(String str) {
	  	browser.selectDropdownList(".id", "show_paymentrefund", str);
	}
	
	public void deselectPaymentOrRefund() {
	  	browser.selectDropdownList(".id", "show_paymentrefund", 0);
	}
	
	public void selectPayGroup(String payGroup) {
	  	browser.selectDropdownList(".id", "show_paymentgroup", payGroup);
	  	waitLoading();
	}
	
	public void deselectPayGroup() {
	  	browser.selectDropdownList(".id", "show_paymentgroup", 0);
	  	waitLoading();
	}
	
	public void selectPayType(String payType) {
	  	browser.selectDropdownList(".id", "show_paymenttypeID", payType);
	}
	
	public void deselectPayType() {
	  	browser.selectDropdownList(".id", "show_paymenttypeID", 0);
	}
	
	public void selectReconcilable(String reconcilable) {
	  	browser.selectDropdownList(".id", "show_reconcilable", reconcilable);
	}
	
	public void deselectReconcilable() {
	  	browser.selectDropdownList(".id", "show_reconcilable", 0);
	}
	
	public void selectDistributable(String distributable) {
	  	browser.selectDropdownList(".id", "show_distributable", distributable);
	}
	
	public void deselectDistributable() {
	  	browser.selectDropdownList(".id", "show_distributable", 0);
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
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Go|Search",false));
	}

	/** Click on Add New link. */
	public void clickAddNew() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Add New", false));
	}
	
	/**
	 * This method used to clear all search criteria
	 *
	 */
	public void clearAllSearchCriteria() {
	  	setSearchByValue("");
	  	setFromDate("");
	  	setToDate("");
	  	deselectShowStatus();
	  	deselectPaymentOrRefund();
	  	deselectPayGroup();
	  	deselectPayType();
	  	deselectReconcilable();
	  	deselectDistributable();
	}
	
	/**
	 * Select specific schedule check box
	 * @param schedule id
	 */
	public void selectScheduleCheckBox(String scheduleId) {
	  	browser.selectCheckBox(".id","row_0_checkbox",".value",scheduleId);//add id to handle both RFT and selenium
	}
//	public void selectScheduleCheckBoxAfterSearch(String scheduleId) {//added pzhu, for fee search and active.
//	  	browser.selectCheckBox(".value",scheduleId,1);
//	}
	
	/**
	 * Click specific schedule
	 * @param schedule Id
	 */
	public void clickSchedule(String scheduleId) {
	  	browser.clickGuiObject(".class", "Html.A", ".text", scheduleId);
	}
	
	public void searchByScheduleId(String scheduleId) {
	  	selectSearBy("Distribution Configuration Schedule ID");
	  	setSearchByValue(scheduleId);
	  	clickGo();
	  	waitLoading();
	}
	
	public void checkScheduleExists(String scheduleId) {
	  	if(!browser.checkHtmlObjectExists(".class", "Html.A", ".text", scheduleId)) {
	  	   throw new ItemNotFoundException("Not Found given Schedule " + scheduleId);
	  	}
	}
	
	public void gotoRunDistributionPage() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Run Distribution");
	}
	
	/**
	 * This method used to search schedule by different criterias
	 * @param schedule
	 */
	public void setUpSearchCriteria(ScheduleData schedule) {
	  	String log = "Search Configuration Schedule by ";
	  	clearAllSearchCriteria();
	  	if(schedule.searchBy!=null&&!schedule.searchBy.equals("")) {
	  	  selectSearBy(schedule.searchBy);
	  	  if(schedule.searchBy.equalsIgnoreCase("Distribution Configuration Schedule ID")) {
	  	    setSearchByValue(schedule.scheduleId);
	  	  }else if(schedule.searchBy.equalsIgnoreCase("Location")) {
	  	    setSearchByValue(schedule.location);
	  	  }else{
	  	    throw new ItemNotFoundException("Unknown Search Type.");
	  	  }
	  	  log = log+schedule.searchBy+" ";
	  	}
	  	if(schedule.dateType!=null&&!schedule.dateType.equals("")) {
		    selectDateType(schedule.dateType);
		    setFromDate(schedule.fromDate);
		    setToDate(schedule.toDate);
		    log = log+schedule.dateType+" ";
		}
	  	if(schedule.activeStatus!=null&&!schedule.activeStatus.equals("")) {
		    selectShowStatus(schedule.activeStatus);
		    log = log+"status ";
		}
	  	if(schedule.paymentOrRefund!=null&&!schedule.paymentOrRefund.equals("")) {
	  	  	selectPaymentOrRefund(schedule.paymentOrRefund);
	  	  	log = log+schedule.paymentOrRefund+" ";
	  	}
	  	if(schedule.payGroup!=null&&!schedule.payGroup.equals("")) {
	  	  	selectPayGroup(schedule.payGroup);
	  	  	log = log+"pay Group ";
	  	}
	  	if(schedule.payType!=null&&!schedule.payType.equals("")) {
	  	  	selectPayType(schedule.payType);
	  	  	log = log+"pay Type ";
	  	}
	  	if(schedule.reconcilable!=null&&!schedule.reconcilable.equals("")) {
	  	  	selectReconcilable(schedule.reconcilable);
	  	  	log = log+"Reconcilable ";
	  	}
	  	if(schedule.distributable!=null&&!schedule.distributable.equals("")) {
	  	  	selectDistributable(schedule.distributable);
	  	  	log = log+"Distributable ";
	  	}
	  	
	  	logger.info(log);
	  	clickGo();
	  	this.waitLoading();
	}
	
	/**
	 * Verify Specific schedule In Search List
	 * @param id-configuration schedule id
	 */
	public void verifyScheduleInSearchList(String id) {
		RegularExpression rex = new RegularExpression(
				"Distribution Configuration Schedule ID Active.*", false);
		IHtmlObject[] objs = null;
		boolean found = false;
		do {
			browser.checkHtmlObjectExists(".class", "Html.TD", ".text", "Page:");//added by pzhu, for waiting the whole page
			objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", rex);
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
			for (int i = 1; i < tableGrid.rowCount(); i++) {
				if (tableGrid.getCellValue(i, 1).toString().equals(id)) {
				  found = true;
				  break;
				}
			}
			if (found) {
			  	logger.info("Configuration Schedule "+id+" is in the search result!");
				break;
			}
			if (!hasNext() && !found) {
				Browser.unregister(objs);
				throw new ItemNotFoundException("Search Configuration Schedule Fail!");
			}
		} while (gotoNext());

		Browser.unregister(objs);
	}
	
	/**
	 * This method used to verify search list contain correct information
	 * @param schedule
	 */
	public void verifyScheduleList(ScheduleData schedule) {
	  if(schedule.searchBy!=null&&!schedule.searchBy.equals("")) {
	    if(schedule.searchBy.equalsIgnoreCase("Distribution Configuration Schedule ID")) {
	      	verifyScheduleInfo("Distribution Configuration Schedule ID",schedule.scheduleId);
	  	  }else if(schedule.searchBy.equalsIgnoreCase("Location")) {
	  	    verifyScheduleInfo("Location",schedule.location);
	  	  }
	  }
	  if(schedule.dateType!=null&&!schedule.dateType.equals("")) {
	    if(schedule.dateType.equalsIgnoreCase("Effective Date")) {
	      	verifyDateInGivenRange("Effective Date",schedule.fromDate,schedule.toDate);
	    }else if(schedule.dateType.equalsIgnoreCase("Reconciliation Start Date")) {
	      	verifyDateInGivenRange("Reconciliation Start Date",schedule.fromDate,schedule.toDate);
	    }else if(schedule.dateType.equalsIgnoreCase("Distribution Start Date")) {
	      	verifyDateInGivenRange("Distribution Start Date",schedule.fromDate,schedule.toDate);
	    }
	  }
	  if(schedule.activeStatus!=null&&!schedule.activeStatus.equals("")) {
	    
		String isActive = "No";
		if(schedule.activeStatus.equalsIgnoreCase("Inactive")) {
		  isActive = "No";
		}else if(schedule.activeStatus.equalsIgnoreCase("Active")) {
		  isActive = "Yes";
		}
		verifyScheduleInfo("Active",isActive);
	  }
	  if(schedule.paymentOrRefund!=null&&!schedule.paymentOrRefund.equals("")) {
	    if(schedule.paymentOrRefund.equalsIgnoreCase("Payment")) {
	      schedule.paymentOrRefund = "Payments";
	    }else{
	      schedule.paymentOrRefund = "Refunds";
	    }
		verifyScheduleInfo("Payments/Refunds",schedule.paymentOrRefund);
	  }
	  if(schedule.payGroup!=null&&!schedule.payGroup.equals("")) {
		verifyScheduleInfo("Payment/Refund Group",schedule.payGroup);
	  }
	  if(schedule.payType!=null&&!schedule.payType.equals("")) {
		verifyScheduleInfo("Payment/Refund Type",schedule.payType);
	  }
	  if(schedule.reconcilable!=null&&!schedule.reconcilable.equals("")) {
		verifyScheduleInfo("Reconcilable",schedule.reconcilable);
	  }
	  if(schedule.distributable!=null&&!schedule.distributable.equals("")) {
		verifyScheduleInfo("Distributable",schedule.distributable);
	  }
	}
	
	/**
	 * This method used to change schedule status
	 * @param schdId
	 * @param status
	 */
	public String changeScheduleStatus(String schdId,String status) {
	  	logger.info("Start to Change Schedule Status to " + status);
	  	
	  	String currentStatus = "";
	  	searchByScheduleId(schdId);
	  	checkScheduleExists(schdId);
	  	selectScheduleCheckBox(schdId);
//	  	selectScheduleCheckBoxAfterSearch(schdId);//updated by pzhu
	  	if(status.equalsIgnoreCase("Active")){
	  	  clickActive();
	      currentStatus= "Active";
	  	}else if(status.equalsIgnoreCase("Inactive")){
	  	  clickDeactive();
	  	  currentStatus= "Deactive";
	  	}else{
	  	  throw new ItemNotFoundException("Unkown Status " + status);
	  	}
	  	waitLoading();
	  	
	  	return currentStatus;
	}
	
	/**
	 * Verify specific schedule status is given status
	 * @param scheduleId
	 * @param status
	 */
	public void verifyScheduleStatus(String scheduleId,String status) {
	   	logger.info("Start to verify Schedule " + scheduleId + " is " + status);
	   	
	   	searchByScheduleId(scheduleId);
	   	String isActive = "No";
	   	if(status.equalsIgnoreCase("Inactive")){
	      isActive = "No";
	    }else if(status.equalsIgnoreCase("Active")){
	      isActive = "Yes";
	    }else{
	  	  throw new ItemNotFoundException("Unkown Status " + status);
	    }
	   	verifyScheduleInfo("Active",isActive);
	}
	
	/**
	   * Verify specific column value is the same with given value
	   * @param colName column Name
	   * @param value
	   */
	 public void verifyScheduleInfo( String colName, String value ) {
	    int colNum = getColNum( colName );
	    RegularExpression rex = new RegularExpression("Distribution Configuration Schedule ID Active.*", false );
	    IHtmlObject[] objs = null;
	    do {
	      objs = browser.getHtmlObject( ".class", "Html.TABLE", ".text", rex );
	      IHtmlTable tableGrid = (IHtmlTable) objs[0];
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
	  * This method is used to verfiy given column date value is in the given date range
	  * @param colName
	  * @param fromDate
	  * @param toDate
	  */
	 public void verifyDateInGivenRange(String colName,String fromDate,String toDate) {
	     int colNum = getColNum(colName);
		 RegularExpression rex = new RegularExpression("Distribution Configuration Schedule ID Active.*", false);
		 IHtmlObject[] objs = null;
		 do {
		    	objs = browser.getHtmlObject(".class", "Html.TABLE", ".text",rex);
		    	IHtmlTable tableGrid = (IHtmlTable) objs[0];
			    for (int i = 1; i < tableGrid.rowCount(); i++) {
			      if (null != tableGrid.getCellValue(i, colNum)) {
						String date = DateFunctions.formatDate(tableGrid.getCellValue(i, colNum).toString(),"M/d/yyyy");
						int value = DateFunctions.compareDates(fromDate,date)+DateFunctions.compareDates(toDate,date);
						if(value==2||value==-2)
						{
					  		Browser.unregister(objs);
					  		logger.error("Date "+date+" is not in the given date range!");
					  		throw new ItemNotFoundException("Date "+date+" is not in the given date range!");
						}
			      }
			    }
		 } while (gotoNext());

		 Browser.unregister(objs);
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
	  public boolean hasNext()
	  {
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
		  IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "First");
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
	    RegularExpression rex = new RegularExpression("Distribution Configuration Schedule ID Active.*", false );
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
	  
	  public boolean verifyScheduleInfo(ScheduleData schedule){
		  IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("Distribution Configuration Schedule ID Active.*", false));
		  if(objs.length < 1){
			  throw new ItemNotFoundException("Can't find schedule list table.");
		  }
		  
		  IHtmlTable table = (IHtmlTable) objs[0];
		  int row = table.findRow(1, schedule.scheduleId);
		  if(row < 0){
			  throw new ItemNotFoundException("Can't find schedule by given ID:"+schedule.scheduleId);
		  }
		  List<String> actualScheduleInfo = table.getRowValues(row);
		  boolean result = true;
		  result &= MiscFunctions.compareResult("Schedule ID", schedule.scheduleId, actualScheduleInfo.get(1));
		  result &= MiscFunctions.compareResult("Status", "Yes", actualScheduleInfo.get(2));
		  result &= MiscFunctions.compareResult("Location", schedule.location, actualScheduleInfo.get(3));
		  result &= MiscFunctions.compareResult("Payment/Refund", schedule.paymentOrRefund, actualScheduleInfo.get(4));
		  result &= MiscFunctions.compareResult("Payment group", schedule.payGroup, actualScheduleInfo.get(5));
		  result &= MiscFunctions.compareResult("Payment type", schedule.payType, actualScheduleInfo.get(6));
		  result &= MiscFunctions.compareResult("Effective Date", schedule.effectiveDate, actualScheduleInfo.get(7));
		  result &= MiscFunctions.compareResult("Reconcilable", schedule.reconcilable, actualScheduleInfo.get(8));
		  result &= MiscFunctions.compareResult("Reconciliation Start Date", schedule.reconcilStartDate, actualScheduleInfo.get(9));
		  result &= MiscFunctions.compareResult("Distributable", schedule.distributable, actualScheduleInfo.get(10));
		  result &= MiscFunctions.compareResult("Distribution Start Date", schedule.distributStartDate, actualScheduleInfo.get(11));
		  return result;
	  }
}
