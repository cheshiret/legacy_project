/*
 * Created on Jan 28, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.resourceManager.recipient;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResourceManagerPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ResMgrRecipientsMainPage extends ResourceManagerPage {

	private ResMgrRecipientsMainPage() {
	}

	private static ResMgrRecipientsMainPage instance = null;

	public static ResMgrRecipientsMainPage getInstance() {
		if (null == instance) {
			instance = new ResMgrRecipientsMainPage();
		}
		return instance;
	}

	/**
	 * Check whether the specific page mark displayed
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("Recipient List ID Facility ID.*",false));
	}
	
	public void waitExists(int syncTime){//override method to handle selenium wait same page syncronize issue
		Browser.sleep(syncTime);
		super.waitLoading();
	}
	/**
	 * Input facility name into text box
	 * @param facility
	 */
	public void setFacility(String facility)
	{
	  	browser.setTextField(".id","_SchedulerSearchFacility",facility);
	}
	
	public void selectReportGroup(String group)
	{
	  	browser.selectDropdownList(".id","_SchedulerSearchReportGroup",group);
	}
	
	public void selectReportName(String reportName)
	{
	  	browser.selectDropdownList(".id","_SchedulerSearchReport",reportName);
	}
	
	public void clickSearch()
	{
	  	browser.clickGuiObject(".class","Html.A",".text","Search");
	}
	
	/**
	 * Select specifc recipient check box
	 * @param recipientId
	 */
	public void selectCheckBox(String recipientId)
	{
	  	browser.selectCheckBox(".id","checkbox_"+recipientId);
	}
	
	public void selectFirstRecipient()
	{
	  	browser.selectCheckBox(".id",new RegularExpression("checkbox_\\d+",false));
	}
	
	/**
	 * Click Delete button
	 *
	 */
	public void clickDelete()
	{
	  	browser.clickGuiObject(".class","Html.A",".text","Delete");
	}
	
	public void clickAddNew()
	{
	  	browser.clickGuiObject(".class","Html.A",".text","Add New");
	}
	
	/**Search recipient by facility name*/
	public void searchByFacilityName(String parkName)
	{
	  	logger.info("Search Recipient By Facility Name.");
	  	
	  	clearAllSearchCriteria();
	  	setFacility(parkName);
	  	clickSearch();
	  	waitLoading();	  	
	}
	
	/**Search recipient by report group*/
	public void searchByGroup(String group)
	{
	  	logger.info("Search Recipient By Report Group.");
	  	
	  	clearAllSearchCriteria();
	  	selectReportGroup(group);
	  	this.waitLoading();
	  	clickSearch();
	  	waitLoading();	
	}
	
	/**Search recipient by report name*/
	public void searchByReportName(String reportName)
	{
	  	logger.info("Search Recipient By Report Name.");
	  	
	  	clearAllSearchCriteria();
	  	selectReportName(reportName);
	  	this.waitLoading();
	  	clickSearch();
	  	waitLoading();	
	}
	
	/**clear all search criteria to default value*/
	public void clearAllSearchCriteria()
	{
	  	setFacility("");
	  	selectReportGroup("All");
	  	this.waitLoading();
	  	selectReportName("All");
	  	this.waitLoading();
	}
	
	public void setupSearchCriteria(ReportData rd)
	{
	  	if(rd.group!=null&&!rd.group.equals("")){
	  	  selectReportGroup(rd.group);
	  	  this.waitLoading();
	  	}
	  	if(rd.reportName!=null&&!rd.reportName.equals("")){
	  	  selectReportName(rd.reportName);
	  	  this.waitLoading();
	  	}
	  	if(rd.park!=null&&!rd.park.equals("")){
			setFacility(rd.park);
	  	}
	  	clickSearch();
	  	waitLoading();
	}
	
	public void clickFirstRecipient()
	{
	  	browser.clickGuiObject(".class","Html.A",".text",new RegularExpression("\\d+",false));
	}
	
	public boolean checkRecipientExists(String recipientID)
	{
	  	return browser.checkHtmlObjectExists(".class","Html.A",".text",recipientID);
	}
	
	public String getFirstRecipientId()
	{
	  	IHtmlObject[] objs = browser.getHtmlObject(".class","Html.A",".text",new RegularExpression("\\d+",false));
	  	String temp = objs[0].getProperty(".text").toString();
	  	Browser.unregister(objs);
	  	return temp;
	}
	
	/**
	 * Verify Specific recipient In Search List
	 * @param id recipient id
	 */
	public void verifyRecipientInSearchList(String id) {
		RegularExpression rex = new RegularExpression("^Recipient List ID Facility ID.*", false);
		boolean found = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text",rex);
		int rowCount=((IHtmlTable)objs[0]).rowCount();
		for (int i = 1; i < rowCount; i++) {
		  if (((IHtmlTable)objs[0]).getCellValue(i, 1).equals(id)) {
		    found = true;
		    break;
		  }	
		}
		if(!found){
		  Browser.unregister( objs );
		  throw new ItemNotFoundException("Not Found given Recipient "+id);
		}
		Browser.unregister(objs);
	}
	
   /**
	* Verify specific column value is the same with given value
	* @param colName column Name
	* @param value
	*/
	public void verifyRecipientInfo( String colName, String value ) {
	    int colNum = getColNum( colName );
	    RegularExpression rex = new RegularExpression("^Recipient List ID Facility ID.*", false );
	    IHtmlObject[] objs = browser.getHtmlObject( ".class", "Html.TABLE", ".text", rex );
	    int rowCount=((IHtmlTable)objs[0]).rowCount();
	      if(rowCount>1){
	        for ( int i = 1; i < rowCount; i++ ) {
		          if ( !((IHtmlTable)objs[0]).getCellValue(i, colNum).trim().equals( value ) ) {
		            if(!((IHtmlTable)objs[0]).getCellValue(i, colNum).trim().equalsIgnoreCase("All")){
		              	Browser.unregister( objs );
			            logger.error( "Recipient Info " + ((IHtmlTable)objs[0]).getCellValue(i, colNum) + " is not Correct! " );
			            throw new ItemNotFoundException( ((IHtmlTable)objs[0]).getCellValue(i, colNum) + " is different with given value " + value );
		            }
		          }
		      }
	      }else{
	        Browser.unregister( objs );
	        throw new ItemNotFoundException("No Recipient Found.");
	      }

	    Browser.unregister( objs );
	}
	
	/**
	   * Get Column Number by Column Name
	   * @param colName
	   * @return Column Number
	   */
	  public int getColNum( String colName ) {
	    RegularExpression rex = new RegularExpression("^Recipient List ID Facility ID.*", false );
	    IHtmlObject[] objs = browser.getHtmlObject( ".class", "Html.TABLE", ".text", rex );
	    if ( null != objs && objs.length > 0 ) {
	      int colCounts = ((IHtmlTable)objs[0]).columnCount();
	      for ( int i = 0; i < colCounts; i++ ) {
	        if ( ((IHtmlTable)objs[0]).getCellValue(0, i).equalsIgnoreCase( colName ) ) {
	          Browser.unregister( objs );
	          return i;
	        }
	      }
	    }
	    Browser.unregister( objs );
	    return -1;
	  }
}
