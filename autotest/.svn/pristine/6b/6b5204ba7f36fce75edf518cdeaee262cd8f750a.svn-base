/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.product;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrMasterPosSearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrPosSetupDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: it is to check a workflow of adding a POS product: 
 * 1. Add a Master POS Product, select Serialized Inventory as Inventory Type
 * 2. Add barcode and product attribute, then remove some
 * 3. click OK, then check the added POS product.   
 * @Preconditions:
 * 1. the role Administrator has the assigned features: SearchPOSProductSetup, AddPOSProduct, EditPOSProduct, DeactivatePOSProduct
 * 2. make sure the customer pass type 'Test Pass' has been added in MS contract by running sql:
 * insert into d_ref_cust_pass_type (id, cd, name, dscr, active_ind, duration_days, proof_req_type_id) values (2, 'CustomerType', 'Test Pass',  'Test Pass', 1,0,0);  
 * 3. make sure the product class 'Auto Test Class' and subclass 'Auto Test SubClass' have been added in MS contract by running sql:
 * insert into x_prop (id, name, namespace, type, value ) values( get_sequence('x_prop'), 'ShowProductClass', 'Contract', 'Boolean', 'true' );
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 4, 'Test Class' );  
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 5, 'Test SubClass' ); 
 * @SPEC: POS Setup [TC:032103] Step37, 40~49, 51~52
 * @Task#: Auto-1210
 * 
 * @author Lesley Wang
 * @Date  Aug 20, 2012
 */
public class Add_OK extends InventoryManagerTestCase{
	private InvMgrPosSetupDetailsPage posSetupDetailsPg = InvMgrPosSetupDetailsPage.getInstance();
	private InvMgrMasterPosSearchPage masterPosSetupPg = InvMgrMasterPosSearchPage.getInstance();
	
	public void execute() {
		// insert customer pass type, product class and product subclass into DB if they don't exist
		if (!im.isCustomerPassTypeExist(schema, pos.serilizReferenceId)) {
			im.insertCustomerPassType(schema, pos.serilizReferenceId);
		}
		if (!im.isProductClassExist(schema, pos.productClass)) {
			im.insertProductClass(schema, pos.productClass);
		}
		if (!im.isProductSubClassExist(schema, pos.productSubClass)) {
			im.insertProductSubClass(schema, pos.productSubClass);
		}
		
		im.loginInventoryManager(login);
		im.gotoMasterPosSetupPage();
		
		// Add a POS Product with serialized inventory, barcode and product attribute
		pos.productID = im.addMasterPosProduct(pos);
		
		// Search the POS Product
		masterPosSetupPg.searchPOSById(pos.productID);
		masterPosSetupPg.verifyPOSInList(pos);
		
		// Check the added POS Product on POS Product Setup Details page
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
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		// POS info
		String randomNum = String.valueOf(DateFunctions.getCurrentTime());
		pos.productCode = randomNum;
		pos.product = "AddPOSProduct" + randomNum;
		pos.productGroup = "Bus Entrance";
		pos.productClass = "Auto Test Class";
		pos.productSubClass = "Auto Test SubClass";
		pos.inventoryType = SERIALIZED_INVENTORY_TYPE;
		pos.productDescription = "AddPOSProduct";
		pos.serilizType = "Customer Pass";
		pos.serilizReferenceId = "Test Pass";
		pos.serilizFormat = randomNum;
		pos.serilizIncrement = "1";
		pos.serilizRule = "Manual";
		pos.orderType = "POS Sale";
		pos.status = ACTIVE_STATUS;
		pos.acquierZipCodeInSale = YES_STATUS;
		pos.applicationCustomer = ALL_STATUS;
		pos.state = "In-State";
		pos.numOfOccupants = "2";
		pos.vehicle = YES_STATUS;
		
		// POS barcode info
		POSInfo.BarCode barcode = pos.new BarCode();
		barcode.barCode = randomNum;
		pos.barcodeList.add(barcode);
		POSInfo.BarCode barcode2 = pos.new BarCode();
		barcode2.barCode = "Add" + randomNum;
		barcode2.isRemove = true;
		pos.barcodeList.add(barcode2);
		
		// POS location info
		pos.availableLocation = "All Agencies";
		pos.specificLocation = true;
		pos.revLocation.agency = "MSHF";
		pos.revLocation.region = "South Region";
		pos.revLocation.facility = "WOODSTOCK HUNTING CLUB(Store Loc)"; // 150355
		
		// POS Product Attribute info
	    POSInfo.PosSalesAttributes posAttrs = pos.new PosSalesAttributes();
	    posAttrs.attributes = "Customer Name";
	    posAttrs.displaySequence = "1";
	    posAttrs.mandatory = YES_STATUS;
	    posAttrs.active = YES_STATUS;
	    pos.attributesArray.add(posAttrs);
	    POSInfo.PosSalesAttributes posAttrs2 = pos.new PosSalesAttributes();
	    posAttrs2.attributes = "Notes";
	    posAttrs2.displaySequence = "2";
	    posAttrs2.mandatory = NO_STATUS;
	    posAttrs2.active = NO_STATUS;
	    posAttrs2.isRemove = true;
	    pos.attributesArray.add(posAttrs2);
	}
}
