/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.failedtransactionvalidationrpt;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:Verify login location not match a chart of account and default start date is yesterday
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC:025038
 * @Task#:Auto-2198
 * 
 * @author ssong
 * @Date  May 21, 2014
 */
public class VerifySelectionCriteriaAndDate extends LicenseManagerTestCase {

	private ReportData rd = new ReportData();
	private OrmsReportCriteriaPage criteriaPg = OrmsReportCriteriaPage.getInstance();
	private String errorMsg;
	
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.selectOneRpt(rd.group, rd.reportName);
		String defaultDate = criteriaPg.getStartDate();
		if(DateFunctions.diffBetween(DateFunctions.getDateAfterToday(-1), defaultDate)!=0){
			throw new ErrorOnPageException("Default Start Date not Correct.",DateFunctions.getDateAfterToday(-1),defaultDate);
		}
		//check run report on non correct location level
		verifyErrorMsg();
		
		
		lm.logOutLicenseManager();
	}
	
	private void verifyErrorMsg(){
		criteriaPg.clickOk();
		criteriaPg.waitLoading();
		String actualMsg = criteriaPg.getErrorMsg();
		if(!actualMsg.equalsIgnoreCase(errorMsg)){
			throw new ErrorOnPageException("Error Message Not Correct",errorMsg,actualMsg);
		}
	}

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/LAKE LINCOLN STATE PARK(Store Loc)";
		
		errorMsg = "This report cannot be accessed at your logged-in location level. Please access this report via a different Role/Location.";
		rd.reportName = "Failed Transaction Validation Report";
	}

}
