/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.discount;

import com.activenetwork.qa.awo.datacollection.legacy.DiscountData;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountScheduleDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountSchedulePage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:test order create start date section when adding discount
 *                   schedule 1.verify order create start date section exist in
 *                   the attribute fee/percent/per unit of stay 2.verify order
 *                   create start date section is not exist in the transaction
 *                   fee 3.verify order create start date section is not exist
 *                   in the override 4.verify order create start date section
 *                   exist in the use fee/flat/per stay 5.add discount schedule
 *                   successful and verify order create start date format
 * 
 * @Preconditions: discount:discount for test Order Create Start Date -- percent
 *                 discount for test Order Create Start Date -- flat discount
 *                 for test Order Create Start Date -- override
 * 
 * @LinkSetUp:d_fin_add_discount:id=1160,1170,1180
 * 
 * @SPEC:Dates - Order Create Start Date [TC:058391] Discount Schedule with
 *             Override Rate Type [TC:058399] Discount Schedule for Transaction
 *             Fee [TC:058398] Discount Schedule for Use/Attribute Fee with
 *             Flat/Percent to be applied Per Unit [TC:058397] Discount Schedule
 *             for Use/Attribute Fee with Flat/Percent to be applied Per Stay
 *             [TC:058396]
 * 
 * @Task#:AUTO-1952
 * 
 * @author szhou
 * @Date Dec 18, 2013
 */
