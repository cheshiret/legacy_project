/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrProductCommonPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @ScriptName LicMgrCreateNewPrivilegePage.java
 * @Date:Mar 7, 2011
 * @Description:This page is father class of LicMgrCreateNewPrivilegePage and LicMgrEditPrivilegeDetailsPage
 * Please don't extend it with any other page. 
 * @author asun
 */
public abstract class LicMgrPrivilegeProductCommonPage extends LicMgrProductCommonPage {
	
	protected Property[] allocationTypeList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("PrivilegeProductView-\\d+\\.allocationType", false));
	}
	
	protected Property[] allocationPrivList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("PrivilegeProductView-\\d+\\.allocationPrivilege", false));
	}
	
	protected Property[] prdCode(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.code", false));
	}
	
	protected Property[] prdName(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.name", false));
	}
	
	protected Property[] prdLegalName(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.productLegalName", false));
	}
	
	protected Property[] prdNum(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.privilegeProductNum", false));
	}
	
	protected Property[] prdStatus(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.statusID", false));
	}
	
	protected Property[] huntsRequired(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.applicationOnly", false));
	}
	
	public Property[] displayCategory(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.displayCategory",false));
	}
	
	public Property[] displaySubCategory(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.displaySubCategory",false));
	}
	
	public Property[] reportCategory(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.reportDisplayCategory",false));
	}
	
	public Property[] validFromDateCalculation(){
		return Property.toPropertyArray(".id", new RegularExpression("^PrivilegeProductView-\\d+\\.validFromDate\\.optionType",false));
	}
	
	public Property[] validToDateCalculation(){
		return Property.toPropertyArray(".id", new RegularExpression("^PrivilegeProductView-\\d+\\.validToDate\\.optionType",false));
	}
	
	public Property[] promptIndicator(){
		return Property.toPropertyArray(".id", new RegularExpression("^^PrivilegeProductView-\\d+\\.validFromDate\\.promptIndicator",false));
	}
	
	public Property[] validDateOrYears(){
		return Property.toPropertyArray(".id", new RegularExpression("^PrivilegeProductView-\\d+\\.validToDate\\.validDaysYears.*",false));
	}
	
	public Property[] dateUnitOfValidToDate(){
		return Property.toPropertyArray(".id", new RegularExpression("^PrivilegeProductView-\\d+\\.validToDate\\.dateUnit",false));
	}
	
	public Property[] renewalDays(){
		return Property.toPropertyArray(".id", new RegularExpression("^PrivilegeProductView-\\d+\\.validToDate\\.renewalDays.*",false));
	}
	
	public Property[] validToAge(){
		return Property.toPropertyArray(".id", new RegularExpression("^PrivilegeProductView-\\d+\\.validToDate\\.validToAge.*",false));
	}
	
	public Property[] validDatePrintingOptions(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.validDatePrintingOptions_\\d+", false));
	}
	
	public Property[] authorizationQuantity(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.authorizationQuantity",false));
	}
	
	public Property[] inventoryType(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.inventoryType",false));
	}
	
	public Property[] inventoryQuantity(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.inventoryQuantity",false));
	}
	
	public Property[] invQuantityType(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.invQuantityType",false));
	}
	
	protected Property[] landownerPri(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.landowner",false));
	}
	
	protected Property[] allowQuickSale(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.allowQuickSale",false));
	}
	
	protected Property[] consecutiveDaysInd(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.consecutiveDaysInd",false));
	}
	
	protected Property[] defaultToBlank(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.defaultToBlank",false));
	}
	
	protected Property[] soldAdBounsOnlyIndicator(){
		return Property.toPropertyArray(".id", new RegularExpression("LandownerConfigurationView-\\d+\\.onlySoldAsLandownerBonus",false));
	}
	
	protected Property[] pricingNote(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.pricingNote",false));
	}
	
	protected Property[] allowGeneralPublic(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.allowGeneralPublic",false));
	}
	
	protected Property[] displayOrder(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotterySalesSettingsView-\\d+\\.displayOrder",false));
	}
	
	protected Property[] inventoryQuantityFrom(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.inventoryQuantityFrom",false));
	}
	
	protected Property[] inventoryQuantityTo(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.inventoryQuantityTo",false));
	}
	
	public Property[] custClassTD(){
		return Property.concatPropertyArray(td(), ".text", new RegularExpression("^Customer Class.*",false));
	}
	
	public Property[] errorMsg(){
		return Property.concatPropertyArray(div(), ".className", "message msgerror");
	}
	
	protected Property[] prdGroup(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.productGroup", false));
	}
	
	public String getStatus(){
		return browser.getDropdownListValue(prdStatus(), 0);
	}
	
	public boolean isPrdStatusExist() {
		return browser.checkHtmlObjectExists(prdStatus());
	}
	
	public void setName(String name){
		browser.setTextField(prdName(), name);
	}
	
	public boolean isPrdNameExist() {
		return browser.checkHtmlObjectExists(prdName());
	}
	
	public boolean isPricingNoteExist() {
		return browser.checkHtmlObjectExists(pricingNote());
	}
	
	public String getPricingNote() {
		return browser.getTextFieldValue(pricingNote());
	}
	
	public void setPricingNote(String pricingNote){
		browser.setTextField(pricingNote(), pricingNote);
	}
	
	public boolean isPrdNumExist() {
		return browser.checkHtmlObjectExists(prdNum());
	}
	
	public boolean isAllowGeneralPublicExist() {
		return browser.checkHtmlObjectExists(allowGeneralPublic());
	}
	
	public void selectAllowGeneralPublic(){
		browser.selectCheckBox(allowGeneralPublic());
	}
	
	public void unSelectAllowGeneralPublic(){
		browser.unSelectCheckBox(allowGeneralPublic());
	}
	
	public boolean isAllowGeneralPublicSelected(){
		return browser.isCheckBoxSelected(allowGeneralPublic());
	}
	
	public boolean isDisplayOrderxist() {
		return browser.checkHtmlObjectExists(displayOrder());
	}
	
	public String getPrivilegeName(){
		return browser.getTextFieldValue(prdName());
	}
	
	public void setPrdLegalName(String name){
		browser.setTextField(prdLegalName(), name);
	}
	
	public String getPrdLegalName(){
		return browser.getTextFieldValue(prdLegalName());
	}
	
	public boolean isPrdLegalNameExist() {
		return browser.checkHtmlObjectExists(prdLegalName());
	}
	
	public void selectValidFromDateCalculation(String item){
	    browser.selectDropdownList(validFromDateCalculation(), item);
	}
	
	public boolean isValidFromDateCalculationExist() {
		return browser.checkHtmlObjectExists(validFromDateCalculation());
	}
	
	public boolean checkValidFromDateCalculationIsDisabled(){		
		return this.checkObjectIsDisabled(validFromDateCalculation());
	}
	
	public boolean checkPrdCodeDisabled(){
		return this.checkObjectIsDisabled(prdCode());
	}
	
	public boolean checkPrdGroupDisabled(){
		return this.checkObjectIsDisabled(prdGroup());
	}
	
	public String getErrorMsg(){
		return browser.getObjectText(errorMsg());
	}
	
	public boolean checkObjectIsDisabled(Property[] p){
		boolean objIsDisabled;
		String isDisabled = null;
		IHtmlObject[] obj = browser.getHtmlObject(p);

		try
		{
			isDisabled = obj[0].getProperty("isDisabled");
			if(isDisabled.equalsIgnoreCase("true")){
				objIsDisabled = true;
			}else {
				objIsDisabled = false;
			}
			Browser.unregister(obj);
			return objIsDisabled;
			
		} catch(Exception e) {
			//added by Peter Zhu, sometimes there is no property of "disabled" for a enabled input,
			//so we catch exception here, and treat it as a enable input
			objIsDisabled = false;
			Browser.unregister(obj);
			return objIsDisabled;
		}
	}
	
	public boolean isPromptIndicatorExist() {
		return browser.checkHtmlObjectExists(promptIndicator());
	}
	
	public boolean checkPromptIndicatorIsDisabled(){		
		return this.checkObjectIsDisabled(promptIndicator());
	}
	
	public void selectPromptIndicator(String promptIndicator){
		if(promptIndicator.trim().length()>0)
			browser.selectDropdownList(promptIndicator(), promptIndicator);
		else
			browser.selectDropdownList(promptIndicator(), 0);
	}
	
	public void selectValidToDateCalculation(String item){
	    browser.selectDropdownList(validToDateCalculation(), item);
	}
	
	public boolean checkValidToDateCalculationIsDisabled(){		
		return this.checkObjectIsDisabled(validToDateCalculation());
	}
	
	public boolean isValidToDateCalculationExist() {
		return browser.checkHtmlObjectExists(validToDateCalculation());
	}
	
	public void setVaildToDateCalculation(PrivilegeInfo pri){
		this.selectValidToDateCalculation(pri.validToDateCalculation);
		ajax.waitLoading();
		this.waitLoading();
		if(!this.checkValidDateOrYearsIsDisabled()){
			this.setValidDateOrYears(pri.validDaysYears);
			this.selectDateUnitOfValidToDate(pri.dateUnitOfValidToDate);
		}
		
		if(!this.checkRenewalDaysIsDisabled()){
			this.setRenewalDays(pri.renewalDays);
		}
		
		if(!this.checkValidToAgeIsDisable()){
			this.setValidToAge(pri.validToAge);
		}
	}
	
	public boolean isValidDateOrYearsExist() {
		return browser.checkHtmlObjectExists(validDateOrYears());
	}
	
	public boolean checkValidDateOrYearsIsDisabled(){		
		return this.checkObjectIsDisabled(validDateOrYears());
	}
	
	public void setValidDateOrYears(String validDateOrYears){
		browser.setTextField(validDateOrYears(), validDateOrYears);
	}
	
	public boolean isDateUnitOfValidToDateExist() {
		return browser.checkHtmlObjectExists(dateUnitOfValidToDate());
	}
	
	public boolean isOrdCodeExist() {
		return browser.checkHtmlObjectExists(dateUnitOfValidToDate());
	}
	
	public boolean checkDateUnitOfValidToDateIsDisabled(){	
		return this.checkObjectIsDisabled(dateUnitOfValidToDate());
	}
	
	public void selectDateUnitOfValidToDate(String dateUnit){
		browser.selectDropdownList(dateUnitOfValidToDate(), dateUnit);
	}
	
	public void selectDateUnitOfValidToDate(int index){
		browser.selectDropdownList(dateUnitOfValidToDate(), index);
	}
	
	public boolean isRenewalDaysExist() {
		return browser.checkHtmlObjectExists(renewalDays());
	}
	
	public boolean checkRenewalDaysIsDisabled(){
		return this.checkObjectIsDisabled(renewalDays());
	}
	
	public void setRenewalDays(String renewalDays){
		browser.setTextField(renewalDays(), renewalDays);
	}
	
	public boolean isValidToAgeExist() {
		return browser.checkHtmlObjectExists(validToAge());
	}
	
	public boolean checkValidToAgeIsDisable(){
		return this.checkObjectIsDisabled(validToAge());
	}
	
	public void setValidToAge(String validToAge){
		browser.setTextField(validToAge(), validToAge);
	}
	
	public void selectValidDatePrinting(String validDatePrint){
		IHtmlObject[] obj = browser.getHtmlObject(".className", "trailing", ".text",validDatePrint);
		obj[0].click();
		Browser.unregister(obj);
	}
	
	public void unSelectAllValidDatePrinting(){
	    IHtmlObject[] objs=browser.getCheckBox(validDatePrintingOptions());
	    if(objs.length<1) {
	    	throw new ObjectNotFoundException("Can't find Valid Date Printing checkbox object.");
	    }
	    for(int i=0;i<objs.length;i++) {
	    	browser.unSelectCheckBox(validDatePrintingOptions(), i);
	    }
	    Browser.unregister(objs);
	}
	
	public boolean isvalidDatePrintingOptionsExist() {
		return browser.checkHtmlObjectExists(validDatePrintingOptions());
	}
	
	/**
	 * Check specific customer class's check box
	 * @param customerclass
	 */
	public void selectCustomerClass(String customerclass){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.LABEL", ".className", "trailing");
		String htmlForStr = "";
		int counter = -1;
		for(int i = 0; i < objs.length; i ++) {
			if(null != objs[i].getProperty(".text") && objs[i].getProperty(".text").trim().length() > 0) {
				if(objs[i].getProperty(".text").trim().equalsIgnoreCase(customerclass.trim())) {
					htmlForStr = objs[i].getProperty("htmlFor").trim();
					counter = i;
					break;
				}
			}
		}
		
		if(counter == -1) {
			throw new ItemNotFoundException("Can't find customer class named - " + customerclass);
		}
		browser.selectCheckBox(".id", htmlForStr);
		
		Browser.unregister(objs);
	}

	public boolean isCustClassExist() {
		return browser.checkHtmlObjectExists(custClassTD());
	}
	
	/**
	 * Check some customer classes' check boxes
	 * @param customerClasses
	 */
	public void selectCustomerClasses(String customerClasses[]) {
		for(String customerclass : customerClasses) {
			if(null != customerclass && customerclass.length() > 0) {
				selectCustomerClass(customerclass);
			}
		}
	}
	
	public void unSelectAllCustomerClass(){
		RegularExpression regx=new RegularExpression("PrivilegeProductView-\\d+\\.customerClasses_\\d+",false);
	    IHtmlObject[] objs=browser.getCheckBox(".id", regx);
	    if(objs.length<1){
	    	throw new ObjectNotFoundException("Can't find customer class");
	    }
	    for(int i=0;i<objs.length;i++){
	    	browser.unSelectCheckBox(".id", regx, i);
	    }
	    Browser.unregister(objs);
	}
	
	public void selectAuthorizationQuantity(String item){
	    browser.selectDropdownList( authorizationQuantity(), item);
	}
	
	public boolean isAuthorizationQuantityExist() {
		return browser.checkHtmlObjectExists(authorizationQuantity());
	}
	
	public void selectInventoryType(String type){
		browser.selectDropdownList(inventoryType(), type, true);
	}
	
	public void selectInventoryType(int index){
		browser.selectDropdownList(inventoryType(), index, true);
	}
	
	public boolean isInventoryTypeExist() {
		return browser.checkHtmlObjectExists(inventoryType());
	}
	
	public boolean isInventoryQuantityExist() {
		return browser.checkHtmlObjectExists(inventoryQuantity());
	}
	
	public String getInventoryQuantity(){
		return browser.getTextFieldValue(inventoryQuantity());
	}
	
	public boolean isInvQuantityTypeExist() {
		return browser.checkHtmlObjectExists(invQuantityType());
	}
	
	public void selectInvQuantityType(String invQuantity){
		browser.selectDropdownList(invQuantityType(), invQuantity, true);
	}
	
	public List<String> getInvQuantityTypes(){
		return browser.getDropdownElements(invQuantityType());
	}
	
	public String getInvQuantityType(){
		return browser.getDropdownListValue(invQuantityType(), 0);
	}
	
	public void setInventoryQty(String qty) {
		browser.setTextField(inventoryQuantity(), qty);
	}
	
	public boolean isInventoryQuantityFromExist() {
		return browser.checkHtmlObjectExists(inventoryQuantityFrom());
	}
	
	public String getInventoryQuantityFrom(){
		return browser.getTextFieldValue(inventoryQuantityFrom());
	}
	
	public void setInventoryQuantityFrom(String inventoryQuantityFrom){
		 browser.setTextField(inventoryQuantityFrom(), inventoryQuantityFrom);
	}
	
	public boolean isInventoryQuantityToExist() {
		return browser.checkHtmlObjectExists(inventoryQuantityTo());
	}
	
	public String getInventoryQuantityTo(){
		return browser.getTextFieldValue(inventoryQuantityTo());
	}
	
	public void setInventoryQuantityTo(String inventoryQuantityTo){
		 browser.setTextField(inventoryQuantityTo(), inventoryQuantityTo);
	}
	
	public String getAllocationType() {
		return browser.getDropdownListValue(allocationTypeList(), 0);
	}
	
	public boolean isAllocationTypeExist() {
		return browser.checkHtmlObjectExists(allocationTypeList());
	}
	
	public void selectAllocationType(String type) {
		browser.selectDropdownList(allocationTypeList(), type);
	}
	
	public void selectAllocationPriv(String privCode) {
		IHtmlObject[] objs = browser.getDropdownList(allocationPrivList());
		if(objs.length == 0) {
			throw new ItemNotFoundException("Can't find any Allocation privilege drop down list objects.");
		}
		((ISelect)objs[0]).select(new RegularExpression("\\("+privCode+"\\).*", false));
		Browser.unregister(objs);
	}
	
	public boolean isAllocationPrivExist() {
		return browser.checkHtmlObjectExists(allocationPrivList());
	}
	
	public void selectDisplayCategory(String category){
	    browser.selectDropdownList(displayCategory(), category);
	}
	
	public boolean isDisplayCategoryExist() {
		return browser.checkHtmlObjectExists(displayCategory());
	}
	
	public void selectDisplaySubCategory(String subCategory){
	    browser.selectDropdownList(displaySubCategory(), subCategory);
	}
	
	public boolean isDisplaySubCategoryExist() {
		return browser.checkHtmlObjectExists(displaySubCategory());
	}
	
	public void selectReportCategory(String reportcategory){
	    browser.selectDropdownList(reportCategory(), reportcategory);
	}
	
	public boolean isReportCategoryExist() {
		return browser.checkHtmlObjectExists(reportCategory());
	}
	
	public void selectHuntsRequired(String indicator){
	    browser.selectDropdownList(huntsRequired(), indicator);
	}
	
	public boolean isHuntsRequiredExist() {
		return browser.checkHtmlObjectExists(huntsRequired());
	}
	
	public void clickOk(){
		browser.clickGuiObject(".class", "Html.A",".text","OK");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A",".text","Cancel");
	}
	
	public void clickApply(){
		browser.clickGuiObject(".class", "Html.A",".text","Apply");
	}
	
	public void selectLandownerPri(){
	    browser.selectCheckBox(landownerPri());
	}

	public boolean isLandownerPriExist() {
		return browser.checkHtmlObjectExists(landownerPri());
	}
	
	public boolean isLandownerPriSelected() {
		return browser.isCheckBoxSelected(landownerPri());
	}
	
	public void unSelectLandownerPri(){
	    browser.unSelectCheckBox(landownerPri());
	}

	public boolean isSoldAsBounsOnlyIndicatorExist() {
		return browser.checkHtmlObjectExists(soldAdBounsOnlyIndicator());
	}
	
	private Property[] partnerLicence() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.partnerPrivilege", false));
	}
	
	private Property[] associatedPartnerLicence1() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.associatedPartnerPrivilege1", false));
	}
	
	private Property[] associatedPartnerLicence2() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.associatedPartnerPrivilege2", false));
	}
	
	public boolean isPartnerLicenceExists() {
		return browser.checkHtmlObjectExists(partnerLicence());
	}
	
	public void selectPartnerLicence() {
		browser.selectCheckBox(partnerLicence());
	}
	
	public void selectAssociatedPartnerLicence1(String app) {
		browser.selectDropdownList(associatedPartnerLicence1(), app);
	}
	
	public void selectAssociatedPartnerLicence2(String app) {
		browser.selectDropdownList(associatedPartnerLicence2(), app);
	}
	
	/**
	 * Set up all privilege details information to create/update a privilege product
	 * @param privilege
	 */
	public void setupPrivilegeInfo(PrivilegeInfo privilege) {
		setName(privilege.name);
		
		//Sara[1282014]
		if(isPrdLegalNameExist() && StringUtil.notEmpty(privilege.legalName))
		setPrdLegalName(privilege.legalName);
		
		if(privilege.huntsRequired.trim().length() > 0) {
			this.selectHuntsRequired(privilege.huntsRequired);
			ajax.waitLoading();
		}
		
		// Nicole. Oct 18 start.
		if(privilege.isLandowner){
			selectLandownerPri();
		} else {
			unSelectLandownerPri();// default: privilege isn't landowner
		}
		// Nicole. Oct 18 end.
		
		// Jane[2014-4-21] add for setup script -- begin
		if(isAllowQuickSaleExist() && privilege.allowQuickSale) {
			selectAllowQuickSale();
			ajax.waitLoading();
		}
		// Jane[2014-4-21] add for setup script -- end
		
		//Quentin[20140528] add Partner Licence for setup script -- begin
		if(privilege.isPartnerLicence) {
			if(isPartnerLicenceExists()) {
				this.selectPartnerLicence();
			} else throw new ErrorOnPageException("Partner Licence checkbox shall exist.");
		}
		if(privilege.associatedPartnerLicences.size() > 0) {
			this.selectAssociatedPartnerLicence1(privilege.associatedPartnerLicences.get(0));
			
			if(privilege.associatedPartnerLicences.size() > 1) {
				this.selectAssociatedPartnerLicence2(privilege.associatedPartnerLicences.get(1));
			}
		}
		
		//Quentin[20140528] add Partner Licence for setup script -- end
		
		//Jane[2014-5-6] add for setup script -- start
		if(StringUtil.notEmpty(privilege.purchaseAuth)) {
			selectPurchaseAuthorization(privilege.purchaseAuth);
			ajax.waitLoading();
		}
		
		if(StringUtil.notEmpty(privilege.purchaseAuthType)) 
			selectPurchaseAuthorizationType(privilege.purchaseAuthType);
		
		if(StringUtil.notEmpty(privilege.authQtyPerLY))
			setAuthQtyPerLY(privilege.authQtyPerLY);
		//Jane[2014-5-6] add for setup script -- end
		
		if(privilege.checkBusiRuleInSaleFlow){
			selectCheckBusiRuleInSalesFlow();
		}else{
			unselectCheckBusiRuleInSalesFlow();
		}
		
		//Sara[2014-6-11] add for setup script -- start
		if(StringUtil.notEmpty(privilege.hunterHostLicenceType)){
			selectHunterHostLicenceType(privilege.hunterHostLicenceType);
			ajax.waitLoading();
		}
		
		if(StringUtil.notEmpty(privilege.requiredHunterHostPrivilege))
			selectRequiredHunterHostPrivilege(privilege.requiredHunterHostPrivilege);
		
		if(StringUtil.notEmpty(privilege.hunterHostAppendLicence))
			selectHunterHostAppendLicence(privilege.hunterHostAppendLicence);
		
		if(StringUtil.notEmpty(privilege.maxHuntersAllowed))
			setMaxHuntersAllowed(privilege.maxHuntersAllowed);
		//Sara[2014-6-11] add for setup script -- end
		
		if(privilege.validFromDateCalculation.trim().length() > 0) {
			selectValidFromDateCalculation(privilege.validFromDateCalculation);
			ajax.waitLoading();
		}
		
		if(this.isPromptIndicatorExist() && !checkPromptIndicatorIsDisabled()){
			selectPromptIndicator(privilege.promptIndicator);
			ajax.waitLoading();
		}
		
		if(privilege.validToDateCalculation.length() > 0) {
			selectValidToDateCalculation(privilege.validToDateCalculation);
			ajax.waitLoading();
		}
		
		if(isDefaultToBlankExist() && StringUtil.notEmpty(privilege.defaultToBlank))
			selectDefaultToBlank(privilege.defaultToBlank);
		
		if(this.isValidDateOrYearsExist() && !checkValidDateOrYearsIsDisabled()){
			setValidDateOrYears(privilege.validDaysYears);
		}
		
		if(this.isDateUnitOfValidToDateExist() && !checkDateUnitOfValidToDateIsDisabled()){
			if(privilege.dateUnitOfValidToDate.trim().length()>0){
				selectDateUnitOfValidToDate(privilege.dateUnitOfValidToDate);
			}else{
				//used to verify error message
				selectDateUnitOfValidToDate(0);
			}
			ajax.waitLoading();
		}
		
		if(isConsecutiveDaysIndExist() && StringUtil.notEmpty(privilege.consecutiveDaysInd))
			selectConsecutiveDaysInd(privilege.consecutiveDaysInd);
		
		if(this.isRenewalDaysExist() && !checkRenewalDaysIsDisabled()){
			setRenewalDays(privilege.renewalDays);
		}
		
		if(this.isValidToAgeExist() && !checkValidToAgeIsDisable()){
			setValidToAge(privilege.validToAge);
		}
		
		if(null != privilege.validDatePrinting && privilege.validDatePrinting.length>0){
			unSelectAllValidDatePrinting();
			for(int i=0; i<privilege.validDatePrinting.length; i++){
				selectValidDatePrinting(privilege.validDatePrinting[i]);
			}
		}
		
		if(null != privilege.customerClasses && privilege.customerClasses.length>0){
			unSelectAllCustomerClass();
			for(int i=0; i<privilege.customerClasses.length; i++){
				selectCustomerClass(privilege.customerClasses[i]);
			}
		}else{
			this.unSelectAllCustomerClass();
		}
		
		if(privilege.authorizationQuantity.length() > 0) {
			selectAuthorizationQuantity(privilege.authorizationQuantity);
		}
		if(privilege.invType.length() > 0) {
			selectInventoryType(privilege.invType);
			ajax.waitLoading();
		}
		
		if(isInvQuantityTypeExist() && StringUtil.notEmpty(privilege.inventoryQtyType)){
			selectInvQuantityType(privilege.inventoryQtyType);
			ajax.waitLoading();
		}
		
		if(privilege.inventoryQty.length() > 0) {
			setInventoryQty(privilege.inventoryQty);
		}
		
		//Sara[1282014] start
		if(isInventoryQuantityFromExist() && StringUtil.notEmpty(privilege.inventoryQtyFrom)) {
			setInventoryQuantityFrom(privilege.inventoryQtyFrom);
		}
		
		if(isInventoryQuantityToExist() && StringUtil.notEmpty(privilege.inventoryQtyTo)) {
			setInventoryQuantityTo(privilege.inventoryQtyTo);
		}
		
		if(isAllowGeneralPublicExist()){
			if(privilege.allowGeneralPublic){
				selectAllowGeneralPublic();
			}else unSelectAllowGeneralPublic();
		}
		//Sara[1282014] end
		
		if (privilege.allocationType.length() > 0) {
			selectAllocationType(privilege.allocationType);
			ajax.waitLoading();
		}
		
		if(isExtensionTypeExist() && StringUtil.notEmpty(privilege.extensionType)) {
			selectExtensionType(privilege.extensionType);
			ajax.waitLoading();
		}
		
		if (privilege.allocationPriv.length() > 0) {
			selectAllocationPriv(privilege.allocationPriv);
		}
		
		if(privilege.displayCategory.length() > 0) {
			selectDisplayCategory(privilege.displayCategory);
		}
		
		if(privilege.displaySubCategory.length() > 0) {
			selectDisplaySubCategory(privilege.displaySubCategory);
		}
		if(privilege.reportCategory.length() > 0) {
			selectReportCategory(privilege.reportCategory);
		}
		
		if(null !=privilege.huntsRequired && privilege.huntsRequired.length()>0){
			this.selectHuntsRequired(privilege.huntsRequired);
		}
           
		//Sara[1282014]
		if(isPricingNoteExist() && StringUtil.notEmpty(privilege.priceNote)){
			setPricingNote(privilege.priceNote);
		}
	}
	
	public boolean checkCodeTextIsDisabled(){
		boolean codeTextIsDisabled = this.checkObjectIsDisabled(prdCode());
		
		return codeTextIsDisabled;
	}
	
	public boolean checkNameTextIsDisabled(){
		boolean nameTextIsDisabled = this.checkObjectIsDisabled(prdName());
		
		return nameTextIsDisabled;
	}
	
	public boolean checkSatusIsDisabled(){
		boolean statusIsDisabled = this.checkObjectIsDisabled(prdStatus());
		
		return statusIsDisabled; 
	}
	
	public List<String> getDisplayCategoryElement(){
		List<String> displayCategoryElement = browser.getDropdownElements(".id", 
				new RegularExpression("^PrivilegeProductView-\\d+\\.displayCategory",false));
		
		return displayCategoryElement;
	}
	
	public List<String> getDisplaySubCategoryElement(){
		List<String> displaySubCategoryElement = browser.getDropdownElements(".id", 
				new RegularExpression("^PrivilegeProductView-\\d+\\.displaySubCategory",false));
		
		return displaySubCategoryElement;
	}
	
	public List<String> getReportCategoryElement(){
		List<String> reportCategoryElement = browser.getDropdownElements(".id", 
				new RegularExpression("^PrivilegeProductView-\\d+\\.reportDisplayCategory",false));
		
		return reportCategoryElement;
	}
	
	public List<String> getAuthorizationQuantityElement(){
		List<String> authorizationQuantityElement = browser.getDropdownElements(".id", 
				new RegularExpression("^PrivilegeProductView-\\d+\\.authorizationQuantity",false));
		
		return authorizationQuantityElement;
	}
	
	public List<String> getValidFromDateElement(){
		List<String> validFromDateElement = browser.getDropdownElements(validFromDateCalculation());
		
		return validFromDateElement;
	}
	
	public String getValidFromDateCalculation(){
		return browser.getDropdownListValue(validFromDateCalculation(), 0);
	}
	
	public List<String> getValidToDateElement(){
		return browser.getDropdownElements(validToDateCalculation());
	}
	
	public String getValidToDateCalculation(){
		return browser.getDropdownListValue(validToDateCalculation(), 0);
	}
	
	public String getPromptIndicator(){
		return browser.getDropdownListValue(promptIndicator(), 0);
	}
	
	public String getValidDateOrYears(){
		return browser.getTextFieldValue(validDateOrYears());
	}
	
	public String getRenewalDays(){
		return browser.getTextFieldValue(renewalDays());
	}
	
	public List<String> getValidDatePrintingValues(){
		List<String> validDatePrintingValue = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTableTestObject(".text", 
				new RegularExpression("^Valid Date Printing",false));
		Property[] p = new Property[1];
		p[0] = new Property(".className","trailing");
		
		IHtmlObject[] validDatePrintVauleObjs = browser.getHtmlObject(p, objs[0]);
		for(int i=0; i<validDatePrintVauleObjs.length; i++){
			validDatePrintingValue.add(validDatePrintVauleObjs[i].text());
		}
		Browser.unregister(objs);
		Browser.unregister(validDatePrintVauleObjs);
		return validDatePrintingValue;
	}
	
	public List<String> getInventoryTypeElement(){
		return browser.getDropdownElements(".id", 
				new RegularExpression("^PrivilegeProductView-\\d+\\.inventoryType",false));
	}
	
	public String getInventoryType(){
		return browser.getDropdownListValue(".id", new RegularExpression("^PrivilegeProductView-\\d+\\.inventoryType",false));
	}
	
	public List<String> getCustomerClassValues(){
		List<String> customerClassValue = new ArrayList<String>();
		IHtmlObject[] objs = browser.getTableTestObject(".text", 
				new RegularExpression("^Customer Class.*",false));
		Property[] p = new Property[1];
		p[0] = new Property(".className","trailing");
		
		IHtmlObject[] customerClassValueObjs = browser.getHtmlObject(p, objs[0]);
		for(int i=0; i<customerClassValueObjs.length; i++){
			customerClassValue.add(customerClassValueObjs[i].text());
		}
		
		Browser.unregister(customerClassValueObjs);
		Browser.unregister(objs);
		return customerClassValue;
	}
	
	public boolean isAllowQuickSaleExist() {
		return browser.checkHtmlObjectExists(allowQuickSale());
	}
	
	public void selectAllowQuickSale(){
	    browser.selectCheckBox(allowQuickSale());
	}

	public void unSelectAllowQuickSale(){
	    browser.unSelectCheckBox(allowQuickSale());
	}
	
	public boolean isConsecutiveDaysIndExist() {
		return browser.checkHtmlObjectExists(consecutiveDaysInd());
	}

	public void selectConsecutiveDaysInd(String consecutiveDaysInd){
		browser.selectDropdownList(consecutiveDaysInd(), consecutiveDaysInd);
	}
	
	public boolean isDefaultToBlankExist() {
		return browser.checkHtmlObjectExists(defaultToBlank());
	}

	public void selectDefaultToBlank(String defaultToBlank){
		browser.selectDropdownList(defaultToBlank(), defaultToBlank);
	}
	
	public void selectPurchaseAuthorization(String auth) {
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeProductView-\\d+\\.purchaseAuthorizationRequired", false), auth);
	}
	
	public void selectPurchaseAuthorizationType(String authType) {
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeProductView-\\d+\\.purchaseAuthorizationType", false), authType);
	}
	
	public void setAuthQtyPerLY(String qty) {
		browser.setTextField(".id", new RegularExpression("PrivilegeProductView-\\d+\\.purchaseAuthorizationQuantity", false), qty);
	}
	
	public void selectCheckBusiRuleInSalesFlow() {
		browser.selectCheckBox(".id", new RegularExpression("PrivilegeProductView-\\d+\\.checkBusinessRulesinSalesFlow", false));
	}
	
	public void unselectCheckBusiRuleInSalesFlow() {
		browser.unSelectCheckBox(".id", new RegularExpression("PrivilegeProductView-\\d+\\.checkBusinessRulesinSalesFlow", false));
	}
	
	public void selectHunterHostLicenceType(String hunterHostLicenceType){
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeProductView-\\d+\\.hunterHostPrivilegeType", false), hunterHostLicenceType);
	}
	
	public void selectHunterHostAppendLicence(String hunterHostAppendLicence){
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeProductView\\d+\\.hunterHostAppendPrivilege", false), hunterHostAppendLicence);
	}
	
	public void selectRequiredHunterHostPrivilege(String requiredHunterHostPrivilege){
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeProductView-\\d+\\.requiredHunterHostPrivilege", false), requiredHunterHostPrivilege);
	}
	
	public void setMaxHuntersAllowed(String maxHuntersAllowed){
		browser.setTextField(".id", new RegularExpression("PrivilegeProductView-\\d+\\.maxHuntersAllowed", false), maxHuntersAllowed);
	}
	
	public Property[] extensionTypeList(){
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeProductView-\\d+\\.extensionType",false));
	}
	
	public boolean isExtensionTypeExist() {
		return browser.checkHtmlObjectExists(extensionTypeList());
	}
	
	public void selectExtensionType(String type) {
		browser.selectDropdownList(extensionTypeList(), type);
	}
}
