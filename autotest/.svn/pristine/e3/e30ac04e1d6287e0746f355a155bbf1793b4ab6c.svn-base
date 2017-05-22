package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafee;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: Verify the cancel operation when after set up all the informations for new added RA Fee schedule for Marina
 * @Preconditions: none
 * @SPEC: 	Add RA Fee Schedule-Cancel [TC:041565]
 * @Task#: Auto-1460
 * @author Phoebe
 * @Date  Feb 02, 2013
 */
public class VerifyCancelForMarina extends FinanceManagerTestCase{
	private FinMgrRaFeeSchMainPage listPage = FinMgrRaFeeSchMainPage.getInstance();
	private FinMgrRaFeeSchDetailPage detailsPage = FinMgrRaFeeSchDetailPage.getInstance();
	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
				
		//cancel after setup all entries at 'Fee Details' page
		fnm.gotoRAFeeScheduleDetailPgByAddNew(schedule.location, schedule.locationCategory);
		detailsPage.selectPrdCategory(schedule.productCategory);
		detailsPage.waitLoading();
		detailsPage.setupRaFeeSchDetailInfo(schedule, false);
		detailsPage.clickCancel();
		listPage.waitLoading();//checkpoint
		
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
