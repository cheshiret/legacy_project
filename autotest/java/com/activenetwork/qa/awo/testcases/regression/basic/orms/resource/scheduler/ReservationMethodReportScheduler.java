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
 * @SPEC:[TC:033958]
 * @Task#:Auto-1198
 * 
 * @author ssong
 * @Date  Aug 24, 2012
 */
public class ReservationMethodReportScheduler  extends ResourceManagerTestCase
{

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
		rd.reportName = "Reservation Methods Report";
		rd.reportFormat = "XLS";
		
		rd.agencyID = "USFS";
		rd.facilityID = "RYAN PARK";
		rd.startDate = "Tue Jul 17 2007";
		rd.endDate = "Wed Aug 1 2007";
		rd.recipient_name = TestProperty.getProperty("notification.from");
		
		//initialize schedule data
		schedule.scheduleType ="Single Instance";
	}
}

