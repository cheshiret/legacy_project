package com.activenetwork.qa.awo.testcases.regression.basic.orms.resource.scheduler;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSchedulerJobPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author Ssong
 */
public class RAGrossContractRevenueReportScheduler extends ResourceManagerTestCase
{
	/**
	 * Script Name   : <b>RAGrossContractRevenueReportScheduler</b>
	 * Generated     : <b>Mar 4, 2010 3:19:34 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/04
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
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";
	
		//initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "RA Gross Contract Revenue Report";
		rd.reportFormat = "XLS";
	
		rd.recipient_name = TestProperty.getProperty("notification.from");
		
		rd.dateType = "Specified Dates";
		rd.contract = "SC";
		rd.startDate = "Tue Jul 1 2008";
		rd.endDate = "Tue Jul 1 2008";
		
		//initialize schedule data
		schedule.scheduleType ="Single Instance";
	}
}

