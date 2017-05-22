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
public class LicMgrStoreViewSoldPrivilegeInventoryWidget extends DialogWidget {
	
	private static LicMgrStoreViewSoldPrivilegeInventoryWidget _instance = null;
	
	private LicMgrStoreViewSoldPrivilegeInventoryWidget() {}
	
	public static LicMgrStoreViewSoldPrivilegeInventoryWidget getInstance() {
		if(_instance == null) {
			_instance = new LicMgrStoreViewSoldPrivilegeInventoryWidget();
		}
		
		return _instance;
	}
	
	private static String INVENTORY_TYPE_COL = "Inventory Type";
	private static String LICENSE_YEAR_COL = "License Year";
	private static String INVENTORY_NUMBER_COL = "Inventory Number";
	private static String INVENTORY_STATUS_COL = "Inventory Status";
	private static String ORDER_NUMBER_COL = "Order Number";
	private static String ORDER_CREATE_DATE_TIME_COL = "Order Create Date/Time";
	private static String CUSTOMER_NAME_COL = "Customer Name";
	private static String MDWFP_NUM_COL = "MDWFP #";
	
	@Override
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(".id", new RegularExpression("StorePrivilegeInventorySearchCriteria-\\d+\\.customerNumber", false));
	}
	
	public void selectInventoryType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("StorePrivilegeInventorySearchCriteria-\\d+\\.inventoryTypeId", false), type, 1);
	}
	
	public void selectLicenseYearAll() {
		browser.selectCheckBox(".id", new RegularExpression("StorePrivilegeInventorySearchCriteria-\\d+\\.yearAll", false));
	}
	
	public void setLicenseYear(String year) {
		browser.setTextField(".id", new RegularExpression("StorePrivilegeInventorySearchCriteria-\\d+\\.licenseYear", false), year);
	}
	
	public void setInventoryNumber(String num) {
		browser.setTextField(".id", new RegularExpression("StorePrivilegeInventorySearchCriteria-\\d+\\.inventoryNumber", false), num, 1);
	}
	
	public void setCustomerLastName(String lName) {
		browser.setTextField(".id", new RegularExpression("StorePrivilegeInventorySearchCriteria-\\d+\\.customerLastName", false), lName);
	}
	
	public void setCustomerNumber(String custNum) {
		browser.setTextField(".id", new RegularExpression("StorePrivilegeInventorySearchCriteria-\\d+\\.customerNumber", false), custNum);
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go", true, 0, getWidget()[0]);
	}
	
	public void setSearchCriteria(PrivilegeInventory inventory, String lastName, String cutomerNum) {
		this.selectInventoryType(inventory.inventoryType);
		this.setLicenseYear(inventory.licenseYear);
		this.setInventoryNumber(inventory.inventoryNumber);
		this.setCustomerLastName(lastName);
		this.setCustomerNumber(cutomerNum);
	}
	
	public void searchSoldInventory(PrivilegeInventory inventory, String lastName, String customerNum) {
		setSearchCriteria(inventory, lastName, customerNum);
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
	
	public List<String> getAllSoldInventories() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		
		List<String> inventories = new ArrayList<String>();
		do {
			objs = getTableObject();
			table = (IHtmlTable)objs[0];
			
			for(int i = 1; i < table.rowCount(); i ++) {
				inventories.addAll(table.getRowValues(i));
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
	
	public boolean compareSoldInventory(List<String> expected, List<String> actual) {
		logger.info("Compare Sold Privilege Inventory info.");
		boolean result = true;
		result &= MiscFunctions.compareResult(INVENTORY_TYPE_COL, expected.get(0), actual.get(0));
		result &= MiscFunctions.compareResult(LICENSE_YEAR_COL, expected.get(1), actual.get(1));
		result &= MiscFunctions.compareResult(INVENTORY_NUMBER_COL, expected.get(2), actual.get(2));
		result &= MiscFunctions.compareResult(INVENTORY_STATUS_COL, expected.get(3), actual.get(3));
		result &= MiscFunctions.compareResult(ORDER_NUMBER_COL, expected.get(4), actual.get(4));
		result &= MiscFunctions.compareResult(ORDER_CREATE_DATE_TIME_COL, expected.get(5), actual.get(5));
		result &= MiscFunctions.compareResult(CUSTOMER_NAME_COL, expected.get(6), actual.get(6));
		result &= MiscFunctions.compareResult(MDWFP_NUM_COL, expected.get(7), actual.get(7));
		
		return result;
	}
}
