package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class UwpPolicyChangePage extends UwpPage {
	private static UwpPolicyChangePage _instance = null;

	protected UwpPolicyChangePage() {
	}

	public static UwpPolicyChangePage getInstance() {
		if (null == _instance)
			_instance = new UwpPolicyChangePage();

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("email|acceptPolicy", false));//Lesley[20131014]: update for new Policy Change page in RA.com in 3.05.00
	}

	protected Property[] keepCurrentUserLink() {
		return Property.concatPropertyArray(this.a(), ".text","Keep my current user name and continue");
	}
	
	protected Property[] acceptPolicyBtn() {
		return Property.toPropertyArray(".class", "Html.Button", ".id", "acceptPolicy");
	}
	
	
	/** Click on Keep current user and continue link.*/
	public void clickKeepCurrentUser(){
		browser.clickGuiObject(keepCurrentUserLink());
	}
	
	public boolean isKeepCurrentUserExist() {
		return browser.checkHtmlObjectExists(keepCurrentUserLink());
	}
	
	public void clickAcceptPolicy() {
		browser.clickGuiObject(acceptPolicyBtn());
	}
}
