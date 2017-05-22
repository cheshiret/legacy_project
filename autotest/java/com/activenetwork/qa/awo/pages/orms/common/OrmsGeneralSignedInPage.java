package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * This is the page class to represent the general signed in page in Orms product
 * @author JDU
 *
 */
public class OrmsGeneralSignedInPage extends OrmsPage {
	static private OrmsGeneralSignedInPage _instance = null;

	protected OrmsGeneralSignedInPage() {
	}
	
	
	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsGeneralSignedInPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsGeneralSignedInPage();
		}

		return _instance;
	}
	@Override
	public boolean exists() {
		//james[20130912] remove "menu_anchor_no_background" as it appears in signin page
		RegularExpression pattern=new RegularExpression("^(menu_anchor|table_anchor|menuContainer)$",false);
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".className",pattern);
	}

}
