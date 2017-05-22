/*
 * $Id: OpMgrTopMenuPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.operationManager;

/**
 * TODO: enter class description
 * 
 * @author CGuo
 */
public class OpMgrTopMenuPage extends OpMgrCommonTopMenuPage {

	/**
	 * Script Name   : OpMgrTopMenuPage
	 * Generated     : Sep 23, 2005 5:54:57 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2005/09/23
	 */

	private static OpMgrTopMenuPage _instance = null;

	public static OpMgrTopMenuPage getInstance() {
		if (null == _instance)
			_instance = new OpMgrTopMenuPage();

		return _instance;
	}

	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		//use search dropdown list as pageMark
//		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id","field_search_dropdown");
		return browser.checkHtmlObjectExists(".id","field_search_dropdown");
	}

}
