/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This test case is a basic case to cover new add 'allocation type' drop down
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC013811,028926
 * @Task#:Auto-2267
 * 
 * @author ssong
 * @Date  Jul 29, 2014
 */
public class QuotaAvailabilityReport_AllocationType extends ResourceManagerTestCase{
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		this.checkIncludeNoShows();
		rm.runSpecialRpt(rd, comparedPath);
		
		result = rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath,rd.fileName, rd.emailSubject));

		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}
	
	private void checkIncludeNoShows(){
		OrmsReportCriteriaPage criteriaPg = OrmsReportCriteriaPage.getInstance();
		
		criteriaPg.selectFacilityLocID(rd.facilityLocID);
		//The facility supports the permit inventory allocations,should not display 'Include NO Show' dropdown
		if(criteriaPg.checkSelectDisplayColumnsExist()){
			throw new ErrorOnPageException("Include No Shows Dropdown should not displayed.");
		}
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		rd.group = "All";
		rd.reportName = "Quota Availability Report";
		rd.facilityLocID="HELLS CANYON - SNAKE RIVER (4 Rivers)";
		rd.startDate = "Sat Jun 01 2013";
		rd.endDate = "Thu Jun 20 2013";
		rd.quotaInterval="River Launches";
		//for quota allocation,allocation type and entrance, all use default value,should be (Total,All,All)
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		
		rd.fileName = caseName;
		
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Email";

	}
}
