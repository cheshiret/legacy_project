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
public class RecgovNewLookFeaturesPage extends RecgovCommonPage {

	private static RecgovNewLookFeaturesPage _instance=null;
	
	private RecgovNewLookFeaturesPage(){}
	
	public static RecgovNewLookFeaturesPage getInstance(){
		if(_instance==null){
			_instance=new RecgovNewLookFeaturesPage();
		}
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", new RegularExpression("Recreation\\.gov \ufffd New Look! New Features!.*",false));
	}

}
