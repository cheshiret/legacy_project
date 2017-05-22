/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * this page will display after click 'Accessibility' link in the bottom of rec.gov
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author asun
 * @Date  2012-4-1
 */
public class RecgovAccessibilityPage extends RecgovCommonPage {

	private static RecgovAccessibilityPage _instance=null;
	
	private RecgovAccessibilityPage(){}
	
	public static RecgovAccessibilityPage getInstance(){
		if(_instance==null){
			_instance=new RecgovAccessibilityPage();
		}
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", new RegularExpression("^Accessibility.*",false));
	}

}
