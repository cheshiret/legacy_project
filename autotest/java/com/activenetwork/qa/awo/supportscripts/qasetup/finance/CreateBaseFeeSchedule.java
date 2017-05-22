package com.activenetwork.qa.awo.supportscripts.qasetup.finance;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.CreateBaseFeeFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class CreateBaseFeeSchedule extends SetupCase
{
	/**
	 * Script Name   : <b>CreateBaseFeeSchedule</b>
	 * Generated     : <b>Mar 8, 2010 2:53:47 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/08
	 * @author Sara Wang
	 */

	private LoginInfo login = new LoginInfo();
	private FeeScheduleData feeSchData;
	private CreateBaseFeeFunction func = new CreateBaseFeeFunction();
	
	public void wrapParameters(Object[] param) {
		dataTableName = "d_fin_base_fee_sched";
		queryDataSql = "select * from " + dataTableName + "where QA4_RESULT <> 2";//" where QA4_RESULT <> 2";
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
	}
	
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = feeSchData;
		
		func.execute(args);
		}

	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("location");
		
		feeSchData = new FeeScheduleData();
		
		feeSchData.location = datasFromDB.get("feeSchLocation");
		feeSchData.locationCategory = datasFromDB.get("locationCategory");
		feeSchData.feeType = datasFromDB.get("feeType");
		
		feeSchData.productCategory = datasFromDB.get("productCategory");

		feeSchData.loop= datasFromDB.get("loop");
		feeSchData.productGroup = datasFromDB.get("productGroup");
		feeSchData.product = datasFromDB.get("product");
		feeSchData.season = datasFromDB.get("season");
		feeSchData.salesChannel = datasFromDB.get("salesChannel");
		feeSchData.state = datasFromDB.get("state");
		feeSchData.custType = datasFromDB.get("customerType");
		feeSchData.unitOfStay = datasFromDB.get("unitOfStay");
		feeSchData.acctCode = datasFromDB.get("accountCode");
		
		feeSchData.effectDate = DateFunctions.getToday();
		feeSchData.startInv =  DateFunctions.getDateAfterToday(-10);
		feeSchData.endInv = DateFunctions.getDateAfterToday(365);
		feeSchData.fromDate =  DateFunctions.getDateAfterToday(-10);
		feeSchData.toDate = DateFunctions.getDateAfterToday(365);
	
		//for activity fee
		feeSchData.effectStartDate =  DateFunctions.getDateAfterToday(-10);
		feeSchData.effectEndDate = DateFunctions.getDateAfterToday(365);

		
		
		feeSchData.isGroupRate = Boolean.parseBoolean(datasFromDB.get("isGroupRate"));
		feeSchData.isOccupantsRanges = Boolean.parseBoolean(datasFromDB.get("isOccupantsRanges"));
		feeSchData.isVehiclesRanges = Boolean.parseBoolean(datasFromDB.get("isVehiclesRanges"));
		
        //rate type is Family
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
		//for activity fee and facility fee
		feeSchData.rateAmount=datasFromDB.get("rate");
		//for POS rate
		feeSchData.rate = datasFromDB.get("rate"); 
		feeSchData.tranType = datasFromDB.get("tranType");//transaction type
		feeSchData.tranFee = datasFromDB.get("rate"); //for transaction rate
        //rate type is Group
	    //1.base fee
		feeSchData.anyUnitRate = datasFromDB.get("anyUnitRate");
		
		//2.Fees for Additional Occupants 
		//2_1.Increments radio
		feeSchData.incrementsOccRate =datasFromDB.get("incrementsOccRate");
		feeSchData.anyUnitIncrementsOccRate = datasFromDB.get("anyUnitIncrementsOccRate");
		feeSchData.monIncrementsOccRate = datasFromDB.get("monIncrementsOccRate");
		feeSchData.tueIncrementsOccRate = datasFromDB.get("tueIncrementsOccRate");
		feeSchData.wedIncrementsOccRate = datasFromDB.get("wedIncrementsOccRate");
		feeSchData.thuIncrementsOccRate = datasFromDB.get("thuIncrementsOccRate");
		feeSchData.friIncrementsOccRate = datasFromDB.get("friIncrementsOccRate");
		feeSchData.satIncrementsOccRate = datasFromDB.get("satIncrementsOccRate");
		feeSchData.sunIncrementsOccRate = datasFromDB.get("sunIncrementsOccRate");
		//2_2.Ranges radio
		feeSchData.rangesOccRate = datasFromDB.get("rangesOccRate");
		feeSchData.anyUnitRangsOccRate = datasFromDB.get("anyUnitRangsOccRate");
		feeSchData.monRangesOccRate = datasFromDB.get("monRangesOccRate");
		feeSchData.tueRangesOccRate = datasFromDB.get("tueRangesOccRate");
		feeSchData.wedRangesOccRate = datasFromDB.get("wedRangesOccRate");
		feeSchData.thuRangesOccRate = datasFromDB.get("thuRangesOccRate");
		feeSchData.friRangesOccRate = datasFromDB.get("friRangesOccRate");
		feeSchData.satRangesOccRate = datasFromDB.get("satRangesOccRate");
		feeSchData.sunRangesOccRate = datasFromDB.get("sunRangesOccRate");
		
		//3.Fees for Additional Vehicles
		//3_1.Increments radio
		feeSchData.incrementsVehRate = datasFromDB.get("incrementsVehRate");
		feeSchData.anyUnitIncrementsVehRate = datasFromDB.get("anyUnitIncrementsVehRate");
		feeSchData.monIncrementsVehRate = datasFromDB.get("monIncrementsVehRate");
		feeSchData.tueIncrementsVehRate = datasFromDB.get("tueIncrementsVehRate");
		feeSchData.wedIncrementsVehRate = datasFromDB.get("wedIncrementsVehRate");
		feeSchData.thuIncrementsVehRate = datasFromDB.get("thuIncrementsVehRate");
		feeSchData.friIncrementsVehRate = datasFromDB.get("friIncrementsVehRate");
		feeSchData.satIncrementsVehRate = datasFromDB.get("satIncrementsVehRate");
		feeSchData.sunIncrementsVehRate = datasFromDB.get("sunIncrementsVehRate");
		//3_2.Ranges radio
		feeSchData.rangesVehRate = datasFromDB.get("rangesVehRate");
		feeSchData.anyUnitRangesVehRate = datasFromDB.get("anyUnitRangesVehRate");
		feeSchData.monRangesVehRate = datasFromDB.get("monRangesVehRate");
		feeSchData.tueRangesVehRate = datasFromDB.get("tueRangesVehRate");
		feeSchData.wedRangesVehRate = datasFromDB.get("wedRangesVehRate");
		feeSchData.thuRangesVehRate = datasFromDB.get("thuRangesVehRate");
		feeSchData.friRangesVehRate = datasFromDB.get("friRangesVehRate");
		feeSchData.satRangesVehRate = datasFromDB.get("satRangesVehRate");
		feeSchData.sunRangesVehRate = datasFromDB.get("sunRangesVehRate");
		//handle applicable taxes
		String applicableTaxesStr = datasFromDB.get("APPLICABLETAXES");
		if(null!=applicableTaxesStr&&StringUtil.notEmpty(applicableTaxesStr)){
			String [] applicableTaxes = applicableTaxesStr.split(",");
			for(int i=0;i<applicableTaxes.length;i++){
				feeSchData.applicableTaxes.add(applicableTaxes[i]);
			}
		}
		//handle schedule name
		feeSchData.scheduleName = datasFromDB.get("schedulename");
//		//permit use fee
//		feeSchData.permitCategory = datasFromDB.get("permitCategory");
//		feeSchData.permitType = datasFromDB.get("permitType");
//        //PermitUnit has four kinds: 1.Per Person 2.Per Permit 3.Flat by Range of Group Size 4.Per Person Per Day
//		feeSchData.permitUnit = datasFromDB.get("permitUnit");
//		String personTypes = datasFromDB.get("personType");
//		if(!StringUtil.isEmpty(personTypes)) {
//			feeSchData.personTypes = personTypes.split(",");
//		}
	}
}

