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
public class RecgovPrivacyPolicyPage extends RecgovCommonPage {
	
	private static RecgovPrivacyPolicyPage _instance=null;
	
	private RecgovPrivacyPolicyPage(){}
	
	public static RecgovPrivacyPolicyPage getInstance(){
		if(_instance==null){
			_instance=new RecgovPrivacyPolicyPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", new RegularExpression("Privacy Policy.*Recreation\\.gov Privacy Policy.*",false));
	}
	
}
