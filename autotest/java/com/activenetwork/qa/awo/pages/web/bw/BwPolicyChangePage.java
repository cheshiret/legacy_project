/*
 * Created on May 10, 2010
 */
package com.activenetwork.qa.awo.pages.web.bw;

import com.activenetwork.qa.awo.pages.UwpPage;



/**
 * @author QA
 */
public class BwPolicyChangePage extends UwpPage {
	private static BwPolicyChangePage _instance = null;

	protected BwPolicyChangePage() {
	}

	public static BwPolicyChangePage getInstance() {
		if (null == _instance)
			_instance = new BwPolicyChangePage();

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "email");
	}

	/** Click on Keep current user and continue link.*/
	public void clickKeepCurrentUser(){
		browser.clickGuiObject(".class","Html.A",".text","Keep my current user name and continue");
	}
}
