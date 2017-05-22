package com.activenetwork.qa.awo.pages.orms.callManager;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author raonqa
 */
public class CallMgrEndCallPage extends OrmsPage {
	/**
	 * Script Name   : <b>CallMgrEndCallPage</b>
	 * Generated     : <b>Oct 29, 2008 2:15:20 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2008/10/29
	 * @author raonqa
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private CallMgrEndCallPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected CallMgrEndCallPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public CallMgrEndCallPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new CallMgrEndCallPage();
		}

		return _instance;
	}

	/** Determine if the CallManager home page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				new RegularExpression("Ok to Cancel Call", false));
	}

	/**Click the Ok to Cancel Call link*/
	public void clickOkToCancelCall() {
		browser.clickGuiObject(".class", "Html.A", ".text",new RegularExpression("Ok to Cancel Call", false));
	}

	/**Click the Don't Cancel link*/
	public void clickDontCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Don't Cancel");
	}

	/**Select the other reason radio button*/
	public void selectOtherReason() {
		browser.selectRadioButton(".id", "REASON_OTHER");
	}

	/**
	 * Select the Cancel reason radio button
	 * @param reasonID -- The reason will be selected
	 */
	public void clickOnCancelReason(String reasonID) {
		browser.selectRadioButton(".value", reasonID);
	}

	/**Select the Cancel Reason radio button*/
	public void clickCancelReason() {
		browser.selectRadioButton(".value", "10");
	}

	/**Set reason description*/
	public void setOtherReasonDescription(String description) {
		browser.setTextArea(".id", "description", description);
	}

	/**
	 * Select the reason 
	 * @param reason -- the reason will be selected
	 */
	public void selectReason(String reason) {
		IHtmlObject[] objs = browser.getTableTestObject(".text",	new RegularExpression("Other$", false));
		int row = ((IHtmlTable)objs[0]).findRow(0, reason);
		Browser.unregister(objs);
		selectReason(row + 1);
	}

	/**
	 * Select the reason by the index
	 * @param index -- The reason will be selected
	 */
	public void selectReason(int index) {
		browser.selectRadioButton(".id", "reason", ".value", index + "");
	}
}
