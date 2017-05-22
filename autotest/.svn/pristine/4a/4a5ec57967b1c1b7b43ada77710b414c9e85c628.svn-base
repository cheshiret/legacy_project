/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.reactivate;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeBusinessRulePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This case is used to verify rule when reactive privilege order
 *              steps:
 *              1. purchase privilege 
 *              2. inactive privilege order
 *              3. add business rule "Customer must be AT LEAST parameter age on the valid date of the Privilege purchased"
 *              4. reactivate privilege order
 *              5. verify message
 * @LinkSetUp:d_hf_add_privilege_prd:id=2800
 *            d_hf_add_cust_profile:id=3090
 * @SPEC:Reactivate Privilege - "Customer Demographic" rule violation [TC:004619] step5
 * @Task#:Auto-2134
 * 
 * @author Vivian
 * @Date  Apr 9, 2014
 */
public class Reactivate_CustDemoAgeOnValidDate_Most extends LicenseManagerTestCase{
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private LicMgrPrivilegeBusinessRulePage businessRulePage = LicMgrPrivilegeBusinessRulePage.getInstance();
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage.getInstance();
	private String message;
	private int paramAge;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		this.updateCustomerDOB();

		//deactivate all business rule
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeSubTabPage(privilege.code, "Business Rule");
		List<String> ids =businessRulePage.getRuleIdsByStatus(true);
		if (ids.size() > 0) {
			lm.deactivateBusinessRules(ids.toArray(new String[0]));
		}
		
		//make a privilege order
		login.location = "HF HQ Role - Auto-WAL-MART";
		lm.switchLocationInHomePage(login.location);
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
	    String ordNum = lm.processOrderCart(pay).split(" ")[0];
	    //invalidate privilege order
		lm.gotoPrivilegeOrderDetailPage(ordNum);
		lm.invalidatePrivilegeOrder(privilege.operateReason, privilege.operateNote);
	    
	    //add business rule
		login.location = "HF Administrator - Auto-Mississippi Department of Wildlife, Fisheries, and Parks";
		lm.switchLocationInHomePage(login.location);
		lm.gotoProductSearchListPageFromTopMenu("Privilege");
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		lm.gotoPrivilegeSubTabPage(privilege.code, "Business Rule");
		lm.addBusinessRuleForProduct(ruleInfo);
		lm.gotoHomePage();
		
		//reactivate privilege order
		login.location = "HF HQ Role - Auto-WAL-MART";
		lm.switchLocationInHomePage(login.location);
		lm.gotoPrivilegeOrderDetailPage(ordNum);
		lm.reactivatePrivilegeOrder(privilege.operateReason, privilege.operateNote);
		//verify error message
		this.verifyErrorMessage(message);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		String fiscalYear = lm.getFiscalYear(schema);
		
		privilege.code = "RP8";
		privilege.name = "ReactivatePriOrd08";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = fiscalYear;
		privilege.qty = "1";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};
		
		paramAge = 20;
		cust.residencyStatus="Non Resident";
		cust.customerClass= OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.identifier.identifierType="US Drivers License";
		cust.identifier.identifierNum="ReactivePri06";
		cust.identifier.state = "Maine";
		
		ruleInfo.status = OrmsConstants.ACTIVE_STATUS;
		ruleInfo.ruleCategory = "Customer Demographic";
		ruleInfo.name = "Customer must be AT LEAST parameter age on the valid date of the Privilege purchased";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].workAction = "Don't Display";
		ruleInfo.parameters[0].age = String.valueOf(paramAge);
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getDateAfterToday(-5);
		
		message = "The reactivation of the Privilege is in violation of the Business Rules. The Privilege cannot be reactivated.";
	}
	
	private void verifyErrorMessage(String expMsg){
		logger.info("Verify Error Message.");
		String actMsg = privOrderDetailPage.getAlertMsg();
		boolean result = MiscFunctions.compareResult("Error Message", expMsg, actMsg);
		if(!result){
			throw new ErrorOnDataException("Error Message not correct, please check.");
		}logger.info("Error Message is correct.");
	}
	
	private void updateCustomerDOB(){
		logger.info("Update DOB for customer.");
		
		String dob = DateFunctions.calculateDate(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)), paramAge*(-1), 3); 
		
		lm.gotoCustomerDetailFromTopMenu(cust);
		cust.dateOfBirth = dob;
		custDetailsPg.changeDateOfBirth(cust.dateOfBirth);
		lm.gotoHomePage();
	}

}
