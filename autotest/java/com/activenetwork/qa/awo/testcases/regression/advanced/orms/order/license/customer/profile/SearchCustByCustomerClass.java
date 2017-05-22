/**
 *
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.profile;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Customer.CleanUpSwitch;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
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
public class SearchCustByCustomerClass extends LicenseManagerTestCase {
	private Customer indivcust1 = new Customer();
	private Customer indivcust2 = new Customer();
	private Customer buscust = new Customer();
	private CleanUpSwitch cleanUpList = buscust.new CleanUpSwitch();
	private CleanUpSwitch custCleanUpList = cust.new CleanUpSwitch();

	@Override
	public void execute() {
		// prepare data for test
		indivcust1.custNum = lm.getCustomerNum(indivcust1, schema);
		indivcust2.custNum = lm.getCustomerNum(indivcust2, schema);
		buscust.custNum = lm.getCustomerNum(buscust, schema);
		if ("".equals(indivcust1.custNum) || "".equals(indivcust2.custNum)
				|| "".equals(buscust.custNum)) {
			throw new ErrorOnDataException(
					"There is no available customer,please run support script 'AddCustomerProfile' to add a customer whose first name is Auto-QA, last name is QA-Test...");
		}

		lm.loginLicenseManager(login);
		// go to customer search page
		lm.gotoCustomerSearchPageFromCustomersTopMenu();

		// verify customer class display
		this.verifyCustomerClassDisplay(schema);

		// verify customer class sorting
		this.verifyCustomerClassSorting(schema);

		// verify identifier type and customer class matching
		// this.verifyIdentifierType();//TODO defect

		// search customer 'Individual',middle name is empty
		lm.searchCustomer(indivcust1);
		// verify customer name display
		this.verifyCustomerName(indivcust1);
		// search customer 'Individual',middle name is not empty
		lm.searchCustomer(indivcust2);
		// verify customer name display
		this.verifyCustomerName(indivcust2);
		// search customer 'Business'
		lm.searchCustomer(buscust,cleanUpList);
		// verify customer name display
		this.verifyCustomerName(buscust);

		// search customer
		cust.dateOfBirth = indivcust1.dateOfBirth;
		lm.searchCustomer(cust,custCleanUpList);
		// verify customer sorting
		this.verifyCustomerSorting();

		/**
		 * verify customer class display;due to update database to effect other person, the check point not verify
		 */
		//		// update customer class only one active(inactive individual)
		//		lm.updateCustomerClassStatus(schema, cust.customerClass, true);
		//		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		//		// verify customer class display
		//		this.verifyCustomerClassDisplay(schema);
		//		// update customer class more than one active(active individual)
		//		lm.updateCustomerClassStatus(schema, cust.customerClass, false);

		lm.logOutLicenseManager();

	}


	@SuppressWarnings("unused")
	private void verifyIdentifierType() {
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		// customer class is empty
		this.verifySearchTypeMatchClass("");// defect

		// customer class is Individual
		custSearchPg.selectCustClass("Individual");
		ajax.waitLoading();
		this.verifySearchTypeMatchClass("Individual");

		// customer class is Business
		custSearchPg.selectCustClass("Business");
		ajax.waitLoading();
		this.verifySearchTypeMatchClass("Business");// defect

	}

	private void verifySearchTypeMatchClass(String custclass) {
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		List<String> values = custSearchPg.getSearchTypeValue();
		List<String> identifier = null;
		List<String> cert = null;
		List<String> edu = null;
		if ("".equals(custclass)) {
			identifier = lm.getCustomerIdentifierType(schema);
			cert = lm.getCustomerCertificationTypes(schema);
			edu = lm.getCustomerEducationType(schema);
		} else {
			identifier = lm.getCustomerIdentifierTypeByCustClass(schema,
					custclass);
			cert = lm
					.getCustomerCertificationTypeByCustClass(schema, custclass);
			edu = lm.getCustomerEducationTypeByCustClass(schema, custclass);
		}

		if (null == identifier
				|| null == cert
				|| null == edu
				|| identifier.size() + cert.size() + edu.size() != values
				.size()) {
			throw new ErrorOnDataException(
					"The search type is not matching customer class.");
		}

		this.verifyListContains(values, identifier);
		this.verifyListContains(values, cert);
		this.verifyListContains(values, edu);

	}

	private void verifyListContains(List<String> values, List<String> contents) {
		for (String content : contents) {
			if (!"Customer #".equals(content) && !values.contains(content)) {
				throw new ErrorOnDataException(
						"The search type is not contain :" + content);
			} else if ("Customer #".equals(content)
					&& !values.contains("MDWFP #")) {
				throw new ErrorOnDataException(
						"The search type is not contain :" + content);
			}
		}
	}

	private void verifyCustomerName(Customer cust) {
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		String value;

		List<String> name = custSearchPg
				.getColValue("Customer / Business Name");
		if (!"".equals(cust.businessName)) {
			value = cust.businessName;
		} else {
			if ("".equals(cust.mName)) {
				value = cust.lName + ", " + cust.fName;
			} else {
				value = cust.lName + ", " + cust.fName + " " + cust.mName;
			}
		}

		if (!value.equals(name.get(1).trim())) {
			throw new ErrorOnDataException(
					"The customer name don't display correct.", value, name
					.get(1));
		}

	}

	private void verifyCustomerSorting() {
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();

		List<String> transdate = custSearchPg.getColValue("Last Txn Date");
		List<String> name = custSearchPg
				.getColValue("Customer / Business Name");
		transdate.remove(0);
		name.remove(0);
		for (int i = 0; i < transdate.size() - 1; i++) {
			if ((i + 1) <= transdate.size() - 1 && "".equals(transdate.get(i))) {
				if ("".equals(transdate.get(i + 1))) {
					if (name.get(i).compareTo(name.get(i + 1)) > 0) {
						throw new ErrorOnDataException(
								"The customer sorting is not correct.");
					}
				} else {
					throw new ErrorOnDataException(
							"The customer sorting is not correct.");
				}
			} else if ((i + 1) <= transdate.size() - 1 && !"".equals(transdate.get(i))) {
				if (!"".equals(transdate.get(i + 1))) {
					if (DateFunctions.compareDates(transdate.get(i), transdate.get(i + 1)) == 0) {
						if (name.get(i).compareTo(name.get(i + 1)) > 0) {//< //Sara[11/26/2013] update "<" to ">" per DEFECT-51987 because "A".compareTo("B")=-1 is correct
							throw new ErrorOnDataException(
									"The customer sorting is not correct."+name.get(i));
						}
					} else if (DateFunctions.compareDates(transdate.get(i),transdate.get(i + 1)) < 0) {
						throw new ErrorOnDataException(
								"The customer sorting is not correct.");
					}
				}
			} else {
				logger.info("There is no available value to compare.");
			}
		}

	}

	private void verifyCustomerClassSorting(String schema) {
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();

		List<String> values = lm.getCustomerClass(schema);
		List<String> elements = custSearchPg.getCustomerClassValue();
		boolean sort = true;
		if (values.size() > 1 && values.contains("Individual")) {
			if (!"".equals(elements.get(0))
					|| !"Individual".equals(elements.get(1))) {
				throw new ErrorOnDataException(
						"The customer class sorting is not correct.");
			}
			values.remove(values.indexOf("Individual"));
			elements.remove(1);
			elements.remove(0);
		} else if (values.size() > 1 && !values.contains("Individual")) {
			if (!"".equals(elements.get(0))) {
				throw new ErrorOnDataException(
						"The customer class sorting is not correct.");
			}
			elements.remove(0);
		}
		for (int i = 0; i < values.size(); i++) {
			if (!elements.get(i).equals(values.get(i))) {
				logger
				.error("The customer class sorting is not correct. Expect class is '"
						+ values.get(i)
						+ "', but actually is '"
						+ elements.get(i + 1) + "'");
				sort &= false;
			}
		}
		if (!sort) {
			throw new ErrorOnDataException(
					"The customer class sorting is not correct.Please see the log file...");
		}
	}

	private void verifyCustomerClassDisplay(String schema) {
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		List<String> values = lm.getCustomerClass(schema);
		String value = custSearchPg.getCustomerClassDefaultValue();
		if (values.size() > 1) {
			if (!"".equals(value)) {
				//				lm.updateCustomerClassStatus(schema, cust.customerClass, false);
				throw new ErrorOnDataException(
						"The customer class default value is not correct.", "",
						value);
			}
		} else if (values.size() == 1) {
			if (!values.get(0).equals(value)) {
				//				lm.updateCustomerClassStatus(schema, cust.customerClass, false);
				throw new ErrorOnDataException(
						"The customer class default value is not correct.",
						values.get(0), value);
			}
		} else {
			//			lm.updateCustomerClassStatus(schema, cust.customerClass, false);
			throw new ErrorOnDataException(
					"There is no active customer class, please check configuration for contract.");
		}

	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		indivcust1.customerClass = "Individual";
		indivcust1.fName = "Auto-QA";
		indivcust1.lName = "QA-Test";
		indivcust1.dateOfBirth = "Jan 01 1980";

		indivcust2.customerClass = "Individual";
		indivcust2.fName = "Astra";
		indivcust2.lName = "LoadTest";
		indivcust2.mName = "tester";
		indivcust2.dateOfBirth = "Jan 01 1980";

		buscust.customerClass = "Business";
		buscust.businessName = "@QaTest-CusotmerProfile-6";
		buscust.dateOfBirth = "Jul 25 1978";
		cleanUpList.isCleanFirstName=true;
		cleanUpList.isCleanLastName=true;
		cleanUpList.isCleanMiddleName=true;

		cust.customerClass = "Individual";
		custCleanUpList.isCleanCustClass=true;
		custCleanUpList.isCleanBusinessName=true;
	}

}
