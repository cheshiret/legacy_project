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
 * @SPEC:TC036506
 * @Task#:Auto-1110
 * @LinkSetUp:  d_inv_add_pos:id=40(product='POSManagement')
                d_inv_assign_postosupplier:id=40(suppliername='456',posname='POSManagement')
 * @author nding1
 * @Date  Jun 21, 2012
 */
public class Receive extends InventoryManagerTestCase {
	private POSPurchaseOrderInfo purchaseOrderInfo = new POSPurchaseOrderInfo();
	private InvMgrPOSPurchaseOrderSearchPage posPurchaseOrderPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();
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
		posPurchaseOrderPg.searchPOPurchaseOrder(purchaseOrderInfo);
		im.gotoReceivePOSPurchaseOrderPage(0);
		im.receiveQtyClickOK(purchaseOrderInfo.orderItemInfo);
		
		// verify
		purchaseOrderInfo.orderItemInfo.get(0).receivingDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		posPurchaseOrderPg.searchPOPurchaseOrder(purchaseOrderInfo);
		im.gotoReceivePOSPurchaseOrderPage(0);
		InvMgrReceivePurchaseOrderPage reveivePage = InvMgrReceivePurchaseOrderPage.getInstance();
		ArrayList<POSPurchaseOrderItemInfo> actualItemInfoList = reveivePage.getOrderItemInfo();
		boolean result = reveivePage.verifyOrderItemInfo(actualItemInfoList, purchaseOrderInfo.orderItemInfo, schema);
		if(!result){
			throw new ErrorOnPageException("---Not all the check points are passed.");
		}
		reveivePage.clickCancel();
		ajax.waitLoading();
		posPurchaseOrderPg.waitLoading();
		// clean up
    	im.cancelPOSPurchaseOrder(purchaseOrderInfo);
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
		orderItemInfo.qtyToReceive = "2";
		orderItemInfo.costPerUnit = "1.94";
		orderItemInfo.ordered = "5";
		orderItemInfo.received = orderItemInfo.qtyToReceive;
		orderItemInfo.receivingDate = DateFunctions.getDateAfterToday(1, DataBaseFunctions.getContractTimeZone(schema));
		itemInfoList.add(orderItemInfo);
		orderItemInfo = new POSPurchaseOrderItemInfo();
		orderItemInfo.productName = "POSManagement";
		orderItemInfo.status = "Received";
		orderItemInfo.qtyToReceive = "2";
		orderItemInfo.costPerUnit = "1.57";
		orderItemInfo.ordered = "2";
		orderItemInfo.receivingDate = DateFunctions.getDateAfterToday(2, DataBaseFunctions.getContractTimeZone(schema));
		orderItemInfo.received = orderItemInfo.qtyToReceive;
		itemInfoList.add(orderItemInfo);
		purchaseOrderInfo.orderItemInfo = itemInfoList;
    }
}
