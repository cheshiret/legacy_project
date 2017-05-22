package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.discount;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountSchedulePage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.NumberUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: search discount schedule by all search criteria
 * @Preconditions: Discount, Slip
 * 							d_fin_add_discount=760
 * 							d_inv_add_dock_area=1360
 * 							d_inv_slip_season=1430
 * 							d_inv_add_slip=14480
 * @SPEC: Search Execution_ All entry [TC:050102]
 * @Task#: AUTO-1951
 * 
 * @author qchen
 * @Date  Dec 26, 2013
 */
public class SearchByAllEntries extends FinanceManagerTestCase {

	private ScheduleData schedule = new ScheduleData();
	private FinMgrDiscountSchedulePage schedulePage = FinMgrDiscountSchedulePage.getInstance();
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoDiscountPage();
		fnm.gotoDiscountSchedulePg();
		//1. add a new discount schedule as precondition
		schedule.scheduleId = fnm.addNewDiscountSchedule(schedule);

		//2. search by all search criteria(except discount schedule id) and this discount schedule shall be found
		schedulePage.searchDiscountSchedule(schedule);
		schedulePage.verifyDiscountScheduleExists(schedule.scheduleId);
		
		//3. search by all search criteria(include discount schedule id) and system shall ignore other conditions only search by schedule id
		schedule.searchBy = "Discount Schedule ID";
		schedule.searchValue = schedule.scheduleId;
		schedule.productCategory = "Site";//other conditions shall be ignored
		schedule.salesChannel = "Call Center";
		schedulePage.searchDiscountSchedule(schedule);
		schedulePage.verifyDiscountScheduleExists(schedule.scheduleId);
		
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		String facilityID = "552903";//Jordan Lake State Rec Area
		
		//discount schedule info
		schedule.location = fnm.getFacilityName(facilityID, schema);
		schedule.locationCategory = "Park";
		
		schedule.activeStatus = OrmsConstants.INACTIVE_STATUS;
		schedule.discountName = "ChangePromoCodeFrom";
		schedule.feeType = "Use Fee";
		schedule.rateType = "Percent";
		schedule.appRate = "Per Stay";
		schedule.additionalDiscount = "No";
		schedule.automaticDiscount = "Yes";
		schedule.displayDiscount = "Yes";
		schedule.buyXGetYDiscount = "No";
		
		schedule.productCategory = "Slip";
		schedule.dock = "DockForTimeToClear";
		schedule.productGroup = "Full attributes";
		schedule.product = "Time To Clear Slip 1";
		schedule.effectiveDate = DateFunctions.getDateAfterToday(-5);
		schedule.startDate = schedule.effectiveDate;
		schedule.endDate = DateFunctions.getDateAfterToday(3);
		schedule.marinaRateType = OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT;
		schedule.rate = String.valueOf(NumberUtil.getRandomDouble(10, 20));
		schedule.salesChannel = "Field";
		schedule.customerType = "Senior";
		schedule.custPass = "Senior Pass";
		schedule.member = "Yes";
		schedule.season = "Peak";
		schedule.state = "In State";
		schedule.boatCategory = "Personal";
		schedule.dateType = "Effective Date";
		schedule.fromDate = schedule.effectiveDate;
		schedule.toDate = schedule.effectiveDate;
	}
}
