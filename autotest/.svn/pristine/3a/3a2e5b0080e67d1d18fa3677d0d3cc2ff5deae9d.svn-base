/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.scparkrevenueexportrpt;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC:118351
 * @Task#:Auto-2205
 * 
 * @author ssong
 * @Date  May 20, 2014
 */
public class VerifyRptDateAndOptions extends ResourceManagerTestCase{

	private List<String> errorMessage = new ArrayList<String>();
	private StringBuffer deliveryMethods = new StringBuffer();
	private OrmsReportCriteriaPage crteriaPg = OrmsReportCriteriaPage.getInstance();
	
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		
		intializeMsg();
		// Not enter Start Date and End Date
		rm.verifyReportDate("", "", errorMessage.get(0));
        // Only enter Start Date
		rd.startDate = DateFunctions.getDateAfterToday(-1);
		rm.verifyReportDate(rd.startDate, " ", errorMessage.get(1));
        // Only enter End Date
		rd.endDate = DateFunctions.getDateAfterToday(0);
		rm.verifyReportDate(" ", rd.endDate, errorMessage.get(2));
		// End Date is early than Start Date
		rd.startDate = DateFunctions.getDateAfterToday(-1);
		rd.endDate = DateFunctions.getDateAfterToday(-2);
		rm.verifyReportDate(rd.startDate, rd.endDate, errorMessage.get(3));
		// report date period exceeds 366 days               
		rd.startDate = DateFunctions.getDateAfterToday(-367);
		rd.endDate = DateFunctions.getDateAfterToday(-1);
		rm.verifyReportDate(rd.startDate, rd.endDate, errorMessage.get(4));
		
		String methods = crteriaPg.getAllDeliveryMethod();
		if(!methods.equalsIgnoreCase(deliveryMethods.toString())){
			throw new ErrorOnPageException("Report Delivery Methods not correct.",deliveryMethods.toString(),methods);
		}
		
		rm.logoutResourceManager();
	}
	
	protected void intializeMsg(){
		//Error Message
		errorMessage.add("Missing Required Field: StartDate, EndDate");
		errorMessage.add("Missing Required Field: EndDate");
		errorMessage.add("Missing Required Field: StartDate");
		errorMessage.add("End date should be greater than or equal to start date");
		errorMessage.add("Reporting period exceeds the maximum report period of 366 days. Please refine the search criteria");
	}

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

		// initialize report data
		rd.group = "All";
		rd.reportName = "SC Revenue Export Report";
		
		deliveryMethods.append("SFTP").append("#");
		deliveryMethods.append("Email").append("#");
		deliveryMethods.append("Online").append("#");
	}
}
