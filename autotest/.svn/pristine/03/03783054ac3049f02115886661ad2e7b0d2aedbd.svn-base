package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class MonthlyTicketingRecipientDistributionReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptMonthlyTicketingRecipientDistributionReport</b>
	 * Generated     : <b>Jul 29, 2010 11:00:09 AM</b>
	 * Description   : Functional Test Script
	 * 
	 */
	public void execute() {

		rm.loginResourceManager(login);
		
		rd.deliveryMethod = "Email";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//PDF Email
		
		rd.deliveryMethod = "Online";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);//PDF online
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "CA Contract";
		login.location = "Administrator/California Parks and Recreation";

		rd.group = "All";
		rd.reportName = "Monthly Ticketing Recipient Distribution Report";
		rd.recipient_name = "California Parks and Recreation";
		rd.lWLocID = "HEARST CASTLE";
		rd.reportType="Tour Month + Distribution Day";
		rd.startDate="Thu May 1 2008";
		rd.endDate = "Sun May 4 2008";
		
		rd.reportFormat = "PDF";
	}
}
