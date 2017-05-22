/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.pages.component.GroupSelectionComponent;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * @Defects:
 * 
 * @author asun
 * @Date  Jul 20, 2011
 */
public class LicMgrAddProductPricingWidget extends LicMgrProductPricingCommonWidget {
	
	private static LicMgrAddProductPricingWidget instance=null;
	
	protected LicMgrAddProductPricingWidget(){
		super("Add Product Pricing");
	}
	
	public static LicMgrAddProductPricingWidget getInstance(){
		if(instance==null){
			instance=new LicMgrAddProductPricingWidget();
		}
		return instance;
	}
	
	public void selectLocationClass(String locClass){
		browser.selectDropdownList(".id", this.locClassRegx, locClass);
	}
	
	public void selectPurchaseType(String type){
		if(type.equalsIgnoreCase("Replacement") || type.equalsIgnoreCase("Duplicate")){
			List<String> typeList = this.getPurchaseTypes();
			if(typeList.contains("Replacement")){
				type = "Replacement";
			}else{
				type = "Duplicate";
			}
		}
		
		browser.selectDropdownList(".id", this.purchaseTypeRegx, type);
	}
	/**
	 * select applies to all state yes.
	 */
	public void selectAppliesToAllStateYes(){
		browser.selectRadioButton("id", new RegularExpression("HFProductPricingDetailUI-\\d+\\.applicableToAllState",false),0);
	}
	/**
	 * select applies to not all state.
	 */
	public void selectAppliesToAllStateNo(){
		browser.selectRadioButton("id", new RegularExpression("HFProductPricingDetailUI-\\d+\\.applicableToAllState",false),1);
	}
	public void setPricingInfo(PricingInfo pricing){		
		this.selectLocationClass(pricing.locationClass);
		this.selectLicenseYearFrom(pricing.licYearFrom);
		ajax.waitLoading();
		
		if(!pricing.licYearFrom.equalsIgnoreCase("ALL") && pricing.licYearTo.length() > 0){
			this.selectLicenseYearTo(pricing.licYearTo);
		}
		
		if(pricing.prodType.matches("(Privilege(s?)|Vehicle(s?))")){
			if(pricing.purchaseType.trim().length() > 0) {
				this.selectPurchaseType(pricing.purchaseType);
			}
		}
		
		this.setVendorFee(pricing.vendorFee);
		this.setStateTransFee(pricing.stateTransFee);
		ajax.waitLoading();
		
		//TODO this Field doesn't exist in build - 3.03.00.299, but exists in build - 3.03.01.56
		//only for privilege have below apply to all state radio, and Lottery
		if(pricing.prodType.matches("Privilege(s?)") || pricing.prodType.equalsIgnoreCase("Lottery")){
			if(!pricing.isAppliesToAllState) {
				this.selectAppliesToAllStateNo();
				ajax.waitLoading();
				this.waitLoading();
				this.selectAppliesState(pricing.addState.split(","));
				ajax.waitLoading();
			} else {
				this.selectAppliesToAllStateYes();
				ajax.waitLoading();
				this.waitLoading();
			}
		}
		
		
		// set state fee info
		this.setStateFee(pricing.stateFee);
		ajax.waitLoading();
		this.selectSplitByForStateFee(pricing.stateFee_SplitBy);
		ajax.waitLoading();
		this.selectSplitIntoForStateFee(pricing.stateFee_SplitInto);
		ajax.waitLoading();
		for(int i=0;i<pricing.stateFee_accounts.size();i++){
			this.selectAccountForStateFee(pricing.stateFee_accounts.get(i)[0], pricing.stateFee_accounts.get(i)[1], i);
			ajax.waitLoading();
		}
        // Keep these two the last, the date component overlay will cause some issue
        this.setEffectiveFromDate(pricing.effectFrom);
        this.removeFocus();
        if(StringUtil.notEmpty(pricing.effectTo.trim())){
    		this.setEffectiveToDate(pricing.effectTo);
    		this.removeFocus();
        }
        
		//set transaction fee info
		this.setTransFee(pricing.transFee);
		ajax.waitLoading();
		this.selectSplitByForTransFee(pricing.transFee_SplitBy);
		ajax.waitLoading();
		this.selectSplitIntoForTransFee(pricing.transFee_SplitInto);
		ajax.waitLoading();
        for(int i=0;i<pricing.transFee_accounts.size();i++){
			this.selectAccountForTransFee(pricing.transFee_accounts.get(i)[0], pricing.transFee_accounts.get(0)[1], i);
		}
        
        //for Lottery product
        if(!StringUtil.isEmpty(pricing.stateVendorFee)) {
        	this.setStateVendorFee(pricing.stateVendorFee);
		}
        if (pricing.prodType.equalsIgnoreCase("Lottery")) {
        	this.selectRateType(pricing.rateType);
        	ajax.waitLoading();
        	if (pricing.rateType.equalsIgnoreCase(OrmsConstants.PER_CHOICE_TYPE)) {
        		// Set pricing when rate type = Per Choice
        		for (int i = 0; i < pricing.choiceFees.size(); i++) {
        			this.setChoiceFee(pricing.choiceFees.get(i), i);
        		}
        	} else if (pricing.rateType.equalsIgnoreCase(OrmsConstants.CHOICE_RANGE_TYPE)) {
        		// Set pricing when rate type = Choice Range
        		for (int i = 0; i < pricing.choiceFees.size(); i++) {
        			if (i != 0) {
        				this.clickAddRange();
        				ajax.waitLoading();
        			}
        			this.setChoiceFee(pricing.choiceFees.get(i), i);
        		}
        	}
        	
        	if(this.isKeepPreviousPriceDuringChangeIfHigherExists()) {
             	this.selectKeepPreviousPriceDuringChangeIfHigher(pricing.keepPreviousPriceDuringChangeIfHigher);
             	ajax.waitLoading();
             }
             if(this.isCalculateHoldingFeeBasedOnSelectedChoicesExists()) {
             	this.selectCalculateHoldingFeeBasedOnSelectedChoices(pricing.calculateHoldingFeeBasedOnSelectedChoices);
             	ajax.waitLoading();
             }
             if(!StringUtil.isEmpty(pricing.holdingFee)) {
             	this.setHoldingFee(pricing.holdingFee);
             } 
             if(isHoldingFeeAccountExist()){
            	 if(!StringUtil.isEmpty(pricing.holdingFeeAccount)) {
            		 this.selectHoldingFeeAccount(pricing.holdingFeeAccount);
            	 } else {
            		 this.selectHoldingFeeAccount(1);
            	 }
             }
        }

	}
	
