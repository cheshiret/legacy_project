package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.duplicate;

import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrGetListForDuplicate;

/**
 * 
 * @Description: Verify whether privilege product can be duplicate AND is grouped by order number in replacement page
 * @Preconditions: Need an existing customer
 * @SPEC: <<Get Privilege List for Duplicate.doc>>
 * @Task#: AUTO-881
 * 
 * @author qchen
 * @Date  Apr 17, 2012
 */
public class GetListForDuplicate_SameOrder extends LicMgrGetListForDuplicate {

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		privilege.code = "GLO";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		//precondition#1: create privilege to make it can be duplicated
		this.createPrivilegeProduct();
		lm.logOutLicenseManager();
		
		login.location = "HF HQ Role/WAL-MART";
		lm.loginLicenseManager(login);
		//precondition#2: make a original privilege order with QTY=3
		privilege.qty = "3";
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		String orderNum = lm.processOrderCart(pay);
		
		//go to 'Replacement Privileges' list page to verify whether the privileges group by order or not
		lm.gotoAddItemPgFromCustomerTopMenu(cust);
		lm.gotoReplacePrivAddItemPgFromOrgiAddPg();
		lm.verifyPrivilegesGroupByOrderNum(orderNum, privilege.purchasingName, privilege.purchasingName, privilege.purchasingName);
		
		//finish the duplicate process to ensure this privilege can be duplicated
		lm.replacePrivilegeToCartFromCustomerTopMenu(cust, orderNum);
		orderNum = lm.processOrderCart(pay);

		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}
}
