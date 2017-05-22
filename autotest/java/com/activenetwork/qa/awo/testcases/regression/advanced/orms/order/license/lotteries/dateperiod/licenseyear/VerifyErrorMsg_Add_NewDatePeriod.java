package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.dateperiod.licenseyear;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo.Dates;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrAddDatePeriodsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrDatePeriodsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Verify the error messages when adding a new Date Period
 * @Preconditions:
 * @SPEC: from date not specified [TC:046993]
 * 				unvalid date for from date [TC:046995]
 * 				unvalid date for to date [TC:046996]
 * 				to date is less than from date [TC:046997]
 * 				Duplicate date range [TC:046999]
 * 				Duplicate license year for same date period [TC:047000]
 * @Task#: AUTO-1260
 * 
 * @author qchen
 * @Date  Sep 28, 2012
 */
public class VerifyErrorMsg_Add_NewDatePeriod extends LicenseManagerTestCase {
	
	private DatePeriodInfo datePeriod = new DatePeriodInfo();
	private String errorMsg_fromDateIsNull, errorMsg_toDateLessThanFromDate, errorMsg_duplicateDateRange;
	private LicMgrAddDatePeriodsPage detailsPage = LicMgrAddDatePeriodsPage.getInstance();
	private TimeZone timeZone;
	private boolean result = true;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoDatePeriodListPgFromLotteriesProdListPg();
		
		//1. From Date is not specified
		datePeriod.getLicenseYears().get(0).getDates().get(0).setFromDate(StringUtil.EMPTY);
		String actualMsg = lm.addDatePeriods(datePeriod);
		result &= MiscFunctions.compareResult("Error message - From Date is not specified", errorMsg_fromDateIsNull, actualMsg);
		
		//2. From Date is invalid date
		lm.gotoAddDatePeriodPgFromListPg();
		result &= detailsPage.isFromDateTextFieldValid(new String[] {"AUTO", "!@#$", "Sep 31 2020"});
		
		//3. To Date is invalid date
		result &= detailsPage.isToDateTextFieldValid(new String[] {"Test", "%^&*", "Jun 31 2020"});
		this.gotoDatePeriodListFromAddDetailsPage();
		
		//4. To Date is less than From Date
		this.initialDatePeriodInfo();
		String fromDate = datePeriod.getLicenseYears().get(0).getDates().get(0).getFromDate();
		datePeriod.getLicenseYears().get(0).getDates().get(0).setToDate(DateFunctions.getDateAfterGivenDay(fromDate, -1));
		actualMsg = lm.addDatePeriods(datePeriod);
		result &= MiscFunctions.compareResult("Error message - To Date is less than From Date", errorMsg_toDateLessThanFromDate, actualMsg);
		this.gotoDatePeriodListFromAddDetailsPage();
		
		//5. Duplicate date range
		this.initialDatePeriodInfo();
		actualMsg = lm.addDatePeriods(datePeriod);
		result &= MiscFunctions.compareResult("Error message - Duplicate Date Range", errorMsg_duplicateDateRange, actualMsg);
		this.gotoDatePeriodListFromAddDetailsPage();
		
		//6. Check license year drop down list - a list of years starting from the current calendar year minus one year, and up to 10 years from the current calendar year.
		lm.gotoAddDatePeriodPgFromListPg();
		result &= detailsPage.verifyLicenseYearOption();
		this.gotoDatePeriodListFromAddDetailsPage();
		
		//final check
		if(!result) {
			throw new ErrorOnPageException("Not all checkpoints passed, please refer to log for details info.");
		} logger.info("All checkpoints passed.");
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		//expected error messages
		errorMsg_fromDateIsNull = "From Date is required. Please specify the From Date.";
		errorMsg_toDateLessThanFromDate = "To Date must be on or after From Date. Please re-enter.";
		//errorMsg_notDateRange = "At least one Date record consisting of From Date must be specified. Please re-enter.";
		errorMsg_duplicateDateRange = "Duplicate Dates (with the same From Date, To Date, and Category) have been specified. Please re-enter.";
		
		this.initialDatePeriodInfo();
	}
	
	private void initialDatePeriodInfo() {
		//Date Period info
		datePeriod.setCode(StringUtil.getRandomString(3, true));
		datePeriod.setDescription("Automation Regression" + datePeriod.getCode());
		//Date Period License Year info
		DatePeriodLicenseYearInfo licenseYear = new DatePeriodLicenseYearInfo();
		licenseYear.setLicenseYear(String.valueOf(DateFunctions.getCurrentYear()));
		
		Dates dates1 = licenseYear.new Dates();
		dates1.setFromDate(DateFunctions.getToday(timeZone));
		dates1.setToDate(DateFunctions.getDateAfterGivenDay(dates1.getFromDate(), 1));
		dates1.setCategory("Auto");//if this category doesn't exist, it will be automatically added
		Dates dates2 = licenseYear.new Dates();
		dates2.setFromDate(dates1.getFromDate());
		dates2.setToDate(dates1.getToDate());
		dates2.setCategory(dates1.getCategory());
		
		List<Dates> dates = new ArrayList<DatePeriodLicenseYearInfo.Dates>();
		dates.add(dates1);
		dates.add(dates2);
		licenseYear.setDates(dates);
		List<DatePeriodLicenseYearInfo> licenseYears = new ArrayList<DatePeriodLicenseYearInfo>();
		licenseYears.add(licenseYear);
		
		datePeriod.setLicenseYears(licenseYears);
	}
	
	private void gotoDatePeriodListFromAddDetailsPage() {
		LicMgrAddDatePeriodsPage.getInstance().clickCancel();
		ajax.waitLoading();
		LicMgrDatePeriodsListPage.getInstance().waitLoading();
	}
}
