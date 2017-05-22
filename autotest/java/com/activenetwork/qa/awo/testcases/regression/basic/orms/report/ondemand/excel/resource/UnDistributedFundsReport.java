package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * In this report, column 'Distribution Date' is 'Process End Date/Time' of this Distribution job in Finance Manager.
 * refer to DEFECT-35990
 * */
public class UnDistributedFundsReport extends ResourceManagerTestCase{
	public void execute() {
		//login resource manager and run undistributed funds report
		rm.loginResourceManager(login);
		
		rd.deliveryMethod = "Email";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		rd.deliveryMethod = "Online";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName =rm.runSpecialRpt(rd, comparedPath);
		result=rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);

		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";
		
		//initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "UnDistributed Funds Report";
		rd.agencyID = "SC parks";
		rd.facilityID = "AIKEN";
		rd.startDate = "Tue Jul 18 2007";
		rd.endDate="Mon Jul 20 2007";
		rd.reportFormat = "XLS";
	}
}
