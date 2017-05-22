package com.activenetwork.qa.awo.pages.orms.common.financial;

import com.activenetwork.qa.awo.pages.OrmsPage;

/**
 * Description   : XDE Tester Script
 * @author CGuo
 */
public class OrmsApproveRefundPage extends OrmsPage {

	/**
	 * Script Name   : <b>FldMgrPaymentDetailPage</b>
	 * Generated     : <b>Sep 30, 2004 6:34:21 PM</b>
	 * Description   : XDE Tester Script
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/09/30
	 * @author CGuo
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsApproveRefundPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsApproveRefundPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsApproveRefundPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsApproveRefundPage();
		}

		return _instance;
	}

	/**
	 * Use Ok Button as page mark check whether this page exists
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "OK");
	}

	/**
	 * Input note information
	 * @param notes
	 */
	public void setNote(String notes) {
		browser.setTextArea(".id", "new_note", notes);
	}

	/**
	 * This method is used click Cancel Button
	 *
	 */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**
	 * This method is used to click Ok Button
	 *
	 */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

}
