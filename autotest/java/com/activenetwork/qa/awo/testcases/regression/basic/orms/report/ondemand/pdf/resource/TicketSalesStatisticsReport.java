package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class TicketSalesStatisticsReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptTicketSalesStatisticsReport</b>
	 * Generated     : <b>Jul 23, 2009 3:55:03 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/23
	 * @author QA
	 */
	public void execute() {

		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);
		result=rm.skipVerifyOnlineReport(templatesPath, fileName);

		rd.reportType = "DETAIL";
		rd.deliveryMethod = "Email";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
//		rd.deliveryMethod = "ONLINE";
//		rm.selectOneRpt(rd.group, rd.reportName);
//		fileName = rm.runSpecialRpt(rd, comparedPath);
//		result&=rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));

		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		rd.group = "Operational Reports";
		rd.reportName = "Ticket Sales Statistics Report";
		rd.agencyID = "NPS";
		rd.facilityID = "All";
		rd.byTicketType = "Yes";
		rd.startDate = "Tue Jul 1 2008";
		rd.endDate = "Thu Jul 3 2008";
		rd.reportFormat = "PDF";
		rd.deliveryMethod = "ONLINE";
		rd.reportType = "SUMMARY";

	}
}
