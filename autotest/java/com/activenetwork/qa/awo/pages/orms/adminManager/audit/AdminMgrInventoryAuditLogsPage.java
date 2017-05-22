package com.activenetwork.qa.awo.pages.orms.adminManager.audit;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class AdminMgrInventoryAuditLogsPage extends AdminMgrAuditLogsPage{

	private static AdminMgrInventoryAuditLogsPage _instance = null;

	public static AdminMgrInventoryAuditLogsPage getInstance(){
		if (null == _instance) {
			_instance = new AdminMgrInventoryAuditLogsPage();
		}

		return _instance;
	}

	/**Determine whether the object exist*/
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("application", false));
	}

	public void setSearchType(String searchType){
		browser.selectDropdownList(".id", "AuditLogSearchCriteria.searchBy", searchType);
	}

	public void setSearchValue(String searchValue){
		browser.setTextField(".id", "AuditLogSearchCriteria.searchByValue", searchValue);
	}

	public void setStartDate(String stratDate){
		browser.setTextField(".id", "AuditLogSearchCriteria.startDate_ForDisplay", stratDate);
	}

	public void setEndDate(String endDate){
		browser.setTextField(".id", "AuditLogSearchCriteria.endDate_ForDisplay", endDate);
	}

	public void setApplication(String application){
		browser.selectDropdownList(".id", "application", application);
	}

	public void setLogArea(String logArea){
		browser.selectDropdownList(".id", "AuditLogSearchCriteria.logArea", logArea);
	}

	public void setActionType(String actionType){
		browser.selectDropdownList(".id", "AuditLogSearchCriteria.actionType", actionType);
	}

	public void setUpSearchCriteria(AuditLogInfo searchCriteria){
		this.setSearchType(searchCriteria.searchType);
		this.setSearchValue(searchCriteria.searchValue);
		this.setStartDate(searchCriteria.stratDate);
		this.setEndDate(searchCriteria.endDate);
		if(searchCriteria.application.length()>0 &&!searchCriteria.application.equals("All")){
			if(!searchCriteria.application.contains("Manager")){
				searchCriteria.application = searchCriteria.application.trim() + " Manager";
			}
			this.setApplication(searchCriteria.application);
		}
		
		this.setLogArea(searchCriteria.logArea);
		this.setActionType(searchCriteria.actionType);
	}

	public void searchLogs(AuditLogInfo searchCriteria){
		this.setUpSearchCriteria(searchCriteria);
		this.clickSearch();
		this.waitLoading();
	}
	private IHtmlObject[] getAuditLogsTable(){
		IHtmlObject[] tableObjs = browser.getHtmlObject(".class", "Html.Table", ".text", new RegularExpression("^Date/Time.*", false));
		if(null == tableObjs || tableObjs.length <= 0){
			throw new ErrorOnPageException("Can't find the table!");
		}
		return tableObjs;
	}
	
	private List<String> getColumnValues(String colName){
		IHtmlObject[] objs = getAuditLogsTable();
		
		IHtmlTable grid = (IHtmlTable)objs[0];
		
		int colNum = grid.findColumn(0, colName);
		
		List<String> values = grid.getColumnValues(colNum);
		Browser.unregister(objs);
		
		return values;
	}
	
	public List<String> getActions(){
		return getColumnValues("Action");
	}
	
	public List<String> getActionDetails(){
		return getColumnValues("Action Details");
	}

	public List<List<String>> getLogs(){
		IHtmlObject[] objs = this.getAuditLogsTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<List<String>> logs = new ArrayList<List<String>>();
		// 1st line is title.
		for(int row=1; row<table.rowCount();row++){
			logs.add(table.getRowValues(row));
		}
		Browser.unregister(objs);
		return logs;
	}
	/**
	 * get inventory audit logs.
	 * @param logInfo
	 * @return
	 */
	public List<AuditLogInfo> getAuditLogsInfo(AuditLogInfo logInfo){
		IHtmlObject[] tableObjs = this.getAuditLogsTable();
		IHtmlTable table = (IHtmlTable)tableObjs[0];
		List<AuditLogInfo> logsList= new ArrayList<AuditLogInfo>();
		for(int i = 1;i<table.rowCount();i++){
			List<String> rowValue = table.getRowValues(i);
			String actDate = rowValue.get(0).split("[0-9]{2}:[0-9]{2}")[0].trim();
			if(DateFunctions.compareDates(DateFunctions.formatDate(actDate,"yyyy/M/d"), DateFunctions.formatDate(logInfo.dateTime,"yyyy/M/d")) ==0
					&&rowValue.get(1).equals(logInfo.logArea)
					&&rowValue.get(2).equals(logInfo.action)
					&&rowValue.get(4).equals(logInfo.affectedLocation)
					&&rowValue.get(5).equals(logInfo.userName)
					&&rowValue.get(6).equals(logInfo.application.split(" ")[0])){
				AuditLogInfo auditLog = new AuditLogInfo();
				auditLog.dateTime = rowValue.get(0);
				auditLog.logArea = rowValue.get(1);
				auditLog.action = rowValue.get(2);
				auditLog.actionDetails = rowValue.get(3);
				auditLog.affectedLocation = rowValue.get(4);
				auditLog.userName = rowValue.get(5);
				auditLog.application = rowValue.get(6);
				logsList.add(auditLog);
			}
		}
		Browser.unregister(tableObjs);
		return logsList;
}
	/**
	 * compare inventory audit logs.
	 * @param expectedLog
	 * @return
	 */
	public boolean compareInvAuditLogs(AuditLogInfo expectedLog){
		List<AuditLogInfo> logList= this.getAuditLogsInfo(expectedLog);
		if(logList.size()<1){
			throw new ErrorOnPageException("No list was found");
		}
		boolean isEqual =MiscFunctions.compareResult("Action Details", expectedLog.actionDetails, logList.get(0).actionDetails);
		return isEqual;
	}
	/**
	 * verify audit logs.
	 * @param expectedLog
	 */
	public void verifyInvAuditLogs(AuditLogInfo expectedLog){
		if(!this.compareInvAuditLogs(expectedLog)){
			throw new ErrorOnPageException("Inventory Audit Log are incorrect.");
		} else logger.info("Inventory Audit Log are correct.");
		
	}
	
	public List<AuditLogInfo> getAuditLogsInfo(boolean justFirst){
		IHtmlObject[] tableObjs = this.getAuditLogsTable();
		IHtmlTable table = (IHtmlTable)tableObjs[0];
		List<AuditLogInfo> logsList= new ArrayList<AuditLogInfo>();
		for(int i = 1;i<table.rowCount();i++){
			List<String> rowValue;
			if(justFirst){
				if(i==1){
					rowValue = table.getRowValues(i);
				}else{
					break;
				}
			}else{
				rowValue = table.getRowValues(i);
			}
			AuditLogInfo auditLog = new AuditLogInfo();
			auditLog.dateTime = rowValue.get(0).split("[0-9]{2}:[0-9]{2}")[0].trim().replace(",", " ");
			auditLog.logArea = rowValue.get(1);
			auditLog.action = rowValue.get(2);
			auditLog.actionDetails = rowValue.get(3);
			auditLog.affectedLocation = rowValue.get(4);
			auditLog.userName = rowValue.get(5);
			auditLog.application = rowValue.get(6);
			logsList.add(auditLog);
		}
		Browser.unregister(tableObjs);
		return logsList;
	}
	
	public List<AuditLogInfo> getAuditLogsInfo(){
		return getAuditLogsInfo(false);
}
	public boolean compareInventoryAuditLogsDetailInfo(AuditLogInfo expectedLog, boolean justFirst){
		List<AuditLogInfo> actAuditList = this.getAuditLogsInfo(justFirst);
		boolean result = true;
		
		if(actAuditList.size() != 1){
			throw new ErrorOnPageException("Audit list info is not correct.");
		}
		
		result &= MiscFunctions.compareResult("Date Time info", DateFunctions.formatDate(expectedLog.dateTime.replaceAll("  ", " "), "M/d/yyyy"), DateFunctions.formatDate(actAuditList.get(0).dateTime.replaceAll("  ", " "), "M/d/yyyy"));
		result &= MiscFunctions.compareResult("Log Area", expectedLog.logArea, actAuditList.get(0).logArea);
		result &= MiscFunctions.compareResult("Action", expectedLog.action, actAuditList.get(0).action);
		
		String actionDetails = actAuditList.get(0).actionDetails;
		if(actionDetails.contains("Inventory Date/Time")){
			actionDetails = this.removeTime(actionDetails);
		}
		result &= MiscFunctions.compareResult("Action Detail", expectedLog.actionDetails, actionDetails);
		result &= MiscFunctions.compareResult("Affected Location", expectedLog.affectedLocation, actAuditList.get(0).affectedLocation);
		result &= MiscFunctions.compareResult("User Name", expectedLog.userName, actAuditList.get(0).userName);
		result &= MiscFunctions.compareResult("Application", expectedLog.application.split(" ")[0], actAuditList.get(0).application);
		
		return result;
	}
	
	
	public boolean compareInventoryAuditLogsDetailInfo(AuditLogInfo expectedLog){
		return compareInventoryAuditLogsDetailInfo(expectedLog, false);
	}
	
	private String removeTime(String str){
		int i = str.indexOf("Inventory Date/Time:");
		int j = str.indexOf("Booking ID");
		String s1 = str.substring(0, i);
		String s2 = str.substring(j);
		return s1 + s2;
	}
	
	public void verifyInventoryAuditLogsDetailInfo(AuditLogInfo expectedLog, boolean justfirst){
		logger.info("Verify Inventroy audit log info.");
		boolean result = this.compareInventoryAuditLogsDetailInfo(expectedLog, justfirst);
		
		if(!result){
			throw new ErrorOnPageException("Audit list info is not correct. Please check log.");
		}else logger.info("Audit list info is correct.");
	}
	
	
	public void verifyInventoryAuditLogsDetailInfo(AuditLogInfo expectedLog){
		verifyInventoryAuditLogsDetailInfo(expectedLog, false);
	}
	
	public void verifyColumnValue(String val, String colName){
		IHtmlObject[] objs = this.getAuditLogsTable();
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		int column = table.findColumn(0, colName);
		List<String> values = table.getColumnValues(column);
		values.remove(0);
		for (String value : values) {
			if (!value.contains(val)) {
				throw new ErrorOnPageException("Audit Log column value not correct, column is" + colName,
						val, value);
			}
		}
		Browser.unregister(objs);
	}
}
