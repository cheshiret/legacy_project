/*
 * Created on Apr 28, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @author raonqa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsLoginPage extends OrmsPage {

	/**
	 * Script Name   : OrmsLoginPage
	 * Generated     : Jul 11, 2007 10:35:18 AM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2009/04/29
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsLoginPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsLoginPage() {
		browser = Browser.getInstance();
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsLoginPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsLoginPage();
		}

		return _instance;
	}

	/** Determine if the ORMS login page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression("e_ContractDropDown",false));
	}
	
	public void logIn(String contract, String location, String role, String station) {
		boolean forceAutoRole=Boolean.parseBoolean(TestProperty.getProperty("role.auto", "true"));
		if (contract!=null && contract.length()>0) {
			selectContract(contract);
		} else {
			throw new ItemNotFoundException("Login Contract is unknown");
		}
		
		if (location!=null && location.length()>0) {
		  	if(location.indexOf("/")<0 && role!=null && role.length()>0) {
		  		if(forceAutoRole && !role.contains(" - Auto") && TestProperty.getProperty("target_env").contains("qa")){
		  			role = role+" - Auto";
		  		}
		  		if (role.startsWith("MO Admin")) { //Lesley[20140328]:the role MO Admin change to MO-ADMIN
		  			role = role.replace("MO Admin", "MO-ADMIN");
		  		}
		  	  selectLocation(role+"/"+location);
		  	} else if(location.equalsIgnoreCase("any")) {
		  	  selectLocation(1);
		  	} else {
		  		if(forceAutoRole &&!location.contains(" - Auto/")){
		  			location = location.replaceAll("/", " - Auto/");
		  		}
		  		if (location.startsWith("MO Admin")) {
		  			location = location.replace("MO Admin", "MO-ADMIN");
		  		}
		  	  selectLocation(location);
		  	}	
		} else {
			selectLocation(1);
		}
		
		waitLoading();
		browser.waitExists();
		if (stationExists()) {
			if (station!=null && station.length()>0) {
				selectStation(station);
			} else {
				selectStation(1);
			}
		}

		clickOK();
	}
	
	public void logIn(String contract,String roleAndLocation){
		boolean forceAutoRole=Boolean.parseBoolean(TestProperty.getProperty("role.auto", "true"));
		if (contract!=null && contract.length()>0) {
			selectContract(contract);
		} else {
			throw new ItemNotFoundException("Login Contract is Null.");
		}
		if (roleAndLocation!=null && roleAndLocation.length()>0) {
			if(forceAutoRole&&!roleAndLocation.contains(" - Auto/")){
				roleAndLocation = roleAndLocation.replaceAll("/", " - Auto/");
	  		}
		  	selectLocation(roleAndLocation);
		} else {
			selectLocation(1);
		}
		waitLoading();
		clickOK();
	}
	
	/** Log into an ORMS application using the data stored in the TestData object */
	public void logIn(LoginInfo td) {
		logIn(td.contract,td.location,td.role,td.station);
	}
	
	/**
	 * If the page refresh twice, second time WAITIJ will think the page is exists and 
	 * the waitExists() method will be invalid, so need to wait for another page mark.
	 * This is a bad logic
	 */
//	public void waitForPageLoad(){
//		browser.searchObjectWaitExists(".class", "Html.SELECT", ".id", "e_RoleLocationDropDown");
//	}
	
	/** Select a contract to edit; parameter = contract name */
	public void selectContract(String contract) {
		waitForContract(Browser.LONG_SLEEP);
		contract=TestProperty.getProperty(contract.replaceAll(" ", "_"), contract);
		browser.selectDropdownList(".id", "e_ContractDropDown", contract, true);
	}

	/** Select a location to sign into, as specified by location name */
	public void selectLocation(String location) {
		waitForLocation(Browser.VERY_LONG_SLEEP);
		browser.selectDropdownList(".id", "e_RoleLocationDropDown", location,true);
	}
	
	public void selectLocation(int index) {
		waitForLocation(Browser.VERY_LONG_SLEEP);
		browser.selectDropdownList(".id", "e_RoleLocationDropDown", index,true);
	}

	/** Get location to sign into*/
	public String getLocationName() {
		return browser.getDropdownListValue(".id", "e_RoleLocationDropDown",0);
	}
	
	public boolean stationExists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id","e_StationDropDown");
	}

	/** Select a station to sign into, as specified by station name */
	public void selectStation(String station) {
		waitForStations(Browser.LONG_SLEEP);
		browser.selectDropdownList(".id", "e_StationDropDown", station, true);
	}

	/** Select a station to sign into, as specified by station drop-down list index */
	public void selectStation(int station) {
		browser.selectDropdownList(".id", "e_StationDropDown", station, true);
	}

	/** Click the OK button to login */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", true);
	}

	/**Click Cancel button*/
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "CANCEL", true);
	}

	/** Wait for the location drop-down list to appear */
	public void waitForLocation(int seconds, String toSelect) {		
		browser.dropdownOptionWaitExists(".id","e_RoleLocationDropDown", toSelect);
	}
	
	public void waitForLocation(int timeout) {		
		browser.dropdownOptionWaitExists(Property.toPropertyArray(".id","e_RoleLocationDropDown"),0,timeout,2,null);
	}
	
	public void waitForContract(int timeout) {
		browser.dropdownOptionWaitExists(Property.toPropertyArray(".id","e_ContractDropDown"),0,timeout,2,null);
	}

	/**Wait for contract drop down exist*/
	public void waitForContract(int seconds, String toSelect) {
		browser.dropdownOptionWaitExists(".id", "e_ContractDropDown", toSelect);
	}

	/**
	 * Wait for stations drop down exist
	 * @param seconds
	 * @param toSelect
	 */
	public void waitForStations(int seconds, String toSelect) {
		browser.dropdownOptionWaitExists(".id", "e_StationDropDown", toSelect);
	}
	
	public void waitForStations(int timeout) {
		browser.dropdownOptionWaitExists(Property.toPropertyArray(".id","e_StationDropDown"),0,timeout,2,null);
	}
}
