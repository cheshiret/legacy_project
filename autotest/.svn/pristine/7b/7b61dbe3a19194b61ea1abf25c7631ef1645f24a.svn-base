package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.calculateorderprice;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrCalculatePrivilegeOrderPriceTestCase;

/**
 *
 * @Description: Verify whether the calculation of privilege order price correct or not when doing "undo void purchase privilege" or "undo void duplicate privilege" transactions
 * Scenario#1: calculate privilege order price with "undo void purchase privilege" transaction
 * Scenario#2: calculate privilege order price with "undo void duplicate privilege" transaction
 *
 * @Preconditions: An existing privilege(pricing, agent assignment, license year, quantity control) named 'CalculateOrderPrice'.
 * @SPEC: 'Re-calculation of Privilege Order Price for Privilege Undo Void' part of <<Calculate Privilege Order Price.doc>>
 * @Task#: AUTO-881
 *
 * @author QA-qchen
 * @Date  Feb 20, 2012
 */
public class UndoVoidOriginalDuplicated extends LicMgrCalculatePrivilegeOrderPriceTestCase {

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//precondition#1: make an privilege order
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);pay = new Payment("Cash");
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		//precondition#2: void the privilege order
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		orderNum = lm.processOrderCart(pay).split(" ")[0]; 

		/*
		 * Scenario#1: calculate privilege order price with 'undo void purchase privilege transaction'
		 */
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.undoVoidPrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		this.calculateOrderPrice(new String[]{OrmsConstants.TRANNAME_UNDO_VOID_PURCHASE_PRIVILEGE}, new String[]{cust.lName}, new String[]{cust.fName});
		lm.cancelCart();

		//precondition#3: make another privilege order
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum2 = lm.processOrderCart(pay).split(" ")[0];

		//precondition#4: duplicate this original privilege order
		lm.replacePrivilegeToCartFromCustQuickSearch(cust, orderNum2, privilege);
		orderNum2 = lm.processOrderCart(pay).split(" ")[0];

		//precondition#5: void the duplicated-privilege order
		lm.gotoPrivilegeOrderDetailPage(orderNum2);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		orderNum2 = lm.processOrderCart(pay).split(" ")[0];

		/*
		 * Scenario#2: calculate privilege order price with 'undo void duplicate privilege transaction'
		 */
		lm.gotoPrivilegeOrderDetailPage(orderNum2);
		lm.undoVoidPrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		this.calculateOrderPrice(new String[]{OrmsConstants.TRANNAME_UNDO_VOID_DUPLICATE_PRIVILEGE}, new String[]{cust.lName}, new String[]{cust.fName});
		lm.cancelCart();
		
		/*
		 * Scenario#3: calculate order price with 'undo void purchase privilege transaction' and 'undo void duplicate privilege transaction'
		 */
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.undoVoidPrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		this.calculateOrderPrice(new String[]{OrmsConstants.TRANNAME_UNDO_VOID_DUPLICATE_PRIVILEGE, OrmsConstants.TRANNAME_UNDO_VOID_PURCHASE_PRIVILEGE},
				new String[]{cust.lName, cust.lName},
				new String[]{cust.fName, cust.fName});
		lm.cancelCart();
		lm.logOutLicenseManager();
	}

//	@Override
//	public void wrapParameters(Object[] param) {
//		login.contract = "MS Contract";
//		login.location = "HF HQ Role/WAL-MART";
//
//		privilege.name = "CalculateOrderPrice";
//		privilege.purchasingName = "COP-CalculateOrderPrice";
//		privilege.licenseYear = "2021";
//		privilege.qty = "1";
//		privilege.operateReason = "";
//		privilege.operateNote = "";
//
//		cust.customerClass = "Individual";
//		cust.identifier.identifierType = "Green Card";
//		cust.identifier.identifierNum = "111111";
//		cust.identifier.country = "Canada";
//		cust.residencyStatus = "Non Resident";
//	}
}
