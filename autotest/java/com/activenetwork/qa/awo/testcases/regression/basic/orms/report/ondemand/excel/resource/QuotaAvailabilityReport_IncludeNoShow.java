/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This test case is a basic case to cover new add 'include no shows' drop down
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC:028926
 * @Task#:Auto-2267
 * 
 * @author ssong
 * @Date  Jul 29, 2014
 */
public class QuotaAvailabilityReport_IncludeNoShow extends ResourceManagerTestCase{

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		result = rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath,rd.fileName, rd.emailSubject));

		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		rd.group = "All";
		rd.reportName = "Quota Availability Report";
		rd.facilityLocID="Boundary Waters Canoe Area Wilderness (Reservations)";
		rd.startDate = "Mon Jun 1 2009";
		rd.endDate = "Mon Jun 15 2009";
		rd.quotaInterval="Overnight Quota";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rd.yesNoFlag = "Yes";//include No Show as Yes
		rd.fileName = caseName;
		
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Email";
	}

}
