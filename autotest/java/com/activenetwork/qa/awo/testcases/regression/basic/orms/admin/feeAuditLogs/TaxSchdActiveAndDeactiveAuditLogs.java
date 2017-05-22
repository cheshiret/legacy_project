package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin.feeAuditLogs;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrFeeAuditLogsPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchedulePage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:verify the audit logs in admine when active and deactive action.
 * @Preconditions:
 * @SPEC:Log Change to Tax [TC:033707]
 * @Task#:Auto-1301
 * 
 * @author Jasmine
 * @Date  Nov 02, 2012
 */
public class TaxSchdActiveAndDeactiveAuditLogs extends AdminManagerTestCase{
	private LoginInfo loginFnm = new LoginInfo();
	private FinanceManager fnm = FinanceManager.getInstance();
	private ScheduleData schedule = new ScheduleData();
	private AuditLogInfo feeLogs = new AuditLogInfo();
	private AdminMgrFeeAuditLogsPage feeAuditLogsPg = AdminMgrFeeAuditLogsPage
			.getInstance();
	private FinMgrTaxSchedulePage taxSchPg = FinMgrTaxSchedulePage.getInstance();
	
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoTaxMainPage();
		schedule.scheduleId = fnm.addNewTaxSchedule(schedule);
		logger.info(schedule.scheduleId );
		taxSchPg.changeTaxScheduleStatus(schedule.scheduleId, "Active");
		fnm.logoutFinanceManager();
		
		feeLogs.searchValue = schedule.scheduleId;
		this.getTaxSchdlDetailInfo();
		am.loginAdminMgr(login);
		am.gotoAuditLogsPage("Fee Audit Logs");
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		feeAuditLogsPg.verifyFeeAuditLogs(feeLogs);
		am.logoutAdminMgr();
		
		fnm.loginFinanceManager(login);
		fnm.gotoTaxMainPage();
		fnm.gotoTaxSchedulePage();
		taxSchPg.changeTaxScheduleStatus(schedule.scheduleId, "Inactive");
		fnm.logoutFinanceManager();
		
		this.getTaxSchdlDetailInfo();
		feeLogs.actionType = "Deactivate";
		feeLogs.action = "Deactivate Tax Schedule";
		am.loginAdminMgr(login);
		am.gotoAuditLogsPage("Fee Audit Logs");
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		feeAuditLogsPg.verifyFeeAuditLogs(feeLogs);
		am.logoutAdminMgr();
	}

	
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator/AutoWarehouse";
		schema = DataBaseFunctions.getSchemaName("MS", env);

		loginFnm.url = login.url;
		loginFnm.contract = login.contract;
		loginFnm.location = login.location;
		loginFnm.userName = TestProperty.getProperty("orms.fnm.user");
		loginFnm.password = TestProperty.getProperty("orms.fnm.pw");

		schedule.taxName = "TaxSchdlAuditLog";
		schedule.productCategory = "Site";
		schedule.feeType = "Attribute Fee";
		schedule.productGroup = "Villa";
		schedule.product = "All";
		schedule.customerType = "All";
		schedule.location = "AutoWarehouse"; // ID : 70034
		schedule.rate = "8.88";

		
		feeLogs.searchType = "Action Details";
		//feeLogs.searchValue = schedule.scheduleId;
		feeLogs.stratDate = DateFunctions.getToday();
		feeLogs.endDate = DateFunctions.getToday();
		feeLogs.actionType = "Activate";
		feeLogs.dateTime = DateFunctions.formatDate(DateFunctions.getToday(),
				"MMM dd,yyyy");
		feeLogs.logArea = "Tax Schedule";
		feeLogs.action = "Activate Tax Schedule";
		
		feeLogs.affectedLocation = login.location.split("/")[1];
		feeLogs.userName = loginFnm.userName;
		feeLogs.application = "Finance";
	}
	/**
	 * get tax Schdl detail info.
	 */
	private void getTaxSchdlDetailInfo(){
		feeLogs.actionDetails = "Old Tax Schedule ID: "+schedule.scheduleId+", New Tax Schedule ID: "+schedule.scheduleId+", Tax Name: "+schedule.taxName+", Product Category: "
				+schedule.productCategory+", Product Group: "+schedule.productGroup + ", Product: "+schedule.product+", Fee Type: "+schedule.feeType;
	}
}
