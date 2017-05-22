package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.testapi.util.Property;

/**
 * 
 * @Description: In web hunting fishing website, in sign in mode, after click Sign In link, this page will display
 * with three links.
 * URL:hfXX-uwppl-torqaX.dev.activenetwork.com/selectOptions.do
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jun 5, 2014
 */
public class HFSignInNavigationPage extends HFHeaderBar {
	static class SingletonHolder {
		protected static HFSignInNavigationPage _instance = new HFSignInNavigationPage();
	}

	protected HFSignInNavigationPage() {
	}

	public static HFSignInNavigationPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] alreadyRegisteredWithAWin(){
		return Property.concatPropertyArray(a(), ".href", "/memberSignInDisplay.do");
	}
	
	protected Property[] notRegisteredWithAWin(){
		return Property.concatPropertyArray(a(), ".href", "/identifyCustomer.do");
	}
	
	protected Property[] applyForYourWinOnline(){
		return Property.concatPropertyArray(a(), ".href", "/createHFProfile.do");
	}
	/** Page Object Property Definition END */
	public boolean exists() {
		return browser.checkHtmlObjectExists(applyForYourWinOnline());
	}
	
	public void clickAlreadyRegisteredWithAWin(){
		browser.clickGuiObject(alreadyRegisteredWithAWin());
	}
	
	public void clickNotRegisteredWithAWin(){
		browser.clickGuiObject(notRegisteredWithAWin());
	}
	
	public void clickApplyForYourWinOnline(){
		browser.clickGuiObject(applyForYourWinOnline());
	}
}
