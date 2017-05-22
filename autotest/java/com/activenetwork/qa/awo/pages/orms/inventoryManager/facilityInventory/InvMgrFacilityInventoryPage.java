/*
 * $Id: InvMgrFacilityInventoryPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityInventory;

import java.util.List;
import java.util.Vector;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * TODO: enter class description
 * Note:This is for site inventory list page
 * @author CGuo
 */
public class InvMgrFacilityInventoryPage extends InventoryManagerPage {

	/**
	 * Script Name   : InvMgrFacilityInventoryPage
	 * Generated     : Apr 22, 2005 3:07:23 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2005/04/22
	 */

	private static InvMgrFacilityInventoryPage _instance = null;
	

	public static InvMgrFacilityInventoryPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrFacilityInventoryPage();
		}

		return _instance;
	}

	protected InvMgrFacilityInventoryPage() {
	}

	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		//use Go button as pageMark
		return browser.checkHtmlObjectExists(".text", new RegularExpression("^Inv ID Created Date/Time Status Site # Site Name Parent Site.*", false));
	}

	/***
	 * Input Arrival Date
	 * @param date
	 */
	public void setArrivalDate(String date) {
		browser.setTextField(".id", "search_arrival_date_ForDisplay", date);
	}

	/**Input Site Number*/
	public void setSiteNumber(String siteNum) {
		browser.setTextField(".id", "search_site_no", siteNum);
	}

	/**Input Res Number*/
	public void setResNumber(String resNum) {
		browser.setTextField(".id", "search_resv_no", resNum);
	}

	/**
	 * Input Closure ID
	 * @param id
	 */
	public void setClosureID(String id) {
		browser.setTextField(".id", "search_cl_id", id);
	}

	/**
	 * Select Inventory Statuss
	 * @param status
	 */
	public void selectInventoryStatus(String status) {
		browser.selectDropdownList(".id", "search_inv_stat", status);
	}

	/**
	 * Input from Date
	 * @param date
	 */
	public void setFromDate(String date) {
		browser.setTextField(".id", "search_daterange_from_ForDisplay", date);
	}

	/**
	 * Input To Date
	 * @param date
	 */
	public void setToDate(String date) {
		browser.setTextField(".id", "search_daterange_to_ForDisplay", date);
	}

	/**
	 * Select Site Type
	 * @param type
	 */
	public void selectSiteType(String type) {
		browser.selectDropdownList(".id", "search_site_type", type);
	}

	/**
	 * Select Speific Site from drop down
	 * @param item
	 */
	public void selectSiteSpecific(String item) {
		browser.selectDropdownList(".id", "search_site_specific", item);
	}

	/**Click GO button*/
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}

	/**
	 * Search Hold Inventory by res Number
	 * @param resNum
	 */
	public void searchHoldInv(String resNum) {
		this.setResNumber(resNum);
		this.selectInventoryStatus("Hold");
		this.clickGo();
	}

	/**
	 * Search inventory by index of inventory
	 * @param index
	 */
	public void selectInventory(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("\\d+", false), index);
	}
	
	public String getInventoryInfo() {
	   IHtmlObject[] objs=browser.getTableTestObject(".text", new RegularExpression("^Inv ID Created Date/Time Status.*", false));
	   String inventory = objs[0].getProperty(".text").toString();
	   Browser.unregister(objs);
	   return inventory;
	}
	
	public List<String> getStatus() {
	  Vector<String> vec=new Vector<String>();
	  
	  IHtmlObject[] comboTable = browser.getTableTestObject(".text", new RegularExpression("^Inv ID Created Date/Time Status Site # Site Name Parent Site.*", false));
      IHtmlTable comboTableGrid = (IHtmlTable) comboTable[0];
      
      int row=comboTableGrid.rowCount();
      int column=comboTableGrid.columnCount();
      int col=0;
      
      for(int j=0;j<column;j++) {
        if(null!=comboTableGrid.getCellValue(0,j)){
           if(comboTableGrid.getCellValue(0,j).toString().equals("Status")) {
              col=j;
              break;
           }
        }
     }
      
      for(int i = 0; i < row-1; i++) {
        if(null != comboTableGrid.getCellValue(i+1, col)) {
          vec.add(i,comboTableGrid.getCellValue(i+1, col).toString());
        }
      }
      Browser.unregister(comboTable);
      
      return vec;
   }
	
	public void clickTicketList(){
	  browser.clickGuiObject(".class", "Html.A", ".text", "Ticket Inventory List");
	}
	
	public boolean checkResult() {
		RegularExpression pattern=new RegularExpression("\"getUsedDetail\"",false);
		try{
			browser.waitExists(Property.toPropertyArray(".class","Html.A",".href",pattern));
			return true;
		} catch(Exception e){}
		return false;
	}
	
	public String[] getInventoryIDs() {
		RegularExpression pattern=new RegularExpression("\"getUsedDetail\"",false);
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.A",".href",pattern);
		
		String[] ids=new String[objs.length];
		for(int i=0;i<objs.length;i++) {
			ids[i]=objs[i].getProperty(".text");
		}
		
		return ids;
	}
	
	public void clickInventoryID(String id) {
		browser.clickGuiObject(".class","Html.A",".text",id);
	}
	
	public void clickSlipInventoryListLabel(){
		browser.clickGuiObject(".class","Html.A",".text","Slip Inventory List");
	}
	
	/**
	 * This method search inventory by reservation number
	 * @param resNum
	 */
	public void searchInventory(String arrDate, String siteCode, String resNum, String closureId, String status, 
			String from,  String to, String siteType, String siteSpecific){
		if(StringUtil.notEmpty(arrDate)){
			this.setArrivalDate(arrDate);
		}else{
			this.setArrivalDate(StringUtil.EMPTY);
		}
		
		if(StringUtil.notEmpty(siteCode)){
			this.setSiteNumber(siteCode);
		}else{
			this.setSiteNumber(StringUtil.EMPTY);
		}
		
		if(StringUtil.notEmpty(resNum)){
			this.setResNumber(resNum);
		}else{
			this.setResNumber(StringUtil.EMPTY);
		}
		
		if(StringUtil.notEmpty(closureId)){
			this.setClosureID(closureId);
		}else{
			this.setClosureID(StringUtil.EMPTY);
		}
		
		if(StringUtil.notEmpty(status)){
			this.selectInventoryStatus(status);
		}else{
			browser.selectDropdownList(".id", "search_inv_stat", 0);
		}
		
		if(StringUtil.notEmpty(from)){
			this.setFromDate(from);
		}else{
			this.setFromDate(StringUtil.EMPTY);
		}
		
		if(StringUtil.notEmpty(to)){
			this.setToDate(to);
		}else{
			this.setToDate(StringUtil.EMPTY);
		}
		
		if(StringUtil.notEmpty(siteType)){
			this.selectSiteType(siteType);
		}else{
			browser.selectDropdownList(".id", "search_site_type", siteType);
		}
		
		if(StringUtil.notEmpty(siteSpecific)){
			this.selectSiteSpecific(siteSpecific);
		}else{
			browser.selectDropdownList(".id", "search_site_specific", 0);
		}
		
		clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	private String getStatusForTheFirstInventoryRecord(){
		IHtmlObject[] comboTable = browser.getTableTestObject(".text", new RegularExpression("^Inv ID Created Date/Time Status Site # Site Name Parent Site.*", false));
	    IHtmlTable comboTableGrid = (IHtmlTable) comboTable[0];
	    String status = comboTableGrid.getCellValue(1,2);
	    Browser.unregister(comboTable);
	    return status;
	}
	
	/**
	 * This method get the status for a special reservation
	 * @param resNum
	 * @return
	 */
	public String getReservationStatus(String resNum){
		this.searchInventory(StringUtil.EMPTY, StringUtil.EMPTY, resNum, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, 
				StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY);
	    return getStatusForTheFirstInventoryRecord();
	}
	
	/**
	 * This method get the status for a special slip in special date period
	 * @param slipCode
	 * @param from
	 * @param to
	 * @return
	 */
	public String getSiteStatusInSpecDateRange(String siteCode, String from, String to){
		this.searchInventory(StringUtil.EMPTY, siteCode, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, 
				from, to, StringUtil.EMPTY, StringUtil.EMPTY);
		 return getStatusForTheFirstInventoryRecord();
	}

}
