/*
 * $Id: InvMgrSitesAlertPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;


/**
 * @author cguo
 */
public class InvMgrSitesAlertPage extends InventoryManagerPage {

	/**
	 * Script Name   : InvMgrSitesAlertPage
	 * Generated     : Jun 20, 2005 9:41:57 AM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2005/06/20
	 */

	private static InvMgrSitesAlertPage _instance = null;

	public static InvMgrSitesAlertPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrSitesAlertPage();
		}

		return _instance;
	}

	protected InvMgrSitesAlertPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "OK");
	}

	/** Click on Notes & Alerts link. */
	public void goNotesAlert() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Notes & Alerts");
	}

	/**
	 * Fill in notes.
	 * @param notes
	 */
	public void setNotes(String notes) {
		browser.setTextArea(".id", "note", notes);
	}

	/**
	 * Fill in alert info.
	 * @param alert
	 */
	public void settAlerts(String alert) {
		browser.setTextArea(".id", "alert", alert);
	}

	/**
	 * Fill in alert start date.
	 * @param startDate
	 */
	public void setAlertStartDate(String startDate) {
		browser.setTextField(".id", "alertStartDate", startDate);
	}

	/**
	 * Fill in alert end date.
	 * @param endDate
	 */
	public void setAlertEndDate(String endDate) {
		browser.setTextField(".id", "alertEndDate", endDate);
	}

	/** Click on OK link. */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/** Click on Cancel link. */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

}
