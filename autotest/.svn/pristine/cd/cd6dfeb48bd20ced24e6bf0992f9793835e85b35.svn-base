package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.pos.supplier.inventory.add;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**  
 * @Description: Add a new POS supplier.
 * @Preconditions:  assign AddPOSSupplier feature to role.
 * @SPEC:  Add POS supplier
 *         Add POS Supplier in Inventory Manager [TC:058714]
 * @Task#: Auto-972,AUTO-2191 
 * @author jwang8  
 * @Date  Apr 18, 2012    
 */
public class AddSuccessAndCancel extends InventoryManagerTestCase {
	private PosSupplier supplier = new PosSupplier();
	private InvMgrPOSSupplierSearchPage posSupplierSearchPg = InvMgrPOSSupplierSearchPage.getInstance();
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoPosSupplierSearchPageFromTopMenu();
		//Add POS supplier success and verify the records exist.
		supplier.id = im.addPosSupplier(supplier);
		posSupplierSearchPg.searchPosSupplierById(supplier.id);
		this.verifyAddPosSupplierSuccess(supplier);
		
		//Cancel the process of adding POS suppler process and verify the records are not exist.
		supplier.name  = "again" + DataBaseFunctions.getEmailSequence();
		im.addPosSupplierCancel(supplier);
		posSupplierSearchPg.searchPosSupplierByName(supplier.name);
		posSupplierSearchPg.verifyPosSupplierNotExist(supplier);
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/AutoWarehouse";
		
		supplier.location = "All Agencies";
		supplier.name = "Add" + DataBaseFunctions.getEmailSequence();
		supplier.description = "AutoTest";
		supplier.webSite = "www.sbccorp.com";
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
		supplier.fobPoint = "Destination";
		supplier.freightTerms = "Free Freight";
	}
	/**
	 * verify add POS supplier success.
	 * @param posSupplier
	 */
	private void verifyAddPosSupplierSuccess(PosSupplier posSupplier){
		boolean pass = posSupplierSearchPg.comparePOSSupplierListInfo(posSupplier);
		if(!pass){
			 throw new ErrorOnPageException("Add pos supplier error");
		}else{
			logger.info("Add pos supplier failed");
		}
	}
}
