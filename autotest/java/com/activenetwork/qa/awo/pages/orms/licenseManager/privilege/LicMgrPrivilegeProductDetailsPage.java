/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @ScriptName LicMgrEditPrivilegeDetailsPage.java
 * @Date:Mar 7, 2011
 * @Description:
 * @author asun
 */
public class LicMgrPrivilegeProductDetailsPage extends LicMgrPrivilegeProductCommonPage {
 
	private static LicMgrPrivilegeProductDetailsPage instance=null;
	
	protected LicMgrPrivilegeProductDetailsPage(){}
	
	public static LicMgrPrivilegeProductDetailsPage getInstance(){
		if(instance==null){
			instance=new LicMgrPrivilegeProductDetailsPage();
		}
		return instance;
	}
	
	protected Property[] editPrivPrdTab(){
		return Property.concatPropertyArray(div(), ".id", "tab_EditPrivilegeProduct");
	}
	
	protected Property[] questionsTab(){
		return Property.concatPropertyArray(a(), ".text", "Questions");
	}
	
	protected Property[] printDocumentsTab(){
		return Property.concatPropertyArray(a(), ".text", "Print Documents");
	}
	
	protected Property[] businessRuleTab(){
		return Property.concatPropertyArray(a(), ".text", "Business Rule");
	}
	
	protected Property[] landowner(){
		return Property.concatPropertyArray(a(), ".text", "Landowner");
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.DIV",".id","EditPrivilegeProduct");
	}
	
	public String getMessage() {
		String warnMes = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length>0){
			warnMes = objs[0].getProperty(".text").trim();
//		}else throw new ObjectNotFoundException("Object can't find.");
		}else {
			warnMes = null;
			logger.info("There isn't any error message!!");
		}

