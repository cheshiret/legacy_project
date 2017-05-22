package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.purchaseorder;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderItemInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.ConfirmationDialogWidget;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrClosePOSPurchaseOrderPage;
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
 * @Description:This test case tests the steps taken by the User to close a purchase order.
 * @Preconditions:
 * 1.warehouse existed.
 * 2.supplier existed.
 * 3.POS existed and have inventory
 * 4.feature assign
 * 5.make an POS purchase order
 * 6.assigned.
 * @SPEC:TC:036504
 * @Task#:Auto-1110
 * 
 * @author nding1
 * @Date  Jun 25, 2012
 */
public class Close extends InventoryManagerTestCase {
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
		im.closePurchaseOrderClickApply();
		this.clickOK();
		
		// search by closed status
		purchaseOrderInfo.searchPONum = "";
		purchaseOrderInfo.searchStatus = "Closed";
		posPurchaseOrderPg.searchPOPurchaseOrder(purchaseOrderInfo);
		this.verifyClosed(purchaseOrderInfo.poNum);
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
    }
    
    /**
     * Verify the order info after close.
     * @param poNum
     */
    private void verifyClosed(String poNum){
    	logger.info("Verify the order info after close.");
    	boolean result = true;
		List<String> poNumList = posPurchaseOrderPg.getColumnByName("PO #");
		if(!poNumList.contains(poNum)){
			throw new ErrorOnPageException("---For this order, the staus should be Close.");
		} else {
			InvMgrPurchaseOrderDetailsPage detailPage = InvMgrPurchaseOrderDetailsPage.getInstance();
			POSPurchaseOrderInfo orderInfo = posPurchaseOrderPg.getOrderInfoWithCloseStatus(poNum);
			
			// verify closed date
	    	logger.info("Verify closed date in search order page.");
			result &= MiscFunctions.compareResult("Closed date", DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)), orderInfo.closedDate);

			// verify status in order detail page
	    	logger.info("Verify status in order detail page.");
	    	im.gotoPOSPurchaseOrderDetailPage(poNum);
			String status = detailPage.getStatus();
			result &= MiscFunctions.compareResult("Status", "Closed", status);
		}
		if(!result){
			throw new ErrorOnPageException("---Not all of the check points are passed!");
		}
    }
    
    private void clickOK(){
		InvMgrClosePOSPurchaseOrderPage closeOrderPage = InvMgrClosePOSPurchaseOrderPage.getInstance();
		InvMgrPOSPurchaseOrderSearchPage posPurchaseOrderPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();
		ConfirmationDialogWidget confirm = new ConfirmationDialogWidget();
		
		logger.info("Click OK button in Close purchase order page.");
		closeOrderPage.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(confirm, closeOrderPage, posPurchaseOrderPg);
		if(page == confirm){
			confirm.clickOK();
			ajax.waitLoading();
			posPurchaseOrderPg.waitLoading();
			logger.info("Cancel successfully.");
		}
    }
}
