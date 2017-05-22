package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.scparkrevenuedetailreport.selectioncriteria.resource;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class VerifyDisplay_ReportDateLocationAbovtFacility extends ResourceManagerTestCase{

	ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage.getInstance();
	List<String> errorMessage = new ArrayList<String>();
	
	public void execute() {
		// login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		
		this.reportSectionInfo();
		rptCriteriaPg.selectAgencyID(rd.agencyID);
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
		// End Date can not a date in the future
		rd.startDate = DateFunctions.getDateAfterToday(0);
		rd.endDate = DateFunctions.getDateAfterToday(1);
		rm.verifyReportDate(rd.startDate, rd.endDate, errorMessage.get(4));
        // End Date minus Start Date is equal 7 days  
		//---needs to run for maximum reporting period of 7 days if run for the whole Agency     
        //rd.startDate = DateFunctions.getDateAfterToday(-7);
        //rd.endDate = DateFunctions.getDateAfterToday(0);
        //rm.verifyReportDate(rd.startDate, rd.endDate, errorMessage.get(5));
		// End Date minus Start Date is greater than 7 days
		rd.startDate = DateFunctions.getDateAfterToday(-8);
		rd.endDate = DateFunctions.getDateAfterToday(0);
		rm.verifyReportDate(rd.startDate, rd.endDate, errorMessage.get(5));
		
		//logout resource manager
		rm.logoutResourceManager();		
	}
	
	public void reportSectionInfo(){
		//Error Message
		errorMessage.add("Missing Required Field: StartDate, EndDate");
		errorMessage.add("Missing Required Field: EndDate");
		errorMessage.add("Missing Required Field: StartDate");
		errorMessage.add("End date should be greater than or equal to start date");
		errorMessage.add("The requested date cannot be a date in the future.");
		errorMessage.add("Reporting period exceeds the maximum report period of 7 days for the selected location. Please refine the search criteria.");
	}
	
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";
		
		// Initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "SC Park Revenue Detail Report";
		rd.agencyID = "SC parks";
	}
}
