/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.voidorder;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrRefundWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:The case is used to verify the work flow of Void Privilege Transaction with:
 * 1. Purchase Privilege Order, and
 * 2. the source payment of the transaction is in Deferred Payment group, and
 * 3. original store location is closed after original purchase, and
 * 4. has a refund generated after void, and
 * 5. the location where the void happens is different from the one where the transaction was originally processed.
 * 6. the location where the void happens is NOT configured to be able to issue Refunds in Cash.
 * @LinkSetUp:d_hf_add_privilege_prd:id=2870
 *            d_hf_add_store:id=380
 *            
 * @SPEC:Privilege Management [TC:034846]
 * @Task#:AUTO-2134 
 * 
 * @author Vivian
 * @Date  Apr 22, 2014
 */
public class VoidInDiffLocNotConfigIssueToCashCloseStore extends LicenseManagerTestCase{
	private OrmsOrderCartPage lmOrderCartPg = OrmsOrderCartPage.getInstance();	
	private LicMgrRefundWidget refundWidget = LicMgrRefundWidget.getInstance();
	private StoreInfo store = new StoreInfo();
	private String admLoc, revLocNm, revLoc;

	@Override
	public void execute() {
		// Update the store's status to Active in DB
		lm.updateStoreStatusFromDB(schema, store.storeName, ACTIVE);
		
		// Turn off the config of issue refunds in cash for the location where reverse happens
		lm.configIssueRefundsInCash(schema, revLocNm, false);
		
		lm.loginLicenseManager(login);	
		//make a privilege order;
 		lm.makePrivilegeToCartFromCustomerQuickSearch(cust, privilege);
		String price = lmOrderCartPg.getTotalPriceAmount();
		String orderNum = lm.processOrderCart(pay,true);//must print document, becacause need to void
		
		// Switch to admin location to close the store
		lm.switchLocationInHomePage(admLoc);
		lm.gotoStoreDetailPage(store.storeName);
		lm.updateStoreStatus(store.status);
		
		// Switch to reverse location
		lm.switchLocationInHomePage(revLoc);
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		//void this privilege order
		lm.voidPrivilegeOrderToCart(privilege.operateReason, privilege.operateNote);
		
		// Verify Refund Widget
		this.verifyRefundWidget(price, store.storeName);
		
		// Select to Issue this Refund later as Check to the Customer
		lm.issueRefundToCustFromRefundWidgetToOrdDetailsPg();
		//verify related privileges status changed to active
		lm.verifyAllPrivilegesStatus(orderNum, PRIV_STATUS_VOIDED, schema);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env+".db.schema.prefix") + "MS";
		
		store.storeName = "AgentForCloseOfPri";
		store.status = "Inactive-Request Agent Closure";
		
		login.contract = "MS Contract";
		login.location = "HF HQ Role/" + store.storeName;
		
		cust.lName = "TEST-Refund";
		cust.fName = "QA-Refund";
		cust.residencyStatus = "Non Resident";
		
		pay.payType = Payment.PAY_DEF_PER_CHQ;// Don't change!! Must use deferral payment
		pay.checkNumber = "123456";
		
		admLoc = "HF Administrator - Auto-" + TestProperty.getProperty("ms.admin.location");
		revLocNm = "RefundTest";
		revLoc = "HF HQ Role - Auto-" + revLocNm;
		
		privilege.code = "VP1";
		privilege.name = "VoidPriOrd01";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.searchStatus = new String[]{OrmsConstants.ACTIVE_STATUS};
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Auto Test - VoidInDiffLocNotConfigIssueToCashCloseStore";
	}
	
	private void verifyRefundWidget(String price, String storeNm) {
		boolean res = true;
		
		// Verify the message and the store id and store name in the option 2 on Refund Widget
		res &= refundWidget.compareMessage(price);
		
		//due to original purchase location store is closed, so will not display original purchase store info
		String storeID = lm.getAgentID(schema, storeNm);
		refundWidget.verifyStoreInfoShouldNotExists(storeID, storeNm);
		
		// Verify the Issue this Refund to the Customer now radio buttons not exists
		refundWidget.verifyIssueRefundMethodNotExists("Issue this Refund to the Customer now");
		
		if (!res) {
			throw new ErrorOnPageException("Please check log info!");
		}else logger.info("Regund widget info is correct.");
	}

}
