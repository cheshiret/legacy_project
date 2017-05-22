package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.facilityutilizationsummaryrpt.selectioncriteria.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class VerifyReportDateMustBePastDate extends ResourceManagerTestCase{

	/**
	 * This test case was blocked by one defect
	 */
	public void execute() {
		//login resource manager 
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		//verify end date must greater than start date
		String alertMsg = rm.getReportDateErrorMesg(rd.startDate, rd.endDate);
		if(!alertMsg.equals("The report can only be run for the past days. please change the reporting period")){
			throw new ErrorOnPageException("No Error Message Prompted or Wrong Error Message Displayed.");
		}
		
		rm.logoutResourceManager();
	}
	
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		//initialize report data
		rd.group = "Operational Reports";
		rd.reportName = "Facility Utilization Summary Report";
		rd.startDate = DateFunctions.getDateAfterToday(3);
		rd.endDate = DateFunctions.getDateAfterToday(6);
	}
}
