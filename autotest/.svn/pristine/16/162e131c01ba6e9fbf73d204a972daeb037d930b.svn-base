package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.sales.inventory.draw;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeLotteryScheduleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.QuotaInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the Draw lottery quota status after processing order cart shall be Booked
 * 						i_hunt_inv, i_hunt_inv_used
 * @Preconditions: 1. privilege lottery product - 'RDQ-ReserveDrawLotteryQuota'
 * 							2. hunt - 'RDQ-ReserveDrawLotteryQuotaHunt'
 * 							3. quota - 'RDQ-ReserveDrawLotteryQuota'
 * 							4. execution config - 'ReserveDrawLotteryQuotaConfig'
 * 							5. processing associated with above execution config - 'ReserveDrawLotteryQuotaProcessing'
 * 							6. permit - 'PFL-PrivilegeForLottery'
 * @SPEC: Lottery Inventory/Quota--Reserve Held Privilege Lottery Quota [TC:052699]
 * @Task#: AUTO-1827
 * 
 * @author qchen
 * @Date  Aug 29, 2013
 */
public class ReserveQuota extends LicenseManagerTestCase {

	private HFLotteryProduct lottery = new HFLotteryProduct();
	private HuntInfo hunt = new HuntInfo();
	private QuotaInfo quota = new QuotaInfo();
	private LicenseYear licenseYear = new LicenseYear();
	private PrivilegeLotteryScheduleInfo schedule = new PrivilegeLotteryScheduleInfo();
	private String salesLocation, adminLocation;
	private TimeZone timeZone;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//1. add or update a valid license year to purchase for this Lottery Product
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryProductDetailsPageFromProductListPage(lottery.getCode());
		lm.gotoLotteryProductLicenseYearsPage();
		licenseYear.id = lm.addLotteryLicenseYear(licenseYear);
		
		//2. deactivate previous Processing schedule, and add a new one
		lm.gotoLotterySchedulePage();
		lm.deactivateLotterySchedule(schedule.getLicenseYear(), schedule.getDescription());
		schedule.setId(lm.addLotterySchedule(schedule));
		
		lm.switchLocationInHomePage(salesLocation);
		String hunt_license_year_quota_id = lm.getHuntLicenseYearQuotaID(schema, lottery.getCode(), hunt.getHuntCode(), quota.getDescription(), quota.getLicenseYear());
		int quantities[] = lm.getHuntInventoryQuantities(schema, hunt_license_year_quota_id);
		//2. make a lottery item into order cart
		lm.makePrivilegeLotteryToCartFromCustomerQuickSearch(cust, lottery);
		
		//3. process order cart, at this time, the i_hunt_inv.AVAILABLE won't be changed and i_hunt_inv_used won't be inserted a new record
		lm.processOrderCart(pay);
		
		lm.switchLocationInHomePage(adminLocation);
		//4. goto Lottery details - License Year tab to update the Sell To Date before current time
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryProductDetailsPageFromProductListPage(lottery.getCode());
		this.initializeLotteryLicenseYearSellToDateTime();
		lm.updatePrivilegeLotteryLicenseYear(licenseYear);
		
		//5. process lottery
		lm.gotoLotterySchedulePage();
		lm.processLotterySchedule(schedule.getLicenseYear(), schedule.getDescription());
		
		//6. verify the available hunt quantity and used inventory status
		lm.verifyHuntInventoryAvailableQuantity(schema, hunt_license_year_quota_id, quantities[1] - 1);
		lm.verifyHuntInventoryStatus(schema, hunt_license_year_quota_id, OrmsConstants.INV_USED_STATUS_BOOKED);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
		salesLocation = "HF HQ Role - Auto-WAL-MART";
		adminLocation = "HF Administrator - Auto-Mississippi Department of Wildlife, Fisheries, and Parks";
		
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		//hunt info
		hunt.setHuntCode("RDQ");
	
		//quota info
		quota.setLicenseYear(lm.getFiscalYear(schema));
		quota.setQuotaStatus(OrmsConstants.ACTIVE_STATUS);
		quota.setDescription("ReserveDrawLotteryQuota");
		
		//lottery info
		lottery.setCode("RDQ");
		lottery.setDescription("ReserveDrawLotteryQuota");
		lottery.setPurchasingName(lottery.getCode() + "-" + lottery.getDescription());
		lottery.setLicenseYear(lm.getFiscalYear(schema));
		List<String> huntCodes = new ArrayList<String>();
		huntCodes.add("RDQ");
		lottery.setHuntCodes(huntCodes);
		
		//lottery license year info
		licenseYear.status = OrmsConstants.ACTIVE_STATUS;
		licenseYear.locationClass = "06 - State Parks Agent";
		licenseYear.licYear = lm.getFiscalYear(schema);
		licenseYear.sellFromDate = DateFunctions.getToday(timeZone);
		licenseYear.sellFromTime = DateFunctions.getCurrentTimeFormated(false, timeZone);
		licenseYear.sellFromAmPm = DateFunctions.getCurrentAMPM(timeZone);
		licenseYear.sellToDate = DateFunctions.getDateAfterToday(30);
		licenseYear.sellToTime = "11:59";
		licenseYear.sellToAmPm = "PM";
		
		//lottery schedule info
		schedule.setExecutionConfig("ReserveDrawLotteryQuotaConfig");
		schedule.setLicenseYear(lm.getFiscalYear(schema));
		schedule.setDescription("ReserveDrawLotteryQuotaProcessing");
		schedule.setLottery(lottery.getDescription());
		schedule.setCalculateAgeMethod(PrivilegeLotteryScheduleInfo.CALCULATE_AGE_METHOD_SUBMISSON_DATE);
		
		//customer info
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "19770707";
		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "19770707";
		cust.residencyStatus = "Non Resident";
	}
	
	private void initializeLotteryLicenseYearSellToDateTime() {
		licenseYear.sellToDate = licenseYear.sellFromDate;
		licenseYear.sellToTime = licenseYear.sellFromTime;
		licenseYear.sellToAmPm = licenseYear.sellFromAmPm;
	}
}
