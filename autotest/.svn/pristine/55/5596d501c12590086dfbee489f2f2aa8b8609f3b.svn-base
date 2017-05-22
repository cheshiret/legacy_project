package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * @Description:This case is used to verify adjust in fee detail page when purchase a lottery privilege
 * @LinkSetUp:None
 * @SPEC:[TC:099413] Check Report Header - Include Loops 
 * 	     [TC:099414] Check Report Header - Slip Reservation Type 
 * 	     [TC:099415] Check Report Header - Including Registrations 
 *		 [TC:099416] Check Report Header - Include In-State/Out-of-State 
 *       [TC:099371] Check Report Header - Include Total Columns 
 *       [TC:100295] Check Report Body - Marina - Location Columns 
 *       [TC:100396] Check Report Body - Marina - Report Columns - Loop/Dock&Slip Reservation Type&In-State/Out-of-State 
 *       [TC:100397] Check Report Body - Marina - Transfer/Mid-Stay Transfer 
 *       [TC:101386] Check Report Body - Marina - Report Columns - Transient 
 *       [TC:101471] Check Report Body - Marina - Combine Call Center and Field application 
 * @Task#: Auto-2270
 * @author Phoebe Chen
 * @Date  July 16, 2014
 */
public class ReservationMethodsReport_Marina extends ResourceManagerTestCase {

	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName=rm.runSpecialRpt(rd, comparedPath);
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
//		result = rm.verifyExcelReport(templatesPath, fileName);
		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NY Contract";
		login.location = "Administrator - Auto/Contract";

		rd.group = "Operational Reports";
		rd.reportName = "Reservation Methods Report";

		rd.agencyID = "OPRHP";
		rd.districtID = "FINGER LAKES";
		rd.productType = "Slip";

		rd.startDate = "Tue Jan 01 2013";
		rd.endDate = "Tue Dec 31 2013";

		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";
		rd.dateType = "Arrival Date";
		
		//New added field
		rd.includeLoops = "Yes";
		rd.includeTotals = "Yes";
		rd.reservationType = "All";
		rd.includeInOrOutOfState = "Yes";
		
//		templatesPath = "C:\\ReportTemplates";
	}

}
