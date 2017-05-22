package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.supplier;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierProductAssignmentListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
/**  
 * @Description: assign pos and unassign pos to specific ware house.
 * @Preconditions:  Assign 
 * @SPEC: Assign/Unassign POS Product to Supplier TC037520
 * @Task#: Auto-972
 * @author jwang8  
 * @Date  Mar 29, 2012   
 */
public class AssignAndUnassignPOS extends InventoryManagerTestCase{
    private POSInfo posInfo = new POSInfo();
    private PosSupplier supplier = new PosSupplier();
    private POSInfo posInfo1 = new POSInfo();
    private InvMgrPOSSupplierProductAssignmentListPage posSupplierSetupPg = InvMgrPOSSupplierProductAssignmentListPage.getInstance();
	
	public void execute() {
		im.loginInventoryManager(login);
		//Add a POS product.
		im.gotoPosProductSetupPage();
		posInfo1.productID=im.addPOSProduct(posInfo1);
		posInfo.productID=im.addPOSProduct(posInfo);
		//Add a new POS supplier.
		im.gotoPosSupplierSearchPageFromTopMenu();
		supplier.id = im.addPosSupplier(supplier);
		//go to supplier product setup page.
		im.gotoPOSSupplierDetailsPage(supplier.id);
		im.gotoPosProductSupplierSetupPage();
		//Search POS by POS name.
		posSupplierSetupPg.searchPosByName(posInfo1.product);
		//Check the assigned value of specific POS.
		posSupplierSetupPg.verifyPosAssignedStatus(posInfo1.productID, posInfo1.assignToSupplier);
		//assign the POS product.
		posSupplierSetupPg.assignSelectedPOSProductToSupplier(posInfo1.productID, posInfo1.unitCost,posInfo1.supplierProductCode);
		
		posInfo1.assignToSupplier = "Yes";
		//Verify the POS assigned value.
		posSupplierSetupPg.verifyPosAssignedStatus(posInfo1.productID, posInfo1.assignToSupplier);
		posSupplierSetupPg.assignSelectedPOSProductToSupplier(posInfo1.productID, posInfo1.unitCost,posInfo1.supplierProductCode);
		
		posSupplierSetupPg.searchPosByName(posInfo.product);
		posSupplierSetupPg.verifyPosAssignedStatus(posInfo.productID, posInfo.assignToSupplier);
		
		posSupplierSetupPg.assignSelectedPOSProductToSupplier(posInfo.productID, posInfo.unitCost,posInfo.supplierProductCode);
		
		
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
		POSInfo.BarCode bCode = posInfo.new BarCode();;
		bCode.barCode = "" +DataBaseFunctions.getEmailSequence();
		bCode.isRemove = false;
		posInfo.barcodeList.add(bCode);
		posInfo.unitCost = "5.0";
		posInfo.supplierProductCode = "+DataBaseFunctions.getEmailSequence()";
		
		posInfo1.orderType = "POS Sale";
		posInfo1.product = "Assign" + DataBaseFunctions.getEmailSequence();
		posInfo1.productGroup = "Agent Fees - Daily";
		posInfo1.inventoryType = "Non-Restrictive Inventory";
		posInfo1.productDescription = "AutoTest";
		posInfo1.assignToSupplier = "No";
		POSInfo.BarCode bCode1 = posInfo.new BarCode();;
		bCode1.barCode = "" +DataBaseFunctions.getEmailSequence();
		bCode1.isRemove = false;
		posInfo1.barcodeList.add(bCode1);
		posInfo1.unitCost = "5.0";
		posInfo1.supplierProductCode = "+DataBaseFunctions.getEmailSequence()";
		
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

}
