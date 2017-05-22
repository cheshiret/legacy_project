package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.ordercart;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify order cart and order summary page when purchasing Seal pack inventory
 * @Preconditions: production data: 20-Seal Pack
 * @SPEC: Order Cart - Privilege Sales - SK Seals and Ledgers [TC:100118]
 * 				Order Summary - Privilege Sales - SK Seals and Ledgers [TC:100119]
 * @Task#: AUTO-2165
 * 
 * @author qchen
 * @Date  May 20, 2014
 */
public class PurchaseInventory_Seal extends LicenseManagerTestCase {
	
	private OrmsOrderCartPage cart = OrmsOrderCartPage.getInstance();
	private OrmsOrderSummaryPage summaryPage = OrmsOrderSummaryPage.getInstance();
	private boolean result = true;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//1. Purchase Inventory to cart from home page
		List<String> inventoryNums = lm.makeInventoryPrivilegeToCart(privilege);
		String nums[] = inventoryNums.get(0).split(",");
		
		//2. verify order cart page - customer(General Public), Seal #(s)
		result &= cart.verifyCustomerInfo(cust, null);
		result &= cart.compareSealNums(nums);
		
		lm.processOrderToOrderSummary(pay);
		//3. verify order summary page
		result &= summaryPage.verifyCustomerInfo(cust, null);
		result &= summaryPage.compareSealNums(nums);
		
		String ordNum = summaryPage.getAllOrdNums();
		lm.finishOrder();
		
		//clean up
		lm.reversePrivilegeOrderToCleanUp(ordNum, privilege.operateReason, privilege.operateNote, pay);
		if(!result) throw new ErrorOnPageException("Not all checkpoints are passed, please refer to log for details info.");
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SK";
		login.contract = "SK Contract";
		login.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		
		cust.fName = "GENERAL";
		cust.lName = "PUBLIC";
		cust.residencyStatus = null;
		
		privilege.code = "20";
		privilege.name = "Seal Pack";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
	}
}
