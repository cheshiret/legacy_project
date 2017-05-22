package com.activenetwork.qa.awo.pages.orms.inventoryManager;

import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author CGuo,jdu
 */
public class InvMgrTopMenuPage extends InvMgrCommonTopMenuPage {

	/**
	 * Script Name   : InvMgrTopMenuPage
	 * Generated     : Oct 28, 2005 3:27:31 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2005/10/28
	 */ 
	
	private static InvMgrTopMenuPage _instance = null;

	protected InvMgrTopMenuPage() {
	}

	public static InvMgrTopMenuPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrTopMenuPage();
		}

		return _instance;
	}

	public boolean exists() {
		RegularExpression rex = new RegularExpression("right\\.menu\\.id\\.\\d+", false);
		return browser.checkHtmlObjectExists(".id",rex,".text","Home");
	}

}
