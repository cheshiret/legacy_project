package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.supplier;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierProductAssignmentDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierProductAssignmentListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**  
 * @Description: edit POS supplier assignment.
 * @Preconditions: 
 * @SPEC: Edit Supplier POS Product Assignment TC037516
 * @Task#: Auto-972.
 * @author jwang8  
 * @Date  Mar 30, 2012    
 */
public class EditSupplierPOSAssigment extends InventoryManagerTestCase{
	 private POSInfo posInfo = new POSInfo();
	 private PosSupplier supplier = new PosSupplier();
	 private InvMgrPOSSupplierProductAssignmentListPage posSupplierSetupPg = InvMgrPOSSupplierProductAssignmentListPage.getInstance();
	 private InvMgrPOSSupplierProductAssignmentDetailsPage posSupplierAssignmentPg = InvMgrPOSSupplierProductAssignmentDetailsPage.getInstance();
	
	public void execute() {
		im.loginInventoryManager(login);
		//Add a POS product.
		im.gotoPosProductSetupPage();
		posInfo.productID = im.addPOSProduct(posInfo);
		//Add a new POS supplier.
		im.gotoPosSupplierSearchPageFromTopMenu();
		supplier.id =im.addPosSupplier(supplier);
		//go to supplier product setup page.
		im.gotoPOSSupplierDetailsPage(supplier.id);
		im.gotoPosProductSupplierSetupPage();
		//Search POS by POS name.
		posSupplierSetupPg.searchPosByName(posInfo.product);
		//assign the POS product.
		posSupplierSetupPg.assignSelectedPOSProductToSupplier(posInfo.productID,posInfo.unitCost,posInfo.supplierProductCode);
		//edit pos supplier product.
		im.gotoSupplierPOSAssignmentDetailsPage(posInfo.productID);
		
		posInfo.supplierProductCode ="" +DataBaseFunctions.getEmailSequence();
		posInfo.unitCost = "3.12";
		im.editSupplierPOSAssignment(posInfo.supplierProductCode, posInfo.unitCost);
		
		//Go to pos supplier product details page and verify the edit info.
		posSupplierSetupPg.searchBySupplierProductCode(posInfo.supplierProductCode);
		//Verify list info.
		posInfo.assignToSupplier="Yes";
		this.verifyPosSupplierAssignmentListResult(posInfo);
		im.gotoSupplierPOSAssignmentDetailsPage(posInfo.productID);
		//Verify POS supplier assignment result info.
		this.verifyPosSupplierAssignmentResult(posInfo);
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/AutoWarehouse";
		
		posInfo.orderType = "POS Sale";
		posInfo.product = "Assign" + DataBaseFunctions.getEmailSequence();
		posInfo.productGroup = "Agent Fees - Daily";
		posInfo.inventoryType = "Non-Restrictive Inventory";
		posInfo.productDescription = "AutoTest";
		posInfo.assignToSupplier = "No";
		posInfo.supplierProductCode = "" + DataBaseFunctions.getEmailSequence();
		posInfo.unitCost = "2.0";
		
		
		supplier.location = "All Agencies";
		supplier.name = "AddSupp" + DataBaseFunctions.getEmailSequence();
		supplier.description = "AutoTest";
		supplier.orderAddress.address = "ShanXi";
		supplier.orderAddress.city = "ShangLuo";
		supplier.orderAddress.state = "Alaska";
		supplier.orderAddress.zip = "12345";
		supplier.orderAddress.country = "United States";
		supplier.isPaymentAddrSameOrderAddr = true;
	}
	/**
	 * verify pos supplier assignment result.
	 * @param posInfo
	 */
	public void verifyPosSupplierAssignmentResult(POSInfo posInfo){
		boolean pass = posSupplierAssignmentPg.comparePOSAssignmentEditInfo(posInfo.supplierProductCode, posInfo.unitCost);
		if(!pass){
			 throw new ErrorOnPageException("edit pos supplier product assignment error");
		}else{
			logger.info("edit pos supplier product assignment successful");
		}
	}
	
	/**
	 * verify POS supplier assignment list result.
	 * @param posInfo
	 */
	public void verifyPosSupplierAssignmentListResult(POSInfo posInfo){
		boolean pass = posSupplierSetupPg.compareSupplierPOSAssignmentListInfo(posInfo);
		if(!pass){
			throw new ErrorOnPageException("edit pos supplier product assignment list info error");
		}else{
			logger.info("edit pos supplier product assignment list info successful");
		}
	}
	

}
