package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.pricing;

import java.util.Arrays;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ReservationInfo.ReservationHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrApplicationOrderHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryLicenseYearsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify refund issue method for lottery execution when lottery is used
 * @Precondition: The holding fee must be set for the lottery product, and the amount of holding fee must be greater than the privilege product, system
 * will use the holding fee to pay for the privilege product, and the case need a refund.
 *  note#:There is not license year set up for lottery product for the case will add a new license year for lottery
 * HF HQ Role - Auto LicenseManager ViewPrivilegeLotteryOrderHistory
 * @LinkSetUp:
 * 			  d_assign_feature:id=5692
 *			  d_hf_add_privilege_prd:id=2650 --PFL,Product For Lottery
 * 			  d_hf_add_pricing:id=3892 --Privilege
 * 			  d_hf_assi_pri_to_store:id=1870
 * 			  d_hf_add_prvi_license_year:id=2770
 * 			  d_hf_add_qty_control:id=1850
 * 			  d_hf_add_hunt_quota:id=280
 * 			  d_hf_add_hunt_location:id=10
 * 			  d_hf_add_weapon:id=10
 *			  d_hf_add_hunt:id=850,980 --HFO1,HF02
 * 			  d_hf_assign_priv_to_hunt:id=550,680
 * 			  d_hf_add_lottery_execution_config:id=140(DESCRIPTION='DrawLotteryForOrder')
 * 			
 * 			  d_hf_add_lottery_prd:id=430(CODE='P09') 
 * 			  d_hf_add_pricing:id=4122 --lottery
 * 			  d_hf_add_lottery_quantity_control:id=330
 *            d_hf_add_lottery_schedule:id=240 
 *            d_hf_assign_lottery_to_store:id=390
 *            d_hf_assi_hunts_to_lottery:id=400,560
 * 			  d_hf_add_hunt_license_year:id=980,1080
 * @SPEC:[TC:056841] Change Refund Issue Method - during Lottery Execution 
 * @Task#: Auto-2056
 * @author Phoebe Chen
 * @Date  March 14, 2014
 */
public class VerifyRefundIssueMethod_Used extends LicenseManagerTestCase{
	private LicenseYear licenseYear = new LicenseYear();
	private PrivilegeLotteryScheduleInfo schedule = new PrivilegeLotteryScheduleInfo();
	private LicMgrLotteryLicenseYearsPage licenseYearPg = LicMgrLotteryLicenseYearsPage.getInstance();
	private String salesLocation, adminLocation;
	private TimeZone timeZone;
	private HFLotteryProduct lottery = new HFLotteryProduct();
	private HuntInfo hunt = new HuntInfo();
	private QuotaInfo quota = new QuotaInfo();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//Prepare a schedule with complete status
		String ordNum = this.prepareRewardedOrder();
		this.acceptLotteryToOrderCartPg(ordNum);
		
		//Go to application history page to verify the refund information
		lm.gotoApplicationOrderDetailsPageFromTopMenu(ordNum);
		lm.gotoApplicationOrderHistoryPgFromDetailPg();
		this.verifyRefundInfoInHistoryPg();
		lm.goBackToApplicationOrderPgFromHistoryPg();
		
