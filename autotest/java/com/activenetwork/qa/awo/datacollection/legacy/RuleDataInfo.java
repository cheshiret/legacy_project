/*
 * Created on Jan 18, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.datacollection.legacy;



/**
 * @author dsui
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RuleDataInfo {
	
	public RuleDataInfo() {}
	
	public RuleDataInfo(String ruleName) {
		this.ruleName = ruleName;
	}
	
	public String condID = "";

	public String location = "";

	public String locationCategory = "";

	public String status = "";
	
	public String ruleName="";
	
	public String ruleCategory="";

	public String productCategory = "";
	
	public String marinaRateType = "";//Slip

	public String ticketCategory = "";

	public String productGroup = "";

	public String loop = "";

	public String product = "";

	public String salesChannel = "";

	public String customerType = "";

	public String season = "";

	public String customerPassType = "";

	public String outOfState = "";

	public String paymentType = "";

	public String customerMember = "";

	public String associatedParty = "";

	public String comments = "";

	public String startDate = "";

	public String endDate = "";

	public String effectiveDate = "";

	//Minimum Stay
	public String minimumStay = "";

	public String minimumStayMon = "";

	public String minimumStayTue = "";

	public String minimumStayWed = "";

	public String minimumStayThu = "";

	public String minimumStayFri = "";

	public String minimumStaySat = "";

	public String minimumStaySun = "";
	
	public String minimumStayHoliday="";

	public String unit = "";

	public String includeHolidayPeriod = "";
	
	public String multiplesOnly = "";

	public String minimumStayRequiredWhenStayIncludesStartDay = "";
	
	public String combineOverlappingSeasons = "";
	
	public String accessType="";
	
	public String length="";
	
	//Access Time
	public String opentime="";
	
	public String dailyopentime="";
	
	public String monOpenTime="";
	
	public String tueOpenTime="";
	
	public String wedOpenTime="";
	
	public String thuOpenTime="";
	
	public String friOpenTime="";
	
	public String satOpenTime="";
	
	public String sunOpenTime="";
	
	public String closeTime="";
	
	public String dailyCloseTime="";
	
	public String monCloseTime="";
	
	public String tueCloseTime="";
	
	public String wedCloseTime="";
	
	public String thuCloseTime="";
	
	public String friCloseTime="";
	
	public String satCloseTime="";
	
	public String sunCloseTime="";
	
	//Associate Entrance
	
	public String associateEntrance="";
	
	//Automatic Cancellation
	public String minimumPaymentDeadline="";
	public String minimumPaymentReminderDaysBeforeDeadline="";
	
	//Block Stay
	public String transactionOccurrence="";
	public String blockStay="";
	public String holidayblockstay="";
	public String allowAvailabilityException="";
	public String allowStayLengthException="";
	
	//Maximum Window
	public String maximumWindowType="";
	public String rollupEndOfMonthExtraDays="";
	public String blockReleaseLength="";
	public String blockReleaseUnit="";
	public String blockReleaseDayOfMonth="";
	public String blockReleaseDayOfWeek="";
	public String blockReleaseDayOfWeekWithinMonth="";

	//Specified Stay Start 
	public String startStayDayOfWeek="";
	
	//Transaction Restriction
	public String transaction="";
	public String transactionOccurrenceDays="";
	public String transactionOccurrenceTime="";
		
	//Maximum Stay Per Period
	public String maximumStayPerPeriod="";
	
	//Maximum Number of Permits Per Period/Non Profit Organization 
	public String maximumNumberOfOrdersPerPeriod="";
	
	//Maximum Number Of Reservations With The Same Start Date 
	public String maximumNumberOfReservationsWithSameStartDate="";
	
	//Maximum Number Of Concurrent Reservations for Same Customer Pass Number
	public String maximumNumberOfConcurrentReservationsForSameCustomerPassNumber = "";
	
	//Maximum Number Of Orders Within Stay Period 
	public String maximumNumberOfOrdersWithinStayPeriod="";
	
	//Maximum Number Of Orders Within a Booking Period 
	public String maximumNumberOfOrdersWithinABookingPeriod="";
	
	//MaximumNumberOfOrders-Times-TicketsWithinInventoryPeriodRule 
	public String maxOrders="";
	public String maxTimes="";
	public String maxTickets="";
	
	//Maximum Number Of Orders Per Call 
	public String maximumNumberOfOrdersPerCall="";
	
	//Maximum Number Of Concurrent Reservations 
	public String maximumNumberOfConcurrentReservations="";
	
	//Maximum Number of Concurrent Reservations (Permits)
	public String permitType = "";
	public String reservationStatus = "";
	
	//Maximum Consecutive Stay 
	public String minimumTimeAwayLength="";
	public String minimumTimeAwayUnit="";
	
	//Inventory Hold Timeout 
	public String timeoutLen="";
	
	//Product Restricted in Use
	public String[] customerTypes;	
	public String[] customerPassTypes;
	
	// Maximum/Minimum Group Size
	public String transactionType;
	public String[] personTypes;
	public String[] personTypesNums;
	
	public String issueStation ="";
	
	// Maximum Stay
	public String maximumStay;
	public String level;
	
	// Restriction Entrance
	public String restriction;
	
	//Maximum Number of Concurrent Transient Orders Per Slip
	public String slipReservationType;
	public int maximumNumberOfTransientReservations;
	
	//Maximum Number of Entries Per List
	public int maximumNumberOfEntries;
	
	//Maximum Commercial Allocation
	public String quotaType;
	public String commercialUser;
	public int maximumNumber;
	public String countStayBeyondRulePeriod;
	
	
	//Minimum Window
	public String zeroDayCutOffTime = "";
	public String zeroDayUseCheckInTime = "";
	
	//For rule of activity in license manager
	public String locationClass = "";
}
