package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin.feeAuditLogs;

import com.activenetwork.qa.awo.datacollection.legacy.Tax;
import com.activenetwork.qa.awo.datacollection.legacy.orms.AuditLogInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrFeeAuditLogsPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:verify the audit logs in admine when active and deactive action.
 * @Preconditions:
 * @SPEC:Log Change to Tax [TC:033707]
 * @Task#:Auto-1301
 * 
 * @author Jasmine
 * @Date  Nov 02, 2012
 */
public class TaxActiveAndDeactiveAuditLogs extends AdminManagerTestCase{
	private LoginInfo loginFnm = new LoginInfo();
	private FinanceManager fnm = FinanceManager.getInstance();
	private Tax tax = new Tax();
	private AuditLogInfo  feeLogs = new AuditLogInfo();
	private AdminMgrFeeAuditLogsPage feeAuditLogsPg = AdminMgrFeeAuditLogsPage.getInstance();
	private FinMgrTaxMainPage taxMainPg = FinMgrTaxMainPage.getInstance();
	public void execute() {
		fnm.loginFinanceManager(loginFnm);
		fnm.gotoTaxMainPage();
		taxMainPg.searchByTaxName(tax.taxName);
		if(taxMainPg.checkTaxStatus(tax.taxName)){
			taxMainPg.deactivateTax(tax.taxName);
		}
		taxMainPg.activateTax(tax.taxName);
		fnm.logoutFinanceManager();

		am.loginAdminMgr(login);
		am.gotoAuditLogsPage("Fee Audit Logs");
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		feeAuditLogsPg.verifyFeeAuditLogs(feeLogs);
		am.logoutAdminMgr();

		fnm.loginFinanceManager(loginFnm);
		fnm.gotoTaxMainPage();
		taxMainPg.searchByTaxName(tax.taxName);
		taxMainPg.deactivateTax(tax.taxName);
		fnm.logoutFinanceManager();

		feeLogs.action = "Deactivate Tax";
		feeLogs.actionType = "Deactivate";
		am.loginAdminMgr(login);
		am.gotoAuditLogsPage("Fee Audit Logs");
		feeAuditLogsPg.searchFeeAuditLogs(feeLogs);
		feeAuditLogsPg.verifyFeeAuditLogs(feeLogs);
		am.logoutAdminMgr();
	}

	
	public void wrapParameters(Object[] param) {
		 login.contract="MS Contract";
	     login.url = AwoUtil.getOrmsURL(env);
	     login.location="Administrator/AutoWarehouse";
	     schema = DataBaseFunctions.getSchemaName("MS", env);
	     
	     loginFnm.url = login.url;
		 loginFnm.contract = login.contract;
		 loginFnm.location = login.location;
		 loginFnm.userName = TestProperty.getProperty("orms.fnm.user");
		 loginFnm.password = TestProperty.getProperty("orms.fnm.pw");
		 
		 tax.taxName ="TaxAuditLogTest";
		 tax.taxCode = "TaxAuditLogTest";
		 tax.taxDescription = "TaxAuditLogTest";
		 tax.taxRateType = "Percentage";
		 tax.feeTypes.add("Attribute Fee");
		 
		 feeLogs.searchType = "Action Details";
		 feeLogs.searchValue =  tax.taxName;
		 feeLogs.stratDate = DateFunctions.getToday();
		 feeLogs.endDate = DateFunctions.getToday();
		 feeLogs.actionType = "Activate";
		 feeLogs.dateTime = DateFunctions.formatDate(DateFunctions.getToday(), "MMM dd,yyyy");
		 feeLogs.logArea = "Tax";
		 feeLogs.action = "Activate Tax";
		 feeLogs.actionDetails = "Tax Name: "+tax.taxName+", Tax Code: "+ tax.taxCode +", "+"Tax Rate Type: "+ tax.taxRateType + ", "+"Fee Types: ["+ tax.feeTypes.get(0)+"]";
		 feeLogs.affectedLocation =  login.location.split("/")[1];
		 feeLogs.userName = loginFnm.userName;
		 feeLogs.application = "Finance";
	}

}
