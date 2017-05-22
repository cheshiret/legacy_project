package com.activenetwork.qa.awo.pages.orms.common.popup;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.page.HtmlPopupPage;


public class OrmsEnterPinNumPopupPage extends HtmlPopupPage{
	private static OrmsEnterPinNumPopupPage _instance;
	
	protected OrmsEnterPinNumPopupPage () {//change to extends HtmlPopupPage, as for close fin session enter pin pop still window pop up,not widget
		super("title","Enter Pin #");
	}
	
	public static OrmsEnterPinNumPopupPage getInstance() {
		if(null==_instance) {
			_instance=new OrmsEnterPinNumPopupPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		popup=Browser.getInstance().getHTMLDialog("title", "Enter Pin #");
		boolean exites=false;
		if(popup == null){//It is work when slip reservation break rule and needed a pin and note
			exites=Browser.getInstance().checkHtmlObjectExists(".class", "Html.SPAN", ".text","Enter Pin #");
		}
		return popup!=null || exites;
	}
	
	public void enterNote(String note) {
		popup.setTextArea(".id","note", note);
	}
	
	/**
	 * this function will check whether pin window has reason field or not.
	 * @return false: there is no reason field in the pin window;
	 * 		   true : there is a reason field in the pin window;
	 */
	public boolean getPinWindowModle(){
		return popup.checkHtmlObjectExists(".id", "reason");
	}
	
	public void enterReason(String reason){
		popup.setTextArea(".id", "reason", reason);
	}
	
	public void enterReason(){
		enterReason("qa auto test");
	}
	
	public void clickCancel() {
		if(popup.checkHtmlObjectExists(".class","Html.A", ".text", "Cancel")){
			popup.clickGuiObject(".class","Html.A", ".text", "Cancel");
			return;
		}
		popup.clickGuiObject(".class","Html.BUTTON", ".text", "Cancel");
	}

	public void clickOK() {
		if(popup.checkHtmlObjectExists(".class","Html.A", ".text", "OK")){
			popup.clickGuiObject(".class","Html.A", ".text", "OK");
			return;
		}
		popup.clickGuiObject(".class","Html.BUTTON", ".text", "OK");
	}
	
	/**
	 * Enter the pin number
	 * @param pin
	 */
	public void enterPIN(String pin) {
		logger.debug("Enter pin#"+pin);
		popup.setTextField(".class","Html.INPUT.password",".id", "pinnumber", pin,false,0);
	}	
	
	public void setReason(String reason) {
		popup.setTextArea(".id", "reason", reason);
	}
	
	public String getMessage(){
		IHtmlObject[] objs = popup.getHtmlObject(".class", "Html.DIV", ".className", "message msgerror");
		
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found message object.");
		}
		String message = objs[0].text();
		Browser.unregister(objs);
		
		return message;
	}

}
