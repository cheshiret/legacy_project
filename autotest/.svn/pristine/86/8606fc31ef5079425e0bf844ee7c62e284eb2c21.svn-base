package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin.feeAuditLogs.searchfeeauditlogs;

import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrFeeAuditLogsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * @Description:This case verify message for search fee audit log
 * @LinkSetUp: none
 * @SPEC:[TC:032910]Search Fee Audit Logs - Successfully workflow 
 *		 [TC:032671]Search Fee Audit Logs - Message 2 
 * 	     [TC:032670]Search Fee Audit Logs - Message 1 
 * @Task#: Auto-2113
 * @author Phoebe Chen
 * @Date  March 25, 2014
 */
public class VerifyMessage extends AdminManagerTestCase{
	private AuditLogInfo  feeLogs = new AuditLogInfo();
	private AdminMgrFeeAuditLogsPage feeAuditLogsPg = AdminMgrFeeAuditLogsPage.getInstance();
	private String message1, message2, message3;
	@Override
	public void execute() {
		am.loginAdminMgr(login);
		am.gotoFeeAuditLogsPage();
		
		//Verify message show for no audit log found
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		verifyMessage(message1);
		
		//Verfy message show for more than 90 days between start date and end date
		feeLogs.stratDate = DateFunctions.getDateAfterGivenDay(feeLogs.endDate, -91);
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		verifyMessage(message2);
		
		//Verify message show for start date and end date is empty
		feeLogs.stratDate = StringUtil.EMPTY;
		feeLogs.endDate = StringUtil.EMPTY;
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		verifyMessage(message3);
		
		am.logoutAdminMgr();
	}

	private void verifyMessage(String expMsg) {
		String actMsg = feeAuditLogsPg.getMessage();
		if(!actMsg.matches(expMsg)){
			throw new ErrorOnPageException("Message is not correct as expect", expMsg, actMsg);
		}
		logger.info("Message is correct!");
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract= "NC Contract";
		login.location= "Administrator/North Carolina State Parks";
		
		//Set up audit log search infor
	  	feeLogs.searchType = "Action Details";
	  	feeLogs.searchValue = "Fee audit log does not exist";
		feeLogs.stratDate = DateFunctions.getToday();
		feeLogs.endDate = feeLogs.stratDate;
		feeLogs.logArea = "Fee Schedule";
		feeLogs.actionType = "Activate";
		
		//Set up message information
		message1="No Audit Logs found for the search criteria.";
		message2="Search period exceeds the maximum period of 90 days. Please change the Start Date or End Date.";
		message3="A date range is required. Please specify a Start Date and an End Date.";
	}
}
