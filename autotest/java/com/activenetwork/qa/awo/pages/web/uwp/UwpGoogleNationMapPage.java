package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 */
public class UwpGoogleNationMapPage extends UwpPage {
	private static UwpGoogleNationMapPage _instance = null;

	public static UwpGoogleNationMapPage getInstance() {
		if (null == _instance)
			_instance = new UwpGoogleNationMapPage();

		return _instance;
	}

	public UwpGoogleNationMapPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.DIV",".id", "mapHome");
	}
	
	/**
	 * Go to special state's google map page by click on state name link.
	 * @param state - state name
	 */
	public void gotoStateMapByClickStateLink(String state) {
		browser.clickGuiObject(".class", "Html.A", ".text", state);
	}
	
	/**
	 * Go to special state's google map page by click on map directly.
	 * @param state - state name
	 */
	public void gotoStateMapByClickOnMap(String state) {
		Property[] p = new Property[1];
		p[0] = new Property(".src", new RegularExpression(".*images/nationwide_camping.gif",false));
		browser.clickImageArea(p, new RegularExpression(".*map_of_"+state.replaceAll(" ", "_")+".*",false));
	}
	
}
