package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.List;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
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
 * @author Swang
 * @Date  Jan 28, 2014
 */
public class LicMgrPrivilegeInventoryListPage extends LicMgrPrivilegeItemDetailPage{
	private static LicMgrPrivilegeInventoryListPage _instance = null;
	
	protected LicMgrPrivilegeInventoryListPage() {
		
	}
	
	public static LicMgrPrivilegeInventoryListPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrPrivilegeInventoryListPage();
		}
		
		return _instance;
	}
	
	protected Property[] replacePrivInventory(){
		return Property.concatPropertyArray(a(), ".text", "Replace Privilege Inventory");
	}
	
	protected Property[] inventoryRadio(){
		return Property.toPropertyArray(".id", "inventoryRadio");
	}
	
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(replacePrivInventory());
	}
	
	protected Property[] privInventoryListTable(){
		return Property.concatPropertyArray(table(), ".className", "table table-striped gridView", ".id", new RegularExpression("grid_\\d+", false));
	}
	public void clickReplacePrivInventory(){
		browser.clickGuiObject(replacePrivInventory());
	}
	
	public IHtmlTable getPrivInventoryListTable(){
		IHtmlObject[] objs = browser.getTableTestObject(privInventoryListTable());
		if(objs.length<1){
			throw new ObjectNotFoundException("Failed to find privilege inventory list table.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		return table;
	}
	
	public int getInventoryNumIndex(String inventoryNum){
		IHtmlTable table = getPrivInventoryListTable();
		int index =-1;
		List<String> inventoryNums = table.getColumnValues(2);
		for(int i=1; i<inventoryNums.size(); i++){
			if(inventoryNums.get(i).equalsIgnoreCase(inventoryNum)){
				index = i-1;
				break;
			}
		}
		Browser.unregister(table);
		return index;
	}
	
	public void selectInventoryNumRadio(String inventoryNum){
		int index = getInventoryNumIndex(inventoryNum);
		browser.selectRadioButton(inventoryRadio(), index);
	}
	
	public void selectInvNumToReplace(String inventoryNum){
		selectInventoryNumRadio(inventoryNum);
		ajax.waitLoading();
		clickReplacePrivInventory();
		ajax.waitLoading();
	}
	
	public String getStatusByInvNum(String inventoryNum) {
		String status = "";
		IHtmlTable table = getPrivInventoryListTable();
		int invNumCol = table.findColumn(0, "Inventory Number");
		int statusCol = table.findColumn(0, "Status");
		int statusRow = -1;
		for(int i=1; i<table.rowCount(); i++){
			if(table.getCellValue(i, invNumCol).equalsIgnoreCase(inventoryNum)){
				statusRow = i;
				break;
			}
		}
		if(statusRow<0)
			throw new ItemNotFoundException("Could not find inventory num "+inventoryNum);
		status = table.getCellValue(statusRow, statusCol);
		Browser.unregister(table);
		return status;
	}
	
}
