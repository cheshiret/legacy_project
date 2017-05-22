/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.Arrays;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @ScriptName LicMgrPrivilegeAddBusinessRulePage.java
 * @Date:Mar 7, 2011
 * @Description:
 * @author asun
 */
public class LicMgrPrivilegeAddBusinessRuleWidget extends DialogWidget {

	private static LicMgrPrivilegeAddBusinessRuleWidget instance = null;

	private LicMgrPrivilegeAddBusinessRuleWidget() {
	}

	public static LicMgrPrivilegeAddBusinessRuleWidget getInstance() {
		if (instance == null) {
			instance = new LicMgrPrivilegeAddBusinessRuleWidget();
		}
		return instance;
	}

	protected Property[] errorMsgDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "NOTSET");
	}
	
	protected Property[] residencyType(){
		return Property.toPropertyArray(".id", new RegularExpression("ResidencyStatusToSelectedHuntsPairing-\\d+\\.residencyStatus", false));
	}
	
	@Override
	public boolean exists() {
		boolean flag1 = super.exists();
		boolean flag2 = browser.checkHtmlObjectExists(".class", "Html.DIV",
				".text", new RegularExpression("Add (Privilege|Licence) Business Rule", false)); //MS|SK
		return flag1 && flag2;
	}

	public void selectRuleCategory(String category) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Rule Category.*", false));
		RegularExpression regx = new RegularExpression(
				"DropdownExt-\\d+\\.selectedValue", false);
		browser.selectDropdownList(".id", regx, category, false, objs[0]);
		Browser.unregister(objs);
		ajax.waitLoading();
	}

	public String getRuleCategory() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Rule Category.*", false));
		RegularExpression regx = new RegularExpression(
				"DropdownExt-\\d+\\.selectedValue", false);
		String category = browser.getDropdownElements(
				new Property[] { new Property(".id", regx) }, objs[0]).get(0);
		Browser.unregister(objs);
		return category;
	}

	public String getBusinessRule() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Business Rule.*", false));
		RegularExpression regx = new RegularExpression(
				"DropdownExt-\\d+\\.selectedValue", false);
		String type = browser.getDropdownElements(
				new Property[] { new Property(".id", regx) }, objs[objs.length-1]).get(0);//Sara[10/23/2013], objs[0]
		Browser.unregister(objs);
		return type;
	}

	public void selectBusinessRule(String rule) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Business Rule.*", false));
		RegularExpression regx = new RegularExpression(
				"DropdownExt-\\d+\\.selectedValue", false);
		browser.selectDropdownList(".id", regx, rule, false, objs[objs.length-1]);//Sara[10/23/2013], objs[0]
		Browser.unregister(objs);
	}
	
	public void selectLocationClass(String locationClass){
		browser.selectDropdownList(".id", new RegularExpression("ProductRuleView-\\d+\\.locationClass",false),locationClass);
	}
	
	public void selectContract(){
		browser.selectCheckBox(".id", "ProductRuleParameterValueBean-\\d+\\.stringValueList_\\d+\\", true);
	}
	
	public void unSelectContract(){
		browser.unSelectCheckBox(".id", "ProductRuleParameterValueBean-\\d+\\.stringValueList_\\d+\\");
	}

	public void selectPrivilege(String privilege, int object) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^(Privilege|Licence) Product.*", false)); //Lesley[20130913]: update for SK contract
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		browser.selectDropdownList(".id", regx, privilege, true, objs[objs.length - 1]);
		Browser.unregister(objs);
	}

	public void selectEducationType(String type, int object) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Education Type.*", false));
		RegularExpression regx = new RegularExpression(
//				"ProductRuleParameterValueBean-\\d+\\.value", false);
				"DropdownExt-\\d+\\.selectedValue", false);//UI changed, updated by Jane
