package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.businessrule.custinforule;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: This test case used when purchase privilege if the parameter customer info(email) not exist will prompt the input to file it and will save the email to customer info.
 * @Preconditions: 1. The privilege "VPP" which has business rule "" "IF parameter CUSTOMER PROFILE INFORMATION not on file, Customer must PROVIDE parameter CUSTOMER PROFILE INFORMATION in order to purchase "". 
 *                 2. Customer exist email info exist.
 *                 3.There should have question widget will not prompt to required email. 
 * @SPEC:Edit Product Business Rule
 * @Task#:Auto-1454
 *
 * @author Jasmine
 * @Date  Feb 26, 2012 Block by defect.
 */
public class BuyPrivWIthEmail extends LicenseManagerTestCase{

	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private PrivilegeInfo privilegeInfo = new PrivilegeInfo();
	private String switchLocation;
	
	public void execute() {
		lm.checkPrivilegesExist(schema, privilegeInfo.code);
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeListPageFromTopMenu();
		lm.gotoPrivilegeSubTabPage(privilegeInfo.code, "Business Rule");
		lm.safeAddBusinessRuleForProduct(ruleInfo);

		lm.switchLocationInHomePage(switchLocation);
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilegeInfo);
		lm.cancelCart();
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		switchLocation = "HF HQ Role - Auto-WAL-MART";
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "Feb 08 1985";
		cust.fName = "QA-Jasmine3";
		cust.lName = "TEST-Jasmine3";
		cust.identifier.identifierType = "MDWFP #";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		cust.email = "jasmine@sina.com";
		
		privilegeInfo.code = "VPP";
		privilegeInfo.name = "VerifyParameterCust";
		privilegeInfo.purchasingName = privilegeInfo.code+"-"+privilegeInfo.name;
		privilegeInfo.licenseYear = lm.getFiscalYear(schema);
		privilegeInfo.qty = "1";
		
		ruleInfo.status = OrmsConstants.ACTIVE_STATUS;
		ruleInfo.ruleCategory = "Customer Demographic";
		ruleInfo.name = "IF parameter CUSTOMER PROFILE INFORMATION not on file, Customer must PROVIDE parameter CUSTOMER PROFILE INFORMATION in order to purchase";
		ruleInfo.parameters =new RuleParameters[1];
		ruleInfo.parameters[0] =ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].prompt = "jasmine@sina.com";
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		ruleInfo.parameters[0].workAction = "Prompt";
		
	}

}
