package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.financialsessiondetailreport.selectioncriteria.resource;

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
		setFinancialSessionID(rd.finSessionId_2);
		reportSectionInfo();

		// End Date is early than Start Date
		rd.startDate = DateFunctions.getDateAfterToday(-1);
		rd.endDate = DateFunctions.getDateAfterToday(-2);
		rm.verifyReportDate(rd.startDate, rd.endDate, errorMessage.get(0));
		//according to SPEC, not contains equals 31 days
//		// End Date minus Report Start Date is equal to 31 days
//		rd.startDate = DateFunctions.getDateAfterToday(-31);
//		rd.endDate = DateFunctions.getDateAfterToday(0);
//		rm.verifyReportDate(rd.startDate, rd.endDate, errorMessage.get(1));
		// End Date minus Report Start Date is greater than 31 days
		rd.startDate = DateFunctions.getDateAfterToday(-32);
		rd.endDate = DateFunctions.getDateAfterToday(0);
		rm.verifyReportDate(rd.startDate, rd.endDate, errorMessage.get(1));

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
		rd.reportName = "Financial Session Detail Report";	
		rd.finSessionId_2 = "110721359";
	}

	private void setFinancialSessionID(String finSessionID) {
		ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage
		.getInstance();
		rptCriteriaPg.setFinSessionId(finSessionID);
	}

	private void reportSectionInfo(){
		//Error Message
		errorMessage.add("End date should be greater than or equal to start date");
		errorMessage.add("Reporting period exceeds the maximum report period of 31 days. Please refine the search criteria");
	}
}
