/*
 * $Id: InvMgrHomePage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author CGuo,jdu
 */
public class InvMgrHomePage extends InvMgrCommonTopMenuPage {

	/**
	 * Script Name   : InvMgrMainPage
	 * Generated     : Feb 9, 2005 4:43:53 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2005/02/09
	 */

	private static InvMgrHomePage _instance = null;

	private InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();

	public static InvMgrHomePage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrHomePage();
		}

		return _instance;
	}

	protected InvMgrHomePage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("^Facility ID ?Facility Name ?Status ?Description ?Parent Location ?Agency ?State.*",false));
	}

	/** Click on Home link from top menu. */
	public void clickHome() {
		topMenu.clickHome();
	}

	/** Click on Sign Out link from top menu. */
	public void clickSignOut() {
		topMenu.clickSignOut();
	}

	/** Click on Change Request Mode link from top menu. */
	public void clickChangeRequestMode() {
		topMenu.clickChangeRequestMode();
	}

	/** Click on Switch link from top menu. */
	public void clickSwitch() {
		topMenu.clickSwitch();
	}

	/** Click on Facilities link. */
	public void clickFacilities() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Facilities");
	}

	
	public boolean checkChangeReqItemsExist(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Change Request Items");
	}
	
	/** Click on Change Requests Items link. */
	public void clickChangeReqItems() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Change Request Items");//Change Requests Items
	}

	/** check object exist */
	public boolean checkChangeRequestsExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Change Requests");
	}

	/** Click on Change Request link. */
	public void clickChangeRequests() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Change Requests");
	}

	/**
	 * Select facility search status.
	 * @param status
	 */
	public void selectFacilitiesSearchStatus(String status) {
		browser.selectDropdownList(".id", "facilitySearchStatus", status);
	}

	/**
	 * Select facility search type.
	 * @param type
	 */
	public void selectFacilitiesSearchType(String type) {
		browser.selectDropdownList(".id", "facilitySearchType", type);
	}

	/**
	 * Fill in facility search value.
	 * @param value
	 */
	public void setFacilitiesSearchValue(String value) {
		browser.setTextField(".id", "facilitySearchInput", value);
	}

	/** Click on Search link to do facility search. */
	public void clickFacilitiesSearch() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", new RegularExpression("Search", false));
		p[2] = new Property(".href", new RegularExpression(
				"\"searchFacility\"|#link", false));
		browser.clickGuiObject(p);
	}

	/** Click on Add New link to add a new facility. */
	public void clickFacilitiesAddNew() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Add New");
		p[2] = new Property(".href", new RegularExpression(
				"(\"SelectParentLocation\\.do\")|#link", false));
		browser.clickGuiObject(p);
	}

	/**
	 * Search facility by given type and value.
	 * @param searchType - search type
	 * @param searchValue - search value
	 */
	public void searchFacilities(String searchType, String searchValue) {
		this.selectFacilitiesSearchType(searchType);
		this.setFacilitiesSearchValue(searchValue);
		this.clickFacilitiesSearch();
		Browser.sleep(1);
		this.waitLoading();
	}

	/**
	 * Go to specific facility's details page by id.
	 * @param id - facility id
	 */
	public void selectFacilityById(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id, true);
	}

	/**
	 * Go to specific facility's details page by name.
	 * @param name - facility name
	 * @return - facility id
	 * @throws ItemNotFoundException
	 */
	public String selectFacilityByName(String name)	throws ItemNotFoundException {
		IHtmlObject[] objs = browser.getTableTestObject(".text",	new RegularExpression("^Facility ID", false));
		IHtmlTable table = (IHtmlTable) objs[0];

		int idCol = table.findColumn(0, "Facility ID");
		int nameCol = table.findColumn( 0, "Facility Name");
		int row = table.findRow(nameCol, new RegularExpression(name.replace("(", "\\(").replace(")", "\\)"),false));
		if (row < 0)
			throw new ItemNotFoundException("Facility " + name	+ " was not found.");
		String id = table.getCellValue(row, idCol).toString();
		Browser.unregister(objs);
		this.selectFacilityById(id);

		return id;
	}

	/**
	 * Verify Change Request Mode link exist or not 
	 * @return
	 */
	public boolean checkChangeRequestModeLink(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Change Request Mode");
	}
	
	public boolean checkSelectWarehouseLink(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Select Warehouse");
	}
	
	public boolean checkSelectFacilityLink(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Select Facility");
	}
	
	public boolean checkHomeLink(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Home");
	}
	
	public boolean checkMapLink(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Map");
	}
	
	public boolean checkHelpLink(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Help");
	}
	
	public boolean checkSwitchLink(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Switch");
	}
	
	public boolean checkSignOutLink(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Sign out");
	}

	/**
	 * Verify Change Immediate Mode link exist or not 
	 * @return
	 */
	public boolean checkChangeImmediateModeLink(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Change Immediate Mode");
	}

	/**
	 * Verify Mode bar exist or not
	 * @return
	 */
	public boolean checkModeBar(){
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", "Change Request Mode");
	}

	/**
	 * Verify 'Add New' button is available or not
	 * @return
	 */
	public boolean checkAddNew(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add New");
	}
	
	/**
	 * Select page from drop down list
	 * @param item
	 */
	public void selectPageFromDropDownList(String item){
		browser.selectDropdownList(".id", "page_name", item);
	}
	
	public String getFacility(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Location.*", false));
		IHtmlObject[] objs1=browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^Facility.*", false),objs[0]);
		String value="";
		if(objs1 != null && objs1.length > 0){
			if(objs1[0].text().length()>"Facility".length()){
				value=objs1[0].text().split("Facility")[1];
			}
			if(objs1[0].text().length()=="Facility".length()){
				value="";
			}
		}
		Browser.unregister(objs);
		Browser.unregister(objs1);
		return value;
	}
	
	public boolean checkFacilityExist(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", new RegularExpression("Facility.*", false));
	}
	
	public String getWarehouse(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("Warehouse.*", false));
		String value="";
		if(objs != null && objs.length > 0){
			if(objs[0].text().length()>"Warehouse".length()){
				value=objs[0].text().split("Warehouse")[1]+"Warehouse";
			}
		}
		Browser.unregister(objs);
		return value;
	}
	
	public boolean checkWarehouseExist(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", new RegularExpression("Warehouse.*", false));
	}
	
	public String getRegion(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^Region.*", false));
		String value="";
		if(objs != null && objs.length > 0){
			if(objs[0].text().length()>"Region".length()){
				value=objs[0].text().split("Region")[1];
			}
			if(objs[0].text().length()=="Region".length()){
				value="";
			}
		}
		Browser.unregister(objs);
		return value;
	}
	
	public String getAgency(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^Agency.*", false));
		String value="";
		if(objs != null && objs.length > 0){
			if(objs[0].text().length()>"Agency".length()){
				value=objs[0].text().split("Agency")[1];
			}
			if(objs[0].text().length()=="Agency".length()){
				value="";
			}
		}
		Browser.unregister(objs);
		return value;
	}
	
	/**
	 * Search and get facility info by facility id/facility name
	 * @param facilityIDOrName
	 * @return
	 */
	public List<String> searchAndGetFacilityInfo(String facilityIDOrName) {
		if(!facilityIDOrName.matches("^[0-9]+$")) {//facility name
			this.searchFacilities("Facility Name", facilityIDOrName);
		} else {
			this.searchFacilities("Facility ID", facilityIDOrName);
		}
		
		IHtmlObject objs[] = browser.getTableTestObject(".text", new RegularExpression("^Facility ID", false));
		if(objs.length < 1) {
			throw new ErrorOnPageException("Can't find table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		if(table.rowCount() != 2) {
			throw new ErrorOnPageException("The facility record is wrong, please specify the unique search criteria value.");
		}
		List<String> facilityValues = table.getRowValues(1);
		
		Browser.unregister(objs);
		return facilityValues;
	}
	
	public boolean compareFacilityInfo(FacilityData expectedFacility) {
		List<String> actualFacilityInfo = this.searchAndGetFacilityInfo(expectedFacility.facilityID);
		
		logger.info("Compare facility(ID#=" + expectedFacility.facilityID + ") info in Inventory Manager home page.");
		boolean result = true;
		if(!actualFacilityInfo.get(0).trim().equals(expectedFacility.facilityID.trim())) {
			logger.error("Facility - ID doesn't match. Expected value is: " + expectedFacility.facilityID + " but actual value is: " + actualFacilityInfo.get(0));
			result &= false;
		}
		if(!actualFacilityInfo.get(1).equals(expectedFacility.facilityName)) {
			logger.error("Facility - ID doesn't match. Expected value is: " + expectedFacility.facilityID + " but actual value is: " + actualFacilityInfo.get(0));
			result &= false;
		}
		if(!actualFacilityInfo.get(2).equals(expectedFacility.status)) {
			logger.error("Facility - ID doesn't match. Expected value is: " + expectedFacility.facilityID + " but actual value is: " + actualFacilityInfo.get(0));
			result &= false;
		}
		if(!actualFacilityInfo.get(3).equals(expectedFacility.description)) {
			logger.error("Facility - ID doesn't match. Expected value is: " + expectedFacility.facilityID + " but actual value is: " + actualFacilityInfo.get(0));
			result &= false;
		}
		//parent location - actualFacilityInfo.get(4)
		if(!actualFacilityInfo.get(5).equals(expectedFacility.agency)) {
			logger.error("Facility - ID doesn't match. Expected value is: " + expectedFacility.facilityID + " but actual value is: " + actualFacilityInfo.get(0));
			result &= false;
		}
//		if(!actualFacilityInfo.get(6).equals(expectedFacility.state)) {
//			logger.error("Facility - ID doesn't match. Expected value is: " + expectedFacility.facilityID + " but actual value is: " + actualFacilityInfo.get(0));
//			result &= false;
//		}
		
		return result;
	}
}
