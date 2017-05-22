/*
 * Created on Oct 19, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.operationManager;

import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OpMgrBulletinLocationPage extends OperationsManagerPage {

	static private OpMgrBulletinLocationPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OpMgrBulletinLocationPage()
	{}
	static public OpMgrBulletinLocationPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OpMgrBulletinLocationPage();
		}

		return _instance;
	}

	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		RegularExpression reg = new RegularExpression(
				"^ ?Location ID", false);
//		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",reg);
		return browser.checkHtmlObjectExists(".class", "Html.TD", ".text",reg);
	}

	/**
	 * Input location Name
	 * @param name
	 */
	public void setLocationName(String name) {
		browser.setTextField(".id", "locationName", name, true);
	}

	/**
	 * Select showlocation
	 * @param show
	 */
	public void selectShowLocation(String show) {
		browser.selectDropdownList(".id", "status", show, true);
	}

	/**
	 * Select location category
	 * @param location
	 */
	public void selectLocationCategory(String location) {
		browser.selectDropdownList(".id", "showCategory", location, true);
	}

	/**Click GO button*/
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$", false), true);
	}

	/**Click select drop down*/
	public void clickSelect() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Select", true);
	}

	/**
	 * Do location search 
	 * @param locationname
	 * @param show
	 * @param category
	 */
	public void searchLocation(String locationname, String show, String category) {
		if (locationname.length()>0) {
			setLocationName(locationname);
		}

		if (category.length()>0) {
			selectLocationCategory(category);
		}

		if (show.length()>0) {
			selectShowLocation(show);
		}
	}
}
