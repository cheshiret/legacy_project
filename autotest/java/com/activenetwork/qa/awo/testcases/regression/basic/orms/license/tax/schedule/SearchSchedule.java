package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.tax.schedule;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.licenseManager.tax.LicMgrTaxScheduleSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This cases verified flow for add edit a tax schedule for vendor fee
 * @LinkSetUp:  d_hf_add_activity:id=380(ACTIVITY_NAME='Activity For Tax Schedule 10')
 * @SPEC:[TC:110574] Search Tax Schedule 
 * @Task#: Auto-2137
 * @author Phoebe
 * @Date  May 19, 2014
 */
public class SearchSchedule extends LicenseManagerTestCase{
	private ScheduleData schedule = new ScheduleData();
	private ScheduleData schedule_search = new ScheduleData();
	private LicMgrTaxScheduleSearchPage scheduleListPg = LicMgrTaxScheduleSearchPage.getInstance();
	private String facilityName;
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoTaxScheduelListPage();
		
		schedule.scheduleId = lm.addNewTaxSchedule(schedule);
		
		//Search by location
		schedule.cleanUpSearchData();
		schedule_search.searchBy="Location";
		schedule_search.searchValue=facilityName;
		scheduleListPg.searchTaxSchedule(schedule_search);
		scheduleListPg.verifySchdInSearchList(schedule.scheduleId);
		scheduleListPg.verifySearchResults(facilityName, "Location");
		
		//Search by tax name
		schedule.cleanUpSearchData();
		schedule_search.searchBy="Tax Name";
		schedule_search.searchValue=schedule.taxName;
		scheduleListPg.searchTaxSchedule(schedule_search);
		scheduleListPg.verifySchdInSearchList(schedule.scheduleId);
		scheduleListPg.verifySearchResults(schedule.taxName, "Tax Name");
		
		//Search by fee type
		schedule.cleanUpSearchData();
		schedule_search.feeType=schedule.feeType;
		scheduleListPg.searchTaxSchedule(schedule_search);
		scheduleListPg.verifySchdInSearchList(schedule.scheduleId);
		scheduleListPg.verifySearchResults(schedule.feeType, "Fee Type");
		
		//Search by tax schedule status
		schedule.cleanUpSearchData();
		schedule_search.searchStatus=INACTIVE_STATUS;
		scheduleListPg.searchTaxSchedule(schedule_search);
		scheduleListPg.verifySchdInSearchList(schedule.scheduleId);
		scheduleListPg.verifySearchResults("No", "Active");
		
		//Search by customer type
		schedule.cleanUpSearchData();
		schedule_search.searchStatus=INACTIVE_STATUS;
		scheduleListPg.searchTaxSchedule(schedule_search);
		scheduleListPg.verifySchdInSearchList(schedule.scheduleId);
		scheduleListPg.verifySearchResults("No", "Active");
		
		//Search by transaction type
		schedule.cleanUpSearchData();
		schedule_search.tranType=schedule.tranType;
		scheduleListPg.searchTaxSchedule(schedule_search);
		scheduleListPg.verifySchdInSearchList(schedule.scheduleId);
		scheduleListPg.verifySearchResults(schedule.tranType, "Transaction Type");
		
		//Search by tax rate type
		schedule.cleanUpSearchData();
		schedule_search.rateType=schedule.rateType;
		scheduleListPg.searchTaxSchedule(schedule_search);
		scheduleListPg.verifySchdInSearchList(schedule.scheduleId);
		scheduleListPg.verifySearchResults(schedule.rateType, "Rate Type");
		
		//Search by product category and fee type:Activity Fee
		schedule.cleanUpSearchData();
		schedule_search.feeType="Activity Fee";
		scheduleListPg.searchTaxSchedule(schedule_search);
		scheduleListPg.verifySearchResults("Activity", "Product Category");
		scheduleListPg.verifySearchResults(schedule_search.feeType, "Fee Type");
		
		//Search by product category and fee type:Transaction Fee
		schedule.cleanUpSearchData();
		schedule_search.feeType="Transaction Fee";
		scheduleListPg.searchTaxSchedule(schedule_search);
		scheduleListPg.verifySearchResults("Activity", "Product Category");
		scheduleListPg.verifySearchResults(schedule_search.feeType, "Fee Type");
		
		//Search by product category and fee type:Vendor Fee
		schedule.cleanUpSearchData();
		schedule_search.feeType="Vendor Fee";
		scheduleListPg.searchTaxSchedule(schedule_search);
		scheduleListPg.verifySearchResults("Activity", "Product Category");
		scheduleListPg.verifySearchResults(schedule_search.feeType, "Fee Type");
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facilityID = "1";  //Mississippi Department of Wildlife, Fisheries, and Parks
		facilityName = lm.getFacilityName(facilityID, schema);
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facilityName;
		
		schedule.taxName = "TaxForActivity";
	 	schedule.productCategory = "Activity"; //This is the test point, can not be changed
	  	schedule.feeType = "Transaction Fee"; //This is the test point, can not be changed
	  	schedule.productGroup = "FeeAddEditGroup";
	 	schedule.product = "Activity For Tax Schedule 10";
	 	schedule.effectiveDate = DateFunctions.getDateAfterToday(0);
	 	schedule.effectiveEndDate = DateFunctions.getDateAfterToday(1);	
	  	schedule.tranType = "Register for Activity";
	  	schedule.accountCode = "3461.3010.41540.7000.---.---.--.----.----.--------; Sales Tax";
	  	schedule.rate = "3.24";
	}

}
