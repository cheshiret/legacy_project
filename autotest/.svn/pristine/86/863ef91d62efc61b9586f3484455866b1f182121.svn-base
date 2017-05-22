package com.activenetwork.qa.awo.pages.orms.licenseManager.hipreporting;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HIPReportingJobInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrHIPReportingJobsListPage extends LicMgrHIPReportingCommonPage{
	private static LicMgrHIPReportingJobsListPage _instance = null;
	
	protected LicMgrHIPReportingJobsListPage (){}
	
	public static LicMgrHIPReportingJobsListPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrHIPReportingJobsListPage();
		}
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class", "Html.TABLE",".text",new RegularExpression("Job ID(| )Status(| )License Year.*",false));
	}
	
	public void selectSearchBy(String searchBy){
		browser.selectDropdownList(".id", new RegularExpression("HIPReportSearchCriteria-\\d+\\.searchType",false), searchBy);
	}
	
	public void selectSearchBy(int index){
		browser.selectDropdownList(".id", new RegularExpression("HIPReportSearchCriteria-\\d+\\.searchType",false), index);
	}
	
	
	public void setSearchValue(String searchValue){
		browser.setTextField(".id", new RegularExpression("HIPReportSearchCriteria-\\d+\\.searchValue",false), searchValue, true);
	}
	
	public void selectHIPReportingJobStatus(String status){
		browser.selectDropdownList(".id", new RegularExpression("HIPReportSearchCriteria-\\d+\\.jobStatus",false), status);
	}
	
	public void selectHIPReportingJobStatus(int index){
		browser.selectDropdownList(".id", new RegularExpression("HIPReportSearchCriteria-\\d+\\.jobStatus",false), index);
	}
	
	public void setSearchCriteria(String searchType, String searchValue, String reportingJobStatus){
		if(StringUtil.notEmpty(searchType)){
			this.selectSearchBy(searchType);
		}else{
			this.selectSearchBy(0);
		}
		
		this.setSearchValue(searchValue);
		
		if(StringUtil.notEmpty(reportingJobStatus)){
			this.selectHIPReportingJobStatus(reportingJobStatus);
		}else{
			this.selectHIPReportingJobStatus(0);
		}
	}
	
	public void clickSearch(){
		browser.clickGuiObject(".class", "Html.A",".text","Search");
	}
	
	public IHtmlObject[] getHIPReportingJobListTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id","grid_\\d+_LIST");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found the HIP Report Job List table object.");
		}
		
		return objs;
	}
	
	public HIPReportingJobInfo getHIPReportingJobInfoByJobID(String jobID){
		HIPReportingJobInfo hipReportingJobInfo = new HIPReportingJobInfo();
		IHtmlObject[] objs = getHIPReportingJobListTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int row = table.findRow(0, jobID);
		if(row>0){
			hipReportingJobInfo.setJobID(table.getCellValue(row, 0));
			hipReportingJobInfo.setStatus(table.getCellValue(row, 1));
			hipReportingJobInfo.setLicenseYear(table.getCellValue(row, 2));
			hipReportingJobInfo.setScheduleID(table.getCellValue(row, 3));
			hipReportingJobInfo.setReportExecutionDate(table.getCellValue(row, 4));
			hipReportingJobInfo.setNumOfRecordsReported(table.getCellValue(row, 5));
			hipReportingJobInfo.setNumOfRecordsReportedYTD(table.getCellValue(row, 6));
			hipReportingJobInfo.setRunDate(table.getCellValue(row, 7));
			hipReportingJobInfo.setRunLocation(table.getCellValue(row, 8));
			hipReportingJobInfo.setRunUser(table.getCellValue(row, 9));
		}else{
			throw new ItemNotFoundException("Did not found HIP Reporting job info, which hunt info = " + jobID );
		}
			
		Browser.unregister(objs);
		return hipReportingJobInfo;
	}
	
	public boolean compareHIPReportingJobInfo(HIPReportingJobInfo expReportingJobInfo){
		HIPReportingJobInfo actHIPReportingJobInfo = this.getHIPReportingJobInfoByJobID(expReportingJobInfo.getJobID());
		
		boolean result = true;
		result &= MiscFunctions.compareResult("Job ID", expReportingJobInfo.getJobID(), actHIPReportingJobInfo.getJobID());
		result &= MiscFunctions.compareResult("Status", expReportingJobInfo.getStatus(), actHIPReportingJobInfo.getStatus());
		result &= MiscFunctions.compareResult("License Year", expReportingJobInfo.getLicenseYear(), actHIPReportingJobInfo.getLicenseYear());
		result &= MiscFunctions.compareResult("Schedule ID", expReportingJobInfo.getScheduleID(), actHIPReportingJobInfo.getScheduleID());
		result &= MiscFunctions.compareResult("Reported Execution Date", expReportingJobInfo.getReportExecutionDate(), actHIPReportingJobInfo.getReportExecutionDate());
		result &= MiscFunctions.compareResult("Number of records Reported", expReportingJobInfo.getNumOfRecordsReported(), actHIPReportingJobInfo.getNumOfRecordsReported());
		result &= MiscFunctions.compareResult("Number of records Reported Year To Date", expReportingJobInfo.getNumOfRecordsReportedYTD(), actHIPReportingJobInfo.getNumOfRecordsReportedYTD());
		result &= MiscFunctions.compareResult("Run Date", expReportingJobInfo.getRunDate(), actHIPReportingJobInfo.getRunDate());
		result &= MiscFunctions.compareResult("Run Location", expReportingJobInfo.getRunLocation(), actHIPReportingJobInfo.getRunLocation());
		result &= MiscFunctions.compareResult("Run User", expReportingJobInfo.getRunUser(), actHIPReportingJobInfo.getRunUser());
		
		return result;
	}
	
	
	
}
