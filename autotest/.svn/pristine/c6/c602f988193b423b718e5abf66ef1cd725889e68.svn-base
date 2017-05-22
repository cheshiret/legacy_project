package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author jchen
 */
public class UwpTourParkListPage extends UwpPage {

	private static UwpTourParkListPage _instance = null;

	public static UwpTourParkListPage getInstance() {
		if (null == _instance)
			_instance = new UwpTourParkListPage();

		return _instance;
	}

	protected UwpTourParkListPage() {
	}

	public boolean exists() {
		//Add the first identifier when make tour in PLW (hc)
		boolean flag_1 = browser.checkHtmlObjectExists(".class", "Html.A", ".href", new RegularExpression(".*tourDetails\\.do.*", false));
		boolean flag_2 = browser.checkHtmlObjectExists(".class", "Html.A", ".className", "book now");
		
		return !flag_1 && flag_2;
	}

	/**
	 * Go to tour list page by index
	 * @param i - index of tour on page, start from 1
	 * @return - tour park name
	 */
	public String gotoTourList(int i) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".className", "book now");
		IHtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.A", ".title", "Details");
		IHtmlObject gto = (IHtmlObject) objs[i - 1];
		String parkName = (String) objs1[(i - 1) * 2].getProperty(".text");
		parkName = parkName.substring(0, parkName.indexOf(","));
		parkName = parkName.trim();
		gto.click();
		Browser.unregister(objs);
		Browser.unregister(objs1);
		return parkName;
	}

	/**
	 * Go to tour park details page by index.
	 * @param i - tour park index, start from 1
	 * @return - tour park name
	 */
	public String gotoTourParkDetails(int i) {
		IHtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.A", ".title", "Details");
		IHtmlObject gto = (IHtmlObject) objs1[(i - 1) * 2];
		String parkName = (String) objs1[(i - 1) * 2].getProperty(".text");
		parkName = parkName.substring(0, parkName.indexOf(","));
		parkName = parkName.trim();
		gto.click();
		Browser.unregister(objs1);
		return parkName;
	}

	/**
	 * Go to first tour park list page.
	 * @return - tour park name
	 */
	public String gotoTourParkDetails() {
		return gotoTourParkDetails(null);
	}

	/**
	 * Go to tour list page by give park name.
	 * @param pName - tour park name
	 * @return - select tour park name
	 */
	public String gotoTourParkDetails(String pName) {
		RegularExpression textPattern=new RegularExpression("^[\\w\\s\\(\\)-]{1,50}, [A-Z]{2}$",true);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", textPattern);
		String parkName = null;
		if (pName == null || pName.length() < 1) { //get the first park
			parkName = objs[0].getProperty(".text");
			Browser.unregister(objs);
			parkName = parkName.substring(0, parkName.indexOf(",")).trim();
			browser.clickGuiObject(".class", "Html.A", ".className", "book now");

			return parkName;
		} else {
			for (int i = 0; i < objs.length; i ++) {
				parkName = objs[i].getProperty(".text");
				parkName = parkName.substring(0, parkName.indexOf(",")).trim();
				if (parkName.equalsIgnoreCase(pName)) {
					Browser.unregister(objs);
					browser.clickGuiObject(".class", "Html.A", ".text", "Book Now", i);
					return parkName;
				}
			}
		}
		Browser.unregister(objs);

		throw new ItemNotFoundException("There is no tour parks called " + pName);
	}
}
