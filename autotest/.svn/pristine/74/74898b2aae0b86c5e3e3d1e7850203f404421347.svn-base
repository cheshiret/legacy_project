/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.text.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

/**
 * @Description:
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC:028929,028943
 * @Task#:Auto-2201
 * 
 * @author ssong
 * @Date  May 8, 2014
 */
public class TransactionDetailRevenueReport extends ResourceManagerTestCase{

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
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
		login.contract = "ID Contract";
		login.location = "Administrator/Idaho Contract";

		rd.group = "Financial Reports";
		rd.reportName = "Transaction Detail Revenue Report";
		rd.agencyID = "IDPR";
		rd.transactionDetailReportBy = "Revenue Location";
		rd.includeRAFee = "No";
		rd.displayColumns = new String[]{"Account ID","Batch ID","Batch/Deposit Date","Deposit ID","Payment Type","Product ID","Product Name"};
		rd.breakAccountCode = "Yes";
		rd.subTotalBy = new String[]{"Batch ID","Deposit ID","Payment Type"};
		
		rd.startDate = "Sat Jan 01 2011";
		rd.endDate = "Sat Jan 01 2011";

		rd.reportFormat = "CSV";
		rd.deliveryMethod = "Email";
	}

}
