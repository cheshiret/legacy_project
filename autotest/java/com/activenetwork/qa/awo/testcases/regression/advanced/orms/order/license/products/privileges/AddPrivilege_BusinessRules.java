package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrCreateNewPriviledgePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case used to verify business rule when add privilege product.
 * @Preconditions:
 * @SPEC:Add Privilege Product.doc
 * @Task#:Auto-672
 * 
 * @author VZhang
 * @Date  Jul 27, 2011
 */
public class AddPrivilege_BusinessRules extends LicenseManagerTestCase{
	private boolean result = true;
	private LicMgrCreateNewPriviledgePage createNewPrivilegeProductPage = LicMgrCreateNewPriviledgePage.getInstance();
	private List<String> authorizationQuntityExpected = new ArrayList<String>();
	private List<String> validFromDateExpected = new ArrayList<String>();
	private List<String> validToDateExprected = new ArrayList<String>();
	private List<String> validDatePrintingValuesExpected = new ArrayList<String>();
	private List<String> customerClassValuesExpected = new ArrayList<String>();
	private String revenueLocation = "";
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoCreatePrivilegeProductPageFromPrivilegeListPage();
		//verify business rule
		this.verifyBusinessRules();
		
		if(!result){
			throw new ErrorOnPageException("For some check points failed,please check error log.");
		}
		
