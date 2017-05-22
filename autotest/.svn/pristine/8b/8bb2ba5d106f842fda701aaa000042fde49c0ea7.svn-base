package com.activenetwork.qa.awo.pages.orms.financeManager;

import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class FinMgrTopMenuPage extends FinMgrCommonTopMenuPage {
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrTopMenuPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrTopMenuPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrTopMenuPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new FinMgrTopMenuPage();
		}

		return _instance;
	}

	public boolean exists() {
		RegularExpression rex = new RegularExpression("finance\\.rightmenu\\.id\\.[0-9]+", false);
		return browser.checkHtmlObjectExists(".id", rex, ".text", "Home");
	}
}
