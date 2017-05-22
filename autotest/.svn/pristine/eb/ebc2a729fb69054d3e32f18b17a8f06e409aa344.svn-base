package com.activenetwork.qa.awo.pages.web.bw;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 */
public class BwReservationListPage extends UwpPage {

	private RegularExpression pageMark = new RegularExpression(
			"(Current Reservations|Past Reservations|Lottery Applications)",false);

	private static BwReservationListPage _instance = null;

	public static BwReservationListPage getInstance() {
		if (null == _instance)
			_instance = new BwReservationListPage();

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text",pageMark);
	}

	/**
	 * Fill in reservation number.
	 * @param resNum - reservation mumber
	 */
	public void setReservationNum(String resNum) {
		browser.setTextField(".id", "reservationNumber", resNum);
	}

	/**
	 * Fill in entry start date.
	 * @param date - start date
	 */
	public void setEntryDateStart(String date) {
		browser.setTextField(".id", "arrivaldate", date);
	}

	/**
	 * Fill in entry end date.
	 * @param date - end date
	 */
	public void setEntryDateEnd(String date) {
		browser.setTextField(".id", "enddate", date);
	}

	/**
	 * Fill in group leader last name.
	 * @param lName - last name
	 */
	public void setGroupLeaderLastName(String lName) {
		browser.setTextField(".id", "groupLeaderLastName", lName);
	}

	/**
	 * Click on button to do search.
	 */
	public void clickSearch() {
		browser.clickGuiObject(".id", "btnfindreservation");
	}

	/**
	 * Search reservation by reservation id.
	 * @param resNum - reservation id
	 */
	public void searchReservation(String resNum) {
		if (browser.checkHtmlObjectExists(".id", "btnfindreservation")) {
			this.setReservationNum(resNum);
			this.clickSearch();
			this.searchWaitexist();
		}
	}
	
	public void searchWaitexist() {
		browser.searchObjectWaitExists(".class","Html.A",".text","See Details");
	}

	/**
	 * Search reservation by given number and click on it to go to details page.
	 * @param resNum - reservation number
	 * @return - true - found link; false - not found
	 */
	public boolean selectReservation(String resNum) {
		boolean found = false;
		searchReservation(resNum);
		
		RegularExpression pattern = new RegularExpression(".*" + resNum + ".*",false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",	".href", pattern);
		
		if (objs.length > 0) {
			found = true;
			((ILink) objs[0]).click();
		}
		Browser.unregister(objs);
		return found;
	}

	/**
	 * Verify whether the next page link is available.
	 * @return
	 */
	public boolean nextPageAvailable() {
		boolean toReturn = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".id",
				"resultNext");
		if (objs.length > 0)
			toReturn = objs[0].getProperty(".href").toString().length() > 0;
		Browser.unregister(objs);
		return toReturn;
	}

	/**
	 * Go to next results page.
	 */
	public void clickNext() {
		browser.clickGuiObject(".class", "Html.A", ".id", "resultNext");
	}

	/**
	 * Go to permit reservation list page.
	 */
	public void gotoPermitResList() {
		browser.clickGuiObject(".class", "Html.A", ".id","Permit Reservations");
	}

	/**
	 * if there are records in search list, return true, or return false.
	 * @return
	 */
	public boolean hasResult() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "See Detail");
	}

	public void clickHome() {
		browser.clickGuiObject(".class","Html.A",".href",new RegularExpression(".*/welcome.do",false));
	}
}
