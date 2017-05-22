package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrPrivilegeQuestionConfirmWidget extends DialogWidget{
	private static LicMgrPrivilegeQuestionConfirmWidget _instance = null;
	
	protected LicMgrPrivilegeQuestionConfirmWidget() {
		
	}
	
	public static LicMgrPrivilegeQuestionConfirmWidget getInstance() {
		if (null == _instance){
			_instance = new LicMgrPrivilegeQuestionConfirmWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", new RegularExpression("The following privilege product.*", false));
	}	

}
