package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.purchaseorder;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderItemInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.ConfirmationDialogWidget;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrCancelPOSPurchaseOrderPage;
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
public class Cancel extends InventoryManagerTestCase {
	private POSPurchaseOrderInfo purchaseOrderInfo = new POSPurchaseOrderInfo();
	private InvMgrPOSPurchaseOrderSearchPage searchPOSPurchaseOrderPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();
	private String wareHouse = "";
	private String wareHouseName = "";

    public void execute(){
    	// login inventory manager
    	im.loginInventoryManager(login);
    	// select warehouse setup
    	im.gotoWarehousesSearchPg();
		//search warehouse
		im.searchWarehouse(wareHouse, wareHouseName);
    	// search warehouse and go to warehouse detail page
    	im.gotoWarehouseDetailPgThroughWarehouseName("WhousePrintBarcode");
    	// select POS Purchase Order
    	im.gotoPOSPurchaseOrderPg();
    	// make a purchase order and get PO#
    	purchaseOrderInfo.poNum = im.createPOSPurchaseOrder(purchaseOrderInfo);
    	
    	// search order
		purchaseOrderInfo.searchPONum = purchaseOrderInfo.poNum;
		searchPOSPurchaseOrderPg.searchPOPurchaseOrder(purchaseOrderInfo);
		im.cancelPOSPurchaseOrderClickApply();
		this.clickOK();

		// search by Cancel status
		purchaseOrderInfo.searchPONum = "";
		purchaseOrderInfo.searchStatus = "Cancelled";
		searchPOSPurchaseOrderPg.searchPOPurchaseOrder(purchaseOrderInfo);
		this.verifyStatus(purchaseOrderInfo.poNum);
		this.verifyDetailInfo(purchaseOrderInfo.poNum);
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
		orderItemInfo.productName = "test12";
		orderItemInfo.ordered = "2";
		itemInfoList.add(orderItemInfo);
		purchaseOrderInfo.orderItemInfo = itemInfoList;
		
		wareHouse = "Warehouse Name";
		wareHouseName = "WhousePrintBarcode";
    }

    /**
     * Verify order status in search order page.
     * @param poNum
     */
    private void verifyStatus(String poNum){
    	boolean result = true;
    	logger.info("Verify order status in search order page.");
    	result = searchPOSPurchaseOrderPg.verifyPONumExist(poNum);
		if(!result){
			throw new ErrorOnPageException("---Order which PO # is "+poNum+" should be contained in the search result.");
		}
    }
    
    /**
     * Verify cancellation date and status.
     * @param poNum
     */
    private void verifyDetailInfo(String poNum){
		InvMgrPurchaseOrderDetailsPage detailPage = InvMgrPurchaseOrderDetailsPage.getInstance();
		
		POSPurchaseOrderInfo orderInfo = searchPOSPurchaseOrderPg.getOrderInfoWithCancelStatus(poNum);

		// verify Cancellation date
    	boolean result = true;
    	logger.info("Verify cancellation date in search order page.");
		result &= MiscFunctions.compareResult("Cancellation date", DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)), orderInfo.cancelledDate);

		// verify status in order detail page
    	logger.info("Verify status in order detail page.");
		im.gotoPOSPurchaseOrderDetailPage(poNum);
		String status = detailPage.getStatus();
		result &= MiscFunctions.compareResult("Status", "Cancelled", status);
		
		if(!result){
			throw new ErrorOnPageException("---Not all of the check points are passed!");
		}
    }
    
    private void clickOK(){
		InvMgrCancelPOSPurchaseOrderPage cancelOrderPage = InvMgrCancelPOSPurchaseOrderPage.getInstance();
		InvMgrPOSPurchaseOrderSearchPage posPurchaseOrderPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();
		ConfirmationDialogWidget confirm = new ConfirmationDialogWidget();
		
		logger.info("Click OK button in Cancel purchase order page.");
		cancelOrderPage.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(confirm, cancelOrderPage, posPurchaseOrderPg);
		if(page == confirm){
			confirm.clickOK();
			ajax.waitLoading();
			posPurchaseOrderPg.waitLoading();
			logger.info("Cancel successfully.");
		}
    }
}
