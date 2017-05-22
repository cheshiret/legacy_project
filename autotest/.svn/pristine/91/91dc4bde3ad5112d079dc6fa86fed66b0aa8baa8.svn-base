/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This script check report by account condition
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC032219
 * @Task#:Auto-2263
 * 
 * @author ssong
 * @Date  Jul 17, 2014
 */
public class RevenueDistributionSummaryReport_ByAccount extends ResourceManagerTestCase {

	public void execute() {

		rm.loginResourceManager(login);
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
	
		result = rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath,rd.fileName, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

		rd.reportName = "Revenue Distribution Summary Report";
		rd.locationID = "South Carolina State Park Service";
		rd.paymentMethod = "ALL";
		rd.reportByLocation = "Account";
		rd.startDate = "Tue Jul 17 2007";
		rd.endDate = "Fri Aug 17 2007";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";
		rd.fileName = rd.reportName.replaceAll(" ", "")+"_ReportByAccount";
		
	}
}

