package com.activenetwork.qa.awo.datacollection.legacy.orms;

import java.util.HashMap;

import com.activenetwork.qa.awo.datacollection.legacy.TestData;

public class SupplyInfo extends TestData {
	
	public String id = "";
	
	public String code = "";
	
	public String status = "";
	
	public String name = "";
	
	public String description = "";
	
	public String productGroup = "";
	
	public HashMap<String, Boolean> availableToApp = new HashMap<String, Boolean>();
	
	public String ofPanels = "";
	
	public String fulfillmentParty = "";
	
	public String supplyCost = "";
	
	public String shippingCost = "";
	
	public String maxDailyOrder = "";
	
	public String recorderThreshold = "";
	
	public String recorderMail = "";
	
	public String qtyOnHand = "";

	/**Fields for adjusting POS inventory */
	public String supplyReceivedDate;
	public String adjustmentType;
	public String adjustmentQuantity;
	public String adjustmentNotes = "";
	
	/**Fields for viewing inventory adjustment info*/
	public String transactionDateTime;
	public String adjustmentUser;
	public String adjustmentLocation;
	public String supplyOrderNum;
}
