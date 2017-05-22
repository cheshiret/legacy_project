/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.monthlyticketrecipientdistributionrpt;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:PCR#2647-Add error if distribution has not been run for report end date
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC029349
 * @Task#:Auto-2262
 * 
 * @author ssong
 * @Date  Jul 23, 2014
 */
public class VerifyDistributeNotComplete extends ResourceManagerTestCase{

	private String errorMessage;
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	public void execute() {
		// login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		
		String msg = rm.checkReportCriteriaValidation(rd);
		if(!msg.equalsIgnoreCase(errorMessage)){
			throw new ErrorOnPageException("Error message not correct",errorMessage,msg);
		}
		
		//logout resource manager
		rm.logoutResourceManager();		
	}
	
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "CA Contract";
		login.location = "Administrator/California Parks and Recreation";

		rd.group = "All";
		rd.reportName = "Monthly Ticketing Recipient Distribution Report";
		
		rd.startDate=DateFunctions.getToday();
		rd.endDate =DateFunctions.getToday();
		
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Online";	
		
		errorMessage = "There are no funds distributed yet for the End Date. Please enter a new End Date.";
	}	

}
