package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 *
 * @ScriptName LicMgrPrivilegeEditRuleWidget.java
 * @Date:Mar 10, 2011
 * @Description:
 * @author asun
 */
public class LicMgrPrivilegeEditRuleWidget extends DialogWidget {
	private static LicMgrPrivilegeEditRuleWidget instance = null;

	private LicMgrPrivilegeEditRuleWidget() {
	}

	public static LicMgrPrivilegeEditRuleWidget getInstance() {
		if (instance == null) {
			instance = new LicMgrPrivilegeEditRuleWidget();
		}
		return instance;
	}

	protected Property[] errorMsgDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "NOTSET");
	}

	protected Property[] busiRuleSpan() {
		return Property.concatPropertyArray(this.span(), ".className", "inputwithrubylabel", ".text", new RegularExpression("^Business Rule.*", false));
	}
	
	protected Property[] ageSpan() {
		return Property.concatPropertyArray(this.span(), ".className", "inputwithrubylabel", ".text", new RegularExpression("^Age.*", false));
	}
	
	protected Property[] ruleParamValueObject() {
		return Property.toPropertyArray(".id", new RegularExpression("ProductRuleParameterValueBean-\\d+\\.value", false));
	}
	
	@Override
	public boolean exists() {
		boolean flag1 = super.exists();
		boolean flag2 = browser.checkHtmlObjectExists(".class", "Html.DIV",
				".text", new RegularExpression("Edit (Privilege|Licence) Business Rule", false));
		return flag1 && flag2;
	}

	public void selectStatus(String status) {
		RegularExpression regx = new RegularExpression(
				"ProductRuleView-\\d+\\.active", false);
		browser.selectDropdownList(".id", regx, status);
	}

	public void setEffectiveDate(String date) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^Effective Date.*", false));
		RegularExpression regx = new RegularExpression("ProductRuleParameterValueBean-\\d+\\.value_ForDisplay", false);
		browser.setTextField(".id", regx, date, objs[objs.length-1]);
		Browser.unregister(objs);
		
