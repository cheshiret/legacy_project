package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.calculateorderprice;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrCalculatePrivilegeOrderPriceTestCase;

/**
 * 
 * @Description: Verify whether the calculation of privilege order price correct or not when doing "purchase privilege" or/and "duplicate privilege" transactions
 * Scenario#1: privilege instance original price for each privilege instance being purchased with the "purchase privilege" transaction type
 * Scenario#2: privilege instance duplicate price for each privilege instance being replaced with the "duplicate privilege" transaction type
 * Scenario#3: sum[scenario#1] plus sum[scenario#2]
 * 
 * @Preconditions: An existing privilege(pricing, agent assignment, license year, quantity control) named 'CalculateOrderPrice'.
 * @SPEC: Calculation of New Privilege Order Price for Privilege Purchases and Duplicates of <<Calculate Privilege Order Price.doc>>
 * @Task#: AUTO-881
 * 
 * @author QA-qchen
 * @Date  Feb 20, 2012
 */
public class PurchaseAndDuplicate extends LicMgrCalculatePrivilegeOrderPriceTestCase {
	
	@Override
	public void execute() {
		lm.checkPrivilegesExist(schema, privilege.code);
		lm.loginLicenseManager(login);
		//Scenario#1: calculate privilege order price with 'purchase privilege' transaction
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		this.calculateOrderPrice(new String[]{OrmsConstants.TRANNAME_PURCHASE_PRIVILEGE}, new String[]{cust.lName}, new String[]{cust.fName});
		pay = new Payment("Cash");
		String orderNum = lm.processOrderCart(pay);
		
		//Scenario#2: calculate privilege order price with 'duplicate privilege' transaction
		lm.replacePrivilegeToCartFromCustQuickSearch(cust, orderNum, privilege);
		this.calculateOrderPrice(new String[]{OrmsConstants.TRANNAME_DUPLICATE_PRIVILEGE}, new String[]{cust.lName}, new String[]{cust.fName});
		
		//Scenario#3: calculate privilege order price with above 2 transactions
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(anotherCust, privilege);
		this.calculateOrderPrice(new String[]{OrmsConstants.TRANNAME_DUPLICATE_PRIVILEGE, OrmsConstants.TRANNAME_PURCHASE_PRIVILEGE},
		new String[]{cust.lName, anotherCust.lName},
		new String[]{cust.fName, anotherCust.fName});
		lm.cancelCart();
		
		//void privilege order
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}
}
