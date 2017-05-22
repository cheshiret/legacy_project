package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.system.eftschedule;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTScheduleInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrEFTSchedulesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify adding a WEEKLY EFT schedule
 * @Preconditions:
 * @SPEC: <<Add EFT Schedule.doc>>
 * @Task#: AUTO-835
 * 
 * @author qchen
 * @Date  Dec 19, 2011
 */
public class AddWeekly extends LicenseManagerTestCase {
	private EFTScheduleInfo schedule = new EFTScheduleInfo();
	private LicMgrEFTSchedulesConfigurationPage eftScheduleConfigurationPage = LicMgrEFTSchedulesConfigurationPage.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoSysConfPgFromTopMenu();
		lm.gotoEFTSchedulePageFromSysConfigPage();
		
		//check whether an EFT schedule exists or not identified by frequency, if yes delete it
		this.checkAndDeleteEFTSchedule(schedule.frequency);
		
		//add EFT schedule
		schedule.id = lm.addEFTSchedule(schedule);
		
		//verify EFT schedule
		eftScheduleConfigurationPage.verifyEFTScheduleInfo(schedule);
		
		//update the vendor financial config default EFT schedule value to avoid impact other cases
		lm.updateVendorFinancialConfigDefaultEFTSchdl(schema, schedule.frequency);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		//EFT schedule info
		schedule.frequency = "Weekly";
		schedule.startDay = "Mon";
		schedule.invoiceDayOffset = "6";//Invoice Day Offset, which shall be the number of days after a 
		//Week's To Day when the Invoicing process shall be executed to include in the Invoice the EFT Batches 
		//for the previous From Day and To Day.  
		//The User shall be required to enter an integer value greater than or equal to 0 and less than or equal to 6.
		schedule.transmissionDayOffset = "1";
		schedule.creationUser = login.userName;
		schedule.creationLocation = login.location.split("/")[1];
//		schedule.creationDateTime = DateFunctions.getToday();
		schedule.creationDateTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
	}
	
	private void checkAndDeleteEFTSchedule(String frequency) {
		//check whether an EFT schedule which the Frequency is Weekly exists or not
		boolean exists = eftScheduleConfigurationPage.checkEFTScheduelExists(frequency);
		if(exists) {
			String id = eftScheduleConfigurationPage.getEFTScheduleID(frequency);
			logger.info("There is already an EFT schedule(ID#=" + id + ") which frequency is " + frequency + " existed in current contract.");
			//clean up environment
			lm.deleteEFTScheduleFromDB(schema, id);
		}
	}
}
