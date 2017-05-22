package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.ExternalWebPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author vzhao,jdu
 */
public class UwpExternalWebsitePage extends ExternalWebPage {
	private static UwpExternalWebsitePage _instance = null;

	public static UwpExternalWebsitePage getInstance() {
		if (null == _instance)
			_instance = new UwpExternalWebsitePage();

		return _instance;
	}

	public UwpExternalWebsitePage() {
		super();
		this.attributeName="url";
		this.value=new RegularExpression(".*(RV.*Resort|Thousand Trails|.*af\\.mil|rv-campground).*",false);
	}

	/**
	 * Verify whether the park name displays on page, will close the external site at the end.
	 * @param parkName - park name will be verified
	 * @return true - displays; false - not displays
	 */
	public boolean checkParkDisplayed(String parkName) {
		boolean toReturn = false;
		String matchText = ".*" + parkName + ".*";
		RegularExpression regText = new RegularExpression(".*" + parkName + ".*", false);


		IHtmlObject[] foundLinks = browser.getHtmlObject(".class","Html.A", ".text", regText);
		IHtmlObject[] foundLocations =browser.getHtmlObject(".class", "Html.DIV", ".id", "location");

		if (foundLinks.length > 0)
			toReturn = true;
		else if (foundLocations.length > 0
				&& foundLocations[0].getProperty(".text").toString().matches(
						matchText))
			toReturn = true;

		Browser.unregister(foundLinks);
		Browser.unregister(foundLocations);
		this.close();

		return toReturn;
	}
	/**
	 * Verify whether the Html Document object with given properties exists.
	 * @param property - the property key
	 * @param value - the property value
	 * @return true - exist; false - not exist
	 */
	public boolean isSpecialURLOpen(String url) {
		boolean toReturn = false;
		
		if(browser.url().equalsIgnoreCase(url)){
			toReturn = true;
		}
		return toReturn;
	}
	
	/** Wait for Air force home page to load*/
	public void waitForAirForcePageLoad(){
		RegularExpression reg = new RegularExpression(".*af\\.mil.*",false);
		browser.searchObjectWaitExists(".class","Html.A",".href", reg);
	}
}
