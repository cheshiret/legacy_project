package com.activenetwork.qa.awo.testcases.regression.basic.orms.resource.scheduler;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSchedulerJobPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author Ssong
 */
public class TransactionsbyOperatorReportScheduler extends ResourceManagerTestCase
{
	/**
	 * Script Name   : <b>TransactionsbyOperatorReportScheduler</b>
	 * Generated     : <b>Mar 5, 2010 2:11:35 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/05
	 * @author QA
	 */
	private ResMgrSchedulerJobPage jobPg = ResMgrSchedulerJobPage.getInstance();
	
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.gotoSchedulerPage();
		schedule.scheduleId = rm.addNewReportScheduler(schedule,rd);  //add a new report scheduler
		rm.switchToScheduleJobPage();
		
		jobPg.checkSchedulerIsRun(schedule.scheduleId);   //check given scheduler is Run
		
		rm.logoutResourceManager();
	}
	
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
	
		//initialize report data
		rd.group = "Statistical Reports";
		rd.reportName = "Transactions by Operator Report";
		rd.reportFormat = "XLS";
		rd.callCenterID = "WI Call Center";
		
		rd.recipient_name = TestProperty.getProperty("notification.from");
		
		//initialize schedule data
		schedule.scheduleType ="Single Instance";
	}
}

