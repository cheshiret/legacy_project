package com.activenetwork.qa.awo.supportscripts.qasetup.finance;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.AddFeeScheduleFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description : Functional Test Script
 * 
 * @author QA
 */
public class AddUseOrAttrFeeSchedule extends SetupCase {
	/**
	 * Script Name : <b>AddUseOrAttrFeeSchedule</b> Generated : <b>Feb 11, 2010
	 * 12:16:59 AM</b> Description : Functional Test Script Original Host :
	 * WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2010/02/11
	 * @author Sara Wang
	 */
	private LoginInfo login = new LoginInfo();
	private FeeScheduleData feeSchData;
	private AddFeeScheduleFunction func = new AddFeeScheduleFunction();

	public void wrapParameters(Object[] param) {
		dataTableName = "d_fin_use_attr_fee_sched";

		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");
	}

	public void executeSetup() {
		Object[] args = new Object[4];
		args[0] = login;
		args[1] = this.feeSchData;
		
		func.execute(args);
		
	}

	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("logLocation");

		feeSchData = new FeeScheduleData();
		 
		feeSchData.location = datasFromDB.get("location");
		feeSchData.locationCategory = datasFromDB.get("locationCategory");
		feeSchData.productCategory = datasFromDB.get("productCategory");
		feeSchData.feeType = datasFromDB.get("feeType");
		feeSchData.loop = datasFromDB.get("loopname");
		feeSchData.dock = datasFromDB.get("loopname");
		feeSchData.productGroup = datasFromDB.get("productGroup");
		feeSchData.product = datasFromDB.get("product");
		feeSchData.effectDate = datasFromDB.get("effectiveDate");
		if(StringUtil.isEmpty(feeSchData.effectDate)){
			feeSchData.effectDate = DateFunctions.getDateAfterToday(-5);
		}
		feeSchData.startInv = datasFromDB.get("startInv");
		if(StringUtil.isEmpty(feeSchData.startInv)){
			feeSchData.startInv = DateFunctions.getDateAfterToday(-365);
		}
		
		String endDate = datasFromDB.get("endInv");
		// if inventory end date is null, set the end date as today+720,ignore past date,as Sophia's data need setup for past date
		//update to 720,as there are some seasonal slip order need make future date order
		if(StringUtil.isEmpty(endDate)){
			endDate = DateFunctions.getDateAfterToday(720);
		}
		feeSchData.endInv = endDate;
		feeSchData.season = datasFromDB.get("season");
		feeSchData.salesChannel = datasFromDB.get("salesChannel");
		feeSchData.state = datasFromDB.get("state");
		feeSchData.custType = datasFromDB.get("customerType");
		feeSchData.unitOfStay = datasFromDB.get("unitOfStay");
		feeSchData.acctCode = datasFromDB.get("accountCode");
		feeSchData.attrType = datasFromDB.get("attributeType");
		//Quentin[20140107] update to handle multiple attribute values
//		feeSchData.attrValue = datasFromDB.get("attributeValue");
		String attrValue = datasFromDB.get("attributeValue");
		if(!StringUtil.isEmpty(attrValue)) {
			feeSchData.attrValues = attrValue.split(",");
		}

		feeSchData.isGroupRate = Boolean.parseBoolean(datasFromDB.get("isGroupRate"));
		feeSchData.isOccupantsRanges = Boolean.parseBoolean(datasFromDB.get("isOccupantsRanges"));
		feeSchData.isVehiclesRanges = Boolean.parseBoolean(datasFromDB.get("isVehiclesRanges"));
		feeSchData.isFullStayMultiunit = Boolean.parseBoolean(datasFromDB.get("isFullStay"));

		// rate type is Family
		feeSchData.nightlyRate = datasFromDB.get("nightlyRate");
		feeSchData.weeklyRate = datasFromDB.get("weeklyRate");
		feeSchData.monthlyRate = datasFromDB.get("monthlyRate");
		feeSchData.unitQuantity = datasFromDB.get("unitQuantity");
		feeSchData.custRate = datasFromDB.get("custRate");
		feeSchData.monRate = datasFromDB.get("monRate");
		feeSchData.tuesRate = datasFromDB.get("tuesRate");
		feeSchData.wedRate = datasFromDB.get("wedRate");
		feeSchData.thurRate = datasFromDB.get("thurRate");
		feeSchData.friRate = datasFromDB.get("friRate");
		feeSchData.satRate = datasFromDB.get("satRate");
		feeSchData.sunRate = datasFromDB.get("sunRate");

		// rate type is Group
		// 1.base fee
		feeSchData.anyUnitRate = datasFromDB.get("anyUnitRate");

