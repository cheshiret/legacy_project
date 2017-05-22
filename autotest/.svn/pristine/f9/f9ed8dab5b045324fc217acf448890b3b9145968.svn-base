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
 * @Description: If parameter privilege is on file, when replacing host privilege order and print it,
 * 						  Host Action to be executed, where the System shall return the existing Privilege
 * 						  instance found to be included in the Document(s) to be printed as part of the
 * 					      purchase and indicating that the Privilege has been paid
 * @Preconditions: Need 2 existing privilege.
 * @SPEC: <<Check Business Rules for Privilege Duplicate.doc>> <<Add Product Business Rule.doc>>
 * @Task#: AUTO-881
 *
 * @author qchen
 * @Date  Mar 6, 2012
 */
public class AutoIncludeInResponseForPrinting extends LicenseManagerTestCase {
	private PrivilegeBusinessRule rule = new PrivilegeBusinessRule();
	private PrivilegeInfo parameterPrivilege = new PrivilegeInfo();
	private String salesAgentLocation, adminLocation;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//1. login LM with Administrator role/location to add a new business rule for privilege
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeSubTabPage(privilege.code, "Business Rule");
		rule.id = lm.safeAddBusinessRuleForProduct(rule)[0];
		lm.logOutLicenseManager();

		//2. make a privilege order as precondition
		login.location = salesAgentLocation;
		lm.loginLicenseManager(login);
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay);

		//4. make a parameter privilege order to achieve "IF parameter privilege is on file" purpose
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, parameterPrivilege);
		lm.processOrderCart(pay);

		//3. duplicate the host privilege order and print document
		lm.replacePrivilegeToCartFromCustomerTopMenu(cust, orderNum);
		lm.processOrderCart(pay, true);

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		salesAgentLocation = "HF HQ Role/WAL-MART";
		adminLocation = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		login.location = adminLocation;
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		String fiscalYear = lm.getFiscalYear(schema);
		
		cust.customerClass = "Individual";
		cust.fName = "QA-Dont";
		cust.lName = "TEST-Touchme";
		cust.dateOfBirth = "Feb 22 2012";
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "63667792";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";

		privilege.code = "CB3";
		privilege.name = "CheckBusinessRules3";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = fiscalYear;
		privilege.qty = "1";

		parameterPrivilege.code = "ARP";
		parameterPrivilege.name = "IfOnFileAutoRespPrinting";
		parameterPrivilege.purchasingName = parameterPrivilege.code + "-" + parameterPrivilege.name;
		parameterPrivilege.licenseYear = fiscalYear;
		parameterPrivilege.qty = "1";

		//business rule info
		rule.status = OrmsConstants.ACTIVE_STATUS;
		rule.ruleCategory = "Privilege Cross Reference";
		rule.name = "IF parameter privilege is on file, automatically include in response for printing";
		rule.parameters = new RuleParameters[1];
		rule.parameters[0] = rule.new RuleParameters();
		rule.parameters[0].product = parameterPrivilege.purchasingName;
		rule.parameters[0].effectiveDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		rule.parameters[0].purchaseType = "Duplicate";
		rule.parameters[0].matchLicYear = true;
	}
}
