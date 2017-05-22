package com.activenetwork.qa.awo.testcases.regression.basic.orms.resource.scheduler;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSchedulerPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddSingleInstanceReportSchedule_DynamicDate extends ResourceManagerTestCase
{
	/**
	 * Script Name   : <b>AddSingleInstanceReportSchedule_DynamicDate</b>
	 * Generated     : <b>Feb 27, 2010 3:53:11 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/27
	 * @author QA
	 */
  private ResMgrSchedulerPage schedulePg = ResMgrSchedulerPage.getInstance();
  
  public void execute() {
	rm.loginResourceManager(login);
	
	rm.gotoSchedulerPage();
	schedule.scheduleId = rm.addNewReportScheduler(schedule,rd);  //add a new report scheduler
	schedulePg.verifyScheduleInfo(schedule);  //verify new scheduler info
	
	rm.logoutResourceManager();
  }

  public void wrapParameters(Object[] param) {
	login.url = AwoUtil.getOrmsURL(env);
	login.contract = "NRRS Contract";
	login.location = "Administrator/NRRS";

	//initialize report data
	rd.group = "Financial Reports";
	rd.reportName = "Central Deposit Report";
	rd.reportFormat = "XLS";
	rd.facilityID = "FISHERMENS BEND";
	rd.dateType = "Dynamic Dates";

	rd.recipient_name = TestProperty.getProperty("notification.from");
	
	//initialize schedule data
	schedule.scheduleType ="Single Instance";
	schedule.notifyRecipient = TestProperty.getProperty("notification.from");
	schedule.location = rd.facilityID;
	schedule.scheduleName = rd.reportName;
	schedule.frequency = "Once";
  }
}

