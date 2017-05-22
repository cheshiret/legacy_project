/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.profile;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:PCR 2881- Wildlife - Support Customer Profile Search for all
 *                  Customer Classes
 * @Preconditions:
 * @SPEC: Search Customer Profile.doc
 * @Task#: Auto-883
 * 
 * @author szhou
 * @Date Feb 28, 2012
 */
public class SearchCustByCustomerNumber extends LicenseManagerTestCase {
	private Customer cust = new Customer();
	private Customer search = new Customer();
	private String message;

	@Override
	public void execute() {
		// prepare data for test
		cust.custNum = lm.getCustomerNum(cust, schema);
		if ("".equals(cust.custNum)) {
			throw new ErrorOnDataException(
					"There is no available customer,please run support script 'AddCustomerProfile' to add a customer whose first name is Auto-QA, last name is QA-Test...");
		}

		lm.loginLicenseManager(login);
		// go to customer search page
		lm.gotoCustomerSearchPageFromCustomersTopMenu();

		// search customer use MDWFP # and specified value
		search.licenseNum = cust.custNum;
		lm.searchCustomer(search);
		// verify search results
		this.verifyCustomerInfo(cust.custNum, cust.customerClass);
		this.goBackTOCustomerSearchListPg();

		// search customer use MDWFP # and enter customer class is not matching
		search.customerClass = "Business";
		lm.searchCustomer(search);
		// verify search results
		this.verifySearchCustomerFailed(message);

		// search customer use MDWFP # and enter other search criteria is not
		// matching
		search.customerClass = "Individual";
		search.fName = "tester";
		lm.searchCustomer(search);
		// verify search results
		this.verifyCustomerInfo(cust.custNum, cust.customerClass);

		lm.logOutLicenseManager();
	}

	private void goBackTOCustomerSearchListPg() {
		LicMgrCustomerDetailsPage detailsPage = LicMgrCustomerDetailsPage
				.getInstance();
		LicMgrCustomersSearchPage custSearch = LicMgrCustomersSearchPage
				.getInstance();

		detailsPage.clickOK();
		ajax.waitLoading();
		custSearch.waitLoading();
	}

	private void verifySearchCustomerFailed(String error) {
		LicMgrCustomersSearchPage custSearch = LicMgrCustomersSearchPage
				.getInstance();

		String warning = custSearch.getWarnMes();
		if (null == warning || (null != warning && !error.equals(warning))) {
			throw new ErrorOnDataException(
					"Customer search result is not correct, there has avaliable customer...");
		}

	}

	private void verifyCustomerInfo(String custnum, String custClass) {
		LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage
				.getInstance();
		String num = custDetailsPg.getCustomerNumber();
		if (null != num && !custnum.equals(num)) {
			throw new ErrorOnDataException("MDWFP # is not correct.", custnum,
					num);
		}
		String cust = custDetailsPg.getCustomerClass();
		if (null != cust && !custClass.equals(cust)) {
			throw new ErrorOnDataException("Customer class is not correct.",
					custClass, cust);
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		cust.customerClass = "Individual";
		cust.fName = "Astra";
		cust.lName = "LoadTest";
		cust.mName = "tester";
		cust.dateOfBirth = "Jan 01 1980";
		cust.licenseType = search.licenseType = "MDWFP #";
		
		message = "No results found matching the search criteria. Please re-enter.";
	}

}
