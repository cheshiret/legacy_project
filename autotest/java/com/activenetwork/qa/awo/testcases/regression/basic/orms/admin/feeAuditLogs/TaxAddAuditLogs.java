package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin.feeAuditLogs;

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
 * @Description:Verify the audit logs in admin when add action.
 * @Preconditions:
 * @SPEC:Log Change to Tax [TC:033707]
 * @Task#:Auto-1301
 * 
 * @author Jasmine
 * @Date  Nov 01, 2012
 */
public class TaxAddAuditLogs extends AdminManagerTestCase{
  private LoginInfo loginFnm = new LoginInfo();
  private FinanceManager fnm = FinanceManager.getInstance();
  private Tax tax = new Tax();
  private AuditLogInfo  feeLogs = new AuditLogInfo();
  private AdminMgrFeeAuditLogsPage feeAuditLogsPg = AdminMgrFeeAuditLogsPage.getInstance();
	
	public void execute() {
		fnm.loginFinanceManager(loginFnm);
		fnm.gotoTaxMainPage();
	    fnm.addNewTax(tax);
	    fnm.logoutFinanceManager();
	    
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
		 
		 tax.taxName ="Log"+ DateFunctions.getCurrentTime();
		 logger.info(tax.taxName);
		 tax.taxCode = "AutoText";
		 tax.taxDescription = "Auto test";
		 tax.taxRateType = "Percentage";
		 tax.feeTypes.add("Use Fee");
		 
		 feeLogs.searchType = "Action Details";
		 feeLogs.searchValue =  tax.taxName;
		 feeLogs.stratDate = DateFunctions.getToday();
		 feeLogs.endDate = DateFunctions.getToday();
		 feeLogs.actionType = "Add";
		 feeLogs.dateTime = DateFunctions.formatDate(DateFunctions.getToday(), "MMM dd,yyyy");
		 feeLogs.logArea = "Tax";
		 feeLogs.action = "Add Tax";
		 feeLogs.actionDetails = "Tax Name: "+tax.taxName+", Tax Code: "+ tax.taxCode +", "+"Tax Rate Type: "+ tax.taxRateType + ", "+"Fee Types: ["+ tax.feeTypes.get(0)+"]";
		 feeLogs.affectedLocation =  login.location.split("/")[1];
		 feeLogs.userName = loginFnm.userName;
		 feeLogs.application = "Finance";
	}

}
