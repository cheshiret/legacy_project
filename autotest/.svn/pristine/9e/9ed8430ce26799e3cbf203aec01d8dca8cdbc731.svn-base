package com.activenetwork.qa.awo.pages.web.bw;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;

/**
 * @author QA
 */
public class BwWelcomePage extends UwpPage {
	String pageMark = "Please Sign In to access the functionality available for you";

	private static BwWelcomePage _instance = null;

	public static BwWelcomePage getInstance() {
		if (null == _instance)
			_instance = new BwWelcomePage();

		return _instance;
	}

	public boolean exists() {
		if (!browser.exists())
			return false;

		browser.sync();
		IHtmlObject[] bto = browser.getHtmlObject(".class", "Html.DIV", ".id", "contentArea");
		String text = bto[0].getProperty(".text");

		return text.indexOf(pageMark) > 0;
	}

	/**
	 * Click on sign in link.
	 */
	public void clickSignIn() {
		browser.clickGuiObject(".id", "signIn",".class","Html.A");
	}
}
