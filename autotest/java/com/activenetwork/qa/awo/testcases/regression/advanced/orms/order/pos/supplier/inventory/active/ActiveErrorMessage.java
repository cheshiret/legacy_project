package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.pos.supplier.inventory.active;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**  
 * @Description: Active a new POS supplier.
 * @Preconditions:  assign AddPOSSupplier feature to role.
 * @SPEC:  Active POS supplier
 * @Task#: Auto-972.
 * @author jwang8  
 * @Date  Apr 23, 2012    
 */
public class ActiveErrorMessage extends InventoryManagerTestCase{

	private PosSupplier supplier = new PosSupplier();
	private InvMgrPOSSupplierSearchPage supplierSarchPg = InvMgrPOSSupplierSearchPage.getInstance();
	private String errorMsg_DuplicateActive = "One or more selected POS Suppliers already have an Active status. Please unselect them.";
	public void execute() {
		
		im.loginInventoryManager(login);
		//Go to POS supplier search page.
		im.gotoPosSupplierSearchPageFromTopMenu();
		//Add a new pos supplier.
		supplier.id =im.addPosSupplier(supplier);
		supplierSarchPg.searchPosSupplierById(supplier.id);
		//Check the default status is active.
		supplierSarchPg.verifyPosSupplierStatus(supplier.id, supplier.status);
		//Deactive the specific supplier.
		supplierSarchPg.activePosSupplier(supplier.id);
		this.verifyErrorMessage(errorMsg_DuplicateActive);
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		supplier.location = "STATE PARKS";//All Agencies
		supplier.name = "AddSupp" + DataBaseFunctions.getEmailSequence();
		supplier.description = "AutoTest";
		supplier.orderAddress.address = "ShanXi";
		supplier.orderAddress.city = "ShangLuo";
		supplier.orderAddress.state = "Alabama";
		supplier.orderAddress.zip = "12345";
		supplier.orderAddress.country = "United States";
		supplier.isPaymentAddrSameOrderAddr = false;
		supplier.paymentAddress.address = "JiangSu";
		supplier.paymentAddress.city = "HangZhou";
		supplier.paymentAddress.state = "Alabama";
		supplier.paymentAddress.zip = "23456";
		supplier.paymentAddress.country = "United States";
		supplier.primPoc.lastName = "primLast";
		supplier.primPoc.firstName = "primFirst";
		supplier.primPoc.phone = "4088456789";
		supplier.primPoc.fax = "01236985";
		supplier.primPoc.email = "prim@sina.com";
		supplier.otherPoc.lastName = "otherLast";
		supplier.otherPoc.firstName = "otherFirst";
		supplier.otherPoc.phone = "4088748596";
		supplier.otherPoc.fax = "02174856";
		supplier.otherPoc.email = "other@sina.com";
		supplier.paymentTerms = "Due on Receipt";
		supplier.paymentMethod = "Cash";
		supplier.shippingMethod = "UPS";
		supplier.status = OrmsConstants.ACTIVE_STATUS;
		
	}
	
	/**
	 * Verify error message.
	 * @param expectedMsg - expected message.
	 * @param actualMsg- actual message.
	 * @return
	 */
	private void verifyErrorMessage(String expectedMsg){
		String actualMsg  = supplierSarchPg.getErrorMessage();
		logger.info("Verify the display warning message are corrected");
		if(!expectedMsg.equals(actualMsg)){
			throw new ErrorOnPageException("Verfiy assign supplier message error",expectedMsg,actualMsg);
		}
		logger.info("Error message - '" + actualMsg + "' is displayed correctly.");
	}
}
