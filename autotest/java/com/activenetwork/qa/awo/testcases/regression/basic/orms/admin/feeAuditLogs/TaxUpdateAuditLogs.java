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
 * @Description:verify the audit logs in admin when update action.
 * @Preconditions:
 * @SPEC:Log Change to Tax [TC:033707]
 * @Task#:Auto-1301
 * 
 * @author Jasmine
 * @Date  Nov 01, 2012
 */
public class TaxUpdateAuditLogs extends AdminManagerTestCase{
	private LoginInfo loginFnm = new LoginInfo();
	private FinanceManager fnm = FinanceManager.getInstance();
	private Tax tax = new Tax();
	private Tax editTax = new Tax();
	private AuditLogInfo  feeLogs = new AuditLogInfo();
	private AdminMgrFeeAuditLogsPage feeAuditLogsPg = AdminMgrFeeAuditLogsPage.getInstance();
	private FinMgrTaxMainPage taxMainPg = FinMgrTaxMainPage.getInstance();
	public void execute() {
		fnm.loginFinanceManager(loginFnm);
		fnm.gotoTaxMainPage();
		 if(!fnm.checkTaxExistOrNotFromDB(tax.taxName, schema)){
			 fnm.addNewTax(tax);
		 }
		 taxMainPg.searchTax(tax.searchItem, tax.taxName);
		 fnm.gotoTaxDetailPage(tax.taxName);
		 this.prepareEditTaxValue();
		 fnm.editTax(editTax);
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
		 
		 tax.taxName ="LogUp"+ DateFunctions.getCurrentTime();
		 logger.info(tax.taxName);
		 tax.taxCode = "AutoText";
		 tax.taxDescription = "Auto test";
		 tax.taxRateType = "Percentage";
		 tax.feeTypes.add("Use Fee");
		 
    
		 editTax.newTaxName ="update"+DateFunctions.getCurrentTime();
		 editTax.taxCode = "Automation";
		 editTax.taxDescription = "Automation";
		 editTax.taxRateType = "Percentage";
		 editTax.feeTypes.add("Attribute Fee");
		 
		 feeLogs.searchType = "Action Details";
		 feeLogs.searchValue =  editTax.newTaxName;
		 feeLogs.stratDate = DateFunctions.getToday();
		 feeLogs.endDate = DateFunctions.getToday();
		 feeLogs.actionType = "Update";
		 feeLogs.dateTime = DateFunctions.formatDate(DateFunctions.getToday(), "MMM dd,yyyy");
		 feeLogs.logArea = "Tax";
		 feeLogs.action = "Update Tax";
		 feeLogs.actionDetails = "Tax Name: "+editTax.newTaxName+", Tax Code: "+ editTax.taxCode +", "+"Tax Rate Type: "+ editTax.taxRateType + ", "+"Fee Types: ["+ editTax.feeTypes.get(0)+"] "
				 + "Tax Description: "+tax.taxDescription+" --> "+editTax.taxDescription 
				 +" Tax Name: "+tax.taxName+" --> " + editTax.newTaxName 
				 +" Fee Types: [" + tax.feeTypes.get(0) +"] --> ["+editTax.feeTypes.get(0)+"]"
				 + " Tax Code: "+tax.taxCode + " --> "+editTax.taxCode;
		 feeLogs.affectedLocation =  login.location.split("/")[1];
		 feeLogs.userName = loginFnm.userName;
		 feeLogs.application = "Finance";
	}
	
	private void prepareEditTaxValue(){
		tax.newTaxName = "update"+DateFunctions.getCurrentTime();
		tax.taxCode = tax.newTaxName;
		tax.taxDescription = "Automation Test";
		tax.feeTypes.clear();
		tax.feeTypes.add("Attribute Fee");
	}

}
