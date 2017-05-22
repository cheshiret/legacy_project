package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBankAccountInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBankAccountsSubPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BankAccountStoreAssignment extends SupportCase{

	private boolean loggedIn = false;// Don't login.
	private LoginInfo login = new LoginInfo();
	private String contract = "";
	private String vendorNum = "";
	private LicenseManager lm = LicenseManager.getInstance();
	private StoreInfo storeInfo = new StoreInfo();
	private VendorBankAccountInfo bankAccount = new VendorBankAccountInfo();
	LicMgrVendorBankAccountsSubPage vendorBankAccountPg = LicMgrVendorBankAccountsSubPage.getInstance();
	
	public void execute() {
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		if((!loggedIn )|| (loggedIn && !vendorBankAccountPg.exists())){
			lm.loginLicenseManager(login);
			loggedIn = true;
		}
		if(!vendorNum.equalsIgnoreCase(storeInfo.belongedVendorNum)) {
			lm.gotoVendorDetailsPgFromVendorsQuickSearch(storeInfo.belongedVendorNum);
			lm.gotoVendorBankAccountPage();
		}
		
		lm.assignVendorBankAccountToStore(storeInfo.storeName, bankAccount.accountRegx, bankAccount.effectiveDate, true);
		
		contract = login.contract;
		vendorNum = storeInfo.belongedVendorNum;
	}

	public void getNextData() {
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		storeInfo.belongedVendorNum = dpIter.dpString("vendorNum");
		storeInfo.storeName = dpIter.dpString("storeName");
		
		bankAccount.accountNum = dpIter.dpString("bankAccountNum");
		if(bankAccount.accountNum.length()<1){
			bankAccount.accountNum = "c" + new Random().nextInt(1000) + DateFunctions.getLongTimeStamp();	
		}
		bankAccount.accountType = dpIter.dpString("bankAccountType");
		bankAccount.routingNum = dpIter.dpString("routingNum");
		String bankAccountRegx = dpIter.dpString("bankAccountRegx");
		if(bankAccountRegx.length()<1){
			bankAccount.accountRegx = bankAccount.accountType + " Routing # " + bankAccount.routingNum + " Acct # " + bankAccount.accountNum.substring(0, 4);;
		}else{
			bankAccount.accountRegx =  bankAccount.accountType + " Routing # " + bankAccount.routingNum + " Acct # " + bankAccountRegx+ "";
		}
		bankAccount.effectiveDate = dpIter.dpString("effectiveDate");
		if(bankAccount.effectiveDate.length()<1){
			bankAccount.effectiveDate = DateFunctions.getDateAfterToday(1);
		}
		
		logMsg[0] = String.valueOf(cursor);
		logMsg[1] = storeInfo.belongedVendorID;
		logMsg[2] = bankAccount.accountRegx;
	}

	public void wrapParameters(Object[] param) {
		cursor = 0;
		startpoint = 1; // the start point in the data pool
		endpoint = 1; // the end point in the data pool
		
		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		logMsg=new String[4];
		logMsg[0] = "Index";
		logMsg[1] = "VendorNum";
		logMsg[2] = "AccountNumber";
		logMsg[3] = "Result";
	}
	
	public void verifyResult(){
		if(!vendorBankAccountPg.exists()){
			logger.error("Add privilege pricing failed:Vendor number="+storeInfo.belongedVendorID+",vendor bank account="+bankAccount.accountRegx+" error");
			logMsg[3] = "Failed";
		}else{
			logMsg[3] = "Passed";
		}
	}
}
