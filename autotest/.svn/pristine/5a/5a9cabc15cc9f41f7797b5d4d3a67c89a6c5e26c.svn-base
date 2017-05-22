package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.businessrule.editbusinessrule;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeBusinessRulePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditRuleWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: This test case used to test edit product business rule
 * 					1.check UI(Rule Id and rule name can not be edited)
 * 					2.SPEC error msg 2,5 and cancel button works
 * @Preconditions: need an exist privilege
 * @SPEC:<Edit Product Business Rule>
 * @Task#:Auto-601
 * 
 * @author ssong
 * @Date  Jun 9, 2011
 */
public class VerifyCancel extends LicenseManagerTestCase{

	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private String privilegeCode;
	private String message1, message2;
	private LicMgrPrivilegeEditRuleWidget editPg = LicMgrPrivilegeEditRuleWidget.getInstance();
	private LicMgrPrivilegeBusinessRulePage rulePg = LicMgrPrivilegeBusinessRulePage.getInstance();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		lm.gotoProductRulePgFromTopMenu(privilegeCode);
		lm.safeAddBusinessRuleForProduct(ruleInfo);
		ruleInfo.id = rulePg.getRuleIdByEffectiveDate(ruleInfo.parameters[0].effectiveDate);
		lm.gotoBusinessRuleDetailPageFromPrivilegeDetailPage(ruleInfo.id);
		//check some business rules about UI display when edit rule
		checkUI();
	
		//verify error message and cancel button
		verifyEditBusinessRule(ruleInfo);
		
		cleanUp();
		
		lm.logOutLicenseManager();
	}
	
	private void checkUI(){
		if(editPg.checkRuleIdEditAble()){
			throw new ErrorOnPageException("Rule Id Should not be editable.");
		}
		if(editPg.checkRuleNameEditAble()){
			throw new ErrorOnPageException("Rule Name Should not be editable.");
		}
	}
	
	private void verifyEditBusinessRule(PrivilegeBusinessRule rule){
		logger.info("Verify Edit Business Rule.");
		
		rule.effectiveDate = "";
		editPg.enterRuleInfo(rule);
		verifyErrorMsg(message1);
		rule.effectiveDate = DateFunctions.getToday();
		ruleInfo.parameters[1].product = "101-RES ALL GAME HUNT/FRESH";
		editPg.enterRuleInfo(rule);
		verifyErrorMsg(message2);
		
		rule.status = "Inactive";
		ruleInfo.parameters[1].product = "100-RES SPORTSMAN";
		editPg.enterRuleInfo(rule);
		verifyCancelBtn();
		
	}

	private void verifyErrorMsg(String msg){
		editPg.clickOK();
		Object page = browser.waitExists(editPg,rulePg);
		if(page == rulePg){
			throw new ErrorOnPageException("Expect Page Is Edit Page.");
		}
		if(!editPg.getErrorMessage().equals(msg)){
			throw new ErrorOnPageException("Error Msg Not Correct, Expect Msg is "+msg);
		}
	}
	
	private void verifyCancelBtn(){
		editPg.clickCancel();
		rulePg.waitLoading();
		lm.gotoBusinessRuleDetailPageFromPrivilegeDetailPage(ruleInfo.id);
		if(editPg.getRuleStatus().equals("Inactive")){
			throw new ErrorOnPageException("Rule Status should be Active, the cancel button not works.");
		}		
	}
	
	private void cleanUp(){
		logger.info("Clean Up!");
		
		lm.deactivateBusinessRule();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		ruleInfo.ruleCategory = "Privilege Cross Reference";
		ruleInfo.name = "Cannot purchase IF ALL parameter privileges on file";
		
		ruleInfo.parameters = new RuleParameters[2];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[1] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].product = "101-RES ALL GAME HUNT/FRESH";
		ruleInfo.parameters[1].product = "100-RES SPORTSMAN";
		ruleInfo.parameters[0].effectiveDate = "08/08/2009";
		ruleInfo.workflowAction = "Don't Display";
		ruleInfo.isProductMuti = true;
		ruleInfo.isTheOnlyOneRule = true;
		
		privilegeCode = "ER4";
		message1 = "The Effective Date is required.";
		message2 = "Duplicate Parameter values exist. Duplicates are not allowed.";
	}
}

