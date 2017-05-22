/*
 * $Id: SysMgrHomePage.java 6670 2008-11-18 22:46:37Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.systemManager;

import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author CGuo
 */
public class SysMgrHomePage extends SysMgrCommonTopMenuPage {
  
	private static SysMgrHomePage _instance = null;

	protected SysMgrHomePage() {
	}

	public static SysMgrHomePage getInstance() {
		if (null == _instance)
			_instance = new SysMgrHomePage();

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("^Welcome To System Manager.*", false));
	}

	

}
