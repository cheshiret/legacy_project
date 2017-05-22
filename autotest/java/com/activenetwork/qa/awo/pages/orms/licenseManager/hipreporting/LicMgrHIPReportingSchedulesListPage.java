package com.activenetwork.qa.awo.pages.orms.licenseManager.hipreporting;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HIPReportingScheduleInfo;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


public class LicMgrHIPReportingSchedulesListPage extends LicMgrHIPReportingCommonPage{
	private static LicMgrHIPReportingSchedulesListPage _instance = null;

	protected LicMgrHIPReportingSchedulesListPage (){}

	public static LicMgrHIPReportingSchedulesListPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrHIPReportingSchedulesListPage();
		}
		return _instance;
	}

	public boolean exists(){
		return browser.checkHtmlObjectExists(".class", "Html.TABLE",".id","HIPReportScheduleList_LIST");
	}
	
	public void clickAddHIPReportingSchedules(){
		browser.clickGuiObject(".class", "Html.A",".text",new RegularExpression("Add HIP R(r)?eporting Schedules",false));
	}
	
	public boolean checkHIPReportingScheduleIDExisting(String scheduleID){
		return browser.checkHtmlObjectExists(".class", "Html.A",".text",scheduleID);
	}
	
	public void clickHIPReportingScheduleID(String scheduleID){
		browser.clickGuiObject(".class", "Html.A",".text",scheduleID);
	}
	
	public IHtmlObject[] getHIPReportScheduleListTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id","HIPReportScheduleList_LIST");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found the HIP Report Schedule List table object.");
		}
		
		return objs;
	}
	
	public String getHIPReportScheduleID(HIPReportingScheduleInfo scheduleInfo){
		IHtmlObject[] objs = this.getHIPReportScheduleListTable();
		
		IHtmlTable table = (IHtmlTable)objs[0];
		for(int i=1; i<table.rowCount(); i++){
			String licenseYear = table.getCellValue(i, 1);//License Year column
			String status = table.getCellValue(i, 2);//Status column
			String perodStartDate = table.getCellValue(i, 3);//Period Start Date column
			String perodEndDate = table.getCellValue(i, 4);//Period End Date column
			if(scheduleInfo.getLicenseYear().equals(licenseYear) &&
					scheduleInfo.getStatus().equals(status) &&
					DateFunctions.formatDate(scheduleInfo.getPeriodStartDate(), "EEE MMM d yyyy").equals(perodStartDate) &&
					DateFunctions.formatDate(scheduleInfo.getPeriodEndDate(), "EEE MMM d yyyy").equals(perodEndDate)){
				scheduleInfo.setScheduleID(table.getCellValue(i, 0));
			}
		}
		
		Browser.unregister(objs);
		return scheduleInfo.getScheduleID();
	}
	
	public void selectFilterStatus(String status){
		browser.selectDropdownList(".id", new RegularExpression("HIPReportingScheduleSearchCriteria-\\d+\\.status",false), status);
	}
	
	public void selectFilterStatus(int index){
		browser.selectDropdownList(".id", new RegularExpression("HIPReportingScheduleSearchCriteria-\\d+\\.status",false), index);
	}
	
	public void selectShowCurrentRecordOnly(){
		browser.selectCheckBox(".id", new RegularExpression("HIPReportingScheduleSearchCriteria-\\d+\\.currentRecordOnly",false));
	}
	
	public void unSelectShowCurrentRecordOnly(){
		browser.unSelectCheckBox(".id", new RegularExpression("HIPReportingScheduleSearchCriteria-\\d+\\.currentRecordOnly",false));
	}
	
	public void setFilterHIPReportingSchedule(String status, boolean showCurrentRecordOnly){
		if(showCurrentRecordOnly){
			this.selectShowCurrentRecordOnly();
		}else{
			this.unSelectShowCurrentRecordOnly();
		}
		ajax.waitLoading();
		
		if(StringUtil.notEmpty(status)){
			this.selectFilterStatus(status);
		}else{
			this.selectFilterStatus(0);
		}
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A",".text","Go");
	}

	
}
