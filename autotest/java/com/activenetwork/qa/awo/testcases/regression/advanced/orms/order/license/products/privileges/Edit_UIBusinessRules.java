/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrCreateNewPriviledgePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This case used to verify business rule when edit privilege product
 * @Preconditions:
 * @SPEC:Edit Privilege Product.doc
 * @Task#:AUTO-670
 * 
 * @author asun
 * @Date  Aug 5, 2011
 */
public class Edit_UIBusinessRules extends LicenseManagerTestCase {
	
	private List<String> validFromDateExpected = new ArrayList<String>();
	private List<String> validToDateExprected = new ArrayList<String>();
	private List<String> validDatePrintingValuesExpected = new ArrayList<String>();
	private List<String> customerClassValuesExpected = new ArrayList<String>();
	private List<String> authorizationQuntityExpected = new ArrayList<String>();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		this.verifyBusinessRule();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		privilege.code="A9";
		
		authorizationQuntityExpected.add("Return as Individual Privileges");
		authorizationQuntityExpected.add("Return as Single Privilege with Quantity");
		
		validFromDateExpected.add("Based on Priv LY Record or Purchase Date (If Greater)");
		validFromDateExpected.add("Prompt for Valid From Date");
		validFromDateExpected.add("Prompt for Valid From Date and Time");
		
		validToDateExprected.add("Based on Priv License Year Record");
		validToDateExprected.add("Valid From Date plus Valid Days/Years");
		validToDateExprected.add("Valid To Date of Previous License plus Valid Days/Years");
		validToDateExprected.add("Expires when the Customer reaches Valid To Age");
		
		validDatePrintingValuesExpected.add("Print Valid From Date");
		validDatePrintingValuesExpected.add("Print Valid To Date");
		validDatePrintingValuesExpected.add("Print Valid From Time");
		validDatePrintingValuesExpected.add("Print Valid To Time");
		
