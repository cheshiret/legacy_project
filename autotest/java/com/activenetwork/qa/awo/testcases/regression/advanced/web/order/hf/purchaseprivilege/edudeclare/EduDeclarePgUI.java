package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.edudeclare;

import com.activenetwork.qa.awo.pages.web.hf.HFEducationDeclarePage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) Verify product related, Attest and process button, Is do not atters link and navigation link in "Education Declare" page
 * @Preconditions:
 * d_web_hf_signupaccount, id=780, edudeclare01@test.com, RCMP #|1R9Y4x4149| |Ontario|
 * d_hf_add_privilege_prd, id=2070, SEA, HFSK EduDeclareLic001
 * d_hf_add_pricing, id=2922, Original
 * d_hf_add_qty_control, id=1300, Yes (Within Same Transaction only)
 * d_hf_add_prvi_license_year, id=1850
 * d_hf_assi_pri_to_store, id=1320
 * d_hf_add_pri_business_rule, id=160

 * @SPEC: Education Declare page UI (for Licence) [TC:056289] 
 * @Task#: Auto-1775
 * 
 * @author Swang
 * @Date  Jul 11, 2013
 */
public class EduDeclarePgUI extends HFSKWebTestCase {
	private String pageTitle, eduSectionTitle, eduSectionSubTitle, eduDeclareValue;
	private HFEducationDeclarePage eduDeclarePg = HFEducationDeclarePage.getInstance();

	public void execute() {
		//Lookup account, make privilege to declare page
		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrder(cus, privilege, eduDeclarePg);

		//Check education declare page UI
		this.verifyEducationDeclarePgUI();

		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "edudeclare01@test.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.fName = "EduDeclare01_FN";
		cus.lName = "EduDeclare01_LN";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4149";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK;
		
		//Privilege parameters
		privilege.name = "HFSK EDUDECLARELIC001";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.qty = "2";

		//UI parameters
		pageTitle = "Education Information ?Please provide your education information below\\.";
		eduSectionTitle = "Hunter Education Declaration Required"; //Lesley[20130902]: UI change. "Hunter Education Declaration";
//		eduSectionSubTitle = "In order to proceed with this purchase, you must acknowledge the following question:";
		eduSectionSubTitle = "In order to proceed with this purchase, you must attest the following.";
		eduDeclareValue = "Do you hav the recommended Hunt Education?*";
	}

	private void verifyEducationDeclarePgUI(){
		boolean passed = MiscFunctions.matchString("Education declare page title", eduDeclarePg.getPageTitle(), pageTitle);
		passed &= MiscFunctions.compareResult("Product name", privilege.name.toUpperCase(), eduDeclarePg.getProductName());
		passed &= MiscFunctions.compareResult("License Year", privilege.licenseYear, eduDeclarePg.getLicenseYear());
		passed &= MiscFunctions.compareResult("Quantity", privilege.qty, eduDeclarePg.getQuantity());
		passed &= MiscFunctions.compareResult("Education delearare section title", eduSectionTitle, eduDeclarePg.getEduSectionTitle());
		passed &= MiscFunctions.compareResult("Education delearare section sub title", eduSectionSubTitle, eduDeclarePg.getEduSectionSubTitle());
		passed &= MiscFunctions.compareResult("Declare value", eduDeclareValue, eduDeclarePg.getEduDeclareValue());
		passed &= MiscFunctions.compareResult("Attest and process button", true, eduDeclarePg.isAttestAndProceedButtonDisplayed());
		passed &= MiscFunctions.compareResult("Is do not atters link", true, eduDeclarePg.isIDoNotAttestLinkDisplayed());
		passed &= MiscFunctions.compareResult("Return to product details link", true, eduDeclarePg.isReturnToProductDetailsLinkDisplayed());

		if(!passed){
			throw new ErrorOnPageException("Failed to check all points in educationd declearare page. Please check detauls from previous logs.");
		}
		logger.info("Successfully verify all check points in education declearare page.");
	}
}
