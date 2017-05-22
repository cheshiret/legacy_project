/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin.feeAuditLogs;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.DiscountData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrFeeAuditLogsPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountDetailsPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.discount.FinMgrDiscountMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:test audit log for update discount
 * 1.add discount in Finance Manager
 * 2.update discount in Finance Manager
 * 3.go to admin manager and verify audit log in admin mgr
 * 
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:Audit Log [TC:058388]
 * @Task#:AUTO-1953
 * 
 * @author szhou
 * @Date Dec 19, 2013
 */
public class EditDiscount_Camping extends AdminManagerTestCase {
	private AdminMgrFeeAuditLogsPage feeAuditLogsPg = AdminMgrFeeAuditLogsPage
			.getInstance();
	private FinanceManager fnm = FinanceManager.getInstance();
	private LoginInfo loginFnm = new LoginInfo();
	private AuditLogInfo feeLogs = new AuditLogInfo();
	private DiscountData discount = new DiscountData();

	@Override
	public void execute() {
		// log into Finance Mgr
		fnm.loginFinanceManager(loginFnm);
		fnm.gotoFeeMainPage();
		// go to discount page and add new
		fnm.gotoDiscountPage();
		// add a new discount
		fnm.addNewDiscount(discount);
		// edit discount
		discount.maxUsagePerCust = "2";
		this.gotoDiscountDetailPage(discount.name);
		this.editMaximumUsagePerCustInDiscountDetailPg(discount.maxUsagePerCust);
		// log out Finance Mgr
		fnm.logoutFinanceManager();

		// log into Admin Mgr
		am.loginAdminMgr(login);
		// go to audit log page and verify audit logs
		am.gotoFeeAuditLogsPage();
		// verify audit logs
		feeLogs.searchValue = "Discount Name: " + discount.name;
		feeLogs.actionDetails = "Discount Name: " + discount.name
				+ ", Discount Rate Type: " + discount.rateType
				+ ", Fee Types: [" + discount.feeTypes.get(0) + ", "
				+ discount.feeTypes.get(1) + "] "
				+"Promo Codes - Promo Code, Promo Description, Maximum Usage Per Customer: "
				+discount.promoCode+", "+discount.promoDescription+", 1"+" --> "
				+discount.promoCode+", "+discount.promoDescription+", "+discount.maxUsagePerCust;
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		feeAuditLogsPg.verifyFeeAuditLogs(feeLogs);
		// log out Admin Mgr
		am.logoutAdminMgr();

	}
	
	private void gotoDiscountDetailPage(String discount){
		FinMgrDiscountMainPage distMainPg = FinMgrDiscountMainPage
				.getInstance();
		FinMgrDiscountDetailsPage detailsPg = FinMgrDiscountDetailsPage
				.getInstance();

		distMainPg.searchByDistName(discount);
		distMainPg.gotoDiscountDetailPg(discount);
		detailsPg.waitLoading();
	}

	private void editMaximumUsagePerCustInDiscountDetailPg(String value) {
		FinMgrDiscountMainPage mainPg = FinMgrDiscountMainPage.getInstance();
		FinMgrDiscountDetailsPage detailsPg = FinMgrDiscountDetailsPage
				.getInstance();

		detailsPg.setMaxUsagePerCust(value);
		detailsPg.clickOK();
		browser.waitExists(mainPg, detailsPg);
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
		discount.name = "discount for test edit promo code"
				+ DateFunctions.getCurrentTime();
		discount.description = "Automation Test -- add discount for test promo codes";
		discount.rateType = "Percent";
		discount.feeTypes.add("Attribute Fee");
		discount.feeTypes.add("Use Fee");
		discount.behaviors.add("Automatic Discount");
		discount.behaviors.add("Display Discount");
		discount.rateUnit = "Per Stay";
		discount.promoCode = "PC0001";
		discount.promoDescription = "Automation Test";
		discount.maxUsagePerCust = "1";

		// initialize fee audit log info
		feeLogs.searchType = "Action Details";
		feeLogs.stratDate = DateFunctions.getToday(timeZone);
		feeLogs.endDate = feeLogs.stratDate;
		feeLogs.actionType = "Update";
		feeLogs.dateTime = feeLogs.stratDate;
		feeLogs.logArea = "Discount";
		feeLogs.action = "Update Discount";
		feeLogs.affectedLocation = login.location.split("/")[1];
		feeLogs.userName = loginFnm.userName;
		feeLogs.application = "Finance";

	}

}
