package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * Description   : Functional Test Script
 * @author QA
 */
public class TourInventoryAvailabilityReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>TourInventoryAvailabilityReport</b>
	 * Generated     : <b>Feb 25, 2010 1:55:54 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/25
	 * @author QA
	 */
	public void execute() {
		rm.loginResourceManager(login);

		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName =rm.runSpecialRpt(rd, comparedPath);//Excel Online
		result =rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		String locId="77813";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NRRS";
		
		rd.group = "Operational Reports";
		rd.reportName = "Tour Inventory Availability Report";
		
			
		rd.facilityLocID = rm.getFacilityName(locId, schema);//"CARLSBAD CAVERNS NATIONAL PARK";
		
		rd.tourID = "All";
		rd.reportType = "Summary";
		rd.startDate = "Sun Jun 1 2008";
		rd.showAllocation="Yes";
		
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Email";
	}
}
