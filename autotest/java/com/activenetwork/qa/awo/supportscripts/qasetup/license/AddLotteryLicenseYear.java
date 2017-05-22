package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddLotteryLicenseYearFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 4, 2013
 */
public class AddLotteryLicenseYear extends SetupCase {

	private LoginInfo login = new LoginInfo();
	private LicenseYear licenseYear = new LicenseYear();
	private int numOfYears = 1;
	private boolean isSellFromCurrent;
	
	@Override
	public void executeSetup() {
		new AddLotteryLicenseYearFunction().execute(new Object[] {login, licenseYear, numOfYears, isSellFromCurrent});
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_lottery_license_year";
		ids = "";
	}
	
	@Override
	public void readDataFromDatabase() {
		//login info
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = datasFromDB.get("Contract");
		login.location = datasFromDB.get("Location");
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		String schema = TestProperty.getProperty(env + ".db.schema.prefix") + login.contract.split("Contract")[0].trim();
				
		//license year
		licenseYear.productCode = datasFromDB.get("ProductCode");
		licenseYear.status = datasFromDB.get("Status");
		licenseYear.locationClass = datasFromDB.get("LocationClass");
		licenseYear.licYear = datasFromDB.get("LicenseYear");
		
		if(StringUtil.isEmpty(licenseYear.licYear) || Integer.parseInt(licenseYear.licYear) < DateFunctions.getCurrentYear()) {
//			licenseYear.licYear = String.valueOf(DateFunctions.getCurrentYear());
			licenseYear.licYear = LicenseManager.getInstance().getFiscalYear(schema); //Lesley[20140324]use fiscal year as License Year to keep consistent with other license year support scripts
		}
		
		licenseYear.sellFromDate = datasFromDB.get("SellFromDate");
		if(StringUtil.isEmpty(licenseYear.sellFromDate)) {
			licenseYear.sellFromDate = DateFunctions.getDateAfterToday(1);
		}
		licenseYear.sellFromTime = datasFromDB.get("SellFromTime");
		if(StringUtil.isEmpty(licenseYear.sellFromTime)) {
			licenseYear.sellFromTime = "12:00";
		}
		licenseYear.sellFromAmPm = datasFromDB.get("SellFromAmPm");
		if(StringUtil.isEmpty(licenseYear.sellFromAmPm)) {
			licenseYear.sellFromAmPm = "AM";
		}
		licenseYear.sellToDate = datasFromDB.get("SellToDate");
		if(StringUtil.isEmpty(licenseYear.sellToDate)) {
			licenseYear.sellToDate = DateFunctions.getDateAfterToday(365);
		}
		licenseYear.sellToTime = datasFromDB.get("SellToTime");
		if(StringUtil.isEmpty(licenseYear.sellToTime)) {
			licenseYear.sellToTime = "11:59";
		}
		licenseYear.sellToAmPm = datasFromDB.get("SellToAmPm");
		if(StringUtil.isEmpty(licenseYear.sellToAmPm)) {
			licenseYear.sellToAmPm = "PM";
		}
		
		
		//IMPORTANT: to repeat add more license years after license year set in db/current year
		String numOfYearsAfterCurrent = datasFromDB.get("numOfYearsAfterCurrent");
		if(!StringUtil.isEmpty(numOfYearsAfterCurrent)) {
			numOfYears = Integer.parseInt(numOfYearsAfterCurrent);
		}
		
		isSellFromCurrent = Boolean.parseBoolean(datasFromDB.get("isSellFromCurrent")); //Lesley[20140324]: make sure the license year's sell from date is current
	}
}
