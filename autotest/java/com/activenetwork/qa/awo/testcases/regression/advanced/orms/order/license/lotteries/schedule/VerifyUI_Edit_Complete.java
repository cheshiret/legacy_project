package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.schedule;

import java.util.Arrays;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryLicenseYearsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrProcessingDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This case is used to verify the permission for view, create/edit lottery schedule
 * @LinkSetUp:
 *			  d_hf_add_privilege_prd:id=2640 --PFL,Product For Lottery
 * 			  d_hf_add_pricing:id=3892,3902 --Priv, lottery 
 * 			  d_hf_assi_pri_to_store:id=1870
 * 			  d_hf_add_prvi_license_year:id=2770
 * 			  d_hf_add_qty_control:id=1850
 * 			  d_hf_add_hunt_quota:id=280
 * 			  d_hf_add_hunt_location:id=10
 * 			  d_hf_add_weapon:id=10
 *			  d_hf_add_hunt:id=850 --HFO1  
 * 			  d_hf_add_hunt_license_year:id=730
 * 			  d_hf_assign_priv_to_hunt:id=550
 * 			  d_hf_add_lottery_prd:id=590(CODE='LPC') 
 * 			  d_hf_assi_hunts_to_lottery:id=660
 * 			  d_hf_add_lottery_license_year:id=470
 * 			  d_hf_add_lottery_quantity_control:id=470
 * 			  d_hf_assign_lottery_to_store:id=460
 *            d_hf_add_lottery_execution_config:id=140(DESCRIPTION='DrawLotteryForOrder')
 *            d_hf_add_lottery_schedule:id=360
 * @SPEC:[TC:044738] Edit Priviledge Lottery Schedule  
 * @Task#: Auto-2064
 * @author Phoebe Chen
 * @Date  March 03, 2014
 */
public class VerifyUI_Edit_Complete  extends LicenseManagerTestCase{
	private LicenseYear licenseYear = new LicenseYear();
	private PrivilegeLotteryScheduleInfo schedule = new PrivilegeLotteryScheduleInfo();
	private LicMgrLotteryLicenseYearsPage licenseYearPg = LicMgrLotteryLicenseYearsPage.getInstance();
	private LicMgrProcessingDetailsPage detailsPage = LicMgrProcessingDetailsPage.getInstance();
	private String salesLocation, adminLocation;
	private TimeZone timeZone;
	private HFLotteryProduct lottery = new HFLotteryProduct();
	private HuntInfo hunt = new HuntInfo();
	private QuotaInfo quota = new QuotaInfo();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//Prepare a schedule with complete status
		schedule.setId(this.prepareCompleteLotterySchedule());
		
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotterySchedulePage();
		
		lm.gotoLotteryScheduleDetailspage(schedule.getDescription());
		
		this.verifyEditableStatusForItem();
		
		lm.logOutLicenseManager();
	}
	
	private void verifyEditableStatusForItem() {
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Status:", true, detailsPage.isStatusEditable());
		passed &= MiscFunctions.compareResult("License Year:", false, detailsPage.isLicenseYearEditable());
		passed &= MiscFunctions.compareResult("Description:", false, detailsPage.isDescriptionEditable());
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

	private String prepareCompleteLotterySchedule() {
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
		lm.processOrderCart(pay);

		//* goto Lottery details - License Year tab to update the Sell To Date before current time
		lm.switchLocationInHomePage(adminLocation);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryProductDetailsPageFromProductListPage(lottery.getCode());
		this.initializeLotteryLicenseYearSellToDateTime();
		lm.updatePrivilegeLotteryLicenseYear(licenseYear);

		//* process lottery so that lottery will be complete status
		lm.gotoLotterySchedulePage();
		lm.processLotterySchedule(schedule.getLicenseYear(), schedule.getDescription());
		lm.refreshProcessingResults();
		return id;
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
		hunt.setHuntCode("HFO1");

		//quota info
		quota.setLicenseYear(lm.getFiscalYear(schema));
		quota.setQuotaStatus(OrmsConstants.ACTIVE_STATUS);
		quota.setDescription("Lottery App Quota");

		//lottery info
		lottery.setCode("LPO");
		lottery.setDescription("Lottery Product For Order");
		lottery.setPurchasingName(lottery.getCode() + "-" + lottery.getDescription());
		lottery.setLicenseYear(String.valueOf(DateFunctions.getCurrentYear())); //lm.getFiscalYear(schema));
		lottery.setHuntCodes(Arrays.asList(hunt.getHuntCode()));
		lottery.setApplicantType("Individual");
		
		//lottery license year info
		licenseYear.status = OrmsConstants.ACTIVE_STATUS;
		licenseYear.locationClass = "All";
		licenseYear.licYear = String.valueOf(DateFunctions.getCurrentYear()); //lm.getFiscalYear(schema);
		licenseYear.sellFromDate = DateFunctions.getDateAfterToday(-2);
		licenseYear.sellFromAmPm = DateFunctions.getCurrentAMPM(timeZone);
		licenseYear.sellToDate = DateFunctions.getDateAfterToday(30);
		licenseYear.sellToTime = "11:59";
		licenseYear.sellToAmPm = "PM";
		
		//lottery schedule info
		schedule.setExecutionConfig("DrawLotteryForOrder");
		schedule.setLicenseYear("2014"); //lm.getFiscalYear(schema));
		schedule.setDescription("For edit award product");
		schedule.setLottery(lottery.getDescription());
		schedule.setCalculateAgeMethod(PrivilegeLotteryScheduleInfo.CALCULATE_AGE_METHOD_SUBMISSON_DATE);

		//Privilege
		privilege.name = "Lottery App Pri 04";
		privilege.licenseYear = "2014"; //lm.getFiscalYear(schema);
		
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
	}
	
	private void initializeLotteryLicenseYearSellToDateTime() {
		licenseYear.sellToDate = licenseYear.sellFromDate;
		licenseYear.sellToTime = licenseYear.sellFromTime;
		licenseYear.sellToAmPm = licenseYear.sellFromAmPm;
	}

}
