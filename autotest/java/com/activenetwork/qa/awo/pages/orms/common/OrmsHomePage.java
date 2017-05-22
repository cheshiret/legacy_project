/*
 * Created on Apr 28, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * Description   : XDE Tester Script
 * @author CGuo
 */
public class OrmsHomePage extends OrmsPage {

	/**
	 * Script Name   : <b>OrmsHomePage</b>
	 * Generated     : <b>Sep 14, 2004 9:33:46 AM</b>
	 * Description   : XDE Tester Script
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 1)
	 *
	 * @since  2004/09/14
	 * @author CGuo
	 */
	/**
	 * @param sHomePageLink_FieldManager2().Table_HtmlTable_1().
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsHomePage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsHomePage() {
	}
	
	
	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsHomePage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsHomePage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		RegularExpression reg = new RegularExpression(".* Manager$", false);
		boolean found=browser.checkHtmlObjectExists(".class", "Html.A", ".text", reg);
		return found;
	}

	/**
	 * Click link by link name
	 * @param name
	 */
	public void clickLink(String name) {
		if (name.matches("^[F|f]ield.*"))
			if(name.contains("Mobile")){
				this.clickFieldManagerMobile();
			}else{
				this.clickFieldManager();
			}
		else if (name.matches("^[C|c]all.*"))
			this.clickCallManager();
		else if (name.matches("^[O|o]peration.*"))
			this.clickOperationsManager();
		else if (name.matches("^[A|a]dmin.*"))
			this.clickAdminManager();
		else if (name.matches("^[F|f]inance.*"))
			this.clickFinanceManager();
		else if (name.matches("^[I|i]nventory.*"))
			this.clickInventoryManager();
		else if (name.matches("^[R|r]esource.*"))
			this.clickResouceManager();
		else if (name.matches("^[P|p]ermit.*"))
			this.clickPermitManager();
		else if (name.matches("^[V|v]enue.*"))
			this.clickVenueManager();
		else if(name.matches("[L|l]icense.*ger$"))
			this.clickLicenseManager();
		else if(name.matches("[M|m]arina.*ger$"))
			this.clickMarinaManager();
		else if(name.matches("^Communities$"))
			this.clickCommunities();
		else
			throw new ItemNotFoundException("Unknown target: " + name);
	}
	
	public void clickCommunities() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Communities", true);
	}

	/** Click Field Manager button.*/ 
	public void clickFieldManager() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Field Manager", true);
	}
	
	/** Click Field Manager Mobile button.*/ 
	public void clickFieldManagerMobile() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Field Manager Mobile", true);
	}

	/**Click Call Manager link*/
	public void clickCallManager() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Call Manager", true);
	}

	/**Click Admin Manager*/
	public void clickAdminManager() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Admin Manager", true);
	}

	/**Click resource Manager*/
	public void clickResouceManager() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Resource Manager",true);
	}

	/**Click Operation Manager*/
	public void clickOperationsManager() {
		browser.clickGuiObject(".class", "Html.A", ".text","Operations Manager", true);
	}

	/**Click Inventory Manager*/
	public void clickInventoryManager() {
		browser.clickGuiObject(".class", "Html.A", ".text","Inventory Manager", true);
	}

	/**Click Finance Mnager*/
	public void clickFinanceManager() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Finance Manager",true);
	}

	/**Click Venue Manager*/
	public void clickVenueManager() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Venue Manager",true);
	}

	/**Click permit Manager*/
	public void clickPermitManager() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Permit Manager",true);
	}
	
	public void clickLicenseManager(){
		browser.clickGuiObject(".class", "Html.A", ".text", "License Manager",true);
	}

	public void clickMarinaManager() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Marina Manager", true);
	}
}
