package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.calculateorderprice;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrVendorFinConfigInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeItemDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorFinConfigSubPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicMgrCalculatePrivilegeOrderPriceTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: Verify whether the calculation of privilege order price correct or not when doing "charge for unreturned privilege documents" transaction
 * 
 * @Preconditions: An existing privilege(pricing, agent assignment, license year, quantity control) named 'CalculateOrderPrice'.
 * @SPEC: 'Re-calculation of Privilege Order Price for Charge of Unreturned Documents' part of <<Calculate Privilege Order Price.doc>>
 * @Task#: AUTO-881
 * 
 * @author QA-qchen
 * @Date  Feb 20, 2012
 */
public class UnreturnedDoccuments extends LicMgrCalculatePrivilegeOrderPriceTestCase {
	private LicMgrPrivilegeOrderDetailsPage orderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
	private VendorInfo vendor = new VendorInfo();
	private LicMgrVendorFinConfigInfo financialConfig = new LicMgrVendorFinConfigInfo();
	
	@Override
	public void execute() {
		login.location = "HF Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
		lm.loginLicenseManager(login);
		
		//precondition#1: go to Financial Config page in Vendor Details page, to update 'Void Return Charge Days' as 1(it's the minimum),
		//and set 'Auto-Return' Voided Documents as NO
		vendor.number = "Auto555";
		lm.gotoVendorDetailsPgFromTopMenu(vendor.number);
		lm.gotoVendorFinConfigSubPage();
		this.editVendorFinancialConfig(prepareVendorFinancialConfigInfo());
		
		lm.gotoHomePage();
		lm.switchLocationInHomePage("HF HQ Role - Auto-WAL-MART");
		//get the previous privilege order number which is stored in QA_AUTOMATION table
		String nums[] = lm.readQADB(this.caseName).split(",");
		boolean flag = false;
		// nums.length should be 2, nums[0] is order number, and nums[1] is privilege number
		if(nums != null && nums.length == 2 && lm.checkOrderExists(schema, nums[0]) && lm.checkPrivilegeNumberExists(schema, nums[1])) {
			String orderNum = nums[0];
			String privilegeNum = lm.getPrivilegeNumByOrdNum(schema, orderNum);
			//go to order detail page
			lm.gotoPrivilegeOrderDetailPage(orderNum);
			String orderStatus = orderDetailsPage.getPrivilegeOrderItemStatus(privilegeNum);
			if(!orderStatus.equals(OrmsConstants.CHARGED_STATUS) && !orderStatus.equals(OrmsConstants.EXPIRED_CHARGED_STATUS)) {//'Void' first, then 'Charged', finally 'Expired Charged', 'Charged' or 'Expired Charged' are both fine for this
				String creationDate = orderDetailsPage.getCreationDate().split(" ")[0];
				String expectedChargedDate = DateFunctions.getDateAfterGivenDay(creationDate, 1);
				String today = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
				if(DateFunctions.compareDates(expectedChargedDate, today) < 0) {
					throw new ActionFailedException("The privilege status should be '" + OrmsConstants.CHARGED_STATUS + "' or '" + OrmsConstants.EXPIRED_CHARGED_STATUS + "' today, but now it is '" + orderStatus + "'");
				}
				throw new TestCaseFailedException("The privilege is '" + orderStatus + "' now to wait Charged. Please re-run this case after " + expectedChargedDate + " CST");
			}
			
			//after document return period(setup in Vendor Financial Config page), the transaction will be automatically changed to 'Charge for Unreturned Documents'
			double totalPrice = Double.parseDouble(orderDetailsPage.getPrice());
			double itemPrice = Double.parseDouble(orderDetailsPage.getPrivilegeOrderItemPrice(privilegeNum));
			this.reCalculateOrderPrice(totalPrice, itemPrice);
			//go to privilege item detail page
			lm.gotoPrivilegeDetailFromOrderPg(privilegeNum);
			this.verifyTransactionName(OrmsConstants.TRANNAME_CHARGE_FOR_UNRETURNED_PRIVILEGE_DOCUMENTS);
			flag = true;// verify successfully
		}
		
		//precondition#2: make a privilege order with printing document
		lm.gotoHomePage();
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		String orderNum = lm.processOrderCart(pay, true).split(" ")[0];
		
		//precondition#3:void the privilege order without returning document
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String privilegeNum = LicMgrPrivilegeOrderDetailsPage.getInstance().getAllPrivilegesNum().trim();
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		lm.writeQADB(this.caseName, orderNum + "," + privilegeNum);
		lm.logOutLicenseManager();
		if(!flag){
			throw new TestCaseFailedException("Data has been prepared, please run it "+DateFunctions.getDateAfterToday(1));
		}
	}
	
	private LicMgrVendorFinConfigInfo prepareVendorFinancialConfigInfo() {
		financialConfig.voidReturnChargeDays = "1";
		financialConfig.autoReturnVoidedDoc = "No";
		
		return financialConfig;
	}
	
	/**
	 * Edit vendor financial config info
	 * @param f
	 */
	private void editVendorFinancialConfig(LicMgrVendorFinConfigInfo f) {
		LicMgrVendorFinConfigSubPage financialConfigPage = LicMgrVendorFinConfigSubPage.getInstance();
		
		logger.info("Edit vendor fianncial config - 'Void Return Charge Days' as " + f.voidReturnChargeDays + " and 'Auto-Return Voided Documents' as " + f.autoReturnVoidedDoc);
//		financialConfigPage.editFinancialConfigInfo(prepareVendorFinancialConfigInfo());
//		financialConfigPage.clickSave();
//		ajax.waitLoading();
//		financialConfigPage.waitExists();
		prepareVendorFinancialConfigInfo();
		financialConfigPage.setVoidReturnChargeDays(financialConfig.voidReturnChargeDays);
		financialConfigPage.selectAutoReturnVoidedDoc(financialConfig.autoReturnVoidedDoc);
		financialConfigPage.clickSave();
		ajax.waitLoading();
		financialConfigPage.waitLoading();
		
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
