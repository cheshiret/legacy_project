package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.profile;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrNewCustomerPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrSelectCustomerClassWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description: This case is used to test when the Add Customer Profile page initially loads, 
 * fields shall be populated with values entered or selected from the last executed search on the Customer Profile Search/List page.
 * Search customer from home page by click Advanced Search Link
 * @Preconditions:
 * @SPEC:TC:035882
 * @Task#:AUTO-1324
 * 
 * @author vzhang
 * @Date  Oct 17, 2012
 */
public class IsPopulatedWithSearchValue_AdvLink extends LicenseManagerTestCase{
	private LicMgrNewCustomerPage newCustPg = LicMgrNewCustomerPage.getInstance();
	private LicMgrCustomersSearchPage customerSearchPg = LicMgrCustomersSearchPage.getInstance();
	private String message;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//go to customer search page from home page by click Advanced Search link
		lm.gotoCustomerSearchPgFromHomePgByClickAdvSearchLink();
		//search customer, this customer is not existing
		lm.searchCustomer(cust);
		this.verifyMessage(message);
		this.gotoNewCustomerPage(cust.customerClass);
		//verify customer info at add new customer page
		this.verifyCustomerInfoByIsPopulatedWithSearch(cust);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		cust.customerClass = "Individual";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "123456";
		cust.lName = "Auto";
		cust.fName = "Test";
		cust.mName = "Qa";
		cust.businessName = "QA Test";
		cust.dateOfBirth = "19680101";
		cust.hPhone = "9057856999";
		cust.includeAreaCode = true;
		cust.address = "Test Address";
		cust.supplementalAddress = "Test Supplemental Address";
		cust.city = "Test City";
		cust.county = "Albany";
		cust.state = "New York";
		cust.zip = "123456";
		cust.country = "United States";
		cust.solicitationIndcator = "Yes";
		
		cust.physicalAddr.address = cust.address;
		cust.physicalAddr.supplementalAddr = cust.supplementalAddress;
		cust.physicalAddr.city = cust.city;
		cust.physicalAddr.county = cust.county;
		cust.physicalAddr.state = cust.state;
		cust.physicalAddr.zip = cust.zip;
		cust.physicalAddr.country = cust.country;
		cust.physicalAddr.status = "";
		
		cust.mailingAddr.address = "";
		cust.mailingAddr.supplementalAddr = "";
		cust.mailingAddr.city = "";
		cust.mailingAddr.county = "";
		cust.mailingAddr.state = "Mississippi";
		cust.mailingAddr.zip = "";
		cust.mailingAddr.country = "United States";
		cust.mailingAddr.status = "";
		
		message = "No results found matching the search criteria. Please re-enter.";
	}
	
	private void gotoNewCustomerPage(String customerClass){
		LicMgrSelectCustomerClassWidget custClassWgt = LicMgrSelectCustomerClassWidget.getInstance();
	
		logger.info("Go to new customer page.");
		customerSearchPg.clickAddCustomer();
		ajax.waitLoading();
		custClassWgt.waitLoading();
		custClassWgt.selectCustomerClass(customerClass);
		custClassWgt.clickOK();
		ajax.waitLoading();
		newCustPg.waitLoading();
	}
	
	private void verifyMessage(String expMessage){
		String actMessage = customerSearchPg.getWarnMes();
		boolean result = MiscFunctions.compareResult("Waring Message", expMessage, actMessage);
		if(!result){
			throw new ErrorOnPageException("The Message is not correct.");
		}logger.info("The Message is correct.");
	}
	
	private void verifyCustomerInfoByIsPopulatedWithSearch(Customer expCust){
		logger.info("Verify customer info.");
		boolean result = newCustPg.compareCustomerNameInfo(expCust);
		if(!result){
			throw new ErrorOnPageException("The customer info is not correct by populated with search value.");
		}logger.info("The customer info is correct by populated with search value.");
	}

}
