package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.schedule;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrProcessingDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify edit a schedule with 'pending' status
 * @LinkSetUp: d_hf_add_lottery_execution_config:id=100(DESCRIPTION='VerifyScheduleHistory')
 * @SPEC:[TC:044738] Edit Priviledge Lottery Schedule   
 * @Task#: Auto-2072
 * @author Phoebe Chen
 * @Date  March 07, 2014
 */
public class VerifyUI_Edit_Pending extends LicenseManagerTestCase{
	private PrivilegeLotteryScheduleInfo schedule = new PrivilegeLotteryScheduleInfo();
	private LicMgrProcessingDetailsPage detailsPage = LicMgrProcessingDetailsPage.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotterySchedulePage();
		//Clean the old data
		this.clearOldSchedule();
		
		//Add a schedule with 'Pending' status
		schedule.setId(lm.addLotterySchedule(schedule));
		
		this.verifyEditableStatusForItem();
		
		lm.logOutLicenseManager();
	}

	private void clearOldSchedule() {
		lm.deactivateLotteryScheduleByDesc(schedule.getDescription());
		lm.deactiveLotteryScheduleByLicenseYearAndLotteryPrd(schedule.getLottery(), schedule.getLicenseYear());
	}

	private void verifyEditableStatusForItem() {
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Status:", true, detailsPage.isStatusEditable());
		passed &= MiscFunctions.compareResult("License Year:", false, detailsPage.isLicenseYearEditable());
		passed &= MiscFunctions.compareResult("Description:", true, detailsPage.isDescriptionEditable());
		passed &= MiscFunctions.compareResult("Lottery:", false, detailsPage.isLotteryEditable());
		passed &= MiscFunctions.compareResult("Lottery execution configuration:", false, detailsPage.isLotteryExecutionConfigEditable());
		passed &= MiscFunctions.compareResult("Lottery processing status:", false, detailsPage.isProcessingStatusEditable());
		passed &= MiscFunctions.compareResult("Freeze Period End Date:", true, detailsPage.isFreezePeriodEndDateEditable());
		passed &= MiscFunctions.compareResult("Freeze Period End Time:", true, detailsPage.isFreezePeriodEndTimeEditable());
		passed &= MiscFunctions.compareResult("Award Acceptance By Date:", true, detailsPage.isAwardAcceptanceByDateEditable());
		passed &= MiscFunctions.compareResult("Award Acceptance By Time:", true, detailsPage.isAwardAcceptanceByTimeEditable());
		if(!passed){
			throw new ErrorOnPageException("The editable status is not correct, please check the log above!");
		}
		logger.info("The editable status for all the item are correct!");
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facility = lm.getFacilityName("1", schema);//Mississippi Department of Wildlife, Fisheries, and Parks
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/" + facility;
		
		//privilege lottery schedule info
		schedule.setExecutionConfig("VerifyScheduleHistory");
		schedule.setLicenseYear(String.valueOf(DateFunctions.getYearAfterCurrentYear(9)));
		schedule.setDescription("Verify edit ui of pending");
		schedule.setLottery("Hunt Product");
		schedule.setCalculateAgeMethod("Specific Date");
		schedule.setSpecificDate(DateFunctions.getDateAfterToday(400));
		schedule.setFreezePeriodEndDate(DateFunctions.getDateAfterToday(9*365+123));
		schedule.setSeedNumber("2");
		schedule.setInitialNumber("5");
		schedule.setFreezePeriodEndTime("11:11");
		schedule.setFreezePeriodEndAmPm("AM");
		schedule.setAwardAcceptanceByDate(DateFunctions.getDateAfterToday(9*365+126));
		schedule.setAwardAcceptanceByTime("11:12");
		schedule.setAwardAcceptanceByAmPm("PM");
	}
}