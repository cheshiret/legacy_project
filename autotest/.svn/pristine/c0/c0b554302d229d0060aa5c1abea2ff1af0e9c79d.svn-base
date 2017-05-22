/*
 * $Id: OrmsSearchEventPage.java 7017 2009-01-16 23:15:02Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.event;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EventInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * TODO: enter class description
 * 
 * @author jdu
 */
public class OrmsSearchEventPage extends OrmsPage {

	/**
	 * Script Name   : OrmsSearchEventPage
	 * Generated     : Jan 24, 2008 5:02:10 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2008/01/24
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsSearchEventPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsSearchEventPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsSearchEventPage getInstance()	throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsSearchEventPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
	  	RegularExpression pattern=new RegularExpression("EventSearchCriteria(-\\d+)?\\.eventId:ZERO_TO_NULL",false);
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text",".id", pattern);
	}

	/**
	 * Wait the searched resout exist
	 * @throws PageNotFoundException
	 */
	public void schWaitExists() throws PageNotFoundException {
	  	ajax.waitLoading();
		browser.searchObjectWaitExists(".class", "Html.A", ".id","EventOrderLightView.ID");
	}

	/**Click Go button*/
	public void clickGo() {
		//James[20130815]: change the pattern to "^(go|search)$)" otherwise, "Event Search List" link will be clicked.
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$",false));
	}

	/**
	 * Input Event ID
	 * @param id
	 */
	public void setEventID(String id) {
	  	RegularExpression pattern=new RegularExpression("EventSearchCriteria(-\\d+)?\\.eventId:ZERO_TO_NULL",false);
		browser.setTextField(".id",pattern,id);
	}

	/**
	 * Input Customer Phone
	 * @param phone
	 */
	public void setCustomerPhone(String phone) {
	  	RegularExpression pattern=new RegularExpression("EventSearchCriteria(-\\d+)?\\.customerPhone",false);
		browser.setTextField(".id", pattern, phone);
	}

	/**
	 * Input Customer Organization 
	 * @param org
	 */
	public void setCustomerOrganization(String org) {
	  	RegularExpression pattern=new RegularExpression("EventSearchCriteria(-\\d+)?\\.customerOrganization",false);
		browser.setTextField(".id", pattern,org);
	}

	/**
	 * Input Last Name
	 * @param lname
	 */
	public void setLastName(String lname) {
	  	RegularExpression pattern=new RegularExpression("EventSearchCriteria(-\\d+)?\\.customerLastName",false);
		browser.setTextField(".id",pattern,lname);
	}

	/**
	 * Input First Name
	 * @param fname
	 */
	public void setFirstName(String fname) {
	  	RegularExpression pattern=new RegularExpression("EventSearchCriteria(-\\d+)?\\.customerFirstName",false);
		browser.setTextField(".id", pattern,fname);
	}

	/**
	 * Inptu Customer Email
	 * @param email
	 */
	public void setCustomerEmail(String email) {
	  	RegularExpression pattern=new RegularExpression("EventSearchCriteria(-\\d+)?\\.customerEmail",false);
		browser.setTextField(".id", pattern, email);
	}

	/**
	 * Set event start date
	 * @param date
	 */
	public void setEventStartDate(String date) {
	  	RegularExpression pattern=new RegularExpression("EventSearchCriteria(-\\d+)?\\.startDate_ForDisplay",false);
		browser.setCalendarField(".id",pattern,date);
	}

	/**
	 * Set Event Location
	 * @param loc
	 */
	public void setEventLocation(String loc) {
	  	RegularExpression pattern=new RegularExpression("EventSearchCriteria(-\\d+)?\\.locationName",false);
		browser.setTextField(".id", pattern, loc);
	}

	/**Input Event Name*/
	public void setEventName(String name) {
	  	RegularExpression pattern=new RegularExpression("EventSearchCriteria(-\\d+)?\\.eventName",false);
		browser.setTextField(".id",pattern, name);
	}

