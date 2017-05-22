package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddStoreFunction extends FunctionCase{
	LoginInfo login=new LoginInfo();
	private StoreInfo store = new StoreInfo();
	private LicenseManager lm = LicenseManager.getInstance();
//	private LicMgrVendorAgentSubPage vendorAgentsPg = LicMgrVendorAgentSubPage.getInstance();
	private boolean loggedin=false;
	private String contract = "";
	private String location;
	
	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = (LoginInfo)param[0];
		store = (StoreInfo)param[1];
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
		contract = login.contract;
		location = login.location;
		lm.gotoVendorDetailsPgFromTopMenu(store.belongedVendorNum);
		lm.gotoVendorAgentsPg();
		//add store under existed vendor.
		store.storeID = lm.addVendorAgents(store);
		this.verifyResult(store);
		newAddValue = store.storeID;
	}
	
	private void verifyResult(StoreInfo expectedStore){
//		boolean pass = true;
//		LicMgrStoreAddPage storeAddPg = LicMgrStoreAddPage.getInstance();
//		LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage.getInstance();
//		LicMgrStoreAddressesAndContactsPage addressAndContactsPg = LicMgrStoreAddressesAndContactsPage.getInstance();
//		if(!vendorAgentsPg.exists()){
//			logger.error("[FAILED]Create store information failed: Vendor Number = " + store.belongedVendorNum + ", " +
//					"Store Name = " + store.storeName + "; failed reason:" + storeAddPg.getErrorMessage());
//		}else{
//			pass = vendorAgentsPg.compareStoreListInfoStatusNotSure(expectedStore, false);
//			lm.gotoVendorAgentsDetailPg(expectedStore.storeID);
//			pass &= storeDetailPg.compareStoreDetailInfoStatusNotSure(expectedStore, false);
//			pass &= addressAndContactsPg.compareStoreDetailInfoStatusNotSure(expectedStore, false);
//			if(!pass) {
//					logger.error("[FAILED]Create store information failed: Vendor Number = " + store.belongedVendorNum + ", Store Name = " + store.storeName);
//			}else{
//				logger.info("[PASSED]Create store information passed: Vendor Number = " + store.belongedVendorNum + ", Store Name = " + store.storeName);
//			}
//		}
//		if(!pass){
//			throw new ErrorOnPageException("Store added failed, please see the log above!");
//		}

		String schema = TestProperty.getProperty(env + ".db.schema.prefix") + contract.replace("Contract", "").trim();
		String storeID=lm.getAgentID(schema, expectedStore.storeName);
		if(StringUtil.isEmpty(storeID))
			logger.error("[FAILED]Create store information failed: Vendor Number = " + store.belongedVendorNum + ", Store Name = " + store.storeName);
		
		logger.info("[PASSED]Create store information passed: Vendor Number = " + store.belongedVendorNum + ", Store Name = " + store.storeName);
		
		//Go back to vendor agent page for both failed and passed cases
		lm.gotoHomePage();
	}

}
