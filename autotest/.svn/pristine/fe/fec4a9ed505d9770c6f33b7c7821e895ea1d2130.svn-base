package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.supplier;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: verify Activate/Deactivate POS Supplier function works correctly on agency level
 * @Preconditions: agency - 'MSHF' has been assigned to role - 'Administrator'
 * @SPEC: Activate/Deactivate POS Supplier TC037519
 * @Task#: AUTO-1140
 * 
 * @author qchen
 * @Date  Jul 24, 2012
 */
public class ActivateAndDeactivateOnAgencyLevel extends InventoryManagerTestCase {
	private PosSupplier supplier = new PosSupplier();
	private InvMgrPOSSupplierSearchPage supplierSearchPage = InvMgrPOSSupplierSearchPage.getInstance();
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoPosSupplierSearchPageFromTopMenu();
		//add a POS Supplier
		supplier.id = im.addPosSupplier(supplier);
		
		//verify the initially status is Active
		supplierSearchPage.searchPosSupplierById(supplier.id);
		supplierSearchPage.verifyPosSupplierStatus(supplier.id, OrmsConstants.ACTIVE_STATUS);
		
		//deactivate this POS Supplier and verify its status has been changed to Inactive
		im.deactivatePOSSupplier(supplier.id);
		supplierSearchPage.verifyPosSupplierStatus(supplier.id, OrmsConstants.INACTIVE_STATUS);
		
		//activate this POS Supplier and verify its status has been changed back Active
		im.activatePOSSupplier(supplier.id);
		supplierSearchPage.verifyPosSupplierStatus(supplier.id, OrmsConstants.ACTIVE_STATUS);
		
		//clean up
		im.deactivatePOSSupplier(supplier.id);
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/MSHF";
		
		supplier.status = OrmsConstants.ACTIVE_STATUS;
		supplier.location = "MSHF";
		supplier.name = this.caseName + DateFunctions.getCurrentTime();
		supplier.description = "Automation Regression Test";
		supplier.orderAddress.address = "West A Street";
		supplier.orderAddress.city = "Xian";
		supplier.orderAddress.state = "Mississippi";
		supplier.orderAddress.zip = "36159";
		supplier.orderAddress.country = "United States";
		supplier.isPaymentAddrSameOrderAddr = true;
	}

}
