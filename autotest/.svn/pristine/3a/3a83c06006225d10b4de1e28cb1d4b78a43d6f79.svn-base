package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.purchaseorder;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderItemInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrPOSPurchaseOrderSearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrPurchaseOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case tests the steps taken by the User to cancel a purchase order.
 * @Preconditions:
 * 1.warehouse existed.
 * 2.supplier existed.
 * 3.POS existed and have inventory
 * 4.feature assign
 * 5.make an POS purchase order
 * 6.assigned.
 * @Task#:TC036505
 * @Task#:Auto-1110
 * 
 * @author nding1
 * @Date  Jun 27, 2012
 */
public class Cancel_CancelAction extends InventoryManagerTestCase {
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
    	purchaseOrderInfo.poNum = im.createPOSPurchaseOrder(purchaseOrderInfo);

    	// search order
		purchaseOrderInfo.searchPONum = purchaseOrderInfo.poNum;
		posPurchaseOrderPg.searchPOPurchaseOrder(purchaseOrderInfo);
		im.cancelPOSPurchaseOrderClickCancel();
		this.verifyStatus();
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
		purchaseOrderInfo.shippingDate = purchaseOrderInfo.purchaseDate;
		purchaseOrderInfo.paymentTerms = "Net 10";
		purchaseOrderInfo.paymentMethod = "Cash";
		purchaseOrderInfo.shippingMethod = "UPS";
		
		// set up order item info
		ArrayList<POSPurchaseOrderItemInfo> itemInfoList = new ArrayList<POSPurchaseOrderItemInfo>();
		POSPurchaseOrderItemInfo orderItemInfo = new POSPurchaseOrderItemInfo();
		orderItemInfo.productName = "test12";
		orderItemInfo.ordered = "2";
		itemInfoList.add(orderItemInfo);
		purchaseOrderInfo.orderItemInfo = itemInfoList;
    }
    
    /**
     * verify status in order detail page
     */
    private void verifyStatus(){
		InvMgrPurchaseOrderDetailsPage detailPage = InvMgrPurchaseOrderDetailsPage.getInstance();
		posPurchaseOrderPg.searchPOPurchaseOrder(purchaseOrderInfo);
		im.gotoPOSPurchaseOrderDetailPage(purchaseOrderInfo.poNum);
		
		// verify status in order detail page
		logger.info("verify status in order detail page");
		String status = detailPage.getStatus();
		if(!MiscFunctions.compareResult("Status", "Open", status)){
			throw new ErrorOnPageException("View log above.");
		} else {
			logger.info("status is correct.");
		}
    }
    
//    /**
//     * Clean up.
//     */
//    private void cleanUp(){
//    	logger.info("Clean up.");
//		posPurchaseOrderPg.searchPOPurchaseOrder(purchaseOrderInfo);
//		im.cancelPOSPurchaseOrderClickOK();
//    }
}
