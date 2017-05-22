package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery.addlotteryschedule;

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
 * @Description: When add a new lottery schedule,
 * 1.Application Start Date is blank.
 * 2.Application End Date is blank.
 * 3.Application Start Date is past date.
 * 4.Application Start Date later than Application End Date.
 * 5.Don't select any sales channels.
 * 6.From Date for the Sales Channel is blank.
 * 7.To Date for the Sales Channel is blank.
 * 8.From Date later than To Date.
 * 9.Sales Channel's From and To Date are not within the Application Start and End Date.
 * @Preconditions: Need a lottery exist.(d_inv_new_lottery_program, ID:440, name: VerifyMsgAppPeriod)
 * @SPEC: Add Lottery Schedule - Application Period validation [TC:042362]
 * @Task#: Auto-1343
 * 
 * @author nding1
 * @Date  Jan 8, 2013
 */
public class VerifyErrMsg_ApplicationPeriodValidation extends InventoryManagerTestCase {
	private String exceptMsg_MissAppStartDate, exceptMsg_PastAppStartDate, exceptMsg_MissAppToDate, exceptMsg_AppStartLaterEnd;
	private String exceptMsg_NoSalesChannel, exceptMsg_MissFromDate, exceptMsg_MissToDate, exceptMsg_FromDateLaterTo, exceptMsg_SaleChannelDateNotWithinAppDate;
	private TimeZone tz;
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoLotterySearchPage();
		
		lottery.id=im.getLotteryId(schema,lottery.name);
		if (StringUtil.isEmpty(lottery.id)) {
			lottery.id = im.addNewLottery(lottery);
		}
		
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		
		// 1.Application Start Date is blank.
		lotterySchedule.appStartDate = StringUtil.EMPTY;
		String message = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);
		if(!MiscFunctions.compareResult("Error message:Application Start Date is blank", exceptMsg_MissAppStartDate, message)){
			throw new ErrorOnPageException("---Check logs above.");
		}

		// 2.Application End Date is blank.
		lotterySchedule.appStartDate = DateFunctions.getDateAfterToday(2, tz);
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);
		if(!MiscFunctions.compareResult("Error message:Application End Date is blank.", exceptMsg_MissAppToDate, message)){
			throw new ErrorOnPageException("---Check logs above.");
		}
		
		// 3.Application Start Date is past date.
		lotterySchedule.appStartDate = DateFunctions.getToday(tz);// Dont' change!
		lotterySchedule.appEndDate = DateFunctions.getDateAfterToday(1, tz);
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);
		if(!MiscFunctions.compareResult("Error message:Application Start Date is past date.", exceptMsg_PastAppStartDate, message)){
			throw new ErrorOnPageException("---Check logs above.");
		}
		
		// 4.Application Start Date later than Application End Date.
		lotterySchedule.appStartDate = DateFunctions.getDateAfterToday(2, tz);
		lotterySchedule.appEndDate = DateFunctions.getDateAfterToday(1, tz);
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);
		if(!MiscFunctions.compareResult("Error message:Application Start Date later than Application End Date.", exceptMsg_AppStartLaterEnd, message)){
			throw new ErrorOnPageException("---Check logs above.");
		}
		
		// 5.Don't select any sales channels.
		lotterySchedule.appEndDate = DateFunctions.getDateAfterToday(5, tz);
		lotterySchedule.callCenter = false;
		lotterySchedule.field = false;
		lotterySchedule.web = false;
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);
		if(!MiscFunctions.compareResult("Error message:Don't select any sales channels.", exceptMsg_NoSalesChannel, message)){
			throw new ErrorOnPageException("---Check logs above.");
		}
		
		// 6.From Date for the Sales Channel is blank.
		lotterySchedule.callCenter = true;
		lotterySchedule.isUpdateCallCenter = true;
		lotterySchedule.callStartDate = StringUtil.EMPTY;
		lotterySchedule.field = true;
		lotterySchedule.isUpdateFeild = true;
		lotterySchedule.fieldStartDate = StringUtil.EMPTY;
		lotterySchedule.web = true;
		lotterySchedule.isUpdateWeb = true;
		lotterySchedule.webStartDate = StringUtil.EMPTY;
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);
		if(!MiscFunctions.compareResult("Error message:From Date for the Sales Channel is blank.", exceptMsg_MissFromDate, message)){
			throw new ErrorOnPageException("---Check logs above.");
		}
		
		// 7.To Date for the Sales Channel is blank.
		lotterySchedule.callStartDate = lotterySchedule.appStartDate;
		lotterySchedule.fieldStartDate = lotterySchedule.appStartDate;
		lotterySchedule.webStartDate = lotterySchedule.appStartDate;
		lotterySchedule.callEndDate = StringUtil.EMPTY;
		lotterySchedule.fieldEndDate = StringUtil.EMPTY;
		lotterySchedule.webEndDate = StringUtil.EMPTY;
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);
		if(!MiscFunctions.compareResult("Error message - To Date for the Sales Channel is blank", exceptMsg_MissToDate, message)){
			throw new ErrorOnPageException("---Check logs above.");
		}
		
		// 8.From Date later than To Date.
		lotterySchedule.callEndDate = DateFunctions.getDateAfterToday(1, tz);
		lotterySchedule.fieldEndDate = DateFunctions.getDateAfterToday(1, tz);
		lotterySchedule.webEndDate = DateFunctions.getDateAfterToday(1, tz);
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);
		if(!MiscFunctions.compareResult("Error message - From Date later than To Date.", exceptMsg_FromDateLaterTo, message)){
			throw new ErrorOnPageException("---Check logs above.");
		}

		// 9.Sales Channel's From and To Date are not within the Application Start and End Date.
		// app date period: 2~5 days after today
		// sales channel period:2~6 or 3~5 or 3~4 days after today
		lotterySchedule.callStartDate = DateFunctions.getDateAfterToday(2, tz);
		lotterySchedule.fieldStartDate = DateFunctions.getDateAfterToday(3, tz);
		lotterySchedule.webStartDate = DateFunctions.getDateAfterToday(3, tz);
		lotterySchedule.callEndDate = DateFunctions.getDateAfterToday(6, tz);
		lotterySchedule.fieldEndDate = DateFunctions.getDateAfterToday(5, tz);
		lotterySchedule.webEndDate = DateFunctions.getDateAfterToday(4, tz);
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		message = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);
		if(!MiscFunctions.compareResult("Error message - Not Within", exceptMsg_SaleChannelDateNotWithinAppDate, message)){
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
		lottery.name = "VerifyMsgAppPeriod";// d_inv_new_lottery_program, ID:440
		lottery.productCategory = "Slip";
		lottery.locationCategory = "Park";
		lottery.description = "QA Auto Test for Add lottery schedule.";		
		lottery.location = im.getFacilityName("552903", schema);//"Jordan Lake State Rec Area";
		lottery.productCategory = "Slip";
		this.setPreferenceAttributes();
		lottery.facility=  lottery.location;//"Jordan Lake State Rec Area";

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
