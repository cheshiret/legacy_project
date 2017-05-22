package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.util.Property;

public class UwpCheckAvailabilityPage extends UwpPage {

	private static UwpCheckAvailabilityPage _instance = null;

	public static UwpCheckAvailabilityPage getInstance() {
		if (null == _instance)
			_instance = new UwpCheckAvailabilityPage();

		return _instance;
	}

	protected UwpCheckAvailabilityPage() {
	}

	public boolean exists() {
		//in order to different checkAvailabilityPage from siteDetailsPage. ".text" property is a must
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class","Html.BUTTON",".id", "btnbookdates",".text","Check Availability"));
	}
		
	/**
	 * retrieve the arrival date on site details page
	 * @return - arrival date
	 */
	public String getArriveDate() {
		return browser.getTextFieldValue(".id", "arrivaldate");
	}
	
	/**
	 * enter the arrival date.
	 * @param date - arrival date
	 */
	public void setArriveDate(String date) {
		browser.setTextField(".id", "arrivaldate", date);
	}
	
	/**
	 * enter length of stay
	 * @param length - length of wanted stay
	 */
	public void setLengthOfStay(String length) {
		browser.setTextField(".id", "lengthOfStay", length);
	}
	
	public void clickCheckAvailability() {
		browser.clickGuiObject(".type","submit",".id", "btnbookdates");
	}
}
