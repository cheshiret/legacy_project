package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.dateofbirth;

import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrAddEducationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerEducationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerInputDateOfBirthWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify if the DOB is required for verifying, activating and editing Customer Education
 * @Preconditions:
 * @SPEC: <<Customer Profile Management.doc>>, TC3933 step137-139,150-152
 * @Task#: AUTO-940
 * 
 * @author qchen
 * @Date  Mar 15, 2012
 */
public class RequiredDOBForVerifiedEducation extends LicenseManagerTestCase {
	private LicMgrCustomerEducationPage educationPage = LicMgrCustomerEducationPage.getInstance();
	private String originalVerifiedEducationNum;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//1. update customer class - Business optional indicator as TRUE
		lm.updateCustomerClassOptionalDobIndicator(schema, OrmsConstants.BUSINESS_CUSTOMER_CLASS, true);
		
		//2. check if the needed customer is existing in system, if not, add new one
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, cust.mName, schema);
		if(cust.custNum.length() < 1) {
			cust.custNum = lm.createNewCustomer(cust);
		}
		
		lm.gotoCustomerSearchPageFromCustomersTopMenu();
		lm.gotoCustomerDetails(cust.lName, cust.fName, cust.customerClass);
		lm.gotoCustomerSubTabPage("Education");
		//3. add a VERIFIED education without DOB
		boolean exists = educationPage.checkEducationExists(cust.education.educationType, cust.education.educationNum);
		if(exists) {
			lm.removeCustomerEducation(cust.education.educationType, cust.education.educationNum);
		}
		this.updateCustomerDateOfBirth("");
		lm.addCustomerEducation(cust.education, true);
		this.inputDateOfBirth(cust.dateOfBirth);
		cust.education.id = educationPage.getEducationID(cust.education.educationType, cust.education.educationNum);
		String statusOnPage = educationPage.getEducationStatus(cust.education.educationType, cust.education.educationNum);
		this.verifyIdentifierStatus(OrmsConstants.VERIFIED_STATUS, statusOnPage);
		
		//4. this step covers TC3933 step137-139, "Activate" or "Manually Verify" customer education without DOB
		lm.changeCustomerEducationStatus("Deactivate", cust.education.educationType, cust.education.educationNum);
		this.updateCustomerDateOfBirth("");
		lm.changeCustomerEducationStatus(new String[]{"Activate", "Manually Verify"}[new Random().nextInt(1)], cust.education.educationType, cust.education.educationNum);
		this.inputDateOfBirth(cust.dateOfBirth);
		statusOnPage = educationPage.getEducationStatus(cust.education.educationType, cust.education.educationNum);
		this.verifyIdentifierStatus(OrmsConstants.VERIFIED_STATUS, statusOnPage);
		
		//5. this step covers TC3933 step 150-152, edit customer education without DOB
		lm.changeCustomerEducationStatus("Deactivate", cust.education.educationType, cust.education.educationNum);
		cust.education.educationNum = "" + String.valueOf(DateFunctions.getCurrentTime());
		cust.education.id = lm.updateCustomerEducation(cust.education);
		lm.changeCustomerEducationStatus("Activate", cust.education.id);
		this.updateCustomerDateOfBirth(StringUtil.EMPTY);
		cust.education.educationNum = originalVerifiedEducationNum;//IMPORTANT
		lm.updateCustomerEducation(cust.education);
		this.inputDateOfBirth(cust.dateOfBirth);
		statusOnPage = educationPage.getEducationStatus(cust.education.educationType, cust.education.educationNum);
		this.verifyIdentifierStatus(OrmsConstants.VERIFIED_STATUS, statusOnPage);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		/**
		 * IMPORTANT: the date of birth must be verified as the corresponding education info
		 * refer to:  http://wiki.reserveamerica.com/display/qa/Mock+Verifier
		 */
		//verifier.ms-mock-education.valid.341470583=1978-09-13
		cust.dateOfBirth = "1978-09-13";
		cust.education.educationType = "HuntEducation";
		cust.education.educationNum = "341470583";
		cust.education.state = "Mississippi";
		cust.education.country = "United States";
		
		originalVerifiedEducationNum = cust.education.educationNum;
		
		//customer info
		cust.fName = "Required";
		cust.mName = "DateOfBirth";
		cust.lName = "ForEducation";
		cust.customerClass = "Business";//PCR 2926 is limited for Non-Individual customer classes
		cust.businessName = "RequiredDOBForEducation";
		cust.hPhone = "02985250452";
		cust.bPhone = "02968685958";
		cust.email = "hfishing@reserveamerica.com";
		cust.physicalAddr.address = "Keji 2nd Road";
		cust.physicalAddr.city = "York";
		cust.physicalAddr.state = "Alabama";
		cust.physicalAddr.county = "Sumter";
		cust.physicalAddr.zip = "36925";
		cust.physicalAddr.country = "United States";
	}
	
	/**
	 * Update customer Date Of Birth in customer detail page
	 * @param dob
	 */
	private void updateCustomerDateOfBirth(String dob) {
		logger.info("Update customer Date Of Birth to " + dob);
		
		educationPage.setDateOfBirth(dob);
		educationPage.clickApply();
		ajax.waitLoading();
		educationPage.waitLoading();
	}
	
	/**
	 * Input date of birth during adding customer identifier process
	 * @param dob
	 */
	private void inputDateOfBirth(String dob) {
		LicMgrCustomerInputDateOfBirthWidget inputDOBPage = LicMgrCustomerInputDateOfBirthWidget.getInstance();
		
		logger.info("Verify and input Date Of Birth.");
		String type = inputDOBPage.getMessage().split("\\\"")[1].split("\\\"")[0].trim();
		if(!type.equals(cust.education.educationType)) {
			throw new ErrorOnPageException("The expected System required input date of birth value for Education Type - " + cust.education.educationType + ", not " + type);
		} else logger.info("Actual education type is correct - " + type);
		
		inputDOBPage.setDateOfBirth(dob);
		inputDOBPage.clickOK();
		ajax.waitLoading();
		educationPage.waitLoading();
	}
	
	/**
	 * Verify identifier status
	 * @param expected
	 * @param actual
	 */
	private void verifyIdentifierStatus(String expected, String actual) {
		boolean result = MiscFunctions.compareResult("Education Status", expected, actual);
		if(!result) {
			throw new ErrorOnPageException("The education status should be " + expected);
		} else logger.info("Education status is correct.");
	}
	
	public void addCustomerEducation(Education education, boolean isSafeAdd) {
		LicMgrCustomerEducationPage educationPage = LicMgrCustomerEducationPage.getInstance();
		LicMgrAddEducationPage addEducationPage = LicMgrAddEducationPage.getInstance();
		LicMgrConfirmDialogWidget confirmWidget = LicMgrConfirmDialogWidget.getInstance();
		LicMgrCustomerInputDateOfBirthWidget inputDobWidget = LicMgrCustomerInputDateOfBirthWidget.getInstance();
		
		logger.info("Add Customer Education(Type=" + education.educationType + ", Num=" + education.educationNum + ").");
		if(isSafeAdd && educationPage.checkEducationExists(education.educationType, education.educationNum)) {
			lm.removeCustomerEducation(education.educationType, education.educationNum);
		}
		educationPage.clickAddEducation();
		ajax.waitLoading();
		addEducationPage.waitLoading();
		addEducationPage.setEducation(education);
		ajax.waitLoading();
		addEducationPage.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(inputDobWidget, confirmWidget, educationPage);
		if (page == confirmWidget) {
			confirmWidget.clickOK();
			ajax.waitLoading();
			browser.waitExists(inputDobWidget, educationPage);
		}
	}
}
