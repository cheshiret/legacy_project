package com.activenetwork.qa.awo.pages.orms.licenseManager.tax;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * This is the tax schedule schedule search page in license manager
 * How to go to this page:Go to schedule search page( Select "Tax" for the Financials dropdown list at the right top menu of license manager ) and then
 * click "Tax Schedules" label, and click "Add New" button
 * @author Phoebe
 */
public class LicMgrTaxScheduleDetailPage extends LicMgrCommonTopMenuPage {
	static private LicMgrTaxScheduleDetailPage _instance = null;

	protected LicMgrTaxScheduleDetailPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public LicMgrTaxScheduleDetailPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new LicMgrTaxScheduleDetailPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.DIV", ".id", "tax.setup.content");
	}
	
	protected Property[] taxNameDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxScheduleView.taxTypeID");
	}
	
	protected Property[] productCategoryDropdownList(){
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxScheduleView.productCategory");
	}

	protected Property[] feeTypeDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxScheduleView.feeType");
	}
	
	protected Property[] productGroupDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxScheduleView.productGroupId");
	}
	
	protected Property[] productTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.TEXT", ".id", "TaxScheduleView.productName");
	}
	
	protected Property[] effectiveStartDateTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.TEXT", ".id", "TaxScheduleView.effectiveStartDate_ForDisplay");
	}
	
	protected Property[] effectiveEndDateTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.TEXT", ".id", "TaxScheduleView.effectiveEndDate_ForDisplay");
	}
	
	protected Property[] transactionTypeDropdownList(){
		return Property.toPropertyArray(".class", "Html.INPUT.TEXT", ".id", "TaxScheduleView.transactionType");
	}
	
	protected Property[] accountCodeDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "TaxScheduleView.accountID");
	}
	
	protected Property[] rateTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.TEXT", ".id", "TaxScheduleView.rate:PERCENTAGE");
	}
	
	protected Property[] applyBtn() {
		return Property.concatPropertyArray(this.a(), ".text", "Apply");
	}
	
	protected Property[] cancelBtn() {
		return Property.concatPropertyArray(this.a(), ".text", "Cancel");
	}
	
	protected Property[] okBtn() {
		return Property.concatPropertyArray(this.a(), ".text", "OK");
	}
	
	public void selectTaxName(String name){
		browser.selectDropdownList(this.taxNameDropdownList(), name);
	}
	
	public void selectTaxNameAndWaitRefresh(String name){
		this.selectTaxName(name);
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void selectFeeType(String type){
		if(StringUtil.notEmpty(type)){
			browser.selectDropdownList(this.feeTypeDropdownList(), type);
		}else{
			browser.selectDropdownList(this.feeTypeDropdownList(), 0);
		}
	}
	
	public void selectFeeTypeAndWaitRefresh(String type){
		this.selectFeeType(type);
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void selectProductGroup(String group){
		browser.selectDropdownList(this.productGroupDropdownList(), group);
	}
	
	public void moveFocusOut(){
		browser.clickGuiObject(".class", "Html.TD", ".text", "Tax Name");
	}
	
	public void setProduct(String product){
		String content = "";
		int i=0;//try three times
		do{
			browser.setTextField(this.productTextField(), StringUtil.EMPTY);
			this.moveFocusOut();
			browser.setTextField(this.productTextField(), product);
			if(!product.equalsIgnoreCase("All")) {
				Browser.sleep(3);
				Property[] p = Property.toPropertyArray(".className", "ac_even");
				browser.waitExists(p);
				IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.LI", ".text", new RegularExpression(product+".*\\(.*", false));
				objs[objs.length - 1].click();
				ajax.waitLoading();
				content = browser.getTextFieldValue(this.productTextField());
				i++;
				Browser.unregister(objs);
			}
		}while(i<5&&!content.matches(product+ "\\(\\d+\\)"));
		if(!content.matches(product+ "\\(\\d+\\)")){
			throw new ErrorOnPageException("The product is not set properly after three times tries!");
		}
	}
	
	public void setEffectiveStartDate(String startDate){
		browser.setCalendarField(this.effectiveStartDateTextField(), startDate);
	}
	
	public void setEffectiveEndDate(String endDate){
		browser.setCalendarField(this.effectiveEndDateTextField(), endDate);
	}
	
	public void selectTransactionType(String transType){
		browser.selectDropdownList(this.transactionTypeDropdownList(), transType);
	}
	
	public void selectAccountCode(String account){
		if(StringUtil.notEmpty(account)){
			browser.selectDropdownList(this.accountCodeDropdownList(), account);
		}else{
			browser.selectDropdownList(this.accountCodeDropdownList(), 0);
		}
	}
	
	public void setAccountRate(String rate){
		browser.setTextField(this.rateTextField(), rate);
	}
	
	public void clickOk(){
		browser.clickGuiObject(this.okBtn());
	}
	
	public void clickCancel(){
		browser.clickGuiObject(this.cancelBtn());
	}
	
	public void clickApply(){
		browser.clickGuiObject(this.applyBtn());
	}
	
	public void setUpTaxScheduelInfo(ScheduleData schedule){
		if(schedule.taxName != null){
			this.selectTaxName(schedule.taxName);
			ajax.waitLoading();
			this.waitLoading();
		}
		if(StringUtil.notEmpty(schedule.feeType)){
			this.selectFeeType(schedule.feeType);
			ajax.waitLoading();
			this.waitLoading();
		}
		if(StringUtil.notEmpty(schedule.productGroup)){
			this.selectProductGroup(schedule.productGroup);
			ajax.waitLoading();
			this.waitLoading();
		}
		if(StringUtil.notEmpty(schedule.product)){
			this.setProduct(schedule.product);
		}
		if(schedule.effectiveDate!=null){
			this.setEffectiveStartDate(schedule.effectiveDate);
		}
		if(schedule.effectiveEndDate != null){
			this.setEffectiveEndDate(schedule.effectiveEndDate);
		}
		if(schedule.feeType.equalsIgnoreCase("Transaction Fee")){
			this.selectTransactionType(schedule.tranType);
		}
		if(schedule.accountCode!=null){
			this.selectAccountCode(schedule.accountCode);
		}
		if(schedule.rate != null){
			this.setAccountRate(schedule.rate);
		}
	}
	
	public void setUpTaxScheduleAndClickApply(ScheduleData schedule){
		this.setUpTaxScheduelInfo(schedule);
		this.clickApply();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public String getTaxName(){
		return browser.getDropdownListValue(this.taxNameDropdownList(), 0);
	}
	
	public String getProductCategory(){
		return browser.getDropdownListValue(this.productGroupDropdownList(), 0);
	}
	
	public String getFeeType(){
		return browser.getDropdownListValue(this.feeTypeDropdownList(), 0);
	}
	
	public String getProductGroup(){
		return browser.getDropdownListValue(this.productGroupDropdownList(), 0);
	}
	
	public String getProduct(){
		return browser.getTextFieldValue(this.productTextField());
	}
	
	public String getEffectiveStartDate(){
		return browser.getTextFieldValue(this.effectiveStartDateTextField());
	}
	
	public String getEffectiveEndDate(){
		return browser.getTextFieldValue(this.effectiveEndDateTextField());
	}
	
	public String getAccountCode(){
		return browser.getDropdownListValue(this.accountCodeDropdownList(), 0);
	}
	
	public String getRateAmount(){
		return browser.getTextFieldValue(this.rateTextField());
	}
	
	public String getTransactionType(){
		return browser.getDropdownListValue(this.transactionTypeDropdownList(), 0);
	}
	
	public String getTaxScheduleId(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^Tax Schedule ID.*", false));
		if(objs.length < 1){
			throw new ErrorOnPageException("Can not find the span object for tax schedule id!");
		}
		String id = objs[0].text().replace("Tax Schedule ID", "");
		Browser.unregister(objs);
		return id;
	}
	
	public String getErrorMsg(){
		String msg = "";
		if(browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "message msgerror")){
			msg = browser.getObjectText(".class", "Html.DIV", ".className", "message msgerror");
		}else if(browser.checkHtmlObjectExists(".class", "Html.SPAN", ".className", "message msgerror")){
			msg = browser.getObjectText(".class", "Html.SPAN", ".className", "message msgerror");
		}
		return msg;
    }
	
	public boolean compareScheduleDetailInfo(ScheduleData expSchedule){
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Tax Name:", expSchedule.taxName, this.getTaxName());
		passed &= MiscFunctions.compareResult("Product Category:", expSchedule.productCategory, this.getProductCategory());
		passed &= MiscFunctions.compareResult("Fee Type:", expSchedule.feeType, this.getFeeType());
		passed &= MiscFunctions.compareResult("Product Group:", expSchedule.productGroup, this.getProductGroup());
		passed &= MiscFunctions.compareResult("Product:", expSchedule.product, this.getProduct());
		passed &= MiscFunctions.compareResult("Effective Start date:", expSchedule.effectiveDate, this.getEffectiveStartDate());
		passed &= MiscFunctions.compareResult("Effective End date:", expSchedule.effectiveEndDate, this.getEffectiveEndDate());
		passed &= MiscFunctions.compareResult("Account Code:", expSchedule.accountCode, this.getAccountCode());
		passed &= MiscFunctions.compareResult("Account Rate:", expSchedule.rate, this.getRateAmount());
		return passed;
	}
	
	public void verifyScheduleInfo(ScheduleData expSchedule){
		if(this.compareScheduleDetailInfo(expSchedule)){
			throw new ErrorOnPageException("Tax schedule detail is not correct, please check the log above!");
		}
		logger.info("New added tax schedule information is correct!");
	}
}