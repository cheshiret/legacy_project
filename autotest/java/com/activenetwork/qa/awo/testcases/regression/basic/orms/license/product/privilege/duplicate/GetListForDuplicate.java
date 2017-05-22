package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.duplicate;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrGetListForDuplicate;

/**
 * 
 * @Description: Verify whether privilege product can be duplicated with some preconditions
 * @Preconditions: Need 2 existing customers
 * @SPEC: <<Get Privilege List for Duplicate.doc>>
 * @Task#: AUTO-881
 * 
 * @author QA-qchen
 * @Date  Feb 27, 2012
 */
public class GetListForDuplicate extends LicMgrGetListForDuplicate {
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		privilege.code = "GLB";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		//precondition: create a privilege
		this.createPrivilegeProduct();
		lm.logOutLicenseManager();
		
		login.location = "HF HQ Role/WAL-MART";
		lm.loginLicenseManager(login);
		//1. make a original privilege order
		lm.makePrivilegeToCartFromCustomerQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay);
		
		//2. duplicate this original privilege order
		//verify the privilege exist in list
		lm.gotoAddItemPgFromCustomerTopMenu(cust);
		lm.gotoReplacePrivAddItemPgFromOrgiAddPg();
		lm.verifyPrivilegesGroupByOrderNum(orderNum, privilege.purchasingName);
		//verify it can be duplicated
		lm.replacePrivilegeToCartFromCustomerTopMenu(cust, orderNum);
		orderNum = lm.processOrderCart(pay);

		//3. make another order and transfer it
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		orderNum = lm.processOrderCart(pay);
		//transfer privilege order
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String privilegeNum = LicMgrPrivilegeOrderDetailsPage.getInstance().getAllPrivilegesNum().trim();
		lm.gotoPrivilegeDetailFromOrderPg(privilegeNum);
		lm.transferPrivToOrderCart(transferCust, privilege);
		orderNum = lm.processOrderCart(pay);
		
		//4. duplicate this transferred privilege order
		//verify the privilege exist in list
		lm.gotoAddItemPgFromCustomerTopMenu(transferCust);
		lm.gotoReplacePrivAddItemPgFromOrgiAddPg();
		lm.verifyPrivilegesGroupByOrderNum(orderNum, privilege.purchasingName);
		//verify it can be duplicated
		lm.replacePrivilegeToCartFromCustomerTopMenu(transferCust, orderNum);
		lm.processOrderCart(pay);
		
		lm.logOutLicenseManager();
	}
}
