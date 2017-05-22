package com.activenetwork.qa.awo.datacollection.legacy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeeValidationData {

	public String tranType = "";

	public String tranOccurance = "";

	public String feeType = "";

	public String bookDate = "";

	public String arriveDate = "";

	public String departureDate = "";

	public String nightStay = "";

	public String rateType = "";

	public String salesChannel = "";
	
	public String deliveryMethod = "";

	public String locationID = "";

	public String state = "";

	public String custType = "";

	public String custPass = "";

	public String member = "";

	public String disctTier1 = "";

	public String disctTier2 = "";

	public String occuptants = "";

	public String vehicles = "";

	public String schedule = "";

	public String rate = "";

	public String productID = "";

	public String prdGrpID = "";

	public String permitTypeID = "";
	
	public String applyLevel = "";

	public String typeOfUse = ""; // Overnight, day

	public boolean isHoliday = false;

	public boolean isPersonType = false;
	
	public String changeUnit = ""; 

	public List<String> nights = new ArrayList<String>();

	public List<Map<String, String>> units = new ArrayList<Map<String, String>>();

	public List<Map<String, String[]>> unitNights = new ArrayList<Map<String, String[]>>();

	public List<String> schedules = new ArrayList<String>();

	public List<String> time = new ArrayList<String>();

	public List<String> personType = new ArrayList<String>();

	public List<String> transCode = new ArrayList<String>();

	public BigDecimal negociatedPrice = BigDecimal.ZERO;

	public Map<String, BigDecimal> minmumRates = new HashMap<String, BigDecimal>();

	public List<Map<String, BigDecimal>> baseRate = new ArrayList<Map<String, BigDecimal>>();

	public List<Map<String, String>> transCond = new ArrayList<Map<String, String>>();

	public List<List<String[]>> groupCond = new ArrayList<List<String[]>>();

	public List<Map<String, String>> taxCond = new ArrayList<Map<String, String>>();

	public List<Map<String, String>> ticketCond = new ArrayList<Map<String, String>>();

	public List<Map<String, Map<String, BigDecimal>>> permitRate = new ArrayList<Map<String, Map<String, BigDecimal>>>();

	public List<Map<String, Map<String, BigDecimal>>> othersRate = new ArrayList<Map<String, Map<String, BigDecimal>>>();

	public List<Map<String, BigDecimal>> slipRate = new ArrayList<Map<String, BigDecimal>>();
	
	public Map<String, BigDecimal> posBaseAmount = new HashMap<String, BigDecimal>();// String-ScheduleID,
	// BigDecimal-Amount

	public List<Map<String, Map<String, Map<String, BigDecimal>>>> incrRate = new ArrayList<Map<String, Map<String, Map<String, BigDecimal>>>>();

	public List<String> updateStartTime = new ArrayList<String>();

	public List<String> updateEndTime = new ArrayList<String>();

	public List<String> updateDisStartTime = new ArrayList<String>();

	public List<String> updateDisEndTime = new ArrayList<String>();

	public List<List<String>> updateHolidayStartTime = new ArrayList<List<String>>();

	public List<List<String>> updateHolidayEndTime = new ArrayList<List<String>>();

	public List<String> disctTier1Items = new ArrayList<String>();

	public List<String> disctTier2Items = new ArrayList<String>();

	public List<List<String>> specificTier1DisctDetail = new ArrayList<List<String>>();

	public List<List<String>> specificTier2DisctDetail = new ArrayList<List<String>>();

	public List<DiscountSchdInfo> disInfo = new ArrayList<DiscountSchdInfo>();

	public List<PenaltySchdInfo> penaltyInfo = new ArrayList<PenaltySchdInfo>();

	public class DiscountSchdInfo {
		public BigDecimal amount = null;
		public BigDecimal friamount = null;
		public BigDecimal monamount = null;
		public BigDecimal tueamount = null;
		public BigDecimal wedamount = null;
		public BigDecimal thuamount = null;
		public BigDecimal satamount = null;
		public BigDecimal sunamount = null;
		public String unitType = "";
		public String discountType = "";
		public String discountName = "";
		public String discountId = "";
		public String night = "";
		public String startTime = "";
		public boolean disOnDis = false;
		public boolean isQuality = false;
		public boolean isWeekly = false;
		
		public boolean monind = false;
		public boolean tueind = false;
		public boolean wedind = false;
		public boolean thuind = false;
		public boolean friind = false;
		public boolean satind = false;
		public boolean sunind = false;
		public String paidUnits = "";
		public String disctUnits = "";
		public String maxDisctUnits = "";
		public String calMethod = "";
		public String marinaRateType = "";
	}

	public class PenaltySchdInfo {
		public BigDecimal penaltyRate = null;
		public String penaltyId = "";
		public String feeTypeID = "";
		public String tranTypeID = "";
		public String tranOccurID = "";
		public String penaltyUnitID = "";
	}
	
}
