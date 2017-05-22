/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.product;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrMasterPosSearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrPosSetupDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: it is to check a workflow of activating and deactivating POS product: 
 * 1. Add a Master POS Product
 * 2. Deactivate the POS product and check the status 
 * 3. Activate the POS product and check the status  
 * @Preconditions:
 * 1. the role Administrator has the assigned features: 
 * SearchPOSProductSetup, AddPOSProduct, EditPOSProduct, DeactivatePOSProduct, ActivatePOSProducts
 * 2. the product group "Books" exist.
 * @SPEC: POS Setup [TC:032103] Step15, 17~19, 21~22
 * @Task#: Auto-1210
 * 
 * @author Lesley Wang
 * @Date  Aug 23, 2012
 */
public class ActivateAndDeactivate extends InventoryManagerTestCase {

	public void execute() {
		im.loginInventoryManager(login);
		im.gotoMasterPosSetupPage();
		
		// Add a POS Product
		pos.productID = im.addMasterPosProduct(pos);
	
		// Deactivate the POS product and check the status
		im.deactivatePOS(pos.productID);
		pos.status = INACTIVE_STATUS;
		this.verifyPOSProductStatus(pos.status, pos.productID);
		
		// Activate the POS product and check the status
		im.activatePOS(pos.productID);
		pos.status = ACTIVE_STATUS;
		this.verifyPOSProductStatus(pos.status, pos.productID);
		
		// Clean up - Deactivate the POS Product
		im.deactivatePOS(pos.productID);
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/" + TestProperty.getProperty("ms.admin.location");
		
		// POS info
		pos.product = "ActivateAndDeactivePOS" + DateFunctions.getCurrentTime();
		pos.productGroup = "Books";
		pos.inventoryType = NO_INVENTORY_TYPE;
		pos.productDescription = "ActivateAndDeactivePOS";
		
		// POS location info
		pos.availableLocation = "All Agencies";
		pos.specificLocation = false;
	}
	
	/** Verify POS product status on Master POS Product List page and Setup Details page*/
	private void verifyPOSProductStatus(String expStatus, String id) {
		InvMgrPosSetupDetailsPage posSetupDetailsPg = InvMgrPosSetupDetailsPage.getInstance();
		InvMgrMasterPosSearchPage masterPosSetupPg = InvMgrMasterPosSearchPage.getInstance();
		
		// Check status on Master POS Product List page
		boolean result = MiscFunctions.compareResult("POS Product Status", expStatus, 
				masterPosSetupPg.getPOSProductStatus(id));	
		
		// Check status on POS Product Setup Details page
		im.gotoMasterPOSDetailsPgFromListPg(id);
		result &= MiscFunctions.compareResult("POS Product Status", expStatus, 
				posSetupDetailsPg.getProductStatus());
		im.gotoMasterListPgFromPOSDetailsPg(false);
		
		if (!result) {
			throw new ErrorOnPageException("The POS product with id=" + id + "has a wrong status! Please check logger error!");
		}
		logger.info("The status of the POS product with id=" + id + "is correct!");
	}
}
