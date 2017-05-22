/*
 * $Id: InvMgrInventoryDetailPage.java 7080 2009-02-02 18:41:15Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityInventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInventoryInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * Inventory detail page.
 * 
 * @author CGuo
 */
public class InvMgrInventoryDetailPage extends InventoryManagerPage {

	/**
	 * Script Name   : InvMgrInventoryDetailPage
	 * Generated     : Apr 22, 2005 4:05:44 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2005/04/22
	 */
	private static InvMgrInventoryDetailPage _instance = null;

	public static InvMgrInventoryDetailPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrInventoryDetailPage();
		}

		return _instance;
	}

	protected InvMgrInventoryDetailPage() {
	}

	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.A",".text","Inventory Detail");
	}

	/**If release inventory button exist, clidk release inventory button*/
	public void releaseInv() {
		clickReleaseInvButton();
	}

	/**click release inventory*/
	public void clickReleaseInvButton() {
		browser.clickGuiObject(".class", "Html.A", ".text","Release Inventory", true);
	}
	
	public boolean isReleaseInvEnable(){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.A", ".text", "Release Inventory");
		if(objs.length < 1){
			logger.info("Release Inventory is not a button.");
			return false;
		} else {
			logger.info("Release Inventory is a button.");
			return true;
		}
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	private String getAttributeByName(String name) {
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN", ".className", "inputwithrubylabel", ".text", new RegularExpression("^" + name, false)));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find attribute by Name - " + name);
		}
		
		String text = objs[0].text().replaceAll(name, "").trim();
		Browser.unregister(objs);
		
		return text;
	}
	
	public String getInventoryID(){
		return this.getAttributeByName("Inventory ID");
	}
	
	public String getSlipNum(){
		return this.getAttributeByName("Slip #");
	}
	
	public String getSlipName(){
		return this.getAttributeByName("Slip Name");
	}
	
	public String getNSSParent(){
		return this.getAttributeByName("NSS Parent");
	}
	
	public String getDockArea(){
		return this.getAttributeByName("Dock/Area");
	}
	
	public String getSlipType(){
		return this.getAttributeByName("Slip Type");
	}
	
	private String getTDObjectTextValue(String trName){
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression("^" + trName, false)));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find tr object by Name - " + trName);
		}
		
		IHtmlObject tdTextObjs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.INPUT.text", ".className", "inputwithrubylabel"), objs[objs.length-1]);
		if(tdTextObjs.length<1){
			throw new ItemNotFoundException("Can't find td text object by Name - " + trName);
		}
		String text = tdTextObjs[0].getProperty(".value");
		Browser.unregister(objs);
		Browser.unregister(tdTextObjs);
		return text;
	}
	
	public String getInventoryDateTime(){
		return this.getTDObjectTextValue("Inventory Date/Time");
	}
	
	public String getStatus(){
		return this.getTDObjectTextValue("Status");
	}
	
	public String getStartDate(){
		return this.getTDObjectTextValue("Start Date");
	}
	
	public String getEndDate(){
		return this.getTDObjectTextValue("End Date");
	}
	
	public String getBookingID(){
		return this.getTDObjectTextValue("Booking ID");
	}
	
	public String getReservationNum(){
		return this.getTDObjectTextValue("Reservation #");
	}
	
	public String getArrivalDate(){
		return this.getTDObjectTextValue("Arrival Date");
	}
	
	public String getDeparturelDate(){
		return this.getTDObjectTextValue("Departure Date");
	}
	
	public String getBoatLength(){
		return this.getTDObjectTextValue("Boat Length");
	}
	
	public String getClosureID(){
		return this.getTDObjectTextValue("Closure ID");
	}
	
	public String getUserName(){
		return this.getTDObjectTextValue("User Name");
	}
	
	public String getUserLocation(){
		return this.getTDObjectTextValue("User Location");
	}
	
	public String getSalesChannel(){
		return this.getTDObjectTextValue("Sales Channel");
	}
	
	public SlipInventoryInfo getSlipInventoryInfo(){
		SlipInventoryInfo actSlipInvInfo = new SlipInventoryInfo();
		actSlipInvInfo.setInvID(this.getInventoryID());
		actSlipInvInfo.setSlipNum(this.getSlipNum());
		actSlipInvInfo.setSlipName(this.getSlipName());
		actSlipInvInfo.setNssParent(this.getNSSParent());
		actSlipInvInfo.setDockArea(this.getDockArea());
		actSlipInvInfo.setSlipType(this.getSlipType());
		
		actSlipInvInfo.setInvDateTime(this.getInventoryDateTime().split(" ")[0].trim());
		actSlipInvInfo.setInvStatus(this.getStatus());
		actSlipInvInfo.setStartDate(this.getStartDate());
		actSlipInvInfo.setEndDate(this.getEndDate());
		actSlipInvInfo.setBookingID(this.getBookingID());
		actSlipInvInfo.setResNum(this.getReservationNum());
		actSlipInvInfo.setArrivalDate(this.getArrivalDate());
		actSlipInvInfo.setDepartureDate(this.getDeparturelDate());
		actSlipInvInfo.setBoatLength(this.getBoatLength());
		actSlipInvInfo.setClosureID(this.getClosureID());
		actSlipInvInfo.setUserName(this.getUserName());
		actSlipInvInfo.setUserLocation(this.getUserLocation());
		actSlipInvInfo.setSalesChannel(this.getSalesChannel());
		return actSlipInvInfo;
	}
	
	public void verifySlipInventoryHeaderSectionInfo(SlipInventoryInfo slipInvInfo){
		SlipInventoryInfo actSlipInvInfo = this.getSlipInventoryInfo();
		boolean result = true;
		result &= MiscFunctions.compareResult("Inventory ID", slipInvInfo.getInvID(), actSlipInvInfo.getInvID());
		result &= MiscFunctions.compareResult("Slip #", slipInvInfo.getSlipNum(), actSlipInvInfo.getSlipNum());
		result &= MiscFunctions.compareResult("Slip Name", slipInvInfo.getSlipName(), actSlipInvInfo.getSlipName());
		result &= MiscFunctions.compareResult("NSS Parent", slipInvInfo.getNssParent(), actSlipInvInfo.getNssParent());
		result &= MiscFunctions.compareResult("Dock/Area", slipInvInfo.getDockArea(), actSlipInvInfo.getDockArea());
		result &= MiscFunctions.compareResult("Slip Type", slipInvInfo.getSlipType(), actSlipInvInfo.getSlipType());
		
		if(!result){
			throw new ErrorOnPageException("Slip Inventory Header info not correct.");
		}else logger.info("Slip Inventory Header info correct.");
	}
	
	public void verifySlipInventoryDetailInfo(SlipInventoryInfo slipInvInfo){
		SlipInventoryInfo actSlipInvInfo = this.getSlipInventoryInfo();
		
		boolean result = true;
		result &= MiscFunctions.compareResult("Inventory Date/Time", slipInvInfo.getInvID(), actSlipInvInfo.getInvID());
		result &= MiscFunctions.compareResult("Status", slipInvInfo.getInvStatus(), actSlipInvInfo.getInvStatus());
		result &= MiscFunctions.compareResult("Start Date", slipInvInfo.getStartDate(), actSlipInvInfo.getStartDate());
		result &= MiscFunctions.compareResult("End Date", slipInvInfo.getEndDate(), actSlipInvInfo.getEndDate());
		result &= MiscFunctions.compareResult("Booking ID", slipInvInfo.getBookingID(), actSlipInvInfo.getBookingID());
		result &= MiscFunctions.compareResult("Reservation #", slipInvInfo.getResNum(), actSlipInvInfo.getResNum());
		result &= MiscFunctions.compareResult("Arrival Date", slipInvInfo.getArrivalDate(), actSlipInvInfo.getArrivalDate());
		result &= MiscFunctions.compareResult("Departure Date", slipInvInfo.getDepartureDate(), actSlipInvInfo.getDepartureDate());
		result &= MiscFunctions.compareResult("Boat Length", slipInvInfo.getBoatLength(), actSlipInvInfo.getBoatLength());
		result &= MiscFunctions.compareResult("Closure ID", slipInvInfo.getClosureID(), actSlipInvInfo.getClosureID());
		result &= MiscFunctions.compareResult("User Name", slipInvInfo.getUserName(),actSlipInvInfo.getUserName());
		result &= MiscFunctions.compareResult("User Location", slipInvInfo.getUserLocation(), actSlipInvInfo.getUserLocation());
		result &= MiscFunctions.compareResult("Sales Channel", slipInvInfo.getSalesChannel(), actSlipInvInfo.getSalesChannel());
		
		if(!result){
			throw new ErrorOnPageException("Slip Inventory Detail info not correct.");
		}else logger.info("Slip Inventory Detail info correct.");
	}
	
}
