/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.businessrule;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeBusinessRulePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditRuleWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: This case was designed to verify add business rule for rule category: Suspension/Revocation
 * 				 and it will verify rule info in list page and also rule details page;
 * @Preconditions:
 * @SPEC:
 * @Task#:Auto-874
 * 
 * @author Jane Wang
 * @Date  Mar 14, 2012
 */
public class Add_3 extends LicenseManagerTestCase {
	
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private String privilegeCode;
	private boolean result = true;
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductRulePgFromTopMenu(privilegeCode);

		lm.safeAddBusinessRuleForProduct(ruleInfo);

		verifyAddBusinessRuleSuccess(ruleInfo);
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilegeCode = "BR3";
		ruleInfo.ruleCategory = "Suspension/Revocation";
		ruleInfo.name = "Customer cannot have parameter SUSPENSION type on file (Allow Sale)";
		ruleInfo.parameters = new RuleParameters[2];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getDateAfterToday(2);
		ruleInfo.parameters[0].suspensionType = "Failure to Submit Harvest Report";
		
		ruleInfo.parameters[1] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[1].effectiveDate = DateFunctions.getDateAfterToday(5);
		ruleInfo.parameters[1].suspensionType = "Bad Check Suspension";
	}
	
	private void verifyAddBusinessRuleSuccess(PrivilegeBusinessRule ruleInfo) {
		LicMgrPrivilegeBusinessRulePage rulePage = LicMgrPrivilegeBusinessRulePage.getInstance();
		LicMgrPrivilegeEditRuleWidget editrule = LicMgrPrivilegeEditRuleWidget.getInstance();

		logger.info("Verify add business rule success or not ...");

		for (int i = 0; i < ruleInfo.parameters.length; i++) {
			logger.info("View rule list ...");
			PrivilegeBusinessRule rulelist = rulePage.getRuleInfoFromBusinessRulePg(ruleInfo,i);

			if (rulelist != null && ruleInfo.listcompare(rulelist, i)) {
				logger.info("Add a new business rule for product successful in rule list.");
			} else {
				logger.error("Failed to add '"
								+ ruleInfo.name
								+ " ' business rule in rule list.");
				result &= false;
			}

			logger.info("View rule detail information ...");
			ruleInfo.id = rulelist.id;
			lm.gotoBusinessRuleDetailPageFromPrivilegeDetailPage(rulelist.id);
			PrivilegeBusinessRule ruledetail = editrule.getBusinessRuleDetailInformation();

			if (ruleInfo.detailcompare(ruledetail, i)) {
				logger.info("Add a new business rule for product successful on the rule detail page.");
			} else {
				logger.error("Failed to add '"
								+ ruleInfo.name
								+ " ' business rule for product on the rule detail page.");
				result &= false;
			}
			lm.gotoPrivilegeBusinessRulePageFromEditRuleWidget();
		}
		
		if(!result){
			throw new ErrorOnPageException("Failed to add business rule");
		}
	}
}
