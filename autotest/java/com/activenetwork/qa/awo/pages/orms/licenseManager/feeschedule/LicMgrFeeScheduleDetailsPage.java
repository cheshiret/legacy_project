package com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * This page is the fee schedule detail page in license manager
 * @author Phoebe
 */
public class LicMgrFeeScheduleDetailsPage extends LicMgrFeeScheduleCommonPage {
	static private LicMgrFeeScheduleDetailsPage _instance = null;

	protected LicMgrFeeScheduleDetailsPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public LicMgrFeeScheduleDetailsPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new LicMgrFeeScheduleDetailsPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "finance.fee.detail.form");
	}
	
	private String prefix = "(ActivityFeeScheduleView|ActivityTransactionFeeScheduleView|VendorFeeScheduleView)-\\d+\\.";
	
	protected Property[] productCategoryDropDownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "prd_grp_cat_id");
	}
	
	protected Property[] feeTypeDropdownListProp() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "fee_type");
	}
	
	protected Property[] scheduleNameTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.TEXT", ".id", new RegularExpression(prefix+ "scheduleName", false));
	}
	
	protected Property[] productGropDropdownList() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "assignment_prodgroup");
	}
	
	protected Property[] productTextField() {
		return Property.toPropertyArray(".class", "Html.INPUT.TEXT", ".id", "s_pn");
	}
	
	protected Property[] effectiveStartDateTextField(){
		return Property.toPropertyArray(".class","Html.INPUT.TEXT", ".id", "date_effective_ForDisplay");
	}
	
	protected Property[] effectiveEndDateTextField(){
		return Property.toPropertyArray(".class","Html.INPUT.TEXT", ".id", "date_effective_end_ForDisplay");
	}
	
	protected Property[] fromLicenseYearDropDownList(){
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "license_year_from");
	}
	
	protected Property[] toLicenseYearDropDownList(){
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "license_year_to");
	}
	
	protected Property[] saleChannelDropDownList(){
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "conditions_channel");
	}
	
	protected Property[] locationClassDropDownList(){
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "location_class");
	}
	
	protected Property[] applicableTaxesCheckBox(){
		return Property.toPropertyArray(".class", "Html.INPUT.CHECKBOX", ".id", new RegularExpression(prefix+ "applicableTaxType_\\d+",false));
	}
	
	protected Property[] transactionTypeDropDownList(){
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", new RegularExpression(prefix+ "tranType",false));
	}
	
	protected Property[] perUnitRadioButton(){
		return Property.toPropertyArray(".class", "Html.INPUT.RADIO",  ".id", new RegularExpression(prefix+ "unitType0", false));
	}
	
	protected Property[] rateTextField(){
		return Property.toPropertyArray(".class","Html.INPUT.TEXT", ".id",  new RegularExpression(prefix+ "(fixedAmount|tranRate|vendorFee):CURRENCY_INPUT", false));
	}
	
	protected Property[] stateTransFeeTextField(){                                       
		return Property.toPropertyArray(".class","Html.INPUT.TEXT", ".id",  new RegularExpression(prefix+ "stateTransFee:CURRENCY_INPUT", false));
	}
	
	protected Property[] stateVendorFeeTextField(){
		return Property.toPropertyArray(".class","Html.INPUT.TEXT", ".id",  new RegularExpression(prefix+ "stateVendorFee:CURRENCY_INPUT", false));
	}
	
	protected Property[] splitByPercentRadioButton(){
		return Property.toPropertyArray(".class", "Html.INPUT.RADIO",  ".id", new RegularExpression(prefix+ "feeSchdSplitType0", false));
	}
	
	protected Property[] splitByAmountRadioButton(){
		return Property.toPropertyArray(".class", "Html.INPUT.RADIO",  ".id", new RegularExpression(prefix+ "feeSchdSplitType1", false));
	}
	
	protected Property[] slipByNumRevenueAccountsDropdownList(){
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", new RegularExpression(prefix+ "feeSchdAcctSplitNum", false));
	}
	
	protected Property[] accountDropDownList(){                        
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", new RegularExpression("FeeScheduleSplitView-\\d+\\.account", false));
	}
	
	protected Property[] vendorFeeAccountDropDownList(){                        
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", new RegularExpression("account_code", false));
	}
	
	protected Property[] accountAmountTextField(){
		return Property.toPropertyArray(".class", "Html.INPUT.TEXT", ".id",  new RegularExpression("FeeScheduleSplitView-\\d+\\.value:CURRENCY_INPUT", false));
	}
	
	protected Property[] okBtn(){
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Ok", false));
	}
	
	protected Property[] cancelBtn(){
		return Property.concatPropertyArray(this.a(), ".text","Cancel");
	}
	
	protected Property[] applyBtn(){
		return Property.concatPropertyArray(this.a(), ".text", "Apply");
	}
	
	public void setScheduleName(String name){
		browser.setTextField(this.scheduleNameTextField(), name);
	}
	
	public void selectProductGroup(String productGroup){
		browser.selectDropdownList(this.productGropDropdownList(), productGroup, 0);
	}
	
	public void setProduct(String product){
		String content = "";
		int i=0;//try three times
		do{
			browser.setTextField(".id", new RegularExpression("s_pn", false), StringUtil.EMPTY);
			browser.setTextField(".id", new RegularExpression("s_pn", false), product, true);
			if(!product.equalsIgnoreCase("All")) {
				Property[] p = Property.toPropertyArray(".className", "ac_results");
				browser.waitExists(p);
				browser.waitExists(OrmsConstants.PAGELOADING_SYNC_TIME);
				Browser.sleep(3);
				browser.clickGuiObject(".class", "Html.LI", ".text", new RegularExpression(product+"\\(.*", false), true);
				ajax.waitLoading();
				content = browser.getTextFieldValue(".id", new RegularExpression("s_pn", false));
				i++;
			}
		}while(i<3&&!content.matches(product+ "\\(\\d+\\)"));
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
	
	public void selectFromLicenseYear(String fromYear){
		browser.selectDropdownList(this.fromLicenseYearDropDownList(), fromYear);
	}
	
	public void selectToLicenseYear(String toYear){
		browser.selectDropdownList(this.toLicenseYearDropDownList(), toYear);
	}
	
	public boolean isToLicenseYearExist(){
		return browser.checkHtmlObjectDisplayed(this.toLicenseYearDropDownList());
	}
	
	public void selectSalesChannel(String saleChannel){
		browser.selectDropdownList(this.saleChannelDropDownList(), saleChannel);
	}
	
	public void selectLocationClass(String locationClass){
		browser.selectDropdownList(this.locationClassDropDownList(), locationClass);
	}

	/**
	 * The taxType maybe:Sales Tax, Restaurant 7%, Group Meals 7%
	 * @param taxType
	 */
	public void setApplicableTax(String taxType){
		IHtmlObject[] spans = browser.getHtmlObject(".class", "Html.SPAN", ".text", taxType);
		browser.selectCheckBox(applicableTaxesCheckBox(), 0, spans[0]);
		Browser.unregister(spans);
	}
	
	public void setApplicableTaxes(List<String> taxTypes){
		for(String taxType:taxTypes){
			this.setApplicableTax(taxType);
		}
	}
	
	public void unSelectAllTaxTypes(){
		IHtmlObject[] objs =  browser.getCheckBox(this.applicableTaxesCheckBox());
		for(int i=0; i<objs.length; i++){
			if(((ICheckBox)objs[i]).isSelected()){
				browser.unSelectCheckBox(this.applicableTaxesCheckBox(), i);
			}
		}
		Browser.unregister(objs);
	}
	
	public void selectTransactionType(String transType){
		browser.selectDropdownList(this.transactionTypeDropDownList(), transType, 0);
	}
	
	public void selectPerUnit(){
		browser.selectRadioButton(this.perUnitRadioButton(), 0);
	}
	
	public void setRateAmount(String rate){
		browser.setTextField(this.rateTextField(), rate);
	}
	
	public void setStateTransFee(String fee){
		browser.setTextField(this.stateTransFeeTextField(), fee);
	}
	
	public void setStateVendorFee(String fee){
		browser.setTextField(this.stateVendorFeeTextField(), fee);
	}
	
	public void selectSplitByPercent(){
		browser.selectRadioButton(this.splitByPercentRadioButton(), 0);
	}
	
	public void selectSplitByAmount(){
		browser.selectRadioButton(this.splitByAmountRadioButton(), 0);
	}
	
	public void selectSplitIntNumOfAccount(String num){
		browser.selectDropdownList(this.slipByNumRevenueAccountsDropdownList(), num);
	}
	
	public void selectAccount(String account, int index){
		browser.selectDropdownList(this.accountDropDownList(), account, index);
	}
	
	public void selectVendorFeeAccount(String account){
		browser.selectDropdownList(this.vendorFeeAccountDropDownList(), account);
	}
	
	public void selectVendorFeeAccount(int index){
		browser.selectDropdownList(this.vendorFeeAccountDropDownList(), index);
	}
	
	public void setPercentOrAmountForAccount(String percentOrAmount, int index){
		browser.setTextField(this.accountAmountTextField(), percentOrAmount, index);
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
	
	public void moveFocus(){
		browser.clickGuiObject(".class", "Html.TD", ".text", "Fee");
	}
	
	public void setUpFeeScheduleInfo(FeeScheduleData schedule){
		if(schedule.scheduleName != null && schedule.feeType.equalsIgnoreCase("Activity Fee")){
			this.setScheduleName(schedule.scheduleName);
		}
		if(schedule.productGroup != null){
			this.selectProductGroup(schedule.productGroup);
			ajax.waitLoading();
			this.waitLoading();
		}
		if(schedule.product != null){
			this.setProduct(schedule.product);
		}
		if(schedule.feeType.equalsIgnoreCase("Transaction Fee")){
			if(schedule.effectDate != null){
				this.setEffectiveStartDate(schedule.effectDate);
			}
			if(schedule.tranType != null){
				this.selectTransactionType(schedule.tranType);
			}
		}else{
			if(schedule.effectStartDate != null){
				this.setEffectiveStartDate(schedule.effectStartDate);
			}
			if(schedule.effectEndDate != null){
				this.setEffectiveEndDate(schedule.effectEndDate);
			}
		}
		if(schedule.fromLicenseYear != null){
			this.selectFromLicenseYear(schedule.fromLicenseYear);
			ajax.waitLoading();
			this.waitLoading();
		}
		if(isToLicenseYearExist()&&schedule.toLicenseYear != null){
			this.selectToLicenseYear(schedule.toLicenseYear);
		}
		if(schedule.salesChannel != null){
			this.selectSalesChannel(schedule.salesChannel);
		}
		if(schedule.locationClass != null){
			this.selectLocationClass(schedule.locationClass);
		}
		if(schedule.applicableTaxes.size() >0){
			this.unSelectAllTaxTypes();
			this.setApplicableTaxes(schedule.applicableTaxes);
		}
		if(schedule.applyRate != null && !schedule.feeType.equalsIgnoreCase("Vendor Fee")){
			if(schedule.applyRate.equalsIgnoreCase("Per Unit")){
				this.selectPerUnit();
			}
		}
		if(schedule.rateAmount != null){
			this.setRateAmount(schedule.rateAmount);
			moveFocus();
			ajax.waitLoading();
			this.waitLoading();
		}
		if(schedule.feeType.equalsIgnoreCase("Transaction Fee")){
			if(schedule.stateTransFee != null){
				this.setStateTransFee(schedule.stateTransFee);
			}
		}
		if(schedule.feeType.equalsIgnoreCase("Vendor Fee")){
			if(schedule.stateVendorFee != null){
				this.setStateVendorFee(schedule.stateVendorFee);
			}
			if(!StringUtil.isEmpty(schedule.acctCode)) {
				this.selectVendorFeeAccount(schedule.acctCode);
			} else {
				this.selectVendorFeeAccount(1);
			}
		}else{
			if(schedule.splitRateBy != null){
				if(schedule.splitRateBy.equalsIgnoreCase("Percent")){
					this.selectSplitByPercent();
				}
				if(schedule.splitRateBy.equalsIgnoreCase("Amount")){
					this.selectSplitByAmount();
				}
				ajax.waitLoading();
				this.waitLoading();
			}
			if(schedule.splitIntoNum != null){
				//Change the number to other, so that it can be changed when select it 
				this.selectSplitIntNumOfAccount("1");
				ajax.waitLoading();
				this.waitLoading();
				
				this.selectSplitIntNumOfAccount(schedule.splitIntoNum);
				ajax.waitLoading();
				this.waitLoading();
			}
			if(schedule.accounts.size() > 0){
				for(int i=0; i<schedule.accounts.size(); i++){
					this.selectAccount(schedule.accounts.get(i), i);
					if(i < (schedule.accounts.size() -1)){
						this.setPercentOrAmountForAccount(schedule.percentOrAmountForEachAccount.get(i), i);
						this.moveFocus();
						ajax.waitLoading();
						this.waitLoading();
					}
				}
			}
		}
	}
	
	public void setUpFeeSchesuleInfoAndClickCancel(FeeScheduleData schedule){
		this.setUpFeeScheduleInfo(schedule);
		this.clickCancel();
	}
	
	public void setUpFeeScheduleInfoAndClickOk(FeeScheduleData schedule){
		this.setUpFeeScheduleInfo(schedule);
		this.clickOk();
	}
	
	public void setUpFeeScheduleInfoAndClickApply(FeeScheduleData schedule){
		this.setUpFeeScheduleInfo(schedule);
		this.clickApply();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void setupFeeScheduleInfoAndClickCancel(FeeScheduleData schedule){
		this.setUpFeeScheduleInfo(schedule);
		this.clickCancel();
	}
	
	public String getFeeScheduleId(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^Fee Schedule ID.*", false));
		if(objs.length < 1){
			throw new ErrorOnPageException("Can not find the span object for fee scheduel id!");
		}
		String id = objs[0].text().replace("Fee Schedule ID", "");
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
	
	public String getProdcutCategory(){
		return browser.getDropdownListValue(this.productCategoryDropDownList(), 0);
	}
	
	public String getFeeType(){
		return browser.getDropdownListValue(this.feeTypeDropdownListProp(), 0);
	}
	
	public String getScheduleName(){
		return browser.getTextFieldValue(this.scheduleNameTextField(), 0);
	}
	
	public String getProductGroup(){
		return browser.getDropdownListValue(this.productGropDropdownList(), 0);
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
	
	public String getFromLicenseYear(){
		return browser.getDropdownListValue(this.fromLicenseYearDropDownList(), 0);
	}
	
	public String getToLicenseYear(){
		return browser.getDropdownListValue(this.toLicenseYearDropDownList(), 0);
	}
	
	public String getSaleChannel(){
		return browser.getDropdownListValue(this.saleChannelDropDownList(), 0);
	}
	
	public String getTransactionType(){
		return browser.getDropdownListValue(this.transactionTypeDropDownList(), 0);
	}
	
	public String getLocationClass(){
		return browser.getDropdownListValue(this.locationClassDropDownList(), 0);
	}
	
	public String getApplyRate(){
		String applyRate = "";
		if(browser.isRadioButtonSelected(this.perUnitRadioButton())){
			applyRate = "Per Unit"; 
		}
		return applyRate;
	}
	
	public String getRate(){
		return browser.getTextFieldValue(this.rateTextField());
	}
	
	public String getStateVendorFee(){
		return browser.getTextFieldValue(this.stateVendorFeeTextField());
	}
	
	public String getStateTransFee(){
		return browser.getTextFieldValue(this.stateTransFeeTextField());
	}
	
	public String splitByType(){
		String splitBy = "";
		if(browser.isRadioButtonSelected(this.splitByPercentRadioButton())){
			splitBy = "Percent";
		}
		if(browser.isRadioButtonSelected(this.splitByAmountRadioButton())){
			splitBy = "Amount";
		}
		return splitBy;
	}
	
	public String getSplitIntoNum(){
		return browser.getDropdownListValue(this.slipByNumRevenueAccountsDropdownList(), 0);
	}
	
	public String getVendorFeeAcount(){
		return browser.getDropdownListValue(this.vendorFeeAccountDropDownList(), 0);
	}
	
	public List<String> getAccounts(int num){
		List<String> accounts = new ArrayList<String>();
		for(int i=0; i< num; i++){
			accounts.add(browser.getDropdownListValue(this.accountDropDownList(), i));
		}
		return accounts;
	}
	
	public List<String> getPercentOrAmountForEachAccount(int num){
		List<String> percentOrAmount = new ArrayList<String>();
		for(int i=0; i< num; i++){
			percentOrAmount.add(browser.getTextFieldValue(this.accountAmountTextField(), i));
		}
		return percentOrAmount;
	}
	
	public FeeScheduleData getFeeScheduleInfo(){
		FeeScheduleData schedule = new FeeScheduleData();
		schedule.productCategory = this.getProdcutCategory();
		schedule.feeType = this.getFeeType();
		if(schedule.feeType.equalsIgnoreCase("Activity Fee")){
			schedule.scheduleName = this.getScheduleName();
		}
		schedule.productGroup = this.getProductGroup();
		schedule.product = this.getProduct();
		if(schedule.feeType.equalsIgnoreCase("Transaction Fee")){
			schedule.effectDate = this.getEffectiveStartDate();
			schedule.tranType = this.getTransactionType();
		}else{
			schedule.effectStartDate = this.getEffectiveStartDate();
			schedule.effectEndDate = this.getEffectiveEndDate();
		}
		schedule.fromLicenseYear = this.getFromLicenseYear();
		schedule.toLicenseYear = this.getToLicenseYear();
		schedule.salesChannel = this.getSaleChannel();
		schedule.locationClass = this.getLocationClass();
		if(schedule.feeType.equalsIgnoreCase("Vendor Fee")){
			schedule.acctCode = this.getVendorFeeAcount();
			schedule.rateAmount = this.getRate();
			schedule.stateVendorFee = this.getStateVendorFee();
		}else{
			schedule.applyRate = this.getApplyRate();
			schedule.rateAmount = this.getRate();
			if(schedule.feeType.equalsIgnoreCase("Transaction Fee")){
				schedule.stateTransFee = this.getStateTransFee();
			}
			schedule.splitRateBy = this.splitByType();
			schedule.splitIntoNum = this.getSplitIntoNum();
			schedule.accounts = this.getAccounts(Integer.parseInt(schedule.splitIntoNum));
			schedule.percentOrAmountForEachAccount = this.getPercentOrAmountForEachAccount(Integer.parseInt(schedule.splitIntoNum));
		}
		return schedule;
	}
	
	public boolean compareScheduleInfo(FeeScheduleData expSchedule){
		boolean passed = true;
		FeeScheduleData actSchedule = this.getFeeScheduleInfo();
		passed &= MiscFunctions.compareResult("Product Category:", expSchedule.productCategory, actSchedule.productCategory);
		passed &= MiscFunctions.compareResult("Fee Type:", expSchedule.feeType, actSchedule.feeType);
		if(expSchedule.feeType.equalsIgnoreCase("Activity Fee")){
			passed &= MiscFunctions.compareResult("Schedule Name:", expSchedule.scheduleName, actSchedule.scheduleName);
		}
		passed &= MiscFunctions.compareResult("Product Group:", expSchedule.productGroup, actSchedule.productGroup);
		passed &= MiscFunctions.compareResult("Product:", expSchedule.product, actSchedule.product.split("\\(")[0].trim());
		if(expSchedule.feeType.equalsIgnoreCase("Transaction Fee")){
			passed &= MiscFunctions.compareResult("Effective date:", expSchedule.effectDate, actSchedule.effectDate);
			passed &= MiscFunctions.compareResult("Transaction Type:", expSchedule.tranType, actSchedule.tranType);
			passed &= MiscFunctions.compareResult("State Trans Fee:", expSchedule.stateTransFee, actSchedule.stateTransFee);
		}else{
			passed &= MiscFunctions.compareResult("Effective Start date:", expSchedule.effectStartDate, actSchedule.effectStartDate);
			passed &= MiscFunctions.compareResult("Effective End date:", expSchedule.effectEndDate, actSchedule.effectEndDate);
		}
		passed &= MiscFunctions.compareResult("From License Year:", expSchedule.fromLicenseYear, actSchedule.fromLicenseYear);
		passed &= MiscFunctions.compareResult("To License Year:", expSchedule.toLicenseYear, actSchedule.toLicenseYear);
		passed &= MiscFunctions.compareResult("Sale Channel:", expSchedule.salesChannel, actSchedule.salesChannel);
		passed &= MiscFunctions.compareResult("Location Class:", expSchedule.locationClass, actSchedule.locationClass);
		if(expSchedule.feeType.equalsIgnoreCase("Vendor Fee")){
			passed &= MiscFunctions.compareResult("Account code:", expSchedule.acctCode, actSchedule.acctCode);
			passed &= MiscFunctions.compareResult("Rate:", expSchedule.rateAmount, actSchedule.rateAmount);
			passed &= MiscFunctions.compareResult("State Vendor Fee:", expSchedule.stateVendorFee, actSchedule.stateVendorFee);
		}else{
			passed &= MiscFunctions.compareResult("Apply Rate:", expSchedule.applyRate, actSchedule.applyRate);
			passed &= MiscFunctions.compareResult("Rate:", expSchedule.rateAmount, actSchedule.rateAmount);
			passed &= MiscFunctions.compareResult("Split By:", expSchedule.splitRateBy, actSchedule.splitRateBy);
			passed &= MiscFunctions.compareResult("Split Into Num:", expSchedule.splitIntoNum, actSchedule.splitIntoNum);
			if(! (expSchedule.accounts.containsAll(actSchedule.accounts)&&actSchedule.accounts.containsAll(expSchedule.accounts)) ){
				logger.info("Accounts are not correct, expect:" + expSchedule.accounts.toString()+ ",but actually is:" + actSchedule.accounts.toString());
				passed = false;
			}else{
				logger.info("Accounts are correct!");
			}
			if(! (expSchedule.percentOrAmountForEachAccount.containsAll(actSchedule.percentOrAmountForEachAccount)&&actSchedule.percentOrAmountForEachAccount.containsAll(expSchedule.percentOrAmountForEachAccount))){
				logger.info("Percent or amount are not correct, expect:" + expSchedule.percentOrAmountForEachAccount.toString()+ ",but actually is:" + actSchedule.percentOrAmountForEachAccount.toString());
				passed = false;
			}else{
				logger.info("Percent or amount are correct!");
			}
		}
		return passed;
	}
	
}
