/*
 * $Id: ResMgrSelectParkSumPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.resourceManager;

/**
 * @author cguo
 */
public class ResMgrSelectParkSumPage extends ResourceManagerPage {

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id",
				"Park");
	}

	/** Click on OK.*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**
	 * Select park from dropdown list.
	 * @param park - park name
	 */
	public void selectPark(String park) {
		browser.selectDropdownList(".id", "Park", park);
	}

	/**
	 * Fill in start date.
	 * @param date - start date
	 */
	public void setStartDate(String date) {
		browser.setTextField(".id", "StartDate", date);
	}

	/**
	 * Fill in end date.
	 * @param date - end date
	 */
	public void setEndDate(String date) {
		browser.setTextField(".id", "EndDate", date);
	}

	/**
	 * Fill in all park sum data.
	 * @param park - park name
	 * @param startDate - start date
	 * @param endDate - end date
	 */
	public void setupParkSumData(String park, String startDate, String endDate) {
		this.selectPark(park);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
	}

}
