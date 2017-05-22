package com.activenetwork.qa.awo.pages.orms.licenseManager.tax;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * This is the tax detail page in license manger
 * How to go to this page:Select "Tax" for the Financials dropdown list at the right top menu of license manager
 * @author Phoebe
 */
public class LicMgrTaxSearchPage extends LicMgrCommonTopMenuPage {
	static private LicMgrTaxSearchPage _instance = null;

	protected LicMgrTaxSearchPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public LicMgrTaxSearchPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new LicMgrTaxSearchPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(taxListTable());
	}
	
	protected Property[] taxSchedulesLable() {
		return Property.concatPropertyArray(this.a(), ".text", "Tax Schedules");
	}
	
	public void clickTaxScheduleLable(){
		browser.clickGuiObject(this.taxSchedulesLable());
	}
	
	protected Property[] searchTypeDropDownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxTypeSearchCriteria.searchBy");
	}
	
	
	protected Property[] searchValueTextField() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxTypeSearchCriteria.searchByValue");
	}
	
	protected Property[] showStatusDropdownlist() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxTypeSearchCriteria.active");
	}
	
	protected Property[] rateTyoeDropdownlist() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxTypeSearchCriteria.rateType");
	}
	
	protected Property[] feeTypeDropdownlist() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxTypeSearchCriteria.feeType");
	}
	
	protected Property[] scheduleDropdownlist() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxTypeSearchCriteria.withSchedules");
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
	
	protected Property[] taxListTable() {
		return Property.concatPropertyArray(this.table(), ".id", "taxListView_LIST");
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
	
	public void selectShowStatus(String status){
		if(StringUtil.notEmpty(status)){
			browser.selectDropdownList(this.showStatusDropdownlist(), status);
		}else{
			browser.selectDropdownList(this.showStatusDropdownlist(), 0);
		}
	}
	
	public void selectRateType(String rateType){
		if(StringUtil.notEmpty(rateType)){
			browser.selectDropdownList(this.rateTyoeDropdownlist(), rateType);
		}else{
			browser.selectDropdownList(this.rateTyoeDropdownlist(), 0);
		}
	}
	
	public void selectFeeType(String feeType){
		if(StringUtil.notEmpty(feeType)){
			browser.selectDropdownList(this.feeTypeDropdownlist(), feeType);
		}else{
			browser.selectDropdownList(this.feeTypeDropdownlist(), 0);
		}
	}
	
	public void selectWithOrWithoutSchedules(String withOrWithOutSchedule){
		if(StringUtil.notEmpty(withOrWithOutSchedule)){
			browser.selectDropdownList(this.scheduleDropdownlist(), withOrWithOutSchedule);
		}else{
			browser.selectDropdownList(this.scheduleDropdownlist(), 0);
		}
	}
	
	public void clickSearch(){
		browser.clickGuiObject(this.searchBtn());
	}
	
	public void clickAddNew(){
		browser.clickGuiObject(this.addNewBtn());
	}
	
	public void clearSearchCritials(){
		this.selectSearchType(StringUtil.EMPTY);
		this.setSearchValue(StringUtil.EMPTY);
		this.selectShowStatus(StringUtil.EMPTY);
		this.selectRateType(StringUtil.EMPTY);
		this.selectFeeType(StringUtil.EMPTY);
		this.selectWithOrWithoutSchedules(StringUtil.EMPTY);
	}
	
	public void searchBySearchTypeAndValue(String type, String value){
		this.clearSearchCritials();
		this.selectSearchType(type);
		this.setSearchValue(value);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void searchByTaxCode(String code){
		this.searchBySearchTypeAndValue("Tax Code", code);
	}
	
	public void searchByTaxName(String name){
		this.searchBySearchTypeAndValue("Tax Name", name);
	}
	
	public void clickTaxName(String taxName){
		browser.clickGuiObject(".class", "Html.A", ".text", taxName);
	}
	
}
