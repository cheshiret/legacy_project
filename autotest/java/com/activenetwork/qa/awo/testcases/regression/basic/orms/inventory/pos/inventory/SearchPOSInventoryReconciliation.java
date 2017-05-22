package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.inventory;


import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory.InvMgrPOSInventoryReconciliationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 				1. Go to IM, Select warehouse 'AutoWarehouse', Go to 'POS product setup' page.
 * 				2. Add new POS product.
 * 				3. Go to 'POS Inventory Reconciliation'
 * 				4. Make reconciliation to new POS product.
 * 				5. Search by different conditions, check search result.
 * @Preconditions:
 * 1. the role Administrator has the assigned features: SearchWarehouse
 * 2. make sure the product class 'Auto Test Class' and subclass 'Auto Test SubClass' have been added in MS contract by running sql:
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 4, 'Test Class' );  
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 5, 'Test SubClass' ); 
 * 
 * @SPEC: TC 042838:Search Warehouse POS Product Physical Inventory Reconciliation 

 * @Task#: AUTO-1246
 * 
 * @author pzhu
 * @Date  Sep 11, 2012
 */
public class SearchPOSInventoryReconciliation extends InventoryManagerTestCase {
	private String warehouseName;
	private InvMgrPOSInventoryReconciliationPage reconciliationPage = InvMgrPOSInventoryReconciliationPage.getInstance();
	private POSInfo.BarCode barcode;
		
	@Override
	public void execute() {
		
		this.preparePrdClass();
		
		im.loginInventoryManager(login);
		im.gotoWarehousesSearchPg();
		im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
		if(!im.verifyProductExistInSys(schema, pos.productCode, pos.product)) {
			im.gotoPosProductSetupPage();
			im.addPOSProduct(pos);
		}
		pos.productID = im.getProductID("Product Name", pos.product, null, schema);
		
		//Make physical reconciliation for POS product
		im.gotoPOSInventoryReconciliationPageFromTopMenu();
		im.reconcilePOSPhysicalInventory(barcode.barCode, pos.product, pos.physicalQtyOnHand);

		//Search reconciliation record.
		POSInfo searchResult = this.searchPosReconciliation();
		
		//Check point 1: check search result.
		this.checkSearchResult(searchResult);

	
		im.logoutInvManager();
	}

	
	private POSInfo searchPosReconciliation() {
		POSInfo searchCondition = new POSInfo();
		searchCondition.product = pos.product;
		searchCondition.productGroup = pos.productGroup;
		searchCondition.qtyOnHand = pos.physicalQtyOnHand;
		
		reconciliationPage.searchPOSProduct(searchCondition);
		return reconciliationPage.getPOSInfo(searchCondition.product);
		
	}

	private void checkSearchResult(POSInfo searchResult) {
		
		boolean result = true;
		
		result &= searchResult.productID.equalsIgnoreCase(pos.productID);
		result &= searchResult.product.equalsIgnoreCase(pos.product);
		result &= searchResult.productDescription.equalsIgnoreCase(pos.productDescription);
		result &= searchResult.productGroup.equalsIgnoreCase(pos.productGroup);
		result &= searchResult.productClass.equalsIgnoreCase(pos.productClass);
		result &= searchResult.productSubClass.equalsIgnoreCase(pos.productSubClass);
		result &= searchResult.inventoryType.equalsIgnoreCase(pos.inventoryType);
		result &= searchResult.qtyOnHand.equalsIgnoreCase(pos.physicalQtyOnHand);
		
		if(!result)
		{
			throw new ErrorOnPageException("Check Search result failed...", StringUtil.ObjToString(pos), StringUtil.ObjToString(searchResult));
		}
		
		logger.info("Check search result PASSED!!!");		
		
	}

	private void preparePrdClass() {
		// insert product class and product subclass into DB if they don't exist
		if (!im.isProductClassExist(schema, pos.productClass)) {
			im.insertProductClass(schema, pos.productClass);
		}
		if (!im.isProductSubClassExist(schema, pos.productSubClass)) {
			im.insertProductSubClass(schema, pos.productSubClass);
		}
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/" + TestProperty.getProperty("ms.admin.location");
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		warehouseName = "AutoWarehouse";	
		
		// POS info
		pos.productCode = "PETER201291101";
		pos.product = "SearchPOSInventoryReconciliation";
		pos.productDescription = pos.product;
		pos.productGroup = "Books";
		pos.productClass = "Auto Test Class"; // Insert it by running sql. See preconditions of the case 
		pos.productSubClass = "Auto Test SubClass"; // Insert it by running sql. See preconditions of the case 
		pos.inventoryType = NON_RESTRICTIVE_INVENTORY_TYPE;
		pos.qtyOnHand = "0";
		// POS location info
		pos.availableLocation = "All Agencies";
		pos.specificLocation = false;
		pos.physicalQtyOnHand = String.valueOf(new Random().nextInt(999));//max digits is 6
		
		// POS barcode info
		barcode = pos.new BarCode();
		barcode.barCode = "BAR0012";
		pos.barcodeList.add(barcode);	
		
	

	}
}
