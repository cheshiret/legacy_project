/*
 * $Id: FldMgrReportPage.java 7017 2009-01-16 23:15:02Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.reports;

import java.util.List;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.Property;

/**
 * TODO: enter class description
 * 
 * @author jdu
 */
public class OrmsRequestReportPage extends OrmsPage {

	/**
	 * Script Name   : FldMgReportPage
	 * Generated     : Jan 19, 2007 7:05:31 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2007/01/19
	 */
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsRequestReportPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsRequestReportPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsRequestReportPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsRequestReportPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Request Report");
	}

	private Property[] reportGroup() {
		return Property.toPropertyArray(".id", "_SchedulerReportGroup");
	}
	
	protected Property[] report() {
		return Property.toPropertyArray(".id", "report_id");
	}
	
	/**
	 * Select report group
	 * @param group
	 */
	public void selectReportGroup(String group) {
		browser.selectDropdownList(reportGroup(), group);
	}

	/**
	 * Select report
	 * @param report
	 */
	public void selectReport(String report) {
		browser.selectDropdownList(".id", "report_id", report);
	}

	/**click cancel button*/
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**
	 * Select report and run it
	 * @param report
	 * @throws PageNotFoundException
	 */
	public void runReport(String report) throws PageNotFoundException {
		this.selectReport(report);
		this.clickOK();
	}
	
	public List<String> getReportOptions() {
		return browser.getDropdownElements(report());
	}
	
	public boolean isReportExists(String report) {
		List<String> allReports = getReportOptions();
		return allReports.contains(report);
	}
}
