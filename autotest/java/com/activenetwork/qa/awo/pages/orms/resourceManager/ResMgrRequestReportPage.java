package com.activenetwork.qa.awo.pages.orms.resourceManager;

/**
 * @author Sara Wang
 */

public class ResMgrRequestReportPage extends ResourceManagerPage {
	
	static private ResMgrRequestReportPage _instance = null;

	private ResMgrRequestReportPage(){}
	
	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	static public ResMgrRequestReportPage getInstance() {
		if (null == _instance) {
			_instance = new ResMgrRequestReportPage();
		}

		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text","Request Report");
	}
	
	/* Click "Request Report" tab */
	public void clickRequestReport()
	{
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Request Report");
	}
	
	/* Click "Today's Requested Reports" tab */
	public void clickTodayRequestReport()
	{
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Today's Requested Reports");
	}
	
	/**
	 * Select report group from dropdown list.
	 * @param reportGroup - report group
	 */
	public void selectReportGroup(String reportGroup) {
		browser.selectDropdownList(".class", "Html.SELECT", ".id", "_SchedulerReportGroup", reportGroup);
	}
	
	/**
	 * Select report name from dropdown list.
	 * @param reportName - report name
	 */
	public void selectReportName(String reportName) {
		browser.selectDropdownList(".class", "Html.SELECT", ".id", "report_id", reportName);
	}
	
	/* Click Ok button */
	public void clickOK()
	{
	  	browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	/* Click Schedule button */
	public void clickSchedule()
	{
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Schedule");
	}
	
	/* Click Cancel button */
	public void clickCancel()
	{
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
}


