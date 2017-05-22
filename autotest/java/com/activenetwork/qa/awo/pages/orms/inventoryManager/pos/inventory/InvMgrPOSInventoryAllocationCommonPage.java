package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description: this page is extended by InvMgrPOSInventoryWebAllocationPage and InvMgrPOSInventoryCallCenterAllocationPage
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Aug 6, 2012
 */
public abstract class InvMgrPOSInventoryAllocationCommonPage extends InvMgrPOSInventoryManagementCommonPage {

	public void selectAllocationStatus(String status) {
		if(StringUtil.isEmpty(status)){
			browser.selectDropdownList(".id", "WebCallPOSInvSearchCriteria.allocStatus", 0, true);
		}else{
			browser.selectDropdownList(".id", "WebCallPOSInvSearchCriteria.allocStatus", status);
		}
	}
	
	public void setProductName(String name) {
		browser.setTextField(".id", new RegularExpression("WebCallPOSInvSearchCriteria.prdName|FieldPOSInvSearchCriteria-\\d+.productName",false), name);
	}
	
	public void setProductGroup(String group) {
		browser.setTextField(".id", "WebCallPOSInvSearchCriteria.prdGrp", group);
	}

	public boolean isInventoryTypeExists() {
		return browser.checkHtmlObjectExists(".id", "WebCallPOSInvSearchCriteria.invType");
	}
	
	/**
	 * Select Product Inventory Type
	 * Added by Nicole, PCR 2956
	 * @param invType
	 */
	public void selectInventoryType(String invType){
		if(StringUtil.isEmpty(invType)){
			browser.selectDropdownList(".id", "WebCallPOSInvSearchCriteria.invType", 0, true);
		}else{
			browser.selectDropdownList(".id", "WebCallPOSInvSearchCriteria.invType", invType);
		}
	}
	
	protected Property[] serializationNumType(){
		return Property.concatPropertyArray(select(), ".id",new RegularExpression("FieldPOSInvSearchCriteria-\\d+\\.serializationNumberType", false));
	}
	
	protected Property[] passNum(){
		return Property.concatPropertyArray(input("text"), ".id",new RegularExpression("FieldPOSInvSearchCriteria-\\d+\\.passNumber", false));
	}
	
	public void selectSerializationNumType(String option){
		browser.selectDropdownList(serializationNumType(), option);
	}
	
	public void setPassNum(String passNum){
		browser.setTextField(passNum(), passNum);
	}
	
	public void setSerializationRangeFrom(String rangeFrom) {
		browser.setTextField(".id", new RegularExpression("(WebCall|Field)POSInvSearchCriteria(\\-\\d+)?(\\.)?(rangeFrom|serializedRangeFrom)", false), rangeFrom);
	}
	
	public void setSerializationRangeTo(String rangeTo) {
		browser.setTextField(".id", new RegularExpression("(WebCall|Field)POSInvSearchCriteria(\\-\\d+)?(\\.)?(rangeTo|serializedRangeTo)", false), rangeTo);
	}
	
	public void selectShowProductPackagesOnly() {
		browser.selectCheckBox(".id", "WebCallPOSInvSearchCriteria.prdPckg");
	}
	
	public void unselectShowProductPackagesOnly() {
		browser.unSelectCheckBox(".id", "WebCallPOSInvSearchCriteria.prdPckg");
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Search|Go",false), true);
	}
	
