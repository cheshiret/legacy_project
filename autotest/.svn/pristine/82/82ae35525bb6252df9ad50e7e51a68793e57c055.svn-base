package com.activenetwork.qa.awo.testcases.regression.basic.orms.resource.scheduler;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSchedulerJobPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC: Request Method - Schedulable [TC:107486]
 * @Task#: Auto-2311
 * 
 * @author Jane Wang
 * @Date  July 31, 2014
 */
public class DailyArrivalReportTicketingScheduler extends ResourceManagerTestCase{

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
		rd.group = "Operational Reports";
		rd.reportName = "Daily Arrival Report-Ticketing";
		rd.reportFormat = "PDF";
		rd.facilityID = "ROOSEVELT-VANDERBILT NATIONAL HISTORIC SITES";

		rd.recipient_name = TestProperty.getProperty("notification.from");
		
		//initialize schedule data
		schedule.scheduleType ="Single Instance";
	  }
}
