package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventory;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description: This page is sub-page in store details page, and it extends from LicMgrStoreDetailsPage
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA-qchen
 * @Date  May 25, 2011
 */
public class LicMgrStoreInventoryPage extends LicMgrStoreDetailsPage {
	
	private static LicMgrStoreInventoryPage _instance = null;
	
	protected LicMgrStoreInventoryPage() {}
	
	public static LicMgrStoreInventoryPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrStoreInventoryPage();
		}
		
		return _instance;
	}
	
	private static String INVENTORY_TYPE_COL = "Inventory Type";
	private static String LICENSE_YEAR_COL = "License Year";
	private static String ISSUE_FROM_COL = "Issue From";
	private static String ISSUE_TO_COL = "Issue To";
	private static String INVENTORY_NUMBER_COL = "Inventory Number";
	private static String INVENTORY_STATUS_COL = "Inventory Status";
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "View Sold Privilege Inventory");
	}
	
	public void clickViewSoldPrivilegeInventory() {
		browser.clickGuiObject(".class", "Html.A", ".text", "View Sold Privilege Inventory");
	}
	
	public void clickViewUnusablePrivilegeInventory() {
		browser.clickGuiObject(".class", "Html.A", ".text", "View Unusable Privilege Inventory");
	}
	
	public void clickViewPrivilegeInventoryAllocationCounts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "View Privilege Inventory Allocation Counts");
	}
	
	/**
	 * Select inventory type
	 * @param inventoryType
	 */
	public void selectInventoryType(String inventoryType) {
		browser.selectDropdownList(".id", new RegularExpression("StorePrivilegeInventorySearchCriteria-\\d+\\.inventoryTypeId", false), inventoryType);
	}
	
	/**
	 * Set inventory number
	 * @param inventoryNum
	 */
	public void setInventoryNumber(String inventoryNum) {
		browser.setTextField(".id", new RegularExpression("StorePrivilegeInventorySearchCriteria-\\d+\\.inventoryNumber", false), inventoryNum);
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	public void searchPrivilegeInventory(String inventoryType, String inventoryNum) {
		selectInventoryType(inventoryType);
		if(!StringUtil.isEmpty(inventoryNum)) {
			setInventoryNumber(inventoryNum);
		}
		clickGo();
		ajax.waitLoading();
		waitLoading();
	}
	
	private IHtmlObject[] getTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", new RegularExpression("grid_\\d+_LIST", false), ".className", "gridView");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Table object.");
		}
		
		return objs;
	}
	
	public List<PrivilegeInventory> getAllPrivilegeInventories(int pageCount) {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<PrivilegeInventory> inventories = null;
		
		PagingComponent turnPage = new PagingComponent();
		int count = 0;
		do {
			objs = getTableObject();
			table = (IHtmlTable)objs[0];
			List<String> rowValues = new ArrayList<String>();
			inventories = new ArrayList<PrivilegeInventory>();
			
			for(int i = 1; i < table.rowCount(); i ++) {
				rowValues = table.getRowValues(i);
				
				PrivilegeInventory inventory = new PrivilegeInventory();
				inventory.inventoryType = rowValues.get(table.findColumn(0, INVENTORY_TYPE_COL));
				inventory.inventoryTypeStatus = rowValues.get(table.findColumn(0, LICENSE_YEAR_COL));
				inventory.issueFromDate = rowValues.get(table.findColumn(0, ISSUE_FROM_COL));
				inventory.issueToDate = rowValues.get(table.findColumn(0, ISSUE_TO_COL));
				inventory.inventoryNumber = rowValues.get(table.findColumn(0, INVENTORY_NUMBER_COL));
				inventory.inventoryStatus = rowValues.get(table.findColumn(0, INVENTORY_STATUS_COL));
				
				inventories.add(inventory);
			}
			count ++;
		} while(turnPage.clickNext() && (pageCount != 0 ? (count <= pageCount) : true));//if pageCount = 0, get the all records, or just get records of [pageCount] pages
		
		Browser.unregister(objs);
		return inventories;
	}
	
	public List<PrivilegeInventory> getAllPrivilegeInventories() {
		return getAllPrivilegeInventories(0);
	}
	
	public List<PrivilegeInventory> getAllPrivilegeInventoriesInLastPage() {
		PagingComponent turnPage = new PagingComponent();
		if(turnPage.lastExists()) {
			turnPage.clickLast();
			ajax.waitLoading();
		}
		
		IHtmlObject objs[] = getTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> rowValues = null;
		List<PrivilegeInventory> inventories = new ArrayList<PrivilegeInventory>();
		
		for(int i = 0; i < table.rowCount(); i ++) {
			rowValues = table.getRowValues(i);
			
			PrivilegeInventory inventory = new PrivilegeInventory();
			inventory.inventoryType = rowValues.get(table.findColumn(0, INVENTORY_TYPE_COL));
			inventory.inventoryTypeStatus = rowValues.get(table.findColumn(0, LICENSE_YEAR_COL));
			inventory.issueFromDate = rowValues.get(table.findColumn(0, ISSUE_FROM_COL));
			inventory.issueToDate = rowValues.get(table.findColumn(0, ISSUE_TO_COL));
			inventory.inventoryNumber = rowValues.get(table.findColumn(0, INVENTORY_NUMBER_COL));
			inventory.inventoryStatus = rowValues.get(table.findColumn(0, INVENTORY_STATUS_COL));
			
			inventories.add(inventory);
		}
		
		Browser.unregister(objs);
		return inventories;
	}
	
	public void verifySearchResult(String columnName, String value) {
		verifySearchResultMatchCriteria(".id", new RegularExpression("grid_\\d+_LIST", false), columnName, value);
	}
	
	public boolean comparePrivilegeInventoryInfo(PrivilegeInventory expected, PrivilegeInventory actual) {
		logger.info("Compare privilege inventory info - " + expected.inventoryType);
		
		boolean result = true;
		result &= MiscFunctions.compareResult("Inventory Type", expected.inventoryType, actual.inventoryType);
		result &= MiscFunctions.compareResult("License Year", expected.licenseYear, actual.licenseYear);
		result &= MiscFunctions.compareResult("Issue From", expected.issueFromDate, actual.issueFromDate);
		result &= MiscFunctions.compareResult("Issue To", expected.issueToDate, actual.issueToDate);
		result &= MiscFunctions.compareResult("Inventory Number", expected.inventoryNumber, actual.inventoryNumber);
		result &= MiscFunctions.compareResult("Inventory Status", expected.inventoryStatus, actual.inventoryStatus);
		
		return result;
	}
	
	public boolean comparePrivilegeInventoryInfo(List<PrivilegeInventory> expecteds, List<PrivilegeInventory> actuals) {
		boolean result = true;
		if(expecteds.size() != actuals.size()) {
			throw new ErrorOnPageException("Available Privilege Inventory list size doesn't match. Expected is: " + expecteds.size() + ", but actual is: " + actuals.size());
		}
		
		for(int i = 0; i < expecteds.size(); i ++) {
			result &= comparePrivilegeInventoryInfo(expecteds.get(i), actuals.get(i));
		}
		
		return result;
	}
}
