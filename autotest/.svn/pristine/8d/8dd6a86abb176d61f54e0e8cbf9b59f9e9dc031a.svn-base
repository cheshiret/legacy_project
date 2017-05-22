package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.schedule;


import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrProcessingDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify error message for add a lottery schedule
 * @LinkSetUp:  d_hf_add_lottery_execution_config:id=100(DESCRIPTION='VerifyScheduleHistory')
 * @SPEC:[TC:054440] Add/Edit Privilege Lottery Schedule - Error Messages  
 * @Task#: Auto-2072
 * @author Phoebe Chen
 * @Date  March 04, 2014
 */
public class VerifyErrorMsg extends LicenseManagerTestCase{
	private PrivilegeLotteryScheduleInfo schedule = new PrivilegeLotteryScheduleInfo();
	private PrivilegeLotteryScheduleInfo updateSchedule = new PrivilegeLotteryScheduleInfo();
	private String errMsg_DesIsEmpty, errMsg_SeedNumIsEmpty, errMsg_initialNumIsEmpty, errMSG_CalculateAgeIsEmpty, errMsg_initialNumNotProperty, errMsg_FreezeDateLessThanCurrentDate,
	errMsg_AwardDateLessThanFreezeDate, errMsg_AwardDateLessThanCurrentDate, errMsg_DesIsDuplicate, errMsg_duplicateLotteryAndLY, randomNumRangeTo;
	private LicMgrProcessingDetailsPage detailsPage = LicMgrProcessingDetailsPage.getInstance();
	private boolean passed = true;
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotterySchedulePage();
		//Clean the old data
		this.clearOldSchedule();
		//For duplicate validation
		lm.addLotteryScheduleAndClickOK(schedule);
		
		//Description is empty
		this.setUpScheduleInfo(String.valueOf(DateFunctions.getYearAfterGivenYear(1,schedule.getLicenseYear())), schedule.getLottery(), StringUtil.EMPTY, schedule.getSeedNumber(), 
				schedule.getInitialNumber(), schedule.getSpecificDate(), schedule.getFreezePeriodEndDate(), schedule.getAwardAcceptanceByDate());
		lm.addLotterySchedule(updateSchedule);
		passed &= MiscFunctions.compareResult("Error message - description is empty", errMsg_DesIsEmpty, detailsPage.getErrorMess());
		
		//Sara[20140626] Get random number range to value and initial related error message
		randomNumRangeTo = detailsPage.getRandomRangeTo();
		errMsg_initialNumNotProperty = "Initial Number from 1 to "+randomNumRangeTo+" is required. Please specify a valid value in the field provided."; //999999999

		//Seed number is empty
		this.setUpScheduleInfo(String.valueOf(DateFunctions.getYearAfterGivenYear(1,schedule.getLicenseYear())), schedule.getLottery(), schedule.getDescription() + "Update", StringUtil.EMPTY, 
				schedule.getInitialNumber(), schedule.getSpecificDate(), schedule.getFreezePeriodEndDate(), schedule.getAwardAcceptanceByDate());
		this.setUpLotteryInfoAndClickApply(updateSchedule);
		passed &= MiscFunctions.compareResult("Error message - seed number is empty", errMsg_SeedNumIsEmpty, detailsPage.getErrorMess());
		
		//Initial number is empty
		this.setUpScheduleInfo(String.valueOf(DateFunctions.getYearAfterGivenYear(1,schedule.getLicenseYear())), schedule.getLottery(), schedule.getDescription() + "Update", schedule.getSeedNumber(), 
				StringUtil.EMPTY, schedule.getSpecificDate(), schedule.getFreezePeriodEndDate(), schedule.getAwardAcceptanceByDate());
		this.setUpLotteryInfoAndClickApply(updateSchedule);
		passed &= MiscFunctions.compareResult("Error message - initial number is empty", errMsg_initialNumIsEmpty, detailsPage.getErrorMess());
		
		//Calculate Age of date is empty is empty
		this.setUpScheduleInfo(String.valueOf(DateFunctions.getYearAfterGivenYear(1,schedule.getLicenseYear())), schedule.getLottery(), schedule.getDescription() + "Update", schedule.getSeedNumber(), 
				schedule.getInitialNumber(), StringUtil.EMPTY, schedule.getFreezePeriodEndDate(), schedule.getAwardAcceptanceByDate());
		this.setUpLotteryInfoAndClickApply(updateSchedule);
		passed &= MiscFunctions.compareResult("Error message - caculate age of date is empty", errMSG_CalculateAgeIsEmpty, detailsPage.getErrorMess());
		
