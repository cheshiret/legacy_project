package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.businessrule.viewbusinessrule;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeBusinessRulePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditRuleWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description: check the sorting of the business rule list
 * @Preconditions: It needs a support script to prepare the data
 * Add a business rule record:
 * 1. Rule Category: Customer Demographic
 * Rule Name: Customer must be AT LEAST parameter age on the date of privilege purchase
 * Effective Date: 3 days after
 * Age: 21
 *
 * 2. Rule Category: Customer Demographic
 * Rule Name: Customer must be a NON-RESIDENT in order to purchase this license
 * Effective Date: 3 days after
 * Age: 21
 * 
 * 3. Rule Category: Customer Demographic
 *  Rule Name: Customer must be AT MOST parameter age on the date of privilege purchase
 *  Effective Date: 3 days after
 *  Age: 21
 *  
 * 4. Rule Category: Customer Demographic
 * Rule Name: Customer must be AT LEAST parameter age on the date of privilege purchase
 * Effective Date: 4 days after
 * Age: 21
 * 
 * 5. Rule Category: Customer Demographic
 * Rule Name: Customer must be AT LEAST parameter age on the date of privilege purchase
 * Effective Date: 5 days after
 * Age: 21
 * 
 * 6. Rule Category: Customer Demographic
 * Rule Name: Customer must be AT LEAST parameter age on the date of privilege purchase
 * Effective Date: 6 days after
 * Age: 21
 * 
 * 7. Rule Category: Privilege Cross Reference
 * Rule Name: Cannot purchase IF parameter privilege on file
 * Effective Date: 6 days after
 * 
 * 8. Rule Category: Suspension/Revocation
 * Rule Name: Customer cannot have parameter SUSPENSION type on file (Deny sale)
 * Effective Date: 7 days after
 * @SPEC:View Product Business Rule
 * @Task#: AUTO-603
 * 
 * @author eliang
 * @Date  Jun 8, 2011
 */
public class CheckBusinessRuleListSorting extends LicenseManagerTestCase{
	LicMgrPrivilegeEditRuleWidget ruleEditPg = LicMgrPrivilegeEditRuleWidget.getInstance();
	LicMgrPrivilegeBusinessRulePage rulePage = LicMgrPrivilegeBusinessRulePage.getInstance();
	private PrivilegeBusinessRule[] ruleInfos = new PrivilegeBusinessRule[8];
	List<String> list = new ArrayList<String>();
	List<String> recordlist = new ArrayList<String>();
	private String privilegeCode;
	String tableProperty = ".id";
	String propertyValue = "PrivilegeProductListBar";
	
