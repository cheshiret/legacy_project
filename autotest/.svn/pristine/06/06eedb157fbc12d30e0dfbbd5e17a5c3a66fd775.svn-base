package com.activenetwork.qa.awo.pages.orms.licenseManager.store;


import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventory;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
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
public class LicMgrStoreViewPrivilegeInventoryAllocationCountsWidget extends DialogWidget {
	
	private static String INVENTORY_TYPE_COL = "Inventory Type";
	private static String INVENTORY_TYPE_STATUS_COL = "Inventory Type Status";
	private static String LICENSE_YEAR_COL = "License Year";
	private static String ISSUE_FROM_COL = "Issue From";
	private static String ISSUE_TO_COL = "Issue To";
	private static String NUM_OF_AVAILABLE_COL = "# Available";
	private static String NUM_OF_UNUSABLE_AVAILABLE_COL = "# Unusable Available";//TODO DEFECT-36252
	private static String NUM_OF_UNUSABLE_RETURNED_COL = "# Unusable Returned";
	private static String NUM_OF_UNUSABLE_WITHDRAWN_COL = "# Unusable Withdrawn";
	private static String NUM_OF_SOLD_COL = "# Used";
	
	private static LicMgrStoreViewPrivilegeInventoryAllocationCountsWidget _instance = null;
	
	private LicMgrStoreViewPrivilegeInventoryAllocationCountsWidget() {}
	
	public static LicMgrStoreViewPrivilegeInventoryAllocationCountsWidget getInstance() {
		if(_instance == null) {
			_instance = new LicMgrStoreViewPrivilegeInventoryAllocationCountsWidget();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(".id", new RegularExpression("grid_\\d+\\_LIST", false), ".className", "gridView", getWidget()[0]);
	}
	
	private IHtmlObject[] getTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(Property.toPropertyArray(".id", new RegularExpression("grid_\\d+\\_LIST", false), ".className", "gridView"), getWidget()[0]);
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find table object.");
		}
		
		return objs;
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
	
	/**
	 * Get all privilege inventories
	 * @return
	 */
	public List<PrivilegeInventory> getAllPrivilegeInventories() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		
		List<PrivilegeInventory> inventories = new ArrayList<PrivilegeInventory>();
		do {
			objs = getTableObject();
			table = (IHtmlTable)objs[0];
			
			for(int i = 1; i < table.rowCount(); i ++) {
				List<String> rowValues = table.getRowValues(i);
				PrivilegeInventory inventory = new PrivilegeInventory();
				
				inventory.inventoryType = rowValues.get(table.findColumn(0, INVENTORY_TYPE_COL));
				inventory.inventoryTypeStatus = rowValues.get(table.findColumn(0, INVENTORY_TYPE_STATUS_COL));
				inventory.licenseYear = rowValues.get(table.findColumn(0, LICENSE_YEAR_COL));
				inventory.issueFromDate = rowValues.get(table.findColumn(0, ISSUE_FROM_COL));
				inventory.issueToDate = rowValues.get(table.findColumn(0, ISSUE_TO_COL));
				inventory.numOfAvailable = Integer.parseInt(rowValues.get(table.findColumn(0, NUM_OF_AVAILABLE_COL)).replace(",", ""));
				inventory.numOfUnusableAvailable = Integer.parseInt(rowValues.get(table.findColumn(0, NUM_OF_UNUSABLE_AVAILABLE_COL)).replace(",", ""));
				inventory.numOfUnusableReturned = Integer.parseInt(rowValues.get(table.findColumn(0, NUM_OF_UNUSABLE_RETURNED_COL)).replace(",", ""));
				inventory.numOfUnusableWithdrawn = Integer.parseInt(rowValues.get(table.findColumn(0, NUM_OF_UNUSABLE_WITHDRAWN_COL)).replace(",", ""));
				inventory.numOfSold = Integer.parseInt(rowValues.get(table.findColumn(0, NUM_OF_SOLD_COL)).replace(",", ""));
				
				inventories.add(inventory);
			}
		} while(this.clickNext());
		
		Browser.unregister(objs);
		return inventories;
	}
	
	/**
	 * Get a specific privilege inventory info identified by Inventory Type, Inventory Type Status and License Year
	 * @param invType
	 * @param invTypeStatus
	 * @param licenseYear
	 * @return
	 */
	public PrivilegeInventory getPrivilegeInventoryInfo(String invType, String invTypeStatus, String licenseYear) {
		List<PrivilegeInventory> inventories = getAllPrivilegeInventories();
		
		for(PrivilegeInventory inv : inventories) {
			if(inv.inventoryType.equals(invType) && inv.inventoryTypeStatus.equals(invTypeStatus) && inv.licenseYear.equals(licenseYear)) {
				return inv;
			}
		}
		return null;
	}
	
	public boolean comparePrivilegeInventoryInfo(PrivilegeInventory expected) {
		PrivilegeInventory actual = getPrivilegeInventoryInfo(expected.inventoryType, expected.inventoryTypeStatus, expected.licenseYear);
		return comparePrivilegeInventoryInfo(expected, actual);
	}
	
	public boolean comparePrivilegeInventoryInfo(PrivilegeInventory expected, PrivilegeInventory actual) {
		boolean result = true;
		
		logger.info("Compare Privilege(Type=" + expected.inventoryType + ") Inventory info.");
		result &= MiscFunctions.compareResult(INVENTORY_TYPE_COL, expected.inventoryType, actual.inventoryType);
		result &= MiscFunctions.compareResult(INVENTORY_TYPE_STATUS_COL, expected.inventoryTypeStatus, actual.inventoryTypeStatus);
		result &= MiscFunctions.compareResult(LICENSE_YEAR_COL, expected.licenseYear, actual.licenseYear);
		result &= MiscFunctions.compareResult(ISSUE_FROM_COL, expected.issueFromDate, actual.issueToDate);
		result &= MiscFunctions.compareResult(NUM_OF_AVAILABLE_COL, expected.numOfAvailable, actual.numOfAvailable);
		result &= MiscFunctions.compareResult(NUM_OF_UNUSABLE_AVAILABLE_COL, expected.numOfUnusableAvailable, actual.numOfUnusableAvailable);
		result &= MiscFunctions.compareResult(NUM_OF_UNUSABLE_RETURNED_COL, expected.numOfUnusableReturned, actual.numOfUnusableReturned);
		result &= MiscFunctions.compareResult(NUM_OF_UNUSABLE_WITHDRAWN_COL, expected.numOfUnusableWithdrawn, actual.numOfUnusableWithdrawn);
		result &= MiscFunctions.compareResult(NUM_OF_SOLD_COL, expected.numOfSold, actual.numOfSold);
		
		return result;
	}
}
