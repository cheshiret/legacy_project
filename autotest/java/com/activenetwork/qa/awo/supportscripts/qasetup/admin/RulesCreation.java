package com.activenetwork.qa.awo.supportscripts.qasetup.admin;



import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.RuleDataInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.admin.CreateRuleFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.TestDriverUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;


/**
 * Description : Functional Test Script
 * 
 * @author QA
 */
public class RulesCreation extends SetupCase {
	/**
	 * Script Name : <b>RulesCreation</b> Generated : <b>Jan 18, 2010 1:46:34
	 * AM</b> Description : Functional Test Script Original Host : WinNT Version
	 * 5.1 Build 2600 (S)
	 * 
	 * @since 2010/01/18 - 2011/01/18 debug advanced regression test cases, use
	 *        this script
	 * @author dsui
	 */
	private LoginInfo login = new LoginInfo();
	private CreateRuleFunction func = new CreateRuleFunction();
	private RuleDataInfo ruleData;

	/***************************** Rule Names *******************************/
	public final static String ACCESS_TIME = "Access Time";
	public final static String ACCESS_TYPE = "Access Type";
	public final static String ASSOCIATE_ENTRANCE = "Associate Entrance";
	public final static String BLOCK_STAY = "Block Stay";
	public final static String INVENTORY_HOLD_TIMEOUT = "Inventory Hold Timeout";
	public final static String MAXIMUM_CONSECUTIVE_STAY = "Maximum Consecutive Stay";
	public final static String MAXIMUM_NUMBER_OF_CONCURRENT_RESERVATIONS_FOR_SAME_CUSTOMER_PASS_NUMBER = "Maximum Number Of Concurrent Reservations For Same Customer Pass Number";
	public final static String MAXIMUM_NUMBER_OF_CONCURRENT_RESERVATIONS = "Maximum Number Of Concurrent Reservations";
//	public final static String MAXIMUM_NUMBER_OF_CONCURRENT_RESERVATIONS_PERMITS = "Maximum Number Of Concurrent Reservations (Permits)";
	public final static String MAXIMUM_NUMBER_OF_ORDERS_PER_CALL = "Maximum Number Of Orders Per Call";
	public final static String MAXIMUM_NUMBER_OF_ORDERS_WITHIN_A_BOOKING_PERIOD = "Maximum Number Of Orders Within a Booking Period";
	public final static String MAXIMUM_NUMBER_OF_ORDERS_WITHIN_STAY_PERIOD = "Maximum Number Of Orders Within Stay Period";
	public final static String MAXIMUM_NUMBER_OF_PERMITS_PER_PERIOD_NON_PROFIT_ORGANIZATION = "Maximum Number of Permits Per Period Non Profit Organization";
	public final static String MAXIMUM_NUMBER_OF_RESERVATIONS_WITH_THE_SAME_START_DATE = "Maximum Number Of Reservations With The Same Start Date";
	public final static String MAXIMUM_NUMBER_OF_STAYS_PER_PERIOD = "Maximum Number Of Stays Per Period";
	public final static String MAXIMUM_STAY_PER_PERIOD = "Maximum Stay Per Period";
	public final static String MAXIMUM_TIME_TO_RECEIVE_PAYMENT = "Maximum Time To Receive Payment";
	public final static String MAXIMUM_TOTAL_STAY = "Maximum Total Stay";
//	public final static String MAXIMUM_TOTAL_STAY_PERMITS = "Maximum Total Stay (Permits)";
	public final static String MAXIMUM_WINDOW = "Maximum Window";
	public final static String MINIMUM_STAY = "Minimum Stay";
//	public final static String MINIMUM_STAY_PERMITS = "Minimum Stay (Permits)";
	public final static String MINIMUM_WINDOW = "Minimum Window";
	public final static String PRODUCT_RESTRICTED_IN_USE = "Product Restricted in Use";
	public final static String SPECIFIED_STAY_START = "Specified Stay Start";
	public final static String STAY_BEYOND_MAXIMUM_WINDOW = "Stay Beyond Maximum Window";
	public final static String TIME_RESTRICTION_BEFORE_CHANGE_OF_DATES_ALLOWED = "Time Restriction Before Change Of Dates Allowed";
	public final static String TIME_TO_CLEAR = "Time To Clear";
	public final static String TRANSACTION_RESTRICTION = "Transaction Restriction";
	public final static String MAXIMUM_NUMBER_OF_ORDERS_TIMES_TICKETS_WITHIN_INVENTORY_PERIOD = "Maximum Number Of Orders-Times-Tickets Within Inventory Period";
	public final static String MAXIMUM_NUMBER_OF_ORDERS_TIMES_TICKETS_PRE_CALL_CART = "Maximum Number of Orders-Times-Tickets per call/cart";
	public final static String ISSUE_PERMIT_RESTRICTION = "Issue Permit Restriction";
	public final static String MAXIMUM_GROUP_SIZE = "Maximum Group Size";
	public final static String MINIMUM_GROUP_SIZE = "Minimum Group Size";
	public final static String MAXIMUM_STAY = "Maximum Stay";
	public final static String RESTRICT_ENTRANCE = "Restrict Entrance";
	
