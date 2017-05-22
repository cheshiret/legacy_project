/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.legacy.orms.license;

import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.Product;

/**
 * @Description: this is the product info(Register,title,inspection vehicle) used in our system
 * 
 * @author ssong
 * @Date  Jun 27, 2012
 */
public class VehicleRTI extends Product{
	
	private String vehicleType;
	
	private Map<String, Boolean> custClass;
	
	private String validToDate;
	
	private String validMonths;
	
	private String month;
	
	private String day;
	
	private String validYears;
	
	private String cycleStartYear;
	
	private String advanceRenewalDays;
	
	private String lateRenewal;
	
	private String lateRenewUnit;
	
	private Map<String,Boolean> boatUseTyp;
	
	private String minLenthOfFt;
	
	private String minLenthOfIn;
	
	private String maxLenthOfFt;
	
	private String maxLenthOfIn;
	
	/**
	 * @return the vehicleType
	 */
	public String getVehicleType() {
		return vehicleType;
	}

	/**
	 * @param vehicleType the vehicleType to set
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**
	 * @return the custClass
	 */
	public Map<String, Boolean> getCustClass() {
		return custClass;
	}

	/**
	 * @param custClass the custClass to set
	 */
	public void setCustClass(Map<String, Boolean> custClass) {
		this.custClass = custClass;
	}

	/**
	 * @return the validToDate
	 */
	public String getValidToDate() {
		return validToDate;
	}

	/**
	 * @param validToDate the validToDate to set
	 */
	public void setValidToDate(String validToDate) {
		this.validToDate = validToDate;
	}

	/**
	 * @return the validMonths
	 */
	public String getValidMonths() {
		return validMonths;
	}

	/**
	 * @param validMonths the validMonths to set
	 */
	public void setValidMonths(String validMonths) {
		this.validMonths = validMonths;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return the validYears
	 */
	public String getValidYears() {
		return validYears;
	}

	/**
	 * @param validYears the validYears to set
	 */
	public void setValidYears(String validYears) {
		this.validYears = validYears;
	}

	/**
	 * @return the cycleStartYear
	 */
	public String getCycleStartYear() {
		return cycleStartYear;
	}

	/**
	 * @param cycleStartYear the cycleStartYear to set
	 */
	public void setCycleStartYear(String cycleStartYear) {
		this.cycleStartYear = cycleStartYear;
	}

	/**
	 * @return the advanceRenewalDays
	 */
	public String getAdvanceRenewalDays() {
		return advanceRenewalDays;
	}

	/**
	 * @param advanceRenewalDays the advanceRenewalDays to set
	 */
	public void setAdvanceRenewalDays(String advanceRenewalDays) {
		this.advanceRenewalDays = advanceRenewalDays;
	}

	/**
	 * @return the lateRenewal
	 */
	public String getLateRenewal() {
		return lateRenewal;
	}

	/**
	 * @param lateRenewal the lateRenewal to set
	 */
	public void setLateRenewal(String lateRenewal) {
		this.lateRenewal = lateRenewal;
	}

	/**
	 * @return the lateRenewUnit
	 */
	public String getLateRenewUnit() {
		return lateRenewUnit;
	}

	/**
	 * @param lateRenewUnit the lateRenewUnit to set
	 */
	public void setLateRenewUnit(String lateRenewUnit) {
		this.lateRenewUnit = lateRenewUnit;
	}

	/**
	 * @return the boatUseTyp
	 */
	public Map<String, Boolean> getBoatUseTyp() {
		return boatUseTyp;
	}

	/**
	 * @param boatUseTyp the boatUseTyp to set
	 */
	public void setBoatUseTyp(Map<String, Boolean> boatUseTyp) {
		this.boatUseTyp = boatUseTyp;
	}

	/**
	 * @return the minLenthOfFt
	 */
	public String getMinLenthOfFt() {
		return minLenthOfFt;
	}

	/**
	 * @param minLenthOfFt the minLenthOfFt to set
	 */
	public void setMinLenthOfFt(String minLenthOfFt) {
		this.minLenthOfFt = minLenthOfFt;
	}

	/**
	 * @return the minLenthOfIn
	 */
	public String getMinLenthOfIn() {
		return minLenthOfIn;
	}

	/**
	 * @param minLenthOfIn the minLenthOfIn to set
	 */
	public void setMinLenthOfIn(String minLenthOfIn) {
		this.minLenthOfIn = minLenthOfIn;
	}

	/**
	 * @return the maxLenthOfFt
	 */
	public String getMaxLenthOfFt() {
		return maxLenthOfFt;
	}

	/**
	 * @param maxLenthOfFt the maxLenthOfFt to set
	 */
	public void setMaxLenthOfFt(String maxLenthOfFt) {
		this.maxLenthOfFt = maxLenthOfFt;
	}

	/**
	 * @return the maxLenthOfIn
	 */
	public String getMaxLenthOfIn() {
		return maxLenthOfIn;
	}

	/**
	 * @param maxLenthOfIn the maxLenthOfIn to set
	 */
	public void setMaxLenthOfIn(String maxLenthOfIn) {
		this.maxLenthOfIn = maxLenthOfIn;
	}
}
