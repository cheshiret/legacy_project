/*
 * $Id: OpMgrHomePage.java 6624 2008-11-14 15:58:20Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.operationManager;

import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author CGuo
 */
public class OpMgrHomePage extends OpMgrCommonTopMenuPage {

	/**
	 * Script Name   : OpMgrHomePage
	 * Generated     : Oct 21, 2004 1:36:21 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/10/21
	 */
	
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OpMgrHomePage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OpMgrHomePage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OpMgrHomePage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OpMgrHomePage();
		}

		return _instance;
	}

	/** Determine if the CallManager home page exists */
	public boolean exists() {
		//use Operation Manager logo as pageMark
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id","callback_view");
	}

	/**Get the information of bulletin in the home page*/
	public String getBulletin() {

		RegularExpression reg = new RegularExpression("^Bulletins.*", false);
		IHtmlObject[] obj = browser.getHtmlObject(".class","Html.TABLE", ".text", reg);
		String content = obj[0].getProperty(".text").toString();
//		String content = obj[1].getProperty(".text").toString();

		return content;
	}

	/**
	 * This method used to check specifc bulletin displayed in page
	 * @param headLine
	 * @return-existe in current page or not
	 */
	public boolean checkBulletinExists(String headLine)
	{
	  	IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("^Bulletins.*",false));
	  	String temp = objs[0].getProperty(".text").toString();
	  	Browser.unregister(objs);
	  	boolean found = true;
	  	if(temp.indexOf(headLine)!=-1){
	  	  return found;
	  	}
	  	
	  	return !found;
	}
}
