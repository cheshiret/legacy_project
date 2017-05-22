package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.dateperiod.licenseyear;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo.Dates;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrDatePeriodsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrEditDatePeriodLicenseYearWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify the error message when edit date period of date period for hunt
 * @LinkSetUp: none
 * @SPEC:[TC:047025] Validation Check 
 * @Task#: Auto-2065
 * @author Phoebe Chen
 * @Date  February 10, 2014
 */
public class VerfifyErrorMsg_Edit extends LicenseManagerTestCase {

	private DatePeriodInfo datePeriod = new DatePeriodInfo();
	private DatePeriodLicenseYearInfo licenseYear = new DatePeriodLicenseYearInfo();
	private Dates dates1 = licenseYear.new Dates(), dates_duplicate = licenseYear.new Dates();
	private String errorMsg_toDateLessThanFromDate,errorMsg_fromDateEmpty, errorMsg_duplicateDateRange;
	private LicMgrEditDatePeriodLicenseYearWidget editWidget = LicMgrEditDatePeriodLicenseYearWidget.getInstance();
	private TimeZone timeZone;
	private boolean result = true;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoDatePeriodListPgFromLotteriesProdListPg();
		//check if Date Period exists, if not, add a new one
		if(	!LicMgrDatePeriodsListPage.getInstance().isDatePeriodExists(datePeriod.getCode())) {
			lm.addDatePeriods(datePeriod);
		}
		
		lm.gotoDatePeriodDetailPgFromListPg(datePeriod.getCode());
		lm.gotoDatePeriodLicenseYearDetailsWidget(datePeriod.getLicenseYears().get(0).getLicenseYear());
		
		//1. Duplicate date range
		datePeriod.getLicenseYears().get(0).getDates().add(dates_duplicate);  //There are two date range with the same date(from, to)
		String actualMsg = editWidget.setDatesRangeAndGetErrorMsg(datePeriod.getLicenseYears().get(0).getDates());
		result &= MiscFunctions.compareResult("Error message - Duplicate date range", errorMsg_duplicateDateRange, actualMsg);
		
		//2. No date Range
		datePeriod.getLicenseYears().get(0).getDates().remove(1);
		datePeriod.getLicenseYears().get(0).getDates().get(0).setFromDate(StringUtil.EMPTY); //No date range date
		actualMsg = editWidget.setDatesRangeAndGetErrorMsg(datePeriod.getLicenseYears().get(0).getDates());
		result &= MiscFunctions.compareResult("Error message - No date range", errorMsg_fromDateEmpty, actualMsg);
		
		//3. To Date is less than From Date
		datePeriod.getLicenseYears().get(0).getDates().get(0).setFromDate(DateFunctions.getToday(timeZone));
		datePeriod.getLicenseYears().get(0).getDates().get(0).setToDate(DateFunctions.getDateAfterGivenDay(dates1.getFromDate(), -1));
		actualMsg = editWidget.setDatesRangeAndGetErrorMsg(datePeriod.getLicenseYears().get(0).getDates());
		result &= MiscFunctions.compareResult("Error message - To Date is less than From Date", errorMsg_toDateLessThanFromDate, actualMsg);
		
		//4. From Date is invalid date
		result &= editWidget.isFromDateTextFieldValid(new String[] {"Sep 31 2020"});
		
		//5. To Date is invalid date
		result &= editWidget.isToDateTextFieldValid(new String[] {"Jun 31 2020"});
		lm.gotoDatePeriodLicenseYearListPageFromDetailsWidget();
				
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
		
		this.initialDatePeriodInfo();
		
		//expected error messages
		/*errorMsg_fromDateIsInvalid = "From Date must be a valid Date.  Please re-enter.";
		errorMsg_toDateIsInvalid = "To Date must be a valid Date.  Please re-enter.";*/
		errorMsg_toDateLessThanFromDate = "To Date must be on or after From Date. Please re-enter.";
//		errorMsg_removeDates = "At least one Date record consisting of From Date must be specified.  Please re-enter." ;
		errorMsg_fromDateEmpty = "From Date is required. Please specify the From Date." ;
		errorMsg_duplicateDateRange = "Duplicate Dates (with the same From Date, To Date, and Category) have been specified. Please re-enter.";
	}
	
	private void initialDatePeriodInfo() {
		//Date Period info
		datePeriod.setCode("EY2");
		datePeriod.setDescription(this.caseName);
		//Date Period License Year info
		licenseYear.setLicenseYear(String.valueOf(DateFunctions.getCurrentYear()));
		
		dates1.setFromDate(DateFunctions.getToday(timeZone));
		dates1.setToDate(DateFunctions.getDateAfterGivenDay(dates1.getFromDate(), 1));
		dates1.setCategory("Auto");//if this category doesn't exist, it will be automatically added
		
		List<Dates> dates = new ArrayList<DatePeriodLicenseYearInfo.Dates>();
		dates.add(dates1);
		licenseYear.setDates(dates);
		List<DatePeriodLicenseYearInfo> licenseYears = new ArrayList<DatePeriodLicenseYearInfo>();
		licenseYears.add(licenseYear);
		
		datePeriod.setLicenseYears(licenseYears);
		
		dates_duplicate.setFromDate(dates1.getFromDate());
		dates_duplicate.setToDate(dates1.getToDate());
		dates_duplicate.setCategory(dates1.getCategory());
	}
}
