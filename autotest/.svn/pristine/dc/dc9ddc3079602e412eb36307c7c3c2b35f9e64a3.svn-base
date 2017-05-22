package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.calculateorderprice;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeItemDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrCalculatePrivilegeOrderPriceTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description: Verify whether the calculation of privilege order price correct or not when doing "return privilege documents" transaction
 * 
 * @Preconditions: An existing privilege(pricing, agent assignment, license year, quantity control) named 'CalculateOrderPrice'.
 * @SPEC: Re-calculate of Privilege Order Price for Return of Privilege Documents part of <<Calculate Privilege Order Price.doc>>
 * @Task#: AUTO-881
 * 
 * @author QA-qchen
 * @Date  Feb 20, 2012
 */
public class ReturnDocuments extends LicMgrCalculatePrivilegeOrderPriceTestCase {
	private LicMgrPrivilegeOrderDetailsPage orderDetailPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//precondition#1: make a privilege order with printing document
		privilege.qty = "2";
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay, true);
		
		//void this privilege order with returning document
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String privilegeNums[] = orderDetailPage.getAllPrivilegesNum().split(" ");
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		
		//return privilege document
		lm.returnPrivilegeDocument(orderNum);
		
		//goto privilege order detail page to verify order price
		lm.gotoPrivilegeOrderDetailPageFromTopMenu(orderNum);
		double totalPrice = Double.parseDouble(orderDetailPage.getPrice());
		double itemPrice1 = Double.parseDouble(orderDetailPage.getPrivilegeOrderItemPrice(privilegeNums[0]));
		double itemPrice2 = Double.parseDouble(orderDetailPage.getPrivilegeOrderItemPrice(privilegeNums[1]));
		this.reCalculateOrderPrice(totalPrice, itemPrice1 + itemPrice2);
		
		//goto privilege item detail to verify transaction name
		lm.gotoPrivilegeDetailFromOrderPg(privilegeNums[0]);
		this.verifyTransactionName(OrmsConstants.TRANNAME_RETURN_DOCUMENT);
		lm.gotoPrivilegeOrderDetailPageFromTopMenu(orderNum);
		lm.gotoPrivilegeDetailFromOrderPg(privilegeNums[1]);
		this.verifyTransactionName(OrmsConstants.TRANNAME_RETURN_DOCUMENT);
		lm.logOutLicenseManager();
	}
	
	/**
	 * Verify the order price in privilege order detail page
	 * @param total
	 * @param item
	 */
	protected void reCalculateOrderPrice(double total, double item) {
		logger.info("Verify privilege order price calculate correct or not.");
		if(Double.compare(total, item) != 0) {
			throw new ErrorOnPageException("The Order Price calculate wrongly.");
		} else logger.info("Privilege Order Price is re-calculated correctly.");
	}
	
	/**
	 * Verify the privilege transaction name
	 * @param expectedName
	 */
	private void verifyTransactionName(String expectedName) {
		LicMgrPrivilegeItemDetailPage itemDetailPage = LicMgrPrivilegeItemDetailPage.getInstance();
		
		logger.info("Verify privilege transaction name is correct or not.");
		List<String> history = itemDetailPage.getPrivilegeHistory(expectedName);
		if(!history.get(1).equals(expectedName)) {
			throw new ErrorOnPageException("Privilege Transaction Name doesn't match. Expected is " + expectedName + ", but actual is " + history.get(1));
		} else logger.info("Privilege Transaction Name correct.");
	}
}
