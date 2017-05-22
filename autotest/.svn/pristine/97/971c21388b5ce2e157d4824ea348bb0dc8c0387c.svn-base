package com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventoryType;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.product.LicMgrPrivilegeInventoryCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrPrivilegeInventoryTypePage extends LicMgrPrivilegeInventoryCommonPage {
	
	private static LicMgrPrivilegeInventoryTypePage _instance = null;
	
	protected LicMgrPrivilegeInventoryTypePage(){
		
	} 
	
	public static LicMgrPrivilegeInventoryTypePage getInstance(){
		if(null == _instance){
			_instance = new  LicMgrPrivilegeInventoryTypePage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".text", "Inventory Types",".className","selected");
	}
	
	public void clickInvTypeLicenseYear(){
		browser.clickGuiObject(".id", new RegularExpression("^InventoryTabBar_\\d+",false), ".text","Inventory Type License Years");
	}
	
	public void clickPrivilegeInventory(){
		browser.clickGuiObject(".id", new RegularExpression("^InventoryTabBar_\\d+",false), ".text","Privilege Inventory");
	}
	
	public void clickAddInventoryType(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Inventory Type", true);
	}
	
	public IHtmlTable getInventoryTypeTableObject(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("grid_\\d+",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found inventory type table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		return table;
	}
	
	public int getInventoryTypeInfoRow(String inventoryType){
		IHtmlTable table = this.getInventoryTypeTableObject();
		int row = table.findRow(1, inventoryType);
		return row;
	}
	
	public IHtmlObject getInventoryTypeRowObject(String inventoryTypeID){
		List<Property[]> prpList = new ArrayList<Property[]>();
		Property[] tablePrp = new Property[2];
		tablePrp[0] = new Property(".class", "Html.TABLE");
		tablePrp[1] = new Property(".id", new RegularExpression("grid_\\d+",false));
		
		Property[] rowPrp = new Property[2];
		rowPrp[0] = new Property(".class", "Html.TR");
		rowPrp[1] = new Property(".text", new RegularExpression("^" +inventoryTypeID + ".*",false));
		
		prpList.add(tablePrp);
		prpList.add(rowPrp);
			
		IHtmlObject[] rowObjs = browser.getHtmlObject(prpList);
		if(rowObjs.length<1){
			throw new ItemNotFoundException("Did not found inventory type row object. Inventory Type ID = " + inventoryTypeID);
		}
		return rowObjs[0];		
	}
	
	public void clickDeactivateButton(String inventoryTypeID){
		IHtmlObject invTypeRowObj = this.getInventoryTypeRowObject(inventoryTypeID);
		
		Property[] p = new Property[2];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Deactivate");
		
		browser.clickGuiObject(p, true, 0, invTypeRowObj);
	}
	
	public void clickActivateButton(String inventoryTypeID){
		IHtmlObject invTypeRowObj = this.getInventoryTypeRowObject(inventoryTypeID);
		
		Property[] p = new Property[2];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Activate");
		
		browser.clickGuiObject(p, true, 0, invTypeRowObj);
	}
	
	public void clickSelectPrivilegeProduct(String inventoryTypeID){
		IHtmlObject invTypeRowObj = this.getInventoryTypeRowObject(inventoryTypeID);
		
		Property[] p = new Property[2];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Select Privilege Products");
		
		browser.clickGuiObject(p, true, 0, invTypeRowObj);
	}
	
	public boolean verifyInventoryTypeInfo(PrivilegeInventoryType expInventoryTypeInfo){
		boolean result = true;
		IHtmlTable table = this.getInventoryTypeTableObject();
		int row = table.findRow(1, expInventoryTypeInfo.inventoryType);
		
		String value = table.getCellValue(row, 0).trim();
		if(!value.equals(expInventoryTypeInfo.id)){
			result &= false;
			MiscFunctions.compareResult("Inventory type id is not correct.", expInventoryTypeInfo.id, value);
		}else {
			logger.info("Inventory type id is correct.");
		}
		
		value = table.getCellValue(row, 1).trim();
		if(!value.equalsIgnoreCase(expInventoryTypeInfo.inventoryType)){
			result &= false;
			MiscFunctions.compareResult("Inventory type is not correct.", expInventoryTypeInfo.inventoryType, value);
		}else {
			logger.info("Inventory type is correct.");
		}
		
		value = table.getCellValue(row, 2).trim();
		if(!value.equals(expInventoryTypeInfo.status)){
			result &= false;
			MiscFunctions.compareResult("inventory type status is not correct.", expInventoryTypeInfo.status, value);
		}else {
			logger.info("Inventory type status is correct.");
		}
		
		value = table.getCellValue(row, 3).trim();
		if(null != expInventoryTypeInfo.privilegeProducts && expInventoryTypeInfo.privilegeProducts.size()>0){			
			String buffer = "";
			for(int i=0; i<expInventoryTypeInfo.privilegeProducts.size(); i++){
				if(expInventoryTypeInfo.privilegeProducts.size()-1 == i){
					buffer = buffer + expInventoryTypeInfo.privilegeProducts.get(i).replace(" - ", "-");
				}else {
					buffer = buffer + expInventoryTypeInfo.privilegeProducts.get(i).replace(" - ", "-") + ", ";
				}				
			}
			
			if(!value.equals(buffer)){
				result &= false;
				MiscFunctions.compareResult("Inventory privilege product info is not correct.", buffer, value);
			}else {
				logger.info("Inventory privilege product info is correct.");
			}
			
		}else {
			if(value.length() !=0){
				result &= false;
				logger.info("Inventory privilege product info is not correct. Expect privilege product info is blank" +
						",but actually is " + value);
			}else {
				logger.info("Inventory privilege product info is correct.");
			}
		}
		
		return result;
	}

	public boolean campareInventoryTypeNameAndStatus(String typeName, boolean isActive) {
		IHtmlTable table = this.getInventoryTypeTableObject();
		int row = table.findRow(1, typeName);
		
		if (row < 1) {
			logger.error("No Inventory Type (" + typeName + " ) in the list!");
			return false;
		}
		String value = table.getCellValue(row, 2).trim();
		String expValue = isActive ? OrmsConstants.ACTIVE_STATUS : OrmsConstants.INACTIVE_STATUS;
		if(!value.equalsIgnoreCase(expValue)){
			logger.error("Inventory type (" + typeName + " ) status is wrong! Expect: " + expValue + ", Actual: " + value);
			return false;
		}
		logger.info("Inventory type and status is correct!");
		return true;
	}
	
	/**
	 * Get Inventory type id by inventory type name
	 * @param typeName
	 * @return
	 * @author Lesley Wang
	 * @date Nov 22, 2012
	 */
	public String getInventoryTypeIDByTypeName(String typeName) {
		IHtmlTable table = this.getInventoryTypeTableObject();
		int row = table.findRow(1, typeName);
		
		String id = table.getCellValue(row, 0).trim();
		Browser.unregister(table);
		return id;
	}
}
