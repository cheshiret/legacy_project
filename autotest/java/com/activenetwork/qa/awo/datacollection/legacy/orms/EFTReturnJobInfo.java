package com.activenetwork.qa.awo.datacollection.legacy.orms;

import java.util.ArrayList;

import com.activenetwork.qa.awo.util.TransmissionAndReturnFileUtil;

/**
 * 
 * @author pchen
 * @Date  August 15, 2012
 *
 */
public class EFTReturnJobInfo {
	public String returnFile;
	
	private String jobId;
	
	private String jobStatus;

	
	private String matchingStatus;
	
	private String status;
	
	private String location;
	
	private String fileName;
	
	private String returnsNum;
	
	private String correctionsNum;
	
	private String runStartDateTime;
	
	private String runEndDateTime;
	
	private String runLocation;
	
	private String runUser;
	
	//Search filter info
	private String searchType;

	private String searchValue;

	private String searchDate;

	private String fromDate;
		
	private String toDate;
	
	public ArrayList<String> returnedVendorName = new ArrayList<String>();
	public TransmissionAndReturnFileUtil transInfo = new TransmissionAndReturnFileUtil();
	public ArrayList<EFTReturnTransactionInfo> returnTransactions = new ArrayList<EFTReturnTransactionInfo>();
	
	/**
	 * @return runUser
	 */
	public String getRunUser() {
		return runUser;
	}

	/**
	 * @param runUser 
	 */
	public void setRunUser(String runUser) {
		this.runUser = runUser;
	}
	
	/**
	 * @return runLocation
	 */
	public String getRunLocation() {
		return runLocation;
	}

	/**
	 * @param runLocation 
	 */
	public void setRunLocation(String runLocation) {
		this.runLocation = runLocation;
	}
	
	/**
	 * @return runStartDateTime
	 */
	public String getRunStartDateTime() {
		return runStartDateTime;
	}

	/**
	 * @param runEndDateTime 
	 */
	public void setRunStartDateTime(String runStartDateTime) {
		this.runStartDateTime = runStartDateTime;
	}
	
	/**
	 * @return runEndDateTime
	 */
	public String getRunEndDateTime() {
		return runEndDateTime;
	}

	/**
	 * @param runEndDateTime 
	 */
	public void setRunEndDateTime(String runEndDateTime) {
		this.runEndDateTime = runEndDateTime;
	}
	
	/**
	 * @return correctionsNum
	 */
	public String getCorrectionsNum() {
		return correctionsNum;
	}

	/**
	 * @param correctionsNum 
	 */
	public void setCorrectionsNum(String correctionsNum) {
		this.correctionsNum = correctionsNum;
	}
	
	/**
	 * @return returnsNum
	 */
	public String getReturnsNum() {
		return returnsNum;
	}

	/**
	 * @param returnsNum 
	 */
	public void setReturnsNum(String returnsNum) {
		this.returnsNum = returnsNum;
	}
	
	/**
	 * @return fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName 
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * @return location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location 
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * @return matchingStatus
	 */
	public String getMatchingStatus() {
		return matchingStatus;
	}

	/**
	 * @param matchingStatus 
	 */
	public void setMatchingStatus(String matchingStatus) {
		this.matchingStatus = matchingStatus;
	}
	
	
	/**
	 * @return jobId
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * @param jobId 
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
	/**
	 * @return jobStatus
	 */
	public String getJobStatus() {
		return jobStatus;
	}

	/**
	 * @param jobStatus 
	 */
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	
	/**
	 * @return searchType
	 */
	public String getSearchType() {
		return searchType;
	}

	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	/**
	 * @return searchValue
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

	/**
	 * @return searchDate
	 */
	public String getSearchDate() {
		return searchDate;
	}

	/**
	 * @param searchDate the searchDate to set
	 */
	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

	/**
	 * @return fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setReturnedVendorName(String... vendors){
		for(String vendor:vendors){
			this.returnedVendorName.add(vendor);
		}
	}
}
