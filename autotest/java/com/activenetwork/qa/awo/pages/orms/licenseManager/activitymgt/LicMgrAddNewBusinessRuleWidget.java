package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import com.activenetwork.qa.awo.datacollection.legacy.RuleDataInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryExecutionConfigSelectWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @author pchen
 *
 */
public class LicMgrAddNewBusinessRuleWidget extends DialogWidget {
	private static LicMgrAddNewBusinessRuleWidget _instance = null;
	
	protected LicMgrAddNewBusinessRuleWidget() {
		super("Add Activity Business Rule");
	}
	
	public static LicMgrAddNewBusinessRuleWidget getInstance() {
		if(_instance == null) _instance = new LicMgrAddNewBusinessRuleWidget();
		
		return _instance;
	}
	
	protected Property[] businessRuleDropdownList() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("DropdownExt-\\d+\\.selectedValue", false));
	}
	
	protected Property[] effectiveDateTextField() {
		return Property.toPropertyArray(".id", new RegularExpression("RuleConditionView-\\d+\\.effectiveDate_ForDisplay", false));
	}
	
	protected Property[] locationClassDropdownList() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("RuleConditionView-\\d+\\.locationClass", false));
	}
	
	protected Property[] productGroupDropdownList() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("RuleConditionView-\\d+\\.productGroup", false));
	}
	
	protected Property[] salesChannelDropdownList() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("RuleConditionView-\\d+\\.salesChannel", false));
	}
	
	protected Property[] lengthTextField() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("RuleAttributeValueView-\\d+\\.value", false));
	}
	
	protected Property[] unitDropdownList() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("RuleAttributeValueView-\\d+\\.value", false));
	}
			
	public void selectBusinessRule(String ruleName){
		browser.selectDropdownList(this.businessRuleDropdownList(), ruleName);
	}
	
	public void setEffectiveDate(String effectiveDate){
		browser.setTextField(this.effectiveDateTextField(), effectiveDate);
	}
	
	public void selectLocationClass(String locationClass){
		browser.selectDropdownList(this.locationClassDropdownList(), locationClass);
	}
	
	public void selectProductGroup(String prdGrp){
		browser.selectDropdownList(this.productGroupDropdownList(), prdGrp);
	}
	
	public void selectSaleChannel(String saleChannel){
		browser.selectDropdownList(this.salesChannelDropdownList(), saleChannel);
	}
	
	public void setLength(String length){
		browser.setTextField(this.lengthTextField(), length);
	}
	
	public void selectUnit(String unit){
		browser.selectDropdownList(this.unitDropdownList(), unit);
	}
	
	public void setUpBusinessRule(RuleDataInfo rule){
		if(StringUtil.notEmpty(rule.ruleName)){
			this.selectBusinessRule(rule.ruleName);
			ajax.waitLoading();
			this.waitLoading();
		}
		if(StringUtil.notEmpty(rule.effectiveDate)){
			this.setEffectiveDate(rule.effectiveDate);
		}
		if(StringUtil.notEmpty(rule.locationClass)){
			this.selectLocationClass(rule.locationClass);
		}
		if(StringUtil.notEmpty(rule.productGroup)){
			this.selectProductGroup(rule.productGroup);
		}
		if(StringUtil.notEmpty(rule.salesChannel)){
			this.selectSaleChannel(rule.salesChannel);
		}
		if(StringUtil.notEmpty(rule.length)){
			this.setLength(rule.length);
		}
		if(StringUtil.notEmpty(rule.unit)){
			this.selectUnit(rule.unit);
		}
	}
	
	public void setUpBusinessRuleAndClickOk(RuleDataInfo rule){
		this.setUpBusinessRule(rule);
		this.clickOK();
	}
}
