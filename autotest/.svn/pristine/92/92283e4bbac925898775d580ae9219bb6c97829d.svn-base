package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery.editlotteryschedule;

import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Add a new active lottery schedule, select the "Add Unsuccessful Applicants to a Waiting List", and select one waiting list.
 * Then edit it, select the "Add Unsuccessful Applicants to a Waiting List", but do not select any waiting list.
 * And then verify the error message.
 * @Preconditions: Need a lottery exist.(d_inv_new_lottery_program, ID:430, name: VerifyMsgNoWaitList)
 * Lottery Schedule is "Active" and Lottery Schedule currently not on-going.(handle in case)
 * @SPEC: Edit Lottery Schedule - Waiting list validation [TC:043006]
 * @Task#: Auto-1343
 * 
 * @author nding1
 * @Date  Jan 10, 2013
 */
public class VerifyErrMsg_NotSpecifyWaitingList extends InventoryManagerTestCase {
	private String exceptMsg = "Please select a waiting list.";
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoLotterySearchPage();

		// ---data prepare start
		lottery.id=im.getLotteryId(schema,lottery.name);
		if (StringUtil.isEmpty(lottery.id)) {
			// add new lottery
			lottery.id = im.addNewLottery(lottery);
		}
		im.deactivateLotterySchedulesFromDB(schema, lottery.id);// clean up
		// ---data prepare end
		
		// add a new active lottery schedule, check the "Add Unsuccessful Applicants to a Waiting List", and select one waiting list.lotteryScheduleIdStr
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		lotterySchedule.id = im.addLotterySchedule(lotterySchedule, true, lottery.productCategory);

		// select the "Add Unsuccessful Applicants to a Waiting List", but do not select any waiting list.
		lotterySchedule.isAddToWaitList = true;
		im.gotoLotterySchdDetailPgFromLotterySchdSearchPg(lotterySchedule.id);
		String message = im.editLotterySchedule(lotterySchedule, lottery.productCategory);

		im.deactivateLotterySchedulesFromDB(schema, lottery.id);// clean up
		if(!MiscFunctions.compareResult("Error message", exceptMsg, message)){
			throw new ErrorOnPageException("---Check logs above.");
		}
		im.logoutInvManager();
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
		lotterySchedule.appStartDate = DateFunctions.getDateAfterToday(2);
		lotterySchedule.appEndDate = DateFunctions.getDateAfterToday(3);
		lotterySchedule.callCenter = true;
		
		lotterySchedule.executeDate = DateFunctions.getDateAfterToday(5);
		lotterySchedule.freezeDuration = "2";
		lotterySchedule.applicableSeason = "FutureSeason (01/01/2022 - 01/31/2022)";
		
		lotterySchedule.isAddToWaitList = false;
	}
}
