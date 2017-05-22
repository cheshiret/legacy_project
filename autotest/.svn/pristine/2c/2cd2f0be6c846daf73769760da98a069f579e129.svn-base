package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery.editlotteryschedule;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryScheduleSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Add a new inactive lottery schedule.
 * 1. Activate it, verify status.
 * 2. Deactivate it, verify status.
 * @Preconditions: Need a lottery exist.(d_inv_new_lottery_program, ID:430, name: VerifyMsgNoWaitList)
 * @SPEC: Edit Lottery Schedule - Only Status [TC:043010]
 * @Task#: Auto-1343
 * 
 * @author nding1
 * @Date  Jan 10, 2013
 */
public class ActiveOrInactiveLotterySchedule extends InventoryManagerTestCase {
	private LotteryScheduleSearchPage lotteryScheSearchPg = LotteryScheduleSearchPage .getInstance();
	private boolean result = true;
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoLotterySearchPage();
		
		lottery.id=im.getLotteryId(schema,lottery.name);
		if (StringUtil.isEmpty(lottery.id)) {
			lottery.id = im.addNewLottery(lottery);
		}

		// search inactive lottery schedule
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		lotterySchedule.id = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);
		
		// 1.active the lottery schedule
		lotteryScheSearchPg.activeScheduleByID(lotterySchedule.id);
		
		// search the schedule and verify status.
		this.searchScheduleAndVerifyStatus(OrmsConstants.YES_STATUS);
		
		// 2. deactivate the lottery schedule
		lotteryScheSearchPg.deactivateScheduleByID(lotterySchedule.id);
		
		// search the schedule and verify status.
		this.searchScheduleAndVerifyStatus(OrmsConstants.NO_STATUS);

		if(!result){
			throw new ErrorOnPageException("---Check logs above.");
		}
		im.logoutInvManager();
	}
	
	private void searchScheduleAndVerifyStatus(String expectStatus){
		logger.info("Search lottery schedule by ID:"+lotterySchedule.id);
		lotteryScheSearchPg.searchLotterySchedule(lotterySchedule.id, null);// search schedule by ID.
		String actualStatus = lotteryScheSearchPg.getLotteryScheduleInfo().get(0).status;
		result &= MiscFunctions.compareResult("Status", expectStatus, actualStatus);
	}
	
	private void setPreferenceAttributes() {
		lottery.attributes.clear();
		String[] attributes = {"Facility","Min Slip Depth","Min Slip Length","Min Slip Width","Boat Category","Dock"};
		for (int i = 0; i < attributes.length; i++) {
			LotteryPreferenceAttribute attr = new LotteryPreferenceAttribute();
			attr.label = attributes[i];
			attr.displayOrder = String.valueOf(i + 1);
			lottery.attributes.add(attr);
		}
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		// lottery info
		lottery.name = "VerifyMsgNoWaitList";// d_inv_new_lottery_program, ID:430
		lottery.productCategory = "Slip";
		lottery.locationCategory = "Park";
		lottery.description = "QA Auto Test for Edit lottery schedule.";		
		lottery.location = im.getFacilityName("552903", schema);//"Jordan Lake State Rec Area";
		lottery.productCategory = "Slip";
		this.setPreferenceAttributes();
		lottery.facility=  lottery.location;//"Jordan Lake State Rec Area";

		// lottery schedule info
		lotterySchedule.appStartDate = DateFunctions.getDateAfterToday(30);
		lotterySchedule.appEndDate = DateFunctions.getDateAfterToday(32);
		lotterySchedule.callCenter = true;
		
		lotterySchedule.executeDate = DateFunctions.getDateAfterToday(35);
		lotterySchedule.freezeDuration = "2";
		lotterySchedule.applicableSeason = "FutureSeason (01/01/2022 - 01/31/2022)";
	}
}
