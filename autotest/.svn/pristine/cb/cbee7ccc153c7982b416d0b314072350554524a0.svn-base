package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.profile;

import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class SearchCustomerProfileByName extends LicenseManagerTestCase{

	private LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		String custNumForIndividual = lm.getCustomerNumByCustName(cust.lName, cust.fName, cust.mName, schema);
		String custNumForBusiness = lm.getCustomerNumByCustName("TEST-Profile2", "QA-Customer2", schema);
		
		//verify search customer by last name
		custSearchPg.searchCustomer("TEST-CustSearch", "");
		custSearchPg.verifyKnownCustNumInSearchList(custNumForIndividual, true);
		
		//verify search customer by last name start with and case-insensitive
		custSearchPg.searchCustomer("test-CustSea", "");
		custSearchPg.verifyKnownCustNumInSearchList(custNumForIndividual, true);
		
		//verify search customer by business name
		custSearchPg.setLastName("");
		custSearchPg.searchCustByBusinessName("@qaTest-CusotmerProfile-2");
		custSearchPg.verifyKnownCustNumInSearchList(custNumForBusiness, true);
		
		//verify search customer by business name case-insensitive
		custSearchPg.searchCustByBusinessName("@QATest-CusotmerProfi");
		custSearchPg.verifyKnownCustNumInSearchList(custNumForBusiness, true);
		
		//verify search customer by date of birth
		custSearchPg.setBusinessName("");
		custSearchPg.searchCustByDOB("Jun 08 1981");
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
