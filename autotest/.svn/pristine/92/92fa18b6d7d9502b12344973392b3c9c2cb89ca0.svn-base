package com.activenetwork.qa.awo.pages.orms.licenseManager.hipreporting;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HIPReportingScheduleInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrEditHIPReportingScheduleWidget extends DialogWidget{
	private static LicMgrEditHIPReportingScheduleWidget _instance = null;

	protected LicMgrEditHIPReportingScheduleWidget (){}

	public static LicMgrEditHIPReportingScheduleWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrEditHIPReportingScheduleWidget();
		}

		return _instance;
	}

	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.TD", ".text", "HIP Reporting Schedule Details",getWidget()[0]);
	}
	
	public String getScheduleID(){
		return browser.getTextFieldValue(".id", new RegularExpression("HIPReportScheduleView-\\d+\\.id",false));
	}
	
	public String getStatus(){
		return browser.getDropdownListValue(".id", new RegularExpression("HIPReportScheduleView-\\d+\\.status",false));
	}
	
	public String getLicenseYear(){
		return browser.getDropdownListValue(".id", new RegularExpression("HIPReportScheduleView-\\d+\\.hipLicenseYear",false));
	}
	
	public String getPeriodStartDate(){
		return browser.getTextFieldValue(".id", new RegularExpression("HIPReportScheduleView-\\d+\\.periodStartDate_ForDisplay",false));
	}
	
	public String getPeriodEndDate(){
		return browser.getTextFieldValue(".id", new RegularExpression("HIPReportScheduleView-\\d+\\.periodEndDate_ForDisplay",false));
	}
	
	public IHtmlObject[] getExecutionDatesTextObject(){
		return browser.getTextField(".id", new RegularExpression("HIPReportingExecuteDate-\\d+\\.executionDate\\:DATE_ForDisplay",false));
	}
	
	public void clickRemove(){
		browser.clickGuiObject(".class", "Html.A",".text","Remove");
	}
	
	public IHtmlObject[] getRemoveObject(){
		return browser.getHtmlObject(".class", "Html.A",".text","Remove");
	}
	
	public void clickAddDate(){
		browser.clickGuiObject(".class", "Html.A",".text","Add Date");
	}
	
	public boolean checkAddDateExisting(){
		return browser.checkHtmlObjectExists(".class", "Html.A",".text","Add Date");
	}
	
	public boolean checkRemoveExisting(){
		return browser.checkHtmlObjectExists(".class", "Html.A",".text","Remove");
	}
	
	public void setExecutionDates(String executionDate,int index){
		browser.setTextField(".id", new RegularExpression("HIPReportingExecuteDate-\\d+\\.executionDate\\:DATE_ForDisplay",false),executionDate ,index);
	}
	
	public void clickLicenseYearSpace(){
		browser.clickGuiObject(".class", "Html.LABEL",".text",new RegularExpression("License Year.*",false));
	}
	
	public void setExecutionDates(List<String> executionDates){
		if(this.checkAddDateExisting()){
			this.clickAddDate();
			ajax.waitLoading();
			
		}else{
			this.clickRemove();
			ajax.waitLoading();
			this.clickAddDate();
			ajax.waitLoading();
		}
		
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
	
	public void selectStatus(String status){
		browser.selectDropdownList(".id", new RegularExpression("HIPReportScheduleView-\\d+\\.status",false), status);
	}
	
	public void editHIPReportingScheduleInfo(List<String> executionDates,String status){
		this.setExecutionDates(executionDates);
		if(StringUtil.notEmpty(status)){
			this.selectStatus(status);
		}
	}
	
	public List<String> getExecutionDates(){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("HIPReportingExecuteDate-\\d+\\.executionDate\\:DATE",false));
		List<String> executionDates = new ArrayList<String>();
		for(int i=0; i<objs.length; i++){
			String text=((IText)objs[i]).getText();
			executionDates.add(text);
		}
		
		Browser.unregister(objs);
		return executionDates;
	}
	
	public String getSpanText(Object divID){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",".id",divID);
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found div object" );
		}
		
		IHtmlObject[] spanObjs = browser.getHtmlObject(".class", "Html.SPAN",objs[0]);
		if(spanObjs.length<1){
			throw new ItemNotFoundException("Did not found span object" );
		}
		
		String text = spanObjs[1].text();
		Browser.unregister(spanObjs);
		Browser.unregister(objs);
		
		return text;
	}
	
	public String getCreationUser(){
		return this.getSpanText(new RegularExpression("HIPReportScheduleView-\\d+\\.creationUser",false)).replaceAll(", ", ",");
	}
	
	public String getCreationLocation(){
		return this.getSpanText(new RegularExpression("HIPReportScheduleView-\\d+\\.creationLocation",false));
	}
	
	public String getCreationDate(){
		return this.getSpanText(new RegularExpression("HIPReportScheduleView-\\d+\\.creationDate\\:DATE_TIME2",false));
	}
	
	public String getLastUpdatedUser(){
		return this.getSpanText(new RegularExpression("HIPReportScheduleView-\\d+\\.updatedUser",false)).replaceAll(", ", ",");
	}
	
	public String getLastUpdatedLocation(){
		return this.getSpanText(new RegularExpression("HIPReportScheduleView-\\d+\\.updatedLocation",false));
	}
	
	public String getLastUpdatedDate(){
		return this.getSpanText(new RegularExpression("HIPReportScheduleView-\\d+\\.updatedDate\\:DATE_TIME2",false));
	}
	
	public HIPReportingScheduleInfo getHIPReportingScheduleInfo(){
		HIPReportingScheduleInfo scheduleInfo = new HIPReportingScheduleInfo();
		scheduleInfo.setScheduleID(this.getScheduleID());
		scheduleInfo.setLicenseYear(this.getLicenseYear());
		scheduleInfo.setPeriodStartDate(this.getPeriodStartDate());
		scheduleInfo.setPeriodEndDate(this.getPeriodEndDate());
		scheduleInfo.setExecutionDates(this.getExecutionDates());
		scheduleInfo.setCreationUser(this.getCreationUser());
		scheduleInfo.setCreationLocation(this.getCreationLocation());
		scheduleInfo.setCreationDate(this.getCreationDate());
		scheduleInfo.setLastUpdatedUser(this.getLastUpdatedUser());
		scheduleInfo.setLastUpdatedLocation(this.getLastUpdatedLocation());
		scheduleInfo.setLastUpdatedDate(this.getLastUpdatedDate());
		
		return scheduleInfo;
	}
	
	public boolean compareHIPReportingScheduleInfo(HIPReportingScheduleInfo expReportingScheduleInfo){
		boolean result = true;
		HIPReportingScheduleInfo actReportingScheduleInfo = this.getHIPReportingScheduleInfo();
		
		result &= MiscFunctions.compareResult("Schedule ID", expReportingScheduleInfo.getScheduleID(), actReportingScheduleInfo.getScheduleID());
		result &= MiscFunctions.compareResult("License Year", expReportingScheduleInfo.getLicenseYear(), actReportingScheduleInfo.getLicenseYear());
		result &= MiscFunctions.compareResult("Period Start date", expReportingScheduleInfo.getPeriodStartDate(), actReportingScheduleInfo.getPeriodStartDate());
		result &= MiscFunctions.compareResult("Period End date", expReportingScheduleInfo.getPeriodEndDate(), actReportingScheduleInfo.getPeriodEndDate());
		
		for(int i=0; i<expReportingScheduleInfo.getExecutionDates().size(); i++){
			result &= MiscFunctions.compareResult("Executions Date", expReportingScheduleInfo.getExecutionDates().get(i), actReportingScheduleInfo.getExecutionDates().get(i));
		}
		result &= MiscFunctions.compareResult("Creation user", expReportingScheduleInfo.getCreationUser(), actReportingScheduleInfo.getCreationUser());
		result &= MiscFunctions.compareResult("Creation Location", expReportingScheduleInfo.getCreationLocation(), actReportingScheduleInfo.getCreationLocation());
		result &= MiscFunctions.compareResult("Creation Date", expReportingScheduleInfo.getCreationDate(), actReportingScheduleInfo.getCreationDate());
		result &= MiscFunctions.compareResult("Last Updated User", expReportingScheduleInfo.getLastUpdatedUser(), actReportingScheduleInfo.getLastUpdatedUser());
		result &= MiscFunctions.compareResult("Last Updated Location", expReportingScheduleInfo.getLastUpdatedLocation(), actReportingScheduleInfo.getLastUpdatedLocation());
		result &= MiscFunctions.compareResult("Last Updated Date", expReportingScheduleInfo.getLastUpdatedDate(), actReportingScheduleInfo.getLastUpdatedDate());
		
		return result;
	}
	
	public String getMessage(){
		return browser.getObjectText(".class", "Html.DIV",".id","NOTSET");
	}
	
	public boolean checkScheduleIDIsEnable(){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("HIPReportScheduleView-\\d+\\.id",false));
		
		boolean isEnable = objs[0].isEnabled();
		Browser.unregister(objs);
		return isEnable;
	}
	
	public boolean checkStatusIsEnable(){
		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("HIPReportScheduleView-\\d+\\.status",false));
		
		boolean isEnable = objs[0].isEnabled();
		Browser.unregister(objs);
		return isEnable;
	}
	
	public boolean checkLicenseYearIsEnable(){
		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("HIPReportScheduleView-\\d+\\.hipLicenseYear",false));

		boolean isEnable = objs[0].isEnabled();
		Browser.unregister(objs);
		return isEnable;
	}
	
	public boolean checkPeriodStartDateIsEnable(){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("HIPReportScheduleView-\\d+\\.periodStartDate_ForDisplay",false));

		boolean isEnable = objs[0].isEnabled();
		Browser.unregister(objs);
		return isEnable;
	}
	
	public boolean checkPeriodEndDateIsEnable(){
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("HIPReportScheduleView-\\d+\\.periodEndDate_ForDisplay",false));

		boolean isEnable = objs[0].isEnabled();
		Browser.unregister(objs);
		return isEnable;
	}
	
	public boolean checkExecutionDatesIsEnable(){
		boolean isEnable = true;
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("HIPReportingExecuteDate-\\d+\\.executionDate\\:DATE",false));
		
		for(int i=0; i<objs.length; i++){
			isEnable &= objs[i].isEnabled();
		}
		
		Browser.unregister(objs);
		return isEnable;
	}
	
}
