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
 * 1.Application Start Date is blank.
 * 2.Application Start Date is past date.
 * 3.Application Start Date is today.
 * 4.Application End Date is blank.
 * 5.Application Start Date later than Application End Date.
 * 6.Not select any sales channels.
 * 7.From Date for the Sales Channel is blank.
 * 8.To Date for the Sales Channel is blank.
 * 9.From Date later than To Date.
 * 10.Sales Channel's From and To Date are not within the Application Start and End Date.
 * @Preconditions:
 * 1.Need a lottery exist.(d_inv_new_lottery_program, ID:440, name: VerifyMsgAppPeriod)
 * 2.Lottery Schedule is "Active" and Lottery Schedule currently not on-going.(handle in case)
 * 3.There is a active season has been assigned a slip and the 'Slip Res. Per. Type' must be 'Seasonal'.
 *   Dock name: DockWithSlipSeason
 *   Slip name: SlipHasSeason
 *   Season display name: DockWithSlipSeason
 * @SPEC: Edit Lottery Schedule - Application Period validation [TC:043002]
 * @Task#: Auto-1343
 * 
 * @author nding1
 * @Date  Jan 10, 2013
 */
public class VerifyErrMsg_ApplicationPeriodValidation extends InventoryManagerTestCase {
	private String exceptMsg_MissAppStartDate, exceptMsg_PastAppStartDate, exceptMsg_MissAppToDate, exceptMsg_AppStartLaterEnd;
	private String exceptMsg_NoSalesChannel, exceptMsg_MissFromDate, exceptMsg_MissToDate, exceptMsg_FromDateLaterTo, exceptMsg_SaleChannelDateNotWithinAppDate;
	private TimeZone tz;
	
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
		// ---data prepare end
		
		// add a new active lottery schedule
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		im.deactivateLotterySchedulesFromDB(schema, lottery.id);// clean up
		lotterySchedule.id = im.addLotterySchedule(lotterySchedule, true, lottery.productCategory);

		// 1.Application Start Date is blank.
		lotterySchedule.appStartDate = StringUtil.EMPTY;
		String message = im.editLotterySchedule(lotterySchedule, lottery.productCategory);
		this.verifyMessage("Error message:Application Start Date is blank", exceptMsg_MissAppStartDate, message);

		// 2.Application Start Date is past date.
		lotterySchedule.appStartDate = DateFunctions.getDateAfterToday(-1, tz);// Dont' change!
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.editLotterySchedule(lotterySchedule, lottery.productCategory);
		this.verifyMessage("Error message:Application Start Date is past date.", exceptMsg_PastAppStartDate, message);
		
		// 3.Application Start Date is today.
		lotterySchedule.appStartDate = DateFunctions.getToday(tz);// Dont' change!
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.editLotterySchedule(lotterySchedule, lottery.productCategory);
		this.verifyMessage("Error message - Application Start Date is today", exceptMsg_PastAppStartDate, message);
		
		// 4.Application End Date is blank.
		lotterySchedule.appStartDate = DateFunctions.getDateAfterToday(28, tz);
		lotterySchedule.appEndDate = StringUtil.EMPTY;// Don't change!
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.editLotterySchedule(lotterySchedule, lottery.productCategory);
		this.verifyMessage("Error message:exceptMsg_MissAppToDate.", exceptMsg_MissAppToDate, message);
		
		// 5.Application Start Date later than Application End Date.
		lotterySchedule.appEndDate = DateFunctions.getDateAfterToday(27, tz);
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.editLotterySchedule(lotterySchedule, lottery.productCategory);
		this.verifyMessage("Error message:Application Start Date later than Application End Date.", exceptMsg_AppStartLaterEnd, message);

		// 6.Not select any sales channels.
		lotterySchedule.appEndDate = DateFunctions.getDateAfterToday(31, tz);
		lotterySchedule.callCenter = false;
		lotterySchedule.field = false;
		lotterySchedule.web = false;
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.editLotterySchedule(lotterySchedule, lottery.productCategory);
		this.verifyMessage("Error message:Don't select any sales channels.", exceptMsg_NoSalesChannel, message);

		// 7.From Date for the Sales Channel is blank.
		lotterySchedule.callCenter = true;
		lotterySchedule.isUpdateCallCenter = true;
		lotterySchedule.callStartDate = StringUtil.EMPTY;
		lotterySchedule.web = true;
		lotterySchedule.isUpdateWeb = true;
		lotterySchedule.webStartDate = StringUtil.EMPTY;
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.editLotterySchedule(lotterySchedule, lottery.productCategory);
		this.verifyMessage("Error message:From Date for the Sales Channel is blank.", exceptMsg_MissFromDate, message);
	