	/**
	 * Input Event End date
	 * @param date
	 */
	public void setEventEndDate(String date) {
	  	RegularExpression pattern=new RegularExpression("EventSearchCriteria(-\\d+)?\\.endDate_ForDisplay",false);
		browser.setCalendarField(".id", pattern,date);
//		this.moveFocusOutOfDateComponent();
	}

	/**
	 * Select Event Status
	 * @param status
	 */
	public void selectEventStatus(String status) {
	  	RegularExpression pattern=new RegularExpression("EventSearchCriteria(-\\d+)?\\.status",false);
		browser.selectDropdownList(".id",pattern, status);
	}

	/**Check IncludeArea checkbox*/
	public void checkIncludeArea() {
	  	RegularExpression pattern=new RegularExpression("EventSearchCriteria(-\\d+)?\\.includeAreaCode",false);
		browser.selectCheckBox(".id", pattern);
	}

	/**Uncheck IncludeArea*/
	public void unCheckIncludeArea() {
	  	RegularExpression pattern=new RegularExpression("EventSearchCriteria(-\\d+)?\\.includeAreaCode",false);
		browser.unSelectCheckBox(".id", pattern);
	}

	/**
	 * Search an event with the given ID and click it
	 * @param eventID
	 * @throws ItemNotFoundException
	 */
	public void selectEvent(String eventID) {
		try {
			browser.clickGuiObject(".id", "EventOrderLightView.ID", ".text",
					eventID, true);
		} catch (ItemNotFoundException e) {
			throw new ItemNotFoundException("Event ID \"" + eventID
					+ "\" was not found.");
		}
	}

	/**
	 * Input eventID and search
	 * @param id
	 */
	public void searchEventByID(String id) {
		this.setEventID(id);
		this.clickGo();
	}

	/**Select first event that displayed in the orms search event page*/
	public boolean selectFirstEvent() {
		boolean result = true;
		try {
			browser.clickGuiObject(".class", "Html.A", ".id",
					"EventOrderLightView.ID", true, 0);
		} catch (ItemNotFoundException e) {
			result = false;
		}

		return result;
	}
	
	/**
	 * This method used to clear all exist search criteria
	 *
	 */
	public void clearSearchCriteria()
	{
		this.setEventID("");
		this.setEventName("");
		this.setEventLocation("");
		this.selectEventStatus("");
		this.setEventStartDate("");
		this.setEventEndDate("");
		this.setCustomerPhone("");
		this.setFirstName("");
		this.setLastName("");
		this.setCustomerEmail("");
		this.setCustomerOrganization("");
	}
	
	/**
	 * This method used to search event by different criteria
	 * @param event-EventInfo
	 */
	public void setUpSearchCriteria(EventInfo event)
	{
		String log = "Search Event By ";
		this.clearSearchCriteria();

		if(event.status!=null&&!event.status.equals("")){
			this.selectEventStatus(event.status);
			log = log +"Status ";
		}
		if(event.fName!=null&&!event.fName.equals("")){
			this.setFirstName(event.fName);
			log = log +"Frist Name ";
		}
		if(event.lName!=null&&!event.lName.equals("")){
			this.setLastName(event.lName);
			log = log +"Last Name ";
		}
		if(event.eventStart!=null&&!event.eventStart.equals("")){
			this.setEventStartDate(event.eventStart);
		}
		if(event.eventEnd!=null&&!event.eventEnd.equals("")){
			this.setEventEndDate(event.eventEnd);
		}
		if(event.searchBy!=null&&!event.searchBy.equals("")){
			if(event.searchBy.equalsIgnoreCase("ID")){
				this.setEventID(event.eventID);
			}else if(event.searchBy.equalsIgnoreCase("Name")){
				this.setEventName(event.eventName);
			}else if(event.searchBy.equalsIgnoreCase("Phone")){
				this.setCustomerPhone(event.phone);
				if(event.includeArea.equalsIgnoreCase("no")){
					this.unCheckIncludeArea();
				}else{
					this.checkIncludeArea();
				}
			}else if(event.searchBy.equalsIgnoreCase("Email")){
				this.setCustomerEmail(event.email);
			}
			log = log+event.searchBy;
		}
		logger.info(log);
		this.clickGo();
		this.waitLoading();
		ajax.waitLoading();
	}
	
