/*
 * Created on Jan 31, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.bulletin;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.BulletinInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsBulletinSearchPage extends OrmsPage {

  	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsBulletinSearchPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	private OrmsBulletinSearchPage()
	{}
	
	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsBulletinSearchPage getInstance()
	{
		if (null == _instance) {
			_instance = new OrmsBulletinSearchPage();
		}
		return _instance;
	}

	/** Determine given page mark exists or not */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Add New Bulletin");
	}

	/**Click add new bulletin button*/
	public void clickAddNewBulletin() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New Bulletin",
				true);
	}

	/**Select search criteria*/
	public void selectSearch(String criteria) {
		browser.selectDropdownList(".id", "BulletinSearchCriteria.search",
				criteria, true);
	}

	/**
	 * Input search value
	 * @param value
	 */
	public void setSearchValue(String value) {
		browser.setTextField(".id", "BulletinSearchCriteria.searchValue", value,
				true);
	}

	/**
	 * Select search date type including start date and end date
	 * @param dateType
	 */
	public void selectDateType(String dateType) {
		browser.selectDropdownList(".id", "BulletinSearchCriteria.date", dateType,
				true);
	}

	/**
	 * Input the start date
	 * @param from
	 */
	public void setDateFrom(String from) {
		browser.setTextField(".id",
				"BulletinSearchCriteria.startdate_ForDisplay", from, true);
	}

	/**
	 * Input the end date
	 * @param to
	 */
	public void setDateTo(String to) {
		browser.setTextField(".id",
				"BulletinSearchCriteria.enddate_ForDisplay", to, true);
	}

	/**
	 * Select display what kind of bulletin, active or Inactive, all
	 * @param status
	 */
	public void selectShow(String status) {
		browser.selectDropdownList(".id", "BulletinSearchCriteria.show",
				status, true);
	}

	/**Select which kind of application*/
	public void selectApp(String app) {
		browser.selectDropdownList(".id", "BulletinSearchCriteria.application",
				app, true);
	}

	/**Select which kind of priority*/
	public void selectPriority(String priority) {
		browser.selectDropdownList(".id", "BulletinSearchCriteria.priority",
				priority, true);
	}

	/**Select specific bulletin*/
	public void selectSpecCheckBox(String bulletinID) {
		browser.selectCheckBox(".value", bulletinID);
	}

	/**Click activate button*/
	public void clickActivateButton() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate", true);
	}

	/**Click Deactivate button*/
	public void clickDeactivateButton() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate", true);
	}

	/**Click Go button*/
	public void clickGO() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$",false));
	}

	public void searchByHeadLine(String headLine)
	{
	  	this.selectSearch("Headline");
	  	this.setSearchValue(headLine);
	  	this.clickGO();
	  	waitLoading();
	}
	
	public void searchByLocation(String location,String show){
		this.selectSearch("Location");
	  	this.setSearchValue(location);
	  	this.selectShow(show);
	  	this.clickGO();
	  	waitLoading();
	}
	
	public void selectAllCheckBox(){
		browser.selectCheckBox(".value", "all");
	}
	
	/**This method used to change bulletin status, include activate and deactivate
	 * @param action---activate or deactivate
	 * */
	public void changeBulletinStatus(String bulletinID,String action) 
	{
	  	logger.info(action+" Bulletin "+bulletinID);
	  	
		selectSpecCheckBox(bulletinID);

		if (action.equalsIgnoreCase("Activate")) {
			clickActivateButton();
		} else if (action.equalsIgnoreCase("Deactivate")) {
			clickDeactivateButton();
		} else
			throw new ItemNotFoundException("Unknown action.");
		this.waitLoading();
	}
	
	public void clearAllSearchCriteria()
	{
	  	this.setSearchValue("");
	  	this.setDateFrom("");
	  	this.setDateTo("");
	  	this.selectShow("All");
	  	this.selectApp("All");
	  	this.selectPriority("All");
	}
	
	/**
	 * This method used to search bulletin by different criteria
	 * @param bulletin
	 */
	public void searchBulletin(BulletinInfo bulletin)
	{
	  	String log = "Search Bulletin by ";
	  	this.clearAllSearchCriteria();
	  	if(bulletin.searchType!=null&&!bulletin.searchType.equals("")){
	  	  selectSearch(bulletin.searchType);
	  	  if(bulletin.searchType.equalsIgnoreCase("Headline")){
	  	    setSearchValue(bulletin.headline);
	  	  }else if(bulletin.searchType.equalsIgnoreCase("Location")){
	  	    setSearchValue(bulletin.location);
	  	  }else if(bulletin.searchType.equalsIgnoreCase("Author")){
	  	    setSearchValue(bulletin.searchValue);
	  	  }else{
	  	    throw new ItemNotFoundException("Unknown Search Type.");
	  	  }
	  	  log = log+bulletin.searchType+" ";
	  	}
	  	if (bulletin.dateType!=null&&!bulletin.dateType.equals("")) {
	  	  selectDateType(bulletin.dateType);
	  	  if(bulletin.dateType.equalsIgnoreCase("Start Date")){
	  	    setDateFrom(bulletin.startdatefrom);
	  	    setDateTo(bulletin.startdateto);
	  	  }else if(bulletin.dateType.equalsIgnoreCase("End Date")){
	  	    setDateFrom(bulletin.enddatefrom);
	  	    setDateTo(bulletin.enddateto);
	  	  }else{
	  	    throw new ItemNotFoundException("Unknown Date Type.");
	  	  }
	  	  log = log+bulletin.dateType+"  ";
		}
		if (bulletin.status!=null&&bulletin.status.length()>0) {
			this.selectShow(bulletin.status);
			log = log +"status ";
		}
		if (bulletin.application!=null&&bulletin.application.length()>0) {
			this.selectApp(bulletin.application);
			log = log +"sale channel";
		}
		if (bulletin.priority!=null&&bulletin.priority.length()>0) {
			this.selectPriority(bulletin.priority);
			log = log +"priority";
		}
		this.clickGO();
		this.waitLoading();
		
		logger.info(log);
	}
	
	/**
	 * This method used to verify Bulletin List contain correct information
	 * @param bulletin
	 */
	public void verifyBulletinList(BulletinInfo bulletin)
	{
	  if(bulletin.searchType!=null&&!bulletin.searchType.equals("")){
	    if(bulletin.searchType.equalsIgnoreCase("Headline")){
	      verifyBulletinInfo("Headline",bulletin.headline);
	    }else if(bulletin.searchType.equalsIgnoreCase("Location")){
	      verifyBulletinInfo("Location",bulletin.location);
	    }else if(bulletin.searchType.equalsIgnoreCase("Author")){
	      verifyBulletinInfo("Author",bulletin.author);
	    }
	  }
	  if(bulletin.dateType!=null&&!bulletin.dateType.equals("")){
	    if(bulletin.dateType.equalsIgnoreCase("Start Date")){
	      verifyDateInGivenRange("Start Date",bulletin.startdatefrom,bulletin.startdateto);
	    }else{
	      verifyDateInGivenRange("End Date",bulletin.enddatefrom,bulletin.enddateto);
	    }
	  }
	  if(bulletin.status!=null&&!bulletin.status.equals("")){
		String isActive = "No";
		if(bulletin.status.equalsIgnoreCase("Inactive")){
		  isActive = "No";
		}else if(bulletin.status.equalsIgnoreCase("Active")){
		  isActive = "Yes";
		}
		verifyBulletinInfo("Active",isActive);
	  }
	  if(bulletin.application!=null&&!bulletin.application.equals("")){
	    verifyBulletinInfo("Applications",bulletin.application);
	  }
	  if(bulletin.priority!=null&&!bulletin.priority.equals("")){
	    verifyBulletinInfo("Priority",bulletin.priority);
	  }
	}
	
	/**
	 * Verify Specific bulletin In Search List
	 * @param id bulletin id
	 */
	public void verifyBulletinInSearchList(String id) 
	{
		RegularExpression rex = new RegularExpression("^Bulletin ID Headline Location.*", false);
		boolean found = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text",rex);
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		for (int i = 1; i < tableGrid.rowCount(); i++) {
		  if (tableGrid.getCellValue(i, 1).toString().equals(id)) {
		    found = true;
		    break;
		  }	
		}
		if(!found){
		  Browser.unregister( objs );
		  throw new ItemNotFoundException("Not Found given Bulletin "+id);
		}
		Browser.unregister(objs);
		
		logger.info("Bulletin "+id+" in Search List.");
	}

	/**
	 * This method is used to verfiy given column date value is in the given date range
	 * @param colName
	 * @param fromDate
	 * @param toDate
	 */
	public void verifyDateInGivenRange(String colName,String fromDate,String toDate)
	{
	    int colNum = getColNum(colName);
		RegularExpression rex = new RegularExpression("^Bulletin ID Headline Location.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text",rex);
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
			}else{
			  throw new ErrorOnPageException("Date Value is Null.");
			}
		}
		Browser.unregister(objs);
	}
	
   /**
	* Verify specific column value is the same with given value
	* @param colName column Name
	* @param value
	*/
	public void verifyBulletinInfo( String colName, String value )
	{
	    int colNum = getColNum( colName );
	    RegularExpression rex = new RegularExpression("^Bulletin ID Headline Location.*", false );
	    IHtmlObject[] objs = browser.getHtmlObject( ".class", "Html.TABLE", ".text", rex );
	    IHtmlTable tableGrid = (IHtmlTable)objs[0];
	      if(tableGrid.rowCount()>1){
	        for ( int i = 1; i < tableGrid.rowCount(); i++ ) {
		          if ( !tableGrid.getCellValue( i, colNum ).toString().trim().equals( value ) ) {
		        	  if(colName.equalsIgnoreCase("Applications")){
		        		  if(tableGrid.getCellValue( i, colNum ).toString().trim().contains(value)){
		        			  continue;
		        		  }
		        	  }
		            if(!tableGrid.getCellValue( i, colNum ).toString().trim().equalsIgnoreCase("All")){
		              	Browser.unregister( objs );
			            logger.error( "Bulletin Info " + tableGrid.getCellValue( i, colNum ) + " is not Correct! " );
			            throw new ItemNotFoundException( tableGrid.getCellValue( i, colNum ) + " is different with given value " + value );
		            }
		          }
		      }
	      }else{
	        Browser.unregister( objs );
	        throw new ItemNotFoundException("No Bulletin Found.");
	      }

	    Browser.unregister( objs );
	}
	
  /**
   * Get Column Number by Column Name
   * @param colName
   * @return Column Number
   */
   public int getColNum( String colName )
   {
      RegularExpression rex = new RegularExpression("^Bulletin ID Headline Location.*", false );
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
   
   /*This method click the first bulletin Id in bulletin search result page, this is useful when search a bulletin and want to go to the detail page*/
   public void clickFirstbulletinId() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("\\d+",false), 0); 
    }
   
   public void clickBulletinId(String bulletinID) {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(bulletinID, false)); 
    }

   /*This method is used to get all the warning messages when add or edit a bulletin*/
	public String[] getWarningMessages() {
		String[] noteMessage = null;
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".id", "NOTSET");
		if(objs.length > 0){
			noteMessage = new String[objs.length];
			for(int i=0; i < objs.length; i++){
				noteMessage[i] = objs[i].getProperty(".text");
			}			
		}		
		Browser.unregister(objs);
		return noteMessage;
	}
	
	private IHtmlTable getSearchResultTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression(".*Bulletin ID.*", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find search result table.");
		}

		IHtmlTable table = (IHtmlTable)objs[0];
//		Browser.unregister(objs);
		return table;
	}
	
	public List<String> getColumnByName(String columnName){
		IHtmlTable table = getSearchResultTable();
		int col = table.findColumn(0, columnName);
		
		List<String> valueList = new ArrayList<String>();
		List<String> tempList = new ArrayList<String>();
		tempList = table.getColumnValues(col);
		tempList.remove(0);
		valueList.addAll(tempList);

		PagingComponent turnPageComponent = new PagingComponent();
		while (turnPageComponent.nextExists()){
			turnPageComponent.clickNext();
			ajax.waitLoading();
			
			table = this.getSearchResultTable();
			col = table.findColumn(0, columnName);
			tempList = new ArrayList<String>();
			tempList = table.getColumnValues(col);
			tempList.remove(0);
			valueList.addAll(tempList);
		}
		return valueList;
	}
}
