/*
 * Created on Oct 28, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.datacollection.legacy;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinSession {

	public String finSessID;

	public String depositID;
	
	public String openUser;

	public String location;

	public String dateRange;

	public String startDate;

	public String endDate;

	public String sessionType;

	public String status;

	public String adjusted;

	public String searchType;

	public String searchValue;

	public String openDate;
	
	public String closeDate;

	public String closeUser;
	
	public String depositDate;
	
	public String depositUser;
	
	public String openStation;
	
	public int transactions;

	public double transactionTotal;

	public double adjustment;

	public double netTotal;

	public double totalIncludeNonDepositables;
	
	public String openingFloat;

	public double closingFloat;
	
	public double floatAdjustment;
	
	public List<PaymentRecord> paymentRecords = new ArrayList<PaymentRecord>();
	
	public double subTotalAvailableForDepositTotalAmount;
	
	public double subTotalAvailableForDepositTotalOnHand;
	
	public double subTotalAvailableForDepositAdjustment;
	
	public double subTotalAvailableForDepositClosingFloat;
	
	public double totalOtherTotalAmount;
	
	public double totalOtherTotalOnHand;
	
	public double finSessionTotalOnHand;
	
	public FinSession() {

		finSessID = "";
		openUser = "";
		location = "";
		dateRange = "";
		startDate = "";
		endDate = "";
		sessionType = "";
		status = "";
		adjusted = "";
		searchType = "";
		searchValue = "";
		openDate = "";
		closeDate = "";
		transactions = 0;
		transactionTotal = 0;
		adjustment = 0;
		netTotal = 0;
		totalIncludeNonDepositables = 0;
	}
	
	public class OpeningFloat {
		String id;
		String createDate;
		String amount;
		String status;
		String userID;
		String pinUserID;
		String locationID;
		String currencyID;
		String finSessionID;
		String paymentTypeID;
		String paymentGroupID;
		String reversedFloatID;
		String floatAjustmentID;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getCreateDate() {
			return createDate;
		}
		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getUserID() {
			return userID;
		}
		public void setUserID(String userID) {
			this.userID = userID;
		}
		public String getPinUserID() {
			return pinUserID;
		}
		public void setPinUserID(String pinUserID) {
			this.pinUserID = pinUserID;
		}
		public String getLocationID() {
			return locationID;
		}
		public void setLocationID(String locationID) {
			this.locationID = locationID;
		}
		public String getFinSessionID() {
			return finSessionID;
		}
		public void setFinSessionID(String finSessionID) {
			this.finSessionID = finSessionID;
		}
		public String getPaymentTypeID() {
			return paymentTypeID;
		}
		public void setPaymentTypeID(String paymentTypeID) {
			this.paymentTypeID = paymentTypeID;
		}
		public String getPaymentGroupID() {
			return paymentGroupID;
		}
		public void setPaymentGroupID(String paymentGroupID) {
			this.paymentGroupID = paymentGroupID;
		}
		public String getReversedFloatID() {
			return reversedFloatID;
		}
		public void setReversedFloatID(String reversedFloatID) {
			this.reversedFloatID = reversedFloatID;
		}
		public String getFloatAjustmentID() {
			return floatAjustmentID;
		}
		public void setFloatAjustmentID(String floatAjustmentID) {
			this.floatAjustmentID = floatAjustmentID;
		}
		public String getCurrencyID() {
			return currencyID;
		}
		public void setCurrencyID(String currencyID) {
			this.currencyID = currencyID;
		}
	}
	
	public class PaymentRecord {
		private String paymentGroup;
		private String paymentType;
		private int numOfPayments;
		private int numOfRefunds;
		private double openingFloat;
		private double changeTendered;
		private double totalAmount;
		private double totalOnHand;
		private double adjustment;
		private double closingFloat;
		private double floatAdjustment;
		
		public boolean equals(PaymentRecord pr) {
			boolean result = true;
			result &= MiscFunctions.compareResult("Payment Group", this.paymentGroup, pr.paymentGroup);
			if(!StringUtil.isEmpty(this.paymentType)) {
				result &= MiscFunctions.compareResult("Payment Type", this.paymentType, pr.paymentType);
			}
			if(!this.paymentGroup.equalsIgnoreCase("Issued")) {
				result &= MiscFunctions.compareResult("# Payments", this.numOfPayments, pr.numOfPayments);
			}
			if(!this.paymentGroup.equalsIgnoreCase("Redeemed")) {
				result &= MiscFunctions.compareResult("# Refunds", this.numOfRefunds, pr.numOfRefunds);
			}
			if(!StringUtil.isEmpty(this.paymentType) && this.paymentType.equalsIgnoreCase("Cash")) {
				result &= MiscFunctions.compareResult("Opening Float", this.openingFloat, pr.openingFloat);
				result &= MiscFunctions.compareResult("Change Tendered", this.changeTendered, pr.changeTendered);
				result &= MiscFunctions.compareResult("Closing Float", this.closingFloat, pr.closingFloat);
				result &= MiscFunctions.compareResult("Float Adjustment", this.floatAdjustment, pr.floatAdjustment);
			}
			result &= MiscFunctions.compareResult("Total Amount", this.totalAmount, pr.totalAmount);
			if(!this.paymentGroup.matches("Issued|Redeemed")) {
				result &= MiscFunctions.compareResult("Total On Hand", this.totalOnHand, pr.totalOnHand);
			}
			return result;
		}
		
		public String getPaymentGroup() {
			return paymentGroup;
		}
		public void setPaymentGroup(String paymentGroup) {
			this.paymentGroup = paymentGroup;
		}
		public String getPaymentType() {
			return paymentType;
		}
		public void setPaymentType(String paymentType) {
			this.paymentType = paymentType;
		}
		public int getNumOfPayments() {
			return numOfPayments;
		}
		public void setNumOfPayments(int numOfPayments) {
			this.numOfPayments = numOfPayments;
		}
		public int getNumOfRefunds() {
			return numOfRefunds;
		}
		public void setNumOfRefunds(int numOfRefunds) {
			this.numOfRefunds = numOfRefunds;
		}

		public double getOpeningFloat() {
			return openingFloat;
		}

		public void setOpeningFloat(double openingFloat) {
			this.openingFloat = openingFloat;
		}

		public double getChangeTendered() {
			return changeTendered;
		}

		public void setChangeTendered(double changeTendered) {
			this.changeTendered = changeTendered;
		}

		public double getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}

		public double getTotalOnHand() {
			return totalOnHand;
		}

		public void setTotalOnHand(double totalOnHand) {
			this.totalOnHand = totalOnHand;
		}

		public double getAdjustment() {
			return adjustment;
		}

		public void setAdjustment(double adjustment) {
			this.adjustment = adjustment;
		}

		public double getClosingFloat() {
			return closingFloat;
		}

		public void setClosingFloat(double closingFloat) {
			this.closingFloat = closingFloat;
		}

		public double getFloatAdjustment() {
			return floatAdjustment;
		}

		public void setFloatAdjustment(double floatAdjustment) {
			this.floatAdjustment = floatAdjustment;
		}
	}
	
	public class FloatAdjustment {
		private String id;
		private double amount;
		private String floatID;
		private String userID;
		private String pinUserID;
		private String locID;
		private String createDate;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public String getFloatID() {
			return floatID;
		}
		public void setFloatID(String floatID) {
			this.floatID = floatID;
		}
		public String getUserID() {
			return userID;
		}
		public void setUserID(String userID) {
			this.userID = userID;
		}
		public String getPinUserID() {
			return pinUserID;
		}
		public void setPinUserID(String pinUserID) {
			this.pinUserID = pinUserID;
		}
		public String getLocID() {
			return locID;
		}
		public void setLocID(String locID) {
			this.locID = locID;
		}
		public String getCreateDate() {
			return createDate;
		}
		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}
	}
}
