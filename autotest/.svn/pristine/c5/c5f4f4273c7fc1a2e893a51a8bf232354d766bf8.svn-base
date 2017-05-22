package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.businessrule.trigger.buyxgetyfree;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: This test case used to buy required qty x privilege product will be add another free privilege in to cart
 * @Preconditions: 1. The privilege "IXB" which has business rule ""Include parameter privilege free of charge when selected quantity privilege purchased". 
 *  and required quantity 2 and free quantity is 
 *                 2. The paraments privilege product is VPH-HisPrivilegeOrder.
 * @SPEC:Edit Product Business Rule
 * @Task#:Auto-1454
 *
 * @author Jasmine
 * @Date  Feb 26, 2012
 */
public class BuyXGetOneYFree extends LicenseManagerTestCase{
    private String switchLocation,paraterPriv,paraterPrivCount;
    private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
    private PrivilegeInfo privilegeInfo = new PrivilegeInfo();
    private OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
    
	public void execute() {
		lm.checkPrivilegesExist(schema, privilegeInfo.code);
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeListPageFromTopMenu();
		lm.gotoPrivilegeSubTabPage(privilegeInfo.code, "Business Rule");
		lm.safeAddBusinessRuleForProduct(ruleInfo);

		lm.switchLocationInHomePage(switchLocation);
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilegeInfo);
		this.verifyPrivilegeAndCount(privilegeInfo.purchasingName, Integer.valueOf(privilegeInfo.qty));
		this.verifyPrivilegeAndCount(paraterPriv, Integer.valueOf(paraterPrivCount));
		this.verifyParaterPrivFree();
		String orderNum = lm.processOrderCart(pay);//"8-519936"
		lm.replacePrivilegeToCartFromCustQuickSearch(cust, orderNum, privilege);
		paraterPrivCount= "0";
		this.verifyPrivilegeAndCount(paraterPriv, Integer.valueOf(paraterPrivCount));
		lm.cancelCart();
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
//		login.location = "HF HQ Role/WAL-MART"/*"HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks"*/;
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		switchLocation="HF HQ Role - Auto-WAL-MART";
		paraterPriv = "VPH-HisPrivilegeOrder";
		paraterPrivCount = "1";
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "Jan 01 1981";
		cust.fName = "QA-Jasmine1";
		cust.lName = "TEST-Jasmine1";
		cust.identifier.identifierType = "MDWFP #";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		
		privilegeInfo.code = "IXB";
		privilegeInfo.name = "IncludeXBusniess";
		privilegeInfo.purchasingName = privilegeInfo.code+"-"+privilegeInfo.name;
		privilegeInfo.licenseYear = lm.getFiscalYear(schema);
		privilegeInfo.qty = "2";
		
		ruleInfo.status = OrmsConstants.ACTIVE_STATUS;
		ruleInfo.ruleCategory = "Privilege Cross Reference";
		ruleInfo.name = "INCLUDE parameter privilege free of charge when selected quantity of privilege purchased";
		ruleInfo.parameters =new RuleParameters[1];
		ruleInfo.parameters[0] =ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].requiredQuantity = "2";
		ruleInfo.parameters[0].freeQuantity = "1";
		ruleInfo.parameters[0].product = paraterPriv;
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		ruleInfo.parameters[0].workAction = "Add at Front End";
	}
	
	public void verifyPrivilegeAndCount(String expectedPriName, int count){
		int priCount = cartPg.getPriviQtyInCartPg(expectedPriName,privilegeInfo.licenseYear);
		if(priCount !=count){
			throw new ErrorOnPageException(expectedPriName + "ccount",count,priCount);
		}else{
			logger.info(expectedPriName +" number is correct");
		}
	}
	
	private void verifyParaterPrivFree(){
		double x = cartPg.getItemTotalAmount(paraterPriv);
		if(x!=0.0){
			throw new ErrorOnPageException(paraterPriv+" price should free");
		}else{
			logger.info(paraterPriv + " price free is correct");
		}
	}

}
