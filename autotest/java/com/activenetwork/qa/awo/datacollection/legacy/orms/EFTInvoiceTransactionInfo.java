package com.activenetwork.qa.awo.datacollection.legacy.orms;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Jun 4, 2012
 */
public class EFTInvoiceTransactionInfo {
	
	private String id;
	
	private String invoiceId;
	
	private String type;
	
	private String transactionDate;
	
	private String transactionUser;
	
	private String transactionLocation;
	
	private String transmissionId;
	
	private String returnTransactionId;
	
	private String comment;
	
	private String transmissionJobId;
	
	private double oldInvoiceAmount;
	
	private double newInvoiceAmount;
	
	private String oldInvoiceTransmissionStatus;
	
	private String newInvoiceTransmissionStatus;
	
	private String oldTransmissionDueDate;
	
	private String newTransmissionDueDate;

	public String getId() {
		return id;  
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTransmissionId() {
		return transmissionId;
	}

	public void setTransmissionId(String transmissionId) {
		this.transmissionId = transmissionId;
	}

	public String getReturnTransactionId() {
		return returnTransactionId;
	}

	public void setReturnTransactionId(String returnTransactionId) {
		this.returnTransactionId = returnTransactionId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTransmissionJobId() {
		return transmissionJobId;
	}

	public void setTransmissionJobId(String transmissionJobId) {
		this.transmissionJobId = transmissionJobId;
	}

	public double getOldInvoiceAmount() {
		return oldInvoiceAmount;
	}

	public void setOldInvoiceAmount(double oldInvoiceAmount) {
		this.oldInvoiceAmount = oldInvoiceAmount;
	}

	public double getNewInvoiceAmount() {
		return newInvoiceAmount;
	}

	public void setNewInvoiceAmount(double newInvoiceAmount) {
		this.newInvoiceAmount = newInvoiceAmount;
	}

	public String getOldInvoiceTransmissionStatus() {
		return oldInvoiceTransmissionStatus;
	}

	public void setOldInvoiceTransmissionStatus(String oldInvoiceTransmissionStatus) {
		this.oldInvoiceTransmissionStatus = oldInvoiceTransmissionStatus;
	}

	public String getNewInvoiceTransmissionStatus() {
		return newInvoiceTransmissionStatus;
	}

	public void setNewInvoiceTransmissionStatus(String newInvoiceTransmissionStatus) {
		this.newInvoiceTransmissionStatus = newInvoiceTransmissionStatus;
	}

	public String getOldTransmissionDueDate() {
		return oldTransmissionDueDate;
	}

	public void setOldTransmissionDueDate(String oldTransmissionDueDate) {
		this.oldTransmissionDueDate = oldTransmissionDueDate;
	}

	public String getNewTransmissionDueDate() {
		return newTransmissionDueDate;
	}

	public void setNewTransmissionDueDate(String newTransmissionDueDate) {
		this.newTransmissionDueDate = newTransmissionDueDate;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionUser() {
		return transactionUser;
	}

	public void setTransactionUser(String transactionUser) {
		this.transactionUser = transactionUser;
	}

	public String getTransactionLocation() {
		return transactionLocation;
	}

	public void setTransactionLocation(String transactionLocation) {
		this.transactionLocation = transactionLocation;
	}
}
