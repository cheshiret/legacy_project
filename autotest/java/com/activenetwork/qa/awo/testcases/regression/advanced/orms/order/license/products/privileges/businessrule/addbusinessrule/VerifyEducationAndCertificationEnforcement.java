/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.businessrule.addbusinessrule;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddBusinessRuleWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeBusinessRulePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditRuleWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:This test case verify the steps taken by the User to add a
 *                   Business Rule that needs to be enforced during the sales
 *                   workflow of a particular Product.
 *               Check point:1. message 11 in the SPEC 
 *                           2. cover the 'Education/Certification Enforcement' rule category
 *                           3. add rule successful and verify the rule information
 * @Preconditions:need a privilege
 * @SPEC:Use Case Specification: Add Product Business Rule
 * @Task#:AUTO-590 
 * 
 * @author szhou
 * @Date Jun 1, 2011
 */
public class VerifyEducationAndCertificationEnforcement extends
		LicenseManagerTestCase {

	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private String privilegeCode;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		lm.gotoProductRulePgFromTopMenu(privilegeCode);

		String[] id = lm.safeAddBusinessRuleForProduct(ruleInfo);
		boolean succ = this.verifyAddBusinessRuleSuccess(ruleInfo);

		lm.addBusinessRuleForProduct(ruleInfo);
		// The selected Business Rule being added to the Product requires an Education Type parameter entry and Numeric entry to be specified, and there is already an existing Business Rule record for the same Product that is also "Active" and has the same values as this record for the following: Business Rule Name, and Education Type parameter value.
		boolean errormess = this.verifyAddBuisnessRuleError(this
				.getErrorMessage(id[0]));
		this.gotoPrivilegeDetailPageFromAddBusinessRulePage();
		lm.deactiveBusinessRuleForProduct(id);

		ruleInfo.name = "Customer must have VERIFIED parameter EDUCATION type on file in order to purchase";
		ruleInfo.parameters[0].locationClass = "01 - MDWFP Headquarters";
		ruleInfo.parameters[0].workAction = "Don't Display";
		ruleInfo.parameters[0].matchState=StringUtil.EMPTY; //false;
		String[] id1 = lm.addBusinessRuleForProduct(ruleInfo);
		succ &= this.verifyAddBusinessRuleSuccess(ruleInfo);

		lm.addBusinessRuleForProduct(ruleInfo);
		// The selected Business Rule being added to the Product requires an Education Type parameter entry and Location Class entry to be specified, and there is already an existing Business Rule record for the same Product that is also "Active" and has the same values as this record for the following: Business Rule Name, Education Type parameter value, and Location Class value.
		errormess &= this.verifyAddBuisnessRuleError(this
				.getErrorMessage(id1[0]));
		this.gotoPrivilegeDetailPageFromAddBusinessRulePage();
		lm.deactiveBusinessRuleForProduct(id1);

		ruleInfo.name = "Customer may BYPASS education type requirement parameter number of times";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].effectiveDate = DateFunctions
				.getDateAfterToday(2);
		ruleInfo.parameters[0].educationType = "HuntEducation";
		ruleInfo.parameters[0].passNum = "3";
		String[] id2 = lm.addBusinessRuleForProduct(ruleInfo);
		succ &= this.verifyAddBusinessRuleSuccess(ruleInfo);

		lm.addBusinessRuleForProduct(ruleInfo);
		// The selected Business Rule being added to the Product requires an Education Type parameter entry and State entries to be specified, and there is already an existing Business Rule record for the same Product that is also "Active" and has the same values as this record for the following: Business Rule Name, and Education Type parameter value.
		errormess &= this.verifyAddBuisnessRuleError(this
				.getErrorMessage(id2[0]));
		this.gotoPrivilegeDetailPageFromAddBusinessRulePage();
		lm.deactiveBusinessRuleForProduct(id2);

		ruleInfo.name = "Customer must have parameter CERTIFICATION type on file in order to purchase";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].effectiveDate = DateFunctions
				.getDateAfterToday(2);
		ruleInfo.parameters[0].certificationType = "Trapper Certification";
		ruleInfo.parameters[0].workAction = "Don't Display";
		String[] id3 = lm.addBusinessRuleForProduct(ruleInfo);
		succ &= this.verifyAddBusinessRuleSuccess(ruleInfo);

		lm.addBusinessRuleForProduct(ruleInfo);
		// The selected Business Rule being added to the Product requires a Certification Type parameter entry to be specified, and there is already an existing Business Rule record for the same Product that is also "Active" and has the same values as this record for the following: Business Rule Name, and Certification Type parameter value.
		errormess &= this.verifyAddBuisnessRuleError(this
				.getErrorMessage(id3[0]));
		this.gotoPrivilegeDetailPageFromAddBusinessRulePage();
		lm.deactiveBusinessRuleForProduct(id3);

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

		privilegeCode = "PB3";
		ruleInfo.status = OrmsConstants.ACTIVE_STATUS;
		ruleInfo.ruleCategory = "Education/Certification Enforcement";
		ruleInfo.name = "IF parameter EDUCATION type not on file, Customer must PROVIDE parameter EDUCATION type information in order to purchase";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].effectiveDate = DateFunctions
				.getDateAfterToday(2);
		ruleInfo.parameters[0].educationType = "HuntEducation";
		ruleInfo.parameters[0].matchState="MS"; //true;
		ruleInfo.parameters[0].workAction = "Prompt for Education with Message 4002";

	}

	private String getErrorMessage(String RuleId) {
		String error = "Another active Product Business Rule record "
				+ RuleId
				+ " already exists for the same Product with the same Business Rule Name, and potentially the same Parameter values. Duplicate active records are not allowed.";

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

			if (rulelist != null && ruleInfo.listcompare(rulelist, i)) {
				logger
						.info("Add a new business rule for product successful on the privilege detail pages.");
			} else {
				logger
						.info("Failed to add '"
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
						.info("Failed to add '"
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
					.error("The error message '"
							+ error
							+ "' on the add product business rule page displayed wrong.");
			return false;
		}
		return true;
	}

	private void gotoPrivilegeDetailPageFromAddBusinessRulePage() {
		LicMgrPrivilegeAddBusinessRuleWidget rulePg = LicMgrPrivilegeAddBusinessRuleWidget
				.getInstance();
		LicMgrPrivilegeBusinessRulePage rulePage = LicMgrPrivilegeBusinessRulePage
				.getInstance();

		logger
				.info("go to privilege detail page from add business rule page...");
		rulePg.clickCancel();
		ajax.waitLoading();
		rulePage.waitLoading();

	}

}
