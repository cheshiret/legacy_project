package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrAddVendorBankAccountWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBankAccountsSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorDetailsPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddBankAccountsFunction extends FunctionCase{
	LoginInfo login=new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private VendorInfo vendor = new VendorInfo();
	private LicMgrVendorDetailsPage vendorDetailPg = LicMgrVendorDetailsPage.getInstance();
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String location;

	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = (LoginInfo)param[0];
		vendor = (VendorInfo)param[1];
		
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
		location = login.location;
		contract = login.contract;
		lm.gotoVendorSearchPg();
		lm.gotoVendorDetailPgFromVendorSearchPg(vendor.number);
		lm.gotoVendorBankAccountPage();
		vendor.bankAccount.accountID = lm.addVendorBankAccount(vendor.bankAccount, true);
		newAddValue = vendor.bankAccount.accountID;
		this.verifyResult(vendor.bankAccount.accountID);
	}

	public void verifyResult(String msg) {
		boolean passed =true;
		LicMgrAddVendorBankAccountWidget addBankAccountWidget = LicMgrAddVendorBankAccountWidget.getInstance();
		if(addBankAccountWidget.exists()) {
			logger.error("[FAILED]Add bank accounts failed: Vendor Number = " + vendor.number + ", Vendor Bank Account = " + vendor.bankAccount.accountNum + ", Failed reason: " + addBankAccountWidget.getErrorMsg());
			addBankAccountWidget.clickCancel();
			vendorDetailPg.waitLoading();
			passed = false;
		} else {
			LicMgrVendorBankAccountsSubPage vendorBankAccountSubPg = LicMgrVendorBankAccountsSubPage.getInstance();
			vendor.bankAccount.creationUser = DataBaseFunctions.getLoginUserName(login.userName);
			if(!vendorBankAccountSubPg.compareBanckAccountListInfo(vendor.bankAccount)||!msg.matches("[0-9]+")){
				logger.error("[FAILED]Add bank accounts failed:: Vendor Number = " + vendor.number + ", Vendor Bank Account = " + vendor.bankAccount.accountNum + ", Failed reason: information not correct, check log above!");
			    passed = false;
			}else{
				logger.info("[PASSED]Add bank accounts failed:: Vendor Number = " + vendor.number + ", Vendor Bank Account = " + vendor.bankAccount.accountNum);
				passed = true;
			}
		}
		if(!passed){
			throw new ErrorOnPageException("Add bank account failed, please see the log above!");
		}
	}
}
