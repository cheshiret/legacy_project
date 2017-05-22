/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

/**
 * @Description: Use basic report verify strategy compare new generate report with template
 * @Preconditions:
 * @SPEC:[TC032693,032694]
 * @Task#:Auto-1198
 * 
 * @author ssong
 * @Date  Aug 24, 2012
 */
public class POSFieldProductSoldDetailReport extends ResourceManagerTestCase {

	public void execute() {
		rm.loginResourceManager(login);

		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);

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
		login.contract = "NH Contract";
		login.location = "Administrator/New Hampshire Contract";

		rd.group = "Financial Reports";
		rd.reportName = "POS Field Product Sold Detail Report";

		rd.locationID = "WHITE LAKE STATE PARK";
		rd.posReportBy = "Facility";
		rd.productGroup = "*All";
		rd.startDate = "Mon Aug 01 2011";
		rd.startTime = "12:00";
		rd.startTimeampm = "AM";
		rd.endDate = "Mon Aug 01 2011";
		rd.endTime = "11:59";
		rd.endTimeampm = "PM";

		rd.reportFormat = "PDF";
		rd.deliveryMethod = "EMAIL";
	}
}
