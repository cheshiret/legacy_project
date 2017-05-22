package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.datacollection.legacy.web.OrderDetails;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author jchen
 */
public class UwpKOAOrderDetailsPage extends UwpPage {

	private static UwpKOAOrderDetailsPage _instance = null;

	public static UwpKOAOrderDetailsPage getInstance() {
		if (null == _instance)
			_instance = new UwpKOAOrderDetailsPage();

		return _instance;
	}

	public UwpKOAOrderDetailsPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "continueshop");
	}
	
	/**
	 * Fill in order details info
	 * @param od - order details info
	 */
	public void fillOrderDetails(OrderDetails od) {
		this.setValueKard(od.valueKard, od.zip);
		this.setSpecialRequest(od.spNote);
		this.checkAgreementCheckBox();
	}
	
	/**
	 * Fill in the Value Card and post code.
	 * @param vk - value card
	 * @param pc - post code
	 */
	public void setValueKard(String vk, String pc) {
		if (vk.length() < 1)
			return;

		RegularExpression vkRE = new RegularExpression("^accessPassNumber.*",	false);
		browser.setTextField(".id", vkRE, vk);

		RegularExpression pcRE = new RegularExpression("^zipCode.*", false);
		browser.setTextField(".id", pcRE, pc);
	}
	
	/**
	 * Fill in the special request id fields.
	 * @param sr - special request
	 */
	public void setSpecialRequest(String sr) {
		if (sr.length() > 0)
			browser.setTextField(".id", "specibalRequestId", sr);
	}
	
	/***
	 *Check Agreement checkbox.
	 */
	public void checkAgreementCheckBox() {
		browser.selectCheckBox(".id", "agreement");
	}
	
	/**
	 * Click on Change Date link.
	 */
	public void gotoChangeDate() {
		browser.clickGuiObject(".id", "changedates");
	}
	
	/**
	 * Click Remove this reservation link.
	 */
	public void gotoRemoveReservation() {
		browser.clickGuiObject(".id", "removethis");
	}
	
	/**
	 * Click Continue to Shopping Cart button
	 */
	public void gotoShoppingCart() {
		browser.clickGuiObject(".id", "continueshop");
	}
}
