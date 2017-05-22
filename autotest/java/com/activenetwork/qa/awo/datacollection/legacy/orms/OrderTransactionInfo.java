/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.legacy.orms;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author szhou
 * @Date  Apr 27, 2012
 */
public class OrderTransactionInfo {
      private String order;
      
      private String orderitem;
      
      private String transtype;
      
      private String transTypeCode;
      
      private String transOccCode;
      
      private String transdate;
      
      private String transinfo;
      
      private String product;
      
      private String user;
      
      private String pinuser;
      
      private String saleschannel;
      
      private String application;
      
      private String location;
      
      private String station;
      
      private String createdate;
      
      private String transReasonCode;
      
      private String transReasonDSCR;
      
      public void setOrderID(String order){
    	  this.order=order;
      }
      
      public String getOrderID(){
    	  return this.order;
      }
      
      public void setOrderItemID(String orderitem){
    	  this.orderitem=orderitem;
      }
      
      public String getOrderItemID(){
    	  return this.orderitem;
      }
      
      public void setTransactionType(String transtype){
    	  this.transtype=transtype;
      }
      
      public String getTransactionType(){
    	  return this.transtype;
      }
      
      public void setTransactionOccurCode(String transOccCode){
    	  this.transOccCode = transOccCode;
      }
      
      public String getTransactionOccurCode(){
    	  return this.transOccCode;
      }
      
      public void setTransactionDate(String date){
    	  this.transdate=date;
      }
      
      public String getTransactionDate(){
    	  return this.transdate;
      }
      
      public void setTransactionInfo(String info){
    	  this.transinfo=info;
      }
      
      public String getTransactionInfo(){
    	  return this.transinfo;
      }
      
      public void setProduct(String product){
    	  this.product=product;
      }
      
      public String getProduct(){
    	  return this.product;
      }
      
      public void setUser(String user){
    	  this.user=user;
      }
      
      public String getUser(){
    	  return this.user;
      }
      
      public void setPinUser(String user){
    	  this.pinuser=user;
      }
      
      public String getPinUser(){
    	  return this.pinuser;
      }
      
      public void setSalesChannel(String saleschannel){
    	  this.saleschannel=saleschannel;
      }
      
      public String getSalesChannel(){
    	  return this.saleschannel;
      }
      
      public void setLocation(String location){
    	  this.location=location;
      }
      
      public String getLocation(){
    	  return this.location;
      }
      
      public void setStation(String station){
    	  this.station=station;
      }
      
      public String getStation(){
    	  return this.station;
      }
      
      public void setCreateDate(String date){
    	  this.createdate=date;
      }
      
      public String getCreateDate(){
    	  return this.createdate;
      }

      public String getTransactionTypeCode() {
    	  return transTypeCode;
      }

      public void setTransactionTypeCode(String transTypeCode) {
    	  this.transTypeCode = transTypeCode;
      }
      
      public void setTransactionReasonCode(String transReasonCode){
    	  this.transReasonCode = transReasonCode;
      }
      
      public String getTransactionReasonCode(){
    	  return this.transReasonCode;
      }
      
      public void setTransactionReasonDSCR(String transReasonDSCR){
    	  this.transReasonDSCR = transReasonDSCR;
      }
      
      public String getTransactionReasonDSCR(){
    	  return this.transReasonDSCR;
      }

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}
}
