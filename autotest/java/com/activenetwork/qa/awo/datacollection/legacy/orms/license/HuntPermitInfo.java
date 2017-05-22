package com.activenetwork.qa.awo.datacollection.legacy.orms.license;

import com.activenetwork.qa.testapi.util.StringUtil;

public class HuntPermitInfo {
	
	private String huntPermitID;
	
	private String huntPermitStatus;
	
	private String huntCode;
	
	private String applicantType;
	
	private String permit;
	
	private String minAge;
	
	private String maxAge;
	
	private String residencyStatus;
	
	private String creationUser;
	
	private String creationLocation;
	
	private String creationDate;
	
	private String lastUpdateUser;
	
	private String lastUpdateLocation;
	
	private String lastUpdateDate;
	
	public void setHuntPermitID(String huntPermitID){
		this.huntPermitID = huntPermitID;
	}
	
	public String getHuntPermitID(){
		return this.huntPermitID;
	}
	
	public void setHuntPermitStatus(String huntPermitStatus){
		this.huntPermitStatus = huntPermitStatus;
	}
	
	public String getHuntPermitStatus(){
		return this.huntPermitStatus;
	}
	
	public void setHuntCode(String huntCode){
		this.huntCode = huntCode;
	}
	
	public String getHuntCode(){
		return this.huntCode;
	}
	
	public void setApplicantType(String applicantType){
		this.applicantType = applicantType;
	}
	
	public String getApplicantType(){
		return this.applicantType;
	}
	
	public void setPermit(String prdCode, String prdName){
		if(StringUtil.notEmpty(prdCode) && StringUtil.notEmpty(prdName)){
			this.permit = prdCode + "-" + prdName;
		}else{
			this.permit = prdCode;
		}		
	}
	
	public void setPermit(String permit){
		this.permit = permit;
	}
	
	public String getPermit(){
		return this.permit;
	}
	
	public void setMinAge(String minAge){
		this.minAge = minAge;
	}
	
	public String getMinAge(){
		return this.minAge;
	}
	
	public void setMaxAge(String maxAge){
		this.maxAge = maxAge;
	}
	
	public String getMaxAge(){
		return this.maxAge;
	}
	
	public void setResidencyStatus(String residencyStatus){
		this.residencyStatus = residencyStatus;
	}
	
	public String getResidencyStatus(){
		return this.residencyStatus;
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
	
	public void setLastUpdateUser(String lastUpdateUser){
		this.lastUpdateUser = lastUpdateUser;
	}
	
	public String getLastUpdateUser(){
		return this.lastUpdateUser;
	}
	
	public void setLastUpdateLocation(String lastUpdateLocation){
		this.lastUpdateLocation = lastUpdateLocation;
	}
	
	public String getLastUpdateLocation(){
		return this.lastUpdateLocation;
	}
	
	public void setLastUpdateDate(String lastUpdateDate){
		this.lastUpdateDate = lastUpdateDate;
	}
	
	public String getLastUpdateDate(){
		return this.lastUpdateDate;
	}
}
