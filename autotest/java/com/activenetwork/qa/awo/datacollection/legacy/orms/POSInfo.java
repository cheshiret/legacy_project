package com.activenetwork.qa.awo.datacollection.legacy.orms;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.TestData;

public class POSInfo extends TestData {
	//	POS parameters
	public String product = "";
	
	public String productForSearch; 
	
	public String productID = "";
	
	public String status = "";

	public String varPrice = "";

	public String quantity = "";//purchase POS quantity

	public String qtyOnHand = "";//POS inventory
	
	public String physicalQtyOnHand = "";
	
	public String price = "";

	public String ordID = ""; //reservation# for pos charge
	
	public String invoiceID = ""; //Invoice# for pos order
	
	public String barcode = "";
	//Pos product info.
	public String orderType = "";
	
	public String productCode = "";
	
	public String productClass = "";
	
	public String productSubClass = "";
	
	public String productGroup = "";
	
	public String productRelactionshipType;
	
	public String inventoryType = "";
	
	public String productDescription = "";
	
	public String availableLocation = "";
	
	public String acquierZipCodeInSale = "";
	
	public String applicationCustomer="";
	
	public String state = ""; // POS Attributes: In/Out State
	
	public String numOfOccupants = "";
	
	public String vehicle = ""; // Vehicle: No / Yes
	
	public String defaultUnitPrice = "";

	public String overrideUnitPrice = ""; //No/Yes
	
	public String account = "";
	
	public String assignToSupplier = "";
	
	public String searchByAssignStatus = "";
	
	public String supplierProductCode = "";
	
	public String unitCost = "";
	
	public ArrayList<BarCode> barcodeList = new ArrayList<BarCode>();
	
	public ArrayList<PosSalesAttributes> attributesArray = new ArrayList<PosSalesAttributes>();
	
	public ArrayList<String> passNums = new ArrayList<>();
	
	public String serilizType = "";
	
	public String serilizReferenceId = "";
	
	public String serilizeNumberType = "";
	
	public String serilizFormat = "";
	
	public String serilizIncrement = "";
	
	public String serilizRule = "";
	
	public boolean specificLocation = false;//default value is false
	
	public RevenueLocation revLocation = new RevenueLocation();
	
	public AdjustInventory adjustInv = new AdjustInventory();
	
	public AdjustPendingInventory adjPendingInv = new AdjustPendingInventory();
	
	public InventoryTrackingLog invTracking = new InventoryTrackingLog();
	
	public PosSalesDetailInfo saleInfo = new PosSalesDetailInfo();
	
	public class RevenueLocation{
		public String agency = "";
		
		public String region = "";
		
		public String district = "";
		
		public String project = "";
		
		public String facility = "";
	}
	
	//for adjust inventory
	public class AdjustInventory{
		public String averageCost = "";
		
		public String supplier = "";
		
		public String suppliesRecievedDate = "";
		
		public String costPerUnit = "";
		
		public String adjustAction = "";
		
		public String adjustQty = "";
		
		public String adjustReason = "";
		
		public String note = "";
	}
	

	//for adjust pending inventory
	public class AdjustPendingInventory{
		public String qtyToAdd = "";
		
		public String clearAnyPendingQty = "";
		
		public String note = "";
	}
	
	//for adjust inventory tracking
	public static class InventoryTrackingLog{
		/**For query*/
		public String userName = "";		
		public String actionDetail = "";		
		public String actionType = "";		
		public String startDate = "";
		public String endDate = "";
		/**For query*/
		
		/**only For query result*/
		public String dateTime = "";
		public String action = "";
		public String notes = "";
		/**only For query result*/
		
	}
	
	public class PosSalesAttributes{
		
		public String attributes = "";
		
		public String displaySequence = "";
		
		public String mandatory = "";
		
		public String active = "";
		
		public boolean isRemove = false;
	}
	
	public class BarCode{
		public String barCode = "";
		
		public boolean isRemove = false;
	}
	
	//Just for field manager.
	public String variablePriceAllowed = "";
	
	public String partialQuantityAllowed = "";
	
	public String displayInFieldManagerMobile = "";
	
	public String displayOrder = "";
	
	public String effectiveSalesStartDate = "";
	
	public String effectiveSalesEndDate = "";
	
	public String effectiveStartDate = "";
	
	public String effectiveEndDate = "";
	
	public String variablePriceIndicator = "";
	
	public String partialQuantityIndicator = "";
	
	public String displayOrderIndetailPg = "";
	
	public String unitPrice = "";
	
	public String extraDecimalIndicator = "No";//default value
	
	//customer
	public String fName = "";

	public String lName = "";
	
	public String email ="";
	
	public String alert="";
	
	public String alertStartDate="";
	
	public String alertEndDate="";

	//index if there are multiple same POS items
	public String index = "";

	//General purpose parameters for move pos or reservation transactions
	public String source = "";

	public String target = "";

	public String sourceID = "";

	public String targetID = "";

	public String note = "Automation Regression";

	public String reason = "2 - Cust Service";

	public String returnQty = "";

	public String group;
	
	public String assignStatus = "";
	
	// For search pos product
	public String effectiveDateType; 
	public boolean isVariablePrice;
	public boolean isPartialQuantityAllowed;
	
	//For Request stock transfer
	public String stockTransferID = "";
	public String qtyToRequestForStockTransfer = "";
	public String stockTransferNotes = "";
	public String fulfillmentLocation = "";
	public String stockTransStartDate = "";
	public String stockTransEndDate = "";
	public String stockTransferStatus = "";
	public String stockTransferType = "";
	
	// added by Nicole, PCR 2956
	public boolean isShowProductPackagesOnly = false;// default value in page is uncheck
	
	public String allocateStatus;
	
	public class PosSalesDetailInfo{
		public String saleDate = "";
		public String saleLocation = "";
		public String createBy = "";
		public String orderStatus = "";
		public String paid = "";
		public String unIssuedRefund = "";
		public String balance = "";
		public String collectionStatus = "";
	}

	public String childPOSName;
	public String childPOSVariableUnitPrice;
	public String childPOSAttr_Color;
	public String childPOSAttr_VehiclePlate;
	
	public String serializedRangeFrom;
	public String serializedRangeTo;
	
	//Boat Launch Permit
	public String permitNum = "";
}
