/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

/**
 * @Description:This script used to verify serialize POS report display
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC032691,32692,32690
 * @Task#:Auto-2272
 * 
 * @author ssong
 * @Date  Jul 18, 2014
 */
public class POSProductSoldDetailReport_SerialPOS extends ResourceManagerTestCase{

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);
		result &= rm.verifyEmailReport(templatesPath, rm.getReportFromMailBox(
				host, username, password, comparedPath,rd.fileName, rd.emailSubject));

		rm.verifyReportCorrect(result);
	
		rm.logoutResourceManager();
	}

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NM Contract";
		login.location = "Administrator - Auto/State of New Mexico";

		rd.reportName = "POS Product Sold Detail Report";
		
		rd.agencyID = "Reserve America";
		rd.regionID = "Call Center";
		rd.facilityID = "Frostburg Call Center";
		rd.productGroup = "Serialized POS";
		rd.yesNoFlag = "Yes";
		rd.startDate = "Wed May 01 2013";
		rd.startTime = "12:00";
		rd.startTimeampm = "AM";
		rd.endDate = "Wed Jul 31 2013";
		rd.endTime = "12:00";
		rd.endTimeampm = "AM";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";
		rd.fileName = rd.reportName.replaceAll(" ", "")+"_SerializePos";
	}

}
