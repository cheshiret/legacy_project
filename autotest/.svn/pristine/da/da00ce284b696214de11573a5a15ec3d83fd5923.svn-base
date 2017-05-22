/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin.feeAuditLogs;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.DiscountData;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrFeeAuditLogsPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountScheduleDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountSchedulePage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:test audit log for update discount schedule 1.add discount
 *                   schedule in Finance Manager 2.update discount schedule in
 *                   Finance Manager 3.go to admin manager and verify audit log
 *                   in admin mgr
 * 
 * @Preconditions: discount:discount for test Order Create Start Date Changed
 * 
 * @LinkSetUp:d_fin_add_discount:id=1380
 * 
 * @SPEC:Change the Order Create Start Date and the discount schedule is Active
 *              and is used [TC:058401]
 * 
 * @Task#:AUTO-1953
 * 
 * @author szhou
 * @Date Dec 23, 2013
 */
public class EditDiscountSchedule_Camping extends AdminManagerTestCase {
	private AdminMgrFeeAuditLogsPage feeAuditLogsPg = AdminMgrFeeAuditLogsPage
			.getInstance();
	private FinanceManager fnm = FinanceManager.getInstance();
	private LoginInfo loginFnm = new LoginInfo();
	private AuditLogInfo feeLogs = new AuditLogInfo();
	private DiscountData discount = new DiscountData();
	private ScheduleData schedule = new ScheduleData();

	@Override
	public void execute() {
		// check data set up
		this.checkDiscountPrepareData(schema, discount.name);

		// log into Finance Mgr
		fnm.loginFinanceManager(loginFnm);
		fnm.gotoFeeMainPage();
		// go to discount page and add new
		fnm.gotoDiscountPage();
		fnm.gotoDiscountSchedulePg();
		schedule.scheduleId = fnm.addNewDiscountSchedule(schedule, false);
		this.gotoDiscountScheduleDetailPage(schedule.scheduleId);
		String oldDate = schedule.orderCreateStartDate;
		schedule.orderCreateStartDate = DateFunctions.formatDate(
				DateFunctions.getDateAfterToday(3), "EEE MMM d yyyy");
		schedule.scheduleId = this.updateOrderCreateStartDate();
		// log out Finance Mgr
		fnm.logoutFinanceManager();

		// log into Admin Mgr
		am.loginAdminMgr(login);
		// go to audit log page and verify audit logs
		am.gotoFeeAuditLogsPage();
		// verify audit logs
		feeLogs.searchValue = "New Discount Schedule ID: "
				+ schedule.scheduleId;
		feeLogs.actionDetails = "Old Discount Schedule ID: "
				+ schedule.scheduleId
				+ ", New Discount Schedule ID: "
				+ schedule.scheduleId
				+ ", Discount Name: "
				+ discount.name
				+ ", Product Category: "
				+ schedule.productCategory
				+ ", Product Group: "
				+ schedule.productGroup
				+ ", Product: "
				+ schedule.product
				+ ", Fee Type: "
				+ schedule.feeType
				+ " Dates - Order Create Start Date: "
				+ DateFunctions.formatDate(oldDate, "MM/dd/yyyy")
				+ " --> "
				+ DateFunctions.formatDate(schedule.orderCreateStartDate,
						"MM/dd/yyyy");
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		feeAuditLogsPg.verifyFeeAuditLogs(feeLogs);
		// log out Admin Mgr
		am.logoutAdminMgr();

	}

	private void checkDiscountPrepareData(String schema, String discountName) {
		// check discount name is exist.
		String discount = fnm.getDiscountPromoCode(discountName, schema);
		if ("".equals(discount)) {
			throw new ErrorOnDataException("There is need a discount:"
					+ discountName + "; Please check data set up...");
		}
	}

	private String updateOrderCreateStartDate() {
		FinMgrDiscountScheduleDetailPage scheduleDetailPg = FinMgrDiscountScheduleDetailPage
				.getInstance();
		FinMgrDiscountSchedulePage schedulePg = FinMgrDiscountSchedulePage
				.getInstance();

		scheduleDetailPg
				.setOrderCreateStartDate(schedule.orderCreateStartDate);
		scheduleDetailPg.clickApply();
		scheduleDetailPg.waitLoading();
		String id = scheduleDetailPg.getScheduleId();
		scheduleDetailPg.clickOK();
		schedulePg.waitLoading();

		return id;
	}

	private void gotoDiscountScheduleDetailPage(String id) {
		FinMgrDiscountSchedulePage schedulePg = FinMgrDiscountSchedulePage
				.getInstance();
		FinMgrDiscountScheduleDetailPage scheduleDetailPg = FinMgrDiscountScheduleDetailPage
				.getInstance();

		schedulePg.searchByScheduleId(id);
		schedulePg.gotoDiscountSchDetailPg(id);
		scheduleDetailPg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// initialize login admin manager loginInfo
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		schema = DataBaseFunctions.getSchemaName("NC", env);
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);

		// initialize login finance manager loginInfo
		loginFnm.url = login.url;
		loginFnm.contract = login.contract;
		loginFnm.location = login.location;
		loginFnm.userName = TestProperty.getProperty("orms.fnm.user");
		loginFnm.password = TestProperty.getProperty("orms.fnm.pw");

		// initialize Discount info
		discount.name = "discount for test Order Create Start Date Changed";

		// initialize Discount Schedule data
		schedule.location = "Carolina Beach State Park";
		schedule.locationCategory = "Park";
		schedule.discountName = discount.name;
		schedule.feeType = "Use Fee";
		schedule.productCategory = "Site";
		schedule.loop = "All";
		schedule.product = "002";
		schedule.productGroup = "Primitive Group Camping";
		schedule.effectiveDate = DateFunctions.formatDate(
				DateFunctions.getDateAfterToday(-1), "EEE MMM d yyyy");
		schedule.startDate = DateFunctions.formatDate(
				DateFunctions.getDateAfterToday(-1), "EEE MMM d yyyy");
		schedule.endDate = DateFunctions.formatDate(
				DateFunctions.getDateAfterToday(3), "EEE MMM d yyyy");
		schedule.orderCreateStartDate = DateFunctions.formatDate(
				DateFunctions.getDateAfterToday(-1), "EEE MMM d yyyy");
		schedule.rate = "7.0";
		schedule.season = "All";
		schedule.salesChannel = "Field";
		schedule.state = "All";
		schedule.customerType = "All";
		schedule.custPass = "All";
		schedule.member = "All";
		schedule.accountCode = "3000.1601.000200000; Default Overage/Shortage";

		// initialize fee audit log info
		feeLogs.searchType = "Action Details";
		feeLogs.stratDate = DateFunctions.getToday(timeZone);
		feeLogs.endDate = feeLogs.stratDate;
		feeLogs.actionType = "Update";
		feeLogs.dateTime = feeLogs.stratDate;
		feeLogs.logArea = "Discount Schedule";
		feeLogs.action = "Update Discount Schedule";
		feeLogs.affectedLocation = schedule.location;
		feeLogs.userName = loginFnm.userName;
		feeLogs.application = "Finance";

	}

}
