/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.product;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrPosSetupDetailsPage;
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
 * 2. the POS 'AssignAndUnassignToWarehouse_NonResInv' has been added by support script.
 * 3. make sure the product class 'Auto Test Class' and subclass 'Auto Test SubClass' have been added in MS contract by running sql:
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 4, 'Test Class' );  
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 5, 'Test SubClass' ); 
 * 
 * @SPEC: 
 * Assign Warehouse POS Product [TC:032106] Step1~2, 4~11
 * Unassign Warehouse POS Product [TC:032107] Step1~2
 * @Task#: Auto-1210
 * 
 * @author Lesley Wang
 * @Date  Aug 23, 2012
 */
public class AssignAndUnassignToWarehouse extends InventoryManagerTestCase {
	private String warehouseName;
	private InvMgrPosSetupListPage posSetUpPg = InvMgrPosSetupListPage.getInstance();
	
	@Override
	public void execute() {
		// Prepare POS product class and sub class
		this.prepareProductClass();
		
		im.loginInventoryManager(login);
		this.preparePOSProduct();
		
		im.gotoWarehousesSearchPg();
		im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
		im.gotoPosProductSetupPage();
		
		// Assign the POS product and verify the assignment info
		posSetUpPg.searchPOSProduct(pos);
		im.assignPOSToWarehouse(pos.product);
		pos.assignStatus = YES_STATUS; 
		posSetUpPg.verifyPOSInfo(pos);
		posSetUpPg.verifyProductIDLinkExist(pos.productID, true);
		
		// Unassign the POS product and verify the info
		im.unassignPOSFromWarehouse(pos.product);
		pos.assignStatus = NO_STATUS;
		posSetUpPg.verifyPOSInfo(pos);
		posSetUpPg.verifyProductIDLinkExist(pos.productID, false);
		
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/" + TestProperty.getProperty("ms.admin.location");
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		warehouseName = "AutoWarehouse";	
		
		// POS info
		pos.product = "AssignAndUnassignToWarehouse_NonResInv";
		pos.productID = im.getProductID("Product Name", pos.product, null, schema);
		pos.productDescription = pos.product;
		pos.productGroup = "Books";
		pos.productClass = "Auto Test Class"; // Insert it by running sql. See preconditions of the case 
		pos.productSubClass = "Auto Test SubClass"; // Insert it by running sql. See preconditions of the case 
		pos.inventoryType = NON_RESTRICTIVE_INVENTORY_TYPE;
		pos.qtyOnHand = "0";
	}
	
	private void prepareProductClass() {
		// insert product class and product subclass into DB if they don't exist
		if (!im.isProductClassExist(schema, pos.productClass)) {
			im.insertProductClass(schema, pos.productClass);
		}
		if (!im.isProductSubClassExist(schema, pos.productSubClass)) {
			im.insertProductSubClass(schema, pos.productSubClass);
		}
	}
	
	private void preparePOSProduct() {
		InvMgrPosSetupDetailsPage posSetupDetailsPg = InvMgrPosSetupDetailsPage.getInstance();
		
		im.gotoMasterPosSetupPage();
		im.gotoMasterPOSDetailsPgFromListPg(pos.productID);
		posSetupDetailsPg.selectProductClass(pos.productClass);
		posSetupDetailsPg.selectProductSubClass(pos.productSubClass);
		posSetupDetailsPg.selectInventoryType(pos.inventoryType);
		ajax.waitLoading();
		im.gotoMasterListPgFromPOSDetailsPg(true);
	}
}
