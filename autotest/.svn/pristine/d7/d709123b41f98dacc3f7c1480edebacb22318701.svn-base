package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery.addlotteryschedule;

import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: When add a new lottery schedule,
 * check the "Add Unsuccessful Applicants to a Waiting List", but do not select any waiting list.
 * @Preconditions: Need a lottery exist.(d_inv_new_lottery_program, ID:430, name: VerifyMsgNoWaitList)
 * @SPEC: Add Lottery Schedule - Waiting list validation [TC:044640]
 * @Task#: Auto-1343
 * 
 * @author nding1
 * @Date  Jan 7, 2013
 */
public class VerifyErrMsg_NotSpecifyWaitingList extends InventoryManagerTestCase {
	private String exceptMsg = "Please select a waiting list.";
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoLotterySearchPage();
		
		lottery.id=im.getLotteryId(schema,lottery.name);
		if (StringUtil.isEmpty(lottery.id)) {
			lottery.id = im.addNewLottery(lottery);
		}
		
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		
		// check the "Add Unsuccessful Applicants to a Waiting List", but do not select any waiting list.
		String message = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);
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
		lottery.description = "QA Auto Test for Add lottery schedule.";		
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
		lotterySchedule.invStartDate = DateFunctions.getDateAfterToday(17);
		lotterySchedule.invEndDate = DateFunctions.getDateAfterToday(19);
		
		lotterySchedule.applicableSeason = "FutureSeason (01/01/2022 - 01/31/2022)";
		
		lotterySchedule.isAddToWaitList = true;// verify point, don't change!
		lotterySchedule.waitList = "Please Select";// default value, don't change!
	}
}
