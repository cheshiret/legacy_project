package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.product.privilege.businessrule;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerEducationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationDeclarePage;
import com.activenetwork.qa.awo.pages.web.hf.HFProductQuestionnairePage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Purchase a privilege, attest education declare for two types, verify two education records generated
 * @Preconditions:
 * 1. Make sure the account doesn't any education records.
 * 2. Make sure the privilege with the setup exist.
 * 1) Valid From Date Calculation: Prompt for Valid From Date;
 * 2) Education Declare rules setup for following education types:
 * HuntEducation type
 * TrapperEducation type
 * 3. Make sure education type "HuntEducation" and "TrapperEducation" exist in DB:
 * select * from D_EDUCATION_TYPE;
 * @SPEC: 	
 * Licence (Prompt for Valid From) + Education Declare rules with multi education types [TC:108548]
 * @LinkSetUp: 
 * d_web_hf_signupaccount:id=1170
 * d_hf_add_privilege_prd:id=2470
 * d_hf_add_qty_control:id=1700
 * d_hf_add_pricing:id=3542
 * d_hf_add_prvi_license_year:id=2610
 * d_hf_assi_pri_to_store:id=1720
 * d_hf_add_pri_business_rule:id=440
 * @Task#: Auto-1962
 * 
 * @author Lesley Wang
 * @Date  Dec 4, 2013
 */
public class Edu_MultDeclare extends HFSKWebTestCase {

	private HFEducationDeclarePage eduDeclarePg = HFEducationDeclarePage.getInstance();
	private LicMgrCustomerEducationPage custEduPg = LicMgrCustomerEducationPage.getInstance();
	private HFProductQuestionnairePage prdQuePg = HFProductQuestionnairePage.getInstance();
	private Education huntEdu, trapperEdu;
	
	@Override
	public void execute() {
		//Precondition: Delete education records from DB
		hf.deleteEducationRecords(schema, cus.fName, cus.lName);

		// 1. Purchase the privilege to Education Declare page, select the two attest checkboxes and continue
		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrder(cus, privilege, eduDeclarePg);
		hf.attestAndProceedEduDeclare(prdQuePg, huntEdu, trapperEdu);
		trapperEdu.educationNum = hf.getEducationNumByType(schema, cus.fName, cus.lName, trapperEdu.educationType);
		huntEdu.educationNum = hf.getEducationNumByType(schema, cus.fName, cus.lName, huntEdu.educationType);
		
		// 2. Go back to license details page and add the privilege again, verify no education declare page shown
		hf.goBackFromQuestionPg();
		hf.addPrivilegeFromPrdDetailsPg(privilege, prdQuePg);
		hf.setupValidDatesToShoppingCartPg(privilege.validFromDate, privilege.validFromDate);
		hf.checkOutCart(pay);
		hf.signOut();	
		
		// 3. Verify the education records exist in LM
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
				
		cus.email = "hfsk_00037@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.fName = "MultEdu_FN2";
		cus.lName = "MultEdu_LN2";
		cus.dateOfBirth = "1986-01-01";
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4172";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK;

		//Privilege parameters
		privilege.name = "HFSK MultipleEdu2";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.qty = "2";
		privilege.validFromDate = DateFunctions.getToday("E MMM dd yyyy");
		
		huntEdu = new Education();
		huntEdu.educationType = "HuntEducation";
		huntEdu.educationDes = "Hunter Education";
		
		trapperEdu = new Education();
		trapperEdu.educationType = "TrapperEducation";
		trapperEdu.educationDes = "Trapper Education";
	}
	
	private void verifyCustEduExist(boolean isHuntEduExist, boolean isTrapperEduExist) {
		boolean result = MiscFunctions.compareResult("Hunt Education Record exist", isHuntEduExist, custEduPg.checkEducationExists(huntEdu.educationType, huntEdu.educationNum));
		result &= MiscFunctions.compareResult("Trapper Education Record exist", isTrapperEduExist, custEduPg.checkEducationExists(trapperEdu.educationType, trapperEdu.educationNum));
		
		if (!result) {
			throw new ErrorOnPageException("Customer Education Records exist wrongly! Check logger error!");
		}
		logger.info("---Verify Customer Education Records exist correctly!");
	}
}
