/*
 * Created on Aug 10, 2009
 */
package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 */
public class UwpPermitFacilityListPage extends UwpPage {

	private static UwpPermitFacilityListPage _instance = null;

	public static UwpPermitFacilityListPage getInstance() {
		if (null == _instance)
			_instance = new UwpPermitFacilityListPage();

		return _instance;
	}

	public UwpPermitFacilityListPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "permitfacilityList");
	}
	
	/**
	 * Click on Check Availability link by facility name.
	 * @param facility - facility name
	 */
	public void clickCheckAvailability(String facility) {
		IHtmlObject[] objs = browser.getTableTestObject(".className","items fullWidth");
		IHtmlTable grid = (IHtmlTable)objs[0];
		RegularExpression reg = new RegularExpression("("+facility+")|("+facility.toUpperCase()+").*", false);
		int row = grid.findRow(1, reg);
		Browser.unregister(objs);
		browser.clickGuiObject(".class","Html.A",".text", "Check Availability",row);
//		facility = facility.replaceAll("\\(|\\)", "").replaceAll(" ", "_").trim();
//		RegularExpression reg = new RegularExpression(facility, false);
//		browser.clickGuiObject(".text", "Check Availability", ".href", ".*"+reg+".*");
//		browser.clickGuiObject(".className","book now", ".href", "http://web02.qa.reserveamerica.com:5101/permits/Boundary_Waters_Canoe_Area_Wilderness/r/entranceSearch.do?search=site&page=siteresult&contractCode=NRSO&parkId=72600&topTabIndex=Permits");
	}
	
	/**
	 * Click on Check Availability link by park ID.
	 * @param parkId - park ID
	 */
	public void clickCheckAvailability(int parkId) {
		RegularExpression reg = new RegularExpression("parkId=" + parkId, false);
		browser.clickGuiObject(".text", "Check Availability", ".href", reg);
	}
}
