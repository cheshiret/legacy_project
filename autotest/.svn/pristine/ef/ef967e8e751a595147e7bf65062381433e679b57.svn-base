package com.activenetwork.qa.awo.pages.orms.operationManager;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OpMgrEndCallPage extends OrmsPage{
	/**
	 * Script Name   : <b>OpMgrEndCallPage</b>
	 * Generated     : <b>Oct 15, 2010 9:54:20 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/10/15
	 * @author Sara Wang
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OpMgrEndCallPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OpMgrEndCallPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OpMgrEndCallPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OpMgrEndCallPage();
		}

		return _instance;
	}
	
	private Property[] okToCancelCart(){
		return Property.toPropertyArray(".class", "Html.A", ".text", "OK to Cancel Call");
	}

	/** Determine if the Opervation Manager home page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(okToCancelCart());
	}

	/**Click the 'Ok to Cancel Call' link*/
	public void clickOkToCancelCall() {
		browser.clickGuiObject(okToCancelCart());
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
