package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin.feeAuditLogs;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
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
 *              1. create RA fee threshold schedule for Slip work flow
 *              2. go to admin manager to verify ra fee threshold schedule audit log
 * @Preconditions:
 * @SPEC:Add RA Fee Threshold Schedule - Write log [TC:043852]
 * @Task#:AUTO-1336
 * 
 * @author vzhang
 * @Date  Nov 13, 2012
 */

public class ThresholdAddAuditLog_Slip extends AdminManagerTestCase{
	private LoginInfo loginFnm = new LoginInfo();
	private FinanceManager fnm = FinanceManager.getInstance();
	private ScheduleData schedule = new ScheduleData();
	private AuditLogInfo  feeLogs = new AuditLogInfo();
	private AdminMgrFeeAuditLogsPage feeAuditLogsPg = AdminMgrFeeAuditLogsPage.getInstance();

	@Override
	public void execute() {
		//login finance manager
		fnm.loginFinanceManager(loginFnm);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeThresholdPage();

		//add new Ra Fee Threshold Schedule
		schedule.scheduleId = fnm.addNewRaFeeThresholdSchedule(schedule);
		if(!schedule.scheduleId.matches("\\d+")){
	    	throw new ErrorOnPageException("Expect add ra fee throeshold schedule success, generate threshod schedule id.");
	    }
		fnm.logoutFinanceManager();

		am.loginAdminMgr(login);
		am.gotoFeeAuditLogsPage();
		feeLogs.searchValue = "New RA Fee Threshold ID: " + schedule.scheduleId;
		feeLogs.actionDetails = "Old RA Fee Threshold ID: None, New RA Fee Threshold ID: " + schedule.scheduleId 
		                      + ", Product Category: " + schedule.productCategory 
		                      + ", Product Group: " + schedule.productGroup 
		                      + ", Product: " + schedule.product;
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		this.verifyThresholdAuditLog(feeLogs);
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
		//initialize schedule data
		schedule.productCategory = "Slip";
		schedule.dock = "All";
		schedule.productGroup = "All";
		schedule.product = "All";
		schedule.effectiveDate = DateFunctions.getToday(timeZone);
		schedule.salesChannel = "Field";
		schedule.tranType = "All";
		schedule.tranOccur = "All";
		schedule.startCounter = "1";
		schedule.currentCounter = "0";
		schedule.locationCategory = "Park";
		schedule.location = fnm.getFacilityName("552818", schema);//Mayo River State Park
		schedule.otherRanges = new ArrayList<String> ();
		schedule.otherRanges.add("3");
		schedule.otherRanges.add("6");
		schedule.otherRanges.add("9");

		feeLogs.searchType = "Action Details";
		feeLogs.stratDate = DateFunctions.getToday(timeZone);
		feeLogs.endDate = DateFunctions.getToday(timeZone);
		feeLogs.actionType = "Add";
		feeLogs.dateTime = DateFunctions.getToday(timeZone);
		feeLogs.logArea = "RA Fee Threshold";
		feeLogs.action = "Add RA Fee Threshold";
		feeLogs.affectedLocation =  schedule.location;
		feeLogs.userName = loginFnm.userName;
		feeLogs.application = "Finance";
	}
	
	private void verifyThresholdAuditLog(AuditLogInfo expAudit){
		logger.info("Verify threshold audit log info.");
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
