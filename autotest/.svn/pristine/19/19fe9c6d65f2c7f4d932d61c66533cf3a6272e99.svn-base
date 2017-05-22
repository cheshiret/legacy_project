package com.activenetwork.qa.awo.pages.web.bw;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;

/**
 * @author QA
 */
public class BwFindGroupLeaderPage extends UwpPage {

	private static BwFindGroupLeaderPage _instance = null;

	public static BwFindGroupLeaderPage getInstance() {
		if (null == _instance)
			_instance = new BwFindGroupLeaderPage();

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.BUTTON", ".name",
				"groupLeaderSearchButton");
	}

	/**
	 * Fill in first name field, if not provided, do nothing.
	 * @param fName - first name
	 */
	public void setFirstName(String fName) {
		if (fName == null || fName.length() < 1)
			return;
		browser.setTextField(".id", "searchFirstName", fName);
	}

	/**
	 * Fill in last name field, if not provided, do nothing.
	 * @param lName - last name
	 */
	public void setLastName(String lName) {
		if (lName == null || lName.length() < 1)
			return;
		browser.setTextField(".id", "searchLastName", lName);
	}

	/**
	 * Fill in phone number.
	 * @param phone - phone number
	 */
	public void setPhone(String phone) {
		if (phone == null || phone.length() < 1)
			return;
		browser.setTextField(".id", "searchTelephoneNumber", phone);
	}

	/**
	 * Fill in email field.
	 * @param email - email account
	 */
	public void setEmail(String email) {
		if (email == null || email.length() < 1)
			return;
		browser.setTextField(".id", "searchEmailAddress", email);
	}

	/**
	 * Fill in organization name.
	 * @param org - organization name
	 */
	public void setOrgName(String org) {
		if (org == null || org.length() < 1)
			return;

		browser.setTextField(".id", "searchOrgName", org);
	}

	/**
	 * Click on search button to search existing user.
	 */
	public void clickSearchButton() {
		browser.clickGuiObject(".class", "Html.BUTTON", ".name",
				"groupLeaderSearchButton");

	}

	/**
	 * Click on button to create a new group leader.
	 */
	public void clickCreateGroupLeaderButton() {
		browser.clickGuiObject(".class", "Html.BUTTON", ".name",
				"groupLeaderCreateButton");
	}

	/**
	 * Fill in all customer info in search fields.
	 * @param cust - customer info
	 */
	public void setupSearchCriteria(Customer cust) {
		this.setFirstName(cust.fName);
		this.setLastName(cust.lName);
		this.setPhone(cust.hPhone);
		this.setEmail(cust.email);
		this.setOrgName(cust.organization);
	}

	/**
	 * Search customer by phone number.
	 * @param phone - phone number
	 */
	public void searchByPhone(String phone) {
		this.setPhone(phone);
		this.clickSearchButton();
	}

	/**
	 * Wait for page to load after submitting the search button.
	 */
	public void searchWaitExists() {
		waitForRetrievingData();
		browser.searchObjectWaitExists(".class", "Html.DIV", ".id","contentArea");
	}

	/**
	 * Verify whether there is any search results after doing search.
	 * @return true - found; false - not found
	 */
	public boolean foundCustomer() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text","Select");
	}

	/**
	 * Select customer by index.
	 * @param index - index of customer want to select, start from 1
	 */
	public void selectCustomer(int index) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",".text", "Select");
		if(index>objs.length) {
		  	throw new ItemNotFoundException("Index "+index+" is out of boundary "+objs.length);
		} else {
		  	((ILink) objs[index - 1]).click();
		}
		Browser.unregister(objs);
	}

	/**
	 * Select the first customer in search results list.
	 */
	public void selectCustomer() {
		selectCustomer(1);
	}

	public void testMain(Object[] args) {
		this.selectCustomer();
	}
	
	/**
	 * local method
	 */
	private void waitForRetrievingData() {
		int counter = 0;
		while(browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "progressBar")) {
			counter++;
			if(counter > Browser.VERY_LONG_SLEEP) {
				throw new PageNotFoundException("Retrieving data progress doesn't finish in " + Browser.VERY_LONG_SLEEP  + " seconds");
			}
			
			Browser.sleep(Browser.FIND_OBJECT_WAIT_BETWEEN_RETRY);
		}
	}
}
