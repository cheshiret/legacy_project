package com.activenetwork.qa.awo.supportscripts.function.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuotaTypeInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Nov 22, 2012
 */
public class AddQuotaTypeFunction extends FunctionCase {
	private String facilityName;
	private QuotaTypeInfo quotaTypeInfo;
	private InventoryManager im = InventoryManager.getInstance();
	private LoginInfo loginInfo = new LoginInfo();
	private boolean loggedin=false;
	private String contract = "";
	private InvMgrHomePage invHmPg=InvMgrHomePage.getInstance();
	@Override
	public void execute() {
		if (!loginInfo.contract.equals(contract) && loggedin && isBrowserOpened) {
			im.switchToContract(loginInfo.contract, loginInfo.location);
		} 
		if (!loggedin || !isBrowserOpened) { // Logged in
			im.loginInventoryManager(loginInfo);
			loggedin = true;
		}
		if(!invHmPg.exists()){
			im.gotoInventoryHomePg();
		}

		contract = loginInfo.contract;
		im.gotoFacilityDetailsPg(facilityName);
		im.gotoEntranceListPage("Entrance Set-up");
		im.gotoQuotaTypeListPage();

		if (!im.verifyQuotaTypeExist(quotaTypeInfo.quotaTypeCode)) {
			// add quota type on Quota Type Detail Page
            im.gotoAddNewQuotaTypePageFromQuotaTypeListPage();
			im.addNewQuotaType(quotaTypeInfo);

			if(!im.verifyQuotaTypeExist(quotaTypeInfo.quotaTypeCode)){
				throw new ErrorOnPageException("Add quota type " + quotaTypeInfo.quotaTypeCode + " failed.");
			} else {
				newAddValue = quotaTypeInfo.quotaTypeCode;
			}

		} else {
			logger.info("Success -- Same quotaType already exists");
		}
	
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		loginInfo = (LoginInfo)param[0];
		facilityName = (String)param[1];
		quotaTypeInfo = (QuotaTypeInfo)param[2]; 
	}
}
