/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.reactivate;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule.RuleParameters;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
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
 *              3. add business rule "Customer MUST have specified attribute on file in order to purchase selected product"
 *              4. reactivate privilege order
 *              5. verify message
 *              make sure: 1. customer solicitationIndcator is 'Yes'
 *                         2. business rule attribute of solicitationIndcator, the value is 'No'
 *              
 * @LinkSetUp:d_hf_add_privilege_prd:id=2860
 *            d_hf_add_cust_profile:id=3100
 * @SPEC:Reactivate Privilege - 'Customer MUST have specified attribute on file in order to purchase selected product' rule [TC:067785]
 * @Task#:Auto-2134
 * 
 * @author Vivian
 * @Date  Apr 9, 2014
 */
public class Reactivate_CustDemoHasAttributeOnFile extends LicenseManagerTestCase{
	private PrivilegeBusinessRule ruleInfo = new PrivilegeBusinessRule();
	private LicMgrPrivilegeBusinessRulePage businessRulePage = LicMgrPrivilegeBusinessRulePage.getInstance();
	private LicMgrCustomerDetailsPage custDetailsPg = LicMgrCustomerDetailsPage.getInstance();
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private String message;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		this.updateCustomerSolicitationIndicator();
		
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
		
		privilege.code = "RP9";
		privilege.name = "ReactivatePriOrd09";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = fiscalYear;
		privilege.qty = "1";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};
		
		cust.residencyStatus="Non Resident";
		cust.customerClass= OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.identifier.identifierType="US Drivers License";
		cust.identifier.identifierNum="ReactivePri07";
		cust.identifier.state = "Maine";
		cust.dateOfBirth = "1985-8-23";
		cust.solicitationIndcator = "Yes";//don't change
		
		ruleInfo.status = OrmsConstants.ACTIVE_STATUS;
		ruleInfo.ruleCategory = "Customer Demographic";
		ruleInfo.name = "Customer MUST have specified attribute on file in order to purchase selected product";
		ruleInfo.parameters = new RuleParameters[1];
		ruleInfo.parameters[0] = ruleInfo.new RuleParameters();
		ruleInfo.parameters[0].workAction = "Don't Display";
		String[] attribute_1 = {"SolicitationInd","No"};//don't change, It is must be No, because cust.solicitationIndcator = "Yes"
		ruleInfo.parameters[0].attributeInfos = new ArrayList<String[]>();
		ruleInfo.parameters[0].attributeInfos.add(attribute_1);
		ruleInfo.parameters[0].effectiveDate = DateFunctions.getDateAfterToday(-5);
		
		message = "The reactivation of the Privilege is in violation of the Business Rules. The Privilege cannot be reactivated.";
	}
	
	private void updateCustomerSolicitationIndicator(){
		logger.info("Update Customer Solicitation Indicator.");
		
		lm.gotoCustomerDetailFromTopMenu(cust);
		custDetailsPg.selectSolicitationIndicator(cust.solicitationIndcator);
		custDetailsPg.clickApply();
		ajax.waitLoading();
		custDetailsPg.waitLoading();
		lm.gotoHomePage();
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
