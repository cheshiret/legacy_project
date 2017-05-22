package com.activenetwork.qa.awo.pages.orms.common.popup;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;


public abstract class OrmsPinPopupPage extends DialogWidget {

	protected OrmsPinPopupPage(String title) {
		super(title);
	}
	
	/**
	 * Click cancel link
	 */
	public abstract void clickCancel();
	
	/**
	 * Click ok link
	 * @return
	 */
	public abstract void clickOK();
		
	/**
	 * Enter the pin number
	 * @param pin
	 */
	public void enterPIN(String pin) {
		logger.debug("Enter pin#"+pin);
		browser.setTextField(".class","Html.INPUT.password",".id", "pinnumber", pin,false,0);
	}	
	
	public void setReason(String reason) {
		browser.setTextArea(".id", "reason", reason);
	}
	
	public String getMessage(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "message msgerror");
		
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found message object.");
		}
		String message = objs[0].text();
		Browser.unregister(objs);
		
		return message;
	}

}
