package com.activenetwork.qa.awo.pages.orms.adminManager.audit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:Verify the audit logs in admin when add,edit,activate,deactivate action.
 * @Preconditions:
 * @SPEC:Log Change to Tax [TC:033707]
 * @Task#:Auto-1301
 * 
 * @author Jasmine
 * @Date  Nov 01, 2012
 */
public class AdminMgrFeeAuditLogsPage extends AdminMgrAuditLogsPage{
	private static AdminMgrFeeAuditLogsPage _instance = null;
	

	public static AdminMgrFeeAuditLogsPage getInstance(){
		if (null == _instance) {
			_instance = new AdminMgrFeeAuditLogsPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "AuditLogSearchCriteria.actionType");
	}
	
	public void setLogArea(String logArea){
		browser.selectDropdownList(".id", "AuditLogSearchCriteria.logArea", logArea);
	}
	
	public String getLogArea(){
		return browser.getDropdownListValue(".id", "AuditLogSearchCriteria.logArea");
	}
	
	public List<String> getLogAreaElement(){
		return browser.getDropdownElements(".id", "AuditLogSearchCriteria.logArea");
	}
	
	public void selectApplication(String application){
		browser.selectDropdownList(".id", "application", application);
	}
	
	public void setActionType(String actionType){
		browser.selectDropdownList(".id", "AuditLogSearchCriteria.actionType", actionType);
	}
	
	public String getActionType(){
		return browser.getDropdownListValue(".id", "AuditLogSearchCriteria.actionType");
	}

