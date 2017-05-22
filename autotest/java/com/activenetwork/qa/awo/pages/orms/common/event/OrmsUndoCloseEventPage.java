/*
 * $Id: OrmsUndoCloseEventPage.java 7017 2009-01-16 23:15:02Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.event;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author jdu
 */
public class OrmsUndoCloseEventPage extends OrmsPage {

	/**
	 * Script Name   : OrmsUndoCloseEventPage
	 * Generated     : Jan 25, 2008 4:17:41 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2008/01/25
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsUndoCloseEventPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsUndoCloseEventPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsUndoCloseEventPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsUndoCloseEventPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("Undo Close Event$", false));
	}

	/**Click OK*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**Click Cancel*/
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**
	 * Input unndoclose note
	 * @param note
	 */
	public void setUndoCloseNote(String note) {
		browser.setTextArea(".id", "undoclosure_notes", note);
	}

	/**
	 * Undo close event
	 * @param note
	 */
	public void undoCloseEvent(String note) {
		this.setUndoCloseNote(note);
		this.clickOK();
	}

}
