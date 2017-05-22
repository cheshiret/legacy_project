/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.system.eftschedule;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTScheduleInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrAddEFTScheduleWidget;
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
 * @Date  Apr 10, 2013
 */
public class VerifyErrorMsg  extends LicenseManagerTestCase{

	private EFTScheduleInfo schedule = new EFTScheduleInfo();
	private String msg1,msg2,msg3;
	
	@Override
	public void execute() {
		lm.deleteEFTScheduleFromDBByName(schema, schedule.name);
		
		lm.loginLicenseManager(login);
		lm.gotoSysConfPgFromTopMenu();
		lm.gotoEFTSchedulePageFromSysConfigPage();
		
		//add EFT schedule
		schedule.id = lm.addEFTSchedule(schedule);
		checkNegativeSids();
		
		//clean up
		lm.deleteEFTScheduleFromDB(schema, schedule.id);
		
		lm.logOutLicenseManager();
		
	}
	
	private void checkNegativeSids(){
		LicMgrAddEFTScheduleWidget addPg = LicMgrAddEFTScheduleWidget.getInstance();
		LicMgrEFTSchedulesConfigurationPage eftSchedulesPage = LicMgrEFTSchedulesConfigurationPage
				.getInstance();
		
		String originalName = schedule.name;
		schedule.name = "";
		String msg = lm.addEFTSchedule(schedule);
		if(!msg.equalsIgnoreCase(msg1)){
			throw new ErrorOnPageException("EFT Schedule Name can not be empty",msg,msg1);
		}
		schedule.name = originalName;
		msg = lm.setupEFTSchedule(schedule);
		if(!msg.equalsIgnoreCase(msg2)){
			throw new ErrorOnPageException("EFT Schedule Name can not be duplicate",msg,msg2);
		}
		schedule.name +=1;
		msg = lm.setupEFTSchedule(schedule);
		if(!msg.equalsIgnoreCase(msg3)){
			throw new ErrorOnPageException("EFT Schedule Seting can not be duplicate",msg, msg3);
		}
		if(addPg.checkStatusDropDownEnable()){
			throw new ErrorOnPageException("Status drop down list should be disabled when creating EFT schedule.");
		}
		if(!addPg.getSelectedStatus().equalsIgnoreCase(ACTIVE_STATUS)){
			throw new ErrorOnPageException("Default status should be Active when creating schedule.");
		}
		addPg.clickCancel();
		ajax.waitLoading();
		eftSchedulesPage.waitLoading();
		if(eftSchedulesPage.checkEFTScheduleExistByName(schedule.name)){
			throw new ErrorOnPageException("Cancel Button not work, scheduel "+schedule.name+" should exists.");
		}
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
		schedule.name = "CheckNegativeSides";
		schedule.frequency = "Monthly";
		schedule.startDay = "21";		
		schedule.invoiceDayOffset = "2";
		schedule.transmissionDayOffset = "3";

		msg1 = "The Name is required. Please specify the Name.";
		msg2 = "There is already an Invoice Schedule with the same Name. Please change your entry.";
		msg3 = "An Invoice Schedule with the same setup already exists. Please change your entry.";
	}

}
