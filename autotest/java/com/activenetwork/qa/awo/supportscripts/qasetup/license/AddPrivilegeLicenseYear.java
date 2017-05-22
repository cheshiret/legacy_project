package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddPriLicenseYearFunction;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * This support script only support for adding "Active"  privilege product license year info.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author bzhang
 * @Date  Feb 7, 2012
 */
public class AddPrivilegeLicenseYear extends SetupCase{
	private TimeZone timeZone = null;
	private String schema = "";
	private int numOfYearsAfterCurrent = 1;//IMPORTANT: this variable will make script add license years records from current year to [current year + numOfYearsAfterCurrent]
	private LicenseYear licenseYear = new LicenseYear();
	private Object[] args = new Object[6];
	private AddPriLicenseYearFunction addPriLYFunc = new AddPriLicenseYearFunction();
	private boolean isSellFromCurrent, isSameSalesDates=false;
	
	@Override
	public void executeSetup() {
		addPriLYFunc.execute(args);
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_prvi_license_year";
	}

	@Override
	public void readDataFromDatabase() {
		args[0] = datasFromDB.get("contract");
		args[1] = datasFromDB.get("location");
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + args[0].toString().split("Contract")[0].trim();
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		licenseYear.productCode = datasFromDB.get("privilegeCode");
		licenseYear.productName = datasFromDB.get("privilegeName");	
		licenseYear.status = "Active";  //need to add logical handle the inactive status when there is business need
		licenseYear.locationClass = datasFromDB.get("locationClass");
		
		//Quentin[20131018] to handle adding more than 1 license year records
		licenseYear.licYear = datasFromDB.get("LY");
		if(StringUtil.isEmpty(licenseYear.licYear)|| Integer.parseInt(licenseYear.licYear) < DateFunctions.getCurrentYear()) {
			licenseYear.licYear = LicenseManager.getInstance().getFiscalYear(schema);
		}
		String num = datasFromDB.get("numOfYearsAfterCurrent");
		if(!StringUtil.isEmpty(num)) {
			numOfYearsAfterCurrent = Integer.parseInt(num.trim());
		}
		
		licenseYear.sellFromDate = datasFromDB.get("sellFromDate");
		if(licenseYear.sellFromDate.trim().length() < 1) {
			licenseYear.sellFromDate = DateFunctions.getDateAfterToday(-2);
		}
		licenseYear.sellFromTime = datasFromDB.get("sellFromHHMM");
		if(licenseYear.sellFromTime.trim().length() < 1) {
			licenseYear.sellFromTime = "12:00";
		}
		licenseYear.sellFromAmPm = datasFromDB.get("sellFromAMPM");
		
		licenseYear.sellToDate = datasFromDB.get("sellToDate");
		if(licenseYear.sellToDate.trim().length() < 1) {
			licenseYear.sellToDate = DateFunctions.getDateAfterToday(365, timeZone);
		}
		licenseYear.sellToTime = datasFromDB.get("sellToHHMM");
		if(licenseYear.sellToTime.trim().length() < 1) {
			licenseYear.sellToTime = "11:59";
		}
		licenseYear.sellToAmPm = datasFromDB.get("sellToAMPM");
		
		licenseYear.validFromDate = datasFromDB.get("validFromDate");
		licenseYear.validFromTime = datasFromDB.get("validFromHHMM");
		licenseYear.validFromAmPm = datasFromDB.get("validFromAMPM");
		
		licenseYear.validToDate = datasFromDB.get("validToDate");
		licenseYear.validToTime = datasFromDB.get("validToHHMM");
		licenseYear.validToAmPm = datasFromDB.get("validToAMPM");
		
		isSellFromCurrent = Boolean.valueOf(datasFromDB.get("isSellFromCurrent")); //Lesley[20131024]: make sure the license year's sell from date is current
		isSameSalesDates = Boolean.valueOf(datasFromDB.get("ISSAMESALESDATES"));//Jane[2014-5-8]: sometimes, license years records were same sell from date and end date 
		
		args[2] = licenseYear;
		args[3] = numOfYearsAfterCurrent;
		args[4] = isSellFromCurrent;
		args[5] = isSameSalesDates;
	}
}
