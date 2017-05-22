package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.schedule;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryExecutionConfigSelectWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrProcessingDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrProcessingListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify ui for add a lottery schedule with 'Leftover Lottery' configuration
 * @LinkSetUp:  d_hf_add_lottery_execution_config:id=130(DESCRIPTION='LeftoverSaleConf')
 *  		    d_hf_add_lottery_prd:id=550 -- For left over lottery schedule
 * @SPEC:[TC:044737] Add Priviledge Lottery Schedule 
 * @Task#: Auto-2064
 * @author Phoebe Chen
 * @Date  March 03, 2014
 */
public class Verify_Add_LeftoverLottery extends LicenseManagerTestCase{
	private PrivilegeLotteryScheduleInfo schedule = new PrivilegeLotteryScheduleInfo();
	private LicMgrProcessingDetailsPage detailsPage = LicMgrProcessingDetailsPage.getInstance();
	private LicMgrProcessingListPage processingListPage = LicMgrProcessingListPage.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotterySchedulePage();
		//Clean the old data
		this.clearOldSchedule();
		
		this.addLotteryScheduleToLotteryScheduelDetailPg();
		
		verifyDefaultValue();
		
		verifyEditableStatusForItem();
		
		this.setUpLotteryInfoAndClickOk(schedule);
		
		processingListPage.searchLotterySchedule(schedule);
		verifyScheduleInfoInList();
		
		lm.gotoLotteryScheduleDetailspage(schedule.getDescription());
		verifyScheduleInfoInDetailPg();
		
		lm.logOutLicenseManager();
	}
	
	private void verifyScheduleInfoInDetailPg() {
		logger.info("Verify lottery schedule details info.");
		boolean result = detailsPage.compareScheduleInfo(schedule);
		if(!result) {
			throw new ErrorOnPageException("Quota License Year details info is incorrect, please refer log for details info.");
		} else logger.info("Quota License Year details info is correct.");
		
	}

	private void verifyEditableStatusForItem() {
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Status:", false, detailsPage.isStatusEditable());
		passed &= MiscFunctions.compareResult("License Year:", true, detailsPage.isLicenseYearEditable());
		passed &= MiscFunctions.compareResult("Description:", true, detailsPage.isDescriptionEditable());
		passed &= MiscFunctions.compareResult("Lottery:", true, detailsPage.isLotteryEditable());
		passed &= MiscFunctions.compareResult("Lottery execution configuration:", false, detailsPage.isLotteryExecutionConfigEditable());
		passed &= MiscFunctions.compareResult("Lottery processing status:", false, detailsPage.isProcessingStatusEditable());
		passed &= MiscFunctions.compareResult("Seed Number:", false, detailsPage.isSeedNumberExist());
		passed &= MiscFunctions.compareResult("Initial Number:", false, detailsPage.isInitalNumberExist());
		passed &= MiscFunctions.compareResult("Freeze Period End Date:", true, detailsPage.isFreezePeriodEndDateExist());
		passed &= MiscFunctions.compareResult("Freeze Period End Time:", true, detailsPage.isFreezePeriodEndTimeExist());
		passed &= MiscFunctions.compareResult("Award Acceptance By Date:", true, detailsPage.isAwardAcceptanceByDateExist());
		passed &= MiscFunctions.compareResult("Award Acceptance By Time:", true, detailsPage.isAwardAcceptanceTimeExist());
		if(!passed){
			throw new ErrorOnPageException("The editable status is not correct, please check the log above!");
		}
		logger.info("The editable status for all the item are correct!");
	}

	private void verifyDefaultValue() {
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Default value for status:", ACTIVE_STATUS, detailsPage.getStatusWhenAddNew());
		passed &= MiscFunctions.compareResult("Default value for processing status:", PENDING_STATUS, detailsPage.getLotteryProcessingStatus());
		if(!passed){
			throw new ErrorOnPageException("Defauclt value is not correct, please check the log above!");
		}
		logger.info("Default value are correct!");
		
	}

	private void addLotteryScheduleToLotteryScheduelDetailPg() {
		LicMgrProcessingListPage listPage = LicMgrProcessingListPage.getInstance();
		LicMgrLotteryExecutionConfigSelectWidget selectWidget = LicMgrLotteryExecutionConfigSelectWidget.getInstance();
		
		listPage.clickAddLotterySchedule();
		ajax.waitLoading();
		selectWidget.waitLoading();
		selectWidget.selectLotteryExecutionConfig(schedule.getExecutionConfig());
		selectWidget.clickOK();
		ajax.waitLoading();
		detailsPage.waitLoading();
	}
	
	private void verifyScheduleInfoInList() {
		boolean passed = true;
		List<String> scheduleInfo = processingListPage.getLotteryScheduleInfo();
		passed &= MiscFunctions.compareResult("Status",	schedule.getStatus(), scheduleInfo.get(1));
		passed &= MiscFunctions.compareResult("Lottery",	schedule.getLottery(), scheduleInfo.get(2));
		passed &= MiscFunctions.compareResult("Algorithm",	"Leftover Sale", scheduleInfo.get(3));
		passed &= MiscFunctions.compareResult("Schedule",	schedule.getDescription(), scheduleInfo.get(4));
		passed &= MiscFunctions.compareResult("license year",	schedule.getLicenseYear(), scheduleInfo.get(5));
		passed &= MiscFunctions.compareResult("Processing status",	schedule.getProcessingStatus(), scheduleInfo.get(6));
		if(!passed){
			throw new ErrorOnPageException("Not all the check point has passed, please check log above.");
		}
		logger.info("The list infor for new added schedule is correct!");
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facility = lm.getFacilityName("1", schema);//Mississippi Department of Wildlife, Fisheries, and Parks
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/" + facility;
		
		//privilege lottery schedule info
		schedule.setStatus(ACTIVE_STATUS);
		schedule.setExecutionConfig("LeftoverSaleConf");//Check point, do not change
		schedule.setLicenseYear(String.valueOf(DateFunctions.getYearAfterCurrentYear(5)));
		schedule.setDescription("Verify add leftover Draw Lottery");
		schedule.setLottery("For left over lottery schedule");
		schedule.setCalculateAgeMethod("Specific Date");
		schedule.setSpecificDate(DateFunctions.getDateAfterToday(5*365+124));
		schedule.setFreezePeriodEndDate(DateFunctions.getDateAfterToday(5*365+125));
		schedule.setFreezePeriodEndTime("10:01");
		schedule.setFreezePeriodEndAmPm("AM");
		schedule.setAwardAcceptanceByDate(DateFunctions.getDateAfterToday(5*365+126));
		schedule.setAwardAcceptanceByTime("11:11");
		schedule.setAwardAcceptanceByAmPm("PM");
		schedule.setProcessingStatus(PENDING_STATUS);
	}
	
	private void setUpLotteryInfoAndClickOk(PrivilegeLotteryScheduleInfo schedule){
		detailsPage.setupLotterySchedule(schedule);
		detailsPage.clickApply();
		ajax.waitLoading();
		detailsPage.waitLoading();
		schedule.setId(detailsPage.getID());
		detailsPage.clickOK();
		ajax.waitLoading();
		processingListPage.waitLoading();
	}
	
	private void clearOldSchedule() {
		lm.deactivateLotteryScheduleByDesc(schedule.getDescription());
		lm.deactiveLotteryScheduleByLicenseYearAndLotteryPrd(schedule.getLottery(), schedule.getLicenseYear());
	}
}
