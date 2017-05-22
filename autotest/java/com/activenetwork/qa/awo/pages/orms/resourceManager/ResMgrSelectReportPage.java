/*
 * $Id: ResMgrSelectReportPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $
 */

package com.activenetwork.qa.awo.pages.orms.resourceManager;

import java.util.List;


/**
 * @author cguo
 */
public class ResMgrSelectReportPage extends ResourceManagerPage {

	private ResMgrSelectReportPage() {

	}

	private static ResMgrSelectReportPage instance = null;

	public static ResMgrSelectReportPage getInstance() {
		if (null == instance) {
			instance = new ResMgrSelectReportPage();
		}
		return instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id",
				"_SchedulerReportGroup");
	}

	/** Click on OK. */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", true);
	}

	/** Go to Home page. */
	public void clickHome() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Home");
	}

	/**
	 * Select report group from dropdown list.
	 *
	 * @param rptgroup
	 *            - report group
	 */
	public void selectReportGroup(String rptgroup) {
		waitForGrounp(rptgroup);
		browser.selectDropdownList(".id", "_SchedulerReportGroup", rptgroup);
	}

	/**
	 * Select report from dropdown list.
	 *
	 * @param report
	 *            - report name
	 */
	public void selectReport(String report) {
		waitForReport(report);
		browser.selectDropdownList(".class", "Html.SELECT", ".id", "report_id",
				report);
	}

	public List<String> getReportOptions() {
		return browser.getDropdownElements(".id", "report_id");
	}
	
	public boolean isReportExists(String report) {
		List<String> allReports = getReportOptions();
		return allReports.contains(report);
	}
	
	/**
	 * Select report from dropdown list.
	 *
	 * @param report
	 *            - report name
	 */
	public void selectReport(int index) {
		browser.selectDropdownList(".class", "Html.SELECT", ".id", "report_id",
				index);
	}

	/** Click on Schedule */
	public void clickSchedule() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Schedule", true);
	}

	/** Click on Cancel */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", true);
	}

	public void waitForGrounp(String toSelect) {
		browser.dropdownOptionWaitExists(".id", "_SchedulerReportGroup",
				toSelect);
	}

	public void waitForReport(String toSelect) {
		browser.dropdownOptionWaitExists(".id", "report_id", toSelect);
	}

	public boolean checkReportExists(String reportName) {
		List<String> list = browser.getDropdownElements(".id", "report_id");
		return list.contains(reportName);
	}
	
	/**
	 * click Request Report
	 * 
	 */
	public void clickRequestReport() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Request Report", true);
	}

}
