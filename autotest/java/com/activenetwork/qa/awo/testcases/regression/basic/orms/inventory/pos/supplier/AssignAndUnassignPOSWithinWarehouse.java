package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.supplier;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierProductAssignmentListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: verify assign/un-assign POS to POS Supplier successfully
 * @Preconditions: an existing warehouse - AutoWarehouse
 * 							if there is production data, use a production supplier
 * @SPEC: Assign/Unassign POS Product to Supplier TC037520
 * @Task#: AUTO-1140
 * 
 * @author qchen
 * @Date  Jul 25, 2012
 */
public class AssignAndUnassignPOSWithinWarehouse extends InventoryManagerTestCase {
	private String warehouseName;
	private POSInfo pos = new POSInfo();
	private PosSupplier supplier = new PosSupplier();
	private InvMgrPOSSupplierProductAssignmentListPage posSupplierPage = InvMgrPOSSupplierProductAssignmentListPage.getInstance();
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoWarehousesSearchPg();
		im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
		//add POS product
		im.gotoPosProductSetupPage();
		pos.productID = im.addPOSProduct(pos);//this POS product has been automatically assigned to current warehouse
		
		//add POS Supplier - TODO if there will be existing production data, just use the existing supplier
		im.gotoPosSupplierSearchPageFromTopMenu();
		supplier.id = im.addPosSupplier(supplier);
		
		//goto POS 'Product-Supplier Setup' tab
		im.gotoPOSSupplierDetailsPage(supplier.id);
		im.gotoPosProductSupplierSetupPage();
		
		//before assigning, verify the assigned status should be NO
		pos.assignToSupplier = OrmsConstants.NO_STATUS;
		posSupplierPage.searchPosByName(pos.product);
		posSupplierPage.verifyPosAssignedStatus(pos.productID, pos.assignToSupplier);
		//assign POS product to POS Supplier
		posSupplierPage.assignSelectedPOSProductToSupplier(pos.productID, pos.unitCost, pos.supplierProductCode);
		pos.assignToSupplier = OrmsConstants.YES_STATUS;
		posSupplierPage.verifyPosAssignedStatus(pos.productID, pos.assignToSupplier);
		
		//un-assign POS product from POS Supplier
		posSupplierPage.unassignSelectedPosProduct(pos.productID);
		pos.assignToSupplier = OrmsConstants.NO_STATUS;
		posSupplierPage.verifyPosAssignedStatus(pos.productID, pos.assignToSupplier);
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		warehouseName = "AutoWarehouse";
		
		supplier.status = OrmsConstants.ACTIVE_STATUS;
		supplier.location = "All Agencies";
		supplier.name = this.caseName + DateFunctions.getCurrentTime();
		supplier.description = "Automation Regression Test";
		supplier.orderAddress.address = "East A Street";
		supplier.orderAddress.city = "Xian";
		supplier.orderAddress.state = "Mississippi";
		supplier.orderAddress.zip = "36159";
		supplier.orderAddress.country = "United States";
		supplier.isPaymentAddrSameOrderAddr = true;
		
		pos.orderType = "POS Sale";
		pos.product = this.caseName + DateFunctions.getCurrentTime();
		pos.productGroup = "Agent Fees - Daily";
		pos.inventoryType = "Non-Restrictive Inventory";
		pos.productDescription = "Automation Regression Test";
		pos.availableLocation = "All Agencies";
		pos.acquierZipCodeInSale = OrmsConstants.YES_STATUS;
		pos.applicationCustomer = "All";
		pos.unitCost = "9.98";
	}
}
