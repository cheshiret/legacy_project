package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.supplier;

import java.util.Random;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierProductAssignmentListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: verify function of searching Supplier-POS Assignment 
 * @Preconditions: warehouse - 'AutoWarehouse' exists
 * @SPEC: Search Supplier POS Assignment TC037522
 * @Task#: AUTO-1140
 * 
 * @author qchen
 * @Date  Jul 26, 2012
 */
public class SearchSupplierPOSAssignmentWithinWarehouse extends InventoryManagerTestCase {

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
		
		//verify searching result
		this.verifySearchPosSupplierAssignmentResult(pos);
		
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
		supplier.orderAddress.address = "Middle A Street";
		supplier.orderAddress.city = "Xian";
		supplier.orderAddress.state = "Mississippi";
		supplier.orderAddress.zip = "36100";
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
		pos.supplierProductCode = String.valueOf(new Random().nextInt(10000));
	}

	/**
	 * verify searching Supplier-POS Assignment result correctly
	 * @param p
	 */
	public void verifySearchPosSupplierAssignmentResult(POSInfo p) {
		logger.info("Verify Supplier-POS Assignment searching result.");
		
		//all searching criteria
		p.searchByAssignStatus = p.assignToSupplier.equals(OrmsConstants.YES_STATUS) ? "Assigned" : "Unassigned";
		supplierPOSPage.searchSupplierPOSAssignment(p);
		supplierPOSPage.verifyPosSupplierAssignmentSearchResult(p); 
		
		POSInfo searchPosInfo = new POSInfo();
		//search by assignment status
		supplierPOSPage.cleanUpSearchCriteria();
		searchPosInfo.searchByAssignStatus = p.assignToSupplier.equals(OrmsConstants.YES_STATUS) ? "Assigned" : "Unassigned";
		supplierPOSPage.searchSupplierPOSAssignment(searchPosInfo);
		supplierPOSPage.verifyPosSupplierAssignmentSearchResult(searchPosInfo);
		
		//search by product name
		supplierPOSPage.cleanUpSearchCriteria();
		searchPosInfo.searchByAssignStatus = "";
		searchPosInfo.product = p.product;
		supplierPOSPage.searchSupplierPOSAssignment(searchPosInfo);
		supplierPOSPage.verifyPosSupplierAssignmentSearchResult(searchPosInfo);
		
		//search by product group
		supplierPOSPage.cleanUpSearchCriteria();
		searchPosInfo.product = "";
		searchPosInfo.productGroup = p.productGroup;
		supplierPOSPage.searchSupplierPOSAssignment(searchPosInfo);
		supplierPOSPage.verifyPosSupplierAssignmentSearchResult(searchPosInfo);
		
		//search by supplier product code
		supplierPOSPage.cleanUpSearchCriteria();
		searchPosInfo.productGroup = "";
		searchPosInfo.supplierProductCode = p.supplierProductCode;
		supplierPOSPage.searchSupplierPOSAssignment(searchPosInfo);
		supplierPOSPage.verifyPosSupplierAssignmentSearchResult(searchPosInfo);
	}
}
