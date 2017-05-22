/*
 * Created on Feb 23, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.pos;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;


/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsVoidPOSSalePage extends OrmsPage {
  /**
	 * Script Name   : FldMgrPOSDetailPage
	 * Generated     : Feb 8, 2008 11:27:51 AM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2008/02/08
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsVoidPOSSalePage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsVoidPOSSalePage() {
		browser = Browser.getInstance();
	}
	
	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsVoidPOSSalePage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsVoidPOSSalePage();
		}

		return _instance;
	}
	
	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TEXTAREA", ".id","void_pos_reason_code");
	}
	
	/**
	 * Select the void reason
	 * @param voidReason
	 */
	public void selectVoidReason(String voidReason){
	    browser.selectDropdownList(".id","pos_reason_code",voidReason);
	}
	
	/**
	 * set the void reason
	 * @param reason
	 */
	public void setVoidReason(String reason){
	    browser.setTextArea(".id","void_pos_reason_code",reason);
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
