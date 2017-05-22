package com.activenetwork.qa.awo.pages.web.bw;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.UwpPage;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class BwCreateGroupLeaderPage extends UwpPage {
	private static BwCreateGroupLeaderPage _instance = null;

	public static BwCreateGroupLeaderPage getInstance() {
		if (null == _instance)
			_instance = new BwCreateGroupLeaderPage();

		return _instance;
	}

	protected BwCreateGroupLeaderPage() {}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.BUTTON", ".text", "Create Group Leader");
	}
	
	/**
	 * Click on Create Grounp Leader button.
	 */
	public void clickCreatGroupLeader() {
		browser.clickGuiObject(".class","Html.BUTTON", ".text","Create Group Leader");
	}

	/**
	 * Fill in first name field.
	 * @param fName - first name
	 */
	public void setFirstName(String fName) {
		browser.setTextField(".id", "firstNameId", fName, true);
	}

	/**
	 * Fill in last name field.
	 * @param lName - last name
	 */
	public void setLastName(String lName) {
		browser.setTextField(".id", "lastNameId", lName, true);
	}

	/**
	 * Fill in address field.
	 * @param address - address
	 */
	public void setAddress(String address) {
		browser.setTextField(".id", "addressId", address, true);
	}

	/**
	 * Fill in group leader's city.
	 * @param city - city
	 */
	public void setCity(String city) {
		browser.setTextField(".id", "cityId", city, true);
	}

	/**
	 * Fill in group leader city zip.
	 * @param zip - city zip
	 */
	public void setPostCode(String zip) {
		browser.setTextField(".id", "zipPostalId", zip, true);
	}

	/**
	 * Fill in group leader's phone number.
	 * @param phone - phone number
	 */
	public void setPhoneNumber(String phone) {
		browser.setTextField(".id", "phone", phone, true);
	}

	/**
	 * Select state.
	 * @param state - state name
	 */
	public void selectState(String state) {
		browser.selectDropdownList(".id", "stateProvinceId", state, true);
	}

	/**
	 * Select come from country.
	 * @param countr - country name
	 */
	public void selectCountry(String country) {
		browser.selectDropdownList(".id", "countryId", country);
	}

	/**
	 * Fill in all group leader information fields.
	 * @param leader - customer info
	 */
	public void setupGroupLeaderInfo(Customer leader) {
		this.setFirstName(leader.fName);
		this.setLastName(leader.lName);
		this.setAddress(leader.address);
		this.setCity(leader.city);
		this.setPostCode(leader.zip);
		this.setPhoneNumber(leader.hPhone);
		this.selectState(leader.state);
		this.selectCountry("Canada");
	}
}