		// 2.Fees for Additional Occupants
		// 2_1.Increments radio
		feeSchData.incrementsOccRate = datasFromDB.get("incrementsOccRate");
		feeSchData.anyUnitIncrementsOccRate = datasFromDB.get("anyUnitIncrementsOccRate");
		feeSchData.monIncrementsOccRate = datasFromDB.get("monIncrementsOccRate");
		feeSchData.tueIncrementsOccRate = datasFromDB.get("tueIncrementsOccRate");
		feeSchData.wedIncrementsOccRate = datasFromDB
				.get("wedIncrementsOccRate");
		feeSchData.thuIncrementsOccRate = datasFromDB
				.get("thuIncrementsOccRate");
		feeSchData.friIncrementsOccRate = datasFromDB
				.get("friIncrementsOccRate");
		feeSchData.satIncrementsOccRate = datasFromDB
				.get("satIncrementsOccRate");
		feeSchData.sunIncrementsOccRate = datasFromDB
				.get("sunIncrementsOccRate");
		// 2_2.Ranges radio
		feeSchData.rangesOccRate = datasFromDB.get("rangesOccRate");
		feeSchData.anyUnitRangsOccRate = datasFromDB.get("anyUnitRangsOccRate");
		feeSchData.monRangesOccRate = datasFromDB.get("monRangesOccRate");
		feeSchData.tueRangesOccRate = datasFromDB.get("tueRangesOccRate");
		feeSchData.wedRangesOccRate = datasFromDB.get("wedRangesOccRate");
		feeSchData.thuRangesOccRate = datasFromDB.get("thuRangesOccRate");
		feeSchData.friRangesOccRate = datasFromDB.get("friRangesOccRate");
		feeSchData.satRangesOccRate = datasFromDB.get("satRangesOccRate");
		feeSchData.sunRangesOccRate = datasFromDB.get("sunRangesOccRate");

		// 3.Fees for Additional Vehicles
		// 3_1.Increments radio
		feeSchData.incrementsVehRate = datasFromDB.get("incrementsVehRate");
		feeSchData.anyUnitIncrementsVehRate = datasFromDB
				.get("anyUnitIncrementsVehRate");
		feeSchData.monIncrementsVehRate = datasFromDB
				.get("monIncrementsVehRate");
		feeSchData.tueIncrementsVehRate = datasFromDB
				.get("tueIncrementsVehRate");
		feeSchData.wedIncrementsVehRate = datasFromDB
				.get("wedIncrementsVehRate");
		feeSchData.thuIncrementsVehRate = datasFromDB
				.get("thuIncrementsVehRate");
		feeSchData.friIncrementsVehRate = datasFromDB
				.get("friIncrementsVehRate");
		feeSchData.satIncrementsVehRate = datasFromDB
				.get("satIncrementsVehRate");
		feeSchData.sunIncrementsVehRate = datasFromDB
				.get("sunIncrementsVehRate");
		// 3_2.Ranges radio
		feeSchData.rangesVehRate = datasFromDB.get("rangesVehRate");
		feeSchData.anyUnitRangesVehRate = datasFromDB
				.get("anyUnitRangesVehRate");
		feeSchData.monRangesVehRate = datasFromDB.get("monRangesVehRate");
		feeSchData.tueRangesVehRate = datasFromDB.get("tueRangesVehRate");
		feeSchData.wedRangesVehRate = datasFromDB.get("wedRangesVehRate");
		feeSchData.thuRangesVehRate = datasFromDB.get("thuRangesVehRate");
		feeSchData.friRangesVehRate = datasFromDB.get("friRangesVehRate");
		feeSchData.satRangesVehRate = datasFromDB.get("satRangesVehRate");
		feeSchData.sunRangesVehRate = datasFromDB.get("sunRangesVehRate");

		/**
		 * For PERMIT
		 */
		// permit use fee
		feeSchData.permitCategory = datasFromDB.get("permitCategory");
		feeSchData.permitType = datasFromDB.get("permitType");
		// PermitUnit has four kinds: 1.Per Person 2.Per Permit 3.Flat by Range
		// of Group Size 4.Per Person Per Day
		feeSchData.permitUnit = datasFromDB.get("permitUnit");

		String personTypesFromDB = datasFromDB.get("personType");
		
