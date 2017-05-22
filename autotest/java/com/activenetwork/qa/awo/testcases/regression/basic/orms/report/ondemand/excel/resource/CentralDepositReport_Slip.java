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
 * @SPEC:Vouchers data -- Count, Redeemed, Issued, NET [TC:084799]
 *       Cash and non-cash depositable data [TC:084798]
 * @Task#:AUTO-2308 
 * 
 * @author Vivian
 * @Date  Jul 7, 2014
 */
public class CentralDepositReport_Slip extends ResourceManagerTestCase {

	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//XLS Email
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		rd.fileName = caseName;
		fileName = rm.runSpecialRpt(rd, comparedPath); //XLS ONLINE
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);

		login.contract = "NY Contract";
		login.location = "Administrator/Contract";
		
		rd.group = "All";
		rd.reportName = "Central Deposit Report";
		rd.agencyID = "OPRHP";
		rd.facilityID = "Allan H. Treman State Marine Park";
		rd.startDate = "Tue Jan 01 2013";
		rd.endDate = "Sun Jul 06 2014";
		rd.paymentGroup = "*All";
		rd.emailSubject = caseName+DateFunctions.getCurrentTime();
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";
	}
}

