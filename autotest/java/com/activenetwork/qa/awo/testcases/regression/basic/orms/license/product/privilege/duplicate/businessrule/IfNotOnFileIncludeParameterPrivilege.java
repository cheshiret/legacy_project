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
 * @Description: If a Privilege instance of the specified parameter Privilege
 *               Product does not exist, and if the parameter Privilege Product
 *               has a Status of "Active", the Work flow Action shall be executed.
 *               Step:
 *               1. Make a privilege(A) order, and ensure the privilege(B) doesn't in this customer file;
 *               2. Duplicate the A privilege order; at the same time the parameter privilege(B) will be automatically added into cart
 *               3. Verify if the B privilege is in file, repeat the 2 step, B will not be added this time.
 *               
 * @Preconditions: Need 2 existing privilege product.
 * @SPEC: <<Check Business Rules for Privilege Duplicate.doc>> <<Add Product
 *        Business Rule.doc>>
 * @Task#: AUTO-881
 * 
 * @author qchen
 * @Date Mar 2, 2012
 */
public class IfNotOnFileIncludeParameterPrivilege extends
		LicenseManagerTestCase {
	private PrivilegeBusinessRule rule = new PrivilegeBusinessRule();
	private PrivilegeInfo parameterPrivilege = new PrivilegeInfo();
	private String salesAgentLocation;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//1. login LM with Administrator role/location to add a new business rule for privilege
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeSubTabPage(privilege.code, "Business Rule");
		rule.id = lm.safeAddBusinessRuleForProduct(rule)[0];
		lm.logOutLicenseManager();
		
		login.location = salesAgentLocation;
		lm.loginLicenseManager(login);
		//2. invalidate previous privileges by customer
		lm.invalidatePrivilegePerCustomer(cust, parameterPrivilege);
		
		//3. make a host privilege order
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		String orderNum = lm.processOrderCart(pay, false);
		
		//4. duplicate the host privilege order
		lm.replacePrivilegeToCartFromCustomerTopMenu(cust, orderNum);
		
		//5. verify the 2 privileges(selected host privilege and parameter privilege which is ACTIVE but NOT ON FILE are all added in cart)
		lm.verifyAddedCorrectPrivilegeConsumableAndQty(privilege.purchasingName, privilege.licenseYear, "1", true, 1);
		lm.verifyAddedCorrectPrivilegeAndQty(parameterPrivilege.purchasingName, parameterPrivilege.licenseYear, "1", 2);
		lm.cancelCart();
		
		/**
		 * Negative scenario: if the parameter privilege has been ON FILE, verify the parameter privilege WON'T be automatically added into cart
		 */
		//6. make a parameter privilege order
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, parameterPrivilege);
		lm.processOrderCart(pay, false);
		
		//7. duplicate the host privilege order and verify the parameter is NOT added into cart this time
		lm.replacePrivilegeToCartFromCustomerTopMenu(cust, orderNum);
		lm.verifyAddedCorrectPrivilegeConsumableAndQty(privilege.purchasingName, privilege.licenseYear, "1", true, 1);
		lm.verifyPrivilegeExistsInCart(parameterPrivilege.purchasingName, parameterPrivilege.licenseYear, false);
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
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-TransferRule2";
		cust.lName = "TEST-TransferRule2";
		cust.dateOfBirth = "Aug 12 1986";
		cust.identifier.identifierType = "MDWFP #";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);

		privilege.code = "CB2";
		privilege.name = "CheckBusinessRules2";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = fiscalYear;
		privilege.qty = "1";
		privilege.operateReason = "25 - Document did not print";
		privilege.operateNote = "Automation Test";

		parameterPrivilege.code = "NFP";
		parameterPrivilege.name = "IfNotOnFileIncludeParaPri";
		parameterPrivilege.purchasingName = parameterPrivilege.code + "-" + parameterPrivilege.name;
		parameterPrivilege.licenseYear = fiscalYear;
		parameterPrivilege.qty = "1";

		// business rule info
		rule.status = OrmsConstants.ACTIVE_STATUS;
		rule.ruleCategory = "Privilege Cross Reference";
		rule.name = "IF NOT ON FILE include parameter privilege when selected privilege purchased";
		rule.parameters = new RuleParameters[1];
		rule.parameters[0] = rule.new RuleParameters();
		rule.parameters[0].product = parameterPrivilege.purchasingName;
		rule.parameters[0].effectiveDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		rule.parameters[0].purchaseType = "Duplicate";
		rule.parameters[0].workAction = "Add at Front End";
	}
}
