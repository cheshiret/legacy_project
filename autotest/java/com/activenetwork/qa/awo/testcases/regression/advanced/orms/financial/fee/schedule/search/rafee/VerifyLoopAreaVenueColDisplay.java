package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.rafee;

import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.raFee.FinMgrRaFeeDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
/**
 * @Preconditions:
 * @SPEC: RA Fee Sechdules - Display 'Loop/Area/Venue' or 'Dock/Area' Columns [TC:053702] 
 * @Task#: Auto-1587
 * @TC: TC:053702
 * @author Jane
 * @Date  Apr 27, 2013
 */
// add ra fee schedule id=1770, id=1780, id=1790
public class VerifyLoopAreaVenueColDisplay extends FinanceManagerTestCase {

	private FinMgrRaFeeSchMainPage feeMainPg = FinMgrRaFeeSchMainPage.getInstance();
	private FinMgrRaFeeDetailPage detailsPg = FinMgrRaFeeDetailPage.getInstance();
	
	private final String LOOP_AREA_VENUE = "Loop/Area/Venue";
	private final String DOCK_AREA = "Dock/Area";
	private String searchValue;
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		
		searchValue = "Site";
		searchByPrdCategory(searchValue);
		feeMainPg.verifyColNameDisplay(LOOP_AREA_VENUE);
		String scheID = feeMainPg.getFirstFeeScheduleID();
		fnm.gotoRaFeeDetailPgFromRaFeeMainPg(scheID);
		String value = detailsPg.getAssignLoop();
		fnm.gotoFeeListPgFromScheDetailsPg(detailsPg);
		feeMainPg.verifyFeeScheColValue(scheID, LOOP_AREA_VENUE, value);
		
		searchValue = "Ticket";
		searchByPrdCategory(searchValue);
		feeMainPg.verifyColNameDisplay(LOOP_AREA_VENUE);
		scheID = feeMainPg.getFirstFeeScheduleID();
		fnm.gotoRaFeeDetailPgFromRaFeeMainPg(scheID);
		value = detailsPg.getAssignLoop();
		fnm.gotoFeeListPgFromScheDetailsPg(detailsPg);
		feeMainPg.verifyFeeScheColValue(scheID, LOOP_AREA_VENUE, value);
		
		
		searchValue = "Slip";
		searchByPrdCategory(searchValue);
		feeMainPg.verifyColNameDisplay(DOCK_AREA);
		scheID = feeMainPg.getFirstFeeScheduleID();
		fnm.gotoRaFeeDetailPgFromRaFeeMainPg(scheID);
		value = detailsPg.getAssignLoop();
		fnm.gotoFeeListPgFromScheDetailsPg(detailsPg);
		feeMainPg.verifyFeeScheColValue(scheID, DOCK_AREA, value);
		
		searchValue = "Permit";
		searchByPrdCategory(searchValue);
		feeMainPg.verifyColNameDisplay(LOOP_AREA_VENUE);
		scheID = feeMainPg.getFirstFeeScheduleID();
		feeMainPg.verifyFeeScheColValue(scheID, LOOP_AREA_VENUE, "");
		
		searchValue = "List";
		searchByPrdCategory(searchValue);
		feeMainPg.verifyColNameDisplay(LOOP_AREA_VENUE);
		scheID = feeMainPg.getFirstFeeScheduleID();
		feeMainPg.verifyFeeScheColValue(scheID, LOOP_AREA_VENUE, "");
		
		searchValue = "Lottery";
		searchByPrdCategory(searchValue);
		feeMainPg.verifyColNameDisplay(LOOP_AREA_VENUE);
		scheID = feeMainPg.getFirstFeeScheduleID();
		feeMainPg.verifyFeeScheColValue(scheID, LOOP_AREA_VENUE, "");
		
		searchValue = "POS";
		searchByPrdCategory(searchValue);
		feeMainPg.verifyColNameDisplay(LOOP_AREA_VENUE);
		scheID = feeMainPg.getFirstFeeScheduleID();
		feeMainPg.verifyFeeScheColValue(scheID, LOOP_AREA_VENUE, "");
		
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
	}
	
	private void searchByPrdCategory(String prdCategory) {
		logger.info("Saerch by product category "+prdCategory);
		
		feeMainPg.selectProductCategory(prdCategory);
		ajax.waitLoading();
		feeMainPg.waitLoading();
	}
}
