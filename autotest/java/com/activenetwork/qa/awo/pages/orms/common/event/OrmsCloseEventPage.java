/*
 * $Id: OrmsCloseEventPage.java 7017 2009-01-16 23:15:02Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.event;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author jdu
 */
public class OrmsCloseEventPage extends OrmsPage {

	/**
	 * Script Name   : OrmsCloseEventPage
	 * Generated     : Jan 25, 2008 2:42:40 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2008/01/25
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsCloseEventPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsCloseEventPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsCloseEventPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsCloseEventPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("Close Event$", false));
	}

	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**Click Cancel*/
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**Input Notes*/
	public void setNotes(String note) {
		browser.setTextArea(".id", "closure_notes", note);
	}

	/**Close Event*/
	public void closeEvent(String note) {
		this.setNotes(note);
		this.clickOK();
	}

	/**Check Warning exist or not*/
	public boolean checkWarning(String warn) {
		boolean result = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",".id", "statusMessages");
		if (objs.length > 0) {
			String str = objs[0].getProperty(".text").toString();
			if (warn.equalsIgnoreCase(str))
				result = true;
		}

		return result;
	}
	
	/**Click Undo close event button*/
	public void clickUndoCloseEvent(){
	  browser.clickGuiObject(".class","Html.A",".text","Undo Close");
	}
}
