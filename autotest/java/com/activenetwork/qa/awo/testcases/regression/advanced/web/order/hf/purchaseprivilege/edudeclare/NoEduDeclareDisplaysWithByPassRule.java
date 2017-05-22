package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.edudeclare;

import com.activenetwork.qa.awo.pages.web.hf.HFEducationDeclarePage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) No education declaration page when Bypass rule added
 * @Preconditions:
 * d_web_hf_signupaccount, id=810, edudeclare04@test.com, EduDeclare04_FN, EduDeclare04_LN, RCMP #|1R9Y4x4152| |Ontario|
 * d_hf_add_privilege_prd, id=2100, SEA, HFSK EduDeclareLic004
 * d_hf_add_pricing, id=2952, Original
 * d_hf_add_qty_control, id=1330, Yes (Within Same Transaction only)
 * d_hf_add_prvi_license_year, id=1900, 1910
 * d_hf_assi_pri_to_store, id=1350
 * d_hf_add_pri_business_rule, id=150, 170
 * 
 * @Note Per DEFECT-45834, system error during process cart, itt is due to missing config in F_CASH_RCPT_CONF cash and disbursment for defered payments, please check record (pmt_grp, sales_chnl, dsbrsmnt_ind; 7,2,0; 7,2,1)
 * @SPEC: Education Declare page - Display / Not display based on ByPass rule set up [TC:059988] 
 * @Task#: Auto-1775
 * 
 * @author SWang
 * @Date  Jul 15, 2013
 */
public class NoEduDeclareDisplaysWithByPassRule extends HFSKWebTestCase {
	private HFEducationDeclarePage eduDeclarePg = HFEducationDeclarePage.getInstance();

	public void execute() {
		//Precondition: Delete education and education deferral records from DB
		hf.deleteEducationRecords(schema, cus.fName, cus.lName);
		hf.deleteEducationDeferralRecords(schema, cus.fName, cus.lName);

		//Check point 1: Make privilege, no education declare page displays before shopping cart page because of Bypass rule
		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrderToCart(cus, privilege);
		hf.checkOutCart(pay);

		//Check point 2: Purchase same license again, education declare page displays
		hf.makePrivilegeOrder(cus, privilege, eduDeclarePg);

		//Check point 3: Sign out, then sign in, purchase same license again, education declare page displays
		hf.signOut();
		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrder(cus, privilege, eduDeclarePg);

		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "edudeclare04@test.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.fName = "EduDeclare04_FN";
		cus.lName = "EduDeclare04_LN";
		cus.country = "Canada";
		cus.state = "Saskatchewan";
		schema = DataBaseFunctions.getSchemaName("SK", env);
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4152";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK;

		//Privilege parameters
		privilege.name = "HFSK EDUDECLARELIC004";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.qty = "2";
	}
}
