package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.lotteries.dateperiods;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodHuntAssignmentInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo.Dates;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrDatePeriodHuntsAssignmentListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrDatePeriodsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify assign/unassign all hunt to date period
 * @Preconditions:
 * @SPEC:   TC:050394,TC:050393,TC:050397
 * @Task#:Auto-1261

 * @author VZhang
 * @Date Oct 10, 2012
 */

public class AssignUnassignAllHuntToDatePeriod extends LicenseManagerTestCase{
	private DatePeriodInfo datePeriod = new DatePeriodInfo();
	private DatePeriodHuntAssignmentInfo assignmentInfo = new DatePeriodHuntAssignmentInfo();
	private String filterStatus;
	private LicMgrDatePeriodHuntsAssignmentListPage datePeriodHuntsAssignmentListPg = LicMgrDatePeriodHuntsAssignmentListPage.getInstance();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		//go to hunt list page
		lm.gotoHuntsListPgFromLotteriesProdListPg();
		
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
		this.assignOrUnassignAllHunts(false);
		
		//assign first hunt to date period which status is unassgined
		this.assignOrUnassignFirstHuntToDatePeriodAtDatePeriodHuntAssignmentListPg(true);
		//assign all hunt to this date period, TC:050394,TC:050393
		this.assignOrUnassignAllHunts(true);
		assignmentInfo.setAssignStatus("Assigned");
		assignmentInfo.setDatePeriod(datePeriod.getCode(), datePeriod.getDescription());
		this.verifyAssignedStatusAndDatePeriodColumnListValue(assignmentInfo.getAssignStatus(), assignmentInfo.getDatePeriod());
				
		//unassign first hunt to date period which status is unassigned
		this.assignOrUnassignFirstHuntToDatePeriodAtDatePeriodHuntAssignmentListPg(false);
		//unassign all hunts to this date period, TC:050398,TC:050399
		this.assignOrUnassignAllHunts(false);
		assignmentInfo.setAssignStatus("Unassigned");
		assignmentInfo.setDatePeriod("");
		this.verifyAssignedStatusAndDatePeriodColumnListValue(assignmentInfo.getAssignStatus(), assignmentInfo.getDatePeriod());
		
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
		datePeriod.setCode("AUA");
		datePeriod.setDescription("AssignUnassignAllHuntToDatePeriod");
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
	
	private void assignOrUnassignFirstHuntToDatePeriodAtDatePeriodHuntAssignmentListPg(boolean isAssign){
		LicMgrDatePeriodHuntsAssignmentListPage datePeriodHuntsAssignmentListPg = LicMgrDatePeriodHuntsAssignmentListPage.getInstance();
		
		logger.info("Assign or Unassign first hunt to date period.");
		datePeriodHuntsAssignmentListPg.selectHuntInfoCheckBox(0);//select first hunt
		if(isAssign){
			datePeriodHuntsAssignmentListPg.clickAssign();
		}else{
			datePeriodHuntsAssignmentListPg.clickUnassign();
		}
		ajax.waitLoading();
		datePeriodHuntsAssignmentListPg.waitLoading();
	}
	
	private void assignOrUnassignAllHunts(boolean isClickAssign){
		PagingComponent paging = new PagingComponent();
		logger.info("Assign or Unassign all hunts to date period.");

		this.gotoFirstPage();
		
		do{
			datePeriodHuntsAssignmentListPg.selectAllHuntsInfoCheckBox();
			if(isClickAssign){
				datePeriodHuntsAssignmentListPg.clickAssign();
			}else{
				datePeriodHuntsAssignmentListPg.clickUnassign();
			}
			ajax.waitLoading();
			datePeriodHuntsAssignmentListPg.waitLoading();
		} while(paging.clickNext());
		
		this.gotoFirstPage();	
	}
	
	private void gotoFirstPage() {
		PagingComponent paging = new PagingComponent();
		
		// if go to other page(not the first page), click First to go back to first page, in order to make next action correctly.
		if(paging.previousExists()){
			paging.clickFirst();
			ajax.waitLoading();
			datePeriodHuntsAssignmentListPg.waitLoading();
		}
	}
	
	private void verifyAssignedStatusAndDatePeriodColumnListValue(String expAssignedStatus, String expDatePeriod){
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

}
