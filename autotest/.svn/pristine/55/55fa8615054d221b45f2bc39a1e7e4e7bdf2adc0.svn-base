/*
 * Created on Dec 28, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author dsui
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsLaunchPadPage extends OrmsPage {
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsLaunchPadPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsLaunchPadPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsLaunchPadPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsLaunchPadPage();
		}
		return _instance;
	}

	/** Determine if the launchpad page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "selected_contract");
	}

	/**
	 * Determine if the support center link exists
	 * 
	 * @return
	 */
	public boolean isSupportCenterExist() {
		boolean exist = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Support Center");
		if (objs.length > 0) {
			exist = true;
		}

		return exist;
	}

	/**
	 * Select one contract
	 * 
	 * @param contract
	 */
	public void selectContract(String contract) {
		browser.selectDropdownList(".id", "selected_contract", contract);
		this.waitLoading();
	}

	/**
	 * Select Location
	 * 
	 * @param location
	 */
	public void selectLocation(String location) {
		browser.selectDropdownList(".id", "selected_loc", location);
		this.waitLoading();
	}

	/** Click support center link */
	public void clickSupportCenter() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Support Center");
	}

	public void clickSignOut() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Sign out");
	}

	/** Click CallManager link */
	public void clickCallManager() {
		browser.clickGuiObject(".class", "Html.A", ".text", "CallManager");
	}
	
	public void clickInventoryManager() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Inventory Manager");
	}

	/**
	 * In the ormslauchpad page,select contract, location and role to login
	 * inventory manager
	 * 
	 * @param login
	 * */
	public void loginInventoryManager(LoginInfo login) {
		RegularExpression reg = new RegularExpression(".*FacilityList.do?.*",
				false);

		this.selectContract(login.contract);
		browser.sync();
		this.selectLocation(login.location);
		browser.clickGuiObject(".href", reg, ".text", "RA - Inventory Admin");

	}

	/**
	 * Select specific role for inventory manager
	 * 
	 * @param rolename
	 */
	public void selectRoleForInvMgr(String rolename) {

		RegularExpression reg = new RegularExpression(".*FacilityList.do?.*",
				false);
		browser.clickGuiObject(".href", reg, ".text", rolename);
	}

}
