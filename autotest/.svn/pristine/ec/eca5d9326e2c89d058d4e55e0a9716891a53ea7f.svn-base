/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author asun
 * @Date  2012-4-1
 */
public class RecgovFAQSMapsPage extends RecgovCommonPage {
	
	private static RecgovFAQSMapsPage _instance=null;
	
	private RecgovFAQSMapsPage(){}
	
	public static RecgovFAQSMapsPage getInstance(){
		if(_instance==null){
			_instance=new RecgovFAQSMapsPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", new RegularExpression("FAQS - Maps.*",false));
	}

}
