package com.activenetwork.qa.awo.datacollection.legacy.orms.license;

import java.util.List;

public class ReturnDocumentOrderInfo {
	private List<ReturnDocumentOrderItem> returnDocumentOrderItems;
	private String creditSalesLocation;
	private String comments;
	private String returnDueDate;
	private String customerInfo;
	private String receiptNumInfo;
	private String orderNum;
	private String returnStatus;
	private String purchasedDate;
	private String purchasedLocation;
	private String voidedDate;
	private String voidedLocation;
	private String receiptNum;
	private String storeID;
	private String registerID;
	private String vendorNum;
	
	public void setReturnDocumentOrderItems(List<ReturnDocumentOrderItem> returnDocumentOrderItems){
		this.returnDocumentOrderItems = returnDocumentOrderItems;
	}
	
	public List<ReturnDocumentOrderItem> getReturnDocumentOrderItems(){
		return this.returnDocumentOrderItems;
	}
	
	public void setCreditSalesLocation(String creditSalesLocation){
		this.creditSalesLocation = creditSalesLocation;
	}
	
	public String getCreditSalesLocation(){
		return this.creditSalesLocation;
	}
	
	public void setComments(String comments){
		this.comments = comments;
	}
	
	public String getComments(){
		return this.comments;
	}
	
	public void setReturnDueDate(String returnDueDate){
		this.returnDueDate = returnDueDate;
	}
	
	public String getReturnDueDate(){
		return this.returnDueDate;
	}
	
	public void setCustomerInfo(String firstName, String lastName){
		this.customerInfo = lastName + ", " + firstName;
	}
	
	public void setCustomerInfo(String customerInfo){
		this.customerInfo = customerInfo;
	}
	
	public String getCustomerInfo(){
		return this.customerInfo;
	}
	
	public void setReceiptNumInfo(List<String> receiptNums){
		this.receiptNumInfo = "";
		for(int i=0; i<receiptNums.size(); i++){
			String receiptNum = receiptNums.get(i);
			if(i<receiptNums.size()-1){
				this.receiptNumInfo = this.receiptNumInfo + receiptNum + ",";
			}else{
				this.receiptNumInfo = this.receiptNumInfo + receiptNum ;
			}
		}
	}
	
	public void setReceiptNumInfo(String receiptNumInfo){
		this.receiptNumInfo = receiptNumInfo;
	}
	
	public String getReceiptNumInfo(){
		return this.receiptNumInfo;
	}
	
	public void setOrdNum(String ordNum){
		this.orderNum = ordNum;
	}
	
	public String getOrdNum(){
		return this.orderNum;
	}
	
	public void setReturnStatus(String returnStatus){
		this.returnStatus = returnStatus;
	}
	
	public String getReturnStatus(){
		return this.returnStatus;
	}
	
	public void setPurchasedLocation(String storeID, String storeName){
		this.purchasedLocation = storeID + " - " + storeName;
	}
	
	public void setPurchasedLocation(String purchasedLocation){
		this.purchasedLocation = purchasedLocation;
	}
	
	public String getPurchasedLocation(){
		return this.purchasedLocation;
	}
	
	public void setPurchaseDate(String purchaseDate){
		this.purchasedDate = purchaseDate;
	}
	
	public String getPurchaseDate(){
		return this.purchasedDate;
	}
	
	public void setVoidedLocation(String storeID, String storeName){
		this.voidedLocation = storeID + " - " + storeName;
	}
	
	public void setVoidedLocation(String voidedLocation){
		this.voidedLocation = voidedLocation;
	}
	
	public String getVoidedLocation(){
		return this.voidedLocation;
	}
	
	public void setVoidedDate(String voidedDate){
		this.voidedDate = voidedDate;
	}
	
	public String getVoidedDate(){
		return this.voidedDate;
	}
	
	public void setReceiptNum(String receiptNum){
		this.receiptNum = receiptNum;
	}
	
	public String getReceiptNum(){
		return this.receiptNum;
	}
	
	public void setStoreID(String storeID){
		this.storeID = storeID;
	}
	
	public String getStoreID(){
		return this.storeID;
	}
	
	public void setRegisterID(String registerID){
		this.registerID = registerID;
	}
	
	public String getRegisterID(){
		return this.registerID;
	}
	
	public void setVendorNum(String vendorNum){
		this.vendorNum = vendorNum;
	}
	
	public String getVendorNum(){
		return this.vendorNum;
	}
	
	public static class ReturnDocumentOrderItem{
		private String privilegeNum;
		private String productInfo;
		private String licenseYear;
		private String transactionType;
		private String validFromDate;
		private String validToDate;
		
		public void setPrivilegeNum(String privilegeNum){
			this.privilegeNum = privilegeNum;
		}
		
		public String getPrivilegeNum(){
			return this.privilegeNum;
		}
		
		public void setProductInfo(String prdCode, String prdName){
			this.productInfo = prdCode + " - " + prdName;
		}
		
		public void setProductInfo(String productInfo){
			this.productInfo = productInfo;
		}
		
		public String getProductInfo(){
			return this.productInfo;
		}
		
		public void setLicenseYear(String licenseYear){
			this.licenseYear = licenseYear;
		}
		
		public String getLicenseYear(){
			return this.licenseYear;
		}
		
		public void setTransactionType(String transactionType){
			this.transactionType = transactionType;
		}
		
		public String getTransactionType(){
			return this.transactionType;
		}
		
		public void setValidFromDate(String validFromDate){
			this.validFromDate = validFromDate;
		}
		
		public String getValidFromDate(){
			return this.validFromDate;
		}
		
		public void setValidToDate(String validToDate){
			this.validToDate = validToDate;
		}
		
		public String getValidToDate(){
			return this.validToDate;
		}
		
	}
	

}
