package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.supplier;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: verify the searching POS Supplier function works correctly within Warehouse in IM
 * @Preconditions: need an existing warehouse - AutoWarehouse
 * @SPEC: Search Warehouse POS Supplier TC036486
 * @Task#: AUTO-1140
 * 
 * @author qchen
 * @Date  Jul 24, 2012
 */
public class SearchWithinWarehouse extends InventoryManagerTestCase {
	private PosSupplier supplier = new PosSupplier();
	private String warehouseName;
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoWarehousesSearchPg();
		im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
		im.gotoPosSupplierSearchPageFromTopMenu();
		
		//add POS Supplier
		supplier.id = im.addPosSupplier(supplier);
		//verify searching function
		supplier.assigned = true;
		this.verifyPosSupplierSearchResult(true);
		
		//un-assign POS Supplier
		im.unassignPOSSupplier(supplier.id);
		//verify searching function
		supplier.assigned = false;
		this.verifyPosSupplierSearchResult(false);
		
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		warehouseName = "AutoWarehouse";
		
		supplier.location = "All Agencies";
		supplier.name = this.caseName + DateFunctions.getCurrentTime();
		supplier.description = "Automation Regression Test";
		supplier.orderAddress.address = "Northwest A Street";
		supplier.orderAddress.city = "Xian";
		supplier.orderAddress.state = "Mississippi";
		supplier.orderAddress.zip = "36159";
		supplier.orderAddress.country = "United States";
		supplier.isPaymentAddrSameOrderAddr = true;
	}
	
	/**
	 * verify the searching POS Supplier function works correctly
	 */
	private void verifyPosSupplierSearchResult(boolean isAssigned) {
		InvMgrPOSSupplierSearchPage posSupplierSearchPage = InvMgrPOSSupplierSearchPage.getInstance();
		
		logger.info("Verify searching POS Supplier function works correctly.");
		posSupplierSearchPage.searchPosSupplier(supplier);
		posSupplierSearchPage.verifyPosSupplierSearchResult(supplier);
		
		PosSupplier searchSupplier = new PosSupplier();
		posSupplierSearchPage.clearUpSearchCriteria();
		searchSupplier.id = supplier.id;
		posSupplierSearchPage.searchPosSupplier(searchSupplier);
		posSupplierSearchPage.verifyPosSupplierSearchResult(searchSupplier);
		
		posSupplierSearchPage.clearUpSearchCriteria();
		searchSupplier.id = "";
		searchSupplier.name = supplier.name;
		posSupplierSearchPage.searchPosSupplier(searchSupplier);
		posSupplierSearchPage.verifyPosSupplierSearchResult(searchSupplier);
		
		posSupplierSearchPage.clearUpSearchCriteria();
		searchSupplier.id = "";
		searchSupplier.searchByStatus = isAssigned ? "Assigned" : "Unassigned";
		posSupplierSearchPage.searchPosSupplier(searchSupplier);
		posSupplierSearchPage.verifyPosSupplierSearchResult(searchSupplier);
	}
}
