package com.activenetwork.qa.awo.datacollection.legacy;

import java.util.ArrayList;
import java.util.List;

public class FeeScheduleData {

	/**
	 * 
	 * @author CGuo
	 * 
	 *         To change the template for this generated type comment go to
	 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
	 *         Comments
	 */

	public String location = "";
	public String locationID = "";
	public String locationCategory = "";
	public String productCategory = "";
	public String assignPrdCategory = "";
	public String feeType = "";
	public String activeStatus = "";
	public String loop = "All";
	public String productGroup = "All";
	public String appProductGroup = "All";
	public String product = "";
	public String productID = null;
	public String rateType= "";
	public String effectDate = ""; // format:dd-MMM-yy, such as 05-DEC-10
	public String startInv = "";
	public String endInv = "";
	public String season = "";
	public String salesChannel = "";
	public String state = "";
	public String custType = "";// handle DEFECT-27997
	// public String custType = "All"; handle DEFECT-27997
	public String custPass = "";
	public String unitOfStay = "";
	public String acctCode = "";
	public String salesCategory = "All"; // ticket or permit category
	public String feeSchdType = ""; // fee schedule type, such as Normal fee
	// schedule, RA Fee schedule, RA Fee
	// Threshold schedule
	
    public String penaltyUnit = "";
    public String penaltyValue = "";
    
	public String productCategoryID;
	public String feeTypeID;
	public String salesChannelID;
	public String stateID = "2"; // defatut: all
	public String tranTypeID = null;
	public String activeStatusID;
	public String seasonID = null;
	public String permitTypeID = null;
	public String salesCategoryID = null;
	public String applyFeeID = "2"; // default: order item level
	public String tranOccuID = null;
	public String feeSchdTypeID = null;
	public String prudFeeClassID = null;
	public String marinaRateTypeID = null;
	
	//for 3.03.01 release add
	public String minimumUnitOfStay = "";
	public String minimumNumOfDayBeforeArrivalDay = "";
	
	public String maximumFeeRestriction = "";
	public String maximumFeeRate = "";
	
	public String rateApplyTo = "";
	
	
	/*Add for Slip*/
	public String dock = "All";
//	public String inOutState = "";//Quentin[20131203] deleted due to duplicated with inOutState
	public String boatCategory = "All";
	public String marinaRateType = "";
	public String slipPricingUnit = "";
	public String rafting = "";
	public String baseRatePerSeason = "";
	public String isbaseRateMultiUnitRate = "";
	public String minimumUseFee = "";
	public String calculationMethod = "";
	public List<SlipFee> slipFees = new ArrayList<SlipFee>();
	public List<PricingBase> pricingBasedonArrival = new ArrayList<PricingBase>();
	public List<PricingBase> pricingBasedonDeparture = new ArrayList<PricingBase>();
	
	/*Add for service product*/
	public String serviceFeeAmount;
	
	public static class SlipFeeCustomDuration
	{
		public String customDuration;
		public String rate;
	}
	
	public class SlipFeeEventHoliday {
		public String name;
		public String dailyNightlyRate;
		public String inventoryStart;
		public String inventoryEnd;
	}
	
	public class SlipFee
	{
		/*seasonal*/
		public String perSeasonFee;
		
		/*lease*/
		
		/*Transient*/
		public String dailyNightlyFee;
		public String weeklyFee;
		public boolean isfullStayForMultiUnitRate = false;
		
		/*shared part*/
		public String monthlyFee;
		public String increment;
		public String rangeFeet;
		public boolean isFeePerFoot = false;
		public List<SlipFeeCustomDuration> customDurations = new ArrayList<SlipFeeCustomDuration>();
		public boolean isRemoveIncrement= false;
		
		public String mondayRate;
		public String tuesdayRate;
		public String wednesdayRate;
		public String thursdayRate;
		public String fridayRate;
		public String saturdayRate;
		public String sundayRate;
		
		public List<SlipFeeEventHoliday> holidays = new ArrayList<FeeScheduleData.SlipFeeEventHoliday>();
	}
	
	public static class PricingBase
	{
		public String startMonth;
		public String startDate;
		public String endMonth;
		public String endDate;
		public String percent;
		
		public boolean isRemove = false;
		
	}
	
	
	/*Add for Slip*/