public class VerifyOrderCreateStartDate_DiscountSchedule extends
		FinanceManagerTestCase {
	private DiscountData discount = new DiscountData();
	private ScheduleData schedule = new ScheduleData();

	@Override
	public void execute() {
		// check data set up
		this.checkDiscountPrepareData(schema, discount.name);

		// login finance manager
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();

		// go to discount page
		fnm.gotoDiscountPage();
		fnm.gotoDiscountSchedulePg();

		// go to discount schedule detail page
		this.gotoDiscountScheduleDetailPg(schedule);

		// verify order create start date section exist in the attribute fee/percent/per unit of stay
		this.verifyOrderCreateStartDateSection(true);

		// verify order create start date section is not exist in the transaction fee
		this.chooseFeeType("Transaction Fee");
		this.verifyOrderCreateStartDateSection(false);
		this.goBackToDiscountMainPage();

		// verify order create start date section is not exist in the override
		discount.name = "discount for test Order Create Start Date -- override";
		schedule.discountName = discount.name;
		// check data set up
		this.checkDiscountPrepareData(schema, discount.name);
		fnm.gotoDiscountSchedulePg();
		// go to discount schedule detail page
		this.gotoDiscountScheduleDetailPg(schedule);
		this.verifyOrderCreateStartDateSection(false);
		this.goBackToDiscountMainPage();

		// verify order create start date section exist in the use fee/flat/per stay
		discount.name = "discount for test Order Create Start Date -- flat";
		schedule.discountName = discount.name;
		// check data set up
		this.checkDiscountPrepareData(schema, discount.name);
		fnm.gotoDiscountSchedulePg();
		// go to discount schedule detail page
		this.gotoDiscountScheduleDetailPg(schedule);
		this.verifyOrderCreateStartDateSection(true);
		this.goBackToDiscountMainPage();

		// add discount schedule successful and verify order create start date format
		fnm.gotoDiscountSchedulePg();
		schedule.scheduleId = fnm.addNewDiscountSchedule(schedule, false);
		// goto schedule details page and verify schedule detail
		this.verifyDiscountScheduleDetailInfo(schedule);
		this.goBackToDiscountMainPage();

		fnm.logoutFinanceManager();
	}

	private void checkDiscountPrepareData(String schema, String discountName) {
		// check discount name is exist.
		String discount = fnm.getDiscountPromoCode(discountName, schema);
		if ("".equals(discount)) {
			throw new ErrorOnDataException("There is need a discount:"
					+ discountName + "; Please check data set up...");
		}
	}

	private void verifyDiscountScheduleDetailInfo(ScheduleData schedule) {
		FinMgrDiscountScheduleDetailPage schedDetailPg = FinMgrDiscountScheduleDetailPage
				.getInstance();
		FinMgrDiscountSchedulePage schedulePg = FinMgrDiscountSchedulePage
				.getInstance();

		schedulePg.gotoDiscountSchDetailPg(schedule.scheduleId);
		schedDetailPg.waitLoading();
		schedDetailPg.verifyScheduleDetail(schedule);
	}

	private void goBackToDiscountMainPage() {
		FinMgrDiscountScheduleDetailPage detailPg = FinMgrDiscountScheduleDetailPage
				.getInstance();
		FinMgrDiscountSchedulePage schedulePg = FinMgrDiscountSchedulePage
				.getInstance();
		FinMgrDiscountMainPage discountPg = FinMgrDiscountMainPage
				.getInstance();

		detailPg.clickCancel();
		schedulePg.waitLoading();

		schedulePg.clickDiscounts();
		discountPg.waitLoading();
	}

	private void chooseFeeType(String type) {
		FinMgrDiscountScheduleDetailPage detailPg = FinMgrDiscountScheduleDetailPage
				.getInstance();

		detailPg.selectFeeType(type);
		detailPg.waitLoading();
	}

	private void verifyOrderCreateStartDateSection(boolean exist) {
		FinMgrDiscountScheduleDetailPage detailPg = FinMgrDiscountScheduleDetailPage
				.getInstance();

		boolean value = detailPg.isOrderCreateStartDateExist();
		if (exist != value) {
			throw new ErrorOnPageException(
					exist ? "Order Create Start Date Section should display in the detail page.."
							: "Order Create Start Date Section should not display in the detail page..");
		}

	}

	private void gotoDiscountScheduleDetailPg(ScheduleData schedule) {
		FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage
				.getInstance();
		FinMgrDiscountScheduleDetailPage detailPg = FinMgrDiscountScheduleDetailPage
				.getInstance();
		FinMgrDiscountSchedulePage schedulePg = FinMgrDiscountSchedulePage
				.getInstance();

		schedulePg.clickAddNew();

		findLocPg.waitLoading();

		findLocPg.searchByLocationName(schedule.location,
				schedule.locationCategory);
		findLocPg.selectLocation(schedule.location);
		detailPg.waitLoading();
		detailPg.setupDiscountNameFeeTypeAndProductCategory(schedule);
	}

	@Override
	public void wrapParameters(Object[] param) {
		// initialize login finance manager loginInfo
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		schema = DataBaseFunctions.getSchemaName("NC", env);

		// initialize Discount info
		discount.name = "discount for test Order Create Start Date -- percent";

		// initialize Discount Schedule data
		schedule.location = "Carolina Beach State Park";
		schedule.locationCategory = "Park";
		schedule.discountName = discount.name;
		schedule.feeType = "Use Fee";
		schedule.productCategory = "Site";
		schedule.loop = "All";
		schedule.product = "001";
		schedule.productGroup = "Primitive Group Camping";
		schedule.effectiveDate = DateFunctions.formatDate(
				DateFunctions.getDateAfterToday(-1), "EEE MMM d yyyy");
		schedule.startDate = DateFunctions.formatDate(
				DateFunctions.getDateAfterToday(-1), "EEE MMM d yyyy");
		schedule.endDate = DateFunctions.formatDate(
				DateFunctions.getDateAfterToday(3), "EEE MMM d yyyy");
		schedule.orderCreateStartDate = DateFunctions.formatDate(
				DateFunctions.getDateAfterToday(3), "EEE MMM d yyyy");
		schedule.rate = "5.0";
		schedule.season = "All";
		schedule.salesChannel = "Field";
		schedule.state = "All";
		schedule.customerType = "All";
		schedule.custPass = "All";
		schedule.member = "All";
		schedule.accountCode = "3000.1601.000200000; Default Overage/Shortage";

	}

}
