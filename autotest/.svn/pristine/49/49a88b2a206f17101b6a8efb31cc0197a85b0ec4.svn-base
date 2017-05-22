package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.purchaseorder;

import java.math.BigDecimal;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrPOSPurchaseOrderSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Verify the User shall be able to toggle between ascending and descending sort order by clicking on the Column Heading repeatedly. 
 * @Preconditions:
 * 1.warehouse existed.
 * 2.supplier existed.
 * 3.POS existed and have inventory
 * 4.feature assign
 * 5.make an POS purchase order
 * 6.assigned.
 * @SPEC:TC036508
 * @Task#:Auto-1110
 * 
 * @author nding1
 * @Date  Jul 4, 2012
 */
public class VerifyrSort extends InventoryManagerTestCase {
	private POSPurchaseOrderInfo purchaseOrderInfo = new POSPurchaseOrderInfo();
	private InvMgrPOSPurchaseOrderSearchPage posPurchaseOrderPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();
	private boolean result = true;
	
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
    	
		// search
		purchaseOrderInfo.searchStatus = "";
		posPurchaseOrderPg.searchPOPurchaseOrder(purchaseOrderInfo);

		// verify sorted by the PO # in ascending order by default
		int cnt = 0;
		this.verifySort(cnt);
		
		// click PO#(ASC)
		posPurchaseOrderPg.clickColumnName("PO #");
		posPurchaseOrderPg.waitLoading();
		cnt++;
		this.verifySort(cnt);

		// click PO#(DESC)
		posPurchaseOrderPg.clickColumnName("PO #");
		posPurchaseOrderPg.waitLoading();
		cnt++;
		this.verifySort(cnt);
		
		if(!result){
			throw new ErrorOnPageException("---Not all the check points are passed.");
		}
		im.logoutInvManager();
    }
    
    public void wrapParameters(Object[] args) {
    	login.url = AwoUtil.getOrmsURL(env);
 	    login.contract = "MS Contract";
 	    login.location="Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
    }
    
    private void verifySort(int cnt){
    	List<String> resultList = posPurchaseOrderPg.getColumnByName("PO #");
    	
    	logger.info("Click PO #:"+cnt);
    	if(resultList.size() > 0){
        	for(int i=0; i<resultList.size(); i++){
        		if(i != resultList.size()-1){
        			this.compare(resultList.get(i), resultList.get(i+1), cnt);
        		}
        	}
    	}
    }
    
    private void compare(String previous, String next, int cnt){
		BigDecimal previous1 = new BigDecimal(previous);
		BigDecimal next1 = new BigDecimal(next);

		if(cnt == 0 || cnt%2 != 0){// ASC
			if(previous1.subtract(next1).compareTo(new BigDecimal("0")) > 0){ 
				result &= false;
				logger.error("The PO # should be sort in ascending order.");
			}
		} else {// DESC
			if(previous1.subtract(next1).compareTo(new BigDecimal("0")) < 0){ 
				result &= false;
				logger.error("The PO # should be sort in descending order.");
			}
		}
    }
}
