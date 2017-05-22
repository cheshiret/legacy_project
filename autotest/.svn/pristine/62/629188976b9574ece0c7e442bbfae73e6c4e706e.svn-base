package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.undistributedfundsreport.selectioncriteria.resource;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class VerifyPaymentGroupDropDownList extends ResourceManagerTestCase{

	private ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage.getInstance();
	
	@Override
	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		
		//verify payment group drop down list contain correct options
		if(!rptCriteriaPg.getDefaultPaymentGrp().equals("All")){
			throw new ErrorOnPageException("Default Payment Group Not Correct.");
		}
		String payGrps = rptCriteriaPg.getAllPaymentGrps();
		if(!payGrps.contains("Credit Card")){
			throw new ErrorOnPageException("Payment Group List Not Contain Credit Card Option.");
		}
		if(!payGrps.contains("Non Credit Card")){
			throw new ErrorOnPageException("Payment Group List Not Contain Non Credit Card Option.");
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
		rd.group = "Financial Reports";
		rd.reportName = "UnDistributed Funds Report";
	}
}
