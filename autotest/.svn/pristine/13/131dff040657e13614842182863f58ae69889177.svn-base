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
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: This case was designed to verify edit business rule for rule category: Customer Demographic
 * 				 and it will verify rule info in list page after modification and also rule details page;
 * @Preconditions:
 * @SPEC:
 * @Task#:Auto-874
 * 
 * @author Jane Wang
 * @Date  Mar 28, 2012
 */
public class Edit_2 extends LicenseManagerTestCase {

	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private PrivilegeBusinessRule originalRuleInfo = new PrivilegeBusinessRule();
	private PrivilegeBusinessRule afterEditRule = new PrivilegeBusinessRule();
	private String privilegeCode;
	private LicMgrPrivilegeEditRuleWidget editPg = LicMgrPrivilegeEditRuleWidget.getInstance();
	private LicMgrPrivilegeBusinessRulePage rulePg = LicMgrPrivilegeBusinessRulePage.getInstance();
	private String updatedUser,updatedLocation;
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoProductRulePgFromTopMenu(privilegeCode);
		//add a business rule for test
		ruleInfo.id = lm.safeAddBusinessRuleForProduct(ruleInfo)[0];
		originalRuleInfo.id = ruleInfo.id;
		//change parameters
		ruleInfo.effectiveDate = DateFunctions.getDateAfterToday(-10);
		ruleInfo.parameters[0].effectiveDate = ruleInfo.effectiveDate;
		ruleInfo.parameters[0].residencyProofsParas.add("Homestead Exempt Certification");
		ruleInfo.parameters[0].residencyProofsParas.add("MDWFP Approved");
		ruleInfo.parameters[0].residencyProofsParas.add("Military Orders");
		//update business rule
		lm.gotoBusinessRuleDetailPageFromPrivilegeDetailPage(ruleInfo.id);
		updateRuleAndVerifyResult();
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		privilegeCode = "BR6";

		ruleInfo.ruleCategory = "Customer Demographic";
		ruleInfo.name = "Customer must be a RESIDENT in order to purchase this license";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getDateAfterToday(2);
		ruleInfo.parameters[0].workAction = "Don't Display";
		ruleInfo.parameters[0].residencyProofsParas = new ArrayList<String>();
		ruleInfo.parameters[0].residencyProofsParas.add("Utility Bill/Lease");
		ruleInfo.parameters[0].residencyProofsParas.add("Student ID");
		ruleInfo.isTheOnlyOneRule = true;
		
		originalRuleInfo.ruleCategory = "Customer Demographic";
		originalRuleInfo.name = "Customer must be a RESIDENT in order to purchase this license";
		originalRuleInfo.parameters = new RuleParameters[1];
		originalRuleInfo.parameters[0] = originalRuleInfo.new RuleParameters();
		originalRuleInfo.parameters[0].effectiveDate = DateFunctions.getDateAfterToday(2);
		originalRuleInfo.parameters[0].workAction = "Don't Display";
		originalRuleInfo.parameters[0].residencyProofsParas = new ArrayList<String>();
		originalRuleInfo.parameters[0].residencyProofsParas.add("Utility Bill/Lease");
		originalRuleInfo.parameters[0].residencyProofsParas.add("Student ID");
		originalRuleInfo.isTheOnlyOneRule = true;
		
		updatedUser = DataBaseFunctions.getLoginUserName(login.userName);
		updatedLocation = login.location.split("/")[1];
	}
	
	private void updateRuleAndVerifyResult(){
		logger.info("Update rule with id:"+originalRuleInfo.id);
		
		editPg.enterRuleInfo(ruleInfo);
		editPg.clickOK();
		ajax.waitLoading();
		
		Object page = browser.waitExists(editPg, rulePg);
		if(page == editPg){
			throw new ErrorOnPageException("Failed to update rule id:"+originalRuleInfo.id);
		}else if(page == rulePg){
			//1. Check new added rule info
			checkNewAddedRuleInfo();
			//2. Check the old rule info
			checkOriginalRuleInfo();
		}else{
			//unexpected page
			throw new ErrorOnPageException("Unexcepted page.");
		}		
	}
	
	/**
	 * Verify a new added rule info are correct
	 */
	private void checkNewAddedRuleInfo(){
		afterEditRule = rulePg.getRuleInfoFromBusinessRulePg(ruleInfo, 0);
		if (afterEditRule!=null && ruleInfo.listcompare(afterEditRule, 0)) {
			logger.info("Edit a new business rule for product successful on the privilege detail pages.");
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
		if(!editPg.getBusinessRuleDetailInformation().detailcompare(ruleInfo, 0)){
			 throw new ErrorOnPageException("New Edit Rule Data Not Correct.");
		}
		lm.gotoPrivilegeBusinessRulePageFromEditRuleWidget();
	}
	
	private void checkOriginalRuleInfo(){
		rulePg.showAllBusinessRule();
		lm.gotoBusinessRuleDetailPageFromPrivilegeDetailPage(originalRuleInfo.id);
		originalRuleInfo.status = "Inactive";
		if(!originalRuleInfo.detailcompare(editPg.getBusinessRuleDetailInformation(),0)){
			 throw new ErrorOnPageException("Rule Data Not Correct.");
		}
		if(!editPg.getLastUpdateUser().equals(updatedUser)){
			throw new ErrorOnPageException("Updated User "+editPg.getLastUpdateUser()+" Not Correct.");
		}
		if(!editPg.getLastUpdateLocation().equals(updatedLocation)){
			throw new ErrorOnPageException("Updated location "+editPg.getLastUpdateLocation()+" Not Correct.");
		}
		if(!editPg.getLastUpdateTime().matches(DateFunctions.getToday("EEE MMM dd yyyy",DataBaseFunctions.getContractTimeZone(DataBaseFunctions.getSchemaName("MS", env)))+".*")){
			throw new ErrorOnPageException("Updated Date Not Correct.");
		}
		lm.gotoPrivilegeBusinessRulePageFromEditRuleWidget();
	}
}
