/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: As this report date is dynamic,always current date, not fit for compare with template check method
 * 				so this test case only verify request this report successful
 * @Preconditions:
 * @SPEC:[037446,037447,TC037448]
 * @Task#:Auto-1139
 * 
 * @author ssong
 * @Date  Jul 26, 2012
 */
public class AfterHoursAvailabilityPostingReport extends ResourceManagerTestCase{


	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//PDF Email
		
		rd.deliveryMethod = "ONLINE";
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//PDF online
		
		rm.logoutResourceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		rd.group = "All";
		rd.reportName = "After Hours Availability Posting Report";
		
		rd.agencyID = "USFS";
		rd.regionID = "F2";
		rd.districtID = "F202";
		rd.facilityID = "WILLOW PARK";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rd.reportFormat = "PDF";
		rd.deliveryMethod = "EMAIL";
	}
}
