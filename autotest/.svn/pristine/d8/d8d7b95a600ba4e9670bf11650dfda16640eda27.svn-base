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
 * 1.Customer Acceptance Period From date and time is blank.
 * 2.Customer Acceptance Period To date and time is blank.
 * 3.Customer Acceptance Period From date equals To date.
 * 4.Customer Acceptance Period From date later than To date.
 * 5.Customer Acceptance Period From date and time is before the Execution date and time plus Freeze Duration.
 * 6.Customer Acceptance Period From date and time is equal to the Execution date and time plus Freeze Duration.
 * @Preconditions: Need a lottery exist.(d_inv_new_lottery_program, ID:480, name: VerifyMsgAcceptancePeriod)
 * 'Requires Customer Acceptance' must be set to Yes in lottery detail page.
 * @SPEC: Add Lottery Schedule - Acceptance Period validation [TC:044641]
 * @Task#: Auto-1343
 * 
 * @author nding1
 * @Date  Jan 8, 2013
 */
public class VerifyErrMsg_AcceptancePeriodValidation extends InventoryManagerTestCase {
	private String exceptMsg_MissFromDate, exceptMsg_MissToDate, exceptMsg_FromEqualLaterTo, exceptMsg_FromEqualBeforeExe;
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
		
		// 1.Customer Acceptance Period From date and time is blank.
		lotterySchedule.customerAcceptFromDate = StringUtil.EMPTY;
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		String message = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);
		if(!MiscFunctions.compareResult("Error message - Customer Acceptance Period From date and time is blank", exceptMsg_MissFromDate, message)){
			throw new ErrorOnPageException("--Check logs above.");
		}
		
		// 2.Customer Acceptance Period To date and time is blank.
		lotterySchedule.customerAcceptFromDate = DateFunctions.getDateAfterToday(14, tz);
		lotterySchedule.customerAcceptToDate = StringUtil.EMPTY;
		this.getMessageAndVerify(exceptMsg_MissToDate);
		
		// 3.Customer Acceptance Period From date equals To date.
		lotterySchedule.customerAcceptToDate = lotterySchedule.customerAcceptFromDate;
		this.getMessageAndVerify(exceptMsg_FromEqualLaterTo);
		
		// 4.Customer Acceptance Period From date later than To date.
		lotterySchedule.customerAcceptToDate = DateFunctions.getDateAfterToday(13, tz);// using boundary value.
		this.getMessageAndVerify(exceptMsg_FromEqualLaterTo);
		
		// 5.Customer Acceptance Period From date and time is before the Execution date and time plus Freeze Duration.
		lotterySchedule.customerAcceptFromDate = DateFunctions.getDateAfterToday(12, tz);// Don't change!
		lotterySchedule.customerAcceptToDate = DateFunctions.getDateAfterToday(18, tz);
		this.getMessageAndVerify(exceptMsg_FromEqualBeforeExe);
		
		// 6.Customer Acceptance Period From date and time is equal to the Execution date and time plus Freeze Duration.
		lotterySchedule.customerAcceptFromDate = DateFunctions.getDateAfterToday(13, tz);// Don't change!
		this.getMessageAndVerify(exceptMsg_FromEqualBeforeExe);

		im.logoutInvManager();
	}
	
	private void getMessageAndVerify(String exceptMsg){
		logger.info("Set customer acceptance from and to date, click Apply button to get error message, then verify messgae.");
		lotteryScheduleDetailsPg.setCustomerAcceptanceFromDate(lotterySchedule.customerAcceptFromDate, lottery.productCategory);
		lotteryScheduleDetailsPg.setCustomerAcceptanceToDate(lotterySchedule.customerAcceptToDate, lottery.productCategory);
		lotteryScheduleDetailsPg.clickApply();
		browser.waitExists();
		lotteryScheduleDetailsPg.waitLoading();
		String message = lotteryScheduleDetailsPg.getErrorMessage();
		if(!MiscFunctions.compareResult("Error message", exceptMsg, message)){
			throw new ErrorOnPageException("--Check logs above.");
		}
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
		lottery.name = "VerifyMsgAcceptancePeriod";// d_inv_new_lottery_program, ID:480
		lottery.productCategory = "Slip";
		lottery.locationCategory = "Park";
		lottery.description = "QA Auto Test for Add lottery schedule.";		
		lottery.location = im.getFacilityName("552903", schema);//"Jordan Lake State Rec Area";
		lottery.productCategory = "Slip";
		this.setPreferenceAttributes();
		lottery.facility=  lottery.location;//"Jordan Lake State Rec Area";

		// lottery schedule info
		lotterySchedule.appStartDate = DateFunctions.getDateAfterToday(1, tz);
		lotterySchedule.appEndDate = DateFunctions.getDateAfterToday(7, tz);
		lotterySchedule.web = true;
		lotterySchedule.executeDate = DateFunctions.getDateAfterToday(9, tz);// Don't change!
		lotterySchedule.freezeDuration = "4";// Don't change!
		lotterySchedule.invStartDate = DateFunctions.getDateAfterToday(20, tz);
		lotterySchedule.invEndDate = DateFunctions.getDateAfterToday(25, tz);
		
		lotterySchedule.applicableSeason = "FutureSeason (01/01/2022 - 01/31/2022)";

		// expect error message
		exceptMsg_MissFromDate = "The Acceptance period start date/time for the Lottery Schedule is required. Please enter the Date and Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the field provided.";
		exceptMsg_MissToDate = "The Acceptance period end date/time for the Lottery Schedule is required. Please enter the Date and Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the field provided.";	
		exceptMsg_FromEqualLaterTo = "The Acceptance period end date/time must be after the Acceptance period start date/time.";
		exceptMsg_FromEqualBeforeExe = "The Acceptance period start date/time must be after the lottery execution date plus the freeze period. Please change your entries.";
	}
}
