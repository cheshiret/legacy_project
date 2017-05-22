package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin.feeAuditLogs;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.SlipFee;
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
 *              1. create attribute fee schedule for Slip work flow
 *              2. go to admin manager to verify add attribute fee schedule audit log
 * @Preconditions:
 * @SPEC:Add Attr Fee Schedule - Log [TC:049742]
 * @Task#:AUTO-1419
 * 
 * @author vzhang
 * @Date  Jan 8, 2013
 */

public class AddAttrFeeSchdAuditLogs_Slip extends AdminManagerTestCase{
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
		this.verifyAddAttrFeeSchdAuditLog(feeLogs);
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
	  	schedule.feeType ="Attribute Fee";
	  	schedule.dock = "OverlapSeasonDock";
	  	schedule.productGroup = "Full attributes";
	  	prdName = "slip for fee schedule";
	  	prdCode = "PZH";
	  	schedule.product = prdCode + "-" + prdName;
		schedule.effectDate = DateFunctions.getToday(timeZone);
	  	schedule.startInv = schedule.effectDate;
	  	schedule.endInv = schedule.effectDate;
	  	schedule.season = "All";
	  	schedule.salesChannel = "All";
	  	schedule.state = "All";
	  	schedule.custType = "All";
	  	schedule.boatCategory = "All";
	  	schedule.acctCode = "3000.1601.000200000; Default Overage/Shortage";
	  	schedule.attrType = "Electricity Hookup";
	  	schedule.attrValue = "30";
	  	schedule.unitOfStay = "Nightly";
	  	schedule.marinaRateType = "Seasonal";
	  	schedule.unitOfStay = "Nightly";
	  	schedule.slipPricingUnit = "Length Range";
	  	schedule.slipFees = new ArrayList<SlipFee>();
	  	
	  	FeeScheduleData.SlipFee slipFee = schedule.new SlipFee();
	  	slipFee.perSeasonFee = "21";
	  	slipFee.isFeePerFoot = false;
	  	schedule.slipFees.add(slipFee);
	  	
	  	schedule.calculationMethod = "Daily";
	  	
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
	
	private void verifyAddAttrFeeSchdAuditLog(AuditLogInfo expAudit){
		logger.info("Verify Add attribute audit log info.");
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
