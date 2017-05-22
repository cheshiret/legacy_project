package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.edudeclare;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Verify education declare page displays or not before shopping cart page after update rule effective date and date of birth
 * @Preconditions:
 * d_web_hf_signupaccount, id=800, edudeclare03@test.com, EduDeclare03_FN, EduDeclare03_LN, RCMP #|1R9Y4x4151| |Ontario|
 * d_hf_add_privilege_prd, id=2090, SEC, HFSK EduDeclareLic003
 * d_hf_add_pricing, id=2942, Original
 * d_hf_add_qty_control, id=1320, Yes (Within Same Transaction only)
 * d_hf_add_prvi_license_year, id=1880, 1890
 * d_hf_assi_pri_to_store, id=1340
 * d_hf_add_pri_business_rule, id=140
 * 
 * @SPEC: Education Declare page - Display / Not display based on ByPass rule set up [TC:059988] 
 * @Task#: Auto-1775
 * 
 * @author SWang
 * @Date  Jul 15, 2013
 */
public class EduDeclareDisplaysOrNot extends HFSKWebTestCase {
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();

	public void execute() {
		//Precondition: Delete education records from DB
		hf.deleteEducationRecords(schema, cus.fName, cus.lName);

		//Check point 1: When current date is earlier than rule effective date and DOB is on or after rule DOB
		//Result: No education declare page before shopping cart page
		lm.loginLicenseManager(loginLM);
		ruleInfo.id = lm.editBusinessRule(privilege.code, ruleInfo)[0];
		lm.logOutLicenseManager();

		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrder(cus, privilege, shoppingCartPg);
		hf.signOut();

		//Check point 2: When current date is equals or later than rule effective date and DOB is before rule DOB
		//Result: No education declare page before shopping cat page
		lm.loginLicenseManager(loginLM);
		ruleInfo.effectiveDate = DateFunctions.getDateAfterToday(-1);
		ruleInfo.parameters[0].effectiveDate = ruleInfo.effectiveDate;
		ruleInfo.parameters[0].dateOfBirth = "01-01-"+DateFunctions.getYearAfterCurrentYear(-15);
		ruleInfo.id = lm.editBusinessRule(privilege.code, ruleInfo)[0];
		lm.logOutLicenseManager();

		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrder(cus, privilege, shoppingCartPg);
		hf.signOut();

		//Check point 3: When current date is equals or later than rule effective date and DOB is on or after rule DOB
		//Result: Has education declare page
		lm.loginLicenseManager(loginLM);
		ruleInfo.parameters[0].dateOfBirth = "01-01-"+DateFunctions.getYearAfterCurrentYear(-17);
		lm.editBusinessRule(privilege.code, ruleInfo);
		lm.logOutLicenseManager();

		hf.signIn(url, cus.email, cus.password);
		hf.makePrivilegeOrderToCart(cus, privilege);
		String orderNum = hf.checkOutCart(pay);

		//Check point 4: Finish order
		//Result: education record is generated, purchase again, no education declare page
		hf.makePrivilegeOrder(cus, privilege, shoppingCartPg);
		hf.signOut();

		//Void license order from License Manager
		loginLM.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		lm.loginLicenseManager(loginLM);
		lm.reversePrivilegeOrderToCleanUp(orderNum, privilege.operateReason, privilege.operateNote, pay);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		//License manager parameters
		loginLM.location = "SK Admin - Auto/SASK";

		cus.email = "edudeclare03@test.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		cus.fName = "EduDeclare03_FN";
		cus.lName = "EduDeclare03_LN";
		cus.country = "Canada";
		cus.state = "Saskatchewan";
		schema = DataBaseFunctions.getSchemaName("SK", env);
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4151";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK;

		//Privilege parameters
		privilege.code  = "SEC";
		privilege.name = "HFSK EDUDECLARELIC003";
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.qty = "2";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Automation-test";	

		//Privilege rule parameters
		ruleInfo.id = hf.getPrivilegeRuleID(schema, privilege.code);
		ruleInfo.ruleCategory = "Education/Certification Enforcement";
		ruleInfo.name = "IF parameter EDUCATION type not on file, Customer must DECLARE parameter EDUCATION type information in order to purchase";
		ruleInfo.effectiveDate = DateFunctions.getDateAfterToday(5);
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].educationType = "TrapperEducation";
		ruleInfo.parameters[0].effectiveDate = ruleInfo.effectiveDate;
		ruleInfo.parameters[0].dateOfBirth = "01-01-"+DateFunctions.getYearAfterCurrentYear(-16);
		ruleInfo.isTheOnlyOneRule = true;
	}
}
