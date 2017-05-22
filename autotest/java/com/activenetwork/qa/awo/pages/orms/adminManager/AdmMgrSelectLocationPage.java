/*
 * $Id: AdmMgrSelectLocationPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.adminManager;

import java.util.List;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author CGuo
 */
public class AdmMgrSelectLocationPage extends AdminManagerPage {

	/**
	 * Script Name   : AdmSelectLocationPage
	 * Generated     : Jul 11, 2007 11:19:04 AM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2007/07/11
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private AdmMgrSelectLocationPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected AdmMgrSelectLocationPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public AdmMgrSelectLocationPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new AdmMgrSelectLocationPage();
		}

		return _instance;
	}

	/**Check whether the mark object exist*/
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text","Select Location");
		return browser.checkHtmlObjectExists(".id", "locationName");
	}

	/**Select the search for item*/
	public void selectSearchFor(String item) {
		browser.selectDropdownList(".id","location", item);
	}

	/**Set the search for value*/
	public void setSearchForValue(String text) {
		browser.setTextField(".id","locationName", text);
	}

	/**Select the location type*/
	public void selectLocationType(String type) {
		browser.selectDropdownList(".id","showCategory", type);
	}

	/**Click GO*/
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Go|Search",false));
	}
	
	/**
	 * The method excute the process search location
	 * @param locationCategory
	 * @param locationName
	 */
	public void searchLocation(String locationCategory, String locationName) {
		logger.info("Start search location info");
		this.selectSearchFor("Location Name");
		this.setSearchForValue(locationName.toUpperCase());
		this.selectLocationType(locationCategory);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}

	/**
	 * select sepcific location identified by location name
	 * @param location
	 */
	public void selectLocation(String location) {
		IHtmlObject objs[] = browser.getTableTestObject(".text", new RegularExpression("Location ID(\\s)?Location Name(\\s)?Location Description", false));
		if(objs.length < 0) {
			throw new ItemNotFoundException("Can't find table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		List<String> locations = table.getColumnValues(table.findColumn(0, "Location Name"));
		int counter = -1;
		for(int i = 1; i < locations.size(); i ++) {
			if(locations.get(i).equals(location)) {
				counter = i;
				break;
			}
		}
		if(counter == -1) {
			throw new ItemNotFoundException("Can't find location - " + location);
		}
		
		browser.clickGuiObject(".class", "Html.A", ".text", "Select", counter - 1);
		Browser.unregister(objs);
	}
	
	/**click select button*/
	public void clickSelectButton()
	{
	  IHtmlObject[] objs = null;
	  boolean found = false;
	  int counter = 0;
	  do{
		  objs = browser.getHtmlObject(".class","Html.A",".text","Select");
		  if (objs.length > 0) {
			  objs[0].click();
			  found = true;
			  break;
		  }
		  counter ++;
		  Browser.sleep(1);
	  } while(!found && counter < 5);
	  
	  if(objs.length < 1) {
		  throw new RuntimeException("Select button doesn't exist, there my no result please check you search condition");
	  } else Browser.unregister(objs);
	}
}
