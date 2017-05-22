/*
 * Created on Nov 3, 2009
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.TourInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @author vzhao You can access this page by clicking on specified tour's code
 *         link
 */
public class InvMgrTourDetailsPage extends InventoryManagerPage {
	private static InvMgrTourDetailsPage _instance = null;

	public static InvMgrTourDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrTourDetailsPage();
		}
		return _instance;
	}

	protected InvMgrTourDetailsPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "OK");
	}

	/** Click on Tour Tickets link. */
	public void clickTourTickets() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Tour Tickets");
	}
	
	/** Click on Participant Data link. */ 
	public void clickParticipantData() {
//		browser.clickGuiObject(".className", "tabanchor", ".text", "Participant Data");
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Participant Data");
	}

	/** Click on Tour Allocation link. */
	public void clickTourAllocation() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Tour Allocation");
	}

	/**
	 * Select whether or not to turn on time conflict from drop down list.
	 * 
	 * @param item
	 */
	public void selectTimeConflict(String item) {
			browser.selectDropdownList(".id", "timeConflictManagement", item);
	}

	/** Turn on time conflict. */
	public void turnOnTimeConflict() {
		selectTimeConflict("Yes");
	}

	/** Turn off time conflict. */
	public void turnOffTimeConflict() {
		selectTimeConflict("No");
	}

	/** Click on OK link. */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/** Click Delete Tour Button **/
	public void clickDeleteTour() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Delete this Tour");
	}

	public void clickAssignTours() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Assigned Tours");
	}

	public void setDefaultCapacity(String capacity) {
		browser.setTextField(".id", "defaultCapacity", capacity);
	}

	public void setSortOrder(String sortorder) {
		browser.setTextField(".id", "sortKey", sortorder);
	}

	public void setNumOfSteps(String num) {
		browser.setTextField(".id", "Number of Steps", num);
	}

	public void setDistance(String distance) {
		browser.setTextField(".id", "Distance (miles)", distance);
	}

	public void setTourCode(String code) {
		browser.setTextField(".id", "sitecode", code);
	}

	public void setTourName(String name) {
		browser.setTextField(".id", "sitename", name);
	}

	public void setTicketTourName(String tourName) {
		browser.setTextField(".id", "shortname", tourName);
	}

	public void setDescription(String description) {
//		browser.setTextArea(".id", "sitedescription", description);
		browser.setTextField(".id", "sitedescription", description);
	}

	public void setBaseCount(String count) {
		browser.setTextField(".id", "baseGroupCount", count);
	}

	public void setLongDescription(String lDescription) {
		browser.setTextArea(".id", "Long Description", lDescription);
	}

	public void setDuration(String duration) {
		browser.setTextField(".id", "duration", duration);
	}

	public void selectTourType(String type) {
		browser.selectDropdownList(".id", "tourType", type);
		waitLoading();
	}

	public void selectFeeClass(String fee) {
		browser.selectDropdownList(".id", "feeClass", fee);
	}

	public void selectTiketCategory(String categroy) {
		browser.selectDropdownList(".id", "salesType", categroy);
	}

	public void selectRateType(String ratetype) {
		browser.selectDropdownList(".id", "prdRateType", ratetype);
		waitLoading();
	}

	public void selectSoldIndividually(String item) {
		browser.selectDropdownList(".id", "soldIndividually", item, true);
	}

	public void selectTimedEntry(String item) {
		browser.selectDropdownList(".id", "timedEntry", item);
		waitLoading();
	}

	public void selectLimitedCapacity(String item) {
		browser.selectDropdownList(".id", "limitedCapacity", item);
		waitLoading();
	}
	
	public void selectMultiDay(String item){
		browser.selectDropdownList(".id", "tour_multidays", item);
	}
	
	public void setValidDays(String item){
		browser.setTextField(".id", "tour_validdays", item);
	}
	
	public void setEntryDays(String item){
		browser.setTextField(".id", "tour_entrydays", item);
	}

	public void selectADAAccessible(String item) {
//		browser.selectDropdownList(".id", "ADA Accessible", item);
		browser.selectDropdownList(".id", "Accessible Site", item);//Quentin[20131106]
	}

	public void setAccessibility(String accessibility) {
		browser.setTextArea(".id", "Accessibility", accessibility);
	}

	public void setAmenities(String amenities) {
		browser.setTextArea(".id", "Amenities", amenities);
	}

	public void setDirectionsToTour(String directions) {
		browser.setTextArea(".id", "Directions To Tour", directions);
	}

	public void setImportantInfo(String info){
		browser.setTextArea(".id", "Important Information", info);
	}
	
	public void setPhysicalEffort(String item) {
		browser.selectDropdownList(".id", "Physical Effort", item);
	}

	public void setDaysHoursDescription(String description) {
		browser
				.setTextArea(".id", "Tour Days / Hours Description",
						description);
	}
	
	public boolean checkMultiDayEditable(){
		IHtmlObject[] objs=browser.getDropdownList(".id", "tour_multidays");
		boolean flag=!Boolean.parseBoolean(objs[0].getAttributeValue("isDisabled"));
		Browser.unregister(objs);
		return flag;
	}
	
	public boolean checkValidDayEditable(){
		IHtmlObject[] objs=browser.getTextField(".id", "tour_validdays");
		boolean flag=!Boolean.parseBoolean(objs[0].getAttributeValue("isDisabled"));
		Browser.unregister(objs);
		return flag;
	}
	
	public boolean checkEntryDayEditable(){
		IHtmlObject[] objs=browser.getTextField(".id", "tour_entrydays");
		boolean flag=!Boolean.parseBoolean(objs[0].getAttributeValue("isDisabled"));
		Browser.unregister(objs);
		return flag;
	}
	
	public boolean isGroupCountDisable(){
		boolean flag=false;
		IHtmlObject[] objs=browser.getTextField(".id", "baseGroupCount");
		flag=!Boolean.parseBoolean(objs[0].getAttributeValue("isContentEditable"));
		Browser.unregister(objs);
		return flag;
	}

	public void setDaysOfWeek(Map<String, Boolean> map) {
		if (map.get("Mon")) {
			browser.selectCheckBox(".id", "Tour Days of Week__Monday");
		} else {
			browser.unSelectCheckBox(".id", "Tour Days of Week__Monday");
		}
		
		if (map.get("Tue")) {
			browser.selectCheckBox(".id", "Tour Days of Week__Tuesday");
		} else {
			browser.unSelectCheckBox(".id", "Tour Days of Week__Tuesday");
		}
		
		if (map.get("Wed")) {
			browser.selectCheckBox(".id", "Tour Days of Week__Wednesday");
		} else {
			browser.unSelectCheckBox(".id", "Tour Days of Week__Wednesday");
		}
		
		if (map.get("Thu")) {
			browser.selectCheckBox(".id", "Tour Days of Week__Thursday");
		} else {
			browser.unSelectCheckBox(".id", "Tour Days of Week__Thursday");
		}
		
		if (map.get("Fri")) {
			browser.selectCheckBox(".id", "Tour Days of Week__Friday");
		} else {
			browser.unSelectCheckBox(".id", "Tour Days of Week__Friday");
		}
		
		if (map.get("Sat")) {
			browser.selectCheckBox(".id", "Tour Days of Week__Saturday");
		} else {
			browser.unSelectCheckBox(".id", "Tour Days of Week__Saturday");
		}
		
		if (map.get("Sun")) {
			browser.selectCheckBox(".id", "Tour Days of Week__Sunday");
		} else {
			browser.unSelectCheckBox(".id", "Tour Days of Week__Sunday");
		}
	}
	
	public void setTourImage(String image){
		browser.setTextField(".id", "Tour Image", image);
	}
	
	public void setTourURL(String url){
		browser.setTextField(".id", "Tour URL", url);
	}

	public void setTour(TourInfo tour) {
		setTourCode(tour.tourCode);
		setTourName(tour.tourName);
		setTicketTourName(tour.ticketTourName);
		this.selectTourType(tour.tourType);
		this.selectFeeClass(tour.tourFeeClass);
		setDescription(tour.description);
		this.selectTiketCategory(tour.ticketCategory);
		if(tour.rateType.length()>0)
		this.selectRateType(tour.rateType);
		
		if(!this.isGroupCountDisable()){
		    setBaseCount(tour.count);
		}
		if (tour.isSoldIndividual) {
			this.selectSoldIndividually("Yes");
		} else {
			this.selectSoldIndividually("No");
		}
		setDuration(tour.duration);
		this.selectTimedEntry(tour.timeEntry);
		this.waitLoading();
		
		//timeEntry=No, time conflict was disabled
		if(StringUtil.notEmpty(tour.timeConflict) && tour.timeEntry.equals("Yes")){
			this.selectTimeConflict(tour.timeConflict);
		}
		if(StringUtil.notEmpty(tour.limitedCapacity)){
			this.selectLimitedCapacity(tour.limitedCapacity);
		}
		
		if(tour.timeEntry.equalsIgnoreCase("No") && tour.multiDay.equalsIgnoreCase("Yes")){
			this.selectMultiDay(tour.multiDay);
			this.waitLoading();
			this.setValidDays(tour.validDays);
			this.setEntryDays(tour.entryDays);
		}
		
		if(tour.limitedCapacity.equalsIgnoreCase("Yes")){
			this.setDefaultCapacity(tour.capacity);
		}
		if(StringUtil.notEmpty(tour.isPreventTicketPrinting)){
			this.selectPreventTicketPrinting(tour.isPreventTicketPrinting);
		}
		setSortOrder(tour.sortOrder);
		this.selectADAAccessible(tour.accessible);
		this.setAccessibility(tour.accessibility);
		this.setAmenities(tour.amenities);
		this.setDirectionsToTour(tour.directions);
		setDistance(tour.distance);
		this.setImportantInfo(tour.information);
		this.setLongDescription(tour.longDescription);
		setNumOfSteps(tour.numOfSteps);
		this.setPhysicalEffort(tour.physicalEffort);
		this.setDaysHoursDescription(tour.days_hoursDescription);
		this.setDaysOfWeek(tour.daysOfWeek);
		this.setTourImage(tour.tourimage);
		this.setTourURL(tour.tourUrl);
	}

	public String getTourCode() {
		return browser.getTextFieldValue(".id", "sitecode");
	}

	public String getTourName() {
		return browser.getTextFieldValue(".id", "sitename");
	}

	public String getTicketTourName() {
		return browser.getTextFieldValue(".id", "shortname");
	}

	public String getTourType() {
		return browser.getDropdownListValue(".id", "tourType", 0);
	}

	public String getFeeClass() {
		return browser.getDropdownListValue(".id", "feeClass", 0);
	}

	public String getDescription() {
//		IHtmlObject[] objs=browser.getTextArea(".id", "sitedescription");
//		if(objs.length<1){
//			throw new ObjectNotFoundException("can't find  description text area....");
//		}
//		
//		String description=objs[0].getProperty(".text");
//		Browser.unregister(objs);
//		return description;
		return browser.getTextFieldValue(".id", "sitedescription");//Quentin[20131105] textarea has been changed to textfield
	}

	public String getTicketCategory() {
		return browser.getDropdownListValue(".id", "salesType", 0);
	}

	public String getRateType() {
		return browser.getDropdownListValue(".id", "prdRateType", 0);
	}

	public String getBaseGroupCount() {
		return browser.getTextFieldValue(".id", "baseGroupCount");
	}

	public String getSoldIndividually() {
		return browser.getDropdownListValue(".id", "soldIndividually", 0);
	}

	public String getDuration() {
		return browser.getTextFieldValue(".id", "duration");
	}
	
	public String getValidDays(){
		return browser.getTextFieldValue(".id", "tour_validdays");
	}

	public String getTimedEntry() {
		return browser.getDropdownListValue(".id", "timedEntry",0);
	}

	public String getTimeConflictManagement() {
		return browser.getDropdownListValue(".id", "timeConflictManagement", 0);
	}

	public String getLimitedCapacity() {
		return browser.getDropdownListValue(".id", "limitedCapacity", 0);
	}

	public String getDefaultCapacity() {
		return browser.getTextFieldValue(".id", "defaultCapacity");
	}

	public String getTourSortOrder() {
		return browser.getTextFieldValue(".id", "sortKey");
	}

	public String getADAAccessible() {
		return browser.getDropdownListValue(".id", "ADA Accessible", 0);//Vivian[20131211]
//		return browser.getDropdownListValue(".id", "Accessible Site", 0);//Quentin[20131106]
	}

	public String getAccessibility() {
		IHtmlObject[] objs=browser.getTextArea(".id", "Accessibility");
		if(objs.length<1){
			throw new ObjectNotFoundException("can't find  long  Accessibility text area....");
		}
		
		String longDescription=objs[0].getProperty(".text");
		Browser.unregister(objs);
		return longDescription;
	}

	public String getAmenities() {
		IHtmlObject[] objs=browser.getTextArea(".id", "Amenities");
		if(objs.length<1){
			throw new ObjectNotFoundException("can't find  long  Amenities text area....");
		}
		
		String text=objs[0].getProperty(".text");
		Browser.unregister(objs);
		return text;
	}

	public String getDirections() {
		IHtmlObject[] objs=browser.getTextArea(".id", "Directions To Tour");
		if(objs.length<1){
			throw new ObjectNotFoundException("can't find  long  Directions text area....");
		}
		String text=objs[0].getProperty(".text");
		Browser.unregister(objs);
		return text;
	}

	public String getDistance() {
		return browser.getTextFieldValue(".id", "Distance (miles)");
	}

	public String getImportantInfo() {
		IHtmlObject[] objs=browser.getTextArea(".id", "Important Information");
		if(objs.length<1){
			throw new ObjectNotFoundException("can't find  long  Important Information text area....");
		}
		String text=objs[0].getProperty(".text");
		Browser.unregister(objs);
		return text;
	}

	public String getLongDescription() {
		IHtmlObject[] objs=browser.getTextArea(".id", "Long Description");
		if(objs.length<1){
			throw new ObjectNotFoundException("can't find  long  description text area....");
		}
		
		String longDescription=objs[0].getProperty(".text");
		Browser.unregister(objs);
		return longDescription;
	}

	public String getNumbersOfSteps() {
		return browser.getTextFieldValue(".id", "Number of Steps");
	}

	public String getDaysHoursDescription() {
		IHtmlObject[] objs=browser.getTextArea(".id", "Tour Days / Hours Description");
		if(objs.length<1){
			throw new ObjectNotFoundException("can't find  long  Tour Days / Hours Descriptiontext area....");
		}
		String text=objs[0].getProperty(".text");
		Browser.unregister(objs);
		return text;
	}

	public String getPhysicalEffort() {
		return browser.getDropdownListValue(".id", "Physical Effort",0);
	}

	/**
	 * get days of week checkboxes with Map.
	 * Key:Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday
	 * Value:true/false
	 * 
	 * @return
	 */
	public Map<String, Boolean> getDaysOfWeek() {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("Mon", browser.isCheckBoxSelected(".id",
				"Tour Days of Week__Monday"));
		map.put("Tue", browser.isCheckBoxSelected(".id",
				"Tour Days of Week__Tuesday"));
		map.put("Wed", browser.isCheckBoxSelected(".id",
				"Tour Days of Week__Wednesday"));
		map.put("Thu", browser.isCheckBoxSelected(".id",
				"Tour Days of Week__Thursday"));
		map.put("Fri", browser.isCheckBoxSelected(".id",
				"Tour Days of Week__Friday"));
		map.put("Sat", browser.isCheckBoxSelected(".id",
				"Tour Days of Week__Saturday"));
		map.put("Sun", browser.isCheckBoxSelected(".id",
				"Tour Days of Week__Sunday"));
		return map;
	}

	public String getTourImage() {
		return browser.getTextFieldValue(".id", "Tour Image");
	}

	public String getTourURL() {
		return browser.getTextFieldValue(".id", "Tour URL");
	}

	public TourInfo getTour() {
		TourInfo tour = new TourInfo();
		tour.tourCode = this.getTourCode();
		tour.tourName = this.getTourName();
		tour.ticketTourName = this.getTicketTourName();
		tour.tourType = this.getTourType();
		tour.tourFeeClass = this.getFeeClass();
		tour.description = this.getDescription();
		tour.ticketCategory = this.getTicketCategory();
		tour.rateType = this.getRateType();
		tour.count = this.getBaseGroupCount();
		tour.soldIndividual = this.getSoldIndividually();
		tour.duration = this.getDuration();
		tour.timeEntry = this.getTimedEntry();
		tour.limitedCapacity = this.getLimitedCapacity();
		tour.capacity = this.getDefaultCapacity();
		tour.sortOrder = this.getTourSortOrder();
		tour.accessible = this.getADAAccessible();
		tour.accessibility = this.getAccessibility();
		tour.amenities = this.getAmenities();
		tour.directions = this.getDirections();
		tour.distance = this.getDistance();
		tour.information = this.getImportantInfo();
		tour.longDescription = this.getLongDescription();
		tour.numOfSteps = this.getNumbersOfSteps();
		tour.physicalEffort = this.getPhysicalEffort();
		tour.days_hoursDescription = this.getDaysHoursDescription();
		tour.daysOfWeek = this.getDaysOfWeek();
		tour.tourimage = this.getTourImage();
		tour.tourUrl = this.getTourURL();

		return tour;
	}

	/**
	 * get the error messages
	 * 
	 * @return
	 */
	public List<String> getErrorMessages() {
		List<String> list = null;
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("(NOTSET)|(V-\\d+)",true));
		if (objs.length > 0) {
			logger.info("Get error message!");
			list = new ArrayList<String>();
			for (IHtmlObject obj : objs) {
				String msg = obj.getProperty(".text");
				list.add(msg);
			}
		}else{
			
		}
		Browser.unregister(objs);
		return list;
	}

	public void clickCancleButton() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	public void clickApplyButton() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	public void clickToursLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Tours");
	}

	public static void main(String[] args) {
		// System.out
		// .print(InvMgrTourDetailsPage.getInstance().getImportantInfo());
		InvMgrTourDetailsPage im = InvMgrTourDetailsPage.getInstance();
		System.out.println(im.getErrorMessages());

	}
	/**
	 * select prevent ticket Printing.
	 * @param item
	 */
	public void selectPreventTicketPrinting(String item){
		browser.selectDropdownList(".id", "tour_prevticketprint", item);
	}

}
