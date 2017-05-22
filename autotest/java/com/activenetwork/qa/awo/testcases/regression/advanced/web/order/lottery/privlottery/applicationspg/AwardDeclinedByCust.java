package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.privlottery.applicationspg;

import java.util.Arrays;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryLicenseYearsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFLotteryApplicationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) In HFSK, check lottery application status and decline award page message during decline, cancel decline Award lottery application
 * @Preconditions:
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1320 --RCMP #|1R9Y4x4187
 * d_hf_add_privilege_prd:id=2640 --LAD,Lottery App Pri 04
 * d_hf_add_pricing:id=3772,3792 --Priv, lottery
 * d_hf_assi_pri_to_store:id=1860
 * d_hf_add_prvi_license_year:id=2750
 * d_hf_add_qty_control:id=1840
 * d_hf_add_hunt:id=600 --LAH5 
 * d_hf_add_hunt_license_year:id=480
 * d_hf_assign_priv_to_hunt:id=490
 * d_hf_add_hunt_quota:id=190
 * d_hf_add_hunt_location:id=70
 * d_hf_add_weapon:id=50
 * d_hf_add_lottery_prd:id=170 --WL4
 * d_hf_add_lottery_license_year:id=100
 * d_hf_add_lottery_quantity_control:id=100
 * d_hf_assi_hunts_to_lottery:id=60
 * d_hf_add_lottery_execution_config:id=70 --WebLottery04Config
 * d_hf_add_lottery_schedule:id=50 --WebLottery04Processing
 * d_assign_feature :id=5232
 * @SPEC:Lottery Applications - Decline Award [TC:058299] 
 * @Task#: Auto-1725
 * 
 * @author SWang
 * @Date  Feb 14, 2014
 */
public class AwardDeclinedByCust extends HFSKWebTestCase {
	private HFLotteryApplicationPage lotteryAppPg = HFLotteryApplicationPage.getInstance();
	private HFLotteryProduct lottery = new HFLotteryProduct();
	private HuntInfo hunt = new HuntInfo();
	private QuotaInfo quota = new QuotaInfo();
	private LicenseYear licenseYear = new LicenseYear();
	private PrivilegeLotteryScheduleInfo schedule = new PrivilegeLotteryScheduleInfo();
	private String salesLocation, adminLocation, orderNum, declineAwardPgTitle;
	private LoginInfo login = new LoginInfo();
	private TimeZone timeZone;

	@Override
	public void execute() {
		//Precondition: Make Award Lottery application
		prepareAwardLotteryAppInLM();

		//Go to HFSK lottery application page to check
		hf.invokeURL(url);
		hf.lookupAccount(cus);
		hf.gotoLotteryAppPgFromYourAccountFoundPg();

		//Check lottery application status and decline award page title during cancel to decline award
		cancelToDeclineAward();

		//Check lottery application status after successfully decline award
		hf.declineAward(orderNum, cus.residencyStatus);
		lotteryAppPg.verifyLotteryAppStatus(orderNum, AWARDEDDECLINEDBYCUSTOMER_STATUS);

		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//customer info
		cus.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cus.identifier.identifierType = hf.getIdenTypeName(schema, OrmsConstants.IDEN_RCMP_ID, true, false);
		cus.identifier.identifierNum = "1R9Y4x4187";
		cus.identifier.state = "Saskatchewan";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.residencyStatus = "Saskatchewan Resident - RCMP #";

		//login info 
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = "SK Contract";
		login.location = "SK Admin - Auto/SASK";
		salesLocation = "SK - Compliance Field Offices - Auto-Ministry of Environment - Big River(Store Loc)-Ministry of Environment - Big River(Station)";
		adminLocation = "SK Admin - Auto-SASK";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);

		//hunt info
		hunt.setHuntCode("LAH5");

		//quota info
		quota.setLicenseYear(lm.getFiscalYear(schema));
		quota.setQuotaStatus(OrmsConstants.ACTIVE_STATUS);
		quota.setDescription("Lottery App Quota");

		//lottery info
		lottery.setCode("WL4");
		lottery.setDescription("WebLottery04");
		lottery.setPurchasingName(lottery.getCode() + "-" + lottery.getDescription());
		lottery.setLicenseYear("2014"); //lm.getFiscalYear(schema));
		lottery.setHuntCodes(Arrays.asList(hunt.getHuntCode()));
		lottery.setApplicantType("Individual");

