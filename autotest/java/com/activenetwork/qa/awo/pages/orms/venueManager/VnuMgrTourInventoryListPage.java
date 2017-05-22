/*
 * Created on Mar 8, 2010
 */
package com.activenetwork.qa.awo.pages.orms.venueManager;

import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.pages.orms.common.popup.OrmsReasonPopupPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;



/**
 * @author QA
 */
public class VnuMgrTourInventoryListPage extends VenueManagerPage {
  	private static VnuMgrTourInventoryListPage _instance = null;

	String reason = "qa-test";

	public static VnuMgrTourInventoryListPage getInstance() {
		if (null == _instance) {
			_instance = new VnuMgrTourInventoryListPage();
		}
		return _instance;
	}

	private VnuMgrTourInventoryListPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				new RegularExpression("Tour Inventory List",false));
	}

	/** Click on Add Tour Inventory link. */
	public void clickAddTourInventory() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Add Tour Inventory");
	}
	
	/** Click Notes & Alerts link. */
	public void clickNotes() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Notes & Alerts");
	}

	/** Click on GO. */
	public void clickGo() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
		browser.clickGuiObject(".class", "Html.A", ".id", "goAnchor"); //Lesley[20131015]: Search button instead of Go button in 3.05.00
	}

	/** Click on Activate link. */
	public void clickActive() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate");
	}

	/** Click on Edit link. */
	public void clickEditTourInv() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Edit Tour Inventory");
	}

	/** Click on Deactivate link. */
	public void clickDeactivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}

	/** Click on Cancel Tour Inventory link. */
	public void clickCancelTourInv() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel Tour Inventory");
	}

	/** Click on Delete link. */
	public void clickDelete() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Delete");
	}

	/**
	 * Select tour by given name.
	 * @param tour - tour name
	 */
	public void selectTour(String tour) {
		browser.selectDropdownList(".id",
				"TourEventSearchCriteria.tour", tour);
	}
	
	/**
	 * Fill in start date.
	 * @param fDate - from date
	 */
	public void setFromDate(String fDate) {
	  	if(null!=fDate && fDate.length()>0)
	  	  	browser.setTextField(".id",
				"TourEventSearchCriteria.fromDate_ForDisplay", fDate,0,IText.Event.LOSEFOCUS);
//	  	this.moveFocusOutOfDateComponent();
	}

	/**
	 * Fill in end date.
	 * @param tDate - end date
	 */
	public void setToDate(String tDate) {
	  	if(null!=tDate && tDate.length()>0)
	  	  	browser.setTextField(".id",
				"TourEventSearchCriteria.toDate_ForDisplay", tDate,0,IText.Event.LOSEFOCUS);
//	  	this.moveFocusOutOfDateComponent();
	}

	/**
	 * Fill in start time.
	 * @param fTime - from time
	 */
	public void setFromTime(String fTime) {
	  	if(null!=fTime && fTime.length()>0)
	  	  	browser.setTextField(".id","time_0", fTime);
	}

	/**
	 * Fill in end time.
	 * @param tTime - end time
	 */
	public void setToTime(String tTime) {
	  	if(null!=tTime && tTime.length()>0)
	  	  browser.setTextField(".id","time_1", tTime);
	}
	
	/**
	 * Select status by given status.
	 * @param status
	 */
	public void selectStatus(String status) {
	  	if(null!=status && status.length()>0)
	  	  	browser.selectDropdownList(".id",
				"TourEventSearchCriteria.tourStatus", status);
	}

	/** Check all check boxes. */
	public void checkAllCheckbox() {
		browser.selectCheckBox(".name", "all_slct", true);
	}

	/**
	 * Verify No tour found.
	 * @return
	 */
	public boolean verifyNoResult() {
		boolean noRes = false;
		IHtmlObject[] objs = browser.getHtmlObject(".id","Messages");
		if (objs.length>0) {
		  	String msg = objs[0].getProperty(".text").toString();
		  	noRes = msg.matches("^There are no tour inventories.*");
		}
		Browser.unregister(objs);
		return noRes;
	}

	/**
	 * Verify whether or not the inventory has been added.
	 * @return
	 */
	public boolean verifyAddSuccess(){
	  	boolean toReturn = false;
		IHtmlObject[] objs = browser.getHtmlObject(".id","NOTSET");
		if (objs.length>0) {
		  	String msg = objs[0].getProperty(".text").toString();
		  	toReturn = msg.matches(".*inventory has been successfully created.");
		}
		Browser.unregister(objs);
		return toReturn;
	}
	
	/** If Next button is enable, Click NextButton. */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".text", "Next");
		boolean toReturn = false;
		if (objs.length > 0) {
			toReturn = true;
			(objs[0]).click();
		}
		Browser.unregister(objs);
		this.waitLoading();
		return toReturn;
	}

	/**
	 * Fill in start time stamp.
	 * @param stamp - AM or PM
	 */
	public void selectFromTimeStamp(String stamp) {
	  	if(null!=stamp && stamp.length()>0)
	  	  	browser.selectDropdownList(".id", "ampm_0", stamp);
	}

	/**
	 * Fill in end time stamp.
	 * @param stamp - AM or PM
	 */
	public void selectToTimeStamp(String stamp) {
	  	if(null!=stamp && stamp.length()>0)
	  		browser.selectDropdownList(".id", "ampm_1", stamp);
	}
	
	/**
	 * Search tour inventory.
	 * @param InventoryInfo tour inventory info
	 */
	public void searchTourInventory(InventoryInfo tourInv) {
		this.selectTour(tourInv.tourName);
		this.setFromDate(tourInv.startDate);
		this.setToDate(tourInv.endDate);
		this.selectStatus(tourInv.status);
		this.setFromTime(tourInv.firstTime);
		this.selectFromTimeStamp(tourInv.firstTimeStamp);
		this.setToTime(tourInv.lastTime);
		this.selectToTimeStamp(tourInv.lastTimeStamp);
		this.clickGo();
		this.waitLoading();
	}
			
	public IHtmlTable getNonTimeSpecificTourInventoryTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".text", 
				new RegularExpression("(| )Tour(| )Date(| )Open Time(| )Close Time(| )Last Entry Time.*",false));
		if(objs.length<1){
			throw new ObjectNotFoundException("Did not found non time specific tour inventory table object");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		return table;
	}
	
	public String getNonTimeSpecificTourOpenTime(String startTime){
		startTime = DateFunctions.formatDate(startTime, "EEE MMM d yyyy");
		IHtmlTable table = this.getNonTimeSpecificTourInventoryTable();
		int row = table.findRow(2, startTime);
		
		String value = table.getCellValue(row, 3).trim();
		
		Browser.unregister(table);
		return value;		
	}
	
	public String getNonTimeSpecificTourCloseTime(String startTime){
		IHtmlTable table = this.getNonTimeSpecificTourInventoryTable();
		int row = table.findRow(2, startTime);
		
		String value = table.getCellValue(row, 4).trim();
		
		Browser.unregister(table);
		return value;		
	}
	
	public void clickHome(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Home");
	}

	/** Handle the pop-up window reason box. */
	public void closePopupWindow() {
		OrmsReasonPopupPage reasonPopupPg = OrmsReasonPopupPage.getInstance();
		reasonPopupPg.waitLoading();
		reasonPopupPg.closePopupReasonBox(reason);
	}
	
	/**
	 * Verify whether the tour inventory results is more than one page.
	 * @return true - more than one; false - only one page
	 */
	public boolean isFindNext(){
	  	boolean toReturn = false;
	  	
	  	IHtmlObject[] objs=browser.getHtmlObject(".class","Html.A",".text",new RegularExpression("Next",false));
	  	if(objs.length>0){
	  	  	toReturn = true;
	  	}
	  	Browser.unregister(objs);
	  	
	  	return toReturn;
	}
	
	public int getUnsoldTicketsNum(){
		IHtmlObject[] objs = browser.getTableTestObject(".id","tourInvByTimeInterval");
		
		if(objs.length<0){
			throw new ErrorOnPageException("Could not get tour inventory");
		}
		
		IHtmlTable table = (IHtmlTable) objs[0];
		if(table.rowCount()<2){
			return 0;
		}
		
		//column 4: Total Sales/Active Capcity
		String[] nums = table.getCellValue(1, 4).split("/");
		int sales = Integer.valueOf(nums[0]);
		int capcity = Integer.valueOf(nums[1]);
		
		Browser.unregister(objs);
		
		return capcity-sales;
	}
}
