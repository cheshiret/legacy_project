package com.activenetwork.qa.awo.pages.orms.licenseManager.hipreporting;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HIPReportingScheduleInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrAddHIPReportingScheduleWidget extends DialogWidget{
	private static LicMgrAddHIPReportingScheduleWidget _instance = null;
	
	protected LicMgrAddHIPReportingScheduleWidget (){}
	
	public static LicMgrAddHIPReportingScheduleWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddHIPReportingScheduleWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.TD", ".text", "HIP Reporting Schedule Details",getWidget()[0]);
	}
	
	public void selectLicenseYear(String licenseYear){
		browser.selectDropdownList(".id", new RegularExpression("HIPReportScheduleView-\\d+\\.hipLicenseYear",false), licenseYear);
	}
	
	public String setPeriodStartDate(String periodStartDate){
		return this.setDateAndGetMessage(Property.toPropertyArray(".id", new RegularExpression("HIPReportScheduleView-\\d+\\.periodStartDate_ForDisplay",false)), periodStartDate);
	}
	
	public String setPeriodEndDate(String periodEndDate){
		return this.setDateAndGetMessage(Property.toPropertyArray(".id", new RegularExpression("HIPReportScheduleView-\\d+\\.periodEndDate_ForDisplay",false)), periodEndDate);
	}
	
	public void setExecutionDates(String executionDate,int index){
		browser.setTextField(".id", new RegularExpression("HIPReportingExecuteDate-\\d+\\.executionDate\\:DATE_ForDisplay",false),executionDate ,index);
	}
	
	public IHtmlObject[] getExecutionDatesTextObject(){
		return browser.getTextField(".id", new RegularExpression("HIPReportingExecuteDate-\\d+\\.executionDate\\:DATE_ForDisplay",false));
	}
	
	public void clickRemove(){
		browser.clickGuiObject(".class", "Html.A",".text","Remove");
	}
	
	public void clickAddDate(){
		browser.clickGuiObject(".class", "Html.A",".text","Add Date");
	}
	
	public IHtmlObject[] getRemoveObject(){
		return browser.getHtmlObject(".class", "Html.A",".text","Remove");
	}
	
	public void setExecutionDates(List<String> executionDates){
		IHtmlObject[] removeObjs = this.getRemoveObject();
		for(int i=0; i<removeObjs.length-1; i++){
			this.clickRemove();
			ajax.waitLoading();
		}
		
		for(int i=0; i<executionDates.size(); i++){
			if(i >0){
				this.clickAddDate();
				ajax.waitLoading();
			}
			this.setExecutionDates(executionDates.get(i), i);
			this.clickLicenseYearSpace();
		}
		           
		Browser.unregister(removeObjs);
	}
	
	public void clickLicenseYearSpace(){
		browser.clickGuiObject(".class", "Html.LABEL",".text",new RegularExpression("License Year.*",false));
	}
	
	public String setHIPReportingScheduleInfo(HIPReportingScheduleInfo reportingScheduleInfo){
		String msg = "";
		this.selectLicenseYear(reportingScheduleInfo.getLicenseYear());
		if(null != reportingScheduleInfo.getPeriodStartDate()){
			msg = this.setPeriodStartDate(reportingScheduleInfo.getPeriodStartDate());
			this.clickLicenseYearSpace();
		}
		
		if(null != reportingScheduleInfo.getPeriodEndDate()){
			msg = this.setPeriodEndDate(reportingScheduleInfo.getPeriodEndDate());
			this.clickLicenseYearSpace();
		}		
		this.setExecutionDates(reportingScheduleInfo.getExecutionDates());
		return msg;
	}
	
	public String getMessage(){
		return browser.getObjectText(".class", "Html.DIV",".id","NOTSET");
	}
}
