package com.activenetwork.qa.awo.testcases.regression.basic.orms.resource.scheduler;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSchedulerJobPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author Ssong
 */
public class PaymentDetailsReportScheduler extends ResourceManagerTestCase
{
	/**
	 * Script Name   : <b>PaymentDetailsReportScheduler</b>
	 * Generated     : <b>Mar 4, 2010 3:03:00 AM</b>
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
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
	
		//initialize report data
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NRRS";
		rd.group = "Financial Reports";
		rd.reportName = "Payment Details Report";
		rd.reportFormat = "XLS";
		rd.facilityID = DataBaseFunctions.getFacilityName("73494", schema);//"ACORN CAMP EAST";
	
		rd.recipient_name = TestProperty.getProperty("notification.from");
		
		//initialize schedule data
		schedule.scheduleType ="Single Instance";
	}
}

