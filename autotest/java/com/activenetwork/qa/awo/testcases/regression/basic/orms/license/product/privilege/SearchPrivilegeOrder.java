package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.common.popup.OrmsPrintPopupPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 *
 * @Description: This case is used to verify searching privilege order.
 * @Preconditions:
 * @SPEC: Search privilege order
 * @Task#: AUTO-871
 *
 * @author Jwang8
 * @Date  Feb 27, 2012
 * @defect DEFECT-33487 and DEFECT-33489.
 */

public class SearchPrivilegeOrder extends LicenseManagerTestCase{
	private OrderInfo orderInfo = new OrderInfo();
	private OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//Make privilege product to cart.
		lm.makePrivilegeToCartFromCustomerTopMenu(cust, privilege);
		orderInfo.orderPrice = "$" + ormsOrderCartPg.getTotalPriceAmount();
		//Pay process.
		orderInfo.orderNum = this.processOrderCart(pay);
		if(orderInfo.orderNum .contains(" ")){
			orderInfo.orderNum  = orderInfo.orderNum .split(" ")[0];
		}
		orderInfo.value =orderInfo.orderNum;
		//go to privilege order search page.
		lm.gotoPrivilegeOrderSearchPage();
		//Search privilege order by different criteria.
		this.verifySearchPrivilege();
		//Log out license manager.
		lm.logOutLicenseManager();
	}
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		privilege.purchasingName = "adv-LocationDailySales";
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "01 - Operator Error";
		privilege.operateNote = "Automation-test";

		cust.identifier.identifierType = "Green Card";;
		cust.identifier.identifierNum = "AutoBasic000015";
		cust.identifier.country = "Canada";
		cust.customerClass = "Individual";
		cust.lName = "TEST-Basic15";
		cust.fName ="QA-Basic15";
		cust.residencyStatus="Non Resident";

		orderInfo.orderType = "Order #";
		orderInfo.value = orderInfo.orderNum;
		orderInfo.purchaseType = "Original";
		orderInfo.residencyStatus = cust.residencyStatus;
		orderInfo.orderFromDate = DateFunctions.getToday();
		orderInfo.orderToDate = DateFunctions.getToday();
		orderInfo.operatorFirstName = cust.fName;
		orderInfo.operatorLastName = cust.lName;
		orderInfo.balance = "$"+"0.00";
		orderInfo.verificationStatus = "Not Applicable";
		orderInfo.saleLocation = login.location.split("/")[1];
		orderInfo.numOfItems = privilege.qty;
	}
	/**Pay for the order.
	 * @param payment- pay info.
	 * @param isPrinted - the boolean value if need to print.
	 */
	public String processOrderCart(Payment pay) {
		OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
		LicenseMgrHomePage lmHomePg = LicenseMgrHomePage.getInstance();
		OrmsPrintPopupPage printPg = OrmsPrintPopupPage.getInstance();

		ConfirmDialogPage confirmDialog = ConfirmDialogPage.getInstance();

		logger.info("Process order cart.");

		lm.processOrderToOrderSummary(pay);

		String ordID = lmOrdSumPg.getAllOrdNums();
		logger.info("Processed order: " + ordID);

        orderInfo.receiptNum = lmOrdSumPg.getReceiptNum();

		logger.info("Receipt number: " + orderInfo.receiptNum);
		Object page1 = browser.waitExists(printPg,lmOrdSumPg);
		if(printPg == page1){
			printPg.clickSuccess();
			ajax.waitLoading();
		}
		lmOrdSumPg.waitLoading();
		lmOrdSumPg.clickFinishButton();

		Object page = browser.waitExists(confirmDialog, lmHomePg);
		if(confirmDialog == page) {
			confirmDialog.clickOK();
			lmHomePg.waitLoading();
		}
		return ordID;
	}
	/**
	 * Verify search privilege order result.
	 */
   private void verifySearchPrivilege(){
	   LicMgrPrivilegeOrderSearchPage privlilegeSearchPg = LicMgrPrivilegeOrderSearchPage.getInstance();
	   privlilegeSearchPg.searchPrivilegeOrder(orderInfo);
	   privlilegeSearchPg.verifySearchPrivilegeOrderResult(orderInfo);

	   OrderInfo searchOrder = new OrderInfo();

	   searchOrder.orderType="Order #";
	   searchOrder.value = orderInfo.orderNum;
	   privlilegeSearchPg.searchPrivilegeOrder(searchOrder);
	   privlilegeSearchPg.verifySearchPrivilegeOrderResult(searchOrder);

	   privlilegeSearchPg.clearSearchCriteria();
	   searchOrder.orderType ="Receipt #";
	   searchOrder.value = orderInfo.receiptNum;
	   privlilegeSearchPg.searchPrivilegeOrder(searchOrder);
	   privlilegeSearchPg.verifySearchPrivilegeOrderResult(searchOrder);

	   privlilegeSearchPg.clearSearchCriteria();
	   searchOrder.value = "";
	   searchOrder.orderFromDate = DateFunctions.getToday();
	   privlilegeSearchPg.searchPrivilegeOrder(searchOrder);
	   privlilegeSearchPg.verifySearchPrivilegeOrderResult(searchOrder);
   }

}
