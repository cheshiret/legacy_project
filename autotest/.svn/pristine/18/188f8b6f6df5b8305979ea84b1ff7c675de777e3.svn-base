package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.idahoexportreport.selectioncriteria.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;


public class VerifyDateMandatoryInput extends ResourceManagerTestCase{

	public void execute() {
		//login resource manager 
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		//verify report date is mandatory input
		rm.verifyReportDate(rd.startDate,rd.endDate,"Missing Required Field:  ?StartDate, EndDate");
		
		rm.verifyReportDate(rd.startDate, "Mon Jul 5 2010","Missing Required Field:  ?StartDate");
		
		rm.verifyReportDate("Mon Jul 5 2010", rd.endDate,"Missing Required Field:  ?EndDate");
		
		rm.logoutResourceManager();
	}
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "ID Contract";
		login.location = "Administrator/Idaho Contract";
		
		//initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "Idaho Export Report";
	}
}
