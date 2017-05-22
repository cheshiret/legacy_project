package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.tax.schedule;

import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchedulePage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description: verify the tax schedule search criteria/search result correctly
 * @Preconditions: 
 * @SPEC: Search Tax Schedule - Search Criteria [TC:049969] & Search Tax Schedule - Result [TC:049970]
 * @Task#: AUTO-1440
 * 
 * @author qchen
 * @Date  Feb 17, 2013
 */
public class VerifySearchCriteriaResult extends FinanceManagerTestCase {
	private FinMgrTaxSchedulePage taxSchdlPage = FinMgrTaxSchedulePage.getInstance();
	private String expectedSearchTypeOptions[];
	private String expectedDateTypeOption;
	private String defaultExpectedResult[], slipExpectedResults[], listExpectedResults[];
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoTaxMainPage();
		fnm.gotoTaxSchedulePage();
		//1. verify the original search criteria: Tax Schedule ID, Location, Tax Name, Product Category, Fee Type, Product Group, Product, Tax Schedule Status, Effective Start Date, Customer Type, Transaction Type, Tax Rate Type, Tax Rate, and Account.
		this.verifyTaxScheduleSearchCriteria(false, false);
		//verify the original search result:
		List<String> actualResults = taxSchdlPage.getSearchResultColumnNames();
		actualResults.remove(0);
		this.verifySearchResult(Arrays.asList(defaultExpectedResult), actualResults);
		
		//2. select Slip as product category, verify there is a more search criteria 'Marina Rate Type'
		this.switchProductCategory("Slip");
		this.verifyTaxScheduleSearchCriteria(true, false);
		//verify the search result
		actualResults = taxSchdlPage.getSearchResultColumnNames();
		actualResults.remove(0);
		this.verifySearchResult(Arrays.asList(slipExpectedResults), actualResults);
		
		//3. select List as product category, verify there is a more search criteria 'Applicable Product Category' compared with step 1
		this.switchProductCategory("List");
		this.verifyTaxScheduleSearchCriteria(false, true);
		//verify the search result
		actualResults = taxSchdlPage.getSearchResultColumnNames();
		actualResults.remove(0);
		this.verifySearchResult(Arrays.asList(listExpectedResults), actualResults);
		
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		expectedSearchTypeOptions = new String[] {"Account", "Location", "Product", "Product Group", "Tax Name", "Tax Schedule ID"};
		expectedDateTypeOption = "Effective Start Date";
		
		defaultExpectedResult = new String[] {"Tax Schedule ID", "Active", "Location", "Tax Name", "Product Category", "Product Or Product Group", "Fee Type", "Effective Start Date", "Effective End Date", "Rate", "Rate Type", "Customer Type", "Transaction Type", "Account"};
		slipExpectedResults = new String[] {"Tax Schedule ID", "Active", "Location", "Tax Name", "Product Category", "Product Or Product Group", "Fee Type", "Effective Start Date", "Effective End Date", "Rate", "Rate Type", "Customer Type", "Transaction Type", "Marina Rate Type", "Account"};
		listExpectedResults = defaultExpectedResult;
	}
	
	private void switchProductCategory(String prodCat) {
		taxSchdlPage.selectProductCategory(prodCat);
		ajax.waitLoading();
	}
	
	private void verifyTaxScheduleSearchCriteria(boolean isSlip, boolean isList) {
		logger.info("Verify the tax schedule search criteria for " + (isSlip ? "Slip" : (isList ? "List" : "default")) + " product category");
		boolean exists = true;
		exists &= MiscFunctions.compareResult("Search Type exists", true, taxSchdlPage.isSearchTypeExists());
		exists &= MiscFunctions.compareResult("Search Value exists", true, taxSchdlPage.isSearchValueExists());
		exists &= MiscFunctions.compareResult("Date exists", true, taxSchdlPage.isDateExists());
		exists &= MiscFunctions.compareResult("From Date exists", true, taxSchdlPage.isFromDateExists());
		exists &= MiscFunctions.compareResult("To Date exists", true, taxSchdlPage.isToDateExists());
		
		//verify the 'Search Type' and 'Date' options
		List<String> actualSearchTypeOptions = taxSchdlPage.getSearchTypeOptions();
		List<String> actualDateTypeOptions = taxSchdlPage.getDateTypeOptions();
		actualSearchTypeOptions.remove(0);//remove the 1st blank option
		if(expectedSearchTypeOptions.length == actualSearchTypeOptions.size()) {
			for(int i = 0; i < expectedSearchTypeOptions.length; i ++) {
				if(!actualSearchTypeOptions.contains(expectedSearchTypeOptions[i])) {
					throw new ErrorOnPageException("Search type dropdown list doesn't contains: " + expectedSearchTypeOptions[i]);
				}
			}
		} else throw new ErrorOnPageException("Search type dropdown list options size is incorrect.");
		
		if(!actualDateTypeOptions.contains(expectedDateTypeOption)) {
			throw new ErrorOnPageException("Date type dorpdown list options are incorrect. It shall contains: " + expectedDateTypeOption);
		}
		
		exists &= MiscFunctions.compareResult("Status exists", true, taxSchdlPage.isStatusExists());
		exists &= MiscFunctions.compareResult("Product Category exists", true, taxSchdlPage.isProductCategoryExists());
		if(isList) {
			exists &= MiscFunctions.compareResult("Applicable Product Category exists", true, taxSchdlPage.isApplicableProductCategoryExists());
		}
		exists &= MiscFunctions.compareResult("Fee Type exists", true, taxSchdlPage.isFeeTypeExists());
		exists &= MiscFunctions.compareResult("Rate Type exist", true, taxSchdlPage.isRateTypeExists());
		exists &= MiscFunctions.compareResult("Customer Type exists", true, taxSchdlPage.isCustomerTypeExists());
		exists &= MiscFunctions.compareResult("Transaction Type exists", true, taxSchdlPage.isTransactionTypeExists());
		if(isSlip) {
			exists &= MiscFunctions.compareResult("Marina Rate Type exists", true, taxSchdlPage.isMarinaRateTypeExist());
		}
		
		if(!exists) {
			throw new ErrorOnPageException("The tax schedule search criteria are incorrect.");
		} else logger.info("The tax schedule search criteria are correct.");
	}
	
	private void verifySearchResult(List<String> expected, List<String> actual) {
		System.out.println(expected.toString());
		System.out.println(actual.toString());
		if(!expected.equals(actual)) {
			throw new ErrorOnPageException("Tax schedule search result is incorrect.");
		} else logger.info("Tax schedule search result is correct.");
	}
}
