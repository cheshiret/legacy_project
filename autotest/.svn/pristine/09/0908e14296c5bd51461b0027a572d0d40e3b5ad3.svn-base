package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery.addlotteryschedule;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryScheduleDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryScheduleSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: When add a new lottery schedule,
 * 1.Execution Date and Time is blank but Freeze Duration is specified.
 * 2.Execution Date and Time is specified but Freeze Duration is blank.
 * 3.Enter valid Execution Period Date & Time and Freeze Duration
 * @Preconditions: Need a lottery exist.(d_inv_new_lottery_program, ID:470, name: VerifyMsgResultReleaseDateValidation)
 * @SPEC: Add Lottery Schedule - Lottery Results Release Date & Time [TC:044751]
 * @Task#: Auto-1343
 * 
 * @author nding1
 * @Date  Jan 9, 2013
 */
public class VerifyErrMsg_ResultReleaseDateValidation extends InventoryManagerTestCase {
	private TimeZone tz;
	private LotteryScheduleDetailsPage lotteryScheduleDetailsPg = LotteryScheduleDetailsPage.getInstance();
	private LotteryScheduleSearchPage lotteryScheduleSearchPg = LotteryScheduleSearchPage.getInstance();
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoLotterySearchPage();
		
		lottery.id=im.getLotteryId(schema,lottery.name);
		if (StringUtil.isEmpty(lottery.id)) {
			lottery.id = im.addNewLottery(lottery);
		}
		
		// go to add new schedule page
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		im.gotoLotteryScheduleSearchPgFromLotteryDetailPg();
		lotteryScheduleSearchPg.clickAddNew();
		lotteryScheduleDetailsPg.waitLoading();

		// 1.Execution Date and Time is blank but Freeze Duration is specified.
		boolean result = true;
		lotterySchedule.executeDate = StringUtil.EMPTY;
		lotterySchedule.freezeDuration = "2";
		this.setDateAndVerifyReleaseDate(StringUtil.EMPTY);
		String releaseDate = lotteryScheduleDetailsPg.getReleaseDate();// result release date should be blank.
		result &= MiscFunctions.compareResult("Result Release Date when Execution Date and Time is blank", StringUtil.EMPTY, releaseDate);

		// 2.Execution Date and Time is specified but Freeze Duration is blank.
		lotterySchedule.executeDate = DateFunctions.getDateAfterToday(10, tz);
		lotterySchedule.freezeDuration = StringUtil.EMPTY;
		this.setDateAndVerifyReleaseDate(StringUtil.EMPTY);
		releaseDate = lotteryScheduleDetailsPg.getReleaseDate();// result release date should be blank.
		result &= MiscFunctions.compareResult("Result Release Date when Freeze Duration is blank", StringUtil.EMPTY, releaseDate);
		
		// 3.Enter valid Execution Period Date & Time and Freeze Duration
		lotterySchedule.executeDate = DateFunctions.getDateAfterToday(10, tz);
		lotterySchedule.freezeDuration = "2";
		lotterySchedule.releaseDate = DateFunctions.getDateAfterToday(13, "EEE MMM d yyy", tz) + " 12:01 AM";
		this.setDateAndVerifyReleaseDate(StringUtil.EMPTY);
		releaseDate = lotteryScheduleDetailsPg.getReleaseDate();// result release date should be blank.
		result &= MiscFunctions.compareResult("Result Release Date(valid Execution Period and Freeze Duration)", lotterySchedule.releaseDate, releaseDate);

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
		lotterySchedule.invStartDate = DateFunctions.getDateAfterToday(10, tz);
		lotterySchedule.invEndDate = DateFunctions.getDateAfterToday(20, tz);
	}
}
