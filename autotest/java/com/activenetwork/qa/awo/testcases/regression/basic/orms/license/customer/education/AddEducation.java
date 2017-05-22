package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.education;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerEducationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description: Verify adding customer education function
 * @Preconditions: An existed customer
 * @SPEC: Add Customer Education.UCS
 * @Task#: AUTO-739
 * 
 * @author SWang
 * @Date  Dec 22, 2011
 */
public class AddEducation extends LicenseManagerTestCase {
	private LicMgrCustomerEducationPage custEducation = LicMgrCustomerEducationPage.getInstance();
	private Customer customer = new Customer();
	private Education education = new Education();
	TimeZone zone = null;

	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromCustomersQuickSearch(customer);
		lm.gotoCustomerSubTabPage("Education");

		//Remove specific education
		this.RemoveEducation();

		//Add customer education
		lm.manageEducationsForCustomer("Add", null, education);

		//Get education ID and Create date 
		education.createDate = DateFunctions.getToday("MMM dd,yyyy hh:mm a z", zone);
		education.id = custEducation.getEducationID(education.educationType, education.educationNum);

		//View customer education list
		custEducation.verifyEducatioList(education);

		//View customer education detail
		lm.verifyCustomerEducationDetailInfo(education);

		//Remove customer education
		lm.logOutLicenseManager();
	}

	private void RemoveEducation() {
		boolean existed = custEducation.checkEducationExists(education.educationType, education.educationNum);
		if(existed){
			lm.manageEducationsForCustomer("Remove", null, education);
		}
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		customer.customerClass = "Individual";
		customer.fName = "QA-Education";
		customer.lName = "TEST-Operate";
		customer.dateOfBirth = "Dec 22 2011";

		education.status = "Active";
		education.educationType = "HuntEducation";
		education.educationNum = "1A9Z2B8Y";
		education.state = "Mississippi";
		education.country = "United States";
		education.createApp = "LicenseManager";
		education.createUser = DataBaseFunctions.getLoginUserName(login.userName);

		zone=DataBaseFunctions.getContractTimeZone("");
	}
}
