package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin.feeAuditLogs;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.SlipFee;
import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrFeeAuditLogsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify Edit Attribute Fee audit log details info when editing attribute fee's Fee section
 * @Preconditions: need an existing Slip - 'EFS-Edit Fee Schedule' which has been inserted into corresponding support table
 * @SPEC: Edit Attr Fee Schedule - Log [TC:049738]
 * @Task#: AUTO-1425
 * 
 * @author qchen
 * @Date  Jan 22, 2013
 */
public class EditAttrFeeSchdAuditLogs_Slip_Fee extends AdminManagerTestCase {
	private FinanceManager fnm = FinanceManager.getInstance();
	private LoginInfo loginFnm = new LoginInfo();
	private FeeScheduleData schedule = new FeeScheduleData();
	private AuditLogInfo logs = new AuditLogInfo();
	private SlipFee originalFee = schedule.new SlipFee();
	private SlipFee editFee = schedule.new SlipFee();
	private AdminMgrFeeAuditLogsPage logsPage = AdminMgrFeeAuditLogsPage.getInstance();
	
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(loginFnm);
		fnm.gotoFeeMainPage();
		//add a new marina attribute fee schedule
		schedule.feeSchdId = fnm.addNewFeeSchedule(schedule);
		
		//edit the new added attribute fee schedule's Fee section
		this.initializeEditingFeeScheduleInfo();
		fnm.gotoFeeSchedulePageByScheduleId(schedule.feeSchdId);
		fnm.editFeeSchedule(schedule);
		fnm.logoutFinanceManager();
		
		am.loginAdminManager(login, false);
		am.gotoFeeAuditLogsPage();
		logs.searchValue = schedule.feeSchdId;
		logs.actionDetails = "Old Fee Schedule ID: " + schedule.feeSchdId + ", New Fee Schedule ID: " + schedule.feeSchdId
									+ ", Product Category: " + schedule.productCategory + ", Product Group: " + schedule.productGroup + ", Product: " + schedule.product.split("-")[1].trim()
									+ ", Fee Type: " + schedule.feeType
									+ " Fee - Range(>=) - (Period, Fee Per Foot, Full Stay Req'd for Multi-unit Rate) - "
									+ "Custom Duration(Rate) , Event/Holiday Name(Daily/Nightly , Inventory Start - Inventory End): 1 - (Daily/Nightly:" + originalFee.dailyNightlyFee + ", Weekly:" + originalFee.weeklyFee + ", Monthly:" + originalFee.monthlyFee +", Fee Per Foot:" + originalFee.isFeePerFoot + ", Full Stay Req'd for Multi-unit Rate:" + originalFee.isfullStayForMultiUnitRate +");"
									+ " --> 1 - (Daily/Nightly:" + editFee.dailyNightlyFee + ", Weekly:" + editFee.weeklyFee + ", Monthly:" + editFee.monthlyFee + ", Fee Per Foot:" + editFee.isFeePerFoot + ", Full Stay Req'd for Multi-unit Rate:" + editFee.isfullStayForMultiUnitRate + ");";
		logsPage.searchFeeAuditLogs(logs);
		logsPage.verifyFeeAuditLogs(logs);
		am.logoutAdminMgr();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		loginFnm.url = login.url;
		loginFnm.contract = login.contract;
		loginFnm.location = login.location;
		loginFnm.userName = login.userName;
		loginFnm.password = login.password;
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		String facilityID = "552903";
		String facility = fnm.getFacilityName(facilityID, schema);
		
		schedule.location = facility; 
		schedule.locationCategory = "Park";
		schedule.productCategory = "Slip";
		schedule.feeType = "Attribute Fee";
		
		schedule.activeStatus = OrmsConstants.NO_STATUS;
		schedule.dock = "All";
		schedule.productGroup = "Full attributes";
		schedule.product = "EFS-Edit Fee Schedule";
		schedule.effectDate = DateFunctions.getDateAfterToday(5);
		schedule.startInv = schedule.effectDate;
		schedule.endInv = schedule.effectDate;
		schedule.season = "All";
		schedule.salesChannel = "All";
		schedule.state = "All";
		schedule.custType = "All";
		schedule.boatCategory = "All";
		schedule.acctCode = "3000.1601.000200000; Default Overage/Shortage";
		schedule.attrType = "Electricity Hookup";
		schedule.attrValue = "15";
		schedule.marinaRateType = OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT;
		schedule.unitOfStay = "Nightly";
		schedule.rafting = "All";
		schedule.slipPricingUnit = "Length Range";
		
		originalFee.dailyNightlyFee = "10.01";
		originalFee.weeklyFee = "100.01";
		originalFee.monthlyFee = "1000.01";
		originalFee.isFeePerFoot = false;
		originalFee.isfullStayForMultiUnitRate = false;
		schedule.slipFees.add(originalFee);
		
		logs.searchType = "Action Details";
		logs.logArea = "Fee Schedule";
		logs.actionType = "Update";
		logs.dateTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		logs.action = logs.actionType + " " + logs.logArea;
		logs.affectedLocation = facility;
		logs.userName = loginFnm.userName;
		logs.application = "Finance";
		logs.stratDate = DateFunctions.getDateAfterToday(-2);
		logs.endDate = DateFunctions.getToday();
	}
	
	private void initializeEditingFeeScheduleInfo() {
		schedule.slipFees.clear();
		editFee.dailyNightlyFee = "11.11";
		editFee.weeklyFee = "111.11";
		editFee.monthlyFee = "1111.11";
		editFee.isFeePerFoot = true;
		editFee.isfullStayForMultiUnitRate = true;
		schedule.slipFees.add(editFee);
	}
}
