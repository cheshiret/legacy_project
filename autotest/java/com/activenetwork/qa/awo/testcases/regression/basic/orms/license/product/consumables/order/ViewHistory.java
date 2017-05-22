/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.consumables.order;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderHistory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrConsumableOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: verify history info after purchase transaction and void transaction
 * @Preconditions: need POS product could purchase
 *                 need active customer 
 * @LinkSetUp:d_hf_add_cust_profile 770 <TEST-Advanced1,QA-Advanced1>
 *            d_hf_addconsu_prd 70 <POSForVoid>
 * @SPEC: 	View Subscription history [TC:024155] 
 * @Task#: AUTO-1456
 * @author szhou
 * @Date  Mar 27, 2013
 */
public class ViewHistory extends LicenseManagerTestCase {
	private OrderHistory expectHistory = new OrderHistory();
	private String voidReason, voidNote;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// purchase a consumable
		lm.makeConsumableOrderToCartFromCustomerTopMenu(cust, consumable);
		String ordNum = lm.processOrderCart(pay);

		/* go to order history page */
		lm.gotoConsumableOrderDetailsPage(ordNum);
		lm.gotoConsumableOrderHistoryPage();
		/* verify order history */
		this.verifyHistory(expectHistory,TRANNAME_PURCHASE_CONSUMABLE);

		// void order
		lm.voidConsumableOrderToCart(voidReason, voidNote);
		lm.processOrderCart(pay);
		/* go to order history page */
		lm.gotoConsumableOrderDetailsPage(ordNum);
		lm.gotoConsumableOrderHistoryPage();

		/* verify order history */
		this.changeHistory();
		this.verifyHistory(expectHistory,TRANNAME_VOID_CONSUMABLE);

		lm.logOutLicenseManager();

	}

	private void verifyHistory(OrderHistory history,String transtype) {
		LicMgrConsumableOrderHistoryPage historyPage = LicMgrConsumableOrderHistoryPage
				.getInstance();
		LicMgrConsumableOrderDetailsPage consumOrderDetailsPage = LicMgrConsumableOrderDetailsPage
				.getInstance();

		if (historyPage.getHistoryInfos().size() < 1) {
			throw new ErrorOnPageException(
					"There is no history record,please check...");
		}
		
		if(transtype.equals(TRANNAME_PURCHASE_CONSUMABLE)){
			this.compareHistoryData(history,historyPage.getHistoryInfos().get(0));
		}else if (transtype.equals(TRANNAME_VOID_CONSUMABLE)){
			this.compareHistoryData(history,historyPage.getHistoryInfos().get(2));
		}
		
		historyPage.clickOK();
		ajax.waitLoading();
		consumOrderDetailsPage.waitLoading();
		
	}

	private void compareHistoryData(OrderHistory history,OrderHistory actually) {
		
		boolean result = true;
		result = MiscFunctions.compareResult("Transaction",
				history.transaction, actually.transaction);
		result &= MiscFunctions.compareResult(
				"Information at time of transaction", history.infoTransaction,
				actually.infoTransaction);
		result &= MiscFunctions.compareResult("Transaction Location",
				history.transactionLocation, actually.transactionLocation);
		result &= MiscFunctions.compareResult("User", history.user.replaceAll(" ", StringUtil.EMPTY),
				actually.user.replaceAll(" ", StringUtil.EMPTY));

		if (!result) {
			throw new ErrorOnPageException(
					"Data comparing is not correct,please check the log file...");
		}

		
	}
	
	private void changeHistory(){
		expectHistory.transaction = TRANNAME_VOID_CONSUMABLE;
		expectHistory.infoTransaction = "Reason: "+voidReason+" Note: "+voidNote;
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		consumable.name = "WL2 - POSForVoid";
		consumable.licenseYear = lm.getFiscalYear(schema);

		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "19850224";
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "111111";
		cust.residencyStatus = "Non Resident";
		cust.lName = "TEST-Advanced1";
		cust.fName = "QA-Advanced1";

		expectHistory.transaction = TRANNAME_PURCHASE_CONSUMABLE;
		expectHistory.infoTransaction = "1 of (" + consumable.licenseYear + ")"
				+ consumable.name;
		expectHistory.transactionLocation = "WAL-MART";
		expectHistory.user = DataBaseFunctions.getLoginUserName(login.userName);

		voidReason = "12 - Payment Not Received After Printing";
		voidNote = "Automation Sanity Test";

	}

}
