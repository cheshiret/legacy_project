package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.purchaseorder;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderItemInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.ConfirmationDialogWidget;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrPOSPurchaseOrderSearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrPurchaseOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Edit a POS purchase order and verify.
 * @Preconditions:
 * 1.warehouse existed.
 * 2.supplier existed.
 * 3.POS existed and have inventory
 * 4.feature assign
 * 5.make an POS purchase order
 * 6.assigned.
 * @SPEC:TC036503
 * @Task#:Auto-1110
 * 
 * @author nding1
 * @Date  Jul 6, 2012
 */
public class Edit extends InventoryManagerTestCase {
	private boolean result = true;
	private POSPurchaseOrderInfo purchaseOrderInfo = new POSPurchaseOrderInfo();
	private InvMgrPOSPurchaseOrderSearchPage searchPOSPurchaseOrderPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();
	private InvMgrPurchaseOrderDetailsPage detailPg = InvMgrPurchaseOrderDetailsPage.getInstance();
	
    public void execute(){
    	im.loginInventoryManager(login);
    	im.gotoWarehousesSearchPg();
		im.searchWarehouse("Warehouse Name", "WhousePrintBarcode");
    	im.gotoWarehouseDetailPgThroughWarehouseName("WhousePrintBarcode");
    	im.gotoPOSPurchaseOrderPg();
    	
    	// make a purchase order and get PO#
    	purchaseOrderInfo.poNum = im.createPOSPurchaseOrder(purchaseOrderInfo);

    	// search order
		purchaseOrderInfo.searchPONum = purchaseOrderInfo.poNum;
		searchPOSPurchaseOrderPg.searchPOPurchaseOrder(purchaseOrderInfo);
		im.gotoPOSPurchaseOrderDetailPage(purchaseOrderInfo.searchPONum);

    	// edit(remove the second order item)
		this.resetOrderInfo();
		im.editPOSPurchaseOrder(purchaseOrderInfo, "Remove", 2, "Apply");
    	this.clickOK();
		
    	// verify detail info
    	searchPOSPurchaseOrderPg.searchPOPurchaseOrder(purchaseOrderInfo);
    	im.gotoPOSPurchaseOrderDetailPage(purchaseOrderInfo.poNum);
		this.verifyPOSPurchaseOrderInfo();
		this.gotoPOSPurchaseOrderSearchPageFromDetails();
    	
    	// clean up
    	this.cancelPurchaseOrder();
		if(!result){
			throw new ErrorOnPageException("---Not all of the check points are passed!");
		}
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
		purchaseOrderInfo.creationDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		purchaseOrderInfo.status = "Open";
		
		// set up order item info
		ArrayList<POSPurchaseOrderItemInfo> itemInfoList = new ArrayList<POSPurchaseOrderItemInfo>();
		POSPurchaseOrderItemInfo orderItemInfo = new POSPurchaseOrderItemInfo();
		orderItemInfo.productName = "posForUse";
		orderItemInfo.supplierProductCode = "PFU";
		orderItemInfo.costPerUnit = "3.48";
		orderItemInfo.ordered = "3";
		orderItemInfo.subTotalForItem = this.calculateCost(orderItemInfo.costPerUnit, orderItemInfo.ordered);
		itemInfoList.add(orderItemInfo);
		orderItemInfo = new POSPurchaseOrderItemInfo();
		orderItemInfo.productName = "test12";
		orderItemInfo.supplierProductCode = "test";
		orderItemInfo.ordered = "2";
		orderItemInfo.costPerUnit = "1.94";
		orderItemInfo.subTotalForItem = this.calculateCost(orderItemInfo.costPerUnit, orderItemInfo.ordered);
		itemInfoList.add(orderItemInfo);
		purchaseOrderInfo.orderItemInfo = itemInfoList;
		purchaseOrderInfo.itemOrdered = purchaseOrderInfo.orderItemInfo.size()+"";
		purchaseOrderInfo.discount = "5.0";
		this.calculateTotal();
    }
    
