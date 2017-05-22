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
 * @Description: This case was designed to verify add business rule for rule category: Education/Certification Enforcement
 * 				 and it will verify rule info in list page and also rule details page;
 * @Preconditions:
 * @SPEC:
 * @Task#:Auto-874
 * 
 * @author Jane Wang
 * @Date  Mar 14, 2012
 */
public class Add_4 extends LicenseManagerTestCase {
	private PrivilegeBusinessRule ruleInfo1 = new PrivilegeBusinessRule();
	private PrivilegeBusinessRule ruleInfo2 = new PrivilegeBusinessRule();
	private String privilegeCode;
	private boolean result = true;
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductRulePgFromTopMenu(privilegeCode);
		
		lm.safeAddBusinessRuleForProduct(ruleInfo1);
		verifyAddBusinessRuleSuccess(ruleInfo1);
		
		lm.safeAddBusinessRuleForProduct(ruleInfo2);
		verifyAddBusinessRuleSuccess(ruleInfo2);
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilegeCode = "BR4";
		ruleInfo1.ruleCategory = "Education/Certification Enforcement";
		ruleInfo1.name = "Customer must have VERIFIED parameter EDUCATION type on file in order to purchase";
		ruleInfo1.parameters = new RuleParameters[2];
		ruleInfo1.parameters[0] = ruleInfo1.new RuleParameters();
		ruleInfo1.parameters[0].effectiveDate = DateFunctions.getDateAfterToday(2);
		ruleInfo1.parameters[0].educationType = "HuntEducation";
		ruleInfo1.parameters[0].locationClass = "01 - MDWFP Headquarters";//hard code
		ruleInfo1.parameters[0].workAction = "Don't Display";	
		ruleInfo1.parameters[1] = ruleInfo1.new RuleParameters();
		ruleInfo1.parameters[1].effectiveDate = DateFunctions.getDateAfterToday(5);
		ruleInfo1.parameters[1].educationType = "HuntEducation";
		ruleInfo1.parameters[1].locationClass = "15 - Active Phone Sales";//hard code
		ruleInfo1.parameters[1].workAction = "Don't Display";
		
		ruleInfo2.ruleCategory = "Education/Certification Enforcement";
		ruleInfo2.name = "Customer may BYPASS education type requirement parameter number of times";
		ruleInfo2.parameters = new RuleParameters[1];
		ruleInfo2.parameters[0] = ruleInfo2.new RuleParameters();
		ruleInfo2.parameters[0].effectiveDate = DateFunctions.getDateAfterToday(3);
		ruleInfo2.parameters[0].educationType = "HuntEducation";
		ruleInfo2.parameters[0].passNum = "3";	
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
