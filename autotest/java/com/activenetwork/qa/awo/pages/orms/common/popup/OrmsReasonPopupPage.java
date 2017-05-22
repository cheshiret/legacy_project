package com.activenetwork.qa.awo.pages.orms.common.popup;

import com.activenetwork.qa.testapi.page.HtmlPopupPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsReasonPopupPage extends HtmlPopupPage {
	
	private static OrmsReasonPopupPage _instance;
	
	protected OrmsReasonPopupPage () {
		super("title","Enter Closure Comments");
	}
	
	public static OrmsReasonPopupPage getInstance() {
		if(null==_instance) {
			_instance=new OrmsReasonPopupPage();
		}
		
		return _instance;
	}
	
	public void clickOK() {
		popup.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel() {
		popup.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void setReason(String reason){
		RegularExpression regTextbox = new RegularExpression("(reason)", false);
		popup.setTextArea(".id", regTextbox, reason);
	}
	
	public void closePopupReasonBox(String reason) {
		setReason(reason);
		clickOK();
	}
}
