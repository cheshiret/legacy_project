/*
 * $Id: CallMgrHomePage.java 7088 2009-02-03 18:01:07Z i2k-net\raonqa $
 */

package com.activenetwork.qa.awo.pages.orms.callManager;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @author CGuo
 */
public class CallMgrHomePage extends OrmsPage {

	/**
	 * Script Name   : <b>CallMgrHomePage</b>
	 * Generated     : <b>Oct 7, 2004 8:40:24 PM</b>
	 * Description   : XDE Tester Script
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/10/07
	 * @author cguo,jdu
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private CallMgrHomePage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected CallMgrHomePage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public CallMgrHomePage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new CallMgrHomePage();
		}
		return _instance;
	}

	/** Determine if the CallManager home page exists */
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class", "Html.LABEL", ".text","Caller's Home Phone#");
//		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id","contractAdded");//Shane:3.05 build, UI style changed
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".className", "contractInfo");//Quentin[20130904] the Contract dropdown list may not exist
	}

	/** Click on the CallManager log out link */
	public void clickSignOut() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", "logout");
		p[2] = new Property(".text", "Sign Out");
		browser.clickGuiObject(p);
	}
	
	/** Click on the CallManager Launch Pad link */
	public void clickLaunchPad() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", "LaunchPad");
		p[2] = new Property(".text", "Launch Pad");
		browser.clickGuiObject(p);
	}
	

	/**Click Reset PIN*/
	public void clickResetPIN() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression("callmgr\\.rightmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Reset PIN");
		browser.clickGuiObject(p);
	}

	/**
	 * Input Phone number
	 * @param phone
	 */
	public void setPhone(String phone) {
		browser.setTextField(".id", "phone", phone);
	}

	/** Start a call with a contract */
	public void clickCampingCall() {
		browser.clickGuiObject(".class", "Html.A", ".text",new RegularExpression("(Start )?Camping Call",false), true);
	}

	public void clickBoatingCall() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("(Start )?Boating Call",false), true);
	}
	
	/**
	 * Click Camping call button
	 * @param contractShortName
	 */
	public void clickCampingCall(String contractShortName) {
		if (contractShortName.equalsIgnoreCase("NRRS"))
			contractShortName = "NRSO";

		Property[] s1 = Property.toPropertyArray(".class", "Html.DIV", ".id","ContractName_" + contractShortName.toUpperCase());
		Property[] s2 = Property.toPropertyArray(".class", "Html.A", ".text",new RegularExpression("(Start )?Camping Call",false));

		browser.clickGuiObject(Browser.atList(s1, s2), true, 0);
	}

	/**Click Ticketing call*/
	public void clickTicketingCall() {
		browser.clickGuiObject(".class", "Html.A", ".text",new RegularExpression("(Start )?Ticketing Call",false));
	}

	/**
	 * Click Ticketing call by contract short name
	 * @param contractShortName
	 */
	public void clickTicketingCall(String contractShortName) {
		if (contractShortName.equalsIgnoreCase("NRRS"))
			contractShortName = "NRSO";

		Property[] s1 = Property.toPropertyArray(".class", "Html.DIV", ".id",
				"ContractName_" + contractShortName.toUpperCase());
		Property[] s2 = Property.toPropertyArray(".class", "Html.A", ".text",
				new RegularExpression("(Start )?Ticketing Call",false));

		browser.clickGuiObject(Browser.atList(s1, s2), true, 0);
	}

	/**Click Permit Call*/
	public void clickPermitCall() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("(Start )?Permit Call",false));
	}

	/**
	 * Click Permit Call by contract short name
	 * @param contractShortName
	 */
	public void clickPermitCall(String contractShortName) {
		if (contractShortName.equalsIgnoreCase("NRRS"))
			contractShortName = "NRSO";

		Property[] s1 = Property.toPropertyArray(".class", "Html.DIV", ".id",
				"ContractName_" + contractShortName.toUpperCase());
		Property[] s2 = Property.toPropertyArray(".class", "Html.A", ".text",
				new RegularExpression("(Start )?Permit Call",false));

		browser.clickGuiObject(Browser.atList(s1, s2), true, 0);
	}

	/**Click Wildlife Call*/
	public void clickWildlifeCall() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("(Start )?Wildlife Call",false));
	}
	
	/**
	 * Click Wildlife Call by contract short name
	 * @param contractFullName
	 */
//	public void clickWildlifeCall(String contractShortName) {
//		
//		Property[] s1 = Property.getPropertyArray(".class", "Html.DIV", ".id",
//				"CtrName_" + contractShortName);
//		Property[] s2 = Property.getPropertyArray(".class", "Html.A", ".text",
//				"Start Wildlife Call");
//
//		browser.clickGuiObject(Browser.atList(s1, s2), true, 0);
//	}
	
	/**
	 * Select Contract by contract full name
	 * @param contractFullName
	 */
	public void selectContract(String contractFullName) {
		logger.debug("Select contract: "+contractFullName);
		IHtmlObject[] objs=browser.getDropdownList(".id", "contractAdded");
		if(objs.length>0) {
			((ISelect)objs[0]).select(TestProperty.getProperty(contractFullName.replaceAll(" ", "_"), contractFullName));
		} else {
			logger.debug("Add Contract dropdownlist doesn't exist.");
		}
		Browser.unregister(objs);
	}

	/**
	 * Check contract is added or not
	 * @param contractShortName
	 * @return
	 */
	public boolean isContractAdded(String contractShortName) {
		if (contractShortName.equalsIgnoreCase("NRRS"))
			contractShortName = "NRSO";

		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id",
				"ContractName_" + contractShortName.toUpperCase());
	}
	
	/**
	 * This method used to check specifc bulletin displayed in page
	 * @param headLine
	 * @return-existe in current page or not
	 */
	public boolean checkBulletinExists(String headLine)
	{
	  	String temp = browser.getObjectText(".class","Html.DIV",".className","bulletin_block");
	  	boolean found = true;
	  	if(temp.indexOf(headLine)!=-1){
	  	  return found;
	  	}
	  	
	  	return !found;
	}

}
