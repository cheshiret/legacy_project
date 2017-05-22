/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

/**
 * @Description:
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC067495,067496,069889,067497,067498,067499,067500,067501,067502
 * @Task#:Auto-2197
 * 
 * @author ssong
 * @Date  May 22, 2014
 */
public class VendorFeeReport extends ResourceManagerTestCase{

	private String saveAsName;
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		//first all use default value to request report
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		result = rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath,saveAsName, rd.emailSubject));
		
		initializeRptCriteria();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}
	
	private void initializeRptCriteria(){
		rd.locationClass = "State Parks Agent";
		rd.store = "LAKE LINCOLN STATE PARK";
		rd.reportBy = "Posted Date";
		rd.reportReversalsAt = "Original Sales Location";
		rd.includeStEFTAdjustment = "Yes";
	}

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract"; 
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		// report information
		rd.group = "All";
		rd.reportName = "Vendor Fee Report";
		rd.startDate = "05/01/2013";
		rd.endDate = "05/07/2013";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Email";
		
		saveAsName = rd.reportName.replaceAll(" ", "")+"_AllDefault";
	}

}
