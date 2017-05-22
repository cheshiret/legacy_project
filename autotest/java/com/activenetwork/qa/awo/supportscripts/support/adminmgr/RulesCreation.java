package com.activenetwork.qa.awo.supportscripts.support.adminmgr;

import java.io.File;

import com.activenetwork.qa.awo.datacollection.legacy.RuleDataInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrRuleDetailPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrRuleListPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrSelectLocationPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description : Functional Test Script
 * 
 * @author QA
 */
public class RulesCreation extends SupportCase {
	/**
	 * Script Name : <b>RulesCreation</b> Generated : <b>Jan 18, 2010 1:46:34
	 * AM</b> Description : Functional Test Script Original Host : WinNT Version
	 * 5.1 Build 2600 (S)
	 * 
	 * @since 2010/01/18 - 2011/01/18 debug advanced regression test cases, use
	 *        this script
	 * @author dsui
	 */
	private AdmMgrHomePage admHmPg = AdmMgrHomePage.getInstance();
	private AdmMgrRuleListPage admRuleLstPg = AdmMgrRuleListPage.getInstance();
	private AdmMgrSelectLocationPage admLocPg = AdmMgrSelectLocationPage
			.getInstance();
	private AdmMgrRuleDetailPage admRuleDetailPg = AdmMgrRuleDetailPage
			.getInstance();

	private LoginInfo login = new LoginInfo();
	private AdminManager admgr = AdminManager.getInstance();
	private RuleDataInfo ruleData = new RuleDataInfo();
	private String contract, location;
	private boolean loggedin = false;
	/***************************** Rule Names *******************************/
	final static String ACCESS_TIME = "Access Time";
	final static String ACCESS_TYPE = "Access Type";
	final static String ASSOCIATE_ENTRANCE = "Associate Entrance";
	final static String AUTOMATIC_CANCELLATION = "Automatic Cancellation";
	final static String BLOCK_STAY = "Block Stay";
	final static String INVENTORY_HOLD_TIMEOUT = "Inventory Hold Timeout";
	final static String MAXIMUM_CONSECUTIVE_STAY = "Maximum Consecutive Stay";
	final static String MAXIMUM_NUMBER_OF_CONCURRENT_RESERVATIONS_FOR_SAME_CUSTOMER_PASS_NUMBER = "Maximum Number Of Concurrent Reservations For Same Customer Pass Number";
	final static String MAXIMUM_NUMBER_OF_CONCURRENT_RESERVATIONS = "Maximum Number Of Concurrent Reservations";
	final static String MAXIMUM_NUMBER_OF_ORDERS_PER_CALL = "Maximum Number Of Orders Per Call";
	final static String MAXIMUM_NUMBER_OF_ORDERS_WITHIN_A_BOOKING_PERIOD = "Maximum Number Of Orders Within a Booking Period";
	final static String MAXIMUM_NUMBER_OF_ORDERS_WITHIN_STAY_PERIOD = "Maximum Number Of Orders Within Stay Period";
	final static String MAXIMUM_NUMBER_OF_PERMITS_PER_PERIOD_NON_PROFIT_ORGANIZATION = "Maximum Number of Permits Per Period Non Profit Organization";
	final static String MAXIMUM_NUMBER_OF_RESERVATIONS_WITH_THE_SAME_START_DATE = "Maximum Number Of Reservations With The Same Start Date";
	final static String MAXIMUM_NUMBER_OF_STAYS_PER_PERIOD = "Maximum Number Of Stays Per Period";
	final static String MAXIMUM_STAY_PER_PERIOD = "Maximum Stay Per Period";
	final static String MAXIMUM_TIME_TO_RECEIVE_PAYMENT = "Maximum Time To Receive Payment";
	final static String MAXIMUM_TOTAL_STAY = "Maximum Total Stay";
	final static String MAXIMUM_WINDOW = "Maximum Window";
	final static String MINIMUM_STAY = "Minimum Stay";
	final static String MINIMUM_WINDOW = "Minimum Window";
	final static String PRODUCT_RESTRICTED_IN_USE = "Product Restricted in Use";
	final static String SPECIFIED_STAY_START = "Specified Stay Start";
	final static String STAY_BEYOND_MAXIMUM_WINDOW = "Stay Beyond Maximum Window";
	final static String TIME_RESTRICTION_BEFORE_CHANGE_OF_DATES_ALLOWED = "Time Restriction Before Change Of Dates Allowed";
	final static String TIME_TO_CLEAR = "Time To Clear";
	final static String TRANSACTION_RESTRICTION = "Transaction Restriction";
	final static String MAXIMUM_NUMBER_OF_ORDERS_TIMES_TICKETS_WITHIN_INVENTORY_PERIOD = "Maximum Number Of Orders-Times-Tickets Within Inventory Period";
	/***************************** Rule Names *******************************/

