/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.hf;

/**
 * @Description: The page will be shown when there is no licenses available to purchase or the account don't have profile after clicking Purchase a License tab
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Apr 16, 2013
 */
public class HFLicensesNotAvaliablePage extends HFHeaderBar {
	private static HFLicensesNotAvaliablePage _instance = null;

	public static HFLicensesNotAvaliablePage getInstance() {
		if (null == _instance)
			_instance = new HFLicensesNotAvaliablePage();

		return _instance;
	}
	
	protected HFLicensesNotAvaliablePage() {
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".className", "messagePanel", ".id", "onlyMsg");
	}
	
	public String getMessages() {
		return browser.getObjectText(".className", "messagePanel", ".id", "onlyMsg");
	}
	
	public void clickCreateHFRecord() {
		browser.clickGuiObject(".class", "Html.A", ".href", "/createHFProfile.do");
	}
	
	public boolean isHomePageLinkExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "homepage");
	}
}
