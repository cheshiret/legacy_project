/*
 * $Id: ResMgrHomePage.java 6670 2008-11-18 22:46:37Z i2k-net\raonqa $ 
 */
package com.activenetwork.qa.awo.pages.orms.resourceManager;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author cguo
 */
public class ResMgrHomePage extends ResMgrCommomTopMenuPage {

	private static ResMgrHomePage _instance = null;

	public static ResMgrHomePage getInstance() {
		if (null == _instance) {
			_instance = new ResMgrHomePage();
		}

		return _instance;
	}

	protected ResMgrHomePage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("My Favorite Reports", false));
	}

	/** Click on Edit Favorite Reports link. */
	public void clickEditFavoriteReports() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Edit Favorite Reports");
	}

	/** Click on Today's Requested Reports link. */
	public void clickTodayRequestedReports() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Today's Requested Reports");
	}

	/** Click on More Reports link. */
	public void clickMoreReports() {
		browser.clickGuiObject(".class", "Html.A", ".text", "More Reports...");
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
