package com.activenetwork.qa.awo.vpData;

import java.util.regex.Pattern;

/**
 * 
 * @author CGuo
 *
 * this class is used to indicate the Fee Schedule verification data which is 
 * combination of "feeType", "feeAmount" and "feeSchId". 
 * 
 * that means fee schedule is considered as same only when feeType is same, 
 * fee amount is equal and fee schedule ID is same.  
 * 
 */

public class VpDistribution extends VpData {

	public String payment;

	public String order;

	public String location;

	public String salesChannel;

	public String itemType;

	public String revenue;

	public String tax;

	public String expense;

	public String date;

	public String transactionType;

	public String transOccurence;

	public String account;

	public VpDistribution() {
		payment = "";
		order = "";
		location = "";
		salesChannel = "";
		itemType = "";
		revenue = "";
		tax = "";
		expense = "";
		date = "";
		transactionType = "";
		transOccurence = "";
		account = "";
		tested = false;
	}

	public VpDistribution(String payment, String order, String location,
			String salesChannel, String itemType, String expense, String date,
			String transactionType, String transOccurence, String account,
			boolean tested) {
		this.payment = payment;
		this.order = order;
		this.location = location;
		this.salesChannel = salesChannel;
		this.itemType = itemType;
		this.expense = expense;
		this.date = date;
		this.transactionType = transactionType;
		this.transOccurence = transOccurence;
		this.account = account;
		this.tested = tested;
	}

	public boolean compare(Object obj) {
		VpDistribution vp = (VpDistribution) obj;

		return Pattern.matches(vp.account, this.account)
				&& Pattern.matches(vp.date, this.date)
				&& Pattern.matches(vp.expense, this.expense)
				&& Pattern.matches(vp.itemType, this.itemType)
				&& Pattern.matches(vp.location, this.location)
				&& Pattern.matches(vp.order, this.order)
				&& Pattern.matches(vp.payment, this.payment)
				&& Pattern.matches(vp.salesChannel, this.payment)
				&& Pattern.matches(vp.transactionType, this.transactionType)
				&& Pattern.matches(vp.transOccurence, this.transOccurence)
				&& vp.tested == this.tested;

	}

}
