package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrOutfitterAssignmentWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreDetailsPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Associate customer with a store function
 * 
 * @author Lesley Wang
 * @Date  Sep 26, 2013
 */
public class AssociateCustWithStoreFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private String storeName, custNum, contract, location;
	private boolean loggedin;
	
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
		
		lm.gotoStoreDetailPage(storeName);
		LicMgrStoreDetailsPage storeDetailsPg = LicMgrStoreDetailsPage.getInstance();
		boolean existed = storeDetailsPg.isCustNumLinkExist(custNum);
		if(existed)
			logger.info("Store "+storeName+" has been asscociated with customer "+custNum);
		else
			lm.associateCustWithStore(custNum);
		lm.associateCustWithStore(custNum);
		this.verifyResult();
		newAddValue = custNum;
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		login.contract = (String)param[0];
		login.location = (String)param[1];
		
		storeName = (String)param[2];
		custNum = (String)param[3];
	}

	private void verifyResult() {
		LicMgrOutfitterAssignmentWidget outfitterAssigWidget = LicMgrOutfitterAssignmentWidget.getInstance();
		LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage.getInstance();
		String msg = "associate customer " + custNum + " to the store " + storeName;
		
		if (outfitterAssigWidget.exists()) {
			String errMsg = outfitterAssigWidget.getErrorMsg();
			outfitterAssigWidget.clickCancel();
			throw new ErrorOnPageException("Fail to " + msg + " due to " + errMsg);
		}
		
		if (!storeDetailPg.isCustNumLinkExist(custNum)) {
			throw new ErrorOnPageException("Fail to " + msg);
		}
		logger.info("Successfully " + msg);
	}
}
