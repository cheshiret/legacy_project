package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.lotteryapplicationdetailreport.selectioncriteria.resource;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSendReportPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class VerifyLotteryNameMandatoryInput extends ResourceManagerTestCase{

	@Override
	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		//this method used to verify lottery name is mandatory input
		this.verifyLotteryNameIsMandatory();
		
		//logout resource manager
		rm.logoutResourceManager();		
	}

	private void verifyLotteryNameIsMandatory(){
		ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage.getInstance();
		ResMgrSendReportPage rmSendRptPg = ResMgrSendReportPage.getInstance();
		
		logger.info("Verify Lottery Name Is Mandatory Input.");
		
		rptCriteriaPg.selectLotteryName(rd.lotteryName);
		rptCriteriaPg.clickOk();
		Object page = browser.waitExists(rptCriteriaPg,rmSendRptPg);
		if(page==rmSendRptPg){
			throw new ErrorOnPageException("Lottery Name is mandatory input,it should not display "+rmSendRptPg);
		}
		String errorMsg = rptCriteriaPg.getErrorMsg();
		if(!errorMsg.equals("Please select the Lottery Name.")){
			throw new ErrorOnPageException("Error Message Not Correct.");
		}
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
		rd.lotteryName = "";
	}
}
