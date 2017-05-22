package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.purchaseorder;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierSearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.warehouse.InvMgrWarehouseDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:
 * 1.Verify the Master POS Setup option shall be displayed when user\ufffds Location is Contract or Agency.
 * 2.Verify the System shall display corresponding POS Supplier Details page when Select POS Supplier from the POS Supplier List.
 * 3.Verify the warehouse details tab/page page shall be displayed when Select a Warehouse from the warehouse list.
 * @Preconditions:Log into the Inventory Manager where User\ufffds Location is Contract or Agency.
 * @SPEC:step 2~4 of TC036511
 * @Task#:Auto-1110
 * 
 * @author nding1
 * @Date  Jun 27, 2012
 */
public class DisplayMasterPOSSetup extends InventoryManagerTestCase {
	private boolean result = true;
	private String warehouseName, supplierName;
	
    public void execute(){
    	im.loginInventoryManager(login);
    	
    	// verify whether Master POS Setup in the list or not
    	this.verifyDisplayMasterPOSSetup();
    	this.verifySupplier(supplierName);
    	this.verifyWarehouse(warehouseName);
    	if(!result){
    		throw new ErrorOnPageException("---Not all of the check points are passed.");
    	}
    	im.logoutInvManager();
    }

    public void wrapParameters(Object[] args) {
    	login.url = AwoUtil.getOrmsURL(env);
 	    login.contract = "MS Contract";
 	    login.location="Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
 	    
 	    warehouseName = "AutoWarehouse";
 	    supplierName = "ForPOSManagement";
    }
    
    /**
     * Verify the Master POS Setup option shall be displayed when user\ufffds Location is Contract or Agency.
     */
    private void verifyDisplayMasterPOSSetup(){
    	// get feature drop down list
		InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();
		List<String> featureList = topMenu.getTopDropDownListElements();
    	
		// verify whether Master POS Setup in the list or not
		logger.info("Verify whether Master POS Setup in the list or not");
		if(!featureList.contains("Master POS Setup")){
			result &= false;
			logger.error("---The Master POS Setup should be displayed in the drop down list.");
		}
    }
    
    /**
     * Verify the System shall display corresponding POS Supplier Details page when Select POS Supplier from the POS Supplier List.
     */
    private void verifySupplier(String name) {
    	im.gotoPosSupplierSearchPageFromTopMenu();
		InvMgrPOSSupplierSearchPage supplierSearchPg = InvMgrPOSSupplierSearchPage.getInstance();
		InvMgrPOSSupplierDetailsPage supplierDetailPg = InvMgrPOSSupplierDetailsPage.getInstance();
		
		supplierSearchPg.searchPosSupplierByName(name);
		List<String> supplierIDList = supplierSearchPg.getSupplierID();
		if(supplierIDList.size()>0){
			supplierSearchPg.clickSupplierId(supplierIDList.get(0));
			ajax.waitLoading();
			supplierDetailPg.waitLoading();
			if(!supplierDetailPg.exists()){
				result &= false;
				logger.error("---It should go to supplier details page.");
			}
		} else {
			throw new ErrorOnPageException("Can't find any supplier.");
		}
    }
    
    /**
     * Verify the warehouse details tab/page page shall be displayed when Select a Warehouse from the warehouse list.
     */
    private void verifyWarehouse(String name) {
    	im.gotoWarehousesSearchPg();
    	im.gotoWarehouseDetailPgThroughWarehouseName(name);
		InvMgrWarehouseDetailsPage whouseDetailsPg = InvMgrWarehouseDetailsPage.getInstance();
    	if(!whouseDetailsPg.exists()){
			result &= false;
			logger.error("---It should go to warehouse details page.");
    	}
    }
}