	/**
	 * specify the rule to be created, which is also the datapool name, for
	 * multiple values, seperated by comma(,), null for run all
	 * 
	 * All Scripts:
	 * 
	 * "Access Time," + "Access Type," + "Associate Entrance," +
	 * "Automatic Cancellation," + "Block Stay," + "Inventory Hold Timeout," +
	 * "Maximum Consecutive Stay," +
	 * "Maximum Number Of Concurrent Reservations For Same Customer Pass Number,"
	 * + "Maximum Number Of Concurrent Reservations," +
	 * "Maximum Number Of Orders Per Call," +
	 * "Maximum Number Of Orders Within a Booking Period," +
	 * "Maximum Number Of Orders Within Stay Period," +
	 * "Maximum Number of Permits Per Period Non Profit Organization," +
	 * "Maximum Number Of Reservations With The Same Start Date," +
	 * "Maximum Number Of Stays Per Period," + "Maximum Stay Per Period," +
	 * "Maximum Time To Receive Payment," + "Maximum Total Stay," +
	 * "Maximum Window," + "Minimum Stay," + "Minimum Window," +
	 * "Specified Stay Start," + "Stay Beyond Maximum Window," +
	 * "Time Restriction Before Change Of Dates Allowed," + "Time To Clear," +
	 * "Transaction Restriction";
	 * 
	 */

	private String ruleTypesToAdded = PRODUCT_RESTRICTED_IN_USE;// specific rule
																// type name
																// here

	public void wrapParameters(Object[] param) {
		startpoint = 0; // the start point in the datapool
		endpoint = 999; // the end point in the datapool

		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");

		if (ruleTypesToAdded != null) {
			constuctDpFileNameByGivenValue();
		} else {
			constructDpFileNameFromDir();
		}
		// dpFileName:supportScripts/adminMgr/RuleDataPool/Inventory Hold
		// Timeout

		logMsg = new String[6];
		logMsg[0] = "index";
		logMsg[1] = "location";
		logMsg[2] = "locationcategory";
		logMsg[3] = "contract";
		logMsg[4] = "ruletype";
		logMsg[5] = "result";
	}

	private void constuctDpFileNameByGivenValue() {
		String[] filesNeedToRun = ruleTypesToAdded.split(",");
		for (String file : filesNeedToRun) {
			if (StringUtil.notEmpty(file)) {
				if (dataSource == null) {
					dataSource = "";
				}

				dataSource += casePath + "/RuleDataPool/"
						+ file.replaceAll("[/]", " ").trim() + ",";
			}
		}
	}

	private void constructDpFileNameFromDir() {
		File datapoolDir = new File(this.getClass().getResource("RuleDataPool")
				.getFile());
		File[] children = datapoolDir.listFiles();
		String projectPath = AwoUtil.PROJECT_PATH;
		for (File f : children) {
			String path = f.getPath();
			if (path.endsWith(".datapool")) {
				path = path.replaceAll("\\\\", "/").replace(projectPath, "")
						.replaceAll("\\.datapool", "");
				if (dataSource == null) {
					dataSource = "";
				}
				dataSource += path + ",";
			}
		}

	}

	@Override
	protected void afterDatapoolIteration() {
		admgr.logoutAdminMgr();
		loggedin = false;
	}

