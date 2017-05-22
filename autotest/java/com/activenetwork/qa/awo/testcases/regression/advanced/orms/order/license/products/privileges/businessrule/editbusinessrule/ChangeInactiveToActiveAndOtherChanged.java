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
 * @Description: This test case used to verify change an inactive rule to active rule,not only edit status and other criteria
 * @Preconditions:
 * @SPEC:Edit Product Business Rule
 * @Task#:Auto-601
 *
 * @author ssong
 * @Date  Jun 14, 2011
 */
public class ChangeInactiveToActiveAndOtherChanged extends LicenseManagerTestCase{

	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private PrivilegeBusinessRule ruleInfo2 = new PrivilegeBusinessRule();
	private PrivilegeBusinessRule beforeEditRule = new PrivilegeBusinessRule();
	private PrivilegeBusinessRule afterEditRule = new PrivilegeBusinessRule();
	private String privilegeCode;
	private LicMgrPrivilegeEditRuleWidget editPg = LicMgrPrivilegeEditRuleWidget.getInstance();
	private LicMgrPrivilegeBusinessRulePage rulePg = LicMgrPrivilegeBusinessRulePage.getInstance();
	private String updatedUser,updatedLocation;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		lm.gotoProductRulePgFromTopMenu(privilegeCode);
		//prepare data to get an exist business rule R1
		String id = lm.safeAddBusinessRuleForProduct(ruleInfo)[0];

		//add another rule with the different product and deactivate it
		ruleInfo.parameters[0].product = "121-APR RES ALL GAME H/F";//"121-APR RES ALL GAME H&F";

		ruleInfo.id = lm.addBusinessRuleForProduct(ruleInfo)[0];

		lm.deactiveBusinessRuleForProduct(ruleInfo.id);
		rulePg.showAllBusinessRule();
		lm.gotoBusinessRuleDetailPageFromPrivilegeDetailPage(ruleInfo.id);

		//update new added inactive rule to active with the same product as R1
		ruleInfo.parameters[0].product = "112-RES FAM NON-HUNT 1 DAY";
		ruleInfo.effectiveDate = DateFunctions.getDateAfterToday(-10);
		ruleInfo.status = "Active";
		editPg.enterRuleInfo(ruleInfo);
		verifyErrorMsg("Another active Product Business Rule record "+id+" already exists for the same Product with the same Business Rule Name, and potentially the same Parameter values. Duplicate active records are not allowed.");


		//switch page and prepare another rule R2
		switchPage();
		rulePg.showActiveBusinessRule();
		String id2 = lm.safeAddBusinessRuleForProduct(ruleInfo2)[0];

		//add another rule with same product and different purchase type
		ruleInfo2.parameters[0].purchaseType = "Original And Replacement";
		ruleInfo2.effectiveDate = DateFunctions.getDateAfterToday(-10,DataBaseFunctions.getContractTimeZone(DataBaseFunctions.getSchemaName("MS", env)));
		ruleInfo2.parameters[0].effectiveDate = ruleInfo2.effectiveDate;
		beforeEditRule = ruleInfo2;
		beforeEditRule.id = lm.addBusinessRuleForProduct(beforeEditRule)[0];

		lm.deactiveBusinessRuleForProduct(beforeEditRule.id);
		rulePg.showAllBusinessRule();
		lm.gotoBusinessRuleDetailPageFromPrivilegeDetailPage(beforeEditRule.id);
		ruleInfo2.parameters[0].purchaseType = "Original";

		editPg.enterRuleInfo(ruleInfo2);
		verifyErrorMsg("Another active Product Business Rule record "+id2+" already exists for the same Product with the same Business Rule Name, and potentially the same Parameter values. Duplicate active records are not allowed.");

		ruleInfo2.parameters[0].purchaseType = "Duplicate";
		editPg.enterRuleInfo(ruleInfo2);
		verifyEditSucess();

		//clean up
		lm.deactiveBusinessRuleForProduct(id,id2);

		lm.logOutLicenseManager();
	}

	private void verifyErrorMsg(String msg){
		editPg.clickOK();

		editPg.waitErrorMsgExist();

		String errMsg = editPg.getErrorMessage();
		if(!msg.equals(errMsg)){
			throw new ErrorOnPageException("Error Msg Not Correct, Expect Msg is <"+msg + "> but actual is <" + errMsg + ">");
		}
	}

	private void verifyEditSucess(){
		editPg.clickOK();
		ajax.waitLoading();
		rulePg.waitLoading();
		checkNewRuleAddedWhenChangeInactiveToActive();

		lm.deactivateBusinessRule();
		checkStatusUpdatedAndOtherNotChanged(beforeEditRule);
		switchPage();

	}

	private void switchPage(){
		editPg.clickCancel();
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
	private void checkNewRuleAddedWhenChangeInactiveToActive(){
		ruleInfo2.status = "Active";
		afterEditRule = rulePg.getRuleInfoFromBusinessRulePg(ruleInfo2,0);
		if (afterEditRule!=null&&ruleInfo2.listcompare(afterEditRule, 0)) {
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
		ruleInfo2.id = afterEditRule.id;
		if(!editPg.getBusinessRuleDetailInformation().detailcompare(ruleInfo2,0)){
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

		ruleInfo.ruleCategory = "Privilege Cross Reference";
		ruleInfo.name = "Cannot purchase IF parameter privilege on file";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].product = "112-RES FAM NON-HUNT 1 DAY";
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getToday();
		ruleInfo.parameters[0].matchLicYear = false;
		ruleInfo.parameters[0].workAction = "Don't Display";

		ruleInfo2.ruleCategory = "Privilege Cross Reference";
		ruleInfo2.name = "IF parameter privilege is on file, automatically include in response for printing";
		ruleInfo2.parameters = new RuleParameters[1];
		ruleInfo2.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo2.parameters[0].product = "112-RES FAM NON-HUNT 1 DAY";
		ruleInfo2.parameters[0].effectiveDate = DateFunctions.getToday();
		ruleInfo2.parameters[0].matchLicYear = false;
		ruleInfo2.parameters[0].purchaseType = "Original";

		privilegeCode = "ER2";

		updatedUser = DataBaseFunctions.getLoginUserName(login.userName);
		updatedLocation = login.location.split("/")[1];
	}

}