	public final static String[][] ruleTypeArray = {
		{ACCESS_TIME,"6"},
		{ACCESS_TYPE,"2"},
		{ASSOCIATE_ENTRANCE,"31"},
		{BLOCK_STAY,"30"},
		{INVENTORY_HOLD_TIMEOUT,"20"},
		{MAXIMUM_CONSECUTIVE_STAY,"18"},
		{MAXIMUM_NUMBER_OF_CONCURRENT_RESERVATIONS_FOR_SAME_CUSTOMER_PASS_NUMBER,"34"},
		{MAXIMUM_NUMBER_OF_CONCURRENT_RESERVATIONS,"12"},
//		{MAXIMUM_NUMBER_OF_CONCURRENT_RESERVATIONS_PERMITS,"12"},
		{MAXIMUM_NUMBER_OF_ORDERS_PER_CALL,"27"},
		{MAXIMUM_NUMBER_OF_ORDERS_WITHIN_A_BOOKING_PERIOD,"28"},
		{MAXIMUM_NUMBER_OF_ORDERS_WITHIN_STAY_PERIOD,"29"},
		{MAXIMUM_NUMBER_OF_PERMITS_PER_PERIOD_NON_PROFIT_ORGANIZATION,"32"},
		{MAXIMUM_NUMBER_OF_RESERVATIONS_WITH_THE_SAME_START_DATE,"13"},
		{MAXIMUM_NUMBER_OF_STAYS_PER_PERIOD,"10"},
		{MAXIMUM_STAY_PER_PERIOD,"16"},
		{MAXIMUM_TIME_TO_RECEIVE_PAYMENT,"9"},
		{MAXIMUM_TOTAL_STAY,"15"},
//		{MAXIMUM_TOTAL_STAY_PERMITS,"15"},
		{MAXIMUM_WINDOW,"1"},
		{MINIMUM_STAY,"7"},
//		{MINIMUM_STAY_PERMITS,"7"},
		{MINIMUM_WINDOW,"3"},
		{PRODUCT_RESTRICTED_IN_USE,"42"},
		{SPECIFIED_STAY_START,"8"},
		{STAY_BEYOND_MAXIMUM_WINDOW,"4"},
		{TIME_RESTRICTION_BEFORE_CHANGE_OF_DATES_ALLOWED,"5"},
		{TIME_TO_CLEAR,"25"},
		{TRANSACTION_RESTRICTION,"26"},
		{MAXIMUM_NUMBER_OF_ORDERS_TIMES_TICKETS_WITHIN_INVENTORY_PERIOD,"41"},
		{MAXIMUM_NUMBER_OF_ORDERS_TIMES_TICKETS_PRE_CALL_CART,"43"},
		{ISSUE_PERMIT_RESTRICTION,"40"},
		{MAXIMUM_GROUP_SIZE, "45"},
		{MINIMUM_GROUP_SIZE, "44"},
		{MAXIMUM_STAY, "49"},
		{RESTRICT_ENTRANCE, "36"}
		};
	//Issue Permit Restriction
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



	private String ruleTypesToAdded = RESTRICT_ENTRANCE;
	private String ruleID;

