package com.activenetwork.qa.awo.supportscripts.function.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory.InvMgrPOSInventoryCallCenterAllocationPage;
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
 * @Date  May 20, 2014
 */
public class AllocateCallCenterPOSInventoryFunction extends FunctionCase {

	private LoginInfo login = new LoginInfo();
	private InventoryManager im = InventoryManager.getInstance();
	private POSInfo pos = new POSInfo();
	private String wareHouseName;
	private boolean loggedin=false;
	private String contract = "";
	private InvMgrHomePage invHmPg=InvMgrHomePage.getInstance();

	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			im.switchToContract(login.contract, login.location);
		} 
		if (!loggedin || !isBrowserOpened) {
			im.loginInventoryManager(login);
			loggedin = true;
		}
		if(!invHmPg.exists()){
			im.gotoInventoryHomePg();
		}
		contract = login.contract;
    	
		im.gotoWarehousesSearchPg();
		im.gotoWarehouseDetailPgThroughWarehouseName(wareHouseName);
		im.gotoPOSInventoryManagementPageFromTopMenuPage();
		im.gotoPOSInventoryCallCenterAllocationPage();
		im.allocatePOSInventoryToCallCenter(pos);
		
		if(InvMgrPOSInventoryCallCenterAllocationPage.getInstance().getAllocationStatus(pos.product).equalsIgnoreCase("Allocated")) {
			newAddValue = pos.productID;
		} else throw new ErrorOnPageException("Allocate POS product - " + pos.product + " to Call Center failed.");
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		wareHouseName = (String)param[1];
		pos = (POSInfo)param[2];
	}
}
