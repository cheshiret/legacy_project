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
 * @Description:This case is used to verify the update both license year status and dates(the status is originally active) during edit date period of date period for hunt
 * @LinkSetUp: none
 * @SPEC:[TC:047001] Edit date period License year 
 * @Task#: Auto-2065
 * @author Phoebe Chen
 * @Date  February 11, 2014
 */
public class Edit_ExistingActive extends LicenseManagerTestCase {
	private DatePeriodInfo datePeriod = new DatePeriodInfo();
	private DatePeriodLicenseYearInfo licenseYear = new DatePeriodLicenseYearInfo();
	private List<Dates> dates_update = new ArrayList<DatePeriodLicenseYearInfo.Dates>();
	private TimeZone timeZone;
	private LicMgrDatePeriodLicenseYearsListPage listPage = LicMgrDatePeriodLicenseYearsListPage.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoDatePeriodListPgFromLotteriesProdListPg();
		if(	!LicMgrDatePeriodsListPage.getInstance().isDatePeriodExists(datePeriod.getCode())) {
			lm.addDatePeriods(datePeriod);
		}
		lm.gotoDatePeriodDetailPgFromListPg(datePeriod.getCode());
		
		//clean up former Date Period License Year records to avoid conflict
		lm.deactivateDatePeriodLicenseYear(datePeriod.getLicenseYears().get(0).getLicenseYear());
		licenseYear.setId(lm.addDatePeriodLicenseYear(licenseYear));

		//Get all inactive license year ids before update
		List<String> ids_beforeUpdate = this.getAllIdsInList(INACTIVE_STATUS, licenseYear.getLicenseYear());
		ids_beforeUpdate.add(licenseYear.getId());
		lm.updateDatePeriodLicenseYearInfo(licenseYear, OrmsConstants.INACTIVE_STATUS, dates_update);
		this.updatelicenseYearInfo();
		List<String> ids_afterUpdate = this.getAllIdsInList(INACTIVE_STATUS, licenseYear.getLicenseYear());
		licenseYear.setId(this.getNewId(ids_beforeUpdate, ids_afterUpdate));
		
		lm.gotoDatePeriodLicenseYearDetailsWidget(licenseYear);
		this.verifyDatePeriodLicenseYearDetailsInfo(licenseYear);
		lm.gotoDatePeriodLicenseYearListPageFromDetailsWidget();
		
		lm.logOutLicenseManager();
	}
	
	private String getNewId(List<String> beforeUpdate, List<String> afterUpdate){
		String id = "";
		for(int i=0; i< afterUpdate.size(); i++){
			if(!beforeUpdate.contains(afterUpdate.get(i))){
				id = afterUpdate.get(i);
				break;
			}
		}
		return id;
	}

	private List<String> getAllIdsInList(String status,	String licenseYear) {
		listPage.searchLicenseYear(status, licenseYear);
		return listPage.getAllLicenseYearIds();
	}

	private void updatelicenseYearInfo() {
		licenseYear.setDates(dates_update);
		licenseYear.setStatus(OrmsConstants.INACTIVE_STATUS);
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
		datePeriod.setCode("EY6"); 
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
		licenseYear.setCreationDateTime(DateFunctions.getToday(timeZone));
		licenseYear.setCreationUser(DataBaseFunctions.getLoginUserName(login.userName));
		List<DatePeriodLicenseYearInfo> licenseYears = new ArrayList<DatePeriodLicenseYearInfo>();
		licenseYears.add(licenseYear);
		
		datePeriod.setLicenseYears(licenseYears);
		
		Dates dates1_update = licenseYear.new Dates();
		dates1_update.setFromDate(DateFunctions.getDateAfterGivenDay(dates1.getFromDate(), 3));
		dates1_update.setToDate(DateFunctions.getDateAfterGivenDay(dates1.getToDate(), 5));
		dates1_update.setCategory("Auto_Update");//if this category doesn't exist, it will be automatically added
		dates_update.add(dates1_update);
	}
}
