/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:Report Header [TC:058182]
 *       Report Columns [TC:058183]
 *       Report Body - Format [TC:058184]
 *       Report Body - Data [TC:058185]
 *       Report Body - Others [TC:058186]
 * @Task#:AUTO-2307
 * 
 * @author Vivian
 * @Date  Aug 4, 2014
 */
public class ResAttributeFeeSummaryReport_IncTotal extends ResourceManagerTestCase{

	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		rd.emailSubject = caseName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		rd.deliveryMethod = "ONLINE";
		rd.fileName = caseName;
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName=rm.runSpecialRpt(rd, comparedPath);
		result &= rm.skipVerifyOnlineReport(templatesPath, fileName);
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));

		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

		rd.group = "All";
		rd.reportName = "Reservation Attribute Fee Summary Report";
		rd.agencyID = "SC parks";
		rd.facilityID = "All";
		rd.attrFeeType = "All";
		rd.startDate = "Thu Aug 02 2007";
		rd.endDate = "Fri Aug 01 2008";

		rd.reportFormat = "XLS";
		rd.includeTotals = "Yes";
		rd.deliveryMethod = "EMAIL";
	}

}
