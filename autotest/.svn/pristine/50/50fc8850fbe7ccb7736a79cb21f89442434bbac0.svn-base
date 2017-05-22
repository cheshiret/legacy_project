package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class ParkDepositConsolidatedReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptParkDepositConsolidatedReport</b>
	 * Generated     : <b>Jul 21, 2009 2:50:14 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/21
	 * @author QA
	 */

	public void execute() {

		rm.loginResourceManager(login);
		
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName=rm.runSpecialRpt(rd, comparedPath);
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

		rd.reportName = "Park Deposit Consolidated Report";
		rd.agencyID = "SC parks";
		rd.regionID = "DISTRICT 1";
		rd.startDate = "Tue Jul 1 2008";
		rd.endDate = "Tue Jul 15 2008";
		rd.paymentGroup = "All";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";

	}
}
