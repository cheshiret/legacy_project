package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.dateperiod.licenseyear;

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
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify the ui, mainly the information can not be edit when edit date period of date period for hunt
 * @LinkSetUp: none
 * @SPEC:[TC:047001] Edit date period License year 
 * @Task#: Auto-2065
 * @author Phoebe Chen
 * @Date  February 10, 2014
 */
public class VerifyUIAndCancelFromEdit extends LicenseManagerTestCase {
	private DatePeriodInfo datePeriod = new DatePeriodInfo();
	private DatePeriodLicenseYearInfo licenseYear = new DatePeriodLicenseYearInfo();
	private Dates dates1 = licenseYear.new Dates(), dates2 = licenseYear.new Dates();
	private LicMgrEditDatePeriodLicenseYearWidget editWidget = LicMgrEditDatePeriodLicenseYearWidget.getInstance();
	LicMgrDatePeriodLicenseYearsListPage listPage = LicMgrDatePeriodLicenseYearsListPage.getInstance();
	private TimeZone timeZone;
	
	@Override
	public void execute() {
		lm.clearDatePeriod(datePeriod.getCode(), datePeriod.getDescription(), schema);
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoDatePeriodListPgFromLotteriesProdListPg();
		lm.addDatePeriods(datePeriod);
		
		lm.gotoDatePeriodDetailPgFromListPg(datePeriod.getCode());
		datePeriod.getLicenseYears().get(0).setId(listPage.getLicenseYearID(datePeriod.getLicenseYears().get(0).getLicenseYear()));
		lm.gotoDatePeriodLicenseYearDetailsWidget(datePeriod.getLicenseYears().get(0).getLicenseYear());
		this.verifyDatePeriodLicenseYearDetailsInfo(licenseYear);
		
		this.verifyWidgetUI();

		//Update the infor and click cancel
		datePeriod.getLicenseYears().get(0).getDates().add(dates2);  
		this.updateInfoAndClickCancel();
		//The page info will not be updated
		datePeriod.getLicenseYears().get(0).getDates().remove(1);
		lm.gotoDatePeriodLicenseYearDetailsWidget(datePeriod.getLicenseYears().get(0).getLicenseYear());
		this.verifyDatePeriodLicenseYearDetailsInfo(licenseYear);
		lm.gotoDatePeriodLicenseYearListPageFromDetailsWidget();
		
		lm.logOutLicenseManager();
	}

	private void updateInfoAndClickCancel() {
		editWidget.updateInfo(INACTIVE_STATUS, datePeriod.getLicenseYears().get(0).getDates());
		editWidget.clickCancel();
		ajax.waitLoading();
		listPage.waitLoading();
	}

	private void verifyWidgetUI() {
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Date Period License Year Id not editable", false, editWidget.isDatePeriodLicenseYearIdEditable());
		passed &= MiscFunctions.compareResult("License Year not editable", false, editWidget.isLicenseYearDropDownListEnabled());
		passed &= MiscFunctions.compareResult("Creation date/Time not editable", false, editWidget.isCreationDateTimeEditable());
		passed &= MiscFunctions.compareResult("Creation User not editable", false, editWidget.isCreationUserEditable());
		if(!passed){
			throw new ErrorOnPageException("Not all checkpoints passed, please refer to log for details info.");
		}
		logger.info("The UI is correct!");
	}
	
	private void verifyDatePeriodLicenseYearDetailsInfo(DatePeriodLicenseYearInfo expected) {
		LicMgrEditDatePeriodLicenseYearWidget detailsWidget = LicMgrEditDatePeriodLicenseYearWidget.getInstance();
		
		logger.info("Verify Date Period License Year details info.");
		boolean result = detailsWidget.compareLicenseYearDetailsInfo(expected);
		if(!result) {
			throw new ErrorOnPageException("Date Period License Year details info is incorrect, please refer log for details info.");
		} else logger.info("Date Period License Year details info is correct.");
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
		datePeriod.setCode("EY1");
		datePeriod.setDescription(this.caseName);
		//Date Period License Year info
		licenseYear.setLicenseYear(String.valueOf(DateFunctions.getCurrentYear()));
		
		dates1.setFromDate(DateFunctions.getToday(timeZone));
		dates1.setToDate(DateFunctions.getDateAfterGivenDay(dates1.getFromDate(), 1));
		dates1.setCategory("Auto");//if this category doesn't exist, it will be automatically added
		
		List<Dates> dates = new ArrayList<DatePeriodLicenseYearInfo.Dates>();
		dates.add(dates1);
		licenseYear.setDates(dates);
		licenseYear.setStatus(OrmsConstants.ACTIVE_STATUS);
		List<DatePeriodLicenseYearInfo> licenseYears = new ArrayList<DatePeriodLicenseYearInfo>();
		licenseYear.setCreationDateTime(DateFunctions.getToday(timeZone));
		licenseYear.setCreationUser(DataBaseFunctions.getLoginUserName(login.userName));
		licenseYears.add(licenseYear);
		
		datePeriod.setLicenseYears(licenseYears);
		
		dates2.setFromDate(dates1.getFromDate());
		dates2.setToDate(dates1.getToDate());
		dates2.setCategory(dates1.getCategory());
	}
}
