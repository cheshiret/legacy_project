package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBondInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBondsSubPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BondStoreAssignment extends SupportCase{

	private boolean loggedIn = false;// Don't login.
	private LoginInfo login = new LoginInfo();
	private String contract = "";
	private LicenseManager lm = LicenseManager.getInstance();
	private VendorBondInfo bondInfo = new VendorBondInfo();
	private String previousVendorNum = "";
	private String vendorNum = "";
	private String agentId = "";
	LicMgrVendorBondsSubPage vendorBondPg = LicMgrVendorBondsSubPage
	.getInstance();
	public void execute() {
		
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		if((!loggedIn )|| (loggedIn && !vendorBondPg.exists())){
			lm.loginLicenseManager(login);
			loggedIn = true;
		}
		if(!previousVendorNum.equalsIgnoreCase(vendorNum)) {
			lm.gotoVendorDetailsPgFromTopMenu(vendorNum);
			lm.gotoBondSubTabFromVendorDetailPg();
		}
		
		lm.changeAgentBondAssignment(agentId, bondInfo.bondNum,bondInfo.issuer);
		
		contract = login.contract;
		previousVendorNum = vendorNum;
	}

	public void getNextData() {
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		
		vendorNum = dpIter.dpString("vendorNum");
		agentId = dpIter.dpString("agentId");
		
		bondInfo.bondNum = dpIter.dpString("bondNum");
		if(bondInfo.bondNum.length()<0){
			bondInfo.bondNum = DateFunctions.getCurrentTime()+"";
		}
		bondInfo.issuer = dpIter.dpString("bondIssuer");
		
		logMsg[0]=vendorNum;
		logMsg[1]=bondInfo.bondNum;
	}

	public void wrapParameters(Object[] param) {
		cursor = 0;
		startpoint = 0 ; // the start point in the data pool
		endpoint = 0 ; // the end point in the data pool
		
		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		logMsg=new String[3];
		logMsg[0]="VendorNum";
		logMsg[1]="bondNumber";
		logMsg[2]="result";
	}
	
	public void vierifyResule(){
		if(!vendorBondPg.exists()){
			logger.error("Add privilege pricing failed:Vendor number="+vendorNum+",bond account="+bondInfo.bondNum+"error");
			logMsg[2] = "Failed";
		}else{
			logMsg[2] = "passed";
		}
	}
	
	
	

}
