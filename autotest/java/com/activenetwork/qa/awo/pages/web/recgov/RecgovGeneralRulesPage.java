/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * this page will display after click 'General Rules' link in the bottom of rec.gov
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author asun
 * @Date  2012-4-1
 */
public class RecgovGeneralRulesPage extends RecgovCommonPage {

	private static RecgovGeneralRulesPage _instance=null;
	
	private RecgovGeneralRulesPage(){}
	
	public static RecgovGeneralRulesPage getInstance(){
		if(_instance==null){
			_instance=new RecgovGeneralRulesPage();
		}
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", new RegularExpression("General Rules.*",false));
	}

}