		//Initial number is not proper
		this.setUpScheduleInfo(String.valueOf(DateFunctions.getYearAfterGivenYear(1,schedule.getLicenseYear())), schedule.getLottery(), schedule.getDescription() + "Update", schedule.getSeedNumber(), 
				"10000000000", schedule.getSpecificDate(), schedule.getFreezePeriodEndDate(), schedule.getAwardAcceptanceByDate());
		this.setUpLotteryInfoAndClickApply(updateSchedule);
		passed &= MiscFunctions.compareResult("Error message - initial number is not property", errMsg_initialNumNotProperty, detailsPage.getErrorMess());
		
		//Freeze date less than current date 
		this.setUpScheduleInfo(String.valueOf(DateFunctions.getYearAfterGivenYear(1,schedule.getLicenseYear())), schedule.getLottery(), schedule.getDescription() + "Update", schedule.getSeedNumber(), 
				schedule.getInitialNumber(), schedule.getSpecificDate(), DateFunctions.getDateAfterToday(-1), schedule.getAwardAcceptanceByDate());
		this.setUpLotteryInfoAndClickApply(updateSchedule);
		passed &= MiscFunctions.compareResult("Error message - freeze date is less than current date", errMsg_FreezeDateLessThanCurrentDate, detailsPage.getErrorMess());
		
		//Award date less than freeze date
		this.setUpScheduleInfo(String.valueOf(DateFunctions.getYearAfterGivenYear(1,schedule.getLicenseYear())), schedule.getLottery(), schedule.getDescription() + "Update", schedule.getSeedNumber(), 
				schedule.getInitialNumber(), schedule.getSpecificDate(), schedule.getFreezePeriodEndDate(), DateFunctions.getDateAfterGivenDay(schedule.getFreezePeriodEndDate(), -3));
		this.setUpLotteryInfoAndClickApply(updateSchedule);
		passed &= MiscFunctions.compareResult("Error message - award date is less than freeze date", errMsg_AwardDateLessThanFreezeDate, detailsPage.getErrorMess());
		
		//Freeze date is null and award date less than current date
		this.setUpScheduleInfo(String.valueOf(DateFunctions.getYearAfterGivenYear(1,schedule.getLicenseYear())), schedule.getLottery(), schedule.getDescription() + "Update", schedule.getSeedNumber(), 
				schedule.getInitialNumber(), schedule.getSpecificDate(), StringUtil.EMPTY, DateFunctions.getDateAfterToday(-3));
		this.setUpLotteryInfoAndClickApply(updateSchedule);
		passed &= MiscFunctions.compareResult("Error message - award date is less than current date", errMsg_AwardDateLessThanCurrentDate, detailsPage.getErrorMess());
		
		//Duplicate description
		this.setUpScheduleInfo(String.valueOf(DateFunctions.getYearAfterGivenYear(1,schedule.getLicenseYear())), schedule.getLottery(), schedule.getDescription(), schedule.getSeedNumber(), 
				schedule.getInitialNumber(), schedule.getSpecificDate(), schedule.getFreezePeriodEndDate(), schedule.getAwardAcceptanceByDate());
		this.setUpLotteryInfoAndClickApply(updateSchedule);
		passed &= MiscFunctions.compareResult("Error message - duplicate description", errMsg_DesIsDuplicate, detailsPage.getErrorMess());
		
		//Duplicate lottery and license year
		this.setUpScheduleInfo(schedule.getLicenseYear(), schedule.getLottery(), schedule.getDescription() + "Update", schedule.getSeedNumber(), 
				schedule.getInitialNumber(), schedule.getSpecificDate(), schedule.getFreezePeriodEndDate(), schedule.getAwardAcceptanceByDate());
		this.setUpLotteryInfoAndClickApply(updateSchedule);
		passed &= MiscFunctions.compareResult("Error message - duplicate lottery and license year", errMsg_duplicateLotteryAndLY, detailsPage.getErrorMess());
		
		if(!passed){
			throw new ErrorOnPageException("Not all the error message is correct, please check the log above!");
		}
		logger.info("Error message for adding privilege are all correct!");
		
