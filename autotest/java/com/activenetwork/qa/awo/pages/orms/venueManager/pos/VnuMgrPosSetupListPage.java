package com.activenetwork.qa.awo.pages.orms.venueManager.pos;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class VnuMgrPosSetupListPage extends VnuMgrTopMenuPage {

	private static VnuMgrPosSetupListPage _instance = null;
	
	protected VnuMgrPosSetupListPage(){
		
	}
	
	public static VnuMgrPosSetupListPage getInstance(){
		if(null == _instance){
			_instance = new VnuMgrPosSetupListPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "POSProductSetupView");// POS product table.
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
	
	private String prefix = "POSProductSetupSearchCriteria.";
	
	public void selectAssignmentStatus(String status) {
		if(StringUtil.isEmpty(status)){
			browser.selectDropdownList(".id", prefix+"assignmentStatusID", 0);
		} else {
			browser.selectDropdownList(".id", prefix+"assignmentStatusID", status, true);
		}
	}
	
	public void selectPrdClass(String prdClass) {
		if(browser.checkHtmlObjectDisplayed(".id", prefix+"productClassID")){
			if(StringUtil.isEmpty(prdClass)){
				browser.selectDropdownList(".id", prefix+"productClassID", 0);
			} else {
				browser.selectDropdownList(".id", prefix+"productClassID", prdClass, true);
			}
		}
	}

	public void selectPrdSubClass(String prdSubClass) {
		if(browser.checkHtmlObjectDisplayed(".id", prefix+"productSubClassID")){
			if(StringUtil.isEmpty(prdSubClass)){
				browser.selectDropdownList(".id", prefix+"productSubClassID", 0);
			} else {
				browser.selectDropdownList(".id", prefix+"productSubClassID", prdSubClass, true);
			}
		}
	}
	
	public void setProductBarcode(String barcode) {
		browser.setTextField(".id", prefix+"productUPC", barcode);
	}
	
	public void setProductName(String name) {
		browser.setTextField(".id", prefix+"productName", name);
	}
	
	public void setProductGroup(String group) {
		browser.setTextField(".id", prefix+"productGroup", group);
	}
	
	public void selectInventoryType(String type) {
		if(StringUtil.isEmpty(type)){
			browser.selectDropdownList(".id", prefix+"inventoryTypeID", 0);
		} else {
			browser.selectDropdownList(".id", prefix+"inventoryTypeID", type, true);
		}
	}

	public void setQuantityOnHand(String qty) {
		browser.setTextField(".id", prefix+"qtyOnHandForInput", qty);
	}
	
	public void selectDateType(String type) {
		if(StringUtil.isEmpty(type)){
			browser.selectDropdownList(".id", prefix+"dateSearchType", 0);
		} else {
			browser.selectDropdownList(".id", prefix+"dateSearchType", type, true);
		}
	}
	
	public void setFromDate(String from) {
		browser.setTextField(".id", prefix+"startDate_ForDisplay", from);
	}
	
	public void setToDate(String to) {
		browser.setTextField(".id", prefix+"endDate_ForDisplay", to);
	}
	
	public void selectShowProductPackagesOnly() {
		browser.selectCheckBox(".id", prefix+"prdPckg");
	}
	
	public void unselectShowProductPackagesOnly() {
		browser.unSelectCheckBox(".id", prefix+"prdPckg");
	}
	
	public void selectVariablePrice() {
		browser.selectCheckBox(".id", prefix+"variablePrice");
	}
	
	public void unselectVariablePrice() {
		browser.unSelectCheckBox(".id", prefix+"variablePrice");
	}
	
	public void selectPartialQuantityAllowed() {
		browser.selectCheckBox(".id", prefix+"partialQuantity");
	}
	
	public void unselectPartialQuantityAllowed() {
		browser.unSelectCheckBox(".id", prefix+"partialQuantity");
	}
	
	public void clickSearch() {// index = 0 is 'Search' button on the top menu
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Search$", false), true, 1);
	}
	
	public void clickAssignSelectedPOSProducts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Assign Selected POS Products");
	}
	
	public void clickUnassignSelectedPOSProducts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Unassign Selected POS Products");
	}

	public void setSearchCriteria(POSInfo pos) {
		this.selectAssignmentStatus(pos.assignStatus);
		this.setProductBarcode(pos.barcode);
		this.setProductName(pos.product);
		this.setProductGroup(pos.productGroup);
		this.selectPrdClass(pos.productClass);
		this.selectPrdSubClass(pos.productSubClass);
		this.selectInventoryType(pos.inventoryType);
		this.setQuantityOnHand(pos.qtyOnHand);
		this.selectDateType(pos.effectiveDateType);
		
		if(!StringUtil.isEmpty(pos.effectiveDateType)) {
			this.setFromDate(pos.effectiveStartDate);
			this.setToDate(pos.effectiveEndDate);	
		}
		
		if(pos.isShowProductPackagesOnly){
			this.selectShowProductPackagesOnly();
		} else {
			this.unselectShowProductPackagesOnly();
		}

		if("Yes".equalsIgnoreCase(pos.variablePriceAllowed)) {
			this.selectVariablePrice();
		} else {
			this.unselectVariablePrice();
		}

		if("Yes".equalsIgnoreCase(pos.partialQuantityAllowed)) {
			this.selectPartialQuantityAllowed();
		} else {
			this.unselectPartialQuantityAllowed();
		}
	}
	
	public void searchPOSProduct(POSInfo pos) {
		this.setSearchCriteria(pos);
		clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	private IHtmlObject[] getPOSProductTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "POSProductSetupView_LIST");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find POS Product table object.");
		}
		return objs;
	}
	
	/**
	 * Get column values in one page.(without turning page)
	 * @param columnName
	 * @return
	 */
	private List<String> getColumnValueInOnePage(String columnName){
		IHtmlObject[] objs = this.getPOSProductTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int columnNum = table.findColumn(0, columnName);
		List<String> columnList = table.getColumnValues(columnNum);
		columnList.remove(0);
		Browser.unregister(objs);
		return columnList;
	}
	
	/**
	 * Get all of the column values.
	 * @param columnName
	 * @return
	 */
	public List<String> getColumnValueByName(String columnName){
		List<String> columnList = this.getColumnValueInOnePage(columnName);
		PagingComponent turnPage = new PagingComponent();
		while(turnPage.nextExists()){
			turnPage.clickNext();
			this.waitLoading();
			columnList.addAll(this.getColumnValueInOnePage(columnName));
		}
		return columnList;
	}
	
	public String getMessage(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length < 0){
			throw new ItemNotFoundException("Can't find any message.");
		}
		String msg = objs[0].getProperty(".text");
		return msg;
	}
}