																// here
	public void wrapParameters(Object[] param) {
		
		//If we use sanity machine to run this script, we override the ruleTypesToAdded in order to consist with DB value.
		if(TestDriverUtil.getParameter(param, "cmdLine",	Boolean.toString(isCommandLine)).equalsIgnoreCase("true"))
		{
			ruleTypesToAdded=TestDriverUtil.getParameter(param, "notes", "");
			String[] idArray=TestProperty.getProperty("ids").split(",");
			logger.resetLogfileName(fullCaseName.replaceAll(caseName, "").replaceAll("supportscripts\\.", "").replaceAll("\\.", "\\\\")
					+ruleTypesToAdded + "_"+ OrmsConstants.TIMESTAMP + "("+idArray[0]+"-"+idArray[idArray.length-1]+")");
		}
		
		if(StringUtil.notEmpty(TestDriverUtil.getParameter(param, "ruleType"))){
			ruleTypesToAdded = TestDriverUtil.getParameter(param, "ruleType");
		}
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
		dataTableName = getTableNameByRuleName(ruleTypesToAdded);
		
		
		ids = "select ID from " + dataTableName + " where locationname = 'Boundary Waters Canoe Area Wilderness (Reservations)'";
		
		this.ruleID = this.getRuleID(ruleTypesToAdded);
	}

	
	private String getRuleID(String ruleName) {
		String id = null;
		for(String[] rule: ruleTypeArray)
		{
			if(rule[0].equalsIgnoreCase(ruleName))//compare with rule name.
			{
				id = rule[1];
				break;
			}
			
		}
		if(StringUtil.isEmpty(id))
		{
			throw new ErrorOnDataException("Cannot find rule ID for rule-->"+ruleName+", in String[][] ruleTypeArray.....");
		}
			
		return id;
	}

	private String getTableNameByRuleName(String ruleName){
		String tableName ="";
		if(ruleName.equals(ACCESS_TIME)){
			tableName = "d_rule_"+ACCESS_TIME.replaceAll(" ", "_").toLowerCase();
		}else if(ruleName.equals(ACCESS_TYPE)){
			tableName = "d_rule_"+ACCESS_TYPE.replaceAll(" ", "_").toLowerCase();
		}else if(ruleName.equals(ASSOCIATE_ENTRANCE)){
			tableName = "d_rule_"+ASSOCIATE_ENTRANCE.replaceAll(" ", "_").toLowerCase();
		}else if(ruleName.equals(BLOCK_STAY)){
			tableName = "d_rule_"+BLOCK_STAY.replaceAll(" ", "_").toLowerCase();
		}else if(ruleName.equals(INVENTORY_HOLD_TIMEOUT)){
			tableName = "d_rule_inv_hold_timeout";
		}else if(ruleName.equals(MAXIMUM_CONSECUTIVE_STAY)){
			tableName = "d_rule_max_consecutive_stay";
		}else if(ruleName.equals(MAXIMUM_NUMBER_OF_CONCURRENT_RESERVATIONS)){
			tableName = "d_rule_max_n_con_res";
		}/*else if(ruleName.equals(MAXIMUM_NUMBER_OF_CONCURRENT_RESERVATIONS_PERMITS)) {
			tableName = "d_rule_max_n_con_res_permit";
		}*/ else if(ruleName.equals(MAXIMUM_NUMBER_OF_CONCURRENT_RESERVATIONS_FOR_SAME_CUSTOMER_PASS_NUMBER)){
			tableName = "d_rule_max_n_cores_sm_cusps";
		}else if(ruleName.equals(MAXIMUM_NUMBER_OF_ORDERS_PER_CALL)){
			tableName = "d_rule_max_n_ords_per_call";
		}else if(ruleName.equals(MAXIMUM_NUMBER_OF_ORDERS_TIMES_TICKETS_WITHIN_INVENTORY_PERIOD)){
			tableName = "d_rule_max_n_ott_in_inv_prd";
		}else if(ruleName.equals(MAXIMUM_NUMBER_OF_ORDERS_WITHIN_A_BOOKING_PERIOD)){
			tableName = "d_rule_max_n_ord_in_bk_perd";
		}else if(ruleName.equals(MAXIMUM_NUMBER_OF_ORDERS_WITHIN_STAY_PERIOD)){
			tableName = "d_rule_max_n_ord_in_st_perd";
		}else if(ruleName.equals(MAXIMUM_NUMBER_OF_PERMITS_PER_PERIOD_NON_PROFIT_ORGANIZATION)){
			tableName = "d_rule_max_n_pmit_p_prd_org";
		}else if(ruleName.equals(MAXIMUM_NUMBER_OF_RESERVATIONS_WITH_THE_SAME_START_DATE)){
			tableName = "d_rule_max_n_res_sm_st_dat";
		}else if(ruleName.equals(MAXIMUM_NUMBER_OF_STAYS_PER_PERIOD)){
			tableName = "d_rule_max_n_sty_per_period";
		}else if(ruleName.equals(MAXIMUM_STAY_PER_PERIOD)){
			tableName = "d_rule_max_stay_per_period";
		}else if(ruleName.equals(MAXIMUM_TIME_TO_RECEIVE_PAYMENT)){
			tableName = "d_rule_max_t_to_recv_pmt";
		}else if(ruleName.equals(MAXIMUM_TOTAL_STAY)) {
			tableName = "d_rule_maximum_total_stay";
		} /*else if(ruleName.equals(MAXIMUM_TOTAL_STAY_PERMITS)) {
			tableName = "d_rule_max_total_stay_permit";
		}*//*else if(ruleName.equals(MINIMUM_STAY_PERMITS)){
			tableName = "d_rule_minimum_stay_permit";
		}*/else if(ruleName.equals(MAXIMUM_WINDOW)){
			tableName = "d_rule_maximum_window";
		}else if(ruleName.equals(MINIMUM_STAY)){
			tableName = "d_rule_minimum_stay";
		}else if(ruleName.equals(MINIMUM_WINDOW)){
			tableName = "d_rule_minimum_window";
		}else if(ruleName.equals(PRODUCT_RESTRICTED_IN_USE)){
			tableName = "d_rule_prod_restrict_in_use";
		}else if(ruleName.equals(SPECIFIED_STAY_START)){
			tableName = "d_rule_specified_stay_start";
		}else if(ruleName.equals(STAY_BEYOND_MAXIMUM_WINDOW)){
			tableName = "d_rule_stay_beyd_maxi_wind";
		}else if(ruleName.equals(TIME_RESTRICTION_BEFORE_CHANGE_OF_DATES_ALLOWED)){
			tableName = "d_rule_t_rest_bf_chg_dt_alo";
		}else if(ruleName.equals(TIME_TO_CLEAR)){
			tableName = "d_rule_time_to_clear";
		}else if(ruleName.equals(TRANSACTION_RESTRICTION)){
			tableName = "d_rule_transact_restrict";
		}else if(ruleName.equals(ISSUE_PERMIT_RESTRICTION)){
			tableName = "d_rule_isspermit_restriction";
		}else if(ruleName.equals(MAXIMUM_NUMBER_OF_ORDERS_TIMES_TICKETS_PRE_CALL_CART)){
			tableName = "d_rule_max_n_ott_per_call_car";
		}else if(ruleName.equals(MAXIMUM_GROUP_SIZE)){
			tableName = "d_rule_max_group_size";
		}else if(ruleName.equals(MINIMUM_GROUP_SIZE)){
			tableName = "d_rule_min_group_size";
		}else if(ruleName.equals(MAXIMUM_STAY)){
			tableName = "d_rule_max_stay";
		}else if(ruleName.equals(RESTRICT_ENTRANCE)){
			tableName = "d_rule_restrict_entrance";
		}
		return tableName;
	}
	


