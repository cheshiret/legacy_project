package com.activenetwork.qa.awo.supportscripts.function.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierProductAssignmentListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierSearchPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class AssignPOSToSupplierFunction extends FunctionCase{
	private LoginInfo login=new LoginInfo();
	private PosSupplier supplier= new PosSupplier();;
	private InventoryManager im = InventoryManager.getInstance();
	private POSInfo posInfo = new POSInfo();
	private String warehouseName;
	private InvMgrPOSSupplierProductAssignmentListPage assignPOSToSupplierPage = InvMgrPOSSupplierProductAssignmentListPage.getInstance();
	private boolean loggedin=false;
	private String contract = "";
	private InvMgrHomePage invHmPg=InvMgrHomePage.getInstance();

	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			im.switchToContract(login.contract, login.location);
		} 
		if (!loggedin || !isBrowserOpened) { // Logged in
			im.loginInventoryManager(login);
			loggedin = true;
		}
		if(!invHmPg.exists()){
			im.gotoInventoryHomePg();
		}
		contract = login.contract;
		im.gotoWarehousesSearchPg();
		
		im.searchWarehouse("Warehouse Name", warehouseName);
    	im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
    	im.gotoPosSupplierSearchPageFromTopMenu();
		InvMgrPOSSupplierSearchPage supplierSetupPage = InvMgrPOSSupplierSearchPage.getInstance();
		supplierSetupPage.searchPosSupplierByName(supplier.name);
		supplier.id = supplierSetupPage.getSupplierIdByName(supplier.name);
		im.gotoPOSSupplierDetailsPage(supplier.id);
		im.gotoPosProductSupplierSetupPage();
		
		assignPOSToSupplierPage.searchPosByName(posInfo.product);
		assignPOSToSupplierPage.assignSelectedPOSProductByName(posInfo.product, posInfo.unitCost, posInfo.productCode);
		
		verifyResult();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		
		warehouseName = param[1].toString();
		supplier.name = param[2].toString();;
		posInfo = (POSInfo)param[3];
		
	}
	
	private void verifyResult(){
		boolean result = true;
		result = assignPOSToSupplierPage.verifyPosAssignedStatusByName(posInfo.product, "Yes");
		if(!result){
			throw new ErrorOnPageException("The pos did not assign to this supplier: posName = '" 
					+ posInfo.product + "', warehouseName = " + warehouseName + ", supplierName = " + supplier.name + "'");
		}else{
			logger.info("The pos is assigned to this supplier: posName = '" 
					+ posInfo.product + "', warehouseName = " + warehouseName + ", supplierName = " + supplier.name + "'");
		}
	}

}
