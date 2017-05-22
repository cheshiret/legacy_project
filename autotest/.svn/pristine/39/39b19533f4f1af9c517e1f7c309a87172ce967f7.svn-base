/*
 * $Id: InvMgrConfirmActionPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;

/**
 * @author jdu
 */
public class InvMgrConfirmActionPage extends InventoryManagerPage {

	/**
	 * Script Name   : ConfirmActionPage
	 * Generated     : Oct 3, 2006 11:50:31 AM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2006/10/03
	 */

	private static InvMgrConfirmActionPage _instance = null;

	public static InvMgrConfirmActionPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrConfirmActionPage();
		}

		return _instance;
	}

	protected InvMgrConfirmActionPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Confirm Action");
	}

	/** Click on OK link. */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/** Click on Cancel link. */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
}
