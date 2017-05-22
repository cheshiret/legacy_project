/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.businessrule.addbusinessrule;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddBusinessRuleWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeBusinessRulePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This test case verify the steps taken by the User to add a
 *                   Business Rule that needs to be enforced during the sales
 *                   workflow of a particular Product. 
 *              Check point:1. message 1-8 in the SPEC 
 *                          2. cancel button work
 * @Preconditions: need a privilege
 * @SPEC:Use Case Specification: Add Product Business Rule
 * @Task#:AUTO-590 
 * 
 * @author szhou
 * @Date May 30, 2011
 */
public class VerifyCancel extends LicenseManagerTestCase {
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private String privilegeCode;
	private String message1, message2, message3, message4;
	private LicMgrPrivilegeBusinessRulePage rulePg = LicMgrPrivilegeBusinessRulePage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductRulePgFromTopMenu(privilegeCode);
		
		//clean up before testing
		List<String> activeId = rulePg.getRuleIdsByStatus(true);
		lm.deactivateBusinessRules((String[])activeId.toArray(new String[activeId.size()]));
		
		lm.addBusinessRuleForProduct(ruleInfo, false);
		// Effective Date is left blank.
		boolean errormess = this.verifyAddBuisnessRuleError(message1);
		// The Business Rule is not specified (message 1 in the Spec)
		errormess &= this.verifyAddBuisnessRuleField("Rule Name", null);
		// The required value for a rule parameter is not specified (message 4 in the Spec)
		errormess &= this.verifyAddBuisnessRuleParameter();

		ruleInfo.ruleCategory = "Privilege Cross Reference";
		ruleInfo.parameters = new RuleParameters[2];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[1] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].product = "111-RES NON-HUNT 1 DAY WMA";//support scipt
		ruleInfo.parameters[1].product = "111-RES NON-HUNT 1 DAY WMA";//support scipt
		ruleInfo.parameters[0].effectiveDate = "2011-5-32";
		ruleInfo.parameters[1].effectiveDate = "2011-5-32";
		lm.addBusinessRuleForProduct(ruleInfo, false);
		// A rule parameter allows for multiple values, and duplicate values for that rule parameter have been specified.
		errormess &= this.verifyAddBuisnessRuleError(message2);
		// Effective Date entry is not a valid date. (message 3 in the Spec)
		errormess &= this.verifyAddBuisnessRuleField("Effective Date",
				"Wed Jun 1 2011");

		ruleInfo.parameters[0].effectiveDate = DateFunctions
				.getDateAfterToday(2);
		ruleInfo.parameters[1].effectiveDate = DateFunctions
				.getDateAfterToday(10);
		lm.addBusinessRuleForProduct(ruleInfo, false);
		// A rule parameter does not allow for entry of the same Product for which the rule is being defined, and the same Product as the Product for which the rule is being defined has been specified as a parameter value.
		errormess &= this.verifyAddBuisnessRuleError(message3);

		ruleInfo.ruleCategory = "Customer Demographic";
		ruleInfo.name = "Customer must be AT LEAST parameter age on the parameter date";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].effectiveDate = DateFunctions
				.getDateAfterToday(2);
		ruleInfo.parameters[0].onDate = "2011-6-00";
		ruleInfo.parameters[0].age = "-5";
		lm.addBusinessRuleForProduct(ruleInfo, false);
		// A rule parameter requires a Numeric value, and the value entered for that Numeric rule parameter is not an integer value greater than 0.
		errormess &= this.verifyAddBuisnessRuleError(message4);
		// A rule parameter requires a Date value, and the value entered for that Date rule parameter is not a valid date.( message 8 in the Spec)
		errormess &= this.verifyAddBuisnessRuleField("On Date",
				"Tue May 31 2011");

		ruleInfo.parameters[0].onDate = DateFunctions.getDateAfterToday(5);
		ruleInfo.parameters[0].age = "32";
		lm.addBusinessRuleForProduct(ruleInfo, true);
		boolean succ = this.verifyCancelRuleSuccess(ruleInfo);

		if (!errormess) {
			throw new ActionFailedException(
					"The error message on the add product business rule page displayed wrong.");
		}
		if (!succ) {
			throw new ActionFailedException(
					"Failed to cancel a new business rule for product.");
		}

		lm.logOutLicenseManager();

	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilegeCode = "PB1";//support scipt
		message1 = "The Effective Date is required.";
		message2 = "Duplicate Parameter values exist. Duplicates are not allowed.";
//		message3 = "The Parameter value is the same as the Product for which the rule is being defined. Please specify another Parameter value.";
		message3 ="Another active Product Business Rule record already exists for the same Product with the same Business Rule Name, and potentially the same Parameter values. Duplicate active records are not allowed.";
		message4 = "The value entered for Age is not valid. Please enter an integer value greater than 0.";
	}

	private boolean verifyCancelRuleSuccess(PrivilegeBusinessRule rule) {
		LicMgrPrivilegeBusinessRulePage rulePage = LicMgrPrivilegeBusinessRulePage
				.getInstance();

		logger.info("Verify add business rule success or not ...");
	
		boolean result = false;
		try{
			rulePage.getRuleInfoFromBusinessRulePg(rule, 0);
		}catch(ItemNotFoundException e){
			logger.info("Cancel a new business rule for product successful.");
			result = true;
		}

		return result;
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
					.error("The error message not correct, Expect Msg: '"
							+ error
							+ "' Actual Msg:"+message);
			return false;
		}
		return true;
	}

	/**
	 * This method verify rule parameter for "Privilege Cross Reference",the
	 * default value when click add business rule button
	 * 
	 * @return
	 */
	private boolean verifyAddBuisnessRuleParameter() {
		LicMgrPrivilegeAddBusinessRuleWidget rulePg = LicMgrPrivilegeAddBusinessRuleWidget
				.getInstance();

		logger.info("Verify business rule parameter is not null.");

		String fieldValue = rulePg.getPrivilegeValue(0);
		if (fieldValue != null || !"".equals(fieldValue)) {
			logger.info("The field is not null.");
		} else {
			logger
					.error("The value for a Parameter is required. Please specify the Parameter value.");
			return false;
		}
		return true;
	}

	private boolean verifyAddBuisnessRuleField(String field, String value) {
		LicMgrPrivilegeAddBusinessRuleWidget rulePg = LicMgrPrivilegeAddBusinessRuleWidget
				.getInstance();

		logger
				.info("Verify add business rule for privilege field is not null.");

		String fieldValue = "";
		if ("Rule Name".equals(field)) {
			fieldValue = rulePg.getBusinessRule();
			if (fieldValue != null || !"".equals(fieldValue)) {
				logger.info("The field is not null.");
			} else {
				logger
						.error("The Business Rule is required. Please select the Business Rule from the list provided.");
				return false;
			}
		} else if ("Effective Date".equals(field)) {
			fieldValue = rulePg.getEffectiveDate(0);
			if(DateFunctions.compareDates(value, fieldValue) != 0){
				logger.error("The Effective Date value is not " + value + " . Actual value is:"+fieldValue);
				return false;
			} else {
				logger.info("The Effective Date value is " + value + " .");
			} 
		} else if ("On Date".equals(field)) {
			fieldValue = rulePg.getOnDateValue();

			if(DateFunctions.compareDates(value, fieldValue) != 0){
				logger.error("The Date value is not " + value + " . . Actual value is:"+fieldValue);
				return false;
			} else {
				logger.info("The Date value is " + value + " .");
			}
		}
		return true;
	}
}
