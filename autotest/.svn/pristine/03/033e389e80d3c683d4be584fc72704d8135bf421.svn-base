package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.pages.OrmsPage;


/**
 * @author QA
 */
public class OrmsLogoutWarningPage extends OrmsPage {
	/**
	 * Script Name   : <b>FldMgrLogoutWarningPage</b>
	 * Generated     : <b>Nov 5, 2008 2:43:24 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2008/11/05
	 * @author QA
	 */
  	/**
	 * A handle to the unique Singleton instance.
	 */
	private static OrmsLogoutWarningPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsLogoutWarningPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	public static OrmsLogoutWarningPage getInstance() {
		if (null == _instance)
			_instance = new OrmsLogoutWarningPage();
		return _instance;
	}

	/**Check the object whether is exists*/
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text","Close Financial Session");
	}

	/**Click Leave Financial Session Open And Sign Out link*/
	public void clickLeaveFinSesOpenAndSignOut() {
		browser.clickGuiObject(".class", "Html.A",
				".text", "Leave Financial Session Open And Sign Out");
	}

	/**Click Close Financial Session link*/
	public void clickCloseFinSession() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Close Financial Session");
	}
	
}
