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

public class InvMgrComboTourDetailsPage extends InventoryManagerPage {

	private static InvMgrComboTourDetailsPage instance = null;

	private InvMgrComboTourDetailsPage() {
	}

	public static InvMgrComboTourDetailsPage getInstance() {
		if (instance == null) {
			instance = new InvMgrComboTourDetailsPage();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "availableSale");
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

	public void selectFeeClass(String feeclass) {
		browser.selectDropdownList(".id", "feeClass", feeclass);
	}

	public void selectTicketCategory(String category) {
		browser.selectDropdownList(".id", "salesType", category);
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

	public void setTourImage(String image) {
		browser.setTextField(".id", "Tour Image", image);
	}

	public void setTourUrl(String url) {
		browser.setTextField(".id", "Tour URL", url);
	}

	public void selectRateType(String type) {
		browser.selectDropdownList(".id", "prdRateType", type);
		this.waitLoading();
	}

	public void selectAvailableForSale(boolean availableSale) {
		if (availableSale) {
			browser.selectDropdownList(".id", "availableSale", "Yes");
		} else {
			browser.selectDropdownList(".id", "availableSale", "No");
		}
	}

	/**
	 * set combo tour details
	 * 
	 * @param tour
	 */
	public void setComboTour(TourInfo tour) {
		setTourCode(tour.tourCode);
		setTourName(tour.tourName);
		setTicketTourName(tour.ticketTourName);
		selectFeeClass(tour.tourFeeClass);
		setDescription(tour.description);
		selectTicketCategory(tour.ticketCategory);
		selectRateType(tour.rateType);
		if(!this.isGroupCountDisable()){
		    setBaseCount(tour.count);
		}
		selectAvailableForSale(tour.availableSale);
		setSortOrder(tour.sortOrder);
		
		//this.setTicketCategory(tour.capacity);
		setLongDescription(tour.longDescription);
		setDaysOfWeek(tour.daysOfWeek);
		setTourImage(tour.tourimage);
		setTourUrl(tour.tourUrl);
	}

	/**
	 * get combo tour details
	 * 
	 * @return
	 */
	public TourInfo getComboTour() {
		TourInfo tour = new TourInfo();
		tour.tourCode = this.getTourCode();
		tour.tourName = this.getTourName();
		tour.ticketTourName = this.getTicketTourName();
		tour.tourFeeClass = this.getFeeClass();
		tour.description = this.getDescription();
		tour.ticketCategory = this.getTicketCategory();
		tour.rateType = this.getRateType();
		tour.count = this.getBaseCount();
		tour.availableSale = this.getAvailableForSale();
		tour.sortOrder = this.getSortOrder();
		tour.longDescription = this.getLongDescription();
		tour.daysOfWeek = this.getDaysOfWeek();
		tour.tourimage = this.getTourImage();
		tour.tourUrl = this.getTourUrl();
		return tour;
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

	public String getFeeClass() {
		return browser.getDropdownListValue(".id", "feeClass", 0);
	}

	public String getDescription() {
//		IHtmlObject[] objs=browser.getTextArea(".id", "sitedescription");
//		IHtmlObject[] objs=browser.getTextField(".id", "sitedescription");
//		if(objs.length<1){
//			throw new ObjectNotFoundException("can't find  description text area....");
//		}
//		
//		String description=objs[0].getProperty(".text");
//		Browser.unregister(objs);
//		return description;
		return browser.getTextFieldValue(".id", "sitedescription");
	}

	public String getTicketCategory() {
		return browser.getDropdownListValue(".id", "salesType", 0);
	}

	public String getRateType() {
		return browser.getDropdownListValue(".id", "prdRateType", 0);
	}

	public String getBaseCount() {
		return browser.getTextFieldValue(".id", "baseGroupCount");
	}

	public boolean getAvailableForSale() {
		if (browser.getDropdownListValue(".id", "availableSale", 0)
				.equalsIgnoreCase("Yes")) {
			return true;
		}
		return false;
	}

	public String getSortOrder() {
		return browser.getTextFieldValue(".id", "sortKey");
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

	public Map<String, Boolean> getDaysOfWeek() {
		Map<String, Boolean> daysOfWeek = new HashMap<String, Boolean>();

		daysOfWeek.put("Mon", browser.isCheckBoxSelected(".id",
				"Tour Days of Week__Monday"));
		daysOfWeek.put("Tue", browser.isCheckBoxSelected(".id",
				"Tour Days of Week__Tuesday"));
		daysOfWeek.put("Wed", browser.isCheckBoxSelected(".id",
				"Tour Days of Week__Wednesday"));
		daysOfWeek.put("Thu", browser.isCheckBoxSelected(".id",
				"Tour Days of Week__Thursday"));
		daysOfWeek.put("Fri", browser.isCheckBoxSelected(".id",
				"Tour Days of Week__Friday"));
		daysOfWeek.put("Sat", browser.isCheckBoxSelected(".id",
				"Tour Days of Week__Saturday"));
		daysOfWeek.put("Sun", browser.isCheckBoxSelected(".id",
				"Tour Days of Week__Sunday"));

		return daysOfWeek;
	}

	public String getTourImage() {
		return browser.getTextFieldValue(".id", "Tour Image");
	}

	public String getTourUrl() {
		return browser.getTextFieldValue(".id", "Tour URL");
	}

	/**
	 * get the error messages
	 * 
	 * @return
	 */
	public List<String> getErrorMessages() {
		List<String> list = null;
		RegularExpression regx=new RegularExpression("(NOTSET)|(V-\\d+)",false);
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.DIV",".id",regx);
		if (objs.length > 0) {
			logger.info("Get error message ~!");
			list = new ArrayList<String>();
			for (IHtmlObject obj : objs) {
				String msg = obj.getProperty(".text");
				list.add(msg);
			}
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

	public void clickDetails() {
		browser.clickGuiObject(".text", "Tour Details", ".class", "Html.A");
	}

	public void clickOk() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	public void clickComboToursLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Combo Tours");
	}

	public void clickTourTickets() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Tour Tickets");
	}

	public void clickAssignedTours() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Assigned Tours");
	}

	public boolean isGroupCountDisable(){
		boolean flag=false;
		IHtmlObject[] objs=browser.getTextField(".id", "baseGroupCount");
		flag=!Boolean.parseBoolean(objs[0].getAttributeValue("isContentEditable"));
		Browser.unregister(objs);
		return flag;
	}
	
	/** Click on Participant Data link. */ 
	public void clickParticipantData() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Participant Data");
	}
	
	/** Click Delete Tour Button **/
	public void clickDeleteTour() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Delete this Tour");
	}
	
}
