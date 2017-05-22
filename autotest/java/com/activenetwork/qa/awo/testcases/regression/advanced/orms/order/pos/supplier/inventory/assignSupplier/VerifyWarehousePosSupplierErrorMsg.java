package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.pos.supplier.inventory.assignSupplier;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**  
 * @Description: Verify assign warehouse supplier error message.
 * @Preconditions:  assign AddPOSSupplier feature to role.
 * @SPEC: assign warehouse POS supplier.
 * @Task#: Auto-972.
 * @author jwang8  
 * @Date  Apr 19, 2012    
 */
public class VerifyWarehousePosSupplierErrorMsg extends InventoryManagerTestCase{

	private PosSupplier supplier = new PosSupplier();
	private InvMgrPOSSupplierSearchPage posSupplierSearchPg = InvMgrPOSSupplierSearchPage.getInstance();
	private String supplierName = "AddSupp" + DataBaseFunctions.getEmailSequence();
	private String errorMsg_DuplicateAssign = "Supplier "+supplierName+" selected has already been assigned to your location. Please unselect it.";
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoPosSupplierSearchPageFromTopMenu();
		//Add a new POS suppler.
		supplier.id =im.addPosSupplier(supplier);
		posSupplierSearchPg.searchPosSupplierById(supplier.id);
		posSupplierSearchPg.verifyPosSupplierAssignValue(supplier.id, supplier.assigned);
		
		//Duplicate assign assigned warehouse POS supplier.
		posSupplierSearchPg.assignPosSupplier(supplier.id);
		
		this.verifyErrorMessage(errorMsg_DuplicateAssign);
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/AutoWarehouse";
		
		supplier.location = "All Agencies";
		supplier.name = supplierName;
		supplier.description = "AutoTest";
		supplier.orderAddress.address = "ShanXi";
		supplier.orderAddress.city = "ShangLuo";
		supplier.orderAddress.state = "Alabama";
		supplier.orderAddress.zip = "12345";
		supplier.orderAddress.country = "United States";
		supplier.isPaymentAddrSameOrderAddr = true;
		supplier.assigned= true;
	}

	
	/**
	 * Verify error message.
	 * @param expectedMsg - expected message.
	 * @param actualMsg- actual message.
	 * @return
	 */
	private void verifyErrorMessage(String expectedMsg){
		String actualMsg = posSupplierSearchPg.getErrorMessage();
		logger.info("Verify the display warning message are corrected");
		if(!expectedMsg.trim().equals(actualMsg.trim())){
			throw new ErrorOnPageException("Verfiy assign supplier message error");
		}else{
			logger.info("Error message - '" + actualMsg + "' is displayed correctly.");
		}
	}
}
