package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class EntranceProductSoldReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptMSWeeklyExportReport</b>
	 * Generated     : <b>Jul 29, 2010 11:00:09 AM</b>
	 * Description   : Functional Test Script
	 * 
	 */
	public void execute() {

		rm.loginResourceManager(login);
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		rd.deliveryMethod = "Online";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName =rm.runSpecialRpt(rd, comparedPath);//Excel Online
		result &= rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		rd.reportBy="Day";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName =rm.runSpecialRpt(rd, comparedPath);//Excel Online
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		rd.group = "All";
		rd.reportName = "Entrance Product Sold Report";
		rd.agencyID="STATE PARKS";
		rd.facilityID="BUCCANEER STATE PARK";
		rd.reportBy="Customer Locations";
		rd.startDate="Tue Jul 1 2008";
		rd.endDate = "Tue Jul 1 2008";
		
		rd.reportFormat="XLS";
		rd.deliveryMethod = "Email";
	}
}
