/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.profile;

import java.util.HashSet;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomersSearchPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:PCR 2881- Wildlife - Support Customer Profile Search for all
 *                  Customer Classes
 * @Preconditions:
 * @SPEC:Home - License Manager.UIS
 * @Task#: Auto-883
 * 
 * @author szhou
 * @Date Mar 2, 2012
 */
public class SearchCustByCustomerClass_HomePg extends LicenseManagerTestCase {

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// verify customer class drop down list
		this.verifyCustomerClassContent();

		// verify identifier type and customer class matching
		this.verifyIdentifierType();

		lm.logOutLicenseManager();

	}

	private void verifyCustomerClassContent() {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();

		List<String> custclass = homePg.getCustomerClass();
		List<String> system = lm.getCustomerClass(schema);

		if (!custclass.contains("") || !"".equals(custclass.get(0))) {
			throw new ErrorOnDataException(
					"The default customer class value is wrong, it should be black.");
		}

		if ((custclass.size() - 1) != system.size()) {
			throw new ErrorOnDataException(
					"The customer class count is wrong...");
		}

		for (int i = 1; i < custclass.size(); i++) {
			if (!system.contains(custclass.get(i))) {
				throw new ErrorOnDataException(
						"The customer class don't contain value :"
								+ custclass.get(i));
			}
		}
	}

	private void verifyIdentifierType() {
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();

		// customer class is empty
		this.verifySearchTypeMatchClass("");

		// customer class is Individual
		homePg.selectCustomerClass("Individual");
		ajax.waitLoading();
		this.verifySearchTypeMatchClass("Individual");

		// customer class is Business
		homePg.selectCustomerClass("Business");
		ajax.waitLoading();
		this.verifySearchTypeMatchClass("Business");

	}

	private void verifySearchTypeMatchClass(String custclass) {
		LicMgrCustomersSearchPage custSearchPg = LicMgrCustomersSearchPage
				.getInstance();
		List<String> values = custSearchPg.getSearchTypeValue();//[MDWFP #, MS Drivers License, Passport, Social Security Number, Employee Federal Identification Number, US Drivers License, Tax ID]
		List<String> identifier = null;//[MS Drivers License, Passport, Social Security Number, Social Security Number, Employee Federal Identification Number, US Drivers License, Tax ID]
		if ("".equals(custclass)) {
			identifier = lm.getCustomerIdentifierType(schema);
		} else {
			identifier = lm.getCustomerIdentifierTypeByCustClass(schema,
					custclass);
		}
		
		// remove duplicate value in the list
		HashSet<String> value  =   new  HashSet<String>(values); 
		values.clear();
		values.addAll(value);
		HashSet<String> identifiervalue  =   new  HashSet<String>(identifier); 
		identifier.clear();
		identifier.addAll(identifiervalue);
     
        
		if (null == identifier || identifier.size() != values.size()) {
			throw new ErrorOnDataException(
					"The search type is not matching customer class.");
		}

		this.verifyListContains(values, identifier);

	}

	private void verifyListContains(List<String> values, List<String> content) {
		for (String value : values) {
			if (!"MDWFP #".equals(value) && !content.contains(value)) {
				throw new ErrorOnDataException(
						"The search type is not contain :" + value);
			} else if ("MDWFP #".equals(value)
					&& !content.contains("Customer #")) {
				throw new ErrorOnDataException(
						"The search type is not contain :" + value);
			}
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

	}

}
