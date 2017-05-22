package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.businessrule.editbusinessrule;

import java.util.TimeZone;

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
 * @Description: This test case used to verify change an inactive rule to active rule,only update status and verify a old rule keep no change
 * a new rule was added.
 * @Preconditions:
 * @SPEC:Edit Product Business Rule
 * @Task#:Auto-601
 *
 * @author ssong
 * @Date  Jun 15, 2011
 */
public class CheckInactiveToActiveAndNoOtherChanged extends LicenseManagerTestCase{

	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private PrivilegeBusinessRule beforeEditRule = new PrivilegeBusinessRule();
	private PrivilegeBusinessRule afterEditRule = new PrivilegeBusinessRule();
	private String privilegeCode;
	private LicMgrPrivilegeEditRuleWidget editPg = LicMgrPrivilegeEditRuleWidget.getInstance();
	private LicMgrPrivilegeBusinessRulePage rulePg = LicMgrPrivilegeBusinessRulePage.getInstance();
	private String updatedUser,updatedLocation;
	private TimeZone tz = DataBaseFunctions.getContractTimeZone(DataBaseFunctions.getSchemaName("MS", env));

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		lm.gotoProductRulePgFromTopMenu(privilegeCode);
		//prepare data to get an exist business rule R1
		String id = lm.safeAddBusinessRuleForProduct(ruleInfo)[0];

		//add another rule with the different suspension type and deactivate it
		ruleInfo.parameters[0].suspensionType = "Failure to Submit Harvest Report";
		ruleInfo.id = lm.addBusinessRuleForProduct(ruleInfo)[0];
		lm.deactiveBusinessRuleForProduct(ruleInfo.id);
		rulePg.showAllBusinessRule();
		lm.gotoBusinessRuleDetailPageFromPrivilegeDetailPage(ruleInfo.id);

		//update new added inactive rule to active with the same product as R1
		ruleInfo.parameters[0].suspensionType = "Bad Check Suspension";
		ruleInfo.effectiveDate = DateFunctions.getDateAfterToday(-10);
		ruleInfo.status = "Active";
		editPg.enterRuleInfo(ruleInfo);
		verifyErrorMsg("Another active Product Business Rule record "+id+" already exists for the same Product with the same Business Rule Name, and potentially the same Parameter values. Duplicate active records are not allowed.");

		//switch page and prepare another rule R2
		switchPage();
		rulePg.showActiveBusinessRule();
		ruleInfo.ruleCategory = "Education/Certification Enforcement";
		ruleInfo.name = "Customer must have VERIFIED parameter EDUCATION type on file in order to purchase";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].educationType = "HuntEducation";
		ruleInfo.parameters[0].locationClass = "01 - MDWFP Headquarters";
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getToday();
		ruleInfo.parameters[0].workAction = "Don't Display";
		String id2 = lm.addBusinessRuleForProduct(ruleInfo)[0];

		//add another rule with same product and different purchase type
		ruleInfo.parameters[0].locationClass = "12 - No Commission Kiosk";
		ruleInfo.effectiveDate = DateFunctions.getDateAfterToday(-10,tz);
		ruleInfo.parameters[0].effectiveDate = ruleInfo.effectiveDate;

		ruleInfo.id = lm.addBusinessRuleForProduct(ruleInfo)[0];
		lm.deactiveBusinessRuleForProduct(ruleInfo.id);
		rulePg.showAllBusinessRule();
		lm.gotoBusinessRuleDetailPageFromPrivilegeDetailPage(ruleInfo.id);
		ruleInfo.parameters[0].locationClass = "01 - MDWFP Headquarters";
		editPg.enterRuleInfo(ruleInfo);
		verifyErrorMsg("Another active Product Business Rule record "+id2+" already exists for the same Product with the same Business Rule Name, and potentially the same Parameter values. Duplicate active records are not allowed.");

		ruleInfo.parameters[0].locationClass = "12 - No Commission Kiosk";
		editPg.enterRuleInfo(ruleInfo);
		beforeEditRule = ruleInfo;
		verifyEditSucess();

		//clean up
		lm.deactiveBusinessRuleForProduct(id,id2);

		lm.logOutLicenseManager();
	}

	private void verifyErrorMsg(String msg){
		editPg.clickOK();
		editPg.waitErrorMsgExist();

		if(!editPg.getErrorMessage().equals(msg)){
			throw new ErrorOnPageException("Error Msg Not Correct, Expect Msg is "+msg);
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
		afterEditRule = rulePg.getRuleInfoFromBusinessRulePg(ruleInfo,0);
		if (afterEditRule!=null&&ruleInfo.listcompare(afterEditRule, 0)) {
			logger
					.info("Edit a new business rule for product successful on the privilege detail pages.");
		} else {
			throw new ErrorOnPageException("Failed to Edit '"
					+ ruleInfo.name
					+ " ' business rule for product on the privilege detail page.");
		}
		if(ruleInfo.id.equals(afterEditRule.id)){
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

		ruleInfo.ruleCategory = "Suspension/Revocation";
		ruleInfo.name = "Customer cannot have parameter SUSPENSION type on file (Deny Sale)";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].suspensionType = "Bad Check Suspension";
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getToday();
		ruleInfo.parameters[0].workAction = "Don't Display";

		privilegeCode = "ER3";

		updatedUser = DataBaseFunctions.getLoginUserName(login.userName);
		updatedLocation = login.location.split("/")[1];
	}

}
