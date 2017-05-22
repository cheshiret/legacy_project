package com.activenetwork.qa.awo.supportscripts.qasetup.finance;

import com.activenetwork.qa.awo.datacollection.legacy.feeData.D_GroupUseFee;
import com.activenetwork.qa.awo.datacollection.legacy.feeData.IncrementsOrRangeFeeData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.AddGroupUseFeeScheduleFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description : Functional Test Script
 * 
 * @since 2010/02/06
 * @author dsui
 */
public class AddGroupUseFeeSchedule extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private D_GroupUseFee fd = new D_GroupUseFee();
	private AddGroupUseFeeScheduleFunction addGrpUseFeeFunc = new AddGroupUseFeeScheduleFunction();

	public void wrapParameters(Object[] param) {
//		queryDataSql = "";//override this if you want to run setup for specific row,it will run all the records by default

		// Initialize login informaiton
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");

		dataTableName = "d_fin_group_usefee_sched";
	}

	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = fd;
		addGrpUseFeeFunc.execute(args);
	}

	public void readDataFromDatabase() {
		this.fd = new D_GroupUseFee();

		login.contract = datasFromDB.get("Contract");
		login.location = datasFromDB.get("logLocation");

		fd.locationCategory = datasFromDB.get("Location Category");
		fd.location = datasFromDB.get("Location").toString().toUpperCase();

		fd.productCategory = datasFromDB.get("Product Category");
		fd.feeType = datasFromDB.get("Fee Type");
		fd.loop = datasFromDB.get("groupLoop").trim().toString();
		fd.rateType = datasFromDB.get("Rate Type");

		fd.productGroup = datasFromDB.get("Product Group").trim().toString();
		fd.product = datasFromDB.get("Product").replaceAll(" - ", "-");
		fd.attrType = datasFromDB.get("attributeType");
		// if date is null, set the date as default value,ignore past date,as Sophia's data need setup for specific date
		fd.effectDate = datasFromDB.get("Effective Date");
		if(StringUtil.isEmpty(fd.effectDate)){
			fd.effectDate = DateFunctions.getDateAfterToday(-5);
		}
		fd.startDate = datasFromDB.get("Start Date");
		if(StringUtil.isEmpty(fd.startDate)){
			fd.startDate = DateFunctions.getDateAfterToday(-365);
		}
		fd.endDate = datasFromDB.get("End Date");
		if(StringUtil.isEmpty(fd.endDate)){
			fd.endDate = DateFunctions.getDateAfterToday(365);
		}

		fd.season = datasFromDB.get("Season").trim();

		fd.salesChannel = datasFromDB.get("Sales Channel").trim();

		fd.inOutState = datasFromDB.get("In Out State").trim();

		fd.unitOfStay = datasFromDB.get("Unit Of Stay");

		fd.baseRate = datasFromDB.get("AnyUnit_Default").replace('$', ' ')
				.trim();
		if (fd.baseRate == null || fd.baseRate.length() < 1)
			fd.baseRate = "0";

		fd.monRate = datasFromDB.get("Monday Fee").replace('$', ' ').trim();
		fd.tuesRate = datasFromDB.get("Tuesday Fee").replace('$', ' ').trim();
		fd.wedRate = datasFromDB.get("Wednesday Fee").replace('$', ' ').trim();
		fd.thurRate = datasFromDB.get("Thursday Fee").replace('$', ' ').trim();
		fd.friRate = datasFromDB.get("Friday Fee").replace('$', ' ').trim();
		fd.satRate = datasFromDB.get("Saturday Fee").replace('$', ' ').trim();
		fd.sunRate = datasFromDB.get("Sunday Fee").replace('$', ' ').trim();

		fd.acctCode = datasFromDB.get("Account Code");
		fd.occupantIncrementOrRange = datasFromDB.get("OccupantIncrementsRanges");

		String ir = datasFromDB.get("IncrementRange_ForEveryOcc");
		if (ir != null && ir.length() > 0) {
			String[] tokens = ir.split("\\|");
			fd.ir = new IncrementsOrRangeFeeData[tokens.length];
			for (int i = 0; i < tokens.length; i++) {

				// String[] s
				// =RegularExpression.getMatches(tokens[i],"\\d+(\\.\\d{0,2})?");
				String[] s = tokens[i].split("\\;");
				fd.ir[i] = new IncrementsOrRangeFeeData();
				for (int j = 0; j < s.length; j++) {
					if (s[j].contains("Increment") || s[j].contains("Range")) {
						fd.ir[i].occIncrementRange = s[j].split("\\:")[1];
					}
					if (s[j].contains("Price")) {
						fd.ir[i].defaultRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Mon")) {
						fd.ir[i].monRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Tue")) {
						fd.ir[i].tuesRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Wed")) {
						fd.ir[i].wedRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Thu")) {
						fd.ir[i].thurRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Fri")) {
						fd.ir[i].friRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Sat")) {
						fd.ir[i].satRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Sun")) {
						fd.ir[i].sunRate = s[j].split("\\:")[1];
					}
				}
			}
		}

		fd.vehicleIncrementOrRange = datasFromDB.get("VehicleIncrementsRanges");
		String ivehicle = datasFromDB.get("IncrementRange_ForEveryVehi");
		if (ivehicle != null && ivehicle.length() > 0) {
			String[] tokens = ivehicle.split("\\|");
			fd.ivehicle = new IncrementsOrRangeFeeData[tokens.length];
			for (int i = 0; i < tokens.length; i++) {
				String[] s = tokens[i].split("\\;");
				fd.ivehicle[i] = new IncrementsOrRangeFeeData();
				for (int j = 0; j < s.length; j++) {
					if (s[j].contains("Increment") || s[j].contains("Range")) {
						fd.ivehicle[i].vehiIncrementRange = s[j].split("\\:")[1];
					}
					if (s[j].contains("Price")) {
						fd.ivehicle[i].defaultRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Mon")) {
						fd.ivehicle[i].monRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Tue")) {
						fd.ivehicle[i].tuesRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Wed")) {
						fd.ivehicle[i].wedRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Thu")) {
						fd.ivehicle[i].thurRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Fri")) {
						fd.ivehicle[i].friRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Sat")) {
						fd.ivehicle[i].satRate = s[j].split("\\:")[1];
					}
					if (s[j].contains("Sun")) {
						fd.ivehicle[i].sunRate = s[j].split("\\:")[1];
					}
				}
			}
		}
	}
}
