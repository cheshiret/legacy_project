package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.voidorder;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrVendorFinConfigInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorFinConfigSubPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:work flow:  
 * 1. make privilege order, with print document
 * 2. void this privilege order   
 * 3. go to return document list page, return privilege order document
 * 4. verify privilege order item status should be returned
 * 
 * @Preconditions:
 * 1. An existing privilege(pricing, agent assignment, license year, quantity control) named 'CalculateOrderPrice'.
 * 2. select * from X_PROP WHERE NAME Like ('%PrivilegeVoidReversalTransactionCoverage%'), make sure value is '3'  
 * 3. Vendor should has no "Auto Return Voided Documents" at finicial configuation
 * 
 * @SPEC: Mark PENDING privilege document as RETURNED [TC:011745]
 * @Task#:AUTO-1314
 * 
 * @author vzhang
 * @Date  Nov 6, 2012
 */

public class MakePendingPriDocAsReturned extends LicenseManagerTestCase{
	private String vendorNum, storeName;
	private LicMgrVendorFinConfigInfo financialConfig = new LicMgrVendorFinConfigInfo();
	private LicMgrPrivilegeOrderDetailsPage orderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		//precondition#1: go to Financial Config page in Vendor Details page, to update 'Void Return Charge Days' as 1(it's the minimum),
		//and set 'Auto-Return' Voided Documents as NO
		lm.gotoVendorDetailsPgFromTopMenu(vendorNum);
		lm.gotoVendorFinConfigSubPage();
		this.editVendorFinancialConfig(prepareVendorFinancialConfigInfo());

		//make privilege order with print document
		lm.gotoHomePage();
		lm.switchLocationInHomePage("HF HQ Role - Auto-" + storeName);
		//make privilege order
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);
		//process order with print document
		String orderNum = lm.processOrderCart(pay, true).split(" ")[0];

		//void privilege order
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		lm.processOrderCart(pay);
		
		//return privilege document
		lm.returnPrivilegeDocument(orderNum);
		
		//Go to privilege order details page to check 'Returned' order item
		lm.gotoPrivilegeOrderDetailPageFromTopMenu(orderNum);
		String privilegeNum = lm.getPrivilegeNumByOrdNum(schema, orderNum);
		String orderStatus = orderDetailsPage.getPrivilegeOrderItemStatus(privilegeNum);
		if(!orderStatus.equals(OrmsConstants.RETURNED_STATUS)){
			throw new ErrorOnPageException("Order item status of product:"+privilege.purchasingName+" is wrong. Expect status should be " + OrmsConstants.RETURNED_STATUS
					+ ", but actullay is " + orderStatus);
		}logger.info("Successfully verify Order item status is:"+OrmsConstants.RETURNED_STATUS);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		storeName = "WAL-MART";
		vendorNum = "Auto555";
		
		privilege.code = "COP";
		privilege.name = "CalculateOrderPrice";
		privilege.purchasingName = "COP-CalculateOrderPrice";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-Basic14";
		cust.lName = "TEST-Basic14";
		cust.dateOfBirth = "19880608";
		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic000014";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
	}
	
	private LicMgrVendorFinConfigInfo prepareVendorFinancialConfigInfo() {
		financialConfig.voidReturnChargeDays = "1";
		financialConfig.autoReturnVoidedDoc = "No";
		
		return financialConfig;
	}
	
	/**
	 * Edit vendor financial config info
	 */
	private void editVendorFinancialConfig(LicMgrVendorFinConfigInfo vendorFinConfigInfo) {
		LicMgrVendorFinConfigSubPage financialConfigPage = LicMgrVendorFinConfigSubPage.getInstance();
		
		logger.info("Edit vendor fianncial config - 'Void Return Charge Days' as " + vendorFinConfigInfo.voidReturnChargeDays + " and 'Auto-Return Voided Documents' as " + vendorFinConfigInfo.autoReturnVoidedDoc);
		financialConfigPage.editFinancialConfigInfo(vendorFinConfigInfo);
		financialConfigPage.clickSave();
		ajax.waitLoading();
		financialConfigPage.waitLoading();
	}

}
