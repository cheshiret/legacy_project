package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.rafee;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:
 * @Preconditions:
 * @SPEC:Specify an entry for RA Fee Schedule ID [TC:042270]
 * @Task#:AUTO-1464
 * 
 * @author Jane
 * @Date  Feb 20, 2013
 */
public class SearchByID extends FinanceManagerTestCase {
	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private FinMgrRaFeeSchMainPage feeMainPg = FinMgrRaFeeSchMainPage.getInstance();
	
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		schedule.feeId = fnm.addNewRaFeeSchedule(schedule);
		
		schedule.searchBy="Fee Schedule ID";
		schedule.searchValue=schedule.feeId;
		schedule.searchStatus=OrmsConstants.ACTIVE_STATUS;//search active status
		feeMainPg.searchSchedule(schedule);
		verifySearchByID(schedule.feeId);
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		String facilityID = "552818";//Mayo River State Park
		schedule.location = DataBaseFunctions.getFacilityName(facilityID, schema);
		schedule.locationCategory = "Park";
		schedule.activeStatus = OrmsConstants.NO_STATUS;
		schedule.productCategory = "Slip";
		schedule.dock = "DockForSlipUseFee";
		schedule.productGroup = "Full attributes";
		schedule.product = "All";
		schedule.effectDate = DateFunctions.getDateAfterToday(3);
		schedule.marinaRateType = "All";
		schedule.salesChannel = "All";
		schedule.raFeeOption = "Service Rendered";
		schedule.tranType = "Reservation";
		schedule.tranOccur = "14 or More Days Before Arrival Date";
		schedule.acctCode = "2000.1601.532170021; Admin Svc-Vendor Commission (RA Fees)";
		schedule.baseRate = "7.37";
	}

	private void verifySearchByID(String scheID) {
		logger.info("Verify search result by schedule ID.");
		
		List<String> idsUI=feeMainPg.getFeeID();
		if(idsUI==null || idsUI.size()<1 || idsUI.size()>1)
			throw new TestCaseFailedException("Failed to search RA Fee schedule by id. There should be only one record:"+scheID);
		if(!idsUI.get(0).equals(scheID))
			throw new TestCaseFailedException("Failed to search RA Fee schedule by id. There should be only one record:"+scheID);
		
		logger.info("---Search RA Fee schedule by ID successfully.");
	}
}
