package com.activenetwork.qa.awo.pages.orms.common.camping;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * Description   : OrmsSiteReservedSitesSummaryPage
 * @author Bzhang
 * @Date 2011\5\30
 */
public class OrmsSiteReservedSitesSummaryPage extends OrmsPage {

	static private OrmsSiteReservedSitesSummaryPage _instance = null;

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsSiteReservedSitesSummaryPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsSiteReservedSitesSummaryPage();
		}
		return _instance;
	}

	/** Determine if the page object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("^Event ID.*",false));
	}

	public void clickOKBtn() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancelBtn() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	
}