	public void searchByNameAndNumber(String prodName,String serialNumType,String passNum){
		this.setProductName(prodName);
		this.selectSerializationNumType(serialNumType);
		ajax.waitLoading();
		this.setPassNum(passNum);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void setSearchCriteria(String status, String name, String group, String type, String rangeFrom, String rangeTo, boolean isShowProductPackagesOnly) {
		if(!StringUtil.isEmpty(status)) {
			selectAllocationStatus(status);
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
		if(!StringUtil.isEmpty(rangeFrom)) {
			setSerializationRangeFrom(rangeFrom);
		}
		if(!StringUtil.isEmpty(rangeTo)) {
			setSerializationRangeTo(rangeTo);
		}
		if(isShowProductPackagesOnly) {
			selectShowProductPackagesOnly();
		} else {
			unselectShowProductPackagesOnly();
		}
	}
	
	public boolean isProductClassExists() {
		return browser.checkHtmlObjectExists(".id", "WebCallPOSInvSearchCriteria.productClassID");
	}
	
	/**
	 * Select Product Class
	 * Added by Nicole, PCR 2956
	 * @param productClass
	 */
	public void selectProductClass(String productClass){
		if(StringUtil.isEmpty(productClass)){
			browser.selectDropdownList(".id", "WebCallPOSInvSearchCriteria.productClassID", 0, true);
		}else{
			browser.selectDropdownList(".id", "WebCallPOSInvSearchCriteria.productClassID", productClass);
		}
	}
	
	public boolean isProductSubClassExists() {
		return browser.checkHtmlObjectExists(".id", "WebCallPOSInvSearchCriteria.productSubClassID");
	}
	
	/**
	 * Select Product Sub-Class
	 * Added by Nicole, PCR 2956
	 * @param productSubClass
	 */
	public void selectProductSubClass(String productSubClass){
		if(StringUtil.isEmpty(productSubClass)){
			browser.selectDropdownList(".id", "WebCallPOSInvSearchCriteria.productSubClassID", 0, true);
		}else{
			browser.selectDropdownList(".id", "WebCallPOSInvSearchCriteria.productSubClassID", productSubClass);
		}
	}

	/**
	 * Check if Show Product Packages Only Check Box selected.
	 * Added by Nicole, PCR 2956
	 */
	public boolean isShowProductPackagesOnlySelected(){
		return browser.isCheckBoxSelected(".id", "WebCallPOSInvSearchCriteria.prdPckg");
	}

	public void searchPOS(String status, String name, String group, String type, String rangeFrom, String rangeTo, boolean isShowProductPackagesOnly) {
		setSearchCriteria(status, name, group, type, rangeFrom, rangeTo, isShowProductPackagesOnly);
		clickGo();
		ajax.waitLoading();
		waitLoading();
	}	
	// added by Nicole, PCR 2956
	public void setSearchCriteria(POSInfo pos, String rangeFrom, String rangeTo) {
		if(null != pos.allocateStatus) {
			selectAllocationStatus(pos.allocateStatus);
		}
		if(!StringUtil.isEmpty(pos.product)) {
			setProductName(pos.product);
		}
		if(!StringUtil.isEmpty(pos.group)) {
			setProductGroup(pos.group);
		}
		if(!StringUtil.isEmpty(pos.productClass)) {
			if(this.isProductClassExists()) {
				selectProductClass(pos.productClass);
			} else throw new ErrorOnPageException("Product Class dropdown list shall exist.");
		}
		if (!StringUtil.isEmpty(pos.productSubClass)) {
			if(this.isProductSubClassExists()) {
				this.selectProductSubClass(pos.productSubClass);
			} else throw new ErrorOnPageException("Product Sub Class dropdown list shall exist.");
		}
		if (!StringUtil.isEmpty(pos.inventoryType)) {
			if(this.isInventoryTypeExists()) {
				this.selectInventoryType(pos.inventoryType);
			} else throw new ErrorOnPageException("Inventory Type dropdown list shall exist.");
		}
		
		if(!StringUtil.isEmpty(rangeFrom)) {
			setSerializationRangeFrom(rangeFrom);
		}
		if(!StringUtil.isEmpty(rangeTo)) {
			setSerializationRangeTo(rangeTo);
		}

		if(pos.isShowProductPackagesOnly) {
			selectShowProductPackagesOnly();
		} else {
			unselectShowProductPackagesOnly();
		}
	}
	
	// search POS with serialization range
	public void searchPOS(POSInfo pos, String rangeFrom, String rangeTo){
		this.setSearchCriteria(pos, rangeFrom, rangeTo);
		clickGo();
		ajax.waitLoading();
		waitLoading();
	}
	
	// search POS without serialization range
	public void searchPOS(POSInfo pos){
		this.searchPOS(pos, "", "");
	}
	
	public void searchPOS(String status, String name, String group, String type) {
		searchPOS(status, name, group, type, null, null, false);
	}
	
	public void searchPOS(String name, String group, String type) {
		searchPOS(null, name, group, type);
	}
	
	private IHtmlObject[] getPOSInventoryTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", new RegularExpression("gridId|grid",false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Web/Call POS Inventory table object.");
		}
		
