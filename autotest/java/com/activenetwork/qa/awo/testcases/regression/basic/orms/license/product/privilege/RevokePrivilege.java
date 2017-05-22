/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Suspension;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**  
 * @Description:  
 * @Preconditions:  The product is existing in our DB and has price,license year,agent assignment, quanity control.
 * @SPEC: Revoke privilege
 * @Task#: Auto-869
 * @author jwang8  
 * @Date  Feb 16, 2012    
 */
public class RevokePrivilege extends LicenseManagerTestCase{

	private Suspension suspension = new Suspension();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//make a new privilege order.
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		String ordNum = lm.processOrderCart(pay,false);
		String priNum = lm.getPrivilegeNumByOrdNum(schema, ordNum);
		
		//go to customer detail from customer quick search.
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		//go to customer suspension tab detail page.
		lm.gotoCustomerSubTabPage("Suspensions");
		//revoke the privilege.
		lm.addCustomerSuspension(suspension);
		//verify revoke status from UI
		lm.gotoHomePage();
		lm.gotoPrivilegeOrderDetailPage(ordNum);
		lm.gotoPrivilegeDetailFromOrderPg(priNum);
		lm.verifyAllPrivilegesStatus(ordNum, PRIV_STATUS_REVOKED, schema);
		lm.verifyPrivilegeStatusFromUI(OrmsConstants.REVOKED_STATUS);
		lm.logOutLicenseManager();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS",env);
		
		privilege.purchasingName = "adv-LocationDailySales";
		privilege.licenseYear = lm.getFiscalYear(schema);		
		privilege.qty = "1";
		privilege.operateReason = "01 - Operator Error";
		privilege.operateNote = "Automation-test";
		
		cust.identifier.identifierType = "Green Card";//"Green Card";
		cust.identifier.identifierNum = "123456780";
		cust.identifier.country = "Canada";
		cust.customerClass = "Individual";
		cust.lName = "TEST-InvalidOrder";
		cust.fName ="QA-InvalidOrder";
		cust.residencyStatus="Non Resident";
		
		suspension.beginDate = DateFunctions.getDateAfterToday(-1);
		suspension.datePosted = DateFunctions.getDateAfterToday(-1);
		suspension.comment = "Auto-test" + DateFunctions.getCurrentTime();
	}
}
