package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin.feeAuditLogs;

import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrFeeAuditLogsPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case contains the scenario:
 *              1. create transaction fee schedule for List work flow
 *              2. update this transaction fee schedule status
 *              2. go to admin manager to verify active/deactive transaction fee schedule audit log
 * @Preconditions:
 * @SPEC: Edit Fee Schedule - List, change status Log [TC:050701]
 * @Task#:AUTO-1427
 * 
 * @author vzhang
 * @Date  Jan 25, 2013
 */
public class ActiveAndDeactiveTranFeeSchd_List extends AdminManagerTestCase{
	private LoginInfo loginFnm = new LoginInfo();
	private FinanceManager fnm = FinanceManager.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();
	private AuditLogInfo  feeLogs = new AuditLogInfo();
	private AdminMgrFeeAuditLogsPage feeAuditLogsPg = AdminMgrFeeAuditLogsPage.getInstance();
	private FinMgrFeeMainPage listPage = FinMgrFeeMainPage.getInstance();

	@Override
	public void execute() {
		//login finance manager
		fnm.loginFinanceManager(loginFnm);
		fnm.gotoFeeMainPage();
		
	    schedule.feeSchdId = fnm.addNewFeeSchedule(schedule);
		//changed schedule status to active
		listPage.changeScheduleStatus(schedule.feeSchdId, OrmsConstants.ACTIVE_STATUS);
		
		//changed schedule status to inactive
		listPage.changeScheduleStatus(schedule.feeSchdId, OrmsConstants.INACTIVE_STATUS);
		fnm.logoutFinanceManager();
		
		am.loginAdminMgr(login);
		am.gotoFeeAuditLogsPage();
		//search and verify active fee schedule audit log
		feeLogs.searchValue = schedule.feeSchdId;
		feeLogs.actionType = "Activate";
		feeLogs.action = feeLogs.actionType + " " +feeLogs.logArea;
		feeLogs.actionDetails = "Old Fee Schedule ID: " + schedule.feeSchdId + ", New Fee Schedule ID: " + schedule.feeSchdId 
                              + ", Product Category: " + schedule.productCategory 
                              + ", Applicable Product Category: " + schedule.assignPrdCategory
                              + ", Product: " + schedule.product
                              + ", Fee Type: " + schedule.feeType;
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		this.verifyEditTranFeeSchdStatusAuditLog(feeLogs);
		
		//search and verify deactive fee schedule audit log
		feeLogs.actionType = "Deactivate";
		feeLogs.action = feeLogs.actionType + " " +feeLogs.logArea;
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		this.verifyEditTranFeeSchdStatusAuditLog(feeLogs);
		
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
		schedule.productCategory = "List";
		schedule.assignPrdCategory = "Slip";
	  	schedule.feeType ="Transaction Fee";
	  	schedule.product = "All";
		schedule.effectDate = DateFunctions.getDateAfterToday(271, timeZone);
	  	schedule.salesChannel = "All";
	  	schedule.state = "All";
	  	schedule.tranType = "Add to Waiting List";
	  	schedule.tranFee = "13";
	  	
	  	feeLogs.searchType = "Action Details";
		feeLogs.stratDate = DateFunctions.getToday(timeZone);
		feeLogs.endDate = feeLogs.stratDate;
		feeLogs.dateTime = feeLogs.stratDate;
		feeLogs.logArea = "Fee Schedule";
		feeLogs.affectedLocation = schedule.location;
		feeLogs.userName = loginFnm.userName;
		feeLogs.application = "Finance";
	}
	
	private void verifyEditTranFeeSchdStatusAuditLog(AuditLogInfo expAudit){
		logger.info("Verify edit transaction fee schedule status audit log info.");
		List<AuditLogInfo> actAuditList = feeAuditLogsPg.getAuditLogInfoList();
		boolean result = true;
		
		if(actAuditList.size() != 1){
			throw new ErrorOnPageException("Audit list info is not correct.");
		}
		int diff = DateFunctions.diffBetween(expAudit.dateTime,  actAuditList.get(0).dateTime);
		if(diff!=0){
			result &= false;
			logger.error("Date info not correct,expect date is "+expAudit.dateTime);
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
