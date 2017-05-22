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
 * @author pzhu
 * @Date  Jul 18, 2012
 */
public class EFTPaymentAllocationRecord {
	private String ordNum;
	private String paymentID;
	private String invoiceNum;
	private String remittanceNum;
	private String agentID;
	private String agentName;
	private String productCategory;
	private String productCode;
	private String productName;
	private String accountCode;
	private String accountDescription;
	
	/*-->Following variable used in page: LicMgrStoreEFTInvoicePaymentAllocationWidget*/
	/*For display record*/
	private String dailyRecordID;
	private String OrdNumAndPaymentID;
	private String prodAndAccount;
	private String feeTransTypeAndAllocationTransType;
	private String diffLocAndDailyType;
	private String feeType;
	private String amount;
	
	private String feeTransType;
	private String allocTransType;
	private String diffLocation;
	private String dailyEFTType;
	
	
	/*For search criteria*/
	private String prdCodeAndName;
	private String accountCodeAndDesc;
	private String user;
	private String register;
	
	/*--<Above variable used in page: LicMgrStoreEFTInvoicePaymentAllocationWidget*/
	
	
	
	
	/**
	 * @return the ordNum
	 */
	public String getOrdNum() {
		return ordNum;
	}
	/**
	 * @return the feeTransType
	 */
	public String getFeeTransType() {
		return feeTransType;
	}
	/**
	 * @param feeTransType the feeTransType to set
	 */
	public void setFeeTransType(String feeTransType) {
		this.feeTransType = feeTransType;
	}
	/**
	 * @return the allocTransType
	 */
	public String getAllocTransType() {
		return allocTransType;
	}
	/**
	 * @param allocTransType the allocTransType to set
	 */
	public void setAllocTransType(String allocTransType) {
		this.allocTransType = allocTransType;
	}
	/**
	 * @return the diffLocation
	 */
	public String getDiffLocation() {
		return diffLocation;
	}
	/**
	 * @param diffLocation the diffLocation to set
	 */
	public void setDiffLocation(String diffLocation) {
		this.diffLocation = diffLocation;
	}
	/**
	 * @return the dailyEFTType
	 */
	public String getDailyEFTType() {
		return dailyEFTType;
	}
	/**
	 * @param dailyEFTType the dailyEFTType to set
	 */
	public void setDailyEFTType(String dailyEFTType) {
		this.dailyEFTType = dailyEFTType;
	}
	/**
	 * @return the prdCodeAndName
	 */
	public String getPrdCodeAndName() {
		return prdCodeAndName;
	}
	/**
	 * @param prdCodeAndName the prdCodeAndName to set
	 */
	public void setPrdCodeAndName(String prdCodeAndName) {
		this.prdCodeAndName = prdCodeAndName;
	}
	/**
	 * @return the accountCodeAndDesc
	 */
	public String getAccountCodeAndDesc() {
		return accountCodeAndDesc;
	}
	/**
	 * @param accountCodeAndDesc the accountCodeAndDesc to set
	 */
	public void setAccountCodeAndDesc(String accountCodeAndDesc) {
		this.accountCodeAndDesc = accountCodeAndDesc;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return the register
	 */
	public String getRegister() {
		return register;
	}
	/**
	 * @param register the register to set
	 */
	public void setRegister(String register) {
		this.register = register;
	}
	/**
	 * @return the dailyRecordID
	 */
	public String getDailyRecordID() {
		return dailyRecordID;
	}
	/**
	 * @param dailyRecordID the dailyRecordID to set
	 */
	public void setDailyRecordID(String dailyRecordID) {
		this.dailyRecordID = dailyRecordID;
	}
	/**
	 * @return the ordNumAndPaymentID
	 */
	public String getOrdNumAndPaymentID() {
		return OrdNumAndPaymentID;
	}
	/**
	 * @param ordNumAndPaymentID the ordNumAndPaymentID to set
	 */
	public void setOrdNumAndPaymentID(String ordNumAndPaymentID) {
		OrdNumAndPaymentID = ordNumAndPaymentID;
	}
	/**
	 * @return the prodAndAccount
	 */
	public String getProdAndAccount() {
		return prodAndAccount;
	}
	/**
	 * @param prodAndAccount the prodAndAccount to set
	 */
	public void setProdAndAccount(String prodAndAccount) {
		this.prodAndAccount = prodAndAccount;
	}
	/**
	 * @return the feeTypeAndAllocationType
	 */
	public String getFeeTransTypeAndAllocationTransType() {
		return feeTransTypeAndAllocationTransType;
	}
	/**
	 * @param feeTypeAndAllocationType the feeTypeAndAllocationType to set
	 */
	public void setFeeTransTypeAndAllocationTransType(String feeTypeAndAllocationType) {
		this.feeTransTypeAndAllocationTransType = feeTypeAndAllocationType;
	}
	/**
	 * @return the diffLocAndDailyType
	 */
	public String getDiffLocAndDailyType() {
		return diffLocAndDailyType;
	}
	/**
	 * @param diffLocAndDailyType the diffLocAndDailyType to set
	 */
	public void setDiffLocAndDailyType(String diffLocAndDailyType) {
		this.diffLocAndDailyType = diffLocAndDailyType;
	}
	/**
	 * @return the feeType
	 */
	public String getFeeType() {
		return feeType;
	}
	/**
	 * @param feeType the feeType to set
	 */
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * @param ordNum the ordNum to set
	 */
	public void setOrdNum(String ordNum) {
		this.ordNum = ordNum;
	}
	/**
	 * @return the paymentID
	 */
	public String getPaymentID() {
		return paymentID;
	}
	/**
	 * @param paymentID the paymentID to set
	 */
	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}
	/**
	 * @return the invoiceNum
	 */
	public String getInvoiceNum() {
		return invoiceNum;
	}
	/**
	 * @param invoiceNum the invoiceNum to set
	 */
	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}
	/**
	 * @return the remittanceNum
	 */
	public String getRemittanceNum() {
		return remittanceNum;
	}
	/**
	 * @param remittanceNum the remittanceNum to set
	 */
	public void setRemittanceNum(String remittanceNum) {
		this.remittanceNum = remittanceNum;
	}
	/**
	 * @return the agentID
	 */
	public String getAgentID() {
		return agentID;
	}
	/**
	 * @param agentID the agentID to set
	 */
	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}
	/**
	 * @return the agentName
	 */
	public String getAgentName() {
		return agentName;
	}
	/**
	 * @param agentName the agentName to set
	 */
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	/**
	 * @return the productCategory
	 */
	public String getProductCategory() {
		return productCategory;
	}
	/**
	 * @param productCategory the productCategory to set
	 */
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}
	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the accountCode
	 */
	public String getAccountCode() {
		return accountCode;
	}
	/**
	 * @param accountCode the accountCode to set
	 */
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	/**
	 * @return the accountDescription
	 */
	public String getAccountDescription() {
		return accountDescription;
	}
	/**
	 * @param accountDescription the accountDescription to set
	 */
	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}
	
	
	
	
	
	
	
}
