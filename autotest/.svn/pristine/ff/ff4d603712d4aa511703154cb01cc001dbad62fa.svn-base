package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBondInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrAddVendorBondsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorBondsSubPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddBondsFunction extends FunctionCase{
	LoginInfo login=new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private VendorBondInfo bondInfo = new VendorBondInfo();
	private LicMgrVendorBondsSubPage vendorBondPg = LicMgrVendorBondsSubPage.getInstance();
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String location;
	
	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = (LoginInfo)param[0];
		bondInfo = (VendorBondInfo)param[1];
		
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
		lm.gotoVendorDetailsPgFromVendorsQuickSearch(bondInfo.belongVendorNum);
		lm.gotoBondSubTabFromVendorDetailPg();
		String msg = lm.addVendorBond(bondInfo, true);
		this.verifyResult(msg);
		newAddValue = bondInfo.id;
	
	}

	public void verifyResult(String msg){
		boolean passed =true;
		LicMgrAddVendorBondsWidget addBondsWidget = LicMgrAddVendorBondsWidget.getInstance();
		if(addBondsWidget.exists()){
			logger.error("[FAILED]Add bond failed:Vendor number="+bondInfo.belongVendorNum+",bond number="+bondInfo.bondNum+addBondsWidget.getErrorMsg());
			addBondsWidget.clickCancel();
			vendorBondPg.waitLoading();
	     }else{
	    	 if (msg.matches("[0-9]+")) {
	 			bondInfo.id = msg;
	 			logger.info("[PASSED]Add bond successful:Vendor number="+bondInfo.belongVendorNum+",bond number="+bondInfo.bondNum);
	    	 }else{
	    		logger.error("[FAILED]Add bond failed:Vendor number="+bondInfo.belongVendorNum+",bond number="+bondInfo.bondNum);
	    	 }
	     }
		if(!passed){
			throw new ErrorOnPageException("Add bond failed, please see the log above!");
		}
	}
}
