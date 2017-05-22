package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin.feeAuditLogs;

import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrFeeAuditLogsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case contains the scenario:
 *              1. create transaction fee schedule for List work flow
 *              2. update this transaction fee schedule marina rate type info
 *              2. go to admin manager to verify edit transaction fee schedule audit log
 * @Preconditions:
 * @SPEC: Edit TRNS Schedule - Log [TC:049739]
 * @Task#:AUTO-1427
 * 
 * @author vzhang
 * @Date  Jan 24, 2013
 */

public class EditTranFeeSchd_Slip_MarinaRateType extends AdminManagerTestCase{
	private LoginInfo loginFnm = new LoginInfo();
	private FinanceManager fnm = FinanceManager.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();
	private AuditLogInfo  feeLogs = new AuditLogInfo();
	private AdminMgrFeeAuditLogsPage feeAuditLogsPg = AdminMgrFeeAuditLogsPage.getInstance();
	private String orgMarinaRateType, newMarinaRateType;

	@Override
	public void execute() {
		//login finance manager
		fnm.loginFinanceManager(loginFnm);
		fnm.gotoFeeMainPage();
		//add a new marina transaction fee schedule
		schedule.feeSchdId = fnm.addNewFeeSchedule(schedule);
		
		//go to fee schedule detail page update marina rate type
		fnm.gotoFeeSchedulePageByScheduleId(schedule.feeSchdId);
		newMarinaRateType = "Seasonal";
		schedule.marinaRateType = newMarinaRateType;
		fnm.editFeeSchedule(schedule);
		fnm.logoutFinanceManager();
		
		//go to admin manger verify edit audit log
		am.loginAdminManager(login, false);
		am.gotoFeeAuditLogsPage();
		feeLogs.searchValue = schedule.feeSchdId;
		feeLogs.actionDetails = "Old Fee Schedule ID: " + schedule.feeSchdId + ", New Fee Schedule ID: " + schedule.feeSchdId
		                      + ", Product Category: " + schedule.productCategory + ", Product Group: " + schedule.productGroup + ", Product: " + schedule.product
		                      + ", Fee Type: " + schedule.feeType
		                      + " Marina Rate Type: " + orgMarinaRateType + " --> " + newMarinaRateType;
		
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		this.verifyEditTranFeeSchdAuditLog(feeLogs);
		am.logoutAdminMgr();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract= "NC Contract";
		login.location= "Administrator/North Carolina State Parks";
		schema = DataBaseFunctions.getSchemaName("NC", env);

		loginFnm.url = login.url;
		loginFnm.contract = login.contract;
		loginFnm.location = login.location;
		loginFnm.userName = TestProperty.getProperty("orms.fnm.user");
		loginFnm.password = TestProperty.getProperty("orms.fnm.pw");
		
		schema = DataBaseFunctions.getSchemaName("NC", env);
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		schedule.location = fnm.getFacilityName("552903", schema);//"Jordan Lake State Rec Area";
		schedule.locationCategory = "Park";
		// initialize attribute fee schedule data
		schedule.productCategory = "Slip";
		schedule.feeType = "Transaction Fee";
		schedule.dock = "All";
		schedule.productGroup = "All";
		schedule.product = "All";
		schedule.effectDate = DateFunctions.getToday(timeZone);
		schedule.salesChannel = "All";
		schedule.state = "All";
		schedule.tranType = "Cancellation";
		schedule.tranOccur = "All";
		schedule.acctCode = "3000.1601.000200000; Default Overage/Shortage";
		orgMarinaRateType = "Transient";
		schedule.marinaRateType = orgMarinaRateType;
		schedule.tranFee = "12.21";
		schedule.tranFeeOption = "Per Transaction";
		
		feeLogs.searchType = "Action Details";
		feeLogs.logArea = "Fee Schedule";
		feeLogs.actionType = "Update";
		feeLogs.dateTime = DateFunctions.getToday(timeZone);
		feeLogs.stratDate = null;
		feeLogs.endDate = null;
		feeLogs.action = feeLogs.actionType + " " + feeLogs.logArea;
		feeLogs.affectedLocation = schedule.location;
		feeLogs.userName = loginFnm.userName;
		feeLogs.application = "Finance";
	}
	
	private void verifyEditTranFeeSchdAuditLog(AuditLogInfo expAudit){
		logger.info("Verify eidt transaction fee audit log info.");
		List<AuditLogInfo> actAuditList = feeAuditLogsPg.getAuditLogInfoList();
		boolean result = true;
		
		if(actAuditList.size() != 1){
			throw new ErrorOnPageException("Audit list info is not correct.");
		}
		
		result &= MiscFunctions.compareResult("Date Time info", expAudit.dateTime, actAuditList.get(0).dateTime);
		result &= MiscFunctions.compareResult("Log Area", expAudit.logArea, actAuditList.get(0).logArea);
		result &= MiscFunctions.compareResult("Action", expAudit.action, actAuditList.get(0).action);
		result &= MiscFunctions.compareResult("Action Detail", expAudit.actionDetails, actAuditList.get(0).actionDetails);
		result &= MiscFunctions.compareResult("Affected Location", expAudit.affectedLocation, actAuditList.get(0).affectedLocation);
		result &= MiscFunctions.compareResult("User Name", expAudit.userName, actAuditList.get(0).userName);
		result &= MiscFunctions.compareResult("Application", expAudit.application, actAuditList.get(0).application);
		
		if(!result){
			throw new ErrorOnPageException("Audit list info is not correct. Please check log.");
		}else logger.info("Audit list info is correct.");
		
	}

}
