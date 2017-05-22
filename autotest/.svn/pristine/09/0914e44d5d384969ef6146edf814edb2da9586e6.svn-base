package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.purchaseorder;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderItemInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrPOSPurchaseOrderSearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrPurchaseOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Create a new POS purchase order and verify.
 * @Preconditions:
 * 1.warehouse existed.
 * 2.supplier existed.
 * 3.POS existed and have inventory
 * 4.feature assign
 * 5.make an POS purchase order
 * 6.assigned.
 * @SPEC:TC036502
 * @Task#:Auto-1110
 * 
 * @author nding1
 * @Date  Jul 1, 2012
 */
public class Create extends InventoryManagerTestCase {
	private boolean result = true;
	private POSPurchaseOrderInfo purchaseOrderInfo = new POSPurchaseOrderInfo();
	private InvMgrPOSPurchaseOrderSearchPage posPurchaseOrderPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();

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

    	// verify purchase order info
		this.verifyNewPurchaseOrder();
		
		// verify order detail
		this.verifyDetailInfo();

    	if(!result){
    		throw new ErrorOnPageException("---Not all of the check points are passed!!");
    	}
    	
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
		orderItemInfo.supplierProductCode = "test12";
		orderItemInfo.ordered = "2";
		orderItemInfo.costPerUnit = "1.94";
		orderItemInfo.subTotalForItem = this.calculateCost(orderItemInfo.costPerUnit, orderItemInfo.ordered);
		itemInfoList.add(orderItemInfo);
		purchaseOrderInfo.orderItemInfo = itemInfoList;
		purchaseOrderInfo.itemOrdered = purchaseOrderInfo.orderItemInfo.size()+"";
		purchaseOrderInfo.discount = "5.0";
		this.calculateTotal();
    }
    
    /**
     * Verify order info in search order page.
     */
    private void verifyNewPurchaseOrder(){

    	logger.info("Verify order info in search order page.");
    	// search order by open status
		purchaseOrderInfo.searchStatus = "Open";
		posPurchaseOrderPg.searchPOPurchaseOrder(purchaseOrderInfo);

    	result &= posPurchaseOrderPg.verifyPONumExist(purchaseOrderInfo.poNum);
		if(!result){
			logger.error("---The status of POS purchase order("+purchaseOrderInfo.poNum+") should be Open!");
		} else {
	    	// search order by PO #
			purchaseOrderInfo.searchPONum = purchaseOrderInfo.poNum;
			posPurchaseOrderPg.searchPOPurchaseOrder(purchaseOrderInfo);
			
			// verify order info in search order page
			POSPurchaseOrderInfo actualOrderInfo = posPurchaseOrderPg.getOrderInfoWithOpenStatus(purchaseOrderInfo.poNum);// 1--Open
			result &= this.compare(purchaseOrderInfo.poNum, actualOrderInfo.poNum);
			result &= this.compare(purchaseOrderInfo.supplierName, actualOrderInfo.supplierName);
			result &= this.compare(purchaseOrderInfo.itemOrdered, actualOrderInfo.itemOrdered);
			result &= this.compareDate(purchaseOrderInfo.creationDate, actualOrderInfo.creationDate);
			if(!posPurchaseOrderPg.checkReceiveExist()){
				result &= false;
				logger.error("---The Receive Qty button of this POS purchase order("+purchaseOrderInfo.poNum+") should be existed!");
			}
		}
    }
    
    /**
     * Verify order info in order detail page.
     */
    private void verifyDetailInfo(){
		InvMgrPurchaseOrderDetailsPage detailPage = InvMgrPurchaseOrderDetailsPage.getInstance();
		
    	logger.info("Verify order info in order detail page.");
		im.gotoPOSPurchaseOrderDetailPage(purchaseOrderInfo.poNum);
		POSPurchaseOrderInfo actualOrderInfo = detailPage.getOrderInfo();
		result &= this.compare(purchaseOrderInfo.poNum, actualOrderInfo.poNum);
		result &= this.compare(purchaseOrderInfo.supplierName, actualOrderInfo.supplierName);
		result &= (StringUtil.compareNumStrings(purchaseOrderInfo.itemOrdered, actualOrderInfo.itemOrdered) ==0);
		result &= this.compare(purchaseOrderInfo.status, actualOrderInfo.status);
		result &= this.compareDate(purchaseOrderInfo.purchaseDate, actualOrderInfo.purchaseDate);
		result &= this.compare(purchaseOrderInfo.paymentTerms, actualOrderInfo.paymentTerms);
		result &= this.compare(purchaseOrderInfo.paymentMethod, actualOrderInfo.paymentMethod);
		result &= this.compare(purchaseOrderInfo.shippingMethod, actualOrderInfo.shippingMethod);
		for(int i=0; i<purchaseOrderInfo.orderItemInfo.size(); i++){
			POSPurchaseOrderItemInfo expectItemInfo = purchaseOrderInfo.orderItemInfo.get(i);
			POSPurchaseOrderItemInfo actualItemInfo = actualOrderInfo.orderItemInfo.get(i);
			result &= this.compare(expectItemInfo.productName, actualItemInfo.productName);
			result &= this.compare(expectItemInfo.supplierProductCode, actualItemInfo.supplierProductCode);
			result &= StringUtil.compareNumStrings(expectItemInfo.costPerUnit, actualItemInfo.costPerUnit) == 0;
			result &= StringUtil.compareNumStrings(expectItemInfo.ordered, actualItemInfo.ordered) == 0;
			result &= StringUtil.compareNumStrings(expectItemInfo.subTotalForItem, actualItemInfo.subTotalForItem) == 0;
		}
		result &= StringUtil.compareNumStrings(purchaseOrderInfo.subTotal, actualOrderInfo.subTotal) == 0;
		result &= StringUtil.compareNumStrings(purchaseOrderInfo.discount, actualOrderInfo.discount) == 0;
		result &= StringUtil.compareNumStrings(purchaseOrderInfo.total, actualOrderInfo.total) == 0;
		detailPage.clickCancel();
		ajax.waitLoading();
		posPurchaseOrderPg.waitLoading();
    }
	
	private boolean compare(String expect, String actual){
    	boolean correct = true;
		if(!expect.equals(actual)){
			correct = false;
			logger.error("---Expect value is:"+expect+", but actual value is:"+actual);
		}
		return correct;
    }
	
	private boolean compareDate(String expect, String actual){
    	boolean correct = true;
		if(DateFunctions.compareDates(expect, actual) != 0){
			correct = false;
			logger.error("---Expect date is:"+expect+", but actual date is:"+actual);
		}
		return correct;
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
		
		if(null != purchaseOrderInfo.discount && !"".equals(purchaseOrderInfo.discount)){
			BigDecimal discount = new BigDecimal(purchaseOrderInfo.discount).divide(new BigDecimal(100));
			total = subTotal.subtract(subTotal.multiply(discount));
		} else {
			total = subTotal;
		}
		purchaseOrderInfo.total = total.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
	}
}
