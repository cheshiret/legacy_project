package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.dateperiod.licenseyear;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo.Dates;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrDatePeriodLicenseYearsListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrDatePeriodsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify the feature of 'Create/Modify Date Period License Year'
 * @LinkSetUp: none
 * @SPEC:[TC:047026] Authority check about edit a date period license year 
 * @Task#: Auto-2065
 * @author Phoebe Chen
 * @Date  February 11, 2014
 */
public class VerifyFeature extends LicenseManagerTestCase {
	private AdminManager adm = AdminManager.getInstance();
	private LoginInfo loginAdm = new LoginInfo();
	private RoleInfo role=new RoleInfo();
	private DatePeriodInfo datePeriod = new DatePeriodInfo();
	private DatePeriodLicenseYearInfo licenseYear = new DatePeriodLicenseYearInfo();
	LicMgrDatePeriodLicenseYearsListPage listPage = LicMgrDatePeriodLicenseYearsListPage.getInstance();
	private TimeZone timeZone;
	@Override
	public void execute() {
		//Assigned the CreateModifyDatePeriodLicenseYear features for system
	    this.updateFeatureAssignment(true);
		
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoDatePeriodListPgFromLotteriesProdListPg();
		if(!LicMgrDatePeriodsListPage.getInstance().isDatePeriodExists(datePeriod.getCode())) {
			lm.addDatePeriods(datePeriod);
		}
		lm.gotoDatePeriodDetailPgFromListPg(datePeriod.getCode());
		
		//clean up former Date Period License Year records to avoid conflict
		lm.deactivateDatePeriodLicenseYear(datePeriod.getLicenseYears().get(0).getLicenseYear());
		//Add a license year and inactive it
		licenseYear.setId(lm.addDatePeriodLicenseYear(licenseYear));
		this.verifyNoDatePeriodLicenseYearsSubPage(true);
		lm.logOutLicenseManager();
		
		//Unassigned the CreateModifyDatePeriodLicenseYear features for system
		this.updateFeatureAssignment(false);
		
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoDatePeriodListPgFromLotteriesProdListPg();
		lm.gotoDatePeriodDetailPgFromListPg(datePeriod.getCode());
		this.verifyNoDatePeriodLicenseYearsSubPage(false);
		lm.logOutLicenseManager();
	}

	private void verifyNoDatePeriodLicenseYearsSubPage(boolean available) {
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Add license year button is available:", available, listPage.isAddLicenseYearButtonExist());
		if(!passed){
			throw new ErrorOnPageException("Add Hunt license year button and hunt licnese year id should " + (available?"":"not ") + "available!");
		}
		logger.info("Add Hunt license year button and hunt licnese year id is correct as " + (available?"":"not ") + "available!");
	}

	private void updateFeatureAssignment(boolean assigned) {
		//Goto Admin Manager to assigned all the feature
		adm.loginAdminMgr(loginAdm);
		adm.assignOrUnAssignRoleFeature(role, assigned);
		adm.logoutAdminMgr();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		//Date Period info
		datePeriod.setCode("EY3"); 
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
		licenseYear.setStatus(OrmsConstants.INACTIVE_STATUS);
		List<DatePeriodLicenseYearInfo> licenseYears = new ArrayList<DatePeriodLicenseYearInfo>();
		licenseYears.add(licenseYear);
		
		datePeriod.setLicenseYears(licenseYears);
		
		//information for assign feature
		role.feature = "CreateModifyDatePeriod";
		//Admin manager login info
		loginAdm.url = AwoUtil.getOrmsURL(env);;
		loginAdm.userName = TestProperty.getProperty("orms.adm.user");
		loginAdm.password = TestProperty.getProperty("orms.adm.pw");
		loginAdm.contract = "MS Contract";
		loginAdm.location = "Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
		role.roleName = "HF HQ Role - Auto";
		role.application = "LicenseManager";
	}

}
