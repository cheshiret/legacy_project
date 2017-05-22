package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.purchaseorder;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrNewPurchaseOrderPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrPOSPurchaseOrderSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Click cancel button in create purchase order page.
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
 * @Date  Jul 6, 2012
 */
public class Cancel_CreateAction extends InventoryManagerTestCase {
	private POSPurchaseOrderInfo purchaseOrderInfo = new POSPurchaseOrderInfo();
	private InvMgrPOSPurchaseOrderSearchPage searchpPosPurchaseOrderPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();
	private InvMgrNewPurchaseOrderPage newOrderPage = InvMgrNewPurchaseOrderPage.getInstance();

    public void execute(){
    	im.loginInventoryManager(login);
    	im.gotoWarehousesSearchPg();
		im.searchWarehouse("Warehouse Name", "WhousePrintBarcode");
    	im.gotoWarehouseDetailPgThroughWarehouseName("WhousePrintBarcode");
    	im.gotoPOSPurchaseOrderPg();

    	// search all the orders
		purchaseOrderInfo.searchStatus = "";
		int ordernumbers = this.getOrderNumber();
    	this.createAndcancel();
		int ordernumbers2 = this.getOrderNumber();
    	if(ordernumbers != ordernumbers2){
    		throw new ErrorOnPageException("The number of purchase order is not correct!!", ordernumbers+"", ordernumbers2+"");
    	}
    	im.logoutInvManager();
    }

    public void wrapParameters(Object[] args) {
    	login.url = AwoUtil.getOrmsURL(env);
 	    login.contract = "MS Contract";
 	    login.location="Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
    }
    
    /**
     * Get number of purchase order.
     * @return
     */
    private int getOrderNumber(){
		searchpPosPurchaseOrderPg.searchPOPurchaseOrder(purchaseOrderInfo);
		return searchpPosPurchaseOrderPg.getColumnByName("PO #").size();
    }
    
    /**
     * Click cancel button in create purchase order page
     */
    private void createAndcancel(){
    	searchpPosPurchaseOrderPg.clickCreatePurchaseOrder();
    	ajax.waitLoading();
    	newOrderPage.waitLoading();
    	newOrderPage.clickCancel();
    	ajax.waitLoading();
    	searchpPosPurchaseOrderPg.waitLoading();
    }
}
