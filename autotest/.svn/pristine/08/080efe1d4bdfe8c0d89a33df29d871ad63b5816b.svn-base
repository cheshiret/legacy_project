package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.feeschedule;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule.LicMgrFeeScheduleSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify add and edit a activity fee schedule for activity
 * @LinkSetUp:  d_assign_feature:id=5722
 *              d_hf_add_facility:id=10
 *              d_hf_add_facility_prd:id=10
 *              d_hf_add_instructor:id=10
 *              d_hf_add_activity:id=100
 * @SPEC:[TC:110012] Search Fee Schedule 
 * @Task#: Auto-2135
 * @author Phoebe Chen
 * @Date  April 23, 2014
 */
public class SearchFeeSchedule_Activity extends LicenseManagerTestCase{
	private LicMgrFeeScheduleSearchPage feeScheduleListPg = LicMgrFeeScheduleSearchPage.getInstance();
	private FeeScheduleData schedule_search = new FeeScheduleData();
	private FeeScheduleData schedule = new FeeScheduleData();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoFeeScheduleListPageFromTopMenu();
		
		// Add a new Fee schedule data
		feeScheduleListPg.deactiveScheduleForPrd(schedule.product);
		schedule.feeSchdId = lm.addNewFeeSchedule(schedule);
		feeScheduleListPg.activeFeeSchedule(schedule_search.feeSchdId, schedule_search.feeSchdId);
				
		//verify column search type 'product'
		schedule_search.cleanUpSearchData();
		schedule_search.searchType="Product";
		schedule_search.searchValue=schedule.product;
		feeScheduleListPg.searchFeeSchedule(schedule_search);
		feeScheduleListPg.verifySchdInSearchList(schedule.feeSchdId);
		feeScheduleListPg.verifySearchResults(schedule_search.searchValue, "Product or Product Group");
		
		//verify column date 'Effective (Start) Date'
		schedule_search.cleanUpSearchData();
		schedule_search.dateType="Effective (Start) Date";
		schedule_search.fromDate=schedule.effectDate;
		feeScheduleListPg.searchFeeSchedule(schedule_search);
		feeScheduleListPg.verifySchdInSearchList(schedule.feeSchdId);
		feeScheduleListPg.verifySearchResults(DateFunctions.formatDate(schedule_search.fromDate, "EEE MMM d yyyy"), "Effective (Start) Date");
		
		//verify column Show 
		schedule_search.cleanUpSearchData();
		schedule_search.productCategory=schedule.productCategory;
		schedule_search.activeStatus=ACTIVE_STATUS;
		feeScheduleListPg.searchFeeSchedule(schedule_search);
		feeScheduleListPg.verifySchdInSearchList(schedule.feeSchdId);
		feeScheduleListPg.verifySearchResults("Yes", "Active");
		
		//verify column Fee Type 
		schedule_search.cleanUpSearchData();
		schedule_search.feeType=schedule.feeType;
		feeScheduleListPg.searchFeeSchedule(schedule_search);
		feeScheduleListPg.verifySchdInSearchList(schedule.feeSchdId);
		feeScheduleListPg.verifySearchResults(schedule_search.feeType, "Fee Type");
		
		//verify column unit 
		schedule_search.cleanUpSearchData();
		schedule_search.attrType=schedule.attrType;
		feeScheduleListPg.searchFeeSchedule(schedule_search);
		feeScheduleListPg.verifySchdInSearchList(schedule.feeSchdId);
		feeScheduleListPg.verifySearchResults(schedule_search.unit, "Unit");
		
		//Verify column transaction type
		schedule_search.cleanUpSearchData();
		schedule_search.tranType=schedule.tranType;
		feeScheduleListPg.searchFeeSchedule(schedule_search);
		feeScheduleListPg.verifySchdInSearchList(schedule.feeSchdId);
		feeScheduleListPg.verifySearchResults(schedule_search.unit, "Transaction Type");
		
		//Verify sales Channel
		schedule_search.cleanUpSearchData();
		schedule_search.salesChannel=schedule.salesChannel;
		feeScheduleListPg.searchFeeSchedule(schedule_search);
		feeScheduleListPg.verifySchdInSearchList(schedule.feeSchdId);
		feeScheduleListPg.verifySearchResults(schedule_search.salesChannel, "Sales Channel");
		
		//Verify location class
		schedule_search.cleanUpSearchData();
		schedule_search.locationClass="07 " + schedule.locationClass;
		feeScheduleListPg.searchFeeSchedule(schedule_search);
		feeScheduleListPg.verifySchdInSearchList(schedule.feeSchdId);
		feeScheduleListPg.verifySearchResults(schedule.locationClass, "Location Class");
		
		//Search By mixed condition
		this.setUpSearchCritial();
		feeScheduleListPg.searchFeeSchedule(schedule_search);
		feeScheduleListPg.verifySchdInSearchList(schedule.feeSchdId);
	}

	private void setUpSearchCritial() {
		schedule_search.cleanUpSearchData();
		schedule_search.searchType = "Product Group";
		schedule_search.searchValue = schedule.productGroup;
		schedule_search.activeStatus = schedule.activeStatus;
		schedule_search.locationClass = "07 " + schedule.locationClass;
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facilityID = "1";  //Mississippi Department of Wildlife, Fisheries, and Parks
		String facilityName = lm.getFacilityName(facilityID, schema);
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facilityName;
		
		//Set up schedule information for add 
		schedule.productCategory = "Activity";
		schedule.feeType = "Transaction Fee";  //Do not change, this is the check point
		
		schedule.productGroup = "FeeAddEditGroup";
		schedule.product = "Activity fee 07";
		schedule.scheduleName = "AddTransactionFee";
		schedule.effectDate = DateFunctions.getDateAfterToday(1);
		schedule.fromLicenseYear = String.valueOf(DateFunctions.getCurrentYear());
		schedule.toLicenseYear = String.valueOf(DateFunctions.getCurrentYear());
		schedule.salesChannel = "Web";
		schedule.locationClass = "WMA Agent";
		schedule.applicableTaxes.add("Sales Tax");
		schedule.applicableTaxes.add("Restaurant 7%");
		schedule.tranType = "Register for Activity";
		schedule.applyRate = "Per Unit";
		schedule.rateAmount = "4.44";
		schedule.stateTransFee = "3.00";
		schedule.splitRateBy = "Amount";
		schedule.splitIntoNum = "1";
		schedule.accounts.add("3450.2050.41502.0044.130.158.AD.W799.----.--------; NR Saltwater Fishing - 100%");
		schedule.percentOrAmountForEachAccount.add("4.44");
	}

}
