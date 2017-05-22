package com.activenetwork.qa.awo.pages.orms;

import com.activenetwork.qa.awo.datacollection.datadefinition.LoginAttr;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.datacollection.StringData;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.TestProperty;

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

	/*******************************
	 * Define Html Object properties
	 *******************************/
	protected Property[] contractDropdown() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id", "e_ContractDropDown");
	}
	
	protected Property[] roleLocationDropdown() {
		return Property.toPropertyArray(".class","Html.SELECT",".id", "e_RoleLocationDropDown");
	}
	
	protected Property[] stationDropdown() {
		return Property.toPropertyArray(".class", "Html.SELECT", ".id","e_StationDropDown");
	}
	
	protected Property[] okButton() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "OK");
	}
	
	protected Property[] cancelButton() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "CANCEL");
	}
	
	/** Determine if the ORMS login page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(contractDropdown());
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
		  		if (role.startsWith("MO Admin")) {
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
	public void logIn(StringData<LoginAttr> td) {
		logIn(td.get(LoginAttr.contract),td.get(LoginAttr.location),td.get(LoginAttr.role),td.get(LoginAttr.station));
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
		browser.selectDropdownList(contractDropdown(), contract, true);
	}

	/** Select a location to sign into, as specified by location name */
	public void selectLocation(String location) {
		waitForLocation(Browser.VERY_LONG_SLEEP);
		browser.selectDropdownList(roleLocationDropdown(), location,true);
	}
	
	public void selectLocation(int index) {
		waitForLocation(Browser.VERY_LONG_SLEEP);
		browser.selectDropdownList(roleLocationDropdown(), index,true);
	}

	/** Get location to sign into*/
	public String getLocationName() {
		return browser.getDropdownListValue(roleLocationDropdown(),0);
	}
	
	public boolean stationExists() {
		return browser.checkHtmlObjectExists(stationDropdown());
	}

	/** Select a station to sign into, as specified by station name */
	public void selectStation(String station) {
		waitForStations(Browser.LONG_SLEEP);
		browser.selectDropdownList(stationDropdown(), station, true);
	}

	/** Select a station to sign into, as specified by station drop-down list index */
	public void selectStation(int station) {
		browser.selectDropdownList(stationDropdown(), station, true);
	}

	/** Click the OK button to login */
	public void clickOK() {
		browser.clickGuiObject(okButton(), true);
	}

	/**Click Cancel button*/
	public void clickCancel() {
		browser.clickGuiObject(cancelButton(), true);
	}

	/** Wait for the location drop-down list to appear */
	public void waitForLocation(int seconds, String toSelect) {		
		browser.dropdownOptionWaitExists(roleLocationDropdown(), 0,seconds,toSelect,null);
	}
	
	public void waitForLocation(int timeout) {		
		browser.dropdownOptionWaitExists(roleLocationDropdown(),0,timeout,2,null);
	}
	
	public void waitForContract(int timeout) {
		browser.dropdownOptionWaitExists(contractDropdown(),0,timeout,2,null);
	}

	/**Wait for contract drop down exist*/
	public void waitForContract(int seconds, String toSelect) {
		browser.dropdownOptionWaitExists(contractDropdown(), toSelect);
	}

	/**
	 * Wait for stations drop down exist
	 * @param seconds
	 * @param toSelect
	 */
	public void waitForStations(int seconds, String toSelect) {
		browser.dropdownOptionWaitExists(stationDropdown(), toSelect);
	}
	
	public void waitForStations(int timeout) {
		browser.dropdownOptionWaitExists(stationDropdown(),0,timeout,2,null);
	}
}

