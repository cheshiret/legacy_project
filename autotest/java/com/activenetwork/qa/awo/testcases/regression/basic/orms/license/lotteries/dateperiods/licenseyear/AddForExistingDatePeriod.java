package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.dateperiods.licenseyear;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo.Dates;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrDatePeriodLicenseYearsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrDatePeriodsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrEditDatePeriodLicenseYearWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the Date Period License Year adding correctly during updating an existing Date Period process
 * @Preconditions:
 * @SPEC: Add date period license year [TC:046991]
 * 				from date not specified [TC:046993]
 * 				unvalid date for from date [TC:046995]
 * 				unvalid date for to date [TC:046996]
 * 				to date is less than from date [TC:046997]
 * 				at least one date range has not been specified [TC:046998]
 * 				Duplicate date range [TC:046999]
 * 				Duplicate license year for same date period [TC:047000]
 * 				check the license year list [TC:047022]
 * @Task#: AUTO-1260
 * 
 * @author qchen
 * @Date  Sep 28, 2012
 */
public class AddForExistingDatePeriod extends LicenseManagerTestCase {
	
	private DatePeriodInfo datePeriod = new DatePeriodInfo();
	private List<DatePeriodLicenseYearInfo> licenseYearList = new ArrayList<DatePeriodLicenseYearInfo>();
	private String nextYear, yearAfterNextYear;
	
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
		//clean up former Date Period License Year records to avoid conflict
		lm.deactivateDatePeriodLicenseYear(nextYear, yearAfterNextYear);
		List<String> ids = lm.addDatePeriodLicenseYears(licenseYearList);
		this.setDatePeriodLicenseYearID(ids);
		
		//verify Date Period License Year list info
		this.verifyDatePeriodLicenseYearListInfo(licenseYearList);
		
		//verify License Year details info
		for(int i = 0; i < licenseYearList.size(); i ++) {
			lm.gotoDatePeriodLicenseYearDetailsWidget(licenseYearList.get(i).getLicenseYear());
			this.verifyDatePeriodLicenseYearDetailsInfo(licenseYearList.get(i));
			lm.gotoDatePeriodLicenseYearListPageFromDetailsWidget();
		}
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		//Date Period info
		datePeriod.setCode("AFE");
		datePeriod.setDescription(this.caseName);
		DatePeriodLicenseYearInfo licenseYear = new DatePeriodLicenseYearInfo();
		licenseYear.setLicenseYear(String.valueOf(DateFunctions.getCurrentYear()));
		Dates d1 = licenseYear.new Dates();
		d1.setFromDate(DateFunctions.getToday(timeZone));
		d1.setToDate(DateFunctions.getDateAfterGivenDay(d1.getFromDate(), 1));
		d1.setCategory("Auto");//if this category doesn't exist, it will be automatically added
		List<Dates> ds = new ArrayList<DatePeriodLicenseYearInfo.Dates>();
		ds.add(d1);
		licenseYear.setDates(ds);
		List<DatePeriodLicenseYearInfo> licenseYears = new ArrayList<DatePeriodLicenseYearInfo>();
		licenseYears.add(licenseYear);
		datePeriod.setLicenseYears(licenseYears);
		
		//Date Period License Year info
		DatePeriodLicenseYearInfo datePeriodLicenseYear1 = new DatePeriodLicenseYearInfo();
		Dates dates1 = datePeriodLicenseYear1.new Dates();
		dates1.setFromDate(DateFunctions.getDateAfterToday(365, timeZone));
		dates1.setToDate(DateFunctions.getDateAfterGivenDay(dates1.getFromDate(), 1));
		dates1.setCategory("Auto");
		List<Dates> ds1 = new ArrayList<DatePeriodLicenseYearInfo.Dates>();
		ds1.add(dates1);
		nextYear = String.valueOf(DateFunctions.getCurrentYear() + 1);
		datePeriodLicenseYear1.setLicenseYear(nextYear);//IMPORTANT
		datePeriodLicenseYear1.setDates(ds1);
		datePeriodLicenseYear1.setStatus(OrmsConstants.ACTIVE_STATUS);
		datePeriodLicenseYear1.setCreationDateTime(DateFunctions.getToday(timeZone));
		datePeriodLicenseYear1.setCreationUser(DataBaseFunctions.getLoginUserName(login.userName));
		
		DatePeriodLicenseYearInfo datePeriodLicenseYear2 = new DatePeriodLicenseYearInfo();
		Dates dates2 = datePeriodLicenseYear2.new Dates();
		dates2.setFromDate(DateFunctions.getDateAfterToday(365*2, timeZone));
		dates2.setToDate(DateFunctions.getDateAfterGivenDay(dates2.getFromDate(), 1));
		dates2.setCategory(dates1.getCategory());
		List<Dates> ds2 = new ArrayList<DatePeriodLicenseYearInfo.Dates>();
		ds2.add(dates2);
		yearAfterNextYear = String.valueOf(DateFunctions.getCurrentYear() + 2);
		datePeriodLicenseYear2.setLicenseYear(yearAfterNextYear);//IMPORTANT
		datePeriodLicenseYear2.setDates(ds2);
		datePeriodLicenseYear2.setStatus(OrmsConstants.ACTIVE_STATUS);
		datePeriodLicenseYear2.setCreationDateTime(DateFunctions.getToday(timeZone));
		datePeriodLicenseYear2.setCreationUser(DataBaseFunctions.getLoginUserName(login.userName));
		
		licenseYearList.add(datePeriodLicenseYear1);
		licenseYearList.add(datePeriodLicenseYear2);
	}
	
	private void setDatePeriodLicenseYearID(List<String> ids) {
		for(int i = 0; i < licenseYearList.size(); i ++) {
			licenseYearList.get(i).setId(ids.get(i));
		}
	}
	
	private void verifyDatePeriodLicenseYearListInfo(List<DatePeriodLicenseYearInfo> expected) {
		LicMgrDatePeriodLicenseYearsListPage licenseYearPage = LicMgrDatePeriodLicenseYearsListPage.getInstance();
		
		logger.info("Verify Date Period License Year list info.");
		boolean result = licenseYearPage.compareAllDatePeriodLicenseYearInfo(expected);
		if(!result) {
			throw new ErrorOnPageException("Date Period License Year list info is incorrect, please refer log for details info.");
		} else logger.info("Date Period License Year list info is correct.");
	}
	
	private void verifyDatePeriodLicenseYearDetailsInfo(DatePeriodLicenseYearInfo expected) {
		LicMgrEditDatePeriodLicenseYearWidget detailsWidget = LicMgrEditDatePeriodLicenseYearWidget.getInstance();
		
		logger.info("Verify Date Period License Year details info.");
		boolean result = detailsWidget.compareLicenseYearDetailsInfo(expected);
		if(!result) {
			throw new ErrorOnPageException("Date Period License Year details info is incorrect, please refer log for details info.");
		} else logger.info("Date Period License Year details info is correct.");
	}
}
