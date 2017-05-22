package com.activenetwork.qa.awo.datacollection.legacy.orms;

/**
 * 
 * @Description: This data collection is used to store Product-Store Assignment records
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA-qchen
 * @Date  May 20, 2011
 */
public class ProductStoreAssignment {
	
	public String productCategory = "";//it should be Privilege, Vehicle and Consumable
	
	public String assignID = "";
	
	public String displayCategory = "";
	
	public String displaySubCategory = "";
	
	public String productCode = "";
	
	public String productName = "";
	
	public String productGroup = "";
	
	public String vehicleType = "";//this field is created for vehicle product
	
	public String orderType = "";//this field is created for consumable product
	
	public boolean isAssigned = false;
	
	public String creationUser = "";
	
	public String creationDateTime = "";
	
	public String assignStatus = "";
	
	public String lastModUser = "";
	
	public String lastModDate = "";
	
	public String species = "";
}
