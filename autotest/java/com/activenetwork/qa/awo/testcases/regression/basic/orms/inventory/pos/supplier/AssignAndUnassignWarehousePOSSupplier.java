package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.supplier;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: Verify POS Supplier assigned to /unassigned from warehouse successfully
 * @Preconditions: an existing warehouse - AutoWarehouse
 * @SPEC: Assign Warehouse POS Supplier & Unassign Warehouse POS Supplier TC036485&TC36488
 * @Task#: AUTO-1140
 * 
 * @author qchen
 * @Date  Jul 23, 2012
 */
public class AssignAndUnassignWarehousePOSSupplier extends InventoryManagerTestCase {
	private PosSupplier supplier = new PosSupplier();
	private String warehouseName;
	private InvMgrPOSSupplierSearchPage supplierSearchPage = InvMgrPOSSupplierSearchPage.getInstance();
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoWarehousesSearchPg();
		im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
		im.gotoPosSupplierSearchPageFromTopMenu();
		
		//add a new POS supplier
		supplier.id = im.addPosSupplier(supplier);
		im.unassignPOSSupplier(supplier.id);
		
		//assign this POS Supplier to warehouse
		im.assignPOSSupplier(supplier.id);
		supplier.assigned = true;
		supplierSearchPage.verifyPosSupplierAssignValue(supplier.id, supplier.assigned);
		
		//un-assign this POS Supplier from warehouse
		im.unassignPOSSupplier(supplier.id);
		supplier.assigned = false;
		supplierSearchPage.verifyPosSupplierAssignValue(supplier.id, supplier.assigned);
		
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		warehouseName = "AutoWarehouse";
		
		supplier.name = this.caseName + DateFunctions.getCurrentTime();
		supplier.description = "Automation Regression Test";
		supplier.orderAddress.address = "ShaanXi";
		supplier.orderAddress.city = "Xian";
		supplier.orderAddress.state = "Mississippi";
		supplier.orderAddress.zip = "36159";
		supplier.orderAddress.country = "United States";
	}
}
