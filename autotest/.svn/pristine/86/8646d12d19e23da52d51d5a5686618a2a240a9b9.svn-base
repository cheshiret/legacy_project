package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBankAccountInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrChangeVendorBankAccountStoreAssignmentsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBankAccountStoreAssignmentsDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBankAccountsSubPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BankAccountStoreAssignmentFunction extends FunctionCase{
	LoginInfo login=new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private StoreInfo storeInfo = new StoreInfo();
	private VendorBankAccountInfo bankAccount = new VendorBankAccountInfo();
	LicMgrVendorBankAccountsSubPage vendorBankAccountPg = LicMgrVendorBankAccountsSubPage.getInstance();
	private String assignId = "";
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String location;
	

	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = (LoginInfo)param[0];
		storeInfo = (StoreInfo)param[1];
		bankAccount = (VendorBankAccountInfo)param[2];
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
	}

	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			lm.logOutLicenseManager();
			loggedin= false;
		}
		if(login.contract.equals(contract) && loggedin && isBrowserOpened){
			if(!login.location.equals(location)){
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
			
		}
		if (!loggedin || !isBrowserOpened) { 
			lm.loginLicenseManager(login);
			loggedin= true;
		}
		if (!homePg.exists()) {
			lm.gotoHomePage();
		}
		contract = login.contract;
		location = login.location;
		lm.gotoVendorDetailsPgFromVendorsQuickSearch(storeInfo.belongedVendorNum);
		lm.gotoVendorBankAccountPage();
		lm.assignVendorBankAccountToStore(storeInfo.storeName, bankAccount.accountRegx, bankAccount.effectiveDate, true);
		this.verifyResult();
		newAddValue = assignId;
	
	}

	public void verifyResult(){
		boolean passed = true;
		LicMgrChangeVendorBankAccountStoreAssignmentsWidget changeVendorBankAccountWidget = LicMgrChangeVendorBankAccountStoreAssignmentsWidget
				.getInstance();
		LicMgrVendorBankAccountStoreAssignmentsDetailsWidget viewPg = LicMgrVendorBankAccountStoreAssignmentsDetailsWidget
				.getInstance();
		LicMgrVendorBankAccountsSubPage vendorBankAccountPg = LicMgrVendorBankAccountsSubPage
				.getInstance();
		if(changeVendorBankAccountWidget.exists()){
			passed = false;
			logger.error("[FAILED]Assign bank account to store failed:Store name="+storeInfo.storeName+",bank account="+bankAccount.accountRegx+" error");
		}else{
			lm.gotoVendorBankAccountStoreAssignmentList();
			assignId = viewPg.getAssignIdByAgentName(storeInfo.storeName);
			if(StringUtil.notEmpty(assignId)){
				logger.info("[PASSED]Assign bank account to store failed:Store name="+storeInfo.storeName+",bank account="+bankAccount.accountRegx);
			}else{
				passed = false;
				logger.error("[FAILED]Assign bank account to store failed:Store name="+storeInfo.storeName+",bank account="+bankAccount.accountRegx+" error");
			}
		}
		if(!passed){
			throw new ErrorOnPageException("Assign bank account to store failed, please see the log above!");
		}
		viewPg.clickOK();
		ajax.waitLoading();
		vendorBankAccountPg.waitLoading();
	}
}
