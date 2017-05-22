/*
 * $Id: InvMgrDeleteSeasonConfimPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * TODO: enter class description
 * 
 * @author CGuo
 */
public class InvMgrConfimActionPage extends InventoryManagerPage {

	/**
	 * Script Name   : InvMgrDeleteSeasonConfimPage
	 * Generated     : Oct 28, 2005 4:28:53 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2005/10/28
	 */
	private static InvMgrConfimActionPage _instance = null;

	public static InvMgrConfimActionPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrConfimActionPage();
		}

		return _instance;
	}

	protected InvMgrConfimActionPage() {
	}

	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text","Confirm Action");
	}

	/**Click OK button*/
	public void clickOK() {
		RegularExpression reg=new RegularExpression("Ok",false);
		browser.clickGuiObject(".class", "Html.A", ".text", reg);
	}
	
	public void clickCancel() {
		RegularExpression reg=new RegularExpression("Cancel",false);
		browser.clickGuiObject(".class", "Html.A", ".text", reg);
	}
	
	/**
	 * Get warning message in confirm action page, 
	 * @param action, eg:"Deactivate Season"
	 * @return
	 */
	public String getWarningMessage(String action){
		IHtmlObject[] objs = browser.getTableTestObject(".text",	new RegularExpression("^" + action + ".*", false));
		IHtmlTable table = (IHtmlTable)objs[0];
		String warningMessage = table.text().replace(action, "").trim();
		Browser.unregister(objs);
		return warningMessage;
	}

}
