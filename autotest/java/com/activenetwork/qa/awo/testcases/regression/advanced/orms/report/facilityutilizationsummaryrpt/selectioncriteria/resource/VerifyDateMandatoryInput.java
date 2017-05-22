package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.facilityutilizationsummaryrpt.selectioncriteria.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class VerifyDateMandatoryInput extends ResourceManagerTestCase{

	public void execute() {
		//login resource manager 
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		//verify report date is mandatory input
		String alertMsg = "";
		alertMsg = rm.getReportDateErrorMesg(rd.startDate,rd.endDate);
		if(!alertMsg.matches("Missing Required Field:  ?StartDate, EndDate")){
			throw new ErrorOnPageException("Wrong Error Message Prompt For Blank Date.");
		}
		alertMsg = rm.getReportDateErrorMesg(rd.startDate, "Mon Jul 5 2010");
		if(!alertMsg.matches("Missing Required Field:  ?StartDate")){
			throw new ErrorOnPageException("Wrong Error Message Prompt For Blank Start Date.");
		}
		alertMsg = rm.getReportDateErrorMesg("Mon Jul 5 2010", rd.endDate);
		if(!alertMsg.matches("Missing Required Field:  ?EndDate")){
			throw new ErrorOnPageException("Wrong Error Message Prompt For Blank End Date.");
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
	}
}

