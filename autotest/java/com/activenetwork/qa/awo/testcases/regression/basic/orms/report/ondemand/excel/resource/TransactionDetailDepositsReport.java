/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

/**
 * @Description:Check all facility and payment group in given agency for specific date period
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC:029646,029648,029647
 * @Task#:Auto-2202
 * 
 * @author ssong
 * @Date  May 8, 2014
 */
public class TransactionDetailDepositsReport extends ResourceManagerTestCase{

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
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		// initialize report data
		rd.group = "All";
		rd.reportName = "Transaction Detail Deposits Report";
		
		rd.agencyID = "USFS";
		rd.startDate = "05/01/2011";
		rd.endDate = "05/02/2011";
		
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Email";
	}

}
