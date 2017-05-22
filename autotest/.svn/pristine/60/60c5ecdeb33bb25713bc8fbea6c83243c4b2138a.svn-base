/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.product;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrMasterPosSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: it is to check a workflow of searching a POS product: 
 * 1. Add two Master POS Products
 * 2. Search by multiple conditions
 * 3. Search by single condition  
 * 4. Select both POS products and deactivate them
 * 5. Sort by ID and check the sort order.   
 * @Preconditions:
 * 1. the role Administrator has the assigned features: 
 * SearchPOSProductSetup, AddPOSProduct, DeactivatePOSProduct, ActivatePOSProduct
 * 2. the product group "Books", "Bus Entrance" exist.
 * 3. make sure the product class 'Auto Test Class' and subclass 'Auto Test SubClass' have been added in MS contract by running sql:
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 4, 'Test Class' );  
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 5, 'Test SubClass' ); 
 * 
 * @SPEC: POS Setup [TC:032103] Step8~9, 23
 * @Task#: Auto-1210
 * 
 * @author Lesley Wang
 * @Date  Aug 23, 2012
 */
public class Search extends InventoryManagerTestCase {
	private InvMgrMasterPosSearchPage masterPosSetupPg = InvMgrMasterPosSearchPage.getInstance();
	private POSInfo secPOS = new POSInfo();
	private String randomNum ;
	
	public void execute() {
		// insert product class and product subclass into DB if they don't exist
		if (!im.isProductClassExist(schema, pos.productClass)) {
			im.insertProductClass(schema, pos.productClass);
		}
		if (!im.isProductSubClassExist(schema, pos.productSubClass)) {
			im.insertProductSubClass(schema, pos.productSubClass);
		}
		
		im.loginInventoryManager(login);
		im.gotoMasterPosSetupPage();
		
		// Add two POS Product
		pos.productID = im.addMasterPosProduct(pos);
		secPOS.productID = im.addMasterPosProduct(secPOS);

		// Search the POS Product with pos info and check only pos info is found
		this.searchAndVerifySearchResults(pos, true, false, "all creteria");

		// Search POS product by product ID
		POSInfo searchPOS = new POSInfo();
		searchPOS.productID = pos.productID;
		this.searchAndVerifySearchResults(searchPOS, true, false, "product id");	
		
		// Search POS product by barcode
		searchPOS.productID = "";
		searchPOS.barcode = pos.barcodeList.get(0).barCode;
		this.searchAndVerifySearchResults(searchPOS, true, false, "product barcode");
		
		// Search POS product by product name
		searchPOS.barcode = "";
		searchPOS.product = pos.product;
		this.searchAndVerifySearchResults(searchPOS, true, false, "product name");
		searchPOS.product = randomNum;
		this.searchAndVerifySearchResults(searchPOS, true, true, "partial product name");

		// Search POS product by product name and description
		searchPOS.productDescription = pos.productDescription;
		this.searchAndVerifySearchResults(searchPOS, true, false, "product description");
		
		// Search POS product by name and order type
		searchPOS.productDescription = "";
		searchPOS.orderType = pos.orderType;
		this.searchAndVerifySearchResults(searchPOS, true, true, "product name and order type");
		
		// Search POS product by name and product group
		searchPOS.orderType = "";
		searchPOS.productGroup = pos.productGroup;
		this.searchAndVerifySearchResults(searchPOS, true, false, "product name and group");
		
		// Search POS product by name and  product class
		searchPOS.productGroup = "";
		searchPOS.productClass = pos.productClass;
		this.searchAndVerifySearchResults(searchPOS, true, false, "product name and class");
		
		// Search POS product by name and  product sub class
		searchPOS.productClass = "";
		searchPOS.productSubClass = pos.productSubClass;
		this.searchAndVerifySearchResults(searchPOS, true, false, "product name and sub class");
		
		// Search POS product by name and  product inventory type
		searchPOS.productSubClass = "";
		searchPOS.inventoryType = pos.inventoryType;
		this.searchAndVerifySearchResults(searchPOS, true, false, "product name and inventory type");
		
		// Search POS product by name and  product available location
		searchPOS.inventoryType = "";
		searchPOS.availableLocation = pos.availableLocation;
		this.searchAndVerifySearchResults(searchPOS, true, false, "product name and available location");
		
		// Search POS product by name and status
		searchPOS.availableLocation = "";
		searchPOS.status = pos.status;
		this.searchAndVerifySearchResults(searchPOS, true, true, "product name and status");		
		
		// Select both and deactivate the POS Product
		masterPosSetupPg.searchPOS(searchPOS);
		masterPosSetupPg.selectAllCheckboxes();
		this.deactivatePOS();
		this.searchAndVerifySearchResults(searchPOS, false, false, "product name and status 'Active'");		
		searchPOS.status = INACTIVE_STATUS;
		this.searchAndVerifySearchResults(searchPOS, true, true, "product name and status 'Inactive'");		
		
		// Click Product ID column and check the sort order
		masterPosSetupPg.searchPOS(searchPOS);
		this.sortAndVerifySortOrderByProductID(pos.productID, secPOS.productID);
		this.sortAndVerifySortOrderByProductID(secPOS.productID, pos.productID);
		
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/" + TestProperty.getProperty("ms.admin.location");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		// POS info
		randomNum = String.valueOf(DateFunctions.getCurrentTime());		
		pos.productCode = randomNum;
		pos.product = "SearchPOSProduct" + randomNum;
		pos.productGroup = "Books";
		pos.inventoryType = NO_INVENTORY_TYPE;
		pos.productDescription = "SearchPOSProduct";
		pos.orderType = "POS Sale";
		pos.status = ACTIVE_STATUS;	
		pos.productClass = "Auto Test Class";
		pos.productSubClass = "Auto Test SubClass";
		// POS barcode info
		POSInfo.BarCode barcode = pos.new BarCode();
		barcode.barCode = randomNum;
		pos.barcodeList.add(barcode);	
		// POS location info
		pos.availableLocation = "All Agencies";
		pos.specificLocation = false;
		
		// Second POS info
		secPOS.product = "POSProductSearch" + randomNum;
		secPOS.productGroup = "Bus Entrance";
		secPOS.inventoryType = NON_RESTRICTIVE_INVENTORY_TYPE;
		secPOS.productDescription = "POSProductSearch";
		secPOS.orderType = "POS Sale";
		secPOS.status = ACTIVE_STATUS;	
		// POS location info
		secPOS.availableLocation = "MSHF";
		secPOS.specificLocation = false;
	}
	
