package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.businessrule.viewbusinessrule;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditRuleWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:Add business rule and verify details are shown successfully
 * @Preconditions:
 * @SPEC:View Product Business Rule
 * @Task#:AUTO-603
 * 
 * @author eliang
 * @Date Jun 1, 2011
 */
public class ViewBusinessRuleDetailDisplay extends LicenseManagerTestCase {
	LicMgrPrivilegeEditRuleWidget ruleEditPg = LicMgrPrivilegeEditRuleWidget.getInstance();
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private String privilegeCode;
	
	public void execute() {
		// add a business rule
		lm.loginLicenseManager(login);
		lm.gotoProductRulePgFromTopMenu(privilegeCode);
//		String[] ruleIds = lm.addBusinessRuleForProduct(ruleInfo);
		String[] ruleIds = lm.safeAddBusinessRuleForProduct(ruleInfo);

		// goto business rule detail and verify 
		lm.gotoBusinessRuleDetailPageFromPrivilegeDetailPage(ruleIds[0]);
		ruleInfo.detailcompare(ruleEditPg.getBusinessRuleDetailInformation(), 0);
		lm.deactivateBusinessRule();
		
		//logout
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilegeCode = "A12";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.ruleCategory = "Customer Demographic";
		ruleInfo.name = "Customer must be a RESIDENT in order to purchase this license";
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getDateAfterToday(2);
		ruleInfo.isTheOnlyOneRule = true;
	}

}
