package com.activenetwork.qa.awo.pages.orms.resourceManager;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class ResMgrTodayRequestRptPage extends ResMgrCommomTopMenuPage {

	private ResMgrTodayRequestRptPage() {
	}

	private static ResMgrTodayRequestRptPage instance = null;

	public static ResMgrTodayRequestRptPage getInstance() {
		if (null == instance) {
			instance = new ResMgrTodayRequestRptPage();
		}
		return instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Delete");
	}
	
	public void clickTodayRequestRptLink(){
		browser.clickGuiObject(".class","Html.A",".text","Today's Requested Reports");
	}
	
	public boolean checkViewReportExist(String reportName,String reportFormat){
		RegularExpression rex = new RegularExpression("javascript:invokeAction\\( \"ViewReportOnline\\.do\", \"\", \"ReportSchedulerWorker\".*"+reportName+".*"+reportFormat+".*",false);
		return browser.checkHtmlObjectExists(".class","Html.A",".href",rex);
	}
	
	public void clickViewReport(String reportName,String reportFormat){
		RegularExpression rex = new RegularExpression("javascript:invokeAction\\( \"ViewReportOnline\\.do\", \"\", \"ReportSchedulerWorker\".*"+reportName+".*"+reportFormat+".*",false);
		browser.clickGuiObject(".class","Html.A",".href",rex);
	}
	
	/**
	 * This method used to view today requested report online
	 * @param reportName
	 * @param deliveryMethod
	 */
	public void viewReport(String reportName,String reportFormat){
		boolean found = checkViewReportExist(reportName, reportFormat);
		int count = 0;
		while(!found){
			clickTodayRequestRptLink();
			waitLoading();
			found = checkViewReportExist(reportName, reportFormat);
			if(count>OrmsConstants.FILE_DIALOG_LONG_SLEEP){
				throw new ItemNotFoundException(reportName+" is not run in "+count+" seconds.");
			}
			Browser.sleep(1); 
			count++;
		}
		clickViewReport(reportName, reportFormat);
	}
}
