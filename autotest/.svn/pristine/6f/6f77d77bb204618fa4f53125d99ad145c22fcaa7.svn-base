/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.system.eftschedule;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTScheduleInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrEFTSchedulesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:TC:057134
 * @Task#:Auto-1453
 * 
 * @author ssong
 * @Date  Apr 9, 2013
 */
public class AddMultiSchedWithSameFrequency extends LicenseManagerTestCase{

	private EFTScheduleInfo schedule = new EFTScheduleInfo();
	private LicMgrEFTSchedulesConfigurationPage eftSchedulesPage = LicMgrEFTSchedulesConfigurationPage
			.getInstance();
	
	@Override
	public void execute() {
		lm.deleteEFTScheduleFromDBByName(schema, schedule.name);
		
		lm.loginLicenseManager(login);
		lm.gotoSysConfPgFromTopMenu();
		lm.gotoEFTSchedulePageFromSysConfigPage();
		
		//add EFT schedule
		schedule.id = lm.addEFTSchedule(schedule);
		schedule.name +=1;
		schedule.startDay = "14";
		//add another EFT schedule with same frequency but different settings
		String id = lm.addEFTSchedule(schedule);
		if(!eftSchedulesPage.checkEFTScheduleExistByName(schedule.name)){
			throw new ErrorOnPageException("Add Multiple schedule with Same frequency Failed.");
		}
		
		lm.deleteEFTScheduleFromDB(schema, schedule.id);
		lm.deleteEFTScheduleFromDB(schema, id);
		
		lm.logOutLicenseManager();
	}

	/* (non-Javadoc)
	 * @see testAPI.abstractcases.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		//EFT schedule info
		schedule.name = "SameFrequency";
		schedule.frequency = "Monthly";
		schedule.startDay = "13";		
		schedule.invoiceDayOffset = "5";
		schedule.transmissionDayOffset = "2";

	}

}
