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
public class RecgovHelpFAQPage extends RecgovCommonPage {
	
	private static RecgovHelpFAQPage _instance=null;
	
	private RecgovHelpFAQPage(){}
	
	public static RecgovHelpFAQPage getInstance(){
		if(_instance==null){
			_instance=new RecgovHelpFAQPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", new RegularExpression("FAQ Index.*",false));
	}

}
