package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.dateperiods.licenseyear;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo.Dates;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrDatePeriodLicenseYearsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrEditDatePeriodLicenseYearWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the Date Period License Year adding correctly during adding Date Period process
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
public class AddForNewDatePeriod extends LicenseManagerTestCase {
	
	private DatePeriodInfo datePeriod = new DatePeriodInfo();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoDatePeriodListPgFromLotteriesProdListPg();
		//add Date Period License Year
		lm.addDatePeriods(datePeriod);
		
		//goto Date Period details - Date Period License Years tab to verify License Year list info
		lm.gotoDatePeriodDetailPgFromListPg(datePeriod.getCode());
		lm.gotoDatePeriodLicenseYearTab();
		String licenseYearId = LicMgrDatePeriodLicenseYearsListPage.getInstance().getLicenseYearID(datePeriod.getLicenseYears().get(0).getLicenseYear());
		datePeriod.getLicenseYears().get(0).setId(licenseYearId);
		this.verifyDatePeriodLicenseYearListInfo(datePeriod.getLicenseYears());
		
		//verify License Year details info
		for(int i = 0; i < datePeriod.getLicenseYears().size(); i ++) {
			lm.gotoDatePeriodLicenseYearDetailsWidget(datePeriod.getLicenseYears().get(i).getLicenseYear());
			this.verifyDatePeriodLicenseYearDetailsInfo(datePeriod.getLicenseYears().get(i));//TODO DEFECT-37561
			lm.gotoDatePeriodLicenseYearListPageFromDetailsWidget();
		}
		
		//clean up
		lm.deactivateDatePeriod();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
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
		dates2.setFromDate(DateFunctions.getDateAfterGivenDay(dates1.getFromDate(), 2));
		dates2.setToDate(DateFunctions.getDateAfterGivenDay(dates2.getFromDate(), 1));
		dates2.setCategory(dates1.getCategory());
		List<Dates> dates = new ArrayList<DatePeriodLicenseYearInfo.Dates>();
		dates.add(dates1);
		dates.add(dates2);
		licenseYear.setDates(dates);
		licenseYear.setStatus(OrmsConstants.ACTIVE_STATUS);
		licenseYear.setCreationDateTime(DateFunctions.getToday(timeZone));
		licenseYear.setCreationUser(DataBaseFunctions.getLoginUserName(login.userName));
		List<DatePeriodLicenseYearInfo> licenseYears = new ArrayList<DatePeriodLicenseYearInfo>();
		licenseYears.add(licenseYear);
		datePeriod.setLicenseYears(licenseYears);
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
