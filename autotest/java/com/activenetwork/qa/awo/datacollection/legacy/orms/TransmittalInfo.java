package com.activenetwork.qa.awo.datacollection.legacy.orms;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Jul 16, 2013
 */
public class TransmittalInfo {

	// search criteria
	private String searchBy;
	private String searchValue;
	private String dateRange;
	private String start;
	private String end;
	private String status;
	
	// search result list
	private String transmittalID;
	private String traceNumber;
	private String transmittalStatus;
	private String createDateTime;
	private String createLoc;
	private String revenueLoc;
	private String createUser;
	private String depositNums;
	private String transmittalTotal;
	private String adjustTotal;
	private String netTotal;

	public String getSearchBy() {
		return searchBy;
	}
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getDateRange() {
		return dateRange;
	}
	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTransmittalID() {
		return transmittalID;
	}
	public void setTransmittalID(String transmittalID) {
		this.transmittalID = transmittalID;
	}
	public String getTraceNumber() {
		return traceNumber;
	}
	public void setTraceNumber(String traceNumber) {
		this.traceNumber = traceNumber;
	}
	public String getTransmittalStatus() {
		return transmittalStatus;
	}
	public void setTransmittalStatus(String transmittalStatus) {
		this.transmittalStatus = transmittalStatus;
	}
	public String getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getCreateLoc() {
		return createLoc;
	}
	public void setCreateLoc(String createLoc) {
		this.createLoc = createLoc;
	}
	public String getRevenueLoc() {
		return revenueLoc;
	}
	public void setRevenueLoc(String revenueLoc) {
		this.revenueLoc = revenueLoc;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getDepositNums() {
		return depositNums;
	}
	public void setDepositNums(String depositNums) {
		this.depositNums = depositNums;
	}
	public String getTransmittalTotal() {
		return transmittalTotal;
	}
	public void setTransmittalTotal(String transmittalTotal) {
		this.transmittalTotal = transmittalTotal;
	}
	public String getAdjustTotal() {
		return adjustTotal;
	}
	public void setAdjustTotal(String adjustTotal) {
		this.adjustTotal = adjustTotal;
	}
	public String getNetTotal() {
		return netTotal;
	}
	public void setNetTotal(String netTotal) {
		this.netTotal = netTotal;
	}
}
