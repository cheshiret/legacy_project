/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.product;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo.BarCode;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo.PosSalesAttributes;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrMasterPosSearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrPosSetupDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: it is to check a workflow of editing a POS product: 
 * 1. Add a Master POS Product with No Inventory as Inventory Type, barcode and product sales attributes, and select specific revenue location
 * 2. Edit the POS, change the invetory type, barcode, product sales attributes, revenue location
 * 3. click OK, then check the edited POS product.   
 * @Preconditions:
 * 1. the role Administrator has the assigned features: SearchPOSProductSetup, AddPOSProduct, EditPOSProduct, DeactivatePOSProduct
 * 2. make sure the customer pass type 'Test Pass' has been added in MS contract by running sql:
 * insert into d_ref_cust_pass_type (id, cd, name, dscr, active_ind, duration_days, proof_req_type_id) values (2, 'CustomerType', 'Test Pass',  'Test Pass', 1,0,0);  
 * 3. make sure the product class 'Auto Test Class' and subclass 'Auto Test SubClass' have been added in MS contract by running sql:
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 4, 'Test Class' );  
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 5, 'Test SubClass' ); 
 * @SPEC: POS Setup [TC:032103] Step24~34, 36
 * @Task#: Auto-1210
 * 
 * @author Lesley Wang
 * @Date  Aug 22, 2012
 */
public class Edit_OK extends InventoryManagerTestCase {

	private InvMgrPosSetupDetailsPage posSetupDetailsPg = InvMgrPosSetupDetailsPage.getInstance();
	private InvMgrMasterPosSearchPage masterPosSetupPg = InvMgrMasterPosSearchPage.getInstance();
	
	public void execute() {
		// insert customer pass type, product class and product subclass into DB if they don't exist
		if (!im.isCustomerPassTypeExist(schema, "Test Pass")) {
			im.insertCustomerPassType(schema, "Test Pass");
		}
		if (!im.isProductClassExist(schema, pos.productClass)) {
			im.insertProductClass(schema, pos.productClass);
		}
		if (!im.isProductSubClassExist(schema, pos.productSubClass)) {
			im.insertProductSubClass(schema, pos.productSubClass);
		}
		
		im.loginInventoryManager(login);
		im.gotoMasterPosSetupPage();
		
		// Add a POS Product
		pos.productID = im.addMasterPosProduct(pos);
	
		// Edit the POS Product
		im.gotoMasterPOSDetailsPgFromListPg(pos.productID);
		POSInfo updatedPOS = this.updatePOSInfo(pos.productID, pos.productCode);
		im.setupPosInfo(updatedPOS);
		// Edit POS barcodes info
		updatedPOS.barcodeList = this.updatePOSBarcodeInfo();
		posSetupDetailsPg.removePOSBarcode(0);
		posSetupDetailsPg.setBarCode(updatedPOS.barcodeList.get(0).barCode, 0);
		posSetupDetailsPg.addPOSBarcode(updatedPOS.barcodeList.get(1).barCode, 1);
		// Edit POS Sales Attributes info
		updatedPOS.attributesArray = this.updatePOSSalesAttributesInfo();
		posSetupDetailsPg.removePOSSalesAttribute(0);
		posSetupDetailsPg.setPosSalesAttribute( updatedPOS.attributesArray.get(0), 0);
		posSetupDetailsPg.addPOSSalesAttribute(updatedPOS.attributesArray.get(1), 1);
		
		im.gotoMasterListPgFromPOSDetailsPg(true);
		masterPosSetupPg.searchPOSById(updatedPOS.productID);
		masterPosSetupPg.verifyPOSInList(updatedPOS);
		
		// Check the edited POS Product on POS Product Setup Details page
		im.gotoMasterPOSDetailsPgFromListPg(updatedPOS.productID);
		posSetupDetailsPg.verifyPOSInfo(updatedPOS);
		
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
		pos.productGroup = "Books";
		pos.inventoryType = NO_INVENTORY_TYPE;
		pos.productDescription = "AddPOSProduct";	
		pos.orderType = "POS Sale";
		pos.status = ACTIVE_STATUS;
		pos.productClass = "Auto Test Class";
		pos.productSubClass = "Auto Test SubClass";
		
		// POS barcode info
		POSInfo.BarCode barcode = pos.new BarCode();
		barcode.barCode = randomNum;
		pos.barcodeList.add(barcode);
		POSInfo.BarCode barcode2 = pos.new BarCode();
		barcode2.barCode = "Edit" + randomNum;
		pos.barcodeList.add(barcode2);
		
		// POS location info
		pos.availableLocation = "MSHF";
		pos.specificLocation = true;
		pos.revLocation.agency = "MSHF";
		pos.revLocation.region = "South Region";
		pos.revLocation.facility = "WOODSTOCK HUNTING CLUB(Store Loc)"; // 150355
		
		// POS Product Attribute info
	    POSInfo.PosSalesAttributes posAttrs = pos.new PosSalesAttributes();
	    posAttrs.attributes = "Customer Name";
	    posAttrs.displaySequence = "2";
	    posAttrs.mandatory = NO_STATUS;
	    posAttrs.active = YES_STATUS;
	    pos.attributesArray.add(posAttrs);
	    POSInfo.PosSalesAttributes posAttrs2 = pos.new PosSalesAttributes();
	    posAttrs2.attributes = "Notes";
	    posAttrs2.displaySequence = "1";
	    posAttrs2.mandatory = YES_STATUS;
	    posAttrs2.active = NO_STATUS;
	    pos.attributesArray.add(posAttrs2);
	}
	
