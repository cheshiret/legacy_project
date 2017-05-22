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
 * @Description: it is to check a workflow of adding a POS product: 
 * 1. Add a Master POS Product, and check default values on the page.
 * 2. Set up POS Info
 * 3. Select a revenue location but click Cancel button on the dialog, then check the revenue location info
 * 4. Click Cancel button on the page, and check the pos is not added  
 * @Preconditions:
 * 1. the role Administrator has the assigned features: SearchPOSProductSetup, AddPOSProduct
 * @SPEC: POS Setup [TC:032103] Step37, 38, 50
 * @Task#: Auto-1210
 * 
 * @author Lesley Wang
 * @Date  Aug 21, 2012
 */
public class Add_Cancel extends InventoryManagerTestCase {
	private InvMgrPosSetupDetailsPage posSetupDetailsPg = InvMgrPosSetupDetailsPage.getInstance();
	private InvMgrMasterPosSearchPage masterPosSetupPg = InvMgrMasterPosSearchPage.getInstance();
	private POSInfo defaultPOS = new POSInfo();
	
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoMasterPosSetupPage();
		
		// Add Marster POS Product and check the default values in the page
		im.gotoAddMasterPOSProductPage();
		posSetupDetailsPg.verifyPOSInfo(defaultPOS); 
		
		// Setup POS info in the page 
		im.setupPosInfo(pos);
		
		// Select a specific location but click Cancel button on Select location dialog
		this.updatePOSLocationInfo();
		im.selectPOSRevenueSpecificLocation(pos.revLocation, false);
		this.verifyPOSRevenueSpecificLocation();
		
		// Click Cancel button on the POS Setup details page
		im.gotoMasterListPgFromPOSDetailsPg(false);		
		
		// Search the POS Product and verify the pos product is not added
		masterPosSetupPg.searchPOSByName(pos.product);
		masterPosSetupPg.verifyPOSProductExistInList(pos.productID, false);
		
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/" + TestProperty.getProperty("ms.admin.location");
		
		// Default POS Info
		defaultPOS.orderType = "POS Sale";
		defaultPOS.productID = "NEW";
		defaultPOS.status = ACTIVE_STATUS;
		defaultPOS.inventoryType = NO_INVENTORY_TYPE;
		defaultPOS.availableLocation = "All Agencies";
		defaultPOS.specificLocation = false;
		defaultPOS.acquierZipCodeInSale = NO_STATUS;
		
		// POS info
		String randomNum = String.valueOf(DateFunctions.getCurrentTime());
		pos.product = "AddPOSAndCancel" + randomNum;
		pos.productGroup = "Books";
		pos.inventoryType = NON_RESTRICTIVE_INVENTORY_TYPE;
		pos.productDescription = "AddPOSAndCancel";
		pos.orderType = "POS Sale";
		pos.status = ACTIVE_STATUS;
		
		// POS location info
		pos.availableLocation = "STATE PARKS";
		pos.specificLocation = false;
	}
	
	/** Update POS Location info */
	private void updatePOSLocationInfo() {
		pos.specificLocation = true;
		pos.revLocation.agency = "STATE PARKS";
		pos.revLocation.region = "DISTRICT 1";
		pos.revLocation.facility = "LEGION STATE PARK"; // 151831
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
