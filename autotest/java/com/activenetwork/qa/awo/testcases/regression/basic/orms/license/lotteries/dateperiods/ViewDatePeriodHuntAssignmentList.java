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
 * @Description:This case is used to verify assign/unassign one hunt to date period list info and search result
 * @Preconditions:
 * @SPEC:   TC:049734,TC:049735
 * @Task#:Auto-1261

 * @author VZhang
 * @Date Oct 11, 2012
 */

public class ViewDatePeriodHuntAssignmentList extends LicenseManagerTestCase{
	private HuntInfo hunt = new HuntInfo();
	private DatePeriodInfo datePeriod = new DatePeriodInfo();
	private DatePeriodHuntAssignmentInfo assignmentInfo = new DatePeriodHuntAssignmentInfo();
	private static final String FILTERSTATUS_ALLHUNTS = "All Hunts";
	private static final String FILTERSTATUS_ASSIGNED = "Hunts Assigned to this Date Period";
	private static final String FILTERSTATUS_UNASSIGNED = "Hunts Not Assigned to this Date Period";
	private List<String> filterOptions = new ArrayList<String>();
	private LicMgrDatePeriodHuntsAssignmentListPage datePeriodHuntsAssignmentListPg = LicMgrDatePeriodHuntsAssignmentListPage.getInstance();

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
		//verify filter options
		this.verifyFilterOptions(filterOptions);
		
		//clear up data
		lm.filterDatePeriodHuntAssignmentInfoAtDatePeriodHuntAssignmentListPg(FILTERSTATUS_ALLHUNTS);
		lm.unAssignHuntToDatePeriodAtDatePeriodHuntAssignmentListPg(assignmentInfo.getHunt());
		
		//assign hunt to date period, and verify assigned info should existing at All Hunts Search result
		assignmentInfo.setAssignStatus("Assigned");
		assignmentInfo.setDatePeriod(datePeriod.getCode(), datePeriod.getDescription());
		lm.assignHuntToDatePeriodAtDatePeriodHuntAssignmentListPg(assignmentInfo.getHunt());
		this.verifyAssignmentInfoWhetherIsExisting(true,assignmentInfo.getHunt());
		
		//verify assigned info should existing at "Hunts Assigned to this Date Period" Search result
		lm.filterDatePeriodHuntAssignmentInfoAtDatePeriodHuntAssignmentListPg(FILTERSTATUS_ASSIGNED);
		this.verifyAssignedStatusAndDatePeriodColumnListValueBySearchResult(assignmentInfo.getAssignStatus(), assignmentInfo.getDatePeriod());
		this.verifyAssignmentInfoWhetherIsExisting(true,assignmentInfo.getHunt());
		
		//verify assigned info should not existing at "Hunts Not Assigned to this Date Period" Search result
		lm.filterDatePeriodHuntAssignmentInfoAtDatePeriodHuntAssignmentListPg(FILTERSTATUS_UNASSIGNED);
		this.verifyAssignmentInfoWhetherIsExisting(false,assignmentInfo.getHunt());
		
		//unassign hunt to date period, and verify unassigned info should existing at All Hunts Search result
		lm.filterDatePeriodHuntAssignmentInfoAtDatePeriodHuntAssignmentListPg(FILTERSTATUS_ALLHUNTS);
		assignmentInfo.setAssignStatus("Unassigned");
		assignmentInfo.setDatePeriod("");
		lm.unAssignHuntToDatePeriodAtDatePeriodHuntAssignmentListPg(assignmentInfo.getHunt());
		this.verifyAssignmentInfoWhetherIsExisting(true,assignmentInfo.getHunt());
		
		//verify unassigned info should existing at "Hunts Not Assigned to this Date Period" Search result
		lm.filterDatePeriodHuntAssignmentInfoAtDatePeriodHuntAssignmentListPg(FILTERSTATUS_UNASSIGNED);
		this.verifyAssignmentInfoWhetherIsExisting(true,assignmentInfo.getHunt());
		
		//verify unassigned info should not existing at "Hunts Assigned to this Date Period" Search result
		lm.filterDatePeriodHuntAssignmentInfoAtDatePeriodHuntAssignmentListPg(FILTERSTATUS_ASSIGNED);
		this.verifyAssignmentInfoWhetherIsExisting(false,assignmentInfo.getHunt());
		
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
		datePeriod.setCode("AUL");
		datePeriod.setDescription("ViewDatePeriodHuntAssignmentList");
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
		hunt.setHuntCode("DatePeriodHuntL");
		hunt.setDescription("DatePeriodHuntL");
		hunt.setAllowedApplicants("Individual");
		hunt.setHuntQuotaDescription("quota_AddHunt");
		
		assignmentInfo.setHunt(hunt.getHuntCode(), hunt.getDescription());
		assignmentInfo.setSpecies(hunt.getSpecie());
		assignmentInfo.setSpeciesSubType("");
		assignmentInfo.setWeapon("");
		assignmentInfo.setHuntLocation("");
		
		filterOptions.add(FILTERSTATUS_ALLHUNTS);
		filterOptions.add(FILTERSTATUS_ASSIGNED);
		filterOptions.add(FILTERSTATUS_UNASSIGNED);
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
	
	private void verifyFilterOptions(List<String> expFilterOptions){
		logger.info("verify filter options");
		
		List<String> actFilterOptions = datePeriodHuntsAssignmentListPg.getFilterOption();
		if(expFilterOptions.size() != actFilterOptions.size()){
			throw new ErrorOnPageException("The filter options are not correct.");
		}else{
			if(!expFilterOptions.equals(actFilterOptions)){
				throw new ErrorOnPageException("The filter options are not correct.");
			}else logger.info("The filter options are correct.");
		}
		
	}
	
	private void verifyAssignedStatusAndDatePeriodColumnListValueBySearchResult(String expAssignedStatus, String expDatePeriod){
		logger.info("Verify assigned status and date period column list value.");
		
		List<String> actAssignedStatusList = datePeriodHuntsAssignmentListPg.getAssignedStatusColumnListValue();
		List<String> actDatePeriodList = datePeriodHuntsAssignmentListPg.getDatePeriodColumnListValue();
		
		this.verifyColumnListValue(expAssignedStatus, actAssignedStatusList);
		this.verifyColumnListValue(expDatePeriod, actDatePeriodList);
	}
	
	private void verifyColumnListValue(String expValue, List<String> actColumnListValue){
		for(int i=0; i<actColumnListValue.size();i++){
			boolean result = MiscFunctions.compareResult("Column list value", expValue, actColumnListValue.get(i));
			if(!result){
				throw new ErrorOnPageException("Column list value is not correct.");
			}else logger.info("Column list value is correct.");
		}
	}
	
	private void verifyAssignmentInfoWhetherIsExisting(boolean expExisting,String huntInfo){
		logger.info("Verify assignment info whether is existing.");
		boolean actIsExisting = datePeriodHuntsAssignmentListPg.checkHuntInfoIsExisting(huntInfo);
		boolean result = MiscFunctions.compareResult("Assignment Info Is Existing", expExisting, actIsExisting);
		if(!result){
			throw new ErrorOnPageException("Assignment Info Is Existing info is not correct. Hunt info = " + huntInfo);
		}else logger.info("Assignment Info Is Existing info is correct. Hunt info = " + huntInfo);
		
	}

}
