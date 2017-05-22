package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.education;


import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerEducationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This test case is used to verify invalid customer education. When use a invalid education info to add education,
 * this education status should be verified and status should just be active.
 * @Preconditions: Please refer to http://wiki.reserveamerica.com/display/qa/Mock+Verifier to prepare customer and education info
 * 1. prepare a customer firstly
 * 2. in this case, the education type = "HuntEducation"; education number = "VALIDFAILED1234".
 * 3. update ALL_D_EDUCATION_TYPE set VERIFIABLE_IND=1;commit;(SQL file: UpdateEducationVerifiable)
 * @SPEC: Validate Customer Education.UCS
 * @Task#: AUTO-1013
 * 
 * @author PChen
 * @Date  July 02, 2012
 */
public class ValidEducation_Failed extends LicenseManagerTestCase {
	private Education education = new Education();
	private String educationVerifyStatus;
	protected AwoDatabase db;
	@Override
	public void execute() {
        lm.loginLicenseManager(login);
		
		//go to customer education sub tab page
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.gotoEducationSubTabFromCustomerDetailsPg();
		
		//add valid customer education
		education.id = lm.addCustomerEducation(education, true);
		
		//verify customer education status and verify status
		this.verifyEducationVerifiedStatus();
	
		//Clear data for next round
		lm.removeCustomerEducation(education.educationType, education.educationNum);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		cust.customerClass = "Individual";
		cust.fName = "QA-CustEduVeriFailed";
		cust.lName = "TEST-CustEduVeriFailed";
		cust.dateOfBirth = "1974/04/04";
		
		education.educationType = "HuntEducation";
		education.educationNum = "VALIDFAILED1234";
		education.state = "Mississippi";
	
		education.status = "Active";
		education.country = "United States";
		educationVerifyStatus = OrmsConstants.FAILED_STATUS;
	}
	private  void verifyEducationVerifiedStatus(){
		LicMgrCustomerEducationPage custEduPg = LicMgrCustomerEducationPage.getInstance();
		logger.info("Verify customer education status and verify status info.");
		String statusShown = custEduPg.getEducationStatus(education.educationType, education.educationNum); 
		
		if(!MiscFunctions.compareResult("Education Statuse shown-", education.status, statusShown)){
			throw new ErrorOnDataException("Education Status is not correct,please check log....");
		}else {
			logger.info("Education Status is correct.");
		}

		lm.verifyEducationVerifyStatusFromDB(education.id, education.status, educationVerifyStatus, schema);	
	}

}
