package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrAddVendorBankAccountWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorDetailsPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang8
 * @Date  Mar 19, 2012
 */
public class AddBankAccounts extends SupportCase{
	private boolean loggedIn = false;// Don't login.
	private LoginInfo login = new LoginInfo();
	private String contract = "";
	private String vendorNum = "";
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrVendorDetailsPage vendorDetailPg = LicMgrVendorDetailsPage.getInstance();
	private VendorInfo vendor = new VendorInfo();
	
	public void execute() {
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		if((!loggedIn )|| (loggedIn && !vendorDetailPg.exists())) {
			lm.loginLicenseManager(login);
			loggedIn = true;
		}
		if(!vendorNum.equalsIgnoreCase(vendor.number)) {
			lm.gotoVendorSearchPg();
			lm.gotoVendorDetailPgFromVendorSearchPg(vendor.number);
			lm.gotoVendorBankAccountPage();
		}
		 lm.addVendorBankAccount(vendor.bankAccount, true);
		 this.verifyResult();
		 
		 contract=login.contract;
		 vendorNum = vendor.number;
	}

	@Override
	public void wrapParameters(Object[] param) {
		cursor = 0;
		startpoint = 0; // the start point in the data pool
		endpoint = 999; // the end point in the data pool
		
		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		logMsg = new String[4];
		logMsg[0] = "Index";
		logMsg[1] = "VendorNum";
		logMsg[2] = "AccountNumber";
		logMsg[3] = "Result";
	}
	
	@Override
	public void getNextData() {
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		
		vendor.number = dpIter.dpString("VendorNum");
		vendor.bankAccount.accountType = dpIter.dpString("accountType");
		vendor.bankAccount.routingNum = dpIter.dpString("routingNum");
		vendor.bankAccount.accountNum = dpIter.dpString("accountNum");
		if(vendor.bankAccount.accountNum.length()<1){
			vendor.bankAccount.accountNum = String.valueOf(DateFunctions.getCurrentTime());
		}
		vendor.bankAccount.accountPrenoteStatus = dpIter.dpString("prenoteStatus");
		vendor.bankAccount.accountStatus = dpIter.dpString("accountStatus");
		logMsg[0] = String.valueOf(cursor);
		logMsg[1]=vendor.number;
		logMsg[2]=vendor.bankAccount.accountNum;
	}

	public void verifyResult() {
		LicMgrAddVendorBankAccountWidget addBankAccountWidget = LicMgrAddVendorBankAccountWidget.getInstance();
		if(!vendorDetailPg.exists()) {
			logger.error("Add privilege pricing failed: Vendor Number = " + vendor.number + ", Vendor Bank Account = " + vendor.bankAccount.accountNum + ", Failed reason: " + addBankAccountWidget.getErrorMsg());
			logMsg[3] = "Failed";
		} else {
			logMsg[3] = "Passed";
		}
	}
}
