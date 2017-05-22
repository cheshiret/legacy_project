/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;

/**  
 * @Description:  The privilege exist in DB and have price ,license year,quality control,agent assignment.
 * @Preconditions:  
 * @SPEC:Reactivate privilege  
 * @Task#: Auto-869
 * @author jwang8  
 * @Date  Feb 15, 2012    
 */
public class ReactivatePrivilege extends LicenseManagerTestCase{
	private LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//make a new privilege order.
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		String ordNum = lm.processOrderCart(pay);
		
		//goto privilege order detail page
		lm.gotoOrderPageFromOrdersTopMenu(ordNum);
		String privNum = privOrderDetailsPage.getAllPrivilegesNum();
		
		//goto privilege detail page.
		lm.gotoPrivilegeDetailFromOrderPg(privNum);
		
		//invalidate privilege 
		lm.invalidatePrivilegeItem(privilege.operateReason, privilege.operateReason);
		
		//reactive privilege.
		lm.reactivatePrivilegeItem(privilege.operateReason, privilege.operateNote);
		
		//verify the privilege reactivated correct from UI and DB
		lm.verifyPrivilegeStatusFromUI(ACTIVE_STATUS);
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
	}
}
