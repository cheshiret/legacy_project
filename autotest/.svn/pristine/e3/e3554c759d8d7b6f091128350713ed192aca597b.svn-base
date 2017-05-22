/*
 * $Id: InvMgrEventDetailPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
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
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author CGuo
 */
public class InvMgrEventDetailPage extends InventoryManagerPage {

	/**
	 * Script Name   : InvMgrEventDetailPage
	 * Generated     : Jan 3, 2006 4:08:13 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2006/01/03
	 */

	private static InvMgrEventDetailPage _instance = null;

	public static InvMgrEventDetailPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrEventDetailPage();
		}

		return _instance;
	}

	protected InvMgrEventDetailPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Service, Activity, Event Details");
	}
	
	public void setEventName(String eventName) {
	   browser.setTextField(".id", "LocationActivityServiceView.activityServiceName", eventName);
	}
	
	public void selectType(String type) {
	   browser.selectDropdownList(".id", "LocationActivityServiceView.typeID", type);
	}
	
	public void selectIcon(String icon) {
	   browser.selectDropdownList(".id", "LocationActivityServiceView.iconName", icon);
	}
	
	public void selectDistanceCode(String distanceCode) {
	   browser.selectDropdownList(".id", "LocationActivityServiceView.distanceTypeID", distanceCode);
	}
	
	public void setDescription(String description) {
	   browser.setTextArea(".id", "LocationActivityServiceView.activityServiceDscr", description);
	}
	
	public void setPublishDate(String pubDate) {
	  browser.setTextField(".id", "LocationActivityServiceView.publishDate_ForDisplay", pubDate, 0, IText.Event.LOSEFOCUS);
//	  this.refreshPage();
	}
	
	public void setStartDate(String startDate) {
	  browser.setTextField(".id", "LocationActivityServiceView.startDate_ForDisplay", startDate, 0, IText.Event.LOSEFOCUS);
//	  this.refreshPage();
	}
	
	public void setEndDate(String endDate) {
	   browser.setTextField(".id", "LocationActivityServiceView.endDate_ForDisplay", endDate, 0, IText.Event.LOSEFOCUS);
//	   this.refreshPage();
	}
	
	public void clickOK() {
	  browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickApply() {
	  browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	
	public void clickCancel() {
	  browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	/**
	 * Get the eventID
	 * @return
	 */
	public String getEventID() {
	  IHtmlObject[] obj=browser.getTableTestObject(".text", new RegularExpression("^Service, Activity, Event.*ID", false));
	  String eventId = ((IHtmlTable)obj[0]).getCellValue(0, 2).split("ID")[1].trim();
	  
	  Browser.unregister(obj);
	  return eventId;
	}
	
	/**
	 * Set event info
	 * @param event
	 */
	public void setEventInfo(EventInfo event) {
		ConfirmDialogPage confirmPage = ConfirmDialogPage.getInstance();
		
		if(event!=null){
			if(event.eventType != null) {
				this.selectType(event.eventType);
			}
			if(event.eventIcon != null) {
				this.selectIcon(event.eventIcon);
			}
			if(event.distanceCode != null) {
				this.selectDistanceCode(event.distanceCode);
			}

			this.setEventName(event.eventName);
			this.setDescription(event.eventDescription);
			this.setPublishDate(event.publishDate);
			browser.waitExists(this, confirmPage);//wait and dismiss date component validation
			this.setStartDate(event.eventStart);
			browser.waitExists(this, confirmPage);//wait and dismiss date component validation
			this.setEndDate(event.eventEnd);
			browser.waitExists(this, confirmPage);//wait and dismiss date component validation
		}
	}
	

	//	public String addEvent(String type, String name, String description, String icon,
	//		String distanceCode, String publishDate, String startDate, String endDate,
	//		String addToConfLetter, String orderNotification
	//		){
	//		
	//		MiscFunctions.selectItem(list_type(), type);
	//		MiscFunctions.setText(text_inpName(), name);
	//		MiscFunctions.setText(text_dscr(), description);
	//		MiscFunctions.selectItem(list_icon(), icon);
	//		MiscFunctions.selectItem(list_dist(), distanceCode);
	//		
	//		MiscFunctions.setText(text_publish_ForDisplay(), publishDate);
	//		MiscFunctions.setText(text_startDate_ForDisplay(), startDate);
	//		MiscFunctions.setText(text_endDate_ForDisplay(), endDate);
	//		
	//		if(!addToConfLetter.equals("")){
	//			checkBox_conflettertrue().deselect();
	//		}
	//		if(!orderNotification.equals("")){
	//			checkBox_ordernotifytrue().deselect();
	//		}
	//		link_apply().click();
	//
	//		String toReturn = HTMLTable.getCellValue(table_htmlTable_2(),0,2).toString().split(" ")[1];
	//		link_ok().click();
	//		
	//		return toReturn;
	//	}

	/**
	 * Check button 'View Change Request Items' exist or not
	 * @return
	 */
	public boolean checkViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		return browser.checkHtmlObjectExists(".class", "Html.A", ".href", rex);
	}
	
    /**
	 * Get one warning message
	 * @return
	 */
	public String getOneWarningMessage(){
		String warningMessage = "";
		
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length>0){
			warningMessage = objs[0].getProperty(".text").toString();
		}else throw new ObjectNotFoundException("Object can't find.");
		
		Browser.unregister(objs);
		return warningMessage;
	}
	
	/**
	 * Get more than one warning message
	 * @return
	 */
	public List<String> getMoreThanOneWarningMessage(){
		List<String> warningMessages = new ArrayList<String>();
		
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length>0){
			for(int i=0; i<objs.length; i++){
				warningMessages.add(objs[i].getProperty(".text").toString());
			}
		}else throw new ObjectNotFoundException("Object can't find.");
		
		Browser.unregister(objs);
		return warningMessages;
	}
	
	/**
	 * Click the button 'View Change Request Items'
	 */
	public void clickViewChangeRequestItems(){
		RegularExpression rex = new RegularExpression("\"SearchCRIListFromViewCRIButton\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href",rex);
	}
	
	public void refreshPage(){
		browser.clickGuiObject(".class","Html.FORM",".id","e_Form");
	}
}
