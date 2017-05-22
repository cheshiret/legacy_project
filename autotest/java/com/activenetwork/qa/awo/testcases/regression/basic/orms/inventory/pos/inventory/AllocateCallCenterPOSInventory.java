package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.inventory;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.posAssignment.FinMgrPosProductAssignmentPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory.InvMgrPOSInventoryCallCenterAllocationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 				1. Create new POS product.
 * 				2. Go to Fnm assign POS to Call Center.
 * 				3. Go to IM, allocate the POS to Call Center.
 * 				4. Check allocate status.
 * @Preconditions:
 * 1. Add new 'Account Schedule' in Fnm, for Agency 'MSHF' and 'STATE PARKS'. Product 'Books'.
 * 2. the role Administrator has the assigned features: SearchWarehouse
 * 3. make sure the product class 'Auto Test Class' and subclass 'Auto Test SubClass' have been added in MS contract by running sql:
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 4, 'Test Class' );  
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 5, 'Test SubClass' ); 
 * @SPEC: TC043074:Allocate Call Center POS Inventory
 * @Task#: AUTO-1246
 * 
 * @author nding1
 * @Date  Sep 17, 2012
 */
public class AllocateCallCenterPOSInventory extends InventoryManagerTestCase {
	private LoginInfo loginFnm = new LoginInfo();
	private POSInfo pos1;
	private POSInfo pos2;
	private String warehouseName;
	private TimeZone timeZone;
	private String expectMessage;
	private FinanceManager fnm = FinanceManager.getInstance();
	private InvMgrPOSInventoryCallCenterAllocationPage callCenterAllocationPage = InvMgrPOSInventoryCallCenterAllocationPage.getInstance();
	private FinMgrPosProductAssignmentPage assignPg = FinMgrPosProductAssignmentPage.getInstance();

	@Override
	public void execute() {
		// if POS is not assigned in Fnm, assign the product to Call Center.
		fnm.loginFinanceManager(loginFnm);
		this.assignPOS(pos);
		this.assignPOS(pos1);
		this.assignPOS(pos2);
		fnm.logoutFinanceManager();

		// Go to Inventory Manager to allocate to Call Center.
		im.loginInventoryManager(login);
		im.gotoWarehousesSearchPg();
		im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
		im.gotoPOSInventoryManagementPageFromTopMenuPage();
		im.gotoPOSInventoryCallCenterAllocationPage();
		this.deallocatePOS();// clean up
		
		// verify allocate info
		this.verifyAllocate(pos);// Non-Restrictive Inventory
		this.verifyAllocate(pos1);// Restrictive Inventory
		this.verifyAllocate(pos2);// Product Package
		im.logoutInvManager();
	}
	
	private void assignPOS(POSInfo pos){
		fnm.gotoCallCenterPOSProductAssignmentPage();
		if (!assignPg.isPOSAssigned(pos.product)) {
			fnm.assignPOSProdToCallCenter(pos);
		}
	}

	private void checkAllocateStatus(POSInfo pos) {
		String status = callCenterAllocationPage.getAllocationStatus(pos.product);
		if(!status.equalsIgnoreCase(POS_ALLOCATED)) {
			throw new ErrorOnPageException("Allocation Status of Pos Product "+pos.product+" Error!!!", POS_ALLOCATED, status);
		}
		logger.info("Check Allocate POS to Call Center Passed!!!");
	}

	private void verifyErrorMsg(){
		logger.info("Re-allocate a POS to Web.");
		String errorMsg = callCenterAllocationPage.getMessage();
		if(!expectMessage.matches(errorMsg)){
			throw new ErrorOnPageException("Message of re-allocate Error!!!", expectMessage, errorMsg);
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/" + TestProperty.getProperty("ms.admin.location");
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
	
		// Fnm Login info
		loginFnm.url = AwoUtil.getOrmsURL(env);
		loginFnm.userName = TestProperty.getProperty("orms.fnm.user");
		loginFnm.password = TestProperty.getProperty("orms.fnm.pw");
		loginFnm.contract = login.contract;
		loginFnm.location = login.location;
		warehouseName = "AutoWarehouse";
		
		// POS info
		pos.productCode = "Call0Nicole43074";
		pos.product = pos.productCode;
		pos.productDescription = pos.product;
		pos.productGroup = "Books";
		pos.productClass = "Auto Test Class";
		pos.productSubClass = "Auto Test SubClass";
		pos.inventoryType = RESTRICTIVE_INVENTORY_TYPE;// Don't change this data.
		pos.productRelactionshipType = "Individual";

		pos1 = new POSInfo();
		pos1.productCode = "Call1Nicole43074";
		pos1.product = pos1.productCode;
		pos1.productDescription = pos.product;
		pos1.productGroup = "Books";
		pos1.productClass = "Auto Test Class";
		pos1.productSubClass = "Auto Test SubClass";
		pos1.inventoryType = NON_RESTRICTIVE_INVENTORY_TYPE;// Don't change this data.
		pos1.productRelactionshipType = "Individual";

		pos2 = new POSInfo();
		pos2.productCode = "Call2Nicole43074";
		pos2.product = pos2.productCode;
		pos2.productDescription = pos.product;
		pos2.productGroup = "Books";
		pos2.productClass = "Auto Test Class";
		pos2.productSubClass = "Auto Test SubClass";
		pos2.inventoryType = NO_INVENTORY_TYPE;// Don't change this data.
		pos2.productRelactionshipType = "Product Package";// Don't change this data.

		//POS fee info
		pos.variablePriceAllowed = YES_STATUS;
		pos.partialQuantityAllowed = NO_STATUS;
		pos.unitPrice = "8.91";
		pos.effectiveSalesStartDate = DateFunctions.getDateAfterToday(-1, timeZone);
		pos.effectiveSalesEndDate = DateFunctions.getDateAfterToday(90, timeZone);
		
		pos1.variablePriceAllowed = YES_STATUS;
		pos1.partialQuantityAllowed = NO_STATUS;
		pos1.unitPrice = "5.36";
		pos1.effectiveSalesStartDate = DateFunctions.getDateAfterToday(-1, timeZone);
		pos1.effectiveSalesEndDate = DateFunctions.getDateAfterToday(90, timeZone);

		pos2.variablePriceAllowed = YES_STATUS;
		pos2.partialQuantityAllowed = NO_STATUS;
		pos2.unitPrice = "9.31";
		pos2.effectiveSalesStartDate = DateFunctions.getDateAfterToday(-1, timeZone);
		pos2.effectiveSalesEndDate = DateFunctions.getDateAfterToday(90, timeZone);
	}
	
	private void deallocatePOS(){
		logger.info("Deallocate all POS from Call Center.");
		im.deallocatePosProductToCallCenter(pos);
		im.deallocatePosProductToCallCenter(pos1);
		im.deallocatePosProductToCallCenter(pos2);
	}
	
	private void verifyAllocate(POSInfo pos){
		logger.info("Verify allocate status of POS:"+pos.product);
		
		//Check point 1: check allocate POS to Call Center
		im.allocatePosProductToCallCenter(pos);
		this.checkAllocateStatus(pos);
		
		//Check point 2: re-allocate that POS to Call Center
		if("Product Package".equalsIgnoreCase(pos.productRelactionshipType)) {
			expectMessage = "The following Product Package "+pos.product+" have already been allocated by the Warehouse "+warehouseName+".";
		} else {
			expectMessage = "The POS Product < "+pos.product+" > has already been allocated for the Call Center. Please unselect it.";
		}
		im.allocatePosProductToCallCenter(pos);
		this.verifyErrorMsg();
	}
}
