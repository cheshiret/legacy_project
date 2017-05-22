/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.legacy.orms.license;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Sep 17, 2012
 */
public class DatePeriodInfo {
	private String id;
	private String status;
	private String code;
	private String description;
	private List<DatePeriodLicenseYearInfo> licenseYears = new ArrayList<DatePeriodLicenseYearInfo>();
	private String privilegeLotteryProduct;
	private String hunt;
	
	
	/**
	 * @return the privilegeLotteryProduct
	 */
	public String getPrivilegeLotteryProduct() {
		return privilegeLotteryProduct;
	}
	/**
	 * @param privilegeLotteryProduct the privilegeLotteryProduct to set
	 */
	public void setPrivilegeLotteryProduct(String privilegeLotteryProduct) {
		this.privilegeLotteryProduct = privilegeLotteryProduct;
	}
	/**
	 * @return the hunt
	 */
	public String getHunt() {
		return hunt;
	}
	/**
	 * @param hunt the hunt to set
	 */
	public void setHunt(String hunt) {
		this.hunt = hunt;
	}
	
			
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the status
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
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
		
	
	public List<DatePeriodLicenseYearInfo> getLicenseYears() {
		return licenseYears;
	}
	
	public void setLicenseYears(List<DatePeriodLicenseYearInfo> licenseYears) {
		this.licenseYears = licenseYears;
	}
	
	public void setLicenseYears(DatePeriodLicenseYearInfo... licenseYears) {
		this.licenseYears = new ArrayList<DatePeriodLicenseYearInfo>();
		for (DatePeriodLicenseYearInfo ly : licenseYears) {
			this.licenseYears.add(ly);
		}
	}
	
	/** Get Date period info by the specific license year with the format: DP3 (Jan 01-Dec 31; Jan 01; Jan 01-Dec 31; Jan 01)*/
	public String getDatePeriodInfoForWeb(String ly) {
		String text = "";
		for (int i = 0; i < this.licenseYears.size(); i++) {
			if (this.licenseYears.get(i).getLicenseYear().equals(ly)) {
				text += this.code + " " + this.licenseYears.get(i).getAllDatesInfo();
				break;
			}
		}
		return text;
	}
}
