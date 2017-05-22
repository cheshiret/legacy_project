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
public class RecgovParticipatingPartnersPage extends RecgovCommonPage {

	private static RecgovParticipatingPartnersPage _instance=null;
	
	private RecgovParticipatingPartnersPage(){}
	
	public static RecgovParticipatingPartnersPage getInstance(){
		if(_instance==null){
			_instance=new RecgovParticipatingPartnersPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", new RegularExpression("^Participating Partners.*",false));
	}
	
	
	
}