//		browser.selectDropdownList(".id", regx, type, true, objs[object + 1]);
		
		//Quentin[20130906] limit the Top Object by index, so the dropdown list index will always be 0
		if(objs.length-1 == object){
			browser.selectDropdownList(Property.toPropertyArray(".id", regx), type, true, 0, objs[object]);
		}else{
			browser.selectDropdownList(Property.toPropertyArray(".id", regx), type, true, 0, objs[object+1]);
		}
		Browser.unregister(objs);
	}
	
/**
 * select required quantity.
 * @param requiredQuantity
 * @param index
 */
	public void setRequiredQuantity(String requiredQuantity,int index){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Required Quantity.*", false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		browser.setTextField(".id", regx, requiredQuantity, objs[index]);
	}
/**
 * select free quantity	
 * @param freeQuantity
 * @param index
 */
	public void setFreeQuantity(String freeQuantity,int index){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Free Quantity.*", false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		browser.setTextField(".id", regx,freeQuantity, objs[index]);
	}

	public void selectCertificationType(String type, int object) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Certification Type.*", false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		browser.selectDropdownList(".id", regx, type, false, objs[object]);
		Browser.unregister(objs);
	}

	public String getPrivilegeValue(int object) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(Property.toPropertyArray(".id", "AddProductRuleParametersUI"), 
				Property.toPropertyArray(".class", "Html.SPAN", ".text", new RegularExpression("^Privilege Product.*", false))));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		Property[] p = new Property[1];
		p[0] = new Property(".id", regx);
		String value = browser.getDropdownElements(p, objs[object]).get(0);
		Browser.unregister(objs);
		return value;
	}

	public String getEducationType() {
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		String type = browser.getDropdownListValue(".id", regx, 0);
		return type;
	}

	public void selectPurchaseType(String type, int index) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Purchase Type.*", false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		browser.selectDropdownList(".id", regx, type, false, objs[index]);
		Browser.unregister(objs);
	}

	public void selectSuspensionType(String type, int object) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Suspension Type.*", false));

		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		browser.selectDropdownList(".id", regx, type, false, objs[object]);
		Browser.unregister(objs);
	}

	public void selectLocationClass(String location, int object) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Location Class.*", false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		if(objs.length>object){
			browser.selectDropdownList(".id", regx, location, false, objs[object]);
		}else{
			browser.selectDropdownList(".id", regx, location, false, objs[objs.length-1]);
		}
		
		Browser.unregister(objs);
	}

	public void selectWorkAction(String action, int object) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Work Flow Action.*", false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		if (object < objs.length) {
			browser
					.selectDropdownList(".id", regx, action, false,
							objs[object]);
		} else {
			browser.selectDropdownList(".id", regx, action, false,
					objs.length - 1);
		}

		Browser.unregister(objs);
	}

	public void setPassNum(String num, int object) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Number of Bypass Allowed.*",
						false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		browser.setTextField(".id", regx, num, false, 0, objs[object]);
		Browser.unregister(objs);
	}

	public void setEffectiveDate(String date, int object) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",".text", new RegularExpression("^Effective Date.*", false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value_ForDisplay", false);
		if(objs.length>object){
			browser.setTextField(".id", regx, date, true, 0, objs[object]);
		}
		
		Browser.unregister(objs);
	}

	public String getEffectiveDate(int object) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Effective Date.*", false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value_ForDisplay", false);
		Property[] p = new Property[1];
		p[0] = new Property(".id", regx);
		String date = browser.getTextFieldValue(p, objs[object]);

		Browser.unregister(objs);
		return date;
	}

	public void setAge(String age) {
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value$", false);
		browser.setTextField(".id", regx, age);
	}

	public String getOnDateValue() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^On Date.*", false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value_ForDisplay", false);
		Property[] p = new Property[1];
		p[0] = new Property(".id", regx);
		String date = browser.getTextFieldValue(p, objs[0]);

		Browser.unregister(objs);
		return date;
	}

	public boolean isEducationTypeExist() {
		RegularExpression regx = new RegularExpression("^Education Type.*",
				false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				regx);
	}

	public boolean isLocationClassExist() {
		RegularExpression regx = new RegularExpression("^Location Class.*",
				false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				regx);
	}

	public boolean isCertificationTypeExist() {
		RegularExpression regx = new RegularExpression("^Certification Type.*",
				false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				regx);
	}

	public boolean isAgeExist() {
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value$", false);
		return browser.checkHtmlObjectExists(".class","Html.INPUT.text",".id",regx);
	}

	public boolean isPassNumExist() {
		RegularExpression regx = new RegularExpression(
				"^Number of Bypass Allowed.*", false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				regx);
	}

	public boolean isDateOfBirthExist(){
		RegularExpression regx = new RegularExpression(
				"^Date of Birth.*", false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				regx);
	}
	
	public boolean isPromptExist(){
		RegularExpression regx = new RegularExpression(
				"^Prompt.*", false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				regx);//Sara[10/23/2013] DIV-SPAN
	}
	
	public void setDateOfBirth(String num, int object) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",".text", new RegularExpression("^Date of Birth.*",false));
		RegularExpression regx = new RegularExpression("ProductRuleParameterValueBean-\\d+\\.value_ForDisplay", false);
		browser.setTextField(".id", regx, num, false, 0, objs[object]); 
		Browser.unregister(objs);
	}
	
	public void setPrompt(String num, int object) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN", ".text", new RegularExpression("^Prompt.*",false)));//Sara[10/23/2013] DIV-SPAN
		RegularExpression regx = new RegularExpression("ProductRuleParameterValueBean-\\d+\\.value", false);
		if(objs.length-1==object){
			browser.setTextField(Property.toPropertyArray(".id", regx), num, true, 0, objs[object]);	
		}else{
			browser.setTextField(Property.toPropertyArray(".id", regx), num, true, 0, objs[object+1]);
		}
		Browser.unregister(objs);
	}
	
	public boolean isPurchaseTypeExist() {
		RegularExpression regx = new RegularExpression("^Purchase Type.*",
				false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				regx);
	}

	protected Property[] waitingPeriodSpan() {
		return Property.concatPropertyArray(this.span(), ".text", new RegularExpression("^Waiting Period.*", false));
	}
	
	public boolean isWaitingPeriodExist() {
		return browser.checkHtmlObjectExists(waitingPeriodSpan());
	}
	
	public void selectWaitingPeriod(String period) {
		IHtmlObject[] objs = browser.getHtmlObject(waitingPeriodSpan());
		RegularExpression regx = new RegularExpression("ProductRuleParameterValueBean-\\d+\\.value", false);
		Property[] p = Property.toPropertyArray(".id", regx);
		IHtmlObject[] objs1 = browser.getHtmlObject(p, objs[0]);
		if(objs1.length>0 && objs1[0].getProperty(".idDisabled").equalsIgnoreCase("false"))
			browser.selectDropdownList(p,  period, true, objs[objs.length - 1]);
		Browser.unregister(objs, objs1);
	}
	
	public boolean isWorkActionExist() {
		RegularExpression regx = new RegularExpression("^Work Flow Action.*",
				false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				regx);
	}

	public void setOnDate(String onDate) {
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value_ForDisplay", false);
//		browser.setTextField(".id", regx, onDate, 1);
		browser.setCalendarField(".id", regx, onDate, 1);
	}

	public boolean isOnDateExist() {
		RegularExpression regx = new RegularExpression("^On Date.*", false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				regx);
	}

	public String getErrorMessage() {
		String error = browser.getObjectText(errorMsgDiv());
		return error;
	}

	public void clickAddButton() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add", true);
	}

	public int getRemoveButtonNum() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Remove");

		Browser.unregister(objs);
		return objs.length;
	}
	
	public void clickToplabel(){
		browser.clickGuiObject(".id", "ui-dialog-title-Dialog001");
	}

	public void setRuleInfo(PrivilegeBusinessRule rule) {
		if (rule.ruleCategory.trim().length() > 1) {
			this.selectRuleCategory(rule.ruleCategory);
			ajax.waitLoading();
		}

		if (rule.name.trim().length() > 1) {
			this.selectBusinessRule(rule.name);
			ajax.waitLoading();
		}

		if (rule.effectiveDate.trim().length() > 1) {
			this.setEffectiveDate(rule.effectiveDate, 0);
			this.clickToplabel();
		}
 
		if (rule.ruleCategory.equalsIgnoreCase("Customer Demographic")) {
			if (rule.parameters[0].age.length() > 0 && this.isAgeExist())
				this.setAge(rule.parameters[0].age);
		

			if (rule.parameters[0].onDate.length() > 0 && this.isOnDateExist()) {
				this.setOnDate(rule.parameters[0].onDate);
			}
		}
		
		if("IF parameter EDUCATION Type not on file, Customer must PROVIDE parameter EDUCATION type information in order to purchase".equals(rule.name)) {
			this.selectContract();
		}
	}
	
	public boolean isMatchLYExist() {
		RegularExpression regx = new RegularExpression("Match (Privilege|Licence) Licen(s|c)e Year.*", false); //Lesley[20130913]: udpate for SK contract - the text privilege displayed as licence
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				regx);
	}
	
	public void selectMatchLY(int object){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Match (Privilege|Licence) Licen(s|c)e Year.*", false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		IHtmlObject[] objs1 = browser.getHtmlObject(".class","Html.INPUT.checkbox",".id", regx);
		browser.selectCheckBox(".class","Html.INPUT.checkbox", ".id", regx, 0, true, objs[object]);
		
		Browser.unregister(objs1);
		Browser.unregister(objs);
	}
	
	protected Property[] ruleParamStateSpan(String state) {
		return Property.concatPropertyArray(this.span(), 
				".text", new RegularExpression("^"+state+"$", false));
	}
	
	protected Property[] ruleParamStateCheckbox() {
		return Property.concatPropertyArray(this.input("checkbox"), 
				".id", new RegularExpression("ProductRuleParameterValueBean-\\d+\\.stringValueList_\\d+", false));
	}
	
	public boolean isStateExist(String isMatchedState) {
//		RegularExpression regx = new RegularExpression("^"+isMatchedState+".*", false);//MS
//		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
//				regx);
		return browser.checkHtmlObjectExists(ruleParamStateSpan(isMatchedState));
	}
	
	public void selectState(int object, String matchedState){
		IHtmlObject[] objs = browser.getHtmlObject(this.ruleParamStateSpan(matchedState));//MS
//		RegularExpression regx = new RegularExpression(
//				"ProductRuleParameterValueBean-\\d+\\.stringValueList_\\d+", false);
//		IHtmlObject[] objs1 = browser.getHtmlObject(".class","Html.INPUT.checkbox",".id", regx);
		browser.selectCheckBox(this.ruleParamStateCheckbox(), 0, true, objs[object]);
//		
//		Browser.unregister(objs1);
		Browser.unregister(objs);
	}

	/**Added by Lesley,  Optional checkbox for Provide Education Rule */
	protected Property[] ruleParamOptionalSpan() {
		return Property.concatPropertyArray(this.span(), ".className", "inputwithrubylabel checkbox",
				".text", new RegularExpression("^Optional$", false));
	}
	
	protected Property[] ruleParamOptionalCheckbox() {
		return Property.concatPropertyArray(this.input("checkbox"), 
				".id", new RegularExpression("ProductRuleParameterValueBean-\\d+\\.value", false));
	}
	
	public boolean isOptionalExist() {
		return browser.checkHtmlObjectExists(ruleParamStateSpan("Optional"));
	}
	
	public void selectOptional(int index) {
		IHtmlObject[] objs = browser.getHtmlObject(this.ruleParamOptionalSpan());
		browser.selectCheckBox(this.ruleParamOptionalCheckbox(), 0, objs[index]);
		Browser.unregister(objs);
	}
	
	public boolean isPriNumExist() {
		RegularExpression regx = new RegularExpression("^Privilege Number.*", false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				regx);
	}
	
