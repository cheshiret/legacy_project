package com.activenetwork.qa.awo.supportscripts.support.inventorymgr;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrPosSetupListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.warehouse.InvMgrWarehouseSearchPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;



public class AssignPOSToWarehouse extends SupportCase{
	private LoginInfo login=new LoginInfo();
	private boolean loggedIn=false;
	private boolean passed = true;
	private String contract = "";
	private InventoryManager im=InventoryManager.getInstance();
	private InvMgrPosSetupListPage posSetupListPg = InvMgrPosSetupListPage.getInstance();
	private InvMgrWarehouseSearchPage whouseSearchPg = InvMgrWarehouseSearchPage.getInstance();
	private String[] posProducts;
	private String warehouseName = "", warehouseID = "";
	
	@Override
	public void execute() {
		//log into license Manger
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			im.logoutInvManager();
			loggedIn = false;
			passed = true;
		}
		if((!loggedIn )|| (loggedIn && !whouseSearchPg.exists())){
			im.loginInventoryManager(login);
			loggedIn=true;
			im.gotoWarehousesSearchPg();
			passed = true;
		}
		
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
			logMsg[3] = "Failed";
		}else{
			logMsg[3] = "Passed";
		}
		posSetupListPg.clickSelectWarehouse();
		whouseSearchPg.waitLoading();
		contract=login.contract;
	}

	@Override
	public void getNextData() {
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		
		warehouseName = dpIter.dpString("warehouseName");
		posProducts = dpIter.dpString("posProducts").split("\\|");
		
		logMsg[0] = String.valueOf(cursor);
		logMsg[1] = warehouseName;
		logMsg[2] = dpIter.dpString("posProducts");
	}

	@Override
	public void wrapParameters(Object[] param) {
		startpoint = 0; // the start point in the data pool
		endpoint = 999; // the end point in the data pool

		dataSource = casePath + "/" + caseName;
		
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.im.user");
		login.password = TestProperty.getProperty("orms.im.pw");
		
		logMsg=new String[4];
		logMsg[0] = "Index";
		logMsg[1] = "Warehouse";
		logMsg[2] = "POSProducts";
		logMsg[3] = "Result";
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
