/*
 * $Id: FinMgrTaxAssignmentsPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.financeManager.tax;

import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;

/**
 * @author CGuo
 */
public class FinMgrTaxAssignmentsPage extends FinanceManagerPage {

	/**
	 * Script Name   : FinMgrTaxAssignmentsPage
	 * Generated     : Dec 17, 2004 2:40:13 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/12/17
	 */
	private static FinMgrTaxAssignmentsPage _instance = null;

	public static FinMgrTaxAssignmentsPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrTaxAssignmentsPage();
		}

		return _instance;
	}

	protected FinMgrTaxAssignmentsPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists("", "", "", "");
	}

	public void selectTaxName(String taxName) {
		//		MiscFunctions.selectItem(List_tax_type_id(), taxName);
	}

	public void selectProductGroup(String productGroup) {
		//		MiscFunctions.selectItem(List_ProductGroupId(),productGroup );
	}

	public void selectProduct(String product) {
		//		MiscFunctions.selectItem(List_ProductId(),product);
	}

	public void clickOK() {
		//		Link_OK().click();
	}

	public void clickApply() {
		//		Link_Apply().click();
	}

	public String getTaxAssignmentID() {

		return null;

	}

}
