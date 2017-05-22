package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.undistributedfundsreport.selectioncriteria.resource;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class VerifyReconcilationStatusDropDownList extends ResourceManagerTestCase{

	private ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage.getInstance();
	
	@Override
	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		
		//verify reconciliation status drop down list contain correct options
		if(!rptCriteriaPg.getDefaultReconcileStatus().equals("All")){
			throw new ErrorOnPageException("Default Reconcilation Status Not Correct.");
		}
		String status = rptCriteriaPg.getAllReconcileStatus();
		if(!status.contains("Reconciled")){
			throw new ErrorOnPageException("Reconcilation Status List Not Contain Reconciled Option.");
		}
		if(!status.contains("Unreconciled")){
			throw new ErrorOnPageException("Reconcilation Status List Not Contain Unreconciled Option.");
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
