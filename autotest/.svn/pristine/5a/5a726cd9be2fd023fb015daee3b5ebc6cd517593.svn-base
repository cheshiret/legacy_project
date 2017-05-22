package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class TransmittalSummaryReport extends ResourceManagerTestCase {
	/**
	 * Script Name : <b>RM_rptTransmittalSummaryReport</b> Generated : <b>Jul
	 * 29, 2010 11:00:09 AM</b> Description : Functional Test Script
	 * 
	 *  * Prepare transmittal data:
	 * Step1, Go to facility detail page(IM), change option 'transmittal' to yes.
	 * Step2, Go to FM, create new transmittal for this facility.
	 */
	public void execute() {
		// login resource manager
		rm.loginResourceManager(login);

		rd.deliveryMethod = "Email";
		rd.emailSubject = rd.reportName + DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);

		rd.deliveryMethod = "Online";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);

		result &= rm.verifyEmailReport(templatesPath, rm.getReportFromMailBox(
				host, username, password, comparedPath, rd.emailSubject));

		rm.verifyReportCorrect(result);
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		// initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "Transmittal Summary Report";
		rd.agencyID = "USFS";
		rd.facilityID = "SIBLEY LAKE";//SIBLEY LAKE (WY)
		rd.startDate = DateFunctions.getDateAfterToday(0);
		rd.endDate = DateFunctions.getDateAfterToday(0);
		rd.transmittalID = "1309184049";
		rd.reportFormat = "XLS";
	}
}
