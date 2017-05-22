/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.supplies;

import java.util.TimeZone;

import com.activenetwork.qa.awo.pages.orms.licenseManager.pos.LicMgrSupplyInventoryTrackingPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Search and view a supply POS product inventory tracking of inventory increase record.
 * 1. Check search filters' default values.
 * 2. Search by criteria not matched the known record
 * 3. Search by criteria matched the known record and check the inventory increase record
 * @Preconditions: 
 * 1. The supply product "InvIncreaseTracking" exists.
 * 2. The feature "ViewWildlifePOSProductInventoryChangeHistory" has been assigned to the role HF Administrator
 * 3. The feature "ViewInternalSupplierPOSSupplies" has been assigned to the role HF HQ Role
 * @SPEC: POS Product Inventory Tracking [TC:028968]
 * @Task#: Auto-1213
 * @Defect: DEFECT-38469.
 * @author Lesley Wang
 * @Date  Sep 1, 2012    
 */
public class ViewInventoryTracking_IncreaseInventory extends
		LicenseManagerTestCase {
	private TimeZone timeZone;
	private String adminLoc;
	@Override
	public void execute() {
		// Go to Supplies Product List page
		lm.loginLicenseManager(login);
		lm.gotoSupplySearchListPageFromTopMenu();
		
		// Adjust the Product's Inventory - Increase Inventory
		supply.qtyOnHand = lm.adjustSupplyProductInventory(supply);
		supply.transactionDateTime = DateFunctions.getToday(timeZone);
		
		// Switch to administrator and go to the product inventory tracking page
		lm.switchLocationInHomePage(adminLoc);
		lm.gotoSupplySearchListPageFromTopMenu();
		lm.gotoSupplyDetailFromListPage(supply.code);
		lm.gotoSupplyInventoryTrackingPgFromDetailsPg();
		
		// Check the default search filter values
		this.verifyDefaultSearchFilterValues();
		
		// Search with TransactionDateTo less than today and check the inventory adjustment record doesn't exist
		String fromDate = DateFunctions.getDateAfterToday(-1, timeZone);
		String toDate = fromDate;
		this.searchAndViewInventoryAdjustmentRecord(fromDate, toDate, false);
		
		// Search with TransactionDateFrom and TransactionDateTo equal to Today, 
		// and check the inventory adjustment record info
		fromDate = supply.transactionDateTime;
		toDate = supply.transactionDateTime;
		supply.qtyOnHand = String.valueOf(Integer.valueOf(supply.qtyOnHand) + 
				Integer.valueOf(supply.adjustmentQuantity)); // new qty on hand
		this.searchAndViewInventoryAdjustmentRecord(fromDate, toDate, true); //DEFECT-38469.

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		String role = "HF Administrator - Auto-";
		adminLoc =role + TestProperty.getProperty("ms.admin.location");
		String salesLoc = "WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		// Login info
		login.contract = "MS Contract";
		login.location = "HF HQ Role/" + salesLoc;
		
		// Supply info
		supply.code = "888";//supply.name = "888SuppiyPricing";
		supply.supplyReceivedDate = DateFunctions.getToday(timeZone);
		supply.adjustmentType = "Increase Inventory";
		supply.adjustmentQuantity = "1";
		supply.adjustmentNotes = "Adjustment Inventory - " + supply.adjustmentType;
		supply.adjustmentLocation = salesLoc;
		supply.adjustmentUser = DataBaseFunctions.getLoginUserName(login.userName);
		supply.supplyOrderNum = "";
	}

	private void verifyDefaultSearchFilterValues() {
		LicMgrSupplyInventoryTrackingPage invTrackingPg = LicMgrSupplyInventoryTrackingPage.getInstance();
		String actFromDate = invTrackingPg.getTransactionDateFrom();
		String actToDate = invTrackingPg.getTransactionDateTo();
		String expToDate = DateFunctions.getToday(timeZone);
		String expFromDate = DateFunctions.getDateAfterToday(-89, timeZone);
		boolean result = MiscFunctions.compareResult("Transcation Date From Default value", expFromDate, actFromDate);
		result &= MiscFunctions.compareResult("Transcation Date To Default value", expToDate, actToDate);
		
		if (!result) {
			throw new ErrorOnPageException("The defalut value of transcation date from and to are wrong! Please check logger error!");
		}
		logger.info("The default values of the transcation date from and to are correct!");
	}
	
	private void searchAndViewInventoryAdjustmentRecord(String fromDate, String toDate, boolean isExist) {
		LicMgrSupplyInventoryTrackingPage invTrackingPg = LicMgrSupplyInventoryTrackingPage.getInstance();
		// Search
		invTrackingPg.searchInventoryTrackingRecords(fromDate, toDate);
		if (isExist) {
			// check the first info
			invTrackingPg.verifySupplyInventoryAdjustmentInfo(supply);
		} else {
			int row = invTrackingPg.getRowIndexByTranscationDate(supply.transactionDateTime);
			if (row > -1) {
				throw new ErrorOnPageException("The inventory adjustment record should NOT exist!");
			}
			logger.info("Correct! No related inventory adjustment record exist!");
		}
	}
}
