package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.lottery.schedule;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryScheduleDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryScheduleSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Add a new lottery schedule and verify whether it is added successfully or not.
 * @Preconditions: Need a lottery exist.(d_inv_new_lottery_program, ID:410, name:AddNewLotteryScheduleMarina)
 * 
 * @SPEC: TC042360,044640,042362,042363,042361,044751,044641,042381
 * @Task#: Auto-1343
 * @author nding1
 * @Date  Jan 7, 2013
 */
public class AddSlipLotterySchedule extends InventoryManagerTestCase {
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoLotterySearchPage();
		
		lottery.id=im.getLotteryId(schema,lottery.name);
		if (StringUtil.isEmpty(lottery.id)) {
			lottery.id = im.addNewLottery(lottery);
		}
		
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		lotterySchedule.id = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);

		// verify schedule info in list page
		LotteryScheduleSearchPage lotteryScheduleSearchPg = LotteryScheduleSearchPage .getInstance();
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		lotteryScheduleSearchPg.verifyScheduleInfo(lotterySchedule);
		
		// go to lottery schedule detail page and verify detail info.
		im.gotoLotterySchdDetailPgFromLotterySchdSearchPg(lotterySchedule.id);
		LotteryScheduleDetailsPage lotteryScheduleDetailsPg = LotteryScheduleDetailsPage .getInstance();
		lotteryScheduleDetailsPg.verifyScheduleDetailInfo(lotterySchedule);
		
		im.logoutInvManager();
	}
	
	private void setPreferenceAttributes() {
		lottery.attributes.clear();
		String[] attributes = {"Facility","Min Slip Depth","Min Slip Length","Min Slip Width","Boat Category","Dock"};
		String[] entriyRequired = {"Per Application","Per Application","Per Application","Per Application","Per Application","Per Preference"};
		for (int i = 0; i < attributes.length; i++) {
			LotteryPreferenceAttribute attr = new LotteryPreferenceAttribute();
			attr.label = attributes[i];
			attr.displayOrder = String.valueOf(i + 1);
			attr.entryRequired = entriyRequired[i];
			lottery.attributes.add(attr);
		}
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		// lottery info
		lottery.name = "AddNewLotteryScheduleMarina";// d_inv_new_lottery_program, ID:410
		lottery.productCategory = "Slip";
		lottery.locationCategory = "Park";
		lottery.description = "QA Auto Test for Add lottery schedule.";		
		lottery.location = im.getFacilityName("552903", schema);//"Jordan Lake State Rec Area";
		this.setPreferenceAttributes();
		lottery.facility=  lottery.location;//"Jordan Lake State Rec Area";

		// lottery schedule info
		lotterySchedule.appStartDate = DateFunctions.getDateAfterToday(2);
		lotterySchedule.appStartDateHour = "6";
		lotterySchedule.appStartDateMinute = "10";
		lotterySchedule.appStartDateAM = true;
		lotterySchedule.appStartDateTime = DateFunctions.formatDate(lotterySchedule.appStartDate, "EEE MMM d yyyy")+" "
				+lotterySchedule.appStartDateHour+":"+lotterySchedule.appStartDateMinute+" "
				+(lotterySchedule.appStartDateAM?"AM":"PM");// format: Tue Jan 8 2013 12:00 AM
		
		lotterySchedule.appEndDate = DateFunctions.getDateAfterToday(3);
		lotterySchedule.appEndDateHour = "11";
		lotterySchedule.appEndDateMinute = "29";
		lotterySchedule.appEndDateAM = false;
		lotterySchedule.appEndDateTime = DateFunctions.formatDate(lotterySchedule.appEndDate, "EEE MMM d yyyy")+" "
				+lotterySchedule.appEndDateHour+":"+lotterySchedule.appEndDateMinute+" "
				+(lotterySchedule.appEndDateAM?"AM":"PM");// format: Tue Jan 8 2013 12:00 AM
		
		lotterySchedule.callCenter = true;
		lotterySchedule.callStartDate = lotterySchedule.appStartDate;
		lotterySchedule.callStartDateHour = lotterySchedule.appStartDateHour;
		lotterySchedule.callStartDateMin = lotterySchedule.appStartDateMinute;
		lotterySchedule.callStartDateAm = lotterySchedule.appStartDateAM;
		lotterySchedule.callEndDate = lotterySchedule.appEndDate;
		lotterySchedule.callEndDateHour = lotterySchedule.appEndDateHour;
		lotterySchedule.callEndDateMin = lotterySchedule.appEndDateMinute;
		lotterySchedule.callEndDateAm = lotterySchedule.appEndDateAM;
		
		lotterySchedule.executeDate = DateFunctions.getDateAfterToday(5);
		lotterySchedule.executeHour = "2";
		lotterySchedule.executeMin = "25";
		lotterySchedule.executeAM = false;
		lotterySchedule.executeDateTime = DateFunctions.formatDate(lotterySchedule.executeDate, "EEE MMM d yyyy")+" "
				+lotterySchedule.executeHour+":"+lotterySchedule.executeMin+" "
				+(lotterySchedule.executeAM?"AM":"PM");// format: Tue Jan 8 2013 12:00 AM
		
		lotterySchedule.freezeDuration = "2";
		
		lotterySchedule.appHeaderMsg = "Application Header - Test case about add lotter schedule.";

		// for verification in schedule list page
		lotterySchedule.invExclusionDate = OrmsConstants.NO_STATUS;
		lotterySchedule.status = OrmsConstants.NO_STATUS;
		lotterySchedule.executionStatus = StringUtil.EMPTY;
		
		lotterySchedule.enteredStatus = "This is enter status.";
		lotterySchedule.awardedUnconfirmedState = "You were successful in securing a reservation for Slip.";
		lotterySchedule.successfulState = "You were successful in securing a reservation for Slip.";
		lotterySchedule.unsuccessfulState = "We are sorry to inform you that you were not successful in securing a reservation.";
		
		lotterySchedule.applicableSeason = "FutureSeason (01/01/2022 - 01/31/2022)";// Don't change!
		String[] applicableSeason = (lotterySchedule.applicableSeason.split("\\(")[1]).split("-");
		lotterySchedule.invStartDate = DateFunctions.formatDate(applicableSeason[0].trim(), "EEE MMM d yyyy");
		lotterySchedule.invEndDate = DateFunctions.formatDate(applicableSeason[1].replaceAll("\\)", StringUtil.EMPTY).trim(), "EEE MMM d yyyy");
	}
}
