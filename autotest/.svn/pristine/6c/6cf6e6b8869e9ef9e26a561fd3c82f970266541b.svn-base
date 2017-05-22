package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.pos.supplier.inventory.deactive;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**  
 * @Description: Verify deactive POS supplier error message.
 * @Preconditions: 
 * @SPEC:  Deactive POS supplier
 * @Task#: Auto-972.
 * @author jwang8  
 * @Date  Apr 23, 2012    
 */
public class VerifyErrorMessage extends InventoryManagerTestCase{
	private PosSupplier supplier = new PosSupplier();
	private InvMgrPOSSupplierSearchPage supplierSarchPg = InvMgrPOSSupplierSearchPage.getInstance();
	private String supplierName = "AddSupp" + DataBaseFunctions.getEmailSequence();
	private String errorMsg_DuplicateDeactive = "The following selected POS Suppliers ("+supplierName+") already have an Inactive status. Please unselect them.";
	private String errorMsg_ExistActiveAssignment = "The following selected POS Suppliers ("+supplierName+") have an active assignment to a Facility or Warehouse location and cannot be deactivated. The assignments must be removed prior to POS Supplier deactivation.";
//	private String wareHouseId = "158951";//Add by support script.
	private String warehouseName = "AutoWarehouse";
	private boolean runningResult = true;
	
	public void execute() {
		im.loginInventoryManager(login);
		//Go to POS supplier search page.
		im.gotoPosSupplierSearchPageFromTopMenu();
		//Add a new pos supplier.
		supplier.id = im.addPosSupplier(supplier);
		supplierSarchPg.searchPosSupplierById(supplier.id);
		//Check the default status is active.
		supplierSarchPg.verifyPosSupplierStatus(supplier.id, supplier.status);
		//Deactive the specific supplier.
		im.deactivatePOSSupplier(supplier.id);
		//Check the deactived supplier status.
		supplier.status = OrmsConstants.INACTIVE_STATUS;
		supplierSarchPg.verifyPosSupplierStatus(supplier.id, supplier.status);
		//Deactve once more.
		im.deactivatePOSSupplier(supplier.id);
		runningResult = this.verifyErrorMessage(errorMsg_DuplicateDeactive);
		
		supplierSarchPg.activePosSupplier(supplier.id);
		supplier.status = OrmsConstants.ACTIVE_STATUS;
		supplierSarchPg.verifyPosSupplierStatus(supplier.id, supplier.status);
		
		//Verify the message have an \ufffdActive\ufffd assignment to a Facility or Warehouse location.
		//Assign POS  not assigned to ware house.
		im.gotoWarehousesSearchPg();
//		im.gotoWarehouseDetailPgThroughClickWarehouseID(wareHouseId);
		im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
		im.gotoPosSupplierSearchPageFromTopMenu();
		supplierSarchPg.searchPosSupplierById(supplier.id);
		supplierSarchPg.assignPosSupplier(supplier.id);
	     //Switch to contract POS supplier search page.
		im.swichWarehouseToContactPosSupllerSearchPg();
		
		supplierSarchPg.searchPosSupplierById(supplier.id);
		im.deactivatePOSSupplier(supplier.id);
		runningResult = this.verifyErrorMessage(errorMsg_ExistActiveAssignment);
		if(!runningResult){
			throw new ErrorOnPageException("Verfiy assign supplier message error");
		}else{
			logger.info("All the error message verify sucess");
		}
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		supplier.location = "MSHF";//All Agencies
		supplier.name = supplierName;
		supplier.description = "AutoTest";
		supplier.orderAddress.address = "ShanXi";
		supplier.orderAddress.city = "ShangLuo";
		supplier.orderAddress.state = "Alabama";
		supplier.orderAddress.zip = "12345";
		supplier.orderAddress.country = "United States";
		supplier.isPaymentAddrSameOrderAddr = false;
		supplier.paymentAddress.address = "JiangSu";
		supplier.paymentAddress.city = "HangZhou";
		supplier.paymentAddress.state = "Alaska";
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
	private boolean verifyErrorMessage(String expectedMsg){
		boolean pass = true;
		String actualMsg = supplierSarchPg.getErrorMessage();
		logger.info("Verify the display warning message are corrected");
		if(!expectedMsg.equals(actualMsg)){
			logger.error("Actual message doesn't match the expected. Actual message is :" + actualMsg + ", but the expected is :" + expectedMsg);
		    pass &= false;
		}else{
			logger.info("Error message - '" + actualMsg + "' is displayed correctly.");
		}
		return pass;
	}
}