	/** Search and verify search result */
	private void searchAndVerifySearchResults(POSInfo searchPOS, boolean isFirstPOSExist, boolean isSecPOSExist, String creteria) {
		masterPosSetupPg.searchPOS(searchPOS);
		masterPosSetupPg.verifyPOSProductExistInList(pos.productID, isFirstPOSExist);
		masterPosSetupPg.verifyPOSProductExistInList(secPOS.productID, isSecPOSExist);
		masterPosSetupPg.clickMasterPOSSetupTab();
		ajax.waitLoading();
		masterPosSetupPg.waitLoading();
		logger.info("Search pos by " + creteria + " correctly!");	
	}
	
	/** Deactivate POS */
	private void deactivatePOS() {
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();
		
		logger.info("Deactivate POS...");
		masterPosSetupPg.clickDeactivate();
		ajax.waitLoading();
		confirmWidget.waitLoading();
		confirmWidget.clickOK();
		ajax.waitLoading();
		masterPosSetupPg.waitLoading();
	}
	
	/** Sort by product ID and verify sort order */
	private void sortAndVerifySortOrderByProductID(String firID, String secID) {
		masterPosSetupPg.clickProductIDColumnName();
		masterPosSetupPg.waitLoading();
		int firstRow = masterPosSetupPg.getRowIndexById(firID);
		int secRow = masterPosSetupPg.getRowIndexById(secID);
		if (firstRow >= secRow) {
			throw new ErrorOnPageException("The sort order by product ID is wrong! Expect: " + firID +
					", " + secID);
		}
		logger.info("The sort order by product ID is correct!");
	}
}
