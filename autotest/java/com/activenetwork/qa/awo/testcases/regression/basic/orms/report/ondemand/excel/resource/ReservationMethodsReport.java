package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class ReservationMethodsReport extends ResourceManagerTestCase {
	/**
	 * Script Name   : <b>RM_rptReservationMethodsReport</b>
	 * Generated     : <b>Jul 23, 2009 1:18:45 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/23
	 * @author QA
	 */
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName=rm.runSpecialRpt(rd, comparedPath);
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
//		result = rm.verifyExcelReport(templatesPath, fileName);
		
		rd.deliveryMethod = "EMAIL";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		rd.dateType = "ARRIVAL DATE";
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName=rm.runSpecialRpt(rd, comparedPath);
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
//		result = rm.verifyExcelReport(templatesPath, fileName);
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";

		rd.group = "Operational Reports";
		rd.reportName = "Reservation Methods Report";

		rd.agencyID = "NC Division of Parks and Recreation";
		rd.productType = "All";

		rd.startDate = "Tue Jul 17 2007";
		rd.endDate = "Fri Aug 17 2007";

		rd.reportFormat = "XLS";
		rd.deliveryMethod = "ONLINE";
		rd.dateType = "ORDER DATE";
		
		//New added field
		rd.includeLoops = "Yes";
		rd.includeTotals = "Yes";
		rd.reservationType = "All";
		rd.includeInOrOutOfState = "Yes";
		
//		templatesPath = "C:\\ReportTemplates";
	}
}
