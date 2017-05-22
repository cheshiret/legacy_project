package com.activenetwork.qa.awo.datacollection.legacy.orms;

import java.util.ArrayList;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Jun 18, 2012
 */
public class POSPurchaseOrderInfo {
	// search criteria
	public String searchPONum;
	public String searchStatus;
	public String searchSupplierName;
	public String searchCreationStartDate;
	public String searchCreationEndDate;
	
	// detail info
	public String poNum;
	public String supplierName;
	public String itemOrdered;
	public String status;
	public String creationDate;
	public String receive;
	public String cancelledDate;
	public String closedDate;
	public String purchaseDate;
	public String supplierOrderNum;
	public String startShippingDate;//Start Shipping Date
	public String shippingDate;//Required Shipping Date
	public String buyer;
	public String paymentTerms;
	public String paymentMethod;
	public String shippingMethod;
	public String orderingAddr;
	public String shiptoAddr;
	public String supplierNotes;
	public String internalNotes;
	
	// order item info(size of this list should be the same as item ordered)
	public ArrayList<POSPurchaseOrderItemInfo> orderItemInfo = new ArrayList<POSPurchaseOrderItemInfo>();
	public String subTotal;
	public String discount;
	public String total;
}
