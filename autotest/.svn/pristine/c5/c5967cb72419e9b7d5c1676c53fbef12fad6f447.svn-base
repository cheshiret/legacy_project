package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderDetailsCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:Pirvilege order history page
 * @Preconditions:
 * @SPEC:View privilege order history
 * @Task#:Auto-871
 * 
 * @author jwang8
 * @Date Feb 22, 2012
 */
public class LicMgrPrivilegeOrderHistoryPage extends
		LicMgrOrderDetailsCommonPage {

	public static LicMgrPrivilegeOrderHistoryPage instance = null;

	private LicMgrPrivilegeOrderHistoryPage() {
	};

	public static LicMgrPrivilegeOrderHistoryPage getInstance() {
		if (null == instance) {
			instance = new LicMgrPrivilegeOrderHistoryPage();
		}

		return instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "privilegeOrderHisList");
	}

	/**
	 * get order history list table.
	 * 
	 * @return List<OrderHistory> the order history info.
	 */
	public List<OrderHistory> getAllOrderHistoriesInfo() {
		List<OrderHistory> list = new ArrayList<OrderHistory>();
		OrderHistory orderHistory;
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"privilegeOrderHisList");
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the privilege order history table object with id='"
							+ "privilegeOrderHisList'");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		for (int i = 1; i < table.rowCount(); i++) {
			orderHistory = new OrderHistory();
			orderHistory.date = table.getCellValue(i, 0);
			orderHistory.transaction = table.getCellValue(i, 1);
			orderHistory.infoTransaction  = table.getCellValue(i, 2);
			orderHistory.transactionLocation = table.getCellValue(i, 3);
			orderHistory.user = table.getCellValue(i, 4);
			list.add(orderHistory);
		}
		Browser.unregister(objs);
		return list;
	}

	/**
	 * get the transaction info by transaction.
	 * 
	 * @param tranType
	 *            - the type of transaction.
	 * @return String - the transaction info.
	 */
	public String getTransactionInfoByTransactin(String tranType) {
		String transactionInfo = "";
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"privilegeOrderHisList");
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the privilege order history table object with id='"
							+ "privilegeOrderHisList'");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		for (int i = 0; i < table.rowCount(); i++) {
			if (table.getCellValue(i, 1).trim().equals(tranType)) {
				transactionInfo = table.getCellValue(i, 2);
			}
		}
		
		Browser.unregister(objs);
		return transactionInfo;
	}

	/**
	 * Compare order history info.
	 * 
	 * @param expectdOrder
	 *            - expected order info.
	 * @param voidReason
	 *            -void reason.
	 * @param undoVoidReason
	 *            -undo void reason.
	 * @return boolean- whether the history info is equal expected value.
	 */
	public boolean compareHistoryInfo(OrderInfo expectdOrder, String voidReason, String undoVoidReason) {
		boolean pass = true;
		List<OrderHistory> list = this.getAllOrderHistoriesInfo();
		for (int i = 0; i < list.size(); i++) {
			pass &= MiscFunctions.compareResult("Transaction type", expectdOrder.transactionList.get(i), list.get(i).transaction);
			pass &= MiscFunctions.compareResult("Transaction info", this.getTransactionInfo(expectdOrder, voidReason, undoVoidReason, i), list.get(i).infoTransaction);
			pass &= MiscFunctions.compareResult("Transaction location", expectdOrder.transactionLocation, list.get(i).transactionLocation);
			pass &= MiscFunctions.compareResult("Create User", expectdOrder.creationUser, list.get(i).user);
		}
		return pass;
	}

	/**
	 * Compare order history info.
	 * 
	 * @param expectdOrder
	 *            - expected order info.
	 * @param voidReason
	 *            -void reason.
	 * @param undoVoidReason
	 *            -undo void reason.
	 * @return boolean- whether the history info is equal expected value.
	 */
	public String getTransactionInfo(OrderInfo expectdOrder, String voidReason,
			String undoVoidReason, int index) {
		String expectedValue = "";
		if (expectdOrder.transactionList.get(index).equals("Original Purchase")) {
			expectedValue = "Privilege Number: " + expectdOrder.privilegeNum
					+ "";
		} else if (expectdOrder.transactionList.get(index).equals("Make Payment")) {
			expectedValue = expectdOrder.orderPrice + " of "
					+ expectdOrder.orderPaid + " VISA payment";
		} else if (expectdOrder.transactionList.get(index).equals("Reverse")) {
			expectedValue = "Privilege Numbers(Original): "
					+ expectdOrder.privilegeNum + " Reason: "
					+ voidReason.split("-")[1].trim() + "";
		} else if (expectdOrder.transactionList.get(index).equals("Reverse Fee")) {
			expectedValue = "";
		} else if (expectdOrder.transactionList.get(index)
				.equals("Reallocate Payment")) {
			expectedValue = "$0.00 of " + expectdOrder.orderPastPaid
					+ " VISA payment; Approved (" + expectdOrder.orderPastPaid
					+ ") VISA refund";
		} else if (expectdOrder.transactionList.get(index).equals("Undo Reverse")) {
			expectedValue = "Privilege Numbers(Original): "
					+ expectdOrder.privilegeNum + " Reason: " + undoVoidReason;
		}
		return expectedValue;
	}

	public List<OrderHistory> getHistoryInfoByTransaction(String transTyp) {
		List<OrderHistory> list = new ArrayList<OrderHistory>();
		OrderHistory orderHistory;
		String typeOnPage;
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"privilegeOrderHisList");
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the privilege order history table object with id='"
							+ "privilegeOrderHisList'");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		for (int i = 1; i < table.rowCount(); i++) {
			typeOnPage = table.getCellValue(i, 1);
			if (StringUtil.isEmpty(typeOnPage) || !typeOnPage.equals(transTyp)) {
				continue;
			}
			orderHistory = new OrderHistory();
			orderHistory.date = table.getCellValue(i, 0);
			orderHistory.transaction = typeOnPage;
			orderHistory.infoTransaction  = table.getCellValue(i, 2);
			orderHistory.transactionLocation = table.getCellValue(i, 3);
			orderHistory.user = table.getCellValue(i, 4);
			list.add(orderHistory);
		}
		return list;
	}

}
