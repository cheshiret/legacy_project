package com.activenetwork.qa.awo.pages.orms.inventoryManager.equipment;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * This page is the equipment search page, select the "Equipment Inventory Set-up" from search drop down list, it will go to this page
 * @author Pchen
 */
public class InvMgrEquipmentInventorySearchPage  extends InvMgrCommonTopMenuPage {
	static private InvMgrEquipmentInventorySearchPage _instance = null;

	protected InvMgrEquipmentInventorySearchPage() {
	}

	static public InvMgrEquipmentInventorySearchPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new InvMgrEquipmentInventorySearchPage();
		}

		return _instance;
	}

	/**
	 * Check the object exists
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.equipmentInventoryTable());
	}
	
	protected Property[] equipmentNameDropdownList() {
		return Property.concatPropertyArray(this.select(), ".id", new RegularExpression("QuantityPeriodInventoryCriteria-\\d+\\.product", false));
	}
	
	protected Property[] startDateTextField() {
		return Property.concatPropertyArray(this.input("text"), ".id", new RegularExpression("QuantityPeriodInventoryCriteria-\\d+\\.startDate_ForDisplay", false));
	}
	
	protected Property[] endDateTextField() {
		return Property.concatPropertyArray(this.input("text"), ".id", new RegularExpression("QuantityPeriodInventoryCriteria-\\d+\\.endDate_ForDisplay", false));
	}
	
	protected Property[] statusDropdownList() {
		return Property.concatPropertyArray(this.select(), ".id", new RegularExpression("QuantityPeriodInventoryCriteria-\\d+\\.status", false));
	}
	
	protected Property[] addNewBtn(){
		return Property.concatPropertyArray(this.a(), ".text", "Add New");
	}
	
	protected Property[] searchBtn() {
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("^Search$", false));
	}
	
	protected Property[] activateBtn() {
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("^Activate$", false));
	}
	
	protected Property[] deactivateBtn() {
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("^Deactivate$", false));
	}
	
	protected Property[] equipmentInventoryTable(){
		return Property.concatPropertyArray(this.table(),".id", "equipmentInvResultsGrid_LIST");
	}
	
	public void clickAddNew(){
		browser.clickGuiObject(this.addNewBtn());
	}
	
	public void selectEquipment(String code, String name) {
		browser.selectDropdownList(equipmentNameDropdownList(), code+"-"+name);
	}
	
	public void setFromDate(String date) {
		browser.setCalendarField(startDateTextField(), date);
	}
	
	public void setToDate(String date) {
		browser.setCalendarField(endDateTextField(), date);
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(statusDropdownList(), status);
	}
	
	public void clickSearch() {
		browser.clickGuiObject(searchBtn());
	}
	
	public void clickDeactivate() {
		browser.clickGuiObject(deactivateBtn());
	}
	
	public void setupEquipmentInvSearchCriteria(String code, String name, String fromDate, String toDate, String status) {
		if(StringUtil.notEmpty(code) && StringUtil.notEmpty(name))
			this.selectEquipment(code, name);
		if(StringUtil.notEmpty(fromDate))
			this.setFromDate(fromDate);
		if(StringUtil.notEmpty(toDate))
			this.setToDate(toDate);
		if(StringUtil.notEmpty(status))
			this.selectStatus(status);
	}
	
	public void searchEquipmentInv(String code, String name, String fromDate, String toDate, String status) {
		this.setupEquipmentInvSearchCriteria(code, name, fromDate, toDate, status);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void deactivateAllInv() {
		logger.info("Deactiavte all inventories.");
		browser.selectCheckBox(Property.concatPropertyArray(this.input(".checkbox"), ".value", "all"));
		this.clickDeactivate();
		ajax.waitLoading();
		this.waitLoading();
	}
}
