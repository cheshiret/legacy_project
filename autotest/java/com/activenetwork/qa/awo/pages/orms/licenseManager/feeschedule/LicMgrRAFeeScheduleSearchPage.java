package com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrRAFeeScheduleSearchPage extends LicMgrFeeScheduleCommonPage {
	static private LicMgrRAFeeScheduleSearchPage _instance = null;

	protected LicMgrRAFeeScheduleSearchPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public LicMgrRAFeeScheduleSearchPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new LicMgrRAFeeScheduleSearchPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(this.raFeeSearchResultTable());
	}

	protected Property[] raFeeSearchResultTable(){
		return Property.toPropertyArray(".id", "RAFeeScheduleSearchResultGrid_LIST");
	}
	
	protected Property[] searchTypeDropDownList(){
		return Property.toPropertyArray(".id", "FeeSearchRequest.searchBy");
	}
	
	protected Property[] searchValueTextField(){
		return Property.toPropertyArray(".id", "FeeSearchRequest.value");
	}
	
	protected Property[] dateDropdownList(){
		return Property.toPropertyArray(".id", "FeeSearchRequest.dateBy");
	}
	
	protected Property[] fromDateTextField(){
		return Property.toPropertyArray(".id", "FeeSearchRequest.start_ForDisplay");
	}
	
	protected Property[] toDateTextField(){
		return Property.toPropertyArray(".id", "FeeSearchRequest.end_ForDisplay");
	}
	
	protected Property[] allLicenseYearRadioBtn() {
		return Property.toPropertyArray(".class", "Html.INPUT.RADIO", ".id", new RegularExpression("FeeSearchRequest-\\d+\\.licenseYearType0",false));
	}
	
	protected Property[] specialLicenseYearRadioBtn() {
		return Property.toPropertyArray(".class", "Html.INPUT.RADIO", ".id", new RegularExpression("FeeSearchRequest-\\d+\\.licenseYearType1",false));
	}
	
	protected Property[] licenseYearTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.TEXT", ".id", "FeeSearchRequest.licenseYear");
	}
	
	protected Property[] showStatusDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "FeeSearchRequest.feeSchdStatus");
	}
	
	protected Property[] productCategoryDropdownList(){
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", new RegularExpression("FeeSearchRequest-\\d+\\.prdGrpCat",false));
	}
	
	protected Property[] applicableProductCategoryDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "FeeSearchRequest.applicableProductCategory");
	}
	
	protected Property[] unitTypeDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "FeeSearchRequest.unitType");
	}
	
	protected Property[] ticketPermitCategoryDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "FeeSearchRequest.ticketCategory");
	}
	
	protected Property[] permitTypeDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "FeeSearchRequest.permitTyleId");
	}
	
	protected Property[] productSubCategoryDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "FeeSearchRequest.prdSubCatID");
	}
	
	protected Property[] vehicleTypeDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "FeeSearchRequest.vehicleTypeID");
	}
	
	protected Property[] productFeeClassDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "FeeSearchRequest.productFeeClass");
	}
	
	protected Property[] transactionTypeDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "FeeSearchRequest.transactionTypeID");
	}
	
	protected Property[] transactionOccurenceDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "FeeSearchRequest.transactionOccurrenceID");
	}
	
	protected Property[] saleChannelDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "FeeSearchRequest.salesChannel");
	}
	
	protected Property[] locationClassDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "FeeSearchRequest.locationClassID");
	}
	
	protected Property[] allCheckBox(){
		return Property.toPropertyArray(".class", "Html.INPUT.CHECKBOX",".value", "all");
	}
	
	protected Property[] feeScheduleCheckBox(String scheduleId){
		return Property.toPropertyArray(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false),
				".class","Html.INPUT.CHECKBOX",".value", scheduleId);
	}
	
	protected Property[] searchBtn(){
		return Property.concatPropertyArray(this.a(), ".text", "Search");
	}
	
	protected Property[] addNewBtn(){
		return Property.concatPropertyArray(this.a(), ".text", "Add New");
	}
	
	protected Property[] searchResultTable(){
		return Property.concatPropertyArray(this.table(), ".id", "RAFeeScheduleSearchResultGrid_LIST");
	}
	
	protected Property[] activateBtn(){
		return Property.concatPropertyArray(this.a(), ".text", "Activate");
	}
	
	protected Property[] deactivateBtn(){
		return Property.concatPropertyArray(this.a(), ".text", "Deactivate");
	}
	
	public void selectSearchType(String type){
		browser.selectDropdownList(this.searchTypeDropDownList(), type);
	}
	
	public void setSearchValue(String value){
		browser.setTextField(this.searchValueTextField(), value);
	}
	
	public void selectDateType(String dateType){
		if(StringUtil.isEmpty(dateType)){
			browser.selectDropdownList(this.dateDropdownList(), 0);
		}else{
			browser.selectDropdownList(this.dateDropdownList(), dateType);
		}
	}
	
	public void setFromDate(String from){
		browser.setTextField(this.fromDateTextField(), from);
	}
	
	public void setToDate(String to){
		browser.setTextField(this.toDateTextField(), to);
	}
	
	public void selectAllLicenseYear(){
		browser.selectRadioButton(this.allLicenseYearRadioBtn(), 0);
	}
	
	public void selectSpecialLicenseYear(){
		browser.selectRadioButton(this.specialLicenseYearRadioBtn(), 0);
	}
	
	public void setLicenseYear(String licenseYear){
		browser.setTextField(this.licenseYearTextField(), licenseYear);
	}
	
	public void selectStatus(String status){
		if(StringUtil.isEmpty(status)){
			browser.selectDropdownList(this.showStatusDropdownList(), 0);
		}else{
			browser.selectDropdownList(this.showStatusDropdownList(), status);
		}
	}
	
	public void selectProductCategory(String prdCat){
		if(StringUtil.isEmpty(prdCat)){
			browser.selectDropdownList(this.productCategoryDropdownList(), 0);
		}else{
			browser.selectDropdownList(this.productCategoryDropdownList(), prdCat);
		}
	}
	
	public void selectUnit(String unit){
		if(StringUtil.isEmpty(unit)){
			browser.selectDropdownList(this.unitTypeDropdownList(), 0);
		}else{
			browser.selectDropdownList(this.unitTypeDropdownList(), unit);
		}
	}
	
	public void selectTransactionType(String transType){
		if(StringUtil.isEmpty(transType)){
			browser.selectDropdownList(this.transactionTypeDropdownList(), 0);
		}else{
			browser.selectDropdownList(this.transactionTypeDropdownList(), transType);
		}
	}
	
	public void selectSalesChannel(String saleChannel){
		if(StringUtil.isEmpty(saleChannel)){
			browser.selectDropdownList(this.saleChannelDropdownList(), 0);
		}else{
			browser.selectDropdownList(this.saleChannelDropdownList(), saleChannel);
		}
	}
	
	public void selectLocationClass(String locationClass){
		if(StringUtil.isEmpty(locationClass)){
			browser.selectDropdownList(this.locationClassDropdownList(), 0);
		}else{
			browser.selectDropdownList(this.locationClassDropdownList(), locationClass);
		}
	}
	
	public void selectApplicableProductCategory(String appPrdCat){
		if(StringUtil.isEmpty(appPrdCat)){
			browser.selectDropdownList(this.applicableProductCategoryDropdownList(), 0);
		}else{
			browser.selectDropdownList(this.applicableProductCategoryDropdownList(), appPrdCat);
		}
	}
	
	public void clickAddNew(){
		browser.clickGuiObject(this.addNewBtn());
	}
	
	public void searchBySearchTypeAndValue(String searchBy, String searchValue){
		this.selectSearchType(searchBy);
		this.setSearchValue(searchValue);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void searchByFeeScheduleId(String scheduleId){
		this.searchBySearchTypeAndValue("Fee Schedule ID", scheduleId);
	}
	
	public void searchByFeeSchedulePrd(String prdName){
		this.searchBySearchTypeAndValue("Product", prdName);
	}
	
	public void clickFeeScheduleId(String scheduleId){
		browser.clickGuiObject(".class", "Html.A", ".text", scheduleId);
	}
	
	public void clickActive(){
		browser.clickGuiObject(this.activateBtn());
	}
	
	public void clickDeactive(){
		browser.clickGuiObject(this.deactivateBtn());
	}
	
	public void selectAllFeeScheduleInList(){
		browser.selectCheckBox(this.allCheckBox());
	}
	
	public void selectCheckBoxBeforeSchedule(String scheduleId){
		browser.selectCheckBox(this.feeScheduleCheckBox(scheduleId));
	}
	
	/**
	 * This used for clear up data
	 * @param schedulePrd
	 */
	public void deactiveScheduleForPrd(String schedulePrd){
		searchByFeeSchedulePrd(schedulePrd);
		this.selectAllFeeScheduleInList();
		this.clickDeactive();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void changeScheduleStatus(String scheduleId, String status){
		this.searchByFeeScheduleId(scheduleId);
		this.selectCheckBoxBeforeSchedule(scheduleId);
		if(status.equalsIgnoreCase(OrmsConstants.ACTIVE_STATUS)){
			this.clickActive();
		}else{
			this.clickDeactive();
		}
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void activeFeeSchedule(String... scheduleIds){
		for(String id:scheduleIds){
			this.changeScheduleStatus(id, OrmsConstants.ACTIVE_STATUS);
		}
	}
	
	public void deactiveFeeSchedule(String... scheduleIds){
		for(String id:scheduleIds){
			this.changeScheduleStatus(id, OrmsConstants.INACTIVE_STATUS);
		}
	}
	
	private IHtmlObject[] getSearchFeeScheduleTable(){
		return browser.getTableTestObject(raFeeSearchResultTable());
	}
	
	public String getValueUnderColForSchedule(String scheduleId, String colName){
		IHtmlObject[] objs = this.getSearchFeeScheduleTable();
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
		this.searchByFeeScheduleId(scheduleId);
		String status = this.getScheduleStatus(scheduleId);
		if(!status.equalsIgnoreCase(expStatus)){
			throw new ErrorOnPageException("The fee schedule status is not correct", expStatus, status);
		}
		logger.info("Fee schedule:" + scheduleId + " is correct with status:" + expStatus);
	}
	
	public boolean isProductCategoryEnabled(){
		return browser.checkHtmlObjectEnabled(this.productCategoryDropdownList());
	}
	
	/**
	 * This method used to clear all search criteria
	 * 
	 */
	public void clearAllSearchCriteria(String productCat) {
		this.selectSearchType(StringUtil.EMPTY);
		this.setSearchValue(StringUtil.EMPTY);
		this.selectDateType(StringUtil.EMPTY);
		this.setFromDate(StringUtil.EMPTY);
		this.setToDate(StringUtil.EMPTY);
		this.selectAllLicenseYear();
		this.setLicenseYear(StringUtil.EMPTY);
		if(this.isProductCategoryEnabled()){
			this.selectProductCategory(productCat);
		}
		this.selectStatus(StringUtil.EMPTY);
		this.selectUnit(StringUtil.EMPTY);
		this.selectTransactionType(StringUtil.EMPTY);
		this.selectSalesChannel(StringUtil.EMPTY);
		this.selectLocationClass(StringUtil.EMPTY);
	}
	
	public void setupSearchCriteria(FeeScheduleData schedule) {
		logger.info("Set up search criteria for fee schedule.");
		if(StringUtil.isEmpty(schedule.productCategory)){
			this.clearAllSearchCriteria("Activity");
		}else{
			this.clearAllSearchCriteria(schedule.productCategory);
		}
		if (StringUtil.notEmpty(schedule.searchType)) {
			this.selectSearchType(schedule.searchType);
			setSearchValue(schedule.searchValue);
		}
		if (StringUtil.notEmpty(schedule.dateType)) {
			selectDateType(schedule.dateType);
			setFromDate(schedule.fromDate);
			setToDate(schedule.toDate);
		}
		if(StringUtil.isEmpty(schedule.licenseYear)){
			this.selectAllLicenseYear();
		}else{
			this.selectSpecialLicenseYear();
			this.setLicenseYear(schedule.licenseYear);
		}
		if(StringUtil.notEmpty(schedule.activeStatus)){
			this.selectStatus(schedule.activeStatus);
		}
		if(this.isProductCategoryEnabled()&&StringUtil.notEmpty(schedule.productCategory)){
			this.selectProductCategory(schedule.productCategory);
		}
		if ( StringUtil.notEmpty(schedule.unit)) {
			this.selectUnit(schedule.unit);
		}
		if (StringUtil.notEmpty(schedule.tranType)) {
			this.selectTransactionType(schedule.tranType);
		}
		if(StringUtil.notEmpty(schedule.salesChannel)){
			this.selectSalesChannel(schedule.salesChannel);
		}
		if(StringUtil.notEmpty(schedule.locationClass)){
			this.selectLocationClass(schedule.locationClass);
		}
	}
	
 	/**
 	 * search fee schedule.
 	 * @param fee
 	 */
 	public void searchFeeSchedule(FeeScheduleData fee){
 		this.setupSearchCriteria(fee);
 		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
 	}
 	
 	public List<String> getSpecificColValueList(String col){
		PagingComponent turnPage = new PagingComponent();
		List<String> values = new ArrayList<String>();
		do{
			IHtmlObject[] objs = this.getSearchFeeScheduleTable();
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
		List<String> colValue = this.getSpecificColValueList("Fee Schedule ID");
		if(!colValue.contains(id)){
			throw new ItemNotFoundException("Search Fee Schedule Fail!");
		}else{
			logger.info("Fee schedule with id:" + id + " is exist");
		}
	}
}
