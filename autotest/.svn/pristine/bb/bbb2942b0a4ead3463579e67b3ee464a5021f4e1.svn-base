package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;

public class UwpPurchaseNotCompleted extends UwpPage{

	private static UwpPurchaseNotCompleted _instance = null;

	public static UwpPurchaseNotCompleted getInstance() {
		if (null == _instance)
			_instance = new UwpPurchaseNotCompleted();

		return _instance;
	}

	private UwpPurchaseNotCompleted() {}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", "One or more purchases not completed!");
	}

	/**
	 * get error message.
	 * @return error message.
	 */
	public String getErrorMessager(){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".className", "msg error");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find error message DIV object.");
		}
		
		String msg = objs[0].getProperty(".text");
		Browser.unregister(objs);
		
		return msg;
	}
}
