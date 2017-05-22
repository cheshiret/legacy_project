package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.transactionfeereport.selectioncriteria.resource;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class VerifyReportDate extends ResourceManagerTestCase{
	private List<String> errorMessage = new ArrayList<String>();
	public void execute() {
		// login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		reportSectionInfo();
		
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
		// End Date minus Report Start Date is greater than 366 days
		
		// per new spec:End Date: The user will enter the ending date for the
		// report. This report can only be run for a maximum of 366 92 days. If
		// the ending date is greater than 92 days from the start date an
		// error message will appear requesting the user to change the end date.
		rd.startDate = DateFunctions.getDateAfterToday(-93);
		rd.endDate = DateFunctions.getDateAfterToday(0);
		logger.info(rd.startDate+"--"+rd.endDate);
		rm.verifyReportDate(rd.startDate, rd.endDate, errorMessage.get(4));
		
		//logout resource manager
		rm.logoutResourceManager();	
	}
	
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";
		
		//initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "Transaction Fee Report";
	}
	
	private void reportSectionInfo(){
		//Error Message
		errorMessage.add("Missing Required Field: StartDate, EndDate");
		errorMessage.add("Missing Required Field: EndDate");
		errorMessage.add("Missing Required Field: StartDate");
		errorMessage.add("End date should be greater than or equal to start date");
		errorMessage.add("Reporting period exceeds the maximum report period of 92 days. Please refine the search criteria");
	}
}
