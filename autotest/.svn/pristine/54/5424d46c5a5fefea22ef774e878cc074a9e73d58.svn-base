package com.activenetwork.qa.awo.datacollection.legacy.orms;

import java.util.ArrayList;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  May 17, 2011
 */
public class StoreInfo {
	
	public String storeID = "";
	
	public String status = "";
	
	public String storeName = "";
	
	public String locationClass = "";
	
	public String createDate = "";
	
	public String createUser = "";
	
	public String createLocation = "";
	
	public String agency = "";
	
	public String region = "";
	
	public String location = "";
	
	public Address physicalAddress = new Address();
	
	public Address mailingAddress = new Address();
	
	public Address AlterAddress = new Address();
	
	public String belongedVendorID = "";
	
	public String belongedVendorNum = "";
	
	public String belongedVendorName = "";
	
	public String storeStatusReason = "";
	
	public String timeZone = "";
	
	public boolean isAssignedProductThruLocationClass = false;
	
	public boolean isMailSamePhyAddress = false;
	
	public boolean isNewLocation = false;
	
	public ArrayList<Contacts> contactArray = new ArrayList<Contacts>();
	

	public String storeSearchBy = "";
	
	public String storeSearchByValue = "";
	
	public String oldStoreID = "";
	
	public String translation = "";
	
	public String associatedCustNum = ""; // The associated business customer number
	
	@Override
	public boolean equals(Object object) {
		if(!(object instanceof StoreInfo)) {
			return false;
		}
		
		StoreInfo store = (StoreInfo)object;
		
		if(!store.storeName.equals(this.storeName)) {
			return false;
		}
		if(!store.belongedVendorID.equals(this.belongedVendorID)) {
			return false;
		}
		if(!store.belongedVendorName.equals(this.belongedVendorName)) {
			return false;
		}
		if(!store.storeStatusReason.equals(this.storeStatusReason)) {
			return false;
		}
		if(store.isAssignedProductThruLocationClass != this.isAssignedProductThruLocationClass) {
			return false;
		}
		
		return true;
	}
}
