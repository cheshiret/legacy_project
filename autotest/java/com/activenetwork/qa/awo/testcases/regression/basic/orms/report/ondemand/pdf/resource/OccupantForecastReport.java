package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class OccupantForecastReport extends ResourceManagerTestCase {
	/**
	 * Script Name   : <b>rptOccupantForecastReport</b>
	 * Generated     : <b>Jul 19, 2009 11:13:54 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/19
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

		rd.group = "Operational Reports";
		rd.reportName = "Occupant Forecast Report";
		rd.agencyID = "SC parks";
		rd.regionID = "All";
		rd.facilityID = "CROFT";
		rd.startDate = DateFunctions.getDateAfterToday(3);
		rd.endDate = DateFunctions.getDateAfterToday(5);
		rd.reportFormat = "PDF";
		rd.deliveryMethod = "EMAIL";
	}
}
