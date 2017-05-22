package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:verify Occupancy Report - by Sites report content using history data on report manager
 *              update site status for pcr 1671
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:Selection Criteria [TC:015237] 
 *       Report Header [TC:015238] 
 *      Report Column & Report body [TC:011748] 
 * @Task#:Auto-2293 
 * 
 * @author szhou
 * @Date Jul 24, 2014
 */
public class OccupancyReport_bySiteMonthly extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptOccupancyReport_bySiteMonthly</b>
	 * Generated     : <b>Jul 27, 2009 6:12:45 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/27
	 * @author QA
	 */

	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//Excel Email
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName=rm.runSpecialRpt(rd, comparedPath);//XLS Online
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));

		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {

		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

		rd.group = "Operational Reports";
		rd.reportName = "Occupancy Report - by Sites";
		rd.agencyID = "SC parks";
		rd.regionLocID = "*All";
		rd.facilityLocID = "*All";
		rd.category = "*All";
		rd.reportType = "Monthly";
		rd.siteStatus="Active";
		rd.startDate = "07/01/2008";
		rd.endDate = "07/31/2008";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";
	}
}
