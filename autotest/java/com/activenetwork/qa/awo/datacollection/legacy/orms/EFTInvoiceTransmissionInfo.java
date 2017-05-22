/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.legacy.orms;

/**
 * @author szhou
 *
 */
public class EFTInvoiceTransmissionInfo {
	private String invoiceId;
	
	private String status;
	
	private String transDueDate;
	
	private String createDate;
	
	/*Used in on FinMgrInvoiceTransmissionPage*/
	private String recordID;
	private String transmissionID;
	private String transJobID;
	private String invoiceAdjID;
	private String invoicePaymentID;
	private String applyAmount;
	/*Used in on FinMgrInvoiceTransmissionPage*/
	
	/* used in view transmission summary page in License Manger*/
	private String invoiceAmount;
	private String sale;
	private String voidPendingDocReturn;
	private String chargedVoids;
	private String creditedVoids;
	private String deductedVendorFee;
	private String storeEFTAdj;
	private String depositAdj;
	private String paymentApplied;
	/* used in view transmission summary page in License Manger*/

	public String getInvoiceId() {
		return invoiceId;
	}

	/**
	 * @return the recordID
	 */
	public String getRecordID() {
		return recordID;
	}

	/**
	 * @param recordID the recordID to set
	 */
	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}

	/**
	 * @return the transmissionID
	 */
	public String getTransmissionID() {
		return transmissionID;
	}

	/**
	 * @param transmissionID the transmissionID to set
	 */
	public void setTransmissionID(String transmissionID) {
		this.transmissionID = transmissionID;
	}

	/**
	 * @return the transJobID
	 */
	public String getTransJobID() {
		return transJobID;
	}

	/**
	 * @param transJobID the transJobID to set
	 */
	public void setTransJobID(String transJobID) {
		this.transJobID = transJobID;
	}

	/**
	 * @return the invoiceAdjID
	 */
	public String getInvoiceAdjID() {
		return invoiceAdjID;
	}

	/**
	 * @param invoiceAdjID the invoiceAdjID to set
	 */
	public void setInvoiceAdjID(String invoiceAdjID) {
		this.invoiceAdjID = invoiceAdjID;
	}

	/**
	 * @return the invoicePaymentID
	 */
	public String getInvoicePaymentID() {
		return invoicePaymentID;
	}

	/**
	 * @param invoicePaymentID the invoicePaymentID to set
	 */
	public void setInvoicePaymentID(String invoicePaymentID) {
		this.invoicePaymentID = invoicePaymentID;
	}

	/**
	 * @return the applyAmount
	 */
	public String getApplyAmount() {
		return applyAmount;
	}

	/**
	 * @param applyAmount the applyAmount to set
	 */
	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTransDueDate() {
		return transDueDate;
	}

	public void setTransDueDate(String date) {
		this.transDueDate = date;
	}
	
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String date) {
		this.createDate = date;
	}
	
	public String getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	
	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}
	
	public String getVoidPendingDocReturn() {
		return voidPendingDocReturn;
	}

	public void setVoidPendingDocReturn(String voidPendingDocReturn) {
		this.voidPendingDocReturn = voidPendingDocReturn;
	}
	
	public String getChargedVoids() {
		return chargedVoids;
	}

	public void setChargedVoids(String chargedVoids) {
		this.chargedVoids = chargedVoids;
	}
	
	public String getCreditedVoids() {
		return creditedVoids;
	}

	public void setCreditedVoids(String creditedVoids) {
		this.creditedVoids = creditedVoids;
	}
	
	public String getDeductedVendorFee() {
		return deductedVendorFee;
	}

	public void setDeductedVendorFee(String deductedVendorFee) {
		this.deductedVendorFee = deductedVendorFee;
	}
	
	public String getStoreEFTAdj() {
		return storeEFTAdj;
	}

	public void setStoreEFTAdj(String storeEFTAdj) {
		this.storeEFTAdj = storeEFTAdj;
	}
	
	public String getDepositAdj() {
		return depositAdj;
	}

	public void setDepositAdj(String depositAdj) {
		this.depositAdj = depositAdj;
	}
	
	public String getPaymentApplied() {
		return paymentApplied;
	}

	public void setPaymentApplied(String paymentApplied) {
		this.paymentApplied = paymentApplied;
	}
}
