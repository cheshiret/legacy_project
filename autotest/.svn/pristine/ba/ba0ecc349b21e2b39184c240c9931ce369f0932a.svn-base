/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.text.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This report used to cover report by type is 'Fixed Length'
 * @Preconditions:
 * @SPEC:
 * @Task#:Auto-1391
 * 
 * @author ssong
 * @Date  Jan 31, 2013
 */
public class PrivilegeProductFulfillmentReport extends ResourceManagerTestCase{

	/* (non-Javadoc)
	 * @see testAPI.abstractcases.TestCase#execute()
	 */
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		//Delivery through "email"		
		rd.deliveryMethod = "Email";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
        rm.runSpecialRpt(rd, comparedPath);

        rd.deliveryMethod = "Online";
		rm.selectOneRpt(rd.group, rd.reportName);
		String fileName = rm.runSpecialRpt(rd, comparedPath);

		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));

		rm.verifyReportCorrect(result);
		
		// log out
		rm.logoutResourceManager();
	}

	/* (non-Javadoc)
	 * @see testAPI.abstractcases.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract"; 
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		// report information
		rd.group = "All";
		rd.reportName = "Privilege Product Fulfillment Report";
		rd.reportByType = "Fixed Length";
		rd.locationID = "Mississippi Department of Wildlife, Fisheries, and Parks";
		rd.privilegeProduct = new String[] { "101 - RES ALL GAME HUNT/FRESH" };
		rd.fixedLengthType = "Lifetime";
		rd.customerAttribute = "Gender";

		rd.yesNoFlag = "No";
		rd.includeAP = "Yes";
		rd.startDate = "Mon Oct 01 2012";
		rd.endDate = "Mon Oct 01 2012";
		rd.reportFormat = "TEXT";
	}

}
