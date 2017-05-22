package com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsPOSCommonPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
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
 * @Date  Oct 8, 2012
 */
public class OrmsPOSInventoryReconciliationPage extends OrmsPOSCommonPage {
	
	private static OrmsPOSInventoryReconciliationPage _instance = null;
	
	private OrmsPOSInventoryReconciliationPage() {}
	
	public static OrmsPOSInventoryReconciliationPage getInstance() {
		if(_instance == null) {
			_instance = new OrmsPOSInventoryReconciliationPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "posinventory_LIST");
	}
	
	public void clickPrintInventoryList() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Print Inventory List", true);
	}
	
	public void clickReconcilePhysicalInventory() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Reconcile Physical Inventory");
	}
	
	public void clickImportInventoryFile() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Import Inventory File");
	}
	
	public void setProductBarcode(String barcode) {
		browser.setTextField(".id", new RegularExpression("(FacWah|FacilityWarehouse)POSSearchCriteria-\\d+\\.prdUpc", false), barcode);
	}
	
	public void setProductName(String name) {
		browser.setTextField(".id", new RegularExpression("(FacWah|FacilityWarehouse)POSSearchCriteria-\\d+\\.prdName", false), name);
	}
	
	public void setProductGroup(String group) {
		browser.setTextField(".id", new RegularExpression("(FacWah|FacilityWarehouse)POSSearchCriteria-\\d+\\.prdGrp", false), group);
	}
	
	public void selectInventoryType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("(FacWah|FacilityWarehouse)POSSearchCriteria-\\d+\\.invType", false), type);
	}
	
	public void setQtyOnHand(String qty) {
		browser.setTextField(".id", new RegularExpression("(FacWah|FacilityWarehouse)POSSearchCriteria-\\d+\\.qtyOnHand", false), qty);
	}
	
	public void clickGo() {
		IHtmlObject objs[] = this.getTransactionFrame();
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Search$", false), true, 0, objs != null && objs.length > 0 ? objs[0] : null);
//		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Search$", false), true);//Quentin[20131203] there's no frame in this page
	}
	
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public IHtmlObject[] getPOSInventoryTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".id","posinventory_LIST");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find POS Inventory Reconciliation table object.");
		}
		
		return objs;
	}

	public void setSearchCriteria(POSInfo pos) {
		if(!StringUtil.isEmpty(pos.barcode)) {
			setProductBarcode(pos.barcode);
		}
		if(!StringUtil.isEmpty(pos.product)) {
			setProductName(pos.product);
		}
		if(!StringUtil.isEmpty(pos.productGroup)) {
			setProductGroup(pos.productGroup);
		}
		if(!StringUtil.isEmpty(pos.inventoryType)) {
			selectInventoryType(pos.inventoryType);
		}
		if(!StringUtil.isEmpty(pos.qtyOnHand)) {
			setQtyOnHand(pos.qtyOnHand);
		}
	}
	
	public void searchPOSProduct(POSInfo pos) {
		setSearchCriteria(pos);
		clickGo();
		ajax.waitLoading();
		waitLoading();
	}
	
	public void searchPOSProduct(String barcode) {
		searchPOSProduct(barcode, null);
	}
	
	public void searchPOSProduct(String barcode, String name) {
		searchPOSProduct(barcode, name, null);
	}
	
	public void searchPOSProduct(String barcode, String name, String group) {
		searchPOSProduct(barcode, name, group, null);
	}
	
	public void searchPOSProduct(String barcode, String name, String group, String type) {
		searchPOSProduct(barcode, name, group, type, null);
	}
	
	public void searchPOSProduct(String barcode, String name, String group, String type, String qty) {
		POSInfo pos = new POSInfo();
		pos.barcode = barcode;
		pos.product = name;
		pos.productGroup = group;
		pos.inventoryType = type;
		pos.qtyOnHand = qty;
		searchPOSProduct(pos);
	}
	
	private int getRowIndexByProductName(String name) {
		IHtmlObject objs[] = getPOSInventoryTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int columnIndex = table.findColumn(0, "Product Name");
		int rowIndex = table.findRow(columnIndex, name);
		
		return rowIndex;
	}
	
	public String getQtyOnHand(int rowIndex) {
		IHtmlObject objs[] = getPOSInventoryTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int columnIndex = table.findColumn(0, "Qty On Hand");
		String qty = table.getCellValue(rowIndex, columnIndex);
		
		Browser.unregister(objs);
		return qty;
	}
	
	public String getQtyOnHand(String name) {
		int rowIndex = getRowIndexByProductName(name);
		return getQtyOnHand(rowIndex);
	}
	
	public String setPhysicalQtyOnHand(int rowIndex, String qty) {
		IHtmlObject objs[] = browser.getTextField(".id", new RegularExpression("(FacWah|FacilityWarehouse)POSInvSearchView-\\d+\\.physicalQty", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Physical Qty On Hand text field object.");
		}
		
		((IText)objs[rowIndex - 1]).setText(qty);
		Browser.unregister(objs);
		
		return qty;
	}
	
	public void setPhysicalQtyOnHand(String name, String qty) {
		int rowIndex = getRowIndexByProductName(name);
		setPhysicalQtyOnHand(rowIndex, qty);
	}
	
	public String getPhysicalQtyOnHand(int rowIndex) {
		IHtmlObject objs[] = browser.getTextField(".id", new RegularExpression("(FacWah|FacilityWarehouse)POSInvSearchView-\\d+\\.physicalQty", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Physical Qty On Hand text field object.");
		}
		
		String qty = ((IText)objs[rowIndex - 1]).getText();
		Browser.unregister(objs);
		
		return qty;
	}
	
	public String getPhysicalQtyOnHandByName(String name) {
		int rowIndex = getRowIndexByProductName(name);
		String qty = getPhysicalQtyOnHand(rowIndex);
		
		return qty;
	}
	
	public POSInfo getPOSInfo(String name) {
		IHtmlObject objs[] = getPOSInventoryTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int columnIndex = table.findColumn(0, "Product Name");
		int rowIndex = table.findRow(columnIndex, name);
		List<String> info = table.getRowValues(rowIndex);
		
		POSInfo pos = new POSInfo();
		pos.productID = info.get(0);
		pos.product = info.get(1);
		pos.productDescription = info.get(2);
		pos.productGroup = info.get(3);
		pos.productClass = info.get(4);
		pos.productSubClass = info.get(5);
		pos.inventoryType = info.get(6);
		pos.qtyOnHand = info.get(7);
		pos.physicalQtyOnHand = getPhysicalQtyOnHandByName(name);
		
		Browser.unregister(objs);
		return pos;
	}
	
	/**
	 * Example: Reconciliation has been finalized for 2 items and 138003002 has been assigned as Reconciliation ID
	 * @return
	 */
	public String getReconciliationSuccessMessage() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", "inventory.pos.reconcile.success");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Reconciliation success message DIV object.");
		}
		
		String text = objs[0].getProperty(".text").trim();
		Browser.unregister(objs);
		
		return text;
	}
	
	/**
	 * get reconciliation id from displayed success message after reconcile physical inventory qty
	 * @return
	 */
	public String getReconciliationID() {
		//Reconciliation has been finalized for 2 items and 138003002 has been assigned as Reconciliation ID
		String text = getReconciliationSuccessMessage();
		String ids[] = RegularExpression.getMatches(text, "[0-9]{8,}");
		if(ids.length < 1) {
			throw new ItemNotFoundException("Can't get Reconciliation ID from success message.");
		}
		
		return ids[0];
	}
	
	public int getReconciliationProductNums() {
		String text = getReconciliationSuccessMessage();
		//Reconciliation has been finalized for 2 items and 138003002 has been assigned as Reconciliation ID
		String itemNums = text.split("items")[0].split("for")[1].trim();
		int num = Integer.parseInt(itemNums);
		
		return num;
	}
	
	public void clickViewInventoryFileLog() {
		browser.clickGuiObject(".class", "Html.A", ".text", "View Inventory File Log");
	}
	
	public void clickViewInventoryReconciliationLog() {
		browser.clickGuiObject(".class", "Html.A", ".text", "View Inventory Reconciliation Log");
	}
}
