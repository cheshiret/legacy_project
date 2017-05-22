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
 * @Date  Jun 4, 2012
 */
public class EFTRemittanceInfo {
	
	private String remittanceNum;
	
	private String invoiceID;
	
	private String invoiceJobID;
	
	private String vendorID;
	
	private String vendorNum;
	
	private String vendorName;//
	
	private String vendorNumAndName;
	
	private String storeID;//
	
	private String storeName;//
	
	private String storeIDAndName;
	
	private String accountCode;//
	
	private String accountDesc;//
	
	private String accountCodeAndDesc;
	
	private String remittanceDate;
	
	private String periodDate;
	
	private String status;//
	
	private String amount;
	
	/**Only for Search on FinMgrRemittancePage--->*/
	private String searchType;
	private String searchValue;
	/**Only for Search on FinMgrRemittancePage*/
	
	
	
	/**
	 * @return the searchType
	 */
	public String getSearchType() {
		return searchType;
	}

	/**
	 * @return the vendorNumAndName
	 */
	public String getVendorNumAndName() {
		return vendorNumAndName;
	}

	/**
	 * @param vendorNumAndName the vendorNumAndName to set
	 */
	public void setVendorNumAndName(String vendorNumAndName) {
		this.vendorNumAndName = vendorNumAndName;
	}

	/**
	 * @return the storeIDAndName
	 */
	public String getStoreIDAndName() {
		return storeIDAndName;
	}

	/**
	 * @param storeIDAndName the storeIDAndName to set
	 */
	public void setStoreIDAndName(String storeIDAndName) {
		this.storeIDAndName = storeIDAndName;
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
	 * @return the accountDesc
	 */
	public String getAccountDesc() {
		return accountDesc;
	}

	/**
	 * @param accountDesc the accountDesc to set
	 */
	public void setAccountDesc(String accountDesc) {
		this.accountDesc = accountDesc;
	}

	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	/**
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * @param searchValue the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	
	
	public void setRemittanceNumber(String num){
		this.remittanceNum=num;
	}
	
	public String getRemittanceNumber(){
		return this.remittanceNum;
	}
	
	public void setInvoiceID(String id){
		this.invoiceID=id;
	}
	
	public String getInvoiceID(){
		return this.invoiceID;
	}
	
	public void setInvoiceJobID(String id){
		this.invoiceJobID=id;
	}
	
	public String getInvoiceJobID(){
		return this.invoiceJobID;
	}
	
	public void setVendorID(String vendor){
		this.vendorID=vendor;
	}
	
	public String getVendorID(){
		return this.vendorID;
	}
	
	public void setVendorNum(String num){
		this.vendorNum=num;
	}
	
	public String getVendorNum(){
		return this.vendorNum;
	}
	
	public void setVendorName(String name){
		this.vendorName=name;
	}
	
	public String getVendorName(){
		return this.vendorName;
	}
	
	public void setStoreID(String store){
		this.storeID=store;
	}
	
	public String getStoreID(){
		return this.storeID;
	}
	
	public void setStoreName(String name){
		this.storeName=name;
	}
	
	public String getStoreName(){
		return this.storeName;
	}
	
	public void setAccountCode(String account){
		this.accountCode=account;
	}
	
	public String getAccountCode(){
		return this.accountCode;
	}
	
	public void setRemittanceDate(String date){
		this.remittanceDate=date;
	}
	
	public String getRemittanceDate(){
		return this.remittanceDate;
	}
	
	public void setPeriodDate(String date){
		this.periodDate=date;
	}
	
	public String getPeriodDate(){
		return this.periodDate;
	}
	
	public void setStatus(String status){
		this.status=status;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public void setAmount(String amount){
		this.amount=amount;
	}
	
	public String getAmount(){
		return this.amount;
	}

}
