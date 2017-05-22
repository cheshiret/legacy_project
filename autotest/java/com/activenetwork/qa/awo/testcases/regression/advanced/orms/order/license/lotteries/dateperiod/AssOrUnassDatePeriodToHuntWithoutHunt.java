package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.dateperiod;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo.Dates;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrDatePeriodHuntsAssignmentListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrDatePeriodsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used when assign or unassign hunt to date period without select any hunt
 * assignment info should not be updated
 * no error message popup
 * @Preconditions:
 * @SPEC:   TC:047024 and TC:050395 
 * @Task#:Auto-1261

 * @author VZhang
 * @Date Oct 10, 2012
 */

public class AssOrUnassDatePeriodToHuntWithoutHunt extends LicenseManagerTestCase{
	private LicMgrDatePeriodHuntsAssignmentListPage datePeriodHuntsAssignmentListPg = LicMgrDatePeriodHuntsAssignmentListPage.getInstance();
	private DatePeriodInfo datePeriod = new DatePeriodInfo();
	private List<String> assignStatusList = new ArrayList<String>();
	private List<String> datePeriodList = new ArrayList<String>();
	private String filterStatus;

	@Override
	public void execute() {		
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		
		lm.gotoDatePeriodListPgFromLotteriesProdListPg();
		//check if Date Period exists, if not, add a new one
		if(	!LicMgrDatePeriodsListPage.getInstance().isDatePeriodExists(datePeriod.getCode())) {
			lm.addDatePeriods(datePeriod);
		}
		
		//goto Date Period details
		lm.gotoDatePeriodDetailPgFromListPg(datePeriod.getCode());
		//goto Date Period Hunt Assignment List
		lm.gotoDatePeriodHuntAssignmentListPgFromDatePeriodDetailPg();
		//filter date period hunt assignment info with status is 'All Hunts'
		lm.filterDatePeriodHuntAssignmentInfoAtDatePeriodHuntAssignmentListPg(filterStatus);
		this.getInitialAssignStatusInfoAndDatePeriodInfo();
		
		//click assign without select hunt
		this.clickAssignOrUnassignWithoutSelectHuntCheckBox(true);
		//verify no error message
		this.verifyErrorMessageNotExisting();
		//verify assignment info should be not updated
		this.verifyAssignStatusAndDatePeriodListInfo(assignStatusList, datePeriodList);
		
		//click unassign without select hunt
		this.clickAssignOrUnassignWithoutSelectHuntCheckBox(false);
		//verify no error message
		this.verifyErrorMessageNotExisting();
		//verify assignment info should be not updated
		this.verifyAssignStatusAndDatePeriodListInfo(assignStatusList, datePeriodList);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facilityID = "1";  //Mississippi Department of Wildlife, Fisheries, and Parks
		String facilityName = lm.getFacilityName(facilityID, schema);
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facilityName;
		
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		//Date Period info
		datePeriod.setCode("AHE");
		datePeriod.setDescription("AssignDatePeriodToHuntMessage");
		//Date Period License Year info
		DatePeriodLicenseYearInfo licenseYear = new DatePeriodLicenseYearInfo();
		licenseYear.setLicenseYear(String.valueOf(DateFunctions.getCurrentYear()));
		Dates dates1 = licenseYear.new Dates();
		dates1.setFromDate(DateFunctions.getToday(timeZone));
		dates1.setToDate(DateFunctions.getDateAfterGivenDay(dates1.getFromDate(), 365));
		dates1.setCategory("Auto");//if this category doesn't exist, it will be automatically added
		List<Dates> dates = new ArrayList<DatePeriodLicenseYearInfo.Dates>();
		dates.add(dates1);
		licenseYear.setDates(dates);
		List<DatePeriodLicenseYearInfo> licenseYears = new ArrayList<DatePeriodLicenseYearInfo>();
		licenseYears.add(licenseYear);
		datePeriod.setLicenseYears(licenseYears);
		
		filterStatus = "All Hunts";
	}
	
	private void getInitialAssignStatusInfoAndDatePeriodInfo(){
		assignStatusList = datePeriodHuntsAssignmentListPg.getAssignedStatusColumnListValue();
		datePeriodList = datePeriodHuntsAssignmentListPg.getAssignedStatusColumnListValue();
	}
	
	private void clickAssignOrUnassignWithoutSelectHuntCheckBox(boolean isClickAssign){
		logger.info("Click Assign without select hunt check box.");
		if(isClickAssign){
			datePeriodHuntsAssignmentListPg.clickAssign();
		}else{
			datePeriodHuntsAssignmentListPg.clickUnassign();
		}
		
		ajax.waitLoading();	
		datePeriodHuntsAssignmentListPg.waitLoading();
	}
	
	private void verifyErrorMessageNotExisting(){
		logger.info("Verify error message should not existing.");
		boolean isExisting = datePeriodHuntsAssignmentListPg.checkMessageIsExisting();
		
		if(isExisting){
			throw new ErrorOnPageException("Error Message should not existing.");
		}else logger.info("Error Message not existing");
	}
	
	private void verifyAssignStatusAndDatePeriodListInfo(List<String> expAssignStatusList, List<String> expDatePeriodList){
		List<String> actAssignStatusList = datePeriodHuntsAssignmentListPg.getAssignedStatusColumnListValue();
		List<String> actDatePeriodList = datePeriodHuntsAssignmentListPg.getAssignedStatusColumnListValue();
		
		if(!expAssignStatusList.equals(actAssignStatusList) || 
				!expDatePeriodList.equals(actDatePeriodList)){
			throw new ErrorOnPageException("The assignment info should be not be updated.");
		}else logger.info("The assign info is not ve update.");
		
	}

}
