package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.supplier;

import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierProductAssignmentDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierProductAssignmentListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: verify editing Supplier-POS assignment info(supplier product code, supplier unit cost) successfully within warehouse level
 * @Preconditions:
 * @SPEC: Edit Supplier POS Product Assignment TC037516
 * @Task#: AUTO-1140
 * 
 * @author qchen
 * @Date  Jul 25, 2012
 */
public class EditSupplierPOSAssignmentWithinWarehouse extends InventoryManagerTestCase {

	private String warehouseName;
	private POSInfo pos = new POSInfo();
	private PosSupplier supplier = new PosSupplier();
	private InvMgrPOSSupplierProductAssignmentListPage supplierPOSPage = InvMgrPOSSupplierProductAssignmentListPage.getInstance();
	
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
		
		//assign POS to Supplier
		im.gotoPOSSupplierDetailsPage(supplier.id);
		im.gotoPosProductSupplierSetupPage();
		supplierPOSPage.searchPosByName(pos.product);
		supplierPOSPage.assignSelectedPOSProductToSupplier(pos.productID, pos.unitCost, pos.supplierProductCode);
		
		//edit Supplier-POS assignment info - supplier product code, supplier unit cost
		im.gotoSupplierPOSAssignmentDetailsPage(pos.productID);
		this.initialEditingAssignmentInfo();
		im.editSupplierPOSAssignment(pos.supplierProductCode, pos.unitCost);
		supplierPOSPage.searchPosByName(pos.product);
		this.verifySupplierPOSAssignmentListInfo(pos);
		im.gotoSupplierPOSAssignmentDetailsPage(pos.productID);
		this.verifySupplierPOSAssignmentDetailInfo(supplier.id, supplier.name, pos);
		
		//clean up
		im.gotoSupplierPOSAssignmentListPageFromDetailsPage();
		supplierPOSPage.searchPosByName(pos.product);
		supplierPOSPage.unassignSelectedPosProduct(pos.productID);
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
		
		pos.assignToSupplier = OrmsConstants.YES_STATUS;
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

	private void initialEditingAssignmentInfo() {
		pos.supplierProductCode = String.valueOf(new Random().nextInt(100000));
		pos.unitCost = "19.99";
	}
	
	private void verifySupplierPOSAssignmentListInfo(POSInfo p) {
		logger.info("Verify Supplier POS Assignment list info correct or not.");
		boolean result = supplierPOSPage.compareSupplierPOSAssignmentListInfo(p);
		if(!result) {
			throw new ErrorOnPageException("Supplier POS Assignment list info is incorrect.");
		} else logger.info("Supplier POS Assignment list info is correct.");
	}
	
	private void verifySupplierPOSAssignmentDetailInfo(String id, String name, POSInfo p) {
		InvMgrPOSSupplierProductAssignmentDetailsPage detailPage = InvMgrPOSSupplierProductAssignmentDetailsPage.getInstance();
		
		logger.info("Verify Supplier POS Assignment detail info correct or not.");
		boolean result = detailPage.compareSupplierPOSAssignmentDetailsInfo(id, name, p);
		if(!result) {
			throw new ErrorOnPageException("Supplier POS Assignment detail info is incorrect.");
		} else logger.info("Supplier POS Assignment detail info is correct.");
	}
}
