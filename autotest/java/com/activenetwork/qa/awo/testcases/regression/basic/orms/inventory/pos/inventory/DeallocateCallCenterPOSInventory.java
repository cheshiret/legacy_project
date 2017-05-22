package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.inventory;

import java.util.Random;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory.InvMgrPOSInventoryCallCenterAllocationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 				1. Create new POS product.
 * 				2. Go to Fnm assign POS to Call Center.
 * 				3. Go to IM, allocate and deallocate the POS to/from Call Center.
 * 				4. Check allocate/deallocate status.
 * @Preconditions:
 * 1. Add new 'Account Schedule' in Fnm, for Agency 'MSHF' and 'STATE PARKS'. Product 'Books'.
 * 2. the role Administrator has the assigned features: SearchWarehouse
 * 3. make sure the product class 'Auto Test Class' and subclass 'Auto Test SubClass' have been added in MS contract by running sql:
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 4, 'Test Class' );  
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 5, 'Test SubClass' ); 
 * 
 * @SPEC: TC043064:Deallocate Call Center POS Inventory 
 * @Task#:AUTO-1246 
 * 
 * @author pzhu
 * @Date  Sep 13, 2012
 */
public class DeallocateCallCenterPOSInventory extends InventoryManagerTestCase {
	private LoginInfo loginFnm = new LoginInfo();
	private String warehouseName;
	private TimeZone timeZone;
	private FinanceManager fnm = FinanceManager.getInstance();
	private InvMgrPOSInventoryCallCenterAllocationPage callCenterAllocationPage = InvMgrPOSInventoryCallCenterAllocationPage.getInstance();
	private POSInfo.BarCode barcode;
		
	
		
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
		
		
		//Step 2: log into Finance Manager, Assign the product to Call Center.
		fnm.loginFinanceManager(loginFnm, false);
		fnm.gotoPosProductAssignmentPage();
		fnm.assignPOSProdToCallCenter(pos);
		fnm.logoutFinanceManager();
		
		
		//Step 3: Go to Inventory Manager to allocate and deallocate to/from Call Center.
		im.loginInventoryManager(login,false);
		im.gotoWarehousesSearchPg();
		im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
		im.gotoPOSInventoryManagementPageFromTopMenuPage();
		im.gotoPOSInventoryCallCenterAllocationPage();
		
		this.allocatePOSToCallCenter();
		
		//Check point 1: check deallocate POS to/from Call Center
		this.checkDeallocatePOSFromCallCenter();
		
		im.logoutInvManager();
		
	}

	/**
	 * 
	 */
	private void allocatePOSToCallCenter() {
		im.allocatePosProductToCallCenter(pos);
		callCenterAllocationPage.searchPOS(pos.product, pos.productGroup, null);
		String status = callCenterAllocationPage.getAllocationStatus(pos.product);
		if(!status.equalsIgnoreCase(POS_ALLOCATED))
		{
			throw new ErrorOnPageException("Allocation Status of Pos Product "+pos.product+" Error!!!", POS_ALLOCATED, status);
		}
		logger.info("Check Allocate POS to Call Center Passed!!!");
		
	}

	private void checkDeallocatePOSFromCallCenter() {
		im.deallocatePosProductToCallCenter(pos);
		callCenterAllocationPage.searchPOS(pos.product, pos.productGroup, null);
		String status = callCenterAllocationPage.getAllocationStatus(pos.product);
		if(!status.equalsIgnoreCase(POS_DEALLOCATED))
		{
			throw new ErrorOnPageException("Allocation Status of Pos Product "+pos.product+" Error!!!", POS_DEALLOCATED, status);
		}
		logger.info("Check Deallocate POS to Call Center Passed!!!");
		
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
		String randomSeed = String.valueOf(new Random().nextInt(99999));
		pos.productCode = "PETER2012913"+randomSeed;
		pos.product = "DeallocateCallCenterPOSInventory"+randomSeed;
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
		pos.unitPrice = "7.77";
		pos.effectiveSalesStartDate = DateFunctions.getToday(timeZone);
		pos.effectiveSalesEndDate = DateFunctions.getToday(timeZone);
		
		// POS barcode info
		barcode = pos.new BarCode();
		barcode.barCode = "BAR20120913"+randomSeed;
		pos.barcodeList.add(barcode);	
		
		
		

	}
}
