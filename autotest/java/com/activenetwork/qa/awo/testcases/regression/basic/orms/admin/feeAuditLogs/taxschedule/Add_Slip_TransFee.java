package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin.feeAuditLogs.taxschedule;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.Tax;
import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrFeeAuditLogsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: It is to check the audit log of adding a tax schedule for slip product,transaction fee type:
 *               1.Add the tax schedule in finance manager.
 *               2.Go to admin manager to verify the new added tax schedule logs
 * @Preconditions:
 * 1. A tax has been setup:audit_log_slip_trans_fee
 * @SPEC: TC:049792
 * @Task#: Auto-1435
 * @author Phoebe Chen
 * @Date  Jan 09, 2013
 */
public class Add_Slip_TransFee extends AdminManagerTestCase{
	private LoginInfo loginFnm = new LoginInfo();
	private FinanceManager fnm = FinanceManager.getInstance();
	private Tax tax = new Tax();
	private ScheduleData schedule = new ScheduleData();
	private AuditLogInfo feeLogs = new AuditLogInfo();
	private AdminMgrFeeAuditLogsPage feeAuditLogsPg = AdminMgrFeeAuditLogsPage
			.getInstance();
	@Override
	public void execute() {
		//Go to finance manager to add a new schedule
		fnm.loginFinanceManager(login);
		fnm.gotoTaxMainPage();
		  //Check data prepare and clean the environment
	    if(!fnm.checkTaxExistOrNotFromDB(tax.taxName, schema)){
			 fnm.addNewTax(tax);
		 }
	    //Add the new schedule
	    schedule.scheduleId = fnm.addNewTaxSchedule(schedule);
		fnm.logoutFinanceManager();
		
		am.loginAdminMgr(login);
		am.gotoAuditLogsPage("Fee Audit Logs");
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		feeLogs.actionDetails = "Old Tax Schedule ID: None, New Tax Schedule ID: "+schedule.scheduleId+", Tax Name: "+schedule.taxName+", Product Category: "
				+schedule.productCategory+", Product Group: "+schedule.productGroup + ", Product: "+schedule.product+", Fee Type: "+schedule.feeType;
		feeAuditLogsPg.verifyFeeAuditLogs(feeLogs);
		am.logoutAdminMgr();
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initialize login admin manager loginInfo
	  	login.url = AwoUtil.getOrmsURL(env);
	  	login.contract = "NC Contract";
	  	login.location = "Administrator - Auto/North Carolina State Parks";
	  	
		loginFnm.url = login.url;
		loginFnm.contract = login.contract;
		loginFnm.location = login.location;
		loginFnm.userName = TestProperty.getProperty("orms.fnm.user");
		loginFnm.password = TestProperty.getProperty("orms.fnm.pw");
		 
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
	  	//initialize Tax info
	  	tax.taxName = "audit_log_slip_trans_fee";
	  	tax.taxCode = "AuditLogSlipTrans";
	  	tax.taxDescription = "add tax schedule slip transaction fee for audit log";
	  	tax.taxRateType ="Percentage";
	  	tax.feeTypes.add("Transaction Fee");
	  	tax.feeTypes.add("Entrance-DayUse Fee");
	  	tax.feeTypes.add("POS Fee");
	  	
	  	schedule.location = fnm.getFacilityName("552861", schema);//"South Mountains State Park";
	 	schedule.locationCategory = "Park";
	 	
	 	schedule.taxName = tax.taxName;
	 	schedule.productCategory = "Slip"; //This is the test point, can not be changed
	 	schedule.productGroup = "Full attributes";
	 	schedule.product = "All";
	  	schedule.feeType = "Transaction Fee"; //This is the test point, can not be changed
	  	schedule.startDate = "2011-9-1";
	  	schedule.endDate = "2012-10-31";
	  	schedule.rateType = "Percentage";
	  	schedule.tranType = "Change Boat";
	  	schedule.marinaRateType = "Transient";
	  	schedule.customerType = "Standard";
	  	schedule.accountCode = "2000.1601.000300000; Default Overpayment Deposit";
	  	schedule.rate = "50";
	  	
	  	//initialize fee log info
	  	feeLogs.searchType = "Action Details";
		feeLogs.searchValue = schedule.taxName;
		feeLogs.stratDate = DateFunctions.getToday(timeZone);
		feeLogs.endDate = DateFunctions.getToday(timeZone);
		feeLogs.actionType = "Add";
		feeLogs.dateTime = DateFunctions.getToday(timeZone);
		feeLogs.logArea = "Tax Schedule";
		feeLogs.action = "Add Tax Schedule";
		feeLogs.affectedLocation = schedule.location;
		feeLogs.userName = loginFnm.userName;
		feeLogs.application = "Finance";
	}

}
