/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.legacy.orms;

/**
 * @author szhou
 *
 */
public class EFTInvoicingInfo {
	private String searchType;
	
	private String searchValue ;
	
	private String searchDate;
	
	private String invoiceNum ;
	
	private String invoiceJobId;
	
	private String invoiceType;
	
	private String status;
	
	private String transmissionStatus;
	
	private String vendorNum;
	
	private String vendorName;
	
	private String vendorNumName;
	
	private String agentNum;
	
	private String agentNumName;
	
	private String agentName;
	
	private String invoiceGrouping;
	
	private String fromDate;
	
	private String invoiceDate;
	
	private String periodDate;
	
	private String toDate;
	
	private String amount;
	
	private String postdate ;
	
	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String date) {
		this.invoiceDate = date;
	}
	
	public String getPeriodDate() {
		return periodDate;
	}

	public void setPeriodDate(String date) {
		this.periodDate = date;
	}

	
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

	public String getInvoiceNum() {
		return invoiceNum;
	}

	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	public String getInvoiceJobId() {
		return invoiceJobId;
	}

	public void setInvoiceJobId(String invoiceJobId) {
		this.invoiceJobId = invoiceJobId;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransmissionStatus() {
		return transmissionStatus;
	}

	public void setTransmissionStatus(String transmissionStatus) {
		this.transmissionStatus = transmissionStatus;
	}

	public String getVendorNumName() {
		return vendorNumName;
	}

	public void setVendorNumName(String vendorNum, String vendorName) {
		this.vendorNumName = vendorNum + " - " + vendorName;
	}

	public String getAgentNumName() {
		return agentNumName;
	}

	public void setAgentNumName(String agent) {
		this.agentNumName = agent;
	}
	
	
	public String getAgentName() {
		return agentName;
	}

	public void setAgentNumName(String agentNum, String agentName) {
		this.agentNumName = agentNum + "-" + agentName;
	}

	public String getInvoiceGrouping() {
		return invoiceGrouping;
	}

	public void setInvoiceGrouping(String invoiceGrouping) {
		this.invoiceGrouping = invoiceGrouping;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPostdate() {
		return postdate;
	}

	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}

	public String getVendorNum() {
		return vendorNum;
	}

	public void setVendorNum(String vendorNum) {
		this.vendorNum = vendorNum;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getAgentNum() {
		return agentNum;
	}

	public void setAgentNum(String agentNum) {
		this.agentNum = agentNum;
	}

	public void setVendorNumName(String vendorNumName) {
		this.vendorNumName = vendorNumName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
}
