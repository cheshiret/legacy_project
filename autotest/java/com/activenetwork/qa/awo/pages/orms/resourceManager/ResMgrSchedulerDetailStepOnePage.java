package com.activenetwork.qa.awo.pages.orms.resourceManager;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.testapi.PageNotFoundException;

/**
 * @author Sara Wang
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ResMgrSchedulerDetailStepOnePage extends ResourceManagerPage{

	static private ResMgrSchedulerDetailStepOnePage _instance = null;
	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected ResMgrSchedulerDetailStepOnePage()
	{}
	static public ResMgrSchedulerDetailStepOnePage getInstance()throws PageNotFoundException {
		if (null == _instance) {
			_instance = new ResMgrSchedulerDetailStepOnePage();
		}

		return _instance;
	}
	
	/** Determine if the Resource Manager Scheduler Detail Page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text","Step 1 of 3: Select Report");
	}

	/** Click on Schedules */
	public void clickSchedules() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Schedules", true);
	}
	
	/** Click on Jobs */
	public void clickJobs() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Schedules Jobs", true);
	}
	
	/**
	 * Select Schedule Type from dropdown list.
	 * @param ScheduleType - Schedule Type
	 */
	public void selectScheduleType(String ScheduleType) {
		browser.selectDropdownList(".id", "ReportMultiInstanceFlag", ScheduleType);
	}
	
	/**
	 * Select scheduler report group from dropdown list.
	 * @param scherptgroup - scheduler report group
	 */
	public void selectScheduleReportGroup(String scherptgroup) {
		browser.selectDropdownList(".id", "_SchedulerReportGroup", scherptgroup);
	}
	
	/**
	 * Select scheduler report from dropdown list.
	 * @param schereport - scheduler report name
	 */
	public void selectScheduleReport(String schereport) {
		browser.selectDropdownList(".id", "_SchedulerReportId", schereport);
	}
	
	/**
	 * Select schedule report format from dropdown list.
	 * @param schereportformat - scheduler report Format name
	 */
	public void selectScheduleReportFormat(String schereportformat) {
		browser.selectDropdownList(".id", "_SchedulerReportFormat", schereportformat);
	}
	
	/** Click on Next */
	public void clickNext() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Next>>", true);
	}
	
	/** Click on Cancel */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", true);
	}
	
	/**
	 * This method used to enter different criteria
	 * @param scheduleType
	 * @param rd
	 */
	public void enterInfoForStepOne(String scheduleType,ReportData rd)
	{
	  	logger.info("Step 1: Select Report.");
	  	
	  	this.selectScheduleType(scheduleType);
	  	this.waitLoading();
	  	this.selectScheduleReportGroup(rd.group);
	  	this.waitLoading();
	  	this.selectScheduleReport(rd.reportName);
	  	this.waitLoading();
	  	this.selectScheduleReportFormat(rd.reportFormat);
	  	this.waitLoading();
	}
	
}
