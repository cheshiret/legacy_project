/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:For marina override rule pop up,it's a widget
 * @author ssong
 * @Date  May 12, 2013
 */
public class OrmsOverrideRulePinWidget extends DialogWidget{

	protected OrmsOverrideRulePinWidget(){
		super("Enter Pin #");
	}
	
	private static OrmsOverrideRulePinWidget _instance = null;
	
	public static OrmsOverrideRulePinWidget getInstance(){
		if(_instance == null){
			_instance = new OrmsOverrideRulePinWidget();
		}
		return _instance;
	}
	
	public void enterPIN(String pin) {
		logger.debug("Enter pin#"+pin);
		browser.setPasswordField(".id", new RegularExpression("(Marina|Base)RuleOverrideDialog-\\d+\\.pin",false), pin);
	}	
	
	public void setReason(String reason){
		browser.setTextArea(".id", new RegularExpression("(Marina|Base)RuleOverrideDialog-\\d+\\.note", false), reason);
	}
	
	public String getErrorMessage(){
		return browser.getObjectText(".className", "message msgerror");
	}
}
