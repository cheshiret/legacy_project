package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBondInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrAddVendorBondsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBondsSubPage;
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
public class AddBonds extends SupportCase{
	private boolean loggedIn = false;// Don't login.
	private LoginInfo login = new LoginInfo();
	private String contract = "";
	private String vendorNum = "";
	private LicenseManager lm = LicenseManager.getInstance();
	private VendorBondInfo bondInfo = new VendorBondInfo();
	private LicMgrVendorBondsSubPage vendorBondPg = LicMgrVendorBondsSubPage.getInstance();
	
	public void execute() {
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		if((!loggedIn )|| (loggedIn && !vendorBondPg.exists())){
			lm.loginLicenseManager(login);
			loggedIn = true;
		}
		if(!vendorNum.equalsIgnoreCase(bondInfo.belongVendorNum)) {
			lm.gotoVendorDetailsPgFromVendorsQuickSearch(bondInfo.belongVendorNum);
			lm.gotoBondSubTabFromVendorDetailPg();
		}
		
		lm.addVendorBond(bondInfo, true);
		this.verifyResult();
		
		 contract = login.contract;
		 vendorNum = bondInfo.belongVendorNum;
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
		logMsg=new String[4];
		logMsg[0] = "Index";
		logMsg[1] = "VendorNum";
		logMsg[2] = "AccountNumber";
		logMsg[3] = "result";
	}
	
	@Override
	public void getNextData() {
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		
		bondInfo.belongVendorNum =dpIter.dpString("vendorNum");
		bondInfo.issuer=dpIter.dpString("bondIssuer");
		bondInfo.type=dpIter.dpString("bondType");
		bondInfo.bondNum=dpIter.dpString("bondNum");
		bondInfo.bondAmount=dpIter.dpString("bondAmount");
		bondInfo.effectiveFrom=dpIter.dpString("effectiveFrom");
		if(bondInfo.effectiveFrom.trim().length() < 1) {
			bondInfo.effectiveFrom = DateFunctions.getDateAfterToday(-1);
		}
		bondInfo.effectiveTo = dpIter.dpString("effectiveTo");
		if(bondInfo.effectiveTo.trim().length() < 1) {
			bondInfo.effectiveTo=DateFunctions.getDateAfterToday(365);
		}
		
		logMsg[0] = String.valueOf(cursor);
		logMsg[1] = bondInfo.belongVendorNum;
		logMsg[2] = bondInfo.bondNum;
	}
	
	public void verifyResult(){
		LicMgrAddVendorBondsWidget addBondsWidget = LicMgrAddVendorBondsWidget.getInstance();
		if(!vendorBondPg.exists()){
			logger.error("Add privilege pricing failed:Vendor number="+bondInfo.belongVendorNum+",bond number="+bondInfo.bondNum+addBondsWidget.getErrorMsg());
			logMsg[3] = "Failed";
		}else{
			logMsg[3]="Passed";
		}
	}
}
