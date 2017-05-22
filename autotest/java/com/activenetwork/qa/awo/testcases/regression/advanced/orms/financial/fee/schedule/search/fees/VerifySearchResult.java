package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.fees;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:
 * Product Category is Slip.
 * This test case contains the scenario: 
 * 1.Search by Marina Rate Type(Seasonal), Boat Category(All), Rafting(All), Effective Date(From), Status(Active).
 * 2.Search by Marina Rate Type(Transient), Boat Category(Personal), Rafting(Excluding Rafting), Effective Date(To), Status(Inactive).
 * 3.Search by Marina Rate Type(Transient), Boat Category(Commercial), Rafting(Rafting Only), Effective Date(From and To).
 * 4.Search by Marina Rate Type(All).
 * 5.Search by Marina Rate Type(Lease).
 * @Preconditions:
 * Step1 linked data: d_fin_use_attr_fee_sched, ID:3650
 * Step2 linked data: d_fin_use_attr_fee_sched, ID:3660
 * Step3 linked data: d_fin_use_attr_fee_sched, ID:3670
 * Step4 linked data: d_fin_tran_fee_sched, ID:3300
 * @SPEC: TC049419,049420,049421,048899,048900,048901
 * @Task#:Auto-1429
 * 
 * @author nding1
 * @Date  Jan 28, 2013
 */
public class VerifySearchResult extends FinanceManagerTestCase {
	private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();
	
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		
		// 1.Search by Marina Rate Type(Seasonal), Boat Category(All), Rafting(All), Effective Date(From), Status(Active).
		schedule.marinaRateType = "Seasonal";
		schedule.boatCategory = "All";
		schedule.rafting = "All";
		schedule.dateType = "Effective (Start) Date";
		schedule.fromDate = DateFunctions.getDateAfterToday(-100);
		schedule.activeStatus = OrmsConstants.ACTIVE_STATUS;
		feeMainPg.searchFeeSchedule(schedule);
		feeMainPg.verifySearchResults(schedule.marinaRateType, "Rate Type");
		feeMainPg.verifySearchResults(schedule.boatCategory, "Boat Category");
		feeMainPg.verifySearchResults(schedule.rafting, "Rafting");
		feeMainPg.verifySearchByFromDate("Effective (Start) Date", schedule.fromDate);
		feeMainPg.verifySearchResults(OrmsConstants.YES_STATUS, "Active");
		
		// 2.Search by Marina Rate Type(Transient), Boat Category(Personal), Rafting(Excluding Rafting), Effective Date(To), Status(Inactive).
		schedule.marinaRateType = "Transient";
		schedule.boatCategory = "Personal";
		schedule.rafting = "Excluding Rafting";
		schedule.dateType = "Effective (Start) Date";
		schedule.toDate = DateFunctions.getDateAfterToday(100);
		schedule.activeStatus = OrmsConstants.INACTIVE_STATUS;
		feeMainPg.searchFeeSchedule(schedule);
		feeMainPg.verifySearchResults(schedule.marinaRateType, "Rate Type");
		feeMainPg.verifySearchResults(schedule.boatCategory, "Boat Category");
		feeMainPg.verifySearchResults(schedule.rafting, "Rafting");
		feeMainPg.verifySearchByToDate("Effective (Start) Date", schedule.toDate);
		feeMainPg.verifySearchResults(OrmsConstants.NO_STATUS, "Active");
		
		// 3.Search by Marina Rate Type(Transient), Boat Category(Commercial), Rafting(Rafting Only), Effective Date(From and To).
		schedule.marinaRateType = "Transient";
		schedule.boatCategory = "Commercial";
		schedule.rafting = "Rafting Only";
		schedule.dateType = "Effective (Start) Date";
		schedule.fromDate = DateFunctions.getDateAfterToday(-10);
		schedule.toDate = DateFunctions.getDateAfterToday(150);
		schedule.activeStatus = OrmsConstants.ACTIVE_STATUS;
		feeMainPg.searchFeeSchedule(schedule);
		feeMainPg.verifySearchResults(schedule.marinaRateType, "Rate Type");
		feeMainPg.verifySearchResults(schedule.boatCategory, "Boat Category");
		feeMainPg.verifySearchResults(schedule.rafting, "Rafting");
		feeMainPg.verifySearchByFromDate("Effective (Start) Date", schedule.fromDate);
		feeMainPg.verifySearchByToDate("Effective (Start) Date", schedule.toDate);
		
		// 4.Search by Marina Rate Type(All).
		schedule.marinaRateType = "All";
		schedule.feeType = "Transaction Fee";
		schedule.boatCategory = "";
		schedule.rafting = "";
		schedule.dateType = "";
		schedule.fromDate = "";
		feeMainPg.searchFeeSchedule(schedule);
		feeMainPg.verifySearchResults(schedule.marinaRateType, "Rate Type");
		feeMainPg.verifySearchResults(schedule.productCategory, "Product Category");

		// 5.Search by Marina Rate Type(Lease).
		schedule.marinaRateType = "Lease";
		schedule.feeType = "Use Fee";
		feeMainPg.searchFeeSchedule(schedule);
		feeMainPg.verifySearchResults(schedule.marinaRateType, "Rate Type");
		
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";
		schedule.productCategory = "Slip";// Don't change!
	}
}
