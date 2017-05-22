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
 * @Date  2012-3-31
 */
public class RecgovContractUsPage extends RecgovCommonPage {
	
	private static RecgovContractUsPage _instance=null;
	
	private RecgovContractUsPage(){}
	
	public static RecgovContractUsPage getInstance(){
		if(_instance==null){
			_instance=new RecgovContractUsPage();
		}
		return _instance;
	}
	
	@Override
	public boolean exists(){
	   return browser.checkHtmlObjectExists(".class", "Html.TD", ".text", new RegularExpression("Contact us By Phone.*",false));
	}

	
	public void clickMapsLink() {
		browser.clickGuiObject(".class","Html.A", ".text", "Maps");
	}
}
