/*
 * $Id: InvMgrEventsPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.serviceEvents;


import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EventInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author CGuo
 */
public class InvMgrEventsPage extends InventoryManagerPage {

	/**
	 * Script Name   : InvMgrEventsPage
	 * Generated     : Jan 3, 2006 3:59:01 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2006/01/03
	 */
	private static InvMgrEventsPage _instance = null;

	public static InvMgrEventsPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrEventsPage();
		}

		return _instance;
	}

	protected InvMgrEventsPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", "Add New Event");
	}
	
	public void clickAddNewEvent(){
	  browser.clickGuiObject(".class","Html.A",".text","Add New Event");
	}
	
	public IHtmlObject[] getEventTable(){
		return browser.getHtmlObject(".class","Html.TABLE",".text",
				new RegularExpression("^ID Type Name Icon Start Date End Date",false));
	}
	
	public String getEventInfo(){
	  IHtmlObject[] objs=this.getEventTable();
	  String eventInfo=objs[0].getProperty(".text").toString();
	  Browser.unregister(objs);
	  
	  return eventInfo;
	}
	
	/**
	 * Get the first event information
	 * @return
	 */
	public EventInfo getEventDetailInfo(){
		EventInfo event = new EventInfo();
		  IHtmlObject[] objs=this.getEventTable();
		  if(objs.length>0){
			  IHtmlTable table = (IHtmlTable)objs[0];
			  event.eventID = table.getCellValue(1, 1);
			  event.eventType = table.getCellValue(1, 2);
			  event.eventName = table.getCellValue(1, 3);
			  event.eventDescription = event.eventName;
			  event.eventStart= table.getCellValue(1, 5);
			  event.eventEnd= table.getCellValue(1, 6);
		  }else throw new ObjectNotFoundException("Object can't find.");
		  Browser.unregister(objs);
		  return event;
		}
	
	public boolean checkEventExist(String eventID){
	  return browser.checkHtmlObjectExists(".class", "Html.A", ".text", eventID);
	}
	
	public void clickEventID(String eventID){
	  browser.clickGuiObject(".class","Html.A",".text",eventID);
	}
	
	public void selectEventCheckbox(String eventID){
	  browser.selectCheckBox(".class","Html.INPUT.checkbox",".value",new RegularExpression(eventID+".*",false));
	}
	
	public void clickRemove(){
	  browser.clickGuiObject(".class","Html.A",".text","Remove");
	}
	
	/**
	 * Select specific Event by rowNum
	 * @param rowNum
	 */
	public void selectEventCheckBoxByID(String rowNum){
		browser.clickGuiObject(".id","row_"+(Integer.parseInt(rowNum)-1)+"_checkbox",".type","checkbox");
	}
	
	/**
	 * Select specific Event by rowNums
	 * @param rowNums
	 */
	public void selectEventCheckBoxByIDs(String[] rowNums){
		if(rowNums==null || rowNums.length<1) //do nothing
			return;

		for(int i=0;i<rowNums.length;i++) {
			selectEventCheckBoxByID(rowNums[i]);
		}
	}
	
	/**
	 * Click the button 'View Change Request Items'
	 */
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
	}
	
    /**
	 * Get warning message
	 * @return
	 */
	public String getWarningMessage(){
		String warningMessage = "";
		
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length>0){
			warningMessage = objs[0].getProperty(".text").toString();
		}else throw new ObjectNotFoundException("Object can't find.");
		
		Browser.unregister(objs);
		return warningMessage;
	}
	
	/**
	 * Get specific event row Numbers via event names
	 * @param eventNames
	 * @return
	 */
	public String getSpecificServicActivitRowNum(String[] eventNames){
		List<String> eventUINames = new ArrayList<String>();
		int eventRowNum = 0;
		String eventRowNums = "";

		IHtmlObject[] objs = this.getEventTable();
		if(objs.length>0){
			do{
				for(int i=0; i<eventNames.length; i++){
					IHtmlTable table = (IHtmlTable)objs[0];
						eventUINames = table.getColumnValues(3);
						for(int j=0; j<eventUINames.size(); j++){
		                    if(eventUINames.get(j).equals(eventNames[i])){
		                    	++eventRowNum;
		                    	eventRowNums = eventRowNums + String.valueOf(j)+"#";
		                    }
						}
				}
			}while(eventNames.length != eventRowNum && gotoNext());
		}else throw new ObjectNotFoundException("Object doesn't find.");
		
		return eventRowNums;
	}
	
	/**
	 * Check all the value of specific String Array aren't null
	 * @param stringArray
	 * @return
	 */
	public boolean CheckStringArrayExistNullValue(String[] stringArray){
		boolean existNull = false;
		
		for(int i=0; i<stringArray.length; i++){
			if(!(null!=stringArray[i] && stringArray[i].length()>0)){
				existNull = true;
			}
		}
		return existNull;
	}
	
	/**
	 * If "Next" button is avaliable, click next button in reservation search page.
	 *
	 */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",".text", "Next" );
		boolean toReturn = false;

		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}

		Browser.unregister(objs);
		this.waitLoading();

		return toReturn;
	}
	/**get event number*/
	public int getEventNum(){
		int num = 0;
		  IHtmlObject[] objs=this.getEventTable();
		  if(objs.length>0){
			  IHtmlTable table = (IHtmlTable)objs[0];
			  num = table.rowCount()-1;
		  }else throw new ObjectNotFoundException();
		  
		  Browser.unregister(objs);
		  return num;
	}
}
