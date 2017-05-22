/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;


/**
 * @Description:This test case is designed to verify following criteria of Permit Reservation Details Report:
 * 1.Trailhead: Trailhead associated with the permit order.
 * If the Trailhead permit type attribute is not applicable for the permit type associated with the permit order, the column shall be set to blank.
 * 2.Permit Delivery Method: The Report Columns Permit Delivery Method display same with the input or selection of "Permit Delivery Method" when make order
 * 3.Verify UI change: 
 * (1)Add new selection criteria:Issue Station.
 * (2)Added "Issue Date" in the drop down list of Date Type selection criteria.
 * (3)Add new selection criteria:Issue Location

 * @SPEC:TC014055[Step23,Step27-28],TC025356[Step1,Step4,Step11-13]
 * @Task#:Auto-2268
 * 
 * @author sborjigin
 * @Date  Aug 2, 2014
 */
public class PermitResDetailData_Desolation extends ResourceManagerTestCase {
	public void execute() {
		rm.loginResourceManager(login);
		rm.selectOneRpt(rd.group, rd.reportName);
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
		rd.facilityLocID="Desolation Wilderness Permit";
		rd.startDate = "05/30/2012";
		rd.endDate = "06/03/2012";
		rd.fileName = rd.reportName.replaceAll(" ", "")+"_DeliveryMethodTrailHead";
		rd.collectIssueLocation="All";
		rd.issueLocation = "All";
		rd.ordReservationStatus.add("All");
		rd.deliveryMethod = "Online";
		rd.dateType="Issue Date";
	}
}
