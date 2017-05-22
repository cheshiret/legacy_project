package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.pos.supplier.inventory.deactive;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;

/**  
 * @Description: deactivate a new POS supplier.
 * @Preconditions: 
 * @SPEC:  deactivate POS supplier
 * @Task#: Auto-972.
 * @author jwang8  
 * @Date  Apr 23, 2012    
 */
public class deactiveSuccessAndCancel extends InventoryManagerTestCase{

	private PosSupplier supplier = new PosSupplier();
	private InvMgrPOSSupplierSearchPage supplierSarchPg = InvMgrPOSSupplierSearchPage.getInstance();
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
		
		//Active the supplier.
		supplierSarchPg.activePosSupplier(supplier.id);
		supplier.status = OrmsConstants.ACTIVE_STATUS;
		supplierSarchPg.verifyPosSupplierStatus(supplier.id, supplier.status);
		
		//Verify the cancel deactive POS supplier.
		im.deActivePosSupplierCancel(supplier.id);
		supplier.status = OrmsConstants.ACTIVE_STATUS;
		supplierSarchPg.verifyPosSupplierStatus(supplier.id, supplier.status);
		im.logoutInvManager();
		
	}
	public void wrapParameters(Object[] param) {
		
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		supplier.location = "STATE PARKS";
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
}
