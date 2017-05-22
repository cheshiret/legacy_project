package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class FeeAdjustmentDetailReport extends ResourceManagerTestCase {
	/**
	 * Script Name   : <b>RM_rptFeeAdjustmentDetailReport</b>
	 * Generated     : <b>Jul 23, 2009 7:28:54 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/23
	 * @author QA
	 */

	public void execute() {
		rm.loginResourceManager(login);

		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//XLS Email

		rd.reportFormat = "XLS";
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);//XLS Online
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";
		
		rd.group = "Financial Reports";
		rd.reportName = "Fee Adjustment Detail Report";
		rd.agencyID = "SC parks";
		rd.regionID = "All";
		rd.facilityID = "CROFT";
		rd.startDate = "07/01/2010";
		rd.endDate = "07/10/2010";
		rd.productType = "All";
		rd.salesChannel = "All";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";
	}
}
