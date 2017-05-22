/*
 * $Id: OrmsCancelEventPage.java 7017 2009-01-16 23:15:02Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.event;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author jdu
 */
public class OrmsCancelEventPage extends OrmsPage {

	/**
	 * Script Name   : OrmsCancelEventPage
	 * Generated     : Jan 25, 2008 2:11:33 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2008/01/25
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsCancelEventPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsCancelEventPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsCancelEventPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsCancelEventPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("Cancel Event$", false));
	}

	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**Click Cancel Button*/
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**Input Reason*/
	public void setReason(String reason) {
		browser.selectDropdownList(".id", "reasonID", reason,true);
	}

	/**Input Cancel Note*/
	public void setCancelNote(String note) {
		browser.setTextArea(".id", "cancelNotes", note);
	}

	/**Cancel event*/
	public void cancelEvent(String reason, String note) {
		selectAllReservationsToBeAutomaticallyCancelled();
		setReason(reason);
		setCancelNote(note);
		clickOK();
	}
	
	public void selectAllReservationsToBeAutomaticallyCancelled() {
		IHtmlObject[] frames=getTransactionFrame();
//		browser.selectCheckBox(".name","all_slct",0,frames != null ? frames[0] : null);
		browser.selectCheckBox(".name","all_slct");
		Browser.unregister(frames);
	}

}
