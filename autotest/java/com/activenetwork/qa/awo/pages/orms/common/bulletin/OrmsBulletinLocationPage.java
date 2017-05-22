/*
 * Created on Jan 31, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.bulletin;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsBulletinLocationPage extends OrmsPage {

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsBulletinLocationPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsBulletinLocationPage()
	{}
	
	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsBulletinLocationPage getInstance()
	{
		if (null == _instance) {
			_instance = new OrmsBulletinLocationPage();
		}

		return _instance;
	}

	/** Determine given page mark exists or not */
	public boolean exists() {
		RegularExpression reg = new RegularExpression(
				"Location ID Location Name*", false);
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",reg);
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
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$",false));
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
	public void searchLocation(String locationname,String category) {
		if (locationname !=null && locationname.length()>0) {
			setLocationName(locationname);
		}
		if (category !=null && category.length()>0) {
			selectLocationCategory(category);
		}

		this.clickGo();
		waitLoading();
	}
}