	private POSInfo updatePOSInfo(String id, String code) {
		POSInfo posInfo = new POSInfo();
		String randomNum = String.valueOf(DateFunctions.getCurrentTime());
		posInfo.productID = id;
		posInfo.productCode = code;
		posInfo.product = "EditPOSProduct" + randomNum;
		posInfo.productGroup = "Bus Entrance";
		posInfo.inventoryType = SERIALIZED_INVENTORY_TYPE;
		posInfo.productDescription = "EditPOSProduct";	
		posInfo.orderType = "POS Sale";
		posInfo.status = ACTIVE_STATUS;
		posInfo.productClass = "";
		posInfo.productSubClass = "";
		posInfo.serilizType = "Customer Pass";
		posInfo.serilizReferenceId = "Test Pass";
		posInfo.serilizFormat = randomNum;
		posInfo.serilizIncrement = "2";
		posInfo.serilizRule = "Fixed Per Inventory";
		posInfo.serilizFormat = "123456";
		
		posInfo.acquierZipCodeInSale = NO_STATUS;
		posInfo.applicationCustomer = "Non General Public";
		posInfo.state = "Out-State";
		posInfo.numOfOccupants = "1";
		posInfo.vehicle = NO_STATUS;
		
		// POS location info
		posInfo.availableLocation = "MSHF";
		posInfo.specificLocation = true;
		posInfo.revLocation.agency = "MSHF";
		posInfo.revLocation.region = "Central Region";
		posInfo.revLocation.facility = "ZIPPIN(Store Loc)"; // 151543
		
		return posInfo;
	}
	
	private ArrayList<BarCode> updatePOSBarcodeInfo() {
		ArrayList<BarCode> barcodes = new ArrayList<BarCode>();
		String randomNum = String.valueOf(DateFunctions.getCurrentTime());
		POSInfo.BarCode barcode = pos.new BarCode();
		barcode.barCode = randomNum;
		barcodes.add(barcode);
		POSInfo.BarCode barcode2 = pos.new BarCode();
		barcode2.barCode = "Edit" + randomNum;
		barcodes.add(barcode2);
		return barcodes;
	}
	
	private ArrayList<PosSalesAttributes> updatePOSSalesAttributesInfo() {
		ArrayList<PosSalesAttributes> salesAttributes = new ArrayList<PosSalesAttributes>();
		PosSalesAttributes posAttrs = pos.new PosSalesAttributes();
		posAttrs.attributes = "Customer Name";
	    posAttrs.displaySequence = "1";
	    posAttrs.mandatory = NO_STATUS;
	    posAttrs.active = NO_STATUS;
	    salesAttributes.add(posAttrs);
	    POSInfo.PosSalesAttributes posAttrs2 = pos.new PosSalesAttributes();
	    posAttrs2.attributes = "Vehicle Plate";
	    posAttrs2.displaySequence = "3";
	    posAttrs2.mandatory = YES_STATUS;
	    posAttrs2.active = YES_STATUS;
	    salesAttributes.add(posAttrs2);    
		return salesAttributes;
	}
}
