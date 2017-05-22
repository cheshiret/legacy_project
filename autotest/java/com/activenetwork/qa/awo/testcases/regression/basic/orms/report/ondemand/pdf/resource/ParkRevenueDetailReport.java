/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

/**
 * @Description:Test include RA fee=Yes,other default selection and PDF format
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC056653,056652
 * @Task#:Auto-2206
 * 
 * @author ssong
 * @Date  Jul 21, 2014
 */
public class ParkRevenueDetailReport extends ResourceManagerTestCase{
	
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);
		result &= rm.verifyEmailReport(templatesPath, rm.getReportFromMailBox(
				host, username, password, comparedPath,rd.fileName, rd.emailSubject));

		rm.verifyReportCorrect(result);
	
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		rd.group = "Financial Reports";
		rd.reportName = "Park Revenue Detail Report";	
		rd.agencyID = "STATE PARKS";
		rd.facilityID = "ROOSEVELT STATE PARK";
		rd.collectIssueLocation = "All";

		rd.includeAdjustments = "No";
		rd.includeRAFee = "Yes";
		rd.yesNoFlag = "No";  //include Non-Depositable items

		rd.startDate = "Mon Jul 01 2013";
		rd.endDate = "Sat Jul 02 2013";
		
		rd.reportFormat = "PDF";
		rd.deliveryMethod = "Email";
	}
}
