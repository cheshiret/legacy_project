package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.edudeclare;

import java.util.List;

import com.activenetwork.qa.awo.pages.web.hf.HFEducationDeclarePage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicensePurchaseDetailsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) Verify profile education records after click "Do not attest" or "Attest & proceed" in education declare page
 * @Preconditions:
 * d_web_hf_signupaccount, id=790, edudeclare02@test.com, RCMP #|1R9Y4x4150| |Ontario|
 * d_hf_add_privilege_prd, id=2080, SEA, HFSK EduDeclareLic002
 * d_hf_add_pricing, id=2932, Original
 * d_hf_add_qty_control, id=1310, Yes (Within Same Transaction only)
 * d_hf_add_prvi_license_year, id=1860, 1870
 * d_hf_assi_pri_to_store, id=1330
 * d_hf_add_pri_business_rule, id=130
 * 
 * @SPEC: Education record generated in profile [TC:059989] 
 * @Task#: Auto-1775
 * 
 * @author SWang
 * @Date  Jul 15, 2013
 */
public class EduGeneratedInProfile extends HFSKWebTestCase {
	private HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
	private HFEducationDeclarePage eduDeclarePg = HFEducationDeclarePage.getInstance();

	public void execute() {
		//Precondition: Delete education records from DB
		hf.deleteEducationRecords(schema, cus.fName, cus.lName);

		//Make privilege to declare page
		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrder(cus, privilege, eduDeclarePg);

		//Check point 1: Do not attest education declare to product details page
		doNotAttestEduDeclare();

		//Check point 2: Attest & Proceed education declare to shopping cart page and education record generated
		hf.addPrivilegeFromPrdDetailsPg(privilege, eduDeclarePg);
		attestAndProceedEduDeclare();
		verifyOnlyOneEduRecordGenerated();

		//Check point 3: Abandon cart, make privilege again, no education declare page displays before shopping cart directly
		hf.abandonCart();
		hf.makePrivilegeOrder(cus, privilege, shoppingCartPg);

		//Abandon and sign out
		hf.abandonCart();
		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "edudeclare02@test.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.fName = "EduDeclare02_FN";
		cus.lName = "EduDeclare02_LN";
		cus.country = "Canada";
		cus.state = "Saskatchewan";
		schema = DataBaseFunctions.getSchemaName("SK", env);
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4150";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK;

		//Privilege parameters
		privilege.name = "HFSK EDUDECLARELIC002";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.qty = "2";
	}

	private void doNotAttestEduDeclare(){
		HFEducationDeclarePage eduDeclarePg = HFEducationDeclarePage.getInstance();
		HFLicensePurchaseDetailsPage productDetailsPg = HFLicensePurchaseDetailsPage.getInstance();
		logger.info("Dn not attest to product details page from education declare page.");
		eduDeclarePg.clickIDoNotAttest();
		productDetailsPg.waitLoading();
	}

	private void attestAndProceedEduDeclare(){
		HFEducationDeclarePage eduDeclarePg = HFEducationDeclarePage.getInstance();
		HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
		logger.info("Attest and proceed to shoopint cart page from education declare page.");
		eduDeclarePg.checkAttest();
		eduDeclarePg.clickAttestAndProceed();
		shoppingCartPg.waitLoading();
	}

	/**
	 * Verify only one education record is generated 
	 */
	private void verifyOnlyOneEduRecordGenerated(){
		List<String> eduRecords = hf.getEducationRecords(schema, cus.fName, cus.lName, cus.country, cus.state);
		if(eduRecords.size()!=1){
			throw new ErrorOnPageException("Should be only one education record is generated when shcema:"+schema+", fName:"+cus.fName+", lName:"+cus.lName+", country:"+cus.country+" and state:"+cus.state);
		}
		logger.info("Successfully verify only one education record is generated when shcema:"+schema+", fName:"+cus.fName+", lName:"+cus.lName+", country:"+cus.country+" and state:"+cus.state);
	}
}
