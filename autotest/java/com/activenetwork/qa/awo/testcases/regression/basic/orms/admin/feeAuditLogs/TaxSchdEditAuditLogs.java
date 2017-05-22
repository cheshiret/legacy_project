package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin.feeAuditLogs;


import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrFeeAuditLogsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class TaxSchdEditAuditLogs extends AdminManagerTestCase{
	private LoginInfo loginFnm = new LoginInfo();
	private FinanceManager fnm = FinanceManager.getInstance();
	private ScheduleData schedule = new ScheduleData();
	private ScheduleData editSchdl = new ScheduleData();
	private AuditLogInfo feeLogs = new AuditLogInfo();
	private AdminMgrFeeAuditLogsPage feeAuditLogsPg = AdminMgrFeeAuditLogsPage
			.getInstance();
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoTaxMainPage();
		fnm.gotoTaxSchedulePage();
	    schedule.scheduleId = fnm.addNewTaxSchedule(schedule);
	    this.prepareTaxSchdlData();
	    editSchdl.scheduleId = schedule.scheduleId;
		fnm.editTaxSchedule(editSchdl);
		fnm.logoutFinanceManager();
		
		feeLogs.searchValue = schedule.scheduleId;
		am.loginAdminMgr(login);
		am.gotoAuditLogsPage("Fee Audit Logs");
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		this.getFeeLogsDetailInfo();
		feeAuditLogsPg.verifyFeeAuditLogs(feeLogs);
		am.logoutAdminMgr();
	}

	public void wrapParameters(Object[] param) {
		
		editSchdl.endDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-1), "MM-dd-yyyy");
		
		login.contract = "MS Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator/AutoWarehouse";
		schema = DataBaseFunctions.getSchemaName("MS", env);

		loginFnm.url = login.url;
		loginFnm.contract = login.contract;
		loginFnm.location = login.location;
		loginFnm.userName = TestProperty.getProperty("orms.fnm.user");
		loginFnm.password = TestProperty.getProperty("orms.fnm.pw");
		
		schedule.searchBy = "Tax Schedule Id";
		schedule.taxName = "TaxAuditLogTest";
		schedule.productCategory = "Site";
		schedule.feeType = "Attribute Fee";
		schedule.productGroup = "Cabin";
		schedule.product = "All";
		schedule.startDate = DateFunctions.getToday("MM-dd-yyyy");
		schedule.endDate = DateFunctions.getToday("MM-dd-yyyy");
		schedule.customerType = "All";
		schedule.accountCode = null;
		schedule.location = "AutoWarehouse"; // ID : 70034
		schedule.rate = "5.7568";
		
		feeLogs.searchType = "Action Details";
		feeLogs.stratDate = DateFunctions.getToday("MM-dd-yyyy");
		feeLogs.endDate = DateFunctions.getToday("MM-dd-yyyy");
		feeLogs.actionType = "Update";
		feeLogs.dateTime = DateFunctions.formatDate(DateFunctions.getToday(),
				"MMM dd,yyyy");
		feeLogs.logArea = "Tax Schedule";
		feeLogs.action = "Update Tax Schedule";
		feeLogs.affectedLocation = login.location.split("/")[1];
		feeLogs.userName = loginFnm.userName;
		feeLogs.application = "Finance";
		feeLogs.affectedLocation = login.location.split("/")[1];
	}
	
	private void prepareTaxSchdlData(){
	
		editSchdl.productGroup = "Group Camp";
		editSchdl.customerType = "Standard";
		editSchdl.rate = "6.742";
		editSchdl.startDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-2), "MM-dd-yyyy");
		editSchdl.endDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-1), "MM-dd-yyyy");
	}
	
	private void getFeeLogsDetailInfo(){
		feeLogs.actionDetails = "Old Tax Schedule ID: "+schedule.scheduleId+", New Tax Schedule ID: "+schedule.scheduleId+", Tax Name: "+schedule.taxName+", Product Category: "
				+schedule.productCategory+", Product Group: "+editSchdl.productGroup + ", Product: "+schedule.product+", Fee Type: "+schedule.feeType 
				+" Account Rate - Rate: "+schedule.rate + "%" +" --> "+editSchdl.rate +"%"+ " Conditions - Customer Type: "+schedule.customerType + " --> "+editSchdl.customerType+" Assignment - Product Group: "+schedule.productGroup +" --> "+editSchdl.productGroup
				+" Dates - Effective Start: "+ schedule.startDate +" --> "+editSchdl.startDate + " Dates - Effective End: "+schedule.endDate + " --> " +editSchdl.endDate; 
	}

}
