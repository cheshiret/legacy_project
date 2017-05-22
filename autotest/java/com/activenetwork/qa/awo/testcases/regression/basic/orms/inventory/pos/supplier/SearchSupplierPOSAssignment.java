package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.supplier;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierProductAssignmentListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;

/**  
 * @Description: search pos supplier assignment.
 * @Preconditions: 
 * @SPEC: Search Supplier POS Assignment TC037522
 * @Task#: Auto-972.
 * @author jwang8  
 * @Date  Mar 230, 2012    
 */
public class SearchSupplierPOSAssignment extends InventoryManagerTestCase{
	 private POSInfo posInfo = new POSInfo();
	 private PosSupplier supplier = new PosSupplier();
	 private InvMgrPOSSupplierProductAssignmentListPage posSupplierSetupPg = InvMgrPOSSupplierProductAssignmentListPage.getInstance();
		
	
	public void execute() {
		//Add a POS product.
		im.loginInventoryManager(login);
		im.gotoPosProductSetupPage();
		posInfo.productID = im.addPOSProduct(posInfo);
		//Add a new POS supplier.
		im.gotoPosSupplierSearchPageFromTopMenu();
		supplier.id = im.addPosSupplier(supplier);
		//go to supplier product setup page.
		im.gotoPOSSupplierDetailsPage(supplier.id);
		im.gotoPosProductSupplierSetupPage();
		//Search POS by POS name.
		posSupplierSetupPg.searchPosByName(posInfo.product);
		//assign the POS product.
		posSupplierSetupPg.assignSelectedPOSProductToSupplier(posInfo.productID,posInfo.unitCost,posInfo.supplierProductCode);
		//Search and verify.
		this.verifySearchPosSupplierAssignmentResult();
		im.logoutInvManager();
	}
	
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/AutoWarehouse";
		
		posInfo.orderType = "POS Sale";
		posInfo.product = "Assign" + DataBaseFunctions.getEmailSequence();
		posInfo.productCode = "" + DataBaseFunctions.getEmailSequence();
		posInfo.productGroup = "Agent Fees - Daily";
		posInfo.inventoryType = "Non-Restrictive Inventory";
		posInfo.productDescription = "AutoTest";
		posInfo.assignToSupplier = "Yes";
		posInfo.searchByAssignStatus = "Assigned";
		posInfo.unitCost = "5";
		posInfo.supplierProductCode = ""+DataBaseFunctions.getEmailSequence();
		
		supplier.location = "All Agencies";
		supplier.name = "AddSupp" + DataBaseFunctions.getEmailSequence();
		supplier.description = "AutoTest";
		supplier.orderAddress.address = "ShanXi";
		supplier.orderAddress.city = "ShangLuo";
		supplier.orderAddress.state = "Alabama";
		supplier.orderAddress.zip = "12345";
		supplier.orderAddress.country = "United States";
		supplier.isPaymentAddrSameOrderAddr = true;
	}
	
	/**
	 * verify pos supplier assignment search result.
	 */
	public void verifySearchPosSupplierAssignmentResult(){
		
		InvMgrPOSSupplierProductAssignmentListPage productSupplerSetupPg = InvMgrPOSSupplierProductAssignmentListPage.getInstance();
		productSupplerSetupPg.setSearchCriteria(posInfo);
		productSupplerSetupPg.verifyPosSupplierAssignmentSearchResult(posInfo); 
		
		productSupplerSetupPg.cleanUpSearchCriteria();
		POSInfo searchPosInfo = new POSInfo();
		searchPosInfo.product = posInfo.product;
		productSupplerSetupPg.setSearchCriteria(searchPosInfo);
		productSupplerSetupPg.verifyPosSupplierAssignmentSearchResult(searchPosInfo);
		
		productSupplerSetupPg.cleanUpSearchCriteria();
		searchPosInfo.product = "";
		searchPosInfo.productGroup = posInfo.productGroup;
		productSupplerSetupPg.setSearchCriteria(searchPosInfo);
		productSupplerSetupPg.verifyPosSupplierAssignmentSearchResult(searchPosInfo);
		
		productSupplerSetupPg.cleanUpSearchCriteria();
		searchPosInfo.productGroup = "";
		searchPosInfo.supplierProductCode = posInfo.supplierProductCode;
		productSupplerSetupPg.setSearchCriteria(searchPosInfo);
		productSupplerSetupPg.verifyPosSupplierAssignmentSearchResult(searchPosInfo);
		
		productSupplerSetupPg.cleanUpSearchCriteria();
		searchPosInfo.searchByAssignStatus = "Assigned";
		searchPosInfo.assignToSupplier = "Yes";
		productSupplerSetupPg.setSearchCriteria(searchPosInfo);
		productSupplerSetupPg.verifyPosSupplierAssignmentSearchResult(searchPosInfo);
	}
}
