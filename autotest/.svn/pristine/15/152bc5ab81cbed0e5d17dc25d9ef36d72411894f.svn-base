package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.lotteryapplicationdetailreport.selectioncriteria.resource;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class VerifyLotteryScheduleDefaultValue extends ResourceManagerTestCase{

	private ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage.getInstance();
	
	@Override
	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		//verify default lottery schedule value correct
		if(!rptCriteriaPg.getDefaultLotterySchedule().equals(rd.lotterySched)){
			throw new ErrorOnPageException("Default Lottery Schedule Not Correct.");
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
		rd.lotterySched = "Please Select";
	}
}