//		RegularExpression regx = new RegularExpression("ProductRuleParameterValueBean-\\d+\\.value_ForDisplay", false);
//		browser.setTextField(".id", regx, date, 0);
	}
	
	public void clickEffectiveDateLabel(){
		browser.clickGuiObject(".class", "Html.LABEL",".text",new RegularExpression("Effective Date.*",false));
	}

	public void setAge(String age) {
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		browser.setTextField(".id", regx, age, 1);
	}

	public boolean isAgeExist() {
//		RegularExpression regx = new RegularExpression("^Age.*", false);
//		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", regx);
		IHtmlObject[] objs = browser.getHtmlObject(ageSpan());
		if(objs.length > 0){
//			RegularExpression regx1 = new RegularExpression("ProductRuleParameterValueBean-\\d+\\.value", false);
			return browser.checkHtmlObjectExists(ruleParamValueObject(), objs[0]);
		} else {
			return false;
		}
	}

	public void setOnDate(String onDate) {
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value_ForDisplay", false);
		browser.setTextField(".id", regx, onDate, 1);
	}

	public boolean isOnDateExist() {
		RegularExpression regx = new RegularExpression("^On Date.*", false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				regx);
	}

	public void setRuleInfo(PrivilegeBusinessRule rule) {
		this.selectStatus(rule.status);
		if (rule.effectiveDate.trim().length() > 1) {
			this.setEffectiveDate(rule.effectiveDate);
		}
		if (rule.paramValue.split(",").length > 0
				&& rule.paramValue.split(",")[0].split(":")[1].trim().length() > 1) {
			if (this.isAgeExist())
				this.setAge(rule.paramValue.split(",")[0].split(":")[1].trim());
		}
		if (rule.paramValue.split(",").length > 1
				&& rule.paramValue.split(",")[1].split(":")[1].trim().length() > 1) {
			if (this.isOnDateExist())
				this.setOnDate(rule.paramValue.split(",")[1].split(":")[1]
						.trim());
		}
	}
	
	public String getErrorMessage() {
		String error = browser.getObjectText(errorMsgDiv());
		return error;
	}

	public void clickAddButton() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add");
		ajax.waitLoading();
	}

	public void selectPrivilege(String privilege, int object) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(Property.toPropertyArray(".id", "addProductRuleTable"), 
				Property.toPropertyArray(".class", "Html.SPAN", ".text", new RegularExpression("^Privilege Product.*", false))));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		browser.selectDropdownList(".id", regx, privilege, false, objs[object]);
	}

	public void selectWorkAction(String action) {
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		IHtmlObject[] objs = browser.getDropdownList(".id", regx);
		((ISelect) objs[objs.length - 1]).select(action);
		Browser.unregister(objs);
	}

	public void selectMatchLicenseYear(boolean match) {
		if (match) {
			browser.selectCheckBox(".id",
					"ProductRuleParameterValueBean-\\d+.value");
		} else {
			browser.unSelectCheckBox(".id",
					"ProductRuleParameterValueBean-\\d+.value");
		}
	}

	public void selectSuspensionType(String type, int object) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Suspension Type.*", false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		browser.selectDropdownList(".id", regx, type, false, objs[object]);
		Browser.unregister(objs);
	}

	public void selectEducationType(String type, int object) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Education Type.*", false));
		RegularExpression regx = new RegularExpression("(ProductRuleParameterValueBean-\\d+\\.value|DropdownExt-\\d+\\.selectedValue)", false);
		browser.selectDropdownList(".id", regx, type, false, objs[object]);
		Browser.unregister(objs);
	}

	public void selectCertificationType(String type, int object) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Certification Type.*", false));
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
		browser.selectDropdownList(".id", regx, location, false, objs[object]);
		Browser.unregister(objs);
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
	
	public void setPassNum(String num, int object) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Number of Bypass Allowed.*",
						false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		browser.setTextField(".id", regx, num, false, 0, objs[object]);
		Browser.unregister(objs);
	}

	public void setDateOfBirth(String num, int object) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",".text", new RegularExpression("^Date of Birth.*",false));
		RegularExpression regx = new RegularExpression("ProductRuleParameterValueBean-\\d+\\.value_ForDisplay", false);
		browser.setTextField(".id", regx, num, false, 0, objs[object]); 
		Browser.unregister(objs);
	}
	
	public void enterRuleInfo(PrivilegeBusinessRule ruleInfo) {
		this.selectStatus(ruleInfo.status);
		if (ruleInfo.parameters != null) {
				if ("Privilege Cross Reference".equals(ruleInfo.ruleCategory)) {
					for (int i = 0; i < ruleInfo.parameters.length; i++) {
						if (i > 0 && getRemoveButtonNum() - 1 != i) {
							clickAddButton();
						}
					selectPrivilege(ruleInfo.parameters[i].product, i);
					}
					if (isPurchaseTypeExist()) {
						selectPurchaseType(ruleInfo.parameters[0].purchaseType,0);
					}
					//Add by Jane
					if(!ruleInfo.parameters[0].postedToParas.isEmpty()){
						unSelectAllPostedToParameter();//clean up
						for(String para:ruleInfo.parameters[0].postedToParas){
							selectPostedToParameter(para);
						}
					}
				} else if ("Customer Demographic".equals(ruleInfo.ruleCategory)) {
					if (this.isOnDateExist()){
						setOnDate(ruleInfo.parameters[0].onDate);
					}
					setAge(ruleInfo.parameters[0].age);
					//Added by Jane
					if(!ruleInfo.parameters[0].residencyProofsParas.isEmpty()){
						selectAllResidencyProofsParameter();//clean up
						for(String para:ruleInfo.parameters[0].residencyProofsParas){
							unSelectResidencyProofsParameter(para);
						}
					}
				} else if ("Suspension/Revocation".equals(ruleInfo.ruleCategory)) {
					selectSuspensionType(ruleInfo.parameters[0].suspensionType,0);
				}else {
					if (isEducationTypeExist()) {
						selectEducationType(ruleInfo.parameters[0].educationType, 0);
						ajax.waitLoading();
					}
					if (isLocationClassExist()) {
						selectLocationClass(ruleInfo.parameters[0].locationClass, 0);
					}
					if (isCertificationTypeExist()) {
						selectCertificationType(ruleInfo.parameters[0].certificationType, 0);
					}
					if (isPassNumExist()) {
						setPassNum(ruleInfo.parameters[0].passNum, 0);
					}
					if(isDateOfBirthExist() && StringUtil.notEmpty(ruleInfo.parameters[0].dateOfBirth)){
						setDateOfBirth(ruleInfo.parameters[0].dateOfBirth, 0);
					}
				}
			}
		if(Boolean.TRUE.equals(ruleInfo.parameters[0].matchLicYear) || Boolean.FALSE.equals(ruleInfo.parameters[0].matchLicYear)) {
			this.selectMatchLicenseYear(ruleInfo.parameters[0].matchLicYear);
		}
		if (ruleInfo.workflowAction != null
				&& ruleInfo.workflowAction.length() > 0) {
			this.selectWorkAction(ruleInfo.workflowAction);
		}

		/*
		 * Keep this the last one to be set, the date component overlay will cover
		 * other component under it, if action happens (like setText) on these component,
		 * sometimes the overlay is clicked and wrong date will be set.
		 */
		this.setEffectiveDate(ruleInfo.effectiveDate);
		this.clickEffectiveDateLabel();
	}

	public String getRuleId() {
		RegularExpression regx = new RegularExpression(
				"ProductRuleView-\\d+\\.id", false);
		String id = browser.getTextFieldValue(".id", regx);
		return id;
	}

	public String getRuleStatus() {
		RegularExpression regx = new RegularExpression(
				"ProductRuleView-\\d+\\.active", false);
		String status = browser.getDropdownListValue(".id", regx, 0);
		return status;
	}

	public String getRuleCategory() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Rule Category.*", false));
		RegularExpression regx = new RegularExpression(
				"DropdownExt-\\d+\\.selectedValue", false);
		IHtmlObject[] objs1=browser.getDropdownList(new Property[]{new Property(".id", regx)}, objs[0]);
		ISelect drop=(ISelect)objs1[0];
		String category =drop.getSelectedText();

		Browser.unregister(objs);
		Browser.unregister(objs1);
		return category;
	}

	public String getRuleType() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Business Rule.*", false));
		RegularExpression regx = new RegularExpression(
				"DropdownExt-\\d+\\.selectedValue", false);
		IHtmlObject[] objs1=browser.getDropdownList(new Property[]{new Property(".id", regx)}, objs[1]);
		ISelect drop=(ISelect)objs1[0];
		String type =drop.getSelectedText();

		Browser.unregister(objs);
		Browser.unregister(objs1);
		return type;
	}
	
	public String getEffectiveDate() {
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value_ForDisplay", false);
		String date = browser.getTextFieldValue(".id", regx);
		return date;
	}

	public boolean verifyEffectiveDate(String[] invalidDates) {
		Property pro[] = new Property[2];
		pro[0] = new Property(".class", "Html.INPUT.text");
		pro[1] = new Property(".id", new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value_ForDisplay", false));
		return verifyAutomaticDateCorrection((IText)browser.getTextField(pro)[0], invalidDates);
	}

	public String getPrivilegeProduct() {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(Property.toPropertyArray(".id", "addProductRuleTable"), 
				Property.toPropertyArray(".class", "Html.SPAN", ".text", new RegularExpression("^Privilege Product.*", false))));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		IHtmlObject[] objs1=browser.getDropdownList(new Property[]{new Property(".id", regx)}, objs[0]);
		ISelect drop=(ISelect)objs1[0];
		String product =drop.getSelectedText();

		Browser.unregister(objs);
		Browser.unregister(objs1);
		return product;
	}

	public String getPurchaseType() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Purchase Type.*", false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		IHtmlObject[] objs1=browser.getDropdownList(new Property[]{new Property(".id", regx)}, objs[0]);
		ISelect drop=(ISelect)objs1[0];
		String type =drop.getSelectedText();

		Browser.unregister(objs);
		Browser.unregister(objs1);
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

	public boolean isPurchaseTypeExist() {
		RegularExpression regx = new RegularExpression("^Purchase Type.*",
				false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				regx);
	}

	public String getWorkFlowAction() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Work Flow Action.*", false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		IHtmlObject[] objs1=browser.getDropdownList(new Property[]{new Property(".id", regx)}, objs[0]);
		ISelect drop=(ISelect)objs1[0];
		String action =drop.getSelectedText();

		Browser.unregister(objs);
		Browser.unregister(objs1);
		return action;
	}

	public String getLocationClass() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Location Class.*", false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		IHtmlObject[] objs1=browser.getDropdownList(new Property[]{new Property(".id", regx)}, objs[0]);
		ISelect drop=(ISelect)objs1[0];
		String loc =drop.getSelectedText();

		Browser.unregister(objs);
		Browser.unregister(objs1);
		return loc;
	}

	public boolean isWorkActionExist() {
		RegularExpression regx = new RegularExpression("^Work Flow Action.*",
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

	public boolean isEducationTypeExist() {
		RegularExpression regx = new RegularExpression("^Education Type.*", false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				regx);
	}

	public boolean isPassNumberExist() {
		RegularExpression regx = new RegularExpression("^Number of Bypass Allowed.*", false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				regx);
	}

	public boolean isCertificationTypeExist() {
		RegularExpression regx = new RegularExpression("^Certification Type.*", false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				regx);
	}

	public String getSuspensionType() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Suspension Type.*", false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		IHtmlObject[] objs1=browser.getDropdownList(new Property[]{new Property(".id", regx)}, objs[0]);
		ISelect drop=(ISelect)objs1[0];
		String type =drop.getSelectedText();

		Browser.unregister(objs);
		Browser.unregister(objs1);
		return type;
	}

	public String getEducationType() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Education Type.*", false));
		RegularExpression regx = new RegularExpression(
				"DropdownExt-\\d+\\.selectedValue", false);
		IHtmlObject[] objs1=browser.getDropdownList(new Property[]{new Property(".id", regx)}, objs[0]);
		ISelect drop=(ISelect)objs1[0];
		String type =drop.getSelectedText();

		Browser.unregister(objs);
		Browser.unregister(objs1);
		return type;
	}

	public String getCertificationType() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Certification Type.*", false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		IHtmlObject[] objs1=browser.getDropdownList(new Property[]{new Property(".id", regx)}, objs[0]);
		ISelect drop=(ISelect)objs1[0];
		String type =drop.getSelectedText();

		Browser.unregister(objs);
		Browser.unregister(objs1);
		return type;
	}

	public String getPassNumber() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Number of Bypass Allowed.*", false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);

		String num = browser.getTextFieldValue(new Property[]{new Property(".id", regx)}, objs[0]);
		Browser.unregister(objs);
		return num;
	}

	public String getAge() {
//		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
//				".text", new RegularExpression("^Age.*", false));
		IHtmlObject[] objs = browser.getHtmlObject(ageSpan());
		if(objs.length>0){
//			RegularExpression regx = new RegularExpression(
//					"ProductRuleParameterValueBean-\\d+\\.value", false);
//			String age = browser.getTextFieldValue(new Property[]{new Property(".id", regx)}, objs[0]);
			String age = browser.getTextFieldValue(ruleParamValueObject(), objs[0]);
			Browser.unregister(objs);
			return age;
		} else {
			logger.error("There isn't Age object!");// Don't throw exception!
			return StringUtil.EMPTY;
		}
	}

	public String getOnDate() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^On Date.*", false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false);
		String date = browser.getTextFieldValue(new Property[]{new Property(".id", regx)}, objs[0]);
		Browser.unregister(objs);
		return date;
	}

	public boolean verifyOnDate(String[] invalidDates) {
		Property pro[] = new Property[2];
		pro[0] = new Property(".class", "Html.INPUT.text");
		pro[1] = new Property(".id", new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.value", false));
		return verifyAutomaticDateCorrection((IText)browser.getTextField(pro)[0], invalidDates);
	}

	public PrivilegeBusinessRule getBusinessRuleDetailInformation() {
		PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();

		ruleInfo.id = this.getRuleId();
		ruleInfo.status = this.getRuleStatus();
		ruleInfo.ruleCategory = this.getRuleCategory();
		ruleInfo.name = this.getRuleType();
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].effectiveDate = this.getEffectiveDate();

		if ("Customer Demographic".equals(ruleInfo.ruleCategory)) {
			if (this.isAgeExist()) {
				ruleInfo.parameters[0].age = this.getAge();
			}
			if (this.isOnDateExist()) {
				ruleInfo.parameters[0].onDate = this.getOnDate();
			}
			ruleInfo.parameters[0].workAction = this.getWorkFlowAction();
			if(this.isResidencyProofsParametersExist()) {
				ruleInfo.parameters[0].residencyProofsParas = this.getUnselectedResidencyProofsParameters();
			}
		}
		if ("Suspension/Revocation".equals(ruleInfo.ruleCategory)) {
			ruleInfo.parameters[0].suspensionType = this.getSuspensionType();
			if(isWorkActionExist()){
				ruleInfo.parameters[0].workAction = this.getWorkFlowAction();
			}
		}
		if ("Privilege Cross Reference".equals(ruleInfo.ruleCategory)) {
			ruleInfo.parameters[0].product = this.getPrivilegeProduct();
			if (this.isPurchaseTypeExist()) {
				ruleInfo.parameters[0].purchaseType = this.getPurchaseType();
			}
			if (this.isWorkActionExist()) {
				ruleInfo.parameters[0].workAction = this.getWorkFlowAction();
			}
			if(this.isPostedToParametersExist()) {
				ruleInfo.parameters[0].postedToParas = this.getPostedToParameters();
			}
		}
		if ("Education/Certification Enforcement".equals(ruleInfo.ruleCategory)) {
			if(this.isEducationTypeExist()){
				ruleInfo.parameters[0].educationType = this.getEducationType();
			}
			//Vivian due to UI is changed by DEFECT-64298, and currently can not  get location class drop down list selected elements
//			if(this.isLocationClassExist()){
//				ruleInfo.parameters[0].locationClass = this.getLocationClass();
//			}
			if(this.isPassNumberExist()){
				ruleInfo.parameters[0].passNum = this.getPassNumber();
			}
			if(this.isCertificationTypeExist()){
				ruleInfo.parameters[0].certificationType = this.getCertificationType();
			}
			if (this.isWorkActionExist()) {
				ruleInfo.parameters[0].workAction = this.getWorkFlowAction();
			}
		}

		return ruleInfo;
	}

	public int getRemoveButtonNum() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Remove");

		Browser.unregister(objs);
		return objs.length;
	}

	public boolean checkRuleIdEditAble() {
		return !browser.checkHtmlObjectExists(".id", new RegularExpression(
				"ProductRuleView-\\d+.id", false), ".className", "readonly");
	}

	public boolean checkRuleNameEditAble() {
//		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.SPAN", ".text",
//				new RegularExpression("^Business Rule.*", false));
		IHtmlObject[] obj = browser.getHtmlObject(busiRuleSpan());
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression(
				"DropdownExt-\\d+.selectedValue", false), obj[0]);

		String str = objs[0].getAttributeValue(".disabled");
		Browser.unregister(obj);
		Browser.unregister(objs);
		return !Boolean.parseBoolean(str);
	}

	public String getLastUpdateUser() {
		RegularExpression lastUpdateUserRegx = new RegularExpression(
				"ProductRuleView-\\d+.lastUpdateUser", false);
		IHtmlObject[] objs = browser.getHtmlObject(".id", lastUpdateUserRegx);
		String user = objs[0].getProperty(".text");
		Browser.unregister(objs);
		return user.split("Last Update User")[1].trim().replace(", ", ",");
	}

	public String getLastUpdateLocation() {
		RegularExpression lastUpdateLocationRegx = new RegularExpression(
				"ProductRuleView-\\d+.lastUpdateLocation", false);
		IHtmlObject[] objs = browser
				.getHtmlObject(".id", lastUpdateLocationRegx);
		String user = objs[0].getProperty(".text");
		Browser.unregister(objs);
		return user.split("Last Update Location")[1].trim();
	}

	public String getLastUpdateTime() {
		RegularExpression lastUpdateTimeRegx = new RegularExpression(
				"ProductRuleView-\\d+.lastUpdateTime:LOCAL_TIME", false);
		IHtmlObject[] objs = browser.getHtmlObject(".id", lastUpdateTimeRegx);
		String user = objs[0].getProperty(".text");
		Browser.unregister(objs);
		return user.split("Last Update Time")[1].trim();
	}

	public boolean isPostedToParametersExist() {
		RegularExpression regx = new RegularExpression("^(Valid From Date|Valid To Date|Valid From Time|Valid To Time|Privilege Number|Inventory Number)$", false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", regx);
	}

	public List<String> getPostedToParameters() {
		List<String> paras = new ArrayList<String>();

		for(String para:Arrays.asList(OrmsConstants.postedToString)){
			IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^"+para+"$", true));
			RegularExpression regx = new RegularExpression(
					"ProductRuleParameterValueBean-\\d+\\.stringValueList_\\d+", false);
			IHtmlObject[] objs1 = browser.getHtmlObject(".class","Html.INPUT.checkbox",".id", regx, objs[0]);
			if(((ICheckBox)objs1[0]).isSelected()){
				paras.add(para);
			}
			Browser.unregister(objs1);
			Browser.unregister(objs);
		}

		return paras;
	}

	public boolean isResidencyProofsParametersExist() {
		RegularExpression regx = new RegularExpression("^(Homestead|MDWFP Approved|Military|State Income Tax Document|Student ID|Utility Bill/Lease|Youth < 19).*", false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", regx);
	}

	public List<String> getUnselectedResidencyProofsParameters() {
		List<String> paras = new ArrayList<String>();

		for(String para:Arrays.asList(OrmsConstants.residencyProofsString)){
			IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^"+para+"$", true));
			RegularExpression regx = new RegularExpression(
					"ProductRuleParameterValueBean-\\d+\\.stringValueList_\\d+", false);
			IHtmlObject[] objs1 = browser.getHtmlObject(".class","Html.INPUT.checkbox",".id", regx, objs[0]);
			if(!((ICheckBox)objs1[0]).isSelected()){
				paras.add(para);
			}
			Browser.unregister(objs1);
			Browser.unregister(objs);
		}

		return paras;
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

	public void selectPostedToParameter(String para){
		if(null == para || !Arrays.asList(OrmsConstants.postedToString).contains(para)){
			throw new ErrorOnDataException("Please check posted to parameter");
		}

		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^"+para+".*", false));
		RegularExpression regx = new RegularExpression(
				"ProductRuleParameterValueBean-\\d+\\.stringValueList_\\d+", false);
		IHtmlObject[] objs1 = browser.getHtmlObject(".class","Html.INPUT.checkbox",".id", regx);
		browser.selectCheckBox(".class","Html.INPUT.checkbox", ".id", regx, 0, true, objs[0]);

		Browser.unregister(objs1);
		Browser.unregister(objs);
	}

	public void unSelectAllPostedToParameter(){
		List<String> paras = Arrays.asList(OrmsConstants.postedToString);

		for(String para:paras){
			IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^"+para+".*", false));
			RegularExpression regx = new RegularExpression(
					"ProductRuleParameterValueBean-\\d+\\.stringValueList_\\d+", false);
			Property[] property = new Property[2];
			property[0] = new Property(".class","Html.INPUT.checkbox");
			property[1] = new Property(".id", regx);
			browser.unSelectCheckBox(property, 0, objs[0]);

			Browser.unregister(objs);
		}
	}

	public void selectAllResidencyProofsParameter(){
		List<String> paras = Arrays.asList(OrmsConstants.residencyProofsString);

		for(String para:paras){
			IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^"+para+".*", false));
			RegularExpression regx = new RegularExpression(
					"ProductRuleParameterValueBean-\\d+\\.stringValueList_\\d+", false);
			IHtmlObject[] objs1 = browser.getHtmlObject(".class","Html.INPUT.checkbox",".id", regx);
			browser.selectCheckBox(".class","Html.INPUT.checkbox", ".id", regx, 0, true, objs[0]);

			Browser.unregister(objs1);
			Browser.unregister(objs);
		}
	}

	public void waitErrorMsgExist() {
//		Property[] ps = new Property[2];
//		ps[0] = new Property(".class", "Html.SPAN");
//		ps[1] = new Property(".id", "NOTSET");

		browser.waitExists(errorMsgDiv());
	}
}
