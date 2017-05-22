package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery.addlotteryschedule;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryScheduleDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: When add a new lottery schedule,
 * 1.Execution Date and Time is blank.
 * 2.Lottery Execution Freeze Duration is blank.
 * 3.Lottery Execution Freeze Duration is negative value.
 * 4.Lottery Execution Freeze Duration is not a integer.
 * @Preconditions: Need a lottery exist.(d_inv_new_lottery_program, ID:460, name: VerifyMsgExecutePeriod)
 * @SPEC: Add Lottery Schedule - Execution Period validation [TC:042361]
 * @Task#: Auto-1343
 * 
 * @author nding1
 * @Date  Jan 9, 2013
 */
public class VerifyErrMsg_ExecutionPeriodValidation extends InventoryManagerTestCase {
	private String exceptMsg_MissExeDate, exceptMsg_MissFreezeDuration, exceptMsg_NegativeFreezeDuration, exceptMsg_NotInteger;
	private TimeZone tz;
	private LotteryScheduleDetailsPage lotteryScheduleDetailsPg = LotteryScheduleDetailsPage.getInstance();
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoLotterySearchPage();
		
		lottery.id=im.getLotteryId(schema,lottery.name);
		if (StringUtil.isEmpty(lottery.id)) {
			lottery.id = im.addNewLottery(lottery);
		}
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		
		// 1.Execution Date and Time is blank.
		boolean result = true;
		lotterySchedule.executeDate = StringUtil.EMPTY;
		lotterySchedule.freezeDuration = StringUtil.EMPTY;
		String message = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);
		result &= MiscFunctions.compareResult("Error message:Execution Date and Time is blank.", exceptMsg_MissExeDate, message);
		
		// 2.Lottery Execution Freeze Duration is blank.
		lotterySchedule.executeDate = DateFunctions.getDateAfterToday(29, tz);
		lotterySchedule.freezeDuration = StringUtil.EMPTY;
		message = this.clickApplyAndGetMessage();
		result &= MiscFunctions.compareResult("Error message:Freeze Duration is blank.", exceptMsg_MissFreezeDuration, message);
		
		// 3.Lottery Execution Freeze Duration is negative value.
		lotterySchedule.freezeDuration = "-1";
		message = this.clickApplyAndGetMessage();
		result &= MiscFunctions.compareResult("Error message:Freeze Duration is negative value.", exceptMsg_NegativeFreezeDuration, message);

		// 4.Lottery Execution Freeze Duration is not a integer.
		lotterySchedule.freezeDuration = "3.1415926";
		message = this.clickApplyAndGetMessage();
		result &= MiscFunctions.compareResult("Error message:Freeze Duration is not a integer.", exceptMsg_NotInteger, message);
		
		if(!result){
			throw new ErrorOnPageException("---Check logs above.");
		}
		im.logoutInvManager();
	}

	private String clickApplyAndGetMessage(){
		lotteryScheduleDetailsPg.setExcutePeriod(lotterySchedule.executeDate, lottery.productCategory);
		lotteryScheduleDetailsPg.setFreezeDuration(lotterySchedule.freezeDuration, lottery.productCategory);
		lotteryScheduleDetailsPg.clickApply();
		browser.waitExists();
		lotteryScheduleDetailsPg.waitLoading();
		return lotteryScheduleDetailsPg.getErrorMessage();
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
		tz = DataBaseFunctions.getContractTimeZone(schema);
		
		// lottery info
		lottery.name = "VerifyMsgExecutePeriod";// d_inv_new_lottery_program, ID:460
		lottery.productCategory = "Slip";
		lottery.locationCategory = "Park";
		lottery.description = "QA Auto Test for Add lottery schedule.";		
		lottery.location = im.getFacilityName("552903", schema);//"Jordan Lake State Rec Area";
		lottery.productCategory = "Slip";
		this.setPreferenceAttributes();
		lottery.facility=  lottery.location;//"Jordan Lake State Rec Area";

		// lottery schedule info
		lotterySchedule.appStartDate = DateFunctions.getDateAfterToday(21, tz);
		lotterySchedule.appEndDate = DateFunctions.getDateAfterToday(27, tz);
		lotterySchedule.web = true;
		lotterySchedule.invStartDate = DateFunctions.getDateAfterToday(30, tz);
		lotterySchedule.invEndDate = DateFunctions.getDateAfterToday(40, tz);
		lotterySchedule.applicableSeason = "FutureSeason (01/01/2022 - 01/31/2022)";
		
		// expect error message
		exceptMsg_MissExeDate = "An Execution Date and Time for the Lottery Schedule is required. Please enter the Execution Date and Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the field provided.";
		exceptMsg_MissFreezeDuration = "The Lottery Execution Freeze Duration is required. Please enter the Lottery Execution Freeze Duration (in days) in the field provided.";	
		exceptMsg_NegativeFreezeDuration = "The Lottery Execution Freeze Duration must be an integer value greater than or equal to 0. Please change your entries.";
		exceptMsg_NotInteger = "The Lottery Execution Freeze Duration must be an integer value greater than or equal to 0. Please change your entries.";
	}
}
