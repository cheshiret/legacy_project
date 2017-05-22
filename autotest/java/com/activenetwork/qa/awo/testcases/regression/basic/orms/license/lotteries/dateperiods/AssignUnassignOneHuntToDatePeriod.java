package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.dateperiods;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodHuntAssignmentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo.Dates;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrDatePeriodHuntsAssignmentListPage;
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
 * @Description:This case is used to verify assign/unassign one hunt to date period
 * @Preconditions:
 * @SPEC:   TC:046992,TC:050396,TC:050397
 * @Task#:Auto-1261

 * @author VZhang
 * @Date Oct 10, 2012
 */

public class AssignUnassignOneHuntToDatePeriod  extends LicenseManagerTestCase{
	private HuntInfo hunt = new HuntInfo();
	private DatePeriodInfo datePeriod = new DatePeriodInfo();
	private DatePeriodHuntAssignmentInfo assignmentInfo = new DatePeriodHuntAssignmentInfo();
	private LicMgrDatePeriodHuntsAssignmentListPage datePeriodHuntsAssignmentListPg = LicMgrDatePeriodHuntsAssignmentListPage.getInstance();
	private String filterStatus;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		//go to hunt list page
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		this.checkAndPrepareHuntInfo();
		
		lm.gotoDatePeriodListPgFromLotteriesHuntsListPg();
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
		//clear up data
		lm.unAssignHuntToDatePeriodAtDatePeriodHuntAssignmentListPg(assignmentInfo.getHunt());
		int origCount = datePeriodHuntsAssignmentListPg.getAssignmentCount();
		
		//assign hunt to date period which status is unassgined
		lm.assignHuntToDatePeriodAtDatePeriodHuntAssignmentListPg(assignmentInfo.getHunt());
		assignmentInfo.setAssignStatus("Assigned");
		assignmentInfo.setDatePeriod(datePeriod.getCode(), datePeriod.getDescription());
		this.verifyDatePeriodHuntAssignmentInfo(assignmentInfo);
		this.verifyHuntTabAssignmentCount(origCount+1);
		
		
		//assign hunt to date period which status is assigned
		lm.assignHuntToDatePeriodAtDatePeriodHuntAssignmentListPg(assignmentInfo.getHunt());
		this.verifyDatePeriodHuntAssignmentInfo(assignmentInfo);
		
		//unassign hunt to date period which status is assigned
		lm.unAssignHuntToDatePeriodAtDatePeriodHuntAssignmentListPg(assignmentInfo.getHunt());
		assignmentInfo.setAssignStatus("Unassigned");
		assignmentInfo.setDatePeriod("");
		this.verifyDatePeriodHuntAssignmentInfo(assignmentInfo);
		this.verifyHuntTabAssignmentCount(origCount);
		
		//unassign hunt to date period which status is unassigned
		lm.unAssignHuntToDatePeriodAtDatePeriodHuntAssignmentListPg(assignmentInfo.getHunt());
		this.verifyDatePeriodHuntAssignmentInfo(assignmentInfo);
		
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
		datePeriod.setCode("AUO");
		datePeriod.setDescription("AssignUnassignOneHuntToDatePeriod");
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
		
		//hunt info
		hunt.setSpecie("Ducks");
		hunt.setHuntCode("DatePeriodHuntO");
		hunt.setDescription("DatePeriodHuntO");
		hunt.setAllowedApplicants("Individual");
		hunt.setHuntQuotaDescription("quota_AddHunt");
		
		assignmentInfo.setHunt(hunt.getHuntCode(), hunt.getDescription());
		assignmentInfo.setSpecies(hunt.getSpecie());
		assignmentInfo.setSpeciesSubType("");
		assignmentInfo.setWeapon("");
		assignmentInfo.setHuntLocation("");
		
		filterStatus = "All Hunts";
	}
	
	private void checkAndPrepareHuntInfo(){
		String huntStatus = lm.getHuntInfoStatusByHuntCodeFromDB(hunt.getHuntCode(), schema);
		if(StringUtil.isEmpty(huntStatus)){
			//Add a new hunt 
			lm.addHuntFromHuntListPage(null, null, null, hunt);
		}else if(huntStatus.equals(OrmsConstants.INACTIVE_STATUS)){
			lm.searchHunt(OrmsConstants.INACTIVE_STATUS, hunt.getSpecie());
			lm.activateHunt(hunt.getHuntCode());
		}
	}
	
	private void verifyDatePeriodHuntAssignmentInfo(DatePeriodHuntAssignmentInfo expAssignmentInfo){
		LicMgrDatePeriodHuntsAssignmentListPage datePeriodHuntsAssignmentListPg = LicMgrDatePeriodHuntsAssignmentListPage.getInstance();
		
		logger.info("Verify date period hunt assignment info.");
		boolean result = datePeriodHuntsAssignmentListPg.compareDatePeriodHuntAssginmentInfo(expAssignmentInfo);
		if(!result){
			throw new ErrorOnPageException("Date period hunt assignment info is not correct.");
		}else logger.info("Date period hunt assignment info is correct.");		
	}
	
	private void verifyHuntTabAssignmentCount(int expCount){
		int actCount = datePeriodHuntsAssignmentListPg.getAssignmentCount();
		
		logger.info("Hunt tab assignment count info.");
		boolean result = MiscFunctions.compareResult("Hunt tab assignment count", expCount, actCount);
		if(!result){
			throw new ErrorOnPageException("Hunt tab assignment count is not correct.");
		}else logger.info("Hunt tab assignment count is correct.");	
		
	}
	
}
