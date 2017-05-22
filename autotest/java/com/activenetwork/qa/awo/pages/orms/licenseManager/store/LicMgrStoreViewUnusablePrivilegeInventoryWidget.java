package com.activenetwork.qa.awo.pages.orms.licenseManager.store;


import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventory;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
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
 * @Date  Aug 23, 2012
 */
public class LicMgrStoreViewUnusablePrivilegeInventoryWidget extends DialogWidget {

	private static LicMgrStoreViewUnusablePrivilegeInventoryWidget _instance;
	
	private  LicMgrStoreViewUnusablePrivilegeInventoryWidget(){}
	
	public static LicMgrStoreViewUnusablePrivilegeInventoryWidget getInstance() {
		if(_instance == null) {
			_instance = new LicMgrStoreViewUnusablePrivilegeInventoryWidget();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(".id", new RegularExpression("StorePrivilegeInventorySearchCriteria-\\d+\\.status", false));
	}
	
	private static String INVENTORY_TYPE_COL = "Inventory Type";
	private static String INVENTORY_TYPE_STATUS_COL = "Inventory Type Status";
	private static String LICENSE_YEAR_COL = "License Year";
	private static String ISSUE_FROM_COL = "Issue From";
	private static String ISSUE_TO_COL = "Issue To";
	private static String INVENTORY_NUMBER_COL = "Inventory Number";
	private static String INVENTORY_STATUS_COL = "Inventory Status";
	
	public void selectInventoryType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("StorePrivilegeInventorySearchCriteria-\\d+\\.inventoryTypeId", false), type, 1);
	}
	
	public void selectLicenseYearAll() {
		browser.selectCheckBox(".id", new RegularExpression("StorePrivilegeInventorySearchCriteria-\\d+\\.yearAll", false));
	}
	
	public void setLicenseYear(String year) {
		browser.setTextField(".id", new RegularExpression("StorePrivilegeInventorySearchCriteria-\\d+\\.licenseYear", false), year);
	}
	
	public void selectInventoryStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression("StorePrivilegeInventorySearchCriteria-\\d+\\.status", false), status);
	}
	
	public void setInventoryNumber(String num) {
		browser.setTextField(".id", new RegularExpression("StorePrivilegeInventorySearchCriteria-\\d+\\.inventoryNumber", false), num, 1);
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go", true, 0, getWidget()[0]);
	}
	
	private void setSearchCriteria(PrivilegeInventory inv) {
		this.selectInventoryType(inv.inventoryType);
		this.setLicenseYear(inv.licenseYear);
		this.selectInventoryStatus(inv.inventoryStatus);
		this.setInventoryNumber(inv.inventoryNumber);
	}
	
	public void searchUnusablePrivilegeInventory(PrivilegeInventory inv) {
		setSearchCriteria(inv);
		clickGo();
		ajax.waitLoading();
	}
	
	private IHtmlObject[] getTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(Property.toPropertyArray(".id", new RegularExpression("grid_\\d+", false), ".className", "gridView"), getWidget()[0]);
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Privilege Inventory table object.");
		}
		
		return objs;
	}
	
	public PrivilegeInventory getUnusableInventory(PrivilegeInventory inv) {
		searchUnusablePrivilegeInventory(inv);
		return getAllUnusableInventories().get(0);
	}
	
	public List<PrivilegeInventory> getAllUnusableInventories() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		
		List<PrivilegeInventory> inventories = new ArrayList<PrivilegeInventory>();
		List<String> rowValues = new ArrayList<String>();
		do {
			objs = getTableObject();
			table = (IHtmlTable)objs[0];
			
			for(int i = 1; i < table.rowCount(); i ++) {
				rowValues = table.getRowValues(i);
				PrivilegeInventory inventory = new PrivilegeInventory();
				inventory.inventoryType = rowValues.get(table.findColumn(0, INVENTORY_TYPE_COL));
				inventory.inventoryTypeStatus = rowValues.get(table.findColumn(0, INVENTORY_TYPE_STATUS_COL));
				inventory.licenseYear = rowValues.get(table.findColumn(0, LICENSE_YEAR_COL));
				inventory.issueFromDate = rowValues.get(table.findColumn(0, ISSUE_FROM_COL));
				inventory.issueToDate = rowValues.get(table.findColumn(0, ISSUE_TO_COL));
				inventory.inventoryNumber = rowValues.get(table.findColumn(0, INVENTORY_NUMBER_COL));
				inventory.inventoryStatus = rowValues.get(table.findColumn(0, INVENTORY_STATUS_COL));
				
				inventories.add(inventory);
			}
		} while(this.clickNext());
		
		Browser.unregister(objs);
		return inventories;
	}
	
	public boolean clickNext() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.A", ".text", "Next", getWidget()[0]);
		if(objs.length > 0) {
			objs[0].click();
			Browser.unregister(objs);
			return true;
		}
		
		return false;
	}
	
	public boolean compareUnusableInventory(PrivilegeInventory expected, PrivilegeInventory actual) {
		boolean result = true;
		result &= MiscFunctions.compareResult(INVENTORY_TYPE_COL, expected.inventoryType, actual.inventoryType);
		result &= MiscFunctions.compareResult(INVENTORY_TYPE_STATUS_COL, expected.inventoryTypeStatus, actual.inventoryTypeStatus);
		result &= MiscFunctions.compareResult(LICENSE_YEAR_COL, expected.licenseYear, actual.licenseYear);
		result &= MiscFunctions.compareResult(ISSUE_FROM_COL, expected.issueFromDate, actual.issueFromDate);
		result &= MiscFunctions.compareResult(ISSUE_TO_COL, expected.issueToDate, actual.issueToDate);
		result &= MiscFunctions.compareResult(INVENTORY_NUMBER_COL, expected.inventoryNumber, actual.inventoryNumber);
		result &= MiscFunctions.compareResult(INVENTORY_STATUS_COL, expected.inventoryStatus, actual.inventoryStatus);
		
		return result;
	}
	
	public boolean compareUnusableInventory(List<PrivilegeInventory> expected, List<PrivilegeInventory> actual) {
		if(expected.size() != actual.size()) {
			throw new ErrorOnPageException("Unusable Inventories size doesn't match.");
		}
		
		boolean result = true;
		for(int i = 0; i < expected.size(); i ++) {
			result &= compareUnusableInventory(expected.get(i), actual.get(i));
		}
		
		return result;
	}
}
