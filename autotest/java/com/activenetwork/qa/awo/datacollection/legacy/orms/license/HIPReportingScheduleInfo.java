package com.activenetwork.qa.awo.datacollection.legacy.orms.license;

import java.util.List;

public class HIPReportingScheduleInfo {
	private String scheduleID;
	private String status;
	private String licenseYear;
	private String periodStartDate;
	private String periodEndDate;
	private List<String> executionDates;
	private String creationUser;
	private String creationLocation;
	private String creationDate;
	private String lastUpdatedUser;
	private String lastUpdatedLocation;
	private String lastUpdatedDate;
	
	public void setScheduleID(String scheduleID){
		this.scheduleID = scheduleID;
	}
	
	public String getScheduleID(){
		return this.scheduleID;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public void setLicenseYear(String licenseYear){
		this.licenseYear = licenseYear;
	}
	
	public String getLicenseYear(){
		return this.licenseYear;
	}
	
	public void setPeriodStartDate(String periodStartDate){
		this.periodStartDate = periodStartDate;
	}
	
	public String getPeriodStartDate(){
		return this.periodStartDate;
	}
	
	public void setPeriodEndDate(String periodEndDate){
		this.periodEndDate = periodEndDate;
	}
	
	public String getPeriodEndDate(){
		return this.periodEndDate;
	}
	
	public void setExecutionDates(List<String> executionDates){
		this.executionDates = executionDates;
	}

	public List<String> getExecutionDates(){
		return this.executionDates;
	}
	
	public void setCreationUser(String creationUser){
		this.creationUser = creationUser;
	}
	
	public String getCreationUser(){
		return this.creationUser;
	}
	
	public void setCreationLocation(String creationLocation){
		this.creationLocation = creationLocation;
	}
	
	public String getCreationLocation(){
		return this.creationLocation;
	}
	
	public void setCreationDate(String creationDate){
		this.creationDate = creationDate;
	}
	
	public String getCreationDate(){
		return this.creationDate;
	}
	
	public void setLastUpdatedUser(String lastUpdatedUser){
		this.lastUpdatedUser = lastUpdatedUser;
	}
	
	public String getLastUpdatedUser(){
		return this.lastUpdatedUser;
	}
	
	public void setLastUpdatedLocation(String lastUpdatedLocation){
		this.lastUpdatedLocation = lastUpdatedLocation;
	}
	
	public String getLastUpdatedLocation(){
		return this.lastUpdatedLocation;
	}
	
	public void setLastUpdatedDate(String lastUpdatedDate){
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
	public String getLastUpdatedDate(){
		return this.lastUpdatedDate;
	}
} 
