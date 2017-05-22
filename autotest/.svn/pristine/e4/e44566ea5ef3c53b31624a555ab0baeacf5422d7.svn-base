/*
 * $Id: FinMgrTaxSchDetailPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.financeManager.tax;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @author CGuo
 */
public class FinMgrTaxSchDetailPage extends FinanceManagerPage {

	/**
	 * Script Name : FinMgrTaxSchDetailPage Generated : Dec 16, 2004 6:29:12 PM
	 * Original Host : WinNT Version 5.1 Build 2600 (Service Pack 2)
	 * 
	 * @since 2004/12/16
	 */

	/** A handle to the unique Singleton instance. */
	private static FinMgrTaxSchDetailPage _instance = null;

	private final String prefix = "(Slip|List)?TaxScheduleView(-\\d+)?\\.";
	
	/**
	 * @return The unique instance of this class.
	 */
	public static FinMgrTaxSchDetailPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrTaxSchDetailPage();
		}

		return _instance;
	}

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrTaxSchDetailPage() {

	}

	/** Check this page is exists */
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
//				"Tax Schedule Details");
		return browser.checkHtmlObjectExists(".id", new RegularExpression("TaxScheduleView(-\\d+)?\\.taxTypeID", false));
	}

	/** Check fee type is exists */
	public boolean checkSelectTaxNameExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(prefix + "taxTypeID",false));
	}

	private boolean isElementEnabled(Object id) {
		IHtmlObject objs[] = browser.getHtmlObject(".id", id);
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find any element by id - " + id);
		}
		
		boolean enabled = objs[0].isEnabled();
		Browser.unregister(objs);
		
		return enabled;
	}
	
	/**
	 * Select Tax name from drop down list
	 * 
	 * @param taxName
	 */
	public void selectTaxName(String taxName) {
		if (checkSelectTaxNameExists()) {
			browser.selectDropdownList(".id", 
					new RegularExpression(prefix + "taxTypeID",false), taxName);
			ajax.waitLoading();
		}
	}
	
	public boolean isTaxNameEnabled() {
		return isElementEnabled(new RegularExpression(prefix + "taxTypeID",false));
	}
	
	/**
	 * Select fee type from drop down list
	 * 
	 * @param feeType
	 */
	public void selectFeeType(String feeType) {
		if (checkSelectFeeTypeExists()) {
			browser.selectDropdownList(".id", new RegularExpression(prefix + "feeType",false), feeType);
			ajax.waitLoading();
		}
	}
	
	public boolean isFeeTypeEnabled() {
		return isElementEnabled(new RegularExpression(prefix + "feeType", false));
	}

	/**
	 * Select product category from drop down list
	 * 
	 * @param category
	 */
	public void selectProductCategory(String category) {
		browser.selectDropdownList(".id", new RegularExpression(prefix + "productCategory",false), category);
		ajax.waitLoading();
	}

	public boolean isProductCategoryEnabled() {
		return isElementEnabled(new RegularExpression(prefix + "productCategory", false));
	}
	
	/**
	 * Input tax schedule start effective date
	 * 
	 * @param startDate
	 */
	public void setEffectiveStartDate(String startDate) {
//		browser.setCalendarField(".id", new RegularExpression(prefix + "effectiveStartDate_ForDisplay",false), startDate);
		setDateAndGetMessage((IText)browser.getTextField(".id", new RegularExpression(prefix + "effectiveStartDate_ForDisplay",false))[0], startDate);
	}
	
	public boolean isEffectiveStartDateEnabled() {
		return isElementEnabled(new RegularExpression(prefix + "effectiveStartDate_ForDisplay",false));
	}

	/**
	 * Input tax schedule end effective date
	 * 
	 * @param date
	 */
	public void setEffectiveEndDate(String endDate) {
//		browser.setTextField(".id",	new RegularExpression(prefix + "effectiveEndDate_ForDisplay",false), endDate);
		setDateAndGetMessage((IText)browser.getTextField(".id",	new RegularExpression(prefix + "effectiveEndDate_ForDisplay",false))[0], endDate);
	}
	
	public boolean isEffectiveEndDateEnabled() {
		return isElementEnabled(new RegularExpression(prefix + "effectiveEndDate_ForDisplay",false));
	}
	
	protected void moveFocusOutOfCalendar(){
		browser.clickGuiObject(".class","Html.LABEL",".text","Effective End");
	}
	
	public void selectMarinaRateType(String marinaRateType){
		browser.selectDropdownList(".id", "SlipTaxScheduleView.marinaRateType", marinaRateType);
	}
	
	public boolean isMarinaRateTypeEnabled() {
		return isElementEnabled("SlipTaxScheduleView.marinaRateType");
	}
	
	public boolean isMarinaRateTypeExist(){
		return browser.checkHtmlObjectExists(".id", "SlipTaxScheduleView.marinaRateType");
	}
	
	public void selectApplicableProductCategory(String appPrdCategory){
		browser.selectDropdownList(".id", new RegularExpression("ListTaxScheduleView-\\d+\\.applicablePrdCategory" ,false), appPrdCategory);
	}

	/**
	 * Select Account Code from drop down list
	 * 
	 * @param acct
	 */
	public void selectAccountCode(String acct) {
		browser.selectDropdownList(".id", new RegularExpression(prefix + "accountID",false), acct);
		ajax.waitLoading();
	}
	
	public boolean isAccountCodeEnabled() {
		return isElementEnabled(new RegularExpression(prefix + "accountID", false));
	}

	/** Click Ok Button */
	public void clickOk() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/** Click Apply Button */
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	/** Click Cancel Button */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	/**
	 * This method is used to enter all tax schedule details info
	 * taxName,feeType,effectiveStartDate,accountCode ---- must set up
	 * accountRate ---- default:0.0%
	 * 
	 * @param schedule
	 */
	public void enterTaxSchDetails(ScheduleData schedule) {
		if (schedule.taxName != null && schedule.taxName.length() > 0) {
			ajax.waitLoading();
			selectTaxName(schedule.taxName);
			ajax.waitLoading();
			waitLoading();
		}
		if (schedule.productCategory != null
				&& schedule.productCategory.length() > 0) {
			selectProductCategory(schedule.productCategory);
			ajax.waitLoading();
			waitLoading();
		}
		if (schedule.feeType != null && schedule.feeType.length() > 0) {
			selectFeeType(schedule.feeType);
			ajax.waitLoading();
			waitLoading();
		}
		if (schedule.productGroup != null && schedule.productGroup.length() > 0) {
			selectProductGroup(schedule.productGroup);
			ajax.waitLoading();
			waitLoading();
		}
		if (schedule.product != null && schedule.product.length() > 0) {
			if(isProductDisplayedAsDropdownList()) {
				selectProduct(schedule.product);
				ajax.waitLoading();
				waitLoading();
			} else {
				setProduct(schedule.product);
				ajax.waitLoading();
				waitLoading();
			}
		}
		if (schedule.startDate != null && schedule.startDate.length() > 0) {
			setEffectiveStartDate(schedule.startDate);
		} else {
			setEffectiveStartDate(DateFunctions.getDateAfterToday(-365));
		}
		if (schedule.endDate != null && schedule.endDate.length() > 0) {
			setEffectiveEndDate(schedule.endDate);
		} else {
			setEffectiveEndDate(DateFunctions.getDateAfterToday(365));
		}
		//For marina tax---slip
		if (StringUtil.notEmpty(schedule.marinaRateType)){
			selectMarinaRateType(schedule.marinaRateType);
		}
		//For marina tax---list
		if(StringUtil.notEmpty(schedule.appPrdCategory) && "List".equals(schedule.productCategory)){
			selectApplicableProductCategory(schedule.appPrdCategory);
			ajax.waitLoading();
			waitLoading();
		}
		
		if (schedule.customerType != null && schedule.customerType.length() > 0) {
			selectCustomerType(schedule.customerType);
		}

		if (schedule.tranType != null && schedule.tranType.trim().length() > 0) {
			this.selectTransactionType(schedule.tranType);
		}

		if (schedule.accountCode != null && schedule.accountCode.length() > 0) {
			// if used in setup script no matter if account code is empty, always get it from UI but not
			// from the static datapool or somewhere.
			if(Boolean.parseBoolean(TestProperty.getProperty("forceOperation"))){
				selectAccountCode(1);
			}else{
				selectAccountCode(schedule.accountCode);
			}
		}else{
			selectAccountCode(1);
		}
		if (schedule.rate != null && schedule.rate.length() > 0) {
			setRate(schedule.rate);
		}
		waitLoading();
	}
	
	public void selectAccountCode(int index) {
		int size = browser.getDropdownElements(".id", new RegularExpression(prefix + "accountID",false)).size();
		if(size>1){
			browser.selectDropdownList(".id", new RegularExpression(prefix + "accountID",false), index);
		}
	}

	/**
	 * Select product group from drop down list
	 * 
	 * @param group
	 */
	public void selectProductGroup(String group) {
		browser.selectDropdownList(".id", new RegularExpression(prefix + "productGroupId",false),group);
		ajax.waitLoading();
	}
	
	public boolean isApplicableProductCategoryEnabled() {
		return isElementEnabled(new RegularExpression(prefix + "applicablePrdCategory", false));
	}
	
	public boolean isApplicableProductCategoryExist(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression(prefix + "applicablePrdCategory", false));
	}
	
	public boolean isProductGroupEnabled() {
		return isElementEnabled(new RegularExpression(prefix + "productGroupId", false));
	}
	
	public boolean isProductDisplayedAsDropdownList() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression(prefix + "productId",false));
	}
	
	/**
	 * Select product from drop down list
	 * 
	 * @param prod
	 */
	public void selectProduct(String prod) {
		browser.selectDropdownList(".id", new RegularExpression(prefix + "productId",false), prod);
		ajax.waitLoading();
	}
	
	public void setProduct(String product) {
//		browser.setTextField(".id", new RegularExpression(prefix + "productName", false), product);
		int i=0;
		String triggerContent = "";
		do{
			browser.setTextField(".id", new RegularExpression(prefix + "productName", false), product, true);
			if(!product.equalsIgnoreCase("All")) {
				Property[] p = Property.toPropertyArray(".className", "ac_results");
				browser.waitExists(p);
				browser.waitExists(OrmsConstants.PAGELOADING_SYNC_TIME);
				Browser.sleep(3);
				browser.clickGuiObject(".class", "Html.LI", ".text", new RegularExpression(product+"\\(\\d+\\)", false), true);
				ajax.waitLoading();
				triggerContent = browser.getTextFieldValue(".id", new RegularExpression(prefix + "productName", false));
				i++;
			}
		}while(i<3&&!triggerContent.matches(product+ "\\(\\d+\\)"));
		
		if(!triggerContent.matches(product+ "\\(\\d+\\)")){
			throw new ErrorOnPageException("The product is not set properly after three times tries!");
		}
	}
	
	public boolean isProductEnabled() {
		return isElementEnabled(new RegularExpression(prefix + "productId",false));
	}

	/**
	 * Input account rate
	 * 
	 * @param rate
	 */
	public void setRate(String rate) {
		browser.setTextField(".id", new RegularExpression(prefix + "rate:PERCENTAGE",false), rate);
	}

	public boolean isRateEnabled() {
		return isElementEnabled(new RegularExpression(prefix + "rate:PERCENTAGE", false));
	}
	
	/** select customer type */
	public void selectCustomerType(String cust) {
		browser.selectDropdownList(".id", new RegularExpression(prefix + "customerType",false), cust);
	}

	public boolean isCustomerTypeEnabled() {
		return isElementEnabled(new RegularExpression(prefix + "customerType", false));
	}
	
	/** select transaction type */
	public void selectTransactionType(String type) {
		browser.selectDropdownList(".id", new RegularExpression(prefix + "transactionType",false), type);
	}

	public boolean isTransactionTypeEnabled() {
		return isElementEnabled(new RegularExpression(prefix + "transactionType", false));
	}
	
	/** select ChartOfAccount */
	public void selectChartOfAccount(String chart) {
		// MiscFunctions.selectItem(List_chart_of_account_id(), chart);
	}

	/**
	 * 
	 * @return tax schedule id
	 */
	public String getTaxSchID() {
		RegularExpression rex = new RegularExpression(
				"Tax Schedule\\W*+Tax Schedule ID.*", false);// updated by pzhu
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		String id = text.substring(
				text.indexOf("Schedule ID") + "Schedule ID".length(),
				text.indexOf("Location Name")).trim();
		return id;
	}

	/**
	 * search the tax schedule by tax schedule id
	 * 
	 * @param searchItem
	 * @param searchValue
	 * @throws PageNotFoundException
	 */
	public void searchItem(String searchItem, String searchValue) {
		browser.selectDropdownList(".id", "TaxScheduleSearchCriteria.searchBy",
				searchItem);
		browser.setTextField(".id", "TaxScheduleSearchCriteria.searchByValue",
				searchValue);
	}

	/** click Go button */
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search", false);
	}

	/**
	 * search tax schedule by tax schedule id
	 * 
	 * @param searchItem
	 * @param searchValue
	 */
	public void searchTaxSchedule(String searchItem, String searchValue) {
		searchItem(searchItem, searchValue);
		clickGo();
		ajax.waitLoading();
	}

	/** Check fee type is exists */
	public boolean checkSelectFeeTypeExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(prefix + "feeType",false));
	}

	/**
	 * set the type about tax schedule.
	 * 
	 * @param schedule
	 */
	public void setupTaxSchedule(ScheduleData schedule) {
		if (  schedule.taxName != null&& !"".equals(schedule.taxName)) {
			selectTaxName(schedule.taxName);
		}

		if ( schedule.feeType != null&&!"".equals(schedule.feeType)) {
			selectFeeType(schedule.feeType);
		}

		if (schedule.productGroup != null&&!"".equals(schedule.productGroup)) {
			selectProductGroup(schedule.productGroup);
			waitLoading();
		}
		if (schedule.product != null&&!"".equals(schedule.product)) {
			selectProduct(schedule.product);
		}

		if ( schedule.startDate != null&&!"".equals(schedule.startDate)) {
			setEffectiveStartDate(schedule.startDate);
		}else {
			setEffectiveStartDate(DateFunctions.getDateAfterToday(-365));
		}

		if (schedule.endDate != null&&!"".equals(schedule.endDate)) {
			setEffectiveEndDate(schedule.endDate);
		}else {
			setEffectiveStartDate(DateFunctions.getDateAfterToday(-365));
		}

		if ( schedule.customerType != null&&!"".equals(schedule.customerType)) {
			selectCustomerType(schedule.customerType);
		}

		if (schedule.tranType != null&&!"".equals(schedule.tranType)) {
			selectTransactionType(schedule.tranType);
		}

		if ( schedule.accountCode != null&&!"".equals(schedule.accountCode)) {
			selectAccountCode(schedule.accountCode);
		}

		if (schedule.rate != null&&!"".equals(schedule.rate)) {
			setRate(schedule.rate);
		}
		clickOk();
	}
	
	public boolean checkScheduleDetailInfo(ScheduleData expectSchedule){
		boolean result = true;
		ScheduleData scheduleDetail = getDetailInfo();
		result &= MiscFunctions.compareResult("Schedule Id:", expectSchedule.scheduleId, scheduleDetail.scheduleId);
		result &= MiscFunctions.compareResult("Location:", expectSchedule.location, scheduleDetail.location);
		result &= MiscFunctions.compareResult("Tax Name:", expectSchedule.taxName, scheduleDetail.taxName);
		result &= MiscFunctions.compareResult("Product Category:", expectSchedule.productCategory, scheduleDetail.productCategory);
		result &= MiscFunctions.compareResult("Fee Type:", expectSchedule.feeType, scheduleDetail.feeType);
		if("List".equalsIgnoreCase(expectSchedule.productCategory)){
			result &= MiscFunctions.compareResult("Applicable Product Category:", expectSchedule.appPrdCategory, scheduleDetail.appPrdCategory);
		}else{
			result &= MiscFunctions.compareResult("Product Group:", expectSchedule.productGroup, scheduleDetail.productGroup);
		}
		result &= MiscFunctions.compareResult("Product:", expectSchedule.product, scheduleDetail.product);
		result &= MiscFunctions.compareResult("Effective Start Date:", expectSchedule.startDate, scheduleDetail.startDate);
		result &= MiscFunctions.compareResult("Effective End Date:", expectSchedule.endDate, scheduleDetail.endDate);
		if("Slip".equalsIgnoreCase(expectSchedule.productCategory)){
			result &= MiscFunctions.compareResult("Marina Rate Type:", expectSchedule.marinaRateType, scheduleDetail.marinaRateType);
		}
		result &= MiscFunctions.compareResult("Customer Type:", expectSchedule.customerType, scheduleDetail.customerType);
		if("Transaction Fee".equalsIgnoreCase(expectSchedule.feeType)){
			result &= MiscFunctions.compareResult("Transaction Type:", expectSchedule.tranType, scheduleDetail.tranType);
		}
		result &= MiscFunctions.compareResult("Account:", expectSchedule.accountCode, scheduleDetail.accountCode);
		result &= MiscFunctions.compareResult("Rate:", Double.parseDouble(expectSchedule.rate), Double.parseDouble(scheduleDetail.rate));
		return result;
	}
	
	private ScheduleData getDetailInfo(){
		ScheduleData schedule = new ScheduleData();
		schedule.scheduleId = this.getTaxSchID();
		schedule.location = this.getLocationName();
		schedule.taxName = getTaxName();
		schedule.productCategory = getProductCategory();
		schedule.feeType = getFeeType();
		if("List".equalsIgnoreCase(schedule.productCategory)){
			schedule.appPrdCategory = getApplicablePrdCategory();
		}else{
			schedule.productGroup = getProductGroup();
		}
		schedule.product = getProduct();
		schedule.startDate = getEffectiveStartDate();
		schedule.endDate = getEffectiveEndDate();
		if("Slip".equalsIgnoreCase(schedule.productCategory)){
			schedule.marinaRateType = getMarinaRateType();
		}
		schedule.customerType = getCustomerType();
		if("Transaction Fee".equalsIgnoreCase(schedule.feeType)){
			schedule.tranType = getTransactionType();
		}
		schedule.accountCode = getAccountCode();
		schedule.rate = getAccountRate();
		
		return schedule;
	}

	private String getDropdownListSelectedValue(String field) {
		return browser.getDropdownListValue(".id", new RegularExpression(field,false));
	}
	
	private String getFieldValue(String field) {
		return browser.getTextFieldValue(".id", new RegularExpression(field,false));
	}

	public String getTaxName(){
		return this.getDropdownListSelectedValue(prefix + "taxTypeID");
	}
	
	public String getProductCategory(){
		return this.getDropdownListSelectedValue(prefix + "productCategory");
	}
	
	public String getApplicablePrdCategory(){
		return this.getDropdownListSelectedValue(prefix + "applicablePrdCategory");
	}
	
	public String getFeeType(){
		return this.getDropdownListSelectedValue(prefix + "feeType");
	}
	
	public String getProductGroup(){
		return this.getDropdownListSelectedValue(prefix + "productGroupId");
	}
	
	public String getProduct(){
		return this.getDropdownListSelectedValue(prefix + "productId");
	}
	
	public String getEffectiveStartDate(){
		return this.getFieldValue(prefix + "effectiveStartDate_ForDisplay");
	}
	
	public String getEffectiveEndDate(){
		return this.getFieldValue(prefix + "effectiveEndDate_ForDisplay"); 
	}
	
	public String getMarinaRateType(){
		return this.getDropdownListSelectedValue(prefix + "marinaRateType");
	}
	
	public String getCustomerType(){
		return this.getDropdownListSelectedValue(prefix + "customerType");
	}
	
	public String getTransactionType(){
		return this.getDropdownListSelectedValue(prefix + "transactionType");
	}
	
	public String getAccountCode(){
		return this.getDropdownListSelectedValue(prefix + "accountID");
	}
	
	public String getAccountRate(){
		return this.getFieldValue(prefix + "rate:PERCENTAGE"); 
	}
	
	private String getLocationName() {
		RegularExpression rex = new RegularExpression(
				"Tax Schedule\\W*+Tax Schedule ID.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		String name = text.substring(
				text.indexOf("Location Name") + "Location Name".length(),
				text.length());
		return name;
	}
	
	public boolean isErrorMessageExists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id" , "NOTSET");
	}
	
	/**
	 * Get error message on the page
	 * @return
	 */
	public String getErrorMessage() {
		String errorMsg = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id" , "NOTSET");
		if(objs.length > 0){
			errorMsg = objs[0].text();
		}
		Browser.unregister(objs);
		return errorMsg;
	}
	
	public void selecttBlankForDropdownlist(String field){
		browser.selectDropdownList(".id", new RegularExpression(field,false), 0);
	}
	
	public void setAccountCodeAsBlank() {
		this.selecttBlankForDropdownlist(prefix + "accountID");
	}
	
	public void setFeeTypeAsBlank() {
		this.selecttBlankForDropdownlist(prefix + "feeType");
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void setTaxNameAsBlank() {
		this.selecttBlankForDropdownlist(prefix + "taxTypeID");
		ajax.waitLoading();
		this.waitLoading();
	}
}
