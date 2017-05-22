package com.activenetwork.qa.awo.supportscripts.function.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrPosSetupListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.warehouse.InvMgrWarehouseSearchPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class AssignPOSToWarehouseFunction extends FunctionCase{
	private LoginInfo login=new LoginInfo();
	private boolean passed = true;
	private InventoryManager im=InventoryManager.getInstance();
	private InvMgrPosSetupListPage posSetupListPg = InvMgrPosSetupListPage.getInstance();
	private InvMgrWarehouseSearchPage whouseSearchPg = InvMgrWarehouseSearchPage.getInstance();
	private String[] posProducts;
	private String warehouseName = "", warehouseID = "",contract = "";
	private boolean loggedIn = false;
	private InvMgrHomePage invHmPg=InvMgrHomePage.getInstance();
	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedIn && isBrowserOpened) {
			im.switchToContract(login.contract, login.location);
		} 
		if (!loggedIn || !isBrowserOpened) { // Logged in
			im.loginInventoryManager(login);
			loggedIn = true;
		}
		if(!invHmPg.exists()){
			im.gotoInventoryHomePg();
		}
		contract = login.contract;
		passed = true;
		im.gotoWarehousesSearchPg();
		im.searchWarehouse("Warehouse Name", warehouseName);
		warehouseID = whouseSearchPg.getWarehouseID(warehouseName);
		im.gotoWarehouseDetailPgThroughClickWarehouseID(warehouseID);
		
		for(int i=0; i<posProducts.length; i++){
			String posName = posProducts[i];
			im.gotoPOSSetupListPage();
			this.searchPOSInfoFromPOSSetupListPage(posName);
			im.assignPOSToWarehouse(posName);	
			passed &= this.verifyResult(posName);
		}

		if(!passed){
			throw new ErrorOnPageException("Some pos product not assigned to this warehouse '" + warehouseName + "' please check log error.");
		}else{
			logger.info("pos product success assigned to this warehouse '" + warehouseName + "'");
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		
		warehouseName = param[1].toString();
		posProducts =(String[]) param[2];
	}
	
	private void searchPOSInfoFromPOSSetupListPage(String productName){
		logger.info("Search pos info from pos setup list page.");
		
		posSetupListPg.selectAssignmentStatus("");
		posSetupListPg.setProductName(productName);
		posSetupListPg.clickGo();
		ajax.waitLoading();
		posSetupListPg.waitLoading();
	}
	
	private boolean verifyResult(String productName){
		boolean result = true;
		String status = posSetupListPg.getAssignedStatus(productName);
		if(status.equalsIgnoreCase("Yes")){
			result = true;
		}else{
			result = false;
			logger.error("The pos did not assign to this warehouse: posName = '" + productName + "' warehouseName = " + warehouseName);
		}
		
		return result;
	}

}
