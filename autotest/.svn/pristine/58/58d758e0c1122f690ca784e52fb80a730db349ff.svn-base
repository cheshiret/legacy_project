package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.supplier;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**  
 * @Description: Add a new pos supplier.
 * @Preconditions:  assign AddPOSSupplier feature to role.
 * @SPEC:  Add POS Supplier TC037517
 * @Task#: Auto-972.
 * @author jwang8  
 * @Date  Mar 28, 2012    
 */
public class AddOnWarehouseLevel extends InventoryManagerTestCase{
    private PosSupplier supplier = new PosSupplier();
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoPosSupplierSearchPageFromTopMenu();
		supplier.id = im.addPosSupplier(supplier);
		im.gotoPOSSupplierDetailsPage(supplier.id);
		this.verifyAddSupplierInfo(supplier);
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/AutoWarehouse";
		
//		supplier.location = "All Agencies";
		supplier.location = "Add Barcode";
		supplier.name = "Add" + DataBaseFunctions.getEmailSequence();
		//supplier.description = "AutoTest";
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
//		supplier.primPoc.phone = "(408) 845-6789";
		supplier.primPoc.fax = "01236985";
		supplier.primPoc.email = "prim@sina.com";
		supplier.otherPoc.lastName = "otherLast";
		supplier.otherPoc.firstName = "otherFirst";
		supplier.otherPoc.phone = "4088748596";
//		supplier.otherPoc.phone = "(408) 874-8596";
		supplier.otherPoc.fax = "02174856";
		supplier.otherPoc.email = "other@sina.com";
		supplier.paymentTerms = "Due on Receipt";
		supplier.paymentMethod = "Cash";
		supplier.shippingMethod = "UPS";
	}
	
	/**
	 * verify the added supplier info success.
	 * @param supplier - the info of supplier.
	 */
	public void verifyAddSupplierInfo(PosSupplier supplier){
		InvMgrPOSSupplierDetailsPage posSupplierDetailPg = InvMgrPOSSupplierDetailsPage.getInstance();
		boolean pass = posSupplierDetailPg.compareSupplierDetailsInfo(supplier);
		if(!pass){
			 throw new ErrorOnPageException("Add pos supplier error");
		}else{
			logger.info("Add pos supplier success");
		}
	}

}
