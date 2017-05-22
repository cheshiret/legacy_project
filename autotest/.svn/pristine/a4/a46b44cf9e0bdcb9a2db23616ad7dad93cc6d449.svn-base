/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.text.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This test case cover CSV format report and compare new generated report with template
 * @Preconditions:
 * @SPEC:[TC:036330,036334,036406]
 * @Task#:Auto-1139
 * 
 * @author ssong
 * @Date  Jul 25, 2012
 */
public class TransactionFeeReport extends ResourceManagerTestCase{

	public void execute() {
		rm.loginResourceManager(login);
		
		rd.deliveryMethod = "EMAIL";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName=rm.runSpecialRpt(rd, comparedPath);
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);

		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
        
		rm.verifyReportCorrect(result);
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "CA Contract";
		login.location = "Administrator/California Parks and Recreation";

		rd.group = "All";
		rd.reportName = "Transaction Fee Report";

		rd.agencyID = "DPR";
		rd.districtID = "SAN LUIS OBISPO C.";
		rd.facilityID = "All";

		rd.startDate = "Tue Jul 01 2008";
		rd.endDate = "Tue Jul 01 2008";
		rd.exceptions = "Yes";

		rd.reportFormat = "CSV";
	}
}
