package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class DailyAccruedRevenueReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptDailyAccruedRevenueReport</b>
	 * Generated     : <b>Jul 22, 2009 10:44:40 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/22
	 * @author QA
	 */

	public void execute() {
		rm.loginResourceManager(login);
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {

		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";
		
		rd.group = "All";
		rd.reportName = "Daily Accrued Revenue Report";
		rd.agencyID = "SC parks";
		rd.regionID = "All";
		rd.facilityID = "CROFT";
		rd.startDate = DateFunctions.getDateAfterToday(-5);
		rd.reportFormat = "PDF";
		rd.deliveryMethod = "EMAIL";

	}
}

