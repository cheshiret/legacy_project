package com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Mar 17, 2014
 */
public class InvMgrLoyaltyProgramLocationSearchPage extends InvMgrLoyaltyProgramDetailsCommonPage {
	
	private static InvMgrLoyaltyProgramLocationSearchPage _instance = null;
	
	private InvMgrLoyaltyProgramLocationSearchPage() {}
	
	public static InvMgrLoyaltyProgramLocationSearchPage getInstance() {
		if(_instance == null) _instance = new InvMgrLoyaltyProgramLocationSearchPage();
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(location());
	}
	
	private static String LOCATION_ID_COL = "Location ID";
	private static String LOCATION_NAME_COL = "Location Name";
	private static String LOCATION_DESCRIPTION_COL = "Location Description";
	private static String PARENT_LOCATION_COL = "Parent Location";
	private static String AGENCY_COL = "Agency";
	private static String STATUS_COL = "Status";
	
	private Property[] location() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyLocationSearchCriteria-\\d+\\.searchByValue", false));
	}
	
	private Property[] locationCategory() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyLocationSearchCriteria-\\d+\\.locationCategory", false));
	}
	
	private Property[] search() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Search");
	}
	
	private Property[] selectBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Select");
	}
	
	private Property[] locationsListTable() {
		return Property.toPropertyArray(".id", "coverageLocationsResultsGrid_LIST");
	}
	
	private Property[] cancel() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Cancel");
	}
	
	public void setLocation(String location) {
		browser.setTextField(location(), location);
	}
	
	public void selectLocationCategory(String category) {
		browser.selectDropdownList(locationCategory(), category);
	}
	
	public void clickSearch() {
		browser.clickGuiObject(search());
	}
	
	public void clickSelect(int index) {
		browser.clickGuiObject(selectBtn(), index);
	}
	
	public void clickCancel() {
		browser.clickGuiObject(cancel());
	}
	
	public void searchLocation(String location, String category) {
		this.setLocation(location);
		this.selectLocationCategory(category);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	private IHtmlObject[] getLocationsListTable() {
		IHtmlObject objs[] = browser.getTableTestObject(locationsListTable());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Locations list table object.");
		
		return objs;
	}
	
	public void selectLocation(String location) {
		IHtmlObject objs[] = getLocationsListTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int rowIndex = table.findRow(table.findColumn(0, LOCATION_NAME_COL), location);
		clickSelect(rowIndex - 1);
	}
}
