package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.profile;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class SearchCustomerProfileByEducation extends LicenseManagerTestCase{

	private LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage.getInstance();
	private Customer searchCust = new Customer();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		String custNumForIndividual =  lm.getCustomerNumByCustName(cust.lName, cust.fName, cust.mName, schema);
		
		//verify search customer by education
		custSearchPg.searchCustomerByIdnetifier("HuntEducation", "NYE222222", "Individual");
		custSearchPg.verifyKnownCustNumInSearchList(custNumForIndividual, true);
		custSearchPg.verifySearchCustomerProfileResult(cust);
		
		//Search by education type and number(case-insensitive,ignoring dashes,ignoring embedded spaces)
		custSearchPg.searchCustomerByIdnetifier("HuntEducation", "N ye222222-", "Individual");
		custSearchPg.verifyKnownCustNumInSearchList(custNumForIndividual, true);
		custSearchPg.verifySearchCustomerProfileResult(cust);
		
		//Search by education type and state
		searchCust.identifier.identifierType = "HuntEducation";
		searchCust.identifier.identifierNum = "";
		searchCust.identifier.state = "Mississippi";
		custSearchPg.searchCust(searchCust);
		custSearchPg.verifyKnownCustNumInSearchList(custNumForIndividual, true);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		//Login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/LAKE LINCOLN STATE PARK(Store Loc)";
		
		cust.status = "Active";
		cust.lName = "TEST-CustSearch";
		cust.fName = "QA-CustSearch";
		cust.mName = "QATest-CustSearch";
		cust.businessName = "";
		cust.dateOfBirth = "Jun 08 1981";
		cust.customerClass = "Individual";
		cust.hPhone = "128612645";
		cust.address = "QA-CustSearch Street";
		cust.supplementalAddress = "QA-CustSearch Supp Street";
		cust.city = "Albany";
		cust.county = "Albany";
		cust.state = "New York";
		cust.zip = "12202";
		cust.country = "United States";
		
	}

}
