package com.activenetwork.qa.awo.pages.orms.licenseManager.tax;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * This is the tax schedule schedule search page in license manager
 * How to go to this page:Go to schedule search page( Select "Tax" for the Financials dropdown list at the right top menu of license manager ) and then
 * click "Tax Schedules" label
 * @author Phoebe
 */
public class LicMgrTaxScheduleSearchPage extends LicMgrCommonTopMenuPage {
	static private LicMgrTaxScheduleSearchPage _instance = null;

	protected LicMgrTaxScheduleSearchPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public LicMgrTaxScheduleSearchPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new LicMgrTaxScheduleSearchPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(taxScheduleListTable());
	}
	
	protected Property[] searchTypeDropDownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxScheduleSearchCriteria.searchBy");
	}
	
	protected Property[] searchValueTextField() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxScheduleSearchCriteria.searchByValue");
	}
	
	protected Property[] dateTypeDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxScheduleSearchCriteria.dateType");
	}
	
	protected Property[] fromDateTextField() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxScheduleSearchCriteria.fromDate_ForDisplay");
	}
	
	protected Property[] toDateTextField() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxScheduleSearchCriteria.toDate_ForDisplay");
	}
	
	protected Property[] showStatusDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxScheduleSearchCriteria.active");
	}
	
	protected Property[] feeTypeDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxScheduleSearchCriteria.feeType");
	}
	
	protected Property[] rateTypeDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxScheduleSearchCriteria.rateType");
	}
	
	protected Property[] customerTypeDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxScheduleSearchCriteria.customerTypeID");
	}
	
	protected Property[] transactionTypeDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxScheduleSearchCriteria.transactionType");
	}
	
	protected Property[] searchBtn() {
		return Property.concatPropertyArray(this.a(), ".text", "Search");
	}
	
	protected Property[] addNewBtn() {
		return Property.concatPropertyArray(this.a(), ".text", "Add New");
	}
	
	protected Property[] activateBtn() {
		return Property.concatPropertyArray(this.a(), ".text", "Activate");
	}
	
	protected Property[] deactivateBtn() {
		return Property.concatPropertyArray(this.a(), ".text", "Deactivate");
	}
	
	protected Property[] taxScheduleListTable(){
		return Property.concatPropertyArray(this.table(), ".id", "taxScheduleListView_LIST");
	}
	
	public void selectSearchType(String type){
		if(StringUtil.notEmpty(type)){
			browser.selectDropdownList(this.searchTypeDropDownList(), type);
		}else{
			browser.selectDropdownList(this.searchTypeDropDownList(), 0);
		}
	}
	
	public void setSearchValue(String value){
			browser.setTextField(this.searchValueTextField(), value);
	}
	
	public void selectDateType(String dateType){
		if(StringUtil.notEmpty(dateType)){
			browser.selectDropdownList(this.dateTypeDropdownList(), dateType);
		}else{
			browser.selectDropdownList(this.dateTypeDropdownList(), 0);
		}
	}
	
	public void setFromDate(String from){
		browser.setTextField(this.fromDateTextField(), from);
	}
	
	public void setToDate(String to){
		browser.setTextArea(this.toDateTextField(), to);
	}
	
	public void selectShowStatus(String status){
		if(StringUtil.notEmpty(status)){
			browser.selectDropdownList(this.showStatusDropdownList(), status);
		}else{
			browser.selectDropdownList(this.showStatusDropdownList(), 0);
		}
	}
	
	public void selectFeeType(String feeType){
		if(StringUtil.notEmpty(feeType)){
			browser.selectDropdownList(this.feeTypeDropdownList(), feeType);
		}else{
			browser.selectDropdownList(this.feeTypeDropdownList(), 0);
		}
	}
	
	public void selectRateType(String rateType){
		if(StringUtil.notEmpty(rateType)){
			browser.selectDropdownList(this.rateTypeDropdownList(), rateType);
		}else{
			browser.selectDropdownList(this.rateTypeDropdownList(), 0);
		}
	}
	
	public void selectCustomerType(String custType){
		if(StringUtil.notEmpty(custType)){
			browser.selectDropdownList(this.customerTypeDropdownList(), custType);
		}else{
			browser.selectDropdownList(this.customerTypeDropdownList(), 0);
		}
	}
	
	public void selectTransactionType(String transType){
		if(StringUtil.notEmpty(transType)){
			browser.selectDropdownList(this.transactionTypeDropdownList(), transType);
		}else{
			browser.selectDropdownList(this.transactionTypeDropdownList(), 0);
		}
	}
	
	public void clickSearch(){
		browser.clickGuiObject(this.searchBtn());
	}
	
	public void clickAddNew(){
		browser.clickGuiObject(this.addNewBtn());
	}
	
	public void clickActivate(){
		browser.clickGuiObject(this.activateBtn());
	}
	
	public void clickDeactivate(){
		browser.clickGuiObject(this.deactivateBtn());
	}
	
	public void clickScheduleId(String id){
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	
	public void selectAllTaxSchedule(){
		browser.selectCheckBox(".class", "Html.INPUT.CHECKBOX", ".name", "all_slct");
	}
	
	public void clearSearchCritials(){
		this.selectSearchType(StringUtil.EMPTY);
		this.setSearchValue(StringUtil.EMPTY);
		this.selectDateType(StringUtil.EMPTY);
		this.setFromDate(StringUtil.EMPTY);
		this.setToDate(StringUtil.EMPTY);
		this.selectShowStatus(StringUtil.EMPTY);
		this.selectFeeType(StringUtil.EMPTY);
		this.selectRateType(StringUtil.EMPTY);
		this.selectCustomerType(StringUtil.EMPTY);
		this.selectTransactionType(StringUtil.EMPTY);
	}
	
	
	public void searchBySearchTypeAndValue(String type, String value){
		this.clearSearchCritials();
		this.selectSearchType(type);
		this.setSearchValue(value);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void searchByScheduleId(String id){
		this.searchBySearchTypeAndValue("Tax Schedule ID", id);
	}
	
	public void searchByProduct(String product){
		this.searchBySearchTypeAndValue("Product", product);
	}
	
	public void searchActiveScheduleByProduct(String product){
		this.clearSearchCritials();
		this.selectSearchType("Product");
		this.setSearchValue(product);
		this.selectShowStatus("Active");
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void clickTaxScheduleId(String id){
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	
	public void selectCheckBoxBeforeTaxSchedule(String id){
		browser.clickGuiObject(".id", "TaxScheduleView.id", ".value", id);
	}
	
	public void activeTaxSchedule(String scheduleId){
		this.searchByScheduleId(scheduleId);
		this.selectCheckBoxBeforeTaxSchedule(scheduleId);
		this.clickActivate();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void activeTaxSchedules(String... scheduleIds){
		for(String id:scheduleIds){
			this.activeTaxSchedule(id);
		}
	}
	
	/**
	 * This used for clear up data
	 * @param schedulePrd
	 */
	public void deactiveScheduleForPrd(String schedulePrd){
		searchActiveScheduleByProduct(schedulePrd);
		this.selectAllTaxSchedule();
		this.clickDeactivate();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void deactiveSchedulesForPrd(String... products){
		for(String prd:products){
			this.deactiveScheduleForPrd(prd);
		}
	}
	
	public void searchTaxSchedule(ScheduleData schedule){
		if(StringUtil.notEmpty(schedule.searchBy)){
			this.selectSearchType(schedule.searchBy);
		}
		if(StringUtil.notEmpty(schedule.searchValue)){
			this.setSearchValue(schedule.searchValue);
		}
		if(StringUtil.notEmpty(schedule.dateType)){
			this.selectDateType(schedule.dateType);
			this.setFromDate(schedule.fromDate);
			this.setToDate(schedule.toDate);
		}
		if(StringUtil.notEmpty(schedule.searchStatus)){
			this.selectShowStatus(schedule.searchStatus);
		}
		if(StringUtil.notEmpty(schedule.feeType)){
			this.selectFeeType(schedule.feeType);
		}
		if(StringUtil.notEmpty(schedule.rateType)){
			this.selectRateType(schedule.rateType);
		}
		if(StringUtil.notEmpty(schedule.customerType)){
			this.selectCustomerType(schedule.customerType);
		}
		if(StringUtil.notEmpty(schedule.tranType)){
			this.selectTransactionType(schedule.tranType);
		}
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public IHtmlObject[] getSearchTaxScheduleTable(){
		IHtmlObject[] objs = browser.getTableTestObject(this.taxScheduleListTable());
		return objs;
	}
	
	public List<String> getSpecificColValueList(String col){
		PagingComponent turnPage = new PagingComponent();
		List<String> values = new ArrayList<String>();
		do{
			IHtmlObject[] objs = this.getSearchTaxScheduleTable();
			IHtmlTable tableGrid = (IHtmlTable) objs[objs.length-1];
			if(tableGrid.rowCount()>1){
				int colNum = tableGrid.findColumn(0, col);
				List<String> columnList = tableGrid.getColumnValues(colNum);
				columnList.remove(0);
				values.addAll(columnList);
			 } else {
				 break;
			 }
			Browser.unregister(objs);
		}while(turnPage.clickNext());
		return values;
	}
	
	public void verifySearchResults(String val, String col) {
		List<String> values = this.getSpecificColValueList(col);
		if(values.size() <= 0){
			logger.error("There isn't any records!");
		} else {
			for (String value : values) {
				if (!value.contains(val)) {
					throw new ErrorOnPageException("Search Fee Schedule Fail!", val, value);
				}
			}
		}
	}
	
	public void verifySchdInSearchList(String id) {
		List<String> colValue = this.getSpecificColValueList("Tax Schedule ID");
		if(!colValue.contains(id)){
			throw new ItemNotFoundException("Search Fee Schedule Fail!");
		}else{
			logger.info("Fee schedule with id:" + id + " is exist");
		}
	}
	
	public String getValueUnderColForSchedule(String scheduleId, String colName){
		IHtmlObject[] objs = this.getSearchTaxScheduleTable();
		IHtmlTable tableGrid = (IHtmlTable) objs[objs.length-1];
		int row = tableGrid.findRow(1, scheduleId);
		int col = tableGrid.findColumn(0, colName);
		String value = tableGrid.getCellValue(row, col);
		Browser.unregister(objs);
		return value;
	}
	
	public String getScheduleStatus(String scheduleId){
		String status = "";
		String value = getValueUnderColForSchedule(scheduleId, "Active");
		if(value.equalsIgnoreCase("Yes")){
			status = OrmsConstants.ACTIVE_STATUS;
		}
		if(value.equalsIgnoreCase("No")){
			status = OrmsConstants.INACTIVE_STATUS;
		}
		return status;
	}
	
	public void verifyScheduleStatus(String scheduleId, String expStatus){
		this.searchByScheduleId(scheduleId);
		String status = this.getScheduleStatus(scheduleId);
		if(!status.equalsIgnoreCase(expStatus)){
			throw new ErrorOnPageException("The tax schedule status is not correct", expStatus, status);
		}
		logger.info("Tax schedule:" + scheduleId + " is correct with status:" + expStatus);
	}
	
}
