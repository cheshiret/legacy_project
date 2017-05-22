/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;

/**  
 * @Description:  The privilege is exist in our DB and have price,license year,quality control,agent assignment.
 * @Preconditions:
 * @SPEC:  Invalidate Privilege
 * @Task#: Auto-869
 * @author jwang8  
 * @Date  Feb 15, 2012    
 */
public class InvalidateOrder extends LicenseManagerTestCase{

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//make a new privilege order.
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		String ordNum = lm.processOrderCart(pay);
		if(ordNum.contains(" ")){
			ordNum = ordNum.split(" ")[0];
		}
		//verify new generated privilege order status correct
		lm.verifyOrderStatus(ordNum, ORD_STATUS_ACTIVE, schema);
		//goto privilege order detail page
		lm.gotoOrderPageFromOrdersTopMenu(ordNum);
		
		//get privilege number from order detail page
		String privilegeNum = LicMgrPrivilegeOrderDetailsPage.getInstance().getAllPrivilegesNum().trim();
		//invalidate privilege order 
		lm.invalidatePrivilegeOrder(privilege.operateReason, privilege.operateNote);
		
		//Verify related privileges order status changed to invalid from UI.
		lm.gotoPrivilegeDetailFromOrderPg(privilegeNum);
		lm.verifyPrivilegeStatusFromUI(OrmsConstants.INVALID_STATUS);
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
		cust.identifier.identifierNum = "123456780";//"123456789";
		cust.identifier.country = "Canada";
		cust.customerClass = "Individual";
		cust.lName = "TEST-InvalidOrder";
		cust.fName ="QA-InvalidOrder";
		cust.residencyStatus="Non Resident";
	}

}
