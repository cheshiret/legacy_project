package com.activenetwork.qa.awo.pages.orms.common.giftcard;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;

public class OrmsVoidGiftCardSalePage extends OrmsPage {

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsVoidGiftCardSalePage _instance = null;
	
	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsVoidGiftCardSalePage() {
		browser = Browser.getInstance();
	}
	
	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsVoidGiftCardSalePage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsVoidGiftCardSalePage();
		}

		return _instance;
	}
	
	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TEXTAREA", ".id","GiftCardOrderView.voidNotes");
	}
	
	/**
	 * set the void reason
	 * @param reason
	 */
	public void setVoidReason(String reason){
	    browser.setTextArea(".id","GiftCardOrderView.voidNotes",reason);
	}
	
	/**
	 * click the OK button
	 *
	 */
	public void clickOK(){
	    browser.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	/**
	 * click the cancel button
	 *
	 */
	public void clickCancel(){
	  browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}

}
