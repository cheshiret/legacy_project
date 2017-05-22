package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin.feeAuditLogs;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.FeePenaltyData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrFeeAuditLogsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Add a Penalty Fee for Slip and check the audit log in Admin Manager.
 * @Preconditions:
 * @SPEC:Add Fee Penalty Scheduel - Log [TC:049452]
 * @Task#:AUTO-1431 
 * 
 * @author Jasmine
 * @Date  Jan 28, 2013
 */
public class AddFeePenaltyAuditLogs_Slip extends AdminManagerTestCase{
	private FeePenaltyData schedule = new FeePenaltyData();
	private FinanceManager fnm = FinanceManager.getInstance();
	private LoginInfo loginFnm = new LoginInfo();
	private AuditLogInfo  feeLogs = new AuditLogInfo();
	private AdminMgrFeeAuditLogsPage feeAuditLogsPg = AdminMgrFeeAuditLogsPage.getInstance();
	public void execute() {
		fnm.loginFinanceManager(loginFnm);
		fnm.gotoFeeMainPage();
		fnm.gotoFeePenaltyPage();
		schedule.id = fnm.addNewFeePenalty(schedule);
		fnm.logoutFinanceManager();
		
		am.loginAdminMgr(login);
		am.gotoFeeAuditLogsPage();
		feeLogs.searchValue = schedule.id;
		feeLogs.actionDetails = "Old Fee Penalty Schedule ID: none, New Fee Penalty Schedule ID: " + schedule.id 
		                      + ", Product Category: " + schedule.productCategory 
		                      + ", Product Group: " + schedule.productGroup 
		                      + ", Product: " + schedule.product;
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		feeAuditLogsPg.verifyFeeAuditLogs(feeLogs);
		am.logoutAdminMgr();
		
	}

	public void wrapParameters(Object[] param) {
		login.contract= "NC Contract";
		login.location= "Administrator/North Carolina State Parks";
		schema = DataBaseFunctions.getSchemaName("NC", env);

		loginFnm.url = login.url;
		loginFnm.contract = login.contract;
		loginFnm.location = login.location;
		loginFnm.userName = TestProperty.getProperty("orms.fnm.user");
		loginFnm.password = TestProperty.getProperty("orms.fnm.pw");
		
		schedule.productCategory = "Slip";
		schedule.feeType = "Use Fee";
		schedule.location = fnm.getFacilityName("552834", schema);// Medoc Mountain State Park
		schedule.locationCategory = "Park";
		schedule.loop = null;
		schedule.dock = "All";
		schedule.productGroup = "Full attributes";
		schedule.product = "All";
		schedule.effectDate = DateFunctions.formatDate(DateFunctions.getToday(), "EEE MMM d yyyy");
		schedule.startInv = DateFunctions.formatDate(DateFunctions.getToday(), "EEE MMM d yyyy");
		schedule.endInv = DateFunctions.formatDate(DateFunctions.getToday(), "EEE MMM d yyyy");
		schedule.tranType = "Change Boat";
		schedule.tranOccur = "All";
		schedule.marinaRateType = "Transient";
		schedule.units = "Nights";
		schedule.value = "5";	
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		feeLogs.searchType = "Action Details";
		feeLogs.stratDate = DateFunctions.getToday(timeZone);
		feeLogs.endDate = feeLogs.stratDate;
		feeLogs.actionType = "Add";
		feeLogs.dateTime = feeLogs.stratDate;
		feeLogs.logArea = "Fee Penalty Schedule";
		feeLogs.action = "Add Fee Penalty Schedule";
		feeLogs.affectedLocation = login.location.split("/")[1];
		feeLogs.userName = loginFnm.userName;
		feeLogs.application = "Finance";
	}

}
