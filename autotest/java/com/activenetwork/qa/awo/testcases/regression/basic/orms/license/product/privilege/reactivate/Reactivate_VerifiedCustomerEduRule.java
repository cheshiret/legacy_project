/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.reactivate;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeBusinessRulePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This case is used to verify rule when reactive privilege order
 *              steps:
 *              1. purchase privilege 
 *              2. inactive privilege order
 *              3. add business rule "Customer must have VERIFIED parameter EDUCATION type on file in order to purchase"
 *              4. reactivate privilege order
 *              5. verify message
 * @LinkSetUp:d_hf_add_privilege_prd:id=2730
 *            d_hf_add_cust_profile:id=3030
 * @SPEC:Reactivate Privilege - "Education/Certification Enforcement" rule violation [TC:004621] step1
 * @Task#:Auto-2134
 * 
 * @author Vivian
 * @Date  Apr 9, 2014
 */
public class Reactivate_VerifiedCustomerEduRule extends LicenseManagerTestCase{
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private LicMgrPrivilegeBusinessRulePage businessRulePage = LicMgrPrivilegeBusinessRulePage.getInstance();
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private String message;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

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
		
		privilege.code = "RP1";
		privilege.name = "ReactivatePriOrd01";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = fiscalYear;
		privilege.qty = "1";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};
		
		ruleInfo.status = OrmsConstants.ACTIVE_STATUS;
		ruleInfo.ruleCategory = "Education/Certification Enforcement";
		ruleInfo.name = "Customer must have VERIFIED parameter EDUCATION type on file in order to purchase";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].educationType = "HuntEducation";
		ruleInfo.parameters[0].locationClass = "06 - State Parks Agent";
		ruleInfo.parameters[0].workAction = "Don't Display";
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getDateAfterToday(-5);
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "1985/08/16";
		cust.fName = "QA-ReactivatePriOrd02";
		cust.lName = "Test-ReactivatePriOrd02";
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "19850816";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";// Don't change this data!
		
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

}