	public boolean isGroupRate = false;
	public boolean isOccupantsRanges = false;
	public boolean isVehiclesRanges = false;
	public boolean isFullStayMultiunit = false;
	public boolean isAddEventHoliday = false;

	// Family and Groups radio
	// 1.rate type is Family
	public String nightlyRate = "";
	public String weeklyRate = "";
	public String monthlyRate = "";
	public String unitQuantity = "";
	public String custRate = "";
	public String monRate = "";
	public String tuesRate = "";
	public String wedRate = "";
	public String thurRate = "";
	public String friRate = "";
	public String satRate = "";
	public String sunRate = "";

	// 2.rate type is Group
	// 2_1.base fee
	public String anyUnitRate = "0";

	// 2_2.fees for additional occupants
	public String incrementsOccRate = "0";
	public String anyUnitIncrementsOccRate = "0";
	public String monIncrementsOccRate = "";
	public String tueIncrementsOccRate = "";
	public String wedIncrementsOccRate = "";
	public String thuIncrementsOccRate = "";
	public String friIncrementsOccRate = "";
	public String satIncrementsOccRate = "";
	public String sunIncrementsOccRate = "";

	public String rangesOccRate = "0";
	public String anyUnitRangsOccRate = "0";
	public String monRangesOccRate = "";
	public String tueRangesOccRate = "";
	public String wedRangesOccRate = "";
	public String thuRangesOccRate = "";
	public String friRangesOccRate = "";
	public String satRangesOccRate = "";
	public String sunRangesOccRate = "";

	// 2_3.fees for additional vehicles
	public String incrementsVehRate = "0";
	public String anyUnitIncrementsVehRate = "0";
	public String monIncrementsVehRate = "";
	public String tueIncrementsVehRate = "";
	public String wedIncrementsVehRate = "";
	public String thuIncrementsVehRate = "";
	public String friIncrementsVehRate = "";
	public String satIncrementsVehRate = "";
	public String sunIncrementsVehRate = "";

	public String rangesVehRate = "0";
	public String anyUnitRangesVehRate = "0";
	public String monRangesVehRate = "";
	public String tueRangesVehRate = "";
	public String wedRangesVehRate = "";
	public String thuRangesVehRate = "";
	public String friRangesVehRate = "";
	public String satRangesVehRate = "";
	public String sunRangesVehRate = "";

	// 2_4.permit parameters
	public String permitCategory;
	public String permitType = "";
	/**
	 * 1 - Per Person; 2 - Per Permit; 3- Flat by Range of Group Size 4 - Per
	 * Person Per Day; 5 - Per Person Per Period
	 * 
	 * */
	public String permitUnit = "";
	public String[] personTypes = null;
	// parameters for permit unit type 1, 2, 4
	public String[] anyDayRates = null;
	public String[] permitMonRates = null;
	public String[] permitTueRates = null;
	public String[] permitWedRates = null;
	public String[] permitThuRates = null;
	public String[] permitFriRates = null;
	public String[] permitSatRates = null;
	public String[] permitSunRates = null;

	// parameters for permit unit type 3, 5
	public List<List<String>> rangeFlats = new ArrayList<List<String>>();
	public List<List<String>> anyDayFlats = new ArrayList<List<String>>();
	public List<List<String>> monFlats = new ArrayList<List<String>>();
	public List<List<String>> tueFlats = new ArrayList<List<String>>();
	public List<List<String>> wedFlats = new ArrayList<List<String>>();
	public List<List<String>> thuFlats = new ArrayList<List<String>>();
	public List<List<String>> friFlats = new ArrayList<List<String>>();
	public List<List<String>> satFlats = new ArrayList<List<String>>();
	public List<List<String>> sunFlats = new ArrayList<List<String>>();
	public String maximumUseFee = "";

	public FeeDataForPersonOrTicketType[] personOrTypeData;

	// Fees by Event/Holiday
	public String[] holidayName = null;
	public String[] holidayRate = null;
	public String[] holidayInvStart = null;
	public String[] holidayInvEnd = null;

	public class FeeDataForPersonOrTicketType {
		public String personOrTicketType = "";
		public String ticketAnyDayRate = "";
		public String ticketMonRate = "";
		public String ticketTueRate = "";
		public String ticketWedRate = "";
		public String ticketThuRate = "";
		public String ticketFriRate = "";
		public String ticketSatRate = "";
		public String ticketSunRate = "";
		
