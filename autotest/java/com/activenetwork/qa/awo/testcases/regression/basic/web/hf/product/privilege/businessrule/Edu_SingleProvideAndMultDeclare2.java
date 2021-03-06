package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.product.privilege.businessrule;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerEducationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationDeclarePage;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationInfomationRequiredPage;
import com.activenetwork.qa.awo.pages.web.hf.HFProductQuestionnairePage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Purchase a privilege by a customer with an education record of HuntEducation, input education required info and attested education declaration, 
 * two education type records for "TrapperEducation" and "TestEducation" generated. 
 * @Preconditions:
 * 1. purchase a privilege setup as :
 * 1) Valid From Date Calculation: Prompt for Valid From Date;
 * 2) Education Declare rules setup for following education types:
 * HuntEducation type
 * TrapperEducation type
 * 3) Education Provide rule setup for following education types:
 * TestEducation type
 * Optional: yes
 * 2. The account has an education record of HuntEducation
 * 3. Make sure education type "HuntEducation", "TrapperEducation", "TestEducation" exist in DB:
 * select * from D_EDUCATION_TYPE;
 * @SPEC: 	
 * Product with multi education rules + Account with education records [TC:108673]
 * @LinkSetUp: 
 * d_web_hf_signupaccount:id=1190
 * d_hf_add_privilege_prd:id=2480
 * d_hf_add_qty_control:id=1710
 * d_hf_add_pricing:id=3552
 * d_hf_add_prvi_license_year:id=2620
 * d_hf_assi_pri_to_store:id=1730
 * d_hf_add_pri_business_rule:id=450,460
 * @Task#: Auto-1962
 * 
 * @author Lesley Wang
 * @Date  Dec 4, 2013
 */
public class Edu_SingleProvideAndMultDeclare2 extends HFSKWebTestCase {
	private HFEducationDeclarePage eduDeclarePg = HFEducationDeclarePage.getInstance();
	private HFEducationInfomationRequiredPage eduRequiredPg = HFEducationInfomationRequiredPage.getInstance();
	private LicMgrCustomerEducationPage custEduPg = LicMgrCustomerEducationPage.getInstance();
	private HFProductQuestionnairePage prdQuePg = HFProductQuestionnairePage.getInstance();
	private Education huntEdu, trapperEdu, testEdu;
	
	@Override
	public void execute() {
		//Precondition: Delete education records from LM
		lm.loginLicenseManager(loginLM);
		lm.gotoCustomerDetailFromTopMenu(cus);
		lm.gotoEducationSubTabFromCustomerDetailsPg();
		lm.removeAllCustEdus();
			
		// Add an education record of HuntEducation for the customer
		lm.addCustomerEducation(huntEdu, true);
		lm.logOutLicenseManager();
		
		// Purchase the privilege to Education Information Required page, and then Declare page, then finish the purchase
		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrder(cus, privilege, eduRequiredPg);
		hf.updateEduAndContinue(testEdu, eduDeclarePg);
		hf.attestAndProceedEduDeclare(prdQuePg, trapperEdu);
		hf.setupValidDatesToShoppingCartPg(privilege.validFromDate);
		hf.checkOutCart(pay);
		trapperEdu.educationNum = hf.getEducationNumByType(schema, cus.fName, cus.lName, trapperEdu.educationType);
		testEdu.educationNum = hf.getEducationNumByType(schema, cus.fName, cus.lName, testEdu.educationType);
		hf.signOut();
		
		// Verify the education records exist in LM
		lm.loginLicenseManager(loginLM);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cus);
		lm.gotoEducationSubTabFromCustomerDetailsPg();
		this.verifyCustEduExist(true, true);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login info for LM
		loginLM.location = "SK Admin - Auto/SASK";
		
		cus.email = "hfsk_00039@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.fName = "MultEdu_FN4";
		cus.lName = "MultEdu_LN4";
		cus.dateOfBirth = "1986-01-01";
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4174";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK;

		//Privilege parameters
		privilege.name = "HFSK MultipleEdu3";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.validFromDate = DateFunctions.getToday("E MMM dd yyyy");
		
		huntEdu = new Education();
		huntEdu.educationType = "HuntEducation";
		huntEdu.educationDes = "Hunter Education";
		huntEdu.educationNum = "HuntEdu";
		huntEdu.status = "Active";
		huntEdu.state = "Saskatchewan";
		huntEdu.country = "Canada";
		
		trapperEdu = new Education();
		trapperEdu.educationType = "TrapperEducation";
		trapperEdu.educationDes = "Trapper Education";
		
		testEdu = new Education();
		testEdu.educationType = "TestEducation";
		testEdu.educationDes = "Test Education";
		testEdu.educationNum = "TestEdu2";
		testEdu.status = "Active";
		testEdu.state = "Saskatchewan";
		testEdu.country = "Canada";
	}
	
	private void verifyCustEduExist(boolean isTrapperEduExist, boolean isTestEduExist) {
		boolean result = MiscFunctions.compareResult("Trapper Education Record exist", isTrapperEduExist, custEduPg.checkEducationExists(trapperEdu.educationType, trapperEdu.educationNum));
		result &= MiscFunctions.compareResult("Test Education Record exist", isTestEduExist, custEduPg.checkEducationExists(testEdu.educationType, testEdu.educationNum));
		
		if (!result) {
			throw new ErrorOnPageException("Customer Education Records exist wrongly! Check logger error!");
		}
		logger.info("---Verify Customer Education Records exist correctly!");
	}
}