	public List<String> getActionTypeElement(){
		return browser.getDropdownElements(".id", "AuditLogSearchCriteria.actionType");
	}
	/**
	 * search fee audit logs.
	 * @param log
	 */
	public void searchFeeAuditLogs(AuditLogInfo log){
		if(StringUtil.notEmpty(log.searchType)){
			this.setSearchType(log.searchType);
		}
//		if(StringUtil.notEmpty(log.searchValue)){
//			this.setSearchValue(log.searchValue);
//		}
		if(log.searchValue != null){ //Need to set as empty
			this.setSearchValue(log.searchValue);
		}
        if(log.stratDate != null){//		if(StringUtil.notEmpty(log.stratDate)){
			this.setStartDate(log.stratDate);
		}
		if(log.endDate != null){// if(StringUtil.notEmpty(log.endDate)){
			this.setEndDate(log.endDate);
		}
		if(StringUtil.notEmpty(log.application)){
			this.selectApplication(log.application);
		}
		if(StringUtil.notEmpty(log.logArea)){
			this.setLogArea(log.logArea);
		}
		if(StringUtil.notEmpty(log.actionType)){
			this.setActionType(log.actionType);
		}
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public boolean isNextbuttonEnable(){
		boolean flag=browser.checkHtmlObjectExists(".class","Html.A",".text","Next");
		return flag;
	}
	
	public void clickNextButton(){
		browser.clickGuiObject(".class", "Html.DIV",".text","Next");
	}
	
	public boolean gotoNextPage(){
		if(this.isNextbuttonEnable()){
			this.clickNextButton();
			ajax.waitLoading();
			this.waitLoading();
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isFirstButtonDisabled(){
		boolean flag=false;
		flag = !browser.checkHtmlObjectExists(".class","Html.A",".text","First");
		return flag;
	}
	
	public void clickFirstButton(){
		browser.clickGuiObject(".class", "Html.DIV",".text","First");
	}
	
	public List<String> getColumnValues( String colName){
		List<String> values = new ArrayList<String>(); 
		do{
			IHtmlObject[] objs = this.getFeeAuditLogsTable();
			IHtmlTable table = (IHtmlTable)objs[objs.length-1];
			int column = table.findColumn(0, colName);
			List<String> colnumValue = table.getColumnValues(column);
			colnumValue.remove(0);
			values.addAll(colnumValue);
			Browser.unregister(objs);
		}while (gotoNextPage());
		return values;
	}
	
	public void verifyColumnValue(String val, String colName){
		List<String> values = this.getColumnValues(colName);
		for (String value : values) {
			if (!value.matches(val)) {
				throw new ErrorOnPageException("Audit Log column value not correct, column is" + colName,
						val, value);
			}
		}
	}
	
	public void verifyDateTimeOrderByDescending(){
		List<String> dateTimevalues = this.getColumnValues("Date/Time");
		for(int i=0;i<dateTimevalues.size()-1;i++){
			String time = dateTimevalues.get(i).replace(",", " ").replace("EDT", "").replace("ECT", "").trim();
			String timeNext = dateTimevalues.get(i+1).replace(",", " ").replace("EDT", "").replace("ECT", "").trim();
			if(DateFunctions.compareDateTime(time, timeNext)<0){
				throw new ErrorOnPageException("Audit Log order should be sort by datetime descending, but time:" + time + " is earlier than time:" + timeNext);
			}
		}
	}
	/**
	 * get fee audit logs table.
	 * @return
	 */
	private IHtmlObject[] getFeeAuditLogsTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".class", "Html.TABLE", ".text", new RegularExpression("Date.*", false));
		if(objs.length<1){
			throw new ErrorOnPageException("No element exist");
		}
		return objs;
	}
	
	public Map<String, String> setFeeDetailsInfo(String detailsInfo){
		Map<String, String> fromToDates = new HashMap<String, String>();
		String[] sub  = detailsInfo.split(",");
		for(int index = 0;index<sub.length;index++){
			fromToDates.put(sub[index].split(":")[0].trim(), sub[index].split(":")[1].trim());
		}
		logger.info(fromToDates.size());
		return fromToDates;
	}
	/**
	 * get audit log info.
	 * @return
	 */
	public List<AuditLogInfo> getAuditLogInfoList(){
		IHtmlObject[] objs = this.getFeeAuditLogsTable();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		List<AuditLogInfo> logsList= new ArrayList<AuditLogInfo>();
		for(int index = 1;index<table.rowCount();index++){
			List<String> rowValue = table.getRowValues(index);
			AuditLogInfo auditLog = new AuditLogInfo();
			auditLog.dateTime = rowValue.get(0).split("[0-9]{2}:[0-9]{2}")[0].trim();
			auditLog.logArea = rowValue.get(1);
			auditLog.action = rowValue.get(2);
			auditLog.actionDetails = rowValue.get(3);
			auditLog.affectedLocation = rowValue.get(4);
			auditLog.userName = rowValue.get(5);
			auditLog.application = rowValue.get(6);
			logsList.add(auditLog);
		}
		return logsList;
	}
	/**
	 * get audit logs info.
	 * @param log
	 * @return
	 */
	public List<AuditLogInfo> getAuditLogInfo(AuditLogInfo log){
		IHtmlObject[] objs = this.getFeeAuditLogsTable();
		IHtmlTable table = (IHtmlTable)objs[1];
		List<AuditLogInfo> logsList= new ArrayList<AuditLogInfo>();
		for(int index = 1;index<table.rowCount();index++){
			List<String> rowValue = table.getRowValues(index);
			String actDate = rowValue.get(0).split("[0-9]{2}:[0-9]{2}")[0].trim();
			if(DateFunctions.compareDates(actDate,log.dateTime) ==0
					&&rowValue.get(1).equals(log.logArea) 
					&&rowValue.get(2).equals(log.action)
					&&rowValue.get(4).equals(log.affectedLocation)
					&&rowValue.get(5).equals(log.userName)
					&&rowValue.get(6).equals(log.application)){
				AuditLogInfo auditLog = new AuditLogInfo();
				auditLog.dateTime = rowValue.get(0).split("[0-9]{2}:[0-9]{2}")[0].trim();
				auditLog.logArea = rowValue.get(1);
				auditLog.action = rowValue.get(2);
				auditLog.actionDetails = rowValue.get(3);
				auditLog.affectedLocation = rowValue.get(4);
				auditLog.userName = rowValue.get(5);
				auditLog.application = rowValue.get(6);
				logsList.add(auditLog);
			}
		}
		System.out.print("");
		Browser.unregister(objs);
		return logsList;
	}
	/**
	 * compare fee audit logs.
	 * @param expectedLog
	 * @return
	 */
	public boolean compareFeeAuditLogs(AuditLogInfo expectedLog){
		List<AuditLogInfo> logList= this.getAuditLogInfo(expectedLog);
		if(logList.size()<1){
			throw new ErrorOnPageException("No list was found");
		}
		AuditLogInfo actualLog = logList.get(0);
		boolean isEqual = true;
		
		isEqual &= DateFunctions.compareDates(expectedLog.dateTime, actualLog.dateTime) == 0;
		isEqual &= MiscFunctions.compareResult("Log Area", expectedLog.logArea, actualLog.logArea);
		isEqual &= MiscFunctions.compareResult("Action", expectedLog.action, actualLog.action);
		isEqual &= MiscFunctions.compareResult("Action Details", expectedLog.actionDetails, logList.get(0).actionDetails);
		isEqual &= MiscFunctions.compareResult("Affected Location", expectedLog.affectedLocation, actualLog.affectedLocation);
		isEqual &= MiscFunctions.compareResult("User Name", expectedLog.userName, actualLog.userName);
		isEqual &= MiscFunctions.compareResult("Application", expectedLog.application, actualLog.application);
		
		return isEqual;
	}
	/**
	 * verify fee audit logs.
	 * @param expectedLog
	 */
	public void verifyFeeAuditLogs(AuditLogInfo expectedLog){
		if(!this.compareFeeAuditLogs(expectedLog)){
			throw new ErrorOnPageException("Fee Audit Log are incorrect.");
		} else logger.info("Fee Audit Log are correct.");
	}
	
	public void verifyFeeAuditLog(AuditLogInfo expectedLog){
		boolean result = true;
		this.searchFeeAuditLogs(expectedLog);
		List<AuditLogInfo> actualLogs = this.getAuditLogInfoList();
		AuditLogInfo actualLog = actualLogs.get(0);
		result &= MiscFunctions.compareResult("Log Area", expectedLog.logArea, actualLog.logArea);
		result &= MiscFunctions.compareResult("Action", expectedLog.action, actualLog.action);
		result &= MiscFunctions.compareResult("Action Details", expectedLog.actionDetails, actualLog.actionDetails);
		result &= MiscFunctions.compareResult("Affected Location", expectedLog.affectedLocation, actualLog.affectedLocation);
		result &= MiscFunctions.compareResult("User Name", expectedLog.userName, actualLog.userName);
		result &= MiscFunctions.compareResult("Application", expectedLog.application, actualLog.application);
		if(!result){
			throw new ErrorOnPageException("--Check logs above.");
		}
	}
	
	public String getMessage() {
		String msg = "";
		Property[] properties=Property.toPropertyArray(".class","Html.DIV",".name","Messages");
		if(browser.checkHtmlObjectExists(properties)){//This is information message
			msg = browser.getObjectText(properties);
		}else{//This is error message
			properties=Property.toPropertyArray(".class","Html.DIV",".name","NOTSET");
			msg = browser.getObjectText(properties);
		}
		return msg;
	}

	public void gotoFirstPage() {
		this.clickFirstButton();
		ajax.waitLoading();
		this.waitLoading();
	}
	
}
