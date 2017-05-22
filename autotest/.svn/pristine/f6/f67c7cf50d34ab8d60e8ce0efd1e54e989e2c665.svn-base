package com.activenetwork.qa.awo.pages.orms.common.popup;


public class OrmsOverrideRulePinPopupPage extends OrmsPinPopupPage {
	private static OrmsOverrideRulePinPopupPage _instance;
	
	protected OrmsOverrideRulePinPopupPage () {
		super("Enter Pin #|");//Shane[20131016] in 3.05.00.141 build, pin pop up has no title
	}
	
	public static OrmsOverrideRulePinPopupPage getInstance() {
		if(null==_instance) {
			_instance=new OrmsOverrideRulePinPopupPage();
		}
		
		return _instance;
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.BUTTON", ".text", "OK");
	}

	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.BUTTON", ".text", "Cancel");
	}

	public void enterNote(String note) {
		browser.setTextArea(".id","note", note);
	}
	
	/**
	 * this function will check whether pin window has reason field or not.
	 * @return false: there is no reason field in the pin window;
	 * 		   true : there is a reason field in the pin window;
	 */
	public boolean getPinWindowModle(){
		return browser.checkHtmlObjectExists(".id", "reason");
	}
	
	public void enterReason(String reason){
		browser.setTextArea(".id", "reason", reason);
	}
	
	public void enterReason(){
		enterReason("qa auto test");
	}
	
}
