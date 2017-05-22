package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class PermitStaticReport extends ResourceManagerTestCase {
	/**
	 * Script Name : <b>RM_rptPermitStaticReport</b> Generated : <b>Jul 29, 2010
	 * 11:00:09 AM</b> Description : Functional Test Script
	 * 
	 */
	public void execute() {

		rm.loginResourceManager(login);

		rd.emailSubject = rd.reportName + DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);// Excel Email

		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		result &= rm.verifyEmailReport(templatesPath, rm.getReportFromMailBox(
				host, username, password, comparedPath, rd.emailSubject));
		rm.verifyReportCorrect(result);

		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		rd.group = "All";
		rd.reportName = "Permit Statistics Report";
		rd.facilityLocID = "Boundary Waters Canoe Area Wilderness (Reservations)";
		rd.permitEntrance = "01 - Trout Lake (op,om)";
		rd.startDate = "Mon Jun 1 2009";
		rd.endDate = "Mon Jun 1 2009";
		rd.dateGroup = "Month";

		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Email";
	}
}
