package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery.editlotteryschedule;

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
 * @Description: When edit lottery schedule,
 * 1.Update Execution Period Date/Time only
 * 2.Update the Freeze Duration only
 * 3.Execution Date and Time is specified but Freeze Duration is blank.
 * 4.Execution Date and Time is blank but Freeze Duration is specified.
 * 5.Execution Date/Time and Freeze Duration are both blank.
 * @Preconditions: Need a lottery exist.(d_inv_new_lottery_program, ID:470, name: VerifyMsgResultReleaseDateValidation)
 * @SPEC: Edit Lottery Schedule - Lottery Results Release Date & Time [TC:044752]
 * @Task#: Auto-1343
 * 
 * @author nding1
 * @Date  Jan 10, 2013
 */
public class VerifyErrMsg_ResultReleaseDateValidation extends InventoryManagerTestCase {
	private TimeZone tz;
	private LotteryScheduleDetailsPage lotteryScheduleDetailsPg = LotteryScheduleDetailsPage.getInstance();

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
		
		// add a new lottery schedule
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		lotterySchedule.id = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);

		// 1.Update Execution Period Date/Time only
		boolean result = true;
		lotterySchedule.executeDate = DateFunctions.getDateAfterToday(26, tz);
		lotterySchedule.releaseDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(32, tz), "EEE MMM d yyy")+" 12:01 AM";// Don't change!
		im.gotoLotterySchdDetailPgFromLotterySchdSearchPg(lotterySchedule.id);
		result &= this.setDateAndVerifyReleaseDate(lotterySchedule.releaseDate);

		// 2.Update the Freeze Duration only
		lotterySchedule.freezeDuration = "10";
		lotterySchedule.releaseDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(37, tz), "EEE MMM d yyy")+" 12:01 AM";// Don't change!
		result &= this.setDateAndVerifyReleaseDate(lotterySchedule.releaseDate);
		
		// 3.Execution Date and Time is specified but Freeze Duration is blank.
		lotterySchedule.freezeDuration = StringUtil.EMPTY;
		lotterySchedule.releaseDate = StringUtil.EMPTY;
		result &= this.setDateAndVerifyReleaseDate(lotterySchedule.releaseDate);

		// 4.Execution Date and Time is blank but Freeze Duration is specified.
		lotterySchedule.executeDate = StringUtil.EMPTY;
		lotterySchedule.freezeDuration = "10";
		result &= this.setDateAndVerifyReleaseDate(lotterySchedule.releaseDate);

		// 5. Execution Date/Time and Freeze Duration are both blank.
		lotterySchedule.executeDate = StringUtil.EMPTY;
		lotterySchedule.freezeDuration = StringUtil.EMPTY;
		result &= this.setDateAndVerifyReleaseDate(lotterySchedule.releaseDate);

		im.deactivateLotterySchedulesFromDB(schema, lottery.id);// clean up
		if(!result){
			throw new ErrorOnPageException("---Check logs above.");
		}
		im.logoutInvManager();
	}
	
	private boolean setDateAndVerifyReleaseDate(String expectReleaseDate){
		lotteryScheduleDetailsPg.setExcutePeriod(lotterySchedule.executeDate, lottery.productCategory);
		lotteryScheduleDetailsPg.clickAppSpaces();
		ajax.waitLoading();
		lotteryScheduleDetailsPg.waitLoading();
		lotteryScheduleDetailsPg.setFreezeDuration(lotterySchedule.freezeDuration, lottery.productCategory);
		lotteryScheduleDetailsPg.clickAppSpaces();
		ajax.waitLoading();
		lotteryScheduleDetailsPg.waitLoading();
		String releaseDate = lotteryScheduleDetailsPg.getReleaseDate();
		return MiscFunctions.compareResult("Result Release Date", expectReleaseDate, releaseDate);
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
		lottery.name = "VerifyMsgResultReleaseDateValidation";// d_inv_new_lottery_program, ID:470
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
		lotterySchedule.callCenter = true;
		lotterySchedule.executeDate = DateFunctions.getDateAfterToday(23, tz);
		lotterySchedule.freezeDuration = "5";
		lotterySchedule.applicableSeason = "FutureSeason (01/01/2022 - 01/31/2022)";
	}
}
