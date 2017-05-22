/*
 * Created on Sep 8, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.reconciliation;

import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrDepositPage extends FinanceManagerPage {

	protected FinMgrDepositPage() {
	}

	private static FinMgrDepositPage _instance = null;

	public static FinMgrDepositPage getInstance() {
		if (null == _instance)
			_instance = new FinMgrDepositPage();
		return _instance;
	}

	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".text","Go",".href",new RegularExpression("javascript:invokeAction(.*\"DepositListForReconciliation.do\",.*\"GetDepositList\",.*\"DepositsWorker\",.*\"\".*)", false));
	}

	/**Click Go button*/
	public void clickGo() {
		browser.clickGuiObject(".text","Search",".href", new RegularExpression("javascript:invokeAction(.*\"DepositListForReconciliation.do\",.*\"GetDepositList\",.*\"DepositsWorker\",.*\"\".*)", false));
	}

	/**
	 * Retrieven unfully reconciliation deposit ID
	 * @return--unfully reconciliation deposit ID
	 */
	public String[] getUnFullyReconcilDepositID() {
		RegularExpression rex = new RegularExpression("Deposit ID Status Total Amount.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",".text", rex);
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		String[] depositsID = { "" };
		for (int i = 0; i < tableGrid.rowCount(); i++) {
			if (!tableGrid.getCellValue(i + 1, 1).toString().equalsIgnoreCase("Fully Reconciled")) {
				depositsID[0] = tableGrid.getCellValue(i + 1, 0).toString();
				break;
			}
		}
		return depositsID;
	}

	/**
	 * Veirfy one reconciliatin is success or not by deposit ID
	 * @param depositID
	 * @return
	 */
	public boolean isReconciliationSuccess(String depositID) {
		RegularExpression rex = new RegularExpression("Deposit ID Status Total Amount.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",".text", rex);
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		for (int i = 0; i < tableGrid.rowCount(); i++) {
			if (tableGrid.getCellValue(i, 0).toString().equals(depositID)) {
				if (tableGrid.getCellValue(i, 1).toString().equalsIgnoreCase("Fully Reconciled"))
					return true;
				else
					return false;

			}
		}
		return false;
	}

}
