package com.activenetwork.qa.awo.pages.orms.common.financial;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * Description   : XDE Tester Script
 * @author CGuo
 */
public class OrmsVoidPaymentPage extends OrmsPage {

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
	static private OrmsVoidPaymentPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsVoidPaymentPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsVoidPaymentPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsVoidPaymentPage();
		}

		return _instance;
	}

	/** 
	 * Determine if the page object exists 
	 */
	public boolean exists() {
		//use OK button as the pageMark
		//.href: javascript:invokeActionValidate(%20"PaymentDetail.do",%20"ReversePaymentAndShowList",%20"PayRefundWorker",%20"132191996"%20%20)
		//return browser.checkTestObjectExists(".class","Html.LABEL",".href",new RegularExpression("\"PaymentDetail\\.do\",.+\"ReversePaymentAndShowList\"",false));
	  return browser.checkHtmlObjectExists(".class","Html.A",".href",new RegularExpression("\"PaymentDetail\\.do\",.+\"ReversePaymentAndShowList\"",false));
	}

	/**
	 * Input Void payment Note
	 * @param notes
	 */
	public void setVoidNote(String notes) {
		browser.setTextArea(".id", "payrefdetailaddnotes", notes);
	}

	/**
	 * Click Cancel Button
	 *
	 */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**
	 * Click Ok Button
	 *
	 */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

}
