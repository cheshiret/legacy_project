/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.product;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo.BarCode;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrPosSetBarcodePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrPosSetupListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: it is to check a workflow of editing warehouse POS product assignment: 
 * 1. Add a POS with non-restrictive inventory type in a warehouse
 * 2. Edit the POS assignment and check the POS assignment info
 * 3. Unassign the POS for clean up
 * 
 * @Preconditions:
 * 1. the role Administrator has the assigned features: SearchWarehouse, EditWarehouse, 
 * SearchPOSProductSetup, AssignPOSProductSetup, UnAssignPOSProductSetup, EditPOSSetup
 * 2. make sure the product class 'Auto Test Class' and subclass 'Auto Test SubClass' have been added in MS contract by running sql:
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 4, 'Test Class' );  
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 5, 'Test SubClass' ); 
 *  
 * @SPEC: Edit Warehouse POS Product [TC:032105]Step1, 5~16
 * @Task#: Auto-1210
 * @author Lesley Wang
 * @Date  Aug 24, 2012
 */
public class EditWarehouseAssignment extends InventoryManagerTestCase {
	private String warehouseName;
	private InvMgrPosSetupListPage posSetUpPg = InvMgrPosSetupListPage.getInstance();
	private InvMgrPosSetBarcodePage posBarcodePg = InvMgrPosSetBarcodePage.getInstance();
	@Override
	public void execute() {
		// insert product class and product subclass into DB if they don't exist
		if (!im.isProductClassExist(schema, pos.productClass)) {
			im.insertProductClass(schema, pos.productClass);
		}
		if (!im.isProductSubClassExist(schema, pos.productSubClass)) {
			im.insertProductSubClass(schema, pos.productSubClass);
		}
		
		im.loginInventoryManager(login);
		im.gotoWarehousesSearchPg();
		im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
		
		// Add a POS product in the warehouse and the POS has been assigned to the warehouse automatically
		im.gotoPosProductSetupPage();
		pos.productID = im.addPOSProduct(pos);
	
		// Go to edit the POS product assignment and verify the info
		im.gotoWarehousePOSSetupDetailsPgFromSetupListPg(pos);
		posBarcodePg.verifyPOSInfo(pos);
		this.verifyPOSProductTextFieldsDisabled();
		
		// Change the product barcode
		pos.barcodeList = this.updatePOSBarcodeInfo();
		posBarcodePg.setBarCode(pos.barcodeList);
		im.gotoWarehousePOSSetupListPgFromSetupDetailsPg();
		
		// Verify barcode in Product Setup Details page
		im.gotoWarehousePOSSetupDetailsPgFromSetupListPg(pos);
		posBarcodePg.verifyPOSInfo(pos);
		
		// Unassign the POS product and verify the info
		im.gotoWarehousePOSSetupListPgFromSetupDetailsPg();
		posSetUpPg.searchPOSProduct(pos);
		im.unassignPOSFromWarehouse(pos.product);
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/" + TestProperty.getProperty("ms.admin.location");
		warehouseName = "AutoWarehouse";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		// POS info
		String randomNum = String.valueOf(DateFunctions.getCurrentTime());
		pos.productCode = randomNum;
		pos.product = "EditWarehouseAssign" + randomNum;
		pos.productGroup = "Books";
		pos.inventoryType = NON_RESTRICTIVE_INVENTORY_TYPE;
		pos.productDescription = "EditWarehouseAssign";	
		pos.productClass = "Auto Test Class";
		pos.productSubClass = "Auto Test SubClass";
		pos.qtyOnHand = "0";
		
		// POS barcode info
		POSInfo.BarCode barcode = pos.new BarCode();
		barcode.barCode = randomNum;
		pos.barcodeList.add(barcode);
		POSInfo.BarCode barcode2 = pos.new BarCode();
		barcode2.barCode = "Edit" + randomNum;
		pos.barcodeList.add(barcode2);
		
		// POS location info
		pos.availableLocation = "MSHF";
		pos.specificLocation = false;
	}
	
	public void verifyPOSProductTextFieldsDisabled() {
		logger.info("Verify POS product textfields are disabled in the page...");
		boolean result = false;
		result |= posBarcodePg.isProductIDTextFieldEnabled();
		result |= posBarcodePg.isProductNameTextFieldEnabled();
		result |= posBarcodePg.isProductDescriptionTextFieldEnabled();
		result |= posBarcodePg.isProductGroupTextFieldEnabled();
		result |= posBarcodePg.isProductInventoryTypeTextFieldEnabled();
		result |= posBarcodePg.isProductBarcodeTextFieldEnabled();
		if (result) {
			throw new ErrorOnPageException("The text fields on the page should be disabled!");
		}
		logger.info("All text fields on the page are disabled!");
	}
	
	private ArrayList<BarCode> updatePOSBarcodeInfo() {
		ArrayList<BarCode> barcodes = new ArrayList<BarCode>();
		String randomNum = String.valueOf(DateFunctions.getCurrentTime());
		POSInfo.BarCode barcode = pos.new BarCode();
		barcode.barCode = randomNum;
		barcodes.add(barcode);
		POSInfo.BarCode barcode2 = pos.new BarCode();
		barcode2.barCode = "Edit" + randomNum;
		barcode2.isRemove = true;
		barcodes.add(barcode2);
		return barcodes;
	}
}
