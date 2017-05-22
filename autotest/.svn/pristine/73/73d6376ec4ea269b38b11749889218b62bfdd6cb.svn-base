/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.product;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrMasterPosSearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrPosSetupDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: it is to check a workflow of editing a POS product: 
 * 1. Add a Master POS Product with No Inventory as Inventory Type
 * 2. Edit the POS, change the pos details,
 * 3. select a specific location and click Save, then change Available Location and check the location info
 * 4. Select a specific location but click Cancel on the dialog, then check the revenue location info
 * 5. click Cancel, then check the edited POS product.   
 * @Preconditions:
 * 1. the role Administrator has the assigned features: SearchPOSProductSetup, AddPOSProduct, EditPOSProduct, DeactivatePOSProduct
 * @SPEC: POS Setup [TC:032103] Step24, 33~35
 * @Task#: Auto-1210
 * 
 * @author Lesley Wang
 * @Date  Aug 23, 2012
 */
public class Edit_Cancel extends InventoryManagerTestCase {

	private InvMgrPosSetupDetailsPage posSetupDetailsPg = InvMgrPosSetupDetailsPage.getInstance();
	private InvMgrMasterPosSearchPage masterPosSetupPg = InvMgrMasterPosSearchPage.getInstance();
	
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoMasterPosSetupPage();
		
		// Add a POS Product
		pos.productID = im.addMasterPosProduct(pos);
	
		// Edit the POS Product
		im.gotoMasterPOSDetailsPgFromListPg(pos.productID);
		POSInfo updatedPOS = this.updatePOSInfo(pos.productID);
		im.setupPosInfo(updatedPOS);
		
		// Change Product Available Location and check revenue location info
		im.changePOSProductAvailableLocation("All Agencies");
		this.verifyPOSRevenueSpecificLocation();
		
		// Select a specific location but click Cancel on the dialog, then check the revenue location info
		im.selectPOSRevenueSpecificLocation(updatedPOS.revLocation, false);
		this.verifyPOSRevenueSpecificLocation();
		
		// Click Cancel button on the POS Setup details page
		im.gotoMasterListPgFromPOSDetailsPg(false);
		
		// Check the POS info in Master Product List page
		masterPosSetupPg.searchPOSById(pos.productID);
		masterPosSetupPg.verifyPOSInList(pos);
		
		// Check the edited POS Product on POS Product Setup Details page
		im.gotoMasterPOSDetailsPgFromListPg(pos.productID);
		posSetupDetailsPg.verifyPOSInfo(pos);
		
		// Clean up - Deactivate the POS Product
		im.gotoMasterListPgFromPOSDetailsPg(false);
		im.deactivatePOS(pos.productID);
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/" + TestProperty.getProperty("ms.admin.location");
		
		// POS info
		String randomNum = String.valueOf(DateFunctions.getCurrentTime());
		pos.product = "AddPOSProduct" + randomNum;
		pos.productGroup = "Bus Entrance";
		pos.inventoryType = NO_INVENTORY_TYPE;
		pos.productDescription = "AddPOSProduct";	
		pos.orderType = "POS Sale";
		pos.status = ACTIVE_STATUS;
		pos.acquierZipCodeInSale = NO_STATUS;
		pos.vehicle = NO_STATUS;
		
		// POS location info
		pos.availableLocation = "MSHF";
		pos.specificLocation = false;
	}
	
	private POSInfo updatePOSInfo(String id) {
		POSInfo posInfo = new POSInfo();
		String randomNum = String.valueOf(DateFunctions.getCurrentTime());
		posInfo.productID = id;
		posInfo.product = "EditPOSProduct" + randomNum;
		posInfo.productGroup = "Bus Entrance";
		posInfo.productDescription = "EditPOSProduct";	
		
		// POS location info
		posInfo.availableLocation = "MSHF";
		posInfo.specificLocation = true;
		posInfo.revLocation.agency = "MSHF";
		
		return posInfo;
	}
	
	/** Verify POS Revenue Specific Location. */
	private void verifyPOSRevenueSpecificLocation() {
		boolean result = true;
		result &= MiscFunctions.compareResult("The selection of the radio button 'A Specific Location'", 
				false, posSetupDetailsPg.isSpecificLocationSelected());
		result &= MiscFunctions.compareResult("The value of specific location'", 
				"", posSetupDetailsPg.getRevenueLocation());

		if (!result) {
			throw new ErrorOnPageException("The revenue location is wrong! Please check logger error.");
		}
		logger.info("The revenue location is correct!");
	}
}
