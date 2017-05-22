/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.transactiondetaildeposit;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This test case check report UI-validate date component
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC:029646
 * @Task#:Auto-2202
 * 
 * @author ssong
 * @Date  May 8, 2014
 */
public class VerifyReportDate extends ResourceManagerTestCase{

	private List<String> errorMessage = new ArrayList<String>();
	private OrmsReportCriteriaPage crtPg = OrmsReportCriteriaPage.getInstance();
	
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		
		crtPg.selectAgencyID("BLM");
		intializeMsg();
		// Not enter Start Date and End Date
		rm.verifyReportDate("", "", errorMessage.get(0));
        // Only enter Start Date
		rd.startDate = DateFunctions.getDateAfterToday(-1);
		rm.verifyReportDate(rd.startDate, " ", errorMessage.get(1));
        // Only enter End Date
		rd.endDate = DateFunctions.getDateAfterToday(-1);
		rm.verifyReportDate(" ", rd.endDate, errorMessage.get(2));
		// End Date is early than Start Date
		rd.startDate = DateFunctions.getDateAfterToday(-1);
		rd.endDate = DateFunctions.getDateAfterToday(-2);
		rm.verifyReportDate(rd.startDate, rd.endDate, errorMessage.get(3));
		//end date is future date
		rd.endDate = DateFunctions.getDateAfterToday(2);
		rm.verifyReportDate(rd.startDate, rd.endDate, errorMessage.get(4));
		// End Date minus Start Date is equal 93 days                   
		rd.startDate = DateFunctions.getDateAfterToday(-94);
		rd.endDate = DateFunctions.getDateAfterToday(-1);
		rm.verifyReportDate(rd.startDate, rd.endDate, errorMessage.get(5));
		
		rm.logoutResourceManager();
	}
	
	protected void intializeMsg(){
		//Error Message
		errorMessage.add("Missing Required Field: StartDate, EndDate");
		errorMessage.add("Missing Required Field: EndDate");
		errorMessage.add("Missing Required Field: StartDate");
		errorMessage.add("End date should be greater than or equal to start date");
		errorMessage.add("The Start/End Date must be less than today's date. Please enter a new Start/End Date");
//		errorMessage.add("The Date Range must be less than 93 days. Please enter a new date range.");
		errorMessage.add("Reporting period exceeds the maximum report period of 93 days. Please refine the search criteria");
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
		
	}

}