		Browser.unregister(objs);
		return warnMes;
	}
	
	/**
	 * Get the privilege product id
	 * @return
	 */
	public String getProductID() {
		return browser.getTextFieldValue(".id", new RegularExpression("PrivilegeProductView-\\d+\\.id", false)).trim();
	}
	
	public String getCode() {
		return browser.getTextFieldValue(".id", new RegularExpression("PrivilegeProductView-\\d+\\.code", false)).trim();
	}
	
	public String getProductName() {
		return browser.getTextFieldValue(".id", new RegularExpression("PrivilegeProductView-\\d+\\.name", false)).trim();
	}
	
	public String getProductGroup(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression("^PrivilegeProductView-\\d+\\.productGroup",false), 0);
	}
	
	public String getPromptIndicator(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression("^PrivilegeProductView-\\d+\\.validFromDate\\.promptIndicator", false), 0);
	}
	

	public String getValidDaysOrYears(){
		return browser.getTextFieldValue(".id", 
				new RegularExpression("^PrivilegeProductView-\\d+\\.validToDate\\.validDaysYears.*",false)).trim();
	}
	
	public String getDateUnitOfValidToDate(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression("^PrivilegeProductView-\\d+\\.validToDate\\.dateUnit",false), 0);
	}
	
	public String getRenewalDays(){
		return browser.getTextFieldValue(".id", 
				new RegularExpression("^PrivilegeProductView-\\d+\\.validToDate\\.renewalDays.*",false));
	}
	
	public String getValidToAge(){
		return browser.getTextFieldValue(".id", 
				new RegularExpression("^PrivilegeProductView-\\d+\\.validToDate\\.validToAge.*",false));
	}
	
	public boolean validDatePrintCheckBoxIsSelected(String validDatePrint){
		boolean isSelected;
		IHtmlObject[] printNameobj = browser.getHtmlObject(".class", "Html.SPAN", ".text", validDatePrint);
		Property[] p = new Property[1];
		p[0] = new Property(".id", new RegularExpression("^PrivilegeProductView-\\d+\\.validDatePrintingOptions.*",false));
		IHtmlObject[] printCheckBoxObj = browser.getCheckBox(p, printNameobj[0]);
		
		if(printCheckBoxObj[0].getProperty("status").equalsIgnoreCase("true")){
			isSelected = true;
		}else {
			isSelected = false;
		}
		
		Browser.unregister(printCheckBoxObj);
		Browser.unregister(printNameobj);
		
		return isSelected;
	}
	
	public boolean customerClassCheckBoxIsSelected(String customerClass){
		boolean isSelected;
		IHtmlObject[] customerClassNameObj = browser.getHtmlObject(".class", "Html.SPAN", ".text", customerClass);
		Property[] p = new Property[1];
		p[0] = new Property(".id", new RegularExpression("^PrivilegeProductView-\\d+\\.customerClasses.*",false));
		IHtmlObject[] customerClassCheckBoxObj = browser.getCheckBox(p, customerClassNameObj[0]);
		
		if(customerClassCheckBoxObj[0].getProperty("status").equalsIgnoreCase("true")){
			isSelected = true;
		}else {
			isSelected = false;
		}
		
		return isSelected;
	}
	
	public String getAuthorizationQuantity(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression("PrivilegeProductView-\\d+\\.authorizationQuantity",false), 0);
	}
	
	public String getInventoryType(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression("PrivilegeProductView-\\d+\\.inventoryType",false), 0);
	}
	
	public String getDisplayCategory(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression("PrivilegeProductView-\\d+\\.displayCategory",false), 0);
	}
	
	public String getDisplaySubCategory(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression("PrivilegeProductView-\\d+\\.displaySubCategory",false), 0);
	}
	
	public String getReportCategory(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression("PrivilegeProductView-\\d+\\.reportDisplayCategory",false), 0);
	}
	
	public void clickChangeHistory() {
		browser.clickGuiObject(".class", "Html.A",".text","Change History");
	
	}
	
	public void clickPricingTab() {
		browser.clickGuiObject(".class", "Html.SPAN",".text","Pricing");
	}
	
	public void clickStoreAssignmentsTab() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text", new RegularExpression("(Agent|Issuer|Location) Assignments", false)); //"Agent Assignments");
	}
	
	public void clickLicenseYearTab() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",".text", new RegularExpression("Licen(s|c)e Year", false));
		objs[objs.length-1].click();
		Browser.unregister(objs);
	}
	
	public void clickQuantityControlTab() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Quantity Control");
	}
	
	public void clickBusinessRuleTab() {
		browser.clickGuiObject(".class", "Html.SPAN",".text","Business Rule");
	}
	
	public void clickLandownerTab() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Landowner");
	}
	
	public void clickPrintDocumentsTab() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Print Documents");
	}
	
	public void clickQuestionsTab() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Questions");
	}
	
	public boolean isQuestionsTabExist(){
		return browser.checkHtmlObjectExists(Property.atList(editPrivPrdTab(), questionsTab()));
	}
	
	public void verifyQuestionsTabExist(boolean existed){
		boolean resultFfromUI = isQuestionsTabExist();
		if(resultFfromUI!=existed){
			throw new ErrorOnPageException("Questions tab should "+(existed?"":"not ")+" be existed");
		}
		logger.info("Successfully verify Question tab "+(existed?"exists":"doesn't exist"));
	}
	
	public boolean isPrintDocumentsTabExist(){
		return browser.checkHtmlObjectExists(Property.atList(editPrivPrdTab(), printDocumentsTab()));
	}
	
	public void verifyPrintDocumentsTabExist(boolean existed){
		boolean resultFfromUI = isPrintDocumentsTabExist();
		if(resultFfromUI!=existed){
			throw new ErrorOnPageException("Print Documents tab should "+(existed?"":"not ")+" be existed");
		}
		logger.info("Successfully verify Print Documents tab "+(existed?"exists":"doesn't exist"));
	}
	
	public boolean isBusinessRuleTabExist(){
		return browser.checkHtmlObjectExists(Property.atList(editPrivPrdTab(), businessRuleTab()));
	}
	
	public void verifyBusinessRuleTabExist(boolean existed){
		boolean resultFfromUI = isBusinessRuleTabExist();
		if(resultFfromUI!=existed){
			throw new ErrorOnPageException("Business Rule tab should "+(existed?"":"not ")+" be existed");
		}
		logger.info("Successfully verify Business Rule tab "+(existed?"exists":"doesn't exist"));
	}
	
	public boolean isLandownerExist(){
		return browser.checkHtmlObjectExists(Property.atList(editPrivPrdTab(), landowner()));
	}
	
	public void verifyLandownerExist(boolean existed){
		boolean resultFfromUI = isLandownerExist();
		if(resultFfromUI!=existed){
			throw new ErrorOnPageException("Landowner tab should "+(existed?"":"not ")+" be existed");
		}
		logger.info("Successfully verify Landowner tab "+(existed?"exists":"doesn't exist"));
	}
	
	public void clickTextDisplayTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Text Display");
	}
	
	public void clickContractorFeesTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Contractor Fees");
		ajax.waitLoading();
	}
	
	public void selectPrivilegeStatus(String status) {
		RegularExpression regx=new RegularExpression("PrivilegeProductView-\\d+\\.statusID",false);
		browser.selectDropdownList(".id", regx, status);
	}
	
	/**
	 * Update a privilege product's detail info
	 * @param privilege
	 * @return
	 */
	public String updatePrivilegeInfo(PrivilegeInfo privilege) {
		selectPrivilegeStatus(privilege.status);
		super.setupPrivilegeInfo(privilege);
		
		String status = this.getStatus();
	
		return status;
	}
	
	public boolean isProductGroupDisable(){
		RegularExpression regx=new RegularExpression("PrivilegeProductView-\\d+\\.productGroup",false);
		IHtmlObject[] objs=browser.getHtmlObject(".id", regx);
		boolean flag=false;
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find product group drop down list");
		}
		flag=Boolean.parseBoolean(objs[0].getProperty(".disabled"));
		
		return flag;
	}
	
	/**
	 * The method used to check privilege valid to date calculation rule matched given options
	 * @param fromDateCal-Valid From Date Calculation
	 * @param promptInd-Prompt Indicator
	 * @param toDateCal-Valid To Date Calculation
	 * @param validDaysOrYear-Valid Days/Years
	 * @param unit-to date unit
	 */
	public void checkAndPrepareDataForValidToDateCalculate(String fromDateCal,String promptInd,String toDateCal,String validDaysOrYear,String unit){
		logger.info("Check And Prepare Valid To Date Calculation Rule.");
		
		if(!this.getValidFromDateCalculation().equalsIgnoreCase(fromDateCal)){
			logger.info("Valid From Date Calculation not matched,update it to '"+fromDateCal+"'.");
			selectValidFromDateCalculation(fromDateCal);
			ajax.waitLoading();
		}
		if(promptInd!=null&&promptInd.length()>0&&!this.getPromptIndicator().equalsIgnoreCase(promptInd)){
			logger.info("Prompt Indicator not matched,update it to '"+promptInd+"'.");
			selectPromptIndicator(promptInd);
			ajax.waitLoading();
		}
		if(!this.getValidToDateCalculation().equalsIgnoreCase(toDateCal)){
			logger.info("Valid To Date Calculation not matched,update it to '"+toDateCal+"'.");
			selectValidToDateCalculation(toDateCal);
			ajax.waitLoading();
		}
		if(!this.getValidDaysOrYears().equalsIgnoreCase(validDaysOrYear)){
			logger.info("Valid Days Or Years not matched,update it to '"+validDaysOrYear+"'.");
			setValidDateOrYears(validDaysOrYear);
			ajax.waitLoading();
		}
		if(!this.getDateUnitOfValidToDate().equalsIgnoreCase(unit)){
			logger.info("Valid To Date Calculation Date Of Unit not matched,update it to '"+unit+"'.");
			selectDateUnitOfValidToDate(unit);
			ajax.waitLoading();
		}
		this.clickApply();
		ajax.waitLoading();
	}

	public boolean comparePrivilegeInfoIsCorrect(PrivilegeInfo privilege, boolean isInventoryPrdType){
		boolean result = true;
		String value = StringUtil.EMPTY;
		
		value = this.getCode();
		result &= MiscFunctions.compareResult("Privilege code", privilege.code, value);
		
		value = this.getPrivilegeName();
		result &= MiscFunctions.compareResult("privilege name", privilege.name, value);
		
		value = this.getPrdLegalName();
		result &= MiscFunctions.compareResult("privilege legal name", privilege.legalName, value);
		
		value = this.getProductGroup();
		result &= MiscFunctions.compareResult("product group", privilege.productGroup, value);
		
		value = this.getStatus();
		result &= MiscFunctions.compareResult("status", privilege.status, value);
		
		if(isInventoryPrdType){
			value = this.getInventoryQuantity();
			result &= MiscFunctions.compareResult("inventory quantity", privilege.inventoryQty, value);
			
			result &= MiscFunctions.compareResult("allow general public", privilege.allowGeneralPublic, this.isAllowGeneralPublicSelected());
		}else{
			if(isLandownerPriExist()){
				result &= MiscFunctions.compareResult("landowner licence", privilege.isLandowner, this.isLandownerPriSelected());
			}
			
			value = this.getValidFromDateCalculation();
			result &= MiscFunctions.compareResult("valid from date caculation", privilege.validFromDateCalculation, value);
			
			value = this.getPromptIndicator();
			result &= MiscFunctions.compareResult("prompt indicator", privilege.promptIndicator, value);
			
			value = this.getValidToDateCalculation();
			result &= MiscFunctions.compareResult("valid to date caculation", privilege.validToDateCalculation, value);
			
			value = this.getValidDaysOrYears();
			result &= MiscFunctions.compareResult("valid days or years", privilege.validDaysYears, value);
			
			value = this.getDateUnitOfValidToDate();
			result &= MiscFunctions.compareResult("date unit of valid to date", privilege.dateUnitOfValidToDate, value);
			
			value = this.getRenewalDays();
			result &= MiscFunctions.compareResult("renewalDay", privilege.renewalDays, value);
			
			value = this.getValidToAge();
			result &= MiscFunctions.compareResult("valid to age", privilege.validToAge, value);
			
			for(int i=0; i<privilege.validDatePrinting.length; i++){
				result &= MiscFunctions.compareResult("valid date print", true, this.validDatePrintCheckBoxIsSelected(privilege.validDatePrinting[i]));
			}
			
			value = this.getAuthorizationQuantity();
			result &= MiscFunctions.compareResult("authorizaiton quantity", privilege.authorizationQuantity, value);

			//If prd type as "Privileges" and Inventory type is not "none"
			if(StringUtil.notEmpty(privilege.inventoryQtyType)){
				value = this.getInvQuantityType();
				result &= MiscFunctions.compareResult("inventory qty type", privilege.inventoryQtyType, value);

				if(privilege.inventoryQtyType.equalsIgnoreCase("Fixed")){
					value = this.getInventoryQuantity();
					result &= MiscFunctions.compareResult("inventory qty", StringUtil.isEmpty(privilege.inventoryQty)?"1":privilege.inventoryQty, value);
				}else{
					value = this.getInventoryQuantityFrom();
					result &= MiscFunctions.compareResult("inventory qty from", StringUtil.isEmpty(privilege.inventoryQtyFrom)?"0":privilege.inventoryQtyFrom, value);
					value = this.getInventoryQuantityTo();
					result &= MiscFunctions.compareResult("inventory qty to", StringUtil.isEmpty(privilege.inventoryQtyTo)?"0":privilege.inventoryQtyTo, value);
				}
			}
		}
		
		for(int i=0; i<privilege.customerClasses.length; i++){
			result &= MiscFunctions.compareResult("customer class", true, this.customerClassCheckBoxIsSelected(privilege.customerClasses[i]));
		}
		
		value = this.getInventoryType();
		result &= MiscFunctions.compareResult("privilege inventory", privilege.invType, value);
		
		value = this.getDisplayCategory();
		result &= MiscFunctions.compareResult("privilege display category", privilege.displayCategory, value);
		
		value = this.getDisplaySubCategory();
		result &= MiscFunctions.compareResult("privilege display sub category", privilege.displaySubCategory, value);
		
		value = this.getReportCategory();
		result &= MiscFunctions.compareResult("privilege report category", privilege.reportCategory, value);
		
		value = this.getPricingNote();
		result &= MiscFunctions.compareResult("Pricing Note", privilege.priceNote, value);
		
		return result;
	}	
	
	public boolean comparePrivilegeInfoIsCorrect(PrivilegeInfo privilege){
		return comparePrivilegeInfoIsCorrect(privilege, false);
	}
	/**
	 * Get privilege info
	 * @param privilege
	 * @return
	 */
	public PrivilegeInfo getPrivilegeInfo(PrivilegeInfo privilege) {
		privilege.privilegeId = this.getProductID();
		privilege.code = this.getCode();
		privilege.name = this.getProductName();
		privilege.productGroup = this.getProductGroup();
		privilege.status = this.getStatus();
		privilege.customerClasses = this.getCustomerClassValues().toArray(new String[0]);
		privilege.authorizationQuantity = this.getAuthorizationQuantity();
		privilege.invType = this.getInventoryType();
		privilege.displayCategory = this.getDisplayCategory();
		privilege.displaySubCategory = this.getDisplaySubCategory();
		privilege.reportCategory = this.getReportCategory();
		
		return privilege;
	}
	
	public void changePrivilegeStatus(String status){
		LicMgrPrivilegesListPage listPg = LicMgrPrivilegesListPage.getInstance();
		this.selectPrivilegeStatus(status);
		this.clickOk();
		ajax.waitLoading();
		listPg.waitLoading();
	}
	/**
	 * select inventory type.
	 */
	public void selectInventoryType(String inventoryType){
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeProductView-\\d+\\.inventoryType",false),inventoryType);
	}
	/**
	 * update privileges details info.
	 * @param privilege
	 */
	public void updatePrivilegeDetailsInfo(PrivilegeInfo privilege){
		if(StringUtil.notEmpty(privilege.invType)){
			this.selectInventoryType(privilege.invType);
		}
		if(StringUtil.notEmpty(privilege.huntsRequired)){
			this.selectHuntsRequired(privilege.huntsRequired);
			ajax.waitLoading();
			this.waitLoading();
		}
		//To do Update other privileges info.
	}
	
	public boolean checkPrivlegeActive(){
		RegularExpression regx=new RegularExpression("PrivilegeProductView-\\d+\\.statusID",false);
		String status = browser.getDropdownListValue(".id", regx);
		if(status.equals(OrmsConstants.ACTIVE_STATUS)){
			return true;
		}else{
			return false;
		}
	}
	
	public void applyToSelectAllocationType(String type) {
		this.selectAllocationType(type);
		ajax.waitLoading();
		this.clickApply();
		ajax.waitLoading();
		this.waitLoading();
	}
}
