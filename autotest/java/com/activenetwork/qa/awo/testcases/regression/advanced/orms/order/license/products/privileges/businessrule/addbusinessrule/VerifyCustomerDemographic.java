/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.businessrule.addbusinessrule;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddBusinessRuleWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeBusinessRulePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditRuleWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This test case verify the steps taken by the User to add a
 *                   Business Rule that needs to be enforced during the sales
 *                   workflow of a particular Product.
 *               Check point:1. message 9,10,11 in the SPEC 
 *                           2. cover the 'Customer Demographic' rule category
 *                           3. add rule successful and verify the rule information
 * @Preconditions:need a privilege
 * @SPEC:Use Case Specification: Add Product Business Rule
 * @Task#:AUTO-590 
 * 
 * @author szhou
 * @Date May 30, 2011
 */
public class VerifyCustomerDemographic extends
		LicenseManagerTestCase {
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private String privilegeCode;
	private LicMgrPrivilegeBusinessRulePage rulePg = LicMgrPrivilegeBusinessRulePage.getInstance();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductRulePgFromTopMenu(privilegeCode);
		
		//clean up before testing
		List<String> activeId = rulePg.getRuleIdsByStatus(true);
		lm.deactivateBusinessRules((String[])activeId.toArray(new String[activeId.size()]));

		String[] id = lm.addBusinessRuleForProduct(ruleInfo);
		boolean succ = this.verifyAddBusinessRuleSuccess(ruleInfo);

		lm.addBusinessRuleForProduct(ruleInfo, false);
		// The selected Business Rule does not require the entry of any parameter value and there is already an existing Business Rule record for the same Product that is also "Active" and has the same value as this record for the following: Business Rule Name.
		boolean errormess = this.verifyAddBuisnessRuleError(getErrorMessage(
				id[0], "message1"));

		ruleInfo.name = "Customer must be a NON-RESIDENT in order to purchase this license";
		lm.addBusinessRuleForProduct(ruleInfo, false);
		// The selected Business Rule Name is the "Customer must be a RESIDENT in order to purchase this license" and there is already an existing Business Rule record for the same Product that is also "Active" where the Business Rule Name is the "Customer must be a NON-RESIDENT in order to purchase this license".
		errormess &= this.verifyAddBuisnessRuleError(getErrorMessage(id[0],
				"message2"));

		this.gotoPrivilegeDetailPageFromAddBusinessRulePage();
		lm.deactiveBusinessRuleForProduct(id);
		
		id = lm.addBusinessRuleForProduct(ruleInfo);

		ruleInfo.name = "Customer must be a RESIDENT in order to purchase this license";
		lm.addBusinessRuleForProduct(ruleInfo, false);
		// The selected Business Rule Name is the "Customer must be a NON-RESIDENT in order to purchase this license" and there is already an existing Business Rule record for the same Product that is also "Active" where the Business Rule Name is the "Customer must be a RESIDENT in order to purchase this license".
		errormess &= this.verifyAddBuisnessRuleError(getErrorMessage(id[0],
				"message2"));
		this.gotoPrivilegeDetailPageFromAddBusinessRulePage();
		lm.deactiveBusinessRuleForProduct(id);

		ruleInfo.name = "Customer must be AT LEAST parameter age on the parameter date";
		ruleInfo.parameters[0].age = "30";
		ruleInfo.parameters[0].onDate = DateFunctions.getDateAfterToday(5);
		ruleInfo.parameters[0].workAction = "Don't Display";
		id = lm.addBusinessRuleForProduct(ruleInfo);
		succ &= this.verifyAddBusinessRuleSuccess(ruleInfo);

		lm.addBusinessRuleForProduct(ruleInfo, false);
		// The selected Business Rule being added to the Product requires Numeric and/or Date parameter entries to be specified, and there is already an existing Business Rule record for the same Product that is also "Active" and has the same values as this record for the following: Business Rule Name, and Effective Date.
		errormess &= this.verifyAddBuisnessRuleError(getErrorMessage(id[0],
				"message3"));
		this.gotoPrivilegeDetailPageFromAddBusinessRulePage();
		lm.deactiveBusinessRuleForProduct(id);

		ruleInfo.ruleCategory = "Suspension/Revocation";
		ruleInfo.name = "Customer cannot have parameter SUSPENSION type on file (Deny Sale)";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].effectiveDate = DateFunctions
				.getDateAfterToday(2);
		ruleInfo.parameters[0].suspensionType = "Bad Check Suspension";
		ruleInfo.parameters[0].workAction = "Don't Display";
		id = lm.addBusinessRuleForProduct(ruleInfo);
		succ &= this.verifyAddBusinessRuleSuccess(ruleInfo);

		lm.addBusinessRuleForProduct(ruleInfo, false);
		// The selected Business Rule being added to the Product requires a Suspension Type parameter entry to be specified, and there is already an existing Business Rule record for the same Product that is also "Active" and has the same values as this record for the following: Business Rule Name, and Suspension Type parameter value.
		errormess &= this.verifyAddBuisnessRuleError(getErrorMessage(id[0],
				"message3"));
		this.gotoPrivilegeDetailPageFromAddBusinessRulePage();
		lm.deactiveBusinessRuleForProduct(id);

		if (!errormess) {
			throw new ActionFailedException(
					"The error message on the add business rule page displayed wrong.");
		}
		if (!succ) {
			throw new ActionFailedException(
					"Failed to add a new business rule for privilege.");
		}

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilegeCode = "PB2";
		ruleInfo.ruleCategory = "Customer Demographic";
		ruleInfo.name = "Customer must be a RESIDENT in order to purchase this license";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].effectiveDate = DateFunctions
				.getDateAfterToday(2);
		ruleInfo.parameters[0].workAction = "Don't Display";
		ruleInfo.isTheOnlyOneRule = true;
	}

	private String getErrorMessage(String RuleId, String message) {
		String error = "";
		if ("message1".equals(message)) {
			error = "Another active Product Business Rule record "
					+ RuleId
					+ " already exists for the same Product with the same Business Rule Name, and potentially the same Parameter values. Duplicate active records are not allowed.";
		} else if ("message2".equals(message)) {
			error = "Another active Product Business Rule record "
					+ RuleId
					+ " already exists for the same Product that contradicts this Business Rule. Contradicting active records are not allowed.";
		} else if ("message3".equals(message)) {
			error = "Another active Product Business Rule record "
					+ RuleId
					+ " already exists for the same Product with the same Business Rule Name, and potentially the same Parameter values. Duplicate active records are not allowed.";
		}
		return error;
	}

	private boolean verifyAddBusinessRuleSuccess(PrivilegeBusinessRule ruleInfo) {
		LicMgrPrivilegeBusinessRulePage rulePage = LicMgrPrivilegeBusinessRulePage
				.getInstance();
		LicMgrPrivilegeEditRuleWidget editrule = LicMgrPrivilegeEditRuleWidget
				.getInstance();

		logger.info("Verify add business rule success or not ...");

		for (int i = 0; i < ruleInfo.parameters.length; i++) {
			logger.info("View rule list ...");
			PrivilegeBusinessRule rulelist = rulePage
					.getRuleInfoFromBusinessRulePg(ruleInfo,i);
			if (rulelist != null &&ruleInfo.listcompare(rulelist, i)) {
				logger
						.info("Add a new business rule for product successful on the privilege detail pages.");
			} else {
				logger
						.error("Failed to add '"
								+ ruleInfo.name
								+ " ' business rule for product on the privilege detail page.");
				return false;
			}

			logger.info("View rule detail information ...");
			ruleInfo.id = rulelist.id;
			lm.gotoBusinessRuleDetailPageFromPrivilegeDetailPage(rulelist.id);
			PrivilegeBusinessRule ruledetail = editrule
					.getBusinessRuleDetailInformation();

			if (ruleInfo.detailcompare(ruledetail, i)) {
				logger
						.info("Add a new business rule for product successful on the rule detail page.");
			} else {
				logger
						.error("Failed to add '"
								+ ruleInfo.name
								+ " ' business rule for product on the rule detail page.");
				return false;
			}
			editrule.clickOK();
			ajax.waitLoading();
			rulePage.waitLoading();
		}
		return true;
	}

	private boolean verifyAddBuisnessRuleError(String error) {
		LicMgrPrivilegeAddBusinessRuleWidget rulePg = LicMgrPrivilegeAddBusinessRuleWidget
				.getInstance();
		String message = rulePg.getErrorMessage();

		logger.info("Verify add business rule for privilege error message.");
		if (error.equals(message)) {
			logger
					.info("The error message '"
							+ error
							+ "' on the add product business rule page displayed correct.");
		} else {
			logger
					.error("The error message not correct,Expect result:'"
							+ error
							+ "' Actual result: '"+message+"'.");
			return false;
		}
		return true;
	}

	private void gotoPrivilegeDetailPageFromAddBusinessRulePage() {
		LicMgrPrivilegeAddBusinessRuleWidget rulePg = LicMgrPrivilegeAddBusinessRuleWidget
				.getInstance();
		LicMgrPrivilegeBusinessRulePage rulePage = LicMgrPrivilegeBusinessRulePage
				.getInstance();

		logger.info("go to privilege detail page from add business rule page...");
		rulePg.clickCancel();
		ajax.waitLoading();
		rulePage.waitLoading();

	}
}