		//lottery license year info
		licenseYear.status = OrmsConstants.ACTIVE_STATUS;
		licenseYear.locationClass = "All";
		licenseYear.licYear = "2014"; //lm.getFiscalYear(schema);
		licenseYear.sellFromDate = DateFunctions.getToday(timeZone);
		licenseYear.sellFromAmPm = DateFunctions.getCurrentAMPM(timeZone);
		licenseYear.sellToDate = DateFunctions.getDateAfterToday(30);
		licenseYear.sellToTime = "11:59";
		licenseYear.sellToAmPm = "PM";

		//lottery schedule info
		schedule.setExecutionConfig("WebLottery04Config");
		schedule.setLicenseYear("2014"); //lm.getFiscalYear(schema));
		schedule.setDescription("WebLottery04Processing");
		schedule.setLottery(lottery.getDescription());
		schedule.setCalculateAgeMethod(PrivilegeLotteryScheduleInfo.CALCULATE_AGE_METHOD_SUBMISSON_DATE);

		//Privilege
		privilege.name = "Lottery App Pri 05";
		privilege.licenseYear = "2014"; //lm.getFiscalYear(schema);
		
		declineAwardPgTitle = "Decline Award Are you sure about declining the awarded draw? This action is final and cannot be undone.";
	}

	private void initializeLotteryLicenseYearSellToDateTime() {
		licenseYear.sellToDate = licenseYear.sellFromDate;
		licenseYear.sellToTime = licenseYear.sellFromTime;
		licenseYear.sellToAmPm = licenseYear.sellFromAmPm;
	}

	private void prepareAwardLotteryAppInLM(){
		LicMgrLotteryLicenseYearsPage licenseYearPg = LicMgrLotteryLicenseYearsPage.getInstance();
		lm.loginLicenseManager(login);

		//* add or update a valid license year to purchase for this Lottery Product
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryProductDetailsPageFromProductListPage(lottery.getCode());
		lm.gotoLotteryProductLicenseYearsPage();
		licenseYear.id = licenseYearPg.getLicenseYearId(licenseYear.status, licenseYear.locationClass, licenseYear.licYear);
		licenseYear.sellFromTime = DateFunctions.getCurrentTimeFormated(false, 1, timeZone);
		if(StringUtil.isEmpty(licenseYear.id)){
			licenseYear.id = lm.addLotteryLicenseYear(licenseYear);
		}
		licenseYear.sellFromDate = DateFunctions.getDateAfterToday(-1, timeZone);
		licenseYear.id = lm.updatePrivilegeLotteryLicenseYear(licenseYear);

		//* deactivate previous Processing schedule, and add a new one
		lm.gotoLotterySchedulePage();
		lm.deactivateLotterySchedule(schedule.getLicenseYear(), schedule.getDescription());
		schedule.setId(lm.addLotterySchedule(schedule));

		//* make a lottery
		lm.switchLocationInHomePage(salesLocation);
		lm.makePrivilegeLotteryToCartFromCustomerQuickSearch(cus, lottery);
		orderNum = lm.processOrderCart(pay);

		//* goto Lottery details - License Year tab to update the Sell To Date before current time
		lm.switchLocationInHomePage(adminLocation);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryProductDetailsPageFromProductListPage(lottery.getCode());
		this.initializeLotteryLicenseYearSellToDateTime();
		lm.updatePrivilegeLotteryLicenseYear(licenseYear);

		//* process lottery
		lm.gotoLotterySchedulePage();
		lm.processLotterySchedule(schedule.getLicenseYear(), schedule.getDescription());
		lm.refreshProcessingResults();

		lm.logOutLicenseManager();
	}
	
	private void cancelToDeclineAward(){
		String declineAwardPgTitleFromUI = hf.gotoDeclindAwardPgFromLotteryAppPg(orderNum);
		hf.gotoLotteryAppPgFromDeclindAwardPg();

		boolean result = MiscFunctions.compareResult("Decline Award page title", declineAwardPgTitle, declineAwardPgTitleFromUI);
		result &= MiscFunctions.compareResult("Order status", AWARDED_STATUS, lotteryAppPg.getLotteryAppStatus(orderNum));
		if(result){
			logger.info("Successfully verify decline award page title and lottery application status during cancel to purchase license.");
		}else throw new ErrorOnPageException("Failed to verify decline award page title and lottery application status during cancel to purchase license.");
	}
}
