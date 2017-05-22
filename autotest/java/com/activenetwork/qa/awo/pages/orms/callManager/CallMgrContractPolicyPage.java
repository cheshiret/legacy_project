/*
 * $Id: CallMgrContractPolicyPage.java 7017 2009-01-16 23:15:02Z i2k-net\raonqa $
 */

package com.activenetwork.qa.awo.pages.orms.callManager;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;

/**
 * @author CGuo
 */
public class CallMgrContractPolicyPage extends OrmsPage {

	/**
	 * Script Name   : <b>CallMgrContractPolicyPage</b>
	 * Generated     : <b>Oct 12, 2004 1:10:38 PM</b>
	 * Description   : XDE Tester Script
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/10/12
	 * @author CGuo
	 */
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private CallMgrContractPolicyPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected CallMgrContractPolicyPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public CallMgrContractPolicyPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new CallMgrContractPolicyPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Accept Policy");
	}

	/** Click the button to accept the policy */
	public void clickAcceptPolicy() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Accept Policy");
	}

	/**Click the Decline Policy link*/
	public void clickDeclinePolicy() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Decline Policy");
	}
}
