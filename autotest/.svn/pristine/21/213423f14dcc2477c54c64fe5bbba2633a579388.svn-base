package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.duplicate.businessrule;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Add a privilege into cart, the parameter privilege will be automatically added into cart.
 * 
 * @Preconditions: Need 2 existing privileges. the privilege should have a business rule record which the parameter product is: parameter
 * 							Business rule name: INCLUDE parameter privilege when selected privilege purchased
 * 
 * @SPEC: <<Check Business Rules for Privilege Duplicate.doc>>
 * @Task#: AUTO-881
 * 
 * @author qchen
 * @Date  Mar 2, 2012
 */
public class IncludeParameterPrivilege extends LicenseManagerTestCase {
	private PrivilegeBusinessRule rule = new PrivilegeBusinessRule();
	private PrivilegeInfo parameterPrivilege = new PrivilegeInfo();
	private String salesAgentLocation;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//1. login in with Administrator role/location to add a new business rule for privilege
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeSubTabPage(privilege.code, "Business Rule");
		rule.id = lm.safeAddBusinessRuleForProduct(rule)[0];
		lm.logOutLicenseManager();
		
		//2. make a privilege order
		login.location = salesAgentLocation;
		lm.loginLicenseManager(login);
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay);
		
		//3. duplicate this privilege order
		lm.replacePrivilegeToCartFromCustomerTopMenu(cust, orderNum);
		
		//4. verify the 2 privileges(selected privilege and parameter privilege are all added in cart)
		lm.verifyAddedCorrectPrivilegeConsumableAndQty(privilege.purchasingName, privilege.licenseYear, "1", true, 1);
		lm.verifyAddedCorrectPrivilegeAndQty(parameterPrivilege.purchasingName, parameterPrivilege.licenseYear, "1", 2);
		lm.cancelCart();
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		salesAgentLocation = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		String fiscalYear = lm.getFiscalYear(schema);
		
		cust.customerClass = "Individual";
		cust.fName = "QA-Basic94";
		cust.lName = "Test-Basic94";
		cust.dateOfBirth = "19880314";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "111194";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		privilege.code = "CBR";
		privilege.name = "CheckBusinessRules";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = fiscalYear;
		privilege.qty = "1";
		
		parameterPrivilege.code = "PPR";
		parameterPrivilege.name = "ParameterPrivilegeForRule";
		parameterPrivilege.purchasingName = parameterPrivilege.code + "-" + parameterPrivilege.name;
		parameterPrivilege.licenseYear = fiscalYear;
		parameterPrivilege.qty = "1";
		
		//business rule info
		rule.status = OrmsConstants.ACTIVE_STATUS;
		rule.ruleCategory = "Privilege Cross Reference";
		rule.name = "INCLUDE parameter privilege when selected privilege purchased";
		rule.parameters = new RuleParameters[1];
		rule.parameters[0] = rule.new RuleParameters();
		rule.parameters[0].product = parameterPrivilege.purchasingName;
		rule.parameters[0].effectiveDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		rule.parameters[0].purchaseType = "Duplicate";
		rule.parameters[0].workAction = "Add at Front End";
	}
}