    private void verifyPOSPurchaseOrderInfo(){
		POSPurchaseOrderInfo actualPurchaseOrderInfo = detailPg.getOrderInfo();
		
		result &= MiscFunctions.compareResult("Date of Purchase", purchaseOrderInfo.purchaseDate, actualPurchaseOrderInfo.purchaseDate);
		result &= MiscFunctions.compareResult("Payment Terms", purchaseOrderInfo.paymentTerms, actualPurchaseOrderInfo.paymentTerms);
		result &= MiscFunctions.compareResult("Payment Method", purchaseOrderInfo.paymentMethod, actualPurchaseOrderInfo.paymentMethod);
		result &= MiscFunctions.compareResult("Shipping Method", purchaseOrderInfo.shippingMethod, actualPurchaseOrderInfo.shippingMethod);
		result &= MiscFunctions.compareResult("Order Subtotal", purchaseOrderInfo.subTotal, actualPurchaseOrderInfo.subTotal);
		result &= MiscFunctions.compareResult("Order Total", purchaseOrderInfo.total, actualPurchaseOrderInfo.total);
		
		// verify order item info
		POSPurchaseOrderItemInfo actualItemInfo = actualPurchaseOrderInfo.orderItemInfo.get(0);
		POSPurchaseOrderItemInfo expectItemInfo = purchaseOrderInfo.orderItemInfo.get(0);
		result &= MiscFunctions.compareResult("Product Name", expectItemInfo.productName, actualItemInfo.productName);
		result &= MiscFunctions.compareResult("Supplier Product Code", expectItemInfo.supplierProductCode, actualItemInfo.supplierProductCode);
		result &= MiscFunctions.compareResult("Supplier Unit Cost", expectItemInfo.costPerUnit, actualItemInfo.costPerUnit);
		result &= MiscFunctions.compareResult("Qty To Order", expectItemInfo.ordered, actualItemInfo.ordered);
		result &= MiscFunctions.compareResult("Sub-Total", expectItemInfo.subTotalForItem, actualItemInfo.subTotalForItem);
    }
	
    private void gotoPOSPurchaseOrderSearchPageFromDetails() {
    	detailPg.clickCancel();
		ajax.waitLoading();
		searchPOSPurchaseOrderPg.waitLoading();
    }
    
	/**
	 * Click OK button in edit purchase order page.
	 */
	private void clickOK(){
		ConfirmationDialogWidget confirm = new ConfirmationDialogWidget();
		
		logger.info("Click OK button in edit purchase order page.");
		detailPg.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(confirm, detailPg, searchPOSPurchaseOrderPg);
		if(page == confirm){
			confirm.clickOK();
			ajax.waitLoading();
			searchPOSPurchaseOrderPg.waitLoading();
			logger.info("Cancel successfully.");
		}
	}
	
    private void resetOrderInfo(){
		purchaseOrderInfo.purchaseDate = DateFunctions.getDateAfterToday(1, DataBaseFunctions.getContractTimeZone(schema));
		purchaseOrderInfo.shippingDate = DateFunctions.getDateAfterToday(2, DataBaseFunctions.getContractTimeZone(schema));
		purchaseOrderInfo.paymentTerms = "Net 15";
		purchaseOrderInfo.paymentMethod = "Other";
		purchaseOrderInfo.shippingMethod = "Fedex";

    	// remove the second order item, so calculate sub-total and total again.
		purchaseOrderInfo.orderItemInfo.remove(1);//remove the second order item
		this.calculateTotal();
    }
	
	/**
	 * Calculate sub total for each order item.
	 * @param costPerUnit
	 * @param quantity
	 * @return
	 */
	private String calculateCost(String costPerUnit, String quantity){
		logger.info("Calculate sub total for each order item.");
		BigDecimal price = new BigDecimal(costPerUnit).setScale(2);
		BigDecimal count = new BigDecimal(quantity).setScale(2);
		String subTotal = price.multiply(count).setScale(2).toString();
		return subTotal;
	}
	
	/**
	 * Calculate sub total and total for order.
	 */
	private void calculateTotal(){
		BigDecimal subTotal = BigDecimal.ZERO;
		BigDecimal total = BigDecimal.ZERO;
		logger.info(" Calculate sub total and total for order.");
		
		for(int i=0; i<purchaseOrderInfo.orderItemInfo.size(); i++){
			subTotal = subTotal.add(new BigDecimal(purchaseOrderInfo.orderItemInfo.get(i).subTotalForItem));
		}
		purchaseOrderInfo.subTotal = subTotal.setScale(2).toString();
		
		if(!StringUtil.isEmpty(purchaseOrderInfo.discount)) {
			total = subTotal.subtract(subTotal.multiply(new BigDecimal(purchaseOrderInfo.discount).divide(new BigDecimal(100))));
		} else {
			total = subTotal;
		}
		purchaseOrderInfo.total = total.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		logger.info("Calculated total amount is: " + purchaseOrderInfo.total);
	}
	
	/**
	 * Clean up. Cancel the POS purchase order
	 */
	private void cancelPurchaseOrder(){
		logger.info("Clean up. Cancel the POS purchase order which PO# is:"+purchaseOrderInfo.searchPONum);
		detailPg.clickCancel();
		ajax.waitLoading();
		searchPOSPurchaseOrderPg.waitLoading();
    	im.cancelPOSPurchaseOrder(purchaseOrderInfo);
	}
}
