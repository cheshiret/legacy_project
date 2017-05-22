/*
 * Created on Mar 8, 2010
 */
package com.activenetwork.qa.awo.pages.orms.venueManager;

import java.util.Date;

import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.KeyInput;
import com.activenetwork.qa.testapi.util.StringUtil;



/**
 * @author QA
 */
public class VnuMgrAddTourInventoryPage extends VenueManagerPage {
  	private static VnuMgrAddTourInventoryPage _instance = null;

	public static VnuMgrAddTourInventoryPage getInstance() {
		if (null == _instance) {
			_instance = new VnuMgrAddTourInventoryPage();
		}
		return _instance;
	}

	private VnuMgrAddTourInventoryPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Create Tour Inventory");
	}

	/** Click quota inventory search/edit. */
	public void clickTourInventoryList() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Tour Inventory List");
	}

	/** Click Notes & Alerts link. */
	public void clickNotes() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Notes & Alerts");
	}

	/** Click on OK. */
	public void clickCreateTourInventory() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Create Tour Inventory");
	}

	/** Click on Cancel. */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**
	 * Select tour by given name.
	 * @param tour - tour name
	 */
	public void selectTour(String tour) {
		browser.selectDropdownList(".id","TourScheduleView.tour", tour);
	}

	/**
	 * Select time recurrence type
	 * @param type
	 */
	public void selectTimeRecurrenceType(String type){
	  	browser.selectDropdownList(".id","TourScheduleView.timeRecurrenceType", type);
	}

	/**
	 * Fill in hours.
	 * @param hour
	 */
	public void setHours(String hour){
	  	browser.setTextField(".id","TourScheduleView.timePatternHrs",hour);
	}

	/**
	 * Fill in minutes
	 * @param min
	 */
	public void setMinutes(String min){
	  	browser.setTextField(".id","TourScheduleView.timePatternMins",min);
	}

	/**
	 * Fill in first hours.
	 * @param hour
	 */
	public void setFirstTourTime(String hour){
	  	browser.setTextField(".id","time_0",hour);
	}

	/**
	 * Fill in last hours.
	 * @param hour
	 */
	public void setLastTourTime(String hour){
	  	browser.setTextField(".id","time_1",hour);
	}

	/**
	 * Select first time stamp
	 * @param stamp
	 */
	public void selectFirstStamp(String stamp){
	  	browser.selectDropdownList(".id","ampm_0", stamp);
	}

	/**
	 * Select last time stamp
	 * @param stamp
	 */
	public void selectLastStamp(String stamp){
	  	browser.selectDropdownList(".id","ampm_1", stamp);
	}

	/**
	 * Fill in specific start time
	 * @param time
	 */
	public void setSpecificStartTime(String time){
	  	browser.setTextField(".id","time",time);
	}

	/**
	 * Select time stamp for specific start time
	 * @param stamp
	 */
	public void selectSpecificTimeStamp(String stamp){
	  	browser.selectDropdownList(".id","ampm", stamp);
	}

	/**
	 * Click to add specific start time
	 */
	public void clickAdd(){
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Add");
	}

	/**
	 * Click to remove specific start time
	 */
	public void clickRemove(){
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Remove");
	}

	public void setOpenTime(String openTime){
		browser.setTextField(".id", "time_0", openTime);
	}

	public void selectOpenTimeAmPm(String openTimeAmPm){
		browser.selectDropdownList(".id", "ampm_0", openTimeAmPm);
	}

	public void setCloseTime(String closeTime){
		browser.setTextField(".id", "time_1", closeTime);
	}

	public void selectCloseTimeAmPm(String closeTimeAmPm){
		browser.selectDropdownList(".id", "ampm_1", closeTimeAmPm);
	}

	public void setLastEntryTime(String lastEntryTime){
		browser.setTextField(".id", "time_2", lastEntryTime);
	}

	public void selectLastEntryTimeAmPm(String lastEntryTimeAmPm){
		browser.selectDropdownList(".id", "ampm_2", lastEntryTimeAmPm);
	}

	/**
	 * Select time recurrence type
	 * @param type
	 */
	public void selectDateRecurrenceType(String type){
	  	browser.selectDropdownList(".id","TourScheduleView.dateRecurrenceType", type);
	}

	/**
	 * Fill in start date
	 * @param date
	 */
	public void setStartDate(String date){
	  	browser.setTextField(".id","TourScheduleView.startDate_ForDisplay",date);
	}

	/**
	 * Fill in end date
	 * @param date
	 */
	public void setEndDate(String date){
	  	browser.setTextField(".id","TourScheduleView.endDate_ForDisplay",date);
	}

	/**
	 * Check the recurring date by object index
	 * @param index
	 */
	public void checkDayByIndex(int index){
	  	browser.selectCheckBox(".id","day_"+index);
	}

	/** Check Monday as recurring date*/
	public void checkMonday(){
	  	browser.selectCheckBox(".id","day_1");
	}

	/** Check Tuesday as recurring date*/
	public void checkTuesday(){
	  	browser.selectCheckBox(".id","day_2");
	}

	/** Check Wednesday as recurring date*/
	public void checkWednesday(){
	  	browser.selectCheckBox(".id","day_3");
	}

	/** Check Thursday as recurring date*/
	public void checkThursday(){
	  	browser.selectCheckBox(".id","day_4");
	}

	/** Check Friday as recurring date*/
	public void checkFriday(){
	  	browser.selectCheckBox(".id","day_5");
	}

	/** Check Saturday as recurring date*/
	public void checkSaturday(){
	  	browser.selectCheckBox(".id","day_6");
	}

	/** Check Sunday as recurring date*/
	public void checkSunday(){
	  	browser.selectCheckBox(".id","day_0");
	}

	/**
	 * Fill in tour capacity
	 * @param capacity
	 */
	public void setCapacity(String capacity){
	  	browser.setTextField(".id", "TourScheduleView.capacity",capacity);
	}

	/** Click on Active. */
	public void checkActive() {
		browser.clickGuiObject(".id","tour_active_true");
	}

	/** Click on Inactive. */
	public void checkInavtive() {
		browser.clickGuiObject(".id","tour_inactive_false");
	}

	/**
	 * set up all criteria when adding new tour inventories.
	 * @param tourInv - tour inventory data
	 * @param isTimeSpecified - is time specified?
	 * @param isDateSpecified - is date specified?
	 */
	public void setupTourInvCriteria(InventoryInfo tourInv, boolean isTimeSpecified,boolean isDateSpecified,boolean isNoTimeSpecific) {
		this.waitLoading();
		this.selectTour(tourInv.tourName);
		this.waitLoading();
		if(isTimeSpecified){// choose the time recurrence type
		  	this.selectTimeRecurrenceType("Specified Times");
		  	this.waitLoading();
		  	//only can add one tour time in this method
		  	this.setSpecificStartTime(tourInv.firstTime);
		  	this.selectSpecificTimeStamp(tourInv.firstTimeStamp);
		  	this.clickAdd();
		}else if(isNoTimeSpecific){
			this.selectTimeRecurrenceType("Non Time-Specific Range");
			this.waitLoading();

			this.setOpenTime(tourInv.openTime);
			this.selectOpenTimeAmPm(tourInv.openTimeAmPm);
			this.setCloseTime(tourInv.closeTime);
			this.selectCloseTimeAmPm(tourInv.closeTimeAmPm);
			this.setLastEntryTime(tourInv.lastEntryTime);
			this.selectLastEntryTimeAmPm(tourInv.lastEntryTimeAmPm);
		}else {
		  	this.selectTimeRecurrenceType("Regular Recurring Interval");
		  	this.waitLoading();

		  	this.setHours(tourInv.recuHour);
		  	this.setMinutes(tourInv.recuMins);
		  	this.setFirstTourTime(tourInv.firstTime);
		  	this.selectFirstStamp(tourInv.firstTimeStamp);
		  	this.setLastTourTime(tourInv.lastTime);
		  	this.selectLastStamp(tourInv.lastTimeStamp);
		}

		if(isDateSpecified){
		  	this.selectDateRecurrenceType("Single Date");
		  	this.waitLoading();

		  	this.setStartDate(tourInv.startDate);
		}else {
		  	this.selectDateRecurrenceType("Recurring Date");
		  	this.waitLoading();

		  	this.setStartDate(tourInv.startDate);
		  	browser.inputKey(KeyInput.get(KeyInput.TAB));

		  	this.setEndDate(tourInv.endDate);
		  	browser.inputKey(KeyInput.get(KeyInput.TAB));

		  	// check the days of week
		  	Date startDate = DateFunctions.parseDateString(tourInv.startDate);
		  	Date endDate = DateFunctions.parseDateString(tourInv.endDate);
		  	int days = DateFunctions.daysBetween(startDate, endDate) + 1;

		  	if(DateFunctions.compareDates(tourInv.endDate, tourInv.startDate)<0){
		  	  	throw new ItemNotFoundException("End date should after start date.");
		  	}

		  	if(days < 7){
		  	  	tourInv.startDate = DateFunctions.formatDate(tourInv.startDate,
		  	  	    							"EEE MMMMM dd yyyy");
		  	  	int index=8;//week object index
		  	  	String week = tourInv.startDate.split(" ")[0];

		  	  	if(week.equals("Sun"))
		  	  	  	index = 0;
		  	  	else if(week.equals("Mon"))
		  	  	  	index = 1;
		  	  	else if(week.equals("Tue"))
		  	  	  	index = 2;
		  	  	else if(week.equals("Wed"))
		  	  	  	index = 3;
		  	  	else if(week.equals("Thu"))
		  	  	  	index = 4;
		  	  	else if(week.equals("Fri"))
		  	  	  	index = 5;
		  	  	else if(week.equals("Sat"))
		  	  	  	index = 6;

		  	  	if(index == 8){
		  	  	  	throw new ErrorOnPageException("Failed to format date.");
		  	  	}else {
			  	  	for(int i=0; i<days; i++){
			  	  	  	this.checkDayByIndex(index);
			  	  	  	index++; // increase the object index to check next day
			  	  	  	if(index == 7)
			  	  	  	  	index = 0; // reset index to 0 to check Sunday
			  	  	}
		  	  	}
		  	} else {
		  		for(int i=0; i<7; i++){
		  	  	  	this.checkDayByIndex(i);
		  	  	}
		  	}
		}
		moveForcus();
		if(!StringUtil.isEmpty(tourInv.capacity.trim())){
			this.setCapacity(tourInv.capacity);
		}
		this.checkActive();
	}
	
	private void moveForcus(){
		browser.inputKey(KeyInput.get(KeyInput.TAB));
	}
}
