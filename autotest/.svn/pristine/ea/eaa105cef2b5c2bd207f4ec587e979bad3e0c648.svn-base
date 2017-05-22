package com.activenetwork.qa.awo.datacollection.legacy;

import java.util.ArrayList;
import java.util.List;

public class RaFeeScheduleData extends FeeScheduleData{

	public String feeId = "";

	public String activeStatus = "";
    /*comment for extends FeeScheduleData*/
//	public String location = "";

//	public String locationCategory = "";
	
//	public String productCategory = "";
	
	public String productSubCategory = "";

	public String applicableProductCategory = "";
	
	/*comment for extends FeeScheduleData*/
//	public String product = "";

//	public String productGroup = "";

//	public String loop = "";
	
//	public String dock = "";//for Slip

//	public String effectDate = "";

//	public String salesChannel = "";
	
//	public String salesChannelID = "";
	
	public String locationClass = "";
	
	public String licenseYearFrom = "";
	
	public String licenseYearTo = "";
	
	public String vehicleType = "";

	public String raFeeOption = "";

//	public String acctCode = "";

	public String marinaRateType = "";
	
	public String unitOption = "";

	public String baseRate = "";  //new units rate
	
	public String changedUnitsRate = "";
	
	public String flatAmountRate = "";
	
	public String percentRate = "";
	
	public String rateType = "";
	
	public String rateAppliesTo = "";
	
	public String otherRate="";
	
	public String changedUnitsOtherRate="";
	
	public String flatAmountOtherRate="";
	
	public String changeRate="";
	
	public String flatAmt="";
	
	public String maxAmt="";
	
	public String maxRestricCond="";
	
	public String personType="";
	
//	public String thresholdRate="";
	
	public String[] personTypes = null;
	
	public List<String> slipThresholdRates = new ArrayList<String>();
	
	public List<String[]> thresholdRates = null;
	
	public String[] baseRates = null;
	
	public String[] rateRanges = null;
	
	//for 3.03.01 release add
	public String minimumUnitOfStay = "";
	public String minimumNumOfDayBeforeArrivalDay = "";
	
	
	//For ticket
	public String ticketCategory="";
	
	public String productFeeClass="";
	
	public String applyRate="";
	
	public String deliverymethod = "";
	
	//For permit
	public String permitCategory="";
	
	public String permitType="";
	
	public String lineofbusiness="";
	
	//For License
	public String licenseYearOfCurrentRAFee = "";//only on RA fee detail page.
	
	public boolean reverseAndRepriceIndicator = false;

	/*
	 * When transaction type is Change Dates, this section will be displayed on the page.
	 * It is radio button with two options:
	 * All Change Dates. Exclude Extend Stay(i.e. Arriving Early Departing Later)
	 * Default select value is: All Change Dates.
	 */
	public String changeDatesAppliesTo;
	
	// for search
	public String searchBy = "";
	public String searchValue = "";
	public String searchDate = "";
	public String searchDateFrom = "";
	public String searchDateTo = "";
	public String searchStatus = "";
	public String appPrdCategory = "";
	
	//for search result
	public String orderNum = "";
	public String price = "";
	
	public RaFeeScheduleData() {
	}

	public RaFeeScheduleData(boolean isNew) {
		// only update any field you want in your test cases.
		// will handle the null in set up fields method
		if(isNew){
			loop = "";
			productGroup = "";
			product = "";
			salesChannel = "";
			state = "";
			season = "";
		}
	}
	
}