		if (personTypesFromDB != null && personTypesFromDB.length() > 0 && personTypesFromDB.contains("|")) {
			String[] types = personTypesFromDB.split("\\|");
			feeSchData.personTypes = new String[types.length];
			feeSchData.anyDayRates = new String[types.length];
			feeSchData.permitMonRates = new String[types.length];
			feeSchData.permitTueRates = new String[types.length];
			feeSchData.permitWedRates = new String[types.length];
			feeSchData.permitThuRates = new String[types.length];
			feeSchData.permitFriRates = new String[types.length];
			feeSchData.permitSatRates = new String[types.length];
			feeSchData.permitSunRates = new String[types.length];
			for (int i = 0; i < types.length; i++) {
				//permit person types
				feeSchData.personTypes[i] = types[i];
				//permit any day rate
				if (!StringUtil.isEmpty(datasFromDB.get("anyDayRate"))) {
					feeSchData.anyDayRates[i] = datasFromDB.get("anyDayRate").split("\\|")[i];
				}
				if (!StringUtil.isEmpty(datasFromDB.get("permitMonRate"))) {
					feeSchData.permitMonRates[i] = datasFromDB.get(
							"permitMonRate").split("\\|")[i];
				}
				if (!StringUtil.isEmpty(datasFromDB.get("permitTueRate"))) {
					feeSchData.permitTueRates[i] = datasFromDB.get(
							"permitTueRate").split("\\|")[i];
				}
				if (!StringUtil.isEmpty(datasFromDB.get("permitWedRate"))) {
					feeSchData.permitThuRates[i] = datasFromDB.get(
							"permitWedRate").split("\\|")[i];
				}
				if (!StringUtil.isEmpty(datasFromDB.get("permitThuRate"))) {
					feeSchData.permitFriRates[i] = datasFromDB.get(
							"permitThuRate").split("\\|")[i];
				}
				if (!StringUtil.isEmpty(datasFromDB.get("permitFriRate"))) {
					feeSchData.permitFriRates[i] = datasFromDB.get(
							"permitFriRate").split("\\|")[i];
				}
				if (!StringUtil.isEmpty(datasFromDB.get("permitSatRate"))) {
					feeSchData.permitSatRates[i] = datasFromDB.get(
							"permitSatRate").split("\\|")[i];
				}
				if (!StringUtil.isEmpty(datasFromDB.get("permitSunRate"))) {
					feeSchData.permitSunRates[i] = datasFromDB.get(
							"permitSunRate").split("\\|")[i];
				}
			}
		} else {
			feeSchData.personTypes = new String[1];
			feeSchData.anyDayRates = new String[1];
			feeSchData.permitMonRates = new String[1];
			feeSchData.permitTueRates = new String[1];
			feeSchData.permitWedRates = new String[1];
			feeSchData.permitThuRates = new String[1];
			feeSchData.permitFriRates = new String[1];
			feeSchData.permitSatRates = new String[1];
			feeSchData.permitSunRates = new String[1];
			
			feeSchData.personTypes[0] = personTypesFromDB;
			feeSchData.anyDayRates[0] = datasFromDB.get("anyDayRate");
			feeSchData.permitMonRates[0] = datasFromDB.get("permitMonRate");
			feeSchData.permitTueRates[0] = datasFromDB.get("permitTueRate");
			feeSchData.permitWedRates[0] = datasFromDB.get("permitWedRate");
			feeSchData.permitThuRates[0] = datasFromDB.get("permitThuRate");
			feeSchData.permitFriRates[0] = datasFromDB.get("permitFriRate");
			feeSchData.permitSatRates[0] = datasFromDB.get("permitSatRate");
			feeSchData.permitSunRates[0] = datasFromDB.get("permitSunRate");
		}
		
		/*for Slip*/
		feeSchData.marinaRateType = datasFromDB.get("MARINARATETYPE");
		feeSchData.rafting = datasFromDB.get("RAFTING");
		feeSchData.slipPricingUnit = datasFromDB.get("SLIPPRICINGUNIT");
		
		{
			String[] slipDailyFees = datasFromDB.get("SLIPDAILYPRICE").split(",");
			String[] slipWeeklyFees = datasFromDB.get("SLIPWEEKLYPRICE").split(",");
			String[] slipMonthlyFees = datasFromDB.get("SLIPMOTHLYPRICE").split(",");
			String[] slipSeasonFees = datasFromDB.get("SLIPSEASONFEE").split(",");
			
			int size = Math.max((Math.max(slipDailyFees.length, slipWeeklyFees.length)),(Math.max(slipMonthlyFees.length, slipSeasonFees.length)));
			
			for(int i=0; i<size; i++)
			{
				FeeScheduleData.SlipFee fee = feeSchData.new SlipFee();
				if(slipDailyFees.length>i)
				{
					fee.dailyNightlyFee = slipDailyFees[i];
				}
				if(slipWeeklyFees.length>i)
				{
					fee.weeklyFee = slipWeeklyFees[i];
				}
				if(slipMonthlyFees.length>i)
				{
					fee.monthlyFee = slipMonthlyFees[i];
				}
				if(slipSeasonFees.length>i)
				{
					fee.perSeasonFee = slipSeasonFees[i];
				}
				
				feeSchData.slipFees.add(fee);				
			}
		}
		feeSchData.boatCategory = datasFromDB.get("BOATCATEGORY");
		if(StringUtil.isEmpty(datasFromDB.get("ISACTIVE")) || "true".equals(datasFromDB.get("ISACTIVE"))){
			feeSchData.activeStatus = OrmsConstants.ACTIVE_STATUS;
		} else {
			feeSchData.activeStatus = OrmsConstants.INACTIVE_STATUS;
		}
		/*for Slip*/
		
	}
}
