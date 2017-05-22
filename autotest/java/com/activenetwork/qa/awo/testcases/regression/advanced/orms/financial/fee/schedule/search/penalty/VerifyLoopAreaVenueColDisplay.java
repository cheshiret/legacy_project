package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.penalty;

import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeePenaltyDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeePenaltyMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
/**
 * @Preconditions:
 * @SPEC: Fee Penalties - Display 'Loop/Area/Venue' or 'Dock/Area' Columns [TC:053700] 
 * @Task#: Auto-1587
 * @TC: TC:053700
 * @author Jane
 * @Date  Apr 27, 2013
 */
public class VerifyLoopAreaVenueColDisplay extends FinanceManagerTestCase {
//DEFECT-43551
	private FinMgrFeePenaltyMainPage feeMainPg = FinMgrFeePenaltyMainPage.getInstance();
	private FinMgrFeePenaltyDetailPage detailsPg = FinMgrFeePenaltyDetailPage.getInstance();
	
	private final String LOOP_AREA_VENUE = "Loop/Area/Venue";
	private final String DOCK_AREA = "Dock/Area";
	private String searchValue;
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoFeePenaltyPage();
		
		searchValue = "Site";
		searchByPrdCategory(searchValue);
		feeMainPg.verifyColNameDisplay(LOOP_AREA_VENUE);
		String scheID = feeMainPg.getFirstFeeScheduleID();
		fnm.searchToFeePenaltyScheduleDetailPg(scheID);
		String value = detailsPg.getAssignLoop();
		fnm.gotoFeeListPgFromScheDetailsPg(detailsPg);
		feeMainPg.verifyFeeScheColValue(scheID, LOOP_AREA_VENUE, value);
			
		searchValue = "Slip";
		searchByPrdCategory(searchValue);
		feeMainPg.verifyColNameDisplay(DOCK_AREA);
		scheID = feeMainPg.getFirstFeeScheduleID();
		fnm.searchToFeePenaltyScheduleDetailPg(scheID);
		value = detailsPg.getAssignDock();
		fnm.gotoFeeListPgFromScheDetailsPg(detailsPg);
		feeMainPg.verifyFeeScheColValue(scheID, DOCK_AREA, value);
		
		searchValue = "Permit";
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
		
		feeMainPg.clearAllSearchCriteria();
		feeMainPg.selectProductCategory(prdCategory);
		ajax.waitLoading();
		feeMainPg.waitLoading();
	}
}