	public void execute() {
		//login license manager and goto privilege business rule sub-page
		lm.loginLicenseManager(login);
		
		lm.gotoProductRulePgFromTopMenu(privilegeCode);

		//add rule records
		String[] ruleRecords = this.addBusinessRuleRecords(ruleInfos);
		
		//verify different rule category
		if(!this.verifySorting(ruleRecords)){
			throw new ErrorOnPageException("The sorting is incorrect.");
		}
		
		//invalid business records
		lm.deactiveBusinessRuleForProduct(ruleRecords);
		
		//logout License Manager
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		privilegeCode = "A10";
		
		ruleInfos[0] = new PrivilegeBusinessRule();
		ruleInfos[0].parameters = new RuleParameters[1];
		ruleInfos[0].parameters[0] = ruleInfos[0].new RuleParameters();
		ruleInfos[0].ruleCategory = "Customer Demographic";
		ruleInfos[0].name = "Customer must be AT LEAST parameter age on the date of privilege purchase";
		ruleInfos[0].parameters[0].effectiveDate = DateFunctions.getDateAfterToday(3);
		ruleInfos[0].parameters[0].age = "21";
		
		ruleInfos[1] = new PrivilegeBusinessRule();
		ruleInfos[1].parameters = new RuleParameters[1];
		ruleInfos[1].parameters[0] = ruleInfos[1].new RuleParameters();
		ruleInfos[1].ruleCategory = "Customer Demographic";
		ruleInfos[1].name = "Customer must be AT LEAST parameter age on the date of privilege purchase";
		ruleInfos[1].parameters[0].effectiveDate = DateFunctions.getDateAfterToday(4);
		ruleInfos[1].parameters[0].age = "21";
		
		ruleInfos[2] = new PrivilegeBusinessRule();
		ruleInfos[2].parameters = new RuleParameters[1];
		ruleInfos[2].parameters[0] = ruleInfos[2].new RuleParameters();
		ruleInfos[2].ruleCategory = "Customer Demographic";
		ruleInfos[2].name = "Customer must be AT LEAST parameter age on the date of privilege purchase";
		ruleInfos[2].parameters[0].effectiveDate = DateFunctions.getDateAfterToday(5);
		ruleInfos[2].parameters[0].age = "21";
		
		ruleInfos[3] = new PrivilegeBusinessRule();
		ruleInfos[3].parameters = new RuleParameters[1];
		ruleInfos[3].parameters[0] = ruleInfos[3].new RuleParameters();
		ruleInfos[3].ruleCategory = "Customer Demographic";
		ruleInfos[3].name = "Customer must be AT LEAST parameter age on the date of privilege purchase";
		ruleInfos[3].parameters[0].effectiveDate = DateFunctions.getDateAfterToday(6);
		ruleInfos[3].parameters[0].age = "21";
		
		ruleInfos[4] = new PrivilegeBusinessRule();
		ruleInfos[4].parameters = new RuleParameters[1];
		ruleInfos[4].parameters[0] = ruleInfos[4].new RuleParameters();
		ruleInfos[4].ruleCategory = "Customer Demographic";
		ruleInfos[4].name = "Customer must be AT MOST parameter age on the date of privilege purchase";
		ruleInfos[4].parameters[0].effectiveDate = DateFunctions.getDateAfterToday(3);
		ruleInfos[4].parameters[0].age = "21";
		
		ruleInfos[5] = new PrivilegeBusinessRule();
		ruleInfos[5].parameters = new RuleParameters[1];
		ruleInfos[5].parameters[0] = ruleInfos[5].new RuleParameters();
		ruleInfos[5].ruleCategory = "Customer Demographic";
		ruleInfos[5].name = "Customer must be a NON-RESIDENT in order to purchase this license";
		ruleInfos[5].parameters[0].effectiveDate = DateFunctions.getDateAfterToday(3);
//		ruleInfos[5].parameters[0].age = "21";

		ruleInfos[6] = new PrivilegeBusinessRule();
		ruleInfos[6].parameters = new RuleParameters[1];
		ruleInfos[6].parameters[0] = ruleInfos[6].new RuleParameters();
		ruleInfos[6].ruleCategory = "Privilege Cross Reference";
		ruleInfos[6].name = "Cannot purchase IF parameter privilege on file";
		ruleInfos[6].parameters[0].effectiveDate = DateFunctions.getDateAfterToday(6);
		
		ruleInfos[7] = new PrivilegeBusinessRule();
		ruleInfos[7].parameters = new RuleParameters[1];
		ruleInfos[7].parameters[0] = ruleInfos[7].new RuleParameters();
		ruleInfos[7].ruleCategory = "Suspension/Revocation";
		ruleInfos[7].name = "Customer cannot have parameter SUSPENSION type on file (Deny Sale)";
		ruleInfos[7].parameters[0].effectiveDate = DateFunctions.getDateAfterToday(7);
		ruleInfos[7].parameters[0].suspensionType="Bad Check Suspension";
	}
	
	private boolean verifySorting(String[] values){
		List<String> list = new ArrayList<String>();
		for(int i = 0; i<values.length; i++){
			list.add(values[i]);
		}
		return rulePage.verifyBusinessRuleRecordsDisplaySort(list);
	}
	
	private String [] addBusinessRuleRecords(PrivilegeBusinessRule[] ruleInfo){
		List<String> ids = rulePage.getRuleIdsByStatus(true);
		if(ids.size() > 0) {
			lm.deactivateBusinessRules(ids.toArray(new String[0]));
		}
		String [] recordsIds= new String[8];
		for(int i =0; i<ruleInfo.length; i++){
			recordsIds[i] = lm.addBusinessRuleForProduct(ruleInfo[i])[0];
		}
		return recordsIds;
	}
}