		// 8.To Date for the Sales Channel is blank.
		lotterySchedule.callStartDate = lotterySchedule.appStartDate;
		lotterySchedule.webStartDate = lotterySchedule.appStartDate;
		lotterySchedule.callEndDate = StringUtil.EMPTY;
		lotterySchedule.webEndDate = StringUtil.EMPTY;
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.editLotterySchedule(lotterySchedule, lottery.productCategory);
		this.verifyMessage("Error message - To Date for the Sales Channel is blank", exceptMsg_MissToDate, message);

		// 9.From Date later than To Date.
		lotterySchedule.callEndDate = DateFunctions.getDateAfterToday(22, tz);
		lotterySchedule.fieldEndDate = DateFunctions.getDateAfterToday(22, tz);
		lotterySchedule.webEndDate = DateFunctions.getDateAfterToday(22, tz);
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.editLotterySchedule(lotterySchedule, lottery.productCategory);
		this.verifyMessage("Error message - From Date later than To Date.", exceptMsg_FromDateLaterTo, message);

		// 10.Sales Channel's From and To Date are not within the Application Start and End Date.
		// app date period: 28~31 days after today
		// sales channel period:27~31 or 28~30 days after today
		lotterySchedule.callStartDate = DateFunctions.getDateAfterToday(27, tz);
		lotterySchedule.webStartDate = DateFunctions.getDateAfterToday(28, tz);
		lotterySchedule.callEndDate = DateFunctions.getDateAfterToday(31, tz);
		lotterySchedule.webEndDate = DateFunctions.getDateAfterToday(30, tz);
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.editLotterySchedule(lotterySchedule, lottery.productCategory);
		this.verifyMessage("Error message - Not Within", exceptMsg_SaleChannelDateNotWithinAppDate, message);

		im.deactivateLotterySchedulesFromDB(schema, lottery.id);// clean up
		im.logoutInvManager();
	}
	
	private void verifyMessage(String errorInfo, String expectMsg, String actualMsg){
		if(!MiscFunctions.compareResult(errorInfo, expectMsg, actualMsg)){
			im.deactivateLotterySchedulesFromDB(schema, lottery.id);// clean up
			throw new ErrorOnPageException("---Check logs above.");
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
		lottery.name = "VerifyMsgAppPeriod";// d_inv_new_lottery_program, ID:440
		lottery.productCategory = "Slip";
		lottery.locationCategory = "Park";
		lottery.description = "QA Auto Test for Edit lottery schedule.";		
		lottery.location = im.getFacilityName("552903", schema);//"Jordan Lake State Rec Area";
		lottery.productCategory = "Slip";
		this.setPreferenceAttributes();
		lottery.facility=  lottery.location;//"Jordan Lake State Rec Area";
		lottery.area = "DockWithSlipSeason";
		

		// lottery schedule info
		lotterySchedule.appStartDate = DateFunctions.getDateAfterToday(10, tz);
		lotterySchedule.appEndDate = DateFunctions.getDateAfterToday(12, tz);
		lotterySchedule.web = true;
		lotterySchedule.executeDate = DateFunctions.getDateAfterToday(81, tz);// Don't change!
		lotterySchedule.freezeDuration = "3";// Don't change!
		lotterySchedule.applicableSeason = "FutureSeason (01/01/2022 - 01/31/2022)";

		// except error message
		exceptMsg_MissAppStartDate = "An Application Start Date and Time for the Lottery Schedule is required. Please enter the Application Start Date and Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the field provided";
		exceptMsg_MissAppToDate = "An Application End Date and Time for the Lottery Schedule is required. Please enter the Application End Date and Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the field provided";
		exceptMsg_PastAppStartDate = "The Application Start Date and Time must be a date in the future.";
		exceptMsg_AppStartLaterEnd = "The Application Start Date and Time must not be later than the Application End Date and Time. Please change your entries.";
		exceptMsg_NoSalesChannel = "Lottery Applications have to be accepted through at least one Sales Channel. Please check a Sales Channel from the list provided.";
		exceptMsg_MissFromDate = "The From Date and Time for a Sales Channel that will be accepting Lottery Applications is required. Please enter the Sales Channel From Date and Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the field provided.";
		exceptMsg_MissToDate = "The To Date and Time for a Sales Channel that will be accepting Lottery Applications is required. Please enter the Sales Channel To Date and Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the field provided.";
		exceptMsg_FromDateLaterTo = "The From Date and Time for each Sales Channel that will be accepting Lottery Applications must not be later than the corresponding End Date and Time. Please change your entries.";
		exceptMsg_SaleChannelDateNotWithinAppDate = "The From Date and Time and corresponding To Date and Time for each Sales Channel that will be accepting Lottery Applications must be within the specified Application Start Date and Time and Application End Date and Time. Please change your entries.";
	}
}
