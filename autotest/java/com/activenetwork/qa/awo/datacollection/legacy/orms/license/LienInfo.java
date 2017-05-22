package com.activenetwork.qa.awo.datacollection.legacy.orms.license;

public class LienInfo {
	
	private String lienId;
	
	private String status;
	
	private String dateOfLien;
	
	private String lienAmount;
	
	private String dateOfRelease;
	
	private LienCompanyDetailsInfo lienCompanyDetailsInfo;//it should be no instance by default
	
	private String creationDateTime;
	
	private String creationUser;

	public String getLienId() {
		return lienId;
	}

	public void setLienId(String lienId) {
		this.lienId = lienId;
	}

	public String getStauts() {
		return status;
	}

	public void setStauts(String stauts) {
		this.status = stauts;
	}

	public String getDateOfLien() {
		return dateOfLien;
	}

	public void setDateOfLien(String dateOfLien) {
		this.dateOfLien = dateOfLien;
	}

	public String getLienAmount() {
		return lienAmount;
	}

	public void setLienAmount(String lienAmount) {
		this.lienAmount = lienAmount;
	}

	public String getDateOfRelease() {
		return dateOfRelease;
	}

	public void setDateOfRelease(String dateOfRelease) {
		this.dateOfRelease = dateOfRelease;
	}

	public LienCompanyDetailsInfo getLienCompanyDetailsInfo() {
		return lienCompanyDetailsInfo;
	}

	public void setLienCompanyDetailsInfo(
			LienCompanyDetailsInfo lienCompanyDetailsInfo) {
		this.lienCompanyDetailsInfo = lienCompanyDetailsInfo;
	}
	
	public String getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(String creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	
	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}
}
