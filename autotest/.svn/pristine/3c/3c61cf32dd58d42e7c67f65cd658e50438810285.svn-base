package com.activenetwork.qa.awo.pages.orms.licenseManager.hipreporting;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrHIPReportingCommonPage extends LicMgrCommonTopMenuPage{
	
	private static LicMgrHIPReportingCommonPage _instance = null;
	
	protected LicMgrHIPReportingCommonPage (){}
	
	public static LicMgrHIPReportingCommonPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrHIPReportingCommonPage();
		}
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression("HIPReportJobs_\\d+",false));
	}
	
	public void clickHIPReportingJobsLabel(){
		browser.clickGuiObject(".class", "Html.A",".text","HIP Reporting Jobs");
	}
	
	public void clickHIPReportingSchedulesLabel(){
		browser.clickGuiObject(".class", "Html.A",".text","HIP Reporting Schedules");
	}
	

}
