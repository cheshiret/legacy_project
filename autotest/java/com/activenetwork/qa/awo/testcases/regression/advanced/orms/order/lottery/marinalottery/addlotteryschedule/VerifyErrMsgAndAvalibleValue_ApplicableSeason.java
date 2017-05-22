package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.lottery.marinalottery.addlotteryschedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.LotteryPreferenceAttribute;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryScheduleDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: It is to valid season for applicable season drop down list and the error message if applicable season is not selected 
 * @LinkSetUp: none
 * @SPEC:[TC:062350]Add Lottery Schedule - Applicable Season
 * @Task#:Auto-2057
 * @author Phoebe Chen
 * @Date  March 26, 2015
 */
public class VerifyErrMsgAndAvalibleValue_ApplicableSeason extends InventoryManagerTestCase {
	private String errMsg = "Please select the applicable season.";
	private String facilityId;
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoLotterySearchPage();
		
		lottery.id=im.getLotteryId(schema,lottery.name);
		if (StringUtil.isEmpty(lottery.id)) {
			lottery.id = im.addNewLottery(lottery);
		}
		
		im.gotoLotteryDetailPageFromSearchPage(lottery);
		
		im.addLotteryScheduleToScheduleDetailPg();
		verifyAvailableApplicableSeason();
		
		String message = im.addInactiveLotterySchedule(lotterySchedule, lottery.productCategory);
		verifyErrorMsg(message);
		
		im.logoutInvManager();
	}
	
	private void verifyAvailableApplicableSeason() {
		LotteryScheduleDetailsPage lotteryScheduleDetailsPg = LotteryScheduleDetailsPage.getInstance();
		List<HashMap<String,String>> seasonInfo = this.getSlipSeasonDateFromDB(facilityId);
		List<String> expApplicableSeason = new ArrayList<String>();
		expApplicableSeason.add("Please Select");
		for(HashMap<String,String> info:seasonInfo){
			String applicableSeason = info.get("SEASON_NAME") + " (" + info.get("START_DATE")+" - " + info.get("END_DATE")+ ")";
			expApplicableSeason.add(applicableSeason);
		}
		List<String> actApplicableSeason = lotteryScheduleDetailsPg.getApplicableSeasonElement();
		if(!actApplicableSeason.containsAll(expApplicableSeason)&&expApplicableSeason.containsAll(actApplicableSeason)){
			throw new ErrorOnPageException("Available applicable season is not correct", expApplicableSeason.toString(), actApplicableSeason.toString());
		}
		logger.info("Error message is correct for applicable season does not select!");
	}

	private void verifyErrorMsg(String actMsg) {
		if(!actMsg.matches(errMsg)){
			throw new ErrorOnPageException("Error message for applicable season does not select is not correct", errMsg, actMsg);
		}
		logger.info("Error message is correct for applicable season does not select!");
	}
	
	public List<HashMap<String, String>> getSlipSeasonDateFromDB(
			String facilityId) {
		//Get active season with seasonal slips
		AwoDatabase db=AwoDatabase.getInstance();
		db.resetSchema(schema);
		String query = "SELECT NAME as SEASON_NAME, TO_CHAR(START_DATE,'MM/DD/YYYY') as START_DATE, TO_CHAR(END_DATE,'MM/DD/YYYY') as END_DATE  "
				+ "FROM I_SEASON_SCHDL   "
				+ "WHERE DELETED_IND=0  "
				+ "AND FACILITY_ID  ="
				+ facilityId
				+ "  "
				+ "AND ACTIVE_IND   =1  "
				+ "AND PRD_CAT_ID   =20  "
				+ "AND id in (select distinct season_schd_id from I_SEASON_SCHDL_MR_PRD where  reservation_type_id=1 and active_ind=1)";

		logger.info("SQL: " + query);

		List<HashMap<String, String>> results = db.executeQuery(query);

		return results;

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
		facilityId = "552806"; //	Carolina Beach State Park
		// lottery info
		lottery.name = this.caseName;
		lottery.productCategory = "Slip";
		lottery.locationCategory = "Park";
		lottery.description = "QA Auto Test for values and error message of applicable season when add a new lottery schedule.";		
		lottery.location = im.getFacilityName(facilityId, schema);
		this.setPreferenceAttributes();
		lottery.facility=  lottery.location;//

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
		
//		lotterySchedule.applicableSeason = "FutureSeason (01/01/2022 - 01/31/2022)";// Don't change!
//		
//		lotterySchedule.isAddToWaitList = true; //This is the check point
//		lotterySchedule.waitList = "ListForSearch01";
		
//		String[] applicableSeason = (lotterySchedule.applicableSeason.split("\\(")[1]).split("-");
//		lotterySchedule.invStartDate = DateFunctions.formatDate(applicableSeason[0].trim(), "EEE MMM d yyyy");
//		lotterySchedule.invEndDate = DateFunctions.formatDate(applicableSeason[1].replaceAll("\\)", StringUtil.EMPTY).trim(), "EEE MMM d yyyy");
	}
	
}
