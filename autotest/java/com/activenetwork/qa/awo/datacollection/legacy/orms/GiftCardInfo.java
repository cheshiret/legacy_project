package com.activenetwork.qa.awo.datacollection.legacy.orms;

public class GiftCardInfo {
	//	Gift Card parameters
	public String giftCardNum = "";
	public String status = "";
	public String giftCardProgrameID = "";
	public String giftCardProgrameName = "";
	public String giftCardSaleID = ""; // "Gift Card Sale #", reservation# for gift card charge
	public String invoiceNum = "";
	public String receiptNum = "";
	public String dateType = "";
	//below fields are usually used search
  	public String fromDate = "";
  	public String endDate = "";
  	public String location = "";

	public String operatorLName = "";
	public String operatorFName = "";
	public String custPhone = "";
	public String lName = "";
	public String fName = "";
	public String email ="";
	
	/**these field only used for search list result in OrmsGiftCardSaleSearchPage*/
	public String availableBalance = "";
	public String custLNameAndFName = "";
	public String createDate = "";
	/**these field only used for search list result*/
	
	
	
	
	
	
	
	//customer
	public String amount = "";
  	
  	public String voidReason = "";

}
