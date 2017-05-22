package com.activenetwork.qa.awo.testcases.regression.basic.orms.resource.scheduler;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSchedulerPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description : Functional Test Script
 * 
 * @author QA
 */
public class SearchReportScheduler extends ResourceManagerTestCase {
	/**
	 * Script Name : <b>SearchReportScheduler</b> Generated : <b>Mar 1, 2010
	 * 3:28:15 AM</b> Description : Functional Test Script Original Host : WinNT
	 * Version 5.1 Build 2600 (S)
	 * 
	 * @since 2010/03/01
	 * @author QA
	 */
	private ResMgrSchedulerPage schedulePg = ResMgrSchedulerPage.getInstance();

	private String searchTypes[];
	private String searchValues[];
	private String statuses[];
	private String scheduleTypes[];
	
	public void execute() {
		rm.loginResourceManager(login);

		rm.gotoSchedulerPage();
		schedule.scheduleId = rm.addNewReportScheduler(schedule, rd); // add a new report scheduler

		for(int i = 0; i < searchTypes.length; i ++) {
			this.initializeSchedulerInfo(i);
			// Search schedule by different criteria read from data pool
			schedulePg.setUpSearchCriteria(schedule);
			schedulePg.verifySchedulerInSearchList(schedule.scheduleId);
			schedulePg.verifyScheduleList(schedule);
		}

		rm.logoutResourceManager();
	}

	private void initializeSchedulerInfo(int index) {
		schedule.searchBy = searchTypes[index];
		schedule.searchValue = searchValues[index];
		schedule.activeStatus = statuses[index];
		schedule.scheduleType = scheduleTypes[index];
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		// initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "Central Deposit Report";
		rd.reportFormat = "XLS";
		rd.facilityID = "FISHERMENS BEND";
		rd.dateType = "Specified Dates";
		rd.startDate = "Thu Jan 1 2009";
		rd.endDate = "Sat Jan 3 2009";
		rd.recipient_name = TestProperty.getProperty("notification.from");

		// initialize schedule data
		schedule.scheduleType = "Single Instance";
		schedule.notifyRecipient = TestProperty
				.getProperty("notification.from");
		schedule.location = rd.facilityID;
		schedule.scheduleName = rd.reportName;
		schedule.createUser = com.activenetwork.qa.awo.util.DataBaseFunctions
				.getLoginUserName(login.userName).replaceAll(",", ", ");
		schedule.timePeriod = "5";
		
		searchTypes = new String[] {"ScheduleID", "", "ScheduleName", "Location", "Created By (Last Name)", "Created By (First Name)", ""};
		searchValues = new String[] {"", "", "", "", "Test-Auto", "QA-Auto", ""};
		statuses = new String[] {"", "Active", "", "", "", "", ""};
		scheduleTypes = new String[] {"", "", "", "", "", "", "Single Instance"};
	}
}
