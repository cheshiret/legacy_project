package com.activenetwork.qa.awo.pages.web.bw;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author QA
 */
public class BwAccountPanel extends UwpPage {
	private static BwAccountPanel _instance = null;

	public static BwAccountPanel getInstance() {
		if (null == _instance)
			_instance = new BwAccountPanel();

		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", new RegularExpression("My Account|myAccount",false));
	}

	/**
	 * Click on Current Reservation link.
	 */
	public void clickCurrentReservations() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Current Reservations");
	}

	/**
	 * Go to past reservation page.
	 */
	public void clickPastReservations() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Past Reservations");
	}
	
	/**
	 * Go to Print Tickets & Permits page
	 */
	public void clickPrintTicketsAndPermits() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Print Tickets & Permits");
	}

	/**
	 * Go to Lottery Applications page.
	 */
	public void clickLotteryApplications() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Lottery Applications");
	}

	/**
	 * Go to update profile page.
	 */
	public void clickUpdateProfile() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Update Profile");
	}

	/**
	 * Go to update email page.
	 */
	public void clickUpdateEmail() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Update Email");
	}

	/**
	 * Go to update password page.
	 */
	public void clickUpdatePassword() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Update Password");
	}
}
