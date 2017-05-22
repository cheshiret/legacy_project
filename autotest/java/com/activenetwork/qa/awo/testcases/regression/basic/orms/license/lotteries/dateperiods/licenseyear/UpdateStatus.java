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
 * @Description:This case is used to verify the update license year status during edit date period of date period for hunt
 * @LinkSetUp: none
 * @SPEC:[TC:047001] Edit date period License year 
 * @Task#: Auto-2065
 * @author Phoebe Chen
 * @Date  February 10, 2014
 */
public class UpdateStatus extends LicenseManagerTestCase {
	private DatePeriodInfo datePeriod = new DatePeriodInfo();
	private DatePeriodLicenseYearInfo licenseYear = new DatePeriodLicenseYearInfo();
	private LicMgrEditDatePeriodLicenseYearWidget editWidget = LicMgrEditDatePeriodLicenseYearWidget.getInstance();
	LicMgrDatePeriodLicenseYearsListPage listPage = LicMgrDatePeriodLicenseYearsListPage.getInstance();
	private TimeZone timeZone;
	
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
		lm.deactivateDatePeriodLicenseYear(datePeriod.getLicenseYears().get(0).getLicenseYear());
	    licenseYear.setId(lm.addDatePeriodLicenseYear(licenseYear));
		
		//Update to the opposite status
	    datePeriod.getLicenseYears().get(0).setStatus(INACTIVE_STATUS);
		lm.updateDatePeriodLicenseYearStatus(datePeriod.getLicenseYears().get(0).getLicenseYear(),  datePeriod.getLicenseYears().get(0).getStatus());
		
		listPage.searchLicenseYear(datePeriod.getLicenseYears().get(0).getStatus(), datePeriod.getLicenseYears().get(0).getLicenseYear());
		this.verifyYearStatusInList();
		lm.gotoDatePeriodLicenseYearDetailsWidget(datePeriod.getLicenseYears().get(0).getLicenseYear());

		this.verifyYearStatusInWidget();
		lm.gotoDatePeriodLicenseYearListPageFromDetailsWidget();
		
		//Update the status to original status
		datePeriod.getLicenseYears().get(0).setStatus(ACTIVE_STATUS);
		lm.updateDatePeriodLicenseYearStatus(datePeriod.getLicenseYears().get(0).getLicenseYear(),  datePeriod.getLicenseYears().get(0).getStatus());
		
		listPage.searchLicenseYear(datePeriod.getLicenseYears().get(0).getStatus(), datePeriod.getLicenseYears().get(0).getLicenseYear());
		this.verifyYearStatusInList();
		lm.gotoDatePeriodLicenseYearDetailsWidget(datePeriod.getLicenseYears().get(0).getLicenseYear());
		
		this.verifyYearStatusInWidget();
		lm.gotoDatePeriodLicenseYearListPageFromDetailsWidget();
		
		lm.logOutLicenseManager();
	}

	private void verifyYearStatusInWidget() {
		String actStatus = editWidget.getStatus();
		if(!actStatus.equalsIgnoreCase(datePeriod.getLicenseYears().get(0).getStatus())){
			throw new ErrorOnPageException("Date Period License Year status is incorrect in list page", datePeriod.getLicenseYears().get(0).getLicenseYear(), actStatus);
		} 
		else logger.info("Date Period License Year status is updated correct in list page.");
	}

	private void verifyYearStatusInList() {
		String actStatus = listPage.getLicenseYearStatus(datePeriod.getLicenseYears().get(0).getLicenseYear());
		if(!actStatus.equalsIgnoreCase(datePeriod.getLicenseYears().get(0).getStatus())){
			throw new ErrorOnPageException("Date Period License Year status is incorrect in widget", datePeriod.getLicenseYears().get(0).getLicenseYear(), actStatus);
		} 
		else logger.info("Date Period License Year status is updated correct in widget.");
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		this.initialDatePeriodInfo();
	}
	
	private void initialDatePeriodInfo() {
		//Date Period info
		datePeriod.setCode("EY4"); 
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
