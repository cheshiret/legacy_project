/*
 * $Id: InvMgrLoopSitePage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;

/**
 * @author CGuo
 */
public class InvMgrLoopSitePage extends InventoryManagerPage {

	/**
	 * Script Name   : InvMgrLoopSiteMainPage
	 * Generated     : Feb 9, 2005 6:39:01 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2005/02/09
	 */

	private static InvMgrLoopSitePage _instance = null;

	public static InvMgrLoopSitePage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrLoopSitePage();
		}

		return _instance;
	}

	protected InvMgrLoopSitePage() {

	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Add New Loop/Area");
	}

	/**click sites tab*/
	public void clickSites() {
		browser.clickGuiObject(".className", "tabanchor", ".text", "Sites");
	}
	
	/**click Non-Site-Specific Groups tab*/
	public void clickNonSpecSiteGroup(){
	  browser.clickGuiObject(".className", "tabanchor", ".text", "Non-Site-Specific Groups");
	}

	/** Click on Add New Loop/Area link. */
	public void clickAddNewLoopArea() {
		browser.clickGuiObject(".class", "Html.A", ".text",
						"Add New Loop/Area");
	}

	/**
	 * Go to special loop's details page by given loop name.
	 * @param loop - loop name
	 */
	public void clickLoopLink(String loop) {
		browser.clickGuiObject(".class", "Html.A", ".text", loop);
	}
	
	/**
	 * Check if site tab exist
	 * @return
	 */
	public boolean isSiteTabExist(){
	   return browser.checkHtmlObjectExists(".className", "tabanchor", ".text", "Sites");
	}
	
	/**
	 * check if NSS tab exist
	 * @return
	 */
	public boolean isNSSTabExist(){
	  return browser.checkHtmlObjectExists(".className", "tabanchor", ".text", "Non-Site-Specific Groups");
	}

}
