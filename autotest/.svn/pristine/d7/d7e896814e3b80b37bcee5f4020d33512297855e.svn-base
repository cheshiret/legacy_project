/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: HF Web Sign Up page, shown after click Sign Up link on header bar or sign in with an account without profile
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Feb 19, 2013
 */
public class HFSignUpPage extends HFHeaderBar {
	private static HFSignUpPage _instance = null;

	public static HFSignUpPage getInstance() {
		if (null == _instance)
			_instance = new HFSignUpPage();

		return _instance;
	}
	
	protected HFSignUpPage() {
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".href", 
						new RegularExpression(".*createHFProfile\\.do", false));
	}
	
	public void clickLookUpExistingRecord() {
		browser.clickGuiObject(".class", "Html.A", ".href", 
						new RegularExpression(".*identifyCustomer\\.do$", false));
	}
	
	public void clickCreateNewRecord() {
		browser.clickGuiObject(".class", "Html.A", ".href", 
				new RegularExpression(".*createHFProfile\\.do$", false));
	}
	
	public String getPageTitleAndCaption() {
		return browser.getObjectText(".class", "Html.DIV", ".id", "pagetitle");
	}
}
