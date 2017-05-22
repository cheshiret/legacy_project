package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description : Functional Test Script
 * 
 * @author QA
 */
public class POSProductSoldSummaryReport extends ResourceManagerTestCase {
	/**
	 * Script Name : <b>RM_rptPOSProductSoldSummaryReport</b> Generated : <b>Jul
	 * 22, 2009 9:40:16 PM</b> Description : Functional Test Script Original
	 * Host : WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2009/07/22
	 * @author QA
	 */

	public void execute() {
		rm.loginResourceManager(login);

		// Park Level
		rd.emailSubject = rd.reportName + rd.reportType
				+ DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);

		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		result &= rm.verifyEmailReport(templatesPath, rm.getReportFromMailBox(
				host, username, password, comparedPath, rd.emailSubject));

		// Agency Level
		this.changeReportSelectionCriteria();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);

		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);
		result &= rm.skipVerifyOnlineReport(templatesPath, fileName);
		result &= rm.verifyEmailReport(templatesPath, rm.getReportFromMailBox(
				host, username, password, comparedPath, rd.emailSubject));

		rm.verifyReportCorrect(result);

		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "VA Contract";
		login.location = "Administrator/Virginia Department of Conservation and Recreation";

		rd.group = "Financial Reports";
		rd.reportName = "POS Product Sold Summary Report";

		rd.agencyID = "Virginia State Parks";
		rd.regionID = "All";
		rd.facilityID = "CHIPPOKES PLANTATION STATE PARK";
		rd.reportType = "_Park";
		rd.productGroup = "*All";
		rd.startDate = "Tue Jul 1 2008";
		rd.startTime = "12:00";
		rd.startTimeampm = "AM";
		rd.endDate = "Tue Jul 15 2008";
		rd.endTime = "12:00";
		rd.endTimeampm = "AM";

		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";

	}

	private void changeReportSelectionCriteria() {
		rd.agencyID = "Virginia State Parks";
		rd.regionID = "All";
		rd.reportName = "POS Product Sold Summary Report";
		rd.facilityID = "All";
		rd.reportType = "_Agency";
		rd.productGroup = "*All";
		rd.startDate = "Tue Jul 1 2008";
		rd.startTime = "12:00";
		rd.startTimeampm = "AM";
		rd.endDate = "Thu Jul 3 2008";
		rd.endTime = "12:00";
		rd.endTimeampm = "AM";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";
		rd.emailSubject = rd.reportName + rd.reportType
				+ DateFunctions.getCurrentTime();
	}
}
