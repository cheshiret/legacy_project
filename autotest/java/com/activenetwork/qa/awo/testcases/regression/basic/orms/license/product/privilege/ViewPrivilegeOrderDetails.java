package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderItems;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.common.popup.OrmsPrintPopupPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.page.AlertDialogPage;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;

/**
 * @Description:  View privilege order details info.
 * @Preconditions:
 * @SPEC:  View privilege order details
 * @Task#: Auto-871
 * @author jwang8
 * @Date  Feb 21, 2012
 */
public class ViewPrivilegeOrderDetails extends LicenseManagerTestCase{
	private OrderInfo orderInfo = new OrderInfo();
	OrderItems orderItem = new OrderItems();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//make a new privilege order.
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		this.getChargeInfo();
		orderInfo.orderNum = this.processOrderCart(pay);
		lm.gotoPrivilegeOrderDetailPage(orderInfo.orderNum);
		this.verifyPrivilegeOrderDetailInfo();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS",env);

		privilege.purchasingName = "adv-LocationDailySales";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "01 - Operator Error";
		privilege.operateNote = "Automation-test";

		cust.lName = "Test-ViewPriDetail";
		cust.fName ="QA-ViewPriDetail";
		cust.identifier.identifierType = "MDWFP #";//"Green Card";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);;
		cust.identifier.country = "Canada";
		cust.customerClass = "Individual";
		cust.residencyStatus="Non Resident";
		cust.phoneContact = "4088144589";
		cust.email = "jas@sina.com";


		orderInfo.balance = "$0.00";
		orderInfo.unissuedRefund = "$0.00";
		orderInfo.confirmStatus = "Full";
		orderInfo.unissuedRefund = "$0.00";
		orderInfo.creationUser = DataBaseFunctions.getLoginUserName(login.userName);

		orderItem.product = privilege.purchasingName;
		orderItem.itemStatus = "Active";
		orderItem.purchaseType = "Original";
		orderItem.itemPrice = orderInfo.orderPaid;
		orderInfo.orderList.add(orderItem);
	}
	
	/**Pay for the order.
	 * @param payment- pay info.
	 * @param isPrinted - the boolean value if need to print.
	 */
	public String processOrderCart(Payment pay) {
		OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
		LicenseMgrHomePage lmHomePg = LicenseMgrHomePage.getInstance();
		OrmsPrintPopupPage printPg = OrmsPrintPopupPage.getInstance();
		AlertDialogPage alert = AlertDialogPage.getInstance();
		ConfirmDialogPage confirmDialog = ConfirmDialogPage.getInstance();

		logger.info("Process order cart.");

		lm.processOrderToOrderSummary(pay);

		String ordID = lmOrdSumPg.getAllOrdNums();
		logger.info("Processed order: " + ordID);

        orderInfo.receiptNum = lmOrdSumPg.getReceiptNum();

		logger.info("Receipt number: " + orderInfo.receiptNum);
		Object page1 = browser.waitExists(printPg,alert,lmOrdSumPg);
		if(printPg == page1){
			printPg.clickSuccess();
			ajax.waitLoading();
			lmOrdSumPg.waitLoading();
		}
		lmOrdSumPg.clickFinishButton();

		browser.waitExists(confirmDialog, lmHomePg);
		return ordID;
	}
	/**Get the charge info
	 */
	private void getChargeInfo(){
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
		orderInfo.orderPrice = "$" + ormsOrderCartPg.getTotalPriceAmount() ;
		orderInfo.orderPaid ="$" + ormsOrderCartPg.getPaymentAmount()+"0";
		orderInfo.balance = "$0.00";
		orderItem.itemPrice = orderInfo.orderPaid;
	}
	/**
	 * verify the privilege order detail info.
	 */
	private void verifyPrivilegeOrderDetailInfo(){
		LicMgrPrivilegeOrderDetailsPage orderDetailsPg = LicMgrPrivilegeOrderDetailsPage.getInstance();
		boolean pass = orderDetailsPg.comparePrivilegeOrderDetailInfo(orderInfo, cust);
		if(!pass){
			throw new ErrorOnPageException("Privilege order detail info error");
		}else{
			logger.info("Privileget order detail info correct");
		}

	}


}
