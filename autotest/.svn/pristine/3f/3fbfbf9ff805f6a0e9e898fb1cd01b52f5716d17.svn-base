/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.supplies;

import java.util.TimeZone;

import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSupplyInventoryTrackingPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 *@Description: Search and view a supply POS product inventory tracking of inventory decrease record.
 * 1. Search by criteria matched the known record and check the record info
 * @Preconditions: 
 * 1. The supply product "InvDecreaseTracking" exists.
 * 2. The features "ViewInternalSupplierPOSSupplies" and "ViewWildlifePOSProductInventoryChangeHistory" 
 * has been assigned to the role HF Administrator
 * @SPEC: POS Product Inventory Tracking [TC:028968]
 * @Task#: Auto-1213
 * 
 * @author Lesley Wang
 * @Date  Sep 3, 2012
 */
public class ViewInventoryTracking_DecreaseInventory extends
LicenseManagerTestCase {
	private TimeZone timeZone;
	private LicMgrSupplyInventoryTrackingPage invTrackingPg = 
		LicMgrSupplyInventoryTrackingPage.getInstance();
	@Override
	public void execute() {
		// Go to Supplies Product List page and adjust the product's inventory - Decrease Inventory
		lm.loginLicenseManager(login);
		lm.gotoSupplySearchListPageFromTopMenu();
		supply.qtyOnHand = lm.adjustSupplyProductInventory(supply);
		supply.transactionDateTime = DateFunctions.getToday(timeZone);
		
		//  Go to the product inventory tracking page and check the inventory adjustment record info
		lm.gotoSupplySearchListPageFromTopMenu();
		lm.gotoSupplyDetailFromListPage(supply.code);
		lm.gotoSupplyInventoryTrackingPgFromDetailsPg();
		supply.adjustmentQuantity = String.valueOf(0 - Integer.valueOf(supply.adjustmentQuantity)); // The negative value is shown on page when adjustment type is Decrease Inventory
		supply.qtyOnHand = String.valueOf(Integer.valueOf(supply.qtyOnHand) + 
				Integer.valueOf(supply.adjustmentQuantity));
		invTrackingPg.searchInventoryTrackingRecords(supply.transactionDateTime, supply.transactionDateTime);
		invTrackingPg.verifySupplyInventoryAdjustmentInfo(supply);
		
		lm.logOutLicenseManager();	
	}

	@Override
	public void wrapParameters(Object[] param) {
		String adminLoc = TestProperty.getProperty("ms.admin.location");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		// Login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + adminLoc;
		
		// Supply info
		supply.code = "DEC";//	supply.name = "InvDecreaseTracking";
		supply.supplyReceivedDate = DateFunctions.getToday(timeZone);
		supply.adjustmentType = "Decrease Inventory";
		supply.adjustmentQuantity = "1";
		supply.adjustmentNotes = "Adjustment Inventory - " + supply.adjustmentType;
		supply.adjustmentLocation = adminLoc;
		supply.adjustmentUser = DataBaseFunctions.getLoginUserName(login.userName);
		supply.supplyOrderNum = "";
	}
}
