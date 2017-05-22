/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.resource.scheduler;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSchedulerJobPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC118351
 * @Task#:Auto-2205
 * 
 * @author ssong
 * @Date  May 20, 2014
 */
public class SCRevenueExportReportScheduler extends ResourceManagerTestCase{

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

		// initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "SC Revenue Export Report";
		rd.reportFormat = "CSV";
	
		rd.recipient_name = TestProperty.getProperty("notification.from");
		
		//initialize schedule data
		schedule.scheduleType ="Single Instance";
	}
}
