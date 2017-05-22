package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.inventory;


import java.util.Random;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory.InvMgrPOSInventoryWebAllocationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 				1. Create new POS product.
 * 				2. Go to Fnm assign POS to web.
 * 				3. Go to IM, allocate and deallocate the POS to/from web.
 * 				4. Check allocate/deallocate status.
 * @Preconditions:
 * 1. Add new 'Account Schedule' in Fnm, for Agency 'MSHF' and 'STATE PARKS'. Product 'Books'.
 * 2. the role Administrator has the assigned features: SearchWarehouse
 * 3. make sure the product class 'Auto Test Class' and subclass 'Auto Test SubClass' have been added in MS contract by running sql:
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 4, 'Test Class' );  
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 5, 'Test SubClass' ); 
 * 
 * @SPEC: TC043063:Deallocate Web POS Inventory  

 * @Task#: AUTO-1246
 * 
 * @author pzhu
 * @Date  Sep 12, 2012
 */
public class DeallocateWebPOSInventory extends InventoryManagerTestCase {
	private LoginInfo loginFnm = new LoginInfo();
	private String warehouseName;
	private TimeZone timeZone;
	private FinanceManager fnm = FinanceManager.getInstance();

	private InvMgrPOSInventoryWebAllocationPage webAllocationPage = InvMgrPOSInventoryWebAllocationPage.getInstance();
		
	
		
	@Override
	public void execute() {
		
		this.preparePrdClass();
		
		//Step 1: create POS product
		im.loginInventoryManager(login);
		im.gotoWarehousesSearchPg();
		im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
		im.gotoPosProductSetupPage();
		im.addPOSProduct(pos);
		pos.productID = im.getProductID("Product Name", pos.product, null, schema);
				
		im.logoutInvManager();
		
		
		//Step 2: log into Finance Manager, Assign the product to Web.
		fnm.loginFinanceManager(loginFnm, false);
		fnm.gotoWebPOSProductAssignmentPage();
		fnm.assignPOSProdToWeb(pos);
		fnm.logoutFinanceManager();
		
		
		//Step 3: Go to Inventory Manager to allocate and deallocate to/from Web.
		im.loginInventoryManager(login,false);
		im.gotoWarehousesSearchPg();
		im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
		im.gotoPOSInventoryManagementPageFromTopMenuPage();
		im.gotoPOSInventoryWebAllocationPage();
		
		//Check point 1: check allocate and deallocate POS to/from web
		this.checkAllocateAndDeallocate();
		
		im.logoutInvManager();
		
	}

	private void checkAllocateAndDeallocate() {
		
		im.allocatePosProductToWeb(pos);
		webAllocationPage.searchPOS(pos.product, pos.productGroup, null);
		String status = webAllocationPage.getAllocationStatus(pos.product);
		if(!status.equalsIgnoreCase(POS_ALLOCATED))
		{
			throw new ErrorOnPageException("Allocation Status of Pos Product "+pos.product+" Error!!!", POS_ALLOCATED, status);
		}
		logger.info("Check Allocate POS to web Passed!!!");
		
		im.deallocatePosProductToWeb(pos);
		webAllocationPage.searchPOS(pos.product, pos.productGroup, null);
		status = webAllocationPage.getAllocationStatus(pos.product);
		if(!status.equalsIgnoreCase(POS_DEALLOCATED))
		{
			throw new ErrorOnPageException("Allocation Status of Pos Product "+pos.product+" Error!!!", POS_DEALLOCATED, status);
		}
		logger.info("Check Deallocate POS to web Passed!!!");
		
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
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
	
		
		// Fnm Login info
		loginFnm.userName = TestProperty.getProperty("orms.fnm.user");
		loginFnm.password = TestProperty.getProperty("orms.fnm.pw");
		loginFnm.contract = login.contract;
		loginFnm.location = login.location;
		
		
		warehouseName = "AutoWarehouse";	
		
		// POS info
		String randomSeed = String.valueOf(new Random().nextInt(9999));
		pos.productCode = "PETER091201"+randomSeed;
		pos.product = "DeallocateWebPOSInventory"+randomSeed;
		pos.productDescription = pos.product;
		pos.productGroup = "Books";
		pos.productClass = "Auto Test Class"; // Insert it by running sql. See preconditions of the case 
		pos.productSubClass = "Auto Test SubClass"; // Insert it by running sql. See preconditions of the case 
		pos.inventoryType = RESTRICTIVE_INVENTORY_TYPE;


		// POS location info
		pos.availableLocation = "All Agencies";
		pos.specificLocation = true;
		pos.revLocation.agency = "STATE PARKS";
		pos.revLocation.region = "DISTRICT 2";
		pos.revLocation.facility = im.getFacilityName("152811", schema);//GEORGE P COSSAR
		
		pos.qtyOnHand = "0";

	
		
		//POS fee info
		pos.variablePriceAllowed = YES_STATUS;
		pos.partialQuantityAllowed = NO_STATUS;
		pos.unitPrice = "8.88";
		pos.effectiveSalesStartDate = DateFunctions.getDateAfterToday(-1, timeZone);
		pos.effectiveSalesEndDate = DateFunctions.getToday(timeZone);
		
		// POS barcode info
		POSInfo.BarCode barcode = pos.new BarCode();
		barcode.barCode = "BAR091201"+randomSeed;
		pos.barcodeList.add(barcode);	
				

	}
}
