package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.customer.education.edit;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerEducationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrEditEducationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Verify business rule in edit education page for Active and Verified education
* @Preconditions: Have Individual customer with fName: QA-Customer5, mName:QaTest-CusotmerProfile-5,lName:TEST-Profile5 and date of birth is '1978-08-23'
 *                 and two educations
 *               -- 	HuntEducation, 111111136(Active)
 *               --     HuntEducation, 318844746(Verified)
 * @SPEC: Edit Customer Education
 * @Task#: AUTO-569
 * 
 * @author SWang
 * @Date  May 26, 2011
 */
public class EditCustomerEducation_BusinessRule extends LicenseManagerTestCase{
	private LicMgrEditEducationPage editEdu = LicMgrEditEducationPage.getInstance();
	private LicMgrCustomerEducationPage custEdu = LicMgrCustomerEducationPage.getInstance();
	private Education edu_1 = new Education();
	private Education edu_2 = new Education();

	public void execute() {
		//Login license manager
		lm.loginLicenseManager(login);
		lm.gotoCustomerSearchPageFromCustomersTopMenu();

		cust.licenseNum = lm.getCustomerNum(cust, schema);
		lm.gotoCuscomerDetailsFromSearchPg(cust.licenseType, cust.licenseNum , cust.customerClass);
		lm.gotoEducationSubTabFromCustomerDetailsPg();
		
		//Add two educations
		lm.manageEducationsForCustomer("Add", null, edu_1, edu_2);

		edu_1.id = custEdu.getEducationID(edu_1.educationType, edu_1.educationNum);
		edu_1.createApp = custEdu.getEducationCreationApplication(edu_1.educationType, edu_1.educationNum);
		edu_1.createDate = custEdu.getEducationCreationDate(edu_1.educationType, edu_1.educationNum);
		edu_1.createUser = custEdu.getEducationCreationUser(edu_1.educationType, edu_1.educationNum);
		edu_2.id = custEdu.getEducationID(edu_2.educationType, edu_2.educationNum);

		//Verify un-edit fields in edit education page
		lm.gotoEditEducationFromEducationPg(edu_1.educationNum);
		this.verifyUnEditFields();

		//Verify the isn't a hyperlink for verified education
		lm.switchEducationAndEditEducationPg(editEdu);
		edu_2.id = custEdu.getEducationID(edu_2.educationType, edu_2.educationNum);
		custEdu.checkVerifiedEducationIDCanNotHyperlinked(edu_2.id);

		//Logout license manager
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		//Login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		cust.customerClass = "INDIVIDUAL";
		cust.licenseType = "MDWFP #";//"Customer #";
		cust.fName = "QA-Customer5";
		cust.mName = "QaTest-CusotmerProfile-5";
		cust.lName = "TEST-Profile5";
		cust.dateOfBirth = "Aug 23 1978";
		
		edu_1.educationType = "HuntEducation";
		edu_1.educationNum = "111111136";
		edu_1.state = "Mississippi";
		edu_1.country = "United States";
		
		edu_2.educationType = "HuntEducation";
		edu_2.educationNum = "318844746";// should be verified education
		edu_2.state = "Mississippi";
		edu_2.country = "United States";
	}

	private void verifyUnEditFields(){
		editEdu.checkEduIdUnEditable(edu_1.id);
		editEdu.checkEduStatusUnEditable();
		editEdu.checkEduTypeUnEditable();
		editEdu.checkEduCreationApp(edu_1.createApp);
		editEdu.checkEduCreationDate(edu_1.createDate);
		editEdu.checkEduCreationUser(edu_1.createUser);
	}
}
