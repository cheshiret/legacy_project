package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin.feeAuditLogs.searchfeeauditlogs;

import java.util.Random;
import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrFeeAuditLogsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case verify search criterial for search fee audit log
 * @LinkSetUp: none
 * @SPEC:[TC:032910] Search Fee Audit Logs - Successfully workflow 
 * 		 [TC:032515] Search Fee Audit Logs - Search dorpdown list and free-text Input Box 
 * 	     [TC:032911] Search Fee Audit Logs - Log Area dropdown list 
 * 		 [TC:032912] Search Fee Audit Logs - Action Type dropdown List 
 * 		 [TC:032514] Fee Audit Logs tab - UI General Requirement 
 * @Task#: Auto-2113
 * @author Phoebe Chen
 * @Date  March 25, 2014
 */
public class SearchCriterial extends AdminManagerTestCase{
	private AuditLogInfo  feeLogs = new AuditLogInfo();
	private AdminMgrFeeAuditLogsPage feeAuditLogsPg = AdminMgrFeeAuditLogsPage.getInstance();
	private String logArea, actionType_search, actionType_value;
	@Override
	public void execute() {
		am.loginAdminMgr(login);
		am.gotoFeeAuditLogsPage();
		
		verifyDefaultSearchCriterial();
		
		//Verify search by action details
		this.setSearchInfo("Action Details", new String[]{"Ticket","Permit","Site","Add"}[new Random().nextInt(4)], "All", "All");
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		feeAuditLogsPg.verifyColumnValue(".*"+feeLogs.searchValue+".*", "Action Details");
		
		//Verify search by location
		this.setSearchInfo("Location", am.getFacilityName("552903", schema), "All", "All");
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		feeAuditLogsPg.verifyColumnValue(feeLogs.searchValue, "Affected Location");
		
		//Verify search by User Name
		this.setSearchInfo("User Name", login.userName, "All", "All");
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		feeAuditLogsPg.verifyColumnValue(feeLogs.searchValue, "User Name");
		feeAuditLogsPg.gotoFirstPage();
		feeAuditLogsPg.verifyDateTimeOrderByDescending();
		
		//Verify search by log area
		this.setSearchInfo("Action Details", StringUtil.EMPTY, logArea, "All");
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		feeAuditLogsPg.verifyColumnValue(logArea, "Log Area");
		
		//Verify search by log area
		this.setSearchInfo("Action Details", StringUtil.EMPTY, "All", actionType_search);
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		feeAuditLogsPg.verifyColumnValue(actionType_value, "Action");
		
		am.logoutAdminMgr();
	}

	private void verifyDefaultSearchCriterial() {
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Default value for Log Area", "All", feeAuditLogsPg.getLogArea());
		passed &= MiscFunctions.compareResult("Default value for Action Type", "All", feeAuditLogsPg.getActionType());
		if(!passed){
			throw new ErrorOnPageException("Default value is not correct as expect, please check log above");
		}
		logger.info("Default value for search criterial is correct");
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		login.contract= "NC Contract";
		login.location= "Administrator/North Carolina State Parks";
		
		//Set up audit log search information
		feeLogs.stratDate = DateFunctions.getDateAfterToday(-1);
		feeLogs.endDate = DateFunctions.getToday();
		
		logArea = new String[]{
				"Discount","Discount Schedule","Fee Penalty Schedule","Fee Schedule","RA Fee Schedule","RA Fee Threshold","Reservation Permit Fee/Discount","Tax","Tax Schedule"
		}[new Random().nextInt(9)];
		
		int randomIndex = new Random().nextInt(4);
		actionType_search = new String[]{"Activate","Add","Deactivate","Update"}[randomIndex] ;
		actionType_value = new String[]{"Active.*","Add.*","Deactivate.*|Deactive.*","Update.*"}[randomIndex] ;
	}
	
	private void setSearchInfo(String searchType, String seachValue, String logArea, String actionType){
		feeLogs.searchType = searchType;
	  	feeLogs.searchValue = seachValue;
	  	feeLogs.logArea = logArea;
		feeLogs.actionType = actionType;
	}
}
