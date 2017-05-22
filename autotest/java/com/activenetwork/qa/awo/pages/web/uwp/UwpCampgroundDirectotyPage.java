package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author vzhao
 */
public class UwpCampgroundDirectotyPage extends UwpPage {
	private static UwpCampgroundDirectotyPage _instance = null;

	public static UwpCampgroundDirectotyPage getInstance() {
		if (null == _instance)
			_instance = new UwpCampgroundDirectotyPage();

		return _instance;
	}

	protected UwpCampgroundDirectotyPage() {
	}

	public boolean exists() {
		RegularExpression reg = new RegularExpression("^Campground Directory.*",	false);
		return browser.checkHtmlObjectExists(".id", "contentArea",".text",reg);
	}

	/**
	 * Retrieve number of agency list
	 */
	public int getAgencyListNum() {
		RegularExpression reg = new RegularExpression(".*/camping/.*/r/.*",	false);

		IHtmlObject[] foundTOs = browser.getHtmlObject(".class", "Html.A",".href", reg);
		int listNum = foundTOs.length;

		Browser.unregister(foundTOs);
		return listNum;
	}

	/**
	 * Click on any of the agency links and return the agency name
	 */
	public String clickOnAgencyLink(int i) {
		RegularExpression reg = new RegularExpression("(.*)?/camping/.*/r/.*",	false);
		IHtmlObject[] foundObjs = browser.getHtmlObject(".class", "Html.A",".href", reg);

		String agencyName = foundObjs[i].getProperty(".text").trim();
		foundObjs[i].click();
		
		Browser.unregister(foundObjs);
		return agencyName;
	}
	
	/**Click the Agency link name based one the name info passed in
	 * @param linkName
	 */
	public void clickOnAgencyLink(String linkName){
		browser.clickGuiObject(".class", "Html.A",	".text", linkName, true);
	}
}