		lm.logOutLicenseManager();		
	}

	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		revenueLocation = login.location.split("/")[1];
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
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
	
	private void verifyBusinessRules(){
		if(createNewPrivilegeProductPage.checkCodeTextIsDisabled()){
			result &= false;
			logger.error("Privilege Code Text should be edited.");
		}
		
		if(createNewPrivilegeProductPage.checkNameTextIsDisabled()){
			result &= false;
			logger.error("Privilege name text should be edited.");
		}
		
		if(!createNewPrivilegeProductPage.checkSatusIsDisabled()){
			result &= false;
			logger.error("Privlege status should not be edited.");
		}
		
		if(!createNewPrivilegeProductPage.getStatus().equals("Active")){
			result &= false;
			logger.error("Privilge status should be Active");
		}
		
		/*
		 * verify display category drop down list value and sorted
		 */
		List<String> displayCategoryFromDB = lm.getDescriptionOfDisplayCategory("Display Category", schema);
		List<String> displayCategoryFromUI = createNewPrivilegeProductPage.getDisplayCategoryElement();		
		if(displayCategoryFromDB.size() != displayCategoryFromUI.size()){
			result &= false;
			logger.error("Display category list is not correct.");
		}else {
			for(int i=0; i<displayCategoryFromDB.size(); i++){
				if(!displayCategoryFromDB.get(i).trim().equals(displayCategoryFromUI.get(i))){
					result &= false;
					logger.error("For category display " + i + " should be: '" + displayCategoryFromDB.get(i).trim() 
							+ "', but acutally display: '" + displayCategoryFromUI.get(i) + "'");
				}
			}
		}
		
		/*
		 * verify display sub category drop down list value and sorted
		 */
		List<String> displaySubCategoryFromDB = lm.getDescriptionOfDisplayCategory("Display Sub Category", schema);
		List<String> displaySubCategoryFromUI = createNewPrivilegeProductPage.getDisplaySubCategoryElement();
		if(displaySubCategoryFromUI.get(0).trim().equalsIgnoreCase("")){
			displaySubCategoryFromUI.remove(0);
		}
		
		if(displaySubCategoryFromDB.size() != displaySubCategoryFromUI.size()){
			result &= false;
			logger.error("Display Sub Category list is not correct.");
		}else {
			for(int i=0; i<displaySubCategoryFromDB.size(); i++){
				if(!displaySubCategoryFromDB.get(i).trim().equals(displaySubCategoryFromUI.get(i))){
					result &= false;
					logger.error("For sub category display " + i + " should be: '" + displaySubCategoryFromDB.get(i).trim() 
							+ "', but acutally display: '" + displaySubCategoryFromUI.get(i) + "'");
				}
			}
		}
		
		/*
		 * verify report category list value and sorted
		 */
		List<String> reportCategoryFromDB = lm.getDescriptionOfDisplayCategory("Report Category", schema);
		List<String> reportCategoryFromUI = createNewPrivilegeProductPage.getReportCategoryElement();
		if(reportCategoryFromDB.size() != reportCategoryFromUI.size()){
			result &= false;
			logger.error("Report Category list is not correct.");
		}else {
			for(int i=0; i<reportCategoryFromDB.size(); i++){
				if(!reportCategoryFromDB.get(i).trim().equals(reportCategoryFromUI.get(i))){
					result &= false;
					logger.error("For report category display " + i + " should be: '" + reportCategoryFromDB.get(i).trim() 
							+ "', but acutally display: '" + reportCategoryFromUI.get(i) + "'");
				}
			}
		}
		
		/*
		 * verify authorization quantity list value
		 */
		List<String> authorizationQuantityFromUI = createNewPrivilegeProductPage.getAuthorizationQuantityElement();
		if(authorizationQuntityExpected.size() != authorizationQuantityFromUI.size()){
			result &= false;
			logger.error("Authorzation quantity list is not correct.");
		}else {
			for(int i=0; i<authorizationQuntityExpected.size(); i++){
				if(!authorizationQuntityExpected.get(i).equals(authorizationQuantityFromUI.get(i))){
					result &= false;
					logger.error("The authorization quantity order is not correct.");
				}
			}
		}
		
		/**
		 * verify valid from date drop down list value and business rule
		 */
		List<String> validFromDateFromUI = createNewPrivilegeProductPage.getValidFromDateElement();
		if(validFromDateExpected.size() != validFromDateFromUI.size()){
			result &= false;
			logger.error("Valid From Date list is not correct.");
		}else {
			for(int i=0; i<validFromDateExpected.size(); i++){
				if(!validFromDateExpected.get(i).equals(validFromDateFromUI.get(i))){
					result &= false;
					logger.error("The valid from date order is not correct.");
				}
			}
		}
		//Based on Priv LY Record or Purchase Date (If Greater) should be set as default
		if(!createNewPrivilegeProductPage.getValidFromDateCalculation().equals("Based on Priv LY Record or Purchase Date (If Greater)")){
			result &= false;
			logger.error("Based on Priv LY Record or Purchase Date (If Greater) " +
					"should be set as the default for valid from date.");
		}
		if(!createNewPrivilegeProductPage.checkPromptIndicatorIsDisabled()){
			result &= false;
			logger.error("Prompt indicator could not edit " +
					"when 'Based on Priv LY Record or Purchase Date (If Greater)' is selected.");
		}	
		//verify business rule when Prompt for Valid From Date option is selected
		createNewPrivilegeProductPage.selectValidFromDateCalculation("Prompt for Valid From Date");
		ajax.waitLoading();
		if(createNewPrivilegeProductPage.checkPromptIndicatorIsDisabled()){
			result &= false;
			logger.error("Prompt indicator could edit when 'Prompt for Valid From Date' is selected.");
		}
		createNewPrivilegeProductPage.selectValidFromDateCalculation("Prompt for Valid From Date and Time");
		ajax.waitLoading();
		if(createNewPrivilegeProductPage.checkPromptIndicatorIsDisabled()){
			result &= false;
			logger.error("Prompt indicator could edit when 'Prompt for Valid From Date and Time' is selected.");
		}
		
		/*
		 * verify valid to date drop down list and business rule
		 */
		List<String> validToDateFromUI = createNewPrivilegeProductPage.getValidToDateElement();
		if(validToDateExprected.size() != validToDateFromUI.size()){
			result &= false;
			logger.error("Valid to date list is not correct.");
		}else{
			for(int i=0; i<validToDateExprected.size(); i++){
				if(!validToDateExprected.get(i).equals(validToDateFromUI.get(i))){
					result &= false;
					logger.error("Valid to date order is not correct.");
				}
			}
		}
		// verify Based on Priv License Year Record should be set as default and business rule
		if(!createNewPrivilegeProductPage.getValidToDateCalculation().equals("Based on Priv License Year Record")){
			result &= false;
			logger.error("Based on Priv License Year Record should be set as defalut for valid to date calculation.");
		}
		if(!createNewPrivilegeProductPage.checkValidDateOrYearsIsDisabled()){
			result &= false;
			logger.error("Valid date or years could not edit when 'Based on Priv License Year Record' is selected.");
		}
		if(!createNewPrivilegeProductPage.checkDateUnitOfValidToDateIsDisabled()){
			result &= false;
			logger.error("Date unit of valid to date could not edit when 'Based on Priv License Year Record' is selected.");
		}
		if(!createNewPrivilegeProductPage.checkRenewalDaysIsDisabled()){
			result &= false;
			logger.error("Renewal Days could not edit when 'Based on Priv License Year Record' is selected.");
		}
		if(!createNewPrivilegeProductPage.checkValidToAgeIsDisable()){
			result &= false;
			logger.error("Valid to age could not edit when 'Based on Priv License Year Record' is selected.");
		}
		//verify business rule when Valid From Date plus Valid Days/Years is selected
		createNewPrivilegeProductPage.selectValidToDateCalculation("Valid From Date plus Valid Days/Years");
		ajax.waitLoading();
		if(createNewPrivilegeProductPage.checkValidDateOrYearsIsDisabled()){
			result &= false;
			logger.error("Valid date or years could edit when 'Valid From Date plus Valid Days/Years' is selected.");
		}
		if(createNewPrivilegeProductPage.checkDateUnitOfValidToDateIsDisabled()){
			result &= false;
			logger.error("Date unit of valid to date could edit when 'Valid From Date plus Valid Days/Years' is selected.");
		}
		if(!createNewPrivilegeProductPage.checkRenewalDaysIsDisabled()){
			result &= false;
			logger.error("Renewal Days could not edit when 'Valid From Date plus Valid Days/Years' is selected.");
		}
		if(!createNewPrivilegeProductPage.checkValidToAgeIsDisable()){
			result &= false;
			logger.error("Valid to age could not edit when 'Valid From Date plus Valid Days/Years' is selected.");
		}
		//verify business rule when Valid To Date of Previous License plus Valid Days/Years is selected
		createNewPrivilegeProductPage.selectValidToDateCalculation("Valid To Date of Previous License plus Valid Days/Years");
		ajax.waitLoading();
		if(createNewPrivilegeProductPage.checkValidDateOrYearsIsDisabled()){
			result &= false;
			logger.error("Valid date or years could edit when 'Valid To Date of Previous License plus Valid Days/Years' is selected.");
		}
		if(createNewPrivilegeProductPage.checkDateUnitOfValidToDateIsDisabled()){
			result &= false;
			logger.error("Date unit of valid to date could edit when 'Valid To Date of Previous License plus Valid Days/Years' is selected.");
		}
		if(createNewPrivilegeProductPage.checkRenewalDaysIsDisabled()){
			result &= false;
			logger.error("Renewal Days could edit when 'Valid To Date of Previous License plus Valid Days/Years' is selected.");
		}
		if(!createNewPrivilegeProductPage.checkValidToAgeIsDisable()){
			result &= false;
			logger.error("Valid to age could not edit when 'Valid To Date of Previous License plus Valid Days/Years' is selected.");
		}
		//verify business rule when Expires when the Customer reaches Valid To Age is selected
		createNewPrivilegeProductPage.selectValidToDateCalculation("Expires when the Customer reaches Valid To Age");
		ajax.waitLoading();
		if(!createNewPrivilegeProductPage.checkValidDateOrYearsIsDisabled()){
			result &= false;
			logger.error("Valid date or years could not edit when 'Expires when the Customer reaches Valid To Age' is selected.");
		}
		if(!createNewPrivilegeProductPage.checkDateUnitOfValidToDateIsDisabled()){
			result &= false;
			logger.error("Date unit of valid to date could not edit when 'Expires when the Customer reaches Valid To Age' is selected.");
		}
		if(!createNewPrivilegeProductPage.checkRenewalDaysIsDisabled()){
			result &= false;
			logger.error("Renewal Days could not edit when 'Expires when the Customer reaches Valid To Age' is selected.");
		}
		if(createNewPrivilegeProductPage.checkValidToAgeIsDisable()){
			result &= false;
			logger.error("Valid to age could edit when 'Expires when the Customer reaches Valid To Age' is selected.");
		}
		
		/**
		 * verify valid date printing value
		 */
		List<String> validDatePrintingValuesFromUI = createNewPrivilegeProductPage.getValidDatePrintingValues();
		if(validDatePrintingValuesExpected.size() != validDatePrintingValuesFromUI.size()){
			result &= false;
			logger.error("Valid date printing value list is not correct.");
		}else {
			for(int i=0; i<validDatePrintingValuesExpected.size(); i++){
				if(!validDatePrintingValuesExpected.get(i).equals(validDatePrintingValuesFromUI.get(i))){
					result &= false;
					logger.error("Valid date printing value is not correct.");
				}
			}
		}
		
		/**
		 * Verify customer class list value
		 */
		List<String> customerClassValuesFromUI = createNewPrivilegeProductPage.getCustomerClassValues();
		if(customerClassValuesExpected.size() != customerClassValuesFromUI.size()){
			result &= false;
			logger.error("Customer class value list is not correct.");
		}else {
			for (int i=0; i< customerClassValuesExpected.size(); i++){
				if(!customerClassValuesExpected.get(i).equals(customerClassValuesFromUI.get(i))){
					result &= false;
					logger.error("Customer class value is not correct.");
				}
			}
		}
		
		/*
		 * verify inventory type drop down list value and sorted
		 * in this list should have "None" option
		 */
		List<String> inventoryTypeValuesFromUI = createNewPrivilegeProductPage.getInventoryTypeElement();
		List<String> inventoryTypeValuesFromDB = lm.getPrivilegeInventoryTypesSortedByAsc(revenueLocation,schema);		
		if(inventoryTypeValuesFromUI.size() != inventoryTypeValuesFromDB.size()+1){
			result &= false;
			logger.error("Inventory type value list is not correct.");
		}else {
			if(!inventoryTypeValuesFromUI.get(0).equals("None")){
				result &= false;
				logger.error("Inventory type list first option should be 'None'");
			}else{
				for(int i=0; i<inventoryTypeValuesFromDB.size(); i++){
					if(!inventoryTypeValuesFromDB.get(i).equals(inventoryTypeValuesFromUI.get(i+1))){
						result &= false;
						logger.error("Inventory type value order is not correct.");
					}
				}
			}
		}	
	}			
}