	public void executeSetup() {
		
		Object[] args = new Object[4];
		args[0] = login;
		args[1] = this.getRuleType();
		args[2] = ruleData;
		args[3] = this.ruleID;
		
		func.execute(args);
	}

	public void readDataFromDatabase() {
		
		this.ruleData = new RuleDataInfo();
		
		login.contract = datasFromDB.get("contract_abbr");
		
		if (!login.contract.toUpperCase().contains("CONTRACT")) {
			login.contract = login.contract.toUpperCase() + " Contract";
		}
		login.location = datasFromDB.get("location_abbr");

		
		ruleData = new RuleDataInfo();
		ruleData.location = datasFromDB.get("LocationName");
		ruleData.locationCategory = datasFromDB.get("LocationCategory");

		// Common Field
		ruleData.status = datasFromDB.get("Active");
		if (!ruleTypesToAdded.equalsIgnoreCase("Time To Clear")) {
			ruleData.productCategory = datasFromDB.get("ProductCategory");
		}
		ruleData.ticketCategory = datasFromDB.get("TicketCategory");
		ruleData.productGroup = datasFromDB.get("ProductGroup");
		ruleData.loop = datasFromDB.get("AreaLoop");
		ruleData.product = datasFromDB.get("Product");
		ruleData.salesChannel = datasFromDB.get("SalesChannel");
		ruleData.customerType = datasFromDB.get("CustomerType");
		if (!ruleTypesToAdded.equalsIgnoreCase("Maximum Number Of Orders-Times-Tickets Within Inventory Period")
				&&!ruleTypesToAdded.equalsIgnoreCase("Issue Permit Restriction")) {
		ruleData.season = datasFromDB.get("SeasonType");
		}		
		
		ruleData.customerPassType = datasFromDB.get("CustomerPassType");
		ruleData.outOfState = datasFromDB.get("OutOfState");
		ruleData.paymentType = datasFromDB.get("PaymentType");
		ruleData.customerMember = datasFromDB.get("CustomerMember");
		ruleData.associatedParty = datasFromDB.get("AssociatedParty");
		ruleData.comments = datasFromDB.get("Comments");
		// COMMON start/end/effective dates
		ruleData.effectiveDate = DateFunctions.getToday();
		ruleData.startDate = DateFunctions.getDateAfterToday(-10);
		ruleData.endDate = DateFunctions.getDateAfterToday(750);

		if (ruleTypesToAdded.equalsIgnoreCase("Access Type")) {
			if (null != datasFromDB.get("StartDate")
					&& datasFromDB.get("StartDate").length() > 0) {
				ruleData.startDate = datasFromDB.get("StartDate");
			}
			if (null != datasFromDB.get("EndDate")
					&& datasFromDB.get("EndDate").length() > 0) {
				ruleData.endDate = datasFromDB.get("EndDate");
			}
			if (null != datasFromDB.get("EffectiveDate")
					&& datasFromDB.get("EffectiveDate").length() > 0) {
				ruleData.effectiveDate = datasFromDB.get("EffectiveDate");
			}
		}

		// for maximum window rule
		if (ruleTypesToAdded.equalsIgnoreCase("Maximum Window")) {
			ruleData.endDate = DateFunctions.getDateAfterToday(730);
		}

		// for minimum stay rule
		if(ruleData.comments!=null){
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
		}

		// Access Type
		if (ruleTypesToAdded.equalsIgnoreCase("Access Type")) {
			ruleData.accessType = datasFromDB.get("AccessType");
			ruleData.length = datasFromDB.get("Length");
			ruleData.unit = datasFromDB.get("Unit");
			ruleData.marinaRateType = datasFromDB.get("MarinaRateType");
		}

		// Max total stay -- added by Nicole
		if (ruleTypesToAdded.equalsIgnoreCase(MAXIMUM_TOTAL_STAY)) {
			ruleData.marinaRateType = datasFromDB.get("MarinaRateType");
		}

		// Access Time
		if (ruleTypesToAdded.equalsIgnoreCase("Access Time")) {
			ruleData.opentime = datasFromDB.get("OpenTime");
			ruleData.dailyopentime = datasFromDB.get("DailyOpenTime");
			ruleData.monOpenTime = datasFromDB.get("MonOpenTime");
			ruleData.tueOpenTime = datasFromDB.get("TueOpenTime");
			ruleData.wedOpenTime = datasFromDB.get("WedOpenTime");
			ruleData.thuOpenTime = datasFromDB.get("ThuOpenTime");
			ruleData.friOpenTime = datasFromDB.get("FriOpenTime");
			ruleData.satOpenTime = datasFromDB.get("SatOpenTime");
			ruleData.sunOpenTime = datasFromDB.get("SunOpenTime");
			ruleData.closeTime = datasFromDB.get("CloseTime");
			ruleData.dailyCloseTime = datasFromDB.get("DailyCloseTime");
			ruleData.monCloseTime = datasFromDB.get("MonCloseTime");
			ruleData.tueCloseTime = datasFromDB.get("TueCloseTime");
			ruleData.wedCloseTime = datasFromDB.get("WedCloseTime");
			ruleData.thuCloseTime = datasFromDB.get("ThuCloseTime");
			ruleData.friCloseTime = datasFromDB.get("FriCloseTime");
			ruleData.satCloseTime = datasFromDB.get("SatCloseTime");
			ruleData.sunCloseTime = datasFromDB.get("SunCloseTime");
		}

		// Associate Entrance
		if (ruleTypesToAdded.equalsIgnoreCase("Associate Entrance")) {
			ruleData.associateEntrance = datasFromDB.get("AssociateEntrance");
		}

		// Automatic Cancellation
		if (ruleTypesToAdded.equalsIgnoreCase("Automatic Cancellation")) {
			ruleData.minimumPaymentDeadline = datasFromDB
					.get("MinimumPaymentDeadline");
			ruleData.minimumPaymentReminderDaysBeforeDeadline = datasFromDB
					.get("MinimumPaymentReminderDaysBeforeDeadline");
		}

		// Block Stay
		if (ruleTypesToAdded.equalsIgnoreCase("Block Stay")) {
			ruleData.transactionOccurrence = datasFromDB
					.get("TransactionOccurrence");
			ruleData.blockStay = datasFromDB.get("BlockStay");
			ruleData.holidayblockstay = datasFromDB.get("HolidayBlockStay");
			ruleData.allowAvailabilityException = datasFromDB
					.get("AllowavailAbilityException");
			ruleData.allowStayLengthException = datasFromDB
					.get("AllowStayLengthException");
		}

		// Maximum Number Of Concurrent Reservations for Same Customer Pass
		// Number
		if (ruleTypesToAdded
				.equalsIgnoreCase("Maximum Number Of Concurrent Reservations for Same Customer Pass Number")) {
			ruleData.maximumNumberOfConcurrentReservationsForSameCustomerPassNumber = datasFromDB
					.get("MAXNUMCONRESCUSTPASSNUM");
		}

		// MaximumNumberOfOrders-Times-TicketsWithinInventoryPeriodRule
		if (ruleTypesToAdded
				.equalsIgnoreCase("Maximum Number Of Orders-Times-Tickets Within Inventory Period")) {
			ruleData.maxOrders = datasFromDB.get("MaxOrders");
			ruleData.maxTimes = datasFromDB.get("MaxTimes");
			ruleData.maxTickets = datasFromDB.get("MaxTickets");
		}
		
		// MaximumNumberOfOrders-Times-TicketsWithinInventoryPeriodRule
		if (ruleTypesToAdded
				.equalsIgnoreCase("Maximum Number of Orders-Times-Tickets per call/cart")) {
			ruleData.maxOrders = datasFromDB.get("MaxOrders");
			ruleData.maxTimes = datasFromDB.get("MaxTimes");
			ruleData.maxTickets = datasFromDB.get("MaxTickets");
		}

		// Minimum Stay
		if (ruleTypesToAdded.equalsIgnoreCase("Minimum Stay")) {
			ruleData.transactionOccurrence = datasFromDB
					.get("TransactionOccurrence");
			ruleData.minimumStay = datasFromDB.get("Minimumstay");
			ruleData.minimumStayMon = datasFromDB.get("MinimumStayMon");
			ruleData.minimumStayTue = datasFromDB.get("MinimumStayTue");
			ruleData.minimumStayWed = datasFromDB.get("MinimumStayWed");
			ruleData.minimumStayThu = datasFromDB.get("MinimumStayThu");
			ruleData.minimumStayFri = datasFromDB.get("MinimumStayFri");
			ruleData.minimumStaySat = datasFromDB.get("MinimumStaySat");
			ruleData.minimumStaySun = datasFromDB.get("MinimumStaySun");
			ruleData.minimumStayHoliday = datasFromDB.get("MinimumStayHoliday");
			ruleData.unit = datasFromDB.get("Unit");
			ruleData.multiplesOnly = datasFromDB.get("MultiplesOnly");
			ruleData.minimumStayRequiredWhenStayIncludesStartDay = datasFromDB
					.get("MINSTAYREQSTYINCLUDSTARTDAY");//MinimumStayRequiredWhenStayIncludesStartDay
			ruleData.combineOverlappingSeasons = datasFromDB
					.get("MINISTAYCOMBINEOVERLAPSEASON");//MinimumStayCombineOverlappingSeasons
			
			ruleData.minimumStay = datasFromDB.get("MinimumStay");
			ruleData.unit = datasFromDB.get("Unit");
			ruleData.permitType = datasFromDB.get("PermitType");
		}
		
//		//Minimum Stay (Permits)
//		if(ruleTypesToAdded.equalsIgnoreCase("Minimum Stay (Permits)")) {
//			ruleData.minimumStay = datasFromDB.get("MinimumStay");
//			ruleData.unit = datasFromDB.get("Unit");
//			ruleData.permitType = datasFromDB.get("PermitType");
//		}

		// Minimum Window
		if (ruleTypesToAdded.equalsIgnoreCase("Minimum Window")) {
			ruleData.length = datasFromDB.get("LENGTHOFSTAY");
			ruleData.unit = datasFromDB.get("Unit");
		}

		// Maximum Window
		if (ruleTypesToAdded.equalsIgnoreCase("Maximum Window")) {
			ruleData.maximumWindowType = datasFromDB.get("MaximumWindowType");
			ruleData.length = datasFromDB.get("LengthOfStay");
			ruleData.unit = datasFromDB.get("Unit");
			ruleData.rollupEndOfMonthExtraDays = datasFromDB
					.get("RollupEndOfMonthExtraDays");
			ruleData.blockReleaseLength = datasFromDB.get("BlockReleaseLength");
			ruleData.blockReleaseUnit = datasFromDB.get("BlockReleaseUnit");
			ruleData.blockReleaseDayOfMonth = datasFromDB
					.get("BlockReleaseDayOfMonth");
			ruleData.blockReleaseDayOfWeek = datasFromDB
					.get("BlockReleaseDayOfWeek");
			ruleData.blockReleaseDayOfWeekWithinMonth = datasFromDB
					.get("BLOCKRLSDAYOFWEEKWITHINMONTH");//BlockReleaseDayOfWeekWithinMonth
			//slip
			ruleData.marinaRateType = datasFromDB.get("MarinaRateType");
		}

		if (ruleTypesToAdded.matches("Maximum Total Stay(\\s\\(Permits\\))?")) {
//			if(ruleTypesToAdded.equalsIgnoreCase("Maximum Total Stay")) {
//				ruleData.length = datasFromDB.get("LengthOfStay");
//			} else {
//				ruleData.length = datasFromDB.get("LengthOfStay");
				ruleData.permitType = datasFromDB.get("PermitType");
				ruleData.reservationStatus = datasFromDB.get("ReservationStatus");
//			}
			ruleData.unit = datasFromDB.get("Unit");
		}

		// Specified Stay Start
		if (ruleTypesToAdded.equalsIgnoreCase("Specified Stay Start")) {
			ruleData.startStayDayOfWeek = datasFromDB.get("StartStayDayOfWeek");
		}

		// Stay Beyond Maximum Window
		if (ruleTypesToAdded.equalsIgnoreCase("Stay Beyond Maximum Window")) {
			ruleData.length = datasFromDB.get("LengthOfStay");
			ruleData.unit = datasFromDB.get("Unit");
			ruleData.marinaRateType = datasFromDB.get("MarinaRateType");//Jane[2014-6-21]
		}

		// Time Restriction Before Change Of Dates Allowed
		if (ruleTypesToAdded
				.equalsIgnoreCase("Time Restriction Before Change Of Dates Allowed")) {
			ruleData.length = datasFromDB.get("Length");
			ruleData.unit = datasFromDB.get("Unit");
		}

		// Time To Clear
		if (ruleTypesToAdded.equalsIgnoreCase("Time To Clear")) {
			ruleData.length = datasFromDB.get("Length");
			ruleData.unit = datasFromDB.get("Unit");
		}

		// Transaction Restriction
		if (ruleTypesToAdded.equalsIgnoreCase("Transaction Restriction")) {
			ruleData.transaction = datasFromDB.get("Transaction");
			ruleData.transactionOccurrence = datasFromDB
					.get("TransactionOccurence");
			ruleData.transactionOccurrenceDays = datasFromDB
					.get("TransactionOccurenceDays");
			ruleData.transactionOccurrenceTime = datasFromDB
					.get("TransactionOccurenceTime");
		}

		// Maximum Time To Receive Payment
		if (ruleTypesToAdded.equalsIgnoreCase("Maximum Time To Receive Payment")) {
			ruleData.length = datasFromDB.get("LENGTHOFSTAY");
			ruleData.unit = datasFromDB.get("Unit");
		}

		// Maximum Stay Per Period
		if (ruleTypesToAdded.equalsIgnoreCase("Maximum Stay Per Period")) {
			ruleData.maximumStayPerPeriod = datasFromDB
					.get("MAXSTAYPERTIMEPERIODLENGTH"); //updated by pzhu
			ruleData.length = datasFromDB.get("LENGTHOFSTAY");
			ruleData.unit = datasFromDB.get("Unit");
		}

		// Maximum Number of Permits Per Period/Non Profit Organization
		if (ruleTypesToAdded
				.equalsIgnoreCase("Maximum Number of Permits Per Period Non Profit Organization")) {//updated by pzhu
			ruleData.maximumNumberOfOrdersPerPeriod = datasFromDB
					.get("MAXNUMOFORDSPERPERIOD");//MaximumNumberOfOrdersPerPeriod
			ruleData.length = datasFromDB.get("LENGTHOFSTAY");
			ruleData.unit = datasFromDB.get("Unit");
		}

		// Maximum Number Of Stays Per Period
		if (ruleTypesToAdded.equalsIgnoreCase("Maximum Number Of Stays Per Period")) {
			ruleData.maximumNumberOfOrdersPerPeriod = datasFromDB
					.get("MAXNUMOFORDSPERPERIOD"); //MaximumNumberOfOrdersPerPeriod
			ruleData.length = datasFromDB.get("LENGTHOFSTAY");
			ruleData.unit = datasFromDB.get("Unit");
		}

		// Maximum Number Of Reservations With The Same Start Date
		if (ruleTypesToAdded
				.equalsIgnoreCase("Maximum Number Of Reservations With The Same Start Date")) {
			ruleData.maximumNumberOfReservationsWithSameStartDate = datasFromDB
					.get("MAXNUMRESWITHSAMESTARTDATE");//MaximumNumberOfReservationsWithSameStartDate
		}

		// Maximum Number Of Orders Within Stay Period
		if (ruleTypesToAdded
				.equalsIgnoreCase("Maximum Number Of Orders Within Stay Period")) {
			ruleData.maximumNumberOfOrdersWithinStayPeriod = datasFromDB
					.get("MAXNUMORDWITHINSTAYPERIOD");//MaximumNumberOfOrdersWithinStayPeriod
		}

		// Maximum Number Of Orders Within A Booking Period
		if (ruleTypesToAdded
				.equalsIgnoreCase("Maximum Number Of Orders Within a Booking Period")) {
			ruleData.maximumNumberOfOrdersWithinABookingPeriod = datasFromDB
					.get("MAXNUMORDINBKINGPERIOD");//MaximumNumberOfOrdersWithinABookingPeriod
			ruleData.length = datasFromDB.get("LengthOfStay");
			ruleData.unit = datasFromDB.get("Unit");
		}

		// Maximum Number Of Orders Per Call
		if (ruleTypesToAdded.equalsIgnoreCase("Maximum Number Of Orders Per Call")) {
			ruleData.maximumNumberOfOrdersPerCall = datasFromDB
					.get("MaxNumOfOrdersPerCall");//update by pzhu, for the column in DB is not 'maximumNumberOfOrdersPerCall'
		}

		// MaximumNumberOfConcurrentReservations
		if (ruleTypesToAdded
				.matches("Maximum Number Of Concurrent Reservations(\\s\\(Permits\\))?")) {
			ruleData.maximumNumberOfConcurrentReservations = datasFromDB
					.get("MAXNUMCONRES");//MaximumNumberOfConcurrentReservations
			ruleData.permitType = datasFromDB.get("PermitType");
			ruleData.reservationStatus = datasFromDB.get("ReservationStatus");
		}
		
		// Maximum Consecutive Stay
		if (ruleTypesToAdded.equalsIgnoreCase("Maximum Consecutive Stay")) {
			ruleData.length = datasFromDB.get("LengthOfStay");
			ruleData.unit = datasFromDB.get("Unit");
			ruleData.minimumTimeAwayLength = datasFromDB
					.get("MinimumTimeAwayLength");
			ruleData.minimumTimeAwayUnit = datasFromDB
					.get("MinimumTimeAwayUnit");
		}

		// Inventory Hold Timeout
		if (ruleTypesToAdded.equalsIgnoreCase("Inventory Hold Timeout")) {
			ruleData.timeoutLen = datasFromDB.get("TimeoutLength");
		}

		// Product Restricted in Use
		if (ruleTypesToAdded.equalsIgnoreCase("Product Restricted in Use")) {
			ruleData.customerType = null;
			ruleData.customerPassType = null;
			String tempCustType = datasFromDB.get("CustomerType");
			if(!StringUtil.isEmpty(tempCustType)) {
				ruleData.customerTypes = this
						.splitTextBySemicolon(tempCustType);
			}
			String tempCustPass = datasFromDB.get("CustomerPassType");
			if(!StringUtil.isEmpty(tempCustPass)) {
				ruleData.customerPassTypes = this
						.splitTextBySemicolon(tempCustPass);
			}
		}
		
		//Issue Permit Restriction.
		if(ruleTypesToAdded.equalsIgnoreCase("Issue Permit Restriction")){
			ruleData.permitType = datasFromDB.get("PERMITTYPE");
			ruleData.issueStation = datasFromDB.get("ISSUESTATION");
		}

		//Maximum/Minimum Group Size
		if (ruleTypesToAdded.equalsIgnoreCase(MAXIMUM_GROUP_SIZE) || 
				ruleTypesToAdded.equalsIgnoreCase(MINIMUM_GROUP_SIZE)) {
			ruleData.transactionType = datasFromDB.get("TransactionType");
			ruleData.transactionOccurrence = datasFromDB.get("TransactionOccurrence");
			ruleData.permitType = datasFromDB.get("PermitType");
			ruleData.personTypes = this.splitTextBySemicolon(datasFromDB.get("PersonTypes"));
			ruleData.personTypesNums = this.splitTextBySemicolon(datasFromDB.get("PersonTypeNums"));
		}
		
		// Maximum Stay
		if (ruleTypesToAdded.equalsIgnoreCase(MAXIMUM_STAY)) {
			ruleData.maximumStay = datasFromDB.get("MaximumStay");
			ruleData.unit = datasFromDB.get("Unit");
			ruleData.permitType = datasFromDB.get("PermitType");
			ruleData.level = datasFromDB.get("Level");
		}
		
		// Restrict Entrance
		if (ruleTypesToAdded.equalsIgnoreCase(RESTRICT_ENTRANCE)) {
			ruleData.restriction = datasFromDB.get("Restriction");
		}
		
	}

	private String getRuleType() {
		String ruleType = ruleTypesToAdded;

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
