package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.voidorder;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrRefundWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: The case is used to verify the work flow of reverse Privilege Transaction with:
 * 1. the source payment of the transaction is in Deferred Payment group, and
 * 2. has a refund generated after reverse, and
 * 4. the location where the reverse happens is DIFFERENT from the one where the transaction was originally processed.
 * 5. the location where the reverse happens is configured to be able to issue Refunds in Cash.
 * @Preconditions:
 * 1. make sure the location 'RefundTest' has the payment type 'Cash*'.
 * @SPEC: Void Privilege Transaction - Sales Location Status [TC:034827]
 * @Task#: Auto-1789
 * 
 * @author nding1
 * @Date  Oct 30, 2013
 */
public class VoidInDiffLocConfigIssueToCash extends LicenseManagerTestCase {
	private String orgLoc, revLoc, revLocNm, orderNum;
	private LicMgrRefundWidget refundWidget = LicMgrRefundWidget.getInstance();
	private OrmsOrderCartPage lmOrderCartPg = OrmsOrderCartPage.getInstance();

	@Override
	public void execute() {
		// Turn ON the config of issue to Cash in DB for the location where reverse happens
		lm.configIssueRefundsInCash(schema, revLocNm, true);

		lm.loginLicenseManager(login);
		
		//make a privilege order;
 		lm.makePrivilegeToCartFromCustomerQuickSearch(cust, privilege);
		String price = lmOrderCartPg.getTotalPriceAmount();
		pay.payType = Payment.PAY_DEF_CASH;// Don't change!! Must use deferral payment
		orderNum = lm.processOrderCart(pay);
		lm.switchLocationInHomePage(revLoc);

		// Reverse 
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.reversePrivilegeOrdToCart(privilege.operateReason, privilege.operateNote);
		
		// Verify Refund Widget
		this.verifyRefundWidget(price, orgLoc);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env+".db.schema.prefix") + "MS";
		
		orgLoc = "RefundTest";
		login.contract = "MS Contract";
		login.location = "HF HQ Role/" + orgLoc;
		
		cust.lName = "TEST-RenewVhclReg1";
		cust.fName = "QA-RenewVhclReg1";
		cust.residencyStatus = "Non Resident";

		privilege.code = "931";
		privilege.name = "ParamPrivilege1";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Auto Test - VoidInDiffLocConfigIssueToCash";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};	
		
		revLocNm = "WAL-MART";
		revLoc = "HF HQ Role - Auto-" + revLocNm;
		
		pay.checkNumber = "123456";
	}

	private void verifyRefundWidget(String price, String storeNm) {
		boolean res = true;
		
		// Verify the message and the store id and store name in the option 2 on Refund Widget
		res &= refundWidget.compareMessage(price);
		
		// Verify the store id and store name in the option 2 on Refund Widget
		String storeID = lm.getAgentID(schema, storeNm);
		res &= refundWidget.compareStoreInfo(storeID, storeNm);
		
		// Verify the status of the two radio buttons
		res &= refundWidget.compareIssueRefundToCustRadioStatus(true);
		res &= refundWidget.comparePostRefundAsCreditRadioStatus(true);

		if (!res) {
			throw new ErrorOnPageException("Please check log info!");
		} else {
			// clean up
			refundWidget.selectIssueRefundToCustomerRadio();
			ajax.waitLoading();
			refundWidget.clickOK();
			ajax.waitLoading();
			browser.waitExists();
		}
	}
}