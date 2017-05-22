/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.businessrule;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeBusinessRulePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditRuleWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: This case was designed to verify add business rule for rule category: Customer Demographic
 * 				 and it will verify rule info in list page and also rule details page;
 * @Preconditions:
 * @SPEC:
 * @Task#:Auto-874
 * 
 * @author Jane Wang
 * @Date  Mar 14, 2012
 */
public class Add_2 extends LicenseManagerTestCase {
	
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

		privilegeCode = "BR2";
		ruleInfo1.ruleCategory = "Customer Demographic";
		ruleInfo1.name = "Customer must be a RESIDENT in order to purchase this license";
		ruleInfo1.parameters = new RuleParameters[1];
		ruleInfo1.parameters[0] = ruleInfo1.new RuleParameters();
		ruleInfo1.parameters[0].effectiveDate = DateFunctions.getDateAfterToday(2);
		ruleInfo1.parameters[0].workAction = "Don't Display";
		ruleInfo1.parameters[0].residencyProofsParas = new ArrayList<String>();
		ruleInfo1.parameters[0].residencyProofsParas.add("Utility Bill/Lease");
		ruleInfo1.parameters[0].residencyProofsParas.add("Student ID");
		ruleInfo1.isTheOnlyOneRule = true;
		
		ruleInfo2.ruleCategory = "Customer Demographic";
		ruleInfo2.name = "Customer must be AT LEAST parameter age on the parameter date";
		ruleInfo2.parameters = new RuleParameters[1];
		ruleInfo2.parameters[0] = ruleInfo2.new RuleParameters();
		ruleInfo2.parameters[0].effectiveDate = DateFunctions.getDateAfterToday(8);
		ruleInfo2.parameters[0].age = "30";
		ruleInfo2.parameters[0].onDate = DateFunctions.getDateAfterToday(5);
		ruleInfo2.parameters[0].workAction = "Don't Display";
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
				logger.error("Business rule - '"
								+ ruleInfo.name
								+ "' list info isn't correct.");
				result &= false;
			}

			logger.info("View rule detail information ...");
			ruleInfo.id = rulelist.id;
			lm.gotoBusinessRuleDetailPageFromPrivilegeDetailPage(rulelist.id);
			PrivilegeBusinessRule ruledetail = editrule.getBusinessRuleDetailInformation();

			if (ruleInfo.detailcompare(ruledetail, i)) {
				logger.info("Add a new business rule for product successful on the rule detail page.");
			} else {
				logger.error("Business rule - '"
								+ ruleInfo.name
								+ " ' details info isn't correct.");
				result &= false;
			}
			lm.gotoPrivilegeBusinessRulePageFromEditRuleWidget();
		}
		
		if(!result){
			throw new ErrorOnPageException("Failed to add business rule");
		}
	}
}
