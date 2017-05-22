/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.text.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: This case covered dealer in CSV format
 * @Preconditions:
 * @SPEC: TC019737,019736,019738,019739
 * @Task#:AUTO-1395
 * 
 * @author ssong
 * @Date  Feb 4, 2013
 */
public class VehicleRegistrationRenewalReport extends ResourceManagerTestCase{

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
		rd.reportName = "Vehicle Registration Renewal Report";
		rd.locationID = "Mississippi Department of Wildlife, Fisheries, and Parks";
		rd.vehicleType = "Dealer";
		rd.startDate = "Mon Oct 01 2012";
		rd.endDate = "Mon Oct 01 2012";
		rd.reportFormat = "CSV";
	}

}
