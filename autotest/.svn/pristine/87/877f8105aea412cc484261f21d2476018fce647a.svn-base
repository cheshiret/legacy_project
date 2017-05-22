/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:Report Header & Column [TC:054195]
 *       Report Body - NSS [TC:054326]
 *       Report Body - SS [TC:054196]
 * @Task#:Auto-2305
 * 
 * @author Vivian
 * @Date  Jul 3, 2014
 */
public class ReservationClosureReport_Slip extends ResourceManagerTestCase {
	public void execute() {
		rm.loginResourceManager(login);
		rd.emailSubject = caseName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//Excel Email
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		rd.fileName = caseName;
		fileName = rm.runSpecialRpt(rd, comparedPath);//Excel Online
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));

		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);

		login.contract = "NY Contract";
		login.location = "Administrator/Contract";
		rd.group = "Operational Reports";
		rd.reportName = "Reservation Closure Report";
        
		rd.agencyID="OPRHP";
		rd.regionLocID = "All";
		rd.facilityID = "Allan H. Treman State Marine Park";
		rd.facilityLocID = "All";
		rd.productCategory = "Slip";

		rd.startDate = "Tue Jan 01 2013";
		rd.endDate = "Tue Dec 31 2013";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";
	}
}