		return objs;
	}
	
	private static final String PRODUCT_ID_COL = "Product ID";
	private static final String PRODUCT_NAME_COL = "Product Name";
	private static final String ALLOCATION_STATUS_COL = "Allocation Status";
	private static final String PRODUCT_GROUP_COL = "Product Group";
	private static final String PRODUCT_CLASS_COL = "Product Class";
	private static final String PRODUCT_SUB_CLASS_COL = "Product Sub-Class";
	private static final String INVENTORY_TYPE_COL = "Inventory Type";
	private static final String QTY_ON_HAND_COL = "Qty On Hand";
	private static final String SERIALIZATION_RANGE_FROM = "Serialization Range From";
	private static final String SERIALIZATION_RANGE_TO = "Serialization Range To";
	private static final String EXPIRY_DATE_COL = "Expiry Date";
	
	private int getPOSInventoryRowIndex(String posName) {
		IHtmlObject[] objs = this.getPOSInventoryTableObject();
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int row = table.findRow(table.findColumn(0, PRODUCT_NAME_COL), posName);
		
		Browser.unregister(objs);
		return row;
	}
	
	public void selectPOS(int rowIndex) {
		IHtmlObject objs[] = browser.getCheckBox(".id", new RegularExpression("^(GenericGrid-\\d+\\.selectedItems|ck)$",false));
//		IHtmlObject objs[] = browser.getCheckBox(".id", "ck");[Shane20140415],comment this line as in inventory manager,select POS product check box id is 'GenericGrid-1060966948.selectedItems'
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find POS Inventory checkbox object.");
		}
		((ICheckBox)objs[rowIndex - 1]).select();
	}
	
	public void selectPOS(String name) {
		int rowIndex = getPOSInventoryRowIndex(name);
		selectPOS(rowIndex);
	}
	
	public void selectAll() {
		browser.selectCheckBox(".name", "all_slct");
	}
	
	public void clickAllocateSelectedPOSProducts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Allocate Selected POS Products");
	}
	
	public void allocatePOS(String name) {
		selectPOS(name);
		clickAllocateSelectedPOSProducts();
		ajax.waitLoading();
		waitLoading();
	}

	
	public void clickDeallocateSelectedPOSProducts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deallocate Selected POS Products");
	}
	
	private String getPOSAttributeByName(String posName, String columnName) {
		IHtmlObject objs[] = getPOSInventoryTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int columnIndex = table.findColumn(0, columnName);
		int rowIndex = table.findRow(table.findColumn(0, PRODUCT_NAME_COL), posName);
		String toReturn = table.getCellValue(rowIndex, columnIndex);
		
		Browser.unregister(objs);
		return toReturn;
	}
	
	public String getAllocationStatus(String posName) {
		return getPOSAttributeByName(posName, "Allocation Status");
	}
	
	public String getProductID(String posName) {
		return getPOSAttributeByName(posName, "Product ID");
	}
	
	public String getProductDescription(String posName) {
		return getPOSAttributeByName(posName, "Product Description");
	}
	
	public String getProductGroup(String posName) {
		return getPOSAttributeByName(posName, "Product Group");
	}
	
	public String getInventoryType(String posName) {
		return getPOSAttributeByName(posName, "Inventory Type");
	}
	
	public String getQtyOnHand(String posName) {
		return getPOSAttributeByName(posName, "Qty On Hand");
	}
	
	public String getSerializationRangeFrom(String posName) {
		return getPOSAttributeByName(posName, "Serialization Range From");
	}
	
	public String getSerializationRangeTo(String posName) {
		return getPOSAttributeByName(posName, "Serialization Range To");
	}
	
	public String getExpiryDate(String posName) {
		return getPOSAttributeByName(posName, "Expiry Date");
	}
	
	public POSInfo getPOSInfo(String posName) {
		IHtmlObject objs[] = getPOSInventoryTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int rowIndex = table.findRow(table.findColumn(0, PRODUCT_NAME_COL), posName);
		List<String> rowValues = table.getRowValues(rowIndex);
		POSInfo pos = new POSInfo();
		pos.allocateStatus = rowValues.get(table.findColumn(0, ALLOCATION_STATUS_COL));
		pos.productID = rowValues.get(table.findColumn(0, PRODUCT_ID_COL));
		pos.product = rowValues.get(table.findColumn(0, PRODUCT_NAME_COL));
//		pos.productDescription = rowValues.get(4);
		pos.productGroup = rowValues.get(table.findColumn(0, PRODUCT_GROUP_COL));
		pos.inventoryType = rowValues.get(table.findColumn(0, INVENTORY_TYPE_COL));
		pos.qtyOnHand = rowValues.get(table.findColumn(0, QTY_ON_HAND_COL));
		//TODO serialization range from, serialization range to, and expiry date
		
		Browser.unregister(objs);
		return pos;
	}

	public List<String> getColumnValueByNam(String columnName){
		logger.info("Get column value by column name "+columnName);
		IHtmlObject[] objs = browser.getTableTestObject(".id", "gridId");
		if(objs.length < 1){
			throw new ItemNotFoundException("Can' find POS Product table.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int column = table.findColumn(0, columnName);
		List<String> columnList = table.getColumnValues(column);
		columnList.remove(0);
		return columnList;
	}
	
	public String getMessage(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find any message.");
		}
		String msg = objs[0].getProperty(".text");
		return msg;
	}
	
}
