package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.purchaseorder;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderItemInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrPOSPurchaseOrderSearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrReceivePurchaseOrderPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case tests the steps taken by the user to receive Purchase Order Item Qty for an associated Purchase Order.
 * @Preconditions:
 * 1.warehouse existed.
 * 2.supplier existed.
 * 3.POS existed and have inventory
 * 4.feature assign
 * 5.make an POS purchase order(contains two order items)
 * 6.assigned.
 * @SPEC:TC036506 & TC036508
 * @Task#:Auto-1110
 * 
 * @author nding1
 * @Date  Jul 6, 2012
 */
public class Cancel_ReceiveAction extends InventoryManagerTestCase {
	private POSPurchaseOrderInfo purchaseOrderInfo = new POSPurchaseOrderInfo();
	private InvMgrPOSPurchaseOrderSearchPage posPurchaseOrderPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();
	private InvMgrReceivePurchaseOrderPage reveivePage = InvMgrReceivePurchaseOrderPage.getInstance();
	
    public void execute(){
    	// login inventory manager
    	im.loginInventoryManager(login);
    	// select warehouse setup
    	im.gotoWarehousesSearchPg();
		//search warehouse
		im.searchWarehouse("Warehouse Name", "WhousePrintBarcode");
    	// search warehouse and go to warehouse detail page
    	im.gotoWarehouseDetailPgThroughWarehouseName("WhousePrintBarcode");
    	// select POS Purchase Order
    	im.gotoPOSPurchaseOrderPg();
    	// make a purchase order and get PO#
    	purchaseOrderInfo.searchPONum = im.createPOSPurchaseOrder(purchaseOrderInfo);
    	// search order
		this.searchAndReceive();
		im.receiveQtyClickCancel(purchaseOrderInfo.orderItemInfo);
		this.searchAndReceive();
		this.verifyStatusAndReceiveNum();
		this.cleanUp();
		im.logoutInvManager();
    }

    public void wrapParameters(Object[] args) {
    	login.url = AwoUtil.getOrmsURL(env);
 	    login.contract = "MS Contract";
 	    login.location="Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		purchaseOrderInfo.supplierName = "456";
		purchaseOrderInfo.purchaseDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		purchaseOrderInfo.shippingDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		purchaseOrderInfo.paymentTerms = "Net 10";
		purchaseOrderInfo.paymentMethod = "Cash";
		purchaseOrderInfo.shippingMethod = "UPS";
		
		// set up order item info
		ArrayList<POSPurchaseOrderItemInfo> itemInfoList = new ArrayList<POSPurchaseOrderItemInfo>();
		POSPurchaseOrderItemInfo orderItemInfo = new POSPurchaseOrderItemInfo();
		orderItemInfo.status = "Partially Received";
		orderItemInfo.productName = "test12";
		orderItemInfo.qtyToReceive = "3";
		orderItemInfo.costPerUnit = "1.37";
		orderItemInfo.ordered = "5";
		orderItemInfo.received = orderItemInfo.qtyToReceive;
		orderItemInfo.receivingDate = DateFunctions.getDateAfterToday(1, DataBaseFunctions.getContractTimeZone(schema));
		itemInfoList.add(orderItemInfo);
		orderItemInfo = new POSPurchaseOrderItemInfo();
		orderItemInfo.productName = "POSManagement";
		orderItemInfo.status = "Received";
		orderItemInfo.qtyToReceive = "2";
		orderItemInfo.costPerUnit = "2.36";// set up is 5.43
		orderItemInfo.ordered = "2";
		orderItemInfo.received = orderItemInfo.qtyToReceive;
		itemInfoList.add(orderItemInfo);
		purchaseOrderInfo.orderItemInfo = itemInfoList;
    }  
    
    /**
     * Search POS purchase order and receive.
     */
    private void searchAndReceive(){
    	logger.info("Search POS purchase order and receive.");
		posPurchaseOrderPg.searchPOPurchaseOrder(purchaseOrderInfo);
		im.gotoReceivePOSPurchaseOrderPage(0);
    }
    
    /**
     * Verify status and number of reveived after cancel receive operation.
     */
    private void verifyStatusAndReceiveNum(){
		
		logger.info("Verify status and number of reveived after cancel receive operation.");
		boolean result = reveivePage.verifyCancelReceive();
		if(!result){
			throw new ErrorOnPageException("---Not all of the check points are PASSED!");
		}
    }
    
    /**
     * Clean up.
     */
    private void cleanUp(){
    	logger.info("Clean up.");
		reveivePage.clickCancel();
		ajax.waitLoading();
		posPurchaseOrderPg.waitLoading();
		im.cancelPOSPurchaseOrder(purchaseOrderInfo);
    }
}
