package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class EarnedDistributionFundsDetailReport extends
		ResourceManagerTestCase {
	/**
	 * Script Name : <b>RM_rptEarnedDistributionFundsDetailReport</b> Generated
	 * : <b>Jul 23, 2009 6:12:58 AM</b> Description : Functional Test Script
	 * Original Host : WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2009/07/23
	 * @author QA
	 * 
	 * Jane Wang[2014-7-30]:Updated by Jane Wang for TC059868,059869,059860
	 * New column for Include Active Advantage Discount, we could run SQL to get this discount configuration
	 * select * from f_advantage_discnt;
	 * 
	 * Change to CA contract, SAN ONOFRE SB park, Start Date: 01/30/2014, End Date: 02/28/2014
	 */

	public void execute() {
		rm.loginResourceManager(login);

		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);// XLS EMAIL
		result = rm.verifyEmailReport(templatesPath, rm
				.getReportFromMailBox(host, username, password, comparedPath,
						rd.emailSubject));

		rm.verifyReportCorrect(result);
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
//		login.url = AwoUtil.getOrmsURL(env);
//		login.contract = "SC Contract";
//		login.location = "Administrator/South Carolina State Park Service";
//
//		rd.group = "All";//Finanical Reports
//		rd.reportName = "Earned Distributed Funds Detail Report";
//		rd.agencyID = "SC parks";
//		rd.facilityID = "CROFT";
//		rd.startDate = "07/01/2007";
//		rd.endDate = "07/31/2007";
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "CA Contract";
		login.location = "Administrator - Auto/California Parks and Recreation";

		rd.group = "All";
		rd.reportName = "Earned Distributed Funds Detail Report";
		rd.agencyID = "DPR";
		rd.facilityID = "SAN ONOFRE SB";
		rd.earnedRevenue = "Both";
		rd.earnedReportBy = "Area/Loop";
		rd.includeAAD = "Yes";
		rd.startDate = "01/30/2014";
		rd.endDate = "02/28/2014";
		
		rd.emailSubject = rd.reportName + DateFunctions.getCurrentTime();
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";

	}
}
