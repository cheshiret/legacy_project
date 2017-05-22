package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.voidorder;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderItems;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * @Description: (P)
 * 1. Make privilege order
 * 2. Void privilege order
 * 3. Go to privilege order details page to check 'Returned' order item
 * 
 * @Preconditions:
 * 1. Have existing Vendor(AutoReturnVoidedDocumantVendor) has "Auto Return Voided Documents"
 * 2. Have existing Agent(AutoReturned)
 * 
 * @SPEC: Process Void Privilege Transaction 
 * @Task#: AUTO-968
 * 
 * @author: SWang
 * @Date: 2012-3-31
 */
public class ReturnedPrivilegeDocument extends LicenseManagerTestCase {
	private String orderNum;

	public void execute() {
		lm.loginLicenseManager(login);

		//Make a privilege order
		lm.makePrivilegeToCartFromCustomerQuickSearch(cust, privilege);
		orderNum = lm.processOrderCart(pay).split(" ")[0];

		//Check "Active" order item
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		this.verifyOrderItemStatus(privilege.purchasingName, OrmsConstants.ACTIVE_STATUS);

		//Void privilege order
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);

		//Go to privilege order details page to check 'Returned' order item
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		this.verifyOrderItemStatus(privilege.purchasingName, OrmsConstants.RETURNED_STATUS);

		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/AutoReturned";
		schema = DataBaseFunctions.getSchemaName("MS",env);

		privilege.purchasingName = "COP-CalculateOrderPrice";
		privilege.licenseYear = lm.getFiscalYear(schema);		
		privilege.qty = "1";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Auto test";

		cust.identifier.identifierType = "VISA";
		cust.identifier.identifierNum = "AUTO12341";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
	}

	/**
	 * Verify Order status for product in order details page
	 */
	private void verifyOrderItemStatus(String purchasingName,String status) {
		//LicMgrPrivilegeOrderSearchPage priviOrderSearchPg = LicMgrPrivilegeOrderSearchPage.getInstance();
		
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage
				.getInstance();
		List<OrderItems> orderItems = privOrderDetailsPage.getOrderInfo(purchasingName);
		if(orderItems.size()!=1){
			throw new ErrorOnDataException("There should be only one order items.");
		}
		logger.info("Successfully verify only one order items.");

		logger.info("Verify Order items status for product:"+purchasingName+" is "+status);
		String sttausOnPage=orderItems.get(0).itemStatus;
		if(!status.equals(sttausOnPage)){
			throw new ErrorOnPageException("Order item status of product:"+purchasingName+" is wrong ",status,sttausOnPage);
		}
		logger.info("Successfully verify Order item status is:"+status);
	}
}

