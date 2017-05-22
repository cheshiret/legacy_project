package com.activenetwork.qa.awo.supportscripts.support.financemgr;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
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
public class AddUseOrAttrFeeSchedule extends SupportCase {
	/**
	 * Script Name : <b>AddUseOrAttrFeeSchedule</b> Generated : <b>Feb 11, 2010
	 * 12:16:59 AM</b> Description : Functional Test Script Original Host :
	 * WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2010/02/11
	 * @author Sara Wang
	 */

	private FinanceManager fin = FinanceManager.getInstance();
	private FinMgrFeeMainPage finFeeMainPg = FinMgrFeeMainPage.getInstance();

	private LoginInfo login = new LoginInfo();
	private FeeScheduleData feeSchData = new FeeScheduleData();
	private String feeScheduleId = "";
	private boolean loggined = false;

	public void wrapParameters(Object[] param) {

		startpoint = 292; // the start point in the data pool
		endpoint = 292; // the end point in the data pool

		dataSource = "d_fin_use_attr_fee_sched";

		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");
		login.envType = "QA";

		// log informaiton
		logMsg = new String[8];
		logMsg[0] = cursor + " ";
		logMsg[1] = "location";
		logMsg[2] = "locationCategory";
		logMsg[3] = "productCategory";
		logMsg[4] = "feeType";

		logMsg[5] = "feeScheduleId";
		logMsg[6] = "ActiveOrInactive";
		logMsg[7] = "result";

	}

	public void execute() {
		// Login Finance Manager
		if ((!loggined) || (loggined && !finFeeMainPg.exists())) {
			fin.loginFinanceManager(login);
			loggined = true;
			fin.gotoFeeMainPage();
		}

		// add use fee or attribute Fee schedule
		feeScheduleId = fin.addNewFeeSchedule(feeSchData);

		// Activate RA Fee Schedule
		finFeeMainPg.changeScheduleStatus(feeScheduleId, "Active");

		// get current fee schedule's status
		if (finFeeMainPg.isFeeScheduleActive(feeScheduleId)
				&& feeScheduleId.length() > 0) {
			logMsg[5] = feeScheduleId;
			logMsg[6] = "Active";
			logMsg[7] = "Success";

		} else {
			logMsg[5] = feeScheduleId;
			logMsg[6] = "Inactive";
			logMsg[7] = "Fail";
		}
	}

	public void getNextData() {
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("logLocation");

		feeSchData.location = datasFromDB.get("location");
		feeSchData.locationCategory = datasFromDB.get("locationCategory");
		feeSchData.productCategory = datasFromDB.get("productCategory");
		feeSchData.feeType = datasFromDB.get("feeType");
		feeSchData.loop = datasFromDB.get("loop");
		feeSchData.productGroup = datasFromDB.get("productGroup");
		feeSchData.product = datasFromDB.get("product");
		feeSchData.effectDate = datasFromDB.get("effectiveDate");
		feeSchData.startInv = datasFromDB.get("startInv");
		
		String endDate = datasFromDB.get("endInv");
		// if inventory end date is past date, set the end date as today+365
		if(endDate.equals("")||DateFunctions.compareDates(endDate, DateFunctions.getToday())<0){
			endDate = DateFunctions.getDateAfterToday(365);
		}
		feeSchData.endInv = endDate;
		feeSchData.season = datasFromDB.get("season");
		feeSchData.salesChannel = datasFromDB.get("salesChannel");
		feeSchData.state = datasFromDB.get("state");
		feeSchData.custType = datasFromDB.get("customerType");
		feeSchData.unitOfStay = datasFromDB.get("unitOfStay");
		feeSchData.acctCode = datasFromDB.get("accountCode");
		feeSchData.attrType = datasFromDB.get("attributeType");
		feeSchData.attrValue = datasFromDB.get("attributeValue");

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

		logMsg[0] = cursor + " ";
		logMsg[1] = feeSchData.location;
		logMsg[2] = feeSchData.locationCategory;
		logMsg[3] = feeSchData.productCategory;
		logMsg[4] = feeSchData.feeType;
		logMsg[5] = "Unknown";
		logMsg[6] = "Inactive";
		logMsg[7] = "Fail due to error";
	}
}
