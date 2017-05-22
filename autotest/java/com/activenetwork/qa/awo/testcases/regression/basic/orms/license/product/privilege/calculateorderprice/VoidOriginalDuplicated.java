package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.calculateorderprice;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrCalculatePrivilegeOrderPriceTestCase;

/**
 * 
 * @Description: Verify whether the calculation of privilege order price correct or not when doing "void purchase privilege" or/and "void duplicate privilege" transactions
 * Scenario#1: calculate privilege order price with "void purchase privilege" transaction;
 * Scenario#2: calculate privilege order price with "void duplicate privilege" transaction;
 * Scenario#4: calculate privilege order price with above 2 transactions
 * 
 * @Preconditions: An existing privilege(pricing, agent assignment, license year, quantity control) named 'CalculateOrderPrice'.
 * @SPEC: Re-calculation of Privilege Order Price for Privilege Void
 * @Task#: AUTO-881
 * 
 * @author QA-qchen
 * @Date  Feb 20, 2012
 */
public class VoidOriginalDuplicated extends LicMgrCalculatePrivilegeOrderPriceTestCase {

	@Override
	public void execute() {
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);
		
		//precondition: make a original privilege order
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);pay = new Payment("Cash");
		String orderNum = lm.processOrderCart(pay,false);
	
		
		//Scenario#1: calculate privilege order price with 'void purchase privilege transaction'
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		this.calculateOrderPrice(new String[]{OrmsConstants.TRANNAME_VOID_PURCHASE_PRIVILEGE}, new String[]{cust.lName}, new String[]{cust.fName});
		lm.cancelCart();
		
		//precondition: duplicate the 1st privilege order
		lm.replacePrivilegeToCartFromCustQuickSearch(cust, orderNum, privilege);
		orderNum = lm.processOrderCart(pay, false);
		
		//Scenario#2: calculate privilege order price with 'void duplicate privilege transaction'
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);//can't Void because the document hasn't been printed
		this.calculateOrderPrice(new String[]{OrmsConstants.TRANNAME_VOID_DUPLICATE_PRIVILEGE}, new String[]{cust.lName}, new String[]{cust.fName});
		lm.cancelCart();
		
		//Can't do void when there still have item in cart.
	/*	//precondition: make another original privilege order
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
	
		String orderNum2 = lm.processOrderCart(pay, false);
		
		
		//Scenario#3: calculate privilege order price with 'void purchase privilege transaction' and 'void duplicate privilege transaction'
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		lm.gotoPrivilegeOrderDetailPageFromTopMenu(orderNum2);
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);//TODO can't void 2 order in the same time?
		this.calculateOrderPrice(new String[]{OrmsConstants.TRANNAME_VOID_PURCHASE_PRIVILEGE, OrmsConstants.TRANNAME_VOID_DUPLICATE_PRIVILEGE},
				new String[]{cust.lName, cust.lName}, 
				new String[]{cust.fName, cust.fName});
		lm.processOrderCart(pay);*/
		
		lm.logOutLicenseManager();
	}
}
