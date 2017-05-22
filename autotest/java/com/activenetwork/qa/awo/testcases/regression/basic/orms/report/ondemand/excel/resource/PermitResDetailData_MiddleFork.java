/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:This test case is designed to verify following criteria of Permit Reservation Details Report:
 * 1.Take out point: Name of the Take Out Point associated with the permit order should displayed in the column.
 * If Take Out Point permit type attribute is not applicable for the permit type associated with the permit order, the column shall be set to blank. 
 * 2.Launch Point: Name of the launch point associated with the permit order should displayed in the column. 
 * If launch point permit type attribute is not applicable for the permit type associated with the permit order, the column shall be set to blank.
 * 3.Exit Point: If Exit Point permit type attribute is not applicable for the permit type associated with the permit order, the column shall be set to blank
 * 4.Verify UI change: 
 * (1)if date range exceed 1 year, system will display error message.
 * (2)verify order reservation status selection criteria: added all option
 * @SPEC:TC:025356[Step3,Step5,Step8,Step9,Step10]
 * @Task#:Auto-2268
 * 
 * @author sborjigin
 * @Date  Aug 2, 2014
 */
public class PermitResDetailData_MiddleFork extends ResourceManagerTestCase{
	private OrmsReportCriteriaPage rmCriteriaPg = OrmsReportCriteriaPage.getInstance();
	private String expErrMsg = ".*Entered reporting period exceeds the maximum allowed reporting period of 1 year. Please change your reporting period.*";
	public void execute() {
		rm.loginResourceManager(login);
		rm.selectOneRpt(rd.group, rd.reportName);
		//verify UI change:error message of date range exceeding 1 year.
		verifyErrorMessage();
		rm.runSpecialRpt(rd, comparedPath);
		result = rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath,rd.fileName, rd.emailSubject));
		rm.verifyReportCorrect(result);
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		rd.group = "All";
		rd.reportName = "Permit Reservation Details Data Report";
		rd.facilityLocID="MIDDLE FORK OF THE SALMON (4 Rivers)";
		rd.startDate = "06/01/2013";
		rd.endDate = "06/25/2013";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rd.fileName = rd.reportName.replaceAll(" ", "")+"_TakeOutLaunchPoint";
		rd.dateType = "Order Date";
		rd.ordReservationStatus.add("All");
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Email";
	}
	public void verifyErrorMessage(){
		rmCriteriaPg.setStartDate(DateFunctions.getDateAfterToday(-366));
		ajax.waitLoading();
		rmCriteriaPg.setEndDate(DateFunctions.getDateAfterToday(1));
		ajax.waitLoading();
		rmCriteriaPg.clickOk();
		rmCriteriaPg.waitLoading();
		String err2 = rmCriteriaPg.getErrorMsg();
		if(StringUtil.isEmpty(err2) || !err2.matches(expErrMsg)){
			throw new ErrorOnPageException("Error message for setting date range greater than 366 is not correct.", expErrMsg, err2);
		} else {
			logger.info("Error message for setting date range greater than 366 is not correct.");
		}
	}
}
