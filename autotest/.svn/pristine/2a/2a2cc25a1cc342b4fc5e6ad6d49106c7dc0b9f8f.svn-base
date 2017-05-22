package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Aug 6, 2012
 */
public class InvMgrPOSInventoryManagementPage extends InvMgrPOSInventoryManagementCommonPage {
	
	private static InvMgrPOSInventoryManagementPage _instance = null;
	
	protected InvMgrPOSInventoryManagementPage() {}
	
	public static InvMgrPOSInventoryManagementPage getInstance() {
		if(_instance == null) {
			_instance = new InvMgrPOSInventoryManagementPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "posinvlist_LIST");
	}
	
	public void setProductBarcode(String barcode) {
		browser.setTextField(".id", "FacilityWarehousePOSSearchCriteria.prdUpc", barcode);
	}
	
	public void setProductName(String name) {
		browser.setTextField(".id", new RegularExpression("(FacWah|FacilityWarehouse)POSSearchCriteria.prdName",false), name);
		//FacWahPOSSearchCriteria.prdName
	}
	
	public void setProductGroup(String group) {
		browser.setTextField(".id", "FacilityWarehousePOSSearchCriteria.prdGrp", group);
	}
	
	public void selectInventoryType(String type) {
		browser.selectDropdownList(".id", "FacilityWarehousePOSSearchCriteria.invType", type);
	}
	
	public void setQtyOnHand(String qty) {
		browser.setTextField(".id", "FacilityWarehousePOSSearchCriteria.qtyOnHandForInput", qty);
	}
	
	public void selectPendingQtyOnly() {
		browser.selectCheckBox(".id", "FacilityWarehousePOSSearchCriteria.pendingQtyOnly");
	}
	
	public void unselectPendingQtyOnly() {
		browser.unSelectCheckBox(".id", "FacilityWarehousePOSSearchCriteria.pendingQtyOnly");
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	public void setSearchCriteria(String barcode, String name, String group, String type, String qty)  {
		if(!StringUtil.isEmpty(barcode)) {
			setProductBarcode(barcode);
		}
		if(!StringUtil.isEmpty(name)) {
			setProductName(name);
		}
		if(!StringUtil.isEmpty(group)) {
			setProductGroup(group);
		}
		if(!StringUtil.isEmpty(type)) {
			selectInventoryType(type);
		}
		if(!StringUtil.isEmpty(qty)) {
			setQtyOnHand(qty);
		}
	}
	
	public void searchPOS(String barcode, String name, String group, String type, String qty) {
		setSearchCriteria(barcode, name, group, type, qty);
		clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void searchPOS(String barcode, String name, String group) {
		searchPOS(barcode, name, group, null, null);
	}
	
	protected Property[] posPassSetup(){
		return Property.toPropertyArray(".class","Html.A",".text","POS Passes Setup");
	}
	
	public void clickPOSPassSetup(){
		browser.clickGuiObject(posPassSetup());
	}
	
	public void clickStockTransfers() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Stock Transfers");
	}
	
	public void clickInventoryTracking() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Inventory Tracking");
	}
	
	public void clickAdjustInventory() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Adjust Inventory");
	}
	
	public void clickRequestStockTransfer() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Request Stock Transfer");
	}
	
	private IHtmlObject[] getPOSInventoryManagementTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "posinvlist_LIST");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find POS Inventory Management table object.");
		}
		
		return objs;
	}
	
	private int getPOSProductRowIndex(String posName) {
		IHtmlObject[] objs = this.getPOSInventoryManagementTableObject();
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(2, posName);
		
		Browser.unregister(objs);
		return row;
	}
	
	/**
	 * get POS value by column name and POS product name
	 * @param posName
	 * @param columnName
	 * @return
	 */
	private String getPOSValueByColumnName(String posName, String columnName) {
		IHtmlObject[] objs = this.getPOSInventoryManagementTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int row = getPOSProductRowIndex(posName);
		int column = table.findColumn(0, columnName);
		
		String toReturn = table.getCellValue(row, column);
		
		Browser.unregister(objs);
		return toReturn;
	}
	
	public String getProductID(String name) {
		return getPOSValueByColumnName(name, "Product ID");
	}
	
	public String getProductDescrption(String name) {
		return getPOSValueByColumnName(name, "Product Description");
	}
	
	public String getProductGroup(String name) {
		return getPOSValueByColumnName(name, "Product Group");
	}
	
	public String getInventoryType(String name) {
		return getPOSValueByColumnName(name, "Inventory Type");
	}
	
	public String getQtyOnHand(String name) {
		return getPOSValueByColumnName(name, "Qty On Hand");
	}
	
	public String getQtyPendingAdjustment(String name) {
		return getPOSValueByColumnName(name, "Qty Pending Adjustment");
	}
	
	public void selectPOS(String name) {
		IHtmlObject objs[] = browser.getRadioButton(".id", "prdId");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find POS radio button object.");
		}
		
		int index = getPOSProductRowIndex(name);
		((IRadioButton)objs[index - 1]).select();
		
		Browser.unregister(objs);
	}
	
	public void adjustInventory(String name) {
		selectPOS(name);
		clickAdjustInventory();
		ajax.waitLoading();
	}
	
	public void requestStockTransfer(String name) {
		selectPOS(name);
		clickRequestStockTransfer();
		ajax.waitLoading();
	}
}
