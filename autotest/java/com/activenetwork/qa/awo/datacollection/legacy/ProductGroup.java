/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.legacy;

/**
 * @Description: this class is for product group info
 * 
 * @author Lesley
 * @Date  July 3, 2013
 */
public class ProductGroup {
	private String prdGrpID;
	private String prdGrpCatgory;
	private String prdGrpCode;
	private String prdGrpDescription;
	private String prdGrpName;
	private String prdGrpIcon;
	private boolean isPrdGrpActive;
	private String prdGrpUnitofStay;
	private String prdGrpSubCategory;
	private String showStatus; // for search criteria
	
	public String getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}

	public String getPrdGrpID() {
		return prdGrpID;
	}
	
	public void setPrdGrpID(String prdGrpID) {
		this.prdGrpID = prdGrpID;
	}
	
	public String getPrdGrpCatgory() {
		return prdGrpCatgory;
	}
	
	public void setPrdGrpCatgory(String prdGrpCatgory) {
		this.prdGrpCatgory = prdGrpCatgory;
	}
	
	public String getPrdGrpCode() {
		return prdGrpCode;
	}
	
	public void setPrdGrpCode(String prdGrpCode) {
		this.prdGrpCode = prdGrpCode;
	}
	
	public String getPrdGrpDescription() {
		return prdGrpDescription;
	}
	
	public void setPrdGrpDescription(String prdGrpDescription) {
		this.prdGrpDescription = prdGrpDescription;
	}
	
	public String getPrdGrpName() {
		return prdGrpName;
	}
	
	public void setPrdGrpName(String prdGrpName) {
		this.prdGrpName = prdGrpName;
	}
	
	public String getPrdGrpIcon() {
		return prdGrpIcon;
	}
	
	public void setPrdGrpIcon(String prdGrpIcon) {
		this.prdGrpIcon = prdGrpIcon;
	}
	
	public boolean isPrdGrpActive() {
		return isPrdGrpActive;
	}
	
	public void setPrdGrpActive(boolean isPrdGrpActive) {
		this.isPrdGrpActive = isPrdGrpActive;
	}
	
	public String getPrdGrpUnitofStay() {
		return prdGrpUnitofStay;
	}
	
	public void setPrdGrpUnitofStay(String prdGrpUnitofStay) {
		this.prdGrpUnitofStay = prdGrpUnitofStay;
	}
	
	public String getPrdGrpSubCategory() {
		return prdGrpSubCategory;
	}
	
	public void setPrdGrpSubCategory(String prdGrpSubCategory) {
		this.prdGrpSubCategory = prdGrpSubCategory;
	}
}
