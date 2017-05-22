package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.lottery.schedule;

import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.datacollection.legacy.LotterySchedule;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryScheduleDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryScheduleSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Edit a lottery schedule and verify whether it is edit successfully or not.
 * @Preconditions: Need a lottery exist.(d_inv_new_lottery_program, ID:420, name:EditLotteryScheduleMarina)
 * @SPEC: TC043006,043009,043010,043002,043003,043004,044752,044750,043005
 * @Task#: Auto-1343
 * @author nding1
 * @Date  Jan 8, 2013
 */
public class EditSlipLotterySchedule extends InventoryManagerTestCase {
	private LotterySchedule editLotterySchedule = new LotterySchedule();
	private TimeZone tz;
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoLotterySearchPage();
		
		// if expect lottery not exist, add a new one.
		lottery.id=im.getLotteryId(schema,lottery.name);
		if (StringUtil.isEmpty(lottery.id)) {
			lottery.id = im.addNewLottery(lottery);
		}
		
		// add a new lottery schedule and then edit it.
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		editLotterySchedule.id = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);
		String[] applicableSeason = (lotterySchedule.applicableSeason.split("\\(")[1]).split("-");
		editLotterySchedule.invStartDate = DateFunctions.formatDate(applicableSeason[0].trim(), "EEE MMM d yyyy");
		editLotterySchedule.invEndDate = DateFunctions.formatDate(applicableSeason[1].replaceAll("\\)", StringUtil.EMPTY).trim(), "EEE MMM d yyyy");
		im.gotoLotterySchdDetailPgFromLotterySchdSearchPg(editLotterySchedule.id);
		im.editLotterySchedule(editLotterySchedule, lottery.productCategory);

		// verify schedule info in list page
		LotteryScheduleSearchPage lotteryScheduleSearchPg = LotteryScheduleSearchPage .getInstance();
		lotteryScheduleSearchPg.verifyScheduleInfo(editLotterySchedule);
		
		// go to lottery schedule detail page and verify detail info.
		im.gotoLotterySchdDetailPgFromLotterySchdSearchPg(editLotterySchedule.id);
		LotteryScheduleDetailsPage lotteryScheduleDetailsPg = LotteryScheduleDetailsPage .getInstance();
		lotteryScheduleDetailsPg.verifyScheduleDetailInfo(editLotterySchedule);
		
		im.logoutInvManager();
	}
	
	private void setPreferenceAttributes() {
		lottery.attributes.clear();
		String[] attributes = {"Facility","Min Slip Depth","Min Slip Length","Min Slip Width","Boat Category","Dock"};
		for (int i = 0; i < attributes.length; i++) {
			LotteryPreferenceAttribute attr = new LotteryPreferenceAttribute();
			attr.label = attributes[i];
			attr.displayOrder = String.valueOf(i + 1);
			if(attributes[i].equals("Dock")) {
				attr.entryRequired = "Per Preference";
			}
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
		lottery.name = "EditLotteryScheduleMarinaTest";// d_inv_new_lottery_program, ID:420
		lottery.productCategory = "Slip";
		lottery.locationCategory = "Park";
		lottery.description = "QA Auto Test for Edit lottery schedule.";		
		lottery.location = im.getFacilityName("552903", schema);//"Jordan Lake State Rec Area";
		lottery.productCategory = "Slip";
		this.setPreferenceAttributes();
		lottery.facility=  lottery.location;//"Jordan Lake State Rec Area";

		// lottery schedule info
		lotterySchedule.appStartDate = DateFunctions.getDateAfterToday(2, tz);
		lotterySchedule.appStartDateHour = "6";
		lotterySchedule.appStartDateMinute = "10";
		lotterySchedule.appStartDateAM = true;
		lotterySchedule.appStartDateTime = DateFunctions.formatDate(lotterySchedule.appStartDate, "EEE MMM d yyy")+" "
				+lotterySchedule.appStartDateHour+":"+lotterySchedule.appStartDateMinute+" "
				+(lotterySchedule.appStartDateAM?"AM":"PM");// format: Tue Jan 8 2013 12:00 AM
		
		lotterySchedule.appEndDate = DateFunctions.getDateAfterToday(3, tz);
		lotterySchedule.appEndDateHour = "11";
		lotterySchedule.appEndDateMinute = "29";
		lotterySchedule.appEndDateAM = false;
		lotterySchedule.appEndDateTime = DateFunctions.formatDate(lotterySchedule.appEndDate, "EEE MMM d yyy")+" "
				+lotterySchedule.appEndDateHour+":"+lotterySchedule.appEndDateMinute+" "
				+(lotterySchedule.appEndDateAM?"AM":"PM");// format: Tue Jan 8 2013 12:00 AM
		
		lotterySchedule.callCenter = true;
		lotterySchedule.isUpdateCallCenter = false;
		lotterySchedule.callStartDate = lotterySchedule.appStartDate;
		lotterySchedule.callStartDateHour = lotterySchedule.appStartDateHour;
		lotterySchedule.callStartDateMin = lotterySchedule.appStartDateMinute;
		lotterySchedule.callStartDateAm = lotterySchedule.appStartDateAM;
		lotterySchedule.callEndDate = lotterySchedule.appEndDate;
		lotterySchedule.callEndDateHour = lotterySchedule.appEndDateHour;
		lotterySchedule.callEndDateMin = lotterySchedule.appEndDateMinute;
		lotterySchedule.callEndDateAm = lotterySchedule.appEndDateAM;
		
		lotterySchedule.executeDate = DateFunctions.getDateAfterToday(5, tz);
		lotterySchedule.executeHour = "2";
		lotterySchedule.executeMin = "25";
		lotterySchedule.executeAM = false;
		lotterySchedule.executeDateTime = DateFunctions.formatDate(lotterySchedule.executeDate, "EEE MMM d yyy")+" "
				+lotterySchedule.executeHour+":"+lotterySchedule.executeMin+" "
				+(lotterySchedule.executeAM?"AM":"PM");// format: Tue Jan 8 2013 12:00 AM
		
		lotterySchedule.freezeDuration = "2";
		lotterySchedule.appHeaderMsg = "Application Header - Test case about add lotter schedule.";

		// for verification in schedule list page
		lotterySchedule.invExclusionDate = OrmsConstants.NO_STATUS;
		lotterySchedule.status = OrmsConstants.NO_STATUS;
		lotterySchedule.executionStatus = StringUtil.EMPTY;
		lotterySchedule.applicableSeason = "FutureSeason (01/01/2022 - 01/31/2022)";// Don't change!
		
		// new lottery schedule info
		editLotterySchedule.appStartDate = DateFunctions.getDateAfterToday(3, tz);
		editLotterySchedule.appStartDateHour = "7";
		editLotterySchedule.appStartDateMinute = "43";
		editLotterySchedule.appStartDateAM = false;
		editLotterySchedule.appStartDateTime = DateFunctions.formatDate(editLotterySchedule.appStartDate, "EEE MMM d yyy")+" "
				+editLotterySchedule.appStartDateHour+":"+editLotterySchedule.appStartDateMinute+" "
				+(editLotterySchedule.appStartDateAM?"AM":"PM");// format: Tue Jan 8 2013 12:00 AM
		
		editLotterySchedule.appEndDate = DateFunctions.getDateAfterToday(5, tz);
		editLotterySchedule.appEndDateHour = "9";
		editLotterySchedule.appEndDateMinute = "15";
		editLotterySchedule.appEndDateAM = true;
		editLotterySchedule.appEndDateTime = DateFunctions.formatDate(editLotterySchedule.appEndDate, "EEE MMM d yyy")+" "
				+editLotterySchedule.appEndDateHour+":"+editLotterySchedule.appEndDateMinute+" "
				+(editLotterySchedule.appEndDateAM?"AM":"PM");// format: Tue Jan 8 2013 12:00 AM
		
		editLotterySchedule.callCenter = false;
		editLotterySchedule.field = true;
		editLotterySchedule.isUpdateFeild = false;
		editLotterySchedule.fieldStartDate = editLotterySchedule.appStartDate;
		editLotterySchedule.fieldStartDateHour = editLotterySchedule.appStartDateHour;
		editLotterySchedule.fieldStartDateMin = editLotterySchedule.appStartDateMinute;
		editLotterySchedule.fieldStartDateAm = editLotterySchedule.appStartDateAM;
		editLotterySchedule.fieldEndDate = editLotterySchedule.appEndDate;
		editLotterySchedule.fieldEndDateHour = editLotterySchedule.appEndDateHour;
		editLotterySchedule.fieldEndDateMin = editLotterySchedule.appEndDateMinute;
		editLotterySchedule.fieldEndDateAm = editLotterySchedule.appEndDateAM;
		
		editLotterySchedule.executeDate = DateFunctions.getDateAfterToday(10, tz);
		editLotterySchedule.executeHour = "4";
		editLotterySchedule.executeMin = "30";
		editLotterySchedule.executeAM = false;
		editLotterySchedule.executeDateTime = DateFunctions.formatDate(editLotterySchedule.executeDate, "EEE MMM d yyy")+" "
				+editLotterySchedule.executeHour+":"+editLotterySchedule.executeMin+" "
				+(editLotterySchedule.executeAM?"AM":"PM");// format: Tue Jan 8 2013 12:00 AM
		
		editLotterySchedule.freezeDuration = "5";
		editLotterySchedule.invStartDate = DateFunctions.getDateAfterToday(30, "EEE MMM d yyy", tz);
		editLotterySchedule.invEndDate = DateFunctions.getDateAfterToday(40, "EEE MMM d yyy", tz);
		
		editLotterySchedule.appHeaderMsg = "New Application Header";
		editLotterySchedule.appBodyMsg = "This is message for Lottery Terms and Conditions, it will displays at bottom of the page.";

		// for verification in schedule list page
		editLotterySchedule.invExclusionDate = OrmsConstants.NO_STATUS;
		editLotterySchedule.status = OrmsConstants.NO_STATUS;
		editLotterySchedule.executionStatus = StringUtil.EMPTY;
		
		editLotterySchedule.enteredStatus = "This is enter status.";
		editLotterySchedule.awardedUnconfirmedState = "You were successful in securing a reservation for Slip.";
		editLotterySchedule.successfulState = "You were successful in securing a reservation for Slip.";
		editLotterySchedule.unsuccessfulState = "We are sorry to inform you that you were not successful in securing a reservation.";
		editLotterySchedule.applicableSeason = "FutureSeason (01/01/2022 - 01/31/2022)";// Don't change!
	}
}
