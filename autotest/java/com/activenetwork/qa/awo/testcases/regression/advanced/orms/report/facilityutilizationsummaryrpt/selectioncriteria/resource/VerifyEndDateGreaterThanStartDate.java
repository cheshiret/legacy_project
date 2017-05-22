package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.facilityutilizationsummaryrpt.selectioncriteria.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class VerifyEndDateGreaterThanStartDate extends ResourceManagerTestCase{

	public void execute() {
		//login resource manager 
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		//verify end date must greater than start date
		String alertMsg = rm.getReportDateErrorMesg(rd.startDate, rd.endDate);
		if(!alertMsg.equals("End date should be greater than or equal to start date")){
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
		rd.startDate = "7/19/2010";
		rd.endDate = "7/18/2010";
	}
}