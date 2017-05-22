package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.education.add;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrAddEducationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerEducationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Verify fields in add customer education
 * @Preconditions: Have Individual customer with fName: QA-Customer1, mName:QaTest-CusotmerProfile-1,lName:TEST-Profile1 and date of birth is '30-Dec-1966'
 * @SPEC: Add Customer Education.UCS
 * @Task#: AUTO_568
 * 
 * @author SWang
 * @Date  May 24, 2011
 */
public class AddCustomerEducation_BusinessRule extends LicenseManagerTestCase{
	private LicMgrAddEducationPage addEducation = LicMgrAddEducationPage.getInstance();
	private LicMgrCustomerEducationPage custEducation = LicMgrCustomerEducationPage.getInstance();
	private List<String> statusOptions = new ArrayList<String>();
	private List<String> stateOptions = new ArrayList<String>();
	private List<String> countryOptions = new ArrayList<String>();
	private List<String> eduTypeOptions = new ArrayList<String>();

	public void execute() {
		//Login license manager
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();

		cust.licenseNum = lm.getCustomerNum(cust, schema);
		lm.gotoCuscomerDetailsFromSearchPg(cust.licenseType, cust.licenseNum , cust.customerClass);

		//Go to add education page
		lm.gotoEducationSubTabFromCustomerDetailsPg();
		lm.switchEducationAndAddEducationPg(custEducation);

		//Verify UI display
		//Status
		addEducation.verifyStatusOption(statusOptions.get(0));
		addEducation.verifyDropDownListOptionsExist(addEducation.statusRegx, statusOptions);
		//State
		addEducation.verifyDropDownListOptionsExist(addEducation.stateRegx, stateOptions);
		//Country
		addEducation.verifyDropDownListOptionsExist(addEducation.CountryRegx, countryOptions);
		//Education
		//		List<String> educationNames = lm.getCustEducationTypesFromDB(schema);
		addEducation.verifyDropDownListOptionsExist(addEducation.typeRegx, eduTypeOptions);//educationNames

		//Logout
		lm.switchEducationAndAddEducationPg(addEducation);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		//Login information
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";

		cust.customerClass = "INDIVIDUAL";
		cust.licenseType = "MDWFP #";//"Customer #";
		cust.lName = "TEST-Profile1";
		cust.mName = "QaTest-CusotmerProfile-1";
		cust.fName = "QA-Customer1";
		cust.dateOfBirth = "Apr 04 1976";

		//Status: No option: Inactive, Removed and Verified
		statusOptions.add("Active");
		statusOptions.add("Manually Verified");
		//State
		stateOptions.add("");
		//Country
		countryOptions.add("");
		//Education type
		eduTypeOptions.add("HuntEducation");
	}
}