	public boolean isStatusEditable(){
		IHtmlObject[] objs=browser.getDropdownList(".id", this.statusRegx);
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find status dropdown list.");
		}
		if(objs[0].getProperty(".disabled").equals("true")){
			return false;
		}
		return true;
	}
	
	
	
	public List<String> getPurchaseTypes(){
		return browser.getDropdownElements(".id", this.purchaseTypeRegx);
	}
	
	/*
	 * Get the warning message.
	 */
	public String getWarningMessage(){
		String message="";
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".id", "NOTSET");
		
		if(objs.length<1){
			return "";
		}
		if (objs.length > 1) {
			for (IHtmlObject obj : objs) {
				message += obj.getProperty(".text");
			}
			Browser.unregister(objs);
			return message;
		}
		message=objs[0].getProperty(".text");
		Browser.unregister(objs);
		return message;
	}
	
	public void removeFocus(){
		browser.clickGuiObject(".class", "Html.SELECT", ".id", new RegularExpression("ProductPricingView-\\d+\\.active",false));
	}
	
	/**
	 * select applies states
	 * @param addStates
	 */
	public void selectAppliesState(String... addStates){
		if(null!= addStates && addStates.length>=1){
//			HtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^AA.*",false));
//			HtmlObject[] objs2 = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^State.*",false));
			Property[] property = new Property[1];
			property[0] = new Property(".id","selection_button");
			IHtmlObject[] from = browser.getDropdownList(".id","selection_button");//, objs1[0]);
			IHtmlObject[] to = browser.getDropdownList(".id","selected_button");//,objs2[0]);
			IHtmlObject[] add = browser.getHtmlObject(".class","Html.A",".text","Add");
			IHtmlObject[] remove = browser.getHtmlObject(".class","Html.A",".text","Remove");
			
			new GroupSelectionComponent((ISelect)from[0], (ISelect)to[0], add[0], remove[0]).add(addStates);
		}
	}
	/**
	 * remove applies state.
	 * @param removeStates
	 */
	public void removeAppliesState(String... removeStates){
		if(null!= removeStates && removeStates.length>1){
			IHtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^AA.*",false));
			IHtmlObject[] objs2 = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^State.*",false));
			Property[] property = new Property[1];
			property[0] = new Property(".id","selected_button");
			IHtmlObject[] from = browser.getDropdownList(property, objs1[0]);
			IHtmlObject[] to = browser.getDropdownList(property,objs2[0]);
			IHtmlObject[] add = browser.getHtmlObject(".class","Html.A",".text","Add");
			IHtmlObject[] remove = browser.getHtmlObject(".class","Html.A",".text","Remove");
			
			new GroupSelectionComponent((ISelect)from[0], (ISelect)to[0], add[0], remove[0]).remove(removeStates);
		}
	}
	
	public void selectRateType(String type) {
		int index = -1;
		if(type.equalsIgnoreCase(OrmsConstants.PER_APPLICATION_TYPE)) {
			index = 0;
		} else if(type.equalsIgnoreCase(OrmsConstants.PER_CHOICE_TYPE)) {
			index = 1;
		} else if(type.equalsIgnoreCase(OrmsConstants.CHOICE_RANGE_TYPE)) {
			index = 2;
		} else throw new ErrorOnPageException("Unknown Rate Type - " + type);
		
		browser.selectRadioButton(".id", new RegularExpression("ProductPricingView-\\d+\\.rateType", false), index);
	}
	
	public void clickTax(){
		browser.clickGuiObject(".class", "Html.A",".text","Tax");
	}
}