		customerClassValuesExpected.add("Individual");
		customerClassValuesExpected.add("Business");
	}
	
	public void verifyBusinessRule(){
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		
		if(!privilegeDetailsPg.checkCodeTextIsDisabled()){
			throw new ErrorOnPageException("Code should be disabled.");
		}
		
		if(!privilegeDetailsPg.isProductGroupDisable()){
			throw new ErrorOnPageException("Product Group should be disabled.");
		}
		this.verifyCustomerClass();
		this.verifyDisplayCategory();
		this.verifyInventoryType();
		this.verifyReportCategory();
		this.verifySubCategory();
		this.verifyVaildDatePrintingValue();
		this.verifyAuthorizationQuantity();
		this.verifyVaildToDate();
		this.verifyValidFromDate();
	}
	
	/**
	 * verify authorization quantity list value
	 */
	public void verifyAuthorizationQuantity(){
		LicMgrCreateNewPriviledgePage createNewPrivilegeProductPage = LicMgrCreateNewPriviledgePage.getInstance();
		List<String> authorizationQuantityFromUI = createNewPrivilegeProductPage.getAuthorizationQuantityElement();
		if(authorizationQuntityExpected.size() != authorizationQuantityFromUI.size()){
			throw new ErrorOnPageException("Authorzation quantity list is not correct.");
		}else {
			for(int i=0; i<authorizationQuntityExpected.size(); i++){
				if(!authorizationQuntityExpected.get(i).equals(authorizationQuantityFromUI.get(i))){
					throw new ErrorOnPageException("The authorization quantity order is not correct.");
				}
			}
		}
	}
	
	/**
	 * verify display category drop down list value and sorted
	 * @return
	 */
	public void verifyDisplayCategory(){
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		List<String> displayCategoryFromDB = lm.getDescriptionOfDisplayCategory("Display Category", schema);
		List<String> displayCategoryFromUI = privilegeDetailsPg.getDisplayCategoryElement();		
		if(displayCategoryFromDB.size() != displayCategoryFromUI.size()){
			
			throw new ErrorOnPageException("Display category list is not correct.");
		}else {
			for(int i=0; i<displayCategoryFromDB.size(); i++){
				if(!displayCategoryFromDB.get(i).equals(displayCategoryFromUI.get(i))){
					throw new ErrorOnPageException("The display category order is not correct.");
				}
			}
		}
	}
	
	/*
	 * verify display sub category drop down list value and sorted
	 */
	public void verifySubCategory(){
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		List<String> displaySubCategoryFromDB = lm.getDescriptionOfDisplayCategory("Display Sub Category", schema);
		List<String> displaySubCategoryFromUI = privilegeDetailsPg.getDisplaySubCategoryElement();
		if(displaySubCategoryFromUI.get(0).trim().equalsIgnoreCase("")){
			displaySubCategoryFromUI.remove(0);
		}
		if(displaySubCategoryFromDB.size() != displaySubCategoryFromUI.size()){
			throw new ErrorOnPageException("Display Sub Category list is not correct.");
		}else {
			for(int i=0; i<displaySubCategoryFromDB.size(); i++){
				if(!displaySubCategoryFromDB.get(i).trim().equals(displaySubCategoryFromUI.get(i).trim())){
					throw new ErrorOnPageException("The display sub category order is not correct.");
				}
			}
		}
	}
	
	/**
	 * verify report category list value and sorted
	 */
	public void verifyReportCategory(){
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		List<String> reportCategoryFromDB = lm.getDescriptionOfDisplayCategory("Report Category", schema);
		List<String> reportCategoryFromUI = privilegeDetailsPg.getReportCategoryElement();
		if(reportCategoryFromDB.size() != reportCategoryFromUI.size()){
			throw new ErrorOnPageException("Report Category list is not correct.");
		}else {
			for(int i=0; i<reportCategoryFromDB.size(); i++){
				if(!reportCategoryFromDB.get(i).trim().equals(reportCategoryFromUI.get(i).trim())){
					throw new ErrorOnPageException("The report category order is not correct.");
				}
			}
		}
	}
	
	/**
	 * verify valid from date drop down list value and business rule
	 */
	public void verifyValidFromDate(){
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		List<String> validFromDateFromUI = privilegeDetailsPg.getValidFromDateElement();
		if(validFromDateExpected.size() != validFromDateFromUI.size()){
			throw new ErrorOnPageException("Valid From Date list is not correct.");
		}else {
			for(int i=0; i<validFromDateExpected.size(); i++){
				if(!validFromDateExpected.get(i).equals(validFromDateFromUI.get(i))){
					throw new ErrorOnPageException("The valid from date order is not correct.");
				}
			}
		}
		//Based on Priv LY Record or Purchase Date (If Greater) should be set as default
		if(!privilegeDetailsPg.getValidFromDateCalculation().equals("Based on Priv LY Record or Purchase Date (If Greater)")){
			throw new ErrorOnPageException("Based on Priv LY Record or Purchase Date (If Greater) " +
					"should be set as the default for valid from date.");
		}
		if(!privilegeDetailsPg.checkPromptIndicatorIsDisabled()){
			throw new ErrorOnPageException("Prompt indicator could not edit " +
					"when 'Based on Priv LY Record or Purchase Date (If Greater)' is selected.");
		}	
		//verify business rule when Prompt for Valid From Date option is selected
		privilegeDetailsPg.selectValidFromDateCalculation("Prompt for Valid From Date");
		ajax.waitLoading();
		if(privilegeDetailsPg.checkPromptIndicatorIsDisabled()){
			throw new ErrorOnPageException("Prompt indicator could edit when 'Prompt for Valid From Date' is selected.");
		}
		privilegeDetailsPg.selectValidFromDateCalculation("Prompt for Valid From Date and Time");
		ajax.waitLoading();
		if(privilegeDetailsPg.checkPromptIndicatorIsDisabled()){
			throw new ErrorOnPageException("Prompt indicator could edit when 'Prompt for Valid From Date and Time' is selected.");
		}
	}
	
	/**
	 * verify valid to date drop down list and business rule
	 */
	public void verifyVaildToDate(){
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		List<String> validToDateFromUI = privilegeDetailsPg.getValidToDateElement();
		if(validToDateExprected.size() != validToDateFromUI.size()){
			logger.error("Valid to date list is not correct.");
		}else{
			for(int i=0; i<validToDateExprected.size(); i++){
				if(!validToDateExprected.get(i).equals(validToDateFromUI.get(i))){
					throw new ErrorOnPageException("Valid to date order is not correct.");
				}
			}
		}
		// verify Based on Priv License Year Record should be set as default and business rule
		if(!privilegeDetailsPg.getValidToDateCalculation().equals("Based on Priv License Year Record")){
			throw new ErrorOnPageException("Based on Priv License Year Record should be set as defalut for valid to date calculation.");
		}
		if(!privilegeDetailsPg.checkValidDateOrYearsIsDisabled()){
			throw new ErrorOnPageException("Valid date or years could not edit when 'Based on Priv License Year Record' is selected.");
		}
		if(!privilegeDetailsPg.checkDateUnitOfValidToDateIsDisabled()){
			throw new ErrorOnPageException("Date unit of valid to date could not edit when 'Based on Priv License Year Record' is selected.");
		}
		if(!privilegeDetailsPg.checkRenewalDaysIsDisabled()){
			throw new ErrorOnPageException("Renewal Days could not edit when 'Based on Priv License Year Record' is selected.");
		}
		if(!privilegeDetailsPg.checkValidToAgeIsDisable()){
			throw new ErrorOnPageException("Valid to age could not edit when 'Based on Priv License Year Record' is selected.");
		}
		//verify business rule when Valid From Date plus Valid Days/Years is selected
		privilegeDetailsPg.selectValidToDateCalculation("Valid From Date plus Valid Days/Years");
		ajax.waitLoading();
		if(privilegeDetailsPg.checkValidDateOrYearsIsDisabled()){
			throw new ErrorOnPageException("Valid date or years could edit when 'Valid From Date plus Valid Days/Years' is selected.");
		}
		if(privilegeDetailsPg.checkDateUnitOfValidToDateIsDisabled()){
			throw new ErrorOnPageException("Date unit of valid to date could edit when 'Valid From Date plus Valid Days/Years' is selected.");
		}
		if(!privilegeDetailsPg.checkRenewalDaysIsDisabled()){
			throw new ErrorOnPageException("Renewal Days could not edit when 'Valid From Date plus Valid Days/Years' is selected.");
		}
		if(!privilegeDetailsPg.checkValidToAgeIsDisable()){
			throw new ErrorOnPageException("Valid to age could not edit when 'Valid From Date plus Valid Days/Years' is selected.");
		}
		//verify business rule when Valid To Date of Previous License plus Valid Days/Years is selected
		privilegeDetailsPg.selectValidToDateCalculation("Valid To Date of Previous License plus Valid Days/Years");
		ajax.waitLoading();
		if(privilegeDetailsPg.checkValidDateOrYearsIsDisabled()){
			logger.error("Valid date or years could edit when 'Valid To Date of Previous License plus Valid Days/Years' is selected.");
		}
		if(privilegeDetailsPg.checkDateUnitOfValidToDateIsDisabled()){
			throw new ErrorOnPageException("Date unit of valid to date could edit when 'Valid To Date of Previous License plus Valid Days/Years' is selected.");
		}
		if(privilegeDetailsPg.checkRenewalDaysIsDisabled()){
			throw new ErrorOnPageException("Renewal Days could edit when 'Valid To Date of Previous License plus Valid Days/Years' is selected.");
		}
		if(!privilegeDetailsPg.checkValidToAgeIsDisable()){
			throw new ErrorOnPageException("Valid to age could not edit when 'Valid To Date of Previous License plus Valid Days/Years' is selected.");
		}
		//verify business rule when Expires when the Customer reaches Valid To Age is selected
		privilegeDetailsPg.selectValidToDateCalculation("Expires when the Customer reaches Valid To Age");
		ajax.waitLoading();
		if(!privilegeDetailsPg.checkValidDateOrYearsIsDisabled()){
			throw new ErrorOnPageException("Valid date or years could not edit when 'Expires when the Customer reaches Valid To Age' is selected.");
		}
		if(!privilegeDetailsPg.checkDateUnitOfValidToDateIsDisabled()){
			logger.error("Date unit of valid to date could not edit when 'Expires when the Customer reaches Valid To Age' is selected.");
		}
		if(!privilegeDetailsPg.checkRenewalDaysIsDisabled()){
			throw new ErrorOnPageException("Renewal Days could not edit when 'Expires when the Customer reaches Valid To Age' is selected.");
		}
		if(privilegeDetailsPg.checkValidToAgeIsDisable()){
			throw new ErrorOnPageException("Valid to age could edit when 'Expires when the Customer reaches Valid To Age' is selected.");
		}
	}
	
	/**
	 * verify valid date printing value
	 */
	public void verifyVaildDatePrintingValue(){
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		List<String> validDatePrintingValuesFromUI = privilegeDetailsPg.getValidDatePrintingValues();
		if(validDatePrintingValuesExpected.size() != validDatePrintingValuesFromUI.size()){
			throw new ErrorOnPageException("Valid date printing value list is not correct.");
		}else {
			for(int i=0; i<validDatePrintingValuesExpected.size(); i++){
				if(!validDatePrintingValuesExpected.get(i).equals(validDatePrintingValuesFromUI.get(i))){
					throw new ErrorOnPageException("Valid date printing value is not correct.");
				}
			}
		}
	}
	
	/**
	 *  Verify customer class list value
	 */
	public void verifyCustomerClass(){
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		List<String> customerClassValuesFromUI = privilegeDetailsPg.getCustomerClassValues();
		if(customerClassValuesExpected.size() != customerClassValuesFromUI.size()){
			throw new ErrorOnPageException("Customer class value list is not correct.");
		}else {
			for (int i=0; i< customerClassValuesExpected.size(); i++){
				if(!customerClassValuesExpected.get(i).equals(customerClassValuesFromUI.get(i))){
					throw new ErrorOnPageException("Customer class value is not correct.");
				}
			}
		}
	}
	
	/**
	 * verify inventory type drop down list value and sorted in this list should have "None" option 
	 */
	public void verifyInventoryType(){
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		List<String> inventoryTypeValuesFromUI = privilegeDetailsPg.getInventoryTypeElement();
		List<String> inventoryTypeValuesFromDB = lm.getPrivilegeInventoryTypesSortedByAsc(login.location.split("/")[1],schema);		
		if(inventoryTypeValuesFromUI.size() != inventoryTypeValuesFromDB.size()+1){
		
			logger.error("Inventory type value list is not correct.");
		}else {
			if(!inventoryTypeValuesFromUI.get(0).equals("None")){
				
				logger.error("Inventory type list first option should be 'None'");
			}else{
				for(int i=0; i<inventoryTypeValuesFromDB.size(); i++){
					if(!inventoryTypeValuesFromDB.get(i).equals(inventoryTypeValuesFromUI.get(i+1))){
			
						logger.error("Inventory type value order is not correct.");
					}
				}
			}
		}	
	}
	
	

}
