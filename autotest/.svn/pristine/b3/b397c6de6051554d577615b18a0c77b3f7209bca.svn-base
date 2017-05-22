/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.hf;

/**
 * @Description: It is for HF home page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Feb 19, 2013
 */
public class HFHomePage extends HFHeaderBar {
	private static HFHomePage _instance = null;

	public static HFHomePage getInstance() {
		if (null == _instance)
			_instance = new HFHomePage();

		return _instance;
	}
	
	protected HFHomePage() {
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "homepage");
	}
}
