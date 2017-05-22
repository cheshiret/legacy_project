package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class TourParkProfileReport extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptTourParkProfileReport</b>
	 * Generated     : <b>Jul 29, 2010 11:00:09 AM</b>
	 * Description   : Functional Test Script
	 * 
	 */
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//PDF Email
		result = rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		String locId="77813";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NRRS";
		
		rd.group = "All";
		rd.reportName = "Tour Park Profile Report";
		
		rd.facilityLocID = rm.getFacilityName(locId, schema);//"CARLSBAD CAVERNS NATIONAL PARK";
		rd.startDate = "Sun Jun 1 2008";
		rd.endDate="Sun Jun 1 2008";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		
		rd.reportFormat = "PDF";
		rd.deliveryMethod = "Email";
	}
}
