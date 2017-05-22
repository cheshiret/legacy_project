package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.calculateorderprice;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrCalculatePrivilegeOrderPriceTestCase;

/**
 * 
 * @Description: verify whether the calculation of privilege order price correct or not when doing "transfer" transaction
 * @Preconditions: An existing privilege(pricing, agent assignment, license year, quantity control) named 'CalculateOrderPrice'.
 * @SPEC: Calculation of New Privilege Order Price for Privilege Transfer of <<Calculate Privilege Order Price.doc>>
 * @Task#: AUTO-881
 * 
 * @author QA-qchen
 * @Date  Feb 20, 2012
 */
public class Transfer extends LicMgrCalculatePrivilegeOrderPriceTestCase {
	
	@Override
	public void execute() {
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);
		
		//precondition: make a privilege order
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay);
		
		//calculate privilege order price with 'transfer privilege' transaction
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String privilegeNum = LicMgrPrivilegeOrderDetailsPage.getInstance().getAllPrivilegesNum();
		lm.gotoPrivilegeDetailFromOrderPg(privilegeNum);
		lm.transferPrivToOrderCart(anotherCust, privilege);
		this.calculateOrderPrice(new String[]{OrmsConstants.TRANNAME_TRANSFER_PRIVILEGE}, new String[]{anotherCust.lName}, new String[]{anotherCust.fName});
		lm.cancelCart();
		
		//void privilege order
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}
}
