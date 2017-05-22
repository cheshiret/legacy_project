package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafee;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: Verify the status for new added RA Fee schedule for Marina
 * @Preconditions: none
 * @SPEC: Add RA Fee Schedule-Status [TC:041566]
 * @Task#: Auto-1460
 * @author Phoebe
 * @Date  Feb 02, 2013
 */
public class VerifyStatusForMarina extends FinanceManagerTestCase{
	private FinMgrRaFeeSchMainPage listPage = FinMgrRaFeeSchMainPage.getInstance();
	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		
		//add a new RA Fee Schedule for Slip 
		schedule.feeId = fnm.addNewRaFeeSchedule(schedule);
		
		//verify the status for new added ra fee schedule is inactive
		listPage.verifyFeeScheduleStatus(schedule.feeId, OrmsConstants.NO_STATUS);

		//Active the schedule
		listPage.activeRaFeeSchedules(schedule.feeId);
		
		//verify the new added ra fee schdule has been actived
		listPage.verifyFeeScheduleStatus(schedule.feeId, OrmsConstants.YES_STATUS);
		
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		String facilityID = "552903";//Jordan Lake State Rec Area
		schedule.location = DataBaseFunctions.getFacilityName(facilityID, schema);
		schedule.locationCategory = "Park";
		schedule.productCategory = "Slip"; //This is the check point, can not be changed
		
		schedule.effectDate = DateFunctions.getDateAfterToday(-100);
		schedule.acctCode = "4000.1601.532170021; Admin Svc-Vendor Commission (RA Fees)";
		schedule.baseRate = "15.55";
	}
}
