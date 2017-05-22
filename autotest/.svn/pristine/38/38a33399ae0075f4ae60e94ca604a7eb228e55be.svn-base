package com.activenetwork.qa.awo.pages.orms.callManager;

import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Apr 11, 2012
 */
public class CallMgrAutoSelectDiscountPage extends CallMgrCommonTopMenuPage {

	static private CallMgrAutoSelectDiscountPage _instance = null;
	
	static public CallMgrAutoSelectDiscountPage getInstance(){
		if (null == _instance) {
			_instance = new CallMgrAutoSelectDiscountPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",new RegularExpression("^( )*Available Discounts.*",false));
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
}
