package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.purchaseorder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderItemInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrPOSPurchaseOrderSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.InvalidDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Search POS Purchase Order by single condition.
 * 1.Search by status
 * 2.Search by PO#
 * 3.Search by supplier name
 * 4.Search by order creation start date
 * 5.search by order creation end date
 * @Preconditions:
 * 1.warehouse existed.
 * 2.supplier existed.
 * 3.POS existed and have inventory
 * 4.feature assign
 * 5.make an POS purchase order
 * 6.assigned.
 * @SPEC:TC036501
 * @Task#:Auto-1110
 * 
 * @author nding1
 * @Date  Jun 18, 2012
 */
public class SearchWithSingleCondition extends InventoryManagerTestCase {
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
		// 1.search by status
		boolean result = true;
		purchaseOrderInfo.searchStatus = "Open";
		result &= this.searchAndVerify("Status", purchaseOrderInfo.searchStatus);
		String creationDate = posPurchaseOrderPg.getColumnByName("Order Creation Date").get(0);
		
		// 2.search by PO#
		purchaseOrderInfo.searchStatus = StringUtil.EMPTY;
		purchaseOrderInfo.searchPONum = purchaseOrderInfo.poNum;
		result &= this.searchAndVerify("PO #", purchaseOrderInfo.searchPONum);
		
		// 3.search by supplier name
		purchaseOrderInfo.searchPONum = StringUtil.EMPTY;
		purchaseOrderInfo.searchSupplierName = "456";
		result &= this.searchAndVerify("Supplier Name", purchaseOrderInfo.searchSupplierName);

		// 4.search by order creation start date
		purchaseOrderInfo.searchCreationStartDate = DateFunctions.getDateAfterGivenDay(creationDate, -1);
		result &= this.searchAndVerify("Order Creation Date", purchaseOrderInfo.searchCreationStartDate);
		
		// 5.search by order creation end date
		purchaseOrderInfo.searchSupplierName = StringUtil.EMPTY;
		purchaseOrderInfo.searchCreationStartDate = StringUtil.EMPTY;
		purchaseOrderInfo.searchCreationEndDate = DateFunctions.getDateAfterGivenDay(creationDate, 1);
		result &= this.searchAndVerify("Order Creation Date", purchaseOrderInfo.searchCreationEndDate);
		
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
		purchaseOrderInfo.orderItemInfo = itemInfoList;
		purchaseOrderInfo.itemOrdered = purchaseOrderInfo.orderItemInfo.size()+"";
		purchaseOrderInfo.discount = "5.0";
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
		
		if(null != purchaseOrderInfo.discount && !"".equals(purchaseOrderInfo.discount)){
			BigDecimal discount = new BigDecimal(purchaseOrderInfo.discount).divide(new BigDecimal(100));
			total = subTotal.subtract(subTotal.multiply(discount));
		} else {
			total = subTotal;
		}
		purchaseOrderInfo.total = total.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
	}
	
    /**
     * Verify search result
     * @param searchBy
     * @param searchValue
     * @return
     */
    private boolean verifySearchResult(String searchBy, String searchValue){
    	boolean result = true;
    	logger.info("Verify search result.");
    	
    	// when search by status
    	if("Status".equalsIgnoreCase(searchBy)){
    		List<String> poNumListUI = posPurchaseOrderPg.getColumnByName("PO #");
    		List<String> poNumListDB = this.getPONumFromDBByStatus();
    		if(!MiscFunctions.compareResult("Search result size", poNumListDB.size(), poNumListUI.size())){
    			throw new ErrorOnPageException("--Check logs above.");
    		} else {
    			for(int i=0; i<poNumListUI.size(); i++){
    				if(!poNumListUI.contains(poNumListDB.get(i))){
    					result &= false;
    					logger.error("---The search result list should contains "+poNumListDB.get(i)+".");
    				}
    			}
    		}
    	} else {
        	List<String> valueList = posPurchaseOrderPg.getColumnByName(searchBy);
        	if("PO #".equalsIgnoreCase(searchBy)){
        		if(valueList.size() != 1){
        			result &= false;
        			logger.error("---When search by PO#, there should be only one record in search result list. But there are "+valueList.size()+" records.");
        		}
        	}
        	
        	// search by creation start date
        	if(null != purchaseOrderInfo.searchCreationStartDate && !"".equals(purchaseOrderInfo.searchCreationStartDate)){ 
        		result &= this.compareDate(valueList, "Start", searchValue);
            // search by creation end date
        	} else if(null != purchaseOrderInfo.searchCreationEndDate && !"".equals(purchaseOrderInfo.searchCreationEndDate)){
        		result &= this.compareDate(valueList, "End", searchValue);
        	// search by other field
        	} else {
            	if(!valueList.contains(searchValue)){
            		result &= false;
            		logger.error("---The search result doesn't match search criteria. Search by:"+searchBy+", search value is:"+searchValue);
            	}
        	}
    	}
    	return result;
    }    
    
    /**
     * When searching by creation date, compare the date range.
     * @param dateList
     * @param dateType
     * @param searchValue
     * @return
     */
    private boolean compareDate(List<String> dateList, String dateType, String searchValue){
    	logger.info("Verify when searching by creation date.");
    	boolean result = true;
    	for(int i=0; i<dateList.size(); i++) {
    		int diff = DateFunctions.compareDates(searchValue, dateList.get(i));
    		if("Start".equalsIgnoreCase(dateType)) {
    			if(diff>0){
    				result = false;
    				logger.error("--The Order Creation Date before "+searchValue+" should not be contained in search result list.");
    			}
    		} else if("End".equalsIgnoreCase(dateType)){
    			if(diff < 0){
    				result = false;
    				logger.error("--The Order Creation Date after "+searchValue+" should not be contained in search result list.");
    			}
    		} else {
    			throw new InvalidDataException("Given date type is not correct. Expect date type is Start or End, but given date type is "+dateType);
    		}
    	}
    	return result;
    }
    
    /**
     * Get status ID from table p_po.
     * 1--Open
     * 2--Closed
     * 3--Cancelled
     * @param PONum
     * @return
     */
    private List<String> getPONumFromDBByStatus(){
    	String statusID = "";
    	if("Open".equalsIgnoreCase(purchaseOrderInfo.searchStatus)){
    		statusID = "1";
    	} else if("Closed".equalsIgnoreCase(purchaseOrderInfo.searchStatus)){
    		statusID = "2";
    	} else if("Cancelled".equalsIgnoreCase(purchaseOrderInfo.searchStatus)){
    		statusID = "3";
    	} else {
    		throw new ErrorOnDataException("No such status of POS Purchase Order.");
    	}

    	logger.info("Get status ID from table p_po.");
    	AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
    	db.resetSchema(schema);
    	String query = "select id from p_po where status_id = '"+statusID+"' and create_loc_id = 1";
    	List<String> poNumList = db.executeQuery(query, "ID");
    	return poNumList;
    }
    
    /**
     * Search POS purchase order and verify.
     * @param searchBy
     * @param searchValue
     * @return
     */
    private boolean searchAndVerify(String searchBy, String searchValue){
    	logger.info("Search POS purchase order and verify.");
    	
    	// clean up
		posPurchaseOrderPg.clearSearchCriteria();
		posPurchaseOrderPg.searchPOPurchaseOrder(purchaseOrderInfo);

    	// verify
		boolean result = true;
		result &= this.verifySearchResult(searchBy, searchValue);
		return result;
    }
}
