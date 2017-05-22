package com.activenetwork.qa.awo.supportscripts.qasetup.finance;

import org.apache.poi.util.ArrayUtil;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.AddRAFeeScheduleFunction;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * Description : Functional Test Script
 *
 * @since 2010/02/08
 * @author dsui
 */
public class AddRAFeeSchedule extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private RaFeeScheduleData ra = new RaFeeScheduleData();
	private AddRAFeeScheduleFunction addRAFeeFunc = new AddRAFeeScheduleFunction();

	public void wrapParameters(Object[] param) {
		dataTableName = "d_fin_ra_fee_schedule";
	}

	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = ra;
		addRAFeeFunc.execute(args);
	}

	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("role");
		ra.locationCategory = datasFromDB.get("LocationCategory");
		ra.location = datasFromDB.get("LocationName");

		ra.productCategory = datasFromDB.get("ProductCategory");

		ra.loop = datasFromDB.get("Loop");
		ra.productGroup = datasFromDB.get("ProductGroup");
		ra.product = datasFromDB.get("Product");
		ra.effectDate = datasFromDB.get("Effective Date");

		if (!StringUtil.notEmpty(ra.effectDate)) {
			ra.effectDate = DateFunctions.getDateAfterToday(-1);
		}

		ra.salesChannel = datasFromDB.get("Sales_Channel");
		ra.raFeeOption = datasFromDB.get("RA_Earned");
		ra.tranType = datasFromDB.get("Transaction_Type");
		ra.tranOccur = datasFromDB.get("Transaction_Occurrence");
		ra.acctCode = datasFromDB.get("Account_Code");
		ra.applyRate = datasFromDB.get("applyRate");
		ra.rateAppliesTo = datasFromDB.get("RATEAPPLYTO");
		ra.permitCategory = datasFromDB.get("PermitCategory");
		ra.permitType = datasFromDB.get("PermitType");
		ra.ticketCategory = datasFromDB.get("TicketCategory");
		ra.productFeeClass = datasFromDB.get("ProductFeeClass");
		ra.unitOption = datasFromDB.get("UnitOption");
		String personType = datasFromDB.get("PersonType");
		if(ra.unitOption.equals("Per Unit") && !StringUtil.isEmpty(personType) && !personType.equals("All")){//ticket per unit
			ra.personTypes = personType.split(",");
			ra.baseRates = datasFromDB.get("BaseRate").split(",");
		}else if(ra.unitOption.equals("Flat by Range of Ticket Quantity")){
			ra.personTypes = new String[] {"All"};
			ra.rateRanges = datasFromDB.get("RateRange").split(",");
			ra.baseRates = datasFromDB.get("BaseRate").split(",");
		}
		ra.baseRate = datasFromDB.get("BaseRate");
		ra.changedUnitsRate = datasFromDB.get("ChangeRate");
		ra.flatAmt = datasFromDB.get("FlatAmount");
		ra.percentRate = datasFromDB.get("PercentRate");
		ra.changedUnitsRate = datasFromDB.get("ChangeUnitsRate");
		ra.flatAmountRate = datasFromDB.get("FlatAmountRate");
		
		ra.otherRate = datasFromDB.get("OtherRate");
		ra.changedUnitsOtherRate = datasFromDB.get("changeUnitsOtherRate");
		ra.flatAmountOtherRate = datasFromDB.get("FlatAmountOtherRate");
		
//		ra.thresholdRate = datasFromDB.get("ThreadsholdRate");//delete this row from DB, no data used
		ra.reverseAndRepriceIndicator = Boolean.parseBoolean(datasFromDB.get("ReverseAndRepriceIndicator"));
		String deliverymethod = datasFromDB.get("deliverymethod");
		if(!StringUtil.isEmpty(deliverymethod))
			ra.deliverymethod = deliverymethod;
		ra.minimumUnitOfStay = datasFromDB.get("MINIMUM_UNIT_OF_STAY");
		ra.minimumNumOfDayBeforeArrivalDay = datasFromDB.get("MINIMUM_NUM_DAYS_BEFORE_ARRIVA");
		ra.marinaRateType = datasFromDB.get("MARINARATETYPE");
		String thresholdValues = datasFromDB.get("THRESHOLDRATES");
		if(StringUtil.notEmpty(thresholdValues)){
			ra.slipThresholdRates = StringUtil.arrayToList(thresholdValues.split(","));			
		}
		
		ra.changeDatesAppliesTo = datasFromDB.get("ChangeDatesAppliesTo");
		ra.maximumFeeRestriction = datasFromDB.get("Max_Fee_Restriction");
		ra.maximumFeeRate = datasFromDB.get("Max_Fee_Rate");
	}
}
