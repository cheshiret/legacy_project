package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.businessrule.editbusinessrule;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeBusinessRulePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditRuleWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Cover SPEC error message 7,8,11 and verify edit an active rule to inactive,there will have two scenarios
 * 					1. just status changed: change status to inactive
 * 					2. other changed: keep currently not changed, add a new one
 * @Preconditions:
 * @SPEC:Edit Product Business Rule
 * @Task#:Auto-601
 *
 * @author ssong
 * @Date  Jun 10, 2011
 */
public class ChangeActiveToInactiveAndOtherChanged extends LicenseManagerTestCase{

	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private PrivilegeBusinessRule beforeEditRule = new PrivilegeBusinessRule();
	private PrivilegeBusinessRule afterEditRule = new PrivilegeBusinessRule();
	private String privilegeCode;
	private String message1, message2,message3;
	private LicMgrPrivilegeEditRuleWidget editPg = LicMgrPrivilegeEditRuleWidget.getInstance();
	private LicMgrPrivilegeBusinessRulePage rulePg = LicMgrPrivilegeBusinessRulePage.getInstance();
	private String updatedUser,updatedLocation;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductRulePgFromTopMenu(privilegeCode);
		String id = lm.safeAddBusinessRuleForProduct(ruleInfo)[0];
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getDateAfterToday(-2);
		beforeEditRule = ruleInfo;
		beforeEditRule.id = lm.addBusinessRuleForProduct(beforeEditRule)[0];
		lm.gotoBusinessRuleDetailPageFromPrivilegeDetailPage(beforeEditRule.id);

		ruleInfo.effectiveDate = DateFunctions.getToday();
		ruleInfo.parameters[0].age = "";
		editPg.enterRuleInfo(ruleInfo);
		verifyErrorMsg(message1);

		ruleInfo.parameters[0].age = "abc";
		editPg.enterRuleInfo(ruleInfo);
		verifyErrorMsg(message2);

		ruleInfo.parameters[0].age = "18";
		ruleInfo.parameters[0].onDate = "";
		editPg.enterRuleInfo(ruleInfo);
		verifyErrorMsg(message3);

		ruleInfo.parameters[0].onDate = DateFunctions.getToday();
		editPg.enterRuleInfo(ruleInfo);
		verifyErrorMsg("Another active Product Business Rule record "+id+" already exists for the same Product with the same Business Rule Name, and potentially the same Parameter values. Duplicate active records are not allowed.");

		ruleInfo.effectiveDate = DateFunctions.getDateAfterToday(-10);
		ruleInfo.parameters[0].effectiveDate = ruleInfo.effectiveDate;
		ruleInfo.parameters[0].age = "97";
		editPg.enterRuleInfo(ruleInfo);
		verifyEditSucess();

		//clean up
		lm.deactiveBusinessRuleForProduct(id);

		lm.logOutLicenseManager();
	}

	private void verifyErrorMsg(String msg){
		editPg.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(editPg,rulePg);
		if(page == rulePg){
			throw new ErrorOnPageException("Expect Page Is Edit Page.");
		}
		if(!editPg.getErrorMessage().equals(msg)){
			throw new ErrorOnPageException("Error Msg Not Correct, Expect Msg is "+msg);
		}
	}

	private void verifyEditSucess(){
		switchPage();
		checkNewRuleAddedWhenOtherChanged();

		lm.deactivateBusinessRule();
		rulePg.showAllBusinessRule();
		checkStatusUpdatedAndOtherNotChanged(ruleInfo);
		switchPage();
		checkStatusUpdatedAndOtherNotChanged(beforeEditRule);
		switchPage();

	}

	private void switchPage(){
		editPg.clickOK();
		ajax.waitLoading();
		rulePg.waitLoading();
	}

	/**
	 * Verify just status changed and other not changed
	 * @param rule
	 */
	private void checkStatusUpdatedAndOtherNotChanged(PrivilegeBusinessRule rule){
		lm.gotoBusinessRuleDetailPageFromPrivilegeDetailPage(rule.id);
		rule.status = "Inactive";
		if(!rule.detailcompare(editPg.getBusinessRuleDetailInformation(),0)){
			 throw new ErrorOnPageException("Rule Data Not Correct.");
		}
		checkUpdateInfo();
	}

	/**
	 * Verify a new rule is added and data correct
	 */
	private void checkNewRuleAddedWhenOtherChanged(){
		afterEditRule = rulePg.getRuleInfoFromBusinessRulePg(ruleInfo,0);
		if (afterEditRule!=null&&ruleInfo.listcompare(afterEditRule, 0)) {
			logger
					.info("Edit a new business rule for product successful on the privilege detail pages.");
		} else {
			throw new ErrorOnPageException("Failed to Edit '"
					+ ruleInfo.name
					+ " ' business rule for product on the privilege detail page.");
		}
		if(beforeEditRule.id.equals(afterEditRule.id)){
			throw new ErrorOnPageException("A new rule should be added when edit an exist rule.");
		}
		lm.gotoBusinessRuleDetailPageFromPrivilegeDetailPage(afterEditRule.id);
		ruleInfo.id = afterEditRule.id;
		if(!editPg.getBusinessRuleDetailInformation().detailcompare(ruleInfo,0)){
			 throw new ErrorOnPageException("New Edit Rule Data Not Correct.");
		}
	}

	private void checkUpdateInfo(){
		if(!editPg.getLastUpdateUser().equals(updatedUser)){
			throw new ErrorOnPageException("Updated User "+editPg.getLastUpdateUser()+" Not Correct.");
		}
		if(!editPg.getLastUpdateLocation().equals(updatedLocation)){
			throw new ErrorOnPageException("Updated location "+editPg.getLastUpdateLocation()+" Not Correct.");
		}
		if(!editPg.getLastUpdateTime().matches(DateFunctions.getToday("EEE MMM dd yyyy",DataBaseFunctions.getContractTimeZone(DataBaseFunctions.getSchemaName("MS", env)))+".*")){
			throw new ErrorOnPageException("Updated Date Not Correct.");
		}
	}


	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		ruleInfo.ruleCategory = "Customer Demographic";
		ruleInfo.name = "Customer must be AT MOST parameter age on the parameter date";

		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].age = "18";
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getToday();
		ruleInfo.parameters[0].onDate = DateFunctions.getToday();
		ruleInfo.parameters[0].workAction = "Don't Display";

		privilegeCode = "ER1";
		message1 = "Age is required.";
		message2 = "The value entered for Age is not valid. Please enter an integer value greater than 0.";
		message3 = "On Date is required.";

		updatedUser = DataBaseFunctions.getLoginUserName(login.userName);
		updatedLocation = login.location.split("/")[1];
	}

}