		lm.logOutLicenseManager();
	}
	
	private void verifyRefundInfoInHistoryPg() {
		LicMgrApplicationOrderHistoryPage historyPage = LicMgrApplicationOrderHistoryPage.getInstance();
		ReservationHistory historyInfo = historyPage.getOrderHistoryByTransaction("Transfer Payment Out");
		if(!historyInfo.info.matches(".*PER CHQ refund has been transferred as payment.")){
			throw new ErrorOnPageException("The refund issue method is not correct!");
		}
		logger.info("The refund issue method is correct!");
	}
	
	private void acceptLotteryToOrderCartPg(String ordNum) {
		lm.gotoHomePage();
		lm.switchLocationInHomePage(salesLocation);
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		lm.acceptAwardLotteryFromAddItemPgToCustDetailPg(cust.residencyStatus, null, ordNum);
		lm.goToCart();
		lm.processOrderCart(pay);
	}
	

	private String prepareRewardedOrder() {
		lm.gotoLotteriesProductListPgFromTopMenu();
		//* add or update a valid license year to purchase for this Lottery Product
		lm.gotoLotteryProductDetailsPageFromProductListPage(lottery.getCode());
		lm.gotoLotteryProductLicenseYearsPage();
		
		licenseYear.id = licenseYearPg.getLicenseYearId(licenseYear.status, licenseYear.locationClass, licenseYear.licYear);
		if(StringUtil.isEmpty(licenseYear.id)){
			licenseYear.id = lm.addLotteryLicenseYear(licenseYear);
		}//Update to a proper from date and to date so the lottery can be bought
		licenseYear.sellFromDate = DateFunctions.getDateAfterToday(-2);
		licenseYear.id = lm.updatePrivilegeLotteryLicenseYear(licenseYear);
	
		//deactivate previous Processing schedule, and add a new one
		lm.gotoLotterySchedulePage();
		lm.deactivateLotterySchedule(schedule.getLicenseYear(), schedule.getDescription());
		String id = lm.addLotterySchedule(schedule);
		if(!id.matches("\\d+")){
			throw new ErrorOnPageException("The schedule is not added correctly!");
		}
		
		//* make a lottery
		lm.switchLocationInHomePage(salesLocation);
		lm.makePrivilegeLotteryToCartFromCustomerQuickSearch(cust, lottery);
		String ordNum = lm.processOrderCart(pay);

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
		return ordNum;
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facility = lm.getFacilityName("1", schema);//Mississippi Department of Wildlife, Fisheries, and Parks
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/" + facility;
		
		adminLocation = "HF Administrator - Auto-" + facility;
		salesLocation = "HF HQ Role - Auto-WAL-MART";
		
		cust.lName = "Test-ViewPriDetail";
		cust.fName ="QA-ViewPriDetail";
		cust.identifier.identifierType = "MDWFP #";//"Green Card";
		cust.identifier.identifierNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);;
		cust.identifier.country = "Canada";
		cust.customerClass = "Individual";
		cust.residencyStatus="Non Resident";
		cust.phoneContact = "4088144589";
		cust.email = "jas@sina.com";
		//hunt info
		hunt.setHuntCode("HFO2");

		//quota info
		quota.setLicenseYear(lm.getFiscalYear(schema));
		quota.setQuotaStatus(OrmsConstants.ACTIVE_STATUS);
		quota.setDescription("Lottery App Quota");

		//lottery info
		lottery.setCode("P09");
		lottery.setDescription("Lottery prodcut for pricing 09");
		lottery.setPurchasingName(lottery.getCode() + "-" + lottery.getDescription());
		lottery.setLicenseYear(String.valueOf(DateFunctions.getCurrentYear())); //lm.getFiscalYear(schema));
		lottery.setHuntCodes(Arrays.asList(hunt.getHuntCode()));
		lottery.setApplicantType("Individual");
		
		//lottery license year info
		licenseYear.status = OrmsConstants.ACTIVE_STATUS;
		licenseYear.locationClass = "All";
		licenseYear.licYear = String.valueOf(DateFunctions.getCurrentYear()); //lm.getFiscalYear(schema);
		licenseYear.sellFromDate = DateFunctions.getDateAfterToday(1);
		licenseYear.sellFromAmPm = DateFunctions.getCurrentAMPM(timeZone);
		licenseYear.sellToDate = DateFunctions.getDateAfterToday(30);
		licenseYear.sellToTime = "11:59";
		licenseYear.sellToAmPm = "PM";
		
		//lottery schedule info
		schedule.setExecutionConfig("DrawLotteryForOrder");
		schedule.setLicenseYear("2014"); //lm.getFiscalYear(schema));
		schedule.setDescription("PrdSchedule09");
		schedule.setLottery(lottery.getDescription());
		schedule.setCalculateAgeMethod(PrivilegeLotteryScheduleInfo.CALCULATE_AGE_METHOD_SUBMISSON_DATE);

		//Privilege
		privilege.code = "PFL";
		privilege.name = "PrivilegeForLottery";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
	}
	
	private void initializeLotteryLicenseYearSellToDateTime() {
		licenseYear.sellToDate = licenseYear.sellFromDate;
		licenseYear.sellToTime = licenseYear.sellFromTime;
		licenseYear.sellToAmPm = licenseYear.sellFromAmPm;
	}

}
