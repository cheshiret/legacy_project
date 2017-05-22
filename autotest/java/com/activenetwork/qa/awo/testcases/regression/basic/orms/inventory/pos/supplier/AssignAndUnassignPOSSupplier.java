package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.supplier;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;

/**  
 * @Description: assign and unassign Pos supplier to warehouse
 * @Preconditions:  Assign 
 * @SPEC:  
 * @Task#: Auto-972
 * @author jwang8  
 * @Date  Mar 29, 2012   
 */
public class AssignAndUnassignPOSSupplier extends InventoryManagerTestCase{
    private PosSupplier supplier = new PosSupplier();
	private InvMgrPOSSupplierSearchPage posSupplierSearchPg = InvMgrPOSSupplierSearchPage.getInstance();
	
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoPosSupplierSearchPageFromTopMenu();
		//Add a new pos suppler.
		supplier.id =im.addPosSupplier(supplier);
		posSupplierSearchPg.searchPosSupplierById(supplier.id);
		posSupplierSearchPg.verifyPosSupplierAssignValue(supplier.id, supplier.assigned);
		
		//Unassign POS supplier.
		im.unassignPOSSupplier(supplier.id);
		supplier.assigned = false;
		posSupplierSearchPg.verifyPosSupplierAssignValue(supplier.id, supplier.assigned);
		//Assign POS Supplier
		posSupplierSearchPg.assignPosSupplier(supplier.id);
		supplier.assigned = true;
		posSupplierSearchPg.verifyPosSupplierAssignValue(supplier.id, supplier.assigned);
		im.logoutInvManager();
	}

	
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/AutoWarehouse";
		
		supplier.location = "All Agencies";
		supplier.name = "AddSupp" + DataBaseFunctions.getEmailSequence();
		supplier.description = "AutoTest";
		supplier.orderAddress.address = "ShanXi";
		supplier.orderAddress.city = "ShangLuo";
		supplier.orderAddress.state = "Alaska";
		supplier.orderAddress.zip = "12345";
		supplier.orderAddress.country = "United States";
		supplier.isPaymentAddrSameOrderAddr = true;
		supplier.assigned= true;
	}

}