	public void execute() {
		// Login admin manager
		if (!contract.toUpperCase().contains("CONTRACT")) {
			contract = contract.toUpperCase() + " Contract";
		}
		if (!login.contractAbbrev.equalsIgnoreCase(contract) || !loggedin) {
			login.contract = contract;
			login.location = location;
			login.contractAbbrev = login.contract;
			if (loggedin) {
				admgr.logoutAdminMgr();
				loggedin = false;
			}
			admgr.loginAdminMgr(login);
			loggedin = true;

			// Select contract rule
			admHmPg.selectContractRules();
			admRuleLstPg.waitLoading();
			admRuleLstPg.enterRuleDetail(getRuleType());

		}

		Object page = browser.waitExists(admLocPg, admRuleDetailPg);
		if (page instanceof AdmMgrRuleDetailPage) {
			admRuleDetailPg.clickSelectLocation();
		}

		// Select location
		admLocPg.searchLocation(ruleData.locationCategory, ruleData.location);
		admLocPg.clickSelectButton();
		admRuleDetailPg.waitLoading();

		// Add a new rule
		admRuleDetailPg.addNewRule(ruleData);
		admRuleDetailPg.waitLoading();

		// Set log info
		if (!admRuleDetailPg.isErrorMsgExist()) {
			logMsg[5] = "Successful";
		} else {
			logMsg[5] = "Page Error Msg=" + admRuleDetailPg.getErrorMsg();
		}

		// Go to search location page
		admgr.goToSelectLocationPg();
	}

