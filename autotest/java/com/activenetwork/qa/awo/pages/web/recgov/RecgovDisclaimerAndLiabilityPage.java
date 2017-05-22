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
public class RecgovDisclaimerAndLiabilityPage extends RecgovCommonPage {
	
	private static RecgovDisclaimerAndLiabilityPage _instance=null;
	
	private RecgovDisclaimerAndLiabilityPage(){}
	
	public static RecgovDisclaimerAndLiabilityPage getInstance(){
		if(_instance==null){
			_instance=new RecgovDisclaimerAndLiabilityPage();
		}
		return _instance;
	}
	
	

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", new RegularExpression("Disclaimer and Liability Notice.*",false));
	}

}