		lm.logOutLicenseManager();
	}
	
	private void setUpLotteryInfoAndClickApply(PrivilegeLotteryScheduleInfo schedule){
		detailsPage.setupLotterySchedule(schedule);
		detailsPage.clickApply();
		ajax.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facilityName = lm.getFacilityName("1", schema);//Mississippi Department of Wildlife, Fisheries, and Parks
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/" + facilityName;
		
		//privilege lottery schedule info
		schedule.setStatus(ACTIVE_STATUS);
		schedule.setExecutionConfig("VerifyScheduleHistory");
		schedule.setLicenseYear(String.valueOf(DateFunctions.getYearAfterCurrentYear(4)));
		schedule.setDescription("Verify error message");
		schedule.setLottery("Hunt Product");
		schedule.setSeedNumber("1");
		schedule.setInitialNumber("3");
		schedule.setCalculateAgeMethod("Specific Date");
		schedule.setSpecificDate(DateFunctions.getDateAfterToday(4*365+121));
		schedule.setFreezePeriodEndDate(DateFunctions.getDateAfterToday(4*365+122));
		schedule.setFreezePeriodEndTime("11:11");
		schedule.setFreezePeriodEndAmPm("AM");
		schedule.setAwardAcceptanceByDate(DateFunctions.getDateAfterToday(4*365+123));
		schedule.setAwardAcceptanceByTime("11:12");
		schedule.setAwardAcceptanceByAmPm("PM");
		
		updateSchedule.setStatus(schedule.getStatus());
		updateSchedule.setExecutionConfig(schedule.getExecutionConfig());
		updateSchedule.setLicenseYear(schedule.getLicenseYear());
		updateSchedule.setDescription(schedule.getDescription());
		updateSchedule.setLottery(schedule.getLottery());
		updateSchedule.setSeedNumber(schedule.getSeedNumber());
		updateSchedule.setInitialNumber(schedule.getInitialNumber());
		updateSchedule.setCalculateAgeMethod(schedule.getCalculateAgeMethod());
		updateSchedule.setSpecificDate(schedule.getSpecificDate());
		updateSchedule.setFreezePeriodEndDate(schedule.getFreezePeriodEndDate());
		updateSchedule.setFreezePeriodEndTime(schedule.getFreezePeriodEndTime());
		updateSchedule.setFreezePeriodEndAmPm(schedule.getFreezePeriodEndAmPm());
		updateSchedule.setAwardAcceptanceByDate(schedule.getAwardAcceptanceByDate());
		updateSchedule.setAwardAcceptanceByTime(schedule.getAwardAcceptanceByTime());
		updateSchedule.setAwardAcceptanceByAmPm(schedule.getAwardAcceptanceByAmPm());
		
		errMsg_DesIsEmpty = "Description is required. Please specify the Description.";
		errMsg_SeedNumIsEmpty = "Seed Number is required. Please specify the value in the field provided.";
		errMsg_initialNumIsEmpty = "Initial Number is required. Please specify the value in the field provided.";
		errMSG_CalculateAgeIsEmpty = "Calculate Age as of is required. Please specify a valid value in the field provided.";
		errMsg_FreezeDateLessThanCurrentDate = "Execution Initiation is \"Manual\". Freeze Period End Date & Time must be a valid future date. Please specify a valid value in the field provided.";
		errMsg_AwardDateLessThanFreezeDate = "Customer Award Acceptance Deadline Date & Time must be greater than or equal to Freeze Period End Date & Time. Please specify a valid value in the field provided.";
		errMsg_AwardDateLessThanCurrentDate = "Customer Award Acceptance Deadline Date & Time must be a future date & time. Please specify a valid value in the field provided.";
		
		errMsg_DesIsDuplicate = "An active Lottery Schedule with the same Description already exists. Please specify a different Description in the field provided.";
		errMsg_duplicateLotteryAndLY  = "Active Lottery Execution Schedule for the Lottery Product and License Year already exists.";
	}
	
	private void setUpScheduleInfo(String licenseYear, String lottery, String description, String seedNum, String iniNum, String specificeDate, String freezeDate, String awardDate){
		updateSchedule.setLicenseYear(licenseYear);
		updateSchedule.setLottery(lottery);
		updateSchedule.setDescription(description);
		updateSchedule.setSeedNumber(seedNum);
		updateSchedule.setInitialNumber(iniNum);
		updateSchedule.setSpecificDate(specificeDate);
		updateSchedule.setFreezePeriodEndDate(freezeDate);
		updateSchedule.setAwardAcceptanceByDate(awardDate);
	}
	
	private void clearOldSchedule() {
		lm.deactivateLotteryScheduleByDesc(schedule.getDescription());
		lm.deactiveLotteryScheduleByLicenseYearAndLotteryPrd(schedule.getLottery(), schedule.getLicenseYear());
	}

}
