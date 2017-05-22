/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.callManager;



import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class CallMgrPrivilegeQuestionConfirmWidget extends DialogWidget{
	private static CallMgrPrivilegeQuestionConfirmWidget _instance = null;
	
	protected CallMgrPrivilegeQuestionConfirmWidget() {
		
	}
	
	public static CallMgrPrivilegeQuestionConfirmWidget getInstance() {
		if (null == _instance){
			_instance = new CallMgrPrivilegeQuestionConfirmWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", new RegularExpression("The following privilege product.*", false));
	}	

}
