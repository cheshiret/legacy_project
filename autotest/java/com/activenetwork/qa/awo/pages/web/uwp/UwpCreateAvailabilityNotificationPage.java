/*
 * Created on Feb 1, 2010
 */
package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author QA
 * You can access this page by clicking Create Availability Notification link in site list page
 * when site is not available.
 */
public class UwpCreateAvailabilityNotificationPage extends UwpPage {
  	private static UwpCreateAvailabilityNotificationPage _instance = null;

	public static UwpCreateAvailabilityNotificationPage getInstance() {
		if (null == _instance)
			_instance = new UwpCreateAvailabilityNotificationPage();

		return _instance;
	}

	public UwpCreateAvailabilityNotificationPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "submit");
	}
	
	/** Click on Back to Previous page link. */
	public void clickBackToPrevious(){
	  	browser.clickGuiObject(".id","dback");
	}
	
	/** Click on Create Availability Notification submit button. */
	public void clickCreateNotificationButton(){
	  	browser.clickGuiObject(".id","submit");
	}
	
	/** Click on Create Availability Notification submit button. */
	public void clickNotCreateNotification(){
	  	browser.clickGuiObject(".id","cancel");
	}
	
	/** Get Arrival Date on the page */
	public String getArrivalDate() {
		String text = browser.getObjectText(".class", "Html.TR", ".text", new RegularExpression("^Arrival:.*", false));
		text = text.replace("Arrival:", "");
		return text.trim();
	}
	
	/** Get length of stay on the page */
	public String getLengthOfStay() {
		String text = browser.getObjectText(".class", "Html.TR", ".text", new RegularExpression("^Length Of Stay:.*", false));
		text = RegularExpression.getMatches(text, "\\d+")[0];
		return text;
	}
	
	/** Verify arrival date and length of stay on the page */
	public void verifyArrivalDateAndLengthOfStay(String expDate, String expLength) {
		boolean result = MiscFunctions.compareResult("arrival date", expDate, this.getArrivalDate());
		result &= MiscFunctions.compareString("length of stay", expLength, this.getLengthOfStay());
		
		if (!result) {
			throw new ErrorOnPageException("The date info is wrong on Create Availability Noticification page. Check logger error!");
		}
		logger.info("The date info is correct on Create Availability Noticification page");
	}
}
