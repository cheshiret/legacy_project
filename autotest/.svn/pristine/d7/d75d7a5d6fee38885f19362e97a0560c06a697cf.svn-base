/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.product;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrPosSetupListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: it is to check a workflow of assigning and unassigning a POS product to a warehouse: 
 * 1. Assign the POS to a warehouse and check the assignment info
 * 2. Unassign the POS and check the assignment info
 * 
 * @Preconditions:
 * 1. the role Administrator has the assigned features: 
 * SearchWarehouse, EditWarehouse, SearchPOSProductSetup, AssignPOSProductSetup, UnAssignPOSProductSetup
 * 2. the POS 'AssignAndUnassignToWarehouse_NoInv' has been added by support script.
 *
 * @SPEC: 
 * Assign Warehouse POS Product [TC:032106] Step1, 3~11
 * Unassign Warehouse POS Product [TC:032107] Step1, 3
 * @Task#: Auto-1210
 * 
 * @author Lesley Wang
 * @Date  Aug 24, 2012
 */
public class CancelToUnassignFromWarehouse extends InventoryManagerTestCase {
	private String warehouseName;
	private InvMgrPosSetupListPage posSetUpPg = InvMgrPosSetupListPage.getInstance();
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoWarehousesSearchPg();
		im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
		im.gotoPosProductSetupPage();
		
		// Clean up at first
		posSetUpPg.searchPOSProduct(pos);
		im.unassignPOSFromWarehouse(pos.product);	
		
		// Verify the unassigned POS info
//		posSetUpPg.searchPOSProduct(pos);
		pos.assignStatus = NO_STATUS;
		posSetUpPg.verifyPOSInfo(pos);
		posSetUpPg.verifyProductIDLinkExist(pos.productID, false);
		
		// Assign and Cancel to Unassign the POS product 
		im.assignPOSToWarehouse(pos.product);
		im.unassignPOSFromWarehouse(false, pos.product);
		pos.assignStatus = YES_STATUS; 
		posSetUpPg.verifyPOSInfo(pos);
		posSetUpPg.verifyProductIDLinkExist(pos.productID, true);	
		
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/" + TestProperty.getProperty("ms.admin.location");
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		warehouseName = "AutoWarehouse";	
		
		// POS info
		pos.product = "AssignAndUnassignToWarehouse_NoInv";
		pos.productID = im.getProductID("Product Name", pos.product, null, schema);
		pos.productDescription = pos.product;
		pos.productGroup = "Bus Entrance";
		pos.inventoryType = NO_INVENTORY_TYPE;
	}
}
