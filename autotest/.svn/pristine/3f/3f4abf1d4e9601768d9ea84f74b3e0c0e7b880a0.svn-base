package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.pos.supplier.inventory.assignPosToSupplier;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierProductAssignmentListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
/**  
 * @Description: Verify error message when assign POS to supplier..
 * @Preconditions:  .
 * @SPEC:  assign POS product to supplier
 * @Task#: Auto-972.
 * @author jwang8  
 * @Date  Apr 24, 2012    
 */
public class VerifyErrorMessage extends InventoryManagerTestCase {
	private POSInfo posInfo = new POSInfo();
	private POSInfo posInfo1 = new POSInfo();
    private PosSupplier supplier = new PosSupplier();
    private String posName = "Assign" + DataBaseFunctions.getEmailSequence();
    private String posName1 = "Assign1" + DataBaseFunctions.getEmailSequence();
    private String posProductCode1 = ""+DataBaseFunctions.getEmailSequence();
    private InvMgrPOSSupplierProductAssignmentListPage posSupplierSetupPg = InvMgrPOSSupplierProductAssignmentListPage.getInstance();
    private boolean runingResult = true;
    private String errorMsg_DuplicateAssign = "Product "+posName+" selected has already been assigned to your Supplier. Please unselect it.";
    private String errorMsg_withoutUnitCost = "The Supplier Unit Cost for "+posName+" must have a value greater than $0.00. Please change your entries.";
    private String errorMsg_DupilicateProductCode = "The existing Product, "+posName1+", already has the specified Supplier Product Code, "+posProductCode1+". Please change your entry. ";
    
	public void execute() {
		im.loginInventoryManager(login);
		//Add a POS product.
		im.gotoPosProductSetupPage();
		posInfo1.productID = im.addPOSProduct(posInfo1);
		posInfo.productID = im.addPOSProduct(posInfo);
		//Add a new POS supplier.
		im.gotoPosSupplierSearchPageFromTopMenu();
		supplier.id = im.addPosSupplier(supplier);
		//go to supplier product setup page.
		im.gotoPOSSupplierDetailsPage(supplier.id);
		im.gotoPosProductSupplierSetupPage();
		//Search POS by POS name.
		posSupplierSetupPg.searchPosByName(posInfo1.product);
		//Check the assigned value of specific POS.
		//posSupplierSetupPg.verifyPosAssignedValue(posInfo1.prodID, posInfo1.assigntoSupplier);
		//assign the POS product.
		posSupplierSetupPg.assignSelectedPOSProductToSupplier(posInfo1.productID, posInfo1.unitCost,posInfo1.supplierProductCode);
	
		//Verify The <<Supplier>> Unit Cost is not a number greater than 0. 
		posInfo.unitCost = "";
		posSupplierSetupPg.searchPosByName(posInfo.product);
		posSupplierSetupPg.assignSelectedPOSProductToSupplier(posInfo.productID, posInfo.unitCost,posInfo.supplierProductCode);
		runingResult &= this.verifyErrorMessage(errorMsg_withoutUnitCost);
		//Verify the error message The <<Supplier>> Product Code is not unique for the selected <<Supplier>> within the known location.
		posInfo.unitCost = "2.0";
		posInfo.supplierProductCode = posInfo1.supplierProductCode;
		posSupplierSetupPg.assignSelectedPOSProductToSupplier(posInfo.productID, posInfo.unitCost,posInfo.supplierProductCode);
		runingResult &= this.verifyErrorMessage(errorMsg_DupilicateProductCode);
		
		//Assign the POS to supplier success.
		posInfo.supplierProductCode = ""+DataBaseFunctions.getEmailSequence();
		posSupplierSetupPg.assignSelectedPOSProductToSupplier(posInfo.productID, posInfo.unitCost,posInfo.supplierProductCode);
		
		//Veiry the error message The POS Product already has an Active Assignment to the known <<Supplier>>. 
		posSupplierSetupPg.assignSelectedPOSProductToSupplier(posInfo.productID, posInfo.unitCost,posInfo.supplierProductCode);
		runingResult &= this.verifyErrorMessage(errorMsg_DuplicateAssign);
		//TODO
		//Need to verify the error message4(The Product <product name> selected is not assigned to your location. Please unselect it.)
		
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/AutoWarehouse";
		
		posInfo.orderType = "POS Sale";
		posInfo.product = posName;
		posInfo.productGroup = "Agent Fees - Daily";
		posInfo.inventoryType = "Non-Restrictive Inventory";
		posInfo.productDescription = "AutoTest";
		posInfo.assignToSupplier = "No";
		POSInfo.BarCode bCode = posInfo.new BarCode();;
		bCode.barCode = "" +DataBaseFunctions.getEmailSequence();
		bCode.isRemove = false;
		posInfo.barcodeList.add(bCode);
		posInfo.unitCost = "5.0";
		posInfo.supplierProductCode = ""+DataBaseFunctions.getEmailSequence();
		
		posInfo1.orderType = "POS Sale";
		posInfo1.product = posName1;
		posInfo1.productGroup = "Agent Fees - Daily";
		posInfo1.inventoryType = "Non-Restrictive Inventory";
		posInfo1.productDescription = "AutoTest";
		posInfo1.assignToSupplier = "No";
		POSInfo.BarCode bCode1 = posInfo.new BarCode();;
		bCode1.barCode = "" +DataBaseFunctions.getEmailSequence();
		bCode1.isRemove = false;
		posInfo1.barcodeList.add(bCode1);
		posInfo1.unitCost = "5.0";
		posInfo1.supplierProductCode = posProductCode1;
		
		
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
	
	 /* Verify error message.
	 * @param expectedMsg - expected message.
	 * @param actualMsg- actual message.
	 * @return
	 */
	private boolean verifyErrorMessage(String expectedMsg){
		boolean pass = true;
		String actualMsg = posSupplierSetupPg.getErrorMessage();
		logger.info("Verify the display warning message are corrected");
		if(!expectedMsg.trim().equals(actualMsg.trim())){
			logger.error("Actual message doesn't match the expected. Actual message is :" + actualMsg + ", but the expected is :" + expectedMsg);
		    pass &= false;
		}else{
			logger.info("Error message - '" + actualMsg + "' is displayed correctly.");
		}
		return pass;
	}
}
