package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class DistributionCycleSummaryReport extends ResourceManagerTestCase
{
	/**
	 * Script Name   : <b>DistributionCycleSummaryReport</b>
	 * Generated     : <b>Feb 6, 2010 3:43:01 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/06
	 * @author QA
	 */
  	public void execute() {
		rm.loginResourceManager(login);
		
		rd.deliveryMethod = "EMAIL";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//PDF Email
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);//PDF online
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);

		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		rd.group = "Financial Reports";
		rd.reportName = "Distribution Cycle Summary Report";
		rd.locationID = "USFS";
		rd.frequency = "Monthly starting on the 1st";
		rd.period = "07-01-2011 - 07-31-2011 Trf 08-04-2011";
		rd.recipientType = "Location";
		rd.recipient_name = "USFS";
		rd.facilityID = "RYAN PARK";
		rd.paymentMethod = "CREDIT CARD";
		
		rd.reportFormat = "PDF";
	}
}
