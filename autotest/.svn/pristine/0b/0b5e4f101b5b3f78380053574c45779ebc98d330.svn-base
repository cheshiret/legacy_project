/*
 * $Id$ 
 */

package com.activenetwork.qa.awo.pages.orms.financeManager.account;

import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;

/**
 * TODO: enter class description
 * 
 * @author raonqa
 */
public class FinMgrAccountSummaryViewPage extends FinanceManagerPage {

	/**
	 * Script Name   : FinMgrAccountSummaryViewPage
	 * Generated     : Feb 13, 2009 10:40:18 AM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2009/02/13
	 */
	private static FinMgrAccountSummaryViewPage _instance = null;

	public static FinMgrAccountSummaryViewPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrAccountSummaryViewPage();
		}

		return _instance;
	}

	protected FinMgrAccountSummaryViewPage() {
	}


	/** Determine if the account summary Summary page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.A",".text","Add New Account");
	}

	/**Click AddNewAccount*/
	public void clickAddNewAccount() {
		browser.clickGuiObject(".class","Html.A",".text","Add New Account");
	}

}
