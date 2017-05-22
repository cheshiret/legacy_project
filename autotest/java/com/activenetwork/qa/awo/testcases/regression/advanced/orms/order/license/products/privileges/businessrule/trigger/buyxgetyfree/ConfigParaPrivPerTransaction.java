package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.businessrule.trigger.buyxgetyfree;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrValidFromDateTime;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:  This test case used to buy required qty x privilege product will be add another free privilege in to cart
 * @Preconditions: 1. The privilege "IXI" which has business rule ""Include parameter privilege free of charge when selected quantity privilege purchased". 
 *  and required quantity 2 and free quantity is 
 *                 2. The Parameters privilege product is 'IXT' was config to 'prompt valid from date and time' with 'per privilege' and 'per transaction'.
 * @SPEC:Edit Product Business Rule
 * @Task#:Auto-1454
 *
 * @author Jasmine
 * @Date  Feb 26, 2012
 */
public class ConfigParaPrivPerTransaction extends LicenseManagerTestCase{
	private String switchLocation, paraPriCode,paraterPriv, paraterPrivCount, validDateCount;
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private PrivilegeInfo privilegeInfo = new PrivilegeInfo();
	private OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
	private LicMgrValidFromDateTime validFromDateTime = LicMgrValidFromDateTime
			.getInstance();
	
	public void execute() {
		lm.checkPrivilegesExist(schema, paraPriCode);
		lm.checkPrivilegesExist(schema, privilegeInfo.code);
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeListPageFromTopMenu();
		lm.gotoPrivilegeSubTabPage(privilegeInfo.code, "Business Rule");
		lm.safeAddBusinessRuleForProduct(ruleInfo);
		
		lm.switchLocationInHomePage(switchLocation);
		lm.gotoAddItemPgFromCustomerTopMenu(cust);
		this.addPrivilegeItem(privilegeInfo);
		lm.goToCart();
		
		this.verifyPrivilegeAndCount(privilegeInfo.purchasingName, Integer.valueOf(privilegeInfo.qty));
		this.verifyPrivilegeAndCount(paraterPriv, Integer.valueOf(paraterPrivCount));
		this.verifyParaterPrivFree(paraterPriv);
		
		lm.cancelCart();
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		switchLocation="HF HQ Role - Auto-WAL-MART";
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "Jan 01 1981";
		cust.fName = "QA-Jasmine1";
		cust.lName = "TEST-Jasmine1";
		cust.identifier.identifierType = "MDWFP #";
		cust.residencyStatus = "Non Resident";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		
		privilegeInfo.code = "IXI";
		privilegeInfo.name = "InacParaPriv";
		privilegeInfo.purchasingName = privilegeInfo.code+"-"+privilegeInfo.name;
		privilegeInfo.licenseYear = lm.getFiscalYear(schema);
		privilegeInfo.qty = "4";
		privilegeInfo.validFromDate = String.valueOf(DateFunctions.getDateAfterToday(1));
		privilegeInfo.validFromTime = "2:00";
		
		paraPriCode = "IXT";
		paraterPriv ="IXT-ParaterPerTransaction";
		paraterPrivCount = "2";
		validDateCount = "1";
		
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
	
	private void addPrivilegeItem(PrivilegeInfo privileges){
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage
				.getInstance();
	
		LicMgrConfirmDialogWidget confirmDialogWidget = LicMgrConfirmDialogWidget.getInstance(true);
		logger.info("Add privilege item - " + privilege.purchasingName + ".");

		addItemPg.addProductToCart(privileges.purchasingName,
				privileges.licenseYear, privileges.qty);
		Object page = browser.waitExists(confirmDialogWidget,validFromDateTime,addItemPg);
		
		if(page == confirmDialogWidget){
			confirmDialogWidget.clickOK();
			ajax.waitLoading();
			page = browser.waitExists(validFromDateTime,addItemPg);
			if(page == validFromDateTime){
				this.verifyDateTimeObjectCount(Integer.valueOf(validDateCount));
				if (privileges.validFromTime != null
						&& privileges.validFromTime.length() > 0) {
					validFromDateTime.setValidFromDateTime(privileges.validFromDate);
					validFromDateTime.setValidFromTime(privileges.validFromTime);
					validFromDateTime.selectAmPm(privileges.validFromAmPm);
				}
					validFromDateTime.clickOK();
					ajax.waitLoading();
					addItemPg.waitLoading();
				}
			}
		}
	
	public void verifyPrivilegeAndCount(String expectedPriName, int count){
		int priCount = cartPg.getPriviQtyInCartPg(expectedPriName,privilegeInfo.licenseYear);
		if(priCount !=count){
			throw new ErrorOnPageException(expectedPriName + "ccount",count,priCount);
		}else{
			logger.info(expectedPriName +" number is correct");
		}
	}
	
	private void verifyDateTimeObjectCount(int expectedCount){
		if(validFromDateTime.getVaildDateTimeObject()!=expectedCount){
			throw new ErrorOnPageException("vaild date time object",expectedCount,validFromDateTime.getVaildDateTimeObject());
		}else{
			logger.info("date time object count correct");
		}
	}
	
	private void verifyParaterPrivFree(String privilegeName){
		double x = cartPg.getItemTotalAmount(privilegeName);
		if(x!=0.0){
			throw new ErrorOnPageException(privilegeName+" price should free");
		}else{
			logger.info(privilegeName + " price free is correct");
		}
	}

}
