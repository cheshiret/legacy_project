package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.lotteryapplicationdetailreport.selectioncriteria.resource;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class VerifyOrderReservationStatusListBox extends ResourceManagerTestCase{

	private ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage.getInstance();
	
	@Override
	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		//verify order reservation status options correct
		String allStatus = rptCriteriaPg.getAllOrderReservationStatus();
		if(!allStatus.contains("Entered")||!allStatus.contains("Awarded")||!allStatus.contains("Pre-Arrival")||!allStatus.contains("Reserved")||!allStatus.contains("Unsuccessful")||!allStatus.contains("Revoked")){
			throw new ErrorOnPageException("Order Reservation Status Not Correct.");
		}

		//verify all Order Reservation Status is default selected
		if(rptCriteriaPg.getDefaultOrderResStatus().size()!=rptCriteriaPg.getAllOrderResStatusNum()){
			throw new ErrorOnPageException("Default Selected Order Reservation Status Not Correct.");
		}
		//logout resource manager
		rm.logoutResourceManager();		
	}

	
	@Override
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		//initialize report data
		rd.group = "Operational Reports";
		rd.reportName = "Lottery Application Details Report";
	}
}
