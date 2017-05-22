package com.activenetwork.qa.awo.datacollection.legacy.orms;

import java.util.ArrayList;

public class OrderInfo {

	public String orderNum = "";
	public String receiptNum = "";
	public String orderDate = "";
	public String product = "";
	public String orderType = "";
	public String billingCustomer = "";
	public String billingCustPhone = "";
	public String billingCustMail = "";
	public String saleLocation = "";
	public String numOfItems = "";
	public String orderPrice = "";
	public String balance = "";
	public String orderPaid = "";
	public String unissuedRefund = "";
	public String confirmStatus = "";
	public String invoiceNum = "";
	public String verificationStatus = "";
	public String creationUser = "";
	public String privilegeNum = "";
	public String orderPastPaid = "";
	public String orderRefund = "";
	public String createDate = "";
	public String orderFromDate = "";
	public String orderToDate = "";
	public String operatorFirstName = "";
	public String operatorLastName = "";
	public String residencyStatus = "";
	public String purchaseType = "";
	public String productCode = "";
	public String productGrp = "";
	public String value = "";
	public String transactionLocation = "";
	public ArrayList<OrderItems> orderList = new ArrayList<OrderItems>();
	public ArrayList<String> transactionList = new ArrayList<String>();
	public String transactionUser = "";
	public String tan = "";//temp_auth_num
	public String status = "";

	/* search criteria */
	public String searchType=null;
	public String searchValue=null;

	/* vehicle order */
	public String hullIdSerialNum=null;
	public String miNum =null;// MI #
	public String vehicleType ="";
	public String vehicleSearchType ="";
	public String vehicleSearchValue =null;
	public String customer = null;
	public String productPurchaseType = null;
	
	public void cleanupVehicleOrderSearchCriteria(){
		this.searchType="Order #";
		this.searchValue="";
		this.hullIdSerialNum="";
		this.miNum="";
		this.vehicleType="";
		this.vehicleSearchType="";
		this.vehicleSearchValue=null;
		this.orderFromDate="";
		this.orderToDate="";
		this.operatorFirstName="";
		this.operatorLastName="";
		this.purchaseType="";
		this.productCode="";
		this.productGrp="";
		this.saleLocation="";
	}
}
