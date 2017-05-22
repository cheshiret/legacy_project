/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.text.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:Basic report case check Revenue section,payment section and gift card section displayed correct in report
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC118351,118362,118372,118373,118377,118821,118958,119148
 * @Task#:Auto-2205
 * 
 * @author ssong
 * @Date  May 20, 2014
 */
public class SCRevenueExportReport extends ResourceManagerTestCase{

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);
		result = rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		rm.logoutResourceManager();
	}

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

		// initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "SC Revenue Export Report";
		
		rd.startDate = "05/01/2012";
		rd.endDate = "05/05/2012";
		rd.reportFormat = "CSV";
		rd.deliveryMethod = "Email";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
	}

}
