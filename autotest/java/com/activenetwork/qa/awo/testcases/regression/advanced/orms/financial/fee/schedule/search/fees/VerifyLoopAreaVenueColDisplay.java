package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.fees;

import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
/**
 * @Preconditions:
 * @SPEC: Fees - Display 'Loop/Area/Venue' or 'Dock/Area' Columns [TC:053701] 
 * @Task#: Auto-1587
 * @TC: TC:053701
 * @author Jane
 * @Date  Apr 27, 2013
 */
// add ticket fee schedule id=1050
public class VerifyLoopAreaVenueColDisplay extends FinanceManagerTestCase {
	private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	
	private final String LOOP_AREA_VENUE = "Loop/Area/Venue";
	private final String DOCK_AREA = "Dock/Area";
	private String searchValue;
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		
		searchValue = "Site";
		searchByPrdCategory(searchValue);
		feeMainPg.verifyColNameDisplay(LOOP_AREA_VENUE);
		String scheID = feeMainPg.getFirstFeeScheduleID();
		fnm.gotoFeeSchedulePageByScheduleId(scheID);
		String value = detailsPg.getAssignLoop();
		fnm.gotoFeeListPgFromScheDetailsPg(detailsPg);
		feeMainPg.verifyFeeScheColValue(scheID, LOOP_AREA_VENUE, value);
		
		searchValue = "Ticket";
		searchByPrdCategory(searchValue);
		feeMainPg.verifyColNameDisplay(LOOP_AREA_VENUE);
		scheID = feeMainPg.getFirstFeeScheduleID();
		fnm.gotoFeeSchedulePageByScheduleId(scheID);
		value = detailsPg.getAssignLoop();
		fnm.gotoFeeListPgFromScheDetailsPg(detailsPg);
		feeMainPg.verifyFeeScheColValue(scheID, LOOP_AREA_VENUE, value);
		
		
		searchValue = "Slip";
		searchByPrdCategory(searchValue);
		feeMainPg.verifyColNameDisplay(DOCK_AREA);
		scheID = feeMainPg.getFirstFeeScheduleID();
		fnm.gotoFeeSchedulePageByScheduleId(scheID);
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
		
		searchValue = "GiftCard";
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
