/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This test case used compare new generated report with template and verify data and format correct
 * @Preconditions:
 * @SPEC:<POS Sold with Reservation Report.RPT>->[TC:037939;TC:037940;TC:037941]
 * @Task#:Auto-1139
 * 
 * @author ssong
 * @Date  Jul 24, 2012
 */
public class POSSoldWithReservationReport extends ResourceManagerTestCase{

	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		rd.deliveryMethod = "EMAIL";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//PDF Email
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);//PDF online
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

		rd.group = "Operational Reports";
		rd.reportName = "POS Sold with Reservation Report";	
		rd.agencyID = "SC parks";
		rd.facilityID = "HICKORY KNOB";
		rd.productGroup = "All";
		rd.posPrds = new String[]{"BREAKFAST,9326","GOLF - 18 HOLES - REGULAR,9318","GOLF CART,9304"};
		rd.promoCodes = new String[]{"HKGPSD"};
		
		rd.startDate = "Fri Apr 17 2009";
		rd.endDate = "Sun Apr 19 2009";
		rd.reportFormat = "PDF";
		rd.deliveryMethod = "Email";
	}
}
