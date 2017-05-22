package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsRestPinPage extends OrmsPage{

	static private OrmsRestPinPage _instance = null;
	
	protected OrmsRestPinPage(){
		
	}
	
	static public OrmsRestPinPage getInstance(){
		if (null == _instance) {
			_instance = new OrmsRestPinPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "OK");
	}
	
	/**
	 * Get reset pin message
	 * @return
	 */
	public String getMessage(){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".id",new RegularExpression("^*resetpin\\.success",false));
    	String errorMsg=objs[0].getProperty(".text");
    	
    	Browser.unregister(objs);
    	return errorMsg;
	}

}
