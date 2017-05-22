package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.edudeclare;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationDeclarePage;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationInfomationRequiredPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicensePurchaseDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify the education provide and declaration pages with multiple education types
 * 1. Skip the education required step and go back from declaration page, then purchase again, verify the required page shown again.
 * 2. Input education info in required page and go back from declaration page, then purchage again, verify the required pge not shown.
 * 3. Verify education declare page UI
 * @Preconditions:
 * 1. purchase a privilege setup as :
 * 1) Valid From Date Calculation: Prompt for Valid From Date;
 * 2) Education Declare rules setup for following education types:
 * HuntEducation type
 * TrapperEducation type
 * 3) Education Provide rule setup for following education types:
 * TestEducation type
 * Optional: yes
 * 2. The account has not any education records
 * 3. Make sure education type "HuntEducation", "TrapperEducation", "TestEducation" exist in DB:
 * select * from D_EDUCATION_TYPE;
 * @SPEC: 
 * Licence (Prompt for Valid From) + Education Declare rules with multi education types [TC:108548]
 * Licence (Prompt for Valid From) + Education Provide rule (single education type) + Education Declare rules(multi education types) [TC:108665]
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1220
 * d_hf_add_privilege_prd:id=2480
 * d_hf_add_qty_control:id=1710
 * d_hf_add_pricing:id=3552
 * d_hf_add_prvi_license_year:id=2620
 * d_hf_assi_pri_to_store:id=1730
 * d_hf_add_pri_business_rule:id=450,460
 * @Task#: Auto-1962
 * 
 * @author Lesley Wang
 * @Date  Dec 5, 2013
 */
public class EduDeclarePgWithMultTypes extends HFSKWebTestCase {
	private HFEducationDeclarePage eduDeclarePg = HFEducationDeclarePage.getInstance();
	private HFEducationInfomationRequiredPage eduRequiredPg = HFEducationInfomationRequiredPage.getInstance();
	private Education huntEdu, trapperEdu, testEdu;
	private String pageTitle, eduSectionTitle, eduSectionSubTitle, errorMsg;
	private String[] eduDeclareValues;
	
	@Override
	public void execute() {
		//Precondition: Delete education records from DB
		hf.deleteEducationRecords(schema, cus.fName, cus.lName);
			
		// 1. Verify education required page still shown after skip this step
		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrder(cus, privilege, eduRequiredPg);
		hf.updateEduAndContinue(null, eduDeclarePg);
		this.doNotAttestEduDeclare();
		hf.addPrivilegeFromPrdDetailsPg(privilege, eduRequiredPg);
				
		// 2. Verify education required page not shown after input info and continue
		hf.updateEduAndContinue(testEdu, eduDeclarePg);
		hf.returnToProductDetailsPage();
		
		//Sara[20140403], DEFECT-58639 is Rejected by Steve because of not a valid scenario 
//		hf.addPrivilegeFromPrdDetailsPg(privilege, eduDeclarePg); // Blocked by DEFECT-58639
//		
//		// 3. Verify education declaration page 
//		this.verifyEducationDeclarePgUI(huntEdu, trapperEdu);
//
//		// 4. Verify error message when only check one attest checkbox
//		hf.attestAndProceedEduDeclare(eduDeclarePg, trapperEdu);
//		eduDeclarePg.verifyEduTypeErrorMsg(huntEdu.educationDes, errorMsg);
//		eduDeclarePg.unCheckAttest(trapperEdu.educationDes);
//		
//		hf.attestAndProceedEduDeclare(eduDeclarePg, huntEdu);
//		eduDeclarePg.verifyEduTypeErrorMsg(trapperEdu.educationDes, errorMsg);
		
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		cus.email = "hfsk_00042@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.fName = "MultEdu_FN7";
		cus.lName = "MultEdu_LN7";
		cus.dateOfBirth = "1986-01-01";
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4177";
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

		trapperEdu = new Education();
		trapperEdu.educationType = "TrapperEducation";
		trapperEdu.educationDes = "Trapper Education";
		
		testEdu = new Education();
		testEdu.educationType = "TestEducation";
		testEdu.educationNum = "TestEdu5";
		testEdu.state = "Saskatchewan";
		testEdu.country = "Canada";
		
		pageTitle = "Education Information Required ?Please provide your education information below\\.";
		eduSectionTitle = " Declaration Required"; 
		eduSectionSubTitle = "In order to proceed with this purchase, you must attest the following.";
		eduDeclareValues = new String[] {"Do you have this Hunt Education record\\?\\*", "Do you have this Trapper Education record\\?\\*"};
		errorMsg = "Attestation is required.";
	}
	
	private void doNotAttestEduDeclare(){
		HFEducationDeclarePage eduDeclarePg = HFEducationDeclarePage.getInstance();
		HFLicensePurchaseDetailsPage productDetailsPg = HFLicensePurchaseDetailsPage.getInstance();
		logger.info("Dn not attest to product details page from education declare page.");
		eduDeclarePg.clickIDoNotAttest();
		productDetailsPg.waitLoading();
	}
	
	private void verifyEducationDeclarePgUI(Education...edus){
		boolean passed = MiscFunctions.matchString("Education declare page title", eduDeclarePg.getPageTitle(), pageTitle);
		passed &= MiscFunctions.compareResult("Product name", privilege.name.toUpperCase(), eduDeclarePg.getProductName());
		passed &= MiscFunctions.compareResult("License Year", privilege.licenseYear, eduDeclarePg.getLicenseYear());
		passed &= MiscFunctions.compareResult("Quantity", privilege.qty, eduDeclarePg.getQuantity());
		for (int i = 0; i < edus.length; i++) {
			String des = edus[i].educationDes;
			passed &= MiscFunctions.compareResult("Education delearare section title", des + eduSectionTitle, eduDeclarePg.getEduSectionTitle(i));
			passed &= MiscFunctions.compareResult("Education delearare section sub title", eduSectionSubTitle, eduDeclarePg.getEduSectionSubTitle(i));
			passed &= MiscFunctions.matchString("Declare value", eduDeclarePg.getEduDeclareValue(i), eduDeclareValues[i]);
			passed &= MiscFunctions.compareResult("Attest checkbox", true, eduDeclarePg.isAttestCheckboxExist(des));
		}
		passed &= MiscFunctions.compareResult("Attest and process button", true, eduDeclarePg.isAttestAndProceedButtonDisplayed());
		passed &= MiscFunctions.compareResult("Is do not atters link", true, eduDeclarePg.isIDoNotAttestLinkDisplayed());
		passed &= MiscFunctions.compareResult("Return to product details link", true, eduDeclarePg.isReturnToProductDetailsLinkDisplayed());

		if(!passed){
			throw new ErrorOnPageException("Failed to check all points in educationd declearare page. Please check detauls from previous logs.");
		}
		logger.info("Successfully verify all check points in education declearare page.");
	}
}
