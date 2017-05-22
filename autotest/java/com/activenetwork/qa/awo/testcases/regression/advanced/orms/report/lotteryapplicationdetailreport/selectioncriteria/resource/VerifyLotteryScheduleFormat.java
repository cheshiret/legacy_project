package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.lotteryapplicationdetailreport.selectioncriteria.resource;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class VerifyLotteryScheduleFormat extends ResourceManagerTestCase{

	private ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage.getInstance();
	
	@Override
	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rptCriteriaPg.selectLotteryName(rd.lotteryName);

		//verify default lottery schedule format correct
		//new format: ID 284497205 (12/01/2008 - 01/15/2009 )
		String schedules = rptCriteriaPg.getAllLotterySchedule();
		for(int i=1;i<schedules.split("#").length;i++){
			if(schedules.split("#")[i]!=null&&schedules.split("#")[i].length()>0){
				if(!schedules.split("#")[i].matches("ID [0-9]+ \\([0-9]{2}/[0-9]{2}/[0-9]{4} - [0-9]{2}/[0-9]{2}/[0-9]{4} \\)")){
					throw new ErrorOnPageException("Lottery Schedule Format Not Correct.");
				}
			}
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
		rd.lotteryName = "BWCAW Lottery 2009";
	}
}
