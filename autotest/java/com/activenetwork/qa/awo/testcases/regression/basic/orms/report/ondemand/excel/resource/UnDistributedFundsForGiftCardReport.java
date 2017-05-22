package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class UnDistributedFundsForGiftCardReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptUnDistributedFundsForGiftCardReport</b>
	 * Generated     : <b>Jul 29, 2010 11:00:09 AM</b>
	 * Description   : Functional Test Script
	 * 
	 */
	public void execute() {
		//login resource manager and run undistributed funds report
		rm.loginResourceManager(login);
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName =rm.runSpecialRpt(rd, comparedPath);
		result=rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		rd.reportType="Detail";
		rd.deliveryMethod = "Email";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		rd.deliveryMethod = "Online";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName =rm.runSpecialRpt(rd, comparedPath);
		result &= rm.skipVerifyOnlineReport(templatesPath, fileName);

		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);

		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		//initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "UnDistributed Funds For Gift Card Report";
		rd.agencyID = "USFS";
		rd.facilityID = "RYAN PARK";//RYAN PARK (WY)
		rd.reportType="Summary";
		rd.startDate = "Thu May 1 2008";
		rd.endDate= "Thu May 1 2008";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Online";
	}
}
