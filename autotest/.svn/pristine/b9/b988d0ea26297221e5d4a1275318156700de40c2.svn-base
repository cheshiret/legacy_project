/*
 * Created on Sep 8, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.reconciliation;

import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrDepositReconciliationPage extends FinanceManagerPage {

	protected FinMgrDepositReconciliationPage() {
	}

	private static FinMgrDepositReconciliationPage _instance = null;

	public static FinMgrDepositReconciliationPage getInstance() {
		if (null == _instance)
			_instance = new FinMgrDepositReconciliationPage();
		return _instance;
	}

	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".text", new RegularExpression("Search", false), ".href",
						new RegularExpression("javascript:invokeAction(.*\"DepositReconciliationList.do\",.*\"SearchDepositReconciliations\",.*\"DepositsWorker\",.*\"\".*)", false));
	}

	/**click RunDepositReconciliation button */
	public void clickRunDepositReconciliation() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(".*Run Deposit Reconciliation", false));
	}
	
	public String getMessage() {
		return browser.getObjectText(".class","Html.TABLE",".id","statusMessages");
	}
}
