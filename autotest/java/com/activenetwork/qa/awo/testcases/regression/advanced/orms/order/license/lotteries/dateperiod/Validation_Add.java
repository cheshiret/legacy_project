package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.dateperiod;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo.Dates;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrDatePeriodLicenseYearsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify the ui, mainly the information can not be edit when edit date period of date period for hunt
 * @LinkSetUp: none
 * @SPEC:[TC:049549] Validation Check--code more than three 
 * @Task#: Auto-2065
 * @author Phoebe Chen
 * @Date  February 11, 2014
 */
public class Validation_Add extends LicenseManagerTestCase{
	private DatePeriodInfo datePeriod = new DatePeriodInfo();
	private DatePeriodLicenseYearInfo licenseYear = new DatePeriodLicenseYearInfo();
	LicMgrDatePeriodLicenseYearsListPage listPage = LicMgrDatePeriodLicenseYearsListPage.getInstance();
	private TimeZone timeZone;
	private String errorMsg_moreThanThree;
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		
		lm.gotoDatePeriodListPgFromLotteriesProdListPg();
		boolean passed = true;
		
		String actMsg = lm.addDatePeriods(datePeriod);
		
		passed &= MiscFunctions.compareResult("Error message - Code is more than three letters", errorMsg_moreThanThree, actMsg);
		
		if(!passed){
			throw new ErrorOnPageException("Not all checkpoints passed, please refer to log for details info.");
		}
		logger.info("The validation for adding new date period is correct!");
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		this.initialDatePeriodInfo();
		
		errorMsg_moreThanThree = "Code must consist of one to three letters and/or numbers. Please re-enter.";
	}
	
	private void initialDatePeriodInfo() {
		//Date Period info
		datePeriod.setCode("MORETHAN3");
		datePeriod.setDescription(this.caseName);
		//Date Period License Year info
		licenseYear.setLicenseYear(String.valueOf(DateFunctions.getCurrentYear()));
		
		Dates dates1 = licenseYear.new Dates();
		dates1.setFromDate(DateFunctions.getToday(timeZone));
		dates1.setToDate(DateFunctions.getDateAfterGivenDay(dates1.getFromDate(), 1));
		dates1.setCategory("Auto");//if this category doesn't exist, it will be automatically added
		
		List<Dates> dates = new ArrayList<DatePeriodLicenseYearInfo.Dates>();
		dates.add(dates1);
		licenseYear.setDates(dates);
		licenseYear.setStatus(OrmsConstants.ACTIVE_STATUS);
		List<DatePeriodLicenseYearInfo> licenseYears = new ArrayList<DatePeriodLicenseYearInfo>();
		licenseYears.add(licenseYear);
		
		datePeriod.setLicenseYears(licenseYears);
	}

}
