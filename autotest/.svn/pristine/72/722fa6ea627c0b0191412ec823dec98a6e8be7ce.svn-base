package com.activenetwork.qa.awo.supportscripts.function.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInventoryAdjust;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
/**
 * 
 * @Description: 
 * @Preconditions:
 * @SPEC:
 * @Task#: 
 * 
 * @author szhou
 * @Date  Apr 25 2014
 */
public class AdjustPOSInventoryFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private InventoryManager im = InventoryManager.getInstance();
	private InvMgrHomePage homePage = InvMgrHomePage.getInstance();
	private POSInfo pos = new POSInfo();
	private POSInventoryAdjust inventory = new POSInventoryAdjust();
	private String wareHouseName;
	private boolean loggedin = false;
	private String previousContract;

	@Override
	public void execute() {
		if (!login.contract.equals(previousContract) && loggedin
				&& isBrowserOpened) {
			im.switchToContract(login.contract, login.location);
		}
		if (!loggedin || !isBrowserOpened) { // Logged in
			im.loginInventoryManager(login);
			loggedin = true;
		}
		if (!homePage.exists()) {
			im.gotoInventoryHomePg();
		}
		previousContract = login.contract;

		im.gotoWarehousesSearchPg();
		im.gotoWarehouseDetailPgThroughWarehouseName(wareHouseName);

		// adjust inventory
		im.gotoPOSInventoryManagementPageFromTopMenuPage();
		im.makePOSInventoryAdjust(pos, inventory);

		newAddValue = pos.product;
	}

	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		wareHouseName = (String)param[1];
		pos = (POSInfo)param[2];
		inventory=(POSInventoryAdjust)param[3];

	}

}
