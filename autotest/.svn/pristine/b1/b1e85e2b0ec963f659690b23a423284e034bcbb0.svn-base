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
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case contains the scenario:
 *              1. create transaction fee schedule for Slip work flow
 *              2. go to admin manager to verify add transaction fee schedule audit log
 * @Preconditions:
 * @SPEC: Add TRNS Fee Schedule - Log [TC:049743]
 * @Task#:AUTO-1419
 * 
 * @author vzhang
 * @Date  Jan 8, 2013
 */

public class AddTranFeeSchdAuditLogs_Slip extends AdminManagerTestCase{
	private LoginInfo loginFnm = new LoginInfo();
	private FinanceManager fnm = FinanceManager.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();
	private AuditLogInfo  feeLogs = new AuditLogInfo();
	private AdminMgrFeeAuditLogsPage feeAuditLogsPg = AdminMgrFeeAuditLogsPage.getInstance();
	private String prdName, prdCode;

	@Override
	public void execute() {
		//login finance manager
		fnm.loginFinanceManager(loginFnm);
		fnm.gotoFeeMainPage();
		
	    schedule.feeSchdId = fnm.addNewFeeSchedule(schedule);
	    if(!schedule.feeSchdId.matches("\\d+")){
	    	throw new ErrorOnPageException("Expect add use fee schedule success, generate use fee schedule id.");
	    }
		fnm.logoutFinanceManager();
		
		am.loginAdminMgr(login);
		am.gotoFeeAuditLogsPage();
		feeLogs.searchValue = "New Fee Schedule ID: " + schedule.feeSchdId;
		feeLogs.actionDetails = "Old Fee Schedule ID: None, New Fee Schedule ID: " + schedule.feeSchdId 
		                      + ", Product Category: " + schedule.productCategory 
		                      + ", Product Group: " + schedule.productGroup 
		                      + ", Product: " + prdName
		                      + ", Fee Type: " + schedule.feeType;
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		this.verifyAddTranFeeSchdAuditLog(feeLogs);
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

		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		schedule.locationCategory = "Park";
		schedule.location = fnm.getFacilityName("552818", schema);//Mayo River State Park
		schedule.productCategory = "Slip";
	  	schedule.feeType ="Transaction Fee";
	  	schedule.dock = "OverlapSeasonDock";
	  	schedule.productGroup = "Full attributes";
	  	prdName = "slip for fee schedule";
	  	prdCode = "PZH";
	  	schedule.product = prdCode + "-" + prdName;
		schedule.effectDate = DateFunctions.getToday(timeZone);
	  	schedule.salesChannel = "All";
	  	schedule.state = "All";
	  	schedule.tranType = "Cancellation";
	  	schedule.tranOccur = "All";
	  	schedule.marinaRateType = "Seasonal";
	  	schedule.tranFee = "13";
	  	
	  	feeLogs.searchType = "Action Details";
		feeLogs.stratDate = DateFunctions.getToday(timeZone);
		feeLogs.endDate = feeLogs.stratDate;
		feeLogs.actionType = "Add";
		feeLogs.dateTime = feeLogs.stratDate;
		feeLogs.logArea = "Fee Schedule";
		feeLogs.action = "Add Fee Schedule";
		feeLogs.affectedLocation = schedule.location;
		feeLogs.userName = loginFnm.userName;
		feeLogs.application = "Finance";
		
	}
	
	private void verifyAddTranFeeSchdAuditLog(AuditLogInfo expAudit){
		logger.info("Verify add transaction fee audit log info.");
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
