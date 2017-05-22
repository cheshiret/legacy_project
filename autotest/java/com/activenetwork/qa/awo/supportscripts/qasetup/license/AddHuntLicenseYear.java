package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddHuntLicenseYearFunction;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: 
 * @Preconditions:
 * @SPEC: 
 * 
 * @Task#: 
 * 
 * @author Lesley Wang
 * @Date  Aug 19, 2013
 */
public class AddHuntLicenseYear extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private LicenseYear ly;
	private String huntCode, today, schema;
	private TimeZone timezone;
	private AddHuntLicenseYearFunction function = new AddHuntLicenseYearFunction();
	private int numOfYears = 1;
	private boolean isSellFromCurrent;
	
	@Override
	public void executeSetup() {
		Object[] args = new Object[5];
		args[0] = login;
		args[1] = huntCode;
		args[2] = ly;
		args[3] = numOfYears;
		args[4] = isSellFromCurrent;
		function.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		schema = DataBaseFunctions.getSchemaName(login.contract.replace("Contract", "").trim(), env);
		timezone = DataBaseFunctions.getContractTimeZone(schema);
		
		huntCode = datasFromDB.get("huntCode");
		ly = new LicenseYear();
		ly.prdCategory = datasFromDB.get("prodCategory");
		ly.assignedProd = datasFromDB.get("assignedProd");
		ly.locationClass = datasFromDB.get("locationClass");
		
//		numOfYearsAfterCurrent = datasFromDB.get("numOfYearsAfterCurrent");
//		currentYear = DateFunctions.getCurrentYear();
//		if (!StringUtil.isEmpty(numOfYearsAfterCurrent) && Integer.valueOf(numOfYearsAfterCurrent) > 0) {
//			ly.licYear = String.valueOf(currentYear + Integer.valueOf(numOfYearsAfterCurrent));
//		} else {
//			ly.licYear = String.valueOf(currentYear);
//		}
		
		ly.licYear = datasFromDB.get("LicenseYear");
		if(StringUtil.isEmpty(ly.licYear) || Integer.parseInt(ly.licYear) < DateFunctions.getCurrentYear()) {
			ly.licYear = LicenseManager.getInstance().getFiscalYear(schema); //Lesley[20140324]use fiscal year as License Year to keep consistent with other license year support scripts
		}
		
		today = DateFunctions.getToday(timezone);
		ly.sellFromDate = datasFromDB.get("sellFromDate");
		if(StringUtil.isEmpty(ly.sellFromDate) || DateFunctions.compareDates(today, ly.sellFromDate) == 1) {
			ly.sellFromDate = today;
		}
		
		ly.sellFromTime = datasFromDB.get("sellFromTime"); // can be empty. Will handle when add hunt license year
		ly.sellFromAmPm = datasFromDB.get("sellFromAMPM"); // can be empty. Will handle when add hunt license year
		
		ly.sellToDate = datasFromDB.get("sellToDate");
		if(StringUtil.isEmpty(ly.sellToDate)) {
			ly.sellToDate = DateFunctions.getDateAfterToday(365, timezone);
		}
		ly.sellToTime = datasFromDB.get("sellToHHMM");
		if(StringUtil.isEmpty(ly.sellToTime)) {
			ly.sellToTime = "11:59";
		}
		ly.sellToAmPm = datasFromDB.get("sellToAMPM");
		if(StringUtil.isEmpty(ly.sellToAmPm)) {
			ly.sellToAmPm = "PM";
		}
		
		String numOfYearsAfterCurrent = datasFromDB.get("numOfYearsAfterCurrent");
		if(!StringUtil.isEmpty(numOfYearsAfterCurrent)) {
			numOfYears = Integer.parseInt(numOfYearsAfterCurrent);
		}
		
		isSellFromCurrent = Boolean.parseBoolean(datasFromDB.get("isSellFromCurrent")); //Lesley[20140324]: make sure the license year's sell from date is current
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_hunt_license_year";
	}

}
