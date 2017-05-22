package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.education;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerEducationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * 
 * @Description: 
 * 1. Verify remove customer education function
 * 2. Verify warning message when:
 *  No Customer Education records were found for the selected Customer Profile.            
 * @Preconditions: An existed customer
 * @SPEC: <Remove Customer Education.UCS> & <View Customer Education.UCS>
 * @Task#: AUTO-739
 * 
 * @author SWang
 * @Date  Dec 22, 2011
 */
public class RemoveEducation extends LicenseManagerTestCase {
	private LicMgrCustomerEducationPage custEducation = LicMgrCustomerEducationPage.getInstance();
	private Customer customer = new Customer();
	private Education education = new Education();
	private String warnMes;

	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoCustomerDetailFromCustomersQuickSearch(customer);
		lm.gotoCustomerSubTabPage("Education");

		//Add customer education
		this.addCustEducation();
		education.id = custEducation.getEducationID(education.educationType, education.educationNum);
		//Verify warning message doesn't exist if it has one education at least
		this.verifyWarnMes(false);

		//Remove customer education
		lm.manageEducationsForCustomer("Remove", null, education);
		//Verify education doesn't exist after removing
		this.verifyEducationExists(education.id, false);
		//Verify warning message if it has no education
		if(custEducation.getEducationNums()!=0){
			lm.removeAllCustEdus();
		}
		this.verifyWarnMes(true);

		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		customer.customerClass = "Individual";
		customer.fName = "QA-Education";
		customer.lName = "TEST-Operate1";
		customer.dateOfBirth = "Dec 22 2000";

		education.status = "Active";
		education.educationType = "HuntEducation";
		education.educationNum = "5E5V5F5U";
		education.state = "Mississippi";
		education.country = "United States"; 
		education.createApp = "LicenseManager";
		education.createUser = DataBaseFunctions.getLoginUserName(login.userName);

		warnMes = "No Educations were found for the selected Customer";
	}

	private void addCustEducation(){
		boolean existed = custEducation.checkEducationExists(education.educationType, education.educationNum);
		if(!existed){
			lm.manageEducationsForCustomer("Add", null, education);
		}
	}

	private void verifyEducationExists(String educationID, boolean expectedExist) {
		logger.info("Verify the customer education(ID#=" + educationID + ") exists or not.");

		boolean result = custEducation.checkEducationExists(educationID);
		if(expectedExist != result) {
			throw new ErrorOnPageException("The education should(ID#=" + educationID + ") " + (expectedExist ? "" : "NOT") + " exist in education list.");
		}
		logger.info("The education should(ID#=" + educationID + ") really " + (expectedExist ? "exists" : "don't exist"));
	}

	private void verifyWarnMes(boolean expectedExist){
		custEducation.clickEducationTab();
		ajax.waitLoading();
		custEducation.waitLoading();
		if(expectedExist){
			custEducation.verifyWarnMesText(warnMes);
		}else{
			custEducation.verifyWarnMesExsit(false);
		}
	}
}
