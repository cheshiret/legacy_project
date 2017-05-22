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
 * 1. Add a POS with no inventory type in a warehouse
 * 2. Edit the POS assignment but click Cancel, then check the POS assignment info
 * 3. Unassign the POS for clean up
 * 
 * @Preconditions:
 * 1. the role Administrator has the assigned features: SearchWarehouse, EditWarehouse, 
 * SearchPOSProductSetup, AssignPOSProductSetup, UnAssignPOSProductSetup, EditPOSSetup
 * 
 * @SPEC: Edit Warehouse POS Product [TC:032105]Step1, 4~16
 * @Task#: Auto-1210
 * @author Lesley Wang
 * @Date  Aug 27, 2012
 */
public class CancelToEditWarehouseAssignment extends InventoryManagerTestCase {

	private String warehouseName;
	private InvMgrPosSetupListPage posSetUpPg = InvMgrPosSetupListPage.getInstance();
	private InvMgrPosSetBarcodePage posBarcodePg = InvMgrPosSetBarcodePage.getInstance();
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoWarehousesSearchPg();
		im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
		
		// Add a POS product in the warehouse and the POS has been assigned to the warehouse automatically
		im.gotoPosProductSetupPage();
		pos.productID = im.addPOSProduct(pos);
	
		// Go to edit the POS product assignment and verify the info
		im.gotoWarehousePOSSetupDetailsPgFromSetupListPg(pos);
		posBarcodePg.verifyPOSInfo(pos);
		this.verifyPOSQtyOnHandTextFieldNotExist();
		
		// Change the product barcode and click Cancel
		ArrayList<BarCode> barcodes = this.updatePOSBarcodeInfo();
		posBarcodePg.setBarCode(barcodes);
		im.gotoWarehousePOSSetupListPgFromSetupDetailsPg(false);
		
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
		
		// POS info
		pos.productCode = String.valueOf(DateFunctions.getCurrentTime());
		pos.product = "CancelToEditWarehouseAssign" + pos.productCode;
		pos.productGroup = "Books";
		pos.inventoryType = NO_INVENTORY_TYPE;
		pos.productDescription = "CancelToEditWarehouseAssign";	
		
		// POS barcode info
		POSInfo.BarCode barcode = pos.new BarCode();
		barcode.barCode = pos.productCode;
		pos.barcodeList.add(barcode);
		POSInfo.BarCode barcode2 = pos.new BarCode();
		barcode2.barCode = "Edit" + pos.productCode;
		pos.barcodeList.add(barcode2);
		
		// POS location info
		pos.availableLocation = "All Agencies";
		pos.specificLocation = false;
	}

	private ArrayList<BarCode> updatePOSBarcodeInfo() {
		ArrayList<BarCode> barcodes = new ArrayList<BarCode>();
		POSInfo.BarCode barcode = pos.new BarCode();
		barcode.barCode = String.valueOf(DateFunctions.getCurrentTime());
		barcodes.add(barcode);
		POSInfo.BarCode barcode2 = pos.new BarCode();
		barcode2.barCode = "Edit" + barcode.barCode;
		barcode2.isRemove = true;
		barcodes.add(barcode2);
		return barcodes;
	}
	
	private void verifyPOSQtyOnHandTextFieldNotExist() {
		boolean result = posBarcodePg.isPosProductQtyOnHandTextFieldExist();
		if (result) {
			throw new ErrorOnPageException("Qty On Hand text field should NOT exist!");
		}
		logger.info("Qty on Hand text field is not shown on the page.");
	}
}
