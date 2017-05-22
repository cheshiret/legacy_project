/*
 * Created on Sep 8, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.reconciliation;

import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrReconciliationPage extends FinanceManagerPage {

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrReconciliationPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrReconciliationPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrReconciliationPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new FinMgrReconciliationPage();
		}
		return _instance;
	}


	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".text", new RegularExpression("Search", false), ".href",
						new RegularExpression("javascript:invokeAction(.*\"CCBatchList.do\",.*\"SearchCCBatches\",.*\"BatchWorker\",.*\"\".*)", false));
	}

	/**Click deposit tab*/
	public void clickDeposits() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deposits");
	}

	/**Click Deposit reconciliation*/
	public void clickDepositReconciliation() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deposit Reconciliation");
	}
}
