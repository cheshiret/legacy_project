package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.NumberUtil;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class InvMgrPosSetupListPage extends InvMgrCommonTopMenuPage{
	private final String PRODUCT_ID = "Product ID";
	private final String ASSIGNED = "Assigned";
	private final String PRODUCT_NAME = "Product Name";
	private final String PRODUCT_DESCR = "Product Description";
	private final String PRODUCT_GROUP = "Product Group";
	private final String PRODUCT_CLASS = "Product Class";
	private final String PRODUCT_SUB_CLASS = "Product Sub-Class";
	private final String INVENTORY_TYPE = "Inventory Type";
	private final String QTY_ON_HAND = "Qty On Hand";
	
	private static InvMgrPosSetupListPage _instance = null;
	
	protected InvMgrPosSetupListPage(){
		
	}
	
	public static InvMgrPosSetupListPage getInstance(){
		if(null == _instance){
			_instance = new InvMgrPosSetupListPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "POSProductSetupView",".class","Html.DIV");
	}
	
	/**
	 * click add pos product.
	 */
	public void clickAddPosProduct(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add POS Product");
	}
	
	public void clickPOSPrintBarcodes() {
		browser.clickGuiObject(".class", "Html.A", ".text", "POS Print Barcodes");
	}
	
	public void selectAssignmentStatus(String status) {
		if(StringUtil.isEmpty(status)){
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.assignmentStatusID", 0);
		} else {
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.assignmentStatusID", status);
		}
	}
	
	
	public void selectPrdClass(String prdClass) {
		browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.productClassID", prdClass);
	}
	
	public void selectPrdClass(int idx) {
		browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.productClassID", idx);
	}
	
	
	public void selectPrdSubClass(String prdSubClass) {
		browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.productSubClassID", prdSubClass);
	}
	
	public void selectPrdSubClass(int idx) {
		browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.productSubClassID", idx);
	}
	
	public void selectAssignmentStatus(int index) {
		browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.assignmentStatusID", index);
	}
	
	public void setProductBarcode(String barcode) {
		browser.setTextField(".id", "POSProductSetupSearchCriteria.productUPC", barcode);
	}
	
	public void setProductName(String name) {
		browser.setTextField(".id", "POSProductSetupSearchCriteria.productName", name);
	}
	
	public void setProductGroup(String group) {
//		browser.setTextField(".id", "POSProductSetupSearchCriteria.productGroup", group);
		if(!StringUtil.isEmpty(group)){
			browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.productGroupID", group);
		}
	}
	
	public void selectInventoryType(String type) {
		browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.inventoryTypeID", type);
	}
	
	public void selectInventoryType(int idx) {
		browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.inventoryTypeID", idx);
	}
	
	public void setQuantityOnHand(String qty) {
		browser.setTextField(".id", new RegularExpression("POSProductSetupSearchCriteria.qtyOnHand(ForInput)?",false), qty);//update by pzhu
	}
	
	public void selectDateType(String type) {
		browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.dateSearchType", type);
	}
	public void selectDateType(int idx) {
		browser.selectDropdownList(".id", "POSProductSetupSearchCriteria.dateSearchType", idx);
	}
	
	public void setFromDate(String from) {
		browser.setTextField(".id", "POSProductSetupSearchCriteria.startDate_ForDisplay", from);
	}
	
	public void setToDate(String to) {
		browser.setTextField(".id", "POSProductSetupSearchCriteria.endDate_ForDisplay", to);
	}
	
	public void selectShowProductPackagesOnly() {
		browser.selectCheckBox(".id", "POSProductSetupSearchCriteria.prdPckg");
	}
	
	public void unselectShowProductPackagesOnly() {
		browser.unSelectCheckBox(".id", "POSProductSetupSearchCriteria.prdPckg");
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	public void clickAssignSelectedPOSProducts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Assign Selected POS Products");
	}
	
	public void clickUnassignSelectedPOSProducts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Unassign Selected POS Products");
	}
	
	private IHtmlObject[] getPOSProductTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "POSProductSetupView_LIST");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find POS Product table object.");
		}
		
		return objs;
	}
	
	public void setSearchCriteria(POSInfo pos) {
		if(!StringUtil.isEmpty(pos.assignStatus)) {
			this.selectAssignmentStatus(pos.assignStatus);
		} else {
			this.selectAssignmentStatus(0);
		}
//		if (!StringUtil.isEmpty(pos.searchByAssignStatus)) {
//			this.selectAssignmentStatus(pos.searchByAssignStatus);
//		}
		if(!StringUtil.isEmpty(pos.barcode)) {
			this.setProductBarcode(pos.barcode);
		}
		if(!StringUtil.isEmpty(pos.product)) {
			this.setProductName(pos.product);
		}
		if(!StringUtil.isEmpty(pos.productGroup)) {
			this.setProductGroup(pos.productGroup);
		}
		
		if(!StringUtil.isEmpty(pos.productClass)) {
			this.selectPrdClass(pos.productClass);
		}
		
		if(!StringUtil.isEmpty(pos.productSubClass)) {
			this.selectPrdSubClass(pos.productSubClass);
		}
		
		if(!StringUtil.isEmpty(pos.inventoryType)) {
			this.selectInventoryType(pos.inventoryType);
		}
//		if(!StringUtil.isEmpty(pos.qtyOnHand) && NumberUtil.isInteger(pos.qtyOnHand)) {
//			this.setQuantityOnHand(pos.qtyOnHand);
//		}
		
		if(!StringUtil.isEmpty(pos.effectiveDateType)) {
			this.selectDateType(pos.effectiveDateType);
			if(!StringUtil.isEmpty(pos.effectiveStartDate))
			{
				this.setFromDate(pos.effectiveStartDate);
			}
			
			if(!StringUtil.isEmpty(pos.effectiveEndDate))
			{
				this.setToDate(pos.effectiveEndDate);
			}
						
		} else {
			this.selectDateType(0);
		}
				
	}
	
	public void searchPOSProduct(POSInfo pos) {
		this.clearSearchCriteria();
		this.setSearchCriteria(pos);
		clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}

	private void clearSearchCriteria() {
	
		this.selectAssignmentStatus(0);
		this.setProductName(StringUtil.EMPTY);
		this.setProductGroup(StringUtil.EMPTY);
		this.selectPrdClass(0);
		this.selectPrdSubClass(0);
		this.selectInventoryType(0);
		this.setQuantityOnHand(StringUtil.EMPTY);
		this.selectDateType(0);
		this.setFromDate(StringUtil.EMPTY);
		this.setToDate(StringUtil.EMPTY);
		
	}

	private int getPOSProductRowCount(String posName) {
		IHtmlObject[] objs = this.getPOSProductTableObject();
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(3, posName);
		
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
		IHtmlObject[] objs = this.getPOSProductTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int row = getPOSProductRowCount(posName);
		int column = table.findColumn(0, columnName);
		
		String toReturn = table.getCellValue(row, column);
		
		Browser.unregister(objs);
		return toReturn;
	}
	
	public String getProductID(String name) {
		return getPOSValueByColumnName(name, "Product ID");
	}
	
	public String getAssignedStatus(String name) {
		return getPOSValueByColumnName(name, "Assigned");
	}
	
	public String getProductName(String name) {
		return getPOSValueByColumnName(name, "Product Name");
	}
	
	public String getProductDescription(String name) {
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
	
	public void selectCheckBox(String posName){
		int index = this.getPOSProductRowCount(posName);
		browser.selectCheckBox(".id", new RegularExpression("\\d+_ck",false), index-1);
	}
	
	/**
	 * verify POS quantity is correct(Extra Decimal Places)
	 * @param posName
	 * @param qty
	 */
	public void verifyPOSQuantity(String posName, String qty) {
		String actulQty = getQtyOnHand(posName);
		if(!NumberUtil.exactEquals(qty, actulQty)) {
			throw new ErrorOnPageException("POS Quantity On Hand", qty, actulQty);
		} else logger.info("Expected is: " + qty + " exactly equals to actual is: " + actulQty);
	}
	
	/** Get All POS info by POS name */
	public POSInfo getPOSInfo(String posName) {
		IHtmlObject[] objs = this.getPOSProductTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		List<String> colNames = table.getRowValues(0);
		int row = table.findRow(colNames.indexOf(PRODUCT_NAME), posName);
		if (row < 1) {
			throw new ItemNotFoundException("Can't find the POS with the name=" + posName);
		}
		
		List<String> values = table.getRowValues(row);
		POSInfo pos = new POSInfo();
		pos.product = posName;
		pos.productID = values.get(colNames.indexOf(PRODUCT_ID));
		pos.assignStatus = values.get(colNames.indexOf(ASSIGNED));
		pos.productDescription = values.get(colNames.indexOf(PRODUCT_DESCR));
		pos.productGroup = values.get(colNames.indexOf(PRODUCT_GROUP));
		if (colNames.contains(PRODUCT_CLASS)) {
			pos.productClass = values.get(colNames.indexOf(PRODUCT_CLASS));
		}
		if (colNames.contains(PRODUCT_SUB_CLASS)) {
			pos.productSubClass = values.get(colNames.indexOf(PRODUCT_SUB_CLASS));
		}
		pos.inventoryType = values.get(colNames.indexOf(INVENTORY_TYPE));
		pos.qtyOnHand = values.get(colNames.indexOf(QTY_ON_HAND));
		
		Browser.unregister(objs);
		return pos;
	}
	
	public boolean comparePOSInfo(POSInfo pos) {
		logger.info("Compare POS '" + pos.product + "' info in the list...");
		POSInfo actPOS = this.getPOSInfo(pos.product);
		boolean result = true;
		result &= MiscFunctions.compareResult(PRODUCT_ID, pos.productID, actPOS.productID);
		result &= MiscFunctions.compareResult(ASSIGNED + " status", pos.assignStatus, actPOS.assignStatus);
		result &= MiscFunctions.compareResult(PRODUCT_DESCR, pos.productDescription, actPOS.productDescription);
		result &= MiscFunctions.compareResult(PRODUCT_GROUP, pos.productGroup, actPOS.productGroup);
		result &= MiscFunctions.compareResult(PRODUCT_CLASS, pos.productClass, actPOS.productClass);
		result &= MiscFunctions.compareResult(PRODUCT_SUB_CLASS, pos.productSubClass, actPOS.productSubClass);
		result &= MiscFunctions.compareResult(INVENTORY_TYPE, pos.inventoryType, actPOS.inventoryType);
		result &= MiscFunctions.compareResult(QTY_ON_HAND, pos.qtyOnHand, actPOS.qtyOnHand);
		return result;
	}

	public void verifyPOSInfo(POSInfo pos) {
		boolean result = this.comparePOSInfo(pos);
		if (!result) {
			throw new ErrorOnPageException("The POS Info is wrong in the list!");
		}
		logger.info("The POS Info is correct in the list!");
	}
	
	public boolean isProductIDLinkExist(String id) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", id);
	}
	
	public void verifyProductIDLinkExist(String id, boolean isExist) {
		boolean act = this.isProductIDLinkExist(id);
		String meg = isExist ? " " : " NOT ";
		if (act != isExist) {
			throw new ErrorOnPageException("The product ID link should" + meg + "exist!");
		}
		logger.info("The product ID link does" + meg + "exist!");
	}
	
	public void clickProductID(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	
	
	/**
	 * get total all records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<POSInfo> getRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<POSInfo> records = new ArrayList<POSInfo>();
		int rows;
		int columns;
		POSInfo pos;
		
		do{
		objs = browser.getTableTestObject(".id", "POSProductSetupView_LIST");
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find pos product setup table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page InvMgrPosSetupListPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			pos = new POSInfo();
			pos.productID = table.getCellValue(i, table.findColumn(0, "Product ID"));
			pos.assignStatus = table.getCellValue(i, table.findColumn(0, "Assigned"));
			pos.product = table.getCellValue(i, table.findColumn(0, "Product Name"));
			pos.productDescription = table.getCellValue(i, table.findColumn(0, "Product Description"));
			pos.productGroup = table.getCellValue(i, table.findColumn(0, "Product Group"));
			pos.productClass = table.getCellValue(i, table.findColumn(0, "Product Class"));
			pos.productSubClass = table.getCellValue(i, table.findColumn(0, "Product Sub-Class"));
			pos.inventoryType = table.getCellValue(i, table.findColumn(0, "Inventory Type"));
			pos.qtyOnHand = table.getCellValue(i, table.findColumn(0, "Qty On Hand"));
						
			records.add(pos);			
		}
				
		}while(gotoNext());
		
		Browser.unregister(objs);
		return records;
	}
	
	
	/**
	 * If "Next" button is available, click it
	 *
	 */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Next" );
		boolean toReturn = false;

		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
			ajax.waitLoading();
		}

		Browser.unregister(objs);
		this.waitLoading();

		return toReturn;
	}
	
	
}
