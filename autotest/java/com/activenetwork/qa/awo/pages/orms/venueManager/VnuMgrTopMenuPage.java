/*
 * Created on Jun 4, 2009
 *
 */
package com.activenetwork.qa.awo.pages.orms.venueManager;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;


/**
 * @author QA
 *
 */
public class VnuMgrTopMenuPage extends VnuMgrCommonTopMenuPage {
	private static VnuMgrTopMenuPage _instance = null;

	protected VnuMgrTopMenuPage() {
	}

	public static VnuMgrTopMenuPage getInstance() {
		if (null == _instance) {
			_instance = new VnuMgrTopMenuPage();
		}

		return _instance;
	}

	/** Determine if the Venue Manager top-menu exists */
	public boolean exists() { // use Search: button as pageMark
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Search:");
	}

	public void selectSearchDropdownList(String item) {
		IHtmlObject[] objs = browser.getDropdownList(".id",
				"field_search_dropdown");
		ISelect select = (ISelect) objs[0];
		if (select.getSelectedText().equalsIgnoreCase(item)) {
			this.clickSearch();
		} else {
			((ISelect) objs[0]).select(item);
		}
		Browser.unregister(objs);
	}
}
