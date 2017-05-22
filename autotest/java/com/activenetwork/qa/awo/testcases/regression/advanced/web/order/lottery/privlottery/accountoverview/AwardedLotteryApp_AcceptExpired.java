package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.accountoverview;

import java.util.Arrays;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFAccountOverviewPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryApplicationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKLotteryApplicationTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify the awarded lottery application section on account overview page when:
 * 1. there are two awarded lottery applications, and one's accept deadline has been passed. -> only show one awarded order; more applications link shown
 * 2. Accept one awarded lottery application so that there is only one awarded lottery application with accept deadline passed -> awarded lottery application not shown
 * @Preconditions:
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1530
 * d_hf_add_privilege_prd:id=2630
 * d_hf_add_hunt_quota:id=270
 * d_hf_add_hunt:id=1020
 * d_hf_assign_priv_to_hunt:id=740
 * d_hf_add_lottery_prd:id=520
 * d_hf_add_lottery_license_year:id=430
 * d_hf_add_lottery_quantity_control:id=430
 * d_hf_assign_lottery_to_store:id=420
 * d_hf_add_pricing:id=4142
 * d_hf_assi_hunts_to_lottery:id=620
 * d_hf_add_hunt_license_year:id=1140
 * d_hf_add_lottery_execution_config:id=300
 * d_hf_add_lottery_schedule:id=340
 * @SPEC: Account Overview page - Awarded Lottery Application [TC:057416]
 * @Task#: Auto-1765
 * 
 * @author Lesley Wang
 * @Date  Apr 1, 2014
 */
public class AwardedLotteryApp_AcceptExpired extends
		HFSKLotteryApplicationTestCase {

	private String lotteryOrdWithAcceptDealine;
	private HFAccountOverviewPage overviewPg = HFAccountOverviewPage.getInstance();
	private HFLotteryApplicationPage lotteryAppPg = HFLotteryApplicationPage.getInstance();
	
	@Override
	public void execute() {
		//Precondition: Make Award Lottery application in LM with accept deadline
		prepareLotteryAppInLM();
		lotteryOrdWithAcceptDealine = lotteryOrderNum;
		
		// Make award lottery application in LM without accept deadline
		this.setupScheduleAwardedDeadline(0);
		licenseYear.sellToDate = DateFunctions.getDateAfterToday(30);
		prepareLotteryAppInLM();
		lotteryOrderNum = lotteryOrderNums.get(1);

		// Login in to Web to check the awarded lottery application section
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		overviewPg.verifyAwardedLotteryAppExist(lotteryOrdWithAcceptDealine, false);
		overviewPg.verifyAwardedLotteryAppInfo(lotteryOrderNum, lottery);
		overviewPg.verifyMoreApplicationsLinkExist(true);
		
		// Click the section title link to lottery application page, verify the two orders exist.
		this.gotoLotteryAppPgByClickAwardedAppSectionTitle();
		lotteryAppPg.verifyLotteryAppStatus(lotteryOrdWithAcceptDealine, AWARDED_STATUS);
		lotteryAppPg.verifyLotteryAppStatus(lotteryOrderNum, AWARDED_STATUS);
		
		// Go back account overview page and accept the awarded lottery. 
		hf.gotoMyAccountPgFromHeaderBar();
		hf.acceptAwardFromAcctOverviewPgToCart(lotteryOrderNum, cus.residencyStatus, cus.identifier);
		hf.checkOutCart(pay);
		
		// Verify the awarded lottery application section is not shown on account overview page
		hf.gotoMyAccountPgFromHeaderBar();
//		overviewPg.verifyNoLotteries();//TODO the verification should be updated, just need to verify the lottery section doesn't display the current 2 lottery application just made
		overviewPg.verifyAwardedLotteryAppExist(lotteryOrdWithAcceptDealine, false);
		overviewPg.verifyAwardedLotteryAppExist(lotteryOrderNum, false);
		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SK";
		
		//customer info
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4208";
		cus.identifier.state = "Ontario";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.residencyStatus = "Saskatchewan Resident";

		//hunt info
		hunt.setHuntCode("HAA");

		//lottery info
		lottery.setCode("AL1");
		lottery.setDescription("AwardedLottery1");
		lottery.setPurchasingName(lottery.getCode() + "-" + lottery.getDescription());
		lottery.setLicenseYear(lm.getFiscalYear(schema)); 
		lottery.setApplicantType("Individual");
		lottery.setHuntCode(hunt.getHuntCode());
		lottery.setHuntCodes(Arrays.asList(hunt.getHuntCode()));
		lottery.setSpecies(new String[] {"Black Bear"});
		lottery.setStatus(AWARDED_STATUS);
		
		//lottery license year info
		licenseYear.status = OrmsConstants.ACTIVE_STATUS;
		licenseYear.locationClass = "All";
		licenseYear.licYear = lottery.getLicenseYear();
		licenseYear.sellFromDate = DateFunctions.getToday(timeZone);
		licenseYear.sellFromAmPm = DateFunctions.getCurrentAMPM(timeZone);
		licenseYear.sellToDate = DateFunctions.getDateAfterToday(30);
		licenseYear.sellToTime = "11:59";
		licenseYear.sellToAmPm = "PM";

		//lottery schedule info
		schedule.setExecutionConfig("AwardedLotteryConfig1");
		schedule.setLicenseYear(lottery.getLicenseYear()); 
		schedule.setDescription("AwardedLotteryProcessing1");
		schedule.setLottery(lottery.getDescription());
		schedule.setCalculateAgeMethod(PrivilegeLotteryScheduleInfo.CALCULATE_AGE_METHOD_SUBMISSON_DATE);
		this.setupScheduleAwardedDeadline(1);
	}

	private void setupScheduleAwardedDeadline(int waitMin) {
		if (waitMin == 0) {
			schedule.setAwardAcceptanceByDate(StringUtil.EMPTY);
			schedule.setAwardAcceptanceByTime(StringUtil.EMPTY);
			schedule.setAwardAcceptanceByAmPm(StringUtil.EMPTY);
		} else {
			String dateFormat = "yyyy/MM/dd hh:mm aa";
			String curTime = DateFunctions.getToday(dateFormat);
			String time = DateFunctions.addTimeMinutes(curTime, waitMin, dateFormat, dateFormat);
			String[] times = time.split(" ");
			schedule.setAwardAcceptanceByDate(times[0]);
			schedule.setAwardAcceptanceByTime(times[1]);
			schedule.setAwardAcceptanceByAmPm("AM");
		}
	}

	private void gotoLotteryAppPgByClickAwardedAppSectionTitle() {
		logger.info("Go to lottery application page from account overview page.");
		accountOverviewPg.clickAwardedLotteryAppTitle();
		lotteryAppPg.waitLoading();
	}
}
