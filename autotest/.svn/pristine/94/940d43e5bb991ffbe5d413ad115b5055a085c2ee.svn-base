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
 * @Description: It is to add a lottery schedule with waiting list 
 * @LinkSetUp: d_inv_add_list:id=560(LISTNAME='ListForSearch01')  
 * @SPEC:[TC:044669] Add Lottery Schedule - with waiting list 
 * @Task#:Auto-2057
 * @author Phoebe Chen
 * @Date  March 26, 2015
 */
public class AddSlipLotterySchedule_WithWaitingList extends InventoryManagerTestCase {
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
		LotteryScheduleSearchPage lotteryScheduleSearchPg = LotteryScheduleSearchPage.getInstance();
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
		lottery.name = "Marina_Lottery_with_Waiting_List";
		lottery.productCategory = "Slip";
		lottery.locationCategory = "Park";
		lottery.description = "QA Auto Test for Add lottery schedule with waiting list.";		
		lottery.location = im.getFacilityName("552903", schema);//"Jordan Lake State Rec Area";
		this.setPreferenceAttributes();
		lottery.facility=  lottery.location;//"Jordan Lake State Rec Area";

		// lottery schedule info
		lotterySchedule.appStartDate = DateFunctions.getDateAfterToday(4);
		lotterySchedule.appStartDateHour = "7";
		lotterySchedule.appStartDateMinute = "11";
		lotterySchedule.appStartDateAM = true;
		lotterySchedule.appStartDateTime = DateFunctions.formatDate(lotterySchedule.appStartDate, "EEE MMM d yyyy")+" "
				+lotterySchedule.appStartDateHour+":"+lotterySchedule.appStartDateMinute+" "
				+(lotterySchedule.appStartDateAM?"AM":"PM");// format: Tue Jan 8 2013 12:00 AM
		
		lotterySchedule.appEndDate = DateFunctions.getDateAfterToday(5);
		lotterySchedule.appEndDateHour = "10";
		lotterySchedule.appEndDateMinute = "27";
		lotterySchedule.appEndDateAM = false;
		lotterySchedule.appEndDateTime = DateFunctions.formatDate(lotterySchedule.appEndDate, "EEE MMM d yyyy")+" "
				+lotterySchedule.appEndDateHour+":"+lotterySchedule.appEndDateMinute+" "
				+(lotterySchedule.appEndDateAM?"AM":"PM");// format: Tue Jan 8 2013 12:00 AM
		
		lotterySchedule.field = true;
		lotterySchedule.fieldStartDate = lotterySchedule.appStartDate;
		lotterySchedule.fieldStartDateHour = lotterySchedule.appStartDateHour;
		lotterySchedule.fieldStartDateMin = lotterySchedule.appStartDateMinute;
		lotterySchedule.fieldStartDateAm = lotterySchedule.appStartDateAM;
		lotterySchedule.fieldEndDate = lotterySchedule.appEndDate;
		lotterySchedule.fieldEndDateHour = lotterySchedule.appEndDateHour;
		lotterySchedule.fieldEndDateMin = lotterySchedule.appEndDateMinute;
		lotterySchedule.fieldEndDateAm = lotterySchedule.appEndDateAM;
		
		lotterySchedule.executeDate = DateFunctions.getDateAfterToday(6);
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
		
		lotterySchedule.isAddToWaitList = true; //This is the check point
		lotterySchedule.waitList = "ListForSearch01";
		
		String[] applicableSeason = (lotterySchedule.applicableSeason.split("\\(")[1]).split("-");
		lotterySchedule.invStartDate = DateFunctions.formatDate(applicableSeason[0].trim(), "EEE MMM d yyyy");
		lotterySchedule.invEndDate = DateFunctions.formatDate(applicableSeason[1].replaceAll("\\)", StringUtil.EMPTY).trim(), "EEE MMM d yyyy");
	}
}
