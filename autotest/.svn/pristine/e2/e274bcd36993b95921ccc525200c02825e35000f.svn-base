/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.financial;

import com.activenetwork.qa.testapi.PageNotFoundException;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date Oct 23, 2012
 */
public class OrmsVoucherExpiryConfirmActionPage extends OrmsVoucherDetailsPage {

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsVoucherExpiryConfirmActionPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsVoucherExpiryConfirmActionPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsVoucherExpiryConfirmActionPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsVoucherExpiryConfirmActionPage();
		}

		return _instance;
	}

	/**
	 * Check this page is exists
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Confirm Action");
//		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
//				"Confirm Action");
	}
	
	public void inputExpiryReason(String text)
	{
		browser.setTextArea(".id", "reason_note", text);
	}
	
	public void clickOK()
	{
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

}
