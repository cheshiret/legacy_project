/**
 *
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.common.popup.OrmsPrintPopupPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerOrdersPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.page.AlertDialogPage;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;


/**
 * @Description:  View the customer order page
 * @Preconditions: The customer searched in system.
 * @SPEC:  View customer order tab
 * @Task#: Auto-871
 * @author jwang8
 * @Date  Feb 20, 2012
 */
public class ViewCustomerOrder extends LicenseManagerTestCase{
	private OrderInfo orderInfo = new OrderInfo();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//make a new privilege order.
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		orderInfo.orderPrice =this.getPrice();
		orderInfo.orderNum = this.processOrderCart(pay);
		if(orderInfo.orderNum .contains(" ")){
			orderInfo.orderNum  = orderInfo.orderNum .split(" ")[0];
		}
		lm.gotoCustomerDetailFromTopMenu(cust);
		lm.gotoCustomerOrderPage();
		this.verifyCustomerInfoSuccess(orderInfo);
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

		cust.identifier.identifierType = "Green Card";//"Green Card";
		cust.identifier.identifierNum = "123456780";//"123456789";
		cust.identifier.country = "Canada";
		cust.customerClass = "Individual";
		cust.lName = "TEST-InvalidOrder";
		cust.fName ="QA-InvalidOrder";
		cust.residencyStatus="Non Resident";

		orderInfo.orderDate = DateFunctions.getToday();
		orderInfo.orderType = "Privilege Sale";
		orderInfo.billingCustomer = "Yes";
		orderInfo.saleLocation = login.location.split("/")[1];
		orderInfo.numOfItems = privilege.qty;
	    orderInfo.balance = "$0.00";

	}

	private void verifyCustomerInfoSuccess(OrderInfo expectedOrder){
		LicMgrCustomerOrdersPage orderPg = LicMgrCustomerOrdersPage.getInstance();
		boolean pass = orderPg.compareOrdersInfo(expectedOrder);
		if(!pass){
			throw new ErrorOnDataException("The custeomer order info error");
		}
	}

	/**Pay for the order.
	 * @param payment- pay info.
	 * @param isPrinted - the boolean value if need to print.
	 */
	public String processOrderCart(Payment pay) {
		OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
		LicenseMgrHomePage lmHomePg = LicenseMgrHomePage.getInstance();
		AlertDialogPage alert = AlertDialogPage.getInstance();
		OrmsPrintPopupPage printPg = OrmsPrintPopupPage.getInstance();
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

	private String getPrice(){
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
		String price = "$" + ormsOrderCartPg.getTotalPriceAmount();
		return price;
	}

}
