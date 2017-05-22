/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.legacy.orms;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: this dataCollection used for Privilege, Vehicle, Consumable,
 *               Supply in license manger
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * @Defects:
 * 
 * @author asun
 * @Date Jul 21, 2011
 */
public class PricingInfo {
	public String prodCode = "";

	public String prodType = "";

	public String id = "";

	public String status = "";

	public String locationClass = "";

	public String licYearFrom = "";

	public String licYearTo = "";

	public String purchaseType = "";

	public String effectFrom = "";

	public String effectTo = "";

	public String vendorFee = "";

	public String stateTransFee = "";

	public String stateFee = "";

	public String stateFee_SplitBy = "";

	public String stateFee_SplitInto = "";

	public List<String[]> stateFee_accounts = new ArrayList<String[]>();

	public String transFee = "";

	public String transFee_SplitBy = "";

	public String transFee_SplitInto = "";

	public List<String[]> transFee_accounts = new ArrayList<String[]>();

	public String createUser = "";

	public String createLocation = "";

	public String createTime = "";

	public String lastUpdateUser = "";

	public String lastUpdateLocation = "";

	public String lastUpdateTime = "";

	public String customerPrice;

	public String totalPrice;
	/*
	 * 3.03 new feature.
	 */
	public boolean isAppliesToAllState = true;

	public String addState;

	public String removeState;

	public String stateProvinces;

	// for Lottery product
	public String rateType;
	public String stateVendorFee;
	public boolean keepPreviousPriceDuringChangeIfHigher = false;
	public boolean calculateHoldingFeeBasedOnSelectedChoices = false;
	public String holdingFee;
	public String holdingFeeAccount;

	public List<ChoiceFee> choiceFees = new ArrayList<ChoiceFee>();

	public class ChoiceFee {
		public String range;
		public String vendorFee;
		public String stateFee;
		public String transFee;
	}

	public List<TaxInfo> vendorTaxes = new ArrayList<TaxInfo>();

	public List<TaxInfo> stateTaxes = new ArrayList<TaxInfo>();

	public List<TaxInfo> transactionTaxes = new ArrayList<TaxInfo>();
	
	public class TaxInfo {
		private boolean isFlat;
		
		private String taxname;

		private String rate;

		private String taxCalRate;

//		private String taxamt;

		private String taxAdjustRate;
		
		private String taxSplitType;
		
		private String purchaseType;
		
		private String splitnum;
		
		//updated by Summer.Date 2014/6.13 reason: handling split tax into more than two accounts. please put additional account to this list 
		private List<String[]> accounts=new ArrayList<String[]>();
		
		public void setTaxName(String name) {
			this.taxname = name;
		}

		public String getTaxName() {
			return this.taxname;
		}

		public void setRate(String rate) {
			this.rate = rate;
		}

		public String getRate() {
			return this.rate;
		}


		public void setTaxCalculationRate(String rate) {
			this.taxCalRate = rate;
		}

		public String getTaxCalculationRate() {
			return this.taxCalRate;
		}

//		public void setTaxAmount(String amount) {
//			this.taxamt = amount;
//		}
//
//		public String getTaxAmount() {
//			return this.taxamt;
//		}

		public void setTaxAdjustRate(String rate) {
			this.taxAdjustRate = rate;
		}

		public String getTaxAdjustRate() {
			return this.taxAdjustRate;
		}
		
		public void setTaxSplitType(String splitType) {
			this.taxSplitType = splitType;
		}

		public String getTaxSplitType() {
			return this.taxSplitType;
		}
		
		public void setPurchaseType(String purchaseType) {
			this.purchaseType = purchaseType;
		}

		public String getPurchaseType() {
			return this.purchaseType;
		}
		
		public void setSplitNumberInfo(String num){
			this.splitnum = num;
		}
		
		public String getSplitNumberInfo(){
			return this.splitnum;
		}
		
		public void addAccount(String[] string){
			this.accounts.add(string);
		}
		public String [] removeSplitAccount(int index){
			return accounts.remove(index);
		}
		
		public String[] getAccount(int i){
			return this.accounts.get(i);
		}
		public List<String[]> getAccounts(){
			return this.accounts;
		}
		public void setIsFlat(boolean isFlat){
			this.isFlat = isFlat;
		}
		
		public boolean getIsFlat(){
			return this.isFlat;
		}
	}

	@Override
	public PricingInfo clone() {
		PricingInfo pricing = new PricingInfo();

		pricing.status = this.status;
		pricing.prodCode = this.prodCode;
		pricing.prodType = this.prodType;
		pricing.locationClass = this.locationClass;
		pricing.licYearFrom = this.licYearFrom;
		pricing.licYearTo = this.licYearTo;
		pricing.purchaseType = this.purchaseType;
		pricing.effectFrom = this.effectFrom;
		pricing.vendorFee = this.vendorFee;
		pricing.stateTransFee = this.stateTransFee;

		pricing.stateFee = this.stateFee;
		pricing.stateFee_SplitBy = this.stateFee_SplitBy;
		pricing.stateFee_SplitInto = this.stateFee_SplitInto;
		pricing.stateFee_accounts = this.stateFee_accounts;

		pricing.transFee = this.transFee;
		pricing.transFee_accounts = this.transFee_accounts;
		pricing.transFee_SplitBy = this.transFee_SplitBy;
		pricing.transFee_SplitInto = this.transFee_SplitInto;

		return pricing;
	}

	
}
