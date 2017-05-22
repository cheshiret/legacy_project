package com.activenetwork.qa.awo.datacollection.legacy.orms;


import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienInfo;

public class TitleInfo {
	public String titleNum = "";
	public String titleId = "";
	public String status = "";
	public String customer = "";
	public String product = "";
	public String activeLiens = "";
	public String numOfDuplicates = "";
	public String numOfCorrections = "";
	
	public String boatValue = "";
	private String motorValue = "";
	private String dealerValue = "";
	public String purchaseType = null;
	
	public LienInfo lienInfo = new LienInfo();
	
	public LienInfo newLienInfo = new LienInfo();

	private String orderNum;
	
	private String receiptNum;
	
	//vehicle title search criteria
	private String titleSearchType;
	private String titleSearchValue;
	private String productCode;
	private String creationValidFrom;
	private String creationValidTo;
	private String vehicleIdMiNum;
	private String hullIdSerialNum;
	private boolean exactMatch;
	private String vehicleType;
	private String vehicleSearchType;
	private String vehicleSearchValue;
	
	public String getTitleSearchType() {
		return titleSearchType;
	}
	public void setTitleSearchType(String titleSearchType) {
		this.titleSearchType = titleSearchType;
	}
	public String getTitleSearchValue() {
		return titleSearchValue;
	}
	public void setTitleSearchValue(String titleSearchValue) {
		this.titleSearchValue = titleSearchValue;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getCreationValidFrom() {
		return creationValidFrom;
	}
	public void setCreationValidFrom(String creationValidFrom) {
		this.creationValidFrom = creationValidFrom;
	}
	public String getCreationValidTo() {
		return creationValidTo;
	}
	public void setCreationValidTo(String creationValidTo) {
		this.creationValidTo = creationValidTo;
	}
	public String getVehicleIdMiNum() {
		return vehicleIdMiNum;
	}
	public void setVehicleIdMiNum(String vehicleIdMiNum) {
		this.vehicleIdMiNum = vehicleIdMiNum;
	}
	public String getHullIdSerialNum() {
		return hullIdSerialNum;
	}
	public void setHullIdSerialNum(String hullIdSerialNum) {
		this.hullIdSerialNum = hullIdSerialNum;
	}
	public boolean isExactMatch() {
		return exactMatch;
	}
	public void setExactMatch(boolean exactMatch) {
		this.exactMatch = exactMatch;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getVehicleSearchType() {
		return vehicleSearchType;
	}
	public void setVehicleSearchType(String vehicleSearchType) {
		this.vehicleSearchType = vehicleSearchType;
	}
	public String getVehicleSearchValue() {
		return vehicleSearchValue;
	}
	public void setVehicleSearchValue(String vehicleSearchValue) {
		this.vehicleSearchValue = vehicleSearchValue;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getReceiptNum() {
		return receiptNum;
	}
	public void setReceiptNum(String receiptNum) {
		this.receiptNum = receiptNum;
	}
	public String getMotorValue() {
		return motorValue;
	}
	public void setMotorValue(String motorValue) {
		this.motorValue = motorValue;
	}
	public String getDealerValue() {
		return dealerValue;
	}
	public void setDealerValue(String dealerValue) {
		this.dealerValue = dealerValue;
	}
	
	
}
