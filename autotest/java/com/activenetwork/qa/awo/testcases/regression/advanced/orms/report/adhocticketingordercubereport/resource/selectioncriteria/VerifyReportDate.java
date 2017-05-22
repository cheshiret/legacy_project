package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.adhocticketingordercubereport.resource.selectioncriteria;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class VerifyReportDate extends ResourceManagerTestCase{
	ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage.getInstance();
	List<String> errorMessage = new ArrayList<String>();
	
	public void execute() {
		// login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		
		this.reportSectionInfo();
		// Not enter Start Date and End Date
		rm.verifyReportDate("", "", errorMessage.get(0));
        // Only enter Start Date
		rd.startDate = DateFunctions.getDateAfterToday(-1);
		rm.verifyReportDate(rd.startDate, " ", errorMessage.get(1));
        // Only enter End Date
		rd.endDate = DateFunctions.getDateAfterToday(0);
		rm.verifyReportDate(" ", rd.endDate, errorMessage.get(2));
		// End Date is early than Start Date
		rd.startDate = DateFunctions.getDateAfterToday(-1);
		rd.endDate = DateFunctions.getDateAfterToday(-2);
		rm.verifyReportDate(rd.startDate, rd.endDate, errorMessage.get(3));
		// End Date minus Start Date is greater than 732 days
		rd.startDate = DateFunctions.getDateAfterToday(-732);
		rd.endDate = DateFunctions.getDateAfterToday(0);
		rm.verifyReportDate(rd.startDate, rd.endDate, errorMessage.get(4));
		
		//logout resource manager
		rm.logoutResourceManager();			
	}
	
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		// Initialize report data
		rd.group = "Ad hoc Cubes";
		rd.reportName = "Ad hoc Ticketing Order Cube Report";	
	}	
	
	public void reportSectionInfo(){
		//Error Message
		errorMessage.add("Missing Required Field: {1,2}StartDate, EndDate");
		errorMessage.add("Missing Required Field: {1,2}EndDate");
		errorMessage.add("Missing Required Field: {1,2}StartDate");
		errorMessage.add("End date should be greater than or equal to start date");
		errorMessage.add("Reporting period exceeds the maximum report period of 732 days. {1,2}Please refine the search criteria");
	}
}
