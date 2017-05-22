package com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrRAFeeScheduleDetailPage extends LicMgrFeeScheduleCommonPage {
	static private LicMgrRAFeeScheduleDetailPage _instance = null;

	protected LicMgrRAFeeScheduleDetailPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public LicMgrRAFeeScheduleDetailPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new LicMgrRAFeeScheduleDetailPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "finance.fee.detail.form");
	}
	
	protected Property[] productCategoryDropDownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "prd_grp_cat_id");
	}
	
	protected Property[] productGroupDropDownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "assignment_prodgroup");
	}
	
	protected Property[] productTextField() {
		return Property.toPropertyArray(".id", "s_pn");
	}
	
	protected Property[] effectiveDateTextField() {
		return Property.toPropertyArray(".id", "date_effective_ForDisplay");
	}
	
	protected Property[] fromLicenseYearDropDownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "license_year_from");
	}
	
	protected Property[] toLicenseYearDropDownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "license_year_to");
	}
	
	protected Property[] saleChannelDropDownList(){
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "conditions_channel");
	}
	
	protected Property[] locationClassDropDownList(){
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "location_class");
	}
	
	protected Property[] whenRAFeeEarnedRadioBtn(String value){
		return Property.toPropertyArray(".class", "Html.INPUT.RADIO", ".id", new RegularExpression("earnedoption" + value,false));
	}
	
	protected Property[] serviceRendoredRadioBtn(){
		return whenRAFeeEarnedRadioBtn("0");
	}
	
	protected Property[] orderConfirmedWillBeReveseredRadioBtn(){
		return whenRAFeeEarnedRadioBtn("1");
	}
	
	protected Property[] orderConfirmedWillNotBeReveseredRadioBtn(){
		return whenRAFeeEarnedRadioBtn("2");
	}
	
	protected Property[] perTransactionRadioBtn(){
		return Property.toPropertyArray(".class", "Html.INPUT.RADIO", ".value", "2");
	}
	
	protected Property[] perUnitRadioBtn(){
		return Property.toPropertyArray(".class", "Html.INPUT.RADIO", ".value", "1");
	}
	
	protected Property[] transactionTypeDropDownList(){
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "transaction_type");
	}
	
	protected Property[] accountCodeDropDownList(){
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "account_code");
	}
	
	protected Property[] applyRateRadioBtn(){
		return Property.concatPropertyArray(this.input("RADIO"),  ".id","feeunitoption");
	}
	
	protected Property[] rateTextField(){
		return Property.toPropertyArray(".id", "base_rate");
	}
	
	protected Property[] okBtn(){
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Ok", false));
	}
	
	protected Property[] cancelBtn(){
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Cancel",false));
	}
	
	protected Property[] applyBtn(){
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Apply",false));
	}
	
	public void selectProductGroup(String productGroup){
		browser.selectDropdownList(this.productGroupDropDownList(), productGroup);
	}
	
	public void setProduct(String product){
		browser.setTextField(this.productTextField(), product);
		if(!product.equalsIgnoreCase("All")) {
			Property[] p = Property.toPropertyArray(".className", "ac_results");
			browser.waitExists(p);
			browser.waitExists(OrmsConstants.PAGELOADING_SYNC_TIME);
			Browser.sleep(3);
			browser.clickGuiObject(".class", "Html.LI", ".text", new RegularExpression(product+"\\(.*", false), true);
			ajax.waitLoading();
		}
	}
	
	public void setEffectiveDate(String effecitve){
		browser.setTextField(this.effectiveDateTextField(), effecitve);
	}
	
	public void selectFromLicenseYear(String fromYear){
		browser.selectDropdownList(this.fromLicenseYearDropDownList(), fromYear);
	}
	
	public void selectToLicenseYear(String toYear){
		browser.selectDropdownList(this.toLicenseYearDropDownList(), toYear);
	}
	
	public void selectSaleChannel(String saleChannel){
		browser.selectDropdownList(this.saleChannelDropDownList(), saleChannel);
	}
	
	public void selectLocationClass(String locClass){
		browser.selectDropdownList(this.locationClassDropDownList(), locClass);
	}
	
	public void selectWhenRAFeeEarned(String value){
		browser.selectRadioButton(this.whenRAFeeEarnedRadioBtn(value), 0);
	}
	
	public void selectTransactionType(String transType){
		browser.selectDropdownList(this.transactionTypeDropDownList(), transType);
	}
	
	public void selectAccountCode(String account){
		browser.selectDropdownList(this.accountCodeDropDownList(), account);
	}
	
	public void clickOk(){
		browser.clickGuiObject(this.okBtn());
	}
	
	public void clickCancelBtn(){
		browser.clickGuiObject(this.cancelBtn());
	}
	
	public void clickApplyBtn(){
		browser.clickGuiObject(this.applyBtn());
	}
	
	public void setRate(String rate){
		browser.setTextField(this.rateTextField(), rate);
	}
	
	public void setUpRAFeeInfo(RaFeeScheduleData raFee){
		if(raFee.productGroup != null){
			this.selectProductGroup(raFee.productGroup);
		}
		if(raFee.product != null){
			this.setProduct(raFee.product);
		}
		if(raFee.effectDate != null){
			this.setEffectiveDate(raFee.effectDate);
		}
		if(raFee.fromLicenseYear != null){
			this.selectFromLicenseYear(raFee.fromLicenseYear);
			ajax.waitLoading();
			this.waitLoading();
		}
		if(raFee.toLicenseYear != null){
			this.selectToLicenseYear(raFee.toLicenseYear);
		}
		if(raFee.salesChannel != null){
			this.selectSaleChannel(raFee.salesChannel);
		}
		if(raFee.locationClass != null){
			this.selectLocationClass(raFee.locationClass);
		}
		if (raFee.raFeeOption != null && raFee.raFeeOption.length() > 0) {
			String value = "1";
			if (raFee.raFeeOption
					.equalsIgnoreCase("Order Confirmed (will be reversed due to non payment)")) {
				value = "2";
			} else if (raFee.raFeeOption
					.equalsIgnoreCase("Order Confirmed (will not be reversed due to non payment)")) {
				value = "3";
			} else if (raFee.raFeeOption.equalsIgnoreCase("Service Rendered")) {
				value = "1";
			}
			this.selectWhenRAFeeEarned(value);
		}
		if(raFee.tranType != null){
			this.selectTransactionType(raFee.tranType);
		}
		if(raFee.acctCode != null){
			this.selectAccountCode(raFee.acctCode);
		}
		if(raFee.rate != null){
			this.setRate(raFee.rate);
		}
	}
	
	public void setUpInfoAndClickOk(RaFeeScheduleData raFee){
		this.setUpRAFeeInfo(raFee);
		this.clickOk();
	}
	
	public void setUpInfoAndClickApply(RaFeeScheduleData raFee){
		this.setUpRAFeeInfo(raFee);
		this.clickApplyBtn();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void setUpInfoAndClickCancel(RaFeeScheduleData raFee){
		this.setUpRAFeeInfo(raFee);
		this.clickCancelBtn();
	}
	

	
	public String getProductCategory(){
		return browser.getDropdownListValue(this.productCategoryDropDownList(), 0);
	}
	
	public String getProductGroup(){
		return browser.getDropdownListValue(this.productGroupDropDownList(), 0);
	}
	
	public String getProduct(){
		return browser.getTextFieldValue(this.productTextField());
	}
	
	public String getEffecitveDate(){
		return browser.getTextFieldValue(this.effectiveDateTextField());
	}
	
	public String getFromLicenseYear(){
		return browser.getDropdownListValue(this.fromLicenseYearDropDownList(), 0);
	}
	
	public String getToLicenseYear(){
		return browser.getDropdownListValue(this.toLicenseYearDropDownList(), 0);
	}
	
	public String getSaleChannel(){
		return browser.getDropdownListValue(this.saleChannelDropDownList(), 0);
	}
	
	public String getLocationClass(){
		return browser.getDropdownListValue(this.locationClassDropDownList(), 0);
	}
	
	/**
	 * 
	 * @return selected RA Fee Earned option
	 */
	public String getSelectedEarnedOption() {
		IHtmlObject[] objs = browser.getRadioButton(".name", "earnedoption");
		String value = "";
		for (IHtmlObject o : objs) {
			if (((IRadioButton) o).isSelected()) {
				value = o.getProperty(".value").toString().trim();
				break;
			}
		}
		Browser.unregister(objs);
		if (value.equals("3"))
			return "Order Confirmed (will not be reversed due to non payment)";
		else if (value.equals("2"))
			return "Order Confirmed (will be reversed due to non payment)";
		else
			return "Service Rendered";
	}
	
	public String getTransactionType(){
		return browser.getDropdownListValue(this.transactionTypeDropDownList(), 0);
	}
	
	public String getAccountCode(){
		return browser.getDropdownListValue(this.accountCodeDropDownList(), 0);
	}
	
	public String getRate(){
		return browser.getTextFieldValue(this.rateTextField());
	}

	public String getApplyRate(){
		String value = "";
		IHtmlObject[] objs = browser.getRadioButton(this.applyRateRadioBtn());
		for(int i=0; i<objs.length; i++){
			if (((IRadioButton) objs[i]).isSelected()) {
				value = objs[i].getProperty(".value").toString().trim();
				break;
			}
		}
		Browser.unregister(objs);
		String applyRate = "";
		if (value.equals("2"))
			applyRate = "Per Transaction";
		else if (value.equals("2"))
			applyRate = "Per Unit";
		return applyRate;
	}
	
	public RaFeeScheduleData getRaFeeInfo(){
		RaFeeScheduleData raFee = new RaFeeScheduleData();
		raFee.productCategory = this.getProductCategory();
		raFee.productGroup = this.getProductGroup();
		raFee.product = this.getProduct();
		raFee.effectDate = this.getEffecitveDate();
		raFee.fromLicenseYear = this.getFromLicenseYear();
		raFee.toLicenseYear = this.getToLicenseYear();
		raFee.salesChannel = this.getSaleChannel();
		raFee.locationClass = this.getLocationClass();
		raFee.raFeeOption = this.getSelectedEarnedOption();
		raFee.tranType = this.getTransactionType();
		raFee.acctCode = this.getAccountCode();
		raFee.applyRate = this.getApplyRate();
		raFee.rate = this.getRate();
		return raFee;
	}
	
	public boolean compareRaFeeInfo(RaFeeScheduleData expRaFee){
		boolean passed = true;
		RaFeeScheduleData actRaFee = new RaFeeScheduleData();
		actRaFee = this.getRaFeeInfo();
		passed &= MiscFunctions.compareResult("Product Category:", expRaFee.productCategory, actRaFee.productCategory);
		passed &= MiscFunctions.compareResult("Product Group:", expRaFee.productGroup, actRaFee.productGroup);
		passed &= MiscFunctions.compareResult("Product:", expRaFee.product, actRaFee.product);
		passed &= MiscFunctions.compareResult("Effective date", expRaFee.effectDate, actRaFee.effectDate);
		passed &= MiscFunctions.compareResult("From license year", expRaFee.fromLicenseYear, actRaFee.fromLicenseYear);
		passed &= MiscFunctions.compareResult("To license year", expRaFee.toLicenseYear, actRaFee.toLicenseYear);
		passed &= MiscFunctions.compareResult("Sale Channel", expRaFee.salesChannel, actRaFee.salesChannel);
		passed &= MiscFunctions.compareResult("Location class", expRaFee.locationClass, actRaFee.locationClass);
		passed &= MiscFunctions.compareResult("When ra fee earned", expRaFee.raFeeOption, actRaFee.raFeeOption);
		passed &= MiscFunctions.compareResult("Transaction type", expRaFee.tranType, actRaFee.tranType);
		passed &= MiscFunctions.compareResult("Account code", expRaFee.acctCode, actRaFee.acctCode);
		passed &= MiscFunctions.compareResult("Apply rate", expRaFee.applyRate, actRaFee.applyRate);
		passed &= MiscFunctions.compareResult("Base rate", expRaFee.rate, actRaFee.rate);
		return passed;
	}
	
	public String getRaFeeScheduleId(){
		String itemTitle = "RA Fee Schedule ID";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^" + itemTitle +  ".*", false));
		String content = objs[0].text().replace(itemTitle, "");
		Browser.unregister(objs);
		return content;
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
	
	public List<String> getAssignProdGroupElements(){
		return browser.getDropdownElements(this.productGroupDropDownList());
	}
	
	public List<String> getTransactionTypeElements(){
		return browser.getDropdownElements(this.transactionTypeDropDownList());
	}
}
