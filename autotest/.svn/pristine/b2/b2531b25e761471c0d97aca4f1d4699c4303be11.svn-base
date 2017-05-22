package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.businessrule.viewbusinessrule;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeBusinessRulePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditRuleWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description: show "Active" record by default
 * @Preconditions:
 * @SPEC:View Product Business Rule
 * @Task#:AUTO-603
 * 
 * @author eliang
 * @Date  Jun 2, 2011
 */
public class ShowActiveRecordByDefault extends LicenseManagerTestCase{
	LicMgrPrivilegeEditRuleWidget ruleEditPg = LicMgrPrivilegeEditRuleWidget.getInstance();
	LicMgrPrivilegeBusinessRulePage rulePage = LicMgrPrivilegeBusinessRulePage.getInstance();
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	List<Exception> list = new ArrayList<Exception>();
	private String privilegeCode;
	
	public void execute() {
		// add a business rule
		lm.loginLicenseManager(login);
		lm.gotoProductRulePgFromTopMenu(privilegeCode);
//		String[] ruleIds = lm.addBusinessRuleForProduct(ruleInfo);
		String ruleIds[] = lm.safeAddBusinessRuleForProduct(ruleInfo);
		
		lm.deactiveBusinessRuleForProduct(ruleIds[0]);
		
		this.verifyRuleIdNoexisted(ruleInfo);
		
		//logout license manager
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilegeCode = "NL2";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.ruleCategory = "Customer Demographic";
		ruleInfo.name = "Customer must be a RESIDENT in order to purchase this license";
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getDateAfterToday(3);
		ruleInfo.isTheOnlyOneRule = true;
	}
	
	private void verifyRuleIdNoexisted(PrivilegeBusinessRule ruleInfo){
		if(rulePage.isRuleExisting(ruleInfo.name)){
			throw new ErrorOnPageException("The business rule id should not be shown in the page.");
		}
	}
}
