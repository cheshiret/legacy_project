package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.profile;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class SearchCustomerProfileByAddress extends LicenseManagerTestCase{

	private LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage.getInstance();
	private Customer searchCust = new Customer();
	private boolean pass = true;
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		String custNumForIndividual =  lm.getCustomerNumByCustName(cust.lName, cust.fName, cust.mName, schema);
		
		//verify search customer by address
		searchCust.address = "QA-CustSearch Street";
		custSearchPg.searchCust(searchCust);
		custSearchPg.verifyKnownCustNumInSearchList(custNumForIndividual, true);
		
		//verify search customer by address start with and case-insensitive
		searchCust.address = "qa-CustSearch";
		custSearchPg.searchCust(searchCust);
		custSearchPg.verifyKnownCustNumInSearchList(custNumForIndividual, true);
		
		
		//verify search customer by city,state,county and zip code
		searchCust.address = "";
		searchCust.city = "albany";
		searchCust.state = "New York";
		searchCust.county = "Albany";
		searchCust.country = "United States";
		searchCust.zip = "12202";
		custSearchPg.searchCust(searchCust);
		custSearchPg.verifyKnownCustNumInSearchList(custNumForIndividual, true);
		
		//Verify error message for zip code
//		searchCust.zip = "#";
//		custSearchPg.searchCust(searchCust);
//		verifyErrorMessage(ziperrStr);// not fix DEFECT-54901
		
		//Throw exception
		if(!pass){
			throw new TestCaseFailedException("Case is running failed.");
		}
				
		lm.logOutLicenseManager();
	}
	
	private void verifyErrorMessage(String expectMsg) {
		ajax.waitLoading();
		String actualMsg = custSearchPg.getErrorMsg();
		if(!actualMsg.equalsIgnoreCase(expectMsg)) {
			pass &=false;
			logger.error("The actual error message: '" + actualMsg
					+"' is not match the expect message: '" +expectMsg+"'");
		}
	}


	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		//Login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/LAKE LINCOLN STATE PARK(Store Loc)";
		
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
		
		searchCust.dateOfBirth = cust.dateOfBirth;
	}

	String ziperrStr = "ZIP/Postal Code must contain at least 1 number or letter, and must only contain numbers, letters, a single dash, or a single space. Please change your entries.";
}
