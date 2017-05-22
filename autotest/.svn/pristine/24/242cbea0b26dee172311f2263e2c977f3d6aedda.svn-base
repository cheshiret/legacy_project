package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.fees;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * Search fee schedule by Inventory Date range and prodcut category.        
 * @Preconditions:
 * Linked data d_fin_use_attr_fee_sched, ID:3720
 * @SPEC:
 * Search Fee Schedule - Inventory Date range [TC:047813]
 * Search Fee Schedule - Product Category [TC:048901]
 * @Task#:Auto-1429
 * 
 * @author nding1
 * @Date  Jan 28, 2013
 */
public class SearchByInvDateAndPrdCategory extends FinanceManagerTestCase {
	private FeeScheduleData schedule = new FeeScheduleData();
	private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();

	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		
		// 1.Search by Inventory Start Date(from)
		schedule.dateType = "Inventory Start Date";// Don't change!
		schedule.fromDate = DateFunctions.getDateAfterToday(-30);
		schedule.productCategory = "Slip";
		feeMainPg.searchFeeSchedule(schedule);
		feeMainPg.verifySearchResults(schedule.productCategory, "Product Category");
		feeMainPg.verifySearchByFromDate(schedule.dateType, schedule.fromDate);
		
		// 2.Search by Inventory Start Date(to) and Product Category(Site)
		schedule.fromDate = StringUtil.EMPTY;
		schedule.toDate = DateFunctions.getDateAfterToday(-30);
		schedule.productCategory = "Site";
		feeMainPg.searchFeeSchedule(schedule);
		feeMainPg.verifySearchResults(schedule.productCategory, "Product Category");
		feeMainPg.verifySearchByToDate(schedule.dateType, schedule.toDate);

		// 3.Search by Inventory Start Date(from and to) and Product Category(Permit)
		schedule.fromDate = DateFunctions.getDateAfterToday(-30);
		schedule.toDate = DateFunctions.getDateAfterToday(30);
		schedule.productCategory = "Permit";
		feeMainPg.searchFeeSchedule(schedule);
		feeMainPg.verifySearchResults(schedule.productCategory, "Product Category");
		feeMainPg.verifySearchByFromDate(schedule.dateType, schedule.fromDate);
		feeMainPg.verifySearchByToDate(schedule.dateType, schedule.toDate);
		
		// 4.Search by Inventory End Date(from)
		schedule.dateType = "Inventory End Date";// Don't change!
		schedule.fromDate = DateFunctions.getDateAfterToday(100);
		schedule.toDate = StringUtil.EMPTY;
		schedule.productCategory = "Slip";
		feeMainPg.searchFeeSchedule(schedule);
		feeMainPg.verifySearchByFromDate(schedule.dateType, schedule.fromDate);
		
		// 5.Search by Inventory End Date(to)
		schedule.fromDate = StringUtil.EMPTY;
		schedule.toDate = DateFunctions.getDateAfterToday(430);
		schedule.productCategory = "Slip";
		feeMainPg.searchFeeSchedule(schedule);
		feeMainPg.verifySearchByToDate(schedule.dateType, schedule.toDate);

		// 6.Search by Inventory End Date(from and to)
		schedule.fromDate = DateFunctions.getDateAfterToday(100);
		schedule.toDate = DateFunctions.getDateAfterToday(530);
		schedule.productCategory = "Slip";
		feeMainPg.searchFeeSchedule(schedule);
		feeMainPg.verifySearchByFromDate(schedule.dateType, schedule.fromDate);
		feeMainPg.verifySearchByToDate(schedule.dateType, schedule.toDate);
		
		// 7.Search by Product Category(List)
		schedule.fromDate = StringUtil.EMPTY;
		schedule.toDate = StringUtil.EMPTY;
		schedule.productCategory = "List";
		feeMainPg.searchFeeSchedule(schedule);
		feeMainPg.verifySearchResults(schedule.productCategory, "Product Category");
		
		fnm.logoutFinanceManager();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		schedule.activeStatus = OrmsConstants.ACTIVE_STATUS;
	}
}
