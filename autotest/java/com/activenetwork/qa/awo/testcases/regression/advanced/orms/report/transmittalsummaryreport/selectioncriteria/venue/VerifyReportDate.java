package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.transmittalsummaryreport.selectioncriteria.venue;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.keywords.orms.ResourceManager;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class VerifyReportDate extends VenueManagerTestCase{
	private ReportData rd = new ReportData();
	private List<String> errorMessage = new ArrayList<String>();
	private ResourceManager rm= ResourceManager.getInstance();
	
	public void execute() {
		//login venue manager
		vm.loginVenueManager(login);
		
		vm.selectOneReport(rd.group, rd.reportName);
		setTransmittalID(rd.transmittalID);
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
		// End Date minus Report Start Date is greater than 31 days
		rd.startDate = DateFunctions.getDateAfterToday(-32);
		rd.endDate = DateFunctions.getDateAfterToday(0);
		rm.verifyReportDate(rd.startDate, rd.endDate, errorMessage.get(4));
		
		vm.logoutVenueManager();
	}
	
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "NRRS - Venue Supervisor/MAMMOTH CAVE NATIONAL PARK TOURS";
		
		//initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "Transmittal Summary Report";	
		rd.transmittalID= "693116502";
	}
	
	private void setTransmittalID(String transmittalID) {
		ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage
				.getInstance();
		rptCriteriaPg.setTransmittalID(transmittalID);
	}
	
	private void reportSectionInfo(){
		//Error Message
		errorMessage.add("Missing Required Field:  StartDate, EndDate");
		errorMessage.add("Missing Required Field:  EndDate");
		errorMessage.add("Missing Required Field:  StartDate");
		errorMessage.add("End date should be greater than or equal to start date");
		errorMessage.add("Reporting period exceeds the maximum report period of 31 days. Please refine the search criteria.");
	}
}
