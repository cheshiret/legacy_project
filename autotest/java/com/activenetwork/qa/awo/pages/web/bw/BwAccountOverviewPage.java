package com.activenetwork.qa.awo.pages.web.bw;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;


/**
 * @author QA
 */
public class BwAccountOverviewPage extends UwpPage {

	private String pageMark = "Account Overview Keep track of your reservations and account information.";

	private static BwAccountOverviewPage _instance = null;

	public static BwAccountOverviewPage getInstance() {
		if (null == _instance)
			_instance = new BwAccountOverviewPage();
		
		return _instance;
	}
	
	/**Start of Property definitions**/
	/**End of Property definitions**/
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", pageMark);
	}

	/**
	 * Verify user account email displays as a link.
	 * @param email - user account
	 * @return true - found; false - not found
	 */
	public boolean verifyEmail(String email) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", email);
		boolean result = objs.length > 0;
		Browser.unregister(objs);
		return result;
	}
}
