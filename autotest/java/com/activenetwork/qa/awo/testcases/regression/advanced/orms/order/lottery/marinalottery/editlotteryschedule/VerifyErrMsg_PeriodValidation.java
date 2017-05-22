package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery.editlotteryschedule;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: When edit lottery schedule,
 * 1.Application Period overlaps with Inventory Period;
 * 2.Application Period later than Inventory Period;
 * 3.Execution Period overlaps with Application Period;
 * 4.Application Period later than Execution Period;
 * 5.Execution Period overlaps with Inventory Period;
 * 6.Execution Period later than Inventory Period;
 * @Preconditions: Need a lottery exist.(d_inv_new_lottery_program, ID:490, name: VerifyMsgPeriodValidation)
 * Lottery Schedule is "Active" and Lottery Schedule currently not on-going(handle in case)
 * @SPEC: Edit Lottery Schedule - Period validation [TC:043005]
 * @Task#: Auto-1343
 * 
 * @author nding1
 * @Date  Jan 10, 2013
 */
public class VerifyErrMsg_PeriodValidation extends InventoryManagerTestCase {
	private String exceptMsg_AppInvOverlap, exceptMsg_AppLaterInv, exceptMsg_AppExeOverlap, exceptMsg_InvExeOverlap;
	private TimeZone tz;
	private boolean result = true;

	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoLotterySearchPage();

		// ---data prepare start
		lottery.id=im.getLotteryId(schema,lottery.name);
		if (StringUtil.isEmpty(lottery.id)) {
			// add new lottery
			lottery.id = im.addNewLottery(lottery);
			
			// assign the location to lottery
			im.assignLocationToLottery(lottery);
		} else {
			// if the location not assigned, assign it.
			im.gotoLotteryDetailPageFromSearchPage(lottery);
			if (!im.checkLocationAssigned(lottery.area)) {
				im.assignLocationToLottery(lottery);
			}
		}
		// ---data prepare end
		
		// add a new active lottery schedule
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		lotterySchedule.id = im.addLotterySchedule(lotterySchedule, true, lottery.productCategory);
		
		// 1.Application Period overlaps with Inventory Period;
		lotterySchedule.appStartDate = DateFunctions.getDateAfterToday(21, tz);// Don't change!
		lotterySchedule.appEndDate = DateFunctions.getDateAfterToday(25, tz);// Don't change!
		lotterySchedule.invStartDate = DateFunctions.getDateAfterToday(22, tz);// Don't change!
		lotterySchedule.invEndDate = DateFunctions.getDateAfterToday(26, tz);// Don't change!
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		String message = im.editLotterySchedule(lotterySchedule, lottery.productCategory);
		if(!MiscFunctions.compareResult("Error message - Application overlaps with Inventory", exceptMsg_AppInvOverlap, message)){
			throw new ErrorOnPageException("---Check logs above.");
		}
		
		// 2.Application Period later than Inventory Period;
		lotterySchedule.appStartDate = DateFunctions.getDateAfterToday(27, tz);// Don't change!
		lotterySchedule.appEndDate = DateFunctions.getDateAfterToday(30, tz);// Don't change!
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);
		if(!MiscFunctions.compareResult("Error message - Application later than Inventory", exceptMsg_AppLaterInv, message)){
			throw new ErrorOnPageException("---Check logs above.");
		}

		// 3.Execution Period overlaps with Application Period;
		lotterySchedule.appStartDate = DateFunctions.getDateAfterToday(21, tz);
		lotterySchedule.appStartDateHour = "10";
		lotterySchedule.appStartDateMinute = "10";
		lotterySchedule.appStartDateAM = true;
		lotterySchedule.appEndDate = DateFunctions.getDateAfterToday(25, tz);
		lotterySchedule.appEndDateHour = "7";
		lotterySchedule.appEndDateMinute = "55";
		lotterySchedule.appEndDateAM = false;
		lotterySchedule.executeDate = lotterySchedule.appStartDate;// Don't change!
		lotterySchedule.executeHour = lotterySchedule.appStartDateHour;// Don't change!
		lotterySchedule.executeMin = lotterySchedule.appStartDateMinute;// Don't change!
		lotterySchedule.executeAM = lotterySchedule.appStartDateAM;// Don't change!
		lotterySchedule.invStartDate = DateFunctions.getDateAfterToday(50, tz);
		lotterySchedule.invEndDate = DateFunctions.getDateAfterToday(51, tz);
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);
		if(!MiscFunctions.compareResult("Error message - Execution overlaps with Application", exceptMsg_AppExeOverlap, message)){
			throw new ErrorOnPageException("---Check logs above.");
		}
		
		// 4.Application Period later than Execution Period;
		lotterySchedule.appStartDate = DateFunctions.getDateAfterToday(26, tz);// Don't change!
		lotterySchedule.appEndDate = DateFunctions.getDateAfterToday(28, tz);// Don't change!
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);
		if(!MiscFunctions.compareResult("Error message - Application later than Execution", exceptMsg_AppExeOverlap, message)){
			throw new ErrorOnPageException("---Check logs above.");
		}

		// 5.Execution Period overlaps with Inventory Period;
		lotterySchedule.appStartDate = DateFunctions.getDateAfterToday(21, tz);// Don't change!
		lotterySchedule.appEndDate = DateFunctions.getDateAfterToday(25, tz);// Don't change!
		lotterySchedule.executeDate = lotterySchedule.invStartDate;// Don't change!
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);
		if(!MiscFunctions.compareResult("Error message - Execution overlaps with Inventory", exceptMsg_InvExeOverlap, message)){
			throw new ErrorOnPageException("---Check logs above.");
		}

		// 6.Execution Period later than Inventory Period;
		lotterySchedule.executeDate = DateFunctions.getDateAfterToday(52, tz);// Don't change!
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);
		if(!MiscFunctions.compareResult("Error message - Execution later than Inventory", exceptMsg_InvExeOverlap, message)){
			throw new ErrorOnPageException("---Check logs above.");
		}

		if(!result){
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
		tz = DataBaseFunctions.getContractTimeZone(schema);
		
		// lottery info
		lottery.name = "VerifyMsgPeriodValidation";// d_inv_new_lottery_program, ID:490
		lottery.productCategory = "Slip";
		lottery.locationCategory = "Park";
		lottery.description = "QA Auto Test for Edit lottery schedule.";		
		lottery.location = im.getFacilityName("552903", schema);//"Jordan Lake State Rec Area";
		lottery.productCategory = "Slip";
		this.setPreferenceAttributes();
		lottery.facility=  lottery.location;//"Jordan Lake State Rec Area";

		// lottery schedule info
		lotterySchedule.callCenter = true;
		lotterySchedule.executeDate = DateFunctions.getDateAfterToday(9, tz);
		lotterySchedule.freezeDuration = "1";


		// expect error message
		exceptMsg_AppInvOverlap = "The Application Period (Application Start and End Dates/Time) must not overlap with the Inventory Period (Inventory Start and End Dates). Please change your entries.";
		exceptMsg_AppLaterInv = "The Application Period (Application Start and End Dates/Time) must be before the Inventory Period (Inventory Start and End Dates). Please change your entries.";	
		exceptMsg_AppExeOverlap = "The Execution Period (Execution Date and Execution Freeze Duration) must be after the Application Period (Application Start and End Dates). Please change your entries.";
		exceptMsg_InvExeOverlap = "The Execution Period (Execution Date and Execution Freeze Duration) must be before the Inventory Period (Inventory Start and End Dates). Please change your entries.";
	}
}
