package com.activenetwork.qa.awo.pages.orms.licenseManager.common;


import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#: AUTO-667
 * 
 * @author qchen
 * @Date  Jul 21, 2011
 */
public class LicMgrEditProductPricingWidget extends LicMgrProductPricingCommonWidget {
	
	private static LicMgrEditProductPricingWidget _instance = null;
	
	protected LicMgrEditProductPricingWidget(){
		super("Edit Product Pricing");
	}
	
	public static LicMgrEditProductPricingWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrEditProductPricingWidget();
		}
		
		return _instance;
	}
	
	private final RegularExpression pricingRegex = new RegularExpression("ProductPricingView-\\d+\\.id", false);
	private final RegularExpression purchaseTypeRegex = new RegularExpression("ProductPricingView-\\d+\\.purchaseType", false);
	
	public String getPricingID() {
		return browser.getTextFieldValue(".id", pricingRegex);
	}
	
	public String getLocationClass() {
		return browser.getDropdownListValue(".id", locClassRegx, 0);
	}
	
	public String getLicenseYearFrom() {
		return browser.getDropdownListValue(".id", licYearFromRegx, 0);
	}
	
	public String getLicenseYearTo() {
		return browser.getDropdownListValue(".id", licYearToRegx, 0);
	}
	
	public boolean isPurchaseTypeExists() {
		return browser.checkHtmlObjectExists(".id", purchaseTypeRegex);
	}
	
	public String getPurchaseType() {
		return browser.getDropdownListValue(".id", purchaseTypeRegex, 0);
	}
	
	public void selectStatus(String status) {
		if(null != status && status.length() > 0) {
			browser.selectDropdownList(".id", statusRegx, status, true);
		} else {
			browser.selectDropdownList(".id", statusRegx, 0, true);
		}
	}
	
	public String getEffectiveFromDate() {
		return browser.getTextFieldValue(".id", effectiveFromDateRegx);
	}
	
	public String getEffectiveToDate() {
		return browser.getTextFieldValue(".id", effectiveToDateRegx);
	}
	
	public String getVendorFee() {
		return browser.getTextFieldValue(".id", new RegularExpression("ProductPricingView-\\d+\\.vendorFee", false));
	}
	
	public String getStateTransFee() {
		return browser.getTextFieldValue(".id", new RegularExpression("ProductPricingView-\\d+\\.stateTransFee", false));
	}
	
	public String getStateFee() {
		return browser.getTextFieldValue(".id", stateFeeRegx);
	}
	
	/**
	 * Get the state/transaction fee split by value
	 * @return "Percent" or "Amount"
	 */
	private String getFeeSplitBy(boolean isStateFee) {
		IHtmlObject[] topTables=browser.getTableTestObject(new Property[]{new Property(".text",new RegularExpression("^"+(isStateFee?"State":"Transaction")+" Fee.*", false))},getWidget()[0]);
		if(topTables==null || topTables.length<1){
			throw new ObjectNotFoundException("Can't get "+(isStateFee?"State":"Transaction")+" table");
		}
		IHtmlObject[] tables=browser.getTableTestObject(new Property[]{new Property(".text",new RegularExpression("^"+"Split By.*Amount", false))},topTables[0]);
		IHtmlTable table=(IHtmlTable)tables[0];
		String[] splitValues=table.getCellValue(0,(isStateFee?1:2)).replaceAll("\\s+", " ").split(" ");
		IHtmlObject objs[] = browser.getRadioButton(".id", isStateFee ? new RegularExpression("ProductPricingView-\\d+\\.feeSchdSplitType", false) : new RegularExpression("ProductPricingView-\\d+\\.transFeeSchdSplitType", false));
	
		if(objs.length !=2) {
			throw new ItemNotFoundException("Can't find any State/Transaction Fee Split By object.");
		}
		
		String splitBy = "";
		for(int i = 0; i < objs.length; i ++) {
			IRadioButton radio = (IRadioButton) objs[i];
			
			if(radio.isSelected()) {
				splitBy = splitValues[i];
				break;
			}
		}
		
		Browser.unregister(objs,tables);
		return splitBy;
	}
	
	public String getStateFeeSplitBy() {
		return this.getFeeSplitBy(true);
	}
	
	public String getTransactionFeeSplitBy() {
		return this.getFeeSplitBy(false);
	}
	
	public String getStateFeeSplitInto() {
		return browser.getDropdownListValue(".id", stateFeeSplitIntoRegx, 0);
	}
	
	private List<String[]> getFeeAccounts(boolean isStateFee) {
		IHtmlObject tableObjs[] = browser.getTableTestObject(".id", isStateFee?"FeeScheduleSplitGrid":"transFeeScheduleSplitGrid");
		if(tableObjs.length == 0) {
			throw new ItemNotFoundException("Can't find State/Transaction Fee Account table object.");
		}
		
		IHtmlObject dropdownListObjs[] = browser.getDropdownList(new Property[]{new Property(".id", accountRegex)}, tableObjs[0]);
		IHtmlObject textFieldObjs[] = browser.getTextField(new Property[]{new Property(".id", accountVelueRegex)}, tableObjs[0]);
		if(dropdownListObjs.length == 0 || textFieldObjs.length == 0) {
			throw new ItemNotFoundException("Can't find State/Transaction Fee Account drop down list/value text field objects.");
		}
		
		List<String[]> accounts = new ArrayList<String[]>();
		String[] account = null;
		for(int i = 0; i < dropdownListObjs.length; i ++) {
			account = new String[2];
			account[0] = ((ISelect)dropdownListObjs[i]).getSelectedText();
			account[1] = ((IText)textFieldObjs[i]).getText();
			accounts.add(account);
		}
		
		Browser.unregister(tableObjs);
		Browser.unregister(dropdownListObjs);
		Browser.unregister(textFieldObjs);
		return accounts;
	}
	
	public List<String[]> getStateFeeAccounts() {
		return this.getFeeAccounts(true);
	}
	
	public List<String[]> getTransactionFeeAccounts() {
		return this.getFeeAccounts(false);
	}
	
	public String getTransactionFee() {
		return browser.getTextFieldValue(".id", new RegularExpression("ProductPricingView-\\d+\\.transFee", false));
	}
	
	public String getTransactionFeeSplitInto() {
		return browser.getDropdownListValue(".id", transactionFeeSplitIntoRegex, 0);
	}
	
	private String getAddUpdateInfosByName(String attributeName) {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN", ".id", new RegularExpression("ProductPricingView-\\d+\\." + attributeName.replaceAll(" ", ""), false));
		String attributeValue = "";
		if(objs.length > 0) {
			String[] value=objs[0].getProperty(".text").toString().split(attributeName);
			if(value.length>1)
			attributeValue = value[1].trim();
		}
		
		Browser.unregister(objs);
		return attributeValue;
	}
	
	public String getCreateUser() {
		return this.getAddUpdateInfosByName("Create User");
	}
	
	public String getCreateLocation() {
		return this.getAddUpdateInfosByName("Create Location");
	}
	
	public String getCreateTime() {
		return this.getAddUpdateInfosByName("Create Time");
	}
	
	public String getLastUpdateUser() {
		return this.getAddUpdateInfosByName("Last Update User");
	}
	
	public String getLastUpdateLocation() {
		return this.getAddUpdateInfosByName("Last Update Location");
	}
	
	public String getLastUpdateTime() {
		return this.getAddUpdateInfosByName("Last Update Time");
	}
	
	/**
	 * Check the specific object whether could be edited
	 * @param idValueRegex
	 * @return
	 */
	private boolean checkObjectEditable(Object idValueRegex) {
		IHtmlObject objs[] = browser.getHtmlObject(".id", idValueRegex);
		boolean editable = true;
		if(objs.length > 0) {
			editable = !Boolean.parseBoolean(objs[0].getProperty("disabled"));
		}
		
		Browser.unregister(objs);
		return editable;
	}
	
	/**
	 * Check the product pricing id text field whether could be edited
	 * @return
	 */
	public boolean checkPricingIDEditable() {
		return this.checkObjectEditable(pricingRegex);
	}
	
	/**
	 * Check the location class drop down list object whether could be edited
	 * @return
	 */
	public boolean checkLocationClassEditable() {
		return this.checkObjectEditable(locClassRegx);
	}
	
	/**
	 * Check the purchase type drop down list object could be edited
	 * @return
	 */
	public boolean checkPurchaseTypeEditable() {
		return this.checkObjectEditable(purchaseTypeRegex);
	}
	
	public void editProductPricing(PricingInfo pricing) {
		if(!this.getStatus().equalsIgnoreCase(pricing.status)) {
			this.selectStatus(pricing.status);
		}
		if(!this.getLicenseYearFrom().equalsIgnoreCase(pricing.licYearFrom)) {
			this.selectLicenseYearFrom(pricing.licYearFrom);
			ajax.waitLoading();
		}
		if(!this.getLicenseYearTo().equalsIgnoreCase(pricing.licYearTo)) {
			this.selectLicenseYearTo(pricing.licYearTo);
		}
//		System.out.println(pricing.effectFrom);
//		System.out.println(pricing.effectTo);
		if(pricing.effectFrom.equals("") || DateFunctions.compareDates(DateFunctions.formatDate(this.getEffectiveFromDate()), pricing.effectFrom) != 0) {
			this.setEffectiveFromDate(pricing.effectFrom);
		}
		if(pricing.effectTo.equals("") || DateFunctions.compareDates(DateFunctions.formatDate(this.getEffectiveToDate()), pricing.effectTo) != 0) {
			this.setEffectiveToDate(pricing.effectTo);
		}
		if(Double.compare(Double.parseDouble(this.getVendorFee()), Double.parseDouble(pricing.vendorFee)) != 0) {
			this.setVendorFee(pricing.vendorFee);
		}
		if(Double.compare(Double.parseDouble(this.getStateTransFee()), Double.parseDouble(pricing.stateTransFee)) != 0) {
			this.setStateTransFee(pricing.stateTransFee);
			ajax.waitLoading();
		}
		if(Double.compare(Double.parseDouble(this.getStateFee()), Double.parseDouble(pricing.stateFee)) != 0) {
			this.setStateFee(pricing.stateFee);
			ajax.waitLoading();
		}
		if(!this.getStateFeeSplitBy().equalsIgnoreCase(pricing.stateFee_SplitBy)) {
			this.selectSplitByForStateFee(pricing.stateFee_SplitBy);
			ajax.waitLoading();
		}
		if(Integer.parseInt(this.getStateFeeSplitInto()) != Integer.parseInt(pricing.stateFee_SplitInto)) {
			this.selectSplitIntoForStateFee(pricing.stateFee_SplitInto);
			ajax.waitLoading();
			this.waitLoading();
		}
		List<String[]> stateFeeAccountsUI = this.getStateFeeAccounts();
		for(int i = 0; i < pricing.stateFee_accounts.size(); i ++) {
			if(!pricing.stateFee_accounts.get(i)[0].equalsIgnoreCase(stateFeeAccountsUI.get(i)[0])) {
				this.selectAccountForStateFee(pricing.stateFee_accounts.get(i)[0], pricing.stateFee_accounts.get(i)[1], i);
				ajax.waitLoading();
			} else {
				if(Double.compare(Double.parseDouble(pricing.stateFee_accounts.get(i)[1]), Double.parseDouble(stateFeeAccountsUI.get(i)[1])) != 0) {
					this.selectAccountForStateFee(pricing.stateFee_accounts.get(i)[0], pricing.stateFee_accounts.get(i)[1], i);
					ajax.waitLoading();
				}
			}
		}
		
		if(Double.compare(Double.parseDouble(this.getTransactionFee()), Double.parseDouble(pricing.transFee)) != 0) {
			this.setTransFee(pricing.transFee);
			ajax.waitLoading();
		}
		if(!this.getTransactionFeeSplitBy().equalsIgnoreCase(pricing.transFee_SplitBy)) {
			this.selectSplitByForTransFee(pricing.transFee_SplitBy);
			ajax.waitLoading();
		}
		if(Integer.parseInt(this.getTransactionFeeSplitInto()) != Integer.parseInt(pricing.transFee_SplitInto)) {
			this.selectSplitIntoForTransFee(pricing.transFee_SplitInto);
			ajax.waitLoading();
			this.waitLoading();
		}
		List<String[]> transFeeAccountsUI = this.getTransactionFeeAccounts();
		for(int i = 0; i < pricing.transFee_accounts.size(); i ++) {
			if(!pricing.transFee_accounts.get(i)[0].equalsIgnoreCase(transFeeAccountsUI.get(i)[0])) {
				this.selectAccountForTransFee(pricing.transFee_accounts.get(i)[0], pricing.transFee_accounts.get(i)[1], i);
			} else {
				if(Double.compare(Double.parseDouble(pricing.transFee_accounts.get(i)[1]), Double.parseDouble(transFeeAccountsUI.get(i)[1])) != 0) {
					this.selectAccountForTransFee(pricing.transFee_accounts.get(i)[0], pricing.transFee_accounts.get(i)[1], i);
				}
			}
		}
	}
	
	/**
	 * 
	 */
	public void editProductPricing(String attribute, String value1, List<String[]> accountInfo) {
		if(attribute.equalsIgnoreCase("Status")) {
			this.selectStatus(value1);
		}
		if(attribute.equalsIgnoreCase("License From")) {
			this.selectLicenseYearFrom(value1);
			ajax.waitLoading();
		}
		if(attribute.equalsIgnoreCase("License To")) {
			this.selectLicenseYearTo(value1);
		}
		if(attribute.equalsIgnoreCase("Effective From")) {
			this.setEffectiveFromDate(value1);
		}
		if(attribute.equalsIgnoreCase("Effective To")) {
			this.setEffectiveToDate(value1);
		}
		if(attribute.equalsIgnoreCase("Vendor Fee")) {
			this.setVendorFee(value1);
		}
		if(attribute.equalsIgnoreCase("State Trans Fee")) {
			this.setStateTransFee(value1);
			ajax.waitLoading();
		}
		if(attribute.equalsIgnoreCase("State Fee")) {
			this.setStateFee(value1);
			ajax.waitLoading();
		}
		if(attribute.equalsIgnoreCase("State Fee Split By")) {
			this.selectSplitByForStateFee(value1);
			ajax.waitLoading();
		}
		if(attribute.equalsIgnoreCase("State Fee Split Into")) {
			this.selectSplitIntoForStateFee(value1);
			ajax.waitLoading();
			for(int i = 0; i < accountInfo.size(); i ++) {
				this.selectAccountForStateFee(accountInfo.get(i)[0], accountInfo.get(i)[1], i);
				ajax.waitLoading();
			}
		}
		
		if(attribute.equalsIgnoreCase("Transaction Fee")) {
			this.setTransFee(value1);
			ajax.waitLoading();
		}
		if(attribute.equalsIgnoreCase("Transaction Fee Split By")) {
			this.selectSplitByForTransFee(value1);
			ajax.waitLoading();
		}
		if(attribute.equalsIgnoreCase("Transaction Fee Split Into")) {
			this.selectSplitIntoForTransFee(value1);
			ajax.waitLoading();
			for(int i = 0; i < accountInfo.size(); i ++) {
				this.selectAccountForTransFee(accountInfo.get(i)[0], accountInfo.get(i)[1], i);
			}
		}
	}
	
	/**
	 * Verify effective from date component works correctly
	 * @param invalidDates
	 * @return
	 */
	public boolean verifyFromDate(String[] invalidDates) {
		return verifyAutomaticDateCorrection((IText)browser.getTextField(".id", new RegularExpression("ProductPricingView-\\d+\\.effectiveStartDate_ForDisplay", false))[0], invalidDates);
	}
	
	/**
	 * Verify effective to date component works correctly
	 * @param invalidDates
	 * @return
	 */
	public boolean verifyToDate(String[] invalidDates) {
		return verifyAutomaticDateCorrection((IText)browser.getTextField(".id", new RegularExpression("ProductPricingView-\\d+\\.effectiveEndDate_ForDisplay", false))[0], invalidDates);
	}
	
	public void clickTax(){
		browser.clickGuiObject(".class", "Html.A",".text","Tax");
	}
	
	/**
	 * Get the pricing info in Edit HF Product Pricing widget
	 * @return
	 */
	public PricingInfo getPricingInfo() {
		PricingInfo pricing = new PricingInfo();
		
		pricing.id = this.getPricingID();
		pricing.status = this.getStatus();
		pricing.locationClass = this.getLocationClass();
		pricing.licYearFrom = this.getLicenseYearFrom();
		if(!pricing.licYearFrom.equalsIgnoreCase("All")) {
			pricing.licYearTo = this.getLicenseYearTo();
		}
		if(this.isPurchaseTypeExists()) {
			pricing.purchaseType = this.getPurchaseType();
		}
		pricing.effectFrom = this.getEffectiveFromDate();
		pricing.effectTo = this.getEffectiveToDate();
		pricing.vendorFee = this.getVendorFee();
		pricing.stateTransFee = this.getStateTransFee();
		
		pricing.stateFee = this.getStateFee();
		pricing.stateFee_SplitBy = this.getStateFeeSplitBy();
		pricing.stateFee_SplitInto = this.getStateFeeSplitInto();
		pricing.stateFee_accounts = this.getStateFeeAccounts();
		
		pricing.transFee = this.getTransactionFee();
		pricing.transFee_SplitBy = this.getTransactionFeeSplitBy();
		pricing.transFee_SplitInto = this.getTransactionFeeSplitInto();
		pricing.transFee_accounts = this.getTransactionFeeAccounts();
		
		pricing.createUser = this.getCreateUser();
		pricing.createLocation = this.getCreateLocation();
		pricing.createTime = this.getCreateTime();
		pricing.lastUpdateUser = this.getLastUpdateUser();
		pricing.lastUpdateLocation = this.getLastUpdateLocation();
		pricing.lastUpdateTime = this.getLastUpdateTime();
		
		return pricing;
	}
}