	/**
	 * This method used to verify event info
	 * @param event
	 */
	public void verifyEventInfo(EventInfo event)
	{		
		if(event.searchBy!=null&&!event.searchBy.equals("")){
			if(event.searchBy.equalsIgnoreCase("ID")){
				this.verifyEventInfo("Event ID",event.eventID);
			}else if(event.searchBy.equalsIgnoreCase("Name")){
				this.verifyEventInfo("Event Name",event.eventName);
			}
		}
		if(event.status!=null&&!event.status.equals("")){
			this.verifyEventInfo("status",event.status);
		}
		if(event.fName!=null&&!event.fName.equals("")){
			this.verifyEventInfo("Billing Customer",event.fullName);
		}
		if(event.lName!=null&&!event.lName.equals("")){
			this.verifyEventInfo("Billing Customer",event.fullName);
		}
	}
	
	/**
	   * This method is used to verify specific column value equals given value
	   * @param colName
	   * @param value
	   */
	  public void verifyEventInfo(String colName,String value)
	  {
	    	int colNum = getColNum(colName);
	    	RegularExpression rex = new RegularExpression("Event ID Event Name.*", false);
	    	IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text",rex);
	    	IHtmlTable tableGrid= (IHtmlTable)objs[0];
			for (int i = 1; i < tableGrid.rowCount(); i++) {
			
				if (null != tableGrid.getCellValue(i, colNum)) {
					if (!tableGrid.getCellValue(i, colNum).toString().trim().equals(value)) {
						Browser.unregister(objs);
						logger.error("Event Info " + tableGrid.getCellValue(i, colNum) + " is not Correct! ");
						throw new ItemNotFoundException("Event Info " + tableGrid.getCellValue(i, colNum) + " is not Correct! ");
					}
				}
			}
			Browser.unregister(objs);
	  }
	  
	  public boolean clickNext(){
		  boolean flag = false;
		  IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.A", ".text", "Next", ".href", "#markread"));
		  
		  if(objs != null && objs.length >0){
			  flag = true;
			  objs[0].click();
		  }
		  Browser.unregister(objs);
		  return flag;
	  }
	  /**
	   * Verify given event Id in the search List
	   * @param eventId
	   */
	  public void verifyGivenEventInSearchList(String eventId)
	  {
	    	logger.info("Verify Given Event "+eventId+" In Search List.");
	    	boolean found = false;
	    	RegularExpression rex = new RegularExpression("Event ID Event Name.*", false);
	    	
	    	do{ 
	    		ajax.waitLoading();
	    		
	    		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text",rex);
	 		    IHtmlTable tableGrid=(IHtmlTable)objs[0];
	 		   
	 		    for (int i = 1; i < tableGrid.rowCount(); i++) {
	 		    	if (tableGrid.getCellValue(i, 0).toString().equals(eventId)) {
	 		    		found = true;
	 					break;
	 				}
	 		    }
	 		    Browser.unregister(objs);
	    		
	    	}while (!found && this.clickNext());
		   
		    if(!found){
		    	throw new ItemNotFoundException("Not Found Event "+eventId);
		    }else{
		    	logger.info("Found Event "+eventId+" Successful.");
		    }
	  }
	  
	  /**
	   * Get Column Number by Column Name
	   * @param colName
	   * @return Column Number
	   */
	   public int getColNum(String colName) {
	 		RegularExpression rex = new RegularExpression(
	 				"Event ID Event Name.*", false);
	 		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
	 				".text", rex);
	 		if (null != objs && objs.length > 0) {
	 			IHtmlTable tableGrid=(IHtmlTable)objs[0];
	 			int colCounts = tableGrid.columnCount();
	 			for (int i = 0; i < colCounts; i++) {
	 				if (tableGrid.getCellValue(0, i).toString().equalsIgnoreCase(colName)) {
	 					Browser.unregister(objs);
	 					return i;
	 				}
	 			}
	 		}
	 		Browser.unregister(objs);
	 		return -1;
	 }
}