//	public void selectPriNum(int object){
//		HtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
//				".text", new RegularExpression("^Privilege Number.*", false));
//		RegularExpression regx = new RegularExpression(
//				"ProductRuleParameterValueBean-\\d+\\.stringValueList_\\d+", false);
//		HtmlObject[] objs1 = browser.getHtmlObject(".class","Html.INPUT.checkbox",".id", regx);
//		browser.selectCheckBox(".class","Html.INPUT.checkbox", ".id", regx, 0, true, objs[object]);
//		
//		Browser.unregister(objs1);
//		Browser.unregister(objs);
//	}
	
	public boolean isInvNumExist() {
		RegularExpression regx = new RegularExpression("^Inventory Number.*", false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				regx);
	}
	
//	public void selectInvNum(int object){
//		HtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
//				".text", new RegularExpression("^Inventory Number.*", false));
//		RegularExpression regx = new RegularExpression(
//				"ProductRuleParameterValueBean-\\d+\\.stringValueList_\\d+", false);
//		HtmlObject[] objs1 = browser.getHtmlObject(".class","Html.INPUT.checkbox",".id", regx);
//		browser.selectCheckBox(".class","Html.INPUT.checkbox", ".id", regx, 0, true, objs[object]);
//		
//		Browser.unregister(objs1);
//		Browser.unregister(objs);
//	}
	
	public boolean isValidFromDateExist() {
		RegularExpression regx = new RegularExpression("^Valid From Date.*", false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", regx);
	}
	
//	public void selectValidFromDate(int object){
//		HtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^Valid From Date.*", false));
//		RegularExpression regx = new RegularExpression(
//				"ProductRuleParameterValueBean-\\d+\\.stringValueList_\\d+", false);
//		HtmlObject[] objs1 = browser.getHtmlObject(".class","Html.INPUT.checkbox",".id", regx);
//		browser.selectCheckBox(".class","Html.INPUT.checkbox", ".id", regx, 0, true, objs[object]);
//		
//		Browser.unregister(objs1);
//		Browser.unregister(objs);
//	}
	
	public boolean isValidToDateExist() {
		RegularExpression regx = new RegularExpression("^Valid To Date.*", false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", regx);
	}
	
//	public void selectValidToDate(int object){
//		HtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^Valid To Date.*", false));
//		RegularExpression regx = new RegularExpression(
//				"ProductRuleParameterValueBean-\\d+\\.stringValueList_\\d+", false);
//		HtmlObject[] objs1 = browser.getHtmlObject(".class","Html.INPUT.checkbox",".id", regx);
//		browser.selectCheckBox(".class","Html.INPUT.checkbox", ".id", regx, 0, true, objs[object]);
//		
//		Browser.unregister(objs1);
//		Browser.unregister(objs);
//	}
	
	public boolean isValidFromTimeExist() {
		RegularExpression regx = new RegularExpression("^Valid From Time.*", false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", regx);
	}
	
//	public void selectValidFromTime(int object){
//		HtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^Valid From Time.*", false));
//		RegularExpression regx = new RegularExpression(
//				"ProductRuleParameterValueBean-\\d+\\.stringValueList_\\d+", false);
//		HtmlObject[] objs1 = browser.getHtmlObject(".class","Html.INPUT.checkbox",".id", regx);
//		browser.selectCheckBox(".class","Html.INPUT.checkbox", ".id", regx, 0, true, objs[object]);
//		
//		Browser.unregister(objs1);
//		Browser.unregister(objs);
//	}
	
	public boolean isValidToTimeExist() {
		RegularExpression regx = new RegularExpression("^Valid To Time.*", false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", regx);
	}
	
//	public void selectValidToTime(int object){
//		HtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^Valid To Time.*", false));
//		RegularExpression regx = new RegularExpression(
//				"ProductRuleParameterValueBean-\\d+\\.stringValueList_\\d+", false);
//		HtmlObject[] objs1 = browser.getHtmlObject(".class","Html.INPUT.checkbox",".id", regx);
//		browser.selectCheckBox(".class","Html.INPUT.checkbox", ".id", regx, 0, true, objs[object]);
//		
//		Browser.unregister(objs1);
//		Browser.unregister(objs);
//	}
	
	public void selectPostedToParameter(String para, int index){
		if(null == para || !Arrays.asList(OrmsConstants.postedToString).contains(para)){
			throw new ErrorOnDataException("Please check posted to parameter");
		}

		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^"+para+".*", false));
//		if(index>= objs.length){
//			throw new ErrorOnDataException("The index should not more than the object array length");
//		}
		int number = objs.length -1;
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.stringValueList_\\d+", false);
		IHtmlObject[] objs1 = browser.getHtmlObject(".class","Html.INPUT.checkbox",".id", regx,objs[number]);
		browser.selectCheckBox(".class","Html.INPUT.checkbox", ".id", objs1[0].getProperty(".id"), 0, true);//updated by pzhu
		
		Browser.unregister(objs1);
		Browser.unregister(objs);
	}
	
	public void unSelectResidencyProofsParameter(String para){
		if(null == para || !Arrays.asList(OrmsConstants.residencyProofsString).contains(para)){
			throw new ErrorOnDataException("Please check residency proof parameter");
		}
		
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^"+para+".*", false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.stringValueList_\\d+", false);
		Property[] property = new Property[2];
		property[0] = new Property(".class","Html.INPUT.checkbox");
		property[1] = new Property(".id", regx);
		browser.unSelectCheckBox(property, 0, objs[0]);
		
		Browser.unregister(objs);
	}
	
	public void setPrompt(String prompt){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Prompt.*", false));
		browser.setTextField("id", new RegularExpression("ProductRuleParameterValueBean-\\d+\\.value",false), prompt,objs[1]);
	}
	
	public void selectAttribute(String attribute, int index){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("AddProductRuleParametersUI",false));
		browser.selectDropdownList(Property.toPropertyArray(".id", new RegularExpression("DropdownExt-\\d+\\.selectedValue",false)), attribute, true, index, objs[objs.length-1]);
		Browser.unregister(objs);
	}
	
	public void selectValue(String value,int index){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("AddProductRuleParametersUI",false));
		browser.selectDropdownList(Property.toPropertyArray(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[\\d+\\].*",false)), value, true, index, objs[objs.length-1]);
		Browser.unregister(objs);
	}
	
	public void selectAttributeInfo(String attribute, String value, int index){
		if(StringUtil.notEmpty(attribute)){
			this.selectAttribute(attribute,index);
			ajax.waitLoading();
		}
		
		if(StringUtil.notEmpty(value)){
			this.selectValue(value, index);
		}
	}
	
	public void selectResidencyType(String residencyType, int index){
		boolean residencyTypeExist = browser.checkHtmlObjectExists(residencyType());
		RegularExpression regx = new RegularExpression("ProductRuleParameterValueBean-\\d+\\.value", false);
		
		if(residencyTypeExist){
			browser.selectDropdownList(residencyType(), residencyType, index);
		}else {
			IHtmlObject[] objs = browser.getHtmlObject(Property.concatPropertyArray(span(), ".text", new RegularExpression("^Residency Type.*", false)));
			if(objs.length<index+1){
				throw new ItemNotFoundException("Can't find Span which text starts with Residency type.");
			}else {
				browser.selectDropdownList(Property.toPropertyArray(".id", regx), residencyType, true, 0, objs[index]);
			}
			Browser.unregister(objs);
		}
	}
	
	public void selectScope(String scope){
		RegularExpression regx = new RegularExpression("DropdownExt-\\d+\\.selectedValue", false);
		Property[] p = Property.toPropertyArray(".id", regx);
		
		IHtmlObject[] objs = browser.getHtmlObject(Property.concatPropertyArray(span(), ".text", new RegularExpression("^Scope.*", false)));
		if(objs.length<1){
			throw new ItemNotFoundException("Can't find Span which text starts with Scope.");
		}else {
			if(!browser.getDropdownListValue(p, 0, objs[0]).equalsIgnoreCase(scope)){
				browser.selectDropdownList(p, scope, true, 0, objs[0]);
			}
		}
		Browser.unregister(objs);
	}
}
