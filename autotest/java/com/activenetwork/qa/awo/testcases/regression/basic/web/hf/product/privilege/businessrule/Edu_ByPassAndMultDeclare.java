package com.activenetwork.qa.awo.testcases.regression.basic.web.hf.product.privilege.businessrule;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerEducationPage;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationDeclarePage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 1. Purchase a privilege by a customer without any education records, verify no HuntEducation type shown on declare page,
 * 2. Attest education declare for TrapperEducation type, and verify the related education record generated.
 * 3. Purchase again and verify no TraaperEducation type shown on declare page.
 * 4. Attest education declare for HuntEducation type, and verify the related education record generated.
 * @Preconditions:
 * 1. Make sure the account doesn't any education records.
 * 2. Make sure the privilege with the setup exist.
 * 1) Valid From Date Calculation: Prompt for Valid From Date;
 * 2) Education Declare rules setup for following education types:
 * HuntEducation type
 * TrapperEducation type
 * 3) ByPass rule setup for following education type 1 time:
 * HuntEducation
 * 3. Make sure education type "HuntEducation" and "TrapperEducation" exist in DB:
 * select * from D_EDUCATION_TYPE;
 * @SPEC: 	
 * Licence (Prompt for Valid From) + ByPass + Education Declare rules with multi education types [TC:108677]
 * @LinkSetUp: 
 * d_web_hf_signupaccount:id=1160
 * d_hf_add_privilege_prd:id=2460
 * d_hf_add_qty_control:id=1690
 * d_hf_add_pricing:id=3532
 * d_hf_add_prvi_license_year:id=2600
 * d_hf_assi_pri_to_store:id=1710
 * d_hf_add_pri_business_rule:id=420,430
 * @Task#: Auto-1962
 * 
 * @author Lesley Wang
 * @Date  Dec 4, 2013
 */
public class Edu_ByPassAndMultDeclare extends HFSKWebTestCase {
	private HFEducationDeclarePage eduDeclarePg = HFEducationDeclarePage.getInstance();
	private LicMgrCustomerEducationPage custEduPg = LicMgrCustomerEducationPage.getInstance();
	private Education huntEdu, trapperEdu;
	
	@Override
	public void execute() {
		//Precondition: Delete education records from DB
		hf.deleteEducationRecords(schema, cus.fName, cus.lName);
		hf.deleteEducationDeferralRecords(schema, cus.fName, cus.lName);

		// 1. Purchase the privilege to Education Declare page, check only Trapper Education type displayed
		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrder(cus, privilege, eduDeclarePg);
		this.verifyEduTypeDeclarExist(false, true);
		hf.attestAndProceedEduDeclare(privilege.validFromDate, privilege.validFromDate);
		hf.checkOutCart(pay);
		trapperEdu.educationNum = hf.getEducationNumByType(schema, cus.fName, cus.lName, trapperEdu.educationType);
		hf.signOut();	
		
		// 2. Purchase the privilege again, check only Hunter Education type shown on Declare page
		privilege.qty = "1";
		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrder(cus, privilege, eduDeclarePg);
		this.verifyEduTypeDeclarExist(true, false);
		hf.attestAndProceedEduDeclare(privilege.validFromDate);
		hf.checkOutCart(pay);
		huntEdu.educationNum = hf.getEducationNumByType(schema, cus.fName, cus.lName, huntEdu.educationType);
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
		
		cus.email = "hfsk_00036@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.fName = "MultEdu_FN1";
		cus.lName = "MultEdu_LN1";
		cus.dateOfBirth = "1986-01-01";
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4171";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK;

		//Privilege parameters
		privilege.name = "HFSK MultipleEdu1";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.qty = "2";
		privilege.validFromDate = DateFunctions.getToday("E MMM dd yyyy", DataBaseFunctions.getContractTimeZone(schema));
		
		huntEdu = new Education();
		huntEdu.educationType = "HuntEducation";
		huntEdu.educationDes = "Hunter Education";
		
		trapperEdu = new Education();
		trapperEdu.educationType = "TrapperEducation";
		trapperEdu.educationDes = "Trapper Education";
	}

	private void verifyEduTypeDeclarExist(boolean isHuntEduExist, boolean isTrapperEduExist) {
		boolean huntEduActual = eduDeclarePg.isEduTypeDeclarationExist(huntEdu.educationDes);
		boolean trapperEduActual = eduDeclarePg.isEduTypeDeclarationExist(trapperEdu.educationDes);
		
		boolean result = MiscFunctions.compareResult("Hunter Education Declare Exist", isHuntEduExist, huntEduActual);
		result &= MiscFunctions.compareResult("Trapper Education Declare Exist", isTrapperEduExist, trapperEduActual);
		
		if (!result) {
			throw new ErrorOnPageException("The two education types are shown wrongly in Declaration page!");
		}
		logger.info("Verify the two education types shown correctly in Declaration page!");
	}
	
	private void verifyCustEduExist(boolean isHuntEduExist, boolean isTrapperEduExist) {
		boolean result = MiscFunctions.compareResult("Hunt Education Record exist", isHuntEduExist, custEduPg.checkEducationExists(huntEdu.educationType, huntEdu.educationNum));
		result = MiscFunctions.compareResult("Trapper Education Record exist", isTrapperEduExist, custEduPg.checkEducationExists(trapperEdu.educationType, trapperEdu.educationNum));
		
		if (!result) {
			throw new ErrorOnPageException("Customer Education Records exist wrongly! Check logger error!");
		}
		logger.info("---Verify Customer Education Records exist correctly!");
	}
}
