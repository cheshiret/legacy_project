package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;

/**
 * 
 * @Description: verify Site Permit Report empty template
 * @Preconditions:
 * @SPEC: Request "Site Permit Report" in Resource Manager [TC:031715]
 * 				Configurable Logo [TC:037629]
 * 				Report Layout on the page [TC:036756]
 * 				Permit Signature section [TC:037049]
 * 				Font Face , Font Size and Text Bold [TC:036940]
 * @Task#: AUTO-2276
 * 
 * @author qchen
 * @Date  Aug 5, 2014
 */
public class SitePermitReport extends ResourceManagerTestCase {

	@Override
	public void execute() {
		rm.loginResourceManager(login);
		rm.gotoReportPage();
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		boolean result = rm.verifyEmailReport(templatesPath, rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "IN Contract";
		login.location = "Administrator - Auto/State of Indiana";

		rd.group = "Operational Reports";
		rd.reportName = "Site Permit Report";
		
		rd.park = "SHAKAMAK";
		rd.siteNumber = "Cabin 14";
		rd.reservationStatus = OrmsConstants.PREARRIVAL_STATUS;
		rd.sortBy = "Loop and Site";
		rd.startDate = "Tue Jun 18 2013";
		rd.endDate = rd.endDate;
		rd.reportFormat = "PDF";
		rd.deliveryMethod = "Email";
	}
}
