package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.customer.education;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerEducationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This test case is used to verify valid customer education. When use a valid education info to add education,
 * this education status should be verified and status should be verified.
 * @Preconditions: Please refer to http://wiki.reserveamerica.com/display/qa/Mock+Verifier(D_EDUCATION_TYPE table for education type) to prepare customer
 * and education info, such as verifier.ms-mock-education.valid.EDU111111=1980-02-22
 * 1. prepare a customer firstly, and the date of birth is 1980-02-22
 * 2. in this case, the education type = "HuntEducation"; education number = "EDU111111".
 * 3. update ALL_D_EDUCATION_TYPE(common schema) set VERIFIABLE_IND=1;commit;
 * 4. make sure VERIFIABLE_IND=1 of table D_Education_Type(MS schema)
 * @SPEC: Validate Customer Education.UCS
 * @Task#: AUTO-1013
 * 
 * @author PChen
 * @Date  July 02, 2012
 */
public class ValidEducation_Verified extends LicenseManagerTestCase {
	private Education education = new Education();
	private String educationVerifyStatus;
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
		cust.fName = "QA-CustEduVerified";
		cust.lName = "TEST-CustEduVerified";
		cust.dateOfBirth = "May 18 1977";//verified, refer to: http://wiki.reserveamerica.com/display/qa/Mock+Verifier
		
		education.educationType = "HuntEducation";
		education.educationNum = "397290360";//verified
		education.state = "Mississippi";
	
		education.status = "Active";
		education.country = "United States";
		educationVerifyStatus = OrmsConstants.VERIFIED_STATUS;

	}
	
	private  void verifyEducationVerifiedStatus(){
		LicMgrCustomerEducationPage custEduPg = LicMgrCustomerEducationPage.getInstance();
		logger.info("Verify customer education status and verify status info.");
		
		String statusShown = custEduPg.getEducationStatus(education.educationType, education.educationNum); 
		               
		boolean result = true;
		
		result &= MiscFunctions.compareResult("Education Statuse shown", educationVerifyStatus, statusShown);
		if(!result){
			throw new ErrorOnDataException("Education Status is not correct,please check log....");
		}else {
			logger.info("Education Status is correct.");
		}

		//status_id is '1' and verify_status_id is '1' in table 'c_cust_hfp_education'
		lm.verifyEducationVerifyStatusFromDB(education.id, educationVerifyStatus, educationVerifyStatus, schema);	
	}

}
