/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.reservationclosurereport;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:This case used to verify reservation closure report of closure id criteria
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:Selection Criteria [TC:054191]
 * @Task#:Auto-2305
 * 
 * @author Vivain
 * @Date  Jul 3, 2014
 */
public class VerifySearchCriteria_Closure extends ResourceManagerTestCase  {
	private String msg_invalidClosure,msg_notBelong;

	@Override
	public void execute() {
		rm.loginResourceManager(login);
		rm.selectOneRpt(rd.group, rd.reportName);
		//closure id contain non-numeric characters
		String message = this.setupReportSearchCriteria(rd);
		this.verifyErrorMessage(msg_invalidClosure, message);
		
		//closure id not belong to the selected location "Allan H. Treman State Marine Park"
		rd.closureID = "123456";
		message = this.setupReportSearchCriteria(rd);
		this.verifyErrorMessage(msg_notBelong, message);
		
		rm.logoutResourceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);

		login.contract = "NY Contract";
		login.location = "Administrator/Contract";
		rd.group = "Operational Reports";
		rd.reportName = "Reservation Closure Report";
        
		rd.agencyID="OPRHP";
		rd.regionLocID = "All";
		rd.facilityID = "Allan H. Treman State Marine Park";
		rd.facilityLocID = "All";
		rd.productCategory = "Slip";
		rd.closureID = "Test123";

		rd.startDate = "Tue Jan 01 2013";
		rd.endDate = "Tue Dec 31 2013";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";
		
		msg_invalidClosure = "An invalid Closure ID entered. Please provide a valid Closure ID.";
		msg_notBelong = "The entered Closure ID does not belong to the selected location. Please provide a valid Closure ID.";
	}
	
	private String setupReportSearchCriteria(ReportData rd){
		OrmsReportCriteriaPage rmCriteriaPg = OrmsReportCriteriaPage.getInstance();
		
		logger.info("Setup Report Search Criteria and Get error message.");
		rmCriteriaPg.setReportCriteria(rd);
		rmCriteriaPg.clickOk();
		rmCriteriaPg.waitLoading();
		String message = rmCriteriaPg.getErrorMsg();
		return message;
	}
	
	private void verifyErrorMessage(String expMsg,String actMsg){
		logger.info("Verify error message.");
		boolean result = MiscFunctions.compareResult("Error Message", expMsg, actMsg);
		if(!result){
			throw new ErrorOnPageException("Error Message not correct, please check log.");
		}else logger.info("Error Message is correct.");
	}

}
