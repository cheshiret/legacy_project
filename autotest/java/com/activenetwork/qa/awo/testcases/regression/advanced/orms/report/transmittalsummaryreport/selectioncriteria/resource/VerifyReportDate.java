package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.transmittalsummaryreport.selectioncriteria.resource;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class VerifyReportDate extends ResourceManagerTestCase{
	private List<String> errorMessage = new ArrayList<String>();
	public void execute() {
		// login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
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
		
		//logout resource manager
		rm.logoutResourceManager();	
	}
	
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		//initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "Transmittal Summary Report";
		//should add a new transmittal when schema has been changed
		//check transmittal property setup in Inventory manager
		rd.transmittalID= "1001300578";
	}
	
	private void setTransmittalID(String transmittalID) {
		ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage
				.getInstance();
		rptCriteriaPg.setTransmittalID(transmittalID);
	}
	
	private void reportSectionInfo(){
		//Error Message   
		errorMessage.add("Missing Required Field: StartDate, EndDate");
		errorMessage.add("Missing Required Field: EndDate");
		errorMessage.add("Missing Required Field: StartDate");
		errorMessage.add("End date should be greater than or equal to start date");
		errorMessage.add("Reporting period exceeds the maximum report period of 31 days. Please refine the search criteria");
	}
}
