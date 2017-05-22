package com.activenetwork.qa.awo.datacollection.legacy.orms.license;


public class EFTAdjustmentInfo {
	private String id;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	private String adjustmentType;
	
	private String reason;
	
	private String note;
	
	private String totalAdjAmount;
	
	private String splitInto;
	
	private String account;
	
	private String invoicingStatus;
	
	private String postedFromDate;
	
	private String postedToDate;
	
	public void setAdjustmentType(String adjType){
		this.adjustmentType=adjType;
	}
	
	public String getAdjustmentType(){
		return this.adjustmentType;
	}
	
	public void setNote(String note){
		this.note=note;
	}
	
	public String getNote(){
		return this.note;
	}
	
	public void setReason(String reason){
		this.reason=reason;
	}
	
	public String getReason(){
		return this.reason;
	}
	
	public void setTotalAdjustmentAmount(String totalAdjAmount){
		this.totalAdjAmount=totalAdjAmount;
	}
	
	public String getTotalAdjustmentAmount(){
		return this.totalAdjAmount;
	}
	
	public void setSplitInto(String split){
		this.splitInto=split;
	}
	
	public String getSplitInto(){
		return this.splitInto;
	}
	
	public void setAccount(String account){
		this.account=account;
	}
	
	public String getAccount(){
		return this.account;
	}
	
	public void setInvoicingStatus(String status){
		this.invoicingStatus=status;
	}
	
	public String getInvoicingStatus(){
		return this.invoicingStatus;
	}
	
	public void setPostedFromDate(String postedFromDate){
		this.postedFromDate=postedFromDate;
	}
	
	public String getPostedFromDate(){
		return this.postedFromDate;
	}
	
	public void setPostedToDate(String postedToDate){
		this.postedToDate=postedToDate;
	}
	
	public String getPostedToDate(){
		return this.postedToDate;
	}
}