	public void getNextData() {
		contract = dpIter.dpString("contract_abbr");
		location = dpIter.dpString("location_abbr");

		ruleData = new RuleDataInfo();
		ruleData.location = dpIter.dpString("LocationName");
		ruleData.locationCategory = dpIter.dpString("LocationCategory");

		// Common Field
		ruleData.status = dpIter.dpString("Active");
		String ruleType = getRuleType();
		if (!ruleType.equalsIgnoreCase("Time To Clear")) {
			ruleData.productCategory = dpIter.dpString("ProductCategory");
		}
		ruleData.ticketCategory = dpIter.dpString("TicketCategory");
		ruleData.productGroup = dpIter.dpString("ProductGroup");
		ruleData.loop = dpIter.dpString("AreaLoop");
		ruleData.product = dpIter.dpString("Product");
		ruleData.salesChannel = dpIter.dpString("SalesChannel");
		ruleData.customerType = dpIter.dpString("CustomerType");
		ruleData.season = dpIter.dpString("SeasonType");
		ruleData.customerPassType = dpIter.dpString("CustomerPassType");
		ruleData.outOfState = dpIter.dpString("OutOfState");
		ruleData.paymentType = dpIter.dpString("PaymentType");
		ruleData.customerMember = dpIter.dpString("CustomerMember");
		ruleData.associatedParty = dpIter.dpString("AssociatedParty");
		ruleData.comments = dpIter.dpString("Comments");
		// COMMON start/end/effective dates
		ruleData.startDate = DateFunctions.getDateAfterToday(-10);
		ruleData.effectiveDate = DateFunctions.getToday();
		ruleData.startDate = DateFunctions.getDateAfterToday(-10);
		ruleData.endDate = DateFunctions.getDateAfterToday(365);

		if (ruleType.equalsIgnoreCase("Access Type")) {
			if (null != dpIter.dpString("StartDate")
					&& dpIter.dpString("StartDate").length() > 0) {
				ruleData.startDate = dpIter.dpString("StartDate");
			}
			if (null != dpIter.dpString("EndDate")
					&& dpIter.dpString("EndDate").length() > 0) {
				ruleData.endDate = dpIter.dpString("EndDate");
			}
			if (null != dpIter.dpString("EffectiveDate")
					&& dpIter.dpString("EffectiveDate").length() > 0) {
				ruleData.effectiveDate = dpIter.dpString("EffectiveDate");
			}
		}

		// for maximum window rule
		if (ruleType.equalsIgnoreCase("Maximum Window")) {
			ruleData.endDate = DateFunctions.getDateAfterToday(730);
		}

		// for minimum stay rule
		if (ruleData.comments
				.equalsIgnoreCase("MinimumStay_BookSiteMinimumStayRequiredWhenStayIncludesStartDay_ON_1")
				|| ruleData.comments
						.equalsIgnoreCase("MinimumStay_BookSiteMinimumStayRequiredWhenStayIncludesStartDay_OFF_1")) {
			// TODO update the start date and end date of this rule manually
			ruleData.startDate = "06-01-"
					+ (DateFunctions.getCurrentYear() + 1);
			ruleData.endDate = "06-05-" + (DateFunctions.getCurrentYear() + 1);
		}
		if (ruleData.comments
				.equalsIgnoreCase("MinimumStay_BookSiteMinimumStayRequiredWhenStayIncludesStartDay_OFF_2")) {
			ruleData.startDate = "06-10-"
					+ (DateFunctions.getCurrentYear() + 1);
			ruleData.endDate = "06-20-" + (DateFunctions.getCurrentYear() + 1);
		}

		// Access Type
		if (ruleType.equalsIgnoreCase("Access Type")) {
			ruleData.accessType = dpIter.dpString("AccessType");
			ruleData.length = dpIter.dpString("Length");
			ruleData.unit = dpIter.dpString("Unit");
		}

		// Access Time
		if (ruleType.equalsIgnoreCase("Access Time")) {
			ruleData.opentime = dpIter.dpString("OpenTime");
			ruleData.dailyopentime = dpIter.dpString("DailyOpenTime");
			ruleData.monOpenTime = dpIter.dpString("MonOpenTime");
			ruleData.tueOpenTime = dpIter.dpString("TueOpenTime");
			ruleData.wedOpenTime = dpIter.dpString("WedOpenTime");
			ruleData.thuOpenTime = dpIter.dpString("ThuOpenTime");
			ruleData.friOpenTime = dpIter.dpString("FriOpenTime");
			ruleData.satOpenTime = dpIter.dpString("SatOpenTime");
			ruleData.sunOpenTime = dpIter.dpString("SunOpenTime");
			ruleData.closeTime = dpIter.dpString("CloseTime");
			ruleData.dailyCloseTime = dpIter.dpString("DailyCloseTime");
			ruleData.monCloseTime = dpIter.dpString("MonCloseTime");
			ruleData.tueCloseTime = dpIter.dpString("TueCloseTime");
			ruleData.wedCloseTime = dpIter.dpString("WedCloseTime");
			ruleData.thuCloseTime = dpIter.dpString("ThuCloseTime");
			ruleData.friCloseTime = dpIter.dpString("FriCloseTime");
			ruleData.satCloseTime = dpIter.dpString("SatCloseTime");
			ruleData.sunCloseTime = dpIter.dpString("SunCloseTime");
		}

		// Associate Entrance
		if (ruleType.equalsIgnoreCase("Associate Entrance")) {
			ruleData.associateEntrance = dpIter.dpString("AssociateEntrance");
		}

		// Automatic Cancellation
		if (ruleType.equalsIgnoreCase("Automatic Cancellation")) {
			ruleData.minimumPaymentDeadline = dpIter
					.dpString("MinimumPaymentDeadline");
			ruleData.minimumPaymentReminderDaysBeforeDeadline = dpIter
					.dpString("MinimumPaymentReminderDaysBeforeDeadline");
		}

		// Block Stay
		if (ruleType.equalsIgnoreCase("Block Stay")) {
			ruleData.transactionOccurrence = dpIter
					.dpString("TransactionOccurrence");
			ruleData.blockStay = dpIter.dpString("BlockStay");
			ruleData.holidayblockstay = dpIter.dpString("HolidayBlockStay");
			ruleData.allowAvailabilityException = dpIter
					.dpString("AllowavailAbilityException");
			ruleData.allowStayLengthException = dpIter
					.dpString("AllowStayLengthException");
		}

		// Maximum Number Of Concurrent Reservations for Same Customer Pass
		// Number
		if (ruleType
				.equalsIgnoreCase("Maximum Number Of Concurrent Reservations for Same Customer Pass Number")) {
			ruleData.maximumNumberOfConcurrentReservationsForSameCustomerPassNumber = dpIter
					.dpString("MaximumNumberOfConcurrentReservationsForSameCustomerPassNumber");
		}

		// MaximumNumberOfOrders-Times-TicketsWithinInventoryPeriodRule
		if (ruleType
				.equalsIgnoreCase("Maximum Number Of Orders-Times-Tickets Within Inventory Period")) {
			ruleData.maxOrders = dpIter.dpString("MaxOrders");
			ruleData.maxTimes = dpIter.dpString("MaxTimes");
			ruleData.maxTickets = dpIter.dpString("MaxTickets");
		}

		// Minimum Stay
		if (ruleType.equalsIgnoreCase("Minimum Stay")) {
			ruleData.transactionOccurrence = dpIter
					.dpString("TransactionOccurrence");
			ruleData.minimumStay = dpIter.dpString("Minimumstay");
			ruleData.minimumStayMon = dpIter.dpString("MinimumStayMon");
			ruleData.minimumStayTue = dpIter.dpString("MinimumStayTue");
			ruleData.minimumStayWed = dpIter.dpString("MinimumStayWed");
			ruleData.minimumStayThu = dpIter.dpString("MinimumStayThu");
			ruleData.minimumStayFri = dpIter.dpString("MinimumStayFri");
			ruleData.minimumStaySat = dpIter.dpString("MinimumStaySat");
			ruleData.minimumStaySun = dpIter.dpString("MinimumStaySun");
			ruleData.minimumStayHoliday = dpIter.dpString("MinimumStayHoliday");
			ruleData.unit = dpIter.dpString("Unit");
			ruleData.multiplesOnly = dpIter.dpString("MultiplesOnly");
			ruleData.minimumStayRequiredWhenStayIncludesStartDay = dpIter
					.dpString("MinimumStayRequiredWhenStayIncludesStartDay");
			ruleData.combineOverlappingSeasons = dpIter
					.dpString("MinimumStayCombineOverlappingSeasons");
		}

		// Minimum Window
		if (ruleType.equalsIgnoreCase("Minimum Window")) {
			ruleData.length = dpIter.dpString("Length");
			ruleData.unit = dpIter.dpString("Unit");
		}

		// Maximum Window
		if (ruleType.equalsIgnoreCase("Maximum Window")) {
			ruleData.maximumWindowType = dpIter.dpString("MaximumWindowType");
			ruleData.length = dpIter.dpString("Length");
			ruleData.unit = dpIter.dpString("Unit");
			ruleData.rollupEndOfMonthExtraDays = dpIter
					.dpString("RollupEndOfMonthExtraDays");
			ruleData.blockReleaseLength = dpIter.dpString("BlockReleaseLength");
			ruleData.blockReleaseUnit = dpIter.dpString("BlockReleaseUnit");
			ruleData.blockReleaseDayOfMonth = dpIter
					.dpString("BlockReleaseDayOfMonth");
			ruleData.blockReleaseDayOfWeek = dpIter
					.dpString("BlockReleaseDayOfWeek");
			ruleData.blockReleaseDayOfWeekWithinMonth = dpIter
					.dpString("BlockReleaseDayOfWeekWithinMonth");
		}

		if (ruleType.equalsIgnoreCase("Maximum Total Stay")) {
			ruleData.length = dpIter.dpString("Length");
			ruleData.unit = dpIter.dpString("Unit");
		}

		// Specified Stay Start
		if (ruleType.equalsIgnoreCase("Specified Stay Start")) {
			ruleData.startStayDayOfWeek = dpIter.dpString("StartStayDayOfWeek");
		}

		// Stay Beyond Maximum Window
		if (ruleType.equalsIgnoreCase("Stay Beyond Maximum Window")) {
			ruleData.length = dpIter.dpString("Length");
			ruleData.unit = dpIter.dpString("Unit");
		}

		// Time Restriction Before Change Of Dates Allowed
		if (ruleType
				.equalsIgnoreCase("Time Restriction Before Change Of Dates Allowed")) {
			ruleData.length = dpIter.dpString("Length");
			ruleData.unit = dpIter.dpString("Unit");
		}

		// Time To Clear
		if (ruleType.equalsIgnoreCase("Time To Clear")) {
			ruleData.length = dpIter.dpString("Length");
			ruleData.unit = dpIter.dpString("Unit");
		}

		// Transaction Restriction
		if (ruleType.equalsIgnoreCase("Transaction Restriction")) {
			ruleData.transaction = dpIter.dpString("Transaction");
			ruleData.transactionOccurrence = dpIter
					.dpString("TransactionOccurence");
			ruleData.transactionOccurrenceDays = dpIter
					.dpString("TransactionOccurenceDays");
			ruleData.transactionOccurrenceTime = dpIter
					.dpString("TransactionOccurenceTime");
		}

		// Maximum Time To Receive Payment
		if (ruleType.equalsIgnoreCase("Maximum Time To Receive Payment")) {
			ruleData.length = dpIter.dpString("Length");
			ruleData.unit = dpIter.dpString("Unit");
		}

		// Maximum Stay Per Period
		if (ruleType.equalsIgnoreCase("Maximum Stay Per Period")) {
			ruleData.maximumStayPerPeriod = dpIter
					.dpString("MaximumStayPerTimePeriodLength");
			ruleData.length = dpIter.dpString("Length");
			ruleData.unit = dpIter.dpString("Unit");
		}

		// Maximum Number of Permits Per Period/Non Profit Organization
		if (ruleType
				.equalsIgnoreCase("Maximum Number of Permits Per Period/Non Profit Organization")) {
			ruleData.maximumNumberOfOrdersPerPeriod = dpIter
					.dpString("MaximumNumberOfOrdersPerPeriod");
			ruleData.length = dpIter.dpString("Length");
			ruleData.unit = dpIter.dpString("Unit");
		}

		// Maximum Number Of Stays Per Period
		if (ruleType.equalsIgnoreCase("Maximum Number Of Stays Per Period")) {
			ruleData.maximumNumberOfOrdersPerPeriod = dpIter
					.dpString("MaximumNumberOfOrdersPerPeriod");
			ruleData.length = dpIter.dpString("Length");
			ruleData.unit = dpIter.dpString("Unit");
		}

		// Maximum Number Of Reservations With The Same Start Date
		if (ruleType
				.equalsIgnoreCase("Maximum Number Of Reservations With The Same Start Date")) {
			ruleData.maximumNumberOfReservationsWithSameStartDate = dpIter
					.dpString("MaximumNumberOfReservationsWithSameStartDate");
		}

		// Maximum Number Of Orders Within Stay Period
		if (ruleType
				.equalsIgnoreCase("Maximum Number Of Orders Within Stay Period")) {
			ruleData.maximumNumberOfOrdersWithinStayPeriod = dpIter
					.dpString("MaximumNumberOfOrdersWithinStayPeriod");
		}

		// Maximum Number Of Orders Within A Booking Period
		if (ruleType
				.equalsIgnoreCase("Maximum Number Of Orders Within a Booking Period")) {
			ruleData.maximumNumberOfOrdersWithinABookingPeriod = dpIter
					.dpString("MaximumNumberOfOrdersWithinABookingPeriod");
			ruleData.length = dpIter.dpString("Length");
			ruleData.unit = dpIter.dpString("Unit");
		}

		// Maximum Number Of Orders Per Call
		if (ruleType.equalsIgnoreCase("Maximum Number Of Orders Per Call")) {
			ruleData.maximumNumberOfOrdersPerCall = dpIter
					.dpString("MaximumNumberOfOrdersPerCall");
		}

		// MaximumNumberOfConcurrentReservations
		if (ruleType
				.equalsIgnoreCase("Maximum Number Of Concurrent Reservations")) {
			ruleData.maximumNumberOfConcurrentReservations = dpIter
					.dpString("MaximumNumberOfConcurrentReservations");
		}

		// Maximum Consecutive Stay
		if (ruleType.equalsIgnoreCase("Maximum Consecutive Stay")) {
			ruleData.length = dpIter.dpString("Length");
			ruleData.unit = dpIter.dpString("Unit");
			ruleData.minimumTimeAwayLength = dpIter
					.dpString("MinimumTimeAwayLength");
			ruleData.minimumTimeAwayUnit = dpIter
					.dpString("MinimumTimeAwayUnit");
		}

		// Inventory Hold Timeout
		if (ruleType.equalsIgnoreCase("Inventory Hold Timeout")) {
			ruleData.timeoutLen = dpIter.dpString("TimeoutLength");
		}

		// Product Restricted in Use
		if (ruleType.equalsIgnoreCase("Product Restricted in Use")) {
			ruleData.customerTypes = this
					.splitTextBySemicolon(ruleData.customerType);
			ruleData.customerType = null;

			ruleData.customerPassTypes = this
					.splitTextBySemicolon(ruleData.customerPassType);
			ruleData.customerPassType = null;
		}

		logMsg[0] = cursor + "";
		logMsg[1] = ruleData.location;
		logMsg[2] = ruleData.locationCategory;
		logMsg[3] = contract;
		logMsg[4] = ruleType;
		logMsg[5] = "Failed due to error";
	}

	private String getRuleType() {
		String ruleType = dpIter.getDatapool().getName();

		if (ruleType
				.contains("Maximum Number of Permits Per Period Non Profit Organization")) {
			ruleType = ruleType
					.replaceAll(
							"Maximum Number of Permits Per Period Non Profit Organization",
							"Maximum Number of Permits Per Period/Non Profit Organization");
		}
		return ruleType;
	}

	private String[] splitTextBySemicolon(String text) {
		String[] list;
		if (text.contains(";")) {
			list = text.split(";", 0);
		} else {
			list = new String[] { text };
		}
		return list;
	}
}
