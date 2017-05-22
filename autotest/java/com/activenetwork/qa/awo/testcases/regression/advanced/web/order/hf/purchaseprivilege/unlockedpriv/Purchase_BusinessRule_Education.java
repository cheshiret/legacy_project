package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.unlockedpriv;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Education;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationDeclarePage;
import com.activenetwork.qa.awo.pages.web.hf.HFEducationInfomationRequiredPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFMOUnlockedPrivTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Purchase a unlocked privilege which has business rule setup: 
 * 1) IF parameter EDUCATION type not on file, Customer must DECLARE parameter EDUCATION type information in order to purchase;
 * @Preconditions:
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=970
 * d_hf_add_privilege_prd:id=2210
 * d_hf_add_pricing:id=3102
 * d_hf_assi_pri_to_store:id=1460
 * d_hf_add_prvi_license_year:id=2240
 * d_hf_add_qty_control:id=1440
 * d_hf_add_hunt:id=90
 * d_hf_assign_priv_to_hunt:id=90
 * d_hf_add_hunt_license_year:id=140
 * d_hf_add_species:id=30
 * 
 * @SPEC: Unlocked Privilege sales flow - business rule [TC:068251]
 * @Task#: Auto-1833
 * 
 * @author Lesley Wang
 * @Date  Aug 14, 2013
 */
public class Purchase_BusinessRule_Education extends HFMOUnlockedPrivTestCase {
	private HFLicenseCategoriesListPage catListPg = HFLicenseCategoriesListPage.getInstance();
	private HFEducationDeclarePage eduDeclarePage = HFEducationDeclarePage.getInstance();
	private HFEducationInfomationRequiredPage eduInfoPg = HFEducationInfomationRequiredPage.getInstance();
	private PrivilegeBusinessRule rule_Declare, rule_Provide;
	
	@Override
	public void execute() {
		// Import 1 unlocked privilege record
		this.prepareUnlockedPriv(fileName, privilege, cus.custNum, hunt);
		
		// In LM, remove all customer educations, add one business rule for the privilege
		this.setPrecondition(rule_Declare);
		
		// Verify the rule: IF parameter EDUCATION type not on file, Customer must DECLARE parameter EDUCATION type information in order to purchase;
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.makePrivilegeOrder(cus, privilege, eduDeclarePage);
		hf.attestAndProceedEduDeclare();
		hf.abandonCart();
		hf.gotoLicenseCategoriesListPg();
		hf.filterPrivilegeInLicenseCategoriesListPg(privilege.licenseYear);
		catListPg.verifyPrivilegeExist(privilege.code, privilege.name, hunt.getDescription(), hunt.getNumOfTagQty(), true);
		hf.signOut();
		
		// Remove the education,  deactivate the rule and add a new rule: IF parameter EDUCATION type not on file, Customer must PROVIDE parameter EDUCATION type information in order to purchase
		this.setPrecondition(rule_Provide);
		
		// purchase and make sure Education page shown, add education to shopping cart, abandon cart
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.makePrivilegeOrder(cus, privilege, eduInfoPg);
		hf.updateEduToShoppingCartPg(cus.education);
		hf.abandonCart();
		hf.gotoLicenseCategoriesListPg();
		hf.filterPrivilegeInLicenseCategoriesListPg(privilege.licenseYear);
		catListPg.verifyPrivilegeExist(privilege.code, privilege.name, hunt.getDescription(), hunt.getNumOfTagQty(), true);
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Customer Info
		cus.fName = "ULPriv07_FN"; // d_web_hf_signupaccount, id=970
		cus.lName = "ULPriv07_LN";
		cus.dateOfBirth = "01/01/"+ DateFunctions.getYearAfterCurrentYear(-16);
		cus.custNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		cus.identifier.identifierType = hf.getIdenTypeName(schema, IDEN_CONSERVATION_ID, false, false).replace("Number", "#");
		cus.identifier.identifierNum = cus.custNum;
		cus.education = new Education();
		cus.education.educationNum = "EduRuleVerify";
		//Sara[12/09/2013] This is a low priority PCR confirmed with Ranjita.
		//This should be the value query "select name from d_ref_country where DSCR = 'United States of America'", not DSCR "United States of America";
		cus.education.country = "United States";
		cus.education.state = "Missouri";
		
		// Hunt info
		hunt = new HuntInfo();
		hunt.setHuntCode("H09");
		hunt.setDescription("HFMO Hunt009");
		hunt.setPointTypeCode(hf.getPointTypeCode(schema, "Deer"));	
		hunt.setNumOfTagQty("1");
		
		// Privilege Info
		privilege.licenseYear = hf.getFiscalYear(schema); //Integer.toString(DateFunctions.getCurrentYear());
		privilege.code = "MOK";
		privilege.name = "HFMO HuntLic006";
		
		fileName = "Purchase_BusinessRule_Education";
		
		// Business Rule Info
		rule_Declare = new PrivilegeBusinessRule();
		rule_Declare.ruleCategory = "Education/Certification Enforcement";
		rule_Declare.name = "IF parameter EDUCATION type not on file, Customer must DECLARE parameter EDUCATION type information in order to purchase";
		rule_Declare.parameters = new RuleParameters[1];
		rule_Declare.parameters[0] = rule_Declare.new RuleParameters();
		rule_Declare.parameters[0].educationType = "HuntEducation";
		rule_Declare.parameters[0].effectiveDate = DateFunctions.getToday();
		rule_Declare.parameters[0].prompt = "Education Declare Rule Required";
		
		rule_Provide = new PrivilegeBusinessRule();
		rule_Provide.ruleCategory = rule_Declare.ruleCategory;
		rule_Provide.name = "IF parameter EDUCATION type not on file, Customer must PROVIDE parameter EDUCATION type information in order to purchase";
		rule_Provide.parameters = new RuleParameters[1];
		rule_Provide.parameters[0] = rule_Provide.new RuleParameters();
		rule_Provide.parameters[0].educationType = "HuntEducation";
		rule_Provide.parameters[0].effectiveDate = DateFunctions.getToday();
	}

	/** Remove all customer educations and add a new business rule */
	private void setPrecondition(PrivilegeBusinessRule rule) {
		lm.loginLicenseManager(loginLM);
		lm.gotoCustomerDetailFromTopMenu(cus);
		lm.gotoEducationSubTabFromCustomerDetailsPg();
		lm.removeAllCustEdus();
		
		lm.gotoProductRulePgFromTopMenu(privilege.code);
		lm.deactivateBusinessRules();
		lm.safeAddBusinessRuleForProduct(rule);
		lm.logOutLicenseManager();
	}
}