		public FeeDataForPersonOrTicketType(){}
	}
	
//	public String anyDayRate = "";

	public String taxName;
	public String taxRate;
	// public int feeTypeId = 0;
	// public int locId = 0;
	// public int prdId = 0;
	
	public String rate;
	
	public String tranFee;
	public String tranFeeChangedUnits;
	public String tranFeeFlatAmount;
	public String tranFeeNumOfFreeChgPerRev;
	
	public String attrType;
	public String attrValue;
	public String[] attrValues;
	public String tranType = "";
	public String tranOccur = "";
	public String tranFeeOption;
	public String feeSchdId;

	// search fee schedule paras
	public String searchType = "";
	public String searchValue = "";
	public String dateType = "";
	public String fromDate = "";
	public String toDate = "";
	public String unit = "";
	public String ticketOrpermitCat = "";
	public String prodOrProdgroup = "";

	// discount parameters
	public String discountRate = "";
	public String discountName = "";

	// tour ticket parameters
	public String[] anyDayRanges = null;//used for ticket Flat by Range of Ticket Quantity
	
	public String ticketCategory = "";
	public String applyFee = "Order Item Level";
	public String ticketType = "";
	public String ticketUnit = "";
	public boolean isEmbedInTicketFee = false;
	public String[] ticketTypes = null;
	public String deliveryMethod = "";
	
	/*
	 * When transaction type is Chanage Dates, this section will be displayed on the page.
	 * It is radio button with two options:
	 * All Change Dates. Exclude Extend Stay(i.e. Arriving Early Departing Later)
	 * Default select value is: All Change Dates.
	 */
	public String changeDatesAppliesTo;
	
	public void cleanUpSearchData(){
		this.searchType="";
		this.searchValue="";
		this.dateType="";
		this.fromDate="";
		this.toDate="";
		this.activeStatus="";
		this.productCategory="";
		this.feeType="";
		this.unit="";
		this.ticketOrpermitCat="";
		this.permitType="";
		this.rateType="";
		this.tranOccur="";
		this.attrType="";
		this.season="";
		this.salesChannel="";
		this.state="";
		this.custType="";
		this.locationClass = "";
		this.tranType = "";
	}

	public FeeScheduleData() {
	}

	public FeeScheduleData(boolean isUpdate) {
		// only update any field you want in your test cases.
		// will handle the null in set up fields method
		loop = "";
		productGroup = "";
		product = "";
		salesChannel = "";
		state = "";
		season = "";
	}
	
	/*********************************For Activity Schedule***********************************************************/
	public String scheduleName = "";
	public String effectStartDate = "";
	public String effectEndDate = "";
	public String fromLicenseYear = "";
	public String toLicenseYear = "";
	public String locationClass = "";
	public String applyRate = ""; //Per Unit
	public String rateAmount = "";
	public String splitRateBy = ""; //Percent, amount
	public String splitIntoNum = "";
	public List<String> accounts = new ArrayList<String>();
	public List<String> percentOrAmountForEachAccount = new ArrayList<String>();
	/**For transaction fee of activity**/
	public List<String> applicableTaxes = new ArrayList<String>();
	public String stateTransFee = "";
	/**For vender fee of activity**/
	public String stateVendorFee = "";
//	public String vendorFeeAccount = "";
	//For search
	public String licenseYear = "";
	
	/*************************************For Pos Fee**********************************************************/
	public String purchaseType = "";
	
	/*************************************For equipment(product Category:Facility, Fee Type:Facility Fee, Schedule Type:Discount)*******************************************************************/
	public class PromoCode {
		public String code = "";
		public String description = "";
		
		public PromoCode(){
			
		}
		public PromoCode(String cd, String desc){
			this.code = cd;
			this.description = desc;
		}
		
		public String toString(){
			return "code:" + code + ", description:" + description;
		}
	}
	public String productSubCategory = "";
	public String scheduleType = "";
	public String discountType = "";
	public String orderCreateStartDate = "";
	public String orderCreateEndDate = "";
	public List<PromoCode> promoCodes = new ArrayList<PromoCode>();
	public String feeApplyTo = "";
	public String chargeFeeDuring = "";
	
//	public List<String> promoCodes = new ArrayList<String>();
//	public List<String> promoDes = new ArrayList<String>();
}
