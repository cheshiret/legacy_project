/**
 * 
 */
package com.activenetwork.qa.awo.testcases.production.orms;


import java.util.Random;
import java.util.TimeZone;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrSchedulerPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:
 * 	1. Go to CA Contract, and make scheduled report.
 * 	2. The report time is 5 minutes later than current time.
 * 	3. Wait 5 mins. And check email box, the report should be there and content is correct.
 * 
 * @Preconditions:
 * 1. PDF report template 'CASP-7-DayCampersReport-DOHENYSB-.PDF' exists on disk X:\.
 * @SPEC:http://wiki.reserveamerica.com/display/qa/Sanity+Test+Procedure+-+ORMS
 * Reports - Scheduled
 * @Task#:AUTO-1494
 * 
 * @author pzhu
 * @Date  Feb 19, 2013
 */


public class RM_ScheduledReport extends ResourceManagerTestCase {
	public ResMgrSchedulerPage schedulePg = ResMgrSchedulerPage.getInstance();
	
	public void execute() {
		rm.loginResourceManager(login);
		rm.gotoSchedulerPage();
		
		schedule.scheduleId = rm.addNewReportScheduler(schedule,rd);  //add a new report scheduler
		rm.searchAndActivateScheduleByID(schedule.scheduleId);
		schedulePg.verifyScheduleInfo(schedule);  //verify new scheduler info
		logger.info("Email subject is-->"+rd.emailSubject);
		
		Browser.sleep(300);//sleep 5 minutes.
		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}


	public void wrapParameters(Object[] param) {
		
		//env = "live";
		if(env.equalsIgnoreCase("live")) {
			AwoUtil.loadLiveInformation();
			login.url = TestProperty.getProperty(env + ".training.orms.url");
		  	login.userName = TestProperty.getProperty(env+".training.orms.adm.user");
			login.password = TestProperty.getProperty(env+".training.orms.adm.pw");
			login.location = "imp-admin-role/";	
			TestProperty.putProperty("role.auto", "false"); //Lesley[20130924]: update for production testing
		} else {
			login.location = "Administrator - Auto/";
			login.userName = TestProperty.getProperty("orms.adm.user");
			login.password = TestProperty.getProperty("orms.adm.pw");
		}

		login.contract = "CA Contract";
		login.location = login.location+"California Parks and Recreation";

		
		String rand = String.valueOf(((new Random().nextInt(9999999))));
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		rd.group = "Operational Reports";
		rd.reportName="7-Day Campers Report";
		rd.reportFormat = "PDF";
		rd.locationID = "DOHENY SB";
		rd.orderBy = "Loop then Site";
		rd.showEmptySites = "Yes";
		rd.dateType = "Specified Dates";
		rd.startDate = "Sun Jul 04 2010";
		rd.recipient_name = TestProperty.getProperty("notification.to");
		rd.emailSubject = TestProperty.getProperty("mail.report.subject.keyword", "")+"RM_ScheduledReport"+rand;
		rd.deliveryMethod= "Email";
		
		schedule.scheduleType = "Single Instance";
		schedule.scheduleName = rd.reportName;
		schedule.location = rd.locationID;
		schedule.frequency = "Once";
		
		schedule.startDate = DateFunctions.getToday(timeZone); 
		schedule.timePeriod = "5"; //after 5 mins, schedule will be triggered.
		
		
		
	}
}
